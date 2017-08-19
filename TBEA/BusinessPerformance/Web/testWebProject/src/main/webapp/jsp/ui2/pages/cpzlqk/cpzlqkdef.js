///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
var plugin;
(function (plugin) {
    var startId = 10293;
    plugin.byqacptjjg = ++startId;
    plugin.byqadwtjjg = ++startId;
    plugin.byqcpycssbhgwtmx = ++startId;
    plugin.byqybysqfyswtmx = ++startId;
    plugin.byqybyspbcpwtmx = ++startId;
    plugin.byqcpycssbhgxxfb = ++startId;
    plugin.byqybysqfysxxfb = ++startId;
    plugin.byqybyspbcpxxfb = ++startId;
    plugin.xkacptjjg = ++startId;
    plugin.xkadwtjjg = ++startId;
    plugin.xkcpycssbhgwtmx = ++startId;
    plugin.xkybysqfyswtmx = ++startId;
    plugin.xkybyspbcpwtmx = ++startId;
    plugin.xkcpycssbhgxxfb = ++startId;
    plugin.xkybysqfysxxfb = ++startId;
    plugin.xkybyspbcpxxfb = ++startId;
    plugin.pdacptjjg = ++startId;
    plugin.pdadwtjjg = ++startId;
    plugin.pdcpycssbhgwtmx = ++startId;
    plugin.pdybysqfyswtmx = ++startId;
    plugin.pdybyspbcpwtmx = ++startId;
    plugin.pdcpycssbhgxxfb = ++startId;
    plugin.pdybysqfysxxfb = ++startId;
    plugin.pdybyspbcpxxfb = ++startId;
    plugin.xlacptjjg = ++startId;
    plugin.xladydjtjjg = ++startId;
    plugin.xlbhgcpmx = ++startId;
})(plugin || (plugin = {}));
var cpzlqk;
(function (cpzlqk) {
    (function (PageType) {
        PageType[PageType["NONE"] = 0] = "NONE";
        PageType[PageType["APPROVE"] = 1] = "APPROVE";
        PageType[PageType["ENTRY"] = 2] = "ENTRY";
        PageType[PageType["SHOW"] = 3] = "SHOW";
    })(cpzlqk.PageType || (cpzlqk.PageType = {}));
    var PageType = cpzlqk.PageType;
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
        Event.ZLFE_APPROVE_COMMENT = FrameEvent.lastEvent();
        Event.ZLFE_APPROVE_COMMENT1 = FrameEvent.lastEvent();
        Event.ZLFE_APPROVE_COMMENT2 = FrameEvent.lastEvent();
        Event.ZLFE_APPROVE_COMMENT3 = FrameEvent.lastEvent();
        Event.ZLFE_COMMENT_UPDATED = FrameEvent.lastEvent();
        Event.ZLFE_DATA_STATUS = FrameEvent.lastEvent();
        Event.ZLFE_COMMENT_DENY = FrameEvent.lastEvent();
        Event.ZLFE_APPROVEAUTH_UPDATED = FrameEvent.lastEvent();
    })(Event = cpzlqk.Event || (cpzlqk.Event = {}));
    (function (NwbzlType) {
        NwbzlType[NwbzlType["ZT"] = 0] = "ZT";
        NwbzlType[NwbzlType["SJZL"] = 1] = "SJZL";
        NwbzlType[NwbzlType["SCZZ"] = 2] = "SCZZ";
    })(cpzlqk.NwbzlType || (cpzlqk.NwbzlType = {}));
    var NwbzlType = cpzlqk.NwbzlType;
})(cpzlqk || (cpzlqk = {}));
