var JQTable;
(function (JQTable) {
    $.jgrid.extend({
        setGroupHeaders: function (o) {
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
                numberOfColumns, titleText, cVisibleColumns, colModel = ts.p.colModel, cml = colModel.length, ths = ts.grid.headers, $htable = $("table.ui-jqgrid-htable", ts.grid.hDiv), $trLabels = $htable
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
                    // build the next cell for the first header row
                    thStyle = {
                        height: '0px',
                        width: ths[i].width + 'px',
                        display: (cmi.hidden ? 'none' : '')
                    };
                    $("<th>", {
                        role: 'gridcell'
                    }).css(thStyle).addClass("ui-first-th-" + ts.p.direction).appendTo($firstHeaderRow);
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
                    }
                    else {
                        if (skip === 0) {
                            if (o.useColSpanStyle) {
                                // expand the header height to two rows
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
                        .each(function () {
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
        },
        setGridWidth: function (nwidth, shrink) {
            return this.each(function () {
                if (!this.grid) {
                    return;
                }
                var $t = this, cw, initwidth = 0, brd = $.jgrid.cell_width ? 0 : $t.p.cellLayout, lvc, vc = 0, hs = false, scw = $t.p.scrollOffset, aw, gw = 0, cr;
                if (typeof shrink !== 'boolean') {
                    shrink = $t.p.shrinkToFit;
                }
                if (isNaN(nwidth)) {
                    return;
                }
                var oldWidth = $t.grid.width;
                if (oldWidth == nwidth) {
                    return;
                }
                nwidth = parseInt(nwidth, 10);
                $t.grid.width = $t.p.width = nwidth;
                $("#gbox_" + $.jgrid.jqID($t.p.id)).css("width", nwidth + "px");
                $("#gview_" + $.jgrid.jqID($t.p.id)).css("width", nwidth + "px");
                $($t.grid.bDiv).css("width", nwidth + "px");
                $($t.grid.hDiv).css("width", nwidth + "px");
                if ($t.p.pager) {
                    $($t.p.pager).css("width", nwidth + "px");
                }
                if ($t.p.toppager) {
                    $($t.p.toppager).css("width", nwidth + "px");
                }
                if ($t.p.toolbar[0] === true) {
                    $($t.grid.uDiv).css("width", nwidth + "px");
                    if ($t.p.toolbar[1] === "both") {
                        $($t.grid.ubDiv).css("width", nwidth + "px");
                    }
                }
                if ($t.p.footerrow) {
                    $($t.grid.sDiv).css("width", nwidth + "px");
                }
                if (shrink === false && $t.p.forceFit === true) {
                    $t.p.forceFit = false;
                }
                if (shrink === true) {
                    $.each($t.p.colModel, function () {
                        if (this.hidden === false) {
                            cw = this.widthOrg;
                            initwidth += cw + brd;
                            if (this.fixed) {
                                gw += cw + brd;
                            }
                            else {
                                vc++;
                            }
                        }
                    });
                    if (vc === 0) {
                        return;
                    }
                    $t.p.tblwidth = initwidth;
                    aw = nwidth - brd * vc - gw;
                    if (!isNaN($t.p.height)) {
                        if ($($t.grid.bDiv)[0].clientHeight < $($t.grid.bDiv)[0].scrollHeight || $t.rows.length === 1) {
                            hs = true;
                            aw -= scw;
                        }
                    }
                    initwidth = 0;
                    var cle = $t.grid.cols.length > 0;
                    $.each($t.p.colModel, function (i) {
                        if (this.hidden === false && !this.fixed) {
                            cw = this.widthOrg;
                            cw = Math.round(aw * cw / ($t.p.tblwidth - brd * vc - gw));
                            if (cw < 0) {
                                return;
                            }
                            this.width = cw;
                            initwidth += cw;
                            $t.grid.headers[i].width = cw;
                            $t.grid.headers[i].el.style.width = cw + "px";
                            $($t.grid.headers[i].el).parent().parent().children().eq(0).find("th").eq(i)[0].style.width = cw + "px";
                            if ($t.p.footerrow) {
                                $t.grid.footers[i].style.width = cw + "px";
                            }
                            if (cle) {
                                $t.grid.cols[i].style.width = cw + "px";
                            }
                            lvc = i;
                        }
                    });
                    if (!lvc) {
                        return;
                    }
                    cr = 0;
                    if (hs) {
                        if (nwidth - gw - (initwidth + brd * vc) !== scw) {
                            cr = nwidth - gw - (initwidth + brd * vc) - scw;
                        }
                    }
                    else if (Math.abs(nwidth - gw - (initwidth + brd * vc)) !== 1) {
                        cr = nwidth - gw - (initwidth + brd * vc);
                    }
                    $t.p.colModel[lvc].width += cr;
                    $t.p.tblwidth = initwidth + cr + brd * vc + gw;
                    if ($t.p.tblwidth > nwidth) {
                        var delta = $t.p.tblwidth - parseInt(nwidth, 10);
                        $t.p.tblwidth = nwidth;
                        cw = $t.p.colModel[lvc].width = $t.p.colModel[lvc].width - delta;
                    }
                    else {
                        cw = $t.p.colModel[lvc].width;
                    }
                    $t.grid.headers[lvc].width = cw;
                    $t.grid.headers[lvc].el.style.width = cw + "px";
                    $($t.grid.headers[lvc].el).parent().parent().children().eq(0).find("th").eq(lvc)[0].style.width = cw + "px";
                    if (cle) {
                        $t.grid.cols[lvc].style.width = cw + "px";
                    }
                    if ($t.p.footerrow) {
                        $t.grid.footers[lvc].style.width = cw + "px";
                    }
                }
                if ($t.p.tblwidth) {
                    $('table:first', $t.grid.bDiv).css("width", $t.p.tblwidth + "px");
                    $('table:first', $t.grid.hDiv).css("width", $t.p.tblwidth + "px");
                    $t.grid.hDiv.scrollLeft = $t.grid.bDiv.scrollLeft;
                    if ($t.p.footerrow) {
                        $('table:first', $t.grid.sDiv).css("width", $t.p.tblwidth + "px");
                    }
                }
                //触发onComplete 重新调整合并表头
                if ($.isFunction($t.p.gridComplete) && oldWidth != nwidth) {
                    $t.p.gridComplete.call($t);
                }
            });
        }
    });
    window['__jqassistDragEnd'] = function (ts, p) {
        this.hDiv.style.cursor = "default";
        if (this.resizing) {
            var idx = this.resizing.idx, nw = this.headers[idx].newWidth || this.headers[idx].width;
            nw = parseInt(nw, 10);
            this.resizing = false;
            $("#rs_m" + $.jgrid.jqID(p.id)).css("display", "none");
            p.colModel[idx].width = nw;
            this.headers[idx].width = nw;
            this.headers[idx].el.style.width = nw + "px";
            $(this.headers[idx].el).parent().parent().children().eq(0).find("th").eq(idx)[0].style.width = nw + "px";
            this.cols[idx].style.width = nw + "px";
            if (this.footers.length > 0) {
                this.footers[idx].style.width = nw + "px";
            }
            if (p.forceFit === true) {
                nw = this.headers[idx + p.nv].newWidth || this.headers[idx + p.nv].width;
                this.headers[idx + p.nv].width = nw;
                this.headers[idx + p.nv].el.style.width = nw + "px";
                $(this.headers[idx + p.nv].el).parent().parent().children().eq(0).find("th").eq(idx + p.nv)[0].style.width = nw + "px";
                this.cols[idx + p.nv].style.width = nw + "px";
                if (this.footers.length > 0) {
                    this.footers[idx + p.nv].style.width = nw + "px";
                }
                p.colModel[idx + p.nv].width = nw;
            }
            else {
                p.tblwidth = this.newWidth || p.tblwidth;
                $('table:first', this.bDiv).css("width", p.tblwidth + "px");
                $('table:first', this.hDiv).css("width", p.tblwidth + "px");
                this.hDiv.scrollLeft = this.bDiv.scrollLeft;
                if (p.footerrow) {
                    $('table:first', this.sDiv).css("width", p.tblwidth + "px");
                    this.sDiv.scrollLeft = this.bDiv.scrollLeft;
                }
            }
            //$(ts).triggerHandler("jqGridResizeStop", [nw, idx]);
            if ($.isFunction(p.resizeStop)) {
                p.resizeStop.call(ts, nw, idx);
            }
        }
        this.curGbox = null;
        document.onselectstart = function () {
            return true;
        };
    };
    var TextAlign;
    (function (TextAlign) {
        TextAlign[TextAlign["Left"] = 0] = "Left";
        TextAlign[TextAlign["Right"] = 1] = "Right";
        TextAlign[TextAlign["Center"] = 2] = "Center";
    })(TextAlign = JQTable.TextAlign || (JQTable.TextAlign = {}));
    JQTable.NodeId = (function (idBase) {
        return function () {
            return "jq_" + (++idBase) + "_node";
        };
    })(2000);
    var Node = (function () {
        function Node(name, id, isReadOnly, align, width, editType, options, isNumber, 
            //add by hzdqzy
            isSortable, sorttype) {
            if (isReadOnly === void 0) { isReadOnly = true; }
            if (align === void 0) { align = TextAlign.Right; }
            if (width === void 0) { width = 0; }
            if (editType === void 0) { editType = undefined; }
            if (options === void 0) { options = undefined; }
            if (isNumber === void 0) { isNumber = true; }
            //add by hzdqzy
            if (isSortable === void 0) { isSortable = false; }
            if (sorttype === void 0) { sorttype = undefined; }
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
                options: options,
                //add by hzdqzy
                isSortable: isSortable,
                sorttype: sorttype
            };
        }
        Node.create = function (opts) {
            var node = new Node(undefined, undefined);
            node.mOpts = $.extend({}, {
                isReadOnly: true,
                isNumber: true,
                align: TextAlign.Right,
                width: 0,
                isSortable: false
            }, opts);
            if (node.mOpts.id == undefined) {
                node.mOpts.id = JQTable.NodeId();
            }
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
        Node.prototype.hidden = function () {
            return this.mOpts.hidden;
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
            for (var i = 0; i < this.mChilds.length; ++i) {
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
                for (var i = 0; i < this.mChilds.length; ++i) {
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
            var children = [];
            children.push(this);
            for (var j = 0; j < depth; j++) {
                var len = children.length;
                for (var k = 0; k < len; k++) {
                    var currentChilds = children[0].childs();
                    children.splice(0, 1);
                    for (var i = 0; i < currentChilds.length; i++) {
                        children.push(currentChilds[i]);
                    }
                }
            }
            return children;
        };
        //add by hzdqzy-------------------------------------------------------
        Node.prototype.isSortable = function () {
            return this.mOpts.isSortable;
        };
        Node.prototype.sorttype = function () {
            return this.mOpts.sorttype;
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
            return this.mGrid.jqGrid("getCell", this.mGrid[0].p.data[this.mRow].id, this.mCol);
        };
        Cell.prototype.setVal = function (val) {
            return this.mGrid.jqGrid("setCell", this.mGrid[0].p.data[this.mRow].id, this.mCol, val, undefined, undefined, true);
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
            var oldVal = this.mDestCell.getVal();
            if (oldVal == "" && (newVal == undefined || newVal == null || newVal == "")) {
                return false;
            }
            else if (oldVal == newVal) {
                return false;
            }
            else {
                if (newVal != null && newVal != undefined) {
                    this.mDestCell.setVal(newVal);
                }
                else {
                    this.mDestCell.setVal("");
                }
                return true;
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
            this.mEditedRows = [];
            var nodes;
            this.mGridName = gridName;
            this.mTitle = titleNodes;
            if (!this.mTitle[this.mTitle.length - 1]) {
                this.mTitle.splice(this.mTitle.length - 1, 1);
            }
            for (var i = 0; i < this.mTitle.length; ++i) {
                //this.mTitle[i].mOpts.id = this.mGridName + this.mTitle[i].mOpts.id;
                nodes = this.mTitle[i].leaves();
                for (var j = 0; j < nodes.length; ++j) {
                    var colId = nodes[j].idChain();
                    this.mColModel.push({
                        name: colId,
                        index: colId,
                        sortable: nodes[j].isSortable(),
                        sorttype: nodes[j].sorttype(),
                        editable: !nodes[j].isReadOnly(),
                        edittype: nodes[j].editType(),
                        editoptions: nodes[j].editOptions(),
                        editrules: !nodes[j].isReadOnly() ? { number: nodes[j].isNumber() } : undefined,
                        hidden: nodes[j].hidden(),
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
        JQGridAssistant.prototype.getCheckedRowIds = function () {
            var ids = $("#" + this.mGridName + "").jqGrid('getGridParam', 'selarrrow');
            return ids;
        };
        JQGridAssistant.prototype.getAllData = function () {
            var grid = $("#" + this.mGridName + "");
            grid[0].p.data;
            var ids = grid.jqGrid('getDataIDs');
            var data = [];
            for (var i = 0; i < grid[0].p.data.length; ++i) {
                var row = [];
                row.push(grid[0].p.data[i].id);
                for (var j = 0; j < this.mColModel.length; ++j) {
                    var val = grid[0].p.data[i][this.mColModel[j].index];
                    row.push(undefined == val ? "" : val + "");
                }
                data.push(row);
            }
            return data;
        };
        JQGridAssistant.prototype.getDataCount = function () {
            var grid = $("#" + this.mGridName + "");
            return grid[0].p.data.length;
        };
        JQGridAssistant.prototype.getCurrentPageNumber = function () {
            var grid = $("#" + this.mGridName + "");
            var curPg = $(grid[0].p.pager + "_center input").val();
            return parseInt(curPg);
        };
        JQGridAssistant.prototype.navigateToPage = function (pg) {
            var grid = $("#" + this.mGridName + "");
            grid[0].p.page = pg;
            grid[0].grid.populate();
        };
        JQGridAssistant.prototype.showError = function (msg, width, height) {
            if (width === void 0) { width = 290; }
            if (height === void 0) { height = 90; }
            this.centerModal(width, height);
            setTimeout(function () {
                $.jgrid.info_dialog($.jgrid.errors.errcap, msg, $.jgrid.edit.bClose);
            }, 100);
        };
        JQGridAssistant.prototype.centerModal = function (width, height) {
            if (width === void 0) { width = 290; }
            if (height === void 0) { height = 90; }
            var grid = $("#gview_" + this.mGridName + "");
            $.jgrid.jqModal = {
                width: width,
                left: grid.offset().left + grid.width() / 2 - width / 2,
                top: grid.offset().top + grid.height() / 2 - height
            };
        };
        JQGridAssistant.prototype.testDepth = function () {
            var depth = 1;
            var tmp = 0;
            for (var i = 0; i < this.mTitle.length; ++i) {
                tmp = this.mTitle[i].depth();
                if (depth < tmp) {
                    depth = tmp;
                }
            }
            return depth;
        };
        JQGridAssistant.prototype.getColNames = function () {
            var leaves = [];
            var names = [];
            for (var i = 0; i < this.mTitle.length; ++i) {
                leaves = this.mTitle[i].leaves();
                for (var j = 0; j < leaves.length; ++j) {
                    names.push(leaves[j].name());
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
            for (var j = 0; j < this.mColModel.length; ++j) {
                if (j < rowData.length) {
                    row[this.mColModel[j].index] = rowData[j];
                }
            }
            grid.jqGrid("addRowData", rowId, row);
        };
        JQGridAssistant.prototype.id = function (col) {
            var colCount = 0;
            var leaves = [];
            for (var i = 0; i < this.mTitle.length; ++i) {
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
            var finished = false;
            this.completeList.push(function () {
                if (finished) {
                    return;
                }
                var nodes = [];
                var headers = [];
                var grid = $("#" + _this.mGridName + "");
                for (var i = 0; i < _this.mDepth - 1; ++i) {
                    nodes = _this.getLevelNodes(i);
                    headers = [];
                    for (var node = 0; node < nodes.length; ++node) {
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
                finished = true;
            });
        };
        JQGridAssistant.prototype.getLevelNodes = function (level) {
            var levelNodes = [];
            for (var i = 0; i < this.mTitle.length; i++) {
                levelNodes = levelNodes.concat(this.mTitle[i].children(level));
                for (var j = levelNodes.length - 1; j >= 0; --j) {
                    if (!levelNodes[j].hasChilds()) {
                        levelNodes.splice(j, 1);
                    }
                }
            }
            return levelNodes;
        };
        JQGridAssistant.prototype.getData = function (data) {
            var alldata = [];
            var colums = [];
            for (var i = 0; i < this.mTitle.length; ++i) {
                colums = colums.concat(this.mTitle[i].leaves());
            }
            var id = 1;
            for (var i = 0; i < data.length; ++i) {
                var rowdata = {};
                rowdata["id"] = "" + (id++);
                for (var j = 0; j < colums.length; ++j) {
                    rowdata[colums[j].idChain()] = data[i][j] == undefined ? "" : data[i][j];
                }
                alldata.push(rowdata);
            }
            return alldata;
        };
        JQGridAssistant.prototype.getDataWithId = function (data) {
            var alldata = [];
            var colums = [];
            for (var i = 0; i < this.mTitle.length; ++i) {
                colums = colums.concat(this.mTitle[i].leaves());
            }
            var col;
            for (var i = 0; i < data.length; ++i) {
                var rowdata = {};
                rowdata["id"] = data[i][0];
                for (var j = 0; j < colums.length; ++j) {
                    col = j + 1;
                    rowdata[colums[j].idChain()] = data[i][col] == undefined ? "" : data[i][col];
                }
                alldata.push(rowdata);
            }
            return alldata;
        };
        //			public bindSorter(col : number, sorter, grid) {
        //				this.mColModel[col]["sorttype"] = function(cell, obj) {
        //					return sorter.weight(cell, grid.jqGrid('getGridParam',
        //							'sortorder') == "asc");
        //				};
        //			},
        JQGridAssistant.prototype.mergeRow = function (iCol, iRowStart, ilen) {
            var _this = this;
            if (iRowStart != undefined) {
                this.completeList.push(function () {
                    var col = _this.id(iCol);
                    var grid = $("#" + _this.mGridName + "");
                    //得到显示到界面的id集合
                    var mya = grid.getDataIDs();
                    for (var i = iRowStart + 1; i < mya.length && i < iRowStart + ilen; i++) {
                        grid.setCell(mya[i], col, '', {
                            display: 'none'
                        });
                    }
                    $("#" + _this.mGridName + " #" + col + "" + mya[iRowStart] + "").attr("rowspan", ilen);
                    if (window['isie'] <= 9) {
                        //IE下overflow-y 无法正常工作
                        $("#" + _this.mGridName).parent().css("overflow-y", "hidden");
                    }
                    if (_this.mOnMergedRows != undefined) {
                        _this.mOnMergedRows(iCol, iRowStart, ilen);
                    }
                    return true;
                });
            }
            else {
                this.completeList.push(function () {
                    var col = _this.id(iCol);
                    var grid = $("#" + _this.mGridName + "");
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
                                _this.mergeRow(iCol, i - mergelen, mergelen);
                            }
                            mergelen = 1;
                            data = grid.getCell(mya[i], col);
                        }
                    }
                    if (mergelen > 1) {
                        _this.mergeRow(iCol, mya.length - mergelen, mergelen);
                    }
                    return true;
                });
            }
        };
        JQGridAssistant.prototype.getRowData = function (id) {
            var grid = $("#" + this.mGridName + "");
            var rdata = grid.getRowData(id);
            var row = [id];
            for (var j = 0; j < this.mColModel.length; ++j) {
                var val = rdata[this.mColModel[j].index];
                row.push(undefined == val ? "" : val + "");
            }
            return row;
        };
        JQGridAssistant.prototype.getRowsData = function (ids) {
            var rows = [];
            for (var i = 0; i < ids.length; ++i) {
                rows.push(this.getRowData(ids[i]));
            }
            return rows;
        };
        JQGridAssistant.prototype.getChangedData = function () {
            var grid = $("#" + this.mGridName + "");
            var data = [];
            for (var i = 0; i < this.mEditedRows.length; ++i) {
                var rdata = grid.getRowData(this.mEditedRows[i]);
                var row = [this.mEditedRows[i]];
                for (var j = 0; j < this.mColModel.length; ++j) {
                    var val = rdata[this.mColModel[j].index];
                    row.push(undefined == val ? "" : val + "");
                }
                for (var j = 1; j < row.length; ++j) {
                    if (row[j] != "") {
                        data.push(row);
                        break;
                    }
                }
            }
            return data;
        };
        JQGridAssistant.prototype.resetChangedData = function () {
            this.mEditedRows = [];
        };
        JQGridAssistant.prototype.setRowBgColor = function (row, r, g, b) {
            var _this = this;
            var grid = $("#" + this.mGridName);
            this.completeList.push(function () {
                $("#" + _this.mGridName + " #" + grid[0].p.data[row].id).css("background", "rgb(" + r + "," + g + "," + b + ")");
            });
        };
        JQGridAssistant.prototype.updateRowBgColor = function (rid, r, g, b) {
            if (r && g && b) {
                $("#" + this.mGridName + " #" + rid).css("background", "rgb(" + r + "," + g + "," + b + ")");
            }
            else {
                $("#" + this.mGridName + " #" + rid).removeCss("background");
            }
        };
        JQGridAssistant.prototype.setSelection = function (rid) {
            var grid = $("#" + this.mGridName);
            grid.jqGrid('setSelection', rid);
        };
        JQGridAssistant.prototype.getRid = function (row) {
            var grid = $("#" + this.mGridName);
            return grid[0].p.data[row].id;
        };
        JQGridAssistant.prototype.getSelection = function () {
            var grid = $("#" + this.mGridName);
            return grid.jqGrid('getGridParam', 'selarrrow');
        };
        JQGridAssistant.prototype.setCellValue = function (rid, col, val) {
            var grid = $("#" + this.mGridName);
            $("#" + this.mGridName).setCell(rid, col, val, undefined, undefined, true);
        };
        JQGridAssistant.prototype.getCellValue = function (rid, col) {
            return $("#" + this.mGridName).getCell(rid, col);
        };
        JQGridAssistant.prototype.domergeColumn = function (col, grid, mya, i) {
            var leftCell = grid.find("#" + mya[i] + " #" + this.id(col) + mya[i]);
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
        };
        JQGridAssistant.prototype.sliceMergeColumn = function (col, start) {
            var _this = this;
            if (start === void 0) { start = 0; }
            var grid = $("#" + this.mGridName + "");
            var mya = grid.getDataIDs();
            for (var i = start; i < mya.length; i++) {
                this.domergeColumn(col, grid, mya, i);
                if ((i - start) > 40) {
                    setTimeout(function () {
                        _this.sliceMergeColumn(col, i + 1);
                        _this.complete();
                    }, 0);
                    break;
                }
            }
        };
        JQGridAssistant.prototype.mergeColum = function (col, row, align) {
            var _this = this;
            if (row != undefined) {
                this.completeList.push(function () {
                    if (align == undefined) {
                        align = TextAlign.Center;
                    }
                    var grid = $("#" + _this.mGridName + "");
                    var mya = grid.getDataIDs();
                    var leftCell = grid.find("#" + mya[row] + " #" + _this.id(col) + mya[row]);
                    var rightCell = leftCell.next();
                    leftCell.css("text-align", "right");
                    rightCell.css("text-align", "left");
                    leftCell.css("border-right-width", "0px");
                    leftCell.css("padding-right", "0px");
                    leftCell.css("disabled", "disabled");
                    rightCell.css("padding-left", "0px");
                    rightCell.css("disabled", "disabled");
                    if (_this.mOnMergedColums != undefined) {
                        _this.mOnMergedColums(col, mya[row]);
                    }
                    _this.selectedList.push(function (newRow, newCol) {
                        if (newRow == mya[row]) {
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
                    return true;
                });
            }
            else {
                this.completeList.push(function () {
                    //var grid = $("#" + this.mGridName + "");
                    //var mya = grid.getDataIDs();
                    //for (var i = 0; i < mya.length; i++) {
                    //    var leftCell = grid.find("#" + mya[i] + " #" + this.id(col) + mya[i]);
                    //    var rightCell = leftCell.next();
                    //    if (leftCell.css("display") != "none" && rightCell.css("display") != "none") {
                    //        if (grid.getCell(mya[i], col) == grid.getCell(mya[i], col + 1)) {
                    //            var content = grid.getCell(mya[i], col);
                    //            grid.setCell(mya[i], col, content.substring(0, content.length / 2));
                    //            grid.setCell(mya[i], col + 1, content.substring(content.length / 2, content.length));
                    //            this.mergeColum(col, i);
                    //        }
                    //        else if (grid.getCell(mya[i], col + 1) == "") {
                    //            this.mergeColum(col, i);
                    //        }
                    //    }
                    //}
                    _this.sliceMergeColumn(col, 0);
                    return true;
                });
            }
        };
        JQGridAssistant.prototype.selected = function (row, col) {
            for (var i = 0; i < this.selectedList.length; i++) {
                this.selectedList[i](row, col);
            }
        };
        JQGridAssistant.prototype.invokeFormula = function (depth) {
            if (depth === void 0) { depth = 0; }
            var changed = false;
            for (var i = 0; i < this.mFormula.length; i++) {
                if (this.mFormula[i].update()) {
                    changed = true;
                }
            }
            if (changed && depth < 10) {
                this.invokeFormula(++depth);
            }
        };
        JQGridAssistant.prototype.addFormula = function (formula) {
            var _this = this;
            formula.setGrid($("#" + this.mGridName));
            if (this.mFormula.length == 0) {
                this.completeList.push(function () {
                    _this.invokeFormula();
                });
            }
            this.mFormula.push(formula);
        };
        JQGridAssistant.prototype.parseInt = function (px) {
            if (undefined != px && null != px) {
                if (px.lastIndexOf("px") > 0) {
                    return parseInt(px.replace("px", ""));
                }
            }
            return 0;
        };
        JQGridAssistant.prototype.mergeTitle = function (iColStart, iCount, hidden) {
            var _this = this;
            if (iCount === void 0) { iCount = 0; }
            if (hidden === void 0) { hidden = false; }
            if (iColStart != undefined) {
                this.completeList.push(function () {
                    var headerStart = $("#" + _this.mGridName + "_" + _this.id(iColStart));
                    var firstWidht = headerStart[0].offsetWidth; //this.parseInt(headerStart[0].style.width);
                    var iWidth = firstWidht;
                    var headerMerge = null;
                    var widthList = [iWidth];
                    for (var i = 1; i < iCount; i++) {
                        headerMerge = $("#" + _this.mGridName + "_" + _this.id(iColStart + i));
                        widthList.push(headerMerge[0].offsetWidth); //this.parseInt(headerMerge.css("width")));
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
                        var e = $("#gbox_" + _this.mGridName + " .jqg-first-row-header th:eq(" + (iColStart + i) + ")");
                        e.css("width", "0px");
                        e.css("padding", "0px");
                        e.css("margin", "0px");
                        e.css("border", "0px");
                        _this.disableDragCell(iColStart + i);
                    }
                    headerStart.css("width", iWidth + "px");
                    var len = headerStart[0].offsetWidth - iWidth;
                    headerStart.css("width", (iWidth - len) + "px");
                    var sizeTitle = $("#gbox_" + _this.mGridName + " .jqg-first-row-header th:eq(" + iColStart + ")");
                    sizeTitle.css("width", iWidth + "px");
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
            var rm = false;
            for (var i = 0; i < this.completeList.length; i++) {
                rm = this.completeList[i]();
                if (rm) {
                    this.completeList.splice(i, 1);
                    --i;
                }
            }
        };
        JQGridAssistant.prototype.disableCellEdit = function (cells) {
            var _this = this;
            this.mDisabledEditCells = cells;
            this.completeList.push(function () {
                for (var i = 0; i < cells.length; ++i) {
                    var id = $("#" + _this.mGridName + "")[0].p.data[cells[i].row()].id;
                    $("#" + _this.mGridName + " #" + id + " td:eq(" + cells[i].col() + ")").css("background", "RGB(183, 222, 232)");
                }
            });
        };
        JQGridAssistant.prototype.addData = function (total, page, records, dataWithoutId, dataWithId) {
            var data = dataWithId;
            if (!dataWithId) {
                data = [];
                for (var i = 0; i < dataWithoutId.length; ++i) {
                    data.push([i].concat(dataWithoutId[i]));
                }
            }
            var rows = [];
            for (var i = 0; i < data.length; ++i) {
                var cells = data[i].slice(1);
                rows.push({ id: data[i][0], cell: cells });
            }
            $("#" + this.mGridName)[0].addJSONData({
                total: total,
                page: page,
                records: records,
                rows: rows
            });
        };
        JQGridAssistant.prototype.enablePageEdit = function (rowNum, pagername, nopagerbutton) {
            var _this = this;
            var grid = $("#" + this.mGridName + "");
            var lastsel = "";
            var lastcell = "";
            var opt = {
                beforeEditCell: function (rowid, cellname, v, iRow, iCol) {
                    lastsel = iRow;
                    lastcell = iCol;
                    $("input").attr("disabled", true);
                    if (undefined != _this.mDisabledEditCells) {
                        var oldFun_1 = $.jgrid.createEl;
                        for (var i = 0; i < _this.mDisabledEditCells.length; ++i) {
                            if (_this.mDisabledEditCells[i].row() == (iRow - 1) && _this.mDisabledEditCells[i].col() == iCol) {
                                $.jgrid.createEl = function () {
                                    $.jgrid.createEl = oldFun_1;
                                };
                                break;
                            }
                        }
                    }
                },
                afterSaveCell: function () {
                    $("input").attr("disabled", false);
                    var ids = grid.jqGrid('getDataIDs');
                    if (_this.mEditedRows.indexOf(ids[lastsel - 1]) < 0) {
                        _this.mEditedRows.push(ids[lastsel - 1]);
                    }
                    lastsel = "";
                    _this.invokeFormula();
                },
                afterRestoreCell: function () {
                    $("input").attr("disabled", false);
                    lastsel = "";
                },
                afterEditCell: function (rowid, cellname, v, iRow, iCol) {
                    var isDisabled = false;
                    if (undefined != _this.mDisabledEditCells) {
                        for (var i = 0; i < _this.mDisabledEditCells.length; ++i) {
                            if (_this.mDisabledEditCells[i].row() == (iRow - 1) && _this.mDisabledEditCells[i].col() == iCol) {
                                isDisabled = true;
                                break;
                            }
                        }
                    }
                    if (isDisabled) {
                        $("#" + _this.mGridName).restoreCell(iRow, iCol);
                    }
                    $("#" + _this.mGridName + " input[type=text]").bind("keydown", function (e) {
                        if (e.keyCode === 13) {
                            var ids = grid.jqGrid('getDataIDs');
                            if (ids.length > iRow) {
                                setTimeout(function () {
                                    grid.jqGrid("editCell", iRow + 1, iCol, true);
                                }, 10);
                            }
                        }
                    });
                },
                beforeSaveCell: function (rowid, cellname, v, iRow, iCol) {
                    if (_this.mColModel[iCol].edittype != "select" &&
                        _this.mColModel[iCol].edittype != "text") {
                        var ret = parseFloat(v.replace(new RegExp(',', 'g'), ''));
                        if (isNaN(ret)) {
                            _this.centerModal();
                            return v;
                        }
                        else {
                            return ret;
                        }
                    }
                }
            };
            $('html').bind('click', function (e) {
                if (lastsel != "") {
                    if ($(e.target).closest("#" + _this.mGridName).length == 0) {
                        //and the click is outside of the grid //save the row being edited and unselect the row
                        //  $("#" + name).jqGrid('saveRow', lastsel);
                        if ($('input[type=search]').length != 0) {
                            return;
                        }
                        grid.jqGrid("saveCell", lastsel, lastcell);
                        //$("#" + name).resetSelection();
                        lastsel = "";
                    }
                }
            });
            if (!nopagerbutton && rowNum != undefined && pagername != undefined) {
                setTimeout(function () {
                    var addId = 1;
                    grid.jqGrid('navGrid', pagername, {
                        del: true, add: true, edit: false, refresh: false, search: false,
                        addfunc: function () {
                            if (lastsel != "") {
                                grid.jqGrid("saveCell", lastsel, lastcell);
                                lastsel = "";
                            }
                            var rid = "add" + (++addId);
                            var rdata = {};
                            rdata[rid] = rid;
                            grid.addRowData(rid, rdata, 'last');
                            var curPg = _this.getCurrentPageNumber();
                            var lastRowPg = parseInt("" + (_this.getDataCount() - 1) / rowNum) + 1;
                            if (curPg != lastRowPg) {
                                _this.navigateToPage(lastRowPg);
                            }
                            setTimeout(function () {
                                var ids = grid.jqGrid('getDataIDs');
                                //使用ids.length 为实验测试获得
                                grid.jqGrid("editCell", ids.length, 0, true);
                            }, 100);
                        },
                        delfunc: function (rowid) {
                            if (lastsel != "") {
                                grid.jqGrid("saveCell", lastsel, lastcell);
                                lastsel = "";
                            }
                            if (rowid.indexOf("add") < 0) {
                                _this.showError("数据已保存，无法删除");
                                return;
                            }
                            var ind = _this.mEditedRows.indexOf(rowid);
                            if (ind >= 0) {
                                _this.mEditedRows.splice(ind, 1);
                            }
                            var ids = grid.jqGrid('getDataIDs');
                            grid.jqGrid("delRowData", rowid);
                            grid.trigger("reloadGrid");
                            var dataCount = _this.getDataCount();
                            if (dataCount == 0) {
                                return;
                            }
                            var curPg = _this.getCurrentPageNumber();
                            var lastRowPg = parseInt("" + (dataCount - 1) / rowNum) + 1;
                            var curSelId = null;
                            if (curPg > lastRowPg) {
                                _this.navigateToPage(lastRowPg);
                                ids = grid.jqGrid('getDataIDs');
                                curSelId = ids.length - 1;
                            }
                            else {
                                for (var i = 0; i < ids.length; ++i) {
                                    if (ids[i] == rowid) {
                                        if (i == ids.length - 1) {
                                            curSelId = i - 1;
                                        }
                                        else if (i == 0) {
                                            curSelId = 0;
                                        }
                                        else {
                                            curSelId = i;
                                        }
                                        break;
                                    }
                                }
                            }
                            //curSelId 值为实验测试获得
                            grid.jqGrid("editCell", curSelId + 1, 0, false);
                        }
                    }, {}, {}, { multipleSearch: false });
                }, 0);
            }
            return opt;
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
            if (option.dataWithId) {
                option.data = this.getDataWithId(option.dataWithId);
            }
            else if (option.data) {
                option.data = this.getData(option.data);
            }
            if (option.assistPagedata) {
                var init = false;
                option.datatype = function (postdata) {
                    _this.resetChangedData();
                    if (!init) {
                        init = true;
                        _this.addData(option.assistTotal, 1, option.assistRecords, option.assistData, option.assistDataWithId);
                    }
                    else {
                        option.assistPagedata(postdata);
                    }
                };
            }
            if (option.assistEditable) {
                $.extend(option, this.enablePageEdit(option.rowNum, option.pager, option.nopagerbutton));
            }
            this.mOnMergedRows = option.onMergedRows;
            this.mOnMergedColums = option.onMergedColums;
            this.mOnMergedTitles = option.onMergedTitles;
            return option;
        };
        JQGridAssistant.prototype.create = function (opt) {
            var grid = $("#" + this.mGridName + "");
            var newOpt = this.decorate(opt);
            grid.jqGrid(newOpt);
            grid.each(function () {
                var _this = this;
                if (this.grid) {
                    this.grid.dragEnd = function () {
                        window['__jqassistDragEnd'].call(_this.grid, _this, _this.p);
                    };
                }
            });
        };
        JQGridAssistant.prototype.resizeHeight = function (maxHeight) {
            var grid = $("#" + this.mGridName + "");
            var hh = $("#gbox_" + this.mGridName + " .ui-jqgrid-hdiv").height();
            var bodyHeight = (maxHeight - hh) - (maxHeight - hh) % 28;
            var ids = grid.jqGrid('getDataIDs');
            if (ids.length * 28 > bodyHeight) {
                grid.setGridHeight(bodyHeight);
            }
            else {
                grid.setGridHeight(ids.length * 28);
                var bd = $("#gbox_" + this.mGridName + " .ui-jqgrid-bdiv")[0];
                if (bd && bd.clientHeight < bd.scrollHeight) {
                    grid.setGridHeight(ids.length * 28 + bd.scrollHeight - bd.clientHeight);
                }
            }
        };
        return JQGridAssistant;
    }());
    JQTable.JQGridAssistant = JQGridAssistant;
})(JQTable || (JQTable = {}));
