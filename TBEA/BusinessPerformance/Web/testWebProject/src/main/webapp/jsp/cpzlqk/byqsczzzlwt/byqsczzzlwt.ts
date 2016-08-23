/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cpzlqkdef.ts"/>
///<reference path="../cpzlqk.ts"/>
///<reference path="../../jqgrid/jqgrid.d.ts"/>

module plugin {
    export let byqsczzzlwt : number = framework.basic.endpoint.lastId();
}
declare var echarts;
module cpzlqk {
    export module byqsczzzlwt {

        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createZtTable(gridName:string, ydjd:YDJDType):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
					Node.create({name : "单位", align : TextAlign.Center}),
                    Node.create({name : ydjd == YDJDType.YD ? "当月" : "当期"})
                        .append(Node.create({name : "内部反馈质量问题数量"}))
                        .append(Node.create({name : "外部反馈质量问题数量"}))
                        .append(Node.create({name : "合计"})),
                    Node.create({name : ydjd == YDJDType.YD ? "年度累计" : "去年同期"})
                        .append(Node.create({name : "内部反馈质量问题数量"}))
                        .append(Node.create({name : "外部反馈质量问题数量"}))
                        .append(Node.create({name : "合计"}))
                ], gridName);
            }
            public static createFdwTable(gridName:string, ydjd:YDJDType):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    Node.create({name : "月份", align : TextAlign.Center}),
                    Node.create({name : "内部质量问题数量"}),
                    Node.create({name : "外部质量问题数量"}),
                    Node.create({name : "合计"})
                ], gridName);
            }
        }

        class ShowView extends ZlPluginView {
            static ins = new ShowView();
            private mData:CpzlqkResp;
            private mAjax:Util.Ajax = new Util.Ajax("../byqsczzzlwt/update.do", false);
            private mCommentGet:Util.Ajax = new Util.Ajax("../report/zlfxUpdate.do", false);
            private mCommentSubmit:Util.Ajax = new Util.Ajax("../report/zlfxSubmit.do", false);
            private mDt: string;
            private mCompType:Util.CompanyType;

            getId():number {
                return plugin.byqsczzzlwt;
            }

            protected isSupported(compType:Util.CompanyType):boolean {
                return compType == Util.CompanyType.SBGS || compType == Util.CompanyType.HBGS
                    ||compType == Util.CompanyType.XBC || compType == Util.CompanyType.BYQCY;
            }
            onEvent(e:framework.route.Event):any {
                switch (e.id) {
                    case Event.ZLFE_IS_COMPANY_SUPPORTED:
                        return true;
                    case Event.ZLFE_SAVE_COMMENT:
                        let param = {
                            condition:Util.Ajax.toUrlParam({
                                url : this.mAjax.baseUrl(),
                                date: this.mDt,
                                companyId:this.mCompType,
                                ydjd:this.mYdjdType
                            }),
                            comment:e.data
                        }
                        this.mCommentSubmit.get({
                            data : JSON.stringify([[param.condition, param.comment]])
                        }).then((jsonData:any)=>{
                            Util.MessageBox.tip("保存成功", undefined);
                        });
                        break;
                }
                return super.onEvent(e);
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "../byqsczzzlwt/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId:compType,
                        ydjd:this.mYdjdType
                 });
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = compType;
                this.mCommentGet.get({condition:Util.Ajax.toUrlParam({
                    url : this.mAjax.baseUrl(),
                    date: date,
                    companyId:compType,
                    ydjd:this.mYdjdType
                }),compId:compType}).then((jsonData:any)=>{
                    if (jsonData.deny == "deny"){
                        framework.router
                            .fromEp(this)
                            .to(framework.basic.endpoint.FRAME_ID)
                            .send(Event.ZLFE_COMMENT_DENY);
                    }else {
                        framework.router
                            .fromEp(this)
                            .to(framework.basic.endpoint.FRAME_ID)
                            .send(Event.ZLFE_COMMENT_UPDATED, jsonData.comment);
                    }
                });
                this.mAjax.get({
                        date: date,
                        companyId:compType,
                        ydjd:this.mYdjdType,
                        all: this.mCompType == Util.CompanyType.BYQCY
                    })
                    .then((jsonData:any) => {
                        this.mData = jsonData;
                        this.refresh();
                    });
            }

            private toCtVal(val:string){
                let index = val.lastIndexOf('%');
                if (index >= 0){
                    return val.substring(0, index);
                }
                return val == '--' ? 0 : val;
            }


            private updateEchart():void {
                let title = "生产制造质量问题情况";
                let legend:Array<string> = [];
                let echart = this.option().ct;

                let series = [];
                var xData:string[] = [];
                let tooltip : any = {
                    trigger: 'axis'
                };
                let yAxis : any = [
                    {
                        type: 'value'
                    }
                ];
                if (this.mYdjdType == YDJDType.YD){
                    for (let i in this.mData.waveItems){
                        legend.push(this.mData.waveItems[i].name);

                        let data = [];
                        for (let j = 0; j < this.mData.waveItems[i].data.length; ++j){
                            data.push((parseFloat("" + this.mData.waveItems[i].data[j]) * 100).toFixed(1));
                        }

                        series.push({
                            name: this.mData.waveItems[i].name,
                            type: 'line',
                            smooth: true,
                            // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                            data: data
                        });
                    }
                    for (let i = 0; i < 12; ++i){
                        xData.push((i + 1) + "月");
                    }
                }else{
                    let dy = [];
                    let qntq = [];

                    if (this.mCompType == Util.CompanyType.BYQCY){
                        for (let i = 0; i < this.mData.tjjg.length; ++i){
                            if (this.mData.tjjg[i][0].replace(/\s/g, "") == "合计"){
                                xData.push("内部质量问题");
                                dy.push(this.toCtVal(this.mData.tjjg[i][1]));
                                qntq.push(this.toCtVal(this.mData.tjjg[i][4]));
                                xData.push("外部质量问题");
                                dy.push(this.toCtVal(this.mData.tjjg[i][2]));
                                qntq.push(this.toCtVal(this.mData.tjjg[i][5]));
                            }
                        }
                    }else{
                        xData.push( "内部质量问题");
                        xData.push( "外部质量问题");
                        dy.push(this.toCtVal(this.mData.tjjg[0][1]));
                        dy.push(this.toCtVal(this.mData.tjjg[0][2]));
                        dy.push(this.toCtVal(this.mData.tjjg[1][1]));
                        qntq.push(this.toCtVal(this.mData.tjjg[1][2]));
                    }

                    legend = ["当月", "去年同期"];
                    series.push({
                        name: legend[0],
                        type: 'bar',
                        smooth: true,
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: dy
                    });
                    series.push({
                        name: legend[1],
                        type: 'bar',
                        smooth: true,
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: qntq
                    });
                }


                var option = {
                    title: {
                        text: title
                    },
                    tooltip: tooltip,
                    legend: {
                        data: legend
                    },
                    toolbox: {
                        show: true,
                    },
                    calculable: false,
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: this.mYdjdType == YDJDType.YD ? false : true,
                            data: xData.length < 1 ? [0] : xData
                        }
                    ],
                    yAxis: yAxis,
                    series: series
                };

                echarts.init(this.$(echart)[0]).setOption(option);

            }

            public refresh() : void{
                if ( this.mData == undefined){
                    return;
                }
                this.updateTable();

                this.$(this.option().ctarea).show();
                this.updateEchart();
            }

            public init(opt:Option):void {
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "生产制造质量问题情况");
            }

			private getMonth():number{
				let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
				return month;
			}

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist:JQTable.JQGridAssistant;
                if (this.mCompType == Util.CompanyType.BYQCY){
                    tableAssist = JQGridAssistantFactory.createZtTable(name, this.mYdjdType);
                }else{
                    tableAssist = JQGridAssistantFactory.createFdwTable(name, this.mYdjdType);
                }

                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                tableAssist.mergeColum(0);
                tableAssist.mergeTitle();
                tableAssist.mergeRow(0);
                this.$(name).jqGrid(
                    tableAssist.decorate({
						datatype: "local",
						data: tableAssist.getData(this.mData.tjjg),
                        multiselect: false,
                        drag: false,
                        resize: false,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        rowNum: 1000,
                        viewrecords : true,
                        caption:"生产制造质量问题情况"
                    }));
            }
        }
    }
}
