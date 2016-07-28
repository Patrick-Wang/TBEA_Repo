///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
///<reference path="../../basic/basicShow.ts"/>
///<reference path="../../../messageBox.ts"/>
///<reference path="../../../components/dateSelectorProxy.ts"/>
///<reference path="../singleDateReport/show.ts"/>
module framework.templates.DateReport {
    import router = framework.router;
    import Node = JQTable.Node;
    import BasicEndpoint = framework.basic.BasicEndpoint;
    import FrameEvent = framework.basic.FrameEvent;
    import UnitedSelector = Util.UnitedSelector;
    import IDataNode = Util.IDataNode;


    export function createInstance() : ShowView {
        return new ShowView();
    }

    export interface ShowOption{
        updateUrl:string;
        exportUrl:string;
        host:string;
        date:Util.Date;
        dtId:string;
        asSeason:boolean;
        itemNodes:IDataNode[];
        itemId:string;
    }

    export class ShowView extends framework.templates.singleDateReport.ShowView{
        unitedSelector : Util.UnitedSelector
        getId():number{
            return framework.templates.singleDateReport.FRAME_ID;
        }

        onInitialize(opt:ShowOption):void{
            this.unitedSelector = new UnitedSelector(opt.itemNodes,opt.itemId);
            $("#" + opt.itemId + " select")
                .multiselect({
                    multiple: false,
                    header: false,
                    minWidth: 100,
                    height: '100%',
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                })
                .css("padding", "2px 0 2px 4px")
                .css("text-align", "left")
                .css("font-size", "12px");
            super.onInitialize(opt);
        }



        getDate(date:Util.Date):string{
            return "" + (date.year + "-" + (date.month == undefined ? 1 :date.month) + "-" + (date.day == undefined ? 1 :date.day));
        }

        update (date:Util.Date){
            this.mAjaxUpdate.get({
                    date: this.getDate(date),
                    item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id
                })
                .then((jsonData:any) => {
                    this.resp = jsonData;
                    this.updateTable();
                });
        }

        updateTable():void {
            var name = this.opt.host + "_jqgrid_uiframe";
            var pagername = name + "pager";
            this.mTableAssist = Util.JQGridAssistantFactory.createTable(name, this.resp);

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
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true
                }));
        }

        exportExcel(date:Util.Date, id:string): void {
            $("#" + id)[0].action = this.opt.exportUrl + "?" +  Util.Ajax.toUrlParam({
                date: this.getDate(date),
                item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id
            });
            $("#" + id)[0].submit();
        }
    }
}