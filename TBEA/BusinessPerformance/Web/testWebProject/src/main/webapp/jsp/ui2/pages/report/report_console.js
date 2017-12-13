/// <reference path="../framework/route/route.ts" />
/// <reference path="../util.ts" />
///<reference path="../unitedSelector.ts"/>
///<reference path="zTreeEx.ts"/>
var report;
(function (report) {
    report.ConsoleID = framework.route.nextId();
    var Message;
    (function (Message) {
        Message.MSG_INIT = framework.route.nextId();
    })(Message = report.Message || (report.Message = {}));
    var Console = (function () {
        function Console() {
            framework.router.register(this);
        }
        Console.prototype.getId = function () {
            return report.ConsoleID;
        };
        Console.prototype.onEvent = function (e) {
            switch (e.id) {
                case Message.MSG_INIT:
                    this.onInit((e.data));
                    break;
            }
        };
        Console.prototype.parseTree = function (zNodes, nodes) {
            var _this = this;
            var node;
            for (var i = 0; i < nodes.length; ++i) {
                node = {
                    open: false,
                    exType: nodes[i].data.id,
                    exPath: nodes[i].data.value
                };
                if (node.exType == 3 || node.exType == 4) {
                    node.name = nodes[i].data.value;
                }
                else {
                    var index = nodes[i].data.value.lastIndexOf("\\");
                    node.name = nodes[i].data.value.substring(index + 1);
                }
                if (nodes[i].data.id != 0) {
                    node.rm = ['添  加', '删  除', '重命名'];
                    node.rmClick = function (n, rmi) {
                        if (rmi == 2) {
                            var newName = prompt("请重新输入名字:", n.name);
                            if (null != newName && n.name != newName) {
                                n.name = newName;
                                _this.zTreeObj.updateNode(n);
                            }
                        }
                        else if (rmi == 1) {
                            if (confirm("确定要删除 \"" + n.name + "\" ？")) {
                                _this.zTreeObj.removeNode(n);
                            }
                        }
                    };
                }
                else {
                    node.open = true;
                }
                if (nodes[i].data.id == 2) {
                    node.icon = "../../jsp/components/ztree/css/zTreeStyle/img/diy/7.png";
                }
                if (nodes[i].data.id == 4) {
                    node.icon = "../../jsp/components/ztree/css/zTreeStyle/img/diy/3.png";
                    node.url = "../" + nodes[i].data.value + ".do";
                }
                if (nodes[i].subNodes != undefined) {
                    node.children = [];
                    this.parseTree(node.children, nodes[i].subNodes);
                }
                zNodes.push(node);
            }
        };
        Console.prototype.onInit = function (root) {
            var _this = this;
            var setting = {};
            var zNodes = [];
            this.parseTree(zNodes, [root]);
            $(document).ready(function () {
                _this.zTreeObj = zTreeEx.init($("#navigator"), setting, zNodes);
            });
        };
        return Console;
    }());
    report.Console = Console;
    var console = new Console();
})(report || (report = {}));
