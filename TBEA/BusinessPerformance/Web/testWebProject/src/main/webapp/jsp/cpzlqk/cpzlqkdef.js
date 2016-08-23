///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
var cpzlqk;
(function (cpzlqk) {
    (function (YDJDType) {
        YDJDType[YDJDType["YD"] = 0] = "YD";
        YDJDType[YDJDType["JD"] = 1] = "JD";
    })(cpzlqk.YDJDType || (cpzlqk.YDJDType = {}));
    var YDJDType = cpzlqk.YDJDType;
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
        Event.ZLFE_COMMENT_UPDATED = FrameEvent.lastEvent();
        Event.ZLFE_DATA_STATUS = FrameEvent.lastEvent();
        Event.ZLFE_COMMENT_DENY = FrameEvent.lastEvent();
    })(Event = cpzlqk.Event || (cpzlqk.Event = {}));
    (function (NwbzlType) {
        NwbzlType[NwbzlType["ZT"] = 0] = "ZT";
        NwbzlType[NwbzlType["SJZL"] = 1] = "SJZL";
        NwbzlType[NwbzlType["SCZZ"] = 2] = "SCZZ";
    })(cpzlqk.NwbzlType || (cpzlqk.NwbzlType = {}));
    var NwbzlType = cpzlqk.NwbzlType;
})(cpzlqk || (cpzlqk = {}));
