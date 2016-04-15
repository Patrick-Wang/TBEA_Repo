/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../sbdddcbjpcqkdef.ts" />

declare var echarts;
declare var view:sbdddcbjpcqk.FrameView;

module sbdddcbjpcqk {
    export module xlkglydd {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, type:KglyddType):JQTable.JQGridAssistant {

                let nodeFirst : JQTable.Node;
                if (type == KglyddType.SCDY){
                    nodeFirst = new JQTable.Node("生产单元", "scdy", true, TextAlign.Center)
                }else{
                    nodeFirst = new JQTable.Node("产品类别", "sclb", true, TextAlign.Center)
                }

                return new JQTable.JQGridAssistant([
                    nodeFirst,
                    new JQTable.Node("月产出能力（产值）", "rqa"),
                    new JQTable.Node("所有可供履约订单总量产值", "ab"),
                    new JQTable.Node("当年可供履约订单总量产值", "ac"),
                    new JQTable.Node("其中：当季度排产订单", "ad")
                        .append( new JQTable.Node("产值", "ba"))
                        .append( new JQTable.Node("产能发挥率", "bb")),
                    new JQTable.Node("其中：下季度排产订单", "ae")
                        .append( new JQTable.Node("产值", "bc"))
                        .append( new JQTable.Node("产能发挥率", "bd")),
                    new JQTable.Node("次年及以后可供履约订单排产值", "ae"),
                    new JQTable.Node("交货期待定产值", "af")
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            tb:string;
        }

        class XlkglyddView extends BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("xlkglydd/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;

            public static newInstance():XlkglyddView {
                return new XlkglyddView();
            }

            pluginGetExportUrl(date:string):string {
                return "xlkglydd/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        type: this.mType
                    });
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string):void {
                this.mDt = date;
                this.mAjax.get({
                        type:this.mType,
                        date: date
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
                view.register("线缆可供履约订单变化情况按生产类别", new TypeViewProxy(this, KglyddType.SCLB));
                view.register("线缆可供履约订单变化情况按生产单元", new TypeViewProxy(this, KglyddType.SCDY));
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

        export var pluginView = XlkglyddView.newInstance();
    }
}