///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
var cwyjsf;
(function (cwyjsf) {
    var FrameEvent = framework.basic.FrameEvent;
    var Event;
    (function (Event) {
        Event.CW_ISMONTH_SUPPORTED = FrameEvent.lastEvent();
    })(Event = cwyjsf.Event || (cwyjsf.Event = {}));
})(cwyjsf || (cwyjsf = {}));
