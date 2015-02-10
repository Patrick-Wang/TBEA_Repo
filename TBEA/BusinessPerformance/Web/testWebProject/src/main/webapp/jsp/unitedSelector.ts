/// <reference path="jqgrid/vector.ts" />
declare var $;
module Util {

    export interface IData {
        id: number;
        value: string;
    }

    export interface IDataNode {
        data: IData;
        parent: IDataNode;
        subNodes: Array<IDataNode>;
    }
    
    export interface NodeVisitor {
        visit: (node: DataNode) => boolean;
    }

    export class DataNode implements IDataNode{
        data: IData;
        parent: DataNode;
        subNodes: Array<DataNode> = [];

        public constructor(data: IData) {
            this.data = data;
        }

        private setParent(parent: DataNode) {
            this.parent = parent;
        }

        public append(node: DataNode): void {
            if (isExist(node)) {
                this.subNodes.push(node);
                node.setParent(this);
            }
        }

        public appendAll(nodes: DataNode[]) {
            for (var i in nodes) {
                this.append(nodes[i]);
            }
        }

        public getParent(): DataNode {
            return this.parent;
        }

        public hasChildren(): boolean {
            return !(this.subNodes.length == 0);
        }

        public getData(): IData {
            return this.data;
        }

        public childCount(): number {
            return this.subNodes.length;
        }

        public childAt(i: number): DataNode {
            return this.subNodes[i];
        }

        public leavesCount(): number {
            if (!this.hasChildren()) {
                return 1;
            }

            var count: number = 0;
            for (var i: number = this.subNodes.length - 1; i >= 0; --i) {
                count += this.subNodes[i].leavesCount();
            }
            return count;
        }

        public mostLeftLeaf(): DataNode {
            var node: DataNode = this;
            if (this.hasChildren()) {
                node = this.subNodes[0].mostLeftLeaf();
            }
            return node;
        }

        public leaves(): DataNode[] {
            var list: DataNode[] = [];
            if (this.hasChildren()) {
                for (var i: number = this.subNodes.length - 1; i >= 0; --i) {
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

        public depth(): number {
            var childDepth: number = 0;
            var depthTmp: number = 0;
            for (var i: number = this.subNodes.length - 1; i >= 0; --i) {
                depthTmp = this.subNodes[i].depth();
                if (depthTmp > childDepth) {
                    childDepth = depthTmp;
                }
            }
            return childDepth + 1;
        }

        
        public find(node: DataNode) : number{
            for (var i = this.subNodes.length - 1; i >= 0; --i){
                if (node == this.subNodes[i]){
                    return i;    
                }
            }    
            return -1;
        }
        
        public children(depth: number): DataNode[] {
            var children: std.vector<DataNode> = new std.vector<DataNode>();
            children.push(this);
            for (var j = 0; j < depth; j++) {
                var len = children.size();
                for (var k = 0; k < len; k++) {
                    var sub: DataNode = children.get(0);
                    children.erase(0);
                    for (var i = 0; i < sub.childCount(); i++) {
                        children.push(sub.childAt(i));
                    }
                }
            }
            return children.toArray();
        }

        public accept(visitor: NodeVisitor): boolean {
            var stop: boolean = visitor.visit(this);
            for (var i: number = this.subNodes.length - 1; !stop && i >= 0; --i) {
                stop = this.subNodes[i].accept(visitor);
            }
            return stop;
        }
        
        public static valueOf(node : IDataNode) : DataNode{
            var retNode : DataNode = new DataNode(node.data);
            $(node.subNodes).each((i)=>{
                retNode.append(this.valueOf(node.subNodes[i]));
            });
            return retNode;
        }
        
        public static valueOfAll(nodes : IDataNode[]) : DataNode[]{
            var retNodes : DataNode[] = [];
            $(nodes).each((i)=>{
                retNodes.push(this.valueOf(nodes[i]))
            });
            return retNodes;
        }
    }


    export class UnitedSelector {
        private mRoot: DataNode = new DataNode(null);
        private mCtrlId: string;
        private mPath: number[] = [];
        private mFnChange : (selector : UnitedSelector)=>void;
                
        public constructor(data: IDataNode[], ctrlId: string, path? : number[]) {
            this.mRoot.appendAll(DataNode.valueOfAll(data));
            this.mCtrlId = ctrlId + "_unitedSelector";
            $("#" + ctrlId).append('<table id="' + this.mCtrlId + '" cellspacing="0" cellpadding="0"><tr></tr></table>');
            if (isExist(data) && this.mRoot.childCount() > 0) {
                this.update(path);
            }
        }
        
        public hide(){
           $("#" + this.mCtrlId).css("display", "none");
        }
        
        public show(){
           $("#" + this.mCtrlId).css("display", "");
        }

        public change(fnChange : (sel : any)=>void){
            this.mFnChange = fnChange;    
        }
        
        public getNodes() : DataNode[]{
            var ret : DataNode[] = [];
            var node: DataNode = this.mRoot;
            for (var i = 0; i < this.mPath.length; ++i) {
                node = node.childAt(this.mPath[i]);
                ret.push(node);
            }
            return ret;
        }
        
        public getPath() : number[]{
            return this.mPath;
        }
        
        public getSelect() : any{
             return $("#" + this.mCtrlId + " tr select");
        }

        private initPath(path?: number[]) {
            var node = this.mRoot.childAt(0);
            this.mPath.push(0);
            while (node.hasChildren()) {
                node = node.childAt(0);
                this.mPath.push(0);
            }
        }

        public getDataNode(path : number[], depth?: number) {
            if (!isExist(depth)){
               depth = path.length;
            }
            var node: DataNode = this.mRoot;
            for (var i = 0; i < depth; ++i) {
                node = node.childAt(path[i]);
            }
            return node;
        }

        private appendChildren(select: any, depth: number): void {
            var data: IData;
            var parent: DataNode = this.getDataNode(this.mPath, depth - 1);
            var sel = this.mPath[depth - 1];
            for (var i = 0; i < parent.childCount(); ++i) {
                data = parent.childAt(i).getData();
                if (i == sel) {
                    select.append('<option index = "' + i + '" value="' + data.id + '" selected="selected">' + data.value + '</option>');
                } else {
                    select.append('<option index = "' + i + '" value="' + data.id + '">' + data.value + '</option>');
                }
            }
            select = $(select);
            select.change((s : any) => {
                var path = [];
                for (var i = 0; i < depth - 1; ++i) {
                    path.push(this.mPath[i]);
                }
                path[depth - 1] = parseInt(select.children('option:selected').eq(0).attr("index"));
                var node: DataNode = this.getDataNode(path, depth);
                while (node.hasChildren()) {
                    node = node.childAt(0);
                    path.push(0);
                }
                this.update(path);
                
                if (isExist(this.mFnChange)){
                    this.mFnChange(select);    
                }
            });

        }

        private updatePath(path: number[]): number {
            var pos = 0;
            if (isExist(path)) {
                pos = path.length > this.mPath.length ? this.mPath.length : path.length;
                for (var i = 0; i < pos; ++i) {
                    if (path[i] != this.mPath[i]) {
                        pos = i + 1;
                        break;
                    }
                }
                this.mPath = path;
            }
            return pos;
        }

        private empty(ctrlTr: any, begin: number) {
            for (var i = ctrlTr.children().length - 1; i >= begin * 2; --i) {
                ctrlTr.children().eq(i).remove();
            }
        }

        private add(ctrlTr: any, begin: number) {
            for (var i = begin; i < this.mPath.length; ++i) {
                ctrlTr.append(
                        '<td><select id="united_' + i + '" style="font-size:13px"></select></td>' +
                        '<td><div style="width:5px;"></div></td>');
                this.appendChildren(ctrlTr.children().eq(i * 2).children().eq(0), i + 1);
            }
        }

        public update(path?: number[]): void {
            if (this.mRoot.childCount() > 0) {
                var ctrlTr = $("#" + this.mCtrlId + " tr");
                var start = 0;
                if (isExist(path)){  
                    start = this.updatePath(path);
                }
                else{
                    this.initPath();
                }
                this.empty(ctrlTr, start);
                this.add(ctrlTr, start);
            }
        }
    }
}