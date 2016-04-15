/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../messageBox.ts"/>
// <reference path="../sbdddcbjpcqkdef.ts" />
///<reference path="../sbdddcbjpcqkEntry.ts"/>

declare var echarts;
declare var entryView:sbdddcbjpcqk.EntryView;

module sbdddcbjpcqk {
    export module xlkglyddEntry {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, type:KglyddType, readOnly : boolean):JQTable.JQGridAssistant {
                let nodeFirst : JQTable.Node;
                if (type == KglyddType.SCDY){
                    nodeFirst = new JQTable.Node("生产单元", "scdy", readOnly, TextAlign.Center)
                }else{
                    nodeFirst = new JQTable.Node("产品类别", "sclb", readOnly, TextAlign.Center)
                }

                return new JQTable.JQGridAssistant([
                    nodeFirst,
                    new JQTable.Node("月产出能力（产值）", "rqa", readOnly),
                    new JQTable.Node("所有可供履约订单总量产值", "ab", readOnly),
                    new JQTable.Node("当年可供履约订单总量产值", "ac", readOnly),
                    new JQTable.Node("当季度排产订单产值", "ad", readOnly),
                    new JQTable.Node("下季度排产订单产值", "ae", readOnly),
                    new JQTable.Node("次年及以后可供履约订单排产值", "ae", readOnly),
                    new JQTable.Node("交货期待定产值", "af", readOnly)
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            tb:string;
        }

        class XlkglyddEntryView extends BaseEntryPluginView {

            private mData:Array<string[]>;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("xlkglydd/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("xlkglydd/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("xlkglydd/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;
            private mIsReadOnly:boolean;

            public static newInstance():XlkglyddEntryView {
                return new XlkglyddEntryView();
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginSave(dt:string):void {
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
                    data: JSON.stringify(submitData),
                    type: this.mType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("保存 成功");
                    } else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            }

            public  pluginSubmit(dt:string):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length - 2; ++j) {
                        submitData[i].push(allData[i][j + 2]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j]){
                            Util.MessageBox.tip("有空内容 无法提交")
                            return;
                        }
                    }
                }
                this.mAjaxSubmit.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    type: this.mType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("提交 成功");
                    } else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            }

            public pluginUpdate(date:string):void {
                this.mDt = date;
                this.mAjaxUpdate.get({
                        type: this.mType,
                        date: date
                    })
                    .then((jsonData: StatusData) => {
                        this.mData = jsonData.data;
                        this.mIsReadOnly = jsonData.readOnly;
                        this.refresh();
                    });
            }

            public refresh():void {
                this.raiseReadOnlyChangeEvent(this.mIsReadOnly);
                if (this.mData == undefined) {
                    return;
                }

                this.updateTable();
            }

            public init(opt:Option):void {
                super.init(opt);
                entryView.register("线缆可供履约订单变化情况按生产类别", new TypeEntryViewProxy(this, KglyddType.SCLB));
                entryView.register("线缆可供履约订单变化情况按生产单元", new TypeEntryViewProxy(this, KglyddType.SCDY));
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, this.mType, true);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "></div>");
                this.$(name).jqGrid(
                    this.mTableAssist.decorate({
                        datatype: "local",
                        multiselect: false,
                        drag: false,
                        resize: false,
                        //autowidth : false,
                        cellsubmit: 'clientArray',
                        cellEdit: true,
                        //height: data.length > 25 ? 550 : '100%',
                        // width: titles.length * 200,
                        rowNum: 150,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        data: this.mTableAssist.getData(this.mData),
                        viewrecords: true,
                        pager: '#'+ pagername,
                    }));

                this.$(name).bind("jqGridAddEditAfterShowForm", (event, element, oper) => {
                    if (oper == "edit") {
                        var page = this.$(name).jqGrid('getGridParam', 'page');
                        var selectid = parseInt(this.$(name).jqGrid('getGridParam', 'selrow'));
                        var rownum =  this.$(name).jqGrid('getGridParam', 'rowNum');
                        var acRowid = ((page - 1) * rownum) + selectid;
                        //if (this.mDocType == 2) {
                        //    this.mOriginalKey = $("#" + childName)[0].p.data[acRowid - 1].t2;
                        //} else if (this.mDocType == 3) {
                        //    this.mOriginalKey = $("#" + childName)[0].p.data[acRowid - 1].t1;
                        //} else if (this.mDocType == 4) {
                        //    this.mOriginalKey = $("#" + childName)[0].p.data[acRowid - 1].t1;
                        //}
                    }
                });

                var editModeWidth = 350;


                this.$(name).bind("jqGridAddEditAfterSubmit", (event, element, data, oper) => {
                    if (oper == "add") {
                        //data.t0 = this.mCompanyName;
                        //this.mEditOper = "add";

                        var submitData: string[][] = [];
                        for (var row = 0; row < 1; row++) {
                            submitData.push([]);
                            submitData[row].push(data["t0"]);
                            for (var col in data) {
                                if (col != "id" && col != "oper" && col != "t0") {
                                    submitData[row].push(data[col]);
                                }
                            }
                        }
                        //this.mSaveDataSet.post({
                        //    mktType: this.mDocType,
                        //    data: JSON.stringify(submitData),
                        //    editOper: this.mEditOper,
                        //    editOriginalKey: this.mOriginalKey
                        //}).then((sr: ISubmitResult) => {
                        //    if (ErrorCode.OK == sr.errorCode) {
                        //        Util.MessageBox.tip("添加成功！");
                        //        $.jgrid.hideModal("#" + $.jgrid.jqID("editmodtable1_jqgrid_1234"), { gb: "#gbox_" + $.jgrid.jqID("table1_jqgrid_1234"), jqm: true, onClose: null });
                        //        $("#" + childName).addRowData($("#" + childName)[0].p.data.length + 1, data, 'first');
                        //        var tmpData: Array<any> = $("#" + childName)[0].p.data;
                        //        tmpData.unshift(tmpData.pop());
                        //        //$("#pager input.ui-pg-input").val($('input.ui-pg-input').next().text());
                        //        $("#pager input.ui-pg-input").val(1);
                        //        var e = $.Event("keypress");
                        //        e.keyCode = 13;
                        //        $("#pager input.ui-pg-input").trigger(e);
                        //        $("#assist").css("display", "block");
                        //    } else if (ErrorCode.PREMARY_KEY_CONFILICT == sr.errorCode) {
                        //        if (this.mDocType == 2) {
                        //            Util.MessageBox.tip("添加失败，项目序号重复！");
                        //        } else if (this.mDocType == 3) {
                        //            Util.MessageBox.tip("添加失败，投标编号重复！");
                        //        } else if (this.mDocType == 4) {
                        //            Util.MessageBox.tip("添加失败，合同编号重复!");
                        //        }
                        //    } else if (ErrorCode.DATABASE_EXCEPTION == sr.errorCode) {
                        //        Util.MessageBox.tip("添加失败!");
                        //    } else if (ErrorCode.PREMARY_KEY_NULL == sr.errorCode) {
                        //        if (this.mDocType == 2) {
                        //            Util.MessageBox.tip("添加失败，项目序号不能为空！");
                        //        } else if (this.mDocType == 3) {
                        //            Util.MessageBox.tip("添加失败，投标编号不能为空！");
                        //        } else if (this.mDocType == 4) {
                        //            Util.MessageBox.tip("添加失败，合同编号不能为空!");
                        //        }
                        //    } else {
                        //    }
                        //});

                    } else if (oper == "edit") {
                        //this.mEditOper = "edit";
                        //data.t0 = this.mCompanyName;

                        //var submitData: string[][] = [];
                        //for (var row = 0; row < 1; row++) {
                        //    submitData.push([]);
                        //    submitData[row].push(data["t0"]);
                        //    for (var col in data) {
                        //        if (col != "id" && col != "oper" && col != "t0") {
                        //            submitData[row].push(data[col]);
                        //        }
                        //    }
                        //}
                        //
                        //var selectid = parseInt($("#" + childName).jqGrid('getGridParam', 'selrow'));
                        //this.mSaveDataSet.post({
                        //    mktType: this.mDocType,
                        //    data: JSON.stringify(submitData),
                        //    editOper: this.mEditOper,
                        //    editOriginalKey: this.mOriginalKey
                        //}).then((sr: ISubmitResult) => {
                        //    if (ErrorCode.OK == sr.errorCode) {
                        //        Util.MessageBox.tip("编辑成功！");
                        //
                        //        $.jgrid.hideModal("#" + $.jgrid.jqID("editmod" + childName), { gb: "#gbox_" + $.jgrid.jqID("table1_jqgrid_1234"), jqm: true, onClose: null });
                        //        var page = $("#" + childName).jqGrid('getGridParam', 'page');
                        //        var rownum = $("#" + childName).jqGrid('getGridParam', 'rowNum');
                        //        var acRowid = ((page - 1) * rownum) + selectid;
                        //
                        //        function swap(arr, a, b) {
                        //            var tmp = arr[a];
                        //            arr[a] = arr[b];
                        //            arr[b] = tmp;
                        //        }
                        //        swap($("#" + childName)[0].p.data, selectid - 1, acRowid - 1);
                        //        $("#" + childName).setRowData(selectid, data);
                        //        swap($("#" + childName)[0].p.data, selectid - 1, acRowid - 1);
                        //
                        //    } else if (ErrorCode.PREMARY_KEY_CONFILICT == sr.errorCode) {
                        //        if (this.mDocType == 2) {
                        //            Util.MessageBox.tip("编辑失败，项目序号重复！");
                        //        } else if (this.mDocType == 3) {
                        //            Util.MessageBox.tip("编辑失败，投标编号重复！");
                        //        } else if (this.mDocType == 4) {
                        //            Util.MessageBox.tip("编辑失败，合同编号重复!");
                        //        }
                        //    } else if (ErrorCode.DATABASE_EXCEPTION == sr.errorCode) {
                        //        Util.MessageBox.tip("数据提交失败!");
                        //    } else if (ErrorCode.PREMARY_KEY_NULL == sr.errorCode) {
                        //        if (this.mDocType == 2) {
                        //            Util.MessageBox.tip("编辑失败，项目序号不能为空！");
                        //        } else if (this.mDocType == 3) {
                        //            Util.MessageBox.tip("编辑失败，投标编号不能为空！");
                        //        } else if (this.mDocType == 4) {
                        //            Util.MessageBox.tip("编辑失败，合同编号不能为空!");
                        //        }
                        //    } else {
                        //    }
                        //});
                    }
                });

                //if (this.mCompanyName == "股份公司") {
                //    $("#" + childName).jqGrid('navGrid', '#pager', {
                //        del: false, add: false, edit: false,
                //        addfunc: function() {
                //            var dataEdit = $("#" + childName).data("formProp");
                //            if (undefined != dataEdit) {
                //                dataEdit.width = editModeWidth;
                //                dataEdit.datawidth = "auto";
                //                $("#" + childName).data("formProp", dataEdit);
                //            }
                //            $("#" + childName).jqGrid("editGridRow", "new", { width: editModeWidth });
                //        },
                //        editfunc: function(sr) {
                //            var dataEdit = this.$(name).data("formProp");
                //            if (undefined != dataEdit) {
                //                dataEdit.width = editModeWidth;
                //                dataEdit.datawidth = "auto";
                //                this.$(name).data("formProp", dataEdit);
                //            }
                //            this.$(name).jqGrid("editGridRow", sr, { width: editModeWidth });
                //        }
                //    }, {}, {}, {}, { multipleSearch: true });
                //} else {
                    this.$(name).jqGrid('navGrid', '#'+ pagername, {
                        del: false, add: true, edit: true,
                        addfunc: function() {
                            var dataEdit = this.$(name).data("formProp");
                            if (undefined != dataEdit) {
                                dataEdit.width = editModeWidth;
                                dataEdit.datawidth = "auto";
                                this.$(name).data("formProp", dataEdit);
                            }
                            this.$(name).jqGrid("editGridRow", "new", { width: editModeWidth });
                        },
                        editfunc: function(sr) {
                            var dataEdit = this.$(name).data("formProp");
                            if (undefined != dataEdit) {
                                dataEdit.width = editModeWidth;
                                dataEdit.datawidth = "auto";
                                this.$(name).data("formProp", dataEdit);
                            }
                            this.$(name).jqGrid("editGridRow", sr, { width: editModeWidth });
                        }
                    }, { width: editModeWidth }, { width: editModeWidth }, {}, { multipleSearch: true });
                //}
            }
        }

        export var pluginView = XlkglyddEntryView.newInstance();
    }
}
