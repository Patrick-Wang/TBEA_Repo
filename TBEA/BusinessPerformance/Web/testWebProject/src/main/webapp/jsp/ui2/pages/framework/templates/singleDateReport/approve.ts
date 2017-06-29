///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
///<reference path="../../../messageBox.ts"/>
///<reference path="../../../components/dateSelectorProxy.ts"/>
module framework.templates.singleDateReport {
    import router = framework.router;
    import Node = JQTable.Node;
    import BasicEndpoint = framework.basic.BasicEndpoint;
    import FrameEvent = framework.basic.FrameEvent;

    export function createInstance() : ApproveView{
        return new ApproveView();
    }

    export interface ApproveOption{
        updateUrl:string;
        approveUrl:string;
        unapproveUrl:string;
        host:string;
        date:Util.Date;
        dtId:string;
    }

    export class ApproveView extends BasicEndpoint{
        //dateSelect : Util.DateSelectorProxy;
        mAjaxUpdate:Util.Ajax;
        mAjaxApprove:Util.Ajax;
        mAjaxUnApprove:Util.Ajax;
        mTableAssist:JQTable.JQGridAssistant;
        resp:Util.ServResp;
        opt:ApproveOption;
        getId():number{
            return framework.basic.endpoint.FRAME_ID;
        }

        onInitialize(opt:any):void{
            this.opt = opt;
            this.mAjaxUpdate = new Util.Ajax(opt.updateUrl, false);
            this.mAjaxApprove = new Util.Ajax(opt.approveUrl, false);
            this.mAjaxUnApprove = new Util.Ajax(opt.unapproveUrl, false);

            if (opt.date == undefined){
                $("#" + opt.dtId).hide();
                this.update(<Util.Date>({}));
            }else {
                //this.dateSelect = new Util.DateSelectorProxy(opt.dtId, {
                //    year: opt.date.year - 3,
                //    month: opt.date.month,
                //    day: opt.date.day
                //}, opt.date, opt.date);

                let seasonClass = "";
                let fmt = "YYYY年MM月";
                if (opt.asSeason){
                    fmt = "YYYY年 && $$MM月";
                    seasonClass = "season-month";
                }else if(opt.asSeasonAcc){
                    fmt = "YYYY年 &&MM月";
                    seasonClass = "season";
                }

                $("#" + this.opt.dtId).jeDate({
                    skinCell: "jedatedeepgreen",
                    format: fmt,
                    isTime: false,
                    isinitVal: true,
                    isClear: false,
                    isToday: false,
                    minDate: Util.date2Str(Util.addYear(opt.date, -3)),
                    maxDate: Util.date2Str(opt.dateEnd),
                    seasonText : opt.jdName ? opt.jdName : undefined
                }).removeCss("height")
                    .removeCss("padding")
                    .removeCss("margin-top")
                    .addClass(seasonClass);
                this.update(this.getUDate());
            }


        }

        protected getUDate():Util.Date {
            let ret : any = {};
            if (this.opt.date){
                let curDate = $("#" + this.opt.dtId).getDate();
                ret = {
                    year : curDate.getFullYear(),
                    month : curDate.getMonth() + 1,
                    day : curDate.getDate()
                };
            }
            return ret;
        }

        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case FrameEvent.FE_UPDATE:
                    if (this.opt.date == undefined){
                        return this.update(<Util.Date>({}));
                    }else{
                        return this.update(this.getUDate());
                    }

                case FrameEvent.FE_APPROVE:
                    if (this.opt.date == undefined){
                        return this.approve(<Util.Date>({}));
                    }else{
                        return this.approve(this.getUDate());
                    }
                case FrameEvent.FE_UNAPPROVE:
                    if (this.opt.date == undefined){
                        return this.unapprove(<Util.Date>({}));
                    }else{
                        return this.unapprove(this.getUDate());
                    }
            }
            return super.onEvent(e);
        }

        getDate(date:Util.Date):string{
            return "" + (date.year + "-" + (date.month == undefined ? 1 :date.month) + "-" + (date.day == undefined ? 1 :date.day));
        }

        getParams(date:Util.Date):any{
            return {
                date: this.getDate(date)
            };
        }

        update (date:Util.Date){
            this.mAjaxUpdate.get(this.getParams(this.getUDate()))
                .then((jsonData:any) => {
                    this.resp = jsonData;
                    if (jsonData.zt == 0){
                        $("#approve").hide();
                        $("#unapprove").hide();
                    }
                    else if (jsonData.zt == 1){
                        $("#approve").hide();
                        $("#unapprove").show();
                    }
                    else if (jsonData.zt == 2){
                        $("#approve").show();
                        $("#unapprove").hide();
                    }
                    this.updateTable();
                });
        }

        adjustSize() {
            var jqgrid = this.jqgrid();
            if ($("#" + this.opt.host).width() != $("#" + this.opt.host + " .ui-jqgrid").width()) {
                jqgrid.setGridWidth($("#" + this.opt.host).width());
            }

            let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
            this.mTableAssist.resizeHeight(maxTableBodyHeight);

            if ($("#" + this.opt.host).width() != $("#" + this.opt.host + " .ui-jqgrid").width()) {
                jqgrid.setGridWidth($("#" + this.opt.host).width());
            }
        }

        jqgrid(){
            return $("#" + this.jqgridName());
        }

        jqgridName():string{
            return this.opt.host + "_jqgrid_real";
        }

        createJqassist():JQTable.JQGridAssistant{
            var parent = $("#" + this.opt.host);
            var pagername = this.jqgridName() + "pager";
            parent.empty();
            parent.append("<table id='"+ this.jqgridName() +"'><div id='" + pagername + "'></table>");
            this.mTableAssist = Util.createTable(this.jqgridName(), this.resp);
            return this.mTableAssist;
        }

        updateTable():void {
            this.createJqassist();

            this.mTableAssist.create({
                dataWithId: this.resp.data,
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: '100%',
                width: $("#" + this.opt.host).width(),
                shrinkToFit: this.resp.shrinkToFit == "false" ? false : true,
                rowNum: 2000,
                autoScroll: true
            });

            this.adjustSize();
        }

        //updateTable():void {
        //    var name = this.opt.host + "_jqgrid_uiframe";
        //    var pagername = name + "pager";
        //    this.mTableAssist = Util.createTable(name, this.resp);
        //
        //    var parent = $("#" + this.opt.host);
        //    parent.empty();
        //    parent.append("<table id='" + name + "'></div>");
        //    let jqTable = $("#" + name);
        //    let opt = {
        //        datatype: "local",
        //        data: this.mTableAssist.getDataWithId(this.resp.data),
        //        multiselect: false,
        //        drag: false,
        //        resize: false,
        //        assistEditable:true,
        //        //autowidth : false,
        //        cellsubmit: 'clientArray',
        //        //editurl: 'clientArray',
        //        cellEdit: false,
        //        // height: data.length > 25 ? 550 : '100%',
        //        // width: titles.length * 200,
        //        rowNum: 1000,
        //        height: '100%',
        //        width: 1200,
        //        shrinkToFit: true,
        //        autoScroll: true,
        //       // pager: '#' + pagername,
        //       // viewrecords: true
        //    };
        //    //if (this.resp.pager == 'none'){
        //    //    opt.pager = undefined;
        //    //}
        //    jqTable.jqGrid(this.mTableAssist.decorate(opt));
        //}

        onLoadSubmitData() : any{
            return this.mTableAssist.getAllData();
        }

        approve(date:Util.Date): void{
            this.mAjaxApprove.get(
                $.extend(this.getParams(this.getUDate()), {
                    data : JSON.stringify(this.onLoadSubmitData())
                }))
            .then((resp:Util.IResponse) => {
                if (Util.ErrorCode.OK == resp.errorCode) {
                    this.update(date);
                    Util.Toast.success("审核 成功");
                } else {
                    Util.Toast.failed(resp.message);
                }
            });
        }

        unapprove(date:Util.Date){
            this.mAjaxUnApprove.get($.extend(this.getParams(this.getUDate()), {
                    data : JSON.stringify(this.onLoadSubmitData())
                }))
                .then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                    this.update(date);
                    Util.Toast.success("反审核 成功");
                } else {
                    Util.Toast.failed(resp.message);
                }
            });
        }
    }
}