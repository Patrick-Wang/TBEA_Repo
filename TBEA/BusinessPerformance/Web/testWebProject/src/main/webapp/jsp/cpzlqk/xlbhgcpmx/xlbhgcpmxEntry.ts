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
    export let xlbhgcpmx : number = framework.basic.endpoint.lastId();
}

module cpzlqk {
    export module xlbhgcpmxEntry {
        import TextAlign = JQTable.TextAlign;
        import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, readOnly : boolean, bhglx:string[], zrlb:string[]):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    Node.create({name : "产品类型", align : TextAlign.Center, isReadOnly:readOnly, isNumber: false}),
                    Node.create({name : "生产号", align : TextAlign.Center, isReadOnly:readOnly, isNumber: false}),
                    Node.create({name : "产品型号", align : TextAlign.Center, isReadOnly:readOnly, isNumber: false}),
                    Node.create({name : "不合格数量", align : TextAlign.Center, isReadOnly:readOnly}),
                    Node.create({name : "试验不合格现象", align : TextAlign.Center, isReadOnly:readOnly, isNumber: false}),
                    Node.create({name : "不合格类别", align : TextAlign.Center, isReadOnly:readOnly, editType:"select", options:{value: bhglx}}),
                    Node.create({name : "原因分析", align : TextAlign.Center, isReadOnly:readOnly, isNumber: false}),
                    Node.create({name : "处理措施", align : TextAlign.Center, isReadOnly:readOnly, isNumber: false}),
                    Node.create({name : "处理结果", align : TextAlign.Center, isReadOnly:readOnly, isNumber: false}),
                    Node.create({name : "责任类别", align : TextAlign.Center, isReadOnly:readOnly, editType:"select", options:{value: zrlb}})
                ], gridName);
            }
        }

        interface Option extends framework.basic.PluginOption {
            tb:string;
        }

        class EntryView extends ZlEntryPluginView {
            static ins = new EntryView();
            private mData:CpzlqkResp;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("../xlbhgcpmx/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("../xlbhgcpmx/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("../xlbhgcpmx/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;
            private mCompType:Util.CompanyType;
            getId():number {
                return pluginEntry.xlbhgcpmx;
            }

            protected isSupported(compType:Util.CompanyType):boolean {
                return compType == Util.CompanyType.LLGS || compType == Util.CompanyType.XLC ||compType == Util.CompanyType.DLGS;
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
                        if ("" == submitData[i][j]) {
                            if (j == 6){
                                Util.MessageBox.tip("不合格类别不能为空");
                                return;
                            }else if (j == 10){
                                Util.MessageBox.tip("责任类别不能为空");
                                return;
                            }

                        }
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
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j]) {
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

                this.updateTable();
            }

            protected init(opt:Option):void {
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "不合格产品明细");
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, Util.ZBStatus.APPROVED == this.mData.status, this.mData.bhglx, this.mData.zrlb);

                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                let jqTable = this.$(name);
                jqTable.jqGrid(
                    this.mTableAssist.decorate({
                        datatype: "local",
                        data: this.mTableAssist.getDataWithId(this.mData.tjjg),
                        multiselect: false,
                        drag: false,
                        resize: false,
                        assistEditable:Util.ZBStatus.APPROVED != this.mData.status,
                        //autowidth : false,
                        cellsubmit: 'clientArray',
                        //editurl: 'clientArray',
                        cellEdit: true,
                        // height: data.length > 25 ? 550 : '100%',
                        // width: titles.length * 200,
                        rowNum: 20,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        pager: '#' + pagername,
                        viewrecords: true
                    }));
            }
        }
    }
}
