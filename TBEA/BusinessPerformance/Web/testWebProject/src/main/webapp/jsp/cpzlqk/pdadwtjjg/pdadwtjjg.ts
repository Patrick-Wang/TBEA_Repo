/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cpzlqkdef.ts"/>
///<reference path="../cpzlqk.ts"/>

module plugin {
    export let pdadwtjjg : number = framework.basic.endpoint.lastId();
}
declare var echarts;
module cpzlqk {
    export module pdadwtjjg {
        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, ydjd:YDJDType):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    Node.create({name : "单位", align : TextAlign.Center}),
                    Node.create({name : "产品：变压器", align : TextAlign.Center, hidden:true}),
                    Node.create({name : ydjd == YDJDType.YD ? "当月" : "当季度"})
                        .append(Node.create({name : "不合格数(台)"}))
                        .append(Node.create({name : "总数(台)"}))
                        .append(Node.create({name : "合格率%"})),
                    Node.create({name : ydjd == YDJDType.YD ? "年度累计" : "去年同期"})
                        .append(Node.create({name : "不合格数(台)"}))
                        .append(Node.create({name : "总数(台)"}))
                        .append(Node.create({name : "合格率%"}))
                ], gridName);
            }
        }

        class ShowView extends ZlPluginView {
            static ins = new ShowView();
            private mData:CpzlqkResp;
            private mAjax:Util.Ajax = new Util.Ajax("../pdadwtjjg/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            private mCompType:Util.CompanyType;
            private mCommentGet:Util.Ajax = new Util.Ajax("../report/zlfxUpdate.do", false);
            private mCommentSubmit:Util.Ajax = new Util.Ajax("../report/zlfxSubmit.do", false);
            getId():number {
                return plugin.pdadwtjjg;
            }
            protected isSupported(compType:Util.CompanyType):boolean {
                return compType == Util.CompanyType.PDCY;
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
                            Util.MessageBox.tip("保存成功", undefined, 1000);
                        });
                        break;
                }
                return super.onEvent(e);
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "../pdadwtjjg/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId:compType,
                        ydjd:this.mYdjdType,
                        all: this.mCompType == Util.CompanyType.PDCY
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
                        all: this.mCompType == Util.CompanyType.PDCY
                    })
                    .then((jsonData:any) => {
                        this.mData = jsonData;
                        this.refresh();
                    });
            }

            public refresh() : void{
                if ( this.mData == undefined){
                    return;
                }

                this.updateTable();
                //if (this.mCompType !=  Util.CompanyType.BYQCY){
                    this.$(this.option().ctarea).show();
                    this.updateEchart();
                //}else{
                //    this.$(this.option().ctarea).hide();
                //}
            }

            private toCtVal(val:string){
                let index = val.lastIndexOf('%');
                if (index >= 0){
                    return val.substring(0, index);
                }
                return val == '--' ? 0 : val;
            }

            private updateEchart():void {
                let title = "按产单位计结果";
                let legend:Array<string> = [];
                let echart = this.option().ct;

                let series = [];
                var xData:string[] = [];
                let tooltip : any = {
                    trigger: 'axis',
                    formatter : (params) => {
                        let ret = params[0][1];
                        for (let i = 0; i < params.length; ++i) {
                            ret += "<br/>" + params[i][0] + ' : ' + params[i][2] + "%";
                        }
                        return ret;
                    }
                };
                let yAxis : any = [
                    {
                        type: 'value',
                        axisLabel : {
                            formatter: '{value} %'
                        }
                    }
                ];
                if (this.mYdjdType == YDJDType.YD){
                    for (let i in this.mData.waveItems){
                        legend.push(this.mData.waveItems[i].name);
                        series.push({
                            name: this.mData.waveItems[i].name,
                            type: this.mYdjdType == YDJDType.YD ? 'line' : "bar",
                            smooth: true,
                            // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                            data: this.mData.waveItems[i].data
                        });
                    }
                    for (let i = 1; i <= 12; ++i){
                        xData.push(i + "月");
                    }
                }else{
                    let dy = [];
                    let qntq = [];
                    for (let i = 0; i < this.mData.tjjg.length; ++i){
                        if (!(this.mData.tjjg[i][0].replace(/\s/g, "") == "合计")){
                            xData.push(this.mData.tjjg[i][0]);
                            dy.push(this.toCtVal(this.mData.tjjg[i][3]));
                            qntq.push(this.toCtVal(this.mData.tjjg[i][6]));
                        }
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

            public init(opt:Option):void {
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "按单位统计结果");
            }

			private getMonth():number{
				let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
				return month;
			}
			
            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mYdjdType);
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
                        viewrecords : true
                    }));
            }
        }
    }
}
