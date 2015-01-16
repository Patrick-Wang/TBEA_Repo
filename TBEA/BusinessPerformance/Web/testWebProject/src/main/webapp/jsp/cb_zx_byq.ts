/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;
module cb_zx_byq {

    class JQGridAssistantFactory {

        private static createSubNode(parent: JQTable.Node): JQTable.Node {
            return parent
                .append(new JQTable.Node("单价", "dj"))
                .append(new JQTable.Node("用量", "yl"));
        }

        public static createMxTable(gridName: string): JQTable.JQGridAssistant {
            var title = ["订单所在单位及项目公司","订单执行阶段","工作号","国别","客户行业类型","合同中标时间 ","产品型号","合同号","订货单位","交货时间","产值","硅钢牌号","硅钢数量","硅钢单价","铜用量","铜单价","铜加工费","变压器油规格","变压器油用量","变压器油单价","钢材用量","钢材单价","纸板用量","纸板单价","五大主材成本","其他材料成本","材料合计","人工制造费用","生产总成本","运费","产值测算毛利额","产值测算毛利率"];

            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                 if (i == 0) {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, JQTable.TextAlign.Left, 90));
                } else  if (i == 6) {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, JQTable.TextAlign.Left, 120));
                } else if (i < 10) {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, JQTable.TextAlign.Left, 80));
                }
                else {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, JQTable.TextAlign.Right, 80));
                }

            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        }

        public static createJttbTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "dw", true, JQTable.TextAlign.Left),
                new JQTable.Node("产值", "cz"),
                new JQTable.Node("产值", "cz_1", true, JQTable.TextAlign.Center),
                new JQTable.Node("毛利额", "mle"),
                new JQTable.Node("毛利率", "mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("硅钢", "gg")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "djt"))
                    .append(new JQTable.Node("加工费", "jgf")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("纸板", "zb")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("变压器油", "byqy")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("钢材", "gc"))
            ], gridName);
        }

        public static createGstbTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("交货时间", "1sj", true, JQTable.TextAlign.Left),
                new JQTable.Node("产值", "1cz"),
                new JQTable.Node("产值", "1cz_1", true, JQTable.TextAlign.Center),
                new JQTable.Node("毛利额", "1mle"),
                new JQTable.Node("毛利率", "1mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("硅钢", "1gg")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "1djt"))
                    .append(new JQTable.Node("加工费", "1jgf")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("纸板", "1zb")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("变压器油", "1byqy")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("钢材", "1gc"))
            ], gridName);
        }
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }


        //		private mfdwData : string[];
        //		private mgwData : string[];
        //		private mnwData : string[];
        private mMxData: string[][];
        private mJtData: string[][];
        private mGsData: string[][];
        private mMonth: number;
        private mMxTableId: string;
        private mJttbTableId: string;
        private mGstbTableId: string;
        private mDataSet : Util.DateDataSet;
        private mComp: Util.CompanyType = Util.CompanyType.SB;
        
        public init(
            mxTableId: string,
            jttbTableId: string,
            gstbTableId: string,
            mx: string[][],
            jt: string[][],
            gs: string[][],
            month: number): void {
            this.mMxTableId = mxTableId;
            this.mJttbTableId = jttbTableId;
            this.mGstbTableId = gstbTableId;
            this.mMxData = mx;
            this.mJtData = jt;
            this.mGsData = gs;
            this.mMonth = month;
            this.mDataSet = new Util.DateDataSet("zx_update.do");
            this.updateMxTable();
            this.updateJttbTable();
            this.updateGstbTable();
            this.updateUI();
        }

        public onCompanySelected(comp : Util.CompanyType){
            this.mComp = comp;
        }
        
        public updateUI(){
            this.mDataSet.getDataByCompany(0, 0, this.mComp, (data : string) =>{
                if (null != data){
                    this.mMxData = JSON.parse(data);
                    this.updateMxTable();
                }
            });
        }

        //        private initEchart(echart): void {
        //            var ysyq_payment_Chart = echarts.init(echart)
        //            var ysyq_payment_Option = {
        //                animation: true,
        //                tooltip: {
        //                    trigger: 'axis',
        //                    /* formatter : "{b}<br/>{a} : {c} 万元<br/>{a1} : {c1} 万元", */
        //
        //                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
        //                        type: 'line'        // 默认为直线，可选为：'line' | 'shadow'
        //                    }
        //                },
        //                legend: {
        //                    x: 'right',
        //                    data: ["合同金额", "预期阶段", "中标阶段", "完工阶段"],
        //
        //                },
        //                xAxis: [{
        //                    type: 'category',
        //                    data: ['沈变', '衡变', '新变', '天变']
        //                }],
        //                yAxis: [{
        //                    type: 'value'
        //
        //                }],
        //
        //                calculable: true,
        //                series: [{
        //                    name: '合同金额',
        //                    type: 'bar',
        //
        //                    barCategoryGap: "50%",
        //                    data: [63363.11, 55628.27, 58521.55, 69100.58]
        //                }, {
        //                        name: '预期阶段',
        //                        type: 'bar',
        //
        //                        stack: '阶段',
        //                        data: [9098.58, 1240.13, 1140.61, 3154.82]
        //                    }, {
        //                        name: '中标阶段',
        //
        //                        type: 'bar',
        //                        stack: '阶段',
        //                        data: [3934.13, 3200.22, 1382.52, 3934.13]
        //                    }, {
        //                        name: '完工阶段',
        //                        type: 'bar',
        //
        //                        stack: '阶段',
        //                        data: [11980.74, 2240.18, 3487.11, 6980.74]
        //                    }]
        //            };
        //            ysyq_payment_Chart.setOption(ysyq_payment_Option);
        //        }

        private updateMxTable(): void {
            var name = this.mMxTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createMxTable(name);
            var data = [[""]];
            var row = [];
            if (this.mMxData != undefined) {
                data = [];
                for (var i = 0; i < this.mMxData.length; ++i) {
                    if (this.mMxData[i] instanceof Array) {
                        row = [].concat(this.mMxData[i]);
                        for (var col in row) {
                            if (col == 10 || col == 13 || col == 15 || col == 18 || col == 19 || col == 20 || col >= 21 && col != 31) {
                                row[col] = Util.formatCurrency(row[col]);
                            } else if (col == 31) {
                                row[col] = (parseFloat(row[col]) * 100).toFixed(2) + "%";
                            }
                        }
                        data.push(row);
                    }
                }
            }


            var parent = $("#" + this.mMxTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");

            $("#" + name).jqGrid(
                tableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: tableAssist.getData(data),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: 250,
                    width: 1250,
                    shrinkToFit: false,
                    autoScroll: true,
                    //                    userData: {
                    //                        'title': "合计"
                    //                    },
                    //                    footerrow: true,
                    //                    userDataOnFooter: true
                }));

        }


        private updateJttbTable(): void {
            var name = this.mJttbTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createJttbTable(name);
            tableAssist.mergeTitle();
            tableAssist.mergeRow(0);
            //  tableAssist.mergeRow(1);
            var data = [
                ["沈变", " ", "中标阶段"],
                ["沈变", " ", "预期阶段"],
                ["衡变", "  ", "中标阶段"],
                ["衡变", "  ", "预期阶段"],
                ["新变", "   ", "中标阶段"],
                ["新变", "   ", "预期阶段"],
                ["总计", "    ", "中标阶段"],
                ["总计", "    ", "预期阶段"]];
            var row = [];

            for (var i = 0; i < 4; ++i) {
                tableAssist.mergeRow(1, i * 2, 2);
            }

            if (this.mJtData != undefined) {
                for (var i = 0; i < this.mJtData.length; ++i) {
                    if (this.mJtData[i] instanceof Array) {
                        row = this.mJtData[i];
                        for (var col in row) {
                            if (col == 0) {
                                data[i][1] = Util.formatCurrency(row[col]);
                            }
                            else {
                                if (2 == col) {
                                    data[i].push((parseFloat(row[col]) * 100).toFixed(2) + "%");
                                }
                                else if (4 != col && 6 != col && 9 != col && 11 != col && 13 != col) {
                                    data[i].push(Util.formatCurrency(row[col]));
                                }

                                else {
                                    data[i].push(parseFloat(row[col]).toFixed(2));
                                }

                            }

                        }
                    }
                }
            }


            //            for (var i = 0; i < data.length; ++i) {
            //                if (rawData[i] instanceof Array) {
            //                    row = [].concat(rawData[i]);
            //                    for (var col in row) {
            //                    	if (col % 2 != 0){
            //                        	row[col] = Util.formatCurrency(row[col]);
            //                        }
            //                                 data[i] = data[i].concat(row);
            //                }
            //            }


            var parent = $("#" + this.mJttbTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");

            $("#" + name).jqGrid(
                tableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: tableAssist.getData(data),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: 1250,
                    shrinkToFit: true,
                    autoScroll: true,
                    //                    userData: {
                    //                        'title': "合计"
                    //                    },
                    //                    footerrow: true,
                    //                    userDataOnFooter: true
                }));

        }

        private updateGstbTable(): void {
            var name = this.mGstbTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createGstbTable(name);
            tableAssist.mergeTitle();
            tableAssist.mergeRow(0);

            var data = [];
            var row = [];
            for (var i = 0; i < this.mMonth; ++i) {
                tableAssist.mergeRow(1, i * 2, 2);
                data.push([i + 1 + "月", "", "中标阶段"]);
                data.push([i + 1 + "月", "", "预期阶段"]);
            }
            tableAssist.mergeRow(1, this.mMonth * 2, 2);
            data.push(["总计", "", "中标阶段"]);
            data.push(["总计", "", "预期阶段"]);

            if (this.mGsData != undefined) {
                for (var i = 0; i < this.mGsData.length; ++i) {
                    if (this.mGsData[i] instanceof Array) {
                        row = this.mGsData[i];
                        for (var col in row) {
                            if (col == 0) {
                                if (i % 2 == 0) {
                                    data[i]['1'] = Util.formatCurrency(row[col]);
                                }

                                else {
                                    data[i]['1'] = "";
                                }
                            }
                            else {
                                if (2 == col) {
                                    data[i].push((parseFloat(row[col]) * 100).toFixed(2) + "%");
                                }
                                else if (4 != col && 6 != col && 9 != col && 11 != col && 13 != col) {
                                    data[i].push(Util.formatCurrency(row[col]));
                                }

                                else {
                                    data[i].push(row[col]);
                                }
                            }

                        }
                    }
                }
            }


            var parent = $("#" + this.mGstbTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            var height : any = 220;
            if (this.mMonth < 4){
                height = "100%";    
            }
            $("#" + name).jqGrid(
                tableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: tableAssist.getData(data),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: height,
                    width: 1250,
                    rowNum: 200,
                    shrinkToFit: true,
                    autoScroll: true,
                    //                    userData: {
                    //                        'title': "合计"
                    //                    },
                    //                    footerrow: true,
                    //                    userDataOnFooter: true
                }));

        }
    }
}