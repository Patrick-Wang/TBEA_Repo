///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
///<reference path="../../../messageBox.ts"/>
///<reference path="../../../components/dateSelectorProxy.ts"/>
module framework.templates.singleDateReport {
    import router = framework.router;
    import Node = JQTable.Node;
    import BasicEndpoint = framework.basic.BasicEndpoint;
    import FrameEvent = framework.basic.FrameEvent;

    export function createInstance() : EntryView{
        return new EntryView();
    }

    export interface EntryOption{
        updateUrl:string;
        submitUrl:string;
        host:string;
        date:Util.Date;
        dtId:string;
        dateEnd:Util.Date;
        asSeason:boolean;
        asSeasonAcc:boolean;
        jdName:string[];
    }

    export class EntryView extends BasicEndpoint{
        //dateSelect : Util.DateSelectorProxy;
        mAjaxUpdate:Util.Ajax;
        mAjaxSubmit:Util.Ajax;
        mTableAssist:JQTable.JQGridAssistant;
        resp:Util.ServResp;
        opt:EntryOption;
        getId():number{
            return framework.basic.endpoint.FRAME_ID;
        }

        onInitialize(opt:any):void{
            this.opt = opt;
            this.mAjaxUpdate = new Util.Ajax(opt.updateUrl, false);
            this.mAjaxSubmit = new Util.Ajax(opt.submitUrl, false);
            if (opt.date == undefined){
                $("#" + opt.dtId).hide();
                this.update(<Util.Date>({}));
            }else {
                if (opt.dateEnd == undefined){
                    opt.dateEnd = $.extend({}, opt.date);
                }
                //this.dateSelect = new Util.DateSelectorProxy(opt.dtId, {
                //    year:opt.date.year - 3,
                //    month:opt.date.month,
                //    day:opt.date.day
                //}, opt.dateEnd, opt.date, opt.asSeason, opt.asSeasonAcc, opt.jdName);

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

                case FrameEvent.FE_SUBMIT:
                    if (this.opt.date == undefined){
                        return this.submit(<Util.Date>({}));
                    }else{
                        return this.submit(this.getUDate());
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

            let opt = {
                datatype: "local",
                dataWithId : this.resp.data,
                multiselect: false,
                drag: false,
                resize: false,
                assistEditable:true,
                //autowidth : false,
                cellsubmit: 'clientArray',
                //editurl: 'clientArray',
                cellEdit: true,
                // height: data.length > 25 ? 550 : '100%',
                // width: titles.length * 200,
                rowNum: 15,
                height: '100%',
                width: $("#" + this.opt.host).width(),
                shrinkToFit: true,
                autoScroll: true,
                pager: '#' + this.jqgridName() + "pager",
                viewrecords: true
            };
            if (this.resp.pager == 'none'){
                opt.pager = undefined;
                opt.rowNum = 2000;
            }
            this.mTableAssist.create(opt);
            this.adjustSize();
        }

        //updateTable():void {
        //    var name = this.opt.host + "_jqgrid_uiframe";
        //    var pagername = name + "pager";
        //    this.mTableAssist = Util.createTable(name, this.resp);
        //
        //    var parent = $("#" + this.opt.host);
        //    parent.empty();
        //    parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
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
        //        cellEdit: true,
        //        // height: data.length > 25 ? 550 : '100%',
        //        // width: titles.length * 200,
        //        rowNum: 20,
        //        height: '100%',
        //        width: 1200,
        //        shrinkToFit: true,
        //        autoScroll: true,
        //        pager: '#' + pagername,
        //        viewrecords: true
        //    };
        //    if (this.resp.pager == 'none'){
        //        opt.pager = undefined;
        //        opt.rowNum = 1000;
        //    }
        //    jqTable.jqGrid(this.mTableAssist.decorate(opt));
        //}

        onLoadSubmitData() : any{
            if (this.resp.pager == 'none'){
                return this.mTableAssist.getAllData();
            }
            return this.mTableAssist.getChangedData();
        }

        submit(date:Util.Date): void{
            this.mAjaxSubmit.get(
                $.extend(this.getParams(this.getUDate()), {
                    data : JSON.stringify(this.onLoadSubmitData())
                }))
            .then((resp:Util.IResponse) => {
                if (Util.ErrorCode.OK == resp.errorCode) {
                    Util.Toast.success("保存 成功");
                } else {
                    Util.Toast.failed(resp.message);
                }
            });
        }
    }
}