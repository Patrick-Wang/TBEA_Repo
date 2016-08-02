/// <reference path="util.ts" />
/// <reference path="jqgrid/jqassist.ts" />
declare var echarts;
module yszkrb_view {

    enum YSZKColumnId {
        JTZJHLZB, DWHKJH, JRHK, YLJ, HLZBWC, HLJHWCL, KJYSZKHK, QBBC, ZQBC, LZHJ, QYQB, JHWCL, JZZMYE
    };

    class JQGridAssistantFactory {

        public static createTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "t0", true, JQTable.TextAlign.Left),
                new JQTable.Node("集团下达月度资金回笼指标", "t1"),
                new JQTable.Node("各单位自行制定的回款计划", "t2"),
                new JQTable.Node("今日回款", "t3"),
                new JQTable.Node("月累计（截止今日）", "t4"),
                new JQTable.Node("资金回笼指标完成", "t5"),
                new JQTable.Node("回款计划完成率", "t6"),
                new JQTable.Node("已回款中可降应收的回款金额", "t7"),
                new JQTable.Node("目前-月底回款计划", "t8")
                    .append(new JQTable.Node("确保办出", "t81"))
                    .append(new JQTable.Node("争取办出", "t82"))
                    .append(new JQTable.Node("两者合计", "t83")),
                new JQTable.Node("全月确保", "t9"),
                new JQTable.Node("预计全月计划完成率", "t10"),
                new JQTable.Node("截止月底应收账款账面余额", "t11")

            ], gridName);
        }
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }
        private mDay: number;
        private mMonth: number;
        private mYear: number;
        private mTableId: string;
        private mDataSet: Util.Ajax = new Util.Ajax("yszk_update.do");
        private mExportDataSet: Util.Ajax;
        private mData: Array<string[]> = [];
        TableId: string;
        childTableId: string;

        public init(tableId: string, month: number, year: number, day: number): void {
            this.mYear = year;
            this.mMonth = month;
            this.mTableId = tableId;
            this.mDay = day;

            $("#date").val(year + "/" + month + "/" + day);
            $("#date").datepicker({
                //            numberOfMonths:1,//显示几个月  
                //            showButtonPanel:true,//是否显示按钮面板  
                dateFormat: 'yy/mm/dd',//日期格式  
                //            clearText:"清除",//清除日期的按钮名称  
                //            closeText:"关闭",//关闭选择框的按钮名称  
                yearSuffix: '年', //年的后缀  
                showMonthAfterYear: true,//是否把月放在年的后面  
                defaultDate: year + "/" + month + "/" + day,//默认日期  
                //            minDate:'2011-03-05',//最小日期  
                //maxDate: year + "-" + month + "-" + day,//最大日期  
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                onSelect: (selectedDate) => {//选择日期后执行的操作  
                    var d: Date = new Date(selectedDate);
                    this.mYear = d.getFullYear();
                    this.mMonth = d.getMonth() + 1;
                    this.mDay = d.getDate();
                }
            });
            $("#ui-datepicker-div").css('font-size', '0.8em'); //改变大小;
             
            this.updateUI();

        }
        
        public exportExcelYSDialy() {
            $("#exportYSDialy")[0].action = "yszk_view_export.do?" + Util.Ajax.toUrlParam({year:this.mYear,month:this.mMonth,day:this.mDay});
            $("#exportYSDialy")[0].submit();
        }

        public updateUI() {
            this.mDataSet.get({ month: this.mMonth, year: this.mYear, day: this.mDay })
                .then((dataArray: any) => {
                    this.mData = dataArray;
                    $('h1').text(this.mYear + "年" + this.mMonth + "月" + this.mDay + "日应收账款日报");
                    document.title = this.mYear + "年" + this.mMonth + "月" + this.mDay + "日应收账款日报";
                    this.updateTable(); //update data for table
                });
        }
        private initPercentList(): std.vector<number> {
            var precentList: std.vector<number> = new std.vector<number>();
            if (this.mData.length == 1){
                precentList.push(YSZKColumnId.HLZBWC + 1);
                precentList.push(YSZKColumnId.HLJHWCL + 1);
                precentList.push(YSZKColumnId.JHWCL + 1);
            }else{
                precentList.push(YSZKColumnId.HLZBWC);
                precentList.push(YSZKColumnId.HLJHWCL);
                precentList.push(YSZKColumnId.JHWCL);
            }

            return precentList;
        }


        private updateTable(): void {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
            var outputData: string[][] = [];
            var data = [[]];
            if (this.mData.length == 1){
                Util.formatData(outputData, this.mData, this.initPercentList(), [], 1);
            }else{
                Util.formatData(outputData, this.mData, this.initPercentList(), [], 0);
                data = [
                    ["沈变公司"],
                    ["衡变公司"],
                    ["新变厂"],
                    ["其中：天变公司"],
                    ["鲁缆公司"],
                    ["新缆厂"],
                    ["德缆公司"],
                    ["输变电小计"],
                    ["新能源"],
                    ["新特能源公司"],
                    ["新能源小计"],
                    ["天池能源"],
                    ["能动公司"],
                    ["能源小计"],
                    ["进出口公司"],
                    ["国际工程公司"],
                    ["工程小计"],
                    ["众和公司"],
                    ["集团合计"]
                ];
            }



            var row = [];
            for (var i = 0; i < outputData.length; ++i) {
                row = [].concat(outputData[i]);
                data[i] = data[i].concat(row);
                if (data[i][0].lastIndexOf("计") >= 0) {
                    tableAssist.setRowBgColor(i, 183, 222, 232);
                }
            }

            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(
                tableAssist.decorate({
                    data: tableAssist.getData(data),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: "100%",
                    width: 1330,
                    shrinkToFit: true,
                    rowNum: 200,
                    autoScroll: true
                }));

            $("#export").css('display', 'block');

        }
    }
}