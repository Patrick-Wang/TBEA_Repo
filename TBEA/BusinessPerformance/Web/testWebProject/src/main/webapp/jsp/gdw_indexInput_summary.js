var gdw_indexinput_summary;
(function (gdw_indexinput_summary) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("公司名称", "gsmc", true, JQTable.TextAlign.Left),
                new JQTable.Node("预计指标填写情况", "inputCondition", true, JQTable.TextAlign.Left),
                new JQTable.Node("填写时间", "inputTime", true, JQTable.TextAlign.Left),
                new JQTable.Node("审核时间", "approveTime", true, JQTable.TextAlign.Left),
            ], gridName);
        };
        return JQGridAssistantFactory;
    }());
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
        View.prototype.init = function (tableId, dateId, year, month, isZHCompany) {
            this.mYear = year;
            this.mTableId = tableId;
            this.mMonth = month;
            this.isZHCompany = isZHCompany;
            this.mDs = new Util.DateSelector({ year: year - 3, month: 1 }, { year: year, month: month }, dateId);
            this.onIndexSelected();
            this.onCompanysSelected();
        };
        View.prototype.onIndexSelected = function () {
            this.mIndex = $("#indextype").val();
        };
        View.prototype.onCompanysSelected = function () {
            this.mCompanyType = $("#companytype").val();
            this.mCompanyName = $("#companytype  option:selected").text();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            var date = this.mDs.getDate();
            this.mDataSet.get({ month: date.month, year: date.year, entryType: this.mIndex, companyType: this.mCompanyType })
                .then(function (dataArray) {
                _this.mData = dataArray;
                if (_this.isZHCompany) {
                    $('h1').text(date.year + "年" + date.month + "月" + "众和公司各项目单位预测指标填报情况");
                    document.title = date.year + "年" + date.month + "月" + "众和公司各项目单位预测指标填报情况";
                }
                else {
                    if (_this.mCompanyType == 1) {
                        $('h1').text(date.year + "年" + date.month + "月" + "各经营单位预测指标填报情况");
                        document.title = date.year + "年" + date.month + "月" + "各经营单位预测指标填报情况";
                    }
                    else {
                        $('h1').text(date.year + "年" + date.month + "月" + _this.mCompanyName + "预测指标填报情况");
                        document.title = date.year + "年" + date.month + "月" + _this.mCompanyName + "预测指标填报情况";
                    }
                }
                _this.updateTable();
            });
        };
        View.prototype.formatData = function () {
            var data = [];
            var row = [];
            for (var j = 0; j < this.mData.length; ++j) {
                row = [].concat(this.mData[j]);
                if (null != row[1]) {
                    if (row[1] == "") {
                        row[1] = "尚未提交";
                        row[2] = "--";
                    }
                    if (row[3] == "") {
                        row[3] = "--";
                    }
                }
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
            parent.append("<table id='" + name + "'></table>" + "<div id= 'pager'></div>");
            $("#" + name).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: '100%',
                width: 500,
                shrinkToFit: true,
                autoScroll: true,
                pager: '#pager',
                rowNum: 20,
                viewrecords: true
            }));
        };
        return View;
    }());
    gdw_indexinput_summary.View = View;
})(gdw_indexinput_summary || (gdw_indexinput_summary = {}));
