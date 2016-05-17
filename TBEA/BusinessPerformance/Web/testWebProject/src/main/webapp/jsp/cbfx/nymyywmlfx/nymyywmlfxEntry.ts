/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../messageBox.ts"/>
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../cbfxdef.ts"/>
declare var $:any;


module pluginEntry {
    export let nymyywmlfx : number = framework.basic.endpoint.lastId();
}

module cbfx {
    export module nymyywmlfxEntry {
        import TextAlign = JQTable.TextAlign;
        import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, readOnly:boolean):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    Node.create({name:"合作客户", align:TextAlign.Left, isReadOnly : readOnly}),
                    Node.create({name:"贸易项目", align:TextAlign.Left, isReadOnly : readOnly}),
                    Node.create({name:"数量", isReadOnly : readOnly}),
                    Node.create({name:"收入", isReadOnly : readOnly}),
                    Node.create({name:"成本", isReadOnly : readOnly})
                ], gridName);
            }
        }

        interface Option extends framework.basic.PluginOption {
            tb:string;
        }

        class EntryView extends framework.basic.EntryPluginView {
            static ins = new EntryView();
            private mData:Array<string[]>;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("../nymyywmlfx/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("../nymyywmlfx/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("../nymyywmlfx/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;
            private mCompType:Util.CompanyType;
            getId():number {
                return pluginEntry.nymyywmlfx;
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginSave(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
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
                    submitData.push([allData[i][0], allData[i][2]]);
                    submitData[i][1] = submitData[i][1].replace(new RegExp(' ', 'g'), '');
                    if ("" == submitData[i][1]) {
                        Util.MessageBox.tip("有空内容 无法提交")
                        return;
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
                    .send(framework.basic.FrameEvent.FE_REGISTER, "能源贸易业务毛利分析");
            }

            private updateTable():void {
                let name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                let pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, false);

                let parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                let jqTable = this.$(name);
                jqTable.jqGrid(
                    this.mTableAssist.decorate({
                        datatype: "local",
                        data: this.mTableAssist.getDataWithId(this.mData),
                        multiselect: false,
                        drag: false,
                        resize: false,
                        assistEditable:true,
                        cellsubmit: 'clientArray',
                        cellEdit: true,
                        rowNum: 22,
                        height: '100%',
                        width: 1000,
                        shrinkToFit: true,
                        autoScroll: true,
                        viewrecords: true,
                        pager: '#' + pagername,
                    }));
            }
        }
    }
}
