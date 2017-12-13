/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
var yszkrb;
(function (yszkrb) {
    var router = framework.router;
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("集团下达月度资金回笼指标", "t1", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("各单位自行制定的回款计划", "t2", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("今日回款", "t3", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("已回款中可降应收的回款金额", "t4", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("确保办出", "t5", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("争取办出", "t6", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("截止月底应收账款账面余额", "t7", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
            ], gridName);
        };
        return JQGridAssistantFactory;
    }());
    var SimpleView = (function () {
        function SimpleView() {
            this.mAjaxSubmit = new Util.Ajax("/BusinessManagement/dailyReport/yszk_submit.do");
            this.mAjaxUpdate = new Util.Ajax("/BusinessManagement/dailyReport/yszk_update.do", false);
            this.mStopBtn = false;
            router.register(this);
        }
        SimpleView.prototype.getId = function () {
            return Util.FAMOUS_VIEW;
        };
        SimpleView.prototype.onEvent = function (e) {
            switch (e.id) {
                case Util.MSG_INIT:
                    this.init(e.data);
                    break;
            }
        };
        SimpleView.prototype.init = function (opt) {
            var _this = this;
            this.mOpt = opt;
            var minDate = Util.addYear(opt.date, -1);
            minDate.month = 1;
            $("#grid-date").jeDate({
                skinCell: "jedatedeepgreen",
                format: "YYYY年MM月DD日",
                isTime: false,
                isinitVal: true,
                isClear: false,
                isToday: false
            }).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top");
            $(window).resize(function () {
                _this.adjustSize();
            });
            $("#grid-update").on("click", function () {
                if (!_this.mStopBtn) {
                    _this.updateUI();
                }
            });
            $("#submit").on("click", function () {
                if (!_this.mStopBtn) {
                    _this.submit();
                }
            });
            this.updateUI();
        };
        SimpleView.prototype.updateUI = function () {
            var _this = this;
            this.mAjaxUpdate.get(this.getDate())
                .then(function (dataArray) {
                _this.mData = dataArray;
                if (dataArray.length == 0) {
                    var pro = $("#prompt");
                    pro.empty();
                    pro.append("<b>暂时没有数据！</b>");
                }
                else {
                    var pro = $("#prompt");
                    pro.empty();
                }
                _this.updateTable();
            });
        };
        SimpleView.prototype.getDate = function () {
            var rq = $("#grid-date").val().replace("年", "-").replace("月", "-").replace("日", "-").split("-");
            return {
                year: rq[0] ? parseInt(rq[0]) : undefined,
                month: rq[1] ? parseInt(rq[1]) : undefined,
                day: rq[2] ? parseInt(rq[2]) : undefined
            };
        };
        SimpleView.prototype.jqgrid = function () {
            return $("#" + this.jqgridName());
        };
        SimpleView.prototype.jqgridName = function () {
            return this.mOpt.tableId + "_jqgrid_real";
        };
        SimpleView.prototype.adjustSize = function () {
            var jqgrid = this.jqgrid();
            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }
            var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
            this.tableAssist && this.tableAssist.resizeHeight(maxTableBodyHeight);
            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }
        };
        SimpleView.prototype.submit = function () {
            var allData = this.tableAssist.getAllData();
            var submitData = [];
            var colNames = this.tableAssist.getColNames();
            for (var i = 0; i < allData.length; ++i) {
                submitData.push([]);
                for (var j = 0; j < allData[i].length; ++j) {
                    if (j != 0) {
                        submitData[i].push(allData[i][j]);
                        allData[i][j] = allData[i][j].replace(new RegExp(' ', 'g'), '');
                    }
                }
            }
            this.mAjaxSubmit.post($.extend(this.getDate(), {
                data: JSON.stringify(submitData[0])
            })).then(function (resp) {
                if (Util.ErrorCode.OK == resp.errorCode) {
                    Util.Toast.success("提交 成功");
                }
                else {
                    Util.Toast.failed(resp.message);
                }
            });
        };
        SimpleView.prototype.refresh = function () {
            if (this.mData == undefined) {
                return;
            }
            this.updateTable();
        };
        SimpleView.prototype.createJqassist = function () {
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='" + this.jqgridName() + "'></table>");
            this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
            return this.tableAssist;
        };
        SimpleView.prototype.updateTable = function () {
            var _this = this;
            this.createJqassist();
            var data = [];
            var row = [];
            for (var i = 0; i < this.mData.length; ++i) {
                data.push([]);
                if (this.mData[i] instanceof Array) {
                    row = [].concat(this.mData[i]);
                    for (var col in row) {
                        row[col] = Util.formatCurrency(row[col]);
                    }
                    data[i] = data[i].concat(row);
                }
            }
            this.tableAssist.create({
                data: this.mData,
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: '100%',
                width: this.tableAssist.getColNames().length * 400,
                shrinkToFit: true,
                rowNum: 2000,
                autoScroll: true,
                assistEditable: true,
                assistOnEdit: function () {
                    _this.mStopBtn = true;
                    $('.btn').attr("disabled", true);
                },
                assistPostEdit: function () {
                    _this.mStopBtn = false;
                    $('.btn').attr("disabled", false);
                }
            });
            this.adjustSize();
        };
        SimpleView.ins = new SimpleView();
        return SimpleView;
    }());
    yszkrb.SimpleView = SimpleView;
})(yszkrb || (yszkrb = {}));
