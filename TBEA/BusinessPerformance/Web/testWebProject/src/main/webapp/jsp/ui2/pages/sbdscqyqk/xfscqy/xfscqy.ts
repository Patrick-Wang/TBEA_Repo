/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../sbdscqyqkdef.ts"/>

module plugin {
    export let xfscqy : number = framework.basic.endpoint.lastId();
}

module sbdscqyqk {
    export module xfscqy {
        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
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
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/xfscqy/update.do", false);
            private tableAssist:JQTable.JQGridAssistant;
            private mDt: string;
            private mFinalData:any;

            getId():number {
                return plugin.xfscqy;
            }
            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "/BusinessManagement/xfscqy/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId: cpType
                    });
            }
            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
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

                this.$(this.option().ctarea).show();
                this.mFinalData = this.updateTable()
                this.updateEchart(this.mFinalData);
                this.adjustSize();
            }

            private getMonth():number{
                let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
                return month;
            }

            private updateEchart(data:any[]):void {
                let title = "行业领域签约趋势";

                let legend:Array<string> = [
                    "传统电力市场",
                    "新能源市场",
                    "重点领域市场",
                    "其它"];

                var xData:any[] = [];

                let month = this.getMonth();
                for (let i = month; i <= 12; ++i) {
                    xData.push(i + "月");
                }

                for (let i = 1; i <= month; ++i) {
                    xData.push(i + "月");
                }


                let tooltip : any = {
                    trigger: 'axis',

                };

                let yAxis : any = [
                    {
                        type: 'value',
                    }
                ];

                let series = [];
                for (let i = 0; i < legend.length; ++i){
                    let rData : any = [0,0,0,0,0,0,0,0,0,0,0,0,0];
                    for (let j = 0; j < data.length; ++j){
                        if (legend[i] == data[j][0]){
                            for (let k = 0; k < 13; ++k){
                                rData[k] += data[j][k + 2] == "--" ? 0 : parseFloat(data[j][k + 2]);
                            }
                        }
                    }
                    for (let k = 0; k < 13; ++k){
                        rData[k] = rData[k].toFixed(1);
                    }
                    series.push({
                        name: legend[i],
                        type: 'line',
                        smooth: true,
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: rData
                    });
                }


                var option = {
                    title: {
                        text: title
                    },
                    tooltip: tooltip,
                    legend: {
                        data: legend
                    },
                    toolbox: {
                        show: true,
                    },
                    calculable: false,
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: xData.length < 1 ? [0] : xData
                        }
                    ],
                    yAxis: yAxis,
                    series: series
                };

                echarts.init(this.$(this.option().ct)[0]).setOption(option);

            }

            public init(opt:Option):void {
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "细分市场签约（国内市场制造业签约）");
            }

            adjustSize() {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                //let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                //this.tableAssist.resizeHeight(maxTableBodyHeight);

                //if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                //    jqgrid.setGridWidth(this.jqgridHost().width());
                //}

                this.$(this.option().ct).css("height", "300px");
                this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                this.updateEchart(this.mFinalData);
            }

            private createJqassist():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(),this.mDt);
                this.tableAssist.mergeColum(0);
                this.tableAssist.mergeRow(0);
                this.tableAssist.mergeTitle();
                return this.tableAssist;
            }

            private updateTable():any {
                this.createJqassist();

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

                this.tableAssist.create({
                    data: dOut,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 2000,
                    autoScroll: true
                });

                return data;
            }
        }
    }
}
