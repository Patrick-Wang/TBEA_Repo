///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
///<reference path="../../../messageBox.ts"/>
///<reference path="../../../components/dateSelectorProxy.ts"/>
///<reference path="../singleDateReport/entry.ts"/>
module framework.templates.dateReport {
    import router = framework.router;
    import Node = JQTable.Node;
    import BasicEndpoint = framework.basic.BasicEndpoint;
    import FrameEvent = framework.basic.FrameEvent;
    import Formula = JQTable.Formula;
    import Cell = JQTable.Cell;

    export function createInstance() : EntryView{
        return new EntryView();
    }

    interface EntryOption extends framework.templates.singleDateReport.EntryOption{
        itemNodes:Util.IDataNode[];
        itemId:string;
    }

    export class EntryView extends framework.templates.singleDateReport.EntryView{

        unitedSelector : Util.UnitedSelector;

        onInitialize(opt:EntryOption):void{
            this.unitedSelector = new Util.UnitedSelector(opt.itemNodes,opt.itemId);
            if (opt.itemNodes.length == 1){
                this.unitedSelector.hide();
                if($("#" + opt.dtId).parent().hasClass("hidden")){
                    $("#grid-update").hide();
                    $("#" + opt.dtId).hide();
                }
            }
            super.onInitialize(opt);
        }

        getParams(date:Util.Date):any{
            return {
                date: this.getDate(date),
                item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id
            };
        }

        createJqassist():JQTable.JQGridAssistant{
            var parent = $("#" + this.opt.host);
            var pagername = this.jqgridName() + "pager";
            parent.empty();
            parent.append("<table id='"+ this.jqgridName() +"'><div id='" + pagername + "'></table>");
            this.mTableAssist = Util.createTable(this.jqgridName(), this.resp);

            let dst = new Cell(0, this.resp.dest);
            let srs = [new Cell(0, this.resp.f1), new Cell(0, this.resp.f2)];
            let form : Formula  = new Formula(dst, srs, (dest:Cell, srcs:Cell[])=>{
                let f1 = srcs[0].getVal();
                let f2 = srcs[1].getVal();
                if (f1 != "" && f2 != ""){
                    return f1 - f2;
                }
            });
            this.mTableAssist.addFormula(form);

            return this.mTableAssist;
        }

        updateTable():void {
            if(this.resp.data.length > 0){
                $("#table").show();
                $("#warning-nodata").hide();
                super.updateTable();
            }else{
                $("#table").hide();
                $("#warning-nodata").show();
            }
        }
    }
}