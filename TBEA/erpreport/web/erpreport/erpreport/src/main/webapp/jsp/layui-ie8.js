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