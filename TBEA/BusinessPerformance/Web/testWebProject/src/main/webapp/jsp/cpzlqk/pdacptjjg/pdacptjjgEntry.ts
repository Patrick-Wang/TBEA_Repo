/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../messageBox.ts"/>
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cpzlqkdef.ts"/>
///<reference path="../cpzlqkEntry.ts"/>
declare var $:any;


module pluginEntry {
    export let pdacptjjg : number = framework.basic.endpoint.lastId();
}

module cpzlqk {
    export module pdacptjjgEntry {
        import TextAlign = JQTable.TextAlign;
        import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, readOnly:boolean):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    Node.create({name : "产品类别", align : TextAlign.Center}),
                    Node.create({name : "产品类别", align : TextAlign.Center}),
                    Node.create({name : "不合格数(台)", isReadOnly:readOnly}),
                    Node.create({name : "总数(台)", isReadOnly:readOnly})
                ], gridName);
            }
        }

        interface Option extends framework.basic.PluginOption {
            tb:string;
        }

        class EntryView extends ZlEntryPluginView {
            static ins = new EntryView();
            private mData:CpzlqkResp;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("../pdacptjjg/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("../pdacptjjg/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("../pdacptjjg/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;
            private mCompType:Util.CompanyType;
            getId():number {
                return pluginEntry.pdacptjjg;
            }

            protected isSupported(compType:Util.CompanyType):boolean {
                return compType == Util.CompanyType.SBZTFGS || compType == Util.CompanyType.HBDQFGS
                    || compType == Util.CompanyType.XBZTGS || compType == Util.CompanyType.TBGS
                    || compType == Util.CompanyType.XBXBGS;
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginSave(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([allData[i][0]]);
                    for (var j = 3; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 3] = submitData[i][j - 3].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("保存 成功", ()=>{
                            this.pluginUpdate(dt, compType);
                        });
                    } else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            }

            public  pluginSubmit(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([allData[i][0]]);
                    for (var j = 3; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 3] = submitData[i][j - 3].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j - 3]) {
                            Util.MessageBox.tip("有空内容 无法提交")
                            return;
                        }
                    }
                }
                this.mAjaxSubmit.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("提交 成功", ()=>{
                            this.pluginUpdate(dt, compType);
                        });
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

                framework.router
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(Event.ZLFE_DATA_STATUS, this.mData.status);

                this.updateTable();
            }

            protected init(opt:Option):void {
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "按产品统计结果");
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, Util.ZBStatus.APPROVED == this.mData.status);

                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                let jqTable = this.$(name);
                this.mTableAssist.mergeColum(0);
                this.mTableAssist.mergeRow(0);
                this.mTableAssist.mergeTitle(0);
                jqTable.jqGrid(
                    this.mTableAssist.decorate({
                        datatype: "local",
                        data: this.mTableAssist.getDataWithId(this.mData.tjjg),
                        multiselect: false,
                        drag: false,
                        resize: false,
                        assistEditable: Util.ZBStatus.APPROVED != this.mData.status,
                        //autowidth : false,
                        cellsubmit: 'clientArray',
                        //editurl: 'clientArray',
                        cellEdit: true,
                        // height: data.length > 25 ? 550 : '100%',
                        // width: titles.length * 200,
                        rowNum: 1000,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        //pager: '#' + pagername,
                        viewrecords: true
                    }));
            }
        }
    }
}
