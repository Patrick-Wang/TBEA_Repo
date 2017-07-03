/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../nwbzlqkdef.ts"/>
///<reference path="../nwbzlqk.ts"/>
///<reference path="../../jqgrid/jqgrid.d.ts"/>

module plugin {
    export let byqwbzlwttjqk : number = framework.basic.endpoint.lastId();
}
declare var echarts;
module nwbzlqk {
    export module byqwbzlwttjqk {

        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        class JQGridAssistantFactory {

            public static createTable(gridName:string):JQTable.JQGridAssistant {
                let tbs = ["公司名称","问题发生时间","产品类型","生产号","产品型号",
                    "一级问题类型","二级问题类型","质量故障现象","问题详细描述",
                    "原材料数量","计量单","供应商名称","责任部门","填报人","产品发货日期",
                    "故障主体名称","原材料问题处理措施","现场处理措施","现场处理结果",
                    "用户单位名称","本单位现场售后人员","本单位现场售后人员电话"
                ];
                let nodes = [];
                for (let i = 0; i < tbs.length; ++i){
                    if (i < 1){
                        nodes.push( Node.create({name : tbs[i], align : TextAlign.Center, hidden : true}));
                    }else{
                        nodes.push( Node.create({name : tbs[i], align : TextAlign.Center}));
                    }
                }
                return new JQTable.JQGridAssistant(nodes, gridName);
            }
        }

        class ShowView extends ZlPluginView {
            onSaveComment(data:any):void {
            }
            static ins = new ShowView();
            private mData:CpzlqkResp;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/byqwbzlwttjqk/update.do", false);
            //private mCommentGet:Util.Ajax = new Util.Ajax("../report/zlfxUpdate.do", false);
            //private mCommentSubmit:Util.Ajax = new Util.Ajax("../report/zlfxSubmit.do", false);
            private mDt: string;
            private mCompType:Util.CompanyType;

            getId():number {
                return plugin.byqwbzlwttjqk;
            }

            protected isSupported(compType:Util.CompanyType):boolean {
                return compType == Util.CompanyType.SBGS || compType == Util.CompanyType.HBGS
                    ||compType == Util.CompanyType.XBC;
            }
            onEvent(e:framework.route.Event):any {
                switch (e.id) {
                    case Event.ZLFE_IS_YDJD_SUPPORTED:
                        return false;
                    case Event.ZLFE_IS_COMPANY_SUPPORTED:
                        return true;
                }
                return super.onEvent(e);
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "/BusinessManagement/byqwbzlwttjqk/export.do?" + Util.Ajax.toUrlParam({
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
                        framework.router
                            .fromEp(this)
                            .to(framework.basic.endpoint.FRAME_ID)
                            .send(Event.ZLFE_COMMENT_DENY);
                //this.mCommentGet.get({condition:Util.Ajax.toUrlParam({
                //    url : this.mAjax.baseUrl(),
                //    date: date,
                //    companyId:compType,
                //    ydjd:this.mYdjdType
                //}),compId:compType}).then((jsonData:any)=>{
                //    if (jsonData.deny == "deny"){
                //        framework.router
                //            .fromEp(this)
                //            .to(framework.basic.endpoint.FRAME_ID)
                //            .send(Event.ZLFE_COMMENT_DENY);
                //    }else {
                //        framework.router
                //            .fromEp(this)
                //            .to(framework.basic.endpoint.FRAME_ID)
                //            .send(Event.ZLFE_COMMENT_UPDATED, jsonData.comment);
                //    }
                //});
                this.mAjax.get({
                        date: date,
                        companyId:compType,
                        pageType:pageType
                        //ydjd:this.mYdjdType,
                        //all: this.mCompType == Util.CompanyType.BYQCY
                    })
                    .then((jsonData:any) => {
                        this.mData = jsonData;
                        this.refresh();
                    });
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
            }

            public init(opt:Option):void {
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "外部质量问题统计情况");
            }

            adjustSize() {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                //this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                //this.updateEchart();
            }

            private createJqassist():JQTable.JQGridAssistant{
                var pagername = this.jqgridName() + "pager";
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table><div id='" + pagername + "'></div>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
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
            //    tableAssist = JQGridAssistantFactory.createTable(name);
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
            //            width: 1300,
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
