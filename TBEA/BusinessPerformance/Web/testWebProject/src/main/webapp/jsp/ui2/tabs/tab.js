var tab;
(function (tab_1) {
    var Tab = /** @class */ (function () {
        function Tab(id) {
            var _this = this;
            this.more = [];
            this.tabs = [];
            this.closelist = [];
            this.closeStarted = false;
            this.tabId = id;
            this.q().append('<ul class="tab">' +
                '<li class="tab-home active" >' +
                '<div class="fa fa-home"></div>' +
                '</li>' +
                '<li class="tab-more dropdown pull-right">' +
                '<a href="#more" data-toggle="dropdown" class="dropdown-toggle"><strong class="fa fa-ellipsis-v"></strong></a>' +
                '<ul class="tab-more-menu dropdown-menu">' +
                '<li class="divider" style="display:none"></li>' +
                '</ul>' +
                '</li>' +
                '</ul>');
            this.q(".tab-home div").click(function () {
                _this.triggerClickHome();
            });
            $('.tab-more').click(function () {
                setTimeout(function () {
                    _this.adjustDropDownHeight();
                }, 0);
            });
            this.q(".tab-more ul").mCustomScrollbar({
                theme: "minimal-dark",
                scrollInertia: 0
            });
            this.q(".tab-more .mCSB_draggerContainer").click(function (event) {
                event.stopPropagation();
            });
        }
        Tab.prototype.getTabs = function () {
            return this.tabs;
        };
        Tab.prototype.q = function (query) {
            if (query == undefined) {
                return $("#" + this.tabId);
            }
            else {
                return $("#" + this.tabId + " " + query);
            }
        };
        Tab.prototype.findMore = function (id) {
            for (var i = 0; i < this.more.length; ++i) {
                if (this.more[i].id == id) {
                    return this.more[i];
                }
            }
        };
        Tab.prototype.findTab = function (id) {
            for (var i = 0; i < this.tabs.length; ++i) {
                if (this.tabs[i].id == id) {
                    return this.tabs[i];
                }
            }
            return undefined;
        };
        Tab.prototype.addMore = function (data) {
            var _this = this;
            this.more.push(data);
            this.q(".tab-more-menu .divider")
                .before('<li id="' + data.id + '"class="tab-more-menu-fixed" >' +
                '<a href="#">' + data.name + '</a>' +
                '</li>');
            this.q(".tab-more-menu #" + data.id).click(function () {
                _this.triggerClickMore(data.id);
            });
            return this;
        };
        Tab.prototype.disableMore = function (id) {
            this.q(".tab-more-menu #" + id).addClass("disable");
            this.q(".tab-more-menu #" + id + ">a").click(function (event) {
                event.stopPropagation();
            });
            return this;
        };
        Tab.prototype.enableMore = function (id) {
            this.q(".tab-more-menu #" + id).removeClass("disable");
            this.q(".tab-more-menu #" + id).removeAttr("readonly");
            this.q(".tab-more-menu #" + id + ">a").unbind("click");
            return this;
        };
        Tab.prototype.removeMore = function (id) {
            for (var i = 0; i < this.more.length; ++i) {
                if (this.more[i].id == id) {
                    this.more.splice(i, 1);
                    break;
                }
            }
            this.q(".tab-more #" + id).remove();
            return this;
        };
        Tab.prototype.getActiveTab = function () {
            return this.findTab(this.getActiveTabId());
        };
        Tab.prototype.setMoreClickListener = function (onClick) {
            this.onClickMore = onClick;
            return this;
        };
        Tab.prototype.setCloseTabClickListener = function (onCloseTab) {
            this.onCloseTab = onCloseTab;
            return this;
        };
        Tab.prototype.setTabClickListener = function (onClick) {
            this.onClickTab = onClick;
            return this;
        };
        Tab.prototype.setHomeClickListener = function (onClick) {
            this.onClickHome = onClick;
            return this;
        };
        Tab.prototype.internalOnBegin = function (id) {
            //if (this.closelist.length > 0){
            //    let id:any = this.closelist.splice(0, 1);
            //    this.interalCloseTab(id);
            //}else{
            //    this.closeStarted = false;
            //}
            this.q("#" + id).prop("disabled", true);
            this.q(".tab-close").prop("disabled", true);
        };
        Tab.prototype.internalOnEnd = function (id, callback) {
            //if (this.closelist.length > 0){
            //    let id:any = this.closelist.splice(0, 1);
            //    this.interalCloseTab(id);
            //}else{
            //    this.closeStarted = false;
            //}
            this.q("#" + id).prop("disabled", false);
            this.q(".tab-close").prop("disabled", false);
            if (callback) {
                callback();
            }
        };
        Tab.prototype.interalCloseTab = function (id, callback) {
            var _this = this;
            this.internalOnBegin(id);
            var closed1 = false;
            var closed2 = false;
            var tabInfo;
            var normalTabSize = this.getNormalTabSize();
            var next = this.nextTab(id);
            if (this.isActiveTab(id)) {
                var prev = this.pervTab(id);
                tabInfo = this.removeTab(id, true);
                if (next != undefined && next != "" && !this.isMoreTab(next)) {
                    this.activeTab(next);
                }
                else {
                    this.activeTab(prev);
                }
            }
            else {
                tabInfo = this.removeTab(id, true);
            }
            if (normalTabSize < this.getNormalTabSize()) {
                var newNormalTabCount = this.getNormalTabSize() - normalTabSize;
                var firstNewTab = this.q(".tab-normal:eq(" + normalTabSize + ")");
                this.q(".tab-more").before('<div id="animHelper" style="float:left;overflow:hidden"></div>');
                var animHelper_1 = this.q('#animHelper');
                var newTab = firstNewTab.next();
                for (var i = 0; i < newNormalTabCount; ++i) {
                    var width_1 = firstNewTab.css("width");
                    var height_1 = firstNewTab.css("height");
                    var textHeight_1 = firstNewTab.find("div>div:first").css("height");
                    firstNewTab.css("height", height_1);
                    firstNewTab.css("width", width_1);
                    firstNewTab.find("div>div:first").css("height", textHeight_1);
                    firstNewTab.remove();
                    animHelper_1.append(firstNewTab);
                    firstNewTab = newTab;
                    newTab = newTab.next();
                }
                var width = parseInt(animHelper_1.css("width").replace("px", ""));
                var height_2 = newTab.css("height");
                animHelper_1.css("width", "0px");
                animHelper_1.css("height", height_2);
                animHelper_1.animate({
                    width: width + "px"
                }, 'fast', 'swing', function () {
                    animHelper_1.find("li").removeCss("width");
                    animHelper_1.find("div").removeCss("width");
                    animHelper_1.find("li").removeCss("height");
                    animHelper_1.find("div").removeCss("height");
                    for (var count = animHelper_1.children().length; count > 0; --count) {
                        var tab_2 = animHelper_1.children().eq(0);
                        tab_2.remove();
                        _this.q(".tab-more").before(tab_2);
                        tab_2.find(" div div:first").click(tab_2, function (event) {
                            _this.triggerClickTab(event.data.attr("id"));
                        });
                        tab_2.find(" div .tab-close").click(tab_2, function (event) {
                            _this.triggerClickClose(event.data.attr("id"));
                        });
                    }
                    animHelper_1.remove();
                    closed1 = true;
                    if (closed1 && closed2) {
                        _this.internalOnEnd(id, callback);
                    }
                });
            }
            else {
                closed1 = true;
            }
            var height = this.q("#" + id).css("height");
            this.q("#" + id)
                .css("height", height)
                .css("display", "");
            this.q("#" + id + " .tab-close").css("visibility", "hidden");
            var textHeight = this.q("#" + id + ">div>div:first").css("height");
            this.q("#" + id + ">div>div:first").css("height", textHeight);
            this.q("#" + id)
                .animate({
                width: '0px'
            }, 'fast', 'swing', function () {
                _this.q("#" + id).remove();
                closed2 = true;
                if (closed1 && closed2) {
                    _this.internalOnEnd(id, callback);
                }
            });
            if (undefined == this.getActiveTab()) {
                if (this.onClickHome != undefined) {
                    this.onClickHome();
                }
            }
            else {
                if (this.onClickTab != undefined) {
                    this.onClickTab(this.getActiveTab());
                }
            }
            if (this.onCloseTab != undefined) {
                this.onCloseTab(tabInfo);
            }
        };
        Tab.prototype.triggerClickClose = function (id, callback) {
            //if (!this.closeStarted){
            //    this.closeStarted = true;
            //    this.interalCloseTab(id);
            //}else{
            //    this.closelist.push(id);
            //}
            this.interalCloseTab(id, callback);
        };
        Tab.prototype.triggerClickMore = function (id) {
            if (this.onClickMore != undefined) {
                this.onClickMore(this.findMore(id));
            }
        };
        Tab.prototype.bindTab = function (data) {
            var _this = this;
            if (!this.hasMoreTab()) {
                this.q(".tab-more").before('<li id="' + data.id + '"class="tab-normal">' +
                    '<div>' +
                    '<div>' + data.name + '</div>' +
                    '<div class="tab-close"></div>' +
                    '</div>' +
                    '</li>');
                if (this.hasTooManyTabs()) {
                    this.q("#" + data.id).remove();
                    this.showMoreDivider();
                    this.q(".tab-more-menu .divider").after('<li id="' + data.id + '" class="tab-more-menu-extend"><a href="#">' +
                        data.name +
                        '</a></li>');
                    this.q("#" + data.id).click(function () {
                        _this.triggerClickTab(data.id);
                    });
                }
                else {
                    this.q("#" + data.id).click(function (e) {
                        _this.triggerClickTab(data.id);
                    });
                    this.q("#" + data.id + " div .tab-close").click(function (e) {
                        _this.triggerClickClose(data.id);
                        e.stopPropagation();
                    });
                }
            }
            else {
                this.q(".tab-more-menu li:last").after('<li id="' + data.id + '" class="tab-more-menu-extend"><a href="#">' +
                    data.name +
                    '</a></li>');
                this.q("#" + data.id).click(function () {
                    _this.triggerClickTab(data.id);
                });
            }
        };
        Tab.prototype.addTab = function (data) {
            this.tabs.push(data);
            this.bindTab(data);
        };
        Tab.prototype.fixedHeight = function () {
            this.q(".tab").css("height", $(".tab").outerHeight() + "px");
        };
        Tab.prototype.unFixedHeight = function () {
            $(".tab").removeCss("height");
        };
        Tab.prototype.hasTooManyTabs = function () {
            if (this.tabs.length > 0) {
                var top_1 = this.q(".tab-home").offset().top;
                if (this.q(".tab-more").prev().offset().top != top_1 ||
                    this.q(".tab-more").offset().top != top_1) {
                    return true;
                }
            }
            return false;
        };
        Tab.prototype.insertTabBefore = function (data, index) {
            if (index != undefined && this.tabs.length > index) {
                var tailTabs = [data];
                while (index < this.tabs.length) {
                    tailTabs.push(this.removeTab(this.tabs[index].id));
                }
                for (var i = 0; i < tailTabs.length; ++i) {
                    this.addTab(tailTabs[i]);
                }
            }
            else {
                this.addTab(data);
            }
        };
        Tab.prototype.isMoreTab = function (id) {
            var tab = this.q(".tab-more-menu #" + id);
            return tab.length > 0;
        };
        Tab.prototype.hasMoreTab = function () {
            return this.q(".tab-more-menu-extend").length > 0;
        };
        Tab.prototype.showMoreDivider = function () {
            this.q(".tab-more-menu .divider").css("display", "");
        };
        Tab.prototype.hideMoreDivider = function () {
            this.q(".tab-more-menu .divider").css("display", "none");
        };
        Tab.prototype.removeTab = function (id, anim) {
            if (anim === void 0) { anim = false; }
            var ret;
            for (var i = 0; i < this.tabs.length; ++i) {
                if (this.tabs[i].id == id) {
                    ret = this.tabs.splice(i, 1)[0];
                    break;
                }
            }
            if (this.isMoreTab(id)) {
                this.q(".tab-more-menu #" + id).remove();
                if (this.hasMoreTab()) {
                    this.hideMoreDivider();
                }
            }
            else {
                var tab_3 = this.q("#" + id);
                tab_3.css("display", "none");
                var extendMenus = this.q(".tab-more-menu .tab-more-menu-extend");
                extendMenus.remove();
                this.hideMoreDivider();
                for (var i = 0; i < extendMenus.length; ++i) {
                    this.bindTab(this.findTab(extendMenus[i].id));
                }
                if (!anim) {
                    tab_3.remove();
                }
            }
            return ret;
        };
        Tab.prototype.getNormalTabSize = function () {
            return this.q(".tab-normal").length;
        };
        Tab.prototype.triggerClickTab = function (id) {
            if (this.isMoreTab(id)) {
                var insertIndex = this.getNormalTabSize() - 1;
                do {
                    var tabInfo = this.removeTab(id);
                    this.insertTabBefore(tabInfo, insertIndex);
                    --insertIndex;
                } while (this.isMoreTab(id) && insertIndex >= 0);
            }
            this.activeTab(id);
            if (this.onClickTab != undefined) {
                this.onClickTab(this.findTab(id));
            }
        };
        Tab.prototype.triggerClickHome = function () {
            this.activeTab();
            if (this.onClickHome != undefined) {
                this.onClickHome();
            }
            return this;
        };
        // undefined : 该元素没有下一个元素
        // ""：该元素不存在
        Tab.prototype.nextTab = function (id) {
            var next = undefined;
            for (var i = 0; i < this.tabs.length; ++i) {
                if (this.tabs[i].id == id) {
                    next = i + 1;
                    break;
                }
            }
            if (next == undefined) {
                return "";
            }
            else if (next == this.tabs.length) {
                return undefined;
            }
            else {
                return this.tabs[next].id;
            }
        };
        //undefined: 该元素前一个元素为Home
        //""：该元素不存在
        Tab.prototype.pervTab = function (id) {
            var prev = undefined;
            for (var i = 0; i < this.tabs.length; ++i) {
                if (this.tabs[i].id == id) {
                    prev = i - 1;
                    break;
                }
            }
            if (prev == undefined) {
                return "";
            }
            else if (prev == -1) {
                return undefined;
            }
            else {
                return this.tabs[prev].id;
            }
        };
        Tab.prototype.rearrange = function () {
            var tmpTabs = [];
            var activeTabId = this.getActiveTabId();
            while (this.tabs.length > 0) {
                tmpTabs.push(this.removeTab(this.tabs[0].id));
            }
            for (var i = 0; i < tmpTabs.length; ++i) {
                this.addTab(tmpTabs[i]);
            }
            var activeChanged = false;
            while (!this.activeTab(activeTabId)) {
                activeChanged = true;
                activeTabId = this.pervTab(activeTabId);
            }
            if (activeChanged) {
                if (activeTabId == undefined) {
                    if (this.onClickHome != undefined) {
                        this.onClickHome();
                    }
                }
                else {
                    if (this.onClickTab != undefined) {
                        this.onClickTab(this.findTab(activeTabId));
                    }
                }
            }
            this.adjustDropDownHeight();
        };
        Tab.prototype.adjustDropDownHeight = function () {
            if ($('.tab-more').attr("class").match('open')) {
                $('.tab-more ul').removeCss('height');
                var ulheight = $('.tab-more ul').offset().top + $('.tab-more ul').height();
                if (ulheight > $("body").height()) {
                    $('.tab-more ul').css('height', ($('.tab-more ul').height() - (ulheight - $("body").height()) - 10) + "px");
                }
            }
        };
        Tab.prototype.getActiveTabId = function () {
            return this.q(".active").attr("id");
        };
        Tab.prototype.activeTab = function (id) {
            if (id == undefined) {
                this.q(".active").removeClass("active");
                this.q(".tab-home").addClass("active");
            }
            else if (!this.isMoreTab(id)) {
                this.q(".active").removeClass("active");
                this.q("#" + id).addClass("active");
            }
            else {
                return false;
            }
            return true;
        };
        Tab.prototype.isActiveTab = function (id) {
            var tab = this.q("#" + id);
            return tab.hasClass("active");
        };
        return Tab;
    }());
    tab_1.Tab = Tab;
})(tab || (tab = {}));
