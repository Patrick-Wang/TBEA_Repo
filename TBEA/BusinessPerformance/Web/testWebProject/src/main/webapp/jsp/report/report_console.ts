/// <reference path="../framework/route/route.ts" />
/// <reference path="../util.ts" />
///<reference path="../unitedSelector.ts"/>
///<reference path="zTreeEx.ts"/>
module report {

    import Endpoint = framework.route.Endpoint;
    export let ConsoleID:number = framework.route.nextId();
    export module Message{
        export let MSG_INIT:number = framework.route.nextId();
    }

    export class Console implements Endpoint{
        private zTreeObj;

        constructor(){
            framework.router.register(this);
        }

        getId():number {
            return ConsoleID;
        }

        onEvent(e:framework.route.Event):any {
            switch (e.id){
                case Message.MSG_INIT:
                    this.onInit(<Util.IDataNode>(e.data));
                    break;
            }
        }

        private parseTree(zNodes, nodes: Util.IDataNode[]){
            let node;
            for (let i = 0; i < nodes.length; ++i){
                node = {
                    name : nodes[i].data.value,
                    open : false,
                    exType : nodes[i].data.id,
                    exPath : nodes[i].data.value
                }

                if (node.exType == 3 || node.exType == 4){
                    node.name = nodes[i].data.value;
                }else{
                    let index = nodes[i].data.value.lastIndexOf("\\");
                    node.name = nodes[i].data.value.substring(index + 1);
                }

                if (nodes[i].data.id != 0){
                    node.rm =['添  加','删  除','重命名'];
                    node.rmClick = (n, rmi)=> {
                       if(rmi == 2){
                           let newName = prompt("请重新输入名字:", n.name);
                           if (null != newName && n.name != newName){
                               n.name = newName;
                               this.zTreeObj.updateNode(n);
                           }
                       }else if(rmi == 1){
                           if(confirm("确定要删除 \"" + n.name+ "\" ？")){
                               this.zTreeObj.removeNode(n);
                           }
                       }
                    }
                }else{
                    node.open = true;
                }
                if (nodes[i].data.id == 2){
                    node.icon = "../../jsp/components/ztree/css/zTreeStyle/img/diy/7.png";
                }
                if (nodes[i].data.id == 4){
                    node.icon = "../../jsp/components/ztree/css/zTreeStyle/img/diy/3.png";
                    node.url = "../" + nodes[i].data.value + ".do";
                }
                if (nodes[i].subNodes != undefined){
                    node.children = [];
                    this.parseTree(node.children, nodes[i].subNodes);
                }

                zNodes.push(node);
            }
        }

        private onInit(root : Util.IDataNode):void {

            var setting = {};
            var zNodes = [];
            this.parseTree(zNodes, [root]);

            $(document).ready(() => {
                this.zTreeObj = zTreeEx.init($("#navigator"), setting, zNodes);
            });
        }
    }

    let console = new Console();
}