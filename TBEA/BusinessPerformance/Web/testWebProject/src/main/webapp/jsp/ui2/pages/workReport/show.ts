
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
    }

    class ItemShowView extends framework.templates.singleDateReport.ShowView{
        unitedSelector : Util.UnitedSelector;
        unitedSelector2 : Util.UnitedSelector;
        doubleHeader : boolean = false;
        firstUpdate = true;
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
                this.unitedSelector.change(()=>{
                    this.adjustHeader();
                });
                if (opt.itemNodes2.length == 1){
                    $("#" + opt.itemId2).hide();
                    item2Hidden = true;
                }
            }

            if (opt.searchlist == 'true'){
                $("#" + opt.itemId + " select").select2({
                    language: "zh-CN"
                });
            }


            if (item2Hidden && itemHidden && $("#" + opt.dtId).hasClass("hidden")){
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
                item2: this.unitedSelector2 ? this.unitedSelector2.getDataNode(this.unitedSelector2.getPath()).data.id : undefined
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
            if (!this.firstUpdate){
                this.mAjaxUpdate.get(this.getParams(date))
                    .then((jsonData:any) => {
                        this.resp = jsonData;
                        $("#exportForm")[0].action = encodeURI(this.opt.exportUrl + "?" +  Util.Ajax.toUrlParam(jsonData));
                        $("#exportForm")[0].submit();
                    });
            } else{
                this.firstUpdate = false;
            }
        }

    }
}