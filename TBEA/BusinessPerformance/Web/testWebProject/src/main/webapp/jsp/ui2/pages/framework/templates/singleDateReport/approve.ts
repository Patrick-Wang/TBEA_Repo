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
        dateSelect : Util.DateSelectorProxy;
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

                case FrameEvent.FE_APPROVE:
                    if (this.dateSelect == undefined){
                        return this.approve(<Util.Date>({}));
                    }else{
                        return this.approve(this.dateSelect.getDate());
                    }
                case FrameEvent.FE_UNAPPROVE:
                    if (this.dateSelect == undefined){
                        return this.unapprove(<Util.Date>({}));
                    }else{
                        return this.unapprove(this.dateSelect.getDate());
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
                cellEdit: false,
                // height: data.length > 25 ? 550 : '100%',
                // width: titles.length * 200,
                rowNum: 1000,
                height: '100%',
                width: 1200,
                shrinkToFit: true,
                autoScroll: true,
               // pager: '#' + pagername,
               // viewrecords: true
            };
            //if (this.resp.pager == 'none'){
            //    opt.pager = undefined;
            //}
            jqTable.jqGrid(this.mTableAssist.decorate(opt));
        }

        onLoadSubmitData() : any{
            return this.mTableAssist.getAllData();
        }

        approve(date:Util.Date): void{
            this.mAjaxApprove.get({
                data : JSON.stringify(this.onLoadSubmitData()),
                date:this.getDate(date)
                })
            .then((resp:Util.IResponse) => {
                if (Util.ErrorCode.OK == resp.errorCode) {
                    this.update(date);
                    Util.MessageBox.tip("审核 成功");
                } else {
                    Util.MessageBox.tip(resp.message);
                }
            });
        }

        unapprove(date:Util.Date){
            this.mAjaxUnApprove.get({
                data : JSON.stringify(this.onLoadSubmitData()),
                date:this.getDate(date)
                    })
                .then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                    this.update(date);
                    Util.MessageBox.tip("反审核 成功");
                } else {
                Util.MessageBox.tip(resp.message);
            }
            });
        }
    }
}