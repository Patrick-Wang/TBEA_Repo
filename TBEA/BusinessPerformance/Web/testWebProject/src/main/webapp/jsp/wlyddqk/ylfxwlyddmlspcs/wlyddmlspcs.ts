/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../wlyddqkdef.ts" />

declare var echarts;
declare var view:wlyddqk.FrameView;

module  ylfxwlyddmlspcs {
    export module wlyddmlspcs {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                
                let curDate : Date = new Date();
                let month = curDate.getMonth() + 1;
                let data = [];
                let node : JQTable.Node;
                let titleNodes : JQTable.Node[] = [];
                
                node = new JQTable.Node("产品", "wlyddmlspcs_cp", true, TextAlign.Center);
                titleNodes.push(node);
                
                node = new JQTable.Node("上年度", "wlyddmlspcs_snd", true, TextAlign.Center);
                for (let i = month + 1; i <= 12; ++i){
                    node.append(new JQTable.Node(i + "月", "wlyddmlspcs_snd_" + i));
                }
                titleNodes.push(node);
                
                node = new JQTable.Node("本年度", "wlyddmlspcs_bnd", true, TextAlign.Center);
                for (let i = 1; i <= month; ++i){
                    node.append(new JQTable.Node(i + "月", "wlyddmlspcs_bnd_" + i));
                }               
                titleNodes.push(node);
                
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            }
        }

        interface Option extends PluginOption {
            tb:string;
        }

        class WLYDDMLSPCSView extends BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("wlyddmlspcs/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            
            public static newInstance():WLYDDMLSPCSView {
                return new WLYDDMLSPCSView();
            }
            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "wlyddmlspcs/export.do?" + Util.Ajax.toUrlParam({
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
                        companyId: cpType,
                        type:this.mType
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
                view.register("变压器未履约订单毛利水平测算-综合", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddqkType.YLFX_WLYMLSP_BYQ_ZH));
                view.register("变压器未履约订单毛利水平测算-电压等级", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddqkType.YLFX_WLYMLSP_BYQ_DYDJ));
                view.register("变压器未履约订单毛利水平测算-产品分类", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddqkType.YLFX_WLYMLSP_BYQ_CPFL));
                view.register("线缆未履约订单毛利水平测算-综合", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddqkType.YLFX_WLYMLSP_XL_ZH));
                view.register("线缆未履约订单毛利水平测算-产品分类", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddqkType.YLFX_WLYMLSP_XL_CPFL));

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

        export var pluginView = WLYDDMLSPCSView.newInstance();
    }
}
