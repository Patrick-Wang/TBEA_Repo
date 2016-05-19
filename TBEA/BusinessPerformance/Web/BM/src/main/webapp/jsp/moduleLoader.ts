
module ModuleLoader {

    var LOADER_JS_NAME : string= "moduleLoader"
    
    function mapUrl(url: string, refUrl: string): string {
        url = url.replace("\\", "/");
        var index = refUrl.lastIndexOf("/");
        var baseUrl: string = refUrl.substring(0, index);
        while (url.indexOf("../") == 0) {
            url = url.substring(3);
            index = baseUrl.lastIndexOf("/");
            baseUrl = baseUrl.substring(0, index);
        }
        return baseUrl + "/" + url;
    }

    function getTotalUrl(url): string {
        url = url.replace("\\", "/");
        if (url.indexOf("://") <= 0) {
            var scripts = document.getElementsByTagName("script");
            url = mapUrl(url, getPolymerizerScriptUrl(scripts));
        }
        return url;
    }

    function getPolymerizerScriptUrl(scripts) {
        var index = getPolymerizerScript(scripts);
        return scripts[index].src;
    }

    function getPolymerizerScript(scripts) {
        for (var i in scripts) {
            if (undefined != scripts[i].src) {
                if (scripts[i].src.indexOf(LOADER_JS_NAME) >= 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    function insertAfter(e, targetElement) {
        var parent = targetElement.parentNode;
        if (parent.lastChild == targetElement) {
            parent.appendChild(e);
        }
        else {
            parent.insertBefore(e, targetElement.nextSibling);
        }
    }

    function getScript(scripts, url: string) {
        for (var i in scripts) {
            if (undefined != scripts[i].src) {
                if (scripts[i].src == url) {
                    return i;
                }
            }
        }
        return -1;
    }

    function findListener(url) {
        for (var i in listener) {
            if (listener[i].url == url) {
                return i;
            };
        }
        return -1;
    }

    export class Listener {
        mUrl: string;
        mCompletes: any[] = [];
        constructor(url: string) {
            listen(url, () => {
                for (var i in this.mCompletes) {
                    this.mCompletes[i]();
                }
            });
        }

        then(complete): Listener {
            this.mCompletes.push(complete);
            return this;
        }

    }

    export function listen(url, callback) {
        url = getTotalUrl(url);
        var index = findListener(url);
        if (index < 0) {
            listener.push({ url: url, callbacks: [], completed: false });
            index = listener.length - 1;
        }
        listener[index].callbacks.push(callback);
    }


    function trigger(url) {
        var index = findListener(url);
        if (index >= 0) {
            if (index == listener.length - 1) {
                for (var i in listener[index].callbacks) {
                    listener[index].callbacks[i]();
                }
                listener.splice(index, 1);
                --index;
                if (index >= 0) {
                    if (listener[index].completed) {
                        trigger(listener[index].url);
                    }
                }
            }
            else {
                listener[index].completed = true;
            }
        }
    }

    var listener = [];

    export function require(url: string): Listener {
        url = getTotalUrl(url);
        var scripts = document.getElementsByTagName("script");
        var ips = getPolymerizerScript(scripts);
        var iUrl = getScript(scripts, url);
        if (iUrl < 0 && ips >= 0) {
            var scr = document.createElement("script");
            scr.src = url;
            insertAfter(scr, scripts[ips]);
            scr.onload = function() {
                trigger(url);
            }
        }
        return new Listener(url);
    }
    
    function GetHttpRequest() {
        if (XMLHttpRequest) // Gecko 
            return new XMLHttpRequest();
        else if (ActiveXObject) // IE 
            return new ActiveXObject("Microsoft.XMLHTTP");
    } 
       
   export function load(url) {
        url = getTotalUrl(url);
        var xmlHttp = GetHttpRequest();
        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status == 200 || xmlHttp.status == 304) {
                    var scripts = document.getElementsByTagName("script");
                    var ips = getPolymerizerScript(scripts);
                    var iUrl = getScript(scripts, url);
                     if (iUrl < 0 && ips >= 0) {
                        var scr = document.createElement("script");
                        scr.text = xmlHttp.responseText;
                        insertAfter(scr, scripts[ips]);
                    }
                }
                else {
                    console.log('XML request error: ' + xmlHttp.statusText + ' (' + xmlHttp.status + ')');
                }
            }
        }
        xmlHttp.open('GET', url, false);
        xmlHttp.send(null);
    }
    
    (function() {
        var scripts = document.getElementsByTagName("script");
        var index = getPolymerizerScript(scripts);
        if (index >= 0) {
            require(scripts[index].getAttribute("main"))
        }
    })();
}