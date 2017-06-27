/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../unitedSelector.ts"/>
///<reference path="../../messageBox.ts"/>
///<reference path="../../companySelector.ts"/>
///<reference path="basicdef.ts"/>
///<reference path="../route/route.ts"/>
module framework.basic {

    import router = framework.router;

    export interface Option {
        dt:string;
        type:string;
        comp:string;
        comps: Util.IDataNode[];
        date: Util.Date;
    }

    interface PluginData extends Util.IData {
        plugin : number;
    }

    export class BasicFrameView extends FrameView {
        protected mOpt:Option;
        protected mItemSelector:Util.UnitedSelector;
        protected mNodesAll:Util.DataNode[] = [];
        protected mCurrentPlugin:number;
        protected mCurrentDate:Util.Date;
        protected mCompanySelector:Util.CompanySelector;
        protected mCurrentComp:Util.CompanyType;

        protected register(name:string, plugin:number):void {
            var data:PluginData = {id: this.mNodesAll.length, value: name, plugin: plugin};
            var node:Util.DataNode = new Util.DataNode(data);
            this.mNodesAll.push(node);
        }

        protected unregister(name:string):number {
            var nod:Util.DataNode;

            for (var i = 0; i < this.mNodesAll.length; ++i) {
                this.mNodesAll[i].accept({
                    visit: (node:Util.DataNode) => {
                        if (node.getData().value == name) {
                            nod = node;
                            return true;
                        }
                        return false;
                    }
                })
                if (nod != undefined) {
                    break;
                }
            }

            return this.plugin(nod);
        }

        protected init(opt:Option):void {
            this.mOpt = opt;

            let minDate = Util.addYear(opt.date, -3);
            let className = "";
            let fmt = "YYYY年MM月";
            if (!opt.date.month){
                fmt = "YYYY年";
                className = "year";
            }else{
                if (!opt.date.day){
                    fmt = "YYYY年MM月DD日";
                    className = "day";
                }
            }

            minDate.month = 1;
            $("#" + this.mOpt.dt).jeDate({
                skinCell: "jedatedeepgreen",
                format: fmt,
                isTime: false,
                isinitVal: true,
                isClear: false,
                isToday: false,
                minDate: Util.date2Str(minDate),
                maxDate: Util.date2Str(opt.date),
            }).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top")
                .addClass(className);

            this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.comp, this.mOpt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }

            this.mCompanySelector.change((selector:any, depth:number) => {
                this.updateTypeSelector();
                this.adjustHeader();
                router.to(this.mCurrentPlugin).send(FrameEvent.FE_ADJUST_SZIE);
            });

            $(window).resize(()=> {
                this.adjustHeader();
                router.to(this.mCurrentPlugin).send(FrameEvent.FE_ADJUST_SZIE);
            });



            this.updateTypeSelector();
            this.adjustHeader();

            router.to(this.mCurrentPlugin).send(FrameEvent.FE_ADJUST_SZIE);
            this.updateUI();
        }

        private adjustHeader(){
            $("#headerHost").removeCss("width");
            if ($("#headerHost").height() > 40){
                $(".page-header").addClass("page-header-double");
                $("#headerHost").css("width", $("#sels").width() + "px");
            }else{
                $(".page-header").removeClass("page-header-double");
                $("#headerHost").removeCss("width");
            }
        }

        private getDate():Util.Date {
            let curDate = $("#" + this.mOpt.dt).getDate();
            return {
                year : curDate.getFullYear(),
                month : curDate.getMonth() + 1,
                day:curDate.getDate()
            };
        }

        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case FrameEvent.FE_UPDATE:
                    this.updateUI();
                    break;
            }
            return super.onEvent(e);
        }

        protected updateTypeSelector(width:number = 285) :boolean{
            let type:Util.CompanyType = this.mCompanySelector != undefined ?
                this.mCompanySelector.getCompany() : undefined;
            let nodes = [];
            for (var i = 0; i < this.mNodesAll.length; ++i) {
                if (router.to(this.plugin(this.mNodesAll[i])).send(FrameEvent.FE_IS_COMPANY_SUPPORTED, type)) {
                    nodes.push(this.mNodesAll[i]);
                }
            }

            let typeChange = false;
            let curNodes = [];
            if (this.mItemSelector != undefined){
                curNodes = this.mItemSelector.getTopNodes()
            }
            if (nodes.length != curNodes.length) {
                typeChange = true;
            } else {
                for (let i = 0; i < nodes.length; ++i) {
                    if (this.plugin(nodes[i]) != this.plugin(curNodes[i])) {
                        typeChange = true;
                        break;
                    }
                }
            }

            if (typeChange){
                this.mItemSelector = new Util.UnitedSelector(nodes, this.mOpt.type);
                if (nodes.length == 1) {
                    this.mItemSelector.hide();
                }
            }
            return typeChange;
        }


        protected plugin(node:Util.DataNode):number {
            return (<PluginData>node.getData()).plugin;
        }

        protected updateUI() {
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());

            let dt:Util.Date = this.getDate();
            if (dt.month == undefined){
                dt.month = 1;
            }

            if (dt.day == undefined) {
                dt.day = 1;
            }

            this.mCurrentPlugin = this.plugin(node);
            router.broadcast(FrameEvent.FE_HIDE);

            this.mCurrentComp = this.mCompanySelector.getCompany();
            this.mCurrentDate = dt;
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SHOW);

            let unit = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GETUNIT);
            if (undefined != unit){
                $("#unit").text(unit);
            }else{
                $("#unit").text("");
            }

            router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                date:dt,
                compType:this.mCurrentComp
            });
        }
    }
}

