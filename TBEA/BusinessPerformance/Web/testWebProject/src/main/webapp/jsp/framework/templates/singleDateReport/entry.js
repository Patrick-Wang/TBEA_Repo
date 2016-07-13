var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
var framework;
(function (framework) {
    var basic;
    (function (basic) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, headers) {
                var nodes = [];
                for (var i = 0; i < headers.length; ++i) {
                    var node = Util.parseHeader(headers[i]);
                    if (null != node) {
                        nodes.push(node);
                    }
                }
                return new JQTable.JQGridAssistant(nodes, gridName);
            };
            return JQGridAssistantFactory;
        })();
        var EntryView = (function (_super) {
            __extends(EntryView, _super);
            function EntryView() {
                _super.apply(this, arguments);
            }
            EntryView.prototype.getId = function () {
                return basic.endpoint.FRAME_ID;
            };
            EntryView.prototype.onInitialize = function (opt) {
                this.opt = opt;
                this.dateSelect = new Util.DateSelector({
                    year: opt.date.year - 3,
                    month: opt.date.month,
                    day: opt.date.day
                }, opt.date, opt.dtId);
                this.mAjaxUpdate = new Util.Ajax(opt.updateUrl, false);
                this.mAjaxSubmit = new Util.Ajax(opt.submitUrl, false);
            };
            EntryView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case basic.FrameEvent.FE_UPDATE:
                        this.update(this.dateSelect.getDate());
                        break;
                }
                return true;
            };
            EntryView.prototype.getDate = function (date) {
                return "" + (date.year + "-" + date.month == undefined ? 1 : date.month + "-" + date.day == undefined ? 1 : date.day);
            };
            EntryView.prototype.update = function (date) {
                var _this = this;
                this.mAjaxUpdate.get({
                    date: this.getDate(date)
                })
                    .then(function (jsonData) {
                    _this.resp = jsonData;
                    _this.refresh();
                });
            };
            EntryView.prototype.refresh = function () {
                var name = this.opt.host + "_jqgrid_uiframe";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, this.resp.header);
                var parent = $(this.opt.host);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                var jqTable = $(name);
                jqTable.jqGrid(this.mTableAssist.decorate({
                    datatype: "local",
                    data: this.mTableAssist.getDataWithId(this.resp.data),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    assistEditable: false,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    //editurl: 'clientArray',
                    cellEdit: true,
                    // height: data.length > 25 ? 550 : '100%',
                    // width: titles.length * 200,
                    rowNum: 1000,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true
                }));
            };
            return EntryView;
        })(basic.BasicEndpoint);
        basic.EntryView = EntryView;
    })(basic = framework.basic || (framework.basic = {}));
})(framework || (framework = {}));
