/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
///<reference path="dateSelector.ts"/>
///<reference path="unitedSelector.ts"/>
///<reference path="companySelector.ts"/>
declare var echarts;

module hzb_companys {

    enum AllZb{
        zb, qnjh, dyjh, dysj, dyjhwcl, dyqntq, dytbzf, jdjh, jdlj, jdjhwcl, jdqntq, jdtbzf, ndlj, ndljjhwcl, ndqntq, ndtbzf
    };
    
    enum HbZb{
         zb, qnjh, dyjh, dysj, dyjhwcl, dysytq, dyhbzf, dytbzf, jdjh, jdlj, jdjhwcl, jdqntq, jdtbzf, ndlj, ndljjhwcl, ndqntq, ndtbzf       
    }
    
    
    
    class JQGridAssistantFactory {

        public static createTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("全年计划", "qnjh"),
                new JQTable.Node("月度", "yd")
                    .append(new JQTable.Node("当月计划", "y1"))
                    .append(new JQTable.Node("当月实际", "y2"))
                    .append(new JQTable.Node("计划完成率", "y3"))
                    .append(new JQTable.Node("去年同期", "y4"))
                    .append(new JQTable.Node("同比增幅", "y5")),
                new JQTable.Node("季度", "jd")
                    .append(new JQTable.Node("季度计划", "j1"))
                    .append(new JQTable.Node("季度累计", "j2"))
                    .append(new JQTable.Node("季度计划完成率", "j3"))
                    .append(new JQTable.Node("去年同期", "j4"))
                    .append(new JQTable.Node("同比增幅", "j5")),
                new JQTable.Node("年度", "nd")
                    .append(new JQTable.Node("年度累计", "n1"))
                    .append(new JQTable.Node("累计计划完成率", "n2"))
                    .append(new JQTable.Node("去年同期", "n3"))
                    .append(new JQTable.Node("同比增幅", "n4"))
            ], gridName);
        }
        
        public static createHbTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("全年计划", "qnjh"),
                new JQTable.Node("月度", "yd")
                    .append(new JQTable.Node("当月计划", "y1"))
                    .append(new JQTable.Node("当月实际", "y2"))
                    .append(new JQTable.Node("计划完成率", "y3"))
                    .append(new JQTable.Node("上月同期", "y4"))
                    .append(new JQTable.Node("环比增幅", "y4"))
                    .append(new JQTable.Node("同比增幅", "y5")),
                new JQTable.Node("季度", "jd")
                    .append(new JQTable.Node("季度计划", "j1"))
                    .append(new JQTable.Node("季度累计", "j2"))
                    .append(new JQTable.Node("季度计划完成率", "j3"))
                    .append(new JQTable.Node("去年同期", "j4"))
                    .append(new JQTable.Node("同比增幅", "j5")),
                new JQTable.Node("年度", "nd")
                    .append(new JQTable.Node("年度累计", "n1"))
                    .append(new JQTable.Node("累计计划完成率", "n2"))
                    .append(new JQTable.Node("去年同期", "n3"))
                    .append(new JQTable.Node("同比增幅", "n4"))
            ], gridName);
        }
    }

    
    
    interface IViewOption {
        tableId: string;
        dateId: string;
        date?: Util.Date;
        companyId: string;
        comps: Util.IDataNode[];
    }
    
    export class View {
        private static ins: View;

        public static newInstance(): View {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        }
        
        private mDateSelector: Util.DateSelector;
        private mCompanySelector: Util.CompanySelector;
        private mOpt: IViewOption;
        private mData: Array<string[]> = [];
        private mDataSet : Util.Ajax = new Util.Ajax("hzb_companys_update.do");
        public init(opt : IViewOption): void {
           this.mOpt = opt;
            
           if (opt.comps.length == 0){
               $('h1').text("没有任何可以查看的公司");
               $('input').css("display", "none");
           } else{
               var month = this.mOpt.date.month + (2 - (this.mOpt.date.month - 1) % 3);
               this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, { year: this.mOpt.date.year, month: month }, this.mOpt.dateId);
               this.mDateSelector.select(this.mOpt.date);
               this.mCompanySelector = new Util.CompanySelector(false, opt.companyId, opt.comps);
               this.updateUI();
           } 
        }
        
        public exportExcel(fName: string) {
            var date : Util.Date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            $("#export")[0].action = "companys_zbhz_export.do?" + Util.Ajax.toUrlParam({ month: date.month, year: date.year, companyId: compType});
            $("#export")[0].submit();
        }
        
        public updateUI() {
            var date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            this.mDataSet.get({ year: date.year, month: date.month, companyId: compType})
                .then((dataArray: any) => {

                    this.mData = dataArray;
                    this.updateTextandTitle(date);
                    this.updateTable();

                });
        }
        
        private updateTextandTitle(date: Util.Date) {
            $('h1').text(date.year + "年" + date.month + "月经营单位与项目公司指标完成情况");
            document.title = date.year + "年" + date.month + "月经营单位与项目公司指标完成情况";
        }
        
        private updateTable(): void {
        	var name = this.mOpt.tableId + "_jqgrid_1234";
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='"+ name +"'></table>");
            if (this.mData.length == 0){
                $("#tips").css("display", "");
                return;
            } 
            $("#tips").css("display", "none");

            var tableAssist: JQTable.JQGridAssistant = null;
            tableAssist = JQGridAssistantFactory.createTable(name)
            var outputData: string[][] = [];
            Util.formatData(outputData, this.mData, this.initPercentList(), [AllZb.dysj,
                AllZb.dyqntq,
                AllZb.jdlj,
                AllZb.jdqntq,
                AllZb.ndlj,
                AllZb.ndqntq,
            ]);
            
            $("#" + name).jqGrid(
                tableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: tableAssist.getData(outputData),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
//                    cellsubmit: 'clientArray',
//                    cellEdit: true,
                    height: outputData.length > 23 ? 500 : '100%',
                    width: 1300,
                    shrinkToFit: true,
                    rowNum: 1000,
                    autoScroll: true
                }));

        }
        
        private initPercentList(): std.vector<number>
        {
            var precentList: std.vector<number> = new std.vector<number>();
            precentList.push(AllZb.dyjhwcl);
            precentList.push(AllZb.dytbzf);
            precentList.push(AllZb.jdjhwcl);
            precentList.push(AllZb.jdtbzf);
            precentList.push(AllZb.ndljjhwcl);
            precentList.push(AllZb.ndtbzf);
            return precentList;
        } 
    }
}