/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cpzlqkdef.ts"/>
///<reference path="../cpzlqk.ts"/>
///<reference path="../../jqgrid/jqgrid.d.ts"/>


declare var echarts;
declare var pageType;
module cpzlqk {
    export module byqacptjjg {

        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        import FRAME_ID = framework.basic.endpoint.FRAME_ID;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, ydjd:YDJDType):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
					Node.create({name : "产品类别", align : TextAlign.Center}),
                    Node.create({name : "产品类别", align : TextAlign.Center}),
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
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/byqacptjjg/update.do", false);
            private mAjaxStatus:Util.Ajax = new Util.Ajax("/BusinessManagement/byqacptjjg/updateStatus.do", false);
            private mDt: string;
            private mCompType:Util.CompanyType;
            private tableAssist:JQTable.JQGridAssistant;
            getId():number {
                return plugin.byqacptjjg;
            }

            protected isSupported(compType:Util.CompanyType):boolean {
                return compType == Util.CompanyType.SBGS || compType == Util.CompanyType.HBGS
                    ||compType == Util.CompanyType.XBC || compType == Util.CompanyType.BYQCY;
            }
            onEvent(e:framework.route.Event):any {
                switch (e.id) {
                    case Event.ZLFE_IS_COMPANY_SUPPORTED:
                        return true;
                }
                return super.onEvent(e);
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "/BusinessManagement/byqacptjjg/export.do?" + Util.Ajax.toUrlParam({
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
                let comment : Comment;
                let cpzlqkResp : CpzlqkResp;
                let complete = (jsonData:any)=>{
                    if (undefined != jsonData.tjjg){
                        cpzlqkResp = jsonData;
                    }else{
                        comment = jsonData;
                    }

                    if (comment != undefined && cpzlqkResp != undefined){
                        this.mData = cpzlqkResp;
                        this.refresh();
                        if (pageType == PageType.APPROVE){
                            framework.router
                                .fromEp(this)
                                .to(framework.basic.endpoint.FRAME_ID)
                                .send(Event.ZLFE_APPROVEAUTH_UPDATED);
                        }

                        framework.router
                            .fromEp(this)
                            .to(framework.basic.endpoint.FRAME_ID)
                            .send(Event.ZLFE_COMMENT_UPDATED, {
                                    comment : comment,
                                    zt : cpzlqkResp.zt});
                    }
                }

                this.mAjax.get({
                    date: date,
                    companyId:compType,
                    ydjd:this.mYdjdType,
                    pageType:pageType
                }).then(complete);

                this.mCommentGet.get({
                    condition : Util.Ajax.toUrlParam({
                                    url : this.mAjax.baseUrl(),
                                    date: date,
                                    companyId:compType,
                                    ydjd:this.mYdjdType}),
                    compId : compType
                }).then(complete);
            }

            private toCtVal(val:string){
                let index = val.lastIndexOf('%');
                if (index >= 0){
                    return val.substring(0, index);
                }
                return val == '--' ? 0 : val;
            }

            adjustSize() {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                this.updateEchart();
                framework.router.to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_ADJUST_HEADER);
            }

            private updateEchart():void {
                let title = "按产品统计结果";
                let legend:Array<string> = [];
                let echart = this.option().ct;

                let series = [];
                var xData:string[] = [];
                let tooltip : any = {
                    trigger: 'axis',
                    formatter : (params) => {
                        let ret = params[0][1];
                        for (let i = 0; i < params.length; ++i) {
                            ret += "<br/>" + params[i][0] + ' : ' + (params[i][2] * 1.0).toFixed(2) + "%";
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
                    for (let i = 0; i < this.mData.tjjg.length; ++i){
                        if (this.mData.tjjg[i][1].replace(/\s/g, "") == "合计"){
                            xData.push(this.mData.tjjg[i][0]);
                            dy.push(this.toCtVal(this.mData.tjjg[i][4]));
                            qntq.push(this.toCtVal(this.mData.tjjg[i][7]));
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

            public refresh() : void{
                if ( this.mData == undefined){
                    return;
                }
                this.updateTable();

                this.$(this.option().ctarea).show();
                this.adjustSize();
            }

            public init(opt:Option):void {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "按产品统计结果");
            }

            private createJqassist():JQTable.JQGridAssistant{
                var pagername = this.jqgridName() + "pager";
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table><div id='" + pagername + "'></div>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mYdjdType);
                this.tableAssist.mergeColum(0);
                this.tableAssist.mergeTitle();
                this.tableAssist.mergeRow(0);
                return this.tableAssist;
            }

            private updateTable():any {
                this.createJqassist();

                this.tableAssist.create({
                    data: this.mData.tjjg,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 10000,
                    autoScroll: true
                });


            }
			
            //private updateTable():void {
            //    var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
            //    var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mYdjdType);
            //    var parent = this.$(this.option().tb);
            //    parent.empty();
            //    parent.append("<table id='" + name + "'></table>");
            //    tableAssist.mergeColum(0);
            //    tableAssist.mergeTitle();
            //    tableAssist.mergeRow(0);
            //    this.$(name).jqGrid(
            //        tableAssist.decorate({
				//		datatype: "local",
				//		data: tableAssist.getData(this.mData.tjjg),
            //            multiselect: false,
            //            drag: false,
            //            resize: false,
            //            height: '100%',
            //            width: 1200,
            //            shrinkToFit: true,
            //            autoScroll: true,
            //            rowNum: 1000,
            //            viewrecords : true,
            //            caption:"按产品统计结果"
            //        }));
            //}

            onSaveComment(comment:any):void {
                let param = {
                    condition:Util.Ajax.toUrlParam({
                        url : this.mAjax.baseUrl(),
                        date: this.mDt,
                        companyId:this.mCompType,
                        ydjd:this.mYdjdType
                    }),
                    comment:comment
                };

                this.mAjaxStatus.get({
                    date: this.mDt,
                    companyId:this.mCompType,
                    zt : Util.IndiStatus.SUBMITTED
                }).then(()=>{

                });

                this.mCommentSubmit.get({
                    data : JSON.stringify([[param.condition, param.comment]])
                }).then((jsonData:any)=>{
                    Util.Toast.success("提交成功");
                });
            }
        }
    }
}
