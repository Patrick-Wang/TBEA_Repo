var Util;
(function (Util) {
    var Loading = (function () {
        function Loading() {
        }
        Loading.init = function () {
            if ($("#mloading").length == 0) {
                $("body").children().eq(0).before("<div id='mloading' style='z-index:1999;position:absolute;width:100%;height:100%'></div>");
                $("#mloading").mLoading({
                    text: Loading.DEFAULT_TEXT //加载文字，默认值：加载中...
                    // icon:"",//加载图标，默认值：一个小型的base64的gif图片
                    // html:false,//设置加载内容是否是html格式，默认值是false
                    //content:"",//忽略icon和text的值，直接在加载框中显示此值
                    // mask:true//是否显示遮罩效果，默认显示
                });
                $("#mloading").mLoading("hide");
                $("#mloading").hide();
            }
        };
        Loading.start = function () {
            Loading.startTime = Date.now();
            $("#mloading").show();
            $("#mloading").mLoading({
                text: Loading.DEFAULT_TEXT //加载文字，默认值：加载中...
                // icon:"",//加载图标，默认值：一个小型的base64的gif图片
                // html:false,//设置加载内容是否是html格式，默认值是false
                //content:"",//忽略icon和text的值，直接在加载框中显示此值
                // mask:true//是否显示遮罩效果，默认显示
            });
            //$("#mloading").mLoading("show");
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
        Loading.DEFAULT_TEXT = "加载中...";
        return Loading;
    }());
    Util.Loading = Loading;
})(Util || (Util = {}));
