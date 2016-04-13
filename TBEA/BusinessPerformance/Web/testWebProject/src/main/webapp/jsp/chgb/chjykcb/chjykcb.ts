/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../chgbdef.ts" />

declare var echarts;
declare var view:chgb.FrameView;

module chgb {
    export module chjykcb {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("项目", "rq", true, TextAlign.Center),
                    new JQTable.Node("项目", "rq1", true, TextAlign.Center),
                    new JQTable.Node("上月余额", "a1"),
                    new JQTable.Node("本月新增", "a2"),
                    new JQTable.Node("本月处置", "a3"),
                    new JQTable.Node("期末余额", "a4")
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            tb:string;
        }

        class CHJYKCBView extends BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("chjykcb/update.do", false);
            private mDateSelector:Util.DateSelector;

            public static newInstance():CHJYKCBView {
                return new CHJYKCBView();
            }



            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, cpType:Util.CompanyType):void {
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
                view.register("积压库存表", this);
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                
                let data = [];
                data.push(["积压库存（原值）"].concat(this.mData[0]));
                data.push(["积压库存（原值）"].concat(this.mData[1]));
                data.push(["积压库存（原值）"].concat(this.mData[2]));
                data.push(["积压库存（原值）"].concat(this.mData[3]));

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

        export var pluginView = CHJYKCBView.newInstance();
    }
}
