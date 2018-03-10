var Driver;
(function (Driver) {
    function query2Table(filename, tableMame) {
        openQuery(filename, tableMame);
    }
    Driver.query2Table = query2Table;
    function mdxQuery2Table(cellsets, tableMame) {
        parse2JQGrid(cellsets, tableMame);
    }
    Driver.mdxQuery2Table = mdxQuery2Table;
    function showTable(servResp, tableId) {
        $("#" + tableId).empty();
        if (servResp) {
            $("#" + tableId).append('<table id="table-jq"></table><div id="pager"></div>');
            var gridCtrl = servResp;
            this.tableAssist = Util.createTable("table-jq", gridCtrl);
            this.tableAssist.create({
                data: gridCtrl.data,
                cellEdit: false,
                datatype: "local",
                cellsubmit: 'clientArray',
                drag: false,
                resize: false,
                height: gridCtrl.data.length > 15 ? (15 * 26) : (gridCtrl.data.length * 26),
                width: $(".well").width() - 30,
                shrinkToFit: true,
                assistEditable: false,
                autoScroll: false,
                rowNum: 1000,
                viewrecords: true
            });
            $("#table-jq").setGridWidth($(".well").width());
            $(window).resize(function () {
                $("#table-jq").setGridWidth($(".well").width());
            });
        }
        else {
            $("#" + tableId).append('<p>查询数据失败</p>');
        }
    }
    Driver.showTable = showTable;
    function parse2JQGrid(cellsets, tableId) {
        var target = {
            header: null,
            mergeRows: [],
            mergeCols: [],
            mergeTitle: undefined,
            width: '',
            pager: "",
            shrinkToFit: "",
            colorKey: "",
            data: null
        };
        target.header = solveHeader(cellsets);
        target.data = solveData(cellsets);
        showTable(target, tableId);
    }
    Driver.parse2JQGrid = parse2JQGrid;
    function solveHeader(cellsets) {
        var headers = [];
        for (var i = 0; i < cellsets.length; i++) {
            if (cellsets[i][0].type == "ROW_HEADER")
                break;
            for (var j = 0; j < cellsets[i].length; j++) {
                var cell = cellsets[i][j];
                var headerCell = {
                    name: cell.value != "null" ? cell.value : "",
                    type: "",
                    readOnly: "",
                    options: [""],
                    sub: null,
                    width: "",
                    align: "",
                    sortable: ""
                };
                if (i == 0) {
                    headers.push(headerCell);
                }
                else {
                    var headerTemp = headers[j];
                    while (headerTemp.sub != null) {
                        headerTemp = headerTemp.sub[0];
                    }
                    headerTemp.sub = [];
                    headerTemp.sub.push(headerCell);
                }
            }
        }
        return headers;
    }
    Driver.solveHeader = solveHeader;
    function solveData(cellsets) {
        var data = [];
        var row;
        for (var i = 0; i < cellsets.length; i++) {
            if (cellsets[i][0].type != "ROW_HEADER")
                continue;
            row = [];
            for (var j = 0; j < cellsets[i].length; j++) {
                row.push(cellsets[i][j].value);
            }
            data.push(row);
        }
        return data;
    }
    Driver.solveData = solveData;
    function creatQueryId() {
        return 'xxxxxxxx-xxxx-xxxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        }).toUpperCase();
    }
    Driver.creatQueryId = creatQueryId;
    function getExecute(queryModule, tableId) {
        $.ajax({
            url: "/BusinessManagement/report/v2/redirectTo.do?url=" + context.url + "rest/saiku/api/query/execute",
            type: 'POST',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(queryModule),
            dataType: 'json',
            processData: false,
            success: function (execute) {
                parse2JQGrid(execute.cellset, tableId);
            },
            error: function () {
                showTable(null, tableId);
            }
        });
    }
    Driver.getExecute = getExecute;
    function openQuery(filename, tableId) {
        $.post("/BusinessManagement/report/v2/redirectTo.do?url=" + context.url + "rest/saiku/api/query/" + creatQueryId(), { file: filename, formatter: "flattened" }, function (queryModel, status) {
            if (status == "success")
                getExecute(queryModel, tableId);
            else
                showTable(null, tableId);
        });
    }
    Driver.openQuery = openQuery;
})(Driver || (Driver = {}));
