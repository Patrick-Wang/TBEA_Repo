var template;
(function (template) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createFlatTable = function (gridName, title) {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                nodes.push(new JQTable.Node(title[i], "_" + i));
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        };
        JQGridAssistantFactory.createHierarchyTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("A", "a"),
                new JQTable.Node("B", "b")
                    .append(new JQTable.Node("C", "c"))
            ], gridName);
        };
        return JQGridAssistantFactory;
    }());
    var View = (function () {
        function View() {
            this.mDataSet = new Util.Ajax("url");
        }
        View.getInstance = function () {
            return View.instance;
        };
        View.prototype.initInstance = function (opt) {
            this.mOpt = opt;
            this.updateTitle();
            this.updateUI();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mDataSet.get({ year: 0, month: 0, day: 0, companyId: 0 })
                .then(function (data) {
                _this.updateTitle();
                _this.updateTable("");
            });
        };
        View.prototype.updateTitle = function () {
            $('h1').text("new header");
            document.title = "new header";
        };
        View.prototype.updateTable = function (tableId) {
            var name = tableId + "_jqgrid";
            var tableAssist = JQGridAssistantFactory.createFlatTable(name, []);
            var data = [];
            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: '100%',
                width: 1250,
                shrinkToFit: true,
                autoScroll: true,
            }));
        };
        View.instance = new View();
        return View;
    }());
    template.View = View;
})(template || (template = {}));
