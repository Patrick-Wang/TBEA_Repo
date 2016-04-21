var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../messageBox.ts"/>
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
var pluginEntry;
(function (pluginEntry) {
    pluginEntry.dzclcb = framework.basic.endpoint.lastId();
})(pluginEntry || (pluginEntry = {}));
var dzwzgb;
(function (dzwzgb) {
    var dzclcbEntry;
    (function (dzclcbEntry) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, readOnly) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("期货盈亏（万元）", "ac", readOnly),
                    new JQTable.Node("市场现货月均价（元/吨）", "ada", readOnly),
                    new JQTable.Node("采购月均价（元/吨）（摊入当月期货盈亏）", "adb", readOnly),
                    new JQTable.Node("三项费用保本价（元/吨）", "adc", readOnly),
                    new JQTable.Node("目标利润倒算价（元/吨）", "ae", readOnly),
                    new JQTable.Node("采购量（吨）", "af", readOnly),
                    new JQTable.Node("期现货合计盈亏", "ag", readOnly)
                        .append(new JQTable.Node("指导价格按照保本价（万元）", "ah", readOnly))
                        .append(new JQTable.Node("指导价格按照目标利润价（万元）", "ai", readOnly))
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var DzclcbEntryView = (function (_super) {
            __extends(DzclcbEntryView, _super);
            function DzclcbEntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("dzclcb/entry/update.do", false);
                this.mAjaxSave = new Util.Ajax("dzclcb/entry/save.do", false);
                this.mAjaxSubmit = new Util.Ajax("dzclcb/entry/submit.do", false);
            }
            DzclcbEntryView.prototype.getId = function () {
                return pluginEntry.dzclcb;
            };
            DzclcbEntryView.prototype.option = function () {
                return this.mOpt;
            };
            DzclcbEntryView.prototype.pluginSave = function (dt, compType) {
                var _this = this;
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 1; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 1] = submitData[i][j - 1].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        _this.pluginUpdate(dt, compType);
                        Util.MessageBox.tip("保存 成功");
                    }
                    else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            };
            DzclcbEntryView.prototype.pluginSubmit = function (dt, compType) {
                var _this = this;
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 1; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 1] = submitData[i][j - 1].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j - 1]) {
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
                        _this.pluginUpdate(dt, compType);
                        Util.MessageBox.tip("提交 成功");
                    }
                    else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            };
            DzclcbEntryView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
                this.mCompType = compType;
                this.mAjaxUpdate.get({
                    date: date,
                    companyId: compType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            DzclcbEntryView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            DzclcbEntryView.prototype.init = function (opt) {
                framework.router.fromEp(this).to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_REGISTER, "大宗材料控成本");
                $.extend($.jgrid.edit, {
                    bSubmit: "确定"
                });
            };
            DzclcbEntryView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, false);
                var data = [];
                if (this.mCompType == Util.CompanyType.SBGS ||
                    this.mCompType == Util.CompanyType.HBGS ||
                    this.mCompType == Util.CompanyType.TBGS ||
                    this.mCompType == Util.CompanyType.XBC) {
                    data.push(["铜"].concat(this.mData[0]));
                }
                else {
                    data.push(["铜"].concat(this.mData[0]));
                    data.push(["铝"].concat(this.mData[1]));
                }
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                var jqTable = this.$(name);
                jqTable.jqGrid(this.mTableAssist.decorate({
                    datatype: "local",
                    data: this.mTableAssist.getData(this.mData),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    assistEditable: true,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    //editurl: 'clientArray',
                    cellEdit: true,
                    //height: data.length > 25 ? 550 : '100%',
                    // width: titles.length * 200,
                    rowNum: 20,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    viewrecords: true
                }));
            };
            DzclcbEntryView.ins = new DzclcbEntryView();
            return DzclcbEntryView;
        })(framework.basic.EntryPluginView);
    })(dzclcbEntry = dzwzgb.dzclcbEntry || (dzwzgb.dzclcbEntry = {}));
})(dzwzgb || (dzwzgb = {}));
