var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../messageBox.ts"/>
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../xnyzbdef.ts"/>
var pluginEntry;
(function (pluginEntry) {
    pluginEntry.xnyzb = framework.basic.endpoint.lastId();
})(pluginEntry || (pluginEntry = {}));
var xnyzb;
(function (xnyzb) {
    var xnyzbEntry;
    (function (xnyzbEntry) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.parseHeader = function (header) {
                var node = null;
                if ("date" == header.type) {
                    node = Node.create({ name: header.name, align: TextAlign.Center, isReadOnly: false, isNumber: false, editType: "text", options: {
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
                    node = Node.create({ name: header.name, align: TextAlign.Center, isReadOnly: false, isNumber: false, editType: "text" });
                }
                else if ("hidden" == header.type) {
                    node = Node.create({ name: header.name, align: TextAlign.Center, isReadOnly: false, isNumber: false, editType: "text", hidden: true });
                }
                else if ("select" == header.type) {
                    node = Node.create({ name: header.name, align: TextAlign.Center, isReadOnly: false, isNumber: false, editType: "select", options: { value: header.options } });
                }
                else {
                    node = Node.create({ name: header.name, align: TextAlign.Center, isReadOnly: false });
                }
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
        var EntryView = (function (_super) {
            __extends(EntryView, _super);
            function EntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("xnyzbEntry.do", false);
                this.mAjaxSave = new Util.Ajax("xnyzbDoEntry.do", false);
                this.mAjaxSubmit = new Util.Ajax("../xnyzb/entry/submit.do", false);
            }
            EntryView.prototype.getId = function () {
                return pluginEntry.xnyzb;
            };
            EntryView.prototype.option = function () {
                return this.mOpt;
            };
            EntryView.prototype.pluginSave = function (dt, compType) {
                var _this = this;
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    compId: 903
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("保存 成功", function () {
                            _this.pluginUpdate(dt, compType);
                        });
                    }
                    else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            };
            EntryView.prototype.pluginSubmit = function (dt, compType) {
                var _this = this;
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 2; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 2] = submitData[i][j - 2].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j - 2]) {
                            Util.MessageBox.tip("有空内容 无法提交");
                            return;
                        }
                    }
                }
                this.mAjaxSubmit.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("提交 成功", function () {
                            _this.pluginUpdate(dt, compType);
                        });
                    }
                    else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            };
            EntryView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mCompType = compType;
                this.mAjaxUpdate.get({
                    dStart: this.mDStart,
                    dEnd: this.mDEnd
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            EntryView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            EntryView.prototype.init = function (opt) {
                var _this = this;
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, opt.title);
                this.mDStart = "2016-1-1";
                $("#dstart").val(2016 + "-" + 1 + "-" + 1);
                $("#dstart").datepicker({
                    //            numberOfMonths:1,//显示几个月
                    //            showButtonPanel:true,//是否显示按钮面板
                    dateFormat: 'yy-mm-dd',
                    //            clearText:"清除",//清除日期的按钮名称
                    //            closeText:"关闭",//关闭选择框的按钮名称
                    yearSuffix: '年',
                    showMonthAfterYear: true,
                    defaultDate: 2016 + "-" + 1 + "-" + 1,
                    //            minDate:'2011-03-05',//最小日期
                    maxDate: 2019 + "-" + 1 + "-" + 1,
                    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                    dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                    dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                    dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                    onSelect: function (selectedDate) {
                        var d = new Date(selectedDate);
                        _this.mDStart = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
                    }
                });
                $("#dEnd").val(2016 + "-" + 1 + "-" + 1);
                this.mDEnd = "2016-1-1";
                $("#dEnd").datepicker({
                    //            numberOfMonths:1,//显示几个月
                    //            showButtonPanel:true,//是否显示按钮面板
                    dateFormat: 'yy-mm-dd',
                    //            clearText:"清除",//清除日期的按钮名称
                    //            closeText:"关闭",//关闭选择框的按钮名称
                    yearSuffix: '年',
                    showMonthAfterYear: true,
                    defaultDate: 2016 + "-" + 1 + "-" + 1,
                    //            minDate:'2011-03-05',//最小日期
                    maxDate: 2019 + "-" + 1 + "-" + 1,
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
            EntryView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, this.mData.header);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                var jqTable = this.$(name);
                jqTable.jqGrid(this.mTableAssist.decorate({
                    datatype: "local",
                    data: this.mTableAssist.getDataWithId(this.mData.data),
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
                    rowNum: 20,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    pager: '#' + pagername,
                    viewrecords: true
                }));
            };
            EntryView.ins = new EntryView();
            return EntryView;
        })(framework.basic.EntryPluginView);
    })(xnyzbEntry = xnyzb.xnyzbEntry || (xnyzb.xnyzbEntry = {}));
})(xnyzb || (xnyzb = {}));
