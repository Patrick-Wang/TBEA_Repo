/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../wlyddqk/wlyddqkdef.ts" />

declare var echarts;
declare var view:wlyddqk.FrameView;

module wlyddqk {
    export module xlkglydd {
        import TextAlign = JQTable.TextAlign;
        import WlyddType = wlyddqk.WlyddType;
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
                    new JQTable.Node("月产出能力（产值）", "rqa"),
                    new JQTable.Node("可供履约订单总额", "ab"),
                    new JQTable.Node("当年可供履约订单总量", "ac"),
                    new JQTable.Node("n+1月订单量", "ada")
                        .append( new JQTable.Node("已排产", "ba"))
                        .append( new JQTable.Node("未排产", "bc"))
                        .append( new JQTable.Node("产能发挥率", "bb")),
                    new JQTable.Node("n+2月订单量", "adb")
                        .append( new JQTable.Node("已排产", "ba"))
                        .append( new JQTable.Node("未排产", "bc"))
                        .append( new JQTable.Node("产能发挥率", "bb")),
                    new JQTable.Node("n+3月订单量", "adc")
                        .append( new JQTable.Node("已排产", "ba"))
                        .append( new JQTable.Node("未排产", "bc"))
                        .append( new JQTable.Node("产能发挥率", "bb")),
                    new JQTable.Node("n+3月以后履约订单", "ae"),
                    new JQTable.Node("次年及以后可供履约订单排产值", "af"),
                    new JQTable.Node("交货期待定产值", "ag"),
                    new JQTable.Node("外协", "ah")
                ], gridName);
            }
        }

        interface Option extends wlyddqk.PluginOption {
            tb:string;
        }

        class XlkglyddView extends wlyddqk.BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/sbdddcbjpcqk/xlkglydd/update.do", false);
            private tableAssist:JQTable.JQGridAssistant;
            private mDt: string;

            isSupported( compType:Util.CompanyType):boolean{
                if (compType == Util.CompanyType.LLGS || compType == Util.CompanyType.XLC || compType == Util.CompanyType.DLGS){
                    return true;
                }
                return false;
            }

            public static newInstance():XlkglyddView {
                return new XlkglyddView();
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "/BusinessManagement/sbdddcbjpcqk/xlkglydd/export.do?" + Util.Ajax.toUrlParam({
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


            public adjustSize() {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                this.tableAssist && this.tableAssist.resizeHeight(maxTableBodyHeight);

                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
            }

            private createJqassist():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mType);
                return this.tableAssist;
            }

            private updateTable():void {
                this.createJqassist();

                this.tableAssist.create({
                    data: this.mData,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 2000,
                    autoScroll: true
                });

                this.adjustSize();
            }

            //private updateTable():void {
            //    var name = this.option().host + this.option().tb + "_jqgrid_1234";
            //    var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mType);
            //    var parent = this.$(this.option().tb);
            //    parent.empty();
            //    parent.append("<table id='" + name + "'></table>");
            //    this.$(name).jqGrid(
            //        tableAssist.decorate({
            //            multiselect: false,
            //            drag: false,
            //            resize: false,
            //            height: '100%',
            //            width: 1400,
            //            shrinkToFit: true,
            //            autoScroll: true,
            //            rowNum: 20,
            //            data: tableAssist.getData(this.mData),
            //            datatype: "local",
            //            viewrecords : true
            //        }));
            //}
        }

        export var pluginView = XlkglyddView.newInstance();
    }
}
