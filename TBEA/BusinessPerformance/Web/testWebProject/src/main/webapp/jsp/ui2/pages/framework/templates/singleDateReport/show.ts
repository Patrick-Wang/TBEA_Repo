///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
///<reference path="../../basic/basicShow.ts"/>
///<reference path="../../../messageBox.ts"/>
///<reference path="../../../components/dateSelectorProxy.ts"/>
module framework.templates.singleDateReport {
    import router = framework.router;
    import Node = JQTable.Node;
    import BasicEndpoint = framework.basic.BasicEndpoint;
    import FrameEvent = framework.basic.FrameEvent;

    export let FRAME_ID:number = framework.route.nextId();
    export let FE_UPDATE:number = framework.route.nextId();
    export let FE_EXPORTEXCEL:number = framework.route.nextId();
    export let FE_INIT_EVENT:number = framework.route.nextId();

    export function create() : ShowView{
        return new ShowView();
    }

    export interface ShowOption{
        updateUrl:string;
        exportUrl:string;
        host:string;
        date:Util.Date;
        dtId:string;
        dateEnd:Util.Date;
        asSeason:boolean;
        asSeasonAcc:boolean;
        jdName:string[];
    }



    export class ShowView extends BasicEndpoint{
        mAjaxUpdate:Util.Ajax;
        mAjaxExport:Util.Ajax;
        mTableAssist:JQTable.JQGridAssistant;
        resp:Util.ServResp;
        opt:ShowOption;
        getId():number{
            return FRAME_ID;
        }

        onInitialize(opt:ShowOption):void{
            this.opt = opt;
            this.mAjaxUpdate = new Util.Ajax(opt.updateUrl, false);
            this.mAjaxExport = new Util.Ajax(opt.exportUrl, false);
            if (opt.date == undefined){
                $("#" + opt.dtId).hide();
                this.update(<Util.Date>({}));
            }else{
                if (opt.dateEnd == undefined){
                    opt.dateEnd = $.extend({}, opt.date);
                }

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
                $(window).resize(()=>{
                    this.adjustSize();
                });
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
                case FE_UPDATE:
                    if (this.opt.date == undefined){
                        return this.update(<Util.Date>({}));
                    }else{
                        return this.update(this.getUDate());
                    }
                case FE_EXPORTEXCEL:
                    if (this.opt.date == undefined){
                        return this.exportExcel(<Util.Date>({}), e.data);
                    }else{
                        return this.exportExcel(this.getUDate(), e.data);
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

            if (this.resp.colorKey){
                for (var i = 0; i < this.resp.data.length; ++i) {
                    if (this.resp.data[i][0].lastIndexOf(this.resp.colorKey) >= 0) {
                        this.mTableAssist.setRowBgColor(i, 183, 222, 232);
                    }
                }
            }

            this.mTableAssist.create({
                data: this.resp.data,
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

        exportExcel(date:Util.Date, id:string): void {
            $("#" + id)[0].action = this.opt.exportUrl + "?" +  Util.Ajax.toUrlParam(this.getParams(this.getUDate()));
            $("#" + id)[0].submit();
        }
    }
}