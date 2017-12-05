module index {

    interface TreeItem {
        id: number;
        parent: number;
        title: string;
        icon: string;
        spread: boolean;
        url: string;
        type: number;
        children: TreeItem[];
    }

    enum NavbarType {
        TOP = 0,
        LEFT = 1
    }

    interface Context {
        userName: string,
        navTree: TreeItem[];
        item: string;
    }

    declare var context: Context;
    declare var $: any;
    declare var layui: any;

    var treeItemMap = {};
    var curRootItem:any;
    function buildTreeItemMap(tree: TreeItem[]) {
        for (let i = 0; tree && i < tree.length; ++i) {
            treeItemMap[tree[i].id] = tree[i];
            if (tree[i].url){
                let inheritList = tree[i].title;
                let item = tree[i];
                while(item.parent){
                    item = treeItemMap[item.parent];
                    inheritList = item.title + "##" + inheritList;
                }
                tree[i].url = encodeURI(tree[i].url + "?breadcrumb=" +
                    JSON.stringify(inheritList.split("##")) +
                    "&item=" + tree[i].id);
            }
            let spread:any = tree[i].spread;
            tree[i].spread = (spread == '1');
            buildTreeItemMap(tree[i].children);
        }
    }

    buildTreeItemMap(context.navTree);

    function buildTopLevel(item: TreeItem) {
        let ret: any = {
            id: item.id,
            title: item.title,
            icon: item.icon
        };
        for (let i = 0; i < item.children.length; ++i) {
            if (item.children[i].type == 0) {
                if (!ret.children) {
                    ret.children = [];
                }
                ret.children.push(item.children[i]);
            }
        }
        return ret;
    }

    function getOnLevelData() {
        let data = [];
        for (let i = 0; i < context.navTree.length; ++i) {
            if (context.navTree[i].type == 0) {
                data.push(buildTopLevel(context.navTree[i]));
            }
        }
        return data;
    }

    function triggerOnClick(ids: any, time :number = 500, pItemElem : any = undefined) {
        let id = ids.splice(0, 1);
        let treeItem: TreeItem = treeItemMap[id];
        if (typeof pItemElem === "function"){
            pItemElem = pItemElem();
        }

        if (!treeItem.parent){
            pItemElem = $(".layui-side .layui-nav-item");
            //save spread status
            for (let i = 0, j = 0; curRootItem && i < curRootItem.children.length; ++i){
                if (!curRootItem.children[i].url){
                    if ($(pItemElem[j]).hasClass('layui-nav-itemed')){
                        curRootItem.children[i].spread = true;
                    }else{
                        curRootItem.children[i].spread = false;
                    }
                    ++j;
                }
            }
            curRootItem = treeItem;
            layui.navbar.set({data: treeItem.children}).render(function (data) {
                layui.tab.tabAdd(data);
            });
            pItemElem = ()=>{return $(".layui-side .layui-nav-tree")};
        } else if (!treeItem.url){
            let pTreeItem: TreeItem = treeItemMap[treeItem.parent];
            let liItem: any;
            for (let i = 0, j = 0; i < pTreeItem.children.length; ++i) {
                if (pTreeItem.children[i].id == treeItem.id) {
                    liItem = pItemElem.children(".layui-nav-item:eq(" + j + ")");
                    if (!liItem.hasClass('layui-nav-itemed')){
                        liItem.find(">a").click();
                        pItemElem = liItem;
                    }
                    break;
                }
                if (!pTreeItem.children[i].url){
                    ++j;
                }
            }
        } else if (treeItem.url){
            if (pItemElem[0].tagName.toLowerCase() == 'ul'){
                let pTreeItem: TreeItem = treeItemMap[treeItem.parent];
                pItemElem = pItemElem.children(".layui-nav-item");
                for (let i = 0; i < pTreeItem.children.length; ++i) {
                    if (pTreeItem.children[i].id == id) {
                        pItemElem.eq(i).find('a').click();
                        break;
                    }
                }
            }else if (pItemElem[0].tagName.toLowerCase() == 'li'){
                let pTreeItem: TreeItem = treeItemMap[treeItem.parent];
                pItemElem = pItemElem.find("dd");
                for (let i = 0; i < pTreeItem.children.length; ++i) {
                    if (pTreeItem.children[i].id == id) {
                        pItemElem.eq(i).find('a').click();
                        break;
                    }
                }
            }else{
                console.log("模拟点击失败")
            }
        }

        if (ids.length > 0){
            setTimeout(()=>{
                triggerOnClick(ids, time, pItemElem);
            }, time);
        }

        //
        //
        //
        //
        // if (ids.length > 1) {
        //     setTimeout(() => {
        //         treeItem = treeItemMap[ids[1]];
        //         if (!treeItem.url){
        //             let parent: TreeItem = treeItemMap[treeItem.parent];
        //             let liItem: any;
        //             for (let i = 0, j = 0; i < parent.children.length; ++i) {
        //                 if (parent.children[i].id == treeItem.id) {
        //                     liItem = $(".layui-side .layui-nav-item:eq(" + j + ")");
        //                     if (!liItem.hasClass('layui-nav-itemed')){
        //                         liItem.find(">a").click();
        //                     }
        //                     break;
        //                 }
        //                 if (!parent.children[i].url){
        //                     ++j;
        //                 }
        //             }
        //             if (ids.length > 2) {
        //                 setTimeout(() => {
        //
        //                     for (let i = 0; i < treeItem.children.length; ++i) {
        //                         if (treeItem.children[i].id == ids[2]) {
        //                             liItem.find("dd:eq(" + i + ") a").click();
        //                             break;
        //                         }
        //                     }
        //
        //                 }, time);
        //             }
        //         }else{
        //             for (let i = 0; i < treeItem.children.length; ++i) {
        //                 if (treeItem.children[i].id == ids[2]) {
        //                     let ddItem = $(".layui-nav-tree").children().eq(i).find("a").click();
        //                     break;
        //                 }
        //             }
        //         }
        //
        //
        //     }, time);
        // }

    }

    function findParents(itemId) {
        let parents = [itemId];
        let treeItem: TreeItem = treeItemMap[itemId];
        while (treeItem.parent) {
            parents = [treeItem.parent].concat(parents);
            treeItem = treeItemMap[treeItem.parent];
            if (treeItem.type == NavbarType.TOP) {
                break;
            }
        }
        return parents;
    }

    let lastTopId;

    export function init() {
        let onelevelopt = {
            elem: "#navTop",
            data: getOnLevelData(),
            onClicked: (id) => {
                if (lastTopId != id){
                    triggerOnClick([id]);
                    lastTopId = id;
                }
            }
        };
        layui.onelevel.set(onelevelopt).render();
        let items: any = [context.item];
        if (!items[0]) {
            if (onelevelopt.data[0].children) {
                items = [onelevelopt.data[0].children[0].id];
            } else {
                items = [context.item = onelevelopt.data[0].id];
            }
        } else {
            items = findParents(context.item);
        }
        lastTopId = items[0];
        triggerOnClick(items);
    }

    export function onClickNavFromSub(item){
        let items: any = [item];
        if (items[0]) {
            items = findParents(item);
        }
        lastTopId = items[0];
        triggerOnClick(items, 500);
    }
}