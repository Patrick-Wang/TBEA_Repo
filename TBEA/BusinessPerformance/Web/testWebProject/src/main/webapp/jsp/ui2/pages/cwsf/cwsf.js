///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="../components/dateSelectorProxy.ts"/>
///<reference path="../unitedSelector.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var cwsf;
(function (cwsf) {
    var router = framework.router;
    var FrameEvent = framework.basic.FrameEvent;
    var CwsfShowView = (function (_super) {
        __extends(CwsfShowView, _super);
        function CwsfShowView() {
            _super.apply(this, arguments);
        }
        CwsfShowView.prototype.getMaxWidth = function (opts) {
            var max = 0;
            var tmp = 0;
            var fontSize = Util.isMSIE() ? 14 : 13;
            for (var i = 0; i < opts.length; ++i) {
                tmp = $(opts[i]).text().getWidth(fontSize) + 25;
                if (max < tmp) {
                    max = tmp;
                }
            }
            return max;
        };
        CwsfShowView.prototype.getStartDate = function () {
            var ret = {};
            var curDate = $("#dstart").getDate();
            ret = {
                year: curDate.getFullYear(),
                month: curDate.getMonth() + 1,
                day: curDate.getDate()
            };
            return ret;
        };
        CwsfShowView.prototype.getEndDate = function () {
            var ret = {};
            var curDate = $("#dEnd").getDate();
            ret = {
                year: curDate.getFullYear(),
                month: curDate.getMonth() + 1,
                day: curDate.getDate()
            };
            return ret;
        };
        CwsfShowView.prototype.init = function (opt) {
            var _this = this;
            this.mOpt = opt;
            var startOpt = this.createInternalDate("dstart", opt.date, {
                nowDate: Util.date2Str(Util.addMonth(opt.date, -1)),
                minDate: Util.date2Str(opt.dateStart),
                maxDate: Util.date2Str(opt.date),
                choosefun: function (elem, val, date) {
                    setTimeout(function () {
                        endOpt.minDate = Util.date2Str(_this.getStartDate());
                        endDates();
                    }, 0);
                }
            });
            var endOpt = this.createInternalDate("dEnd", opt.date, {
                nowDate: Util.date2Str(opt.date),
                minDate: startOpt.nowDate,
                maxDate: Util.date2Str(opt.dateEnd),
                choosefun: function (elem, val, date) {
                    startOpt.maxDate = Util.date2Str(_this.getEndDate()); //将结束日的初始值设定为开始日的最大日期
                }
            });
            //这里是日期联动的关键
            function endDates() {
                //将结束日期的事件改成 false 即可
                endOpt.insTrigger = false;
                $("#dEnd").jeDate(endOpt).jePopup();
            }
            var nodes = [];
            for (var i = 0; i < this.mNodesAll.length; ++i) {
                if (router.to(this.plugin(this.mNodesAll[i])).send(FrameEvent.FE_IS_COMPANY_SUPPORTED)) {
                    nodes.push(this.mNodesAll[i]);
                }
            }
            this.mItemSelector = new Util.UnitedSelector(nodes, this.mOpt.type);
            if (nodes.length == 1) {
                this.mItemSelector.hide();
            }
            this.mCompanySelector = new Util.CompanySelector(true, this.mOpt.comp, this.mOpt.comps);
            if (opt.comps.length <= 10) {
                this.mCompanySelector.hide();
            }
            this.mCompanySelector.checkAll();
            this.unitedSelector = new Util.UnitedSelector(opt.itemNodes, opt.itemId);
            this.updateUI();
            $(window).resize(function () {
                _this.adjustHeader();
                router.to(_this.mCurrentPlugin).send(FrameEvent.FE_ADJUST_SZIE);
            });
            this.adjustHeader();
        };
        CwsfShowView.prototype.onEvent = function (e) {
            switch (e.id) {
                case FrameEvent.FE_EXPORTEXCEL:
                    this.exportExcel(e.data);
                    break;
            }
            return _super.prototype.onEvent.call(this, e);
        };
        CwsfShowView.prototype.updateUI = function () {
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            this.mCurrentPlugin = this.plugin(node);
            router.broadcast(FrameEvent.FE_HIDE);
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SHOW);
            this.mCompanySelector.getCompanyNames();
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                dStart: Util.date2Str(this.getStartDate()),
                dEnd: Util.date2Str(this.getEndDate()),
                comps: JSON.stringify(this.mCompanySelector.getCompanyNames()),
                item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.value
            });
        };
        CwsfShowView.prototype.exportExcel = function (elemId) {
            var url = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GET_EXPORTURL, {
                dStart: Util.date2Str(this.getStartDate()),
                dEnd: Util.date2Str(this.getEndDate()),
                comps: JSON.stringify(this.mCompanySelector.getCompanyNames()),
                item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.value
            });
            $("#" + elemId)[0].action = url;
            $("#" + elemId)[0].submit();
        };
        return CwsfShowView;
    })(framework.basic.ShowFrameView);
    var ins = new CwsfShowView();
})(cwsf || (cwsf = {}));
