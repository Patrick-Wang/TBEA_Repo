/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../messageBox.ts" />
///<reference path="../dateSelector.ts"/>
module yszkgb {

    import Endpoint = framework.route.Endpoint;
    import router = framework.router;
    import EntryPluginView = yszkgb.EntryPluginView;
    import DateSelector = Util.DateSelector;

    interface Option {
        comps: Util.IDataNode[];
        date: Util.Date;
    }

    interface PluginData extends Util.IData {
        plugin : EntryPluginView;
    }

    class EntryView implements Endpoint {

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

        private static ins:EntryView = new EntryView();

        protected mOpt:Option;
        protected mItemSelector:Util.UnitedSelector;
        protected mCompanySelector:Util.CompanySelector;
        protected mNodes:Util.DataNode[] = [];
        protected mCurrentPlugin:EntryPluginView;
        protected mCurrentDate:Util.Date;
        protected mCurrentComp:Util.CompanyType;

        public init(opt:any):void {
            this.mOpt = opt;
            $("#grid-date").jeDate({
                skinCell: "jedatedeepgreen",
                format: "YYYY年MM月",
                isTime: false,
                isinitVal: true,
                isClear: false,
                isToday: false,
                maxDate: Util.date2Str(opt.date),
            }).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top");
            this.mCompanySelector = new Util.CompanySelector(false, "comp-sel", this.mOpt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }
            this.mItemSelector = new Util.UnitedSelector(this.mNodes, "item-sel");
            if (this.mNodes.length == 1) {
                this.mItemSelector.hide();
            }
            this.mNodes = this.mItemSelector.getTopNodes();
            this.updateUI();

            $(window).resize(()=> {
                this.mCurrentPlugin.adjustSize();
            });

            $("#grid-update").on("click", ()=> {
                this.updateUI();
            });

            $("#save").on("click", ()=> {
                this.mCurrentPlugin.save(this.mCurrentDate, this.mCurrentComp);
            });

            $("#submit").on("click", ()=> {
                this.mCurrentPlugin.submit(this.mCurrentDate, this.mCurrentComp);
            });

            this.updateUI();
        }

        private getDate():Util.Date {
            let rq = $("#grid-date").val().replace("年", "-").replace("月", "-").replace("日", "-").split("-");
            return {
                year: rq[0] ? parseInt(rq[0]) : undefined,
                month: rq[1] ? parseInt(rq[1]) : undefined,
                day: rq[2] ? parseInt(rq[2]) : undefined
            };
        }

        protected plugin(node:Util.DataNode):EntryPluginView {
            return (<PluginData>node.getData()).plugin;
        }

        protected getActiveNode():Util.DataNode {
            return this.mItemSelector.getDataNode(this.mItemSelector.getPath());
        }

        public updateUI() {

            let node:Util.DataNode = this.getActiveNode();

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
    }
}