/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../chgbdef.ts" />

declare var echarts;
declare var view:chgb.FrameView;

module chgb {
    export module chxzqk {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月度", "chxzqk_yd", true, TextAlign.Center),
                    new JQTable.Node("月度", "chxzqk_ydtwo", true, TextAlign.Center),
                    new JQTable.Node("原材料", "chxzqk_ycl"),
                    new JQTable.Node("半成品", "chxzqk_bcp"),
                    new JQTable.Node("实际库存商品", "chxzqk_sjkcsp"),
                    new JQTable.Node("已发货未开票", "chxzqk_yfhwkp"),
                    new JQTable.Node("期货浮动盈亏(盈+，亏-)", "chxzqk_qhfdyk"),
                    new JQTable.Node("期货平仓盈亏(盈-，亏+)", "chxzqk_qhpc"),
                    new JQTable.Node("未发货已开票", "chxzqk_wfhykp"),
                    new JQTable.Node("其他", "chxzqk_qt"),
                    new JQTable.Node("合计", "chxzqk_hj")
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            tb:string;
        }

        class CHXZQKView extends BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("chxzqk/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            
            public static newInstance():CHXZQKView {
                return new CHXZQKView();
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
                view.register("存货性质情况", this);
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

        export var pluginView = CHXZQKView.newInstance();
    }
}
