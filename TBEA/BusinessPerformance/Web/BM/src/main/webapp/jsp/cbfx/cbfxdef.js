///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
var cbfx;
(function (cbfx) {
    (function (CbfxType) {
        CbfxType[CbfxType["dmcbfx"] = 0] = "dmcbfx";
        CbfxType[CbfxType["dmcbqsfx"] = 1] = "dmcbqsfx";
    })(cbfx.CbfxType || (cbfx.CbfxType = {}));
    var CbfxType = cbfx.CbfxType;
})(cbfx || (cbfx = {}));
