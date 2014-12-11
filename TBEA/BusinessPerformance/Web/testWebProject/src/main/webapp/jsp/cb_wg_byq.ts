/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;
module cb_wg_byq {

    class JQGridAssistantFactory {

        private static createSubNode(parent: JQTable.Node): JQTable.Node {
            return parent
                .append(new JQTable.Node("单价", "dj"))
                .append(new JQTable.Node("用量", "yl"));
        }

        public static createMxTable(gridName: string): JQTable.JQGridAssistant {
            var title = ["工作号","完工时间","产值","实际硅钢片用量 ",
            "实际硅钢片单价 ","实际电解铜用量 ","实际电解铜单价（无税含加工费） ",
            "加工费(含税) "," 实际变压器油用量 "," 实际变压器油单价 ","实际钢材用量 ",
            "实际钢材单价 "," 实际绝缘纸板用量 "," 实际绝缘纸板单价 ","实际五大主材成本 ",
            "实际其他材料成本合计 ","实际材料成本总计 ","实际人工制造费用 ",
            "实际总成本 ","运费 ","实际毛利额 ","实际毛利率"];

            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i < 2){
                     nodes.push(new JQTable.Node(title[i], "Mx" + i, true, JQTable.TextAlign.Left));
                    }else{
                     nodes.push(new JQTable.Node(title[i], "Mx" + i));
                    }
               
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        }

        public static createJttbTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("完工时间", "wgsj", true, JQTable.TextAlign.Left),
                new JQTable.Node("单位", "dw", true, JQTable.TextAlign.Left),
                new JQTable.Node("合同金额", "htje"),
                new JQTable.Node("合同金额", "htje_1", true, JQTable.TextAlign.Center),
                new JQTable.Node("毛利额", "mle"),
                new JQTable.Node("毛利率", "mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("硅钢", "gg")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "djt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("纸板", "zb")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("变压器油", "byqy")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("钢材", "gc"))
            ], gridName);
        }

        public static createGstbTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "1dw", true, JQTable.TextAlign.Left),
                new JQTable.Node("完工时间", "1wgsj", true, JQTable.TextAlign.Left),
                new JQTable.Node("合同金额", "1htje"),
                new JQTable.Node("合同金额", "1htje_1", true, JQTable.TextAlign.Center),
                new JQTable.Node("毛利额", "1mle"),
                new JQTable.Node("毛利率", "1mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("硅钢", "1gg")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "1djt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("纸板", "1zb")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("变压器油", "1byqy")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("钢材", "1gc"))
            ], gridName);
        }
        
        public static createFdyTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("完工时间", "2wgsj", true, JQTable.TextAlign.Left),
                new JQTable.Node("电压等级", "2dydj", true, JQTable.TextAlign.Left),
                new JQTable.Node("合同金额", "2htje"),
                new JQTable.Node("合同金额", "2htje_1", true, JQTable.TextAlign.Center),
                new JQTable.Node("毛利额", "2mle"),
                new JQTable.Node("毛利率", "2mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("硅钢", "2gg")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "2djt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("纸板", "2zb")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("变压器油", "2byqy")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("钢材", "2gc"))
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
         private mBtdyData: string[][];
        private mMonth: number;
                private mYear: number;
         private mDataSet : Util.DateDataSet;
        private mMxTableId: string;
        private mJttbTableId: string;
        private mGstbTableId: string;
        private mFdyTableId: string;
        public init(
            mxTableId: string,
            jttbTableId: string,
            gstbTableId: string,
            fdyTableId : string,
              mx: string[][],
            jt: string[][],
            gs: string[][],
             btdy: string[][],
            month: number,
            year: number): void {
            this.mMxTableId = mxTableId;
            this.mJttbTableId = jttbTableId;
            this.mGstbTableId = gstbTableId;
            this.mFdyTableId = fdyTableId;
    this.mDataSet = new Util.DateDataSet("wg_update.do");
            this.mMxData = mx;
            this.mJtData = jt;
            this.mGsData = gs;
            this.mBtdyData = btdy;
            this.mMonth = month;
             this.mYear = year;
            this.updateMxTable();
            this.updateJttbTable();
            this.updateGstbTable();
            this.updateFdyTable();
        }

        
          public onYearSelected(year : number){
            this.mYear = year;
        }
        
        public onMonthSelected(month : number){
            this.mMonth = month;
        }
        
        public updateUI(){
            this.mDataSet.getData(this.mMonth, this.mYear, (dataArray : Array<string[]>) =>{
                if (null != dataArray){
                    var data : any = dataArray[0];
                    this.mJtData = data;
                    data = dataArray[1];
                    this.mBtdyData = data;

                    this.updateJttbTable();
                    this.updateFdyTable();
//                    this.mData = dataArray;
//                    $('h1').text(this.mYear + "年" + this.mMonth + "月 各产业指标汇总");
//                    document.title = this.mYear + "年" + this.mMonth + "月 各产业指标汇总";
//                    this.updateTable();
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
                            if (21 == col) {
                                row[col] = (parseFloat(row[col]) * 100).toFixed(2) + "%";
                            } else if (col != 0 && col != 1 && col != 3 && col != 5 && col != 8 && col != 10 && col != 12) {
                                row[col] = Util.formatCurrency(row[col]);
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
                    rowNum:10000,
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
            tableAssist.mergeColum(0, 9);
            tableAssist.mergeRow(0);
            tableAssist.mergeRow(1);
            tableAssist.mergeRow(2, 0, 3);
            tableAssist.mergeRow(2, 3, 3);
            tableAssist.mergeRow(2, 6, 3);
            tableAssist.mergeRow(2, 9, 3);
            var data = [
                [this.mMonth + "月","沈变","","中标阶段"],
                [this.mMonth + "月","沈变","","预期阶段"],
                [this.mMonth + "月","沈变","","完工阶段"],
                [this.mMonth + "月","衡变"," ","中标阶段"],
                [this.mMonth + "月","衡变"," ","预期阶段"],
                [this.mMonth + "月","衡变"," ","完工阶段"],
                [this.mMonth + "月","新变","","中标阶段"],
                [this.mMonth + "月","新变","","预期阶段"],
                [this.mMonth + "月","新变","","完工阶段"],
                [this.mYear + "年" + this.mMonth,"月小计"," ","中标阶段"],
                [this.mYear + "年" + this.mMonth, "月小计", " ", "预期阶段"],
                [this.mYear + "年" + this.mMonth, "月小计", " ", "完工阶段"]];
            for (var i = 0; i < this.mJtData.length; ++i) {
                if (this.mJtData[i] instanceof Array) {
                    for (var col in this.mJtData[i]) {
                        if (col == 0){
                            data[i][2] = Util.formatCurrency(this.mJtData[i][col]);
                        }
                        else if ( col % 2 == 1){
                            data[i].push(Util.formatCurrency(this.mJtData[i][col]));
                            }
                        else if (col == 2){
                            data[i].push((parseFloat(this.mJtData[i][col]) * 100.0).toFixed(2) + "%");
                        }
                        else {
                            data[i].push(parseFloat(this.mJtData[i][col]).toFixed(2));
                        }       
                    }
                }
            }
            
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
            tableAssist.mergeColum(0, 15);
            tableAssist.mergeRow(0);
            tableAssist.mergeRow(1);
            tableAssist.mergeRow(2);
            var data = [["XX公司","1月","","中标阶段"],
                        ["XX公司","1月","","预期阶段"],
                        ["XX公司","1月","","完工阶段"],
                        ["XX公司","2月"," ","中标阶段"],
                        ["XX公司","2月"," ","预期阶段"],
                        ["XX公司","2月"," ","完工阶段"],
                        ["XX公司","3月","","中标阶段"],
                        ["XX公司","3月","","预期阶段"],
                        ["XX公司","3月","","完工阶段"],
                        ["XX公司","4月"," ","中标阶段"],
                        ["XX公司","4月"," ","预期阶段"],
                        ["XX公司","4月"," ","完工阶段"],
                        ["XX公司","5月","","中标阶段"],
                        ["XX公司","5月","","预期阶段"],
                        ["XX公司","5月","","完工阶段"],
                        ["X月","小计"," ","中标阶段"],
                        ["X月","小计"," ","预期阶段"],
                        ["X月","小计"," ","完工阶段"]];
            var row = [];
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
            
            
            var parent = $("#" + this.mGstbTableId);
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
        
        private updateFdyTable(): void {
            var name = this.mFdyTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createFdyTable(name);
            tableAssist.mergeTitle();
            tableAssist.mergeColum(0, 18);
            tableAssist.mergeRow(0);
            tableAssist.mergeRow(1);
            for (var i = 0; i < 7; ++i){
                tableAssist.mergeRow(2, i * 3, 3);
            }
            
            var data = [[this.mMonth + "月","110KV","","中标阶段"],
                        [this.mMonth + "月","110KV","","预期阶段"],
                        [this.mMonth + "月","110KV","","完工阶段"],
                        [this.mMonth + "月","220KV"," ","中标阶段"],
                        [this.mMonth + "月","220KV"," ","预期阶段"],
                        [this.mMonth + "月","220KV"," ","完工阶段"],
                        [this.mMonth + "月","330KV","","中标阶段"],
                        [this.mMonth + "月","330KV","","预期阶段"],
                        [this.mMonth + "月","330KV","","完工阶段"],
                        [this.mMonth + "月","500KV"," ","中标阶段"],
                        [this.mMonth + "月","500KV"," ","预期阶段"],
                        [this.mMonth + "月","500KV"," ","完工阶段"],
                        [this.mMonth + "月","直流","","中标阶段"],
                        [this.mMonth + "月","直流","","预期阶段"],
                        [this.mMonth + "月","直流","","完工阶段"],
                        [this.mMonth + "月","电抗器"," ","中标阶段"],
                        [this.mMonth + "月","电抗器"," ","预期阶段"],
                        [this.mMonth + "月","电抗器"," ","完工阶段"],
                        [this.mYear + "年" + this.mMonth,"月小计","","中标阶段"],
                        [this.mYear + "年" + this.mMonth,"月小计","","预期阶段"],
                        [this.mYear + "年" + this.mMonth,"月小计","","完工阶段"]];
          for (var i = 0; i < this.mBtdyData.length; ++i) {
                if (this.mBtdyData[i] instanceof Array) {
                    for (var col in this.mBtdyData[i]) {
                        if (col == 0){
                            data[i][2] = Util.formatCurrency(this.mBtdyData[i][col]);
                        }
                        else if ( col % 2 == 1){
                            data[i].push(Util.formatCurrency(this.mBtdyData[i][col]));
                            }
                        else if (col == 2){
                            data[i].push((parseFloat(this.mBtdyData[i][col]) * 100.0).toFixed(2) + "%");
                        }
                        else {
                            data[i].push(parseFloat(this.mBtdyData[i][col]).toFixed(2));
                        }       
                    }
                }
            }
            
            
            var parent = $("#" + this.mFdyTableId);
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