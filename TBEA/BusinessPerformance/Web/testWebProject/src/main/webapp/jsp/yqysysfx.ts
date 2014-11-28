/// <reference path="jqgrid/jqassist.ts" />
declare var echarts;

module yqysysfx {

    class JQGridAssistantFactory {
        public static createTable(gridName): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("因素分类", "ysfl", true, JQTable.TextAlign.Left),
                new JQTable.Node("因素分类", "ysfl_1", true, JQTable.TextAlign.Left),
                new JQTable.Node("内部因素", "nbys", true, JQTable.TextAlign.Left),
                new JQTable.Node("客户资信", "khzx", true, JQTable.TextAlign.Left),
                new JQTable.Node("滚动付款", "gdfk", true, JQTable.TextAlign.Left),
                new JQTable.Node("项目变化", "xmbh", true, JQTable.TextAlign.Left),
                new JQTable.Node("合同因素", "htys", true, JQTable.TextAlign.Left),
                new JQTable.Node("手续办理", "sxbl", true, JQTable.TextAlign.Left),
                new JQTable.Node("其它", "qt", true, JQTable.TextAlign.Left)
            ], gridName);
        }
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }

        
        private mData: Array<string[]> = [];
        private mDataSet : Util.DateDataSet;
        private mTableId : string;
        private mComp: Util.CompanyType = Util.CompanyType.JT;
        
        public init(echartId: string, tableId: string): void {
           // this.initEchart($('#' + echartId)[0]);
            this.mTableId = tableId;
            this.mDataSet = new Util.DateDataSet("yqysysfx_update.do");
            this.updateTable();
            this.updateUI();
        }
        
        public updateUI(){
            this.mDataSet.getDataByCompany(1, 2014, this.mComp, (dataArray : string) =>{
                if (null != dataArray){
                    this.mData = JSON.parse(dataArray);
//                    $('h1').text(this.mYear + "年" + this.mMonth + "月" + this.mDay + "日 现金流日报");
//                    document.title = this.mYear + "年" + this.mMonth + "月" + this.mDay + "日 现金流日报";
                    this.updateTable();
                }
            });
        }

        public onCompanySelected(comp : Util.CompanyType){
            this.mComp = comp;
        }
        
        private initEchart(echart): void {
            var ysyq_payment_Chart = echarts.init(echart)
            var ysyq_payment_Option = {
                animation: true,
                tooltip: {
                    trigger: 'axis',
                    /* formatter : "{b}<br/>{a} : {c} 万元<br/>{a1} : {c1} 万元", */

                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'line'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                legend: {
                    x: 'right',
                    data: ["合同金额", "预期阶段", "中标阶段", "完工阶段"],

                },
                xAxis: [{
                    type: 'category',
                    data: ['沈变', '衡变', '新变', '天变']
                }],
                yAxis: [{
                    type: 'value'

                }],

                calculable: true,
                series: [{
                    name: '合同金额',
                    type: 'bar',

                    barCategoryGap: "50%",
                    data: [63363.11, 55628.27, 58521.55, 69100.58]
                }, {
                        name: '预期阶段',
                        type: 'bar',

                        stack: '阶段',
                        data: [9098.58, 1240.13, 1140.61, 3154.82]
                    }, {
                        name: '中标阶段',

                        type: 'bar',
                        stack: '阶段',
                        data: [3934.13, 3200.22, 1382.52, 3934.13]
                    }, {
                        name: '完工阶段',
                        type: 'bar',

                        stack: '阶段',
                        data: [11980.74, 2240.18, 3487.11, 6980.74]
                    }]
            };
            ysyq_payment_Chart.setOption(ysyq_payment_Option);
        }

        private updateTable(): void {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
            tableAssist.mergeRow(0);
            tableAssist.mergeTitle();
            var data = [
                ["总数量","户数"],
                ["总数量","金额"],
                ["其中：法律手段清收","户数"],
                ["其中：法律手段清收","金额"]];

            if (this.mData != null) {
                var row = [];
                for (var i = 0; i < data.length; ++i) {
                    row = [].concat(this.mData[i]);
                    if (0 != i % 2) {
                        for (var col in row) {
                            row[col] = Util.formatCurrency(row[col]);
                        }
                    }

                    data[i] = data[i].concat(row);
                }
            }

            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='"+ name +"'></table>");
            
                
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
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true
                }));

        }
    }
}