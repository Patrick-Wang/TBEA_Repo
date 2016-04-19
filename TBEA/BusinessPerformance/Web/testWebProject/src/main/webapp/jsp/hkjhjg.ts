/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module hkjhjg {

    class JQGridAssistantFactory {

        public static createJGTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("逾期应收账款", "yqyszk", true, JQTable.TextAlign.Center),
                new JQTable.Node("逾期应收账款", "yqyszk_1"),
                new JQTable.Node("逾期款", "yqk", true, JQTable.TextAlign.Center),
                new JQTable.Node("逾期款", "yqk_1"),
                new JQTable.Node("未到期应收账款", "wdqyszk", true, JQTable.TextAlign.Center),
                new JQTable.Node("未到期应收账款", "wdqyszk_1"),
                new JQTable.Node("未到期款", "wdqk", true, JQTable.TextAlign.Center),
                new JQTable.Node("未到期款", "wdqk_1"),
                new JQTable.Node("小计", "xj", true, JQTable.TextAlign.Center),
                new JQTable.Node("小计", "xj_1")
            ], gridName);
        }
        
        public static createZTTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("到期款", "dqk"),
                new JQTable.Node("未到期款", "wdqk"),
                new JQTable.Node("未到期应收账款", "wdqyszk"),
                new JQTable.Node("逾期应收账款", "yqyszk"),
                new JQTable.Node("合计", "hj")
            ], gridName);
        }
        
         public static createXZTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("确保可回款", "qbkhk"),
                new JQTable.Node("争取可回款", "zqkhk"),
                new JQTable.Node("下月清收款", "xyqsk"),
                new JQTable.Node("隔月清收款", "gyqsk"),
                new JQTable.Node("合计", "hj")
            ], gridName);
        }
    }

	enum HKJHType{
		JG,
		ZT,
		XZ
	}

    export class View {
        public static newInstance(): View {
            return new View();
        }
        private mComp : Util.CompanyType = Util.CompanyType.HBGS;
        private mMonth: number;
        private mYear: number;
        private mJGData: Array<string[]>;
        private mZTData: Array<string>;
        private mXZData: Array<string>;
        private mDataSet : Util.Ajax = new Util.Ajax("hkjhjg_update.do");
        private mTableIds: string[];
        private mEchartId;
        public init(echartId: string, tableId: string[], month: number, year: number): void {
            this.mYear = year;
            this.mMonth = month;
            this.mTableIds = tableId;
            this.mEchartId = echartId;
            this.updateJGTable();
            this.updateZTTable();
            this.updateXZTable();
            this.updateUI();
        }
        
        public onYearSelected(year : number){
        	this.mYear = year;
        }
        
        public onMonthSelected(month : number){
        	this.mMonth = month;
        }
        
        public onCompanySelected(comp : Util.CompanyType){
        	this.mComp = comp;
        }
        
        public updateUI() {
            this.mDataSet.get({ month: this.mMonth, year: this.mYear, companyId: this.mComp })
                .then((data: any) => {

                    this.mJGData = data[0];

                    this.mZTData = data[1][0];

                    this.mXZData = data[2][0];

                    $('h1').text(this.mYear + "年" + this.mMonth + "月 回款计划结构明细");
                    document.title = this.mYear + "年" + this.mMonth + "月 回款计划结构明细";
                    this.updateJGTable();
                    this.updateZTTable();
                    this.updateXZTable();
                    this.updateEchart();

                });
        }
        private updateEchart(): void {
            var hkjhjgChart = echarts.init($("#" + this.mEchartId)[0]);
            var legend = ["确保可回逾期应收账款", "确保可回逾期款", "确保可回未到期应收账款"
                          , "确保可回未到期款", "争取可回逾期应收账款", "争取可回逾期款"
                          , "争取可回未到期应收账款", "争取可回未到期款"];
            var legendNew = [];
            var dataOut = [];
            var qbTotal = 0;
            var zqTotal = 0;
            var val : any = null;
            for (var i = 0; i < legend.length - 4; ++i) {
                val = parseFloat(this.mJGData[1][i]).toFixed(2);
                if (val > 0){
                    dataOut.push({ name: legend[i], value: val });
                    legendNew.push(legend[i]);
                }
            }
            
            for (var i = 4; i < legend.length; ++i) {
                val = parseFloat(this.mJGData[2][i - 4]).toFixed(2);
                if (val > 0){
                    dataOut.push({ name: legend[i], value: val });
                    legendNew.push(legend[i]);
                }
            }
            
            
            var dataIn = [];
            val = { name: "确保", value: parseFloat(this.mXZData[0]).toFixed(2) };
            if (val.value > 0){
                dataIn.push({ name: "确保", value: parseFloat(this.mXZData[0]).toFixed(2) });
            }
            val = { name: "确保", value: parseFloat(this.mXZData[1]).toFixed(2) };
            if (val.value > 0){
                dataIn.push({ name: "争取", value: parseFloat(this.mXZData[1]).toFixed(2) });
            }
            
//          [{ name: "确保", value: qbTotal },
//          { name: "争取", value: zqTotal }];

            var hkjhjgOption = {
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    x: "left",
                    data: legendNew,
                    orient: "vertical"
                },
                toolbox: {
                    show: true,
                },
                calculable: false,
                series: [
                    {
                        name: "款项名称",
                        type: 'pie',
                        radius: [100, 130],
                        data: dataOut
                    }, {
                        name: "款项名称",
                        type: 'pie',
                        radius: [0, 60],
                         itemStyle : {
			                normal : {
			                    label : {
			                        position : 'inner'
			                    },
			                    labelLine : {
			                        show : false
			                    }
			                }
		                },
                        data: dataIn
                    }
                ]
            }

            hkjhjgChart.setOption(hkjhjgOption);
        }

        private updateJGTable(): void {
       		var name = this.mTableIds[HKJHType.JG] + "_jqgrid_1234";
            var tableAssist : JQTable.JQGridAssistant  = JQGridAssistantFactory.createJGTable(name);
            tableAssist.mergeTitle(undefined, 0, true);
            var data = [
                ["本月回笼", "", "本月回笼", "","本月回笼", "","本月回笼", "","本月回笼", ""],
                ["确保可回", "", "确保可回", "","确保可回", "","确保可回", "","确保可回", ""],
                ["争取可回", "", "争取可回", "","争取可回", "","争取可回", "","争取可回", ""]
            ];

            if (undefined != this.mJGData) {
                for (var i = 0; i < this.mJGData[0].length; ++i) {
                    data[0][i * 2 + 1] = Util.formatCurrency(this.mJGData[0][i]);
                    data[1][i * 2 + 1] = Util.formatCurrency(this.mJGData[1][i]);
                    data[2][i * 2 + 1] = Util.formatCurrency(this.mJGData[2][i]);
                }
            }


            var parent = $("#" + this.mTableIds[HKJHType.JG]);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(
                tableAssist.decorate({
                 caption : "回款计划结构",		
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
                    width: 1000,
                    shrinkToFit: true,
                    autoScroll: true
                }));
        }
        
        
          private updateZTTable(): void {
       		var name = this.mTableIds[HKJHType.ZT] + "_jqgrid_1234";
            var tableAssist : JQTable.JQGridAssistant  = JQGridAssistantFactory.createZTTable(name);
            tableAssist.mergeTitle(undefined, 0, true);
            var data = [[]];

            if (undefined != this.mZTData) {
                for (var i = 0; i < this.mZTData.length; ++i){
                	data[0].push(Util.formatCurrency(this.mZTData[i]));
                }
            }


            var parent = $("#" + this.mTableIds[HKJHType.ZT]);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(
                tableAssist.decorate({
                	caption : "回款计划款项状态结构",		
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
                    width: '100%',
                    shrinkToFit: true,
                    autoScroll: true
                }));
        }
        
          private updateXZTable(): void {
       		var name = this.mTableIds[HKJHType.XZ] + "_jqgrid_1234";
            var tableAssist : JQTable.JQGridAssistant  = JQGridAssistantFactory.createXZTable(name);
            tableAssist.mergeTitle(undefined, 0, true);
            var data = [[]];

            if (undefined != this.mXZData) {
                for (var i = 0; i < this.mXZData.length; ++i){
                	data[0].push(Util.formatCurrency(this.mXZData[i]));
                }
            }


            var parent = $("#" + this.mTableIds[HKJHType.XZ]);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(
                tableAssist.decorate({
                caption : "回款计划款项性质结构",		
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
                    width: '100%',
                    shrinkToFit: true,
                    autoScroll: true
                }));
            $(".ui-jqgrid-titlebar-close").hide();
        }
    }
}