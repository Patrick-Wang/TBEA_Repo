/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../messageBox.ts"/>
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../nyzbscqkdef.ts"/>
declare var $:any;


module pluginEntry {
    export let nyzbscxl : number = framework.basic.endpoint.lastId();
}

module nyzbscqk {
    export module nyzbscxlEntry {
        import TextAlign = JQTable.TextAlign;
        import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, readOnly:boolean):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("矿区", "kq", true, TextAlign.Center),
                    new JQTable.Node("煤种", "mz", true, TextAlign.Center),
                    new JQTable.Node("价格", "yxl", readOnly)
                ], gridName);
            }
        }

        interface Option extends framework.basic.PluginOption {
            tb:string;
        }

        class EntryView extends framework.basic.EntryPluginView {
            static ins = new EntryView();
            private mData:Array<string[]>;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("/BusinessManagement/nyzbscxl/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("/BusinessManagement/nyzbscxl/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("/BusinessManagement/nyzbscxl/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;

            getId():number {
                return pluginEntry.nyzbscxl;
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginSave(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    submitData[i].push(allData[i][0]);
                    submitData[i].push(allData[i][3]);
                    submitData[i][1] = submitData[i][1].replace(new RegExp(' ', 'g'), '');
                }
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        this.pluginUpdate(dt, compType);
                        Util.Toast.success("保存 成功");
                    } else {
                        Util.Toast.failed(resp.message);
                    }
                });
            }

            public  pluginSubmit(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    submitData[i].push(allData[i][0]);
                    submitData[i].push(allData[i][3]);
                    submitData[i][1] = submitData[i][1].replace(new RegExp(' ', 'g'), '');
                }
                this.mAjaxSubmit.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        this.pluginUpdate(dt, compType);
                        Util.Toast.success("提交 成功");

                    } else {
                        Util.Toast.failed(resp.message);
                    }
                });
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mAjaxUpdate.get({
                        date: date,
                        companyId: compType
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

            protected init(opt:Option):void {
                framework.router.fromEp(this).to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_REGISTER, "月销量");
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
                this.mTableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), false);
                this.mTableAssist.mergeRow(0);
                return this.mTableAssist;
            }

            private updateTable():void {
                this.createJqassist();

                this.mTableAssist.create({
                    dataWithId:this.mData,
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