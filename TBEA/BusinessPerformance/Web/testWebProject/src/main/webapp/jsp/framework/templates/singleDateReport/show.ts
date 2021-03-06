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

    export function createInstance() : ShowView{
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
        dateSelect : Util.DateSelectorProxy;
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
                this.dateSelect = new Util.DateSelectorProxy(opt.dtId, {
                    year:opt.date.year - 3,
                    month:opt.date.month,
                    day:opt.date.day
                }, opt.dateEnd, opt.date, opt.asSeason, opt.asSeasonAcc, opt.jdName);
                this.update(this.dateSelect.getDate());
            }



        }

        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case FE_UPDATE:
                    if (this.dateSelect == undefined){
                        return this.update(<Util.Date>({}));
                    }else{
                        return this.update(this.dateSelect.getDate());
                    }
                case FE_EXPORTEXCEL:
                    if (this.dateSelect == undefined){
                        return this.exportExcel(<Util.Date>({}), e.data);
                    }else{
                        return this.exportExcel(this.dateSelect.getDate(), e.data);
                    }
            }
            return super.onEvent(e);
        }

        getDate(date:Util.Date):string{
            return "" + (date.year + "-" + (date.month == undefined ? 1 :date.month) + "-" + (date.day == undefined ? 1 :date.day));
        }

        update (date:Util.Date){
            this.mAjaxUpdate.get({
                    date: this.getDate(date)
                })
                .then((jsonData:any) => {
                    this.resp = jsonData;
                    this.updateTable();
                });
        }

        updateTable():void {
            var name = this.opt.host + "_jqgrid_uiframe";
            var pagername = name + "pager";
            this.mTableAssist = Util.createTable(name, this.resp);


            if (this.resp.colorKey){
                for (var i = 0; i < this.resp.data.length; ++i) {
                    if (this.resp.data[i][0].lastIndexOf(this.resp.colorKey) >= 0) {
                        this.mTableAssist.setRowBgColor(i, 183, 222, 232);
                    }
                }
            }


            var parent = $("#" + this.opt.host);
            parent.empty();
            parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
            let jqTable = $("#" + name);
            jqTable.jqGrid(
                this.mTableAssist.decorate({
                    datatype: "local",
                    data: this.mTableAssist.getData(this.resp.data),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    assistEditable:false,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    //editurl: 'clientArray',
                    cellEdit: false,
                    // height: data.length > 25 ? 550 : '100%',
                    // width: titles.length * 200,
                    rowNum: 1000,
                    height: this.resp.data.length > 25 ? 550 : '100%',
                    width: this.resp.width == undefined ? 1300 : this.resp.width,
                    shrinkToFit: true,
                    autoScroll: true
                }));
        }

        exportExcel(date:Util.Date, id:string): void {
            $("#" + id)[0].action = this.opt.exportUrl + "?" +  Util.Ajax.toUrlParam({
                date: this.getDate(date)
            });
            $("#" + id)[0].submit();
        }
    }
}