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
    export let byqcpycssbhgwtmx : number = framework.basic.endpoint.lastId();
}

module cpzlqk {
    export module byqcpycssbhgwtmxEntry {
        import TextAlign = JQTable.TextAlign;
        import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, readOnly:boolean, bhglx:string[], zrlb:string[]):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    Node.create({name : "产品类型", align : TextAlign.Center, isReadOnly:readOnly, isNumber: false}),
                    Node.create({name : "生产号", align : TextAlign.Center, isReadOnly:readOnly, isNumber: false}),
                    Node.create({name : "产品型号", align : TextAlign.Center, isReadOnly:readOnly, isNumber: false}),
                    Node.create({name : "试验不合格现象", align : TextAlign.Center, isReadOnly:readOnly, isNumber: false}),
                    Node.create({name : "不合格类别", align : TextAlign.Center, isReadOnly:readOnly, editType:"select", options:{value: bhglx}, isNumber: false}),
                    Node.create({name : "原因分析", align : TextAlign.Center, isReadOnly:readOnly, isNumber: false}),
                    Node.create({name : "处理措施", align : TextAlign.Center, isReadOnly:readOnly, isNumber: false}),
                    Node.create({name : "处理结果", align : TextAlign.Center, isReadOnly:readOnly, isNumber: false}),
                    Node.create({name : "责任类别", align : TextAlign.Center, isReadOnly:readOnly, editType:"select", options:{value: zrlb}, isNumber: false})
                ], gridName);
            }
        }

        interface Option extends framework.basic.PluginOption {
            tb:string;
        }

        class EntryView extends ZlEntryPluginView {
            static ins = new EntryView();
            private mData:CpzlqkResp;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("../byqcpycssbhgwtmx/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("../byqcpycssbhgwtmx/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("../byqcpycssbhgwtmx/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;
            private mCompType:Util.CompanyType;
            getId():number {
                return pluginEntry.byqcpycssbhgwtmx;
            }

            isSupportBhglb():boolean{
                return true;
            }
            protected isSupported(compType:Util.CompanyType):boolean {
                if (compType == Util.CompanyType.SBGS ||
                    compType == Util.CompanyType.HBGS ||
                    compType == Util.CompanyType.XBC){
                    return true;
                }
                return false;
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
                            if (j == 5){
                                Util.MessageBox.tip("不合格类别不能为空");
                                return;
                            }else if (j == 9){
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
                     //   Util.MessageBox.tip("提交 成功", ()=>{
                            let param = {
                                companyId:compType,
                                year : Util.toDate(dt).year,
                                month: Util.toDate(dt).month,
                                pageType:2,
                                tableStatus: JSON.stringify([
                                    {
                                        id:plugin.byqcpycssbhgwtmx
                                    },{
                                        id:plugin.byqcpycssbhgxxfb
                                    }
                                ])
                            }
                            window.location.href = "show.do?param=" + JSON.stringify(param);
                            //this.pluginUpdate(dt, compType);
                      //  });
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
					.send(framework.basic.FrameEvent.FE_REGISTER, "产品一次送试不合格问题明细");
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
                        assistEditable: Util.ZBStatus.APPROVED != this.mData.status,
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
