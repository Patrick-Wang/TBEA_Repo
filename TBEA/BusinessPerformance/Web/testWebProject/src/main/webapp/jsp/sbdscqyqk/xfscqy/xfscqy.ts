/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../sbdscqyqkdef.ts"/>

module plugin {
    export let xfscqy : number = framework.basic.endpoint.lastId();
}

module sbdscqyqk {
    export module xfscqy {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, date:string):JQTable.JQGridAssistant {

                let curDate:Date = new Date(Date.parse(date.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
                let data = [];
                let node:JQTable.Node;
                let titleNodes:JQTable.Node[] = [];

                node = new JQTable.Node("行业", "hy1", true, TextAlign.Center);
                titleNodes.push(node);
                node = new JQTable.Node("行业", "hy2", true, TextAlign.Center);
                titleNodes.push(node);

                node = new JQTable.Node("上年度", "snd", true, TextAlign.Center);
                for (let i = month; i <= 12; ++i) {
                    node.append(new JQTable.Node(i + "月", "snd_" + i));
                }

                titleNodes.push(node);
                //if (month != 12) {
                //    titleNodes.push(node);
                //}

                node = new JQTable.Node("本年度", "wlyddmlspcs_bnd", true, TextAlign.Center);
                for (let i = 1; i <= month; ++i) {
                    node.append(new JQTable.Node(i + "月", "bnd_" + i));
                }
                titleNodes.push(node);

                return new JQTable.JQGridAssistant(titleNodes, gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("../xfscqy/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            private mCompType:Util.CompanyType;

            getId():number {
                return plugin.xfscqy;
            }

            pluginGetUnit():string{
                return "单位：万元";
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "../xfscqy/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId:compType
                    });
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = compType;
                this.mAjax.get({
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
                framework.router.fromEp(this).to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_REGISTER, "细分市场签约（国内市场制造业签约）");
            }

			private getMonth():number{
				let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
				return month;
			}
			
            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mDt);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");

                let data = [["传统电力市场"],
                    ["传统电力市场"],
                    ["传统电力市场"],
                    ["传统电力市场"],
                    ["新能源市场"],
                    ["新能源市场"],
                    ["新能源市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["连锁经营"],
                    ["其它"],
                    ["合计"]];
                for (let i = 0; i < data.length; ++i){
                    if (i == data.length - 3){
                        data[i] = data[i].concat(this.mData[i + 1]);
                    }else if(i == data.length - 2){
                        data[i] = data[i].concat(this.mData[i - 1]);
                    }else{
                        data[i] = data[i].concat(this.mData[i]);
                    }
                }
                let dOut = [];
                let vec = new std.vector();
                vec.push(-1);
                var formaterChain: Util.FormatHandler = new Util.FormatFordotHandler(1, []);
                var row = [];
                for (var j = 0; j < data.length; ++j) {
                    row = [].concat(data[j]);
                    for (var i = 2; i < row.length; ++i) {
                        row[i] = formaterChain.handle(row[0], i, row[i]);
                    }
                    dOut.push(row);
                }
                tableAssist.mergeColum(0);
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
                        rowNum: 1000,
                        data: tableAssist.getData(dOut),
                        datatype: "local",
                        viewrecords : true
                    }));
            }
        }
    }
}
