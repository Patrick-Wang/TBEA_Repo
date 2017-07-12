/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cpzlqkdef.ts"/>
///<reference path="../cpzlqk.ts"/>


module cpzlqk {
    export module byqcpycssbhgxxfb {
        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, title: string[], type:YDJDType ):JQTable.JQGridAssistant {
                let nodes : Node[];
                if (YDJDType.YD == type){
                    nodes = [Node.create({name : "单位", align: TextAlign.Center})];

                }else{
                    nodes = [
                        Node.create({name : "单位", align: TextAlign.Center}),
                        Node.create({name : "单位", align: TextAlign.Center})];
                }
                for (let i in title){
                    nodes.push(Node.create({name : title[i]}));
                }
                nodes.push(Node.create({name : "合计"}));
                return new JQTable.JQGridAssistant(nodes, gridName);

            }
        }

        interface BhgxxfbResp{
            bhglbs : string[];
            result : string[][];
            waveItems: WaveItem[];
            zt:number;
        }
        class ShowView extends ZlPluginView  {
            static ins = new ShowView();
            private mData:BhgxxfbResp;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/byqcpycssbhgxxfb/update.do", false);
            private tableAssist:JQTable.JQGridAssistant;
            private mAjaxStatus:Util.Ajax = new Util.Ajax("/BusinessManagement/byqcpycssbhgwtmx/updateStatus.do", false);
            private mDt: string;
            private mCompType:Util.CompanyType;
            getId():number {
                return plugin.byqcpycssbhgxxfb;
            }
            protected isSupported(compType:Util.CompanyType):boolean {
                return compType == Util.CompanyType.SBGS || compType == Util.CompanyType.HBGS
                    || compType == Util.CompanyType.XBC || compType == Util.CompanyType.BYQCY;
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "/BusinessManagement/byqcpycssbhgxxfb/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId:compType,
                        ydjd:this.mYdjdType,
                        all: this.mCompType == Util.CompanyType.BYQCY
                    });
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = compType;
                let comment : Comment;
                let bhgxxfbResp : BhgxxfbResp;
                let complete = (jsonData:any)=>{
                    if (undefined != jsonData.result){
                        bhgxxfbResp = jsonData;
                    }else{
                        comment = jsonData;
                    }

                    if (comment != undefined && bhgxxfbResp != undefined){
                        this.mData = bhgxxfbResp;
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
                                zt : bhgxxfbResp.zt});
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

                //this.mCommentGet.get({condition:Util.Ajax.toUrlParam({
                //    url : this.mAjax.baseUrl(),
                //    date: date,
                //    companyId:compType,
                //    ydjd:this.mYdjdType
                //}),compId:compType}).then((jsonData:any)=>{
                //    framework.router
                //        .fromEp(this)
                //        .to(framework.basic.endpoint.FRAME_ID)
                //        .send(Event.ZLFE_COMMENT_UPDATED, jsonData);
                //});
                //
                //this.mAjax.get({
                //        date: date,
                //        companyId:compType,
                //        ydjd:this.mYdjdType,
                //        all: this.mCompType == Util.CompanyType.BYQCY,
                //        pageType:pageType
                //    })
                //    .then((jsonData:any) => {
                //        this.mData = jsonData;
                //        this.refresh();
                //    });
            }

            public refresh() : void{
                if ( this.mData == undefined){
                    return;
                }

                this.updateTable();

                this.$(this.option().ctarea).show();


                this.adjustSize();
            }

            onEvent(e:framework.route.Event):any {
                switch (e.id) {
                    case Event.ZLFE_IS_COMPANY_SUPPORTED:
                        return true;
                }
                return super.onEvent(e);
            }

            public init(opt:Option):void {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "产品送试不合格现象分布");
            }

            adjustSize() {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                if (this.mYdjdType == YDJDType.YD){
                    if (this.mData.waveItems.length == 0){
                        this.$(this.option().ctarea).hide();
                    }else {
                        this.$(this.option().ct1).parent().hide();
                        this.$(this.option().ct).parent().css("width", "100%");
                        this.updateYDEchart();
                    }
                }else{
                    if (this.mData.result.length == 0){
                        this.$(this.option().ctarea).hide();
                    }else {
                        this.$(this.option().ct).parent().show();
                        this.$(this.option().ct1).parent().show();
                        this.$(this.option().ct).parent().css("width", "50%");
                        this.$(this.option().ct1).parent().css("width", "50%");
                        this.updateJDEchart();
                    }
                }
                framework.router.to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_ADJUST_HEADER);
            }

            private createJqassist():JQTable.JQGridAssistant{
                var pagername = this.jqgridName() + "pager";
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table><div id='" + pagername + "'></div>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mData.bhglbs, this.mYdjdType);
                this.tableAssist.mergeColum(0);
                this.tableAssist.mergeTitle(0);
                this.tableAssist.mergeRow();
                return this.tableAssist;
            }

            private updateTable():any {
                this.createJqassist();

                let data = [];
                if (this.mYdjdType == YDJDType.YD){
                    data = this.mData.result;
                }else{
                    let i = 0;
                    for (; i < this.mData.result.length; ++i){
                        data.push(["当期"].concat(this.mData.result[i]));
                        if (this.mData.result[i][0].replace(/\s/g, "") == "合计"){
                            break;
                        }
                    }
                    ++i;
                    for (; i < this.mData.result.length; ++i){
                        data.push(["去年同期"].concat(this.mData.result[i]));
                    }
                }

                this.tableAssist.create({
                    data: data,
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

            private updateYDEchart():void {
                let title = "按产单位计结果";
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
                for (let i in this.mData.waveItems){
                    legend.push(this.mData.waveItems[i].name);
                    series.push({
                        name: this.mData.waveItems[i].name,
                        type: 'line',
                        smooth: true,
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: this.mData.waveItems[i].data
                    });
                }
                for (let i = 1; i <= 12; ++i){
                    xData.push(i + "月");
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
                            boundaryGap: false,
                            data: xData.length < 1 ? [0] : xData
                        }
                    ],
                    yAxis: yAxis,
                    series: series
                };
                this.$(this.option().ct).empty();
                this.$(this.option().ct).removeAttr("_echarts_instance_");
                echarts.init(this.$(this.option().ct)[0]).setOption(option);
            }

            private toCtVal(val:string){
                let index = val.lastIndexOf('%');
                if (index >= 0){
                    return val.substring(0, index);
                }
                return val == '--' ? 0 : val;
            }

            private updateJDEchart():void {
                let legend:any = {
                    orient : 'vertical',
                    x : 'left',
                    data:this.mData.bhglbs
                };
                let tooltip : any = {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                };

                let series = [{
                    name: "不合格类型",
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data: []
                }];


                let i = 0;
                for ( ;i < this.mData.result.length; ++i){
                    if (this.mData.result[i][0].replace(/\s/g, "") == "合计"){
                        for(let j = 0; j < this.mData.bhglbs.length; ++j){
                            series[0].data.push({
                                name: this.mData.bhglbs[j],
                                value : this.mData.result[i][j + 1]
                            });
                        }
                        break;
                    }


                }

                let option = {
                    title: {
                        text: "当期",
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

                series = [{
                    name: "不合格类型",
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data: []
                }];

                ++i;
                for (;i < this.mData.result.length; ++i){
                    if (this.mData.result[i][0].replace(/\s/g, "") == "合计") {
                        for (let j = 0; j < this.mData.bhglbs.length; ++j) {
                            series[0].data.push({
                                name: this.mData.bhglbs[j],
                                value: this.mData.result[i][j + 1]
                            });
                        }
                        break;
                    }
                }
                if(series[0].data.length != 0){
                    let option1 = {
                        title: {
                            text: "去年同期",
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
                    this.$(this.option().ct1).empty();
                    this.$(this.option().ct1).removeAttr("_echarts_instance_");
                    echarts.init(this.$(this.option().ct1)[0]).setOption(option1);
                }else{
                    this.$(this.option().ct).css("width", "100%");
                    this.$(this.option().ct1).hide();
                }
                this.$(this.option().ct).empty();
                this.$(this.option().ct).removeAttr("_echarts_instance_");
                echarts.init(this.$(this.option().ct)[0]).setOption(option);
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
                this.mAjaxStatus.get({
                    date: this.mDt,
                    companyId:this.mCompType,
                    zt : Util.IndiStatus.SUBMITTED
                }).then(()=>{

                });
                this.mCommentSubmit.get({
                    data : JSON.stringify([[param.condition, param.comment]])
                }).then((jsonData:any)=>{
                    Util.Toast.tip("提交成功", undefined);
                });
            }
        }
    }
}
