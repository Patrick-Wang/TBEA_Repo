///<reference path="treeNode.ts"/>

declare var $;

module tree{

    export class Tree{
        treeId:string;
        treeNodes:TreeNode[];
        onClick:(node:TreeNode)=>boolean;
        extractedNodes:TreeNode[] = [];


        q(query?:string):any{
            if (query == undefined){
                return $("#" + this.treeId);
            }else{
                return $("#" + this.treeId + " " + query);
            }
        }

        constructor(id:string){
            this.treeId = id;
            this.q().addClass("tree");
        }

        setOnClickListener(click:(node:TreeNode)=>boolean) : (node:TreeNode)=>boolean{
            let oldFn = this.onClick;
            this.onClick = click;
            return oldFn;
        }

        refresh(treeNode:TreeNode){
            if (treeNode.data.icon && treeNode.data.iconOpen){
                this.q("#" + treeNode.data.id + ">div i").removeClass(treeNode.data.icon_);
                this.q("#" + treeNode.data.id + ">div i").addClass(
                    this.getIcon(treeNode, (treeNode.getData().extracted ? treeNode.data.iconOpen : treeNode.data.icon)));
            }
        }

        triggerClicked(treeNode:TreeNode, ignoreStop?:boolean){

            let stop = true;
            if (treeNode.data.click != undefined){
                stop = treeNode.data.click(treeNode);
            }
            if (undefined != this.onClick){
                stop = this.onClick(treeNode);
            }

            if (!ignoreStop && stop){
                return;
            }

            if (treeNode.hasChildren()){
                let ul = this.q("#" + treeNode.data.id + ">ul");
                if (treeNode.getData().extracted){
                    treeNode.getData().extracted = false;
                    treeNode.data.height = ul.css("height");
                    if (treeNode.data.icon && treeNode.data.iconOpen){
                        this.q("#" + treeNode.data.id + ">div i").removeClass(treeNode.data.icon_);
                        this.q("#" + treeNode.data.id + ">div i").addClass(this.getIcon(treeNode, treeNode.data.icon));
                    }

                    ul.animate({
                        height : "0px"
                    }, 'fast');
                }else{
                    treeNode.getData().extracted = true;
                    if (treeNode.data.icon && treeNode.data.iconOpen){
                        this.q("#" + treeNode.data.id + ">div i").removeClass(treeNode.data.icon_);
                        this.q("#" + treeNode.data.id + ">div i").addClass(this.getIcon(treeNode, treeNode.data.iconOpen));
                    }
                    ul.animate({
                        height : treeNode.getData().height
                    }, 'fast', ()=>{
                        ul.removeCss("height");
                    });
                }
            }
            this.q("div").removeClass("active");
            this.q("#" + treeNode.data.id + ">div").addClass("active");
        }

        private getIcon(curNode, icon){
            if (icon){
                if (typeof icon === 'function'){
                    return curNode.data.icon_ = icon(curNode);
                }else{
                    return curNode.data.icon_ = icon;
                }
            }
        }

        private getIconHtml(curNode, icon){
            let iconHtml = "";
            let iconClass = this.getIcon(curNode, icon);
            if (iconClass){
                iconHtml = '<i class="' + iconClass + '"></i> ';
            }
            return iconHtml;
        }

        render(tree:ITreeNode[]):TreeNode[]{
            this.q().empty();
            this.treeNodes = TreeNode.valueOfAll(tree);
            this.q().empty();
            this.q().append('<ul></ul>');
            for (let i = 0; i < this.treeNodes.length; ++i){
                let curNode = this.treeNodes[i];
                let iconHtml = this.getIconHtml(curNode, curNode.getData().icon);
                this.q('>ul')
                    .append('<li id="' + curNode.getData().id + '"><div class="tree-item0">' +
                        iconHtml +
                        curNode.getData().value + '</div></li>');

                this.q('>ul>li:last>div').click(curNode, (event)=>{
                     this.triggerClicked(event.data);
                });

                if (curNode.hasChildren()){
                    this.q('>ul>li:last').append('<ul></ul>');
                    this.renderChildren(this.q('>ul>li:last>ul'), curNode, 1);
                    if (curNode.data.extracted){
                        this.extractedNodes.push(curNode);
                    }
                    curNode.data.extracted = false;
                    curNode.data.height = this.q('>ul>li:last>ul').css("height");
                    this.q('>ul>li:last>ul').css("height", "0px");
                }
            }

            for (let i = 0; i < this.extractedNodes.length; ++i){
                this.triggerClicked(this.extractedNodes[i], true);
            }
            this.extractedNodes = [];
            return this.treeNodes;
        }

        private renderChildren(ul:any, parent:TreeNode, depth:number){
            let children : TreeNode[] = parent.subNodes;
            for (let i = 0; i < children.length; ++i){
                let curNode = children[i];
                let iconHtml = this.getIconHtml(curNode, curNode.getData().icon);
                ul.append('<li id="' + curNode.getData().id + '"><div class="tree-item' + depth + '">' +
                    iconHtml +
                    curNode.getData().value + '</div></li>');

                ul.children('li:last').children('div').click(curNode, (event)=>{
                    this.triggerClicked(event.data);
                });

                if (curNode.hasChildren()){
                    ul.children('li:last').append('<ul></ul>');
                    this.renderChildren(ul.children('li:last').children('ul'), curNode, depth + 1);
                    if (curNode.data.extracted){
                        this.extractedNodes.push(curNode);
                    }
                    curNode.data.extracted = false;
                    curNode.data.height = ul.children('li:last').children('ul').css("height");
                    ul.children('li:last').children('ul').css("height", "0px");
                }
            }
        }
    }

}