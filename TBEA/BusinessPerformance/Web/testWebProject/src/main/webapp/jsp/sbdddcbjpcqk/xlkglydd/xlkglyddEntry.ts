/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../messageBox.ts"/>
// <reference path="../sbdddcbjpcqkdef.ts" />
///<reference path="../../wlyddqk/wlyddqkEntry.ts"/>

declare var echarts;
declare var entryView:wlyddqk.EntryView;
declare var $:any;
module sbdddcbjpcqk {
    export module xlkglyddEntry {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, type:wlyddqk.WlyddType, readOnly:boolean, cplb:string[]):JQTable.JQGridAssistant {
                let nodeFirst:JQTable.Node;
                if (type == wlyddqk.WlyddType.SCDY) {
                    nodeFirst = new JQTable.Node("生产单元（项目公司）", "scdy", readOnly, TextAlign.Center, 0, "text", undefined, false);
                } else {
                    let vals:string = "";
                    for (let i in cplb){
                        if (i < cplb.length - 1){
                            vals += cplb[i] + ':' + cplb[i] + ';';
                        }else{
                            vals += cplb[i] + ':' + cplb[i];
                        }
                    }

                    nodeFirst = new JQTable.Node("产品类别", "sclb", readOnly, TextAlign.Center, 0, "select",
                        { value:vals }
                        , false);
                }

                return new JQTable.JQGridAssistant([
                    nodeFirst,
                    new JQTable.Node("月产出能力（产值）", "rqa", readOnly),
                    new JQTable.Node("未履约订单总额", "ab", readOnly),
                    new JQTable.Node("当年未履约订单总量", "ac", readOnly),
                    new JQTable.Node("n+1月订单量", "ada", readOnly)
                        .append(new JQTable.Node("已排产", "ba", readOnly))
                        .append(new JQTable.Node("未排产", "bc", readOnly)),
                    new JQTable.Node("n+2月订单量", "adb", readOnly)
                        .append(new JQTable.Node("已排产", "ba", readOnly))
                        .append(new JQTable.Node("未排产", "bc", readOnly)),
                    new JQTable.Node("n+3月订单量", "adc", readOnly)
                        .append(new JQTable.Node("已排产", "ba", readOnly))
                        .append(new JQTable.Node("未排产", "bc", readOnly)),
                    new JQTable.Node("n+3月以后履约订单", "ae", readOnly),
                    new JQTable.Node("次年及以后可供履约订单排产值", "af", readOnly),
                    new JQTable.Node("交货期待定产值", "ag", readOnly),
                    new JQTable.Node("外协", "ah", readOnly)
                ], gridName);
            }
        }

        interface Option extends wlyddqk.PluginOption {
            tb:string;
        }

        class XlkglyddEntryView extends wlyddqk.BaseEntryPluginView {

            private mData: wlyddqk.EntryLyddData;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("../sbdddcbjpcqk/xlkglydd/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("../sbdddcbjpcqk/xlkglydd/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("../sbdddcbjpcqk/xlkglydd/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;

            public static newInstance():XlkglyddEntryView {
                return new XlkglyddEntryView();
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            isSupported( compType:Util.CompanyType):boolean{
                if (compType == Util.CompanyType.LLGS || compType == Util.CompanyType.XLC || compType == Util.CompanyType.DLGS){
                    return true;
                }
                return false;
            }

            public pluginSave(dt:string):void {
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
                    type: this.mType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        this.pluginUpdate(dt);
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
                    type: this.mType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        this.pluginUpdate(dt);
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
                    .then((jsonData:wlyddqk.EntryLyddData) => {
                        this.mData = jsonData;
                        this.refresh();
                    });
            }

            public refresh():void {
                this.raiseReadOnlyChangeEvent(this.mData.statusData.readOnly);
                if (this.mData == undefined) {
                    return;
                }

                this.updateTable();
            }

            public init(opt:Option):void {
                super.init(opt);
                entryView.register("线缆可供履约订单变化情况按生产类别", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.SCLB));
                entryView.register("线缆可供履约订单变化情况按生产单元", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.SCDY));
                $.extend($.jgrid.edit, {
                        bSubmit: "确定"
                 });
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, this.mType, false, this.mData.cplb);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                let addId = 0;
                let delId = 0;
                let jqTable = this.$(name);
                jqTable.jqGrid(
                    this.mTableAssist.decorate({
                        datatype: "local",
                        multiselect: false,
                        drag: false,
                        resize: false,
                        //autowidth : false,
                        //cellsubmit: 'clientArray',
                        editurl: 'clientArray',
                        //cellEdit: true,
                        //height: data.length > 25 ? 550 : '100%',
                        // width: titles.length * 200,
                        rowNum: 150,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        data: this.mTableAssist.getDataWithId(this.mData.statusData.data),
                        viewrecords: true,
                        pager: '#' + pagername,
                    }));

                jqTable.bind("jqGridAddEditAfterShowForm", (event, element, oper) => {
                    if (oper == "edit") {
                        var page = jqTable.jqGrid('getGridParam', 'page');
                        var selectid = parseInt(jqTable.jqGrid('getGridParam', 'selrow'));
                        var rownum = jqTable.jqGrid('getGridParam', 'rowNum');
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


                jqTable.bind("jqGridAddEditAfterSubmit", (event, element, data, oper) => {
                    if (oper == "add") {
                        jqTable.addRowData("add" + (++addId), data, 'first');
                    } else if (oper == "edit") {
                        let selectid = parseInt(jqTable.jqGrid('getGridParam', 'selrow'));
                        jqTable.setRowData(selectid, data);
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
                //            var dataEdit = jqTable.data("formProp");
                //            if (undefined != dataEdit) {
                //                dataEdit.width = editModeWidth;
                //                dataEdit.datawidth = "auto";
                //                jqTable.data("formProp", dataEdit);
                //            }
                //            jqTable.jqGrid("editGridRow", sr, { width: editModeWidth });
                //        }
                //    }, {}, {}, {}, { multipleSearch: true });
                //} else {
                jqTable.jqGrid('navGrid', '#' + pagername, {
                    del: false, add: true, edit: true, refresh: false,
                    addfunc: () => {
                        var dataEdit = jqTable.data("formProp");
                        if (undefined != dataEdit) {
                            dataEdit.width = editModeWidth;
                            dataEdit.datawidth = "auto";
                            jqTable.data("formProp", dataEdit);
                        }
                        jqTable.jqGrid("editGridRow", "new", {width: editModeWidth, closeAfterEdit: true, closeAfterAdd:true});
                    },
                    editfunc: (sr) => {
                        var dataEdit = jqTable.data("formProp");
                        if (undefined != dataEdit) {
                            dataEdit.width = editModeWidth;
                            dataEdit.datawidth = "auto";
                            jqTable.data("formProp", dataEdit);
                        }
                        jqTable.jqGrid("editGridRow", sr, {width: editModeWidth, closeAfterEdit: true, closeAfterAdd:true});
                    }
                }, {width: editModeWidth}, {}, {multipleSearch: true});
                //}
            }
        }

        export var pluginView = XlkglyddEntryView.newInstance();
    }
}
