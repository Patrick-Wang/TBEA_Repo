/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="../companySelector.ts" />
/// <reference path="bglx_selector.ts" />
var fx_cpylsp_hqlyddzl;
(function (fx_cpylsp_hqlyddzl) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            /**
            var nodes = [];
            var titles =["产品类型", "产值", "产量", "中标毛利率", "预计优化后毛利率", "预计优化后毛利额"];
            for (var i = 0; i < titles.length; ++i) {
                if (i == 0) {
                    nodes.push(new JQTable.Node(titles[i], "_" + i, true, JQTable.TextAlign.Left));
                } else {
                    nodes.push(new JQTable.Node(titles[i], "_" + i,false));
                }
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
            **/
            return new JQTable.JQGridAssistant([
                new JQTable.Node("产品类型", "_0", true, JQTable.TextAlign.Left),
                new JQTable.Node("产值", "_1"),
                new JQTable.Node("1层", "1c").append(new JQTable.Node("2层1", "2c1").append(new JQTable.Node("产量", "_2")).append(new JQTable.Node("中标毛利率", "_3")))
                    .append(new JQTable.Node("2层2", "2c2").append(new JQTable.Node("预计优化后毛利率", "_4")).append(new JQTable.Node("预计优化后毛利额", "_5")))
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mDataSet = new Util.Ajax("read.do", false);
            this.mSave = new Util.Ajax("save.do");
        }
        View.getInstance = function () {
            if (View.instance == undefined) {
                View.instance = new View();
            }
            return View.instance;
        };
        View.prototype.initInstance = function (opt) {
            this.mOpt = opt;
            this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, this.mOpt.date, this.mOpt.dateId);
            this.mCompanySelector = new Util.CompanySelector(false, opt.companyId, opt.comps);
            this.mBglxSelector = new Util.BglxSelector(opt.bglxId, opt.bglxs, opt.curbglx, opt.writeorview);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }
            this.updateTitle();
        };
        View.prototype.read = function () {
            var _this = this;
            $("#nodatatips").css("display", "none");
            $("#entryarea").css("display", "");
            var date = this.mDateSelector.getDate();
            this.mDataSet.get({ year: date.year, month: date.month, companyId: this.mCompanySelector.getCompany() })
                .then(function (data) {
                _this.mTableData = data.values;
                _this.updateTitle();
                _this.updateTable(_this.mOpt.tableId);
                $('#save').css("display", "block");
            });
        };
        View.prototype.save = function () {
            var date = this.mDateSelector.getDate();
            var allData = this.mTableAssist.getAllData();
            var submitData = [];
            var colNames = this.mTableAssist.getColNames();
            for (var i = 0; i < allData.length; ++i) {
                submitData.push([]);
                for (var j = 0; j < allData[i].length; ++j) {
                    if (j != 1) {
                        submitData[i].push(allData[i][j]);
                        allData[i][j] = allData[i][j].replace(new RegExp(' ', 'g'), '');
                    }
                }
            }
            this.mSave.post({
                year: date.year,
                month: date.month,
                entryType: this.mOpt.entryType,
                companyId: this.mCompanySelector.getCompany(),
                data: JSON.stringify(submitData)
            }).then(function (data) {
                if ("true" == data.result) {
                    Util.MessageBox.tip("保存 成功");
                }
                else if ("false" == data.result) {
                    Util.MessageBox.tip("保存 失败");
                }
                else {
                    Util.MessageBox.tip(data.result);
                }
            });
        };
        View.prototype.updateTitle = function () {
            var header = "";
            var date = this.mDateSelector.getDate();
            var compName = this.mCompanySelector.getCompanyName();
            header = date.year + "年" + date.month + "月 " + compName + " 制造业指标情况数据录入";
            $('h1').text(header);
            document.title = header;
        };
        View.prototype.updateTable = function (tableId) {
            var name = tableId + "_jqgrid";
            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            this.mTableAssist = JQGridAssistantFactory.createTable(name);
            for (var i = 0; i < this.mTableData.length; ++i) {
                for (var j = 2; j < this.mTableData[i].length; ++j) {
                    if ("" != this.mTableData[i][j]) {
                        this.mTableData[i][j] = parseFloat(this.mTableData[i][j]) + "";
                    }
                }
            }
            var data = this.mTableData;
            var lastsel = "";
            var lastcell = "";
            $("#" + name).jqGrid(this.mTableAssist.decorate({
                // url: "TestTable/WGDD_load.do",
                // datatype: "json",
                data: this.mTableAssist.getDataWithId(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth : false,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: data.length > 25 ? 550 : '100%',
                width: 1000,
                shrinkToFit: true,
                autoScroll: true,
                rowNum: 150,
                onSelectCell: function (id, nm, tmp, iRow, iCol) {
                    //                       console.log(iRow +', ' + iCol);
                },
                //                    onCellSelect: (ri,ci,tdHtml,e) =>{
                //                       console.log(ri +', ' + ci);
                //                    },
                beforeSaveCell: function (rowid, cellname, v, iRow, iCol) {
                    var ret = parseFloat(v.replace(new RegExp(',', 'g'), ''));
                    if (isNaN(ret)) {
                        $.jgrid.jqModal = {
                            width: 290,
                            left: $("#table").offset().left + $("#table").width() / 2 - 290 / 2,
                            top: $("#table").offset().top + $("#table").height() / 2 - 90 };
                        return v;
                    }
                    else {
                        return ret;
                    }
                },
                beforeEditCell: function (rowid, cellname, v, iRow, iCol) {
                    lastsel = iRow;
                    lastcell = iCol;
                    //                        console.log(iRow +', ' + iCol);
                    $("input").attr("disabled", true);
                },
                afterEditCell: function (rowid, cellname, v, iRow, iCol) {
                    $("input[type=text]").bind("keydown", function (e) {
                        if (e.keyCode === 13) {
                            setTimeout(function () {
                                $("#" + name).jqGrid("editCell", iRow + 1, iCol, true);
                            }, 10);
                        }
                    });
                },
                afterSaveCell: function () {
                    $("input").attr("disabled", false);
                    lastsel = "";
                },
                afterRestoreCell: function () {
                    $("input").attr("disabled", false);
                    lastsel = "";
                }
            }));
            $('html').bind('click', function (e) {
                if (lastsel != "") {
                    if ($(e.target).closest("#" + name).length == 0) {
                        //  $("#" + name).jqGrid('saveRow', lastsel); 
                        $("#" + name).jqGrid("saveCell", lastsel, lastcell);
                        //$("#" + name).resetSelection(); 
                        lastsel = "";
                    }
                }
            });
        };
        return View;
    })();
    fx_cpylsp_hqlyddzl.View = View;
})(fx_cpylsp_hqlyddzl || (fx_cpylsp_hqlyddzl = {}));
