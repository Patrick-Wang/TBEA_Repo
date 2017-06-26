(function(){
    var _old_jq_resize = $.fn.resize;
    var _old_jq_removeClass = $.fn.removeClass;
    var _un_resize_list__ = [];


    window.triggerResize = function(){
        $(document).trigger("resize");
    }

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

        },

        //removeClass : function(cls){
        //    _old_jq_removeClass.call(this, cls);
        //    if (cls == "gone"){
        //        var oldWidth = $("iframe").css("width");
        //        $("iframe").css("width", "99%");
        //        $("iframe").css("width", oldWidth);
        //        /*for (var i = 0; i < _un_resize_list__.length; ++i){
        //            if (this[0].name == _un_resize_list__[i].name){
        //                _un_resize_list__[i].onResize();
        //                _un_resize_list__.splice(i, 1);
        //                break;
        //            }
        //        }*/
        //    }
        //    return $(this);
        //},

        resize : function(onResize){
            _old_jq_resize.call(this, function(){
                if (!this.lastResizeTime){
                    this.lastResizeTime = new Date().getTime();
                    this.inCheckTime = false;
                }
                var _this = this;
                var cur = new Date().getTime();

                if ((cur - this.lastResizeTime) < 10){
                    if (!this.inCheckTime){
                        this.inCheckTime = true;
                        setTimeout(function(){
                            _this.inCheckTime = false;
                            $(_this).trigger("resize");
                        }, 11);
                    }
                    this.lastResizeTime = cur;
                    return $(this);
                }
                this.lastResizeTime = cur;



                if (window.parent){
                    if(!$(window.parent.document.getElementsByName(window.name)).hasClass("gone")){
                        onResize();
                    }else{
                        /*for (var i = 0; i < _un_resize_list__.length; ++i){
                            if (window.name == _un_resize_list__[i].name){
                                return $(this);
                            }
                        }
                        _un_resize_list__.push({name : window.name, onResize : onResize});*/
                    }
                }else{
                    onResize();
                }
            });
            return $(this);
        }
    });
})();
