/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="dateSelector.ts" />
declare var echarts;
declare var $;
module template {

    class JQGridAssistantFactory {

        public static createFlatTable(gridName: string, title: string[]): JQTable.JQGridAssistant {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                nodes.push(new JQTable.Node(title[i], "_" + i));
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        }

        public static createHierarchyTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("A", "a"),
                new JQTable.Node("B", "b")
                    .append(new JQTable.Node("C", "c"))
            ], gridName);
        }
    }

    export interface IViewOption {
        date?: Util.Date;
        company?: Util.CompanyType;
    }

    export class View {
        public static instance: View = new View();
        public static getInstance(): View {
            return View.instance;
        }

        private mOpt: IViewOption;
        private mDataSet: Util.Ajax = new Util.Ajax("url");
        initInstance(opt: IViewOption) {
            this.mOpt = opt;
            this.updateTitle();
            this.updateUI();
        }

        public updateUI() {
            this.mDataSet.get({ year: 0, month: 0, day: 0, companyId: 0 })
                .then((data: any) => {
                    this.updateTitle();
                    this.updateTable("");
                });
        }

        private updateTitle(){
             $('h1').text("new header");
             document.title = "new header";    
        }

        private updateTable(tableId: string): void {
            var name = tableId + "_jqgrid";
            var tableAssist: JQTable.JQGridAssistant =
                JQGridAssistantFactory.createFlatTable(name, []);

            var data = [];

            var parent = $("#" + tableId);
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
                }));

        }
    }
}