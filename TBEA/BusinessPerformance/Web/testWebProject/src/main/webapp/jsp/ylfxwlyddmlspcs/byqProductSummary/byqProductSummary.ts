/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../wlyddqk/wlyddqkdef.ts" />

declare var echarts;
declare var view:wlyddqk.FrameView;

module  ylfxwlyddmlspcs {
    export module byqProductSummary {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                
                let curDate : Date = new Date();
                let month = curDate.getMonth() + 1;
                let data = [];
                let node : JQTable.Node;
                let titleNodes : JQTable.Node[] = [];
                
                node = new JQTable.Node("产品", "byqProductSummary_cp", true, TextAlign.Center);
                titleNodes.push(node);
                
                node = new JQTable.Node("上年度", "byqProductSummary_snd", true, TextAlign.Center);
                for (let i = month + 1; i <= 12; ++i){
                    node.append(new JQTable.Node(i + "月", "ylfxwlyddmlspcs_snd_" + i));
                }
                titleNodes.push(node);
                
                node = new JQTable.Node("本年度", "byqProductSummary_bnd", true, TextAlign.Center);
                for (let i = 1; i <= month; ++i){
                    node.append(new JQTable.Node(i + "月", "ylfxwlyddmlspcs_bnd_" + i));
                }               
                titleNodes.push(node);
                
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            }
        }

        interface Option extends PluginOption {
            tb:string;
        }

        class ByqProductSummaryView extends BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("byqProductSummary/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            
            public static newInstance():ByqProductSummaryView {
                return new ByqProductSummaryView();
            }
            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "byqProductSummary/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId: cpType
                    });
            }
            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, cpType:Util.CompanyType):void {
                this.mDt = date;
                this.mAjax.get({
                        date: date,
                        companyId: cpType
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
                view.register("未履约订单毛利水平测算 -产品综合", this);
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                
                let curDate : Date = new Date(Date.parse(this.mDt));
                let month = curDate.getMonth() + 1;
                let data = [];
                for (let i = month + 1; i <= 12; ++i){
                    data.push(["上年度", i + "月"].concat(this.mData[i - month - 1]));
                }
                for (let i = 1; i <= month; ++i){
                    data.push(["本年度", i + "月"].concat(this.mData[12 - month + i - 1]));
                }

                tableAssist.mergeRow(0);
                tableAssist.mergeTitle();
                
                this.$(name).jqGrid(
                    tableAssist.decorate({
                        multiselect: false,
                        drag: false,
                        resize: false,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        rowNum: 20,
                        data: tableAssist.getData(data),
                        datatype: "local",
                        viewrecords : true
                    }));
            }
        }

        export var pluginView = ByqProductSummaryView.newInstance();
    }
}
