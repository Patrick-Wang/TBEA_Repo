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
    }

    export class EntryView extends BasicEndpoint{
        dateSelect : Util.DateSelectorProxy;
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
                this.dateSelect = new Util.DateSelectorProxy(opt.dtId, {
                    year: opt.date.year - 3,
                    month: opt.date.month,
                    day: opt.date.day
                }, opt.date, opt.date);
                this.update(this.dateSelect.getDate());
            }


        }

        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case FrameEvent.FE_UPDATE:
                    if (this.dateSelect == undefined){
                        return this.update(<Util.Date>({}));
                    }else{
                        return this.update(this.dateSelect.getDate());
                    }

                case FrameEvent.FE_SUBMIT:
                    if (this.dateSelect == undefined){
                        return this.submit(<Util.Date>({}));
                    }else{
                        return this.submit(this.dateSelect.getDate());
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

            var parent = $("#" + this.opt.host);
            parent.empty();
            parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
            let jqTable = $("#" + name);
            let opt = {
                datatype: "local",
                data: this.mTableAssist.getDataWithId(this.resp.data),
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
                rowNum: 20,
                height: '100%',
                width: 1200,
                shrinkToFit: true,
                autoScroll: true,
                pager: '#' + pagername,
                viewrecords: true
            };
            if (this.resp.pager == 'none'){
                opt.pager = undefined;
                opt.rowNum = 1000;
            }
            jqTable.jqGrid(this.mTableAssist.decorate(opt));
        }

        onLoadSubmitData() : any{
            if (this.resp.pager == 'none'){
                return this.mTableAssist.getAllData();
            }
            return this.mTableAssist.getChangedData();
        }

        submit(date:Util.Date): void{
            this.mAjaxSubmit.get({
                data : JSON.stringify(this.onLoadSubmitData()),
                date:this.getDate(date)
                })
            .then((resp:Util.IResponse) => {
                if (Util.ErrorCode.OK == resp.errorCode) {
                    this.update(date);
                    Util.MessageBox.tip("保存 成功");
                } else {
                    Util.MessageBox.tip(resp.message);
                }
            });
        }
    }
}