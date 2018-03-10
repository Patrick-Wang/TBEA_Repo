module Driver {
    declare var context;

    export function query2Table(filename: string, tableMame: string): void{
        openQuery(filename, tableMame);
    }

    export function mdxQuery2Table(cellsets: any, tableMame: string): void{
        parse2JQGrid(cellsets, tableMame);
    }

    export function showTable(servResp:Util.ServResp, tableId: string): void{
        $(`#${tableId}`).empty();
        if (servResp) {
            $(`#${tableId}`).append('<table id="table-jq"></table><div id="pager"></div>');
            let gridCtrl: Util.ServResp = servResp;
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
            $(window).resize(()=>{
                $("#table-jq").setGridWidth($(".well").width());
            });
        } else {
            $(`#${tableId}`).append('<p>查询数据失败</p>');
        }

    }

    export function parse2JQGrid(cellsets:any, tableId: string): void {
        let target: any = {
            header: null,
            mergeRows:[],
            mergeCols:[],
            mergeTitle: undefined,
            width:'',
            pager:"",
            shrinkToFit:"",
            colorKey:"",
            data: null
        };
        target.header = solveHeader(cellsets);
        target.data = solveData(cellsets);
        showTable(target, tableId);
    }

    export function solveHeader(cellsets:any): Util.Header[]{
        let headers: Util.Header[] = [];
        for (let i = 0; i < cellsets.length; i++) {
            if(cellsets[i][0].type == "ROW_HEADER") break;
            for (let j = 0; j < cellsets[i].length; j++) {
                let cell = cellsets[i][j];
                let headerCell : any = {
                    name: cell.value != "null" ? cell.value : "",
                    type: "",
                    readOnly: "",
                    options: [""],
                    sub: null,
                    width: "",
                    align: "",
                    sortable: ""
                };
                if(i == 0){
                    headers.push(headerCell);
                } else {
                    let headerTemp = headers[j];
                    while(headerTemp.sub != null) {
                        headerTemp = headerTemp.sub[0];
                    }
                    headerTemp.sub = [];
                    headerTemp.sub.push(headerCell);
                }
            }
        }
        return headers;
    }

    export function solveData(cellsets:any[][]): string[][]{
        let data: string[][] = [];
        let row;
        for (let i = 0; i < cellsets.length; i++) {
            if(cellsets[i][0].type != "ROW_HEADER") continue;
            row = [];
            for (let j = 0; j < cellsets[i].length; j++) {
                row.push(cellsets[i][j].value);
            }
            data.push(row);
        }
        return data;
    }

    export function creatQueryId(): string {
        return 'xxxxxxxx-xxxx-xxxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g,
            function (c) {
                let r = Math.random() * 16 | 0,
                    v = c == 'x' ? r : (r & 0x3 | 0x8);
                return v.toString(16);
            }).toUpperCase();
    }

    export function getExecute(queryModule: any, tableId: string){
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

    export function openQuery(filename: string, tableId: string): void {
        $.post(
            "/BusinessManagement/report/v2/redirectTo.do?url=" + context.url + "rest/saiku/api/query/" + creatQueryId(),
            { file: filename, formatter: "flattened" },
            function (queryModel, status) {
                if(status == "success") getExecute(queryModel, tableId);
                else showTable(null, tableId);
            });
    }
}
