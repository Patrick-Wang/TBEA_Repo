/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../xnyzbdef.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.xnyzb = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var xnyzb;
(function (xnyzb_1) {
    var xnyzb;
    (function (xnyzb) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.parseHeader = function (header) {
                var node = Node.create({ name: header.name, align: TextAlign.Center });
                if (header.sub != undefined) {
                    for (var i = 0; i < header.sub.length; ++i) {
                        node.append(JQGridAssistantFactory.parseHeader(header.sub[i]));
                    }
                }
                return node;
            };
            JQGridAssistantFactory.createTable = function (gridName, headers) {
                var nodes = [];
                for (var i = 0; i < headers.length; ++i) {
                    nodes.push(JQGridAssistantFactory.parseHeader(headers[i]));
                }
                return new JQTable.JQGridAssistant(nodes, gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("xnyzb.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.xnyzb;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "xnyzbExport.do?" + Util.Ajax.toUrlParam({
                    dStart: this.mDStart,
                    dEnd: this.mDEnd
                });
            };
            ShowView.prototype.option = function () {
                return this.mOpt;
            };
            ShowView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
                this.mCompType = compType;
                this.mAjax.get({
                    dStart: this.mDStart,
                    dEnd: this.mDEnd
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            ShowView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            ShowView.prototype.init = function (opt) {
                var _this = this;
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "新能源周报");
                this.mDStart = "2016-1-1";
                $("#dstart").val(2016 + "/" + 1 + "/" + 1);
                $("#dstart").datepicker({
                    //            numberOfMonths:1,//显示几个月
                    //            showButtonPanel:true,//是否显示按钮面板
                    dateFormat: 'yy/mm/dd',
                    //            clearText:"清除",//清除日期的按钮名称
                    //            closeText:"关闭",//关闭选择框的按钮名称
                    yearSuffix: '年',
                    showMonthAfterYear: true,
                    defaultDate: 2016 + "/" + 1 + "/" + 1,
                    //            minDate:'2011-03-05',//最小日期
                    maxDate: 2019 + "/" + 1 + "/" + 1,
                    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                    dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                    dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                    dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                    onSelect: function (selectedDate) {
                        var d = new Date(selectedDate);
                        _this.mDStart = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
                    }
                });
                $("#dEnd").val(2016 + "/" + 1 + "/" + 1);
                this.mDEnd = "2016-1-1";
                $("#dEnd").datepicker({
                    //            numberOfMonths:1,//显示几个月
                    //            showButtonPanel:true,//是否显示按钮面板
                    dateFormat: 'yy/mm/dd',
                    //            clearText:"清除",//清除日期的按钮名称
                    //            closeText:"关闭",//关闭选择框的按钮名称
                    yearSuffix: '年',
                    showMonthAfterYear: true,
                    defaultDate: 2016 + "/" + 1 + "/" + 1,
                    //            minDate:'2011-03-05',//最小日期
                    maxDate: 2019 + "/" + 1 + "/" + 1,
                    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                    dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                    dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                    dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                    onSelect: function (selectedDate) {
                        var d = new Date(selectedDate);
                        _this.mDEnd = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
                    }
                });
                $("#ui-datepicker-div").css('font-size', '0.8em'); //改变大小;
            };
            ShowView.prototype.getMonth = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                return month;
            };
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist = JQGridAssistantFactory.createTable(name, this.mData.header);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                tableAssist.mergeRow(0);
                tableAssist.mergeRow(1);
                tableAssist.mergeRow(2);
                tableAssist.mergeRow(3);
                tableAssist.mergeColum(3);
                this.$(name).jqGrid(tableAssist.decorate({
                    datatype: "local",
                    data: tableAssist.getDataWithId(this.mData.data),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 1000,
                    viewrecords: true
                }));
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(framework.basic.ShowPluginView);
    })(xnyzb = xnyzb_1.xnyzb || (xnyzb_1.xnyzb = {}));
})(xnyzb || (xnyzb = {}));
