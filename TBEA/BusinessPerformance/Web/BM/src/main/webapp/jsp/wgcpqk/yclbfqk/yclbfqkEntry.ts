/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../messageBox.ts"/>
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
declare var $:any;


module pluginEntry {
    export let yclbfqk : number = framework.basic.endpoint.lastId();
}

module wgcpqk {
    export module yclbfqkEntry {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, readOnly:boolean):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("材料名称", "clmc", true, TextAlign.Center),
                    new JQTable.Node("领用量", "ac", readOnly),
                    new JQTable.Node("废料", "ada", readOnly)
                ], gridName);
            }
        }

        interface Option extends framework.basic.PluginOption {
            tb:string;
        }

        class EntryView extends framework.basic.EntryPluginView {
            static ins = new EntryView();
            private mData:Array<string[]>;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("../yclbfqk/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("../yclbfqk/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("../yclbfqk/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;
            private mCompType:Util.CompanyType;
            getId():number {
                return pluginEntry.yclbfqk;
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginSave(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    let index = 0;
                    for (var j = 0; j < allData[i].length; ++j) {
                        if (j == 1){
                            continue;
                        }
                        submitData[i].push(allData[i][j]);
                        submitData[i][index] = submitData[i][index].replace(new RegExp(' ', 'g'), '');
                        ++index;
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
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
                    let index = 0;
                    for (var j = 0; j < allData[i].length; ++j) {
                        if (j == 1){
                            continue;
                        }
                        submitData[i].push(allData[i][j]);
                        submitData[i][index] = submitData[i][index].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][index]) {
                            Util.MessageBox.tip("有空内容 无法提交")
                            return;
                        }
                        ++index;
                    }
                }
                this.mAjaxSubmit.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        this.pluginUpdate(dt, compType);
                        Util.MessageBox.tip("提交 成功");
                    } else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            }

            protected isSupported(compType:Util.CompanyType):boolean {
                return true;
            }


            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = compType;
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
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "原材料报废情况");
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, false);
                let data = [];
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'>");
                let jqTable = this.$(name);
                jqTable.jqGrid(
                    this.mTableAssist.decorate({
                        datatype: "local",
                        data: this.mTableAssist.getDataWithId(this.mData),
                        multiselect: false,
                        drag: false,
                        resize: false,
                        assistEditable:true,
                        //autowidth : false,
                        cellsubmit: 'clientArray',
                        //editurl: 'clientArray',
                        cellEdit: true,
                        //height: data.length > 25 ? 550 : '100%',
                        // width: titles.length * 200,
                        rowNum: 20,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        viewrecords: true
                    }));
            }
        }
    }
}
