/// <reference path="jqgrid/jqassist.ts" />

var gdw_zbhz;
(function (gdw_zbhz) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName, month) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb"),
                new JQTable.Node("企业名称", "qymc"),
                new JQTable.Node("年度计划", "ndjh"),
                new JQTable.Node("当期", "dq").append(new JQTable.Node(month + "月计划", "yjh")).append(new JQTable.Node(month + "月完成", "ywc")).append(new JQTable.Node(month + "月计划完成率", "yjhwcl", true, 180)).append(new JQTable.Node(month + "年度累计", "ndlj")).append(new JQTable.Node(month + "累计完成率", "ljwcl")),
                new JQTable.Node("去年同期", "qntq_1").append(new JQTable.Node("去年同期", "qntq")).append(new JQTable.Node("同比增长", "tbzz")).append(new JQTable.Node("去年同期累计", "qntqlj")).append(new JQTable.Node("同比增长", "tbzz"))
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();

    var View = (function () {
        function View() {
        }
        View.newInstance = function () {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        };

        View.prototype.init = function (echartIdPie, tableId, month, year, data) {
            this.mYear = year;
            this.mMonth = month;
            this.mEchartIdPie = echartIdPie;
            this.mData = data;
            this.updateTable(tableId);
        };

        View.prototype.updateTable = function (name) {
            var tableAssist = JQGridAssistantFactory.createTable(name, this.mMonth);
            tableAssist.mergeRow(0);

            var data = [
                ["利润总额", "沈变公司"],
                ["利润总额", "衡变公司"],
                ["利润总额", "新变厂"],
                ["利润总额", "天变公司"],
                ["利润总额", "鲁缆公司"],
                ["利润总额", "新缆厂"],
                ["利润总额", "德缆公司"],
                ["利润总额", "输变电小计"],
                ["利润总额", "新特能源公司"],
                ["利润总额", "新能源"],
                ["利润总额", "新能源小计"],
                ["利润总额", "天池能源"],
                ["利润总额", "能动公司"],
                ["利润总额", "中疆物流"],
                ["利润总额", "能源小计"],
                ["利润总额", "进出口公司"],
                ["利润总额", "国际工程公司"],
                ["利润总额", "工程小计"],
                ["利润总额", "股份公司小计"],
                ["利润总额", "众和公司"],
                ["利润总额", "集团合计"],
                ["销售收入", "沈变公司"],
                ["销售收入", "衡变公司"],
                ["销售收入", "新变厂"],
                ["销售收入", "天变公司"],
                ["销售收入", "鲁缆公司"],
                ["销售收入", "新缆厂"],
                ["销售收入", "德缆公司"],
                ["销售收入", "输变电小计"],
                ["销售收入", "新特能源公司"],
                ["销售收入", "新能源"],
                ["销售收入", "新能源小计"],
                ["销售收入", "天池能源"],
                ["销售收入", "能动公司"],
                ["销售收入", "中疆物流"],
                ["销售收入", "能源小计"],
                ["销售收入", "进出口公司"],
                ["销售收入", "国际工程公司"],
                ["销售收入", "工程小计"],
                ["销售收入", "股份公司小计"],
                ["销售收入", "众和公司"],
                ["销售收入", "集团合计"],
                ["销售收入", "沈变公司"],
                ["销售收入", "衡变公司"],
                ["销售收入", "新变厂"],
                ["销售收入", "天变公司"],
                ["销售收入", "鲁缆公司"],
                ["销售收入", "新缆厂"],
                ["销售收入", "德缆公司"],
                ["销售收入", "输变电小计"],
                ["销售收入", "新特能源公司"],
                ["销售收入", "新能源"],
                ["销售收入", "新能源小计"],
                ["销售收入", "天池能源"],
                ["销售收入", "能动公司"],
                ["销售收入", "中疆物流"],
                ["销售收入", "能源小计"],
                ["销售收入", "进出口公司"],
                ["销售收入", "国际工程公司"],
                ["销售收入", "工程小计"],
                ["销售收入", "股份公司小计"],
                ["销售收入", "众和公司"],
                ["销售收入", "集团合计"],
                ["销售收入", "沈变公司"],
                ["销售收入", "衡变公司"],
                ["销售收入", "新变厂"],
                ["销售收入", "天变公司"],
                ["销售收入", "鲁缆公司"],
                ["销售收入", "新缆厂"],
                ["销售收入", "德缆公司"],
                ["销售收入", "输变电小计"],
                ["销售收入", "新特能源公司"],
                ["销售收入", "新能源"],
                ["销售收入", "新能源小计"],
                ["销售收入", "天池能源"],
                ["销售收入", "能动公司"],
                ["销售收入", "中疆物流"],
                ["销售收入", "能源小计"],
                ["销售收入", "进出口公司"],
                ["销售收入", "国际工程公司"],
                ["销售收入", "工程小计"],
                ["销售收入", "股份公司小计"],
                ["销售收入", "众和公司"],
                ["销售收入", "集团合计"],
                ["存货", "沈变公司"],
                ["存货", "衡变公司"],
                ["存货", "新变厂"],
                ["存货", "天变公司"],
                ["存货", "鲁缆公司"],
                ["存货", "新缆厂"],
                ["存货", "德缆公司"],
                ["存货", "输变电小计"],
                ["存货", "新特能源公司"],
                ["存货", "新能源"],
                ["存货", "新能源小计"],
                ["存货", "能动公司"],
                ["存货", "能源小计"],
                ["存货", "进出口公司"],
                ["存货", "国际工程公司"],
                ["存货", "工程小计"],
                ["存货", "股份公司小计"],
                ["存货", "众和公司"],
                ["存货", "集团合计"]];

            for (var i = 0; i < data.length; ++i) {
                if (data[i][1].lastIndexOf("计") >= 0) {
                    tableAssist.setRowBgColor(i, 183, 222, 232);
                }

                if (this.mData[i] instanceof Array) {
                    data[i] = data[i].concat(this.mData[i]);
                }
            }

            $("#" + name).jqGrid(tableAssist.decorate({
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
                height: 600,
                width: 1200,
                shrinkToFit: true,
                rowNum: 200,
                autoScroll: true
            }));
        };
        return View;
    })();
    gdw_zbhz.View = View;
})(gdw_zbhz || (gdw_zbhz = {}));
//# sourceMappingURL=gdw_zbhz.js.map
