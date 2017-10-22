var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
/// <reference path="jqgrid/vector.ts" />
///<reference path="jqgrid/jqassist.ts"/>
String.prototype["getWidth"] = function (fontSize) {
    var span = document.getElementById("__getwidth");
    if (span == null) {
        span = document.createElement("span");
        span.id = "__getwidth";
        document.body.appendChild(span);
        span.style.visibility = "hidden";
        span.style.whiteSpace = "nowrap";
    }
    span.innerText = this;
    span.style.fontSize = fontSize + "px";
    return span.offsetWidth;
};
var Util;
(function (Util) {
    Util.FAMOUS_VIEW = 0;
    Util.MSG_INIT = 1;
    Util.MSG_UPDATE = 2;
    Util.MSG_REG = 3;
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
    })();
    Util.Breadcrumb = Breadcrumb;
    function getUIWidth(opts) {
        var max = 0;
        var tmp = 0;
        var fontSize = Util.isMSIE() ? 14 : 13;
        for (var i = 0; i < opts.length; ++i) {
            tmp = opts[i].getWidth(fontSize) + 25;
            if (max < tmp) {
                max = tmp;
            }
        }
        return max;
    }
    Util.getUIWidth = getUIWidth;
    (function (ErrorCode) {
        ErrorCode[ErrorCode["OK"] = 0] = "OK";
        ErrorCode[ErrorCode["DATABASE_EXCEPTION"] = 1] = "DATABASE_EXCEPTION";
        ErrorCode[ErrorCode["PREMARY_KEY_CONFILICT"] = 2] = "PREMARY_KEY_CONFILICT";
        ErrorCode[ErrorCode["PREMARY_KEY_NULL"] = 3] = "PREMARY_KEY_NULL";
        ErrorCode[ErrorCode["HAVE_NO_RIGHT"] = 4] = "HAVE_NO_RIGHT";
        ErrorCode[ErrorCode["PRICELIB_JCYCLJG_IMPORT_ERROR"] = 5] = "PRICELIB_JCYCLJG_IMPORT_ERROR";
    })(Util.ErrorCode || (Util.ErrorCode = {}));
    var ErrorCode = Util.ErrorCode;
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
                } });
        }
        else if ("text" == header.type) {
            node = JQTable.Node.create({
                name: header.name,
                align: align,
                isReadOnly: readOnly,
                isNumber: false,
                editType: "text",
                isSortable: sortable });
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
                isSortable: sortable });
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
    function indexOf(arr, val) {
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }
    Util.indexOf = indexOf;
    var AbstractFormatHandler = (function () {
        function AbstractFormatHandler(zbs, cols) {
            this.mZbs = zbs;
            this.mCols = cols;
        }
        AbstractFormatHandler.prototype.handle = function (zb, col, val) {
            return "";
        };
        AbstractFormatHandler.prototype.match = function (zb, col) {
            if (this.mZbs.length == 0 || indexOf(this.mZbs, zb) >= 0) {
                if (this.mCols.length == 0 || indexOf(this.mCols, col) >= 0) {
                    return true;
                }
            }
            return false;
        };
        AbstractFormatHandler.prototype.callNext = function (zb, col, val) {
            if (this.mNextHandler != undefined) {
                return this.mNextHandler.handle(zb, col, val);
            }
            return "--";
        };
        AbstractFormatHandler.prototype.next = function (handler) {
            this.mNextHandler = handler;
            return handler;
        };
        return AbstractFormatHandler;
    })();
    var FormatIntHandler = (function (_super) {
        __extends(FormatIntHandler, _super);
        function FormatIntHandler(zbs, cols) {
            if (zbs === void 0) { zbs = []; }
            if (cols === void 0) { cols = []; }
            _super.call(this, zbs, cols);
        }
        FormatIntHandler.prototype.handle = function (zb, col, val) {
            if (this.match(zb, col)) {
                return Util.formatInt(val);
            }
            else {
                return this.callNext(zb, col, val);
            }
        };
        return FormatIntHandler;
    })(AbstractFormatHandler);
    Util.FormatIntHandler = FormatIntHandler;
    var FormatCurrencyHandler = (function (_super) {
        __extends(FormatCurrencyHandler, _super);
        function FormatCurrencyHandler(zbs, cols) {
            if (zbs === void 0) { zbs = []; }
            if (cols === void 0) { cols = []; }
            _super.call(this, zbs, cols);
        }
        FormatCurrencyHandler.prototype.handle = function (zb, col, val) {
            if (this.match(zb, col)) {
                return Util.formatCurrency(val);
            }
            else {
                return this.callNext(zb, col, val);
            }
        };
        return FormatCurrencyHandler;
    })(AbstractFormatHandler);
    Util.FormatCurrencyHandler = FormatCurrencyHandler;
    var FormatPercentHandler = (function (_super) {
        __extends(FormatPercentHandler, _super);
        function FormatPercentHandler(zbs, cols) {
            if (zbs === void 0) { zbs = []; }
            if (cols === void 0) { cols = []; }
            _super.call(this, zbs, cols);
        }
        FormatPercentHandler.prototype.handle = function (zb, col, val) {
            if (this.match(zb, col)) {
                return Util.formatPercent(val);
            }
            else {
                return this.callNext(zb, col, val);
            }
        };
        return FormatPercentHandler;
    })(AbstractFormatHandler);
    Util.FormatPercentHandler = FormatPercentHandler;
    var FormatPercentSignalHandler = (function (_super) {
        __extends(FormatPercentSignalHandler, _super);
        function FormatPercentSignalHandler(zbs, cols) {
            if (zbs === void 0) { zbs = []; }
            if (cols === void 0) { cols = []; }
            _super.call(this, zbs, cols);
        }
        FormatPercentSignalHandler.prototype.handle = function (zb, col, val) {
            if (this.match(zb, col)) {
                return Util.formatPercentSignal(val);
            }
            else {
                return this.callNext(zb, col, val);
            }
        };
        return FormatPercentSignalHandler;
    })(AbstractFormatHandler);
    Util.FormatPercentSignalHandler = FormatPercentSignalHandler;
    var FormatFordotHandler = (function (_super) {
        __extends(FormatFordotHandler, _super);
        function FormatFordotHandler(dotCount, zbs, cols) {
            if (dotCount === void 0) { dotCount = 1; }
            if (zbs === void 0) { zbs = []; }
            if (cols === void 0) { cols = []; }
            _super.call(this, zbs, cols);
            this.mDotCount = dotCount;
        }
        FormatFordotHandler.prototype.handle = function (zb, col, val) {
            if (this.match(zb, col)) {
                return Util.formatFordot(val, this.mDotCount);
            }
            else {
                return this.callNext(zb, col, val);
            }
        };
        return FormatFordotHandler;
    })(AbstractFormatHandler);
    Util.FormatFordotHandler = FormatFordotHandler;
    var ZBStatus = (function () {
        function ZBStatus() {
        }
        ZBStatus.NONE = "NONE";
        ZBStatus.APPROVED = "APPROVED";
        ZBStatus.SUBMITTED = "SUBMITTED";
        ZBStatus.SAVED = "SAVED";
        ZBStatus.APPROVED_2 = "APPROVED_2";
        ZBStatus.SUBMITTED_2 = "SUBMITTED_2";
        ZBStatus.INTER_APPROVED_1 = "内部一级已审核";
        ZBStatus.INTER_APPROVED_2 = "内部二级已审核";
        return ZBStatus;
    })();
    Util.ZBStatus = ZBStatus;
    (function (IndiStatus) {
        IndiStatus[IndiStatus["NONE"] = 0] = "NONE";
        IndiStatus[IndiStatus["APPROVED"] = 1] = "APPROVED";
        IndiStatus[IndiStatus["SUBMITTED"] = 2] = "SUBMITTED";
        IndiStatus[IndiStatus["SAVED"] = 3] = "SAVED";
        IndiStatus[IndiStatus["APPROVED_2"] = 4] = "APPROVED_2";
        IndiStatus[IndiStatus["SUBMITTED_2"] = 5] = "SUBMITTED_2";
        IndiStatus[IndiStatus["INTER_APPROVED_1"] = 6] = "INTER_APPROVED_1";
        IndiStatus[IndiStatus["INTER_APPROVED_2"] = 7] = "INTER_APPROVED_2";
        IndiStatus[IndiStatus["INTER_APPROVED_3"] = 8] = "INTER_APPROVED_3"; //("内部三级已审核");
    })(Util.IndiStatus || (Util.IndiStatus = {}));
    var IndiStatus = Util.IndiStatus;
    (function (ZBType) {
        ZBType[ZBType["QNJH"] = 0] = "QNJH";
        ZBType[ZBType["YDJDMJH"] = 1] = "YDJDMJH";
        ZBType[ZBType["BY20YJ"] = 2] = "BY20YJ";
        ZBType[ZBType["BY28YJ"] = 3] = "BY28YJ";
        ZBType[ZBType["BYSJ"] = 4] = "BYSJ";
    })(Util.ZBType || (Util.ZBType = {}));
    var ZBType = Util.ZBType;
    (function (CompanyType) {
        CompanyType[CompanyType["BB"] = 0] = "BB";
        CompanyType[CompanyType["BBXSGS"] = 1] = "BBXSGS";
        CompanyType[CompanyType["BJFGS"] = 2] = "BJFGS";
        CompanyType[CompanyType["BYQC"] = 3] = "BYQC";
        CompanyType[CompanyType["DBGS"] = 4] = "DBGS";
        CompanyType[CompanyType["DJBGS"] = 5] = "DJBGS";
        CompanyType[CompanyType["DJGEGS"] = 6] = "DJGEGS";
        CompanyType[CompanyType["DJGGS"] = 7] = "DJGGS";
        CompanyType[CompanyType["DJGYFGS"] = 8] = "DJGYFGS";
        CompanyType[CompanyType["DKHEB"] = 9] = "DKHEB";
        CompanyType[CompanyType["DKHYB"] = 10] = "DKHYB";
        CompanyType[CompanyType["DLAZB"] = 11] = "DLAZB";
        CompanyType[CompanyType["DLBYGS"] = 12] = "DLBYGS";
        CompanyType[CompanyType["DLC"] = 13] = "DLC";
        CompanyType[CompanyType["DLCLFGS"] = 14] = "DLCLFGS";
        CompanyType[CompanyType["DLDLXMGS"] = 15] = "DLDLXMGS";
        CompanyType[CompanyType["DLGCGS"] = 16] = "DLGCGS";
        CompanyType[CompanyType["DLGCSYB"] = 17] = "DLGCSYB";
        CompanyType[CompanyType["DLGS"] = 18] = "DLGS";
        CompanyType[CompanyType["DLJSGCGS"] = 19] = "DLJSGCGS";
        CompanyType[CompanyType["DLJXSYB"] = 20] = "DLJXSYB";
        CompanyType[CompanyType["DLKCSJGS"] = 21] = "DLKCSJGS";
        CompanyType[CompanyType["DLZDHGS"] = 22] = "DLZDHGS";
        CompanyType[CompanyType["DQFGS"] = 23] = "DQFGS";
        CompanyType[CompanyType["DQZJFGS"] = 24] = "DQZJFGS";
        CompanyType[CompanyType["DYCJGJWLMYYXGS"] = 25] = "DYCJGJWLMYYXGS";
        CompanyType[CompanyType["FDGCSYB"] = 26] = "FDGCSYB";
        CompanyType[CompanyType["FLSWB"] = 27] = "FLSWB";
        CompanyType[CompanyType["FNSYB"] = 28] = "FNSYB";
        CompanyType[CompanyType["FWGS"] = 29] = "FWGS";
        CompanyType[CompanyType["GCFWGS"] = 30] = "GCFWGS";
        CompanyType[CompanyType["GCGS_DL"] = 31] = "GCGS_DL";
        CompanyType[CompanyType["GCGS_GCL"] = 32] = "GCGS_GCL";
        CompanyType[CompanyType["GCGS_ND"] = 33] = "GCGS_ND";
        CompanyType[CompanyType["GCL"] = 34] = "GCL";
        CompanyType[CompanyType["GCLYPGS"] = 35] = "GCLYPGS";
        CompanyType[CompanyType["GFGS"] = 36] = "GFGS";
        CompanyType[CompanyType["GJB"] = 37] = "GJB";
        CompanyType[CompanyType["GJCTGCGS"] = 38] = "GJCTGCGS";
        CompanyType[CompanyType["GJCTZGS"] = 39] = "GJCTZGS";
        CompanyType[CompanyType["GJGCGS_SYB"] = 40] = "GJGCGS_SYB";
        CompanyType[CompanyType["GJGCGS_GFGS"] = 41] = "GJGCGS_GFGS";
        CompanyType[CompanyType["GJGCGS_XL"] = 42] = "GJGCGS_XL";
        CompanyType[CompanyType["GJMYB"] = 43] = "GJMYB";
        CompanyType[CompanyType["GJMYCTGS"] = 44] = "GJMYCTGS";
        CompanyType[CompanyType["GJSYB"] = 45] = "GJSYB";
        CompanyType[CompanyType["GJYWB"] = 46] = "GJYWB";
        CompanyType[CompanyType["GMB"] = 47] = "GMB";
        CompanyType[CompanyType["GNCTB"] = 48] = "GNCTB";
        CompanyType[CompanyType["GNFGS"] = 49] = "GNFGS";
        CompanyType[CompanyType["GNGCGS"] = 50] = "GNGCGS";
        CompanyType[CompanyType["GNGCJXGS"] = 51] = "GNGCJXGS";
        CompanyType[CompanyType["GPSYB"] = 52] = "GPSYB";
        CompanyType[CompanyType["HBDQFGS"] = 53] = "HBDQFGS";
        CompanyType[CompanyType["HBGS"] = 54] = "HBGS";
        CompanyType[CompanyType["HBYYGS"] = 55] = "HBYYGS";
        CompanyType[CompanyType["HJBLGS"] = 56] = "HJBLGS";
        CompanyType[CompanyType["HNGCGS"] = 57] = "HNGCGS";
        CompanyType[CompanyType["HNGJWLGS"] = 58] = "HNGJWLGS";
        CompanyType[CompanyType["HNYLGS"] = 59] = "HNYLGS";
        CompanyType[CompanyType["HNYNDQGS"] = 60] = "HNYNDQGS";
        CompanyType[CompanyType["HNZNDQGS"] = 61] = "HNZNDQGS";
        CompanyType[CompanyType["HXGS"] = 62] = "HXGS";
        CompanyType[CompanyType["HXTG"] = 63] = "HXTG";
        CompanyType[CompanyType["JCK"] = 64] = "JCK";
        CompanyType[CompanyType["JCKGS_JYDW"] = 65] = "JCKGS_JYDW";
        CompanyType[CompanyType["JCKGS_SYB"] = 66] = "JCKGS_SYB";
        CompanyType[CompanyType["JJWL"] = 67] = "JJWL";
        CompanyType[CompanyType["JNDXXMGS"] = 68] = "JNDXXMGS";
        CompanyType[CompanyType["JNDXXMGS_LL"] = 69] = "JNDXXMGS_LL";
        CompanyType[CompanyType["JNDXXMGS_XL"] = 70] = "JNDXXMGS_XL";
        CompanyType[CompanyType["JSJGYTSBLGS"] = 71] = "JSJGYTSBLGS";
        CompanyType[CompanyType["JT"] = 72] = "JT";
        CompanyType[CompanyType["JWYHGS"] = 73] = "JWYHGS";
        CompanyType[CompanyType["JYGS"] = 74] = "JYGS";
        CompanyType[CompanyType["KGYJS"] = 75] = "KGYJS";
        CompanyType[CompanyType["KJHGQ"] = 76] = "KJHGQ";
        CompanyType[CompanyType["KYDLXMGS"] = 77] = "KYDLXMGS";
        CompanyType[CompanyType["KYGS"] = 78] = "KYGS";
        CompanyType[CompanyType["LBGS"] = 79] = "LBGS";
        CompanyType[CompanyType["LHB"] = 80] = "LHB";
        CompanyType[CompanyType["LLGS"] = 81] = "LLGS";
        CompanyType[CompanyType["LYWYGS"] = 82] = "LYWYGS";
        CompanyType[CompanyType["MYGS_TCNY"] = 83] = "MYGS_TCNY";
        CompanyType[CompanyType["CJRDC"] = 84] = "CJRDC";
        CompanyType[CompanyType["RLGS"] = 85] = "RLGS";
        CompanyType[CompanyType["NDGS"] = 86] = "NDGS";
        CompanyType[CompanyType["NJZNDQGS"] = 87] = "NJZNDQGS";
        CompanyType[CompanyType["NLTK"] = 88] = "NLTK";
        CompanyType[CompanyType["NYCY"] = 89] = "NYCY";
        CompanyType[CompanyType["NYSYB"] = 90] = "NYSYB";
        CompanyType[CompanyType["RDGS"] = 91] = "RDGS";
        CompanyType[CompanyType["SBDCY"] = 92] = "SBDCY";
        CompanyType[CompanyType["SBDCYJT"] = 93] = "SBDCYJT";
        CompanyType[CompanyType["SBDLKBSJGS"] = 94] = "SBDLKBSJGS";
        CompanyType[CompanyType["SBDLYDHGS"] = 95] = "SBDLYDHGS";
        CompanyType[CompanyType["SBGJMYCTGS"] = 96] = "SBGJMYCTGS";
        CompanyType[CompanyType["SBGS"] = 97] = "SBGS";
        CompanyType[CompanyType["SBKJHGQ"] = 98] = "SBKJHGQ";
        CompanyType[CompanyType["SBSKGS"] = 99] = "SBSKGS";
        CompanyType[CompanyType["SBWYGS"] = 100] = "SBWYGS";
        CompanyType[CompanyType["SBXDWLGS"] = 101] = "SBXDWLGS";
        CompanyType[CompanyType["SBXNY"] = 102] = "SBXNY";
        CompanyType[CompanyType["SBXSYX"] = 103] = "SBXSYX";
        CompanyType[CompanyType["SBZTFGS"] = 104] = "SBZTFGS";
        CompanyType[CompanyType["SBZXGS"] = 105] = "SBZXGS";
        CompanyType[CompanyType["SDDLGCGS"] = 106] = "SDDLGCGS";
        CompanyType[CompanyType["SDFGS"] = 107] = "SDFGS";
        CompanyType[CompanyType["SKGS"] = 108] = "SKGS";
        CompanyType[CompanyType["TBDG_YD_NYYXGS"] = 109] = "TBDG_YD_NYYXGS";
        CompanyType[CompanyType["TBGS"] = 110] = "TBGS";
        CompanyType[CompanyType["TCNY"] = 111] = "TCNY";
        CompanyType[CompanyType["TLGS"] = 112] = "TLGS";
        CompanyType[CompanyType["TLXMGS"] = 113] = "TLXMGS";
        CompanyType[CompanyType["TYDXXMGS"] = 114] = "TYDXXMGS";
        CompanyType[CompanyType["TYDXXMGS_LL"] = 115] = "TYDXXMGS_LL";
        CompanyType[CompanyType["TYDXXMGS_XL"] = 116] = "TYDXXMGS_XL";
        CompanyType[CompanyType["TYXMGS"] = 117] = "TYXMGS";
        CompanyType[CompanyType["TZDGXJDGCLYXGS"] = 118] = "TZDGXJDGCLYXGS";
        CompanyType[CompanyType["TZDLXMGS"] = 119] = "TZDLXMGS";
        CompanyType[CompanyType["TZDLYFXMGS"] = 120] = "TZDLYFXMGS";
        CompanyType[CompanyType["WLGS"] = 121] = "WLGS";
        CompanyType[CompanyType["WYDXDLC"] = 122] = "WYDXDLC";
        CompanyType[CompanyType["WYGS"] = 123] = "WYGS";
        CompanyType[CompanyType["XADLSJY"] = 124] = "XADLSJY";
        CompanyType[CompanyType["XBC"] = 125] = "XBC";
        CompanyType[CompanyType["XBCBB"] = 126] = "XBCBB";
        CompanyType[CompanyType["XBGJCTGCGS"] = 127] = "XBGJCTGCGS";
        CompanyType[CompanyType["XBGNGCJXGS"] = 128] = "XBGNGCJXGS";
        CompanyType[CompanyType["XBGS"] = 129] = "XBGS";
        CompanyType[CompanyType["XBXBGS"] = 130] = "XBXBGS";
        CompanyType[CompanyType["XBZTGS"] = 131] = "XBZTGS";
        CompanyType[CompanyType["XDWLGS"] = 132] = "XDWLGS";
        CompanyType[CompanyType["XJFGS"] = 133] = "XJFGS";
        CompanyType[CompanyType["XJNY"] = 134] = "XJNY";
        CompanyType[CompanyType["XJXTGJWLMYGS"] = 135] = "XJXTGJWLMYGS";
        CompanyType[CompanyType["XJZXGS"] = 136] = "XJZXGS";
        CompanyType[CompanyType["XKGS"] = 137] = "XKGS";
        CompanyType[CompanyType["XLC"] = 138] = "XLC";
        CompanyType[CompanyType["XLGGS"] = 139] = "XLGGS";
        CompanyType[CompanyType["XNDQGCGS"] = 140] = "XNDQGCGS";
        CompanyType[CompanyType["XNDQGS"] = 141] = "XNDQGS";
        CompanyType[CompanyType["XNY"] = 142] = "XNY";
        CompanyType[CompanyType["XNYCY"] = 143] = "XNYCY";
        CompanyType[CompanyType["XNYGS"] = 144] = "XNYGS";
        CompanyType[CompanyType["XNYSYB"] = 145] = "XNYSYB";
        CompanyType[CompanyType["XNYYJY"] = 146] = "XNYYJY";
        CompanyType[CompanyType["XSGS"] = 147] = "XSGS";
        CompanyType[CompanyType["XSZGS"] = 148] = "XSZGS";
        CompanyType[CompanyType["XSZX"] = 149] = "XSZX";
        CompanyType[CompanyType["XTBLGS"] = 150] = "XTBLGS";
        CompanyType[CompanyType["XTDLXMGS"] = 151] = "XTDLXMGS";
        CompanyType[CompanyType["XTGS"] = 152] = "XTGS";
        CompanyType[CompanyType["XTJCSYB"] = 153] = "XTJCSYB";
        CompanyType[CompanyType["XTNYGS"] = 154] = "XTNYGS";
        CompanyType[CompanyType["XTWLGS"] = 155] = "XTWLGS";
        CompanyType[CompanyType["ZBDC"] = 156] = "ZBDC";
        CompanyType[CompanyType["ZHGS"] = 157] = "ZHGS";
        CompanyType[CompanyType["ZHGS_SYB"] = 158] = "ZHGS_SYB";
        CompanyType[CompanyType["YJJSGCGS"] = 159] = "YJJSGCGS";
        CompanyType[CompanyType["ZPDCJ"] = 160] = "ZPDCJ";
        CompanyType[CompanyType["ZTWLGS"] = 161] = "ZTWLGS";
        CompanyType[CompanyType["YXGS"] = 162] = "YXGS";
        CompanyType[CompanyType["ZHGS_MYGS"] = 163] = "ZHGS_MYGS";
        CompanyType[CompanyType["ZJWL"] = 164] = "ZJWL";
        CompanyType[CompanyType["ZJZTGJWLYXGS"] = 165] = "ZJZTGJWLYXGS";
        CompanyType[CompanyType["ZTFGS"] = 166] = "ZTFGS";
        CompanyType[CompanyType["ZTGS"] = 167] = "ZTGS";
        CompanyType[CompanyType["ZXGS"] = 168] = "ZXGS";
        CompanyType[CompanyType["ZYGS"] = 169] = "ZYGS";
        //三期新增项目公司
        CompanyType[CompanyType["SBDQSBGS"] = 170] = "SBDQSBGS";
        CompanyType[CompanyType["CTGYGS"] = 171] = "CTGYGS";
        CompanyType[CompanyType["DXDLJCZXGS"] = 172] = "DXDLJCZXGS";
        //非正式公司
        CompanyType[CompanyType["DBSBDCYJT"] = 173] = "DBSBDCYJT";
        CompanyType[CompanyType["NFSBDCYJT"] = 174] = "NFSBDCYJT";
        CompanyType[CompanyType["GCCY"] = 175] = "GCCY";
        CompanyType[CompanyType["XBCZT"] = 176] = "XBCZT";
        CompanyType[CompanyType["BYQCY"] = 177] = "BYQCY";
        CompanyType[CompanyType["XLCY"] = 178] = "XLCY";
        CompanyType[CompanyType["UNKNOWN"] = 179] = "UNKNOWN";
        CompanyType[CompanyType["RSGS"] = 180] = "RSGS";
        CompanyType[CompanyType["PDCY"] = 181] = "PDCY";
        CompanyType[CompanyType["ALL"] = 1000] = "ALL";
    })(Util.CompanyType || (Util.CompanyType = {}));
    var CompanyType = Util.CompanyType;
    $.ajaxSetup({ cache: false });
    //    export function parse(jsstr: string): any {
    //        var jsonValue;
    //        eval('jsonValue = ' + jsstr); 
    //        return jsonValue;
    //    }
    //    
    //    export function stringify(json : any) : string{
    //        var s = '';
    //
    //        if (typeof (json) == "string") {
    //            s = '"' + json.replace(new RegExp('\\\\',"g"), '\\\\\\\\').replace(new RegExp('"', "g"), '\\"') + '"';
    //        } else if (typeof (json) == "object") {
    //            if (json instanceof Array) {
    //                for (var k in json) {
    //                    s += "," + stringify(json[k]);
    //                }
    //                s = '[' + s.substring(1) + ']';
    //            } else {
    //                for (var k in json) {
    //                    s += ',"' + k + '":' + stringify(json[k]);
    //                }
    //                s = '{' + s.substring(1) + '}';
    //            }
    //        } else {
    //            s += json;
    //        }
    //        return s;
    //    }
    var Promise = (function () {
        function Promise() {
            this.mSuccessList = [];
            this.mFailedList = [];
        }
        Promise.prototype.succeed = function (data) {
            for (var i = 0; i < this.mSuccessList.length; ++i) {
                this.mSuccessList[i](data);
            }
        };
        Promise.prototype.failed = function (data) {
            for (var i = 0; i < this.mFailedList.length; ++i) {
                this.mFailedList[i](data);
            }
        };
        Promise.prototype.then = function (success, failed) {
            if (null != success && undefined != success) {
                this.mSuccessList.push(success);
            }
            if (failed != null && failed != undefined) {
                this.mFailedList.push(failed);
            }
            return this;
        };
        return Promise;
    })();
    Util.Promise = Promise;
    function isIframe() {
        return !window.invalidate && window.parent.invalidate;
    }
    Util.isIframe = isIframe;
    function loadCssFile(f) {
        if (isIframe()) {
            var head = document.getElementsByTagName('HEAD').item(0);
            var style = document.createElement('link');
            style.href = f;
            style.rel = 'stylesheet';
            style.type = 'text/css';
            head.appendChild(style);
        }
    }
    Util.loadCssFile = loadCssFile;
    var Ajax = (function () {
        function Ajax(baseUrl, useCache) {
            if (useCache === void 0) { useCache = true; }
            this.mCache = {};
            this.mBaseUrl = baseUrl;
            this.mUseCache = useCache;
        }
        Ajax.prototype.baseUrl = function () {
            return this.mBaseUrl;
        };
        Ajax.toUrlParam = function (option) {
            var keys = [];
            for (var key in option) {
                if ("" !== option[key] && null != option[key] && undefined != option[key]) {
                    keys.push(key + "=" + option[key]);
                }
            }
            keys.sort();
            return keys.join("&");
        };
        Ajax.prototype.setCache = function (option, data) {
            if (this.mUseCache && undefined != option) {
                this.mCache[Ajax.toUrlParam(option)] = data;
            }
        };
        Ajax.prototype.clean = function () {
            this.mCache = {};
        };
        Ajax.prototype.getCache = function (option) {
            if (undefined == option) {
                return undefined;
            }
            return this.mCache[Ajax.toUrlParam(option)];
        };
        Ajax.parentInvalidate = function (redirect) {
            if (isIframe()) {
                window.parent.invalidate(redirect);
                return true;
            }
            return false;
        };
        Ajax.prototype.validate = function (data) {
            if (data.error == "invalidate session") {
                if (!Ajax.parentInvalidate(data.redirect)) {
                    window.location.href = data.redirect;
                }
                return false;
            }
            return true;
        };
        Ajax.prototype.post = function (option) {
            var _this = this;
            var promise = new Promise();
            $.ajax({
                type: "POST",
                url: encodeURI(this.mBaseUrl),
                data: option,
                success: function (data) {
                    var jsonData = JSON.parse(data);
                    if (_this.validate(jsonData)) {
                        promise.succeed(jsonData);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    promise.failed(textStatus);
                }
            });
            return promise;
        };
        Ajax.prototype.get = function (option) {
            var _this = this;
            var promise = new Promise();
            var cacheData = this.getCache(option);
            if (undefined == cacheData) {
                $.ajax({
                    type: "GET",
                    url: encodeURI(this.mBaseUrl),
                    data: option,
                    success: function (data) {
                        var jsonData = JSON.parse(data);
                        if (_this.validate(jsonData)) {
                            _this.setCache(option, jsonData);
                            promise.succeed(jsonData);
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        promise.failed(textStatus);
                    }
                });
            }
            else {
                setTimeout(function () {
                    promise.succeed(cacheData);
                }, 0);
            }
            return promise;
        };
        return Ajax;
    })();
    Util.Ajax = Ajax;
    function formatData(outputData, inputData, precentList, specialsjzhCols, formatStartColumn) {
        if (formatStartColumn === void 0) { formatStartColumn = 1; }
        var zhZb = [
            '人均发电量（万度/人）',
            '外购电单位成本（元/度）',
            '铝杆棒一次综合成品率（%）',
            '其中：5154合金杆一次成品率（%）',
            '4043&8030&6201合金杆一次成品率（%）',
            '高纯铝杆产品一次成品率（%）',
            '铝棒产品一次成品率（%）',
            '铝电解高品质槽99.90%以上等级13项元素符合率（二级以上）（%）',
            '失败成本率1（%）',
            '外部客诉率（%）',
            '4N6精铝块一次成品率（%）',
            '精铝杆一次成品率（%）',
            '综合成品率（%）',
            '基材成品率（%）',
            '粉末喷涂成品率（%）',
            '隔热产品成品率（%）',
            '失败成本率（%）',
            '自产箔综合符单率（%）',
            '委托加工化成箔符单率（%）',
            '架空电缆（1KV、10KV）合格率（%）',
            '钢芯铝绞线合格率（%）',
            '布电线合格率（%）',
            "失败成本率1（%）",
            "外部客诉率(合金材料公司)（%）",
            "外部客诉率(铝箔公司)（%）",
            "外部客诉率(电极箔公司)（%）",
            "失败成本率(金属结构与炭素材料公司)（%）"
        ];
        var formaterChain = new Util.FormatPercentHandler([], precentList.toArray());
        formaterChain.next(new Util.FormatIntHandler(['人数', '制造业人数', '工程、修试业务人数', '物流贸易人数']))
            .next(new Util.FormatPercentHandler(['净资产收益率(%)', '三项费用率(%)', '销售利润率(%)', '负债率', '制造业三项费用率', '工程、修试业务三项费用率', '物流贸易三项费用率']))
            .next(new Util.FormatFordotHandler(1, ['人均利润', '人均收入', '精铝块13项元素和值（ppm）']))
            .next(new Util.FormatFordotHandler(2, ['标煤单耗（g/度）', '厂用电率（%）'], specialsjzhCols))
            .next(new Util.FormatFordotHandler(2, zhZb))
            .next(new Util.FormatFordotHandler(4, ['单位供电成本（元/度）']))
            .next(new Util.FormatCurrencyHandler());
        var row = [];
        for (var j = 0; j < inputData.length; ++j) {
            row = [].concat(inputData[j]);
            for (var i = formatStartColumn; i < row.length; ++i) {
                row[i] = formaterChain.handle(row[0], i, row[i]);
            }
            outputData.push(row);
        }
        return;
    }
    Util.formatData = formatData;
    function toNumber(val) {
        var numberTpe = new Number(val).valueOf();
        return numberTpe;
    }
    Util.toNumber = toNumber;
    function formatInt(val) {
        return formatFordot(val, 0);
    }
    Util.formatInt = formatInt;
    function formatCommaCurrency(val, dotCount) {
        if (dotCount === void 0) { dotCount = 0; }
        if (val === "--" || val === "" || val === "-") {
            return val;
        }
        val = toNumber(val).toFixed(dotCount);
        var dot = val.lastIndexOf('.');
        var intPart = "";
        var parts = [];
        var positive = (val.charAt(0) != '-');
        if (dot > 0) {
            if (positive) {
                intPart = val.substring(0, dot);
            }
            else {
                intPart = val.substring(1, dot);
            }
            parts.push(val.substring(dot));
        }
        else {
            if (positive) {
                intPart = val;
            }
            else {
                intPart = val.substring(1);
            }
        }
        var leftLength = intPart.length;
        while (leftLength > 3) {
            parts.push("," + intPart.substring(leftLength - 3, leftLength));
            leftLength -= 3;
        }
        parts.push(intPart.substring(0, leftLength));
        if (!positive) {
            parts.push("-");
        }
        parts = parts.reverse();
        return parts.join("");
    }
    Util.formatCommaCurrency = formatCommaCurrency;
    function formatCurrency(val) {
        return formatFordot(val, 0);
    }
    Util.formatCurrency = formatCurrency;
    function formatPercent(val) {
        if (val === "--" || val === "" || val === "-") {
            return val;
        }
        return (toNumber(val) * 100).toFixed(1) + "%";
    }
    Util.formatPercent = formatPercent;
    function formatFordot(val, dotCount) {
        if (dotCount === void 0) { dotCount = 1; }
        if (val === "--" || val === "" || val === "-") {
            return val;
        }
        return toNumber(val).toFixed(dotCount);
    }
    Util.formatFordot = formatFordot;
    function formatPercentSignal(val) {
        if (val === "--" || val === "" || val === "-") {
            return val;
        }
        return toNumber(val).toFixed(1) + "%";
    }
    Util.formatPercentSignal = formatPercentSignal;
    function isExist(val) {
        return val != undefined;
    }
    Util.isExist = isExist;
    function isMSIE() {
        return navigator.appName == "Microsoft Internet Explorer";
    }
    Util.isMSIE = isMSIE;
    function replaceNull(arr) {
        for (var i = 0; i < arr.length; ++i) {
            if (arr[i] == null) {
                arr[i] = 0;
            }
        }
        return arr;
    }
    Util.replaceNull = replaceNull;
})(Util || (Util = {}));
