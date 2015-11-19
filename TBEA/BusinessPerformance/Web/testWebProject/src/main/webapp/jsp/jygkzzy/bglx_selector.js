/// <reference path="../util.ts" />
var Util;
(function (Util) {
    var BglxSelector = (function () {
        function BglxSelector(divId, bglxs, curbglx, view) {
            var _this = this;
            this.mCtrlId = divId + "_bglx";
            this.mView = view;
            $("#" + divId).append('<table id="' + this.mCtrlId + '" cellspacing="0" cellpadding="0"><tr></tr></table>');
            $("#" + this.mCtrlId + " tr").append('<td>' +
                '<select id="' + this.mCtrlId + 'sel"' +
                'style="width: 200px;"></select>' +
                '</td><td><div style="width:5px;"></div></td>');
            var bglxSel = $("#" + this.mCtrlId + "sel");
            for (var i = 0; i < bglxs.length; i++) {
                if (curbglx == bglxs[i].bglxid) {
                    bglxSel.append('<option value= "' + bglxs[i].bglxid + '" selected="selected">' + bglxs[i].wvtype + '</option>');
                }
                else {
                    bglxSel.append('<option value= "' + bglxs[i].bglxid + '">' + bglxs[i].wvtype + '</option>');
                }
            }
            bglxSel.change(function () {
                var changebglx = bglxSel.children('option:selected').val();
                $("#bglx").val(changebglx);
                _this.mView.updateEntry();
            });
            bglxSel.multiselect({
                multiple: false,
                header: false,
                minWidth: 150,
                height: '100%',
                // noneSelectedText: "请选择月份",
                selectedList: 1
            });
            var changebglx = bglxSel.children('option:selected').val();
            $("#bglx").val(changebglx);
            this.mView.updateEntry();
        }
        return BglxSelector;
    })();
    Util.BglxSelector = BglxSelector;
    var BglxViewSelector = (function () {
        function BglxViewSelector(divId, curbglx) {
            var _this = this;
            this.mCtrlId = divId + "_bglx";
            $("#" + divId).append('<table id="' + this.mCtrlId + '" cellspacing="0" cellpadding="0"><tr></tr></table>');
            $("#" + this.mCtrlId + " tr").append('<td>' +
                '<select id="' + this.mCtrlId + 'sel"' +
                'style="width: 220px;"></select>' +
                '</td><td><div style="width:5px;"></div></td>');
            var bglxSel = $("#" + this.mCtrlId + "sel");
            bglxSel.append('<option value= "20001" ' + (curbglx == '20001' ? 'selected="selected"' : '') + '>当期订单毛利情况</option>');
            bglxSel.append('<option value= "20002" ' + (curbglx == '20002' ? 'selected="selected"' : '') + '>后期履约订单质量（变压器）</option>');
            bglxSel.append('<option value= "20003" ' + (curbglx == '20003' ? 'selected="selected"' : '') + '>后期履约订单质量（线缆）</option>');
            bglxSel.append('<option value= "20004" ' + (curbglx == '20004' ? 'selected="selected"' : '') + '>降控指标完成情况 </option>');
            bglxSel.append('<option value= "20005" ' + (curbglx == '20005' ? 'selected="selected"' : '') + '>技术降本（变压器）</option>');
            bglxSel.append('<option value= "20006" ' + (curbglx == '20006' ? 'selected="selected"' : '') + '>技术降本（线缆）</option>');
            bglxSel.append('<option value= "20007" ' + (curbglx == '20007' ? 'selected="selected"' : '') + '>采购降本</option>');
            bglxSel.append('<option value= "20008" ' + (curbglx == '20008' ? 'selected="selected"' : '') + '>生产降本</option>');
            bglxSel.append('<option value= "20009" ' + (curbglx == '20009' ? 'selected="selected"' : '') + '>整体能耗情况（变压器）</option>');
            bglxSel.append('<option value= "20010" ' + (curbglx == '20010' ? 'selected="selected"' : '') + '>整体能耗情况（线缆）</option>');
            bglxSel.append('<option value= "20011" ' + (curbglx == '20011' ? 'selected="selected"' : '') + '>销售降本</option>');
            bglxSel.append('<option value= "20012" ' + (curbglx == '20012' ? 'selected="selected"' : '') + '>产出完成情况（变压器）</option>');
            bglxSel.append('<option value= "20013" ' + (curbglx == '20013' ? 'selected="selected"' : '') + '>产出完成情况（线缆）</option>');
            bglxSel.append('<option value= "20014" ' + (curbglx == '20014' ? 'selected="selected"' : '') + '>产出完成情况（线缆）工时</option>');
            bglxSel.append('<option value= "20015" ' + (curbglx == '20015' ? 'selected="selected"' : '') + '>可供履约订单储备情况（变压器）</option>');
            bglxSel.append('<option value= "20016" ' + (curbglx == '20016' ? 'selected="selected"' : '') + '>可供履约订单储备情况（线缆）</option>');
            bglxSel.append('<option value= "20017" ' + (curbglx == '20017' ? 'selected="selected"' : '') + '>存货结构及内涵 </option>');
            bglxSel.append('<option value= "20018" ' + (curbglx == '20018' ? 'selected="selected"' : '') + '>账龄结构 </option>');
            bglxSel.append('<option value= "20019" ' + (curbglx == '20019' ? 'selected="selected"' : '') + '>原材料存货</option>');
            bglxSel.append('<option value= "20020" ' + (curbglx == '20020' ? 'selected="selected"' : '') + '>三项费用管控</option>');
            bglxSel.append('<option value= "20021" ' + (curbglx == '20021' ? 'selected="selected"' : '') + '>能耗情况</option>');
            bglxSel.change(function () {
                var changebglx = bglxSel.children('option:selected').val();
                _this.tobglxLocation(changebglx);
            });
            bglxSel.multiselect({
                multiple: false,
                header: false,
                minWidth: 150,
                height: '100%',
                // noneSelectedText: "请选择月份",
                selectedList: 1
            });
        }
        BglxViewSelector.prototype.tobglxLocation = function (bglx) {
            if (bglx == '20001') {
                window.location.href = '../fxcpylspdqddmlqk/openview.do';
            }
            else if (bglx == '20002') {
                window.location.href = '../fxcpylsphqlyddzl/openviewbyq.do';
            }
            else if (bglx == '20003') {
                window.location.href = '../fxcpylsphqlyddzl/openviewxl.do';
            }
            else if (bglx == '20004') {
                window.location.href = '../fxjkcbzbwcqk/openview.do';
            }
            else if (bglx == '20005') {
                window.location.href = '../fxjkcbjsjb/openviewbyq.do';
            }
            else if (bglx == '20006') {
                window.location.href = '../fxjkcbjsjb/openviewxl.do';
            }
            else if (bglx == '20007') {
                window.location.href = '../fxjkcbcgjb/openview.do';
            }
            else if (bglx == '20008') {
                window.location.href = '../fxjkcbscjb/openview.do';
            }
            else if (bglx == '20009') {
                window.location.href = '../fxjkcbztnhqk/openviewbyq.do';
            }
            else if (bglx == '20010') {
                window.location.href = '../fxjkcbztnhqk/openviewxl.do';
            }
            else if (bglx == '20011') {
                window.location.href = '../fxjkcbxsjb/openview.do';
            }
            else if (bglx == '20012') {
                window.location.href = '../ccccwcqk/openviewbyq.do';
            }
            else if (bglx == '20013') {
                window.location.href = '../ccccwcqk/openviewxl.do';
            }
            else if (bglx == '20014') {
                window.location.href = '../ccccwcqkgs/openview.do';
            }
            else if (bglx == '20015') {
                window.location.href = '../kglyddcbqk/openview.do';
            }
            else if (bglx == '20016') {
                window.location.href = '../kglyddcbqk/openviewxl.do';
            }
            else if (bglx == '20017') {
                window.location.href = '../chjgjnh/openview.do';
            }
            else if (bglx == '20018') {
                window.location.href = '../zljj/openview.do';
            }
            else if (bglx == '20019') {
                window.location.href = '../yclch/openview.do';
            }
            else if (bglx == '20020') {
                window.location.href = '../fxsxfykz/openview.do';
            }
            else if (bglx == '20021') {
                window.location.href = '../fxnhqk/openview.do';
            }
        };
        return BglxViewSelector;
    })();
    Util.BglxViewSelector = BglxViewSelector;
})(Util || (Util = {}));
