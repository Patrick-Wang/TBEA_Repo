/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../messageBox.ts"/>
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../chgbdef.ts"/>
declare var $:any;


module pluginEntry {
    export let chzlbhqk : number = framework.basic.endpoint.lastId();
}

module chgb {
    export module chzlbhqkEntry {
        import TextAlign = JQTable.TextAlign;
        import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, readOnly : boolean):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "chzlbhqk_arq_entry", true, TextAlign.Center),
                    new JQTable.Node("5年以上", "chzlbhqk_aa_entry", false),
                    new JQTable.Node("4-5年", "chzlbhqk_ab_entry", false),
                    new JQTable.Node("3-4年", "chzlbhqk_ac_entry", false),
                    new JQTable.Node("2-3年", "chzlbhqk_ad_entry", false),
                    new JQTable.Node("1-2年", "chzlbhqk_ae_entry", false),
                    new JQTable.Node("1年以内", "chzlbhqk_af_entry", false)
                ], gridName);
            }
        }

        interface Option extends framework.basic.PluginOption {
            tb:string;
        }

        class EntryView extends framework.basic.EntryPluginView {
            static ins = new EntryView();
            private mData:Array<string[]>;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("/BusinessManagement/chgb/chzlbhqk/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("/BusinessManagement/chgb/chzlbhqk/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("/BusinessManagement/chgb/chzlbhqk/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;
            getId():number {
                return pluginEntry.chzlbhqk;
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginSave(dt:string, cpType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length - 2; ++j) {
                        submitData[i].push(allData[i][j + 2]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    companyId: cpType,
                    data: JSON.stringify(submitData)
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.Toast.success("保存 成功");
                    } else {
                        Util.Toast.failed(resp.message);
                    }
                });
            }

            public  pluginSubmit(dt:string, cpType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length - 2; ++j) {
                        submitData[i].push(allData[i][j + 2]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j]){
                            Util.Toast.failed("有空内容 无法提交")
                            return;
                        }
                    }
                }
                this.mAjaxSubmit.post({
                    date: dt,
                    companyId: cpType,
                    data: JSON.stringify(submitData)
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.Toast.success("提交 成功");
                    } else {
                        Util.Toast.failed(resp.message);
                    }
                });
            }

            public pluginUpdate(date:string, cpType:Util.CompanyType):void {
                this.mDt = date;
                this.mAjaxUpdate.get({
                        date: date,
                        companyId: cpType
                    })
                    .then((jsonData: any) => {
                        this.mData = jsonData.data;
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
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "存货账龄变化情况");
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
                this.mTableAssist.mergeTitle();
                return this.mTableAssist;
            }

            private updateTable():void {
                this.createJqassist();

                let ny = this.mDt.substr(0, this.mDt.length - 2).replace("-", "年") + "月";

                for (var i = 0; i < this.mData.length; ++i) {
                    for (var j = 0; j < this.mData[i].length; ++j) {
                        if ("" != this.mData[i][j]) {
                            this.mData[i][j] = parseFloat(this.mData[i][j]) + "";
                        }
                    }
                }


                this.mTableAssist.create({
                    data: [[ny].concat(this.mData[0])],
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