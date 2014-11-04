/// <reference path="jqgrid/jqassist.ts" />

var hzb_zbhz;
(function (hzb_zbhz) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName, month) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("序号", "xh", true, 60),
                new JQTable.Node("指标", "zb", true, 85),
                new JQTable.Node("指标", "zb_"),
                new JQTable.Node("当期", "dq").append(new JQTable.Node(month + "月计划", "yjh")).append(new JQTable.Node(month + "月完成", "ywc")).append(new JQTable.Node(month + "月完成率", "ywcl")).append(new JQTable.Node("季度计划", "jdjh")).append(new JQTable.Node("季度累计", "jdlj")).append(new JQTable.Node("季度完成率", "jdwcl")).append(new JQTable.Node("年度累计", "ndlj")).append(new JQTable.Node("年度完成率", "ndwcl")),
                new JQTable.Node("去年", "qn").append(new JQTable.Node("去年同期", "qntq")).append(new JQTable.Node("同比增长", "tbzz")).append(new JQTable.Node("去年同期累计", "qntqlj")).append(new JQTable.Node("同比增长", "tbzz"))
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
            tableAssist.mergeTitle();
            tableAssist.mergeColum(1);
            tableAssist.mergeColum(1, 12);
            tableAssist.mergeColum(1, 14);
            tableAssist.mergeColum(1, 16);
            tableAssist.mergeColum(1, 17);
            tableAssist.mergeColum(1, 19);
            tableAssist.mergeColum(1, 32);
            tableAssist.mergeRow(1);
            var data = [
                ["1", "利润总额", ""],
                ["2", "营业收入", ""],
                ["3", "&#12288;其中", "变压器产品"],
                ["4", "&#12288;其中", "线缆产品"],
                ["5", "&#12288;其中", "多晶硅产品"],
                ["6", "&#12288;其中", "硅片产品"],
                ["7", "&#12288;其中", "逆变器产品"],
                ["8", "&#12288;其中", "发电收入"],
                ["9", "&#12288;其中", "煤炭产品"],
                ["10", "&#12288;其中", "工程类收入"],
                ["11", "&#12288;其中", "物流类收入"],
                ["12", "&#12288;其中", "其他类收入"],
                ["13", "经营性净", "现金流"],
                ["14", "应收账款", ""],
                ["15", "其中：逾", "期款"],
                ["16", "存货&#12288;&#12288;", ""],
                ["17", "其中：积", "压物资"],
                ["18", "合同签约", "额"],
                ["19", "资金回笼", ""],
                ["20", "不含税产", "值"],
                ["21", "产量&#12288;&#12288;", ""],
                ["22", "&#12288;其中", "变压器(万KVA)"],
                ["23", "&#12288;其中", "线缆导线(吨)"],
                ["24", "&#12288;其中", "多晶硅(吨)"],
                ["25", "&#12288;其中", "硅片(片)"],
                ["26", "&#12288;其中", "发电量（万度）"],
                ["27", "&#12288;其中", "逆变器产量（MW）"],
                ["28", "&#12288;其中", "煤炭(万吨)"],
                ["29", "人数&#12288;&#12288;", ""],
                ["30", "人均利润", ""],
                ["31", "人均收入", ""],
                ["32", " 三项费用", ""],
                ["33", "三项费用", "率"]];

            for (var i = 0; i < data.length; ++i) {
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
                width: 1100,
                shrinkToFit: true,
                rowNum: 100,
                autoScroll: true
            }));
        };
        return View;
    })();
    hzb_zbhz.View = View;
})(hzb_zbhz || (hzb_zbhz = {}));
//# sourceMappingURL=hzb_zbhz.js.map
