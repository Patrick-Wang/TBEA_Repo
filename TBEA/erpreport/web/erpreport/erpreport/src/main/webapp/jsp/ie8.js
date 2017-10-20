if(!window.console){
    window.console = {
        log : function(){

        }
    }
}

if (!document.addEventListener) {
    document.addEventListener = function(event, callback){
        return document.attachEvent("on" + event, callback);
    }
}

if (!window.addEventListener) {
    window.addEventListener = function(event, callback){
        return window.attachEvent("on" + event, callback);
    }
}

if (!document.removeEventListener) {
    document.removeEventListener = function(event, fn){
        return document.attachEvent("on" + event, fn);
    }
}

if (!window.removeEventListener) {
    window.removeEventListener = function(event, fn){
        return window.detachEvent("on" + event, fn);
    }
}
//
// if (!Object.prototype.removeEventListener){
//     Object.prototype.removeEventListener = function(event, fn){
//         if (this.detachEvent){
//             return this.detachEvent("on" + event, fn);
//         } else if (this.removeEventListener){
//             return this.removeEventListener("on" + event, fn);
//         }
//     }
// }

window.iever = 100;

if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/7./i)=="7.")
{
    window.iever = 7;
}
else if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/8./i)=="8.")
{
    window.iever = 8;
}
else if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/9./i)=="9.")
{
    window.iever = 9;
}