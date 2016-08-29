/// <reference path="../../../jqgrid/jqassist.ts" />
/// <reference path="../../../util.ts" />
/// <reference path="../../../dateSelector.ts" />
/// <reference path="../jcycljgdef.ts" />

declare var echarts;
declare var view:jcycljg.FrameView;

module jcycljg {
    export module yhjzll {
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq", true),
                    new JQTable.Node("贷款利率（%）", "a1")
                        .append(new JQTable.Node("六个月内", "b1"))
                        .append(new JQTable.Node("六个月至一年", "b2"))
                        .append(new JQTable.Node("一年至三年", "b3")),
                    new JQTable.Node("存款利率（%）", "a2")
                        .append(new JQTable.Node("活期", "b4"))
                        .append(new JQTable.Node("定期半年", "b5"))
                        .append(new JQTable.Node("定期一年", "b6"))
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            ct:string;
            tb:string;
        }

        class YhjzllView extends BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("update.do?type=" + jcycljg.JcycljgType.YHJZLL, false);
            private mDateSelector:Util.DateSelector;

            public static newInstance():YhjzllView {
                return new YhjzllView();
            }

            public getContentType():ContentType{
                return ContentType.TABLE;
            }

            switchDisplayType(type:jcycljg.DisplayType):void {

            }

            public refresh() : void{
                this.updateTable();
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(start:string, end:string):void {
                this.mAjax.get({
                        start: start,
                        end: end
                    })
                    .then((jsonData:any) => {
                        this.mData = jsonData;
                        this.updateTable();
                    });
            }

            public init(opt:Option):void {
                super.init(opt);
                view.register("银行基准利率", this);
            }

            public  getDateType():DateType {
                return DateType.YEAR;
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + name + "pager" + "'></div>");
                this.$(name).jqGrid(
                    tableAssist.decorate({
                        multiselect: false,
                        drag: false,
                        resize: false,
                        height: '100%',
                        width: 800,
                        shrinkToFit: true,
                        autoScroll: true,
                        rowNum: 20,
                        data: tableAssist.getData(this.mData),
                        datatype: "local",
                        viewrecords : true,
                        pager : name + "pager"
                    }));
            }
        }
        export var pluginView = YhjzllView.newInstance();
    }
}
