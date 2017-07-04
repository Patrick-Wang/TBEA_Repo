/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cwgbjyxxjldef.ts"/>

module plugin {
    export let dyjyxxjl : number = framework.basic.endpoint.lastId();
}

module cwgbjyxxjl {
    export module dyjyxxjl {
        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        import EndpointProxy = framework.basic.EndpointProxy;
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                let node:JQTable.Node;
                let titleNodes:JQTable.Node[] = [];
                node = new JQTable.Node("科目", "cwgbjyxxjl_cp", true, TextAlign.Left, 300);
                titleNodes.push(node);

                node = new JQTable.Node("月度", "cwgbjyxxjl_snd", true, TextAlign.Center);
                node.append(new JQTable.Node("当月计划", "dyjyxxjl_dyjh"));
                node.append(new JQTable.Node("当月实际", "dyjyxxjl_dysj"));
                node.append(new JQTable.Node("计划完成率", "dyjyxxjl_jhwcl"));
                node.append(new JQTable.Node("去年同期", "dyjyxxjl_qntq"));
                node.append(new JQTable.Node("同比增幅", "dyjyxxjl_tbzf"));

                titleNodes.push(node);

                node = new JQTable.Node("年度", "cwgbjyxxjl_bnd", true, TextAlign.Center);
                node.append(new JQTable.Node("年度累计", "dyjyxxjl_ndlj"));
                node.append(new JQTable.Node("去年同期", "dyjyxxjl_qntq"));
                node.append(new JQTable.Node("同比增幅", "dyjyxxjl_tbzf"));

                titleNodes.push(node);

                return new JQTable.JQGridAssistant(titleNodes, gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/dyjyxxjl/update.do", false);
            private tableAssist:JQTable.JQGridAssistant;
            private mDt: string;

            getId():number {
                return plugin.dyjyxxjl;
            }
            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "/BusinessManagement/dyjyxxjl/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId: cpType
                    });
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mAjax.get({
                        date: date,
                        companyId:compType
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
                this.adjustSize();
            }

            public init(opt:Option):void {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "经营性现金流月度情况");
            }

            adjustSize() {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                this.tableAssist.resizeHeight(maxTableBodyHeight);

                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                //this.$(this.option().ct).css("height", "300px");
                //this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                //this.updateEchart(this.mFinalData);
            }

            private createJqassist():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());

                return this.tableAssist;
            }

            private updateTable():any {
                this.createJqassist();

                //var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                //let tableAssist:JQTable.JQGridAssistant;
                //if (this.mCurCbfxType == CbfxType.dmcbfx){
                //    this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.getYear(), this.getMonth());
                //}else{
                //    this.tableAssist = JQGridAssistantFactory.createQsTable(this.jqgridName());
                //}
                this.tableAssist.create({
                    data: this.mData,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 2000,
                    autoScroll: true
                });
                return ;
            }
        }
    }
}
