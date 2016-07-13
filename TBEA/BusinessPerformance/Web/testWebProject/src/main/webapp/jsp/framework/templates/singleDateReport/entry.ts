///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
module framework.basic {
    import router = framework.router;
    import Node = JQTable.Node;
    class JQGridAssistantFactory {

        public static createTable(gridName:string, headers: Util.Header[]):JQTable.JQGridAssistant {

            let nodes : Node[] = [];
            for (let i= 0; i < headers.length; ++i) {
                let node = Util.parseHeader(headers[i]);
                if (null != node) {
                    nodes.push(node);
                }
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        }
    }

    export class EntryView extends BasicEndpoint{
        dateSelect : Util.DateSelector;
        mAjaxUpdate:Util.Ajax;
        mAjaxSubmit:Util.Ajax;
        mTableAssist:JQTable.JQGridAssistant;
        resp:any;
        opt:any;
        getId():number{
            return endpoint.FRAME_ID;
        }

        onInitialize(opt:any):void{
            this.opt = opt;
            this.dateSelect = new Util.DateSelector({
                year:opt.date.year - 3,
                month:opt.date.month,
                day:opt.date.day
            }, opt.date, opt.dtId);

            this.mAjaxUpdate = new Util.Ajax(opt.updateUrl, false);
            this.mAjaxSubmit = new Util.Ajax(opt.submitUrl, false);
        }

        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case FrameEvent.FE_UPDATE:
                    this.update(this.dateSelect.getDate());
                    break;
            }
            return true;
        }

        getDate(date:Util.Date):string{
            return "" + (date.year + "-" + date.month == undefined ? 1 :date.month + "-" + date.day == undefined ? 1 :date.day);
        }

        update (date:Util.Date){
            this.mAjaxUpdate.get({
                    date: this.getDate(date)
                })
                .then((jsonData:any) => {
                    this.resp = jsonData;
                    this.refresh();
                });
        }

        private refresh():void {
            var name = this.opt.host + "_jqgrid_uiframe";
            var pagername = name + "pager";
            this.mTableAssist = JQGridAssistantFactory.createTable(name, this.resp.header);

            var parent = $(this.opt.host);
            parent.empty();
            parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
            let jqTable = $(name);
            jqTable.jqGrid(
                this.mTableAssist.decorate({
                    datatype: "local",
                    data: this.mTableAssist.getDataWithId(this.resp.data),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    assistEditable:false,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    //editurl: 'clientArray',
                    cellEdit: true,
                    // height: data.length > 25 ? 550 : '100%',
                    // width: titles.length * 200,
                    rowNum: 1000,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true
                }));
        }
    }
}