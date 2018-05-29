/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../messageBox.ts" />
///<reference path="../dateSelector.ts"/>
var yszkgb;
(function (yszkgb) {
    var router = framework.router;
    var FrameView = /** @class */ (function () {
        function FrameView() {
            this.mNodes = [];
            router.register(this);
        }
        FrameView.prototype.getId = function () {
            return Util.FAMOUS_VIEW;
        };
        FrameView.prototype.onEvent = function (e) {
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
        FrameView.prototype.init = function (opt) {
            var _this = this;
            this.mOpt = opt;
            var minDate = Util.addYear(opt.date, -3);
            minDate.month = 1;
            $("#grid-date").jeDate({
                skinCell: "jedatedeepgreen",
                format: "YYYY年MM月",
                isTime: false,
                isinitVal: true,
                isClear: false,
                isToday: false,
                minDate: Util.date2Str(minDate),
                maxDate: Util.date2Str(opt.date),
            }).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top");
            this.mCompanySelector = new Util.CompanySelector(false, "comp-sel", opt.comps);
            this.mItemSelector = new Util.UnitedSelector(this.mNodes, "item-sel");
            if (this.mNodes.length == 1) {
                this.mItemSelector.hide();
            }
            if (opt.firstItem) {
                this.mItemSelector.update([opt.firstItem]);
            }
            this.mNodes = this.mItemSelector.getTopNodes();
            $("#grid-update").on("click", function () {
                _this.updateUI();
            });
            $("#grid-export").on("click", function () {
                _this.exportExcel();
            });
            $(window).resize(function () {
                _this.adjustHeader();
                _this.mCurrentPlugin.adjustSize();
            });
            this.adjustHeader();
            this.updateUI();
        };
        FrameView.prototype.adjustHeader = function () {
            $("#headerHost").removeCss("width");
            if ($("#headerHost").height() > 40) {
                $(".page-header").addClass("page-header-double");
                $("#headerHost").css("width", $("#sels").width() + "px");
            }
            else {
                $(".page-header").removeClass("page-header-double");
            }
        };
        FrameView.prototype.plugin = function (node) {
            return node.getData().plugin;
        };
        FrameView.prototype.getDate = function () {
            var rq = $("#grid-date").val().replace("年", "-").replace("月", "-").replace("日", "-").split("-");
            return {
                year: rq[0] ? parseInt(rq[0]) : undefined,
                month: rq[1] ? parseInt(rq[1]) : undefined,
                day: rq[2] ? parseInt(rq[2]) : undefined
            };
        };
        FrameView.prototype.updateUI = function () {
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
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
        //不可以起名叫做export 在IE中有冲突
        FrameView.prototype.exportExcel = function () {
            var url = this.mCurrentPlugin.getExportUrl(this.mCurrentDate, this.mCurrentComp);
            $("#exportExcel")[0].action = url;
            $("#exportExcel")[0].submit();
        };
        FrameView.ins = new FrameView();
        return FrameView;
    }());
    yszkgb.FrameView = FrameView;
})(yszkgb || (yszkgb = {}));
