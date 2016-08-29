/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="dateSelector.ts" />
var userStatus;
(function (userStatus) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createFlatTable = function (gridName, title) {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                nodes.push(new JQTable.Node(title[i], "_" + i, true, JQTable.TextAlign.Left));
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
        }
        View.getInstance = function () {
            return View.instance;
        };
        View.prototype.initInstance = function (data) {
            this.mData = data;
            this.updateTitle();
            this.updateUI();
        };
        View.prototype.updateUI = function () {
            this.updateTitle();
            this.updateTable("table");
        };
        View.prototype.updateTitle = function () {
            $('h1').text("用户在线状态 ");
            document.title = "用户在线状态";
        };
        View.prototype.updateTable = function (tableId) {
            var name = tableId + "_jqgrid";
            var tableAssist = JQGridAssistantFactory.createFlatTable(name, [
                "用户名", "SID", "登录时间", "最近访问时间"]);
            var summary = "● 在线户数 : " + this.mData.active_user_count + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp";
            if (undefined != this.mData.latest_active_user) {
                summary += "● 最近访问用户 : " + this.mData.latest_active_user + "&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;";
            }
            if (undefined != this.mData.last_accessed_time) {
                summary += "● 最近访问用户 : " + this.mData.last_accessed_time;
            }
            $('#text')[0].innerHTML = summary;
            var data = [];
            for (var i = 0; i < this.mData.users.length; ++i) {
                data.push([
                    this.mData.users[i].name,
                    this.mData.users[i].sid,
                    this.mData.users[i].login_time,
                    this.mData.users[i].last_accessed_time,
                ]);
            }
            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                // url: "TestTable/WGDD_load.do",
                // datatype: "json",
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth : true,
                //  cellsubmit: 'clientArray',
                //  cellEdit: true,
                width: 1000,
                height: '100%',
                shrinkToFit: true,
                autoScroll: true
            }));
        };
        View.instance = new View();
        return View;
    })();
    userStatus.View = View;
})(userStatus || (userStatus = {}));
