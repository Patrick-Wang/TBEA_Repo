///<reference path="../framework/route/route.ts"/>
///<reference path="../framework/basic/basicdef.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="../framework/templates/singleDateReport/show.ts"/>
module rzywtj {
    import router = framework.router;
    import Node = JQTable.Node;
    import BasicEndpoint = framework.basic.BasicEndpoint;
    import FrameEvent = framework.basic.FrameEvent;
    import UnitedSelector = Util.UnitedSelector;
    import IDataNode = Util.IDataNode;


    export let FE_SOLVED:number = framework.route.nextId();

    export function create() : framework.templates.singleDateReport.ShowView {
        return new ItemShowView();
    }

    interface ShowOption extends framework.templates.singleDateReport.ShowOption{
        itemNodes:IDataNode[];
        itemId:string;
        itemNodes2:IDataNode[];
        itemId2:string;
        submitUrl:string;
    }

    class ItemShowView extends framework.templates.singleDateReport.ShowView{
        unitedSelector : Util.UnitedSelector;
        unitedSelector2 : Util.UnitedSelector;
        doubleHeader : boolean = false;
        mAjaxSubmit:Util.Ajax;
        item = 0;
        onInitialize(opt:ShowOption):void{
            this.mAjaxSubmit = new Util.Ajax(opt.submitUrl, false);

            this.unitedSelector = new Util.UnitedSelector(opt.itemNodes,opt.itemId);
            this.unitedSelector.change(()=>{
                this.adjustHeader();
                this.item = this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id;
                if (this.item == 0){
                    $("#grid-date").parent().removeClass("hidden");
                }else{
                    $("#grid-date").parent().addClass("hidden");
                }
            });

            this.item = this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id;
            if (this.item == 0){
                $("#grid-date").parent().removeClass("hidden");
            }else{
                $("#grid-date").parent().addClass("hidden");
            }
            let itemHidden = false;
            if (opt.itemNodes.length == 1){
                $("#" + opt.itemId).hide();
                itemHidden = true;
            }


            let item2Hidden = false;
            if (opt.itemNodes2 != undefined){
                this.unitedSelector2 = new Util.UnitedSelector(opt.itemNodes2,opt.itemId2);
                this.unitedSelector2.change(()=>{
                    this.adjustHeader();
                    this.item = this.unitedSelector2.getDataNode(this.unitedSelector2.getPath()).data.id;
                    if (this.item == 13 || this.item == 14){
                        this.unitedSelector.hide();
                        $("#grid-solve").css("display", "none");
                    }else{
                        this.unitedSelector.show();
                        $("#grid-solve").removeCss("display");
                    }



                });
                if (opt.itemNodes2.length == 1){
                    $("#" + opt.itemId2).hide();
                    item2Hidden = true;
                }
            }


            if (item2Hidden && itemHidden && $("#" + opt.dtId).hasClass("hidden")){
                $("#sels").hide();
                $("#" + opt.dtId).hide();
            }



            $(window).resize(()=>{
                this.adjustHeader();
            });

            super.onInitialize(opt);


        }

        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case FE_SOLVED:
                    this.mAjaxSubmit.post($.extend({
                        data : JSON.stringify(this.mTableAssist.getAllData())
                    }, this.getParams(this.getUDate())))
                        .then((jsonData:any) => {
                            let resp = jsonData;
                            if (Util.ErrorCode.OK == resp.errorCode) {
                                Util.Toast.success("处理成功");
                                this.updateStatus();
                            } else {
                                Util.Toast.failed(resp.message);
                            }
                        });
            }
            return super.onEvent(e);
        }


        getParams(date:Util.Date):any{
            return {
                date: this.getDate(date),
                item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id,
                item2: this.unitedSelector2 ? this.unitedSelector2.getDataNode(this.unitedSelector2.getPath()).data.id : undefined
            };
        }

        adjustHeader(){
            $("#headerHost").removeCss("width");
            if ($("#headerHost").height() > 40){
                $(".page-header").addClass("page-header-double");
                $("#headerHost").css("width", $("#sels").width() + "px");
                if (!this.doubleHeader){
                    this.doubleHeader = true;
                    return true;
                }
            }else{
                $(".page-header").removeClass("page-header-double");
                if (this.doubleHeader){
                    this.doubleHeader = false;
                    return true;
                }
            }
            return false;
        }

        update (date:Util.Date){
            this.mAjaxUpdate.get(this.getParams(date))
                .then((jsonData:any) => {
                    this.resp = jsonData;
                    this.updateTable();
                });
        }

        updateColor(){
            //for (var i = 0; i < this.resp.data.length; ++i) {
            //    let dt = this.resp.data[i][this.resp.data[i].length - 2];
            //    if (dt && this.mTableAssist.getCellValue(this.mTableAssist.getRid(i), this.resp.data[i].length - 1) == 'N'){
            //        let deadline = Util.toDate(dt);
            //        let dtNow = Util.now();
            //        let dDeadline = new Date(deadline.year, deadline.month, deadline.day);
            //        let dNow = new Date(dtNow.year, dtNow.month, dtNow.day);
            //        if (dDeadline.getTime() <= dNow.getTime()){
            //            this.mTableAssist.updateRowBgColor(this.mTableAssist.getRid(i), 237, 28, 36);
            //            continue;
            //        }
            //    }
            //    this.mTableAssist.updateRowBgColor(this.mTableAssist.getRid(i));
            //}
        }

        updateRowColor(rowid){
            let dt = this.mTableAssist.getCellValue(rowid, this.resp.data[0].length - 2);
            if (dt && this.mTableAssist.getCellValue(rowid, this.resp.data[0].length - 1) == 'N'){
                let deadline = Util.toDate(dt);
                let dtNow = Util.now();
                let dDeadline = new Date(deadline.year, deadline.month, deadline.day);
                let dNow = new Date(dtNow.year, dtNow.month, dtNow.day);
                if (dDeadline.getTime() <= dNow.getTime()){
                    this.mTableAssist.updateRowBgColor(rowid, 237, 28, 36);
                    return;
                }
            }
            this.mTableAssist.updateRowBgColor(rowid);
        }

        updateStatus(){
            let sels = this.mTableAssist.getSelection();
            for (var i = 0; i < this.resp.data.length; ++i) {
                if (this.resp.data[i][this.resp.data[i].length - 1] == 'Y'){
                    var isSeled = false;
                    $(sels).each((j, e)=>{
                       if (e == this.mTableAssist.getRid(i)){
                           isSeled = true;
                           return false;
                       }
                    });
                    if (!isSeled){
                        this.mTableAssist.setSelection(this.mTableAssist.getRid(i));
                    }
                }
            }
        }

        updateTable():void {
            if (this.resp.data.length == 0){
                $("#tip").removeClass("hidden");
                $("#" + this.opt.host).hide();
                $("#grid-solve").addClass("hidden");

            }else{
                $("#tip").addClass("hidden");
                $("#grid-solve").removeClass("hidden");
                $("#" + this.opt.host).show();

                this.createJqassist();

                let data = {

                };
                if (this.item != 13 && this.item != 14 && this.item != 0) {
                    data.dataWithId = this.resp.data;
                }else {
                    data.data = this.resp.data;
                }

                this.mTableAssist.create( $.extend({
                    datatype: "local",
                   // multiselect: this.item != 13 && this.item != 14,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: $("#" + this.opt.host).width(),
                    shrinkToFit: this.resp.shrinkToFit == "false" ? false : true,
                    rowNum: 10000,
                    autoScroll: true
                    //onSelectRow: (rowid, status)=>{
                    //    if (this.item != 13 && this.item != 14){
                    //        if (status){
                    //            this.mTableAssist.setCellValue(rowid, this.resp.data[0].length - 1, 'Y');
                    //        }else{
                    //            this.mTableAssist.setCellValue(rowid, this.resp.data[0].length - 1, 'N');
                    //        }
                    //        this.updateRowColor(rowid);
                    //    }
                    //},
                    //onSelectAll: (rowids, status)=>{
                    //    if (this.item != 13 && this.item != 14) {
                    //        if (status) {
                    //            for (let i = 0; i < rowids.length; ++i) {
                    //                this.mTableAssist.setCellValue(rowids[i], this.resp.data[0].length - 1, 'Y');
                    //            }
                    //        } else {
                    //            for (let i = 0; i < rowids.length; ++i) {
                    //                this.mTableAssist.setCellValue(rowids[i], this.resp.data[0].length - 1, 'N');
                    //            }
                    //        }
                    //
                    //        this.updateColor();
                    //    }
                    //}
                }, data));
                if (this.item != 13 && this.item != 14) {
                    this.updateStatus();
                    this.updateColor();
                }
                this.adjustSize();
            }
        }

    }
}