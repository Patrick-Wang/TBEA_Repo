/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
declare var echarts;

module user_mgr {

    import Endpoint = framework.route.Endpoint;
    import router = framework.router;


    class JQGridAssistantFactory {

        public static createTable(gridName: string, dataInit : any): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                JQTable.Node.create({
                    name : "用户名",
                    align : JQTable.TextAlign.Left,
                    width : 150}),
                JQTable.Node.create({
                    name: "角色",
                    align : JQTable.TextAlign.Left,
                    isReadOnly: false,
                    isNumber:false,
                    editType:'text',
                    options:{ dataInit : dataInit}
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

        private mOpt: IViewOption;
        private mData:Array<string[]> = [];
        private tableAssist:JQTable.JQGridAssistant;
        private mUpdateAjax:Util.Ajax;// = new Util.Ajax("/BusinessManagement/ydzb/hzb_companys_update.do");
        private mSubmitAjax:Util.Ajax;// = new Util.Ajax("/BusinessManagement/ydzb/hzb_companys_update.do");

        public init(opt:any):void {
            this.mOpt = opt;
            this.mUpdateAjax = new Util.Ajax(opt.updateUrl);
            this.mSubmitAjax = new Util.Ajax(opt.submitUrl);

            $("#user-sel").append(
                '<select class="selectpicker" multiple data-live-search="true" ></select>');
            var sel = $("#user-sel select");
            for (var i = 0; i < opt.users.length; ++i){
                if (i == 0){
                    sel.append('<option value="' + opt.users[i][0] + '" selected="selected">' + opt.users[i][1] + '</option>');
                }else{
                    sel.append('<option value="' + opt.users[i][0] + '">' + opt.users[i][1] + '</option>');
                }

            }
            $('#user-sel .selectpicker').selectpicker({
               // style: 'btn-info'
            });

            $("#grid-update").on('click', ()=>{
               this.updateUI();
            });

            $("#grid-submit").on('click', ()=>{
                this.submitData();
            });

            this.adjustHeader();
            this.updateUI();


        }

        private adjustHeader(){
            //$("#headerHost").removeCss("width");
            //if ($("#headerHost").height() > 40){
            //    $(".page-header").addClass("page-header-double");
            //    $("#headerHost").css("width", $("#comp-sel").width() + "px");
            //}else{
            //    $(".page-header").removeClass("page-header-double");
            //}
        }



        public updateUI() {
            var uids = $('#user-sel .selectpicker').selectpicker('val');
            var iUids = [];
            for (var i = 0; i < uids.length; ++i){
                iUids.push(parseInt(uids[i]));
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

        private jqgrid(){
            return $("#" + this.jqgridName());
        }

        private jqgridName():string{
            return this.mOpt.tableId + "_jqgrid_real";
        }

        private createJqassist():JQTable.JQGridAssistant{
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='"+ this.jqgridName() +"'></table>");
            this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), (e, opt) => {
              // var text =
                var width = $(e).width();
                $(e).hide();
                var p = $(e).parent();

                p.append('<div style="position:absolute;z-index:30000;margin-top:-12px;"><select class="selectpicker" multiple data-live-search="true" ></select></div>');
                var sel =  p.find('select');
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
                    $(e).val(roleNames);
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
                cellEdit:true,
                cellsubmit: 'clientArray',
                multiselect: false,
                drag: false,
                resize: false,
                height: '100%',
                width: $("#" + this.mOpt.tableId).width(),
                shrinkToFit: true,
                rowNum: 2000,
                assistEditable:true,
                autoScroll: true
            });


            this.adjustSize();
        }


        private submitData():void {
            var data = this.tableAssist.getChangedData();
            var users = [];
            var roles = [];
            for (var i = 0; i < data.length; ++i){
                var uid = parseInt(data[i][0]);
                var role = data[i][2].split(",");
                for (var j = 0; j < role.length; ++j){
                    roles.push(role[j]);
                    users.push(uid);
                }
            }

            if (users.length > 0){

                this.mSubmitAjax.post({
                    users: JSON.stringify(users),
                    roles: JSON.stringify(roles)})
                    .then((dataArray:any) => {
                       // this.updateUI();
                        this.tableAssist.resetChangedData();
                        Util.Toast.success('用户数据修改成功');
                    });
            }else{
                Util.Toast.warning('没有可修改的用户数据');
            }
        }
    }
}