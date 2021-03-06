/// <reference path="../util.ts" />

declare var $:any;

(function () {
    $.fn.jqGrid.setGroupHeaders = function (o) {
        o = $.extend({
            useColSpanStyle: false,
            depth: 2,
            groupHeaders: []
        }, o || {});
        return this
            .each(function () {
                this.p.groupHeader = o;
                var ts = this, i, cmi, skip = 0, $tr, $colHeader, th, $th, thStyle, iCol, cghi,
                // startColumnName,
                    numberOfColumns, titleText, cVisibleColumns, colModel = ts.p.colModel, cml = colModel.length, ths = ts.grid.headers, $htable = $(
                    "table.ui-jqgrid-htable", ts.grid.hDiv), $trLabels = $htable
                    .children("thead").children(
                        "tr.ui-jqgrid-labels:last").addClass(
                        "jqg-second-row-header"), $thead = $htable
                    .children("thead"), $theadInTable, $firstHeaderRow = $htable
                    .find(".jqg-first-row-header");
                if ($firstHeaderRow[0] === undefined) {
                    $firstHeaderRow = $('<tr>', {
                        role: "row",
                        "aria-hidden": "true"
                    }).addClass("jqg-first-row-header").css("height",
                        "auto");
                } else {
                    $firstHeaderRow.empty();
                }
                var $firstRow, inColumnHeader = function (text,
                                                          columnHeaders) {
                    var length = columnHeaders.length, i;
                    for (i = 0; i < length; i++) {
                        if (columnHeaders[i].startColumnName === text) {
                            return i;
                        }
                    }
                    return -1;
                };

                $(ts).prepend($thead);
                $tr = $('<tr>', {
                    role: "rowheader"
                }).addClass("ui-jqgrid-labels jqg-third-row-header");
                for (i = 0; i < cml; i++) {
                    th = ths[i].el;
                    $th = $(th);
                    cmi = colModel[i];
                    // build the next cell for the first header row
                    thStyle = {
                        height: '0px',
                        width: ths[i].width + 'px',
                        display: (cmi.hidden ? 'none' : '')
                    };
                    $("<th>", {
                        role: 'gridcell'
                    }).css(thStyle).addClass(
                        "ui-first-th-" + ts.p.direction).appendTo(
                        $firstHeaderRow);

                    th.style.width = ""; // remove unneeded style
                    iCol = inColumnHeader(cmi.name, o.groupHeaders);
                    if (iCol >= 0) {
                        cghi = o.groupHeaders[iCol];
                        numberOfColumns = cghi.numberOfColumns;
                        titleText = cghi.titleText;

                        // caclulate the number of visible columns from the
                        // next numberOfColumns columns
                        for (cVisibleColumns = 0, iCol = 0; iCol < numberOfColumns
                        && (i + iCol < cml); iCol++) {
                            if (!colModel[i + iCol].hidden) {
                                cVisibleColumns++;
                            }
                        }

                        // The next numberOfColumns headers will be moved in
                        // the next row
                        // in the current row will be placed the new column
                        // header with the titleText.
                        // The text will be over the cVisibleColumns columns
                        $colHeader = $('<th>').attr({
                            role: "columnheader"
                        }).addClass(
                            "ui-state-default ui-th-column-header ui-th-"
                            + ts.p.direction).css({
                            'height': '22px',
                            'border-top': '0 none'
                        }).html(titleText);
                        if (cVisibleColumns > 0) {
                            $colHeader.attr("colspan",
                                String(cVisibleColumns));
                        }
                        if (ts.p.headertitles) {
                            $colHeader.attr("title", $colHeader.text());
                        }
                        // hide if not a visible cols
                        if (cVisibleColumns === 0) {
                            $colHeader.hide();
                        }

                        $th.before($colHeader); // insert new column header
                        // before the current
                        $tr.append(th); // move the current header in the
                        // next row

                        // set the coumter of headers which will be moved in
                        // the next row
                        skip = numberOfColumns - 1;
                    } else {
                        if (skip === 0) {
                            if (o.useColSpanStyle) {
                                // expand the header height to two rows
                                $th.attr("rowspan", o.depth + "");
                            } else {
                                $('<th>', {
                                    role: "columnheader"
                                }).addClass(
                                    "ui-state-default ui-th-column-header ui-th-"
                                    + ts.p.direction).css({
                                    "display": cmi.hidden ? 'none' : '',
                                    'border-top': '0 none'
                                }).insertBefore($th);
                                $tr.append(th);
                            }
                        } else {
                            // move the header to the next row
                            // $th.css({"padding-top": "2px", height:
                            // "19px"});
                            $tr.append(th);
                            skip--;
                        }
                    }
                }
                $theadInTable = $(ts).children("thead");
                $theadInTable.prepend($firstHeaderRow);
                $tr.insertAfter($trLabels);
                $htable.append($theadInTable);

                if (o.useColSpanStyle) {
                    // Increase the height of resizing span of visible
                    // headers
                    $htable
                        .find("span.ui-jqgrid-resize")
                        .each(
                            function () {
                                var $parent = $(this).parent();
                                if ($parent.is(":visible")) {
                                    this.style.cssText = 'height: '
                                        + $parent.height()
                                        + 'px !important; cursor: col-resize;';
                                }
                            });

                    // Set position of the sortable div (the main lable)
                    // with the column header text to the middle of the
                    // cell.
                    // One should not do this for hidden headers.
                    $htable
                        .find("div.ui-jqgrid-sortable")
                        .each(
                            function () {
                                var $ts = $(this), $parent = $ts
                                    .parent();
                                if ($parent.is(":visible")
                                    && $parent
                                        .is(":has(span.ui-jqgrid-resize)")) {
                                    $ts.css('top', ($parent
                                            .height() - $ts
                                            .outerHeight())
                                        / 2 + 'px');
                                }
                            });
                }

                $firstRow = $theadInTable.find("tr.jqg-first-row-header");
                $(ts).bind('jqGridResizeStop.setGroupHeaders',
                    function (e, nw, idx) {
                        $firstRow.find('th').eq(idx).width(nw);
                    });
            });
    }
})();

module JQTable {


    export enum TextAlign {
        Left,
        Right,
        Center
    }

    export let NodeId : ()=>string= (function(idBase:number){
        return function(){
            return "jq_" + (++idBase) + "_node";
        };
    })(2000);

    export interface NodeOption {
        name: string;
        id?: string;
        isReadOnly?: boolean;
        isNumber?: boolean;
        align?: TextAlign;
        width?: number;
        editType?: string;
        options?: any;
        hidden?:boolean;
        //add by hzdqzy
        isSortable?: boolean;
        sorttype?: string
    }

    export class Node {
        private mChilds:Node[] = [];
        private mParent:Node = null;
        mOpts:NodeOption;

        constructor(name:string,
                    id:string,
                    isReadOnly:boolean = true,
                    align:TextAlign = TextAlign.Right,
                    width:number = 0,
                    editType:string = undefined,
                    options:any = undefined,
                    isNumber:boolean = true,
                    //add by hzdqzy
                    isSortable:boolean = false,
                    sorttype:string = undefined) {
            this.mOpts = {
                name: name,
                id: id,
                isReadOnly: isReadOnly,
                isNumber: isNumber,
                align: align,
                width: width,
                editType: editType,
                options: options,
                //add by hzdqzy
                isSortable: isSortable,
                sorttype: sorttype
            }
        }

        public static create(opts:NodeOption):Node {
            var node:Node = new Node(undefined, undefined);
            node.mOpts = $.extend({}, {
                isReadOnly: true,
                isNumber: true,
                align: TextAlign.Right,
                width: 0,
                isSortable : false
            }, opts);
            if (node.mOpts.id == undefined){
                node.mOpts.id = NodeId();
            }
            return node;
        }

        public align():TextAlign {
            return this.mOpts.align;
        }

        public width():number {
            if (this.mOpts.width == undefined) {
                return -1;
            }
            return this.mOpts.width;
        }

        public isReadOnly():boolean {
            return true == this.mOpts.isReadOnly;
        }

        public isNumber():boolean {
            return true == this.mOpts.isNumber;
        }

        public editType():string {
            return this.mOpts.editType;
        }

        public editOptions():string {
            return this.mOpts.options;
        }

        public hidden():boolean {
            return this.mOpts.hidden;
        }

        public append(child:Node):Node {
            this.mChilds.push(child);
            child.mParent = this;
            return this;
        }

        public parent():Node {
            return this.mParent;
        }

        public hasChilds():boolean {
            return this.mChilds.length > 0;
        }

        public childs():Node[] {
            return this.mChilds;
        }

        public idChain():string {
            if (this.mParent != null) {
                return this.mParent.idChain() + "_" + this.mOpts.id;
            }
            return this.mOpts.id;
        }

        public id():string {
            return this.mOpts.id;
        }

        public leavesCount():number {
            var count = 0;
            for (var i in this.mChilds) {
                if (this.mChilds[i].hasChilds()) {
                    count += this.mChilds[i].leavesCount();
                } else {
                    ++count;
                }
            }
            return count;
        }

        public mostLeftLeaf():Node {
            var node:Node = this;
            if (this.hasChilds()) {
                node = this.mChilds[0].mostLeftLeaf();
            }
            return node;
        }

        public leaves():Node[] {
            var list:Node[] = [];
            if (this.hasChilds()) {
                for (var i in this.mChilds) {
                    if (this.mChilds[i].hasChilds()) {
                        list = list.concat(this.mChilds[i].leaves());
                    } else {
                        list.push(this.mChilds[i]);
                    }
                }
            } else {
                list.push(this);
            }

            return list;
        }

        public depth():number {
            var childDepth = 0;
            for (var i = 0; i < this.mChilds.length; i++) {
                if (this.mChilds[i].depth() > childDepth) {
                    childDepth = this.mChilds[i].depth();
                }
            }
            return childDepth + 1;
        }

        public name():string {
            return this.mOpts.name;
        }

        public children(depth:number):Node[] {
            var children = new std.vector<Node>();
            children.push(this);
            for (var j = 0; j < depth; j++) {
                var len = children.size();
                for (var k = 0; k < len; k++) {
                    var currentChilds = children.get(0).childs();
                    children.erase(0);
                    for (var i = 0; i < currentChilds.length; i++) {
                        children.push(currentChilds[i]);
                    }
                }
            }
            return children.toArray();
        }

        //add by hzdqzy-------------------------------------------------------
        public isSortable():boolean {
            return this.mOpts.isSortable;
        }

        public sorttype():string {
            return this.mOpts.sorttype;
        }

        //---------------------------------------------------------------------
    }

    export class Cell {
        private mRow:number;
        private mCol:number;
        private mGrid:any;

        constructor(row:number, col:number) {
            this.mRow = row;
            this.mCol = col;
        }

        public setGrid(grid) {
            this.mGrid = grid;
        }

        public getVal() {
            return this.mGrid.jqGrid("getCell", this.mGrid[0].p.data[this.mRow].id, this.mCol);
        }

        public setVal(val) {
            return this.mGrid.jqGrid("setCell", this.mGrid[0].p.data[this.mRow].id, this.mCol, val, undefined, undefined, true);
        }

        public row():number {
            return this.mRow;
        }

        public col():number {
            return this.mCol;
        }
    }

    export class Formula {
        private mDestCell:Cell;
        private mSrcCellarray:Cell[];
        private mFormula:(dest:Cell, srcCells:Cell[]) => number;

        constructor(destCell:Cell, srcCellarray:Cell[], formula:(dest:Cell, srcCells:Cell[]) => number) {
            this.mDestCell = destCell;
            this.mSrcCellarray = srcCellarray;
            this.mFormula = formula;
        }

        public destCell():Cell {
            return this.mDestCell;
        }

        public setGrid(grid):void {
            this.mDestCell.setGrid(grid);
            for (var i = 0; i < this.mSrcCellarray.length; i++) {
                this.mSrcCellarray[i].setGrid(grid);
            }
        }

        public update(): boolean {
            let newVal : any = this.mFormula(this.mDestCell, this.mSrcCellarray);
            let oldVal = this.mDestCell.getVal();
            if (oldVal == "" && (newVal == undefined || newVal == null || newVal == "")){
                return false;
            } else if (oldVal == newVal){
                return false;
            } else{
                if (newVal != null && newVal != undefined) {
                    this.mDestCell.setVal(newVal);
                }else{
                    this.mDestCell.setVal("");
                }
                return true;
            }
        }
    }

    export class JQGridAssistant {
        private completeList:Array<() => void> = [];
        private selectedList:Array<(newRow:number, newCol:number) => void> = [];
        private mTitle:Node[] = [];
        private mGridName:string;
        private mColModel = [];
        private mResizeList = [];
        private mFormula:Formula[] = [];
        private mOnMergedRows:(iCol:number, iRowStart:number, ilen:number) => void;
        private mOnMergedColums:(col:number, row:number) => void;
        private mOnMergedTitles:(iColStart:number, iCount:number) => void;
        private mEditedRows:string[] = [];
        private mDepth:number;
        private mDisabledEditCells:Cell[];

        constructor(titleNodes:Node[], gridName:string) {
            var nodes:Node[];
            this.mGridName = gridName;
            this.mTitle = titleNodes;
            for (var i in this.mTitle) {
                //this.mTitle[i].mOpts.id = this.mGridName + this.mTitle[i].mOpts.id;
                nodes = this.mTitle[i].leaves();

                for (var j in nodes) {
                    var colId = nodes[j].idChain();
                    this.mColModel.push({
                        name: colId,
                        index: colId,
                        sortable: nodes[j].isSortable(),//update by hzdqzy
                        sorttype: nodes[j].sorttype(),//add by hzdqzy
                        editable: !nodes[j].isReadOnly(),
                        edittype: nodes[j].editType(),
                        editoptions: nodes[j].editOptions(),
                        editrules: !nodes[j].isReadOnly() ? {number: nodes[j].isNumber()} : undefined,
                        hidden:nodes[j].hidden(),
                        //editrules:undefined,
                        cellattr: function (rowId, tv, rawObject, cm, rdata) {
                            return 'id=\'' + cm.name + rowId + "\'";
                        }
                    });

                    switch (nodes[j].align()) {
                        case TextAlign.Left:
                            this.mColModel[this.mColModel.length - 1].align = 'left';
                            break;
                        case TextAlign.Center:
                            this.mColModel[this.mColModel.length - 1].align = 'center';
                            break;
                        case TextAlign.Right:
                            this.mColModel[this.mColModel.length - 1].align = 'right';
                            break;
                    }

                    if (nodes[j].width() > 0) {
                        this.mColModel[this.mColModel.length - 1].width = nodes[j].width();
                    }
                }
            }
            this.mDepth = this.testDepth();

            if (this.mDepth > 1) {
                this.group();
            }
        }

        public getCheckedRowIds():string[] {
            var ids = $("#" + this.mGridName + "").jqGrid('getGridParam', 'selarrrow');
            return ids;
        }

        public getAllData():Array<string[]> {
            var grid = $("#" + this.mGridName + "");
            grid[0].p.data
            var ids = grid.jqGrid('getDataIDs');
            var data:Array<string[]> = [];
            for (var i in grid[0].p.data) {
                var row = [];
                row.push(grid[0].p.data[i].id);
                for (var j in this.mColModel) {
                    let val = grid[0].p.data[i][this.mColModel[j].index];
                    row.push(undefined == val ? "" : val + "");
                }
                data.push(row);
            }
            return data;
        }

        public getDataCount():number {
            var grid = $("#" + this.mGridName + "");
            return grid[0].p.data.length;
        }

        public getCurrentPageNumber():number {
            var grid = $("#" + this.mGridName + "")
            let curPg = $(grid[0].p.pager + "_center input").val();
            return parseInt(curPg);
        }

        public navigateToPage(pg:number):void {
            var grid = $("#" + this.mGridName + "")
            grid[0].p.page = pg;
            grid[0].grid.populate();
        }

        public showError(msg:string, width:number = 290, height:number = 90) {
            this.centerModal(width, height);
            setTimeout(function () {
                $.jgrid.info_dialog($.jgrid.errors.errcap, msg, $.jgrid.edit.bClose);
            }, 100);
        }

        public centerModal(width:number = 290, height:number = 90) {
            var grid = $("#gview_" + this.mGridName + "");
            $.jgrid.jqModal = {
                width: width,
                left: grid.offset().left + grid.width() / 2 - width / 2,
                top: grid.offset().top + grid.height() / 2 - height
            };
        }

        public testDepth():number {
            var depth = 1;
            var tmp = 0;
            for (var node in this.mTitle) {
                tmp = this.mTitle[node].depth();
                if (depth < tmp) {
                    depth = tmp;
                }
            }
            return depth;
        }

        public getColNames():string[] {
            var leaves = [];
            var names = [];
            for (var node in this.mTitle) {
                leaves = this.mTitle[node].leaves();
                for (var leaf in leaves) {
                    names.push(leaves[leaf].name());
                }
            }
            return names;
        }

        public getColModel() {
            return this.mColModel;
        }


        public addRowData(rowId:string, rowData:string[]) {
            var grid = $("#" + this.mGridName + "");
            var row:any = {};
            for (var j in this.mColModel) {
                if (j < rowData.length) {
                    row[this.mColModel[j].index] = rowData[j];
                }
            }
            grid.jqGrid("addRowData", rowId, row)
        }

        public id(col:number):string {
            var colCount = 0;
            var leaves = [];
            for (var i in this.mTitle) {
                leaves = this.mTitle[i].leaves();
                if (colCount < (col + 1)
                    && (col + 1) <= (colCount + leaves.length)) {
                    return leaves[col - colCount].idChain();
                }
                colCount += leaves.length;
            }
            return "";
        }

        public onResized(nw:number, idx:number) {
            for (var i = 0; i < this.mResizeList.length; i++) {
                this.mResizeList[i](nw, idx);
            }
        }

        public group():void {
            var finished = false;
            this.completeList.push(() => {
                if (finished) {
                    return;
                }
                var nodes = [];
                var headers = [];
                var grid = $("#" + this.mGridName + "");
                for (var i = 0; i < this.mDepth - 1; ++i) {
                    nodes = this.getLevelNodes(i);
                    headers = [];
                    for (var node in nodes) {
                        if (nodes[node].leavesCount() > 0) {
                            headers.push({
                                startColumnName: nodes[node].mostLeftLeaf()
                                    .idChain(),
                                numberOfColumns: nodes[node].leavesCount(),
                                titleText: nodes[node].name()
                            });
                        }
                    }

                    grid.jqGrid('setGroupHeaders', {
                        depth: this.mDepth,
                        useColSpanStyle: true,
                        groupHeaders: headers
                    });
                }
                finished = true;
            });
        }

        public getLevelNodes(level:number):Node[] {
            var levelNodes = [];
            for (var i = 0; i < this.mTitle.length; i++) {
                levelNodes = levelNodes.concat(this.mTitle[i].children(level));
                for (var j = levelNodes.length - 1; j >= 0; --j) {
                    if (!levelNodes[j].hasChilds()) {
                        levelNodes.splice(j, 1)
                    }
                }
            }
            return levelNodes;
        }

        public getData(data:string[][]):any[] {
            var alldata:any[] = [];
            var colums:Node[] = [];
            for (var i in this.mTitle) {
                colums = colums.concat(this.mTitle[i].leaves());
            }
            let id = 1;
            for (var i in data) {
                var rowdata = {};
                rowdata["id"] = "" + (id++);
                for (var j in colums) {
                    rowdata[colums[j].idChain()] = data[i][j] == undefined ? "" : data[i][j];
                }
                alldata.push(rowdata);
            }
            return alldata;
        }

        public getDataWithId(data:string[][]):any[] {
            var alldata:any[] = [];
            var colums:Node[] = [];
            for (var i in this.mTitle) {
                colums = colums.concat(this.mTitle[i].leaves());
            }
            let col;
            for (var i in data) {
                var rowdata:any = {};
                rowdata["id"] = data[i][0];
                for (var j in colums) {
                    col = parseInt(j) + 1;
                    rowdata[colums[j].idChain()] = data[i][col] == undefined ? "" : data[i][col];
                }
                alldata.push(rowdata);
            }
            return alldata;
        }

        //			public bindSorter(col : number, sorter, grid) {
        //				this.mColModel[col]["sorttype"] = function(cell, obj) {
        //					return sorter.weight(cell, grid.jqGrid('getGridParam',
        //							'sortorder') == "asc");
        //				};
        //			},

        public mergeRow(iCol:number, iRowStart?:number, ilen?:number) {
            if (iRowStart != undefined) {
                this.completeList.push(() => {
                    var col = this.id(iCol);
                    var grid = $("#" + this.mGridName + "");
                    //得到显示到界面的id集合
                    var mya = grid.getDataIDs();
                    for (var i = iRowStart + 1; i < mya.length && i < iRowStart + ilen; i++) {
                        grid.setCell(mya[i], col, '', {
                            display: 'none'
                        });
                    }
                    $("#" + this.mGridName + " #" + col + "" + mya[iRowStart] + "").attr("rowspan", ilen);
                    if (Util.isMSIE()){
                        //IE下overflow-y 无法正常工作
                        $("#" + this.mGridName).parent().css("overflow-y", "hidden");
                    }
                    if (this.mOnMergedRows != undefined) {
                        this.mOnMergedRows(iCol, iRowStart, ilen);
                    }
                });
            }
            else {
                this.completeList.push(() => {
                    var col = this.id(iCol);
                    var grid = $("#" + this.mGridName + "");
                    //得到显示到界面的id集合
                    var mya = grid.getDataIDs();
                    var mergelen = 1;
                    var data = grid.getCell(mya[0], col);
                    for (var i = 1; i < mya.length && i < mya.length; i++) {
                        if (data == grid.getCell(mya[i], col)) {
                            ++mergelen;
                        }
                        else {
                            if (mergelen > 1) {
                                this.mergeRow(iCol, i - mergelen, mergelen);
                            }
                            mergelen = 1;
                            data = grid.getCell(mya[i], col);
                        }
                    }
                    if (mergelen > 1) {
                        this.mergeRow(iCol, mya.length - mergelen, mergelen);
                    }
                });
            }
        }

        public getChangedData(){
            var grid = $("#" + this.mGridName + "");
            var data:Array<string[]> = [];
            for (var i in grid[0].p.data) {
                if (Util.indexOf(this.mEditedRows, grid[0].p.data[i].id) >= 0){
                    var row = [];
                    row.push(grid[0].p.data[i].id);
                    for (var j in this.mColModel) {
                        let val = grid[0].p.data[i][this.mColModel[j].index];
                        row.push(undefined == val ? "" : val + "");
                    }
                    data.push(row);
                }
            }
            return data;
        }

        public setRowBgColor(row:number, r:number, g:number, b:number):void {
            this.completeList.push(() => {
                $("#" + this.mGridName + " #" + (row + 1)).css("background", "rgb(" + r + "," + g + "," + b + ")");
            })
        }

        public mergeColum(col:number, row?:number, align?:TextAlign) {
            if (row != undefined) {
                this.completeList.push(() => {
                    if (align == undefined) {
                        align = TextAlign.Center;
                    }
                    var grid = $("#" + this.mGridName + "");
                    var mya = grid.getDataIDs();
                    var leftCell = $("#" + this.mGridName + " #" + mya[row] + " #" + this.id(col) + mya[row]);
                    var rightCell = leftCell.next();
                    leftCell.css("text-align", "right");
                    rightCell.css("text-align", "left");

                    leftCell.css("border-right-width", "0px");
                    leftCell.css("padding-right", "0px");
                    leftCell.css("disabled", "disabled");
                    rightCell.css("padding-left", "0px");
                    rightCell.css("disabled", "disabled");
                    if (this.mOnMergedColums != undefined) {
                        this.mOnMergedColums(col, mya[row]);
                    }
                    this.selectedList.push(function (newRow:number, newCol:number) {

                        if (newRow == mya[row]) {
                            if (col == newCol || col + 1 == newCol) {
                                rightCell.addClass("edit-cell");
                                rightCell.addClass("ui-state-highlight");
                                leftCell.addClass("edit-cell");
                                leftCell.addClass("ui-state-highlight");
                            } else {
                                leftCell.removeClass("edit-cell");
                                leftCell.removeClass("ui-state-highlight");
                                rightCell.removeClass("edit-cell");
                                rightCell.removeClass("ui-state-highlight");
                            }
                        }
                        else {
                            leftCell.removeClass("edit-cell");
                            leftCell.removeClass("ui-state-highlight");
                            rightCell.removeClass("edit-cell");
                            rightCell.removeClass("ui-state-highlight");
                        }
                    });
                });
            }
            else {
                this.completeList.push(() => {
                    var grid = $("#" + this.mGridName + "");
                    var mya = grid.getDataIDs();
                    for (var i = 0; i < mya.length; i++) {
                        var leftCell = $("#" + this.mGridName + " #" + mya[i] + " #" + this.id(col) + mya[i]);
                        var rightCell = leftCell.next();
                        if (leftCell.css("display") != "none" && rightCell.css("display") != "none") {
                            if (grid.getCell(mya[i], col) == grid.getCell(mya[i], col + 1)) {
                                var content = grid.getCell(mya[i], col);
                                grid.setCell(mya[i], col, content.substring(0, content.length / 2));
                                grid.setCell(mya[i], col + 1, content.substring(content.length / 2, content.length));
                                this.mergeColum(col, i);
                            }
                            else if (grid.getCell(mya[i], col + 1) == "") {
                                this.mergeColum(col, i);
                            }
                        }
                    }
                });
            }
        }

        public selected(row:number, col:number):void {
            for (var i = 0; i < this.selectedList.length; i++) {
                this.selectedList[i](row, col);
            }
        }

        private invokeFormula(depth:number = 0){
            let changed : boolean  = false;
            for (var i = 0; i < this.mFormula.length; i++) {
                if (this.mFormula[i].update()){
                    changed = true;
                }
            }
            if (changed && depth < 10){
                this.invokeFormula(++depth);
            }
        }

        public addFormula(formula:Formula):void {
            formula.setGrid($("#" + this.mGridName));
            if (this.mFormula.length == 0) {
                this.completeList.push(() => {
                    if (this.getDataCount() > 0){
                        this.invokeFormula();
                    }
                });
            }
            this.mFormula.push(formula);
        }


        private parseInt(px:string):number {
            if (undefined != px && null != px) {
                if (px.lastIndexOf("px") > 0) {
                    return parseInt(px.replace("px", ""));
                }
            }
            return 0;
        }

        public mergeTitle(iColStart?:number, iCount:number = 0, hidden:boolean = false) {
            if (iColStart != undefined) {
                this.completeList.push(() => {
                    var headerStart = $("#" + this.mGridName + "_" + this.id(iColStart));

                    var firstWidht = headerStart[0].offsetWidth;//this.parseInt(headerStart[0].style.width);
                    var iWidth = firstWidht;

                    var headerMerge:any = null;
                    var widthList = [iWidth];
                    for (var i = 1; i < iCount; i++) {
                        headerMerge = $("#" + this.mGridName + "_" + this.id(iColStart + i));
                        widthList.push(headerMerge[0].offsetWidth);//this.parseInt(headerMerge.css("width")));
                        iWidth += widthList[widthList.length - 1];
                        headerMerge.removeClass("ui-state-default");
                        headerMerge.children("span").css("display", "none");
                        headerMerge.children("div").css("display", "none");
                        headerMerge.css("width", "0px");
                        headerMerge.css("padding", "0px");
                        headerMerge.css("margin", "0px");
                        headerMerge.css("border", "0px");
                        if (hidden) {
                            headerMerge.css("display", "none");
                        }

                        var e = $("#gbox_" + this.mGridName + " .jqg-first-row-header th:eq(" + (iColStart + i) + ")")
                        e.css("width", "0px");
                        e.css("padding", "0px");
                        e.css("margin", "0px");
                        e.css("border", "0px");

                        this.disableDragCell(iColStart + i);
                    }

                    headerStart.css("width", iWidth + "px");
                    let len = headerStart[0].offsetWidth - iWidth;
                    headerStart.css("width", (iWidth - len) + "px");
                    var sizeTitle = $("#gbox_" + this.mGridName + " .jqg-first-row-header th:eq(" + iColStart + ")");
                    sizeTitle.css("width", iWidth + "px");
                    this.disableDragCell(iColStart);

                    if (this.mOnMergedTitles != undefined) {
                        this.mOnMergedTitles(iColStart, iCount);
                    }
                });
            }
            else {
                this.completeList.push(() => {

                    var mergeLen = 0;
                    var data:string = null;
                    for (var i = 0; i < this.mTitle.length; i++) {
                        if (!this.mTitle[i].hasChilds()) {
                            if (mergeLen == 0) {
                                data = this.mTitle[i].name();
                                ++mergeLen;
                            }
                            else if (data != this.mTitle[i].name()) {
                                if (mergeLen > 1) {
                                    this.mergeTitle(i - mergeLen, mergeLen, hidden);
                                }
                                mergeLen = 0;
                                data == null;
                                --i;
                            } else {
                                ++mergeLen;
                            }
                        }
                        else {
                            if (mergeLen > 1) {
                                this.mergeTitle(i - mergeLen, mergeLen, hidden);
                            }
                            mergeLen = 0;
                            data == null;
                        }
                    }

                    if (mergeLen > 1) {
                        this.mergeTitle(this.mTitle.length - mergeLen, mergeLen, hidden);
                    }


                    //						this.mResizeList.push(function(nw, iCol){
                    //							if (iCol == iColStart) {
                    //								var usedWidth = 0;
                    //								var curFirstWidth = nw;
                    //								nw += nw - firstWidht + iWidht;
                    //								sizeTitle.css("width", nw + "px");
                    //								for (var i = 0; i < widthList.Length; i++) {
                    //									usedWidth += (nw - iWidht) * widthList[i] / iWidht;
                    //									widthList[i] += (nw - iWidht) * widthList[i] / iWidht;
                    //								}
                    //								widthList[0] += nw - usedWidth;
                    //								
                    //								for (var i = 0; i < widthList.length; i++) {
                    //				("#" + this.mGridName + " .jqgfirstrow td:eq(" + i + iColStart + ")").css("width", widthList[i] + "px");
                    //								}
                    //								iWidht = nw;
                    //								firstWidht = curFirstWidth;
                    //							}
                    //						}.bind(this));

                });
            }
        }

        public disableDragCell(iCol?:number):void {
            this.completeList.push(() => {
                var spanls = $("#gview_" + this.mGridName + " .ui-jqgrid-resize");
                if (iCol == undefined) {
                    for (var i = 0; i < spanls.length; i++) {
                        spanls[i].style.visibility = "hidden";
                    }
                }
                else {
                    if (iCol < spanls.length) {
                        spanls[iCol].style.visibility = "hidden";
                    }
                }

            });
        }

        public complete():void {
            for (var i = 0; i < this.completeList.length; i++) {
                this.completeList[i]();
            }
        }

        public disableCellEdit(cells:Cell[]){
            this.mDisabledEditCells = cells;
            this.completeList.push(()=>{
                for (let i = 0; i < cells.length; ++i){
                    let id = $("#" + this.mGridName + "")[0].p.data[cells[i].row()].id;
                    $("#" + this.mGridName + " #" + id + " td:eq(" + cells[i].col() + ")").css("background", "RGB(183, 222, 232)");
                }
            });
        }

        private enablePageEdit(rowNum:number, pagername:string):any {
            var grid = $("#" + this.mGridName + "");
            let lastsel : any = "";
            let lastcell : any = "";
            let opt = {
                beforeEditCell: (rowid, cellname, v, iRow, iCol) => {
                    lastsel = iRow;
                    lastcell = iCol;
                    $("input").attr("disabled", true);



                    if (undefined != this.mDisabledEditCells){
                        let oldFun = $.jgrid.createEl;
                        for (let i = 0; i < this.mDisabledEditCells.length; ++i){
                            if (this.mDisabledEditCells[i].row() == (iRow - 1) && this.mDisabledEditCells[i].col() == iCol){
                                $.jgrid.createEl = ()=>{
                                    $.jgrid.createEl = oldFun;
                                }
                                break;
                            }
                        }
                    }
                },

                afterSaveCell: () => {
                    $("input").attr("disabled", false);
                    let ids:string[] = grid.jqGrid('getDataIDs');
                    if (Util.indexOf(this.mEditedRows, ids[lastsel - 1]) < 0){
                        this.mEditedRows.push(ids[lastsel - 1]);
                    }
                    lastsel = "";
                    this.invokeFormula();
                },

                afterRestoreCell: () => {
                    $("input").attr("disabled", false);
                    lastsel = "";
                },

                afterEditCell: (rowid, cellname, v, iRow, iCol) => {
                    let isDisabled:boolean = false;
                    if (undefined != this.mDisabledEditCells){
                        for (let i = 0; i < this.mDisabledEditCells.length; ++i){
                            if (this.mDisabledEditCells[i].row() == (iRow - 1) && this.mDisabledEditCells[i].col() == iCol){
                                isDisabled = true;
                                break;
                            }
                        }
                    }
                    if (isDisabled){
                        $("#" + this.mGridName).restoreCell(iRow, iCol);
                    }
                    $("#" + this.mGridName + " input[type=text]").bind("keydown", function (e) {
                        if (e.keyCode === 13) {
                            let ids:string[] = grid.jqGrid('getDataIDs');
                            if (ids.length > iRow){
                                setTimeout(function () {
                                    grid.jqGrid("editCell", iRow + 1, iCol, true);
                                }, 10);
                            }
                        }
                    });
                },

                beforeSaveCell: (rowid, cellname, v, iRow, iCol) => {
                    if (this.mColModel[iCol].edittype != "select" &&
                        this.mColModel[iCol].edittype != "text"){
                        var ret = parseFloat(v.replace(new RegExp(',', 'g'), ''));
                        if (isNaN(ret)) {
                           this.centerModal();
                            return v;
                        } else {
                            return ret;
                        }
                    }
                }
            }

            $('html').bind('click', (e)=> { //用于点击其他地方保存正在编辑状态下的行
                if (lastsel != "") { //if a row is selected for edit
                    if ($(e.target).closest("#" + this.mGridName).length == 0) {
                        //and the click is outside of the grid //save the row being edited and unselect the row
                        //  $("#" + name).jqGrid('saveRow', lastsel);

                        if ($('input[type=search]').length != 0){
                            return;
                        }
                        grid.jqGrid("saveCell", lastsel, lastcell);
                        //$("#" + name).resetSelection();
                        lastsel = "";
                    }
                }
            });

            if (rowNum != undefined && pagername != undefined){
                setTimeout( () => {
                    let addId = 1;
                    grid.jqGrid('navGrid', pagername, {
                        del: true, add: true, edit: false, refresh: false, search: false,
                        addfunc: () => {
                            if (lastsel != "") { //if a row is selected for edit
                                grid.jqGrid("saveCell", lastsel, lastcell);
                                lastsel = "";
                            }
                            let rid = "add" + (++addId);
                            let rdata = {};
                            rdata[rid] = rid;
                            grid.addRowData(rid, rdata, 'last');
                            let curPg = this.getCurrentPageNumber();
                            let lastRowPg = parseInt("" + (this.getDataCount() - 1) / rowNum) + 1;
                            if (curPg != lastRowPg) {//当前页已经填满，切换到最后一页
                                this.navigateToPage(lastRowPg);
                            }

                            setTimeout(() => {
                                let ids:string[] = grid.jqGrid('getDataIDs');
                                //使用ids.length 为实验测试获得
                                grid.jqGrid("editCell", ids.length, 0, true);
                            }, 100);


                        },
                        delfunc: (rowid)=> {
                            if (lastsel != "") { //if a row is selected for edit
                                grid.jqGrid("saveCell", lastsel, lastcell);
                                lastsel = "";
                            }

                            if (rowid.indexOf("add") < 0) {
                                this.showError("数据已保存，无法删除");
                                return;
                            }
                            let ind = Util.indexOf(this.mEditedRows, rowid);
                            if (ind >= 0){
                                this.mEditedRows.splice(ind, 1);
                            }

                            let ids:string[] = grid.jqGrid('getDataIDs');
                            grid.jqGrid("delRowData", rowid);
                            grid.trigger("reloadGrid");

                            let dataCount = this.getDataCount();
                            if (dataCount == 0){
                                return;
                            }

                            let curPg = this.getCurrentPageNumber();
                            let lastRowPg = parseInt("" + (dataCount - 1) / rowNum) + 1;
                            let curSelId = null;
                            if (curPg > lastRowPg) {//最后一页数据已经全部被删除，切换到最新的last page
                                this.navigateToPage(lastRowPg);
                                ids = grid.jqGrid('getDataIDs');
                                curSelId = ids.length - 1;
                            } else {
                                for (let i = 0; i < ids.length; ++i) {
                                    if (ids[i] == rowid) {
                                        if (i == ids.length - 1) {
                                            curSelId = i - 1;
                                        } else if (i == 0) {
                                            curSelId = 0;
                                        } else {
                                            curSelId = i;
                                        }
                                        break;
                                    }
                                }
                            }
                            //curSelId 值为实验测试获得
                            grid.jqGrid("editCell", curSelId + 1, 0, false);

                        }
                    }, {}, {}, {multipleSearch: false});
                }, 0);
            }
            return opt;
        }

        public decorate(option:any):any {
            if (option.gridComplete != undefined) {
                var gridComplete = option.gridComplete;
                option.gridComplete = () => {
                    gridComplete();
                    this.complete();
                };
            }
            else {
                option.gridComplete = () => {
                    this.complete();
                };
            }

            if (option.onSelectCell != undefined) {
                var onSelectCell = option.onSelectCell;
                option.onSelectCell = (id:any, nm:any, tmp:any, iRow:any, iCol:any) => {
                    onSelectCell(id, nm, tmp, iRow, iCol);
                    this.selected(iRow, iCol);
                };
            }
            else {
                option.onSelectCell = (id:any, nm:any, tmp:any, iRow:any, iCol:any) => {
                    this.selected(iRow, iCol);
                };
            }

            if (option.beforeEditCell != undefined) {
                var beforeEditCell = option.beforeEditCell;
                option.beforeEditCell = (id:any, nm:any, tmp:any, iRow:any, iCol:any) => {
                    beforeEditCell(id, nm, tmp, iRow, iCol);
                    this.selected(iRow, iCol);
                };

            }
            else {
                option.beforeEditCell = (id:any, nm:any, tmp:any, iRow:any, iCol:any) => {
                    this.selected(iRow, iCol);
                };
            }

            if (option.afterSaveCell != undefined) {
                var afterSaveCell = option.afterSaveCell;
                option.afterSaveCell = (id:any, nm:any, tmp:any, iRow:any, iCol:any) => {
                    afterSaveCell(id, nm, tmp, iRow, iCol);
                    //this.cellChanged(iRow, iCol);
                };
            }
            //else {
            //    option.afterSaveCell = (id:any, nm:any, tmp:any, iRow:any, iCol:any) => {
            //        this.cellChanged(iRow, iCol);
            //    };
            //}

            if (option.resizeStop != undefined) {
                var resizeStop = option.resizeStop;
                option.resizeStop = (nw:number, idx:number) => {
                    resizeStop(nw, idx);
                    this.onResized(nw, idx);
                };
            }
            else {
                option.resizeStop = (nw:number, idx:number) => {
                    this.onResized(nw, idx);
                };
            }


            if (option.colNames == undefined) {
                option.colNames = this.getColNames();
            }

            if (option.colModel == undefined) {
                option.colModel = this.getColModel();
            }

            if (option.loadError != undefined) {
                var onFailed:() => void = option.loadError;
                option.loadError = () => {
                    onFailed();
                }
            }

            if (option.assistEditable) {
                $.extend(option, this.enablePageEdit(option.rowNum, option.pager));
            }


            this.mOnMergedRows = option.onMergedRows;
            this.mOnMergedColums = option.onMergedColums;
            this.mOnMergedTitles = option.onMergedTitles;

            return option;
        }
    }
}
