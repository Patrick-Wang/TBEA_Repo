///<reference path="treeNode.ts"/>

declare var $;

module tree{

    export class Tree{
        treeId:string;
        treeNodes:TreeNode[];
        onClick:(node:TreeNode)=>void;
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

        setOnClickListener(click:(node:TreeNode)=>void) : (node:TreeNode)=>void{
            let oldFn = this.onClick;
            this.onClick = click;
            return oldFn;
        }

        triggerClicked(treeNode:TreeNode){
            if (treeNode.hasChildren()){
                let ul = this.q("#" + treeNode.data.id + ">ul");
                if (treeNode.getData().extracted){
                    treeNode.getData().extracted = false;
                    treeNode.data.height = ul.css("height");
                    if (treeNode.data.icon && treeNode.data.iconOpen){
                        this.q("#" + treeNode.data.id + " i").removeClass();
                        this.q("#" + treeNode.data.id + " i").addClass(treeNode.data.icon);
                    }

                    ul.animate({
                        height : "0px"
                    }, 'fast');
                }else{
                    treeNode.getData().extracted = true;
                    if (treeNode.data.icon && treeNode.data.iconOpen){
                        this.q("#" + treeNode.data.id + " i").removeClass();
                        this.q("#" + treeNode.data.id + " i").addClass(treeNode.data.iconOpen);
                    }
                    ul.animate({
                        height : treeNode.getData().height
                    }, 'fast', ()=>{
                        ul.removeCss("height");
                    });
                }
            }
            if (treeNode.data.click != undefined){
                treeNode.data.click(treeNode);
            }
            if (undefined != this.onClick){
                this.onClick(treeNode);
            }
            this.q("div").removeClass("active");
            this.q("#" + treeNode.data.id + ">div").addClass("active");
        }



        render(tree:ITreeNode[]):TreeNode[]{
            this.q().empty();
            this.treeNodes = TreeNode.valueOfAll(tree);
            this.q().empty();
            this.q().append('<ul></ul>');
            for (let i = 0; i < this.treeNodes.length; ++i){
                let curNode = this.treeNodes[i];
                this.q('>ul')
                    .append('<li id="' + curNode.getData().id + '"><div class="tree-item0">' +
                        (curNode.getData().icon ? ('<i class="' + curNode.getData().icon + '"></i> ') : '') +
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
                this.triggerClicked(this.extractedNodes[i]);
            }
            this.extractedNodes = [];
            return this.treeNodes;
        }

        private renderChildren(ul:any, parent:TreeNode, depth:number){
            let children : TreeNode[] = parent.subNodes;
            for (let i = 0; i < children.length; ++i){
                let curNode = children[i];
                ul.append('<li id="' + curNode.getData().id + '"><div class="tree-item' + depth + '">' +
                    (curNode.getData().icon ? ('<i class="' + curNode.getData().icon + '"></i> ') : '') +
                    curNode.getData().value + '</div></li>');

                ul.children('li:last').children('div').click(curNode, (event)=>{
                    this.triggerClicked(event.data);
                });

                if (curNode.hasChildren()){
                    ul.children('li:last').append('<ul></ul>');
                    this.renderChildren(ul.children('li:last').children('ul'), curNode, depth + 1);
                    if (curNode.data.extracted){
                        this.extractedNodes.push[curNode];
                    }
                    curNode.data.extracted = false;
                    curNode.data.height = ul.children('li:last').children('ul').css("height");
                    ul.children('li:last').children('ul').css("height", "0px");
                }
            }
        }
    }

}