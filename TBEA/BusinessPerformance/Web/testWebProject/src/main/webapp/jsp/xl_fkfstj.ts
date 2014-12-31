/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
module xl_fkfstj {

    class JQGridAssistantFactory {

        private static createSubNode(parent: JQTable.Node): JQTable.Node {
            return parent
                .append(new JQTable.Node("笔数", "bs"))
                .append(new JQTable.Node("金额", "je"));
        }

        public static createFdwTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, JQTable.TextAlign.Left),
                new JQTable.Node("", "title1", true, JQTable.TextAlign.Left),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("订单总量", "ddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无预付款合同", "wyfkht")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("预付款<10%合同", "yfkxy10")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("预付款10%-30%之间的合同", "yfk1030zj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("货物交付后付款比例小于80%的合同", "hwjfhfkblxy80")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保金10%的合同", "zbj10")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保金5%的合同", "zbj5")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无质保金合同", "wzbj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保期超过1年的合同", "zbqcg1n")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无兜底时间合同", "wddsj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("现款现货合同", "xkxh"))
            ], gridName);
        }

        public static createGwTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, JQTable.TextAlign.Left),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("国网合同订单总量", "gwhtddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("3:06:0:01", "306001")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0:09:0:01", "009001")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("3:4:2:1", "3421")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("2:5:2:1", "2521")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("2:5:2.5:0.5", "252505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0：10：0:0", "01000")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0:9.5:0.5", "09505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("其他", "qt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无质保金合同", "qzbj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保期超过1年的合同", "zbqcq1n"))
            ], gridName);
        }

        public static createNwTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, JQTable.TextAlign.Left),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("南网合同订单总量", "gwhtddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:6:2:1", "1621")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:2:6:1", "1261")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0:09:01", "00901")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("其他", "qt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保期超过1年的合同", "zbqcq1n"))
            ], gridName);
        }
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }
        private mMonth: number;
        private mYear: number;
        echartIdGW: string;
        echartIdNW: string;
        fdwTableId: string;
        gwTableId: string;
        nwTableId: string;
        echartIdFDW: string;
        private mDataSet: Util.DateDataSet;
        private mComp: Util.CompanyType = Util.CompanyType.SB;

        public init(echartIdFDW: string,
            echartIdGW: string,
            echartIdNW: string,
            fdwTableId: string,
            gwTableId: string,
            nwTableId: string,
            year : number,
            month : number): void {
            this.mYear = year;
            this.mMonth = month;
            this.echartIdFDW = echartIdFDW;
            this.echartIdGW = echartIdGW;
            this.echartIdNW = echartIdNW;
            this.fdwTableId = fdwTableId;
            this.gwTableId = gwTableId;
            this.nwTableId = nwTableId;
            this.mDataSet = new Util.DateDataSet("xlfkfstj_update.do");
            this.updateUI();
        }

        
        public onYearSelected(year : number){
            this.mYear = year;
        }
        
        public onMonthSelected(month : number){
            this.mMonth = month;
        }
        
        public onCompanySelected(comp: Util.CompanyType) {
            this.mComp = comp;
        }

        public updateUI() {
            this.mDataSet.getDataByCompany(this.mMonth, this.mYear, this.mComp, (data: string) => {
                if (null != data) {
                    var fktjData = JSON.parse(data);
                    this.updateFdwTable(
                        this.fdwTableId,
                        this.fdwTableId + "_jqgrid_1234",
                        JQGridAssistantFactory.createFdwTable(this.fdwTableId + "_jqgrid_1234"),
                        fktjData[0]);

                    var rawData = [
                        ["集中招标"],
                        ["非集中招标"],
                        ["合计"]];

                    this.updateTable(
                        this.gwTableId,
                        this.gwTableId + "_jqgrid_1234",
                        JQGridAssistantFactory.createGwTable(this.gwTableId + "_jqgrid_1234"),
                        rawData,
                        fktjData[1]);

                    rawData = [
                        ["集中招标"],
                        ["非集中招标"],
                        ["合计"]];
                    
                    this.updateTable(
                        this.nwTableId,
                        this.nwTableId + "_jqgrid_1234",
                        JQGridAssistantFactory.createNwTable(this.nwTableId + "_jqgrid_1234"),
                        rawData,
                        fktjData[2]);
                    
                    $('h1').text("线缆 " + this.mYear + "年" + this.mMonth + "月 付款方式统计");
                    document.title = "线缆 " + this.mYear + "年" + this.mMonth + "月 付款方式统计";
                    
                    
                    var chartDataFdw = [];
                    for (var i = 0; i < fktjData[0].length - 1; ++i){
                        chartDataFdw.push(parseFloat(fktjData[0][i][1]).toFixed(2));
                    }
                    
                     this.updatePieEchart(this.echartIdFDW, "非电网合同订单总量", chartDataFdw);
                    
                    

                    this.updateEchart(this.echartIdGW, "国网合同订单总量",
                        [{ value: parseFloat(fktjData[1][0][1]).toFixed(2), name: '集中招标' },
                            { value: parseFloat(fktjData[1][1][1]).toFixed(2), name: '非集中招标' }]);
                    
                    this.updateEchart(this.echartIdNW, "南网合同订单总量",
                        [{ value: parseFloat(fktjData[2][0][1]).toFixed(2), name: '集中招标' },
                            { value: parseFloat(fktjData[2][1][1]).toFixed(2), name: '非集中招标' }]);
                        }
                    });
        }




        private updatePieEchart(chartId: string, tileTex: string, data: any[]): void {
            var chart = echarts.init($("#" + chartId)[0]);
            var legend = ["火电", "水电", "核电", "风电、光伏",
                "轨道交通", "石油石化", "煤炭煤化工", "钢铁冶金", "航天军工", "连锁经营", "其他"];
            var dataOut = [];
            var dykhTotal = 0;
            var fdlscTotal = 0;
            for (var i = 0; i < legend.length; ++i) {
                dataOut.push({ name: legend[i], value: parseInt(data[i]) });
                if (i < 4) {
                    dykhTotal += parseInt(data[i]);
                } else {
                    fdlscTotal += parseInt(data[i]);
                }
            }

            var dataIn = [{ name: "电源客户", value: dykhTotal },
                { name: "非电力市场", value: fdlscTotal }];

            var option = {
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    x: "left",
                    data: legend,
                    orient: "vertical"
                },
                toolbox: {
                    show: true,
                },
                calculable: false,
                series: [
                    {
                        type: 'pie',
                        radius: [100, 130],
                        data: dataOut
                    }, {
                        type: 'pie',
                        radius: [0, 60],
                        itemStyle: {
                            normal: {
                                label: {
                                    position: 'inner'
                                },
                                labelLine: {
                                    show: false
                                }
                            }
                        },
                        data: dataIn
                    }
                ]
            }
            chart.setOption(option);
        }

        private updateEchart(chartId: string, tileTex: string, data: any[]): void {
            var chart = echarts.init($("#" + chartId)[0]);
            var legend = ["集中招标", "非集中招标"];
            var total = 0;

            var option = {
                title: {
                    text: tileTex,
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    x: "left",
                    data: legend,
                    orient: "vertical"
                },
                toolbox: {
                    show: true,
                },
                calculable: false,
                series: [
                    {
                        type: 'pie',
                        radius: '50%',
                        data: data
                    }
                ]
            }

            chart.setOption(option);
        }

        private updateTable(
            parentName: string,
            childName: string,
            tableAssist: JQTable.JQGridAssistant,
            data: Array<string[]>,
            rawData: Array<string[]>): void {

            var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (rawData[i] instanceof Array) {
                    row = [].concat(rawData[i]);
                    for (var col in row) {
                        if (col % 2 != 0) {
                            row[col] = Util.formatCurrency(row[col]);
                        } else{
                            row[col] = parseInt(row[col]);
                        }
                    }
                    data[i] = data[i].concat(row);
                }
            }


            var parent = $("#" + parentName);
            parent.empty();
            parent.append("<table id='" + childName + "'></table>");

            $("#" + childName).jqGrid(
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
                    shrinkToFit: false,
                    autoScroll: true,
                    //                    userData: {
                    //                        'title': "合计"
                    //                    },
                    //                    footerrow: true,
                    //                    userDataOnFooter: true
                }));

        }

        private updateFdwTable(
            parentName: string,
            childName: string,
            tableAssist: JQTable.JQGridAssistant,
            rawData: Array<string[]>): void {

            tableAssist.mergeTitle();
            tableAssist.mergeRow(0);
            tableAssist.mergeColum(0, 11);

            var data = [
                ["电源客户", "火电"],
                ["电源客户", "水电"],
                ["电源客户", "核电"],
                ["电源客户", "风电、光伏"],
                ["非电力市场", "轨道交通"],
                ["非电力市场", "石油石化"],
                ["非电力市场", "煤炭煤化工"],
                ["非电力市场", "钢铁冶金"],
                ["非电力市场", "航天军工"],
                ["非电力市场", "连锁经营"],
                ["非电力市场", "其他"],
                ["合", "计"]];

            var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (rawData[i] instanceof Array) {
                    row = [].concat(rawData[i]);
                    for (var col in row) {
                        if (col % 2 != 0) {
                            row[col] = Util.formatCurrency(row[col]);
                        }else{
                            row[col] = parseInt(row[col]);    
                        }
                    }
                    data[i] = data[i].concat(row);
                }
            }

            var parent = $("#" + parentName);
            parent.empty();
            parent.append("<table id='" + childName + "'></table>");

            $("#" + childName).jqGrid(
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
                    shrinkToFit: false
                    //                    autoScroll: true,
                    //                    footerrow: true,
                    //                    userDataOnFooter: true
                }));

        }
    }
}