var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var framework;
(function (framework) {
    var templates;
    (function (templates) {
        var dateReport;
        (function (dateReport) {
            function create() {
                return new ItemShowView();
            }
            dateReport.create = create;
            var ItemShowView = /** @class */ (function (_super) {
                __extends(ItemShowView, _super);
                function ItemShowView() {
                    var _this = _super !== null && _super.apply(this, arguments) || this;
                    _this.doubleHeader = false;
                    _this.firstUpdate = true;
                    _this.chartsData = {};
                    return _this;
                }
                ItemShowView.prototype.onInitialize = function (opt) {
                    var _this = this;
                    this.mDataUrl = new Util.Ajax(opt.dataUrl, false);
                    this.unitedSelector = new Util.UnitedSelector(opt.itemNodes, opt.itemId);
                    this.unitedSelector.change(function () {
                        _this.adjustHeader();
                    });
                    var itemHidden = false;
                    if (opt.itemNodes.length == 1) {
                        $("#" + opt.itemId).hide();
                        itemHidden = true;
                    }
                    var item2Hidden = false;
                    if (opt.itemNodes2 != undefined) {
                        this.unitedSelector2 = new Util.UnitedSelector(opt.itemNodes2, opt.itemId2);
                        this.unitedSelector.change(function () {
                            _this.adjustHeader();
                        });
                        if (opt.itemNodes2.length == 1) {
                            $("#" + opt.itemId2).hide();
                            item2Hidden = true;
                        }
                    }
                    // if (opt.searchlist == 'true'){
                    //     $("#" + opt.itemId + " select").select2({
                    //         language: "zh-CN"
                    //     });
                    // }
                    if (item2Hidden && itemHidden && $("#" + opt.dtId).hasClass("hidden")) {
                        $("#sels").hide();
                        $("#" + opt.dtId).hide();
                    }
                    $(window).resize(function () {
                        _this.adjustHeader();
                    });
                    _super.prototype.onInitialize.call(this, opt);
                };
                ItemShowView.prototype.getParams = function (date) {
                    return {
                        date: this.getDate(date),
                        item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id,
                        item2: this.unitedSelector2 ? this.unitedSelector2.getDataNode(this.unitedSelector2.getPath()).data.id : undefined
                    };
                };
                ItemShowView.prototype.adjustHeader = function () {
                    $("#headerHost").removeCss("width");
                    if ($("#headerHost").height() > 40) {
                        $(".page-header").addClass("page-header-double");
                        $("#headerHost").css("width", $("#sels").width() + "px");
                        if (!this.doubleHeader) {
                            this.doubleHeader = true;
                            return true;
                        }
                    }
                    else {
                        $(".page-header").removeClass("page-header-double");
                        if (this.doubleHeader) {
                            this.doubleHeader = false;
                            return true;
                        }
                    }
                    return false;
                };
                ItemShowView.prototype.onUpdateChartsData = function () {
                    var Douw = this.resp;
                    var LRZB = new Array();
                    var ZbSj;
                    var bhqs_lr = new Array();
                    var bhqs_sr = new Array();
                    var bhqs_xslr = new Array();
                    var bhqs_lr_12;
                    var bhqs_sr_12;
                    var bhqs_xslr_12;
                    var xjl_lr_1;
                    var xjl_lr_2;
                    var xjl_lr_3;
                    var xjl_lc_1;
                    var xjl_lc_2;
                    var xjl_lc_3;
                    var yszk_zl_1 = new Array();
                    var yszk_zl_2 = new Array();
                    var yszk_zl_3 = new Array();
                    var yszk_zl_4 = new Array();
                    var yszk_zl_5 = new Array();
                    var yszk_zl_6 = new Array();
                    var yqk_zx_1 = new Array();
                    var yqk_zx_2 = new Array();
                    var yqk_zx_3 = new Array();
                    var yqk_zx_4 = new Array();
                    var yqk_zx_5 = new Array();
                    var yqk_zt_1 = new Array();
                    var yqk_zt_2 = new Array();
                    var yqk_zt_3 = new Array();
                    var yqk_ys_sum = [0, 0, 0, 0, 0, 0, 0];
                    var yqk_ys_sum2 = new Array();
                    var yqk_ys_1 = new Array();
                    var yqk_ys_2 = new Array();
                    var yqk_ys_3 = new Array();
                    var yqk_ys_4 = new Array();
                    var yszk_zmye_1 = new Array();
                    var yszk_zmye_2 = new Array();
                    var yszk_sxzb_1 = new Array();
                    var yszk_sxzb_2 = new Array();
                    var yszk_sxzb_3 = new Array();
                    var ch_zlbh_1 = new Array();
                    var ch_zlbh_2 = new Array();
                    var ch_zlbh_3 = new Array();
                    var ch_zlbh_4 = new Array();
                    var ch_zlbh_5 = new Array();
                    var ch_zlbh_6 = new Array();
                    var ch_xz_1 = new Array();
                    var ch_xz_2 = new Array();
                    var ch_xz_3 = new Array();
                    var zjhl_yw_1;
                    var zjhl_yw_2;
                    var zjhl_hkfs_1;
                    var zjhl_hkfs_2;
                    var zjhl_hkfs_3;
                    var djqy_bhqs_1;
                    var qyqk_scqs_1 = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
                    var qyqk_scqs_2 = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
                    var qyqk_scqs_3 = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
                    var qyqk_scqs_4 = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
                    var cyqy_kV_0;
                    var cyqy_kV_1;
                    var cyqy_kV_2;
                    var cyqy_kV_3;
                    var cyqy_cp_1;
                    var cyqy_cp_2;
                    var czzb_wcqk_1;
                    var czzb_wcqk_2;
                    var czzb_wcqk_3;
                    var czzb_wcqk_4;
                    var czzb_wcqk_5;
                    var czzb_wcqk_6;
                    var czzb_wcqk_7;
                    var czzb_wcqk_8;
                    var fyzk_qsbd_1;
                    var fyzk_qsbd_2;
                    var fyzk_qsbd_3;
                    var qz_tlyl_1;
                    var qz_tlyl_2;
                    LRZB.push(Douw.lrsr[1][11]);
                    LRZB.push(Douw.lrsr[2][11]);
                    LRZB.push(Douw.lrsr[3][11]);
                    LRZB.push(Douw.lrsr[4][11]);
                    LRZB.push(Douw.lrsr[5][11]);
                    LRZB.push(Douw.lrsr[7][11]);
                    LRZB.push(Douw.lrsr[8][11]);
                    LRZB.push(Douw.lrsr[9][11]);
                    LRZB.push(Douw.lrsr[10][11]);
                    ZbSj = Douw.zbsj[1];
                    for (var lrw = 0; lrw < Douw.bhqs_lr.length; lrw++) {
                        for (var lr = 0; lr < Douw.bhqs_lr[lrw].length; lr++) {
                            if (Douw.bhqs_lr[lrw][lr] == "--") {
                                Douw.bhqs_lr[lrw][lr] = "0.0";
                            }
                        }
                    }
                    bhqs_lr.push(Douw.bhqs_lr[0]);
                    bhqs_sr.push(Douw.bhqs_lr[6]);
                    bhqs_xslr.push(Douw.bhqs_lr[11]);
                    for (var xs = 0; xs < bhqs_xslr[0].length; xs++) {
                        var xslr = bhqs_xslr[0][xs];
                        var w = RegExp(/%/);
                        if (w.exec(xslr)) {
                            bhqs_xslr[0][xs] = xslr.replace(w, "");
                        }
                    }
                    bhqs_lr_12 = bhqs_lr[0];
                    bhqs_sr_12 = bhqs_sr[0];
                    bhqs_xslr_12 = bhqs_xslr[0];
                    for (var xlrlc = 0; xlrlc < Douw.xjl_lrlc.length; xlrlc++) {
                        for (var xlr = 0; xlr < Douw.xjl_lrlc[xlrlc].length; xlr++) {
                            if (Douw.xjl_lrlc[xlrlc][xlr] == "--") {
                                Douw.xjl_lrlc[xlrlc][xlr] = "0.0";
                            }
                        }
                    }
                    xjl_lr_1 = Douw.xjl_lrlc[0];
                    xjl_lc_1 = Douw.xjl_lrlc[11];
                    xjl_lr_2 = Douw.xjl_lrlc[1];
                    xjl_lc_2 = Douw.xjl_lrlc[12];
                    xjl_lr_3 = Douw.xjl_lrlc[2];
                    xjl_lc_3 = Douw.xjl_lrlc[13];
                    for (var yzl = 0; yzl < Douw.yszk_zl.length; yzl++) {
                        for (var zl = 0; zl < Douw.yszk_zl[yzl].length; zl++) {
                            if (Douw.yszk_zl[yzl][zl] == "--") {
                                Douw.yszk_zl[yzl][zl] = "0.0";
                            }
                        }
                        yszk_zl_1.push(Douw.yszk_zl[yzl][0]);
                        yszk_zl_2.push(Douw.yszk_zl[yzl][1]);
                        yszk_zl_3.push(Douw.yszk_zl[yzl][2]);
                        yszk_zl_4.push(Douw.yszk_zl[yzl][3]);
                        yszk_zl_5.push(Douw.yszk_zl[yzl][4]);
                        yszk_zl_6.push(Douw.yszk_zl[yzl][5]);
                    }
                    for (var yqwd = 0; yqwd < Douw.yqkzx_yqwd.length; yqwd++) {
                        for (var qwd = 0; qwd < Douw.yqkzx_yqwd[yqwd].length; qwd++) {
                            if (Douw.yqkzx_yqwd[yqwd][qwd] == "--") {
                                Douw.yqkzx_yqwd[yqwd][qwd] = "0.0";
                            }
                        }
                        yqk_zx_1.push(Douw.yqkzx_yqwd[yqwd][0]);
                        yqk_zx_2.push(Douw.yqkzx_yqwd[yqwd][1]);
                        yqk_zx_3.push(Douw.yqkzx_yqwd[yqwd][2]);
                        yqk_zx_4.push(Douw.yqkzx_yqwd[yqwd][3]);
                        yqk_zx_5.push(Douw.yqkzx_yqwd[yqwd][4]);
                        yqk_zt_1.push(Douw.yqkzx_yqwd[yqwd][5]);
                        yqk_zt_2.push(Douw.yqkzx_yqwd[yqwd][6]);
                        yqk_zt_3.push(Douw.yqkzx_yqwd[yqwd][7]);
                    }
                    for (var yqwd = 0; yqwd < Douw.yqk_ys.length; yqwd++) {
                        for (var qwd = 0; qwd < Douw.yqk_ys[yqwd].length; qwd++) {
                            if (Douw.yqk_ys[yqwd][qwd] == "--") {
                                Douw.yqk_ys[yqwd][qwd] = "0.0";
                            }
                        }
                        yqk_ys_sum2[0] = yqk_ys_sum[0] = yqk_ys_sum[0] + parseFloat(Douw.yqk_ys[yqwd][0]);
                        yqk_ys_sum2[1] = yqk_ys_sum[1] = yqk_ys_sum[1] + parseFloat(Douw.yqk_ys[yqwd][1]);
                        yqk_ys_sum2[2] = yqk_ys_sum[2] = yqk_ys_sum[2] + parseFloat(Douw.yqk_ys[yqwd][2]);
                        yqk_ys_sum2[3] = yqk_ys_sum[3] = yqk_ys_sum[3] + parseFloat(Douw.yqk_ys[yqwd][3]);
                        yqk_ys_sum2[4] = yqk_ys_sum[4] = yqk_ys_sum[4] + parseFloat(Douw.yqk_ys[yqwd][4]);
                        yqk_ys_sum2[5] = yqk_ys_sum[5] = yqk_ys_sum[5] + parseFloat(Douw.yqk_ys[yqwd][5]);
                        yqk_ys_sum2[6] = yqk_ys_sum[6] = yqk_ys_sum[6] + parseFloat(Douw.yqk_ys[yqwd][6]);
                    }
                    for (var su = 0; su < yqk_ys_sum.length - 1; su++) {
                        for (var su2 = 0; su2 < yqk_ys_sum.length - 1 - su; su2++) {
                            if (yqk_ys_sum[su2] < yqk_ys_sum[su2 + 1]) {
                                var temp = yqk_ys_sum[su2];
                                yqk_ys_sum[su2] = yqk_ys_sum[su2 + 1];
                                yqk_ys_sum[su2 + 1] = temp;
                            }
                        }
                    }
                    for (var sum2 = 0; sum2 < yqk_ys_sum2.length; sum2++) {
                        if (yqk_ys_sum[0] == yqk_ys_sum2[sum2]) {
                            for (var yqwd = 0; yqwd < Douw.yqk_ys.length; yqwd++) {
                                yqk_ys_1.push(Douw.yqk_ys[yqwd][sum2]);
                            }
                        }
                        else if (yqk_ys_sum[1] == yqk_ys_sum2[sum2]) {
                            for (var yqwd = 0; yqwd < Douw.yqk_ys.length; yqwd++) {
                                yqk_ys_2.push(Douw.yqk_ys[yqwd][sum2]);
                            }
                        }
                        else if (yqk_ys_sum[2] == yqk_ys_sum2[sum2]) {
                            for (var yqwd = 0; yqwd < Douw.yqk_ys.length; yqwd++) {
                                yqk_ys_3.push(Douw.yqk_ys[yqwd][sum2]);
                            }
                        }
                        else if (yqk_ys_sum[3] == yqk_ys_sum2[sum2]) {
                            for (var yqwd = 0; yqwd < Douw.yqk_ys.length; yqwd++) {
                                yqk_ys_4.push(Douw.yqk_ys[yqwd][sum2]);
                            }
                        }
                    }
                    for (var ysj = 0; ysj < Douw.yszk_sj.length; ysj++) {
                        for (var sj = 0; sj < Douw.yszk_sj[ysj].length; sj++) {
                            if (Douw.yszk_sj[ysj][sj] == "--") {
                                Douw.yszk_sj[ysj][sj] = "0.0";
                            }
                        }
                        yszk_zmye_1.push(Douw.yszk_sj[ysj][0]);
                        yszk_zmye_2.push(Douw.yszk_sj[ysj][7]);
                        yszk_sxzb_1.push(Douw.yszk_sj[ysj][2]);
                        yszk_sxzb_2.push(Douw.yszk_sj[ysj][3]);
                        yszk_sxzb_3.push(Douw.yszk_sj[ysj][4]);
                    }
                    for (var yzlbh = 0; yzlbh < Douw.ch_zlbh.length; yzlbh++) {
                        for (var zlbh = 0; zlbh < Douw.ch_zlbh[yzlbh].length; zlbh++) {
                            if (Douw.ch_zlbh[yzlbh][zlbh] == "--") {
                                Douw.ch_zlbh[yzlbh][zlbh] = "0.0";
                            }
                        }
                        ch_zlbh_1.push(Douw.ch_zlbh[yzlbh][0]);
                        ch_zlbh_2.push(Douw.ch_zlbh[yzlbh][1]);
                        ch_zlbh_3.push(Douw.ch_zlbh[yzlbh][2]);
                        ch_zlbh_4.push(Douw.ch_zlbh[yzlbh][3]);
                        ch_zlbh_5.push(Douw.ch_zlbh[yzlbh][4]);
                        ch_zlbh_6.push(Douw.ch_zlbh[yzlbh][5]);
                    }
                    for (var cxz = 0; cxz < Douw.ch_xz.length; cxz++) {
                        for (var xz = 0; xz < Douw.ch_xz[cxz].length; xz++) {
                            if (Douw.ch_xz[cxz][xz] == "--") {
                                Douw.ch_xz[cxz][xz] = "0.0";
                            }
                        }
                        ch_xz_1.push(Douw.ch_xz[cxz][0]);
                        ch_xz_2.push(Douw.ch_xz[cxz][1]);
                        ch_xz_3.push(Douw.ch_xz[cxz][2]);
                    }
                    for (var zyw = 0; zyw < Douw.zjhl_yw.length; zyw++) {
                        for (var yw = 0; yw < Douw.zjhl_yw[zyw].length; yw++) {
                            if (Douw.zjhl_yw[zyw][yw] == "--") {
                                Douw.zjhl_yw[zyw][yw] = "0.0";
                            }
                        }
                    }
                    zjhl_yw_1 = Douw.zjhl_yw[0];
                    zjhl_yw_2 = Douw.zjhl_yw[5];
                    for (var zhkfs = 0; zhkfs < Douw.zjhl_hkfs.length; zhkfs++) {
                        for (var hkfs = 0; hkfs < Douw.zjhl_hkfs[zhkfs].length; hkfs++) {
                            if (Douw.zjhl_hkfs[zhkfs][hkfs] == "--") {
                                Douw.zjhl_hkfs[zhkfs][hkfs] = "0.0";
                            }
                        }
                    }
                    zjhl_hkfs_1 = Douw.zjhl_hkfs[1];
                    zjhl_hkfs_2 = Douw.zjhl_hkfs[2];
                    zjhl_hkfs_3 = Douw.zjhl_hkfs[3];
                    for (var dbhqs = 0; dbhqs < Douw.djqy_bhqs.length; dbhqs++) {
                        for (var bhqs = 0; bhqs < Douw.djqy_bhqs[dbhqs].length; bhqs++) {
                            if (Douw.djqy_bhqs[dbhqs][bhqs] == "--") {
                                Douw.djqy_bhqs[dbhqs][bhqs] = "0.0";
                            }
                        }
                    }
                    djqy_bhqs_1 = Douw.djqy_bhqs[0];
                    for (var qscqs = 0; qscqs < Douw.qyqk_scqs.length; qscqs++) {
                        for (var scqs = 0; scqs < Douw.qyqk_scqs[qscqs].length; scqs++) {
                            if (Douw.qyqk_scqs[qscqs][scqs] == "--") {
                                Douw.qyqk_scqs[qscqs][scqs] = "0.0";
                            }
                        }
                    }
                    for (var scqs = 0; scqs < Douw.qyqk_scqs[0].length; scqs++) {
                        qyqk_scqs_1[scqs] = qyqk_scqs_1[scqs] + parseFloat(Douw.qyqk_scqs[0][scqs]) + parseFloat(Douw.qyqk_scqs[1][scqs]) + parseFloat(Douw.qyqk_scqs[2][scqs]) + parseFloat(Douw.qyqk_scqs[3][scqs]);
                        qyqk_scqs_2[scqs] = qyqk_scqs_2[scqs] + parseFloat(Douw.qyqk_scqs[4][scqs]) + parseFloat(Douw.qyqk_scqs[5][scqs]) + parseFloat(Douw.qyqk_scqs[6][scqs]);
                        qyqk_scqs_3[scqs] = qyqk_scqs_3[scqs] + parseFloat(Douw.qyqk_scqs[7][scqs]) + parseFloat(Douw.qyqk_scqs[8][scqs]) + parseFloat(Douw.qyqk_scqs[9][scqs]) + parseFloat(Douw.qyqk_scqs[10][scqs]) + parseFloat(Douw.qyqk_scqs[11][scqs]) + parseFloat(Douw.qyqk_scqs[12][scqs]);
                        qyqk_scqs_4[scqs] = qyqk_scqs_4[scqs] + parseFloat(Douw.qyqk_scqs[13][scqs]) + parseFloat(Douw.qyqk_scqs[14][scqs]);
                    }
                    for (var ckv = 0; ckv < Douw.cyqy_kV.length; ckv++) {
                        for (var kv = 0; kv < Douw.cyqy_kV[ckv].length; kv++) {
                            if (Douw.cyqy_kV[ckv][kv] == "--") {
                                Douw.cyqy_kV[ckv][kv] = "0.0";
                            }
                        }
                    }
                    cyqy_kV_0 = Douw.cyqy_kV[2];
                    cyqy_kV_1 = Douw.cyqy_kV[3];
                    cyqy_kV_2 = Douw.cyqy_kV[4];
                    cyqy_kV_3 = Douw.cyqy_kV[5];
                    cyqy_cp_1 = Douw.cyqy_kV[25];
                    cyqy_cp_2 = Douw.cyqy_kV[26];
                    for (var cwcqk = 0; cwcqk < Douw.czzb_wcqk.length; cwcqk++) {
                        for (var wcqk = 0; wcqk < Douw.czzb_wcqk[cwcqk].length; wcqk++) {
                            if (Douw.czzb_wcqk[cwcqk][wcqk] == "--") {
                                Douw.czzb_wcqk[cwcqk][wcqk] = "0.0";
                            }
                        }
                    }
                    czzb_wcqk_1 = Douw.czzb_wcqk[0];
                    czzb_wcqk_2 = Douw.czzb_wcqk[8];
                    czzb_wcqk_3 = Douw.czzb_wcqk[14];
                    czzb_wcqk_4 = Douw.czzb_wcqk[19];
                    czzb_wcqk_5 = Douw.czzb_wcqk[23];
                    czzb_wcqk_6 = Douw.czzb_wcqk[28];
                    czzb_wcqk_7 = Douw.czzb_wcqk[33];
                    czzb_wcqk_8 = Douw.czzb_wcqk[39];
                    for (var fqsbd = 0; fqsbd < Douw.fyzk_qsbd.length; fqsbd++) {
                        for (var qsbd = 0; qsbd < Douw.fyzk_qsbd[fqsbd].length; qsbd++) {
                            if (Douw.fyzk_qsbd[fqsbd][qsbd] == "--") {
                                Douw.fyzk_qsbd[fqsbd][qsbd] = "0.0";
                            }
                        }
                    }
                    fyzk_qsbd_1 = Douw.fyzk_qsbd[0];
                    fyzk_qsbd_2 = Douw.fyzk_qsbd[1];
                    fyzk_qsbd_3 = Douw.fyzk_qsbd[2];
                    for (var qtlyl = 0; qtlyl < Douw.qz_tlyl.length; qtlyl++) {
                        for (var tlyl = 0; tlyl < Douw.qz_tlyl[qtlyl].length; tlyl++) {
                            if (Douw.qz_tlyl[qtlyl][tlyl] == "--") {
                                Douw.qz_tlyl[qtlyl][tlyl] = "0.0";
                            }
                        }
                    }
                    qz_tlyl_1 = Douw.qz_tlyl[0];
                    qz_tlyl_2 = Douw.qz_tlyl[1];
                    var lrzb = echarts.init(document.getElementById("lrzb"));
                    var lrzbOption = {
                        animation: false,
                        title: {
                            text: '利润指标完成情况',
                            x: 'center'
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                        },
                        series: [
                            {
                                type: 'pie',
                                radius: '45%',
                                center: ['50%', '60%'],
                                data: [
                                    { value: LRZB[0], name: '制造业' },
                                    { value: LRZB[1], name: '集成服务业' },
                                    { value: LRZB[2], name: '物流贸易' },
                                    { value: LRZB[3], name: '项目资金' },
                                    { value: LRZB[4], name: '其他' }
                                ],
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    },
                                    normal: {
                                        label: {
                                            show: true,
                                            position: 'outer',
                                            formatter: "{b}{c} "
                                        }
                                    }
                                }
                            }
                        ]
                    };
                    lrzb.setOption(lrzbOption);
                    //收入指标占比饼状图
                    var srzb = echarts.init(document.getElementById("srzb"));
                    var srzbOption = {
                        animation: false,
                        title: {
                            text: '收入指标完成情况',
                            x: 'center'
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                        },
                        series: [
                            {
                                type: 'pie',
                                radius: '45%',
                                center: ['50%', '60%'],
                                data: [
                                    { value: LRZB[5], name: '制造业' },
                                    { value: LRZB[6], name: '集成服务业' },
                                    { value: LRZB[7], name: '物流贸易' }
                                ],
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    },
                                    normal: {
                                        label: {
                                            show: true,
                                            position: 'outer',
                                            formatter: "{b}{c} "
                                        }
                                    }
                                }
                            }
                        ]
                    };
                    srzb.setOption(srzbOption);
                    //利润变化趋势
                    var bhqs_lrChart = echarts.init(document.getElementById("bhqs_lr"));
                    var bhqs_lroption = {
                        animation: false,
                        title: {
                            text: '利润变化趋势',
                            x: 'center'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                                data: bhqs_lr_12,
                                type: 'line',
                                label: {
                                    normal: {
                                        show: true,
                                        position: 'top'
                                    }
                                },
                                smooth: true
                            }]
                    };
                    bhqs_lrChart.setOption(bhqs_lroption);
                    //收入变化趋势
                    var bhqs_srChart = echarts.init(document.getElementById("bhqs_sr"));
                    var bhqs_sroption = {
                        animation: false,
                        title: {
                            text: '收入变化趋势',
                            x: 'center'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                                data: bhqs_sr_12,
                                type: 'line',
                                label: {
                                    normal: {
                                        show: true,
                                        position: 'top'
                                    }
                                },
                                smooth: true
                            }]
                    };
                    bhqs_srChart.setOption(bhqs_sroption);
                    //销售利润率变化趋势
                    var bhqs_xslrChart = echarts.init(document.getElementById("bhqs_xslr"));
                    var bhqs_xslroption = {
                        animation: false,
                        title: {
                            text: '销售利润率变化趋势',
                            x: 'center'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}%'
                            }
                        },
                        series: [{
                                data: bhqs_xslr_12,
                                type: 'line',
                                label: {
                                    normal: {
                                        show: true,
                                        position: 'top',
                                        formatter: "{c}%"
                                    }
                                },
                                smooth: true
                            }]
                    };
                    bhqs_xslrChart.setOption(bhqs_xslroption);
                    //现金流流入
                    var xjl_lr = echarts.init(document.getElementById("xjl_lr"));
                    var xjl_lroption = {
                        animation: false,
                        title: {
                            text: '现金流流入情况',
                            x: 'center'
                        },
                        legend: {
                            data: ['销售商品、提供劳务收到的现金', '收到的税费返还', '收到的其他与经营活动有关的现金'],
                            top: '8%'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                name: '销售商品、提供劳务收到的现金',
                                data: xjl_lr_1,
                                type: 'line',
                            },
                            {
                                name: '收到的税费返还',
                                data: xjl_lr_2,
                                type: 'line',
                            },
                            {
                                name: '收到的其他与经营活动有关的现金',
                                data: xjl_lr_3,
                                type: 'line',
                            }
                        ]
                    };
                    xjl_lr.setOption(xjl_lroption);
                    //现金流流出
                    var xjl_lc = echarts.init(document.getElementById("xjl_lc"));
                    var xjl_lcoption = {
                        animation: false,
                        title: {
                            text: '现金流流出情况',
                            x: 'center'
                        },
                        legend: {
                            data: ['购买商品、接受劳务所支付的现金', '支付给职工以及为职工支付的现金', '支付的各项税费'],
                            top: '8%'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                name: '购买商品、接受劳务所支付的现金',
                                data: xjl_lc_1,
                                type: 'line',
                            },
                            {
                                name: '支付给职工以及为职工支付的现金',
                                data: xjl_lc_2,
                                type: 'line',
                            },
                            {
                                name: '支付的各项税费',
                                data: xjl_lc_3,
                                type: 'line',
                            }
                        ]
                    };
                    xjl_lc.setOption(xjl_lcoption);
                    //应收账款账龄
                    var yszk_zl = echarts.init(document.getElementById("yszk_zl"));
                    var yszk_zloption = {
                        animation: false,
                        title: {
                            text: '应收账款账龄变化情况 ',
                            x: 'center'
                        },
                        legend: {
                            data: ['5年以上', '4-5年', '3-4年', '2-3年', '1-2年', '1年以内'],
                            top: '8%'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                name: '5年以上',
                                data: yszk_zl_1,
                                type: 'line',
                            },
                            {
                                name: '4-5年',
                                data: yszk_zl_2,
                                type: 'line',
                            },
                            {
                                name: '3-4年',
                                data: yszk_zl_3,
                                type: 'line',
                            },
                            {
                                name: '2-3年',
                                data: yszk_zl_4,
                                type: 'line',
                            },
                            {
                                name: '1-2年',
                                data: yszk_zl_5,
                                type: 'line',
                            },
                            {
                                name: '1年以内',
                                data: yszk_zl_6,
                                type: 'line',
                            }
                        ]
                    };
                    yszk_zl.setOption(yszk_zloption);
                    //应收账款款项性质情况整体
                    var yqk_zt = echarts.init(document.getElementById("yqk_zt"));
                    var yqk_ztoption = {
                        animation: false,
                        title: {
                            text: '应收账款变化情况 ',
                            x: 'center'
                        },
                        legend: {
                            data: ['逾期款', '未到期款', '未到期质保金'],
                            top: '8%'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                name: '逾期款',
                                data: yqk_zt_1,
                                type: 'line',
                            },
                            {
                                name: '未到期款',
                                data: yqk_zt_2,
                                type: 'line',
                            },
                            {
                                name: '未到期质保金',
                                data: yqk_zt_3,
                                type: 'line',
                            }
                        ]
                    };
                    yqk_zt.setOption(yqk_ztoption);
                    //逾期款专项
                    var yqk_zx = echarts.init(document.getElementById("yqk_zx"));
                    var yqk_zxoption = {
                        animation: false,
                        title: {
                            text: '逾期款专项变化情况',
                            x: 'center'
                        },
                        legend: {
                            data: ['逾期0-1个月', '逾期1-3月', '逾期3-6月', '逾期6-12月', '逾期1年以上'],
                            top: '8%'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                name: '逾期0-1个月',
                                data: yqk_zx_1,
                                type: 'line',
                            },
                            {
                                name: '逾期1-3月',
                                data: yqk_zx_2,
                                type: 'line',
                            },
                            {
                                name: '逾期3-6月',
                                data: yqk_zx_3,
                                type: 'line',
                            },
                            {
                                name: '逾期6-12月',
                                data: yqk_zx_4,
                                type: 'line',
                            },
                            {
                                name: '逾期1年以上',
                                data: yqk_zx_5,
                                type: 'line',
                            }
                        ]
                    };
                    yqk_zx.setOption(yqk_zxoption);
                    //逾期应收账产生因素
                    var yqk_ys = echarts.init(document.getElementById("yqk_ys"));
                    var yqk_ysoption = {
                        animation: false,
                        title: {
                            text: '占比前四项因素的款项的变化情况 ',
                            x: 'center'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                data: yqk_ys_1,
                                type: 'line',
                            },
                            {
                                data: yqk_ys_2,
                                type: 'line',
                            },
                            {
                                data: yqk_ys_3,
                                type: 'line',
                            },
                            {
                                data: yqk_ys_4,
                                type: 'line',
                            }
                        ]
                    };
                    yqk_ys.setOption(yqk_ysoption);
                    //账面余额的变化趋势
                    var yszk_zmye = echarts.init(document.getElementById("yszk_zmye"));
                    var yszk_zmyeoption = {
                        animation: false,
                        title: {
                            text: '账面余额的变化趋势',
                            x: 'center'
                        },
                        legend: {
                            data: ['应收账款账面余额', '预警台账账面余额'],
                            top: '8%'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                name: '应收账款账面余额',
                                data: yszk_zmye_1,
                                type: 'line',
                            },
                            {
                                name: '预警台账账面余额',
                                data: yszk_zmye_2,
                                type: 'line',
                            }
                        ]
                    };
                    yszk_zmye.setOption(yszk_zmyeoption);
                    //三项指标变化趋势
                    var yszk_sxzb = echarts.init(document.getElementById("yszk_sxzb"));
                    var yszk_sxzboption = {
                        animation: false,
                        title: {
                            text: '指标变化趋势',
                            x: 'center'
                        },
                        legend: {
                            data: ['货发票未开金额', '票开货未发金额', '预收款冲减应收'],
                            top: '8%'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                name: '货发票未开金额',
                                data: yszk_sxzb_1,
                                type: 'line',
                            },
                            {
                                name: '票开货未发金额',
                                data: yszk_sxzb_2,
                                type: 'line',
                            },
                            {
                                name: '预收款冲减应收',
                                data: yszk_sxzb_3,
                                type: 'line',
                            }
                        ]
                    };
                    yszk_sxzb.setOption(yszk_sxzboption);
                    //存货账龄变化情况
                    var ch_zlbh = echarts.init(document.getElementById("ch_zlbh"));
                    var ch_zlbhoption = {
                        animation: false,
                        title: {
                            text: '账龄存货变化情况 ',
                            x: 'center'
                        },
                        legend: {
                            data: ['5年以上', '4-5年', '3-4年', '2-3年', '1-2年', '1年以内'],
                            top: '8%'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                name: '5年以上',
                                data: ch_zlbh_1,
                                type: 'line',
                            },
                            {
                                name: '4-5年',
                                data: ch_zlbh_2,
                                type: 'line',
                            },
                            {
                                name: '3-4年',
                                data: ch_zlbh_3,
                                type: 'line',
                            },
                            {
                                name: '2-3年',
                                data: ch_zlbh_4,
                                type: 'line',
                            },
                            {
                                name: '1-2年',
                                data: ch_zlbh_5,
                                type: 'line',
                            },
                            {
                                name: '1年以内',
                                data: ch_zlbh_6,
                                type: 'line',
                            }
                        ]
                    };
                    ch_zlbh.setOption(ch_zlbhoption);
                    //存货性质情况
                    var ch_xz = echarts.init(document.getElementById("ch_xz"));
                    var ch_xzoption = {
                        animation: false,
                        title: {
                            text: '存货变化情况',
                            x: 'center'
                        },
                        legend: {
                            data: ['原材料', '半成品', '实际库存商品'],
                            top: '8%'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                name: '原材料',
                                data: ch_xz_1,
                                type: 'line',
                            },
                            {
                                name: '半成品',
                                data: ch_xz_2,
                                type: 'line',
                            },
                            {
                                name: '实际库存商品',
                                data: ch_xz_3,
                                type: 'line',
                            }
                        ]
                    };
                    ch_xz.setOption(ch_xzoption);
                    //资金回笼
                    var zjhl_yw = echarts.init(document.getElementById("zjhl_yw"));
                    var zjhl_ywoption = {
                        animation: false,
                        title: {
                            text: '资金回笼情况',
                            x: 'center'
                        },
                        legend: {
                            data: ['销售收入', '资金回笼'],
                            top: '8%'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                name: '销售收入',
                                data: zjhl_yw_1,
                                type: 'line',
                            },
                            {
                                name: '资金回笼',
                                data: zjhl_yw_2,
                                type: 'line',
                            }
                        ]
                    };
                    zjhl_yw.setOption(zjhl_ywoption);
                    //回款方式
                    var zjhl_hkfs = echarts.init(document.getElementById("zjhl_hkfs"));
                    var zjhl_hkfsoption = {
                        animation: false,
                        title: {
                            text: '回款方式的变化趋势',
                            x: 'center'
                        },
                        legend: {
                            data: ['现金', '票据', '其他'],
                            top: '8%'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                name: '现金',
                                data: zjhl_hkfs_1,
                                type: 'line',
                            },
                            {
                                name: '票据',
                                data: zjhl_hkfs_2,
                                type: 'line',
                            },
                            {
                                name: '其他',
                                data: zjhl_hkfs_3,
                                type: 'line',
                            }
                        ]
                    };
                    zjhl_hkfs.setOption(zjhl_hkfsoption);
                    //单机签约方式
                    var djqy_bhqs = echarts.init(document.getElementById("djqy_bhqs"));
                    var djqy_bhqsoption = {
                        animation: false,
                        title: {
                            text: '单机签约的变化趋势',
                            x: 'center'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                data: djqy_bhqs_1,
                                type: 'line',
                            }
                        ]
                    };
                    djqy_bhqs.setOption(djqy_bhqsoption);
                    //市场签约情况
                    var qyqk_scqs = echarts.init(document.getElementById("qyqk_scqs"));
                    var qyqk_scqsoption = {
                        animation: false,
                        title: {
                            text: '市场签约情况',
                            x: 'center'
                        },
                        legend: {
                            data: ['电力市场', '新能源市场', '重点领域市场', '其他市场'],
                            top: '8%'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                name: '电力市场',
                                data: qyqk_scqs_1,
                                type: 'line',
                            },
                            {
                                name: '新能源市场',
                                data: qyqk_scqs_2,
                                type: 'line',
                            },
                            {
                                name: '重点领域市场',
                                data: qyqk_scqs_3,
                                type: 'line',
                            },
                            {
                                name: '其他市场',
                                data: qyqk_scqs_4,
                                type: 'line',
                            }
                        ]
                    };
                    qyqk_scqs.setOption(qyqk_scqsoption);
                    //产品签约情况及趋势   110-500kV产品签约进行展示
                    var cyqy_kV = echarts.init(document.getElementById("cyqy_kV"));
                    var cyqy_kVoption = {
                        animation: false,
                        title: {
                            text: '110-500kV产品签约的变化趋势',
                            x: 'center'
                        },
                        legend: {
                            data: ['66-110KV', '220KV', '330KV', '500KV'],
                            top: '8%'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                name: '66-110KV',
                                data: cyqy_kV_0,
                                type: 'line',
                            },
                            {
                                name: '220KV',
                                data: cyqy_kV_1,
                                type: 'line'
                            },
                            {
                                name: '330KV',
                                data: cyqy_kV_2,
                                type: 'line'
                            },
                            {
                                name: '500KV',
                                data: cyqy_kV_3,
                                type: 'line'
                            }
                        ]
                    };
                    cyqy_kV.setOption(cyqy_kVoption);
                    //对非晶合金、卷铁芯产品签约进行展示
                    var cyqy_cp = echarts.init(document.getElementById("cyqy_cp"));
                    var cyqy_cpoption = {
                        animation: false,
                        title: {
                            text: '非晶合金、卷铁芯产品签约的变化趋势',
                            x: 'center'
                        },
                        legend: {
                            data: ['非晶合金', '卷铁芯'],
                            top: '8%'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                name: '非晶合金',
                                data: cyqy_cp_1,
                                type: 'line',
                            },
                            {
                                name: '卷铁芯',
                                data: cyqy_cp_2,
                                type: 'line',
                            }
                        ]
                    };
                    cyqy_cp.setOption(cyqy_cpoption);
                    //产值情况
                    var czzb_wcqk = echarts.init(document.getElementById("czzb_wcqk"));
                    var czzb_wcqkoption = {
                        animation: false,
                        title: {
                            text: '产品产出变化趋势',
                            x: 'center'
                        },
                        legend: {
                            data: ['交流变压器', '直流变压器', '电抗器', '干式变压器', '配电变压器', '箱式变电站', '特种变压器', '产业链延伸类'],
                            top: '8%'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                name: '交流变压器',
                                data: czzb_wcqk_1,
                                type: 'line',
                            },
                            {
                                name: '直流变压器',
                                data: czzb_wcqk_2,
                                type: 'line',
                            },
                            {
                                name: '电抗器',
                                data: czzb_wcqk_3,
                                type: 'line',
                            },
                            {
                                name: '干式变压器',
                                data: czzb_wcqk_4,
                                type: 'line',
                            },
                            {
                                name: '配电变压器',
                                data: czzb_wcqk_5,
                                type: 'line',
                            },
                            {
                                name: '箱式变电站',
                                data: czzb_wcqk_6,
                                type: 'line',
                            },
                            {
                                name: '特种变压器',
                                data: czzb_wcqk_7,
                                type: 'line',
                            },
                            {
                                name: '产业链延伸类',
                                data: czzb_wcqk_8,
                                type: 'line',
                            }
                        ]
                    };
                    czzb_wcqk.setOption(czzb_wcqkoption);
                    //三项费用管控
                    var fyzk_qsbd = echarts.init(document.getElementById("fyzk_qsbd"));
                    var fyzk_qsbdoption = {
                        animation: false,
                        title: {
                            text: '三项费用变化趋势',
                            x: 'center'
                        },
                        legend: {
                            data: ['销售费用', '管理费用', '财务费用'],
                            top: '8%'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                name: '销售费用',
                                data: fyzk_qsbd_1,
                                type: 'line',
                            },
                            {
                                name: '管理费用',
                                data: fyzk_qsbd_2,
                                type: 'line',
                            },
                            {
                                name: '财务费用',
                                data: fyzk_qsbd_3,
                                type: 'line',
                            }
                        ]
                    };
                    fyzk_qsbd.setOption(fyzk_qsbdoption);
                    //铜铝用量
                    var qz_tlyl = echarts.init(document.getElementById("qz_tlyl"));
                    var qz_tlyloption = {
                        animation: false,
                        title: {
                            text: '铜铝用量情况',
                            x: 'center'
                        },
                        legend: {
                            data: ['铜', '铝'],
                            top: '8%'
                        },
                        xAxis: {
                            type: 'category',
                            data: ZbSj
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [
                            {
                                name: '铜',
                                data: qz_tlyl_1,
                                type: 'line',
                            },
                            {
                                name: '铝',
                                data: qz_tlyl_2,
                                type: 'line',
                            }
                        ]
                    };
                    qz_tlyl.setOption(qz_tlyloption);
                };
                ItemShowView.prototype.onUpdateFormData = function () {
                    this.updateFormInput("lrzb");
                    this.updateFormInput("srzb");
                    this.updateFormInput("bhqs_lr");
                    this.updateFormInput("bhqs_sr");
                    this.updateFormInput("bhqs_xslr");
                    this.updateFormInput("xjl_lr");
                    this.updateFormInput("xjl_lc");
                    this.updateFormInput("yszk_zl");
                    this.updateFormInput("yqk_zt");
                    this.updateFormInput("yqk_zx");
                    this.updateFormInput("yqk_ys");
                    this.updateFormInput("yszk_zmye");
                    this.updateFormInput("yszk_sxzb");
                    this.updateFormInput("ch_zlbh");
                    this.updateFormInput("ch_xz");
                    this.updateFormInput("zjhl_yw");
                    this.updateFormInput("zjhl_hkfs");
                    this.updateFormInput("djqy_bhqs");
                    this.updateFormInput("qyqk_scqs");
                    this.updateFormInput("cyqy_kV");
                    this.updateFormInput("cyqy_cp");
                    this.updateFormInput("czzb_wcqk");
                    this.updateFormInput("fyzk_qsbd");
                    this.updateFormInput("qz_tlyl");
                };
                ItemShowView.prototype.updateFormInput = function (id) {
                    this.chartsData[id] = this.toDataUrl($("#" + id + " canvas"));
                };
                ItemShowView.prototype.update = function (date) {
                    var _this = this;
                    if (!this.firstUpdate) {
                        this.mDataUrl.get(this.getParams(date))
                            .then(function (data) {
                            _this.resp = data;
                            _this.onUpdateChartsData();
                            _this.onUpdateFormData();
                            _this.mAjaxUpdate.post($.extend(_this.chartsData, _this.getParams(date))).then(function (jsonData) {
                                $("#exportForm")[0].action = encodeURI(_this.opt.exportUrl + "?" + Util.Ajax.toUrlParam(jsonData));
                                $("#exportForm")[0].submit();
                            });
                        });
                    }
                    else {
                        this.firstUpdate = false;
                    }
                };
                ItemShowView.prototype.toDataUrl = function (canvases) {
                    if (canvases.length > 0) {
                        var cloneElem = canvases.eq(0).clone();
                        var context = cloneElem[0].getContext("2d");
                        for (var i = 0; i < canvases.length; ++i) {
                            context.drawImage(canvases[i], 0, 0);
                        }
                        var dwpclPng = cloneElem[0].toDataURL();
                        return dwpclPng;
                    }
                };
                return ItemShowView;
            }(framework.templates.singleDateReport.ShowView));
        })(dateReport = templates.dateReport || (templates.dateReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
