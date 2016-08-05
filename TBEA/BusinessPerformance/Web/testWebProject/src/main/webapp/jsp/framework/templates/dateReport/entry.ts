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

    export interface EntryOption extends framework.templates.singleDateReport.EntryOption{
        itemNodes:Util.IDataNode[];
        itemId:string;
    }

    export class EntryView extends framework.templates.singleDateReport.EntryView{

        unitedSelector : Util.UnitedSelector;

        onInitialize(opt:any):void{
            this.unitedSelector = new Util.UnitedSelector(opt.itemNodes,opt.itemId);
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

        getParams(date:Util.Date):any{
            return {
                date: this.getDate(date),
                item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id
            };
        }

        update (date:Util.Date){
            this.mAjaxUpdate.get(this.getParams(date))
                .then((jsonData:any) => {
                    this.resp = jsonData;
                    this.updateTable();
                });
        }

        submit(date:Util.Date): void{
            this.mAjaxSubmit.get($.extend(this.getParams(date), {
                    data : JSON.stringify(this.onLoadSubmitData())
                }))
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