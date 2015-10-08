/// <reference path="util.ts" />
/// <reference path="jqgrid/jqassist.ts" />
declare var echarts;
module accounts_receivable_dialy {

    class JQGridAssistantFactory {

        public static createBidTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "t0", true, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("集团下达月度资金回笼指标", "t1", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("各单位自行制定的回款计划", "t2", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("今日回款", "t3", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("月累计（截止今日）", "t4", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("资金回笼指标完成", "t5", false, JQTable.TextAlign.Left, 0,undefined, undefined, false),
                new JQTable.Node("回款计划完成率", "t6", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("已回款中可降应收的回款金额", "t7", false, JQTable.TextAlign.Left, 0, undefined,undefined,false),
                new JQTable.Node("目前-月底回款计划", "t8", true, JQTable.TextAlign.Left, 0, undefined,undefined,false)
                    .append(new JQTable.Node("确保办出", "t81"))
                    .append(new JQTable.Node("争取办出", "t82"))
                    .append(new JQTable.Node("两者合计", "t83")),
                new JQTable.Node("全月确保", "t9", false, JQTable.TextAlign.Left, 0, undefined,undefined,false),
                new JQTable.Node("预计全月计划完成率", "t10", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("截止月底应收账款账面余额", "t11", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
               
            ], gridName);
        }
    }


    enum ErrorCode {
        OK,
        DATABASE_EXCEPTION,
        PREMARY_KEY_CONFILICT
    }

    interface ISubmitResult {
        errorCode: number;
        message: string;
    }

    interface ISubmitResultDeprecated {
        result: string;
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }
        private mDataSet: Util.Ajax;
        private mSaveDataSet: Util.Ajax;
        private mExportDataSet: Util.Ajax;
        private mCompanyName;
        private mOriginalKey;
        private mEditOper: string;
        TableId: string;
        childTableId: string;

        public init(TableId: string, companyName: string): void {
            this.mCompanyName = companyName;
            this.TableId = TableId;
            this.childTableId = TableId + "_jqgrid_1234";
            //请求数据
            this.mDataSet = new Util.Ajax("mkt_view_update.do", false);
            this.mSaveDataSet = new Util.Ajax("mkt_entry_data.do", false);
            //this.updateUI();
        }


        public exportExcel() {
            $("#exportMarketData")[0].action = "mkt_view_export1.do?" + Util.Ajax.toUrlParam({});
            $("#exportMarketData")[0].submit();
        }

        public saveData() {
            var submitData: string[][] = [];
            for (var row = 0; row < $("#" + this.childTableId)[0].p.data.length; row++) {
                submitData.push([]);
                for (var col in $("#" + this.childTableId)[0].p.data[row]) {
                    submitData[row].push($("#" + this.childTableId)[0].p.data[row][col]);
                }
            }
            this.mSaveDataSet.post({
                data: JSON.stringify(submitData),
                editOper: this.mEditOper,
                //editOriginalKey: this.mOriginalKey
            }).then((data: ISubmitResultDeprecated) => {
                if ("true" == data.result) {
                    Util.MessageBox.tip("提交 成功");
                } else if ("false" == data.result) {
                    Util.MessageBox.tip("提交 失败");
                } else {
                    Util.MessageBox.tip(data.result);
                }
            });
        }

        public updateUI() {
            //删去历史内容
            var parent = $("#" + this.TableId);
            parent.empty();
            parent.append("<table id='" + this.childTableId + "'></table>" + "<div id='pager'></div>");

            this.mDataSet.get({ })
                .then((data: any) => {
                    var fktjData = data;

                    $('#dataStatus').css("display", "none");
                  
                    this.updateTable(
                        this.TableId,
                        this.childTableId,
                        JQGridAssistantFactory.createBidTable(this.childTableId),
                        fktjData[0]);

                    var title = this.mCompanyName + "市场部" + $("#rpttype option:selected").text();
                    $('h1').text(title);
                    document.title = title;
                });
        }

        private updateTable(
            parentName: string,
            childName: string,
            tableAssist: JQTable.JQGridAssistant,
            rawData: Array<string[]>): void {

            $("#" + childName).jqGrid(
                tableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: tableAssist.getData(rawData),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    // autowidth : false,
                    //cellsubmit: 'clientArray',
                    //cellEdit: true,
                    editurl: 'clientArray',
                    height: '100%',
                    width: $(document).width() - 60,
                    shrinkToFit: true,
                    autoScroll: true,
                    pager: '#pager',
                    rowNum: 20,
                    viewrecords: true//是否显示行数 
                })
                );
            if (rawData.length != 0) {
                $("#assist").css("display", "block");
            }

            $("#" + childName).bind("jqGridAddEditAfterShowForm", (event, element, oper) => {
                if (oper == "edit") {
                    var page = $("#" + childName).jqGrid('getGridParam', 'page');
                    var selectid = parseInt($("#" + childName).jqGrid('getGridParam', 'selrow'));
                    var rownum = $("#" + childName).jqGrid('getGridParam', 'rowNum');
                    var acRowid = ((page - 1) * rownum) + selectid;
                   
                }
            });

            var editModeWidth = 350;


            $("#" + childName).bind("jqGridAddEditAfterSubmit", (event, element, data, oper) => {
                if (oper == "add") {
                    data.t0 = this.mCompanyName;
                    this.mEditOper = "add";

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
                    this.mSaveDataSet.post({
                        data: JSON.stringify(submitData),
                        editOper: this.mEditOper,
                        editOriginalKey: this.mOriginalKey
                    }).then((sr: ISubmitResult) => {
                        if (ErrorCode.OK == sr.errorCode) {
                            Util.MessageBox.tip("添加成功！");
                            $.jgrid.hideModal("#" + $.jgrid.jqID("editmodtable1_jqgrid_1234"), { gb: "#gbox_" + $.jgrid.jqID("table1_jqgrid_1234"), jqm: true, onClose: null });
                            $("#" + childName).addRowData($("#" + childName)[0].p.data.length + 1, data, 'first');
                            var tmpData: Array<any> = $("#" + childName)[0].p.data;
                            tmpData.unshift(tmpData.pop());
                            //$("#pager input.ui-pg-input").val($('input.ui-pg-input').next().text());
                            $("#pager input.ui-pg-input").val(1);
                            var e = $.Event("keypress");
                            e.keyCode = 13;
                            $("#pager input.ui-pg-input").trigger(e);
                            $("#assist").css("display", "block");
                        } else if (ErrorCode.PREMARY_KEY_CONFILICT == sr.errorCode) {
//                            if (this.mDocType == 2) {
//                                Util.MessageBox.tip("添加失败，项目编号重复！");
//                            } else if (this.mDocType == 3) {
//                                Util.MessageBox.tip("添加失败，投标编号重复！");
//                            } else if (this.mDocType == 4) {
//                                Util.MessageBox.tip("添加失败，合同编号重复!");
//                            }
                        } else if (ErrorCode.DATABASE_EXCEPTION == sr.errorCode) {
                            Util.MessageBox.tip("添加失败!");
                        } else {
                        }
                    });

                } else if (oper == "edit") {
                    this.mEditOper = "edit";
                    data.t0 = this.mCompanyName;

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

                    var selectid = parseInt($("#" + childName).jqGrid('getGridParam', 'selrow'));
                    this.mSaveDataSet.post({
                        //mktType: this.mDocType,
                        data: JSON.stringify(submitData),
                        editOper: this.mEditOper,
                        editOriginalKey: this.mOriginalKey
                    }).then((sr: ISubmitResult) => {
                        if (ErrorCode.OK == sr.errorCode) {
                            Util.MessageBox.tip("编辑成功！");

                            $.jgrid.hideModal("#" + $.jgrid.jqID("editmod" + childName), { gb: "#gbox_" + $.jgrid.jqID("table1_jqgrid_1234"), jqm: true, onClose: null });
                            var page = $("#" + childName).jqGrid('getGridParam', 'page');
                            var rownum = $("#" + childName).jqGrid('getGridParam', 'rowNum');
                            var acRowid = ((page - 1) * rownum) + selectid;

                            function swap(arr, a, b) {
                                var tmp = arr[a];
                                arr[a] = arr[b];
                                arr[b] = tmp;
                            }
                            swap($("#" + childName)[0].p.data, selectid - 1, acRowid - 1);
                            $("#" + childName).setRowData(selectid, data);
                            swap($("#" + childName)[0].p.data, selectid - 1, acRowid - 1);

                        } else if (ErrorCode.PREMARY_KEY_CONFILICT == sr.errorCode) {
//                            if (this.mDocType == 2) {
//                                Util.MessageBox.tip("编辑失败，项目编号重复！");
//                            } else if (this.mDocType == 3) {
//                                Util.MessageBox.tip("编辑失败，投标编号重复！");
//                            } else if (this.mDocType == 4) {
//                                Util.MessageBox.tip("编辑失败，合同编号重复!");
//                            }
                        } else if (ErrorCode.DATABASE_EXCEPTION == sr.errorCode) {
                            Util.MessageBox.tip("数据提交失败!");
                        } else {
                        }
                    });
                }
            });

            if (this.mCompanyName == "股份公司") {
                $("#" + childName).jqGrid('navGrid', '#pager', {
                    del: false, add: false, edit: false,
                    addfunc: function() {
                        var dataEdit = $("#" + childName).data("formProp");
                        if (undefined != dataEdit) {
                            dataEdit.width = editModeWidth;
                            dataEdit.datawidth = "auto";
                            $("#" + childName).data("formProp", dataEdit);
                        }
                        $("#" + childName).jqGrid("editGridRow", "new", { width: editModeWidth });
                    },
                    editfunc: function(sr) {
                        var dataEdit = $("#" + childName).data("formProp");
                        if (undefined != dataEdit) {
                            dataEdit.width = editModeWidth;
                            dataEdit.datawidth = "auto";
                            $("#" + childName).data("formProp", dataEdit);
                        }
                        $("#" + childName).jqGrid("editGridRow", sr, { width: editModeWidth });
                    }
                }, {}, {}, {}, { multipleSearch: true });
            } else {
                $("#" + childName).jqGrid('navGrid', '#pager', {
                    del: false, add: true, edit: true,
                    addfunc: function() {
                        var dataEdit = $("#" + childName).data("formProp");
                        if (undefined != dataEdit) {
                            dataEdit.width = editModeWidth;
                            dataEdit.datawidth = "auto";
                            $("#" + childName).data("formProp", dataEdit);
                        }
                        $("#" + childName).jqGrid("editGridRow", "new", { width: editModeWidth });
                    },
                    editfunc: function(sr) {
                        var dataEdit = $("#" + childName).data("formProp");
                        if (undefined != dataEdit) {
                            dataEdit.width = editModeWidth;
                            dataEdit.datawidth = "auto";
                            $("#" + childName).data("formProp", dataEdit);
                        }
                        $("#" + childName).jqGrid("editGridRow", sr, { width: editModeWidth });
                    }
                }, { width: editModeWidth }, { width: editModeWidth }, {}, { multipleSearch: true });
            }
        }
    }
}