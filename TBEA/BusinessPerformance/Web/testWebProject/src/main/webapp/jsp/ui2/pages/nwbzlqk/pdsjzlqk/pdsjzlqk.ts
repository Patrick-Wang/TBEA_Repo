/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../nwbzlqkdef.ts"/>
///<reference path="../nwbzlqk.ts"/>
///<reference path="../../jqgrid/jqgrid.d.ts"/>

module plugin {
    export let pdsjzlqk : number = framework.basic.endpoint.lastId();
}
declare var echarts;
module nwbzlqk {
    export module pdsjzlqk {

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
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/pdsjzlqk/update.do", false);
            private mDt: string;
            private mCompType:Util.CompanyType;

            getId():number {
                return plugin.pdsjzlqk;
            }

            protected isSupported(compType:Util.CompanyType):boolean {
                return compType == Util.CompanyType.SBZTFGS || compType == Util.CompanyType.HBDQFGS
                    || compType == Util.CompanyType.XBZTGS || compType == Util.CompanyType.TBGS
                    || compType == Util.CompanyType.XBXBGS || compType == Util.CompanyType.PDCY;
            }
            onEvent(e:framework.route.Event):any {
                switch (e.id) {
                    case Event.ZLFE_IS_COMPANY_SUPPORTED:
                        return true;
                }
                return super.onEvent(e);
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "/BusinessManagement/pdsjzlqk/export.do?" + Util.Ajax.toUrlParam({
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

            private toCtVal(val:string){
                let index = val.lastIndexOf('%');
                if (index >= 0){
                    return val.substring(0, index);
                }
                return val == '--' ? 0 : val;
            }

            private splitLongString(val:string): string{
                return val.replace("特变电工沈阳变压器集团有限公司中特分公司", "沈变中特")
                            .replace("特变电工衡阳变压器有限公司电气分公司", "衡变电气")
                            .replace("特变电工新疆变压器厂中特公司", "新变中特")
                            .replace("天津市特变电工变压器有限公司", "天变")
                            .replace("特变电工智能电气有限责任公司", "智能电气");
            }
            private findTotal(valShort:string):string{
                return valShort.replace(/\s/g, "");
                //for (let i = 0; i < this.mData.tjjg.length; ++i){
                //    if (valShort.indexOf('内部') >= 0){
                //        if (this.mData.tjjg[i][0].indexOf(valShort.replace(/\.\.\./g, "").replace("内部", "")) >= 0){
                //            return this.mData.tjjg[i][0] +　'内部';
                //        }
                //    }else{
                //        if (this.mData.tjjg[i][0].indexOf(valShort.replace(/\.\.\./g, "").replace("外部", "")) >= 0){
                //            return this.mData.tjjg[i][0] +　'外部';
                //        }
                //    }
                //}
                //return valShort;
            }

            private updateEchart():void {
                let title = "";
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
                        legend.push(this.splitLongString(this.mData.waveItems[i].name));

                        let data = [];
                        for (let j = 0; j < this.mData.waveItems[i].data.length; ++j){
                            data.push(this.mData.waveItems[i].data[j]);
                        }

                        series.push({
                            name: this.splitLongString(this.mData.waveItems[i].name),
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

                    if (this.mCompType == Util.CompanyType.PDCY){
                        for (let i = 0; i < this.mData.tjjg.length; ++i){
                            if (this.mData.tjjg[i][0].replace(/\s/g, "") == "合计"){
                                xData.push(this.splitLongString(this.mData.tjjg[i][0] + "内部"));
                                dy.push(this.toCtVal(this.mData.tjjg[i][1]));
                                qntq.push(this.toCtVal(this.mData.tjjg[i][4]));
                                xData.push(this.splitLongString(this.mData.tjjg[i][0] + "外部"));
                                dy.push(this.toCtVal(this.mData.tjjg[i][2]));
                                qntq.push(this.toCtVal(this.mData.tjjg[i][5]));
                            }
                        }

                        tooltip.formatter = (params) => {
                            let ret = this.findTotal(params[0][1]);
                            for (let i = 0; i < params.length; ++i) {
                                ret += "<br/>" + params[i][0] + ' : ' + params[i][2];
                            }
                            return ret;
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
                this.adjustSize();
            }

            public init(opt:Option):void {
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "设计质量问题情况");
            }

            adjustSize() {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                this.updateEchart();
            }

            private createJqassist():JQTable.JQGridAssistant{
                var pagername = this.jqgridName() + "pager";
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table><div id='" + pagername + "'></div>");
                if (this.mCompType == Util.CompanyType.PDCY){
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
            //    if (this.mCompType == Util.CompanyType.PDCY){
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
        }
    }
}
