/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../messageBox.ts"/>
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../xnyzbdef.ts"/>
declare var $:any;


module pluginEntry {
    export let xnyzb : number = framework.basic.endpoint.lastId();
}

module xnyzb {
    export module xnyzbEntry {
        import TextAlign = JQTable.TextAlign;
        import Node = JQTable.Node;
        class JQGridAssistantFactory {

            public static createTable(gridName:string, headers: Util.Header[]):JQTable.JQGridAssistant {

                let nodes : Node[] = [];
                for (let i= 0; i < headers.length; ++i) {
                    let node = Util.parseHeader(headers[i]);
                    if (null != node) {
                        nodes.push(node);
                    }
                }
                return new JQTable.JQGridAssistant(nodes, gridName);
            }
        }

        interface Option extends framework.basic.PluginOption {
            tb:string;
            title:string;
            updateUrl:string;
            submitUrl:string;
        }

        class EntryView extends framework.basic.EntryPluginView {
            static ins = new EntryView();
            private mData:Util.ServResp;
            private mAjaxUpdate:Util.Ajax;
            //private mAjaxSave:Util.Ajax;
            private mAjaxSubmit:Util.Ajax;
            private mTableAssist:JQTable.JQGridAssistant;
            private mCompType:Util.CompanyType;
            getId():number {
                return pluginEntry.xnyzb;
            }

            onEvent(e:framework.route.Event):any {
                switch (e.id) {
                    case framework.basic.FrameEvent.FE_SAVE:
                    {

                        //this.pluginSave(e.data.dStart, e.data.dEnd, e.data.compType);
                    }
                        return;
                    case framework.basic.FrameEvent.FE_SUBMIT:
                    {
                        this.pluginSubmit(e.data.dStart, e.data.dEnd, e.data.compType);
                    }
                        return;
                    case framework.basic.FrameEvent.FE_UPDATE:
                    {
                        this.pluginUpdate(e.data.dStart, e.data.dEnd, e.data.compType);
                    }
                        return;
                    default:
                        break;
                }
                return super.onEvent(e);
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            //public pluginSave(dStart:string, dEnd:string, compType:Util.CompanyType):void {
            //    var allData = this.mTableAssist.getAllData();
            //    var submitData = [];
            //    for (var i = 0; i < allData.length; ++i) {
            //        submitData.push([]);
            //        for (var j = 0; j < allData[i].length; ++j) {
            //            submitData[i].push(allData[i][j]);
            //            submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
            //        }
            //    }
            //    this.mAjaxSave.post({
            //        dStart: dStart,
            //        data: JSON.stringify(submitData),
            //        dEnd:dEnd,
            //        compId: compType
            //    }).then((resp:Util.IResponse) => {
            //        if (Util.ErrorCode.OK == resp.errorCode) {
            //            Util.MessageBox.tip("保存 成功", ()=>{
            //                this.pluginUpdate(dStart, dEnd, compType);
            //            });
            //        } else {
            //            Util.MessageBox.tip(resp.message);
            //        }
            //    });
            //}

            public  pluginSubmit(dStart:string, dEnd:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                        //if (j != 3 && "" == submitData[i][j]) {
                        //    Util.MessageBox.tip("有空内容 无法提交")
                        //    return;
                        //}
                    }
                }
                this.mAjaxSubmit.post({
                    dStart: dStart,
                    data: JSON.stringify(submitData),
                    dEnd:dEnd,
                    compId: compType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("提交 成功", ()=>{
                            this.pluginUpdate(dStart, dEnd, compType);
                        });
                    } else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            }

            public pluginUpdate(dStart:string, dEnd:string, compType:Util.CompanyType):void {
                this.mCompType = compType;
                this.mAjaxUpdate.get({
                        dStart: dStart,
                        dEnd:dEnd,
                        compId: compType
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
					.send(framework.basic.FrameEvent.FE_REGISTER, opt.title);
                this.mAjaxUpdate = new Util.Ajax(this.option().updateUrl, false);
                this.mAjaxSubmit = new Util.Ajax(this.option().submitUrl, false);
            }

            adjustSize() {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                //let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                //this.tableAssist.resizeHeight(maxTableBodyHeight);
                //
                //if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                //    jqgrid.setGridWidth(this.jqgridHost().width());
                //}

            }

            createJqassist():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table><div id='" + this.jqgridName() + "pager'></div>");
                return this.mTableAssist;
            }

            private updateTable():void {
                this.createJqassist();

                this.mTableAssist.create({
                    dataWithId: this.mData.data,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 15,
                    assistEditable:true,
                    autoScroll: true,
                    pager: '#' + this.jqgridName() + "pager",
                });

            }

            //private updateTable():void {
            //    var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
            //    var pagername = name + "pager";
            //    this.mTableAssist = JQGridAssistantFactory.createTable(name, this.mData.header);
            //
            //    var parent = this.$(this.option().tb);
            //    parent.empty();
            //    parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
            //    let jqTable = this.$(name);
            //    jqTable.jqGrid(
            //        this.mTableAssist.decorate({
            //            datatype: "local",
            //            data: this.mTableAssist.getDataWithId(this.mData.data),
            //            multiselect: false,
            //            drag: false,
            //            resize: false,
            //            assistEditable:true,
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
