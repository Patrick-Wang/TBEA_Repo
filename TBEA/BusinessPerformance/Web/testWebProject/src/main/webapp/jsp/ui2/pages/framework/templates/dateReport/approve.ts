///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
///<reference path="../../../messageBox.ts"/>
///<reference path="../../../components/dateSelectorProxy.ts"/>
///<reference path="../singleDateReport/entry.ts"/>
///<reference path="../singleDateReport/approve.ts"/>
module framework.templates.dateReport {
    import router = framework.router;
    import Node = JQTable.Node;
    import BasicEndpoint = framework.basic.BasicEndpoint;
    import FrameEvent = framework.basic.FrameEvent;

    declare var gZt : any;

    export function createInstance() : ApproveView{
        return new ApproveView();
    }

    export interface EntryOption extends framework.templates.singleDateReport.ApproveOption{
        itemNodes:Util.IDataNode[];
        itemId:string;
    }

    export class ApproveView extends framework.templates.singleDateReport.ApproveView{

        unitedSelector : Util.UnitedSelector;

        onInitialize(opt:any):void{
            this.unitedSelector = new Util.UnitedSelector(opt.itemNodes,opt.itemId);
            if (opt.itemNodes.length == 1){
                this.unitedSelector.hide();
                $("#headertitle").text(opt.itemNodes[0].data.value + " " + $("#headertitle").text());
            }
            super.onInitialize(opt);
        }

        getParams(date:Util.Date):any{
            return {
                date: this.getDate(date),
                item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id,
                zt : window.gZt
            };
        }

    }
}