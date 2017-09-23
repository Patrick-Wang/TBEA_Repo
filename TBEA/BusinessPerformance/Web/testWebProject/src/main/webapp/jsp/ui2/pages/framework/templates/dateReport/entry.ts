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
    }
}