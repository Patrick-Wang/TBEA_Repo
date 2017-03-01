
$.fn.extend({
    newClass:function(name, classObj) {
        var style = '<style id="' + name + '"> .' + name + ' {';
        var first = true;
        for(var i in classObj){
            if (!first){
                style += ",";
            }else{
                first = false;
            }
            style += i + ':' + classObj[i];
        }
        style +="}</style>";
        $("head").append(style);
        return $(this);
    },

    deleteClass:function(name) {
        $("#" + name).remove();
        return $(this);
    },

    removeCss:function(name, value){
        $(this).each(function(i, e){
            var style = $(e).attr("style");
            if (style != undefined){
                var reger = new RegExp(name + "\\s*:\\s*.*;*","gm");
                style = style.replace(reger,"");
                if (style.replace(/\s*/g, "").length == 0){
                    $(e).removeAttr("style");
                }else{
                    $(e).attr("style", style);
                }
            }
        });

        return $(this);
    },

    active : function(onActive, inActive){
        var activeTracker = [];
        var _this = $(this);
        _this.each(function(i, e){
            if (document.activeElement == e){
                activeTracker.push(true);
            }else{
                activeTracker.push(false);
            }
        });

        setInterval(function(){
            _this.each(function(i, e){

                if (activeTracker[i]){
                    if (document.activeElement != e){
                        activeTracker[i] = false;
                        inActive == undefined ? undefined : inActive();
                    }
                }else{
                    if (document.activeElement == e){
                        activeTracker[i] = true;
                        onActive == undefined ? undefined : onActive();
                    }
                }
            });
        }, 200);
        return _this;

    }
});