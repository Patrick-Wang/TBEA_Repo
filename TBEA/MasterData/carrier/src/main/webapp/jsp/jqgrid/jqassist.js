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
            var ts = this, i, cmi, skip = 0, $tr, $colHeader, th, $th, thStyle, iCol, cghi, numberOfColumns, titleText, cVisibleColumns, colModel = ts.p.colModel, cml = colModel.length, ths = ts.grid.headers, $htable = $("table.ui-jqgrid-htable", ts.grid.hDiv), $trLabels = $htable
                .children("thead").children("tr.ui-jqgrid-labels:last").addClass("jqg-second-row-header"), $thead = $htable
                .children("thead"), $theadInTable, $firstHeaderRow = $htable
                .find(".jqg-first-row-header");
            if ($firstHeaderRow[0] === undefined) {
                $firstHeaderRow = $('<tr>', {
                    role: "row",
                    "aria-hidden": "true"
                }).addClass("jqg-first-row-header").css("height", "auto");
            }
            else {
                $firstHeaderRow.empty();
            }
            var $firstRow, inColumnHeader = function (text, columnHeaders) {
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
                thStyle = {
                    height: '0px',
                    width: ths[i].width + 'px',
                    display: (cmi.hidden ? 'none' : '')
                };
                $("<th>", {
                    role: 'gridcell'
                }).css(thStyle).addClass("ui-first-th-" + ts.p.direction).appendTo($firstHeaderRow);
                th.style.width = "";
                iCol = inColumnHeader(cmi.name, o.groupHeaders);
                if (iCol >= 0) {
                    cghi = o.groupHeaders[iCol];
                    numberOfColumns = cghi.numberOfColumns;
                    titleText = cghi.titleText;
                    for (cVisibleColumns = 0, iCol = 0; iCol < numberOfColumns
                        && (i + iCol < cml); iCol++) {
                        if (!colModel[i + iCol].hidden) {
                            cVisibleColumns++;
                        }
                    }
                    $colHeader = $('<th>').attr({
                        role: "columnheader"
                    }).addClass("ui-state-default ui-th-column-header ui-th-"
                        + ts.p.direction).css({
                        'height': '22px',
                        'border-top': '0 none'
                    }).html(titleText);
                    if (cVisibleColumns > 0) {
                        $colHeader.attr("colspan", String(cVisibleColumns));
                    }
                    if (ts.p.headertitles) {
                        $colHeader.attr("title", $colHeader.text());
                    }
                    if (cVisibleColumns === 0) {
                        $colHeader.hide();
                    }
                    $th.before($colHeader);
                    $tr.append(th);
                    skip = numberOfColumns - 1;
                }
                else {
                    if (skip === 0) {
                        if (o.useColSpanStyle) {
                            $th.attr("rowspan", o.depth + "");
                        }
                        else {
                            $('<th>', {
                                role: "columnheader"
                            }).addClass("ui-state-default ui-th-column-header ui-th-"
                                + ts.p.direction).css({
                                "display": cmi.hidden ? 'none' : '',
                                'border-top': '0 none'
                            }).insertBefore($th);
                            $tr.append(th);
                        }
                    }
                    else {
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
                $htable
                    .find("span.ui-jqgrid-resize")
                    .each(function () {
                    var $parent = $(this).parent();
                    if ($parent.is(":visible")) {
                        this.style.cssText = 'height: '
                            + $parent.height()
                            + 'px !important; cursor: col-resize;';
                    }
                });
                $htable
                    .find("div.ui-jqgrid-sortable")
                    .each(function () {
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
            $(ts).bind('jqGridResizeStop.setGroupHeaders', function (e, nw, idx) {
                $firstRow.find('th').eq(idx).width(nw);
            });
        });
    };
})();
var JQTable;
(function (JQTable) {
    (function (TextAlign) {
        TextAlign[TextAlign["Left"] = 0] = "Left";
        TextAlign[TextAlign["Right"] = 1] = "Right";
        TextAlign[TextAlign["Center"] = 2] = "Center";
    })(JQTable.TextAlign || (JQTable.TextAlign = {}));
    var TextAlign = JQTable.TextAlign;
    var Node = (function () {
        function Node(name, id, isReadOnly, align, width, editType, options, isNumber) {
            if (isReadOnly === void 0) { isReadOnly = true; }
            if (align === void 0) { align = TextAlign.Right; }
            if (width === void 0) { width = 0; }
            if (editType === void 0) { editType = undefined; }
            if (options === void 0) { options = undefined; }
            if (isNumber === void 0) { isNumber = true; }
            this.mChilds = [];
            this.mParent = null;
            this.mOpts = {
                name: name,
                id: id,
                isReadOnly: isReadOnly,
                isNumber: isNumber,
                align: align,
                width: width,
                editType: editType,
                options: options
            };
        }
        Node.create = function (opts) {
            var node = new Node(null, null);
            node.mOpts = $.extend({}, {
                isReadOnly: false,
                isNumber: true,
                align: TextAlign.Right,
                width: 0
            }, opts);
            return node;
        };
        Node.prototype.align = function () {
            return this.mOpts.align;
        };
        Node.prototype.width = function () {
            if (this.mOpts.width == undefined) {
                return -1;
            }
            return this.mOpts.width;
        };
        Node.prototype.isReadOnly = function () {
            return true == this.mOpts.isReadOnly;
        };
        Node.prototype.isNumber = function () {
            return true == this.mOpts.isNumber;
        };
        Node.prototype.editType = function () {
            return this.mOpts.editType;
        };
        Node.prototype.editOptions = function () {
            return this.mOpts.options;
        };
        Node.prototype.append = function (child) {
            this.mChilds.push(child);
            child.mParent = this;
            return this;
        };
        Node.prototype.parent = function () {
            return this.mParent;
        };
        Node.prototype.hasChilds = function () {
            return this.mChilds.length > 0;
        };
        Node.prototype.childs = function () {
            return this.mChilds;
        };
        Node.prototype.idChain = function () {
            if (this.mParent != null) {
                return this.mParent.idChain() + "_" + this.mOpts.id;
            }
            return this.mOpts.id;
        };
        Node.prototype.id = function () {
            return this.mOpts.id;
        };
        Node.prototype.leavesCount = function () {
            var count = 0;
            for (var i in this.mChilds) {
                if (this.mChilds[i].hasChilds()) {
                    count += this.mChilds[i].leavesCount();
                }
                else {
                    ++count;
                }
            }
            return count;
        };
        Node.prototype.mostLeftLeaf = function () {
            var node = this;
            if (this.hasChilds()) {
                node = this.mChilds[0].mostLeftLeaf();
            }
            return node;
        };
        Node.prototype.leaves = function () {
            var list = [];
            if (this.hasChilds()) {
                for (var i in this.mChilds) {
                    if (this.mChilds[i].hasChilds()) {
                        list = list.concat(this.mChilds[i].leaves());
                    }
                    else {
                        list.push(this.mChilds[i]);
                    }
                }
            }
            else {
                list.push(this);
            }
            return list;
        };
        Node.prototype.depth = function () {
            var childDepth = 0;
            for (var i = 0; i < this.mChilds.length; i++) {
                if (this.mChilds[i].depth() > childDepth) {
                    childDepth = this.mChilds[i].depth();
                }
            }
            return childDepth + 1;
        };
        Node.prototype.name = function () {
            return this.mOpts.name;
        };
        Node.prototype.children = function (depth) {
            var children = new std.vector();
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
        };
        return Node;
    }());
    JQTable.Node = Node;
    var Cell = (function () {
        function Cell(row, col) {
            this.mRow = row;
            this.mCol = col;
        }
        Cell.prototype.setGrid = function (grid) {
            this.mGrid = grid;
        };
        Cell.prototype.getVal = function () {
            return this.mGrid.jqGrid("getCell", this.mRow + 1, this.mCol);
        };
        Cell.prototype.setVal = function (val) {
            return this.mGrid.jqGrid("setCell", this.mRow + 1, this.mCol, val);
        };
        Cell.prototype.row = function () {
            return this.mRow;
        };
        Cell.prototype.col = function () {
            return this.mCol;
        };
        return Cell;
    }());
    JQTable.Cell = Cell;
    var Formula = (function () {
        function Formula(destCell, srcCellarray, formula) {
            this.mDestCell = destCell;
            this.mSrcCellarray = srcCellarray;
            this.mFormula = formula;
        }
        Formula.prototype.calc = function (newRow, newCol) {
            var oldVal = this.mDestCell.getVal();
            for (var i = 0; i < this.mSrcCellarray.length; i++) {
                if (this.mSrcCellarray[i].row() == newRow && this.mSrcCellarray[i].col() == newCol) {
                    var newVal = this.mFormula(this.mDestCell, this.mSrcCellarray);
                    if (newVal != null && newVal != undefined && oldVal != newVal) {
                        this.mDestCell.setVal(newVal);
                        return true;
                    }
                }
            }
            return false;
        };
        Formula.prototype.destCell = function () {
            return this.mDestCell;
        };
        Formula.prototype.setGrid = function (grid) {
            this.mDestCell.setGrid(grid);
            for (var i = 0; i < this.mSrcCellarray.length; i++) {
                this.mSrcCellarray[i].setGrid(grid);
            }
        };
        Formula.prototype.update = function () {
            var newVal = this.mFormula(this.mDestCell, this.mSrcCellarray);
            if (newVal != null && newVal != undefined) {
                this.mDestCell.setVal(newVal);
            }
        };
        return Formula;
    }());
    JQTable.Formula = Formula;
    var JQGridAssistant = (function () {
        function JQGridAssistant(titleNodes, gridName) {
            this.completeList = [];
            this.selectedList = [];
            this.mTitle = [];
            this.mColModel = [];
            this.mResizeList = [];
            this.mFormula = [];
            var nodes;
            this.mGridName = gridName;
            this.mTitle = titleNodes;
            for (var i in this.mTitle) {
                nodes = this.mTitle[i].leaves();
                for (var j in nodes) {
                    var colId = nodes[j].idChain();
                    this.mColModel.push({
                        name: colId,
                        index: colId,
                        sortable: false,
                        editable: !nodes[j].isReadOnly(),
                        edittype: nodes[j].editType(),
                        editoptions: nodes[j].editOptions(),
                        editrules: !nodes[j].isReadOnly() ? { number: nodes[j].isNumber() } : undefined,
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
        JQGridAssistant.prototype.getCheckedRowIds = function () {
            var ids = $("#" + this.mGridName + "").jqGrid('getGridParam', 'selarrrow');
            return ids;
        };
        JQGridAssistant.prototype.getAllData = function () {
            var grid = $("#" + this.mGridName + "");
            var ids = grid.jqGrid('getDataIDs');
            var data = [];
            for (var i in ids) {
                var row = [];
                row.push(ids[i]);
                for (var j in this.mColModel) {
                    row.push(grid.jqGrid("getCell", ids[i], this.mColModel[j].index));
                }
                data.push(row);
            }
            return data;
        };
        JQGridAssistant.prototype.testDepth = function () {
            var depth = 1;
            var tmp = 0;
            for (var node in this.mTitle) {
                tmp = this.mTitle[node].depth();
                if (depth < tmp) {
                    depth = tmp;
                }
            }
            return depth;
        };
        JQGridAssistant.prototype.getColNames = function () {
            var leaves = [];
            var names = [];
            for (var node in this.mTitle) {
                leaves = this.mTitle[node].leaves();
                for (var leaf in leaves) {
                    names.push(leaves[leaf].name());
                }
            }
            return names;
        };
        JQGridAssistant.prototype.getColModel = function () {
            return this.mColModel;
        };
        JQGridAssistant.prototype.addRowData = function (rowId, rowData) {
            var grid = $("#" + this.mGridName + "");
            var row = {};
            for (var j in this.mColModel) {
                if (j < rowData.length) {
                    row[this.mColModel[j].index] = rowData[j];
                }
            }
            grid.jqGrid("addRowData", rowId, row);
        };
        JQGridAssistant.prototype.id = function (col) {
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
        };
        JQGridAssistant.prototype.onResized = function (nw, idx) {
            for (var i = 0; i < this.mResizeList.length; i++) {
                this.mResizeList[i](nw, idx);
            }
        };
        JQGridAssistant.prototype.group = function () {
            var _this = this;
            this.completeList.push(function () {
                var nodes = [];
                var headers = [];
                var grid = $("#" + _this.mGridName + "");
                for (var i = 0; i < _this.mDepth - 1; ++i) {
                    nodes = _this.getLevelNodes(i);
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
                        depth: _this.mDepth,
                        useColSpanStyle: true,
                        groupHeaders: headers
                    });
                }
            });
        };
        JQGridAssistant.prototype.getLevelNodes = function (level) {
            var levelNodes = new std.vector();
            for (var i = 0; i < this.mTitle.length; i++) {
                levelNodes.concat(this.mTitle[i].children(level));
                for (var j = levelNodes.size() - 1; j >= 0; --j) {
                    if (!levelNodes.get(j).hasChilds()) {
                        levelNodes.erase(j);
                    }
                }
            }
            return levelNodes.toArray();
        };
        JQGridAssistant.prototype.getData = function (data) {
            var alldata = [];
            var colums = [];
            for (var i in this.mTitle) {
                colums = colums.concat(this.mTitle[i].leaves());
            }
            for (var i in data) {
                var rowdata = {};
                for (var j in colums) {
                    rowdata[colums[j].idChain()] = data[i][j];
                }
                alldata.push(rowdata);
            }
            return alldata;
        };
        JQGridAssistant.prototype.getDataWithId = function (data) {
            var alldata = [];
            var colums = [];
            for (var i in this.mTitle) {
                colums = colums.concat(this.mTitle[i].leaves());
            }
            for (var i in data) {
                var rowdata = {};
                rowdata["id"] = data[i][0];
                for (var j in colums) {
                    rowdata[colums[j].idChain()] = data[i][parseInt(j) + 1];
                }
                alldata.push(rowdata);
            }
            return alldata;
        };
        JQGridAssistant.prototype.mergeRow = function (iCol, iRowStart, ilen) {
            var _this = this;
            if (iRowStart != undefined) {
                this.completeList.push(function () {
                    var col = _this.id(iCol);
                    var grid = $("#" + _this.mGridName + "");
                    var mya = grid.getDataIDs();
                    for (var i = iRowStart + 1; i < mya.length && i < iRowStart + ilen; i++) {
                        grid.setCell(mya[i], col, '', {
                            display: 'none'
                        });
                    }
                    $("#" + col + "" + mya[iRowStart] + "").attr("rowspan", ilen);
                    if (_this.mOnMergedRows != undefined) {
                        _this.mOnMergedRows(iCol, iRowStart, ilen);
                    }
                });
            }
            else {
                this.completeList.push(function () {
                    var col = _this.id(iCol);
                    var grid = $("#" + _this.mGridName + "");
                    var mya = grid.getDataIDs();
                    var mergelen = 1;
                    var data = grid.getCell(mya[0], col);
                    for (var i = 1; i < mya.length && i < mya.length; i++) {
                        if (data == grid.getCell(mya[i], col)) {
                            ++mergelen;
                        }
                        else {
                            if (mergelen > 1) {
                                _this.mergeRow(iCol, i - mergelen, mergelen);
                            }
                            mergelen = 1;
                            data = grid.getCell(mya[i], col);
                        }
                    }
                    if (mergelen > 1) {
                        _this.mergeRow(iCol, mya.length - mergelen, mergelen);
                    }
                });
            }
        };
        JQGridAssistant.prototype.setRowBgColor = function (row, r, g, b) {
            var _this = this;
            this.completeList.push(function () {
                $("#" + _this.mGridName + " #" + (row + 1)).css("background", "rgb(" + r + "," + g + "," + b + ")");
            });
        };
        JQGridAssistant.prototype.mergeColum = function (col, row, align) {
            var _this = this;
            if (row != undefined) {
                this.completeList.push(function () {
                    if (align == undefined) {
                        align = TextAlign.Center;
                    }
                    ++row;
                    var leftCell = $("#" + _this.mGridName + " #" + row + " #" + _this.id(col) + row);
                    var rightCell = leftCell.next();
                    leftCell.css("text-align", "right");
                    rightCell.css("text-align", "left");
                    leftCell.css("border-right-width", "0px");
                    leftCell.css("padding-right", "0px");
                    leftCell.css("disabled", "disabled");
                    rightCell.css("padding-left", "0px");
                    rightCell.css("disabled", "disabled");
                    if (_this.mOnMergedColums != undefined) {
                        _this.mOnMergedColums(col, row);
                    }
                    _this.selectedList.push(function (newRow, newCol) {
                        if (newRow == row) {
                            if (col == newCol || col + 1 == newCol) {
                                rightCell.addClass("edit-cell");
                                rightCell.addClass("ui-state-highlight");
                                leftCell.addClass("edit-cell");
                                leftCell.addClass("ui-state-highlight");
                            }
                            else {
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
                this.completeList.push(function () {
                    var grid = $("#" + _this.mGridName + "");
                    var mya = grid.getDataIDs();
                    for (var i = 0; i < mya.length; i++) {
                        var leftCell = $("#" + _this.mGridName + " #" + mya[i] + " #" + _this.id(col) + mya[i]);
                        var rightCell = leftCell.next();
                        if (leftCell.css("display") != "none" && rightCell.css("display") != "none") {
                            if (grid.getCell(mya[i], col) == grid.getCell(mya[i], col + 1)) {
                                var content = grid.getCell(mya[i], col);
                                grid.setCell(mya[i], col, content.substring(0, content.length / 2));
                                grid.setCell(mya[i], col + 1, content.substring(content.length / 2, content.length));
                                _this.mergeColum(col, i);
                            }
                            else if (grid.getCell(mya[i], col + 1) == "") {
                                _this.mergeColum(col, i);
                            }
                        }
                    }
                });
            }
        };
        JQGridAssistant.prototype.selected = function (row, col) {
            for (var i = 0; i < this.selectedList.length; i++) {
                this.selectedList[i](row, col);
            }
        };
        JQGridAssistant.prototype.addFormula = function (formula) {
            formula.setGrid($("#" + this.mGridName));
            if (this.mFormula.length == 0) {
                this.completeList.push(function () {
                    for (var i = 0; i < this.mFormula.length; i++) {
                        this.mFormula[i].update();
                    }
                }.bind(this));
            }
            this.mFormula.push(formula);
        };
        JQGridAssistant.prototype.cellChanged = function (iRow, iCol) {
            for (var i = 0; i < this.mFormula.length; i++) {
                var changed = this.mFormula[i].calc(iRow - 1, iCol);
                if (changed) {
                    this.cellChanged(this.mFormula[i].destCell().row() + 1, this.mFormula[i].destCell().col());
                }
            }
        };
        JQGridAssistant.prototype.mergeTitle = function (iColStart, iCount, hidden) {
            var _this = this;
            if (iCount === void 0) { iCount = 0; }
            if (hidden === void 0) { hidden = false; }
            if (iColStart != undefined) {
                this.completeList.push(function () {
                    var headerStart = $("#" + _this.mGridName + "_" + _this.id(iColStart));
                    var firstWidht = parseInt(headerStart.css("width").replace("px", ""));
                    ;
                    var iWidht = firstWidht;
                    var headerMerge = null;
                    var widthList = [iWidht];
                    for (var i = 1; i < iCount; i++) {
                        headerMerge = $("#" + _this.mGridName + "_" + _this.id(iColStart + i));
                        widthList.push(parseInt(headerMerge.css("width").replace("px", "")) +
                            parseInt(headerMerge.css("padding-left").replace("px", "")) +
                            parseInt(headerMerge.css("padding-right").replace("px", "")));
                        iWidht += widthList[widthList.length - 1];
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
                        var e = $("#gbox_" + _this.mGridName + " .jqg-first-row-header th:eq(" + (iColStart + i) + ")");
                        e.css("width", "0px");
                        e.css("padding", "0px");
                        e.css("margin", "0px");
                        e.css("border", "0px");
                        _this.disableDragCell(iColStart + i);
                    }
                    headerStart.css("width", iWidht + 1 + "px");
                    var sizeTitle = $("#gbox_" + _this.mGridName + " .jqg-first-row-header th:eq(" + iColStart + ")");
                    sizeTitle.css("width", iWidht + 1 + "px");
                    _this.disableDragCell(iColStart);
                    if (_this.mOnMergedTitles != undefined) {
                        _this.mOnMergedTitles(iColStart, iCount);
                    }
                });
            }
            else {
                this.completeList.push(function () {
                    var mergeLen = 0;
                    var data = null;
                    for (var i = 0; i < _this.mTitle.length; i++) {
                        if (!_this.mTitle[i].hasChilds()) {
                            if (mergeLen == 0) {
                                data = _this.mTitle[i].name();
                                ++mergeLen;
                            }
                            else if (data != _this.mTitle[i].name()) {
                                if (mergeLen > 1) {
                                    _this.mergeTitle(i - mergeLen, mergeLen, hidden);
                                }
                                mergeLen = 0;
                                data == null;
                                --i;
                            }
                            else {
                                ++mergeLen;
                            }
                        }
                        else {
                            if (mergeLen > 1) {
                                _this.mergeTitle(i - mergeLen, mergeLen, hidden);
                            }
                            mergeLen = 0;
                            data == null;
                        }
                    }
                    if (mergeLen > 1) {
                        _this.mergeTitle(_this.mTitle.length - mergeLen, mergeLen, hidden);
                    }
                });
            }
        };
        JQGridAssistant.prototype.disableDragCell = function (iCol) {
            var _this = this;
            this.completeList.push(function () {
                var spanls = $("#gview_" + _this.mGridName + " .ui-jqgrid-resize");
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
        };
        JQGridAssistant.prototype.complete = function () {
            for (var i = 0; i < this.completeList.length; i++) {
                this.completeList[i]();
            }
        };
        JQGridAssistant.prototype.decorate = function (option) {
            var _this = this;
            if (option.gridComplete != undefined) {
                var gridComplete = option.gridComplete;
                option.gridComplete = function () {
                    gridComplete();
                    _this.complete();
                };
            }
            else {
                option.gridComplete = function () {
                    _this.complete();
                };
            }
            if (option.onSelectCell != undefined) {
                var onSelectCell = option.onSelectCell;
                option.onSelectCell = function (id, nm, tmp, iRow, iCol) {
                    onSelectCell(id, nm, tmp, iRow, iCol);
                    _this.selected(iRow, iCol);
                };
            }
            else {
                option.onSelectCell = function (id, nm, tmp, iRow, iCol) {
                    _this.selected(iRow, iCol);
                };
            }
            if (option.beforeEditCell != undefined) {
                var beforeEditCell = option.beforeEditCell;
                option.beforeEditCell = function (id, nm, tmp, iRow, iCol) {
                    beforeEditCell(id, nm, tmp, iRow, iCol);
                    _this.selected(iRow, iCol);
                };
            }
            else {
                option.beforeEditCell = function (id, nm, tmp, iRow, iCol) {
                    _this.selected(iRow, iCol);
                };
            }
            if (option.afterSaveCell != undefined) {
                var afterSaveCell = option.afterSaveCell;
                option.afterSaveCell = function (id, nm, tmp, iRow, iCol) {
                    afterSaveCell(id, nm, tmp, iRow, iCol);
                    _this.cellChanged(iRow, iCol);
                };
            }
            else {
                option.afterSaveCell = function (id, nm, tmp, iRow, iCol) {
                    _this.cellChanged(iRow, iCol);
                };
            }
            if (option.resizeStop != undefined) {
                var resizeStop = option.resizeStop;
                option.resizeStop = function (nw, idx) {
                    resizeStop(nw, idx);
                    _this.onResized(nw, idx);
                };
            }
            else {
                option.resizeStop = function (nw, idx) {
                    _this.onResized(nw, idx);
                };
            }
            if (option.colNames == undefined) {
                option.colNames = this.getColNames();
            }
            if (option.colModel == undefined) {
                option.colModel = this.getColModel();
            }
            if (option.loadError != undefined) {
                var onFailed = option.loadError;
                option.loadError = function () {
                    onFailed();
                };
            }
            this.mOnMergedRows = option.onMergedRows;
            this.mOnMergedColums = option.onMergedColums;
            this.mOnMergedTitles = option.onMergedTitles;
            return option;
        };
        return JQGridAssistant;
    }());
    JQTable.JQGridAssistant = JQGridAssistant;
})(JQTable || (JQTable = {}));
