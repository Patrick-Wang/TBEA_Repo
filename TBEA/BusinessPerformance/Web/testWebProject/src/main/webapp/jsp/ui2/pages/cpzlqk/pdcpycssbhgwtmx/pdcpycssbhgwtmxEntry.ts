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
    export let pdcpycssbhgwtmx : number = framework.basic.endpoint.lastId();
}

module cpzlqk {
    export module pdcpycssbhgwtmxEntry {
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
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("/BusinessManagement/pdcpycssbhgwtmx/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("/BusinessManagement/pdcpycssbhgwtmx/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("/BusinessManagement/pdcpycssbhgwtmx/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;
            private mCompType:Util.CompanyType;
            getId():number {
                return pluginEntry.pdcpycssbhgwtmx;
            }

            isSupportBhglb():boolean{
                return true;
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
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j]) {
                            if (j == 5){
                                Util.Toast.failed("不合格类别不能为空");
                                return;
                            }else if (j == 9){
                                Util.Toast.failed("责任类别不能为空");
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
                        let param = {
                            year : Util.toDate(dt).year,
                            month: Util.toDate(dt).month,
                            pageType:2,
                            companyId:compType,
                            tableStatus: JSON.stringify([
                                {
                                    id:plugin.pdcpycssbhgwtmx
                                },{
                                    id:plugin.pdcpycssbhgxxfb
                                }
                            ])
                        }
                        window.location.href = encodeURI("/BusinessManagement/cpzlqk/v2/show.do?breads=" + breads + "&param=" + JSON.stringify(param));
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
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j]) {
                            Util.Toast.failed("有空内容 无法提交")
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
                        Util.Toast.success("提交 成功");
                    } else {
                        Util.Toast.failed(resp.message);
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

            adjustSize() {
                if (document.body.clientHeight < 10 || document.body.clientWidth < 10){
                    return;
                }

                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() <= this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                //
                //let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                //this.mTableAssist.resizeHeight(maxTableBodyHeight);
                //
                //if (this.jqgridHost().width() < this.jqgridHost().find(".ui-jqgrid").width()) {
                //    jqgrid.setGridWidth(this.jqgridHost().width());
                //}
            }

            private createJqassist():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table><div id='" + this.jqgridName()  + "pager'></div>");
                this.mTableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), Util.ZBStatus.APPROVED == this.mData.status, this.mData.bhglx, this.mData.zrlb);
                this.mTableAssist.mergeTitle();
                this.mTableAssist.mergeRow(0);
                return this.mTableAssist;
            }


            private updateTable():void {
                this.createJqassist();

                this.mTableAssist.create({
                    dataWithId: this.mData.tjjg,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.mTableAssist.getColNames().length * 400,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 15,
                    assistEditable: Util.ZBStatus.APPROVED != this.mData.status,
                    pager: '#' + this.jqgridName()  + "pager",
                    viewrecords: true
                });

                this.adjustSize();
            }

            //private updateTable():void {
            //    var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
            //    var pagername = name + "pager";
            //    this.mTableAssist = JQGridAssistantFactory.createTable(name, Util.ZBStatus.APPROVED == this.mData.status, this.mData.bhglx, this.mData.zrlb);
            //
            //    var parent = this.$(this.option().tb);
            //    parent.empty();
            //    parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
            //    let jqTable = this.$(name);
            //    jqTable.jqGrid(
            //        this.mTableAssist.decorate({
            //            datatype: "local",
            //            data: this.mTableAssist.getDataWithId(this.mData.tjjg),
            //            multiselect: false,
            //            drag: false,
            //            resize: false,
            //            assistEditable: Util.ZBStatus.APPROVED != this.mData.status,
            //            //autowidth : false,
            //            cellsubmit: 'clientArray',
            //            //editurl: 'clientArray',
            //            cellEdit: true,
            //            // height: data.length > 25 ? 550 : '100%',
            //            // width: titles.length * 200,
            //            rowNum: 20,
            //            height: '100%',
            //            width: 1200,
            //            shrinkToFit: true,
            //            autoScroll: true,
            //            pager: '#' + pagername,
            //            viewrecords: true
            //        }));
            //}
        }
    }
}
