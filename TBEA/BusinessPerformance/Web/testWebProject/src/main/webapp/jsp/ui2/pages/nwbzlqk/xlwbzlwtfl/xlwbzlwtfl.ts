/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../nwbzlqkdef.ts"/>
///<reference path="../nwbzlqk.ts"/>
///<reference path="../../jqgrid/jqgrid.d.ts"/>

module plugin {
    export let xlwbzlwtfl : number = framework.basic.endpoint.lastId();
}
declare var echarts;
module nwbzlqk {
    export module xlwbzlwtfl {

        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createZtTable(gridName:string, ydjd:YDJDType):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
					Node.create({name : "问题类别", align : TextAlign.Center}),
                    Node.create({name : "鲁缆"})
                        .append(Node.create({name : ydjd == YDJDType.YD ? "当月" : "当期"}))
                        .append(Node.create({name : ydjd == YDJDType.YD ? "年度累计" : "去年同期"})),
                    Node.create({name : "新缆"})
                        .append(Node.create({name : ydjd == YDJDType.YD ? "当月" : "当期"}))
                        .append(Node.create({name : ydjd == YDJDType.YD ? "年度累计" : "去年同期"})),
                    Node.create({name : "德缆"})
                        .append(Node.create({name : ydjd == YDJDType.YD ? "当月" : "当期"}))
                        .append(Node.create({name : ydjd == YDJDType.YD ? "年度累计" : "去年同期"})),
                    Node.create({name : "合计"})
                        .append(Node.create({name : ydjd == YDJDType.YD ? "当月" : "当期"}))
                        .append(Node.create({name : ydjd == YDJDType.YD ? "年度累计" : "去年同期"}))
                ], gridName);
            }
            public static createFdwTable(gridName:string, ydjd:YDJDType):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    Node.create({name : "问题类别", align : TextAlign.Center}),
                    Node.create({name : ydjd == YDJDType.YD ? "当月" : "当期"}),
                    Node.create({name : ydjd == YDJDType.YD ? "年度累计" : "去年同期"})
                ], gridName);
            }
        }

        class ShowView extends ZlPluginView {
            static ins = new ShowView();
            private mData:CpzlqkResp;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/xlwbzlwtfl/update.do", false);
            private mDt: string;
            private mCompType:Util.CompanyType;

            getId():number {
                return plugin.xlwbzlwtfl;
            }

            protected isSupported(compType:Util.CompanyType):boolean {
                return compType == Util.CompanyType.LLGS || compType == Util.CompanyType.XLC
                    ||compType == Util.CompanyType.DLGS || compType == Util.CompanyType.XLCY;
            }
            onEvent(e:framework.route.Event):any {
                switch (e.id) {
                    case Event.ZLFE_IS_COMPANY_SUPPORTED:
                        return true;
                        break;
                }
                return super.onEvent(e);
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "/BusinessManagement/xlwbzlwtfl/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId:compType,
                        ydjd:this.mYdjdType
                 });
            }

            private option():Option {
                return <Option>this.mOpt;
            }

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
                this.mCommentSubmit.get({
                    data : JSON.stringify([[param.condition, param.comment]])
                }).then((jsonData:any)=>{
                    Util.Toast.success("提交成功", undefined);
                });
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

            private toCtVal(val:string) : any{
                let index = val.lastIndexOf('%');
                if (index >= 0){
                    return val.substring(0, index);
                }
                return val == '--' ? 0 : val;
            }

            public refresh() : void {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
                this.adjustSize();
                //if (this.mData.tjjg.length > 0){
                //    this.$(this.option().ctarea).show();
                //
                //    if (this.mYdjdType == YDJDType.JD) {
                //        this.$(this.option().ct1).hide();
                //        this.$(this.option().ct).css("width", "100%");
                //        this.updateJDEchart();
                //    } else {
                //        this.$(this.option().ct).show();
                //        this.$(this.option().ct1).show();
                //        this.$(this.option().ct).css("width", "50%");
                //        this.$(this.option().ct1).css("width", "50%");
                //        this.updateYDEchart();
                //    }
                //}
            }

            public init(opt:Option):void {
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "外部质量问题分类统计情况");
            }

            adjustSize() {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                if (this.mData.tjjg.length > 0){
                    this.$(this.option().ctarea).show();

                    if (this.mYdjdType == YDJDType.JD) {
                        this.$(this.option().ct1).parent().hide();
                        this.$(this.option().ct).parent().css("width", "100%");
                        this.updateJDEchart();
                    } else {
                        this.$(this.option().ct).parent().show();
                        this.$(this.option().ct1).parent().show();
                        this.$(this.option().ct).parent().css("width", "50%");
                        this.$(this.option().ct1).parent().css("width", "50%");
                        this.updateYDEchart();
                    }
                }
            }

            private createJqassist():JQTable.JQGridAssistant{
                var pagername = this.jqgridName() + "pager";
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table><div id='" + pagername + "'></div>");
                if (this.mCompType == Util.CompanyType.XLCY){
                    this.tableAssist = JQGridAssistantFactory.createZtTable(this.jqgridName(), this.mYdjdType);
                }else{
                    this.tableAssist = JQGridAssistantFactory.createFdwTable(this.jqgridName(), this.mYdjdType);
                }
                this.tableAssist.mergeTitle();
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
                    rowNum: 15,
                    autoScroll: true,
                    pager:'#' + this.jqgridName() + "pager",
                });


            }

            //private updateTable():void {
            //    var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
            //    var tableAssist:JQTable.JQGridAssistant;
            //    if (this.mCompType == Util.CompanyType.XLCY){
            //        tableAssist = JQGridAssistantFactory.createZtTable(name, this.mYdjdType);
            //    }else{
            //        tableAssist = JQGridAssistantFactory.createFdwTable(name, this.mYdjdType);
            //    }
            //
            //    let pagername = name + "pager"
            //    var parent = this.$(this.option().tb);
            //    parent.empty();
            //    parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
            //    tableAssist.mergeTitle();
            //    this.$(name).jqGrid(
            //        tableAssist.decorate({
            //            datatype: "local",
            //            data: tableAssist.getData(this.mData.tjjg),
            //            multiselect: false,
            //            drag: false,
            //            resize: false,
            //            height: this.mData.tjjg.length > 20 ? 20 * 22 : '100%',
            //            width: 1200,
            //            shrinkToFit: true,
            //            autoScroll: true,
            //            rowNum: this.mData.tjjg.length + 10,
            //            viewrecords : true,
            //            pager:'#' + pagername,
            //        }));
            //}

            private updateYDEchart():void {
                let title = "外部质量问题分类统计情况";
                let wtlb = [];
                let wtlb1 = [];
                for (let i = 0 ;i < this.mData.tjjg.length; ++i){
                    wtlb.push(this.mData.tjjg[i][0]);
                    wtlb1.push(this.mData.tjjg[i][0]);
                }

                let legend:any = {
                    orient : 'vertical',
                    x : 'left',
                    data:wtlb
                };
                let legend1:any = {
                    orient : 'vertical',
                    x : 'left',
                    data:wtlb1
                };

                let tooltip : any = {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                };

                let series = [{
                    name: "问题类别",
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data: []
                }];

                let series1 = [{
                    name: "问题类别",
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data: []
                }];

                if (this.mCompType == Util.CompanyType.XLCY){
                    for (let i = 0 ;i < this.mData.tjjg.length; ++i){
                        series[0].data.push({
                            name: wtlb[i],
                            value : this.toCtVal(this.mData.tjjg[i][7] + "")
                        });
                        series1[0].data.push({
                            name: wtlb1[i],
                            value : this.toCtVal(this.mData.tjjg[i][8] + "")
                        });
                    }
                }else{
                    for (let i = 0 ;i < this.mData.tjjg.length; ++i){
                        series[0].data.push({
                            name: wtlb[i],
                            value : this.toCtVal(this.mData.tjjg[i][1] + "")
                        });
                        series1[0].data.push({
                            name: wtlb1[i],
                            value : this.toCtVal(this.mData.tjjg[i][2] + "")
                        });
                    }
                }

                for (let i = series[0].data.length - 1 ;i >= 0; --i){
                    if (series[0].data[i].value == 0){
                        series[0].data.splice(i, 1);
                        wtlb.splice(i, 1);
                    }

                    if (series1[0].data[i].value == 0){
                        series1[0].data.splice(i, 1);
                        wtlb1.splice(i, 1);
                    }
                }

                let option = {
                    title: {
                        text: "当月",
                        x:'center'
                    },
                    tooltip: tooltip,
                    legend: legend,
                    toolbox: {
                        show: true,
                    },
                    calculable: true,
                    series: series
                };

                let option1 = {
                    title: {
                        text: "年度累计",
                        x:'center'
                    },
                    tooltip: tooltip,
                    legend: legend1,
                    toolbox: {
                        show: true,
                    },
                    calculable: true,
                    series: series1
                };
                if (this.mData.tjjg.length != 0) {
                    echarts.init(this.$(this.option().ct)[0]).setOption(option);
                    echarts.init(this.$(this.option().ct1)[0]).setOption(option1);
                }else{
                    this.$(this.option().ct).hide();
                    this.$(this.option().ct1).hide();
                }
            }

            private updateJDEchart():void {
                let dq = 0;
                let qn = 0;
                if (this.mCompType == Util.CompanyType.XLCY){
                    for (let i = 0 ;i < this.mData.tjjg.length; ++i){
                        dq += parseInt(this.toCtVal(this.mData.tjjg[i][7] + ""));
                        qn += parseInt(this.toCtVal(this.mData.tjjg[i][8] + ""));
                    }
                }else{
                    for (let i = 0 ;i < this.mData.tjjg.length; ++i){
                        dq += parseInt(this.toCtVal(this.mData.tjjg[i][1] + ""));
                        qn += parseInt(this.toCtVal(this.mData.tjjg[i][2] + ""));
                    }
                }
                let title = "外部质量问题分类统计情况";
                let legend:Array<string> = ["当期", "去年同期"];
                let echart = this.option().ct;

                let series = [];
                var xData:string[] = ["内部质量问题分类统计"];
                let tooltip : any = {
                    trigger: 'axis'
                };
                let yAxis : any = [
                    {
                        type: 'value'
                    }
                ];


                series.push({
                    name: legend[0],
                    type: 'bar',
                    smooth: true,
                    // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                    data: [dq]
                });
                series.push({
                    name: legend[1],
                    type: 'bar',
                    smooth: true,
                    // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                    data: [qn]
                });



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
                            boundaryGap: true,
                            data: xData.length < 1 ? [0] : xData
                        }
                    ],
                    yAxis: yAxis,
                    series: series
                };

                echarts.init(this.$(echart)[0]).setOption(option);
            }
        }
    }
}
