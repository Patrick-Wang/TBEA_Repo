/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
declare var echarts;

module user_mgr {

    import Endpoint = framework.route.Endpoint;
    import router = framework.router;


    class JQGridAssistantFactory {

        public static createTable(gridName:string, dataInit:any):JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                JQTable.Node.create({
                    name: "用户名",
                    align: JQTable.TextAlign.Left,
                    width: 150
                }),
                JQTable.Node.create({
                    name: "角色",
                    align: JQTable.TextAlign.Left,
                    isReadOnly: false,
                    isNumber: false,
                    editType: 'text',
                    options: {dataInit: dataInit}
                })
            ], gridName);
        }
    }

    interface IViewOption {
        tableId: string;
        date: Util.Date;
        comps: Util.IDataNode[];
    }

    export class SimpleView implements Endpoint {

        getId():number {
            return Util.FAMOUS_VIEW;
        }

        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case Util.MSG_INIT:
                    this.init(e.data);
                    break;
                case Util.MSG_UPDATE:
                    this.updateUI();
                    break;
            }
        }

        public constructor() {
            router.register(this);
        }

        private static ins:SimpleView = new SimpleView();

        private mOpt:IViewOption;
        private mData:Array<string[]> = [];
        private tableAssist:JQTable.JQGridAssistant;
        private mUpdateAjax:Util.Ajax;// = new Util.Ajax("/BusinessManagement/ydzb/hzb_companys_update.do");
        private mSubmitAjax:Util.Ajax;// = new Util.Ajax("/BusinessManagement/ydzb/hzb_companys_update.do");
        mCreateUserAjax:Util.Ajax;
        mGetUserAjax:Util.Ajax;

        public init(opt:any):void {
            this.mOpt = opt;
            this.mUpdateAjax = new Util.Ajax(opt.updateUrl);
            this.mSubmitAjax = new Util.Ajax(opt.submitUrl);
            this.mGetUserAjax = new Util.Ajax(opt.getUserUrl);
            this.mCreateUserAjax = new Util.Ajax(opt.createUserUrl);

            this.updateUserSel();

            $("#grid-update").on('click', ()=> {
                this.updateUI();
            });

            $("#grid-submit").on('click', ()=> {
                this.submitData();
            });

            $("#grid-create").on('click', ()=> {
                this.createUser();
            });

            this.adjustHeader();
            this.updateUI();

            $(window).resize(()=>{
                this.adjustHeader();
                this.adjustSize();
            })
        }

        private updateUserSel() {
            $("#user-sel").empty();
            $("#user-sel").append(
                '<select class="selectpicker" multiple data-live-search="true" title="用户"></select>');
            var sel = $("#user-sel select");
            for (var i = 0; i < this.mOpt.users.length; ++i) {
                sel.append('<option value="' + this.mOpt.users[i][0] + '">' + this.mOpt.users[i][1] + '</option>');
            }
            $('#user-sel .selectpicker').selectpicker({
                // style: 'btn-info'
            });
        }

        private adjustHeader() {
            $("#headerHost").removeCss("width");
            if ($("#headerHost").height() > 40) {
                $(".page-header").addClass("page-header-double");
                $("#headerHost").css("width", $("#comp-sel").width() + "px");
            } else {
                $(".page-header").removeClass("page-header-double");
            }
        }


        public updateUI() {
            var uids = $('#user-sel .selectpicker').selectpicker('val');
            var iUids = [];
            if (!uids) {
                iUids.push(-1);
            } else {
                for (var i = 0; i < uids.length; ++i) {
                    iUids.push(parseInt(uids[i]));
                }
            }

            this.mUpdateAjax.post({uids: JSON.stringify(iUids)})
                .then((dataArray:any) => {
                    this.mData = dataArray.data;
                    this.updateTable();
                });
        }

        //
        private adjustSize() {
            var jqgrid = this.jqgrid();
            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }

            let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
            this.tableAssist && this.tableAssist.resizeHeight(maxTableBodyHeight);

            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }
        }

        private jqgrid() {
            return $("#" + this.jqgridName());
        }

        private jqgridName():string {
            return this.mOpt.tableId + "_jqgrid_real";
        }

        private createJqassist():JQTable.JQGridAssistant {
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='" + this.jqgridName() + "'></table>");
            this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), (e, opt) => {
                // var text =
                var width = $(e).width();
                $(e).hide();
                var p = $(e).parent();

                p.append('<div style="position:absolute;z-index:30000;margin-top:-12px;"><select class="selectpicker" multiple data-live-search="true"  title="用户"></select></div>');
                var sel = p.find('select');
                var oldRoles = $(e).val().split(",");
                for (var i = 0; i < this.mOpt.roles.length; ++i) {
                    sel.append('<option value="' + this.mOpt.roles[i][1] + '">' + this.mOpt.roles[i][1] + '</option>');
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
                sel.on('changed.bs.select', () => {
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
        }

        private updateTable():void {
            this.createJqassist();


            this.tableAssist.create({
                dataWithId: this.mData,
                datatype: "local",
                cellEdit: false,
                cellsubmit: 'clientArray',
                multiselect: true,
                //multiboxonly:true,
                //beforeSelectRow:()=>
                //{
                //    $("#" + this.jqgridName()).jqGrid('resetSelection');
                //    return(true);
                //},
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
        }


        private submitData():void {
            var selIds = this.tableAssist.getSelection();

            if (selIds.length > 0) {
                var rData = this.tableAssist.getRowsData(selIds);
                let dialog = bootbox.dialog({
                    message: $("#configRoleTemplate").html().replace(/__/g, ""),
                    title: "角色分配",
                    className: "modal-darkorange",
                    buttons: {
                        success: {
                            label: "确定",
                            className: "btn-blue",
                            callback: () => {

                                var sel = $("#configRole #roleNames").find('select');
                                var roles = sel.selectpicker('val');
                                if (roles == null) {
                                    $("#warning").text("角色不能为空").show();
                                    return false;
                                }

                                var users = [];
                                var rNames = [];
                                for (var j = 0; j < rData.length; ++j){
                                    var uid = parseInt(rData[j][0]);
                                    rNames = rNames.concat(roles);
                                    for (var i = 0; i < roles.length; ++i) {
                                        users.push(uid);
                                    }
                                }



                                this.mSubmitAjax.post({
                                        users: JSON.stringify(users),
                                        roles: JSON.stringify(rNames)
                                    })
                                    .then((dataArray:any) => {
                                        // this.updateUI();
                                        //    this.tableAssist.resetChangedData();
                                        Util.Toast.success('用户数据修改成功');
                                        for (var j = 0; j < rData.length; ++j){
                                            this.tableAssist.setCellValue(rData[j][0], 2, roles.join());
                                        }

                                        dialog.modal("hide");
                                    });
                                return false;
                            }
                        },
                        "取消": {
                            className: "btn-default",
                            callback: function () {
                            }
                        }
                    }
                });

                dialog.modal("show");

                var uNames = [];
                for (var i = 0; i < rData.length; ++i){
                    uNames.push(rData[i][1]);
                }

                $("#configRole #userName").val(uNames.join());
                $("#configRole #roleNames").append('<select class="selectpicker" multiple data-live-search="true" title="角色"></select>');
                var roles = [];
                if (rData.length == 1){
                    roles = rData[0][2].split(",");
                }

                var sel = $("#configRole #roleNames").find('select');

                for (var i = 0; i < this.mOpt.roles.length; ++i) {
                    sel.append('<option value="' + this.mOpt.roles[i][1] + '">' + this.mOpt.roles[i][1] + '</option>');
                }
                sel.selectpicker({
                    size: 10
                });
                sel.selectpicker('val', roles);
                $(".role_drop>div").css("width", "100%");
            } else {
                Util.Toast.warning('请选择用户');
            }
        }

        private createUser():void {
            let dialog = bootbox.dialog({
                message: $("#createUserTemplate").html().replace(/__/g, ""),
                title: "新建用户",
                className: "modal-darkorange",
                buttons: {
                    success: {
                        label: "确定",
                        className: "btn-blue",
                        callback: () => {
                            let uName = $("#userName").val();
                            if (!uName) {
                                $("#warning").text("用户名不能为空").show();
                                return false;
                            }

                            let psw = $("#userPsw").val();
                            if (!psw) {
                                psw = "1234";
                            }

                            this.mCreateUserAjax.post({
                                userName: uName,
                                userPsw: psw
                            }).then((ret:any)=> {
                                if (ret.errorCode == 0) {
                                    dialog.modal("hide");
                                    Util.Toast.success("新建用户成功");
                                    this.mGetUserAjax.post({}).then((ret:any)=> {
                                        this.mOpt.users = ret.users;
                                        this.updateUserSel();
                                    });
                                } else {
                                    $("#warning").text("用户名已被占用").show();
                                }
                            });
                            return false;
                        }
                    },
                    "取消": {
                        className: "btn-default",
                        callback: function () {
                        }
                    }
                }
            });
            dialog.modal("show");

        }
    }
}