
declare var $;

module tree {
    export interface INodeData {
        id: number;
        value: string;
        icon:string;
        iconOpen:string;
        click?:(node:ITreeNode)=>void;
    }

    export interface ITreeNode {
        data: INodeData;
        parent?: ITreeNode;
        subNodes?: ITreeNode[];
    }

    export interface NodeVisitor {
        visit: (node:TreeNode) => boolean;
    }

    export interface NodeData extends INodeData{
        extracted:boolean;
        height:string;
    }

    export class TreeNode implements ITreeNode {
        data:NodeData;
        parent:TreeNode;
        subNodes:Array<TreeNode> = [];

        public constructor(data:INodeData) {
            this.data = <NodeData>data;
        }

        private setParent(parent:TreeNode) {
            this.parent = parent;
        }

        public append(node:TreeNode):void {
            if (node != null && node != undefined) {
                this.subNodes.push(node);
                node.setParent(this);
            }
        }

        public appendAll(nodes:TreeNode[]) {
            for (var i = 0; i < nodes.length; ++i) {
                this.append(nodes[i]);
            }
        }

        public getParent():TreeNode {
            return this.parent;
        }

        public hasChildren():boolean {
            return !(this.subNodes.length == 0);
        }

        public getData():NodeData {
            return this.data;
        }

        public childCount():number {
            return this.subNodes.length;
        }

        public childAt(i:number):TreeNode {
            return this.subNodes[i];
        }

        public leavesCount():number {
            if (!this.hasChildren()) {
                return 1;
            }

            var count:number = 0;
            for (var i:number = this.subNodes.length - 1; i >= 0; --i) {
                count += this.subNodes[i].leavesCount();
            }
            return count;
        }

        public mostLeftLeaf():TreeNode {
            var node:TreeNode = this;
            if (this.hasChildren()) {
                node = this.subNodes[0].mostLeftLeaf();
            }
            return node;
        }

        public leaves():TreeNode[] {
            var list:TreeNode[] = [];
            if (this.hasChildren()) {
                for (var i:number = this.subNodes.length - 1; i >= 0; --i) {
                    if (this.subNodes[i].hasChildren()) {
                        list = list.concat(this.subNodes[i].leaves());
                    } else {
                        list.push(this.subNodes[i]);
                    }
                }
            }
            else {
                list.push(this);
            }
            return list;
        }

        public depth():number {
            let depth = 0;
            let p = this.parent;
            while (p != undefined){
                p = p.parent;
                ++depth;
            };

            return depth;


            //var childDepth:number = 0;
            //var depthTmp:number = 0;
            //for (var i:number = this.subNodes.length - 1; i >= 0; --i) {
            //    depthTmp = this.subNodes[i].depth();
            //    if (depthTmp > childDepth) {
            //        childDepth = depthTmp;
            //    }
            //}
            //return childDepth + 1;
        }


        public find(node:TreeNode):number {
            for (var i = this.subNodes.length - 1; i >= 0; --i) {
                if (node == this.subNodes[i]) {
                    return i;
                }
            }
            return -1;
        }

        public children(depth:number):TreeNode[] {
            var children = [];
            children.push(this);
            for (var j = 0; j < depth; j++) {
                var len = children.length;
                for (var k = 0; k < len; k++) {
                    var sub:TreeNode = children[0];
                    children.splice(0, 1);
                    for (var i = 0; i < sub.childCount(); i++) {
                        children.push(sub.childAt(i));
                    }
                }
            }
            return children;
        }

        public accept(visitor:NodeVisitor):boolean {
            var stop:boolean = visitor.visit(this);
            for (var i:number = 0; !stop && i < this.subNodes.length; ++i) {
                stop = this.subNodes[i].accept(visitor);
            }
            return stop;
        }

        public static valueOf(node:ITreeNode):TreeNode {
            var retNode:TreeNode = new TreeNode(node.data);
            $(node.subNodes).each((i) => {
                retNode.append(this.valueOf(node.subNodes[i]));
            });
            return retNode;
        }

        public static valueOfAll(nodes:ITreeNode[]):TreeNode[] {
            var retNodes:TreeNode[] = [];
            $(nodes).each((i) => {
                retNodes.push(this.valueOf(nodes[i]))
            });
            return retNodes;
        }

        public remove(i:number):TreeNode {
            this.subNodes[i].setParent(null);
            return this.subNodes.splice(i, 1)[0];
        }

        public removeAll() {
            $(this.subNodes).each((i) => {
                this.subNodes[i].setParent(null);
            });
            this.subNodes = [];
        }
    }
}