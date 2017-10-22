if (!window.console) {
    window.console = {
        log: function () {

        }
    }
}
//asdfasdfasdf阿斯顿发送到发送到发生地方
window.iever = 100;
if (navigator.appName == "Microsoft Internet Explorer") {
    if (navigator.appVersion.match(/7./i) == "7.") {
        window.iever = 7;
    }
    else if (navigator.appVersion.match(/8./i) == "8.") {
        window.iever = 8;
    }
    else if (navigator.appVersion.match(/9./i) == "9.") {
        window.iever = 9;
    }
    window.isie = true;
}
