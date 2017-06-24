/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../messageBox.ts" />
///<reference path="../dateSelector.ts"/>
var yszkgb;
(function (yszkgb) {
    var router = framework.router;
    var EntryView = (function () {
        function EntryView() {
            this.mNodes = [];
            router.register(this);
        }
        EntryView.prototype.getId = function () {
            return Util.FAMOUS_VIEW;
        };
        EntryView.prototype.onEvent = function (e) {
            switch (e.id) {
                case Util.MSG_INIT:
                    this.init(e.data);
                    break;
                case Util.MSG_UPDATE:
                    this.updateUI();
                    break;
                case Util.MSG_REG:
                    var data = { id: this.mNodes.length, value: e.data.name, plugin: e.data.plugin };
                    var node = new Util.DataNode(data);
                    this.mNodes.push(node);
                    break;
            }
        };
        EntryView.prototype.init = function (opt) {
            var _this = this;
            this.mOpt = opt;
            $("#grid-date").jeDate({
                skinCell: "jedatedeepgreen",
                format: "YYYY年MM月",
                isTime: false,
                isinitVal: true,
                isClear: false,
                isToday: false,
                maxDate: Util.date2Str(opt.date),
            }).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top");
            this.mCompanySelector = new Util.CompanySelector(false, "comp-sel", this.mOpt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }
            this.mItemSelector = new Util.UnitedSelector(this.mNodes, "item-sel");
            if (this.mNodes.length == 1) {
                this.mItemSelector.hide();
            }
            this.mNodes = this.mItemSelector.getTopNodes();
            this.updateUI();
            $(window).resize(function () {
                _this.mCurrentPlugin.adjustSize();
            });
            $("#grid-update").on("click", function () {
                _this.updateUI();
            });
            $("#save").on("click", function () {
                _this.mCurrentPlugin.save(_this.mCurrentDate, _this.mCurrentComp);
            });
            $("#submit").on("click", function () {
                _this.mCurrentPlugin.submit(_this.mCurrentDate, _this.mCurrentComp);
            });
            this.updateUI();
        };
        EntryView.prototype.getDate = function () {
            var rq = $("#grid-date").val().replace("年", "-").replace("月", "-").replace("日", "-").split("-");
            return {
                year: rq[0] ? parseInt(rq[0]) : undefined,
                month: rq[1] ? parseInt(rq[1]) : undefined,
                day: rq[2] ? parseInt(rq[2]) : undefined
            };
        };
        EntryView.prototype.plugin = function (node) {
            return node.getData().plugin;
        };
        EntryView.prototype.getActiveNode = function () {
            return this.mItemSelector.getDataNode(this.mItemSelector.getPath());
        };
        EntryView.prototype.updateUI = function () {
            var node = this.getActiveNode();
            var dt = this.getDate();
            dt.day = 1;
            this.mCurrentPlugin = this.plugin(node);
            for (var i = 0; i < this.mNodes.length; ++i) {
                if (node != this.mNodes[i]) {
                    this.plugin(this.mNodes[i]).hide();
                }
            }
            this.mCurrentComp = this.mCompanySelector.getCompany();
            this.mCurrentDate = dt;
            this.mCurrentPlugin.show();
            this.plugin(node).update(dt, this.mCurrentComp);
        };
        EntryView.ins = new EntryView();
        return EntryView;
    })();
})(yszkgb || (yszkgb = {}));
