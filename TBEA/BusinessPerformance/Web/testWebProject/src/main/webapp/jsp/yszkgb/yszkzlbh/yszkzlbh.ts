/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../yszkgbdef.ts" />

declare var echarts;
declare var view:yszkgb.FrameView;

module yszkgb {
    export module yszkzlbh {
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("账面净额", "rq"),
                    new JQTable.Node("坏账准备", "a1"),
                    new JQTable.Node("原值", "a2")
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            tb:string;
        }

        class YSZKZLBHView extends BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("yszkzlbh/update.do", false);
            private mDateSelector:Util.DateSelector;

            public static newInstance():YSZKZLBHView {
                return new YSZKZLBHView();
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
                view.register("应收账款账龄变化", this);
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
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
                        data: tableAssist.getData(this.mData),
                        datatype: "local",
                        viewrecords : true
                    }));
            }
        }

        export var pluginView = YSZKZLBHView.newInstance();
    }
}
