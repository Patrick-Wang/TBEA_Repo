/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cpzlqkdef.ts"/>
///<reference path="../cpzlqk.ts"/>

module plugin {

}

module cpzlqk {
    export module pdcpycssbhgxxfb {
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
            zt:number;
            waveItems: WaveItem[];
        }
        class ShowView extends ZlPluginView  {
            static ins = new ShowView();
            private mData:BhgxxfbResp;
            private mAjax:Util.Ajax = new Util.Ajax("../pdcpycssbhgxxfb/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            private mCompType:Util.CompanyType;

            private mAjaxStatus:Util.Ajax = new Util.Ajax("../pdcpycssbhgwtmx/updateStatus.do", false);
            private mCommentGet:Util.Ajax = new Util.Ajax("../report/zlfxUpdate.do", false);
            private mCommentSubmit:Util.Ajax = new Util.Ajax("../report/zlfxSubmit.do", false);
            getId():number {
                return plugin.pdcpycssbhgxxfb;
            }
            protected isSupported(compType:Util.CompanyType):boolean {
                return compType == Util.CompanyType.SBZTFGS || compType == Util.CompanyType.HBDQFGS
                    || compType == Util.CompanyType.XBZTGS || compType == Util.CompanyType.TBGS
                    || compType == Util.CompanyType.XBXBGS || compType == Util.CompanyType.PDCY;
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "../pdcpycssbhgxxfb/export.do?" + Util.Ajax.toUrlParam({
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
                //        all: this.mCompType == Util.CompanyType.PDCY
                //    })
                //    .then((jsonData:any) => {
                //        this.mData = jsonData;
                //        this.refresh();
                //    });

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
            }

            public refresh() : void{
                if ( this.mData == undefined){
                    return;
                }

                this.updateTable();

                this.$(this.option().ctarea).show();

                if (this.mYdjdType == YDJDType.YD){
                    if (this.mData.waveItems.length == 0){
                        this.$(this.option().ctarea).hide();
                    }else{
                        this.$(this.option().ct1).hide();
                        this.$(this.option().ct).css("width", "100%");
                        this.updateYDEchart();
                    }
                }else{
                    if (this.mData.result.length == 0){
                        this.$(this.option().ctarea).hide();
                    }else {
                        this.$(this.option().ct).show();
                        this.$(this.option().ct1).show();
                        this.$(this.option().ct).css("width", "50%");
                        this.$(this.option().ct1).css("width", "50%");
                        this.updateJDEchart();
                    }
                }
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

			private getMonth():number{
				let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
				return month;
			}
			
            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mData.bhglbs, this.mYdjdType);

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
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                tableAssist.mergeRow(0);
                tableAssist.mergeColum(0);
                tableAssist.mergeTitle(0);
                this.$(name).jqGrid(
                    tableAssist.decorate({
						datatype: "local",
						data: tableAssist.getData(data),
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

                    echarts.init(this.$(this.option().ct1)[0]).setOption(option1);
                }else{
                    this.$(this.option().ct).css("width", "100%");
                    this.$(this.option().ct1).hide();
                }

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
                    Util.MessageBox.tip("提交成功", undefined);
                });
            }
        }
    }
}
