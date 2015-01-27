var entry_template;
(function (entry_template) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createFlatTable = function (gridName, title) {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i == 0) {
                    nodes.push(new JQTable.Node(title[i], "_" + i, true, 0 /* Left */));
                }
                else {
                    nodes.push(new JQTable.Node(title[i], "_" + i, false));
                }
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mDataSet = new Util.Ajax("zb_update.do");
            this.mSubmit = new Util.Ajax("zb_submit.do");
        }
        View.getInstance = function () {
            return View.instance;
        };
        View.prototype.initInstance = function (opt) {
            this.mOpt = opt;
            this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 1 }, this.mOpt.date, this.mOpt.dateId);
            this.updateTitle();
            this.updateUI();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            var date = this.mDateSelector.getDate();
            this.mDataSet.get({ year: date.year, month: date.month, entryType: this.mOpt.entryType }).then(function (data) {
                _this.mTableData = data;
                _this.updateTitle();
                _this.updateTable(_this.mOpt.tableId);
            });
        };
        View.prototype.submit = function () {
            var date = this.mDateSelector.getDate();
            this.mSubmit.post({
                year: date.year,
                month: date.month,
                entryType: this.mOpt.entryType,
                data: JSON.stringify(this.mTableAssist.getAllData())
            }).then(function (data) {
                if (data.result) {
                    alert("submit 成功");
                }
                else {
                    alert("submit 失敗");
                }
            });
        };
        View.prototype.updateTitle = function () {
            var header = "";
            var date = this.mDateSelector.getDate();
            switch (this.mOpt.entryType) {
                case 0 /* QNJH */:
                    header = date.year + "年 计划数据录入";
                    break;
                case 1 /* BY20JH */:
                    header = date.year + "年" + date.month + "月 20日计划值录入";
                    break;
                case 2 /* BY28JH */:
                    header = date.year + "年" + date.month + "月 28日计划值录入";
                    break;
                case 3 /* BY20YJ */:
                    header = date.year + "年" + date.month + "月 20日预计值录入";
                    break;
                case 4 /* BY28YJ */:
                    header = date.year + "年" + date.month + "月 28日预计值录入";
                    break;
                case 5 /* BYSJ */:
                    header = date.year + "年" + date.month + "月 实际数据录入";
                    break;
            }
            $('h1').text(header);
            document.title = header;
        };
        View.prototype.createPredict = function (title) {
            var ret = [title[0]];
            var date = this.mDateSelector.getDate();
            var left = date.month % 3;
            if ((this.mOpt.entryType == 1 /* BY20JH */ || this.mOpt.entryType == 2 /* BY28JH */) && 0 == left) {
                if (12 == date.month) {
                    ret.push((date.year + 1) + "年1月计划");
                    ret.push((date.year + 1) + "年2月计划");
                    ret.push((date.year + 1) + "年3月计划");
                }
                else {
                    ret.push((date.month + 1) + "月计划");
                    ret.push((date.month + 2) + "月计划");
                    ret.push((date.month + 3) + "月计划");
                }
            }
            else if (this.mOpt.entryType == 3 /* BY20YJ */ || this.mOpt.entryType == 4 /* BY28YJ */) {
                ret.push(title[1]);
                if (0 != left) {
                    var leftMonth = 3 - left;
                    for (var i = 1; i <= leftMonth; ++i) {
                        ret.push((date.month + i) + "月预计");
                    }
                }
            }
            return ret;
        };
        View.prototype.updateTable = function (tableId) {
            var name = tableId + "_jqgrid";
            var titles = null;
            switch (this.mOpt.entryType) {
                case 0 /* QNJH */:
                    titles = ["指标名称", "全年计划"];
                    break;
                case 1 /* BY20JH */:
                case 3 /* BY20YJ */:
                    titles = this.createPredict(["指标名称", "本月20日预计值"]);
                    break;
                case 2 /* BY28JH */:
                case 4 /* BY28YJ */:
                    titles = this.createPredict(["指标名称", "本月28日预计值"]);
                    break;
                case 5 /* BYSJ */:
                    titles = ["指标名称", "本月实际"];
                    break;
            }
            this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles);
            var data = this.mTableData;
            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(this.mTableAssist.decorate({
                data: this.mTableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: '100%',
                width: titles.length * 200,
                shrinkToFit: true,
                autoScroll: true
            }));
        };
        View.instance = new View();
        return View;
    })();
    entry_template.View = View;
})(entry_template || (entry_template = {}));
