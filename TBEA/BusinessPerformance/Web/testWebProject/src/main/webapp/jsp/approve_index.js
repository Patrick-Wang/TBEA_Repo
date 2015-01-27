var approveIndex;
(function (approveIndex) {
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
            $('h1').text("数据审核");
            document.title = "数据审核";
        };
        View.instance = new View();
        return View;
    })();
    approveIndex.View = View;
})(approveIndex || (approveIndex = {}));
