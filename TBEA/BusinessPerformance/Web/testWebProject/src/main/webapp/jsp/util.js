var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
/// <reference path="jqgrid/vector.ts" />
///<reference path="jqgrid/jqassist.ts"/>
var Util;
(function (Util) {
    var TextAlign = JQTable.TextAlign;
    var Node = JQTable.Node;
    (function (ErrorCode) {
        ErrorCode[ErrorCode["OK"] = 0] = "OK";
        ErrorCode[ErrorCode["DATABASE_EXCEPTION"] = 1] = "DATABASE_EXCEPTION";
        ErrorCode[ErrorCode["PREMARY_KEY_CONFILICT"] = 2] = "PREMARY_KEY_CONFILICT";
        ErrorCode[ErrorCode["PREMARY_KEY_NULL"] = 3] = "PREMARY_KEY_NULL";
        ErrorCode[ErrorCode["HAVE_NO_RIGHT"] = 4] = "HAVE_NO_RIGHT";
        ErrorCode[ErrorCode["PRICELIB_JCYCLJG_IMPORT_ERROR"] = 5] = "PRICELIB_JCYCLJG_IMPORT_ERROR";
    })(Util.ErrorCode || (Util.ErrorCode = {}));
    var ErrorCode = Util.ErrorCode;
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName, gridCtrl) {
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
            return new JQTable.JQGridAssistant(nodes, gridName);
        };
        return JQGridAssistantFactory;
    })();
    Util.JQGridAssistantFactory = JQGridAssistantFactory;
    function parseHeader(header) {
        var node = null;
        var readOnly = header.readOnly == "true";
        if ("date" == header.type) {
            node = Node.create({ name: header.name, align: TextAlign.Center, isReadOnly: readOnly, isNumber: false, editType: "text", options: {
                    dataInit: function (element) {
                        $(element).datepicker({
                            dateFormat: 'yy-mm-dd',
                            onSelect: function (dateText, inst) {
                            }
                        });
                    }
                } });
        }
        else if ("text" == header.type) {
            node = Node.create({ name: header.name, align: TextAlign.Center, isReadOnly: readOnly, isNumber: false, editType: "text" });
        }
        else if ("hidden" == header.type) {
            node = null; //Node.create({name : header.name, align : TextAlign.Center, isReadOnly:readOnly,isNumber:false,editType:"text", hidden:true});
        }
        else if ("select" == header.type) {
            node = Node.create({ name: header.name, align: TextAlign.Center, isReadOnly: readOnly, isNumber: false, editType: "select", options: { value: header.options } });
        }
        else {
            node = Node.create({ name: header.name, align: TextAlign.Center, isReadOnly: readOnly });
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
        return ZBStatus;
    })();
    Util.ZBStatus = ZBStatus;
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
        CompanyType[CompanyType["NDGS"] = 84] = "NDGS";
        CompanyType[CompanyType["NJZNDQGS"] = 85] = "NJZNDQGS";
        CompanyType[CompanyType["NLTK"] = 86] = "NLTK";
        CompanyType[CompanyType["NYCY"] = 87] = "NYCY";
        CompanyType[CompanyType["NYSYB"] = 88] = "NYSYB";
        CompanyType[CompanyType["RDGS"] = 89] = "RDGS";
        CompanyType[CompanyType["SBDCY"] = 90] = "SBDCY";
        CompanyType[CompanyType["SBDCYJT"] = 91] = "SBDCYJT";
        CompanyType[CompanyType["SBDLKBSJGS"] = 92] = "SBDLKBSJGS";
        CompanyType[CompanyType["SBDLYDHGS"] = 93] = "SBDLYDHGS";
        CompanyType[CompanyType["SBGJMYCTGS"] = 94] = "SBGJMYCTGS";
        CompanyType[CompanyType["SBGS"] = 95] = "SBGS";
        CompanyType[CompanyType["SBKJHGQ"] = 96] = "SBKJHGQ";
        CompanyType[CompanyType["SBSKGS"] = 97] = "SBSKGS";
        CompanyType[CompanyType["SBWYGS"] = 98] = "SBWYGS";
        CompanyType[CompanyType["SBXDWLGS"] = 99] = "SBXDWLGS";
        CompanyType[CompanyType["SBXNY"] = 100] = "SBXNY";
        CompanyType[CompanyType["SBXSYX"] = 101] = "SBXSYX";
        CompanyType[CompanyType["SBZTFGS"] = 102] = "SBZTFGS";
        CompanyType[CompanyType["SBZXGS"] = 103] = "SBZXGS";
        CompanyType[CompanyType["SDDLGCGS"] = 104] = "SDDLGCGS";
        CompanyType[CompanyType["SDFGS"] = 105] = "SDFGS";
        CompanyType[CompanyType["SKGS"] = 106] = "SKGS";
        CompanyType[CompanyType["TBDG_YD_NYYXGS"] = 107] = "TBDG_YD_NYYXGS";
        CompanyType[CompanyType["TBGS"] = 108] = "TBGS";
        CompanyType[CompanyType["TCNY"] = 109] = "TCNY";
        CompanyType[CompanyType["TLGS"] = 110] = "TLGS";
        CompanyType[CompanyType["TLXMGS"] = 111] = "TLXMGS";
        CompanyType[CompanyType["TYDXXMGS"] = 112] = "TYDXXMGS";
        CompanyType[CompanyType["TYDXXMGS_LL"] = 113] = "TYDXXMGS_LL";
        CompanyType[CompanyType["TYDXXMGS_XL"] = 114] = "TYDXXMGS_XL";
        CompanyType[CompanyType["TYXMGS"] = 115] = "TYXMGS";
        CompanyType[CompanyType["TZDGXJDGCLYXGS"] = 116] = "TZDGXJDGCLYXGS";
        CompanyType[CompanyType["TZDLXMGS"] = 117] = "TZDLXMGS";
        CompanyType[CompanyType["TZDLYFXMGS"] = 118] = "TZDLYFXMGS";
        CompanyType[CompanyType["WLGS"] = 119] = "WLGS";
        CompanyType[CompanyType["WYDXDLC"] = 120] = "WYDXDLC";
        CompanyType[CompanyType["WYGS"] = 121] = "WYGS";
        CompanyType[CompanyType["XADLSJY"] = 122] = "XADLSJY";
        CompanyType[CompanyType["XBC"] = 123] = "XBC";
        CompanyType[CompanyType["XBCBB"] = 124] = "XBCBB";
        CompanyType[CompanyType["XBGJCTGCGS"] = 125] = "XBGJCTGCGS";
        CompanyType[CompanyType["XBGNGCJXGS"] = 126] = "XBGNGCJXGS";
        CompanyType[CompanyType["XBGS"] = 127] = "XBGS";
        CompanyType[CompanyType["XBXBGS"] = 128] = "XBXBGS";
        CompanyType[CompanyType["XBYTGS"] = 129] = "XBYTGS";
        CompanyType[CompanyType["XDWLGS"] = 130] = "XDWLGS";
        CompanyType[CompanyType["XJFGS"] = 131] = "XJFGS";
        CompanyType[CompanyType["XJNY"] = 132] = "XJNY";
        CompanyType[CompanyType["XJXTGJWLMYGS"] = 133] = "XJXTGJWLMYGS";
        CompanyType[CompanyType["XJZXGS"] = 134] = "XJZXGS";
        CompanyType[CompanyType["XKGS"] = 135] = "XKGS";
        CompanyType[CompanyType["XLC"] = 136] = "XLC";
        CompanyType[CompanyType["XLGGS"] = 137] = "XLGGS";
        CompanyType[CompanyType["XNDQGCGS"] = 138] = "XNDQGCGS";
        CompanyType[CompanyType["XNDQGS"] = 139] = "XNDQGS";
        CompanyType[CompanyType["XNY"] = 140] = "XNY";
        CompanyType[CompanyType["XNYCY"] = 141] = "XNYCY";
        CompanyType[CompanyType["XNYGS"] = 142] = "XNYGS";
        CompanyType[CompanyType["XNYSYB"] = 143] = "XNYSYB";
        CompanyType[CompanyType["XNYYJY"] = 144] = "XNYYJY";
        CompanyType[CompanyType["XSGS"] = 145] = "XSGS";
        CompanyType[CompanyType["XSZGS"] = 146] = "XSZGS";
        CompanyType[CompanyType["XSZX"] = 147] = "XSZX";
        CompanyType[CompanyType["XTBLGS"] = 148] = "XTBLGS";
        CompanyType[CompanyType["XTDLXMGS"] = 149] = "XTDLXMGS";
        CompanyType[CompanyType["XTGS"] = 150] = "XTGS";
        CompanyType[CompanyType["XTJCSYB"] = 151] = "XTJCSYB";
        CompanyType[CompanyType["XTNYGS"] = 152] = "XTNYGS";
        CompanyType[CompanyType["XTWLGS"] = 153] = "XTWLGS";
        CompanyType[CompanyType["ZBDC"] = 154] = "ZBDC";
        CompanyType[CompanyType["ZHGS"] = 155] = "ZHGS";
        CompanyType[CompanyType["ZHGS_SYB"] = 156] = "ZHGS_SYB";
        CompanyType[CompanyType["YJJSGCGS"] = 157] = "YJJSGCGS";
        CompanyType[CompanyType["ZPDCJ"] = 158] = "ZPDCJ";
        CompanyType[CompanyType["ZTWLGS"] = 159] = "ZTWLGS";
        CompanyType[CompanyType["YXGS"] = 160] = "YXGS";
        CompanyType[CompanyType["ZHGS_MYGS"] = 161] = "ZHGS_MYGS";
        CompanyType[CompanyType["ZJWL"] = 162] = "ZJWL";
        CompanyType[CompanyType["ZJZTGJWLYXGS"] = 163] = "ZJZTGJWLYXGS";
        CompanyType[CompanyType["ZTFGS"] = 164] = "ZTFGS";
        CompanyType[CompanyType["ZTGS"] = 165] = "ZTGS";
        CompanyType[CompanyType["ZXGS"] = 166] = "ZXGS";
        CompanyType[CompanyType["ZYGS"] = 167] = "ZYGS";
        //三期新增项目公司
        CompanyType[CompanyType["SBDQSBGS"] = 168] = "SBDQSBGS";
        CompanyType[CompanyType["CTGYGS"] = 169] = "CTGYGS";
        CompanyType[CompanyType["DXDLJCZXGS"] = 170] = "DXDLJCZXGS";
        //非正式公司
        CompanyType[CompanyType["DBSBDCYJT"] = 171] = "DBSBDCYJT";
        CompanyType[CompanyType["NFSBDCYJT"] = 172] = "NFSBDCYJT";
        CompanyType[CompanyType["GCCY"] = 173] = "GCCY";
        CompanyType[CompanyType["XBCZT"] = 174] = "XBCZT";
        CompanyType[CompanyType["BYQCY"] = 175] = "BYQCY";
        CompanyType[CompanyType["XLCY"] = 176] = "XLCY";
        CompanyType[CompanyType["UNKNOWN"] = 177] = "UNKNOWN";
        CompanyType[CompanyType["RSGS"] = 178] = "RSGS";
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
    var Ajax = (function () {
        function Ajax(baseUrl, useCache) {
            if (useCache === void 0) { useCache = true; }
            this.mCache = {};
            this.mBaseUrl = baseUrl;
            this.mUseCache = useCache;
        }
        Ajax.toUrlParam = function (option) {
            var keys = [];
            for (var key in option) {
                keys.push(key + "=" + option[key]);
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
        Ajax.prototype.validate = function (data) {
            if (data.error == "invalidate session") {
                window.location.href = data.redirect;
                return false;
            }
            return true;
        };
        Ajax.prototype.post = function (option) {
            var _this = this;
            var promise = new Promise();
            $.ajax({
                type: "POST",
                url: this.mBaseUrl,
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
                    url: this.mBaseUrl,
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
            '布电线合格率（%）'];
        var formaterChain = new Util.FormatPercentHandler([], precentList.toArray());
        formaterChain.next(new Util.FormatIntHandler(['人数', '制造业人数', '工程、修试业务人数', '物流贸易人数']))
            .next(new Util.FormatPercentSignalHandler(['净资产收益率(%)']))
            .next(new Util.FormatPercentHandler(['三项费用率(%)', '销售利润率(%)', '负债率', '制造业三项费用率', '工程、修试业务三项费用率', '物流贸易三项费用率']))
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
