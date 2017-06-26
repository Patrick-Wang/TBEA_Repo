/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../sbdczclwcqkdef.ts"/>

module plugin {
    export let clylwcqk : number = framework.basic.endpoint.lastId();
    export let clylwcqk_xl : number = framework.basic.endpoint.lastId();
}

module sbdczclwcqk {
    export module clylwcqk {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, date:string):JQTable.JQGridAssistant {
                let curDate:Date = new Date(Date.parse(date.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
                let data = [];
                let node:JQTable.Node;
                let titleNodes:JQTable.Node[] = [];

                node = new JQTable.Node("主材", "clylwcqk_cp", true, TextAlign.Left);
                titleNodes.push(node);

                node = new JQTable.Node("上年度", "clylwcqk_snd", true, TextAlign.Center);
                for (let i = month; i <= 12; ++i) {
                    node.append(new JQTable.Node(i + "月", "clylwcqk_snd_" + i));
                }

                //if (month != 12) {
                    titleNodes.push(node);
                //}

                node = new JQTable.Node("本年度", "clylwcqk_bnd", true, TextAlign.Center);
                for (let i = 1; i <= month; ++i) {
                    node.append(new JQTable.Node(i + "月", "clylwcqk_bnd_" + i));
                }
                titleNodes.push(node);

                return new JQTable.JQGridAssistant(titleNodes, gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/clylwcqk/update.do", false);
            private mDt: string;
            private mCompType:Util.CompanyType;
            private mSbdczclwcqkType:sbdczclwcqk.SbdczclwcqkType;
            private tableAssist:JQTable.JQGridAssistant;

            getId():number {
                return plugin.clylwcqk;
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "/BusinessManagement/clylwcqk/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId:compType,
                        sbdczclwcqkType: this.mSbdczclwcqkType
                    });
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = compType;
                this.mAjax.get({
                        date: date,
                        companyId:compType,
                        sbdczclwcqkType: this.mSbdczclwcqkType
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
            }

            isSupported(compType:Util.CompanyType):boolean {
                if (this.mSbdczclwcqkType == sbdczclwcqk.SbdczclwcqkType.SBDCZCLWCQK_CLYLWCQK_XL) {

                    if (compType == Util.CompanyType.LLGS ||
                        compType == Util.CompanyType.XLC ||
                        compType == Util.CompanyType.DLGS
                    ) {
                        return true;
                    }
                } else {
                    if (compType == Util.CompanyType.SBGS ||
                        compType == Util.CompanyType.HBGS ||
                        compType == Util.CompanyType.XBC ||
                        compType == Util.CompanyType.TBGS
                    ) {
                        return true;
                    }
                }
                return false;
            }

            public init(opt:Option):void {
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.clylwcqk_xl, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "铜铝用量情况");
            }

            onEvent(e: framework.route.Event): any {
                if (e.road != undefined) {
                    switch (e.road[e.road.length - 1]) {
                        case plugin.clylwcqk_xl:
                            this.mSbdczclwcqkType = sbdczclwcqk.SbdczclwcqkType.SBDCZCLWCQK_CLYLWCQK_XL;
                            break;
                        default:
                            this.mSbdczclwcqkType = sbdczclwcqk.SbdczclwcqkType.SBDCZCLWCQK_CLYLWCQK_XL;
                    }
                }
                return super.onEvent(e);
            }

            adjustSize() {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150 - 23;
                this.tableAssist.resizeHeight(maxTableBodyHeight);

                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
            }

            private createJqassist():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mDt);
                return this.tableAssist;
            }

            private updateTable():any {
                this.createJqassist();

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
                this.adjustSize();
            }
        }
    }
}
