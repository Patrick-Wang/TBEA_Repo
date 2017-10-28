var Util;
(function (Util) {
    var Loading = (function () {
        function Loading() {
        }
        Loading.pause = function () {
            Loading.isPaused = true;
        };
        Loading.resume = function () {
            Loading.isPaused = false;
        };
        Loading.init = function () {
            if ($("#mloading").length == 0) {
                $("body").children().eq(0).before("<div id='mloading' style='z-index:1999;position:absolute;width:100%;height:100%'></div>");
                $("#mloading").mLoading({});
                $("#mloading").mLoading("hide");
                $("#mloading").hide();
            }
        };
        Loading.start = function () {
            Loading.startTime = Date.now();
            if (!Loading.isPaused) {
                $("#mloading").show();
                $("#mloading").mLoading("show");
            }
        };
        Loading.stop = function () {
            var endTime = Date.now();
            if (endTime - Loading.startTime < 1200) {
                setTimeout(function () {
                    $("#mloading").mLoading("hide");
                    $("#mloading").hide();
                }, 1500 - (endTime - Loading.startTime));
            }
            else {
                $("#mloading").mLoading("hide");
                $("#mloading").hide();
            }
        };
        Loading.startTime = 0;
        Loading.isPaused = false;
        return Loading;
    }());
    Util.Loading = Loading;
})(Util || (Util = {}));
