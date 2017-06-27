/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../wlyddqkdef.ts" />

declare var echarts;
declare var view : wlyddqk.FrameView;

module  wlyddqk {
    export module wlyddmlspcs {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, date : string):JQTable.JQGridAssistant {
                
                let curDate : Date = new Date(Date.parse(date.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
                let data = [];
                let node : JQTable.Node;
                let titleNodes : JQTable.Node[] = [];
                
                node = new JQTable.Node("产品", "wlyddmlspcs_cp", true, TextAlign.Left);
                titleNodes.push(node);
                
                node = new JQTable.Node("上年度", "wlyddmlspcs_snd", true, TextAlign.Center);
                for (let i = month ; i <= 12; ++i){
                    node.append(new JQTable.Node(i + "月", "wlyddmlspcs_snd_" + i));
                }
                
                //if (month != 12) {
                    titleNodes.push(node);
                //}
                
                node = new JQTable.Node("本年度", "wlyddmlspcs_bnd", true, TextAlign.Center);
                for (let i = 1; i <= month; ++i){
                    node.append(new JQTable.Node(i + "月", "wlyddmlspcs_bnd_" + i));
                }               
                titleNodes.push(node);
                
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            }
        }

        interface Option extends wlyddqk.PluginOption {
            tb:string;
        }

        class WLYDDMLSPCSView extends wlyddqk.BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/wlydd/wlyddmlspcs/update.do", false);
            private mDt: string;
            private tableAssist:JQTable.JQGridAssistant;
            public static newInstance():WLYDDMLSPCSView {
                return new WLYDDMLSPCSView();
            }
            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "/BusinessManagement/wlydd/wlyddmlspcs/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId: cpType,
                        type:this.mType
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

            pluginGetUnit():string{
                return "单位：百分比";
            }

            isSupported( compType:Util.CompanyType):boolean{

                if (this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_ZH){
                    if (compType == Util.CompanyType.BYQCY){
                        return true;
                    }
                }

                if (this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_XL_ZH){
                    if (compType == Util.CompanyType.XLCY||
                        compType == Util.CompanyType.SBDCYJT){
                        return true;
                    }
                }

                if (this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_XL_CPFL){
                    if (compType == Util.CompanyType.XLCY){
                        return true;
                    }
                }

                if (this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_ZZY){
                    if (compType == Util.CompanyType.BYQCY){
                        return true;
                    }
                }

                if (this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_ZH ||
                    this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_DYDJ ||
                    this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_CPFL||
                    this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_ZZY){
                    if (compType == Util.CompanyType.SBGS ||
                        compType == Util.CompanyType.HBGS ||
                        compType == Util.CompanyType.XBC ||
                        compType == Util.CompanyType.TBGS
                    ){
                        return true;
                    }
                }else{
                    if (compType == Util.CompanyType.LLGS ||
                        compType == Util.CompanyType.XLC ||
                        compType == Util.CompanyType.DLGS
                    ){
                        return true;
                    }
                }
                return false;
            }

            public init(opt:Option):void {
                super.init(opt);
                view.register("未履约订单毛利水平测算(转型业务口径)", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_ZH));
                //view.register("未履约订单毛利水平测算(制造主业-电压等级口径)", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_DYDJ));
                //view.register("未履约订单毛利水平测算(制造主业-产品类别口径)", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_CPFL));
                view.register("未履约订单毛利水平测算(制造业)", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_ZZY));
                view.register("未履约订单毛利水平测算(转型业务口径)", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_XL_ZH));
                view.register("未履约订单毛利水平测算(制造业)", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_XL_CPFL));
            }



            public adjustSize() {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                this.tableAssist.resizeHeight(maxTableBodyHeight);

                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
            }

            private createJqassist():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mDt);
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
            //    var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mDt);
            //    var parent = this.$(this.option().tb);
            //    parent.empty();
            //    parent.append("<table id='" + name + "'></table>");
            //
            //
            //    this.$(name).jqGrid(
            //        tableAssist.decorate({
            //            multiselect: false,
            //            drag: false,
            //            resize: false,
            //            height: '100%',
            //            width: 1200,
            //            shrinkToFit: true,
            //            autoScroll: true,
            //            rowNum: 100,
            //            data: tableAssist.getData(this.mData),
            //            datatype: "local",
            //            viewrecords : true
            //        }));
            //}
        }

        export var pluginView = WLYDDMLSPCSView.newInstance();
    }
}
