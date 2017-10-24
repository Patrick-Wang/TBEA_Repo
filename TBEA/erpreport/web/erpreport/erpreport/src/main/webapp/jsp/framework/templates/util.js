var Util;
(function (Util) {
    var Breadcrumb = (function () {
        function Breadcrumb() {
        }
        Breadcrumb.render = function (breads) {
            this.breads = breads;
            if (breads.length == 1) {
                $(".breadcrumb").append('<li class="active"><i class="fa fa-caret-right"></i>' +
                    breads[0].value + '</li>');
            }
            else {
                $(".breadcrumb").append('<li ><i class="fa fa-caret-right"></i>' +
                    '<a href="#" onclick="Util.Breadcrumb.onClickBread(0)">' + breads[0].value + '</a></li>');
                for (var i = 1; i < breads.length - 1; ++i) {
                    $(".breadcrumb").append('<li>' +
                        '<a href="#"  onclick="Util.Breadcrumb.onClickBread(' + i + ')">' + breads[i].value + '</a>' +
                        '</li>');
                }
                $(".breadcrumb").append('<li>' +
                    breads[breads.length - 1].value +
                    '</li>');
            }
        };
        Breadcrumb.onClickBread = function (i) {
            if (Breadcrumb.onClick) {
                Breadcrumb.onClick(this.breads[i]);
            }
        };
        Breadcrumb.setOnClickListener = function (onClick) {
            Breadcrumb.onClick = onClick;
        };
        return Breadcrumb;
    }());
    Util.Breadcrumb = Breadcrumb;
    var ErrorCode;
    (function (ErrorCode) {
        ErrorCode[ErrorCode["OK"] = 0] = "OK";
        ErrorCode[ErrorCode["DATABASE_EXCEPTION"] = 1] = "DATABASE_EXCEPTION";
        ErrorCode[ErrorCode["PREMARY_KEY_CONFILICT"] = 2] = "PREMARY_KEY_CONFILICT";
        ErrorCode[ErrorCode["PREMARY_KEY_NULL"] = 3] = "PREMARY_KEY_NULL";
        ErrorCode[ErrorCode["HAVE_NO_RIGHT"] = 4] = "HAVE_NO_RIGHT";
        ErrorCode[ErrorCode["PRICELIB_JCYCLJG_IMPORT_ERROR"] = 5] = "PRICELIB_JCYCLJG_IMPORT_ERROR";
    })(ErrorCode = Util.ErrorCode || (Util.ErrorCode = {}));
    function createTable(gridName, gridCtrl) {
        var nodes = [];
        for (var i = 0; i < gridCtrl.header.length; ++i) {
            var node = Util.parseHeader(gridCtrl.header[i]);
            if (null != node) {
                nodes.push(node);
            }
        }
        var tableAssist = new JQTable.JQGridAssistant(nodes, gridName);
        if (gridCtrl.mergeTitle != undefined) {
            tableAssist.mergeTitle();
        }
        if (gridCtrl.mergeRows != undefined) {
            for (var i = 0; i < gridCtrl.mergeRows.length; ++i) {
                if (gridCtrl.mergeRows[i].col != undefined) {
                    if (gridCtrl.mergeRows[i].rowStart != undefined && gridCtrl.mergeRows[i].rowLen != undefined) {
                        tableAssist.mergeRow(parseInt(gridCtrl.mergeRows[i].col), parseInt(gridCtrl.mergeRows[i].rowStart), parseInt(gridCtrl.mergeRows[i].rowLen));
                    }
                    else if (gridCtrl.mergeRows[i].rowStart != undefined &&
                        gridCtrl.mergeRows[i].step != undefined &&
                        gridCtrl.mergeRows[i].count != undefined) {
                        for (var j = 0; j < parseInt(gridCtrl.mergeRows[i].count); ++j) {
                            tableAssist.mergeRow(parseInt(gridCtrl.mergeRows[i].col), parseInt(gridCtrl.mergeRows[i].rowStart) + j * parseInt(gridCtrl.mergeRows[i].step), parseInt(gridCtrl.mergeRows[i].step));
                        }
                    }
                    else if (gridCtrl.mergeRows[i].rowStart != undefined) {
                        tableAssist.mergeRow(parseInt(gridCtrl.mergeRows[i].col), parseInt(gridCtrl.mergeRows[i].rowStart));
                    }
                    else {
                        tableAssist.mergeRow(parseInt(gridCtrl.mergeRows[i].col));
                    }
                }
            }
        }
        if (gridCtrl.mergeCols != undefined) {
            for (var i = 0; i < gridCtrl.mergeCols.length; ++i) {
                if (gridCtrl.mergeCols[i].col != undefined) {
                    tableAssist.mergeColum(parseInt(gridCtrl.mergeCols[i].col));
                }
            }
        }
        return tableAssist;
    }
    Util.createTable = createTable;
    function parseHeader(header) {
        var node = null;
        var readOnly = header.readOnly == "true";
        var sortable = header.sortable == "true";
        var align = JQTable.TextAlign.Center;
        if (header.align == 'left') {
            align = JQTable.TextAlign.Left;
        }
        else if (header.align == 'right') {
            align = JQTable.TextAlign.Right;
        }
        if ("date" == header.type) {
            node = JQTable.Node.create({
                name: header.name,
                align: align,
                isReadOnly: readOnly,
                isNumber: false,
                editType: "text",
                isSortable: sortable,
                options: {
                    dataInit: function (element) {
                        var fmt = "YYYY-MM-DD";
                        var seasonClass = "day";
                        $(element).jeDate({
                            skinCell: "jedatedeepgreen",
                            format: fmt,
                            isTime: false,
                            isinitVal: true,
                            isClear: false,
                            isToday: false
                        }).removeCss("height")
                            .removeCss("padding")
                            .removeCss("margin-top")
                            .removeClass(seasonClass)
                            .addClass(seasonClass)
                            .jePopup();
                        //$(element).datepicker({
                        //    dateFormat: 'yy-mm-dd',
                        //    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                        //    dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                        //    dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                        //    dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                        //    onSelect: function (dateText, inst) {
                        //    }
                        //});
                    }
                }
            });
        }
        else if ("text" == header.type) {
            node = JQTable.Node.create({
                name: header.name,
                align: align,
                isReadOnly: readOnly,
                isNumber: false,
                editType: "text",
                isSortable: sortable
            });
        }
        else if ("hidden" == header.type) {
            node = null; //JQTable.Node.create({name : header.name, align : align, isReadOnly:readOnly,isNumber:false,editType:"text", hidden:true});
        }
        else if ("select" == header.type) {
            node = JQTable.Node.create({
                name: header.name,
                align: align,
                isReadOnly: readOnly,
                isNumber: false,
                editType: "select",
                isSortable: sortable,
                options: {
                    value: header.options
                }
            });
        }
        else if ("searchSelect" == header.type) {
            node = JQTable.Node.create({
                name: header.name,
                align: align,
                isReadOnly: readOnly,
                isNumber: false,
                editType: "select",
                isSortable: sortable,
                options: {
                    value: header.options,
                    dataInit: function (element) {
                        $(element).select2({
                            language: "zh-CN"
                        });
                    }
                }
            });
        }
        else {
            node = JQTable.Node.create({
                name: header.name,
                align: align,
                width: header.width,
                isReadOnly: readOnly,
                isSortable: sortable
            });
        }
        if (header.sub != undefined) {
            for (var i = 0; i < header.sub.length; ++i) {
                if (header.sub[i].type != 'hidden') {
                    node.append(Util.parseHeader(header.sub[i]));
                }
            }
        }
        return node;
    }
    Util.parseHeader = parseHeader;
    function isIframe() {
        //  return !window.invalidate && window.parent.invalidate;
    }
    Util.isIframe = isIframe;
    function zeroDiv(sub, base) {
        return (sub - (sub % base)) / base;
    }
    Util.zeroDiv = zeroDiv;
    function roundDiv(sub, base) {
        var ret = (sub - (sub % base)) / base;
        ret += (sub % base > 0) ? 1 : 0;
        return ret;
    }
    Util.roundDiv = roundDiv;
})(Util || (Util = {}));
