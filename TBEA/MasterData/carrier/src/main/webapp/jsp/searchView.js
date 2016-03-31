/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
var searchView;
(function (searchView) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createFlatTable = function (gridName, ids, title) {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i == 1 || i == 5 || i == 7 || i == 8) {
                    nodes.push(new JQTable.Node(title[i], ids[i], true, JQTable.TextAlign.Left, 50));
                }
                else if (i == 3) {
                    nodes.push(new JQTable.Node(title[i], ids[i], true, JQTable.TextAlign.Left, 70));
                }
                else if (i == 6) {
                    nodes.push(new JQTable.Node(title[i], ids[i], true, JQTable.TextAlign.Left, 120));
                }
                else {
                    nodes.push(new JQTable.Node(title[i], ids[i]));
                }
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mDataSet = new Util.Ajax("query_by_name.do");
        }
        View.getInstance = function () {
            return View.instance;
        };
        View.prototype.initInstance = function (opt) {
            this.mOpt = opt;
            //this.updateTitle();
            this.updateUI();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            var val = $("#searchText").val();
            if (val.length != 0) {
                this.mDataSet.get({ companyName: val })
                    .then(function (data) {
                    //                    this.updateTitle();
                    if (undefined != data.result) {
                        var parent = $("#table");
                        parent.empty();
                        parent.append("<div>" + data.result + "</div>");
                    }
                    else {
                        _this.updateTable("table", data);
                    }
                });
            }
        };
        //        private updateTitle(){
        //             $('h1').text("new header");
        //             document.title = "new header";    
        //        }
        View.prototype.updateTable = function (tableId, data) {
            var name = tableId + "_jqgrid";
            var tableAssist = JQGridAssistantFactory.createFlatTable(name, [
                "jgmc",
                "jgdm",
                "jgdz",
                "jglx",
                "bzjgmcs",
                "bzrq",
                "zch",
                "zcrq",
                "zfrq"
            ], [
                "机构名称",
                "机构代码",
                "机构地址",
                "机构类型",
                "办证机构名称",
                "办证日期",
                "注册号",
                "注册日期",
                "作废日期"
            ]);
            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                // url: "TestTable/WGDD_load.do",
                // datatype: "json",
                data: data,
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth: true,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: data.length > 25 ? 550 : '100%',
                width: 1250,
                rowNum: 10000,
                shrinkToFit: true,
                autoScroll: true,
            }));
        };
        View.instance = new View();
        return View;
    })();
    searchView.View = View;
})(searchView || (searchView = {}));
