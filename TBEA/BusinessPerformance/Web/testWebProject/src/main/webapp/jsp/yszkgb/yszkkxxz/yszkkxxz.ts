/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../yszkgbdef.ts" />

declare var echarts;
declare var view:yszkgb.FrameView;

module yszkgb {
    export module yszkkxxz {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月度", "aa", true, TextAlign.Center),
                    new JQTable.Node("月度", "a1a", true, TextAlign.Center),
                    new JQTable.Node("月度", "ab", true, TextAlign.Center)
                        .append(new JQTable.Node("逾期0-1个月", "ba"))
                        .append(new JQTable.Node("逾期1-3月", "bb"))
                        .append(new JQTable.Node("逾期3-6月", "bc"))
                        .append(new JQTable.Node("逾期6-12月", "bd"))
                        .append(new JQTable.Node("逾期1年以上", "be"))
                        .append(new JQTable.Node("小计", "bf")),
                    new JQTable.Node("未到期(不含质保金)", "ah"),
                    new JQTable.Node("未到期质保金", "ai"),
                    new JQTable.Node("合计", "aj")
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            tb:string;
        }

        class YSZKKXXZView extends BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("yszkkxxz/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            public static newInstance():YSZKKXXZView {
                return new YSZKKXXZView();
            }

            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "yszkkxxz/export.do?" + Util.Ajax.toUrlParam({
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

            public refresh():void {
                if (this.mData == undefined) {
                    return;
                }

                this.updateTable();
            }

            public init(opt:Option):void {
                super.init(opt);
                view.register("应收账款款项性质情况", this);
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");

                let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
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

        export var pluginView = YSZKKXXZView.newInstance();
    }
}
