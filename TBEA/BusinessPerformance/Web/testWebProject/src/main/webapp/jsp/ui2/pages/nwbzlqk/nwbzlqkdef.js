///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
var nwbzlqk;
(function (nwbzlqk) {
    var PageType;
    (function (PageType) {
        PageType[PageType["NONE"] = 0] = "NONE";
        PageType[PageType["APPROVE"] = 1] = "APPROVE";
        PageType[PageType["ENTRY"] = 2] = "ENTRY";
        PageType[PageType["SHOW"] = 3] = "SHOW";
    })(PageType = nwbzlqk.PageType || (nwbzlqk.PageType = {}));
    var YDJDType;
    (function (YDJDType) {
        YDJDType[YDJDType["YD"] = 0] = "YD";
        YDJDType[YDJDType["JD"] = 1] = "JD";
    })(YDJDType = nwbzlqk.YDJDType || (nwbzlqk.YDJDType = {}));
    var FrameEvent = framework.basic.FrameEvent;
    var Event;
    (function (Event) {
        Event.ZLFE_IS_COMPANY_SUPPORTED = FrameEvent.lastEvent();
        Event.ZLFE_IS_YDJD_SUPPORTED = FrameEvent.lastEvent();
        Event.ZLFE_YD_SELECTED = FrameEvent.lastEvent();
        Event.ZLFE_JD_SELECTED = FrameEvent.lastEvent();
        Event.ZLFE_IS_BHGLX_SUPPORTED = FrameEvent.lastEvent();
        Event.ZLFE_SET_ZBSTATUS = FrameEvent.lastEvent();
        Event.ZLFE_SAVE_COMMENT = FrameEvent.lastEvent();
        Event.ZLFE_APPROVE_COMMENT = FrameEvent.lastEvent();
        Event.ZLFE_APPROVE_COMMENT1 = FrameEvent.lastEvent();
        Event.ZLFE_APPROVE_COMMENT2 = FrameEvent.lastEvent();
        Event.ZLFE_APPROVE_COMMENT3 = FrameEvent.lastEvent();
        Event.ZLFE_COMMENT_UPDATED = FrameEvent.lastEvent();
        Event.ZLFE_DATA_STATUS = FrameEvent.lastEvent();
        Event.ZLFE_COMMENT_DENY = FrameEvent.lastEvent();
        Event.ZLFE_APPROVEAUTH_UPDATED = FrameEvent.lastEvent();
    })(Event = nwbzlqk.Event || (nwbzlqk.Event = {}));
    var NwbzlType;
    (function (NwbzlType) {
        NwbzlType[NwbzlType["ZT"] = 0] = "ZT";
        NwbzlType[NwbzlType["SJZL"] = 1] = "SJZL";
        NwbzlType[NwbzlType["SCZZ"] = 2] = "SCZZ";
    })(NwbzlType = nwbzlqk.NwbzlType || (nwbzlqk.NwbzlType = {}));
})(nwbzlqk || (nwbzlqk = {}));
