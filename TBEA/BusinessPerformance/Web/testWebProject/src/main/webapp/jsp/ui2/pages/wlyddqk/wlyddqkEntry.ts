/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="wlyddqkdef.ts" />
/// <reference path="../unitedSelector.ts"/>
///<reference path="../messageBox.ts"/>
///<reference path="../companySelector.ts"/>

module wlyddqk {

    interface Option {
        dt:string;
        type:string;
        comp:string;
        comps: Util.IDataNode[];
        date: Util.Date;
    }

    interface PluginData extends Util.IData {
        plugin : EntryPluginView;
    }

    export class EntryView implements EntryFrameView {

        protected mOpt:Option;
        protected mItemSelector:Util.UnitedSelector;
        protected mNodesAll:Util.DataNode[] = [];
        protected mCurrentPlugin:EntryPluginView;
        protected mCurrentDate:Util.Date;
        protected mCompanySelector:Util.CompanySelector;
        protected mCurrentComp:Util.CompanyType;

        public register(name:string, plugin:EntryPluginView):void {
            var data:PluginData = {id: this.mNodesAll.length, value: name, plugin: plugin};
            var node:Util.DataNode = new Util.DataNode(data);
            this.mNodesAll.push(node);
            plugin.setOnReadOnlyChangeListener((isReadOnly:boolean)=> {
                if (isReadOnly) {
                    $("#save").hide();
                    $("#submit").hide();
                } else {
                    $("#save").show();
                    $("#submit").show();
                }
            });
        }

        unregister(name:string):EntryPluginView {
            return undefined;
        }

        public init(opt:Option):void {
            this.mOpt = opt;


            $("#" + this.mOpt.dt).jeDate({
                skinCell: "jedatedeepgreen",
                format: "YYYY年MM月",
                isTime: false,
                isinitVal: true,
                isClear: false,
                isToday: false,
                minDate: Util.date2Str(Util.addYear(this.mOpt.date, -3)),
                maxDate: Util.date2Str(this.mOpt.date)
            }).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top");

            this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.comp, this.mOpt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }

            this.mCompanySelector.change((selector:any, depth:number) => {
                this.updateTypeSelector();
            });

            $(window).resize(()=>{
                this.mCurrentPlugin.adjustSize();
                this.adjustHeader();
            });

            this.updateTypeSelector();
            this.updateUI();
            this.adjustHeader();
        }


        adjustHeader(){
            $("#headerHost").removeCss("width");
            if ($("#headerHost").height() > 40){
                $(".page-header").addClass("page-header-double");
                $("#headerHost").css("width", $("#sels").width() + "px");
            }else{
                $(".page-header").removeClass("page-header-double");
            }
            return false;
        }

        protected getDate():Util.Date {
            let ret : any = {};
            if (this.mOpt.date){
                let curDate = $("#" + this.mOpt.dt).getDate();
                ret = {
                    year : curDate.getFullYear(),
                    month : curDate.getMonth() + 1,
                    day : curDate.getDate()
                };
            }
            return ret;
        }

        protected updateTypeSelector(width : number = 325) {
            let type:Util.CompanyType = this.mCompanySelector.getCompany();
            let nodes = [];
            for (var i = 0; i < this.mNodesAll.length; ++i) {
                if (this.plugin(this.mNodesAll[i]).isSupported(type)) {
                    nodes.push(this.mNodesAll[i]);
                }
            }

            let curNodes = [];
            if (this.mItemSelector != undefined) {
                curNodes = this.mItemSelector.getTopNodes()
            }
            let typeChange = false;
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

            if (typeChange) {
                this.mItemSelector = new Util.UnitedSelector(nodes, this.mOpt.type);
                if (nodes.length == 1) {
                    this.mItemSelector.hide();
                }
            }
        }

        protected plugin(node:Util.DataNode):EntryPluginView {
            return (<PluginData>node.getData()).plugin;
        }

        protected getActiveNode():Util.DataNode {
            return this.mItemSelector.getDataNode(this.mItemSelector.getPath());
        }

        public updateUI() {
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());

            let dt:Util.Date = this.getDate();
            dt.day = 1;

            this.mCurrentPlugin = this.plugin(node);
            for (var i = 0; i < this.mNodesAll.length; ++i) {
                if (this.plugin(node) != this.plugin(this.mNodesAll[i])) {
                    this.plugin(this.mNodesAll[i]).hide();
                }
            }
            this.mCurrentComp = this.mCompanySelector.getCompany();
            this.mCurrentDate = dt;
            this.mCurrentPlugin.show();
            this.plugin(node).update(dt, this.mCurrentComp);
        }

        public submit() {
            this.plugin(this.getActiveNode()).submit(this.mCurrentDate, this.mCurrentComp);
        }

        public save() {
            this.plugin(this.getActiveNode()).save(this.mCurrentDate, this.mCurrentComp);
        }
    }
}

var entryView:wlyddqk.EntryView = new wlyddqk.EntryView();