/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../chgbdef.ts"/>

module plugin {
    export let chnych : number = framework.basic.endpoint.lastId();
}

module chgb {
    export module chnych {
        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月度", "chnych_yd", true, TextAlign.Center),
                    new JQTable.Node("月度", "chnych_ydtwo", true, TextAlign.Center),
                    new JQTable.Node("原材料", "chnych_ycl"),
                    new JQTable.Node("库存商品", "chnych_kcsp"),
                    new JQTable.Node("生产成本-待配比土方", "chnych_tf"),
                    new JQTable.Node("发出商品", "chnych_fcsp"),
                    new JQTable.Node("低耗", "chnych_dh"),
                    new JQTable.Node("合计", "chnych_hj")
                ], gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/chgb/chnych/update.do", false);
            private mDt: string;
            private mCompType:Util.CompanyType;

            private tableAssist:JQTable.JQGridAssistant;
            getId():number {
                return plugin.chnych;
            }

            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "/BusinessManagement/chgb/chnych/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId: cpType
                    });
            }

            protected isSupported(compType:Util.CompanyType):boolean {
                if (compType == Util.CompanyType.TCNY || compType == Util.CompanyType.NDGS){
                    return true;
                }
                return false;
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, cpType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = cpType;
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
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "能源存货");
            }


            private createJqassist():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());

                this.tableAssist.mergeRow(0);
                this.tableAssist.mergeTitle();
                this.tableAssist.mergeColum(0);
                return this.tableAssist;
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

            private updateTable():void {
                this.createJqassist();


                let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
                let year = curDate.getFullYear();


                let data = [];
                data.push([year + "年期初结余", year + "年期初结余"].concat(this.mData[12]));

                for (let i = month + 1; i <= 12; ++i){
                    data.push(["上年度", i + "月"].concat(this.mData[i - month - 1]));
                }
                for (let i = 1; i <= month; ++i){
                    data.push(["本年度", i + "月"].concat(this.mData[12 - month + i - 1]));
                }


                this.tableAssist.create({
                    data: data,
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
        }
    }
}
