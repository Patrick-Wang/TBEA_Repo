var Util;
(function (Util) {
    var Loading = (function () {
        function Loading() {
        }
        Loading.init = function () {
            if ($("#mloading").length == 0) {
                $("body").children().eq(0).before("<div id='mloading' style='z-index:1999;position:absolute;width:100%;height:100%'></div>");
                $("#mloading").mLoading({});
            }
        };
        Loading.start = function () {
            Loading.startTime = Date.now();
            $("#mloading").show();
            $("#mloading").mLoading("show");
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
        return Loading;
    })();
    Util.Loading = Loading;
})(Util || (Util = {}));
