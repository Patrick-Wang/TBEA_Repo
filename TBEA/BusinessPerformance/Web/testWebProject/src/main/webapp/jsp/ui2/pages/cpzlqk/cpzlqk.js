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
            this.mAjaxApprove = new Util.Ajax("/BusinessManagement/cpzlqk/doApprove.do", false);
            this.mAjaxYclApprove = new Util.Ajax("/BusinessManagement/report/yclhglqktjZlDoApprove.do", false);
            this.mAjaxAuth = new Util.Ajax("/BusinessManagement/cpzlqk/auth.do", false);
        }
        CpzlqkFrameView.prototype.checkCompanyCount = function (comps) {
            if (comps == undefined) {
                return true;
            }
            if (comps.length == 1) {
                return this.checkCompanyCount(comps[0].subNodes);
            }
            return false;
        };
        CpzlqkFrameView.prototype.init = function (opt) {
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
            this.createDate({ maxDate: Util.date2Str({
                    year: this.mOpt.date.year,
                    month: 12
                }) });
            this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.comp, this.mOpt.comps);
            if (this.checkCompanyCount(opt.comps)) {
                this.mCompanySelector.hide();
            }
            this.mCompanySelector.change(function (selector, depth) {
                var compType = _this.mCompanySelector.getCompany();
                var isPd = (compType == Util.CompanyType.SBZTFGS || compType == Util.CompanyType.HBDQFGS
                    || compType == Util.CompanyType.XBZTGS || compType == Util.CompanyType.TBGS
                    || compType == Util.CompanyType.XBXBGS || compType == Util.CompanyType.PDCY);
                if (isPd) {
                    $("#zlAndyclhgl").hide();
                }
                else {
                    $("#zlAndyclhgl").show();
                }
                _this.updateTypeSelector();
                _this.adjustHeader();
            });
            $("#" + this.mOpt.contentType).on("change", function () {
                var node = _this.triggerYdjdChecked();
            });
            //let inputs = $("#" + (<FrameOption>this.mOpt).contentType).show();
            //inputs.click((e)=>{
            //    let node : Util.DataNode = this.triggerYdjdChecked();
            //});
            var pageSlector = new Util.UnitedSelector([{
                    data: {
                        id: 0,
                        value: "产品一次送试"
                    }
                }, {
                    data: {
                        id: 1,
                        value: "原材料合格率"
                    }
                }], "zlAndyclhgl");
            pageSlector.change(function () {
                if (pageSlector.getPath()[0] == 1) {
                    if (pageType == cpzlqk.PageType.APPROVE) {
                        window.location.href = "/BusinessManagement/report/v2/yclhglqktj.do?approve=true&breads=" + breads;
                    }
                    else {
                        window.location.href = "/BusinessManagement/report/v2/yclhglqktj.do?breads=" + breads;
                    }
                }
            });
            $(window).resize(function () {
                _this.adjustHeader();
                router.to(_this.mCurrentPlugin).send(FrameEvent.FE_ADJUST_SZIE);
            });
            this.updateTypeSelector();
            this.updateUI();
            this.adjustHeader();
        };
        CpzlqkFrameView.prototype.register = function (name, plugin) {
            var contains = true;
            if (tableStatus != undefined) {
                contains = false;
                for (var i = 0; i < tableStatus.length; ++i) {
                    if (tableStatus[i].id == plugin) {
                        contains = true;
                        break;
                    }
                }
            }
            if (contains) {
                _super.prototype.register.call(this, name, plugin);
            }
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
                $("#" + this.mOpt.contentType).val("0");
                this.triggerYdjdChecked();
                this.mYdjdType = undefined;
            }
        };
        CpzlqkFrameView.prototype.triggerYdjdChecked = function () {
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            if ($("#" + this.mOpt.contentType).val() == 0) {
                // if ( this.mYdjdType ==  YDJDType.JD || this.mYdjdType == undefined){
                var dtNow = this.getDate();
                this.mDtJd = dtNow;
                if (this.mDtYd) {
                    dtNow = this.mDtYd;
                }
                this.mYdjdType = cpzlqk.YDJDType.YD;
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
                router.to(this.plugin(node)).send(cpzlqk.Event.ZLFE_YD_SELECTED);
            }
            else {
                //if ( this.mYdjdType ==  YDJDType.YD || this.mYdjdType == undefined){
                var dtNow = this.getDate();
                this.mDtYd = dtNow;
                //if (this.mDtJd != undefined){
                //    dtNow = this.mDtJd;
                //}
                this.mYdjdType = cpzlqk.YDJDType.JD;
                //$("#" + this.mOpt.dt).empty();
                //let dsp : any = new Util.DateSelectorProxy(this.mOpt.dt,
                //    {year: this.mOpt.date.year - 3, month: 1},
                //    {
                //        year: this.mOpt.date.year,
                //        month: 12
                //    },
                //    dtNow, false, true);
                //this.mDtSec = dsp;
                this.createDate({
                    format: "YYYY年  &&MM月",
                    seasonText: ["一季度", "半年度", "三季度", "年度"],
                    nowDate: Util.date2Str(dtNow),
                    maxDate: Util.date2Str({
                        year: this.mOpt.date.year,
                        month: 12
                    })
                });
                router.to(this.plugin(node)).send(cpzlqk.Event.ZLFE_JD_SELECTED);
            }
            this.adjustHeader();
            return node;
        };
        CpzlqkFrameView.prototype.updateTypeSelector = function (width) {
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
        CpzlqkFrameView.prototype.onApproved = function (id) {
            var _this = this;
            var zt;
            switch (id) {
                case cpzlqk.Event.ZLFE_APPROVE_COMMENT1:
                    zt = Util.IndiStatus.INTER_APPROVED_1;
                    break;
                case cpzlqk.Event.ZLFE_APPROVE_COMMENT2:
                    zt = Util.IndiStatus.INTER_APPROVED_2;
                    break;
                case cpzlqk.Event.ZLFE_APPROVE_COMMENT3:
                    zt = Util.IndiStatus.INTER_APPROVED_3;
                    break;
                case cpzlqk.Event.ZLFE_APPROVE_COMMENT:
                    zt = Util.IndiStatus.APPROVED;
                    break;
            }
            if (undefined != zt) {
                this.mAjaxYclApprove.get({
                    date: this.mCurrentDate.year + "-" + this.mCurrentDate.month + "-1",
                    item: this.mCurrentComp,
                    zt: zt
                }).then(function (jsonData) {
                    _this.mAjaxApprove.get({
                        date: _this.mCurrentDate.year + "-" + _this.mCurrentDate.month + "-1",
                        companyId: _this.mCurrentComp,
                        zt: zt
                    }).then(function (jsonData) {
                        framework.router
                            .fromEp(_this)
                            .to(framework.basic.endpoint.FRAME_ID)
                            .send(cpzlqk.Event.ZLFE_APPROVEAUTH_UPDATED);
                        framework.router
                            .fromEp(_this)
                            .to(framework.basic.endpoint.FRAME_ID)
                            .send(cpzlqk.Event.ZLFE_COMMENT_UPDATED, { comment: {
                                comment: $("#commentText").val()
                            }, zt: zt });
                        if (zt == Util.IndiStatus.INTER_APPROVED_1) {
                            Util.Toast.success("审核成功", undefined);
                        }
                        else {
                            Util.Toast.success("上报成功", undefined);
                        }
                    });
                });
            }
        };
        CpzlqkFrameView.prototype.onEvent = function (e) {
            switch (e.id) {
                case cpzlqk.Event.ZLFE_APPROVE_COMMENT1:
                case cpzlqk.Event.ZLFE_APPROVE_COMMENT2:
                case cpzlqk.Event.ZLFE_APPROVE_COMMENT3:
                case cpzlqk.Event.ZLFE_APPROVE_COMMENT:
                    this.onApproved(e.id);
                    break;
                case cpzlqk.Event.ZLFE_SAVE_COMMENT:
                    router.to(this.mCurrentPlugin).send(e.id, $("#commentText").val());
                    break;
                case cpzlqk.Event.ZLFE_COMMENT_DENY:
                    $(".comment-area").hide();
                    break;
                case cpzlqk.Event.ZLFE_APPROVEAUTH_UPDATED:
                    this.onUpdateAuth();
                    break;
                case cpzlqk.Event.ZLFE_COMMENT_UPDATED:
                    this.onUpdateComment(e.data.comment, e.data.zt);
                    break;
                case cpzlqk.Event.ZLFE_ZLREPORT:
                    //this.onUpdateComment(e.data.comment, e.data.zt);
                    break;
            }
            return _super.prototype.onEvent.call(this, e);
        };
        CpzlqkFrameView.prototype.updateUI = function () {
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
            if (pageType == cpzlqk.PageType.APPROVE) {
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
        CpzlqkFrameView.prototype.onUpdateComment = function (comment, zt) {
            if (comment.deny == "deny") {
                $(".comment-area").hide();
                return;
            }
            this.mCurZt = zt;
            $(".comment-area").show();
            $("#commentText").val(comment.comment);
            $("#commentText").attr("readonly", "readonly");
            if (pageType == cpzlqk.PageType.APPROVE) {
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
                    $(".comment-area").hide();
                }
            }
            else if (pageType == cpzlqk.PageType.ENTRY) {
                $("#approveComment").hide();
                $("#approveComment1").hide();
                $("#approveComment2").hide();
                $("#approveComment3").hide();
                $("#commentText").removeAttr("readonly");
                if (zt == 0) {
                    $(".comment-area").hide();
                }
            }
            else if (pageType == cpzlqk.PageType.SHOW) {
                $("#approveComment").hide();
                $("#approveComment1").hide();
                $("#approveComment2").hide();
                $("#approveComment3").hide();
                if (zt != 1) {
                    $("#commentText").val("");
                }
            }
        };
        CpzlqkFrameView.prototype.onUpdateAuth = function () {
            $("#approveComment").hide();
            $("#approveComment1").hide();
            $("#approveComment2").hide();
            $("#approveComment3").hide();
            if (Util.indexOf(this.mAuths, 22) >= 0) {
                $("#approveComment").css("display", "inline-block");
            }
            if (Util.indexOf(this.mAuths, 53) >= 0) {
                $("#approveComment1").css("display", "inline-block");
            }
            if (Util.indexOf(this.mAuths, 54) >= 0) {
                $("#approveComment2").css("display", "inline-block");
            }
            if (Util.indexOf(this.mAuths, 55) >= 0) {
                $("#approveComment3").css("display", "inline-block");
            }
        };
        return CpzlqkFrameView;
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
                case cpzlqk.Event.ZLFE_SAVE_COMMENT:
                    this.onSaveComment(e.data);
                    break;
            }
            return _super.prototype.onEvent.call(this, e);
        };
        return ZlPluginView;
    })(framework.basic.ShowPluginView);
    cpzlqk.ZlPluginView = ZlPluginView;
    var ins = new CpzlqkFrameView();
})(cpzlqk || (cpzlqk = {}));
