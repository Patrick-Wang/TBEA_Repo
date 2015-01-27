var entryIndex;
(function (entryIndex) {
    var View = (function () {
        function View() {
        }
        View.getInstance = function () {
            return View.instance;
        };
        View.prototype.initInstance = function (opt) {
            this.updateTitle();
        };
        View.prototype.updateTitle = function () {
            $('h1').text("数据录入");
            document.title = "数据录入";
        };
        View.instance = new View();
        return View;
    })();
    entryIndex.View = View;
})(entryIndex || (entryIndex = {}));
