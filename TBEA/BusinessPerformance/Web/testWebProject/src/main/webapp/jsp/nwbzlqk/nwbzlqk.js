///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="nwbzlqkdef.ts"/>
///<reference path="../components/dateSelectorProxy.ts"/>
///<reference path="../framework/basic/basicdef.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var nwbzlqk;
(function (nwbzlqk) {
    var router = framework.router;
    var FrameEvent = framework.basic.FrameEvent;
    var NwbzlqkFrameView = (function (_super) {
        __extends(NwbzlqkFrameView, _super);
        function NwbzlqkFrameView() {
            _super.apply(this, arguments);
            this.isCompanySupported = false;
        }
        NwbzlqkFrameView.prototype.init = function (opt) {
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
        NwbzlqkFrameView.prototype.checkCompanySupported = function () {
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            var isSupported = router.to(this.plugin(node)).send(nwbzlqk.Event.ZLFE_IS_COMPANY_SUPPORTED, this.mOpt.comps.length);
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
        NwbzlqkFrameView.prototype.checkYdjdSupported = function () {
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            var isSupported = router.to(this.plugin(node)).send(nwbzlqk.Event.ZLFE_IS_YDJD_SUPPORTED);
            if (isSupported) {
                $("#" + this.mOpt.contentType).show();
                this.triggerYdjdChecked();
            }
            else {
                $("#" + this.mOpt.contentType).hide();
                this.mYdjdType = undefined;
            }
        };
        NwbzlqkFrameView.prototype.triggerYdjdChecked = function () {
            var inputs = $("#" + this.mOpt.contentType + " input");
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            for (var i = 0; i < inputs.length; i++) {
                if (true == inputs[i].checked) {
                    if (inputs[i].id == 'rdyd') {
                        this.mYdjdType = nwbzlqk.YDJDType.YD;
                        router.to(this.plugin(node)).send(nwbzlqk.Event.ZLFE_YD_SELECTED);
                        var dtNow = this.mDtSec.getDate();
                        $("#" + this.mOpt.dt).empty();
                        var dsp = new Util.DateSelectorProxy(this.mOpt.dt, { year: this.mOpt.date.year - 3, month: 1 }, {
                            year: this.mOpt.date.year,
                            month: 12
                        }, dtNow, false, false);
                        this.mDtSec = dsp;
                    }
                    else {
                        this.mYdjdType = nwbzlqk.YDJDType.JD;
                        var dtNow = this.mDtSec.getDate();
                        $("#" + this.mOpt.dt).empty();
                        var dsp = new Util.DateSelectorProxy(this.mOpt.dt, { year: this.mOpt.date.year - 3, month: 1 }, {
                            year: this.mOpt.date.year,
                            month: 12
                        }, dtNow, false, true);
                        this.mDtSec = dsp;
                        router.to(this.plugin(node)).send(nwbzlqk.Event.ZLFE_JD_SELECTED);
                    }
                }
            }
            return node;
        };
        NwbzlqkFrameView.prototype.updateTypeSelector = function (width) {
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
        NwbzlqkFrameView.prototype.onEvent = function (e) {
            switch (e.id) {
                case nwbzlqk.Event.ZLFE_SAVE_COMMENT:
                    router.to(this.mCurrentPlugin).send(nwbzlqk.Event.ZLFE_SAVE_COMMENT, $("#commentText").val());
                    break;
                case nwbzlqk.Event.ZLFE_COMMENT_UPDATED:
                    var comment = e.data;
                    if (comment.deny == "deny") {
                        $("#comment").hide();
                    }
                    else if (comment.readonly == "true") {
                        $("#saveComment").hide();
                        $("#comment").show();
                        $("#commentText").val(comment.comment);
                        $("#commentText").attr("readonly", "readonly");
                    }
                    else {
                        $("#comment").show();
                        $("#saveComment").show();
                        $("#commentText").val(comment.comment);
                        $("#commentText").removeAttr("readonly");
                    }
                    break;
                case nwbzlqk.Event.ZLFE_COMMENT_DENY:
                    $("#comment").hide();
                    break;
            }
            return _super.prototype.onEvent.call(this, e);
        };
        NwbzlqkFrameView.prototype.updateUI = function () {
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
            if (this.mYdjdType == nwbzlqk.YDJDType.YD) {
                title = "月度" + title;
            }
            else if (this.mYdjdType == nwbzlqk.YDJDType.JD) {
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
        return NwbzlqkFrameView;
    })(framework.basic.ShowFrameView);
    var ZlPluginView = (function (_super) {
        __extends(ZlPluginView, _super);
        function ZlPluginView() {
            _super.apply(this, arguments);
        }
        ZlPluginView.prototype.onEvent = function (e) {
            switch (e.id) {
                case nwbzlqk.Event.ZLFE_IS_YDJD_SUPPORTED:
                    return true;
                case nwbzlqk.Event.ZLFE_JD_SELECTED:
                    this.mYdjdType = nwbzlqk.YDJDType.JD;
                    break;
                case nwbzlqk.Event.ZLFE_YD_SELECTED:
                    this.mYdjdType = nwbzlqk.YDJDType.YD;
                    break;
                case nwbzlqk.Event.ZLFE_IS_COMPANY_SUPPORTED:
                    this.mCompSize = e.data;
                    return false;
            }
            return _super.prototype.onEvent.call(this, e);
        };
        return ZlPluginView;
    })(framework.basic.ShowPluginView);
    nwbzlqk.ZlPluginView = ZlPluginView;
    var ins = new NwbzlqkFrameView();
})(nwbzlqk || (nwbzlqk = {}));
