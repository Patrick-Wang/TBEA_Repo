/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
var user_mgr;
(function (user_mgr) {
    var router = framework.router;
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName, dataInit) {
            return new JQTable.JQGridAssistant([
                JQTable.Node.create({
                    name: "用户名",
                    align: JQTable.TextAlign.Left,
                    width: 150 }),
                JQTable.Node.create({
                    name: "角色",
                    align: JQTable.TextAlign.Left,
                    isReadOnly: false,
                    isNumber: false,
                    editType: 'text',
                    options: { dataInit: dataInit }
                })
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var SimpleView = (function () {
        function SimpleView() {
            this.mData = [];
            router.register(this);
        }
        SimpleView.prototype.getId = function () {
            return Util.FAMOUS_VIEW;
        };
        SimpleView.prototype.onEvent = function (e) {
            switch (e.id) {
                case Util.MSG_INIT:
                    this.init(e.data);
                    break;
                case Util.MSG_UPDATE:
                    this.updateUI();
                    break;
            }
        };
        SimpleView.prototype.init = function (opt) {
            var _this = this;
            this.mOpt = opt;
            this.mUpdateAjax = new Util.Ajax(opt.updateUrl);
            this.mSubmitAjax = new Util.Ajax(opt.submitUrl);
            $("#user-sel").append('<select class="selectpicker" multiple data-live-search="true" title="公司名称"></select>');
            var sel = $("#user-sel select");
            for (var i = 0; i < opt.users.length; ++i) {
                sel.append('<option value="' + opt.users[i][0] + '">' + opt.users[i][1] + '</option>');
            }
            $('#user-sel .selectpicker').selectpicker({});
            $("#grid-update").on('click', function () {
                _this.updateUI();
            });
            $("#grid-submit").on('click', function () {
                _this.submitData();
            });
            this.adjustHeader();
            this.updateUI();
        };
        SimpleView.prototype.adjustHeader = function () {
            $("#headerHost").removeCss("width");
            if ($("#headerHost").height() > 40) {
                $(".page-header").addClass("page-header-double");
                $("#headerHost").css("width", $("#comp-sel").width() + "px");
            }
            else {
                $(".page-header").removeClass("page-header-double");
            }
        };
        SimpleView.prototype.updateUI = function () {
            var _this = this;
            var uids = $('#user-sel .selectpicker').selectpicker('val');
            var iUids = [];
            if (!uids) {
                iUids.push(-1);
            }
            else {
                for (var i = 0; i < uids.length; ++i) {
                    iUids.push(parseInt(uids[i]));
                }
            }
            this.mUpdateAjax.post({ uids: JSON.stringify(iUids) })
                .then(function (dataArray) {
                _this.mData = dataArray.data;
                _this.updateTable();
            });
        };
        //
        SimpleView.prototype.adjustSize = function () {
            var jqgrid = this.jqgrid();
            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }
            var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
            this.tableAssist && this.tableAssist.resizeHeight(maxTableBodyHeight);
            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }
        };
        SimpleView.prototype.jqgrid = function () {
            return $("#" + this.jqgridName());
        };
        SimpleView.prototype.jqgridName = function () {
            return this.mOpt.tableId + "_jqgrid_real";
        };
        SimpleView.prototype.createJqassist = function () {
            var _this = this;
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='" + this.jqgridName() + "'></table>");
            this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), function (e, opt) {
                // var text =
                var width = $(e).width();
                $(e).hide();
                var p = $(e).parent();
                p.append('<div style="position:absolute;z-index:30000;margin-top:-12px;"><select class="selectpicker" multiple data-live-search="true" ></select></div>');
                var sel = p.find('select');
                var oldRoles = $(e).val().split(",");
                for (var i = 0; i < _this.mOpt.roles.length; ++i) {
                    sel.append('<option value="' + _this.mOpt.roles[i][1] + '">' + _this.mOpt.roles[i][1] + '</option>');
                }
                var div = p.find('div');
                var roleNames = oldRoles;
                //sel.on('hidd.bs.select',  () => {
                //
                //    $("#" + this.jqgridName())
                //        .jqGrid("restoreCell", opt.rowId, opt.name);
                //
                //    $("#" + this.jqgridName())
                //        .jqGrid("setCell", opt.rowId, opt.name, this.roleNames);
                //    div.remove();
                //});
                sel.on('changed.bs.select', function () {
                    roleNames = p.find('.selectpicker').selectpicker('val');
                    $(e).val(roleNames.join());
                });
                sel.selectpicker({
                    size: 10
                });
                sel.selectpicker('val', oldRoles);
                div.children().css("width", width + "px");
                div.children().eq(0).find("button").css("padding-top", "3px");
                div.children().eq(0).find("button").css("padding-bottom", "3px");
            });
            return this.tableAssist;
        };
        SimpleView.prototype.updateTable = function () {
            this.createJqassist();
            this.tableAssist.create({
                dataWithId: this.mData,
                datatype: "local",
                cellEdit: true,
                cellsubmit: 'clientArray',
                multiselect: false,
                drag: false,
                resize: false,
                height: '100%',
                width: $("#" + this.mOpt.tableId).width(),
                shrinkToFit: true,
                rowNum: 2000,
                assistEditable: true,
                autoScroll: true
            });
            this.adjustSize();
        };
        SimpleView.prototype.submitData = function () {
            var _this = this;
            var data = this.tableAssist.getChangedData();
            var users = [];
            var roles = [];
            for (var i = 0; i < data.length; ++i) {
                var uid = parseInt(data[i][0]);
                var role = data[i][2].split(",");
                for (var j = 0; j < role.length; ++j) {
                    roles.push(role[j]);
                    users.push(uid);
                }
            }
            if (users.length > 0) {
                this.mSubmitAjax.post({
                    users: JSON.stringify(users),
                    roles: JSON.stringify(roles) })
                    .then(function (dataArray) {
                    // this.updateUI();
                    _this.tableAssist.resetChangedData();
                    Util.Toast.success('用户数据修改成功');
                });
            }
            else {
                Util.Toast.warning('没有可修改的用户数据');
            }
        };
        SimpleView.ins = new SimpleView();
        return SimpleView;
    })();
    user_mgr.SimpleView = SimpleView;
})(user_mgr || (user_mgr = {}));
