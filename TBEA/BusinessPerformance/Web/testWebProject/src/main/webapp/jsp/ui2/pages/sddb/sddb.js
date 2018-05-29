///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="../components/dateSelectorProxy.ts"/>
///<reference path="../unitedSelector.ts"/>
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var sddb;
(function (sddb) {
    var router = framework.router;
    var FrameEvent = framework.basic.FrameEvent;
    var SDDBEvent;
    (function (SDDBEvent) {
        SDDBEvent.FE_SHOWTIME = FrameEvent.lastEvent();
        SDDBEvent.FE_HIDETIME = FrameEvent.lastEvent();
    })(SDDBEvent = sddb.SDDBEvent || (sddb.SDDBEvent = {}));
    var SddbShowView = /** @class */ (function (_super) {
        __extends(SddbShowView, _super);
        function SddbShowView() {
            return _super !== null && _super.apply(this, arguments) || this;
        }
        SddbShowView.prototype.onEvent = function (e) {
            switch (e.id) {
                case SDDBEvent.FE_SHOWTIME:
                    if (this.mOpt.date != undefined) {
                        $("#dstart").parent().css("display", "");
                        $("#dEnd").parent().css("display", "");
                        //$("#dstartLabel").css("display", "");
                        //$("#dEndLabel").css("display", "");
                    }
                    break;
                case SDDBEvent.FE_HIDETIME:
                    if (this.mOpt.date != undefined) {
                        $("#dstart").css("display", "none");
                        $("#dEnd").css("display", "none");
                        //$("#dstartLabel").css("display", "none");
                        //$("#dEndLabel").css("display", "none");
                    }
                    break;
            }
            return _super.prototype.onEvent.call(this, e);
        };
        SddbShowView.prototype.renderItemSelector = function (itemId) {
            var sels = $("#" + itemId + " select");
            for (var i = 0; i < sels.length; ++i) {
                if (sels.length == i + 1 && sels.length > 1) {
                    this.renderSelector2(sels[i]);
                }
                else {
                    var opts = $("#" + itemId + " select:eq(" + i + ") option");
                    var items = [];
                    for (var j = 0; j < opts.length; ++j) {
                        items.push(opts[j].text);
                    }
                    var hasScroll = $(sels).find("option").length > 10;
                    var extendWidth = hasScroll ? 27 : 0;
                    var width = Util.getUIWidth(items);
                    //$(sels[i]) .multiselect({
                    //        multiple: false,
                    //        header: false,
                    //        minWidth: width < 80 ? 80 + extendWidth : width + 20 + extendWidth,
                    //        height: hasScroll ? 270 : '100%',
                    //        // noneSelectedText: "请选择月份",
                    //        selectedList: 1
                    //    })
                    //    .css("padding", "2px 0 2px 4px")
                    //    .css("text-align", "left")
                    //    .css("font-size", "12px");
                }
            }
        };
        SddbShowView.prototype.renderSelector2 = function (sels) {
            var opts = $(sels).find("option");
            var items = [];
            for (var j = 0; j < opts.length; ++j) {
                items.push(opts[j].text);
            }
            $(sels)
                //.css("width", Math.max(Util.getUIWidth(items), 80))
                //.css("height", "20px")
                .select2({
                language: "zh-CN"
            });
        };
        SddbShowView.prototype.getStartDate = function () {
            var ret = {};
            var curDate = $("#dstart").getDate();
            ret = {
                year: curDate.getFullYear(),
                month: curDate.getMonth() + 1,
                day: curDate.getDate()
            };
            return ret;
        };
        SddbShowView.prototype.getEndDate = function () {
            var ret = {};
            var curDate = $("#dEnd").getDate();
            ret = {
                year: curDate.getFullYear(),
                month: curDate.getMonth() + 1,
                day: curDate.getDate()
            };
            return ret;
        };
        SddbShowView.prototype.init = function (opt) {
            var _this = this;
            this.mOpt = opt;
            if (opt.itemNodes != '' && opt.itemNodes.length != 0) {
                this.unitedSelector = new Util.UnitedSelector(opt.itemNodes, opt.itemId);
                this.unitedSelector.change(function () {
                    _this.renderItemSelector(opt.itemId);
                });
                this.renderItemSelector(opt.itemId);
            }
            else {
                $("#item-label").css("display", "none");
            }
            if (opt.itemNodes0 != '') {
                this.unitedSelector0 = new Util.UnitedSelector(opt.itemNodes0, opt.itemId0);
                this.unitedSelector0.change(function () {
                    _this.renderItemSelector(opt.itemId0);
                });
                this.renderItemSelector(opt.itemId0);
            }
            if (opt.date != undefined) {
                //this.mDStartSel = new Util.DateSelectorProxy("dstart",
                //    opt.dateStart == undefined ? Util.addYear(opt.date, -3) : opt.dateStart,
                //    opt.dateEnd == undefined ? Util.addYear(opt.date, 20) : opt.dateEnd,
                //    Util.addDay(opt.date, -5 * 7)
                //);
                var dateWithOutDay = { year: opt.date.year, month: opt.date.month };
                var startOpt_1 = this.createInternalDate("dstart", dateWithOutDay, {
                    nowDate: Util.date2Str(Util.addDay(opt.date, -5 * 7)),
                    minDate: Util.date2Str(opt.dateStart == undefined ? Util.addYear(opt.date, -3) : opt.dateStart),
                    maxDate: Util.date2Str(opt.date),
                    choosefun: function (elem, val, date) {
                        setTimeout(function () {
                            endOpt_1.minDate = Util.date2Str(_this.getStartDate());
                            endDates();
                        }, 0);
                    }
                });
                var endOpt_1 = this.createInternalDate("dEnd", dateWithOutDay, {
                    nowDate: Util.date2Str(opt.date),
                    minDate: startOpt_1.nowDate,
                    maxDate: Util.date2Str(opt.dateEnd == undefined ? Util.addYear(opt.date, 20) : opt.dateEnd),
                    choosefun: function (elem, val, date) {
                        startOpt_1.maxDate = Util.date2Str(_this.getEndDate()); //将结束日的初始值设定为开始日的最大日期
                    }
                });
                //这里是日期联动的关键
                function endDates() {
                    //将结束日期的事件改成 false 即可
                    endOpt_1.insTrigger = false;
                    $("#dEnd").jePopup();
                }
                //this.mDEndSel = new Util.DateSelectorProxy("dEnd",
                //    opt.dateStart == undefined ? Util.addYear(opt.date, -3) : opt.dateStart,
                //    opt.dateEnd == undefined ? Util.addYear(opt.date, 20) : opt.dateEnd,
                //    opt.date
                //);
                //this.mDEndSel.change((date:any)=>{
                //    let d = this.mDStartSel.getDate();
                //    let dS = Util.uDate2sDate(d).getTime();
                //    let dE = Util.uDate2sDate(date).getTime();
                //    if (dS > dE){
                //        this.mDStartSel.setDate(date);
                //    }
                //});
            }
            else {
                $("#dstart").parent().css("display", "none");
                $("#dEnd").parent().css("display", "none");
                //$("#dstartLabel").css("display", "none");
                //$("#dEndLabel").css("display", "none");
            }
            //$("#ui-datepicker-div").css('font-size', '0.8em'); //改变大小;
            if (this.mOpt.comps != '') {
                this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.comp, this.mOpt.comps);
                if (opt.comps.length == 1) {
                    this.mCompanySelector.hide();
                }
                this.mCompanySelector.change(function (selector, depth) {
                    _this.updateTypeSelector();
                });
            }
            else {
                $("#" + this.mOpt.comp).hide();
            }
            if ((opt.itemNodes == '' || opt.itemNodes.length == 0) && opt.itemNodes0 == '' && opt.date == undefined && this.mOpt.comps == '') {
                $("#doUpdate").css("display", "none");
            }
            this.mCurrentDate = { year: 2010, month: 1, day: 1 };
            this.updateTypeSelector();
            this.updateUI();
            $(window).resize(function () {
                _this.adjustHeader();
                router.to(_this.mCurrentPlugin).send(FrameEvent.FE_ADJUST_SZIE);
            });
            this.adjustHeader();
        };
        SddbShowView.prototype.updateUI = function () {
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            this.mCurrentPlugin = this.plugin(node);
            router.broadcast(FrameEvent.FE_HIDE);
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SHOW);
            if (this.mCompanySelector == undefined) {
                //$("#headertitle")[0].innerHTML = node.getData().value;
            }
            else {
                this.mCurrentComp = this.mCompanySelector.getCompany();
                //if (null != this.mCurrentComp){
                //    $("#headertitle")[0].innerHTML = this.mCompanySelector.getCompanyName() + " " + node.getData().value;
                //}
                //else {
                //    $("#headertitle")[0].innerHTML = node.getData().value;
                //}
            }
            //let unit = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GETUNIT);
            //if (undefined != unit){
            //    $("#unit").text(unit);
            //}else{
            //    $("#unit").text("");
            //}
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                dStart: this.mOpt.date == undefined ? undefined : Util.date2Str(this.getStartDate()),
                dEnd: this.mOpt.date == undefined ? undefined : Util.date2Str(this.getEndDate()),
                compType: this.mCurrentComp,
                item0: this.unitedSelector0 != undefined ? this.unitedSelector0.getDataNode(this.unitedSelector0.getPath()).data.value : undefined,
                item: this.unitedSelector != undefined ? this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.value : undefined
            });
        };
        SddbShowView.prototype.exportExcel = function (elemId) {
            var url = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GET_EXPORTURL, {
                dStart: this.mOpt.date == undefined ? undefined : Util.date2Str(this.getStartDate()),
                dEnd: this.mOpt.date == undefined ? undefined : Util.date2Str(this.getEndDate()),
                compType: this.mCurrentComp,
                item: this.unitedSelector != undefined ? this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.value : undefined
            });
            $("#" + elemId)[0].action = url;
            $("#" + elemId)[0].submit();
        };
        return SddbShowView;
    }(framework.basic.ShowFrameView));
    var ins = new SddbShowView();
})(sddb || (sddb = {}));
