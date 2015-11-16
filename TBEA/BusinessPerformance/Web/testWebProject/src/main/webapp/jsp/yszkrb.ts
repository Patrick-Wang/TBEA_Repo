/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module yszkrb {

    class JQGridAssistantFactory {

        public static createTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("集团下达月度资金回笼指标", "t1", true, JQTable.TextAlign.Left),
                new JQTable.Node("各单位自行制定的回款计划", "t2"),
                new JQTable.Node("今日回款", "t3"),
                new JQTable.Node("已回款中可降应收的回款金额", "t4"),
                new JQTable.Node("确保办出", "t5"),
                new JQTable.Node("争取办出", "t6"),
                new JQTable.Node("截止月底应收账款账面余额", "t7")
            ], gridName);
        }
    }
    
    interface ISubmitResult {
        result: string;
    }
    
    export class View {
        private static ins: View;

        public static newInstance(): View {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        }

        private mMonth: number;
        private mYear: number;
        private mDay: number;
        private mData: Array<string[]> = [];
        private mDataSet: Util.Ajax = new Util.Ajax("yszk_update.do");
        private mTableId: string;

        private mTableAssist: JQTable.JQGridAssistant;
        private mSave: Util.Ajax = new Util.Ajax("yszk_submit.do");
        
        public init(tableId: string, month: number, year: number, day: number): void {
            this.mYear = year;
            this.mMonth = month;
            this.mDay = day;
            this.mTableId = tableId;
  
            $("#date").val(year + "-" + month + "-" + day);
            $("#date").datepicker({
                //            numberOfMonths:1,//显示几个月  
                //            showButtonPanel:true,//是否显示按钮面板  
                dateFormat: 'yy-mm-dd',//日期格式  
                //            clearText:"清除",//清除日期的按钮名称  
                //            closeText:"关闭",//关闭选择框的按钮名称  
                yearSuffix: '年', //年的后缀  
                showMonthAfterYear: true,//是否把月放在年的后面  
                defaultDate: year + "-" + month + "-" + day,//默认日期  
                //            minDate:'2011-03-05',//最小日期  
                maxDate: year + "-" + month + "-" + day,//最大日期  
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
            
            //this.updateTable();
            this.updateUI();

        }
     
         public save() {
            var allData = this.mTableAssist.getAllData();
            var submitData = [];
            var colNames = this.mTableAssist.getColNames();
            for (var i = 0; i < allData.length; ++i){
                submitData.push([]);
                for (var j = 0; j < allData[i].length; ++j){
                    if (j != 1){
                        submitData[i].push(allData[i][j])
                        allData[i][j] = allData[i][j].replace(new RegExp(' ', 'g'), '')
                    }
                }
            }
            
            this.mSave.post({
                year: this.mYear,
                month: this.mMonth,
                day: this.mDay,
                data: JSON.stringify(submitData)
            }).then((data: ISubmitResult) => {
                    if ("true" == data.result) {
                         Util.MessageBox.tip("保存 成功");
                    } else if ("false" == data.result) {
                         Util.MessageBox.tip("保存 失败");
                    } else {
                        Util.MessageBox.tip(data.result);
                    }
            });
        }
        public updateUI() {
            this.mDataSet.get({ month: this.mMonth, year: this.mYear, day: this.mDay })
                .then((dataArray: any) => {
                    this.mData = dataArray;
                    $('h1').text(this.mYear + "年" + this.mMonth + "月" + this.mDay + "日应收账款日报");
                    document.title = this.mYear + "年" + this.mMonth + "月" + this.mDay + "日应收账款日报";
                    this.updateTable();
                });
        }

        private updateTable(): void {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
            
            var data = [];
            
            var row = [];
            for (var i = 0; i < this.mData.length; ++i) {
                if (this.mData[i] instanceof Array) {
                    row = [].concat(this.mData[i]);
                    for (var col in row) {
                        row[col] = Util.formatCurrency(row[col]);
                    }
                    data[i] = data[i].concat(row);
                }
            }
            var parent = $("#" + this.mTableId);
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
                    //                    cellsubmit: 'clientArray',
                    //                    cellEdit: true,
                    height: '100%',
                    width: 1300,
                    shrinkToFit: true,
                    rowNum: 100,
                    autoScroll: true
                }));

        }
    }
}