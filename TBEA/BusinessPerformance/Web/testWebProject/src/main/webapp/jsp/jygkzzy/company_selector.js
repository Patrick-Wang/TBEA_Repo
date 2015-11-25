/// <reference path="../util.ts" />
var Util;
(function (Util) {
    var CompanySelectorZzy = (function () {
        function CompanySelectorZzy(mCtrlId, dwxxs, isSbdcy) {
            this.mCtrlId = mCtrlId;
            this.companyId = mCtrlId + "Id";
            this.tableId = mCtrlId + "Table";
            $("#" + this.mCtrlId).append('<table id="' + this.tableId + '" cellspacing="0" cellpadding="0"><tr></tr></table>');
            $("#" + this.tableId + " tr").append('<td>' +
                '<select id="' + this.companyId +
                '" style="width: 100px;"></select>' +
                '</td><td><div style="width:5px;"></div></td>');
            var Sel = $("#" + this.companyId);
            if (dwxxs.length >= 1) {
                for (var i = 0; i < dwxxs.length; i++) {
                    Sel.append('<option value= "' + dwxxs[i].id + '">' + dwxxs[i].name + '</option>');
                }
                if (isSbdcy) {
                    Sel.append('<option value= 900000>变压器产业</option>');
                }
                if (isSbdcy) {
                    Sel.append('<option value= 800000>线缆产业</option>');
                }
            }
            Sel.multiselect({
                multiple: false,
                header: false,
                minWidth: 150,
                height: '100%',
                // noneSelectedText: "请选择月份",
                selectedList: 1
            });
        }
        CompanySelectorZzy.prototype.getCompany = function () {
            return $("#" + this.companyId).val();
        };
        return CompanySelectorZzy;
    })();
    Util.CompanySelectorZzy = CompanySelectorZzy;
})(Util || (Util = {}));
