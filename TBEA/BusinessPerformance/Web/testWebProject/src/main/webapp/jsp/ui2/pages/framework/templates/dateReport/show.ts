///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
///<reference path="../../basic/basicShow.ts"/>
///<reference path="../../../messageBox.ts"/>
///<reference path="../../../components/dateSelectorProxy.ts"/>
///<reference path="../singleDateReport/show.ts"/>
module framework.templates.dateReport {
    import router = framework.router;
    import Node = JQTable.Node;
    import BasicEndpoint = framework.basic.BasicEndpoint;
    import FrameEvent = framework.basic.FrameEvent;
    import UnitedSelector = Util.UnitedSelector;
    import IDataNode = Util.IDataNode;


    export function create() : framework.templates.singleDateReport.ShowView {
        return new ItemShowView();
    }

    interface ShowOption extends framework.templates.singleDateReport.ShowOption{
        itemNodes:IDataNode[];
        itemId:string;
        itemNodes2:IDataNode[];
        itemId2:string;
        itemNodes3:IDataNode[];
        itemId3:string;
    }

    class ItemShowView extends framework.templates.singleDateReport.ShowView{
        unitedSelector : Util.UnitedSelector;
        unitedSelector2 : Util.UnitedSelector;
        unitedSelector3 : Util.UnitedSelector;
        doubleHeader : boolean = false;

        onInitialize(opt:ShowOption):void{
            this.unitedSelector = new Util.UnitedSelector(opt.itemNodes,opt.itemId);
            this.unitedSelector.change(()=>{
                this.adjustHeader();
            });


            let itemHidden = false;
            if (opt.itemNodes.length == 1){
                $("#" + opt.itemId).hide();
                itemHidden = true;
            }


            let item2Hidden = false;
            if (opt.itemNodes2 != undefined){
                this.unitedSelector2 = new Util.UnitedSelector(opt.itemNodes2,opt.itemId2);
                this.unitedSelector2.change(()=>{
                    this.adjustHeader();
                });
                if (opt.itemNodes2.length == 1){
                    $("#" + opt.itemId2).hide();
                    item2Hidden = true;
                }
            }

            let item3Hidden = false;
            if (opt.itemNodes3 != undefined){
                this.unitedSelector3 = new Util.UnitedSelector(opt.itemNodes3,opt.itemId3);
                this.unitedSelector3.change(()=>{
                    this.adjustHeader();
                });
                if (opt.itemNodes3.length == 1){
                    $("#" + opt.itemId3).hide();
                    item3Hidden = true;
                }
            }

            if (opt.searchlist == 'true'){
                $("#" + opt.itemId + " select").select2({
                    language: "zh-CN"
                });
            }


            if (item2Hidden && itemHidden  && item3Hidden && $("#" + opt.dtId).hasClass("hidden")){
                $("#sels").hide();
                $("#" + opt.dtId).hide();
            }


            $(window).resize(()=>{
                this.adjustHeader();
            });

            super.onInitialize(opt);
        }

        getParams(date:Util.Date):any{
            return {
                date: this.getDate(date),
                item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id,
                item2: this.unitedSelector2 ? this.unitedSelector2.getDataNode(this.unitedSelector2.getPath()).data.id : undefined,
                item3: this.unitedSelector3 ? this.unitedSelector3.getDataNode(this.unitedSelector3.getPath()).data.id : undefined
            };
        }

        adjustHeader(){
            $("#headerHost").removeCss("width");
            if ($("#headerHost").height() > 40){
                $(".page-header").addClass("page-header-double");
                $("#headerHost").css("width", $("#sels").width() + "px");
                if (!this.doubleHeader){
                    this.doubleHeader = true;
                    return true;
                }
            }else{
                $(".page-header").removeClass("page-header-double");
                if (this.doubleHeader){
                    this.doubleHeader = false;
                    return true;
                }
            }
            return false;
        }

        update (date:Util.Date){
            this.mAjaxUpdate.get(this.getParams(date))
                .then((jsonData:any) => {
                    this.resp = jsonData;
                    if (this.resp.data.length == 0){
                        $("#" + this.opt.host).addClass("hidden");
                        $("#warning").removeClass("hidden");
                    }else{
                        $("#" + this.opt.host).removeClass("hidden");
                        $("#warning").addClass("hidden");
                        this.updateTable();
                    }
                });
        }

    }
}