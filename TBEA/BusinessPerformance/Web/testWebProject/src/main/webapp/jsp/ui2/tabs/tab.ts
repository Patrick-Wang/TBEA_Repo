declare var $;
module tab {

    export interface MoreInfo {
        name:string;
        id:string;
    }

    export interface TabInfo {
        name:string;
        id:string;
    }

    export class Tab {
        more:MoreInfo[] = [];
        tabs:TabInfo[] = [];
        onClickMore:(data:MoreInfo) => void;
        onClickTab:(data:TabInfo) => void;
        onCloseTab:(data:TabInfo) => void;
        onClickHome:()=>void;
        tabId:string;
        closelist:string[] = [];
        closeStarted:boolean = false;

        getTabs():TabInfo[] {
            return this.tabs;
        }


        q(query?:string):any {
            if (query == undefined) {
                return $("#" + this.tabId);
            } else {
                return $("#" + this.tabId + " " + query);
            }
        }

        constructor(id:string) {
            this.tabId = id;
            this.q().append(
                '<ul class="tab">' +
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
            this.q(".tab-home div").click(()=> {
                this.triggerClickHome();
            });

            $('.tab-more').click(()=> {
                setTimeout(()=> {
                    this.adjustDropDownHeight();
                }, 0);
            });
            this.q(".tab-more ul").mCustomScrollbar({
                theme: "minimal-dark",
                scrollInertia: 0
            });
            this.q(".tab-more .mCSB_draggerContainer").click((event)=> {
                event.stopPropagation();
            });
        }

        findMore(id:string):MoreInfo {
            for (let i = 0; i < this.more.length; ++i) {
                if (this.more[i].id == id) {
                    return this.more[i];
                }
            }
        }

        findTab(id:string):TabInfo {
            for (let i = 0; i < this.tabs.length; ++i) {
                if (this.tabs[i].id == id) {
                    return this.tabs[i];
                }
            }
            return undefined;
        }

        addMore(data:tab.MoreInfo):Tab {
            this.more.push(data);
            this.q(".tab-more-menu .divider")
                .before(
                    '<li id="' + data.id + '"class="tab-more-menu-fixed" >' +
                    '<a href="#">' + data.name + '</a>' +
                    '</li>'
                );
            this.q(".tab-more-menu #" + data.id).click(()=> {
                this.triggerClickMore(data.id);
            });
            return this;
        }

        disableMore(id:string):Tab {
            this.q(".tab-more-menu #" + id).addClass("disable");
            this.q(".tab-more-menu #" + id + ">a").click((event)=> {
                event.stopPropagation();
            });
            return this;
        }

        enableMore(id:string):Tab {
            this.q(".tab-more-menu #" + id).removeClass("disable");
            this.q(".tab-more-menu #" + id).removeAttr("readonly");
            this.q(".tab-more-menu #" + id + ">a").unbind("click");
            return this;
        }

        removeMore(id:string):Tab {
            for (let i = 0; i < this.more.length; ++i) {
                if (this.more[i].id == id) {
                    this.more.splice(i, 1);
                    break;
                }
            }
            this.q(".tab-more #" + id).remove();
            return this;
        }

        getActiveTab():TabInfo {
            return this.findTab(this.getActiveTabId());
        }

        setMoreClickListener(onClick:(data:tab.MoreInfo)=>void):Tab {
            this.onClickMore = onClick;
            return this;
        }

        setCloseTabClickListener(onCloseTab:(data:tab.TabInfo)=>void):Tab {
            this.onCloseTab = onCloseTab;
            return this;
        }

        setTabClickListener(onClick:(data:tab.TabInfo)=>void):Tab {
            this.onClickTab = onClick;
            return this;
        }

        setHomeClickListener(onClick:()=>void):Tab {
            this.onClickHome = onClick;
            return this;
        }

        private internalOnBegin(id:string):void {
            //if (this.closelist.length > 0){
            //    let id:any = this.closelist.splice(0, 1);
            //    this.interalCloseTab(id);
            //}else{
            //    this.closeStarted = false;
            //}
            this.q("#" + id).prop("disabled", true);
            this.q(".tab-close").prop("disabled", true);
        }

        private internalOnEnd(id:string, callback?:any):void {
            //if (this.closelist.length > 0){
            //    let id:any = this.closelist.splice(0, 1);
            //    this.interalCloseTab(id);
            //}else{
            //    this.closeStarted = false;
            //}
            this.q("#" + id).prop("disabled", false);
            this.q(".tab-close").prop("disabled", false);
            if (callback){
                callback();
            }
        }

        private interalCloseTab(id:string, callback?:any) {

            this.internalOnBegin(id);

            let closed1:boolean = false;
            let closed2:boolean = false;
            let tabInfo;
            let normalTabSize = this.getNormalTabSize();

            let next = this.nextTab(id);
            if (this.isActiveTab(id)) {
                let prev = this.pervTab(id);
                tabInfo = this.removeTab(id, true);
                if (next != undefined && next != "" && !this.isMoreTab(next)) {
                    this.activeTab(next);
                } else {
                    this.activeTab(prev);
                }
            } else {
                tabInfo = this.removeTab(id, true);
            }

            if (normalTabSize < this.getNormalTabSize()) {
                let newNormalTabCount = this.getNormalTabSize() - normalTabSize;
                let firstNewTab = this.q(".tab-normal:eq(" + normalTabSize + ")");

                this.q(".tab-more").before('<div id="animHelper" style="float:left;overflow:hidden"></div>');
                let animHelper = this.q('#animHelper');
                let newTab = firstNewTab.next();
                for (let i = 0; i < newNormalTabCount; ++i) {
                    let width = firstNewTab.css("width");
                    let height = firstNewTab.css("height");
                    let textHeight = firstNewTab.find("div>div:first").css("height");
                    firstNewTab.css("height", height);
                    firstNewTab.css("width", width);
                    firstNewTab.find("div>div:first").css("height", textHeight);
                    firstNewTab.remove();
                    animHelper.append(firstNewTab);
                    firstNewTab = newTab;
                    newTab = newTab.next();
                }
                let width = parseInt(animHelper.css("width").replace("px", ""));
                let height = newTab.css("height");
                animHelper.css("width", "0px");
                animHelper.css("height", height);
                animHelper.animate({
                        width: width + "px"
                    },
                    'fast',
                    'swing',
                    ()=> {
                        animHelper.find("li").removeCss("width");
                        animHelper.find("div").removeCss("width");
                        animHelper.find("li").removeCss("height");
                        animHelper.find("div").removeCss("height");
                        for (let count = animHelper.children().length; count > 0; --count) {
                            let tab = animHelper.children().eq(0);
                            tab.remove();
                            this.q(".tab-more").before(tab);
                            tab.find(" div div:first").click(tab, (event)=> {
                                this.triggerClickTab(event.data.attr("id"));
                            });
                            tab.find(" div .tab-close").click(tab, (event)=> {
                                this.triggerClickClose(event.data.attr("id"));
                            });
                        }
                        animHelper.remove();
                        closed1 = true;
                        if (closed1 && closed2) {
                            this.internalOnEnd(id, callback);
                        }
                    });

            } else {
                closed1 = true;
            }
            let height = this.q("#" + id).css("height");
            this.q("#" + id)
                .css("height", height)
                .css("display", "");
            this.q("#" + id + " .tab-close").css("visibility", "hidden");
            let textHeight = this.q("#" + id + ">div>div:first").css("height");
            this.q("#" + id + ">div>div:first").css("height", textHeight);

            this.q("#" + id)
                .animate({
                        width: '0px'
                    }, 'fast',
                    'swing',
                    ()=> {
                        this.q("#" + id).remove();
                        closed2 = true;
                        if (closed1 && closed2) {
                            this.internalOnEnd(id, callback);
                        }
                    });

            if (undefined == this.getActiveTab()) {
                if (this.onClickHome != undefined) {
                    this.onClickHome();
                }
            } else {
                if (this.onClickTab != undefined) {
                    this.onClickTab(this.getActiveTab());
                }
            }

            if (this.onCloseTab != undefined) {
                this.onCloseTab(tabInfo);
            }
        }

        triggerClickClose(id:string, callback?:any) {
            //if (!this.closeStarted){
            //    this.closeStarted = true;
            //    this.interalCloseTab(id);
            //}else{
            //    this.closelist.push(id);
            //}
            this.interalCloseTab(id, callback);
        }

        triggerClickMore(id:string) {
            if (this.onClickMore != undefined) {
                this.onClickMore(this.findMore(id));
            }
        }

        private bindTab(data:tab.TabInfo):void {
            if (!this.hasMoreTab()) {
                this.q(".tab-more").before(
                    '<li id="' + data.id + '"class="tab-normal">' +
                    '<div>' +
                    '<div>' + data.name + '</div>' +
                    '<div class="tab-close"></div>' +
                    '</div>' +
                    '</li>');
                if (this.hasTooManyTabs()) {
                    this.q("#" + data.id).remove();
                    this.showMoreDivider();
                    this.q(".tab-more-menu .divider").after(
                        '<li id="' + data.id + '" class="tab-more-menu-extend"><a href="#">' +
                        data.name +
                        '</a></li>');

                    this.q("#" + data.id).click(()=> {
                        this.triggerClickTab(data.id);
                    });
                } else {
                    this.q("#" + data.id).click((e)=> {
                        this.triggerClickTab(data.id);
                    });
                    this.q("#" + data.id + " div .tab-close").click((e)=> {
                        this.triggerClickClose(data.id);
                        e.stopPropagation();
                    });
                }
            } else {
                this.q(".tab-more-menu li:last").after(
                    '<li id="' + data.id + '" class="tab-more-menu-extend"><a href="#">' +
                    data.name +
                    '</a></li>');

                this.q("#" + data.id).click(()=> {
                    this.triggerClickTab(data.id);
                });
            }
        }

        addTab(data:tab.TabInfo):void {
            this.tabs.push(data);
            this.bindTab(data);
        }

        fixedHeight(){
            this.q(".tab").css("height", $(".tab").outerHeight() + "px");
        }

        unFixedHeight(){
            $(".tab").removeCss("height");
        }

        hasTooManyTabs():boolean {
            if (this.tabs.length > 0) {
                let top = this.q(".tab-home").offset().top;
                if (this.q(".tab-more").prev().offset().top != top ||
                    this.q(".tab-more").offset().top != top) {
                    return true;
                }
            }
            return false;
        }

        insertTabBefore(data:TabInfo, index?:number):void {
            if (index != undefined && this.tabs.length > index) {
                let tailTabs = [data];
                while (index < this.tabs.length) {
                    tailTabs.push(this.removeTab(this.tabs[index].id));
                }

                for (let i = 0; i < tailTabs.length; ++i) {
                    this.addTab(tailTabs[i]);
                }
            } else {
                this.addTab(data);
            }
        }

        isMoreTab(id:string):boolean {
            let tab = this.q(".tab-more-menu #" + id);
            return tab.length > 0;
        }

        hasMoreTab():boolean {
            return this.q(".tab-more-menu-extend").length > 0;
        }

        showMoreDivider():void {
            this.q(".tab-more-menu .divider").css("display", "");
        }

        hideMoreDivider():void {
            this.q(".tab-more-menu .divider").css("display", "none");
        }

        removeTab(id:string, anim:boolean = false) {
            let ret:TabInfo;
            for (let i = 0; i < this.tabs.length; ++i) {
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
            } else {
                let tab = this.q("#" + id);
                tab.css("display", "none");
                let extendMenus = this.q(".tab-more-menu .tab-more-menu-extend");
                extendMenus.remove();
                this.hideMoreDivider();
                for (let i = 0; i < extendMenus.length; ++i) {
                    this.bindTab(this.findTab(extendMenus[i].id));
                }
                if (!anim) {
                    tab.remove();
                }
            }

            return ret;
        }

        getNormalTabSize() {
            return this.q(".tab-normal").length;
        }

        triggerClickTab(id:string) {
            if (this.isMoreTab(id)) {
                let insertIndex = this.getNormalTabSize() - 1;
                do {
                    let tabInfo = this.removeTab(id);
                    this.insertTabBefore(tabInfo, insertIndex);
                    --insertIndex;
                } while (this.isMoreTab(id) && insertIndex >= 0)
            }

            this.activeTab(id);

            if (this.onClickTab != undefined) {
                this.onClickTab(this.findTab(id));
            }
        }

        triggerClickHome():Tab {
            this.activeTab();
            if (this.onClickHome != undefined) {
                this.onClickHome();
            }
            return this;
        }

        // undefined : 该元素没有下一个元素
        // ""：该元素不存在
        private nextTab(id:string):string {
            let next = undefined;
            for (let i = 0; i < this.tabs.length; ++i) {
                if (this.tabs[i].id == id) {
                    next = i + 1;
                    break;
                }
            }

            if (next == undefined) {
                return "";
            } else if (next == this.tabs.length) {
                return undefined;
            } else {
                return this.tabs[next].id;
            }
        }

        //undefined: 该元素前一个元素为Home
        //""：该元素不存在
        private pervTab(id:string):string {
            let prev = undefined;
            for (let i = 0; i < this.tabs.length; ++i) {
                if (this.tabs[i].id == id) {
                    prev = i - 1;
                    break;
                }
            }

            if (prev == undefined) {
                return "";
            } else if (prev == -1) {
                return undefined;
            } else {
                return this.tabs[prev].id;
            }
        }

        rearrange():void {
            let tmpTabs = [];

            let activeTabId:string = this.getActiveTabId();

            while (this.tabs.length > 0) {
                tmpTabs.push(this.removeTab(this.tabs[0].id));
            }

            for (let i = 0; i < tmpTabs.length; ++i) {
                this.addTab(tmpTabs[i]);
            }

            let activeChanged:boolean = false;
            while (!this.activeTab(activeTabId)) {
                activeChanged = true;
                activeTabId = this.pervTab(activeTabId);
            }
            if (activeChanged) {
                if (activeTabId == undefined) {
                    if (this.onClickHome != undefined) {
                        this.onClickHome();
                    }
                } else {
                    if (this.onClickTab != undefined) {
                        this.onClickTab(this.findTab(activeTabId));
                    }
                }
            }
            this.adjustDropDownHeight();
        }

        private adjustDropDownHeight() {
            if ($('.tab-more').attr("class").match('open')) {
                $('.tab-more ul').removeCss('height');
                let ulheight = $('.tab-more ul').offset().top + $('.tab-more ul').height();
                if (ulheight > $("body").height()) {
                    $('.tab-more ul').css('height', ($('.tab-more ul').height() - (ulheight - $("body").height()) - 10) + "px");
                }
            }
        }

        getActiveTabId():string {
            return this.q(".active").attr("id");
        }

        private activeTab(id?:string):boolean {
            if (id == undefined) {
                this.q(".active").removeClass("active");
                this.q(".tab-home").addClass("active");
            } else if (!this.isMoreTab(id)) {
                this.q(".active").removeClass("active");
                this.q("#" + id).addClass("active");
            } else {
                return false;
            }
            return true;
        }

        private isActiveTab(id:String):any {
            let tab = this.q("#" + id);
            return tab.hasClass("active");
        }

    }


}