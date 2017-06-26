/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../messageBox.ts"/>
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../sbdczclwcqkdef.ts"/>
declare var $:any;


module pluginEntry {
    export let cpclwcqk : number = framework.basic.endpoint.lastId();
    export let cpczclwcqk_byq : number = framework.basic.endpoint.lastId();
    export let cpczclwcqk_xl : number = framework.basic.endpoint.lastId();
}

module sbdczclwcqk {
    export module cpclwcqkEntry {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, readOnly : boolean, date : string, unit:string):JQTable.JQGridAssistant {
                let curDate : Date = new Date(Date.parse(date.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
                let year = curDate.getFullYear();
                let data = [];
                let node : JQTable.Node;
                let titleNodes : JQTable.Node[] = [];

                node = new JQTable.Node("产品", "cpclwcqkEntry_cp", true, TextAlign.Left);
                titleNodes.push(node);

                //node = new JQTable.Node(year + "年" + month + "月", "cpclwcqkEntry_riqi", false, TextAlign.Center);

                //
                //node.append(new JQTable.Node("产量(" + unit + ")", "cpclwcqkEntry_cl", false));
                //
                titleNodes.push(new JQTable.Node("产值", "cpclwcqkEntry_cz", false));
                titleNodes.push(new JQTable.Node("产量(" + unit + ")", "cpclwcqkEntry_cl", false));

                return new JQTable.JQGridAssistant(titleNodes, gridName);
            }
        }

        interface Option extends framework.basic.PluginOption {
            tb:string;
        }

        class EntryView extends framework.basic.EntryPluginView {
            static ins = new EntryView();
            private mData:Array<string[]>;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("/BusinessManagement/cpclwcqk/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("/BusinessManagement/cpclwcqk/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("/BusinessManagement/cpclwcqk/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;
            private mCompType:Util.CompanyType;
            private mSbdczclwcqkType:sbdczclwcqk.SbdczclwcqkType;

            getId():number {
                return pluginEntry.cpclwcqk;
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginSave(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 2; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 2] = submitData[i][j - 2].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType,
                    sbdczclwcqkType: this.mSbdczclwcqkType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        this.pluginUpdate(dt, compType);
                        Util.MessageBox.tip("保存 成功");
                    } else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            }

            public  pluginSubmit(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 2; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 2] = submitData[i][j - 2].replace(new RegExp(' ', 'g'), '');
                        //if ("" == submitData[i][j - 2]) {
                        //    Util.MessageBox.tip("有空内容 无法提交")
                        //    return;
                        //}
                    }
                }
                this.mAjaxSubmit.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType,
                    sbdczclwcqkType: this.mSbdczclwcqkType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        this.pluginUpdate(dt, compType);
                        Util.MessageBox.tip("提交 成功");
                    } else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = compType;
                this.mAjaxUpdate.get({
                        date: date,
                        companyId: compType,
                        sbdczclwcqkType: this.mSbdczclwcqkType
                    })
                    .then((jsonData:any) => {
                        this.mData = jsonData;
                        this.refresh();
                    });
            }

            public refresh():void {
                if (this.mData == undefined) {
                    return;
                }

                this.updateTable();
            }

            isSupported(compType:Util.CompanyType):boolean {
                if (this.mSbdczclwcqkType == sbdczclwcqk.SbdczclwcqkType.SBDCZCLWCQK_BYQ) {
                    if (compType == Util.CompanyType.SBGS ||
                        compType == Util.CompanyType.HBGS ||
                        compType == Util.CompanyType.XBC ||
                        compType == Util.CompanyType.TBGS
                    ) {
                        return true;
                    }
                } else if (this.mSbdczclwcqkType == sbdczclwcqk.SbdczclwcqkType.SBDCZCLWCQK_XL){
                    if (compType == Util.CompanyType.LLGS ||
                        compType == Util.CompanyType.XLC ||
                        compType == Util.CompanyType.DLGS
                    ) {
                        return true;
                    }
                }
                return false;
            }

            protected init(opt:Option):void {
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(pluginEntry.cpczclwcqk_byq, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "产值产量完成情况");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(pluginEntry.cpczclwcqk_xl, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "产值产量完成情况");
            }

            onEvent(e: framework.route.Event): any {
                if (e.road != undefined) {
                    switch (e.road[e.road.length - 1]) {
                        case pluginEntry.cpczclwcqk_byq:
                            this.mSbdczclwcqkType = sbdczclwcqk.SbdczclwcqkType.SBDCZCLWCQK_BYQ;
                            break;
                        case pluginEntry.cpczclwcqk_xl:
                            this.mSbdczclwcqkType = sbdczclwcqk.SbdczclwcqkType.SBDCZCLWCQK_XL;
                            break;
                    }
                }
                return super.onEvent(e);
            }

            adjustSize() {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                this.mTableAssist.resizeHeight(maxTableBodyHeight);

                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
            }

            private createJqassist():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table>");
                this.mTableAssist = JQGridAssistantFactory.createTable(name, false, this.mDt,
                    this.mSbdczclwcqkType == sbdczclwcqk.SbdczclwcqkType.SBDCZCLWCQK_BYQ
                        ? "万kVA(其中电抗器产量万kvar":
                        "导线：吨；电缆：千米；电缆附件：件");
                return this.mTableAssist;
            }

            private updateTable():void {
                this.createJqassist();

                this.mTableAssist.create({
                    data: this.mData,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.mTableAssist.getColNames().length * 400,
                    shrinkToFit: true,
                    rowNum: 2000,
                    autoScroll: true,
                    assistEditable: true
                });

                this.adjustSize();
            }
        }
    }
}
