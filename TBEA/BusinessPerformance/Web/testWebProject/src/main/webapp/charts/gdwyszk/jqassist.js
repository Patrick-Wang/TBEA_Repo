(function() {
	$.fn.jqGrid.setGroupHeaders = function(o) {
		o = $.extend({
			useColSpanStyle : false,
			depth : 2,
			groupHeaders : []
		}, o || {});
		return this
				.each(function() {
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
							role : "row",
							"aria-hidden" : "true"
						}).addClass("jqg-first-row-header").css("height",
								"auto");
					} else {
						$firstHeaderRow.empty();
					}
					var $firstRow, inColumnHeader = function(text,
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
						role : "rowheader"
					}).addClass("ui-jqgrid-labels jqg-third-row-header");
					for (i = 0; i < cml; i++) {
						th = ths[i].el;
						$th = $(th);
						cmi = colModel[i];
						// build the next cell for the first header row
						thStyle = {
							height : '0px',
							width : ths[i].width + 'px',
							display : (cmi.hidden ? 'none' : '')
						};
						$("<th>", {
							role : 'gridcell'
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
								role : "columnheader"
							}).addClass(
									"ui-state-default ui-th-column-header ui-th-"
											+ ts.p.direction).css({
								'height' : '22px',
								'border-top' : '0 none'
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
										role : "columnheader"
									}).addClass(
											"ui-state-default ui-th-column-header ui-th-"
													+ ts.p.direction).css({
										"display" : cmi.hidden ? 'none' : '',
										'border-top' : '0 none'
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
										function() {
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
										function() {
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
							function(e, nw, idx) {
								$firstRow.find('th').eq(idx).width(nw);
							});
				});
	}
})();

Class.define("Node", function(name, id, readonly, width) {
	this.mName = name;
	this.mId = id;
	this.mChilds = [];
	this.mParent = null;
	this.mReadOnly = readonly;
	this.mWidth = width;
}, {
	
	width : function(){
		if (this.mWidth == undefined) {
			return -1;
		}
		return this.mWidth;
	},
	
	isReadOnly : function(){
		return true == this.mReadOnly;
	},
	
	parent : function() {
		return this.mParent;
	},
	hasChilds : function() {
		return this.mChilds.length > 0;
	},

	childs : function() {
		return this.mChilds;
	},

	addNode : function(node) {
		this.mChilds.push(node);
		node.mParent = this;
		return this;
	},

	idChain : function() {
		if (this.mParent != null) {
			return this.mParent.idChain() + "_" + this.mId;
		}
		return this.mId;
	},

	name : function() {
		return this.mName;
	},

	id : function() {
		return this.mId;
	},

	leavesCount : function() {
		var count = 0;
		for ( var i in this.mChilds) {
			if (this.mChilds[i].hasChilds()) {
				count += this.mChilds[i].leavesCount();
			} else {
				++count;
			}
		}
		return count;
	},

	mostLeftLeaf : function() {
		var node = this;
		if (this.hasChilds()) {
			node = this.mChilds[0].mostLeftLeaf();
		}
		return node;
	},

	leaves : function() {
		var list = [];
		if (this.hasChilds()) {
			for ( var i in this.mChilds) {
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
	},

	depth : function() {
		var childDepth = 0;
		for (var i = 0; i < this.mChilds.length; i++) {
			if (this.mChilds[i].depth() > childDepth) {
				childDepth = this.mChilds[i].depth();
			}
		}
		return childDepth + 1;
	},

	children : function(depth) {
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
	}
});


Class.define("Cell", function(iRow, iCol) {
	this.mRow = iRow;
	this.mCol = iCol;
	this.mGrid = null;
},{
	setGrid : function(grid){
		this.mGrid = grid;
	},
	
	getVal : function(){
		return this.mGrid.jqGrid("getCell", this.mRow + 1, this.mCol);
	},
	
	setVal : function(val){
		return this.mGrid.jqGrid("setCell", this.mRow + 1, this.mCol, val);
	},
	
	row : function(){
		return this.mRow;
	},
	col : function(){
		return this.mCol;
	}
},{});


Class.define("Formula", function(destCell, srcCellarray, formula) {
	this.mDestCell = destCell;
	this.mSrcCellarray = srcCellarray;
	this.mFormula = formula;
},{
	//return changed
	calc : function(newRow, newCol){
		var oldVal = this.mDestCell.getVal();
		for (var i = 0; i < this.mSrcCellarray.length; i++) {
			if (this.mSrcCellarray[i].row() == newRow && this.mSrcCellarray[i].col() == newCol){
				var newVal = this.mFormula(this.destCell, this.mSrcCellarray);
				if (newVal != null && newVal != undefined && oldVal != newVal){
					this.mDestCell.setVal(newVal);
					return true;
				}
			}
		}
		return false;
	},
	
	destCell : function() {
		return this.mDestCell;
	},
	
	setGrid : function(grid){
		this.mDestCell.setGrid(grid);
		for (var i = 0; i < this.mSrcCellarray.length; i++) {
			this.mSrcCellarray[i].setGrid(grid);
		}
	},
	
	update : function(){
		var newVal = this.mFormula(this.destCell, this.mSrcCellarray);
		if (newVal != null && newVal != undefined){
			this.mDestCell.setVal(newVal);
		}
	}
},{});


Class.define("JQGridAssistant", function(titleNodes, gridName) {

	this.completeList = [];
	this.selectedList = [];
	this.mTitle = titleNodes;
	this.mGridName = gridName;
	this.mColModel = [];
	this.mResizeList = [];
	this.mFormula = [];
	var nodes = null;
	
	for ( var i in this.mTitle) {
		nodes = this.mTitle[i].leaves();

		for ( var j in nodes) {
			var colId = nodes[j].idChain();		
			this.mColModel.push({
				name : colId,
				index : colId,
				sortable : false,
				editable: !nodes[j].isReadOnly(),
				cellattr : function(rowId, tv, rawObject, cm, rdata) {
					return 'id=\'' + cm.name + rowId + "\'";
				}
			});
			if (nodes[j].width() > 0){
				this.mColModel[this.mColModel.length - 1].width = nodes[j].width();
			}
		}
	}
	this.mDepth = this.testDepth();
	
	if (this.mDepth > 1){
		this.group();
	}
},
		{

			testDepth : function() {
				var depth = 1;
				var tmp = 0;
				for ( var node in this.mTitle) {
					tmp = this.mTitle[node].depth();
					if (depth < tmp) {
						depth = tmp;
					}
				}
				return depth;
			},

			getColNames : function() {
				var leaves = [];
				var names = [];
				for ( var node in this.mTitle) {
					leaves = this.mTitle[node].leaves();
					for (leaf in leaves) {
						names.push(leaves[leaf].name());
					}
				}
				return names;
			},

			getColModel : function() {
				return this.mColModel;
			},

			id : function(col) {
				var colCount = 0;
				var leaves = [];
				for ( var i in this.mTitle) {
					leaves = this.mTitle[i].leaves();
					if (colCount < (col + 1)
							&& (col + 1) <= (colCount + leaves.length)) {
						return leaves[col - colCount].idChain();
					}
					colCount += leaves.length;
				}
				return "";
			},

			onResized : function(nw,idx) {
				for (var i = 0; i < this.mResizeList.length; i++) {
					this.mResizeList[i](nw, idx);
				}
			},
			
			group : function() {
				this.completeList.push(function(){
					var nodes = [];
					var headers = [];
					var grid = $("#" + this.mGridName + "");
					for (var i = 0; i < this.mDepth - 1; ++i) {
						nodes = this.getLevelNodes(i);
						headers = [];
						for ( var node in nodes) {
							if (nodes[node].leavesCount() > 0){
								headers.push({
									startColumnName : nodes[node].mostLeftLeaf()
											.idChain(),
									numberOfColumns : nodes[node].leavesCount(),
									titleText : nodes[node].name()
								});
							}
						}
	
						grid.jqGrid('setGroupHeaders', {
							depth : this.mDepth,
							useColSpanStyle : true,
							groupHeaders : headers
						});
					}
				}.bind(this));
			},

			getLevelNodes : function(level) {
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
			},

			getData : function(data) {
				var alldata = [];
				var colums = [];
				for ( var i in this.mTitle) {
					colums = colums.concat(this.mTitle[i].leaves());
				}

				for ( var i in data) {
					var rowdata = {};
					for ( var j in colums) {
						rowdata[colums[j].idChain()] = data[i][j];
					}
					alldata.push(rowdata);
				}
				return alldata;
			},

			bindSorter : function(col, sorter, grid) {
				this.mColModel[col]["sorttype"] = function(cell, obj) {
					return sorter.weight(cell, grid.jqGrid('getGridParam',
							'sortorder') == "asc");
				};
			},

			mergeRow : function(iCol, iRowStart, ilen) {
				this.completeList.push(function(){
					col = this.id(iCol);
					var grid = $("#" + this.mGridName + "");
					//得到显示到界面的id集合
					var mya = grid.getDataIDs();
					for (i = iRowStart + 1; i < mya.length && i < iRowStart + ilen; i++) {
						grid.setCell(mya[i], col, '', {
							display : 'none'
						});
					}
					$("#" + col + "" + mya[iRowStart] + "").attr("rowspan", ilen);
				}.bind(this));
			},
			
			mergeColum : function(row, col) {
				this.completeList.push(function(){
					++row;
					var leftCell = $("#" + this.mGridName + " #" + row + " #" + this.id(col) + row);
					var rightCell = leftCell.next();
					leftCell.css("border-right-width", "0px");
					leftCell.css("padding-right", "0px");
					leftCell.css("text-align", "right");
					leftCell.css("disabled", "disabled");
					rightCell.css("padding-left", "0px");
					rightCell.css("disabled", "disabled");
					
					this.selectedList.push(function(newRow, newCol){
						
						if (newRow == row){
							if (col == newCol || col + 1 == newCol){
								rightCell.addClass("edit-cell ui-state-highlight");
								leftCell.addClass("edit-cell ui-state-highlight");
							} else{
								leftCell.removeClass("edit-cell ui-state-highlight");
								rightCell.removeClass("edit-cell ui-state-highlight");
							}
						}
						else{
							leftCell.removeClass("edit-cell ui-state-highlight");
							rightCell.removeClass("edit-cell ui-state-highlight");
						}
					});
				}.bind(this));
			},
			
			selected : function(row, col){
				for (var i = 0; i < this.selectedList.length; i++) {
					this.selectedList[i](row, col);
				}
			},
			
			addFormula : function(formula){
				formula.setGrid($("#" + this.mGridName));
				if (this.mFormula.length == 0) {
					this.completeList.push(function(){
						for (var i = 0; i < this.mFormula.length; i++) {
							this.mFormula[i].update();
						}
					}.bind(this));
				}
				this.mFormula.push(formula);
			},
			
	
			cellChanged : function (iRow, iCol){

				for (var i = 0; i < this.mFormula.length; i++) {
					var changed = this.mFormula[i].calc(iRow - 1, iCol);
					if (changed) {
						this.cellChanged(this.mFormula[i].destCell().row() + 1, this.mFormula[i].destCell().col());
					}
				}
			},
			
			mergeTitle : function(iColStart, iCount) {
				this.completeList.push(function(){
					var headerStart = $("#" + this.mGridName + "_" + this.id(iColStart));
					
					var firstWidht = parseInt(headerStart.css("width").replace("px", ""));;
					var iWidht = firstWidht;
					
					var headerMerge = null;
					var widthList = [widthList];
					for (var i = 1; i < iCount; i++) {
							headerMerge =  $("#" + this.mGridName + "_" + this.id(iColStart + i));
							widthList.push(parseInt(headerMerge.css("width").replace("px", "")) + 
									parseInt(headerMerge.css("padding-left").replace("px", "")) + 
									parseInt(headerMerge.css("padding-right").replace("px", "")))
							iWidht += widthList[widthList.length - 1];
							$("#gbox_" + this.mGridName + " .jqg-first-row-header th:eq(" + (iColStart + i) + ")").css("width", "0px");
					}
					
					headerStart.css("width", iWidht + "px");
					var sizeTitle = $("#gbox_" + this.mGridName + " .jqg-first-row-header th:eq(" + iColStart + ")");
					sizeTitle.css("width", iWidht + 1 + "px");

					this.mResizeList.push(function(nw, iCol){
						if (iCol == iColStart) {
							var usedWidth = 0;
							var curFirstWidth = nw;
							nw += nw - firstWidht + iWidht;
							sizeTitle.css("width", nw + "px");
							for (var i = 0; i < widthList.Length; i++) {
								usedWidth += (nw - iWidht) * widthList[i] / iWidht;
								widthList[i] += (nw - iWidht) * widthList[i] / iWidht;
							}
							widthList[0] += nw - usedWidth;
							
							for (var i = 0; i < widthList.length; i++) {
								$("#" + this.mGridName + " .jqgfirstrow td:eq(" + i + iColStart + ")").css("width", widthList[i] + "px");
							}
							iWidht = nw;
							firstWidht = curFirstWidth;
						}
					}.bind(this));
					
				}.bind(this));
			},
			
			disableDragCell : function(iCol){
				this.completeList.push(function(){
					var spanls = $("#gview_" + this.mGridName + " .ui-jqgrid-resize");
					if (iCol == undefined) {
						for (var i = 0; i < spanls.length; i++) {
							spanls[i].style.visibility = "hidden";
						}
					}
					else{
						if (iCol < spanls.length) {
							spanls[iCol].style.visibility = "hidden";
						}
					}
					
				}.bind(this));
			},
			
			complete : function() {
				for (var i = 0; i < this.completeList.length; i++) {
					this.completeList[i]();
				}
			},
			
			decorate : function(option) {
				if (option.gridComplete != undefined) {
					var gridComplete = option.gridComplete;
					option.gridComplete = function() {
						gridComplete();
						this.complete();
					}.bind(this);
				}
				else {
					option.gridComplete = function() {
						this.complete();
					}.bind(this);
				}
				
				if (option.onSelectCell != undefined) {
					var onSelectCell = option.onSelectCell;
					option.onSelectCell = function(id, nm, tmp, iRow, iCol) {
						onSelectCell(id, nm, tmp, iRow, iCol);
						this.selected(iRow, iCol);
					}.bind(this);
				}
				else {
					option.onSelectCell = function(id, nm, tmp, iRow, iCol) {
						this.selected(iRow, iCol);
					}.bind(this);
				}
				
				if (option.beforeEditCell != undefined) {
					var beforeEditCell = option.beforeEditCell;
					option.beforeEditCell = function(id, nm, tmp, iRow, iCol) {
						beforeEditCell(id, nm, tmp, iRow, iCol);
						this.selected(iRow, iCol);
					}.bind(this);
				}
				else {
					option.beforeEditCell = function(id, nm, tmp, iRow, iCol) {
						this.selected(iRow, iCol);
					}.bind(this);
				}
				
				if (option.afterSaveCell != undefined) {
					var afterSaveCell = option.afterSaveCell;
					option.afterSaveCell = function(id, nm, tmp, iRow, iCol) {
						afterSaveCell(id, nm, tmp, iRow, iCol);
						this.cellChanged(iRow, iCol);
					}.bind(this);
				}
				else {
					option.afterSaveCell = function(id, nm, tmp, iRow, iCol) {
						this.cellChanged(iRow, iCol);
					}.bind(this);
				}

				if (option.resizeStop != undefined) {
					var resizeStop = option.resizeStop;
					option.resizeStop = function(nw, idx) {
						resizeStop(nw, idx);
						this.onResized(nw, idx);
					}.bind(this);
				}
				else {
					option.resizeStop = function(nw, idx) {
						this.onResized(nw, idx);
					}.bind(this);
				}	
				
				
				if (option.colNames == undefined) {
					option.colNames = this.getColNames();
				}

				if (option.colModel == undefined) {
					option.colModel = this.getColModel();
				}
				return option;
			}

		});

Class.define("JQGridAssistantFactory", null, {}, {
	createAcountsReceivableWarning : function() {
		return new JQGridAssistant([
				new Node("", "").addNode(new Node("合同编号", "htbh")).addNode(
						new Node("所属片区", "shpq")).addNode(
						new Node("单位名称", "dwmc")).addNode(
						new Node("工程项目名称", "gcxmmc")).addNode(
						new Node("客户行业性质", "khhyxz")).addNode(
						new Node("合同签订时间", "htqdsj")).addNode(
						new Node("产品金额", "cpje")).addNode(new Node("费用", "fy"))
						.addNode(new Node("总金额", "zje")),
				new Node("开票情况", "kpqk").addNode(new Node("开票时间", "kpsj"))
						.addNode(new Node("开票金额", "kpje")),
				new Node("逾期款进度款（万元）", "jdyqk").addNode(
						new Node("预付款", "yfk").addNode(new Node("金额", "je"))
								.addNode(new Node("逾期时间", "yqsj"))).addNode(
						new Node("进度款", "jdk").addNode(new Node("金额", "je"))
								.addNode(new Node("逾期时间", "yqsj"))).addNode(
						new Node("完工款", "wgk").addNode(new Node("金额", "je"))
								.addNode(new Node("逾期时间", "yqsj"))).addNode(
						new Node("合计", "hj")),
				new Node("逾期款应收账款（万元）", "yqyszk").addNode(
						new Node("预付款", "yfk").addNode(new Node("金额", "je"))
								.addNode(new Node("逾期时间", "yqsj"))).addNode(
						new Node("进度款", "jdk").addNode(new Node("金额", "je"))
								.addNode(new Node("逾期时间", "yqsj"))).addNode(
						new Node("完工款", "wgk").addNode(new Node("金额", "je"))
								.addNode(new Node("逾期时间", "yqsj"))).addNode(
						new Node("发货款", "fhk").addNode(new Node("金额", "je"))
								.addNode(new Node("逾期时间", "yqsj"))).addNode(
						new Node("到货款", "dhk").addNode(new Node("金额", "je"))
								.addNode(new Node("逾期时间", "yqsj"))).addNode(
						new Node("验收款", "ysk").addNode(new Node("金额", "je"))
								.addNode(new Node("逾期时间", "yqsj"))).addNode(
						new Node("投运款", "tyk").addNode(new Node("金额", "je"))
								.addNode(new Node("逾期时间", "yqsj"))).addNode(
						new Node("调试款", "tsk").addNode(new Node("金额", "je"))
								.addNode(new Node("逾期时间", "yqsj"))).addNode(
						new Node("质保金", "zbj").addNode(new Node("金额", "je"))
								.addNode(new Node("逾期时间", "yqsj"))).addNode(
						new Node("费用款", "fyk").addNode(new Node("金额", "je"))
								.addNode(new Node("逾期时间", "yqsj"))).addNode(
						new Node("合计", "hj")),
				new Node("未到期应收账款（万元）", "wdqyszk").addNode(
						new Node("预付款", "yfk")).addNode(new Node("发货款", "fhk"))
						.addNode(new Node("到货款", "dhk")).addNode(
								new Node("验收款", "ysk")).addNode(
								new Node("投运款", "tyk")).addNode(
								new Node("调试款", "tsk")).addNode(
								new Node("质保金", "zbj")).addNode(
								new Node("费用款", "fyk")).addNode(
								new Node("合计", "hj")),
				new Node("保理办理情况", "blblqk").addNode(new Node("保理时间", "blsj"))
						.addNode(new Node("保理款性质", "blkxz")).addNode(
								new Node("保理金额（万元）", "blje")).addNode(
								new Node("保理到期日", "blrq")).addNode(
								new Node("保理应收款实际到期日", "blyszksjdqi")).addNode(
								new Node("保理回款金额（万元）", "blhkje")).addNode(
								new Node("保理余额（万元）", "blje")) ]);
	},

	createAcountsReceivableCustomerStructureAnalysis : function(gridName) {
		return new JQGridAssistant([
				new Node("客户所属行业", "khsshy"),
				new Node("应收账款情况", "yszkqk")
					.addNode(new Node("金额", "je"))
					.addNode(new Node("户数", "hs"))
					.addNode(new Node("金额占比", "jezb")),
				new Node("欠款情况", "qkqk")
					.addNode(new Node("应收未收（包括到期质保金）", "ysws").addNode(
								new Node("逾期1个月内", "yq1gyn")).addNode(
								new Node("逾期1-3个月", "yq33gy")).addNode(
								new Node("逾期3-6个月", "yq36gy")).addNode(
								new Node("逾期6-12个月", "yq612gy")).addNode(
								new Node("逾期1年以上", "yq1nys"))).addNode(
						new Node("未到期款", "wdqk")).addNode(
						new Node("未到期质保金", "wdqzbj")) ], gridName);
	},

	createCompanyAcountsReceivable : function() {
		return new JQGridAssistant([ new Node("企业名称", "qymc"),
				new Node("应收总额", "ysze"), new Node("逾期款总额", "yqkze"),
				new Node("质保金总额", "zbjze"), new Node("逾期质保金", "yqzbj") ]);
	},

	createReceivableCustomerTrend : function() {
		return new JQGridAssistant([
				new Node("月份", "yf"),
				new Node("应收账款情况", "yszkqk").addNode(new Node("金额", "je"))
						.addNode(new Node("户数", "hs")),
				new Node("欠款情况", "qkqk").addNode(
						new Node("应收未收（包括到期质保金）", "ysws").addNode(
								new Node("逾期1个月内", "yq1gyn")).addNode(
								new Node("逾期1-3个月", "yq33gy")).addNode(
								new Node("逾期3-6个月", "yq36gy")).addNode(
								new Node("逾期6-12个月", "yq612gy")).addNode(
								new Node("逾期1年以上", "yq1nys"))).addNode(
						new Node("未到期款", "wdqk")).addNode(
						new Node("未到期质保金", "wdqzbj")) ]);
	},
	createTest : function() {
		return new JQGridAssistant([
				new Node("上月末账面应收余额", "sy")]);
	},
	createYSPZ1 : function(gridName) {
		return new JQGridAssistant([
		         	new Node("上月末累计销售收入", "symljxssr", true, 124),
		         	new Node("本月计划销售收入", "byjhxssr", true, 123),
		         	new Node("本月目标责任书应收指标", "bymbzrsyszb", true, 150),
		         	new Node("本月应收内控指标", "byysnkzb", false, 124),
		         	new Node("本月资金回笼计划", "byzjhljh", true, 124)], gridName);
	},
	
	createYSPZ2 : function(gridName) {
		return new JQGridAssistant([
		         	new Node("(加)本月销售收入新增应收金额", "byxssrxzysje", false, 123),
		         	new Node("(减)本月可降应收资金回笼金额", "bykjyszjhlje", false, 150),
		         	new Node("(加)本月归还保理增加应收金额", "byghblzjysje", false, 124),
		         	new Node("(减)本月新增保理回款冲减应收金额", "byxzblhkcjysje", false, 124),
		         	new Node("本月预计账面应收余额", "byyjzmysye", true),
		         	new Node("与目标责任书指标差距", "ymbzeszbcj", true),
		         	new Node("与内部控制指标差距", "ynbkzzbcj", true)], gridName);
	},
	createYSPZ3 : function(gridName) {
		return new JQGridAssistant([
		         	new Node("上月末账面应收余额", "symzbysye", false, 124)
		         	], gridName);
	},
	
	createYSPZ4 : function(gridName) {
		return new JQGridAssistant([
		         	new Node("(减)上月末已开票未发货产生应收金额", "symykfpfhscysje", false, 123),
		         	new Node("(加)上月末已发货未开票增加实际应收金额", "symyfhwkpzjsjsuje", false, 150),
		         	new Node("(加)上月末保理回款冲减应收金额", "symblhkzjysje", false, 124),
		         	new Node("(加)上月末预收冲减应收的金额", "symyscjysdje", false, 124),
		         	new Node("(加)其他冲减应收", "qtcjys"),
		         	new Node("上月实际应收余额", "sysjysye", true),
		         	new Node("(加)本月发货产品新增应收金额", "byfhcpxzysje"),
		         	new Node("(减)本月回款降低应收金额（发货后的款项）", "byhkjdysje"),
		         	new Node("本月预计实际应收余额", "byyjsjysye", true),
		         	new Node("与目标责任书指标差距", "ymbzeszbcj", true),
		         	new Node("与内部控制指标差距", "ynbkzzbcj", true)], gridName);
	},
	
	createHKJHJG : function(gridName) {
		return new JQGridAssistant([
		            new Node("款项", "kx", true),
		         	new Node("争取可回", "zqkh", false),
		         	new Node("确保可回", "qbkh", false),
		         	new Node("本月回笼", "byhl", true)], gridName);
	},
	
	createYSZKJGMX : function(gridName) {
		return new JQGridAssistant([
		            new Node("客户所属行业", "khsshy", true),
		         	new Node("应收账款情况", "zqkh")
		            	.addNode(new Node("金额", "je"))
		            	.addNode(new Node("占全部比例", "zqbbl", true)),
		         	new Node("欠款构成", "qkgc")
		            	.addNode(new Node("应收未收(包括到期质保金）", "ysws")
			            	.addNode(new Node("逾期1个月以内", "yq1yn"))
			            	.addNode(new Node("逾期1-3月", "yq13y"))
			            	.addNode(new Node("逾期3-6月", "yq36y"))
			            	.addNode(new Node("逾期6-12月", "yq612y"))
			            	.addNode(new Node("逾期1年以上", "yqynys")))
		            	.addNode(new Node("未到期款", "wdqk"))
		            	.addNode(new Node("未到期质保金", "wdqzbj"))
		            	.addNode(new Node("应收账款合计", "yszkhj", true))
		         	], gridName);
	},
	
	
	createDDHKWCQKTJ : function(gridName) {
		
		function createDDYFGroup(month, key) {
			return  new Node(month, key)
			            	.addNode(new Node("督导领导", "ddld"))
			            	.addNode(new Node("督导片区（部门）", "ddpq"))
			            	.addNode(new Node("回款计划", "hkjh"))
			            	.addNode(new Node("实际完成", "sjwc"))
			            	.addNode(new Node("完成率", "wcl"))
			            	.addNode(new Node("排名情况", "pmqk"))
			            	.addNode(new Node("考核结果", "khjg"));
			         	
		}
		
		var title = [new Node("序号", "xh", true)];
		
		for (var i = 1; i <= 12; i++) {
			title.push(createDDYFGroup(i + "月", i + "y"))
		}
		
		return new JQGridAssistant(title, gridName);
	},
	
	createYQKQSBHB : function(gridName) {
		return new JQGridAssistant([
		        		            new Node("月份", "yf", true),
		        		         	new Node("逾期1个月以内", "yq1yn"),
		        		         	new Node("逾期1-3月", "yq13y"),
		        		         	new Node("逾期3-6月", "yq36y"),
		        		         	new Node("逾期6-12月", "yq612y"),
		        		         	new Node("逾期1年以上", "yq1n")
		        		         	], gridName);
	},

	createSYHKJHZXQK : function(gridName) {
		return new JQGridAssistant([
			                            new Node("group", "g", true),
			        		            new Node("款项性质", "kxxz", true, 245),
			        		         	new Node("计划回款", "jhhk", true, 245),
			        		         	new Node("实际回款", "sjhk", true, 245),
			        		         	new Node("计划完成率", "jhwcl", true, 245)
		        		         	], gridName);
	},

	createWGDD : function(gridName) {
		function createMaterial(name, key) {
			return  new Node(name, key)
			            	.addNode(new Node("用量", "yl", true, 70))
			            	.addNode(new Node("单价", "dj", true, 70));
			         	
		}
		
		
		return new JQGridAssistant([
			                            new Node("完工时间", "wgsj", true, 120),
			        		            new Node("单位", "dw", true, 120),
			        		         	new Node("合同金额", "htje", true, 120),
			        		         	new Node("阶段", "jd", true, 120),
			        		         	new Node("毛利额", "mle", true, 120),
			        		         	new Node("毛利率", "mll", true, 120),
			        		         	createMaterial("硅钢", "gk"),
			        		         	createMaterial("电解铜", "djt"),
			        		         	createMaterial("变压器油", "byqy"),
			        		         	createMaterial("纸板", "zb"),
			        		         	createMaterial("钢材", "gc")
		        		         	], gridName);
	}

});