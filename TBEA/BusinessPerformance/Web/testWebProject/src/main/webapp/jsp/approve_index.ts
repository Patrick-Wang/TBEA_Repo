/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;
declare var $;
module approveIndex {



    export interface IViewOption {
//        date?: Util.Date;
//        company?: Util.CompanyType;
    }

    export class View {
        public static instance: View = new View();
        public static getInstance(): View {
            return View.instance;
        }

//        private mOpt: IViewOption;
//        private mDataSet: Util.Ajax = new Util.Ajax("url");
        initInstance(opt: IViewOption) {
//            this.mOpt = opt;
            this.updateTitle();

        }


        private updateTitle(){
             $('h1').text("数据审核");
             document.title = "数据审核";    
        }

//        private updateTable(tableId: string): void {
//            var name = tableId + "_jqgrid";
//            var tableAssist: JQTable.JQGridAssistant =
//                JQGridAssistantFactory.createFlatTable(name, []);
//
//            var data = [];
//
//            var parent = $("#" + tableId);
//            parent.empty();
//            parent.append("<table id='" + name + "'></table>");
//
//            $("#" + name).jqGrid(
//                tableAssist.decorate({
//                    // url: "TestTable/WGDD_load.do",
//                    // datatype: "json",
//                    data: tableAssist.getData(data),
//                    datatype: "local",
//                    multiselect: false,
//                    drag: false,
//                    resize: false,
//                    //autowidth : false,
//                    cellsubmit: 'clientArray',
//                    cellEdit: true,
//                    height: '100%',
//                    width: 1250,
//                    shrinkToFit: true,
//                    autoScroll: true,
//                }));
//
//        }
    }
}