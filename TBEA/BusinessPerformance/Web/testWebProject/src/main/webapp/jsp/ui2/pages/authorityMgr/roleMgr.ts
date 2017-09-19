/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
declare var echarts;

module role_mgr {

    import Endpoint = framework.route.Endpoint;
    import router = framework.router;


    class JQGridAssistantFactory {

        public static createTable(gridName:string, dataInit:any):JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                JQTable.Node.create({
                    name: "角色",
                    align: JQTable.TextAlign.Left,
                    width: 150
                }),
                JQTable.Node.create({
                    name: "权限",
                    align: JQTable.TextAlign.Left
                }),
                JQTable.Node.create({
                    name: "公司",
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
        private mData:any;
        private tableAssist:JQTable.JQGridAssistant;
        private mUpdateAjax:Util.Ajax;// = new Util.Ajax("/BusinessManagement/ydzb/hzb_companys_update.do");
        private mSubmitAjax:Util.Ajax;// = new Util.Ajax("/BusinessManagement/ydzb/hzb_companys_update.do");
        private mCreateRoleAjax:Util.Ajax;
        private mAddAuthAjax:Util.Ajax;

        private mGetRoleName:Util.Ajax;
        rowNum:number;
        rids:any;
        aids:any;

        public init(opt:any):void {
            this.mOpt = opt;
            this.mUpdateAjax = new Util.Ajax(opt.updateUrl);
            this.mSubmitAjax = new Util.Ajax(opt.submitUrl);
            this.mCreateRoleAjax = new Util.Ajax(opt.createRoleUrl);
            this.mAddAuthAjax = new Util.Ajax(opt.addAuthUrl);
            this.mGetRoleName = new Util.Ajax(opt.roleNameUrl);
            this.updateRoleSel();

            $("#auth-sel").append(
                '<select class="selectpicker" multiple data-live-search="true"  title="权限" ></select>');
            sel = $("#auth-sel select");
            for (var i = 0; i < opt.auths.length; ++i) {
                sel.append('<option value="' + opt.auths[i][0] + '">' + opt.auths[i][1] + '</option>');
            }


            $('#auth-sel .selectpicker').selectpicker({
                // style: 'btn-info'
            });

            $("#grid-update").on('click', ()=> {
                this.updateUI();
            });

            $("#grid-submit").on('click', ()=> {
                this.submitData();
            });

            $("#grid-create").on('click', ()=> {
                this.createRole();
            });
            $("#add-auth").on('click', ()=> {
                this.addAuth();
            });

            var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150 - 26;
            this.rowNum = parseInt("" + (maxTableBodyHeight / 28)) - 1;
            this.adjustHeader();
            this.updateUI();


        }

        private updateRoleSel() {
            $("#role-sel").empty();
            $("#role-sel").append(
                '<select class="selectpicker" multiple data-live-search="true" title="角色" ></select>');
            var sel = $("#role-sel select");
            for (var i = 0; i < this.mOpt.roles.length; ++i) {
                sel.append('<option value="' + this.mOpt.roles[i][0] + '">' + this.mOpt.roles[i][1] + '</option>');
            }
            $('#role-sel .selectpicker').selectpicker({
                // style: 'btn-info'
            });
        }

        private adjustHeader() {
            //$("#headerHost").removeCss("width");
            //if ($("#headerHost").height() > 40){
            //    $(".page-header").addClass("page-header-double");
            //    $("#headerHost").css("width", $("#comp-sel").width() + "px");
            //}else{
            //    $(".page-header").removeClass("page-header-double");
            //}
        }

        toIntArray(strArr) {
            var iarr = [];
            for (var i = 0; i < strArr.length; ++i) {
                iarr.push(parseInt(strArr[i]));
            }
            return iarr;
        }

        public updateUI() {
            var rids = $('#role-sel .selectpicker').selectpicker('val');
            var aids = $('#auth-sel .selectpicker').selectpicker('val');
            if (!rids) {
                rids = [-1];
            } else {
                rids = this.toIntArray(rids);
            }
            if (!aids) {
                aids = [-1];
            } else {
                aids = this.toIntArray(aids);
            }
            this.rids = rids;
            this.aids = aids;
            this.mUpdateAjax.post({
                    rids: JSON.stringify(rids),
                    aids: JSON.stringify(aids),
                    pgNum: 0,
                    pgSize: this.rowNum
                })
                .then((data:any) => {
                    this.mData = data;
                    this.updateTable();
                });
        }

        //
        private adjustSize() {
            var jqgrid = this.jqgrid();
            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }

            //let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
            //this.tableAssist && this.tableAssist.resizeHeight(maxTableBodyHeight);
            //
            //if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
            //    jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            //}
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
            parent.append("<table id='" + this.jqgridName() + "'></table><div id='pager'></div>");
            this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), (e, opt) => {
                // var text =
                var width = $(e).width();
                $(e).hide();
                var p = $(e).parent();

                p.append('<div style="position:absolute;z-index:30000;margin-top:-12px;">' +
                    '<select class="selectpicker" multiple data-live-search="true" title="请选择公司"></select>' +
                    '</div>');
                var sel = p.find('select');
                var oldComps = $(e).val().split(",");
                for (var i = 0; i < this.mOpt.comps.length; ++i) {
                    sel.append('<option value="' + this.mOpt.comps[i][1] + '">' + this.mOpt.comps[i][1] + '</option>');
                }

                var div = p.find('div');
                var compNames = oldComps;

                sel.on('changed.bs.select', () => {
                    compNames = p.find('.selectpicker').selectpicker('val');
                    $(e).val(compNames.join());
                });
                sel.selectpicker({
                    size: 10
                });
                sel.selectpicker('val', compNames);

                div.children().css("width", width + "px");
                div.children().eq(0).find("button").css("padding-top", "3px");
                div.children().eq(0).find("button").css("padding-bottom", "3px");
            });
            return this.tableAssist;
        }

        private totalCount(count) {
            var totalPage = count / this.rowNum;
            if (0 != this.mData.count % this.rowNum) {
                totalPage += 1;
            }
            totalPage = parseInt("" + totalPage);
            return totalPage;
        }

        private updateTable():void {
            this.createJqassist();


            this.tableAssist.create({
                assistData: this.mData.data,
                assistTotal: this.totalCount(this.mData.count),
                assistRecords: this.mData.count,
                assistPagedata: (postdata) => {
                    this.mUpdateAjax.post({
                            rids: JSON.stringify(this.rids),
                            aids: JSON.stringify(this.aids),
                            pgNum: postdata.page - 1,
                            pgSize: this.rowNum
                        })
                        .then((data:any) => {
                            this.mData = data;
                            this.tableAssist.addData(this.totalCount(this.mData.count), postdata.page, this.mData.count, this.mData.data);
                        });
                },
                cellEdit: true,
                cellsubmit: 'clientArray',
                multiselect: false,
                drag: false,
                resize: false,
                height: '100%',
                width: $("#" + this.mOpt.tableId).width(),
                shrinkToFit: true,
                assistEditable: true,
                autoScroll: true,
                rowNum: this.rowNum,
                viewrecords: true,
                nopagerbutton: true,
                pager: "#pager"
            });


            this.adjustSize();
        }


        private findId(idMap, val) {
            for (var i = 0; i < idMap.length; ++i) {
                if (idMap[i][1] == val) {
                    return idMap[i][0];
                }
            }
            return null;
        }

        private submitData():void {
            var data = this.tableAssist.getChangedData();
            var aids = [];
            var rids = [];
            var cids = [];
            for (var i = 0; i < data.length; ++i) {
                var rid = this.findId(this.mOpt.roles, data[i][1]);
                var aid = this.findId(this.mOpt.auths, data[i][2]);
                if (rid && aid) {
                    var compNames = data[i][3].split(",");
                    for (var j = 0; j < compNames.length; ++j) {
                        var cid = this.findId(this.mOpt.comps, compNames[j]);
                        if (cid != null) {
                            aids.push(aid);
                            rids.push(rid);
                            cids.push(cid);
                        }
                    }
                    if (compNames.length == 0) {
                        aids.push(aid);
                        rids.push(rid);
                        cids.push(null);
                    }
                }
            }

            if (rids.length > 0) {

                this.mSubmitAjax.post({
                        rids: JSON.stringify(rids),
                        cids: JSON.stringify(cids),
                        aids: JSON.stringify(aids),
                    })
                    .then((dataArray:any) => {
                        // this.updateUI();
                        this.tableAssist.resetChangedData();
                        Util.Toast.success('用户数据修改成功');
                    });
            } else {
                Util.Toast.warning('没有可修改的用户数据');
            }
        }

        private createRole():void {
            let dialog = bootbox.dialog({
                message: $("#createRoleTemplate").html().replace(/__/g, ""),
                title: "创建角色",
                className: "modal-darkorange",
                buttons: {
                    success: {
                        label: "确定",
                        className: "btn-blue",
                        callback: () => {
                            let rName = $("#roleName").val();
                            if (!rName) {
                                $("#warning").text("角色不能为空").show();
                                return false;
                            }

                            this.mCreateRoleAjax.post({
                                rName: rName
                            }).then((ret:any)=> {
                                if (ret.errorCode == 0) {
                                    dialog.modal("hide");
                                    Util.Toast.success("角色创建成功");
                                    this.mGetRoleName.post({}).then((roles:any)=> {
                                        this.mOpt.roles = roles.roles;
                                        this.updateRoleSel();
                                    });
                                } else {
                                    $("#warning").text("角色已经存在，不可重复添加").show();
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


            $(".role_drop>div").addClass("col-md-12");
        }

        private addAuth():void {
            let dialog = bootbox.dialog({
                message: $("#addAuthTemplate").html().replace(/__/g, ""),
                title: "添加权限",
                className: "modal-darkorange",
                buttons: {
                    success: {
                        label: "确定",
                        className: "btn-blue",
                        callback: () => {
                            let rid = $("#roleName .selectpicker").val();
                            let aid = $("#authName .selectpicker").val();
                            let cids = $("#compName .selectpicker").val();
                            if (!rid || !aid) {
                                $("#warning").text("角色、权限不能为空").show();
                                return false;
                            }
                            if (!cids) {
                                cids = [];
                            }

                            this.mAddAuthAjax.post({
                                rid: rid,
                                aid: aid,
                                cids: JSON.stringify(this.toIntArray(cids))
                            }).then((ret:any)=> {
                                if (ret.errorCode == 0) {
                                    dialog.modal("hide");
                                    Util.Toast.success("角色权限添加成功");
                                    let pgNum = this.tableAssist.getCurrentPageNumber();
                                    this.mUpdateAjax.post({
                                            rids: JSON.stringify(this.rids),
                                            aids: JSON.stringify(this.aids),
                                            pgNum: pgNum - 1,
                                            pgSize: this.rowNum
                                        })
                                        .then((data:any) => {
                                            this.mData = data;
                                            this.tableAssist.addData(this.totalCount(this.mData.count), pgNum, this.mData.count, this.mData.data);
                                        });


                                } else {
                                    $("#warning").text("角色权限组合已经存在，不可重复添加").show();
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

            $("#roleName").append(
                '<select class="selectpicker" data-live-search="true" title="角色"></select>');
            var sel = $("#roleName select");
            for (var i = 0; i < this.mOpt.roles.length; ++i) {
                sel.append('<option value="' + this.mOpt.roles[i][0] + '">' + this.mOpt.roles[i][1] + '</option>');
            }
            $('#roleName .selectpicker').selectpicker({
                // style: 'btn-info'
            });

            $("#authName").append(
                '<select class="selectpicker" data-live-search="true"  title="权限"></select>');
            var sel = $("#authName select");
            for (var i = 0; i < this.mOpt.auths.length; ++i) {
                sel.append('<option value="' + this.mOpt.auths[i][0] + '">' + this.mOpt.auths[i][1] + '</option>');
            }
            $('#authName .selectpicker').selectpicker({
                // style: 'btn-info'
            });

            $("#compName").append(
                '<select class="selectpicker" multiple data-live-search="true"  title="公司"></select>');
            var sel = $("#compName select");
            for (var i = 0; i < this.mOpt.comps.length; ++i) {
                sel.append('<option value="' + this.mOpt.comps[i][0] + '">' + this.mOpt.comps[i][1] + '</option>');
            }
            $('#compName .selectpicker').selectpicker({
                // style: 'btn-info'
            });

            $(".role_drop>div").addClass("col-md-12");
        }
    }
}