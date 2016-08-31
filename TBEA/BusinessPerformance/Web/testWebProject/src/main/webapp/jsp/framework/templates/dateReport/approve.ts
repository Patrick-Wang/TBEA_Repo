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
            if (opt.itemNodes.length == 1){
                this.unitedSelector.hide();
                $("#headertitle").text(opt.itemNodes[0].data.value + " " + $("#headertitle").text());
            }
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

        approve(date:Util.Date): void{
            this.mAjaxApprove.get($.extend(this.getParams(date), {
                    data : JSON.stringify(this.onLoadSubmitData())
                }))
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
            this.mAjaxUnApprove.get($.extend(this.getParams(date), {
                    data : JSON.stringify(this.onLoadSubmitData())
                }))
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