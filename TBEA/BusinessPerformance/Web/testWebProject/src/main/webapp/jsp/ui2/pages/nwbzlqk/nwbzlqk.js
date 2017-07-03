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
    var PageType = nwbzlqk.PageType;
    var NwbzlqkFrameView = (function (_super) {
        __extends(NwbzlqkFrameView, _super);
        function NwbzlqkFrameView() {
            _super.apply(this, arguments);
            this.isCompanySupported = false;
            this.mAjaxApprove = new Util.Ajax("/BusinessManagement/nwbzlqk/doApprove.do", false);
            this.mAjaxAuth = new Util.Ajax("/BusinessManagement/nwbzlqk/auth.do", false);
        }
        NwbzlqkFrameView.prototype.init = function (opt) {
            var _this = this;
            this.mOpt = opt;
            //let dsp : any = new Util.DateSelectorProxy(this.mOpt.dt,
            //    {year: this.mOpt.date.year - 3, month: 1},
            //    {
            //        year: this.mOpt.date.year,
            //        month: 12
            //    },
            //    {
            //        year: this.mOpt.date.year,
            //        month: this.mOpt.date.month
            //    }, false, false);
            //this.mDtSec = dsp;
            this.createDate({
                nowDate: Util.date2Str(this.mOpt.date),
                maxDate: Util.date2Str({
                    year: this.mOpt.date.year,
                    month: 12
                })
            });
            this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.comp, this.mOpt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }
            this.mCompanySelector.change(function (selector, depth) {
                _this.updateTypeSelector();
            });
            var inputs = $("#" + this.mOpt.contentType).show();
            $("#" + this.mOpt.contentType).change(function () {
                var node = _this.triggerYdjdChecked();
                _this.adjustHeader();
            });
            //inputs.click((e)=>{
            //    let node:Util.DataNode = this.triggerYdjdChecked();
            //});
            this.updateTypeSelector();
            this.updateUI();
            $(window).resize(function () {
                _this.adjustHeader();
                router.to(_this.mCurrentPlugin).send(FrameEvent.FE_ADJUST_SZIE);
            });
            this.adjustHeader();
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
            //let inputs = $("#" + (<FrameOption>this.mOpt).contentType).val();
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            //for (let i = 0; i < inputs.length; i++) {
            //    if (true == inputs[i].checked) {
            if ($("#" + this.mOpt.contentType).val() == '0') {
                this.mYdjdType = nwbzlqk.YDJDType.YD;
                router.to(this.plugin(node)).send(nwbzlqk.Event.ZLFE_YD_SELECTED);
                var dtNow = this.getDate();
                //$("#" + this.mOpt.dt).empty();
                //let dsp : any = new Util.DateSelectorProxy(this.mOpt.dt,
                //    {year: this.mOpt.date.year - 3, month: 1},
                //    {
                //        year: this.mOpt.date.year,
                //        month: 12
                //    },
                //    dtNow, false, false);
                //this.mDtSec = dsp;
                this.createDate({
                    nowDate: Util.date2Str(dtNow),
                    maxDate: Util.date2Str({
                        year: this.mOpt.date.year,
                        month: 12
                    })
                });
            }
            else {
                this.mYdjdType = nwbzlqk.YDJDType.JD;
                var dtNow = this.getDate();
                //$("#" + this.mOpt.dt).empty();
                //let dsp : any = new Util.DateSelectorProxy(this.mOpt.dt,
                //    {year: this.mOpt.date.year - 3, month: 1},
                //    {
                //        year: this.mOpt.date.year,
                //        month: 12
                //    },
                //    dtNow, true, false);
                //this.mDtSec = dsp;
                this.createDate({
                    nowDate: Util.date2Str(dtNow),
                    maxDate: Util.date2Str({
                        year: this.mOpt.date.year,
                        month: 12
                    })
                });
                router.to(this.plugin(node)).send(nwbzlqk.Event.ZLFE_JD_SELECTED);
            }
            //}
            //}
            return node;
        };
        NwbzlqkFrameView.prototype.updateTypeSelector = function (width) {
            var _this = this;
            if (width === void 0) { width = 285; }
            if (_super.prototype.updateTypeSelector.call(this, width)) {
                this.mItemSelector.change(function () {
                    _this.checkCompanySupported();
                    _this.checkYdjdSupported();
                    _this.adjustHeader();
                });
                this.checkCompanySupported();
                this.checkYdjdSupported();
                return true;
            }
            return false;
        };
        NwbzlqkFrameView.prototype.onEvent = function (e) {
            switch (e.id) {
                case nwbzlqk.Event.ZLFE_APPROVE_COMMENT1:
                case nwbzlqk.Event.ZLFE_APPROVE_COMMENT2:
                case nwbzlqk.Event.ZLFE_APPROVE_COMMENT3:
                case nwbzlqk.Event.ZLFE_APPROVE_COMMENT:
                    this.onApproved(e.id);
                    break;
                case nwbzlqk.Event.ZLFE_SAVE_COMMENT:
                    router.to(this.mCurrentPlugin).send(e.id, $("#commentText").val());
                    break;
                case nwbzlqk.Event.ZLFE_COMMENT_DENY:
                    $(".comment-area").hide();
                    break;
                case nwbzlqk.Event.ZLFE_APPROVEAUTH_UPDATED:
                    this.onUpdateAuth();
                    break;
                case nwbzlqk.Event.ZLFE_COMMENT_UPDATED:
                    this.onUpdateComment(e.data.comment, e.data.zt);
                    break;
            }
            return _super.prototype.onEvent.call(this, e);
        };
        NwbzlqkFrameView.prototype.updateUI = function () {
            var _this = this;
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            var dt = this.getDate();
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
            //let title = node.getData().value;
            //if (this.mYdjdType == YDJDType.YD){
            //    title = "月度" + title;
            //}else if (this.mYdjdType == YDJDType.JD){
            //    title = "季度" + title;
            //}
            //if (this.isCompanySupported){
            //    title = this.mCompanySelector.getCompanyName() + " " + title;
            //}
            //$("#headertitle")[0].innerHTML = title;
            if (pageType == PageType.APPROVE) {
                this.mAjaxAuth.get({ companyId: this.mCurrentComp })
                    .then(function (jsonData) {
                    _this.mAuths = jsonData;
                    router.to(_this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                        date: dt,
                        compType: _this.mCurrentComp
                    });
                });
            }
            else {
                router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                    date: dt,
                    compType: this.mCurrentComp
                });
            }
        };
        NwbzlqkFrameView.prototype.onApproved = function (id) {
            var _this = this;
            var zt;
            switch (id) {
                case nwbzlqk.Event.ZLFE_APPROVE_COMMENT1:
                    zt = Util.IndiStatus.INTER_APPROVED_1;
                    break;
                case nwbzlqk.Event.ZLFE_APPROVE_COMMENT2:
                    zt = Util.IndiStatus.INTER_APPROVED_2;
                    break;
                case nwbzlqk.Event.ZLFE_APPROVE_COMMENT3:
                    zt = Util.IndiStatus.INTER_APPROVED_3;
                    break;
                case nwbzlqk.Event.ZLFE_APPROVE_COMMENT:
                    zt = Util.IndiStatus.APPROVED;
                    break;
            }
            if (undefined != zt) {
                this.mAjaxApprove.get({
                    date: this.mCurrentDate.year + "-" + this.mCurrentDate.month + "-1",
                    companyId: this.mCurrentComp,
                    zt: zt
                }).then(function (jsonData) {
                    framework.router
                        .fromEp(_this)
                        .to(framework.basic.endpoint.FRAME_ID)
                        .send(nwbzlqk.Event.ZLFE_APPROVEAUTH_UPDATED);
                    framework.router
                        .fromEp(_this)
                        .to(framework.basic.endpoint.FRAME_ID)
                        .send(nwbzlqk.Event.ZLFE_COMMENT_UPDATED, { comment: {
                            comment: $("#commentText").val()
                        }, zt: zt });
                    if (zt == Util.IndiStatus.INTER_APPROVED_1) {
                        Util.Toast.success("审核成功", undefined);
                    }
                    else {
                        Util.Toast.success("上报成功", undefined);
                    }
                });
            }
        };
        NwbzlqkFrameView.prototype.onUpdateComment = function (comment, zt) {
            if (comment.deny == "deny") {
                $(".comment-area").hide();
                return;
            }
            this.mCurZt = zt;
            $(".comment-area").show();
            $("#commentText").val(comment.comment);
            $("#commentText").attr("readonly", "readonly");
            if (pageType == PageType.APPROVE) {
                if (zt == Util.IndiStatus.SUBMITTED) {
                    if ($("#approveComment1").is(":visible")) {
                    }
                    else {
                        $("#commentText").val("");
                    }
                    $("#approveComment").hide();
                    $("#approveComment2").hide();
                    $("#approveComment3").hide();
                }
                else if (zt == Util.IndiStatus.INTER_APPROVED_1) {
                    if ($("#approveComment2").is(":visible") ||
                        $("#approveComment1").is(":visible")) {
                    }
                    else {
                        $("#commentText").val("");
                    }
                    $("#approveComment").hide();
                    $("#approveComment1").hide();
                    $("#approveComment3").hide();
                }
                else if (zt == Util.IndiStatus.INTER_APPROVED_2) {
                    if ($("#approveComment2").is(":visible") ||
                        $("#approveComment1").is(":visible") ||
                        $("#approveComment3").is(":visible")) {
                    }
                    else {
                        $("#commentText").val("");
                    }
                    $("#approveComment1").hide();
                    $("#approveComment2").hide();
                    $("#approveComment").hide();
                }
                else if (zt == Util.IndiStatus.INTER_APPROVED_3) {
                    if ($("#approveComment2").is(":visible") ||
                        $("#approveComment1").is(":visible") ||
                        $("#approveComment3").is(":visible") ||
                        $("#approveComment").is(":visible")) {
                    }
                    else {
                        $("#commentText").val("");
                    }
                    $("#approveComment1").hide();
                    $("#approveComment2").hide();
                    $("#approveComment3").hide();
                }
                else if (zt == Util.IndiStatus.APPROVED) {
                    $("#approveComment").hide();
                    $("#approveComment1").hide();
                    $("#approveComment2").hide();
                    $("#approveComment3").hide();
                }
                else {
                    $("#approveComment").hide();
                    $("#approveComment1").hide();
                    $("#approveComment2").hide();
                    $("#approveComment3").hide();
                    $("#commentText").val("");
                }
            }
            else if (pageType == PageType.ENTRY) {
                $("#approveComment").hide();
                $("#approveComment1").hide();
                $("#approveComment2").hide();
                $("#approveComment3").hide();
                $("#commentText").removeAttr("readonly");
            }
            else if (pageType == PageType.SHOW) {
                $("#approveComment").hide();
                $("#approveComment1").hide();
                $("#approveComment2").hide();
                $("#approveComment3").hide();
                if (zt != 1) {
                    $("#commentText").val("");
                }
            }
        };
        NwbzlqkFrameView.prototype.onUpdateAuth = function () {
            $("#approveComment").hide();
            $("#approveComment1").hide();
            $("#approveComment2").hide();
            $("#approveComment3").hide();
            if (Util.indexOf(this.mAuths, 22) >= 0) {
                $("#approveComment").show();
            }
            if (Util.indexOf(this.mAuths, 53) >= 0) {
                $("#approveComment1").show();
            }
            if (Util.indexOf(this.mAuths, 54) >= 0) {
                $("#approveComment2").show();
            }
            if (Util.indexOf(this.mAuths, 55) >= 0) {
                $("#approveComment3").show();
            }
        };
        return NwbzlqkFrameView;
    })(framework.basic.ShowFrameView);
    var ZlPluginView = (function (_super) {
        __extends(ZlPluginView, _super);
        function ZlPluginView() {
            _super.apply(this, arguments);
            this.mCommentSubmit = new Util.Ajax("/BusinessManagement/report/zlfxSubmit.do", false);
            this.mCommentGet = new Util.Ajax("/BusinessManagement/report/zlfxUpdate.do", false);
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
                case nwbzlqk.Event.ZLFE_SAVE_COMMENT:
                    this.onSaveComment(e.data);
                    break;
            }
            return _super.prototype.onEvent.call(this, e);
        };
        return ZlPluginView;
    })(framework.basic.ShowPluginView);
    nwbzlqk.ZlPluginView = ZlPluginView;
    var ins = new NwbzlqkFrameView();
})(nwbzlqk || (nwbzlqk = {}));
