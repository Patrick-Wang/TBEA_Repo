/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
///<reference path="companySelector.ts"/>
///<reference path="unitedSelector.ts"/>
declare var echarts;

module companys_zbhz_prediction {

    enum FirstMonthZb{
        zb, ndjh, bjdjh, byjhz, dyyjz, dyjhwcl, dyqntq, dytbzf,
        cyyj, myyj, jdyjhj, jdyjwcl, jdqntq, jdtbzf, 
        ndljwcz, ndzbwcl, ndqntqz, ndtbzf    
    }
    
    enum SecondMonthZb{
        zb, ndjh, jdjh, byjhz, dyyjz, dyjhwcl, dyqntq, dytbzf,
        jdlj, jdjhwcl, jdqntqz, jdtbzf,
        jdmyyj, jdyjhj, jdyjwcl, jdyjqntq, jdyjtbzf,
        ndljwcz, ndzbwcl, ndqntqz, ndtbzf    
    }
       
    enum ThirdMonthZb{
        zb, ndjh, bjdjh, xjdjh, dyjhz, dyyjz, dyjhwcl, dyqntq, dytbzf,
        jdlj, jdjhwcl, jdqntqz, jdtbzf,
        ndljwcz, ndzbwcl, ndqntqz, ndtbzf,
        xjdsyyj, xjdcyyj, xjdmyyj, xjdyjhj, xjdyjwcl, xjdndlj, xjdndljwcl, xjdqntq,xjdtbzf
    }
    
    enum TableType{
        firstMonthinSeason, secondMonthinSeason, thirdMonthinSeason
    }
    
    class JQGridAssistantFactory {

        public static createTable(gridName: string, gridStyle: number): JQTable.JQGridAssistant {
            if (1 == gridStyle)
            {
                return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
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
        private mCompanySelector: Util.CompanySelector;
        private mData: Array<string[]> = [];
        private mDataSet : Util.Ajax = new Util.Ajax("hzb_companys_prediction_update.do");
        private mTableId : string;
        private mCompanyId : string;
        private mComps : Util.IDataNode[];
        public init(tableId: string, year: number, companyId: string, comps: Util.IDataNode[]): void {
            this.mYear = year;
            this.mTableId = tableId;
            this.mCompanyId = companyId;
            this.mComps = comps;
            this.mCompanySelector = new Util.CompanySelector(false, this.mCompanyId, this.mComps);
            this.updateTextandTitle();
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
        
        public exportExcel(fName: string) {
            //var date : Util.Date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            $("#export")[0].action = "hzb_companys_prediction_export.do?" + Util.Ajax.toUrlParam({ month: this.mActualMonth, year: this.mYear, companyId: compType});
            $("#export")[0].submit();
        }
        
        
        public updateUI() {

            this.mActualMonth = (this.mSeason - 1) * 3 + this.mDelegateMonth;
            var compType = this.mCompanySelector.getCompany();
            this.mDataSet.get({ month: this.mActualMonth, year: this.mYear, companyId: compType })
                .then((dataArray: any) => {
                    this.mData = dataArray;                   
                    this.updateTable();
                    this.updateTextandTitle();
                });
        }
        
        private updateTextandTitle()
        {
             $('h1').text(this.mYear + "年" +  "季度项目公司及经营单位指标预测完成情况");
             document.title = this.mYear + "年" + "季度项目公司及经营单位指标预测完成情况";
        }
        
        private formatFirstMonthData(outputData: string[][]) {
            var precentList: std.vector<number> = new std.vector<number>();
            precentList.push(FirstMonthZb.dyjhwcl);
            precentList.push(FirstMonthZb.dytbzf);
            precentList.push(FirstMonthZb.jdyjwcl);
            precentList.push(FirstMonthZb.jdtbzf);
            precentList.push(FirstMonthZb.ndzbwcl);
            precentList.push(FirstMonthZb.ndtbzf);
            return Util.formatData(outputData, this.mData, precentList, [
                FirstMonthZb.dyyjz,
                FirstMonthZb.dyqntq,
                FirstMonthZb.cyyj,
                FirstMonthZb.myyj,
                FirstMonthZb.jdyjhj,
                FirstMonthZb.jdqntq,
                FirstMonthZb.ndljwcz,
                FirstMonthZb.ndqntqz
            ]);
        }

        private formatSecondMonthData(outputData: string[][]) {
            var precentList: std.vector<number> = new std.vector<number>();
            precentList.push(SecondMonthZb.dyjhwcl);
            precentList.push(SecondMonthZb.dytbzf);
            precentList.push(SecondMonthZb.jdjhwcl);
            precentList.push(SecondMonthZb.jdyjtbzf);
            precentList.push(SecondMonthZb.jdyjwcl);
            precentList.push(SecondMonthZb.jdtbzf);
            precentList.push(SecondMonthZb.ndzbwcl);
            precentList.push(SecondMonthZb.ndtbzf);
            return Util.formatData(outputData, this.mData, precentList, [
                SecondMonthZb.dyyjz,
                SecondMonthZb.dyqntq,
                SecondMonthZb.jdlj,
                SecondMonthZb.jdqntqz,
                SecondMonthZb.jdmyyj,
                SecondMonthZb.jdyjhj,
                SecondMonthZb.jdyjqntq,
                SecondMonthZb.ndljwcz,
                SecondMonthZb.ndqntqz
            ]);
        }

        private formatThirdMonthData(outputData: string[][]) {
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
            return Util.formatData(outputData, this.mData, precentList, [
                ThirdMonthZb.dyyjz,
                ThirdMonthZb.dyqntq,
                ThirdMonthZb.jdlj,
                ThirdMonthZb.jdqntqz,
                ThirdMonthZb.ndljwcz,
                ThirdMonthZb.ndqntqz,
                ThirdMonthZb.xjdsyyj,
                ThirdMonthZb.xjdcyyj,
                ThirdMonthZb.xjdmyyj,
                ThirdMonthZb.xjdyjhj,
                ThirdMonthZb.xjdndlj,
                ThirdMonthZb.xjdqntq
            ]);
        }
        
        private updateTable(): void {
        	var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mDelegateMonth);
            var outputdata : string [][] = [];

 			if (1 == this.mDelegateMonth){
                this.formatFirstMonthData(outputdata);
            } else if (2 == this.mDelegateMonth){
                this.formatSecondMonthData(outputdata);
            } else if (3 == this.mDelegateMonth){
                this.formatThirdMonthData(outputdata);
            } 
            
			var parent = $("#" + this.mTableId);
			parent.empty();
			parent.append("<table id='"+ name +"'></table>");
            $("#" + name).jqGrid(
                tableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: tableAssist.getData(outputdata),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
//                    cellsubmit: 'clientArray',
//                    cellEdit: true,
                    height: outputdata.length > 23 ? 500 : '100%',
                    width: 1330,
                    shrinkToFit: true,
                    rowNum: 100,
                    autoScroll: true
                }));
             $("#export").css('display','block'); 

        }
    }
}