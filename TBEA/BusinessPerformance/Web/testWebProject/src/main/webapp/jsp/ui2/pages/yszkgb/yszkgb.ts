/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../messageBox.ts" />
///<reference path="../dateSelector.ts"/>
declare var echarts;

module yszkgb {

    import Endpoint = framework.route.Endpoint;
    import router = framework.router;

    interface PluginData extends Util.IData {
        plugin : PluginView;
    }


    interface IViewOption {
        tableId: string;
        date: Util.Date;
        comps: Util.IDataNode[];
    }

    export class FrameView implements Endpoint {

        getId():number {
            return Util.FAMOUS_VIEW;
        }

        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case Util.MSG_INIT:
                    this.init(e.data);
                    break;
                case Util.MSG_UPDATE:
                    this.updateUI();
                    break;
                case Util.MSG_REG:
                    var data:PluginData = {id: this.mNodes.length, value: e.data.name, plugin: e.data.plugin};
                    var node:Util.DataNode = new Util.DataNode(data);
                    this.mNodes.push(node);
                    break;
            }
        }


        public constructor() {
            router.register(this);
        }

        static ins : FrameView = new FrameView();

        mNodes:Util.DataNode[] = [];
        mOpt: IViewOption;
        mCompanySelector: Util.CompanySelector;
        mItemSelector:Util.UnitedSelector;
        mCurrentPlugin:PluginView;
        mCurrentDate:Util.Date;
        mCurrentComp:Util.CompanyType;

        public init(opt:any):void {
            this.mOpt = opt;

            let minDate = Util.addYear(opt.date, -3);
            minDate.month = 1;
            $("#grid-date").jeDate({
                skinCell: "jedatedeepgreen",
                format: "YYYY年MM月",
                isTime: false,
                isinitVal: true,
                isClear: false,
                isToday: false,
                minDate: Util.date2Str(minDate),
                maxDate: Util.date2Str(opt.date),
            }).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top");

            this.mCompanySelector = new Util.CompanySelector(false, "comp-sel", opt.comps);

            this.mItemSelector = new Util.UnitedSelector(this.mNodes, "item-sel");
            if (this.mNodes.length == 1) {
                this.mItemSelector.hide();
            }
            if (opt.firstItem){
                this.mItemSelector.update([opt.firstItem]);
            }



            this.mNodes = this.mItemSelector.getTopNodes();

            $("#grid-update").on("click", ()=> {
                this.updateUI();
            });

            $("#grid-export").on("click", ()=> {
                this.exportExcel();
            });

            $(window).resize(()=> {
                this.adjustHeader();
                this.mCurrentPlugin.adjustSize();
            });

            this.adjustHeader();
            this.updateUI();
        }

        private adjustHeader(){
            $("#headerHost").removeCss("width");
            if ($("#headerHost").height() > 40){
                $(".page-header").addClass("page-header-double");
                $("#headerHost").css("width", $("#sels").width() + "px");
            }else{
                $(".page-header").removeClass("page-header-double");
            }
        }

        plugin(node:Util.DataNode):PluginView {
            return (<PluginData>node.getData()).plugin;
        }

        getDate():Util.Date {
            let rq = $("#grid-date").val().replace("年", "-").replace("月", "-").replace("日", "-").split("-");
            return {
                year: rq[0] ? parseInt(rq[0]) : undefined,
                month: rq[1] ? parseInt(rq[1]) : undefined,
                day: rq[2] ? parseInt(rq[2]) : undefined
            };
        }

        updateUI() {
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());

            let dt:Util.Date = this.getDate();
            dt.day = 1;

            this.mCurrentPlugin = this.plugin(node);
            for (var i = 0; i < this.mNodes.length; ++i) {
                if (node != this.mNodes[i]) {
                    this.plugin(this.mNodes[i]).hide();
                }
            }

            this.mCurrentComp = this.mCompanySelector.getCompany();
            this.mCurrentDate = dt;
            this.mCurrentPlugin.show();
            this.plugin(node).update(dt, this.mCurrentComp);
        }

        //不可以起名叫做export 在IE中有冲突
        exportExcel() {
            let url:string = this.mCurrentPlugin.getExportUrl(this.mCurrentDate, this.mCurrentComp);
            $("#exportExcel")[0].action = url;
            $("#exportExcel")[0].submit();
        }
    }
}