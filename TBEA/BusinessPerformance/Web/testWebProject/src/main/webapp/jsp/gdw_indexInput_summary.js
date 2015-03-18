/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
var gdw_indexinput_summary;
(function (gdw_indexinput_summary) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("公司名称", "gsmc"),
                new JQTable.Node("预计指标填写情况", "inputCondition"),
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("status_update.do");
        }
        View.newInstance = function () {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        };
        View.prototype.init = function (tableId, dateId, year, month) {
            this.mYear = year;
            this.mTableId = tableId;
            this.mMonth = month;
            this.mDs = new Util.DateSelector({ year: year - 2, month: 1 }, { year: year, month: month }, dateId);
            this.onIndexSelected();
        };
        View.prototype.onIndexSelected = function () {
            this.mIndex = $("#indextype").val();
            //this.mIndex = $("#indextype  option:selected").text();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            var date = this.mDs.getDate();
            //this.onIndexSelected();
            this.mDataSet.get({ month: date.month, year: date.year, entryType: this.mIndex }).then(function (dataArray) {
                _this.mData = dataArray;
                $('h1').text(date.year + "年" + date.month + "月" + "经营单位预测指标填报情况");
                document.title = date.year + "年" + date.month + "月" + "经营单位预测指标填报情况";
                _this.updateTable();
            });
        };
        View.prototype.formatData = function () {
            var data = [];
            var row = [];
            for (var j = 0; j < this.mData.length; ++j) {
                row = [].concat(this.mData[j]);
                if (row.length == 2 && null != row[1]) {
                    if (row[1] == "true") {
                        row[1] = "已提交";
                    }
                    else {
                        row[1] = "尚未提交";
                    }
                }
                //mdata[j] = data[j].concat(row);
                data.push(row);
            }
            return data;
        };
        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var data = [];
            var tableAssist = null;
            tableAssist = JQGridAssistantFactory.createTable(name);
            data = this.formatData();
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                // url: "TestTable/WGDD_load.do",
                // datatype: "json",
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth : false,
                //                    cellsubmit: 'clientArray',
                //                    cellEdit: true,
                height: '100%',
                width: 500,
                shrinkToFit: true,
                rowNum: 100,
                autoScroll: true
            }));
        };
        return View;
    })();
    gdw_indexinput_summary.View = View;
})(gdw_indexinput_summary || (gdw_indexinput_summary = {}));
