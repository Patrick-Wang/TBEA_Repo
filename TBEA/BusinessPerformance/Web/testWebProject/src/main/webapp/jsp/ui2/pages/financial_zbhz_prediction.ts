/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module financial_zbhz_prediciton {

    enum FirstMonthZb{
        ndjh, jdjh, byjhz, dyyjz, dyjhwcl, dyqntq, dytbzf,
        cyyj, myyj, jdyjhj, jdyjwcl, jdqntq, jdtbzf, 
        ndljwcz, ndzbwcl, ndqntqz, ndtbzf    
    }
    
    enum SecondMonthZb{
        ndjh, jdjh, byjhz, dyyjz, dyjhwcl, dyqntq, dytbzf,
        jdlj, jdjhwcl, jdqntqz, jdtbzf,
        jdmyyj, jdyjhj, jdyjwcl, jdyjqntq, jdyjtbzf,
        ndljwcz, ndzbwcl, ndqntqz, ndtbzf    
    }
       
    enum ThirdMonthZb{
        ndjh, bjdjh, xjdjh, dyjhz, dyyjz, dyjhwcl, dyqntq, dytbzf,
        jdlj, jdjhwcl, jdqntqz, jdtbzf,
        ndljwcz, ndzbwcl, ndqntqz, ndtbzf,
        xjdsyyj, xjdcyyj, xjdmyyj, xjdyjhj, xjdyjwcl, xjdndlj, xjdndljwcl, xjdqntq,xjdtbzf
    }
    
    class JQGridAssistantFactory {

        public static createTable(gridName: string, gridStyle: number): JQTable.JQGridAssistant {
            if (1 == gridStyle)
            {
                return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("产业", "cy"),
                new JQTable.Node("年度计划", "ndjh"),
                new JQTable.Node("本季度计划", "jdjh"),
                new JQTable.Node("当月完成", "dywc")
                    .append(new JQTable.Node("本月计划值", "y1"))
                    .append(new JQTable.Node("当月预计值", "y2"))
                    .append(new JQTable.Node("计划完成率", "y3"))
                    .append(new JQTable.Node("去年同期", "y4"))
                    .append(new JQTable.Node("同比增幅", "y5")),
                new JQTable.Node("季度预计完成", "jdyjwc")
                    .append(new JQTable.Node("次月预计", "j1"))
                    .append(new JQTable.Node("末月预计", "j2"))
                    .append(new JQTable.Node("季度预计合计", "j3"))
                    .append(new JQTable.Node("季度预计完成率", "j4"))
                    .append(new JQTable.Node("去年同期", "j5"))
                    .append(new JQTable.Node("同比增幅", "j6")),
                new JQTable.Node("年度累计完成", "nd")
                    .append(new JQTable.Node("累计完成值", "n1"))
                    .append(new JQTable.Node("年度指标完成率", "n2"))
                    .append(new JQTable.Node("去年同期值", "n3"))
                    .append(new JQTable.Node("同比增幅", "n4"))
                ], gridName);
            }
            if (2 == gridStyle)
            {
                return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("产业", "cy"),
                new JQTable.Node("年度计划", "ndjh"),
                new JQTable.Node("本季度计划", "jdjh"),
                new JQTable.Node("当月完成", "dywc")
                    .append(new JQTable.Node("本月计划值", "y1"))
                    .append(new JQTable.Node("当月预计值", "y2"))
                    .append(new JQTable.Node("计划完成率", "y3"))
                    .append(new JQTable.Node("去年同期", "y4"))
                    .append(new JQTable.Node("同比增幅", "y5")),
                new JQTable.Node("季度累计完成", "jdljwc")
                    .append(new JQTable.Node("季度累计", "jl1"))
                    .append(new JQTable.Node("季度计划完成率", "jl2"))
                    .append(new JQTable.Node("去年同期值", "jl3"))
                    .append(new JQTable.Node("同比增幅", "jl4")),
                new JQTable.Node("季度预计完成", "jdyjwc")
                    .append(new JQTable.Node("末月预计", "jy1"))
                    .append(new JQTable.Node("季度预计合计", "jy2"))
                    .append(new JQTable.Node("季度预计完成率", "jy3"))
                    .append(new JQTable.Node("去年同期", "jy4"))
                    .append(new JQTable.Node("同比增幅", "jy5")),
                new JQTable.Node("年度累计完成", "nd")
                    .append(new JQTable.Node("累计完成值", "n1"))
                    .append(new JQTable.Node("年度指标完成率", "n2"))
                    .append(new JQTable.Node("去年同期值", "n3"))
                    .append(new JQTable.Node("同比增幅", "n4"))
                ], gridName);
            }
               if (3 == gridStyle)
               {
                    return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("产业", "cy"),
                new JQTable.Node("年度计划", "ndjh"),
                new JQTable.Node("本季度计划", "jdjh"),
                new JQTable.Node("下季度计划", "xjdjh"),
                new JQTable.Node("当月完成", "dywc")
                    .append(new JQTable.Node("本月计划值", "y1"))
                    .append(new JQTable.Node("当月预计值", "y2"))
                    .append(new JQTable.Node("计划完成率", "y3"))
                    .append(new JQTable.Node("去年同期", "y4"))
                    .append(new JQTable.Node("同比增幅", "y5")),
                new JQTable.Node("季度累计完成", "jdljwc")
                    .append(new JQTable.Node("季度累计", "jl1"))
                    .append(new JQTable.Node("季度计划完成率", "jl2"))
                    .append(new JQTable.Node("去年同期值", "jl3"))
                    .append(new JQTable.Node("同比增幅", "jl4")),
                new JQTable.Node("年度累计完成", "nd")
                    .append(new JQTable.Node("累计完成值", "n1"))
                    .append(new JQTable.Node("年度指标完成率", "n2"))
                    .append(new JQTable.Node("去年同期值", "n3"))
                    .append(new JQTable.Node("同比增幅", "n4")),
                new JQTable.Node("下季度预计完成", "xjdyjwc")
                    .append(new JQTable.Node("下季度首月预计", "xjy1"))
                    .append(new JQTable.Node("下季度次月预计", "xjy2"))
                    .append(new JQTable.Node("下季度末月预计", "xjy3"))
                    .append(new JQTable.Node("季度预计合计", "xjy4"))
                    .append(new JQTable.Node("季度预计完成率", "xjy5"))
                    .append(new JQTable.Node("年度累计", "xjy6"))
                    .append(new JQTable.Node("年度累计完成率", "xjy7"))
                    .append(new JQTable.Node("去年同期", "xjy8"))
                    .append(new JQTable.Node("同比增幅", "xjy9"))
                ], gridName);
               }
           
        }
    }

    export class View {
        private static ins: View;

        public static newInstance(): View {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        }
        private mYear : number;
        private mSeason: number;
        private mActualMonth: number;
        private mDelegateMonth: number;
       
        private mData: Array<string[]> = [];
        private mDataSet : Util.Ajax = new Util.Ajax("financial_zbhz_prediction_update.do");
        private mTableId : string;
        public init(tableId: string, year: number): void {
            this.mYear = year;
            this.mTableId = tableId;            
            $('h1').text(this.mYear + "年" + "季度五大经营指标预测完成情况");
            //this.updateTable();
            //this.updateUI();

        }
 		public onYearSelected(year : number){
        	this.mYear = year;
        }
        
        public onSeasonChange(season : string){
            this.mSeason = parseInt(season);
        }
        
        public onMonthDelegateSelected(month : string){
        	this.mDelegateMonth = parseInt(month);
        }
        
        public updateUI() {

            this.mActualMonth = (this.mSeason - 1) * 3 + this.mDelegateMonth;
            
            this.mDataSet.get({ month: this.mActualMonth, year: this.mYear })
                .then((dataArray: any) => {

                    this.mData = dataArray;
                    $('h1').text(this.mYear + "年" + "季度五大经营指标预测完成情况");
                    document.title = this.mYear + "年" + "季度五大经营指标预测完成情况";
                    this.updateTable();

                });
        }
        
         public exportExcel() {
            this.mActualMonth = (this.mSeason - 1) * 3 + this.mDelegateMonth;
            $("#export")[0].action = "gcy_zbhz_prediction_export.do?" + Util.Ajax.toUrlParam({ month: this.mActualMonth, year: this.mYear });
            $("#export")[0].submit();
        }
        
        
        private formatData(data : string[][], precentList : std.vector<number>){
            var row = [];
            for (var j = 0; j < this.mData.length; ++j) {
                row = [].concat(this.mData[j]);
                for (var i = 0; i < row.length; ++i) {
                    if (precentList.contains(i)) {
                        row[i] = Util.formatPercent(row[i]);
                    } else {
                        row[i] = Util.formatCurrency(row[i]);
                    }
                }
                data[j] = data[j].concat(row);
            }
            return data;
        }
        
        private formatFirstMonthData(data: string[][]) {
            var precentList: std.vector<number> = new std.vector<number>();
            precentList.push(FirstMonthZb.dyjhwcl);
            precentList.push(FirstMonthZb.dytbzf);
            precentList.push(FirstMonthZb.jdyjwcl);
            precentList.push(FirstMonthZb.jdtbzf);
            precentList.push(FirstMonthZb.ndzbwcl);
            precentList.push(FirstMonthZb.ndtbzf);
            return this.formatData(data, precentList);
        }
        
        private formatSecondMonthData(data: string[][]) {
            var precentList: std.vector<number> = new std.vector<number>();
            precentList.push(SecondMonthZb.dyjhwcl);
            precentList.push(SecondMonthZb.dytbzf);
            precentList.push(SecondMonthZb.jdjhwcl);
            precentList.push(SecondMonthZb.jdyjtbzf);
            precentList.push(SecondMonthZb.jdyjwcl);
            precentList.push(SecondMonthZb.jdtbzf);
            precentList.push(SecondMonthZb.ndzbwcl);
            precentList.push(SecondMonthZb.ndtbzf);
            return this.formatData(data, precentList);
        }
        
        private formatThirdMonthData(data : string[][]) {
            var precentList: std.vector<number> = new std.vector<number>();
            precentList.push(ThirdMonthZb.dyjhwcl);
            precentList.push(ThirdMonthZb.dytbzf);
            precentList.push(ThirdMonthZb.jdjhwcl);
            precentList.push(ThirdMonthZb.jdtbzf);
            precentList.push(ThirdMonthZb.ndzbwcl);
            precentList.push(ThirdMonthZb.ndtbzf);
            precentList.push(ThirdMonthZb.xjdyjwcl);
            precentList.push(ThirdMonthZb.xjdndljwcl);
            precentList.push(ThirdMonthZb.xjdtbzf);
            return this.formatData(data, precentList);
        }
        
        private updateTable(): void {
        	var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mDelegateMonth);
            tableAssist.mergeRow(0);
           
            for (var i = 0; i < 5; ++i) {
                tableAssist.setRowBgColor(i * 8 + 5, 183, 222, 232);
                tableAssist.setRowBgColor(i * 8 + 7, 183, 222, 232);
            }

            var data = [
                ["报表利润", "输变电产业"],
                ["报表利润", "新能源产业"],
                ["报表利润", "能源产业"],
                ["报表利润", "进出口公司"],
                ["报表利润", "国际工程公司"],
                ["报表利润", "股份合计"],
                ["报表利润", "众和公司"],
                ["报表利润", "集团合计"],
                ["销售收入", "输变电产业"],
                ["销售收入", "新能源产业"],
                ["销售收入", "能源产业"],
                ["销售收入", "进出口公司"],
                ["销售收入", "国际工程公司"],
                ["销售收入", "股份合计"],
                ["销售收入", "众和公司"],
                ["销售收入", "集团合计"],
                ["现金流", "输变电产业"],
                ["现金流", "新能源产业"],
                ["现金流", "能源产业"],
                ["现金流", "进出口公司"],
                ["现金流", "国际工程公司"],
                ["现金流", "股份合计"],
                ["现金流", "众和公司"],
                ["现金流", "集团合计"],
                ["应收账款", "输变电产业"],
                ["应收账款", "新能源产业"],
                ["应收账款", "能源产业"],
                ["应收账款", "进出口公司"],
                ["应收账款", "国际工程公司"],
                ["应收账款", "股份合计"],
                ["应收账款", "众和公司"],
                ["应收账款", "集团合计"],
                ["存 货", "输变电产业"],
                ["存 货", "新能源产业"],
                ["存 货", "能源产业"],
                ["存 货", "进出口公司"],
                ["存 货", "国际工程公司"],
                ["存 货", "股份合计"],
                ["存 货", "众和公司"],
                ["存 货", "集团合计"]];
            
            if (1 == this.mDelegateMonth){
                data = this.formatFirstMonthData(data);
            } else if (2 == this.mDelegateMonth){
                data = this.formatSecondMonthData(data);
            } else if (3 == this.mDelegateMonth){
                data = this.formatThirdMonthData(data);
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
//                    cellsubmit: 'clientArray',
//                    cellEdit: true,
                    height: 550,
                    width: 1350,
                    shrinkToFit: true,
                    rowNum: 200,
                    autoScroll: true
                }));
            
             $("#export").css('display','block'); 
        }
    }
}