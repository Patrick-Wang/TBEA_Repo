/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../wlyddqk/wlyddqkdef.ts" />

declare var echarts;
declare var view:wlyddqk.FrameView;

module sbdddcbjpcqk {
    export module byqkglydd {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, type:wlyddqk.WlyddType):JQTable.JQGridAssistant {

                let nodeFirst : JQTable.Node;
                if (type == wlyddqk.WlyddType.SCDY){
                    nodeFirst = new JQTable.Node("生产单元（项目公司）", "scdy", true, TextAlign.Center)
                }else{
                    nodeFirst = new JQTable.Node("产品类别", "sclb", true, TextAlign.Center)
                }

                return new JQTable.JQGridAssistant([
                    nodeFirst,
                    new JQTable.Node("月产出能力", "rqa")
                        .append( new JQTable.Node("产值", "ba"))
                        .append( new JQTable.Node("产量", "bb")),
                    new JQTable.Node("所有可供履约订单总量产值", "ab")
                        .append( new JQTable.Node("产值", "cca"))
                        .append( new JQTable.Node("产量", "ccb")),
                    new JQTable.Node("当年可供履约订单总量产值", "ac")
                        .append( new JQTable.Node("产值", "da"))
                        .append( new JQTable.Node("产量", "db")),
                    new JQTable.Node("n+1月订单量", "ada")
                        .append( new JQTable.Node("产值", "ea"))
                        .append( new JQTable.Node("产量", "eb"))
                        .append( new JQTable.Node("产能发挥率", "ec")),
                    new JQTable.Node("n+2月订单量", "aeb")
                        .append( new JQTable.Node("产值", "fc"))
                        .append( new JQTable.Node("产量", "fb"))
                        .append( new JQTable.Node("产能发挥率", "fd")),
                    new JQTable.Node("n+3月订单量", "aec")
                        .append( new JQTable.Node("产值", "fc"))
                        .append( new JQTable.Node("产量", "fb"))
                        .append( new JQTable.Node("产能发挥率", "fd")),
                    new JQTable.Node("n+4月订单量", "aed")
                        .append( new JQTable.Node("产值", "fc"))
                        .append( new JQTable.Node("产量", "fb"))
                        .append( new JQTable.Node("产能发挥率", "fd")),
                    new JQTable.Node("n+5月订单量", "aef")
                        .append( new JQTable.Node("产值", "fc"))
                        .append( new JQTable.Node("产量", "fb"))
                        .append( new JQTable.Node("产能发挥率", "fd")),
                    new JQTable.Node("n+6月订单量", "aeg")
                        .append( new JQTable.Node("产值", "fc"))
                        .append( new JQTable.Node("产量", "fb"))
                        .append( new JQTable.Node("产能发挥率", "fd")),
                    new JQTable.Node("n+6月以后可供履约订单", "aeh")
                        .append( new JQTable.Node("产值", "fc"))
                        .append( new JQTable.Node("产量", "fb"))
                        .append( new JQTable.Node("产能发挥率", "fd")),
                    new JQTable.Node("次年及以后可供履约订单排产值", "af")
                        .append( new JQTable.Node("产值", "ga"))
                        .append( new JQTable.Node("产量", "gb")),
                    new JQTable.Node("交货期待定产值", "ag")
                        .append( new JQTable.Node("产值", "ga"))
                        .append( new JQTable.Node("产量", "gb"))
                ], gridName);
            }
        }

        interface Option extends wlyddqk.PluginOption {
            tb:string;
        }

        class ByqkglyddView extends wlyddqk.BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("../sbdddcbjpcqk/byqkglydd/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;

            public static newInstance():ByqkglyddView {
                return new ByqkglyddView();
            }
            isSupported( compType:Util.CompanyType):boolean{
                if (compType == Util.CompanyType.SBGS || compType == Util.CompanyType.HBGS || compType == Util.CompanyType.XBC  || compType == Util.CompanyType.TBGS){
                    return true;
                }
                return false;
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "../sbdddcbjpcqk/byqkglydd/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        type: this.mType,
                        companyId:compType
                    });
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mAjax.get({
                        type:this.mType,
                        date: date,
                        companyId:compType
                    })
                    .then((jsonData:any) => {
                        this.mData = jsonData;
                        this.refresh();
                    });
            }

            public refresh() : void{
                if ( this.mData == undefined){
                    return;
                }

                this.updateTable();
            }

            public init(opt:Option):void {
                super.init(opt);
                view.register("可供履约订单情况(产品类别口径)", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddType.SCLB));
                view.register("可供履约订单情况(生产单元口径)", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddType.SCDY));
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mType);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                this.$(name).jqGrid(
                    tableAssist.decorate({
                        multiselect: false,
                        drag: false,
                        resize: false,
                        height: '100%',
                        width: 1400,
                        shrinkToFit: true,
                        autoScroll: true,
                        rowNum: 20,
                        data: tableAssist.getData(this.mData),
                        datatype: "local",
                        viewrecords : true
                    }));
            }
        }

        export var pluginView = ByqkglyddView.newInstance();
    }
}
