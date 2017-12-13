
Array.prototype.pushAll = function(items){
    for (var i = 0; i < items.length; ++i){
        this.push(items[i]);
    }
    return this;
}

function createNode(value, url, icon, iconOpen){
    if (url){
        var protocol = "http://";
        if (url.length < protocol.length || url.substring(0, protocol.length) != protocol){
            url = baseUrl + url;
        }
        icon = icon ? icon : "fa fa-dot-circle-o";
        iconOpen = iconOpen ? iconOpen : "fa fa-dot-circle-o";
    }else{
        url = undefined;
        icon = icon ? icon : "fa fa-plus-square-o";
        iconOpen = iconOpen ? iconOpen : "fa fa-minus-square-o";
    }
    var node = {
        data : {
            id : navi.uid(),
            value:value,
            url : url,
            icon : icon,
            iconOpen : iconOpen
        },
        subNodes:[]
    };

    node.append  = function(sub){
        if (sub != undefined){
            if (sub instanceof Array){
                node.subNodes.pushAll(sub);
            }else{
                node.subNodes.push(sub);
            }
        }
        return node;
    }
    return node;
}

module navi{

    import ITreeNode = tree.ITreeNode;

    interface NavigatorItem{
        id : number;
        name : string;
        url : string;
        extend : number;
        iconClose : string;
        iconOpen : string;
        parent : number;
    }

    export class Builder{
        builders = {};
        register(group, builder:()=>ITreeNode[]){
            if (this.builders[group] == undefined){
                this.builders[group] = [];
            }
            this.builders[group].push(builder);
        }

        build(group) {
            let ret = [];
            if (this.builders[group] != undefined) {
                for (let i = 0; i < this.builders[group].length; ++i) {
                    let nodes = this.builders[group][i]();
                    if (nodes instanceof Array) {
                        ret.pushAll(nodes);
                    } else {
                        ret.push(nodes);
                    }
                }
            }
            return ret;
        }

        static build(naviItems : NavigatorItem[]){
            let workMap = {};
            let changed = true;
            let ret = [];
            for (let i = 0; i < naviItems.length; ++i) {
                if (!naviItems[i].parent){
                    let node = createNode(naviItems[i].name, naviItems[i].url, naviItems[i].iconOpen, naviItems[i].iconClose);
                    node.data.extracted = (naviItems[i].extend == 1);
                    ret.push(node);
                    workMap[naviItems[i].id] = node;
                }
            }



            while (changed) {
                changed = false;
                for (let i = 0; i < naviItems.length; ++i) {
                    if (naviItems[i].parent && workMap[naviItems[i].parent]){
                        let node = createNode(naviItems[i].name, naviItems[i].url, naviItems[i].iconOpen, naviItems[i].iconClose);
                        node.data.extracted = (naviItems[i].extend == 1);
                        workMap[naviItems[i].parent].append(node);
                        workMap[naviItems[i].id] = node;
                        naviItems[i].parent = undefined;
                        changed = true;
                    }
                }
            }


            var isExtracted = false;

            function clean(node){
                for (var i = 0; i < node.subNodes.length; ++i){
                    if (clean(node.subNodes[i])){
                        node.subNodes.splice(i, 1);
                        --i;
                    }
                }

                if (node.subNodes.length == 0 && !node.data.url){
                    return true;
                }

                if (!isExtracted){
                    if (node.data.extracted){
                        isExtracted = true;
                    }
                }else{
                    node.data.extracted = undefined;
                }

                return false;
            }

            for (var i = 0; i < ret.length; ++i){
                if (clean(ret[i])){
                    ret.splice(i, 1);
                    --i;
                }
            }
            return ret;
        }
    }



    export let uid = ((seed) => {
        return () => {
            ++seed;
            return seed + "";
        }
    })(new Date().getTime());
}

let builder:navi.Builder = new navi.Builder();