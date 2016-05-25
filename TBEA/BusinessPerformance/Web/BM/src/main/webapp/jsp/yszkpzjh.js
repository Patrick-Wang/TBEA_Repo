var yszkpzjh;
(function (yszkpzjh) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createYSPZ1 = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("上月末累计销售收入", "symljxssr", true, 1 /* Right */, 100),
                new JQTable.Node("本月计划销售收入", "byjhxssr", true, 1 /* Right */, 100),
                new JQTable.Node("本月目标责任书应收指标", "bymbzrsyszb", true, 1 /* Right */, 100),
                new JQTable.Node("本月应收内控指标", "byysnkzb", true, 1 /* Right */, 100),
                new JQTable.Node("本月资金回笼计划", "byzjhljh", true, 1 /* Right */, 120)
            ], gridName);
        };
        JQGridAssistantFactory.createYSPZ2 = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("(加)本月销售收入新增应收金额", "byxssrxzysje", true, 1 /* Right */, 100),
                new JQTable.Node("(减)本月可降应收资金回笼金额", "bykjyszjhlje", true, 1 /* Right */, 100),
                new JQTable.Node("(加)本月归还保理增加应收金额", "byghblzjysje", true, 1 /* Right */, 100),
                new JQTable.Node("(减)本月新增保理回款冲减应收金额", "byxzblhkcjysje", true, 1 /* Right */, 120),
                new JQTable.Node("本月预计账面应收余额", "byyjzmysye", true, 1 /* Right */, 100),
                new JQTable.Node("与目标责任书指标差距", "ymbzeszbcj", true, 1 /* Right */, 100),
                new JQTable.Node("与内部控制指标差距", "ynbkzzbcj", true, 1 /* Right */, 100)
            ], gridName);
        };
        JQGridAssistantFactory.createYSPZ3 = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("上月末账面<br/>应收余额", "symzbysye", true, 1 /* Right */, 100)
            ], gridName);
        };
        JQGridAssistantFactory.createYSPZ4 = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("(减)上月末已开票未发货产生应收金额", "symykfpfhscysje", true, 1 /* Right */, 100),
                new JQTable.Node("(加)上月末已发货未开票增加实际应收金额", "symyfhwkpzjsjsuje", true, 1 /* Right */, 100),
                new JQTable.Node("(加)上月末保理回款冲减应收金额", "symblhkzjysje", true, 1 /* Right */, 100),
                new JQTable.Node("(加)上月末预收冲减应收的金额", "symyscjysdje", true, 1 /* Right */, 120),
                new JQTable.Node("(加)其他冲减应收", "qtcjys", true, 1 /* Right */, 100),
                new JQTable.Node("上月实际应收余额", "sysjysye", true, 1 /* Right */, 100),
                new JQTable.Node("(加)本月发货产品新增应收金额", "byfhcpxzysje", true, 1 /* Right */, 100),
                new JQTable.Node("(减)本月回款降低应收金额（发货后的款项）", "byhkjdysje", true, 1 /* Right */, 100),
                new JQTable.Node("本月预计实际应收余额", "byyjsjysye", true, 1 /* Right */, 100),
                new JQTable.Node("与目标责任书指标差距", "ymbzeszbcj", true, 1 /* Right */, 100),
                new JQTable.Node("与内部控制指标差距", "ynbkzzbcj", true, 1 /* Right */, 100)
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mComp = 95 /* SBGS */;
            this.mDataSet = new Util.Ajax("yszkpzjh_update.do");
        }
        View.newInstance = function () {
            return new View();
        };
        View.prototype.init = function (echartId, tableId, month, year) {
            this.mYear = year;
            this.mMonth = month;
            this.mTableId = tableId;
            this.mEchartId = echartId;
            this.updateTable(this.mTableId);
            this.updateUI();
        };
        View.prototype.onYearSelected = function (year) {
            this.mYear = year;
        };
        View.prototype.onMonthSelected = function (month) {
            this.mMonth = month;
        };
        View.prototype.onCompanySelected = function (comp) {
            this.mComp = comp;
        };
        View.prototype.convertData = function (src) {
            var dest = [];
            var row = [];
            for (var i in src.list1) {
                row.push(Util.formatCurrency(src.list1[i]));
            }
            dest.push(row);
            row = [];
            for (var i in src.list2) {
                row.push(Util.formatCurrency(src.list2[i]));
            }
            dest.push(row);
            row = [];
            for (var i in src.list3) {
                row.push(Util.formatCurrency(src.list4[i]));
            }
            dest.push(row);
            row = [];
            for (var i in src.list4) {
                row.push(Util.formatCurrency(src.list4[i]));
            }
            dest.push(row);
            row = [];
            return dest;
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mDataSet.get({ month: this.mMonth, year: this.mYear, companyId: this.mComp }).then(function (data) {
                _this.mData = data;
                $('#title h1').text(_this.mYear + "年" + _this.mMonth + "月 应收账款盘子规划");
                document.title = _this.mYear + "年" + _this.mMonth + "月 应收账款盘子规划";
                $("#dataarea tr").css("display", "none");
                for (var i = 0; i < _this.mData.length; ++i) {
                    var curData = _this.mData[i];
                    $("#dataarea #" + curData.companyType).css("display", "");
                    $("#dataarea #" + curData.companyType + "block").css("display", "");
                    _this.updateTable("list" + curData.companyType, _this.convertData(curData));
                }
            });
        };
        View.prototype.refreshTable = function (listName, refreshTags, list) {
            for (var i = 0; i < refreshTags.length; i++) {
                if (refreshTags[i]) {
                    if (!(list[i] >= 0)) {
                        $("#" + listName + (i + 1) + "_jqgrid_1234").jqGrid('saveCell', '1', list[i]);
                        list[i] = -1;
                    }
                    $("#" + listName + (i + 1) + "_jqgrid_1234").trigger("reloadGrid");
                    refreshTags[i] = false;
                }
            }
        };
        View.prototype.updateTable = function (listName, currData) {
            var _this = this;
            var data = [[[""]], [[""]], [[""]], [[""]]];
            if (undefined != this.mData) {
                data[0] = [currData[0]];
                data[1] = [currData[1]];
                data[2] = [currData[2]];
                data[3] = [currData[3]];
            }
            var selectGrid = null;
            var selectRow = 0;
            var currentList;
            var refreshTags = [false, false, false, false];
            var listCurrentColum = [-1, -1, -1, -1];
            var name1 = listName + "1" + "_jqgrid_1234";
            var parent = $("#" + listName + "1");
            parent.empty();
            parent.append("<table id='" + name1 + "'></table>");
            var t1Assist = JQGridAssistantFactory.createYSPZ1(name1);
            t1Assist.disableDragCell();
            $("#" + name1).jqGrid(t1Assist.decorate({
                data: t1Assist.getData(data[0]),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                autowidth: false,
                cellEdit: true,
                height: '100%',
                width: '100%',
                shrinkToFit: false,
                autoScroll: true,
                beforeEditCell: function (id, nm, tmp, iRow, iCol) {
                    listCurrentColum[0] = iCol;
                },
                onSelectCell: function (id, nm, tmp, iRow, iCol) {
                    if (currentList != $("#" + name1)) {
                        currentList = $("#" + name1);
                        refreshTags = [false, true, true, true];
                    }
                    _this.refreshTable(listName, refreshTags, listCurrentColum);
                    t1Assist.selected(iRow, iCol);
                },
                gridComplete: function () {
                    $("#gbox_" + name1).css("border-bottom", "0px solid #a6c9e2");
                    $("#" + name1 + " #1").css("height", "24px");
                }
            }));
            var name2 = listName + "2" + "_jqgrid_1234";
            parent = $("#" + listName + "2");
            parent.empty();
            parent.append("<table id='" + name2 + "'></table>");
            var t2Assist = JQGridAssistantFactory.createYSPZ2(name2);
            t2Assist.disableDragCell();
            $("#" + name2).jqGrid(t2Assist.decorate({
                data: t2Assist.getData(data[1]),
                datatype: "local",
                drag: false,
                resize: false,
                multiselect: false,
                cellEdit: true,
                height: '100%',
                width: '100%',
                shrinkToFit: false,
                autoScroll: true,
                beforeEditCell: function (id, nm, tmp, iRow, iCol) {
                    listCurrentColum[1] = iCol;
                },
                onCellSelect: function () {
                    if (currentList != $("#" + name2)) {
                        currentList = $("#" + name2);
                        refreshTags = [true, false, true, true];
                    }
                    _this.refreshTable(listName, refreshTags, listCurrentColum);
                },
                gridComplete: function () {
                    $("#gbox_" + name2).css("border-left", "0px solid #a6c9e2");
                    $("#gbox_" + name2).css("border-bottom", "0px solid #a6c9e2");
                    $("#" + name2 + " #1").css("height", "24px");
                }
            }));
            var name4 = listName + "4" + "_jqgrid_1234";
            parent = $("#" + listName + "4");
            parent.empty();
            parent.append("<table id='" + name4 + "'></table>");
            var t4Assist = JQGridAssistantFactory.createYSPZ4(name4);
            t4Assist.disableDragCell();
            $("#" + name4).jqGrid(t4Assist.decorate({
                data: t4Assist.getData(data[3]),
                datatype: "local",
                cellEdit: true,
                multiselect: false,
                height: '100%',
                width: '100%',
                shrinkToFit: false,
                autoScroll: true,
                beforeEditCell: function (id, nm, tmp, iRow, iCol) {
                    listCurrentColum[3] = iCol;
                },
                onCellSelect: function () {
                    if (currentList != $("#" + name4)) {
                        currentList = $("#" + name4);
                        refreshTags = [true, true, true, false];
                    }
                    _this.refreshTable(listName, refreshTags, listCurrentColum);
                },
                gridComplete: function () {
                    $("#gbox_" + name4).css("border-left", "0px solid #a6c9e2");
                    $("#" + name4 + " #1").css("height", "24px");
                }
            }));
            var name3 = listName + "3" + "_jqgrid_1234";
            parent = $("#" + listName + "3");
            parent.empty();
            parent.append("<table id='" + name3 + "'></table>");
            var t3Assist = JQGridAssistantFactory.createYSPZ3(name3);
            t3Assist.disableDragCell();
            var colModel = t3Assist.getColModel();
            colModel[0].height = 70;
            $("#" + name3).jqGrid(t3Assist.decorate({
                data: t3Assist.getData(data[2]),
                datatype: "local",
                colModel: colModel,
                cellEdit: true,
                multiselect: false,
                height: '100%',
                width: '100%',
                shrinkToFit: false,
                autoScroll: true,
                onCellSelect: function () {
                    if (currentList != $("#" + name3)) {
                        currentList = $("#" + name3);
                        refreshTags = [true, true, false, true];
                    }
                    _this.refreshTable(listName, refreshTags, listCurrentColum);
                },
                beforeEditCell: function (id, nm, tmp, iRow, iCol) {
                    listCurrentColum[2] = iCol;
                },
                gridComplete: function () {
                    var grid = $("#" + name3);
                    var height = parseInt($("#gview_" + name2).css("height").replace("px", "")) + parseInt($("#gview_" + name4).css("height").replace("px", ""));
                    var titleHeight = parseInt($("#" + name2 + "_" + t2Assist.id(0)).css("height").replace("px", ""));
                    $("#" + name3 + " #1").css("height", (height - titleHeight) + "px");
                    $("#gbox_" + name3).css("border-right", "0px solid #a6c9e2");
                }
            }));
        };
        return View;
    })();
    yszkpzjh.View = View;
})(yszkpzjh || (yszkpzjh = {}));