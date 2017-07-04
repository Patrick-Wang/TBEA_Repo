///<reference path="../framework/route/route.ts"/>
///<reference path="../jqgrid/jqassist.ts"/>
///<reference path="../framework/basic/basicdef.ts"/>
///<reference path="../framework/templates/singleDateReport/show.ts"/>
module framework.templates.dateReport.gcyzb {
    import router = framework.router;
    import Node = JQTable.Node;
    import BasicEndpoint = framework.basic.BasicEndpoint;
    import FrameEvent = framework.basic.FrameEvent;
    import UnitedSelector = Util.UnitedSelector;
    import IDataNode = Util.IDataNode;


    export function createInstance() : ShowView {
        return new GCYZBView();
    }

    export interface ShowOption extends framework.templates.singleDateReport.ShowOption{
        itemNodes:IDataNode[];
        itemId:string;
    }

    export class GCYZBView extends framework.templates.singleDateReport.ShowView{
        unitedSelector : Util.UnitedSelector;
        xmmcSel : Util.UnitedSelector;

        private renderItemSelector(itemId:string):void{
            let sels = $("#" + itemId + " select");
            for (let i = 0; i < sels.length; ++i){
                let opts = $("#" + itemId + " select:eq(" + i + ") option");
                let items = [];
                for (let j = 0; j < opts.length; ++j){
                    items.push(opts[j].text);
                }
                //$(sels[i]) .multiselect({
                //    multiple: false,
                //    header: false,
                //    minWidth: Util.getUIWidth(items) + 40,
                //    height: '100%',
                //    // noneSelectedText: "请选择月份",
                //    selectedList: 1
                //})
                //.css("padding", "2px 0 2px 4px")
                //.css("text-align", "left")
                //.css("font-size", "12px");
            }
        }

        private renderXmmcSelector(itemId:string):void{
            let sels = $("#" + itemId + " select");
            for (let i = 0; i < sels.length; ++i){
                let opts = $("#" + itemId + " select:eq(" + i + ") option");
                let items = [];
                for (let j = 0; j < opts.length; ++j){
                    items.push(opts[j].text);
                }
                $(sels[i])
                          .select2({
                               language: "zh-CN"
                            });
            }
        }

        onInitialize(opt:any):void{

            this.unitedSelector = new Util.UnitedSelector(opt.itemNodes,opt.itemId);
            this.xmmcSel = new Util.UnitedSelector(opt.xmmcNodes,opt.xmmcId);
            this.unitedSelector.change(()=>{
                this.renderItemSelector(opt.itemId);
                let id = this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id;
                if (id >= 3 && id <= 7){
                    $("#" + opt.dtId).parent().show();
                }


            });
            this.renderItemSelector(opt.itemId);

            this.renderXmmcSelector(opt.xmmcId);
            this.xmmcSel.change(()=>{
                this.renderXmmcSelector(opt.xmmcId);
            });
            super.onInitialize(opt);

            $("#" + opt.dtId).parent().hide();
        }


        getParams(date:Util.Date):any{
            return {
                date: this.getDate(date),
                item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id,
                xmmc: this.xmmcSel.getDataNode(this.xmmcSel.getPath()).data.value,
            };
        }

        update (date:Util.Date){
            this.mAjaxUpdate.get(this.getParams(date))
                .then((jsonData:any) => {
                    this.resp = jsonData;
                    this.updateTable();
                });
        }


        createJqassist():JQTable.JQGridAssistant{
            var parent = $("#" + this.opt.host);
            parent.empty();
            parent.append("<table id='"+ this.jqgridName() +"'></table>");
            this.mTableAssist = Util.createTable(this.jqgridName(), this.resp);
            return this.mTableAssist;
        }

        updateTable():void {
            this.createJqassist();

            this.mTableAssist.create({
                data: this.resp.data,
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: '100%',
                width: '100%',
                shrinkToFit: this.resp.shrinkToFit == undefined ? true : this.resp.shrinkToFit == "true",
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
        //    parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
        //    let jqTable = $("#" + name);
        //    jqTable.jqGrid(
        //        this.mTableAssist.decorate({
        //            datatype: "local",
        //            data: this.mTableAssist.getData(this.resp.data),
        //            multiselect: false,
        //            drag: false,
        //            resize: false,
        //            assistEditable:false,
        //            //autowidth : false,
        //            cellsubmit: 'clientArray',
        //            //editurl: 'clientArray',
        //            cellEdit: false,
        //            // height: data.length > 25 ? 550 : '100%',
        //            // width: titles.length * 200,
        //            rowNum: 1000,
        //            height: this.resp.data.length > 25 ? 550 : '100%',
        //            width: this.resp.width == undefined ? 1300 : this.resp.width,
        //            shrinkToFit: this.resp.shrinkToFit == undefined ? true : this.resp.shrinkToFit == "true",
        //            autoScroll: true
        //        }));
        //}

        exportExcel(date:Util.Date, id:string): void {
            $("#" + id)[0].action = this.opt.exportUrl + "?" +  Util.Ajax.toUrlParam(this.getParams(date));
            $("#" + id)[0].submit();
        }
    }
}