///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="cpzlqkdef.ts"/>
///<reference path="../components/dateSelectorProxy.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var cpzlqk;
(function (cpzlqk) {
    var router = framework.router;
    var FrameEvent = framework.basic.FrameEvent;
    var CpzlqkFrameView = (function (_super) {
        __extends(CpzlqkFrameView, _super);
        function CpzlqkFrameView() {
            _super.apply(this, arguments);
            this.isCompanySupported = false;
        }
        CpzlqkFrameView.prototype.init = function (opt) {
            var _this = this;
            this.mOpt = opt;
            var dsp = new Util.DateSelectorProxy(this.mOpt.dt, { year: this.mOpt.date.year - 3, month: 1 }, {
                year: this.mOpt.date.year,
                month: 12
            }, {
                year: this.mOpt.date.year,
                month: this.mOpt.date.month
            }, false, false);
            this.mDtSec = dsp;
            this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.comp, this.mOpt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }
            this.mCompanySelector.change(function (selector, depth) {
                _this.updateTypeSelector();
            });
            var inputs = $("#" + this.mOpt.contentType).show();
            inputs.click(function (e) {
                var node = _this.triggerYdjdChecked();
            });
            this.updateTypeSelector();
            this.updateUI();
        };
        CpzlqkFrameView.prototype.checkCompanySupported = function () {
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            var isSupported = router.to(this.plugin(node)).send(cpzlqk.Event.ZLFE_IS_COMPANY_SUPPORTED, this.mOpt.comps.length);
            if (undefined == isSupported || isSupported) {
                if (this.mOpt.comps.length > 1) {
                    this.mCompanySelector.show();
                }
                this.isCompanySupported = true;
            }
            else {
                this.mCompanySelector.hide();
                this.isCompanySupported = false;
            }
        };
        CpzlqkFrameView.prototype.checkYdjdSupported = function () {
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            var isSupported = router.to(this.plugin(node)).send(cpzlqk.Event.ZLFE_IS_YDJD_SUPPORTED);
            if (isSupported) {
                $("#" + this.mOpt.contentType).show();
                this.triggerYdjdChecked();
            }
            else {
                $("#" + this.mOpt.contentType).hide();
                this.mYdjdType = undefined;
            }
        };
        CpzlqkFrameView.prototype.triggerYdjdChecked = function () {
            var inputs = $("#" + this.mOpt.contentType + " input");
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            for (var i = 0; i < inputs.length; i++) {
                if (true == inputs[i].checked) {
                    if (inputs[i].id == 'rdyd') {
                        this.mYdjdType = cpzlqk.YDJDType.YD;
                        router.to(this.plugin(node)).send(cpzlqk.Event.ZLFE_YD_SELECTED);
                        var dtNow = this.mDtSec.getDate();
                        $("#" + this.mOpt.dt).empty();
                        var dsp = new Util.DateSelectorProxy(this.mOpt.dt, { year: this.mOpt.date.year - 3, month: 1 }, {
                            year: this.mOpt.date.year,
                            month: 12
                        }, dtNow, false, false);
                        this.mDtSec = dsp;
                    }
                    else {
                        this.mYdjdType = cpzlqk.YDJDType.JD;
                        var dtNow = this.mDtSec.getDate();
                        $("#" + this.mOpt.dt).empty();
                        var dsp = new Util.DateSelectorProxy(this.mOpt.dt, { year: this.mOpt.date.year - 3, month: 1 }, {
                            year: this.mOpt.date.year,
                            month: 12
                        }, dtNow, false, true);
                        this.mDtSec = dsp;
                        router.to(this.plugin(node)).send(cpzlqk.Event.ZLFE_JD_SELECTED);
                    }
                }
            }
            return node;
        };
        CpzlqkFrameView.prototype.updateTypeSelector = function (width) {
            var _this = this;
            if (width === void 0) { width = 285; }
            if (_super.prototype.updateTypeSelector.call(this, width)) {
                this.mItemSelector.change(function () {
                    _this.checkCompanySupported();
                    _this.checkYdjdSupported();
                });
                this.checkCompanySupported();
                this.checkYdjdSupported();
                return true;
            }
            return false;
        };
        CpzlqkFrameView.prototype.onEvent = function (e) {
            switch (e.id) {
                case cpzlqk.Event.ZLFE_SAVE_COMMENT:
                    router.to(this.mCurrentPlugin).send(cpzlqk.Event.ZLFE_SAVE_COMMENT, $("#commentText").val());
                    break;
                case cpzlqk.Event.ZLFE_COMMENT_DENY:
                    $("#comment").hide();
                    break;
                case cpzlqk.Event.ZLFE_COMMENT_UPDATED:
                    $("#comment").show();
                    $("#commentText").val(e.data);
                    break;
            }
            return _super.prototype.onEvent.call(this, e);
        };
        CpzlqkFrameView.prototype.updateUI = function () {
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            var dt = this.mDtSec.getDate();
            if (dt.month == undefined) {
                dt.month = 1;
            }
            if (dt.day == undefined) {
                dt.day = 1;
            }
            this.mCurrentPlugin = this.plugin(node);
            router.broadcast(FrameEvent.FE_HIDE);
            this.mCurrentComp = this.mCompanySelector.getCompany();
            this.mCurrentDate = dt;
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SHOW);
            var title = node.getData().value;
            if (this.mYdjdType == cpzlqk.YDJDType.YD) {
                title = "月度" + title;
            }
            else if (this.mYdjdType == cpzlqk.YDJDType.JD) {
                title = "季度" + title;
            }
            if (this.isCompanySupported) {
                title = this.mCompanySelector.getCompanyName() + " " + title;
            }
            $("#headertitle")[0].innerHTML = title;
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                date: dt,
                compType: this.mCurrentComp
            });
        };
        return CpzlqkFrameView;
    })(framework.basic.ShowFrameView);
    var ZlPluginView = (function (_super) {
        __extends(ZlPluginView, _super);
        function ZlPluginView() {
            _super.apply(this, arguments);
        }
        ZlPluginView.prototype.onEvent = function (e) {
            switch (e.id) {
                case cpzlqk.Event.ZLFE_IS_YDJD_SUPPORTED:
                    return true;
                case cpzlqk.Event.ZLFE_JD_SELECTED:
                    this.mYdjdType = cpzlqk.YDJDType.JD;
                    break;
                case cpzlqk.Event.ZLFE_YD_SELECTED:
                    this.mYdjdType = cpzlqk.YDJDType.YD;
                    break;
                case cpzlqk.Event.ZLFE_IS_COMPANY_SUPPORTED:
                    this.mCompSize = e.data;
                    return false;
            }
            return _super.prototype.onEvent.call(this, e);
        };
        return ZlPluginView;
    })(framework.basic.ShowPluginView);
    cpzlqk.ZlPluginView = ZlPluginView;
    var ins = new CpzlqkFrameView();
})(cpzlqk || (cpzlqk = {}));
