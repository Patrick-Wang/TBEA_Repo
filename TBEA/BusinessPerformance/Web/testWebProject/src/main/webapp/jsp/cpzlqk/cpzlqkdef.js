///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
var cpzlqk;
(function (cpzlqk) {
    (function (YDJDType) {
        YDJDType[YDJDType["YD"] = 0] = "YD";
        YDJDType[YDJDType["JD"] = 1] = "JD";
    })(cpzlqk.YDJDType || (cpzlqk.YDJDType = {}));
    var YDJDType = cpzlqk.YDJDType;
    (function (ByqBhgType) {
        ByqBhgType[ByqBhgType["YBYSQFJYS"] = 0] = "YBYSQFJYS";
        ByqBhgType[ByqBhgType["PBCP"] = 1] = "PBCP";
    })(cpzlqk.ByqBhgType || (cpzlqk.ByqBhgType = {}));
    var ByqBhgType = cpzlqk.ByqBhgType;
    var FrameEvent = framework.basic.FrameEvent;
    var Event;
    (function (Event) {
        Event.ZLFE_IS_COMPANY_SUPPORTED = FrameEvent.lastEvent();
        Event.ZLFE_IS_YDJD_SUPPORTED = FrameEvent.lastEvent();
        Event.ZLFE_YD_SELECTED = FrameEvent.lastEvent();
        Event.ZLFE_JD_SELECTED = FrameEvent.lastEvent();
        Event.ZLFE_IS_BHGLX_SUPPORTED = FrameEvent.lastEvent();
        Event.ZLFE_GET_BHGLX = FrameEvent.lastEvent();
        Event.ZLFE_SET_ZBSTATUS = FrameEvent.lastEvent();
    })(Event = cpzlqk.Event || (cpzlqk.Event = {}));
})(cpzlqk || (cpzlqk = {}));
