///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicEntry.ts"/>
///<reference path="../framework/templates/singleDateReport/entry.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var xtnyzb;
(function (xtnyzb) {
    framework.templates.singleDateReport.createInstance = function () {
        return new XtnyzbEntryView();
    };
    var XtnyzbEntryView = (function (_super) {
        __extends(XtnyzbEntryView, _super);
        function XtnyzbEntryView() {
            _super.apply(this, arguments);
        }
        XtnyzbEntryView.prototype.response = function () {
            return (this.resp);
        };
        XtnyzbEntryView.prototype.updateTable = function () {
            $("#" + this.opt.host)
                .attr("align", "");
            $("#" + this.opt.host).css("float", "left")
                .css("margin-top", "5px");
            var nameDjg = this.opt.host + "_jqgrid_uiframe_djg";
            var nameZbdc = this.opt.host + "_jqgrid_uiframe_zbc";
            this.mTableAssist = Util.createTable(nameDjg, this.response());
            this.mZbdcAssist = Util.createTable(nameZbdc, this.response().zbdc);
            var parent = $("#" + this.opt.host);
            parent.empty();
            parent.append("<table id='" + nameDjg + "'></table><div><table id='" + nameZbdc + "'></table></div>");
            var jqTable = $("#" + nameDjg);
            jqTable.jqGrid(this.mTableAssist.decorate({
                datatype: "local",
                data: this.mTableAssist.getDataWithId(this.response().data),
                multiselect: false,
                drag: false,
                resize: false,
                assistEditable: true,
                //autowidth : false,
                cellsubmit: 'clientArray',
                //editurl: 'clientArray',
                cellEdit: true,
                // height: data.length > 25 ? 550 : '100%',
                // width: titles.length * 200,
                rowNum: 1000,
                height: '100%',
                width: '100%',
                shrinkToFit: true,
                autoScroll: true,
                caption: "多晶硅产量（吨）"
            }));
            jqTable = $("#" + nameZbdc);
            jqTable.jqGrid(this.mZbdcAssist.decorate({
                datatype: "local",
                data: this.mZbdcAssist.getDataWithId(this.response().zbdc.data),
                multiselect: false,
                drag: false,
                resize: false,
                assistEditable: true,
                //autowidth : false,
                cellsubmit: 'clientArray',
                //editurl: 'clientArray',
                cellEdit: true,
                // height: data.length > 25 ? 550 : '100%',
                // width: titles.length * 200,
                rowNum: 1000,
                height: '100%',
                width: '100%',
                shrinkToFit: true,
                autoScroll: true,
                caption: "自备电厂电量（万kw·h）"
            }));
        };
        XtnyzbEntryView.prototype.onLoadSubmitData = function () {
            return { djg: this.mTableAssist.getAllData(), zbdc: this.mZbdcAssist.getAllData() };
        };
        return XtnyzbEntryView;
    }(framework.templates.singleDateReport.EntryView));
    var ins = new XtnyzbEntryView();
})(xtnyzb || (xtnyzb = {}));
