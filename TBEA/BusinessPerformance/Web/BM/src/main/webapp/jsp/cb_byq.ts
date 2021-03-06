/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;
module cb_byq {
    class JQGridAssistantFactory {

        private static createSubNode(parent: JQTable.Node): JQTable.Node {
            return parent
                .append(new JQTable.Node("单价", "dj"))
                .append(new JQTable.Node("用量", "yl"));
        }

        public static createMxTable(gridName: string): JQTable.JQGridAssistant {
            var title = ["订单所在单位", "订单所在项目公司", "投标报价时间", "用户单位名称", "项目名称",
                "预计交货时间", "型号", "电压", "产量（万KVA）", "产值",
                "预计开标时间", "销售部门预测的中标概率", "投标硅钢牌号", "投标硅钢用量（单台）",
                "投标硅钢单价", "投标电解铜用量（单台）", "投标电解铜单价", "投标变压器油用量（单台）",
                "投标变压器油单价", "投标钢材用量（单台）", "投标钢材单价", "投标纸板用量（单台）",
                "投标纸板单价", "投标五大主材成本", "投标其他材料成本", "投标材料成本总计（不含税）",
                "人工及制造费用", "投标制造成本", "运费", "投标毛利（单台）", "投标毛利率"];
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i == 0 || i == 1) {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, JQTable.TextAlign.Left, 90));
                } else if (i == 3 || i == 4) {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, JQTable.TextAlign.Left, 200));
                } else if (i == 6) {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, JQTable.TextAlign.Left, 120));
                } else if (i < 7) {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, JQTable.TextAlign.Left, 80));
                }
                else {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, JQTable.TextAlign.Right, 80));
                }
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        }

        public static createJttbTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "dw", true, JQTable.TextAlign.Left),
                new JQTable.Node("产值", "cz"),
                new JQTable.Node("毛利额", "mle"),
                new JQTable.Node("毛利率", "mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("硅钢", "gg")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "djt"))
                    .append(new JQTable.Node("加工费", "jgf")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("纸板", "zb")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("变压器油", "byqy")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("钢材", "gc"))
            ], gridName);
        }

        public static createGstbTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("时间", "1sj", true, JQTable.TextAlign.Left),
                new JQTable.Node("产值", "1cz"),
                new JQTable.Node("毛利额", "1mle"),
                new JQTable.Node("毛利率", "1mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("硅钢", "1gg")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "1djt"))
                    .append(new JQTable.Node("加工费", "1jgf")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("纸板", "1zb")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("变压器油", "1byqy")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("钢材", "1gc"))
            ], gridName);
        }
    }
    

    export class View {

        public static newInstance(): View {
            return new View();
        }
        
        private mComp: Util.CompanyType = Util.CompanyType.SBGS;
        private mMxData: string[][];
        private mJtData: string[][];
        private mGsData: string[][];
        private mMonth: number;
        private mMxTableId: string;
        private mJttbTableId: string;
        private mGstbTableId: string;
        private mDataSet: Util.Ajax;
        
        
        public init(
            mxTableId: string,
            jttbTableId: string,
            gstbTableId: string,
            mx: string[][],
            jt: string[][],
            gs: string[][],
            month: number) {

            this.mMxTableId = mxTableId;
            this.mJttbTableId = jttbTableId;
            this.mGstbTableId = gstbTableId;
            this.mMxData = mx;
            this.mJtData = jt;
            this.mGsData = gs;
            this.mMonth = month;
            this.mDataSet = new Util.Ajax("tb_update.do");

            this.updateMxTable();
            this.updateJttbTable();
            this.updateGstbTable();
            this.updateUI();

        }

        public onCompanySelected(comp: Util.CompanyType) {
            this.mComp = comp;
        }

        public updateUI() {
            this.mDataSet.get({ companyId: this.mComp })
                .then((data: any) => {
                    this.mMxData = data;
                    this.updateMxTable();
                });
        }

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
                            col = parseInt(col) - 1;
                            if (col == 8 || col == 13 || col == 15 || col == 17 || col == 19 || col == 21 || col >= 21 && col != 29) {
                                ++col;
                                row[col] = Util.formatCurrency(row[col]);
                            } else if (col == 29) {
                                ++col;
                                row[col] = (parseFloat(row[col]) * 100).toFixed(2) + "%";
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
                    cellEdit: true,
                    height: 250,
                    width: 1250,
                    shrinkToFit: false,
                    autoScroll: true,
                    rowNum: 1000
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
            var data = [
                ["沈变"],
                ["衡变"],
                ["新变"],
                ["总计"]];

            for (var i = 0; i < data.length; ++i) {
                data[i] = this.format(data[i].concat(this.mJtData[i]))
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

        private format(row: string[]) {
            for (var col = 1; col < row.length; ++col) {
                if (col == 3) {
                    row[col] = (parseFloat(row[col]) * 100).toFixed(2) + "%";
                } else if (col != 5 && col != 7 && col != 10 && col != 14 && col != 16) {
                    row[col] = Util.formatCurrency(row[col]);
                } else {
                    row[col] = parseFloat(row[col]).toFixed(2);
                }
            }
            return row
        }

        private updateGstbTable(): void {
            var name = this.mGstbTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createGstbTable(name);
            var data = [];
            for (var i = 0; i < this.mMonth; ++i) {
                data.push(this.format([(i + 1) + "月"].concat(this.mGsData[i])));
            }

            data.push(this.format(["总计"].concat(this.mGsData[this.mMonth])));

            var parent = $("#" + this.mGstbTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            var height: any = '100%';
            if (this.mMonth > 4) {
                height = 110;
            }
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
                    height: height,
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
    }
}