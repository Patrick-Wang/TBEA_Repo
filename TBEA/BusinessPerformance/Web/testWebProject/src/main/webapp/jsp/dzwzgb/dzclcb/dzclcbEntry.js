var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var pluginEntry;
(function (pluginEntry) {
    pluginEntry.dzclcb = framework.basic.endpoint.lastId();
})(pluginEntry || (pluginEntry = {}));
var dzwzgb;
(function (dzwzgb) {
    var dzclcbEntry;
    (function (dzclcbEntry) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, readOnly) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("材料", "cl", true, TextAlign.Center),
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
        }());
        var EntryView = (function (_super) {
            __extends(EntryView, _super);
            function EntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("dzclcb/entry/update.do", false);
                this.mAjaxSave = new Util.Ajax("dzclcb/entry/save.do", false);
                this.mAjaxSubmit = new Util.Ajax("dzclcb/entry/submit.do", false);
            }
            EntryView.prototype.getId = function () {
                return pluginEntry.dzclcb;
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
                    for (var j = 2; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 2] = submitData[i][j - 2].replace(new RegExp(' ', 'g'), '');
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
                        _this.pluginUpdate(dt, compType);
                        Util.MessageBox.tip("提交 成功");
                    }
                    else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            };
            EntryView.prototype.pluginUpdate = function (date, compType) {
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
            EntryView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            EntryView.prototype.init = function (opt) {
                framework.router.fromEp(this).to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_REGISTER, "大宗材料控成本");
                $.extend($.jgrid.edit, {
                    bSubmit: "确定"
                });
            };
            EntryView.prototype.updateTable = function () {
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
                    data: this.mTableAssist.getData(data),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    assistEditable: true,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    rowNum: 20,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    viewrecords: true,
                }));
            };
            EntryView.ins = new EntryView();
            return EntryView;
        }(framework.basic.EntryPluginView));
    })(dzclcbEntry = dzwzgb.dzclcbEntry || (dzwzgb.dzclcbEntry = {}));
})(dzwzgb || (dzwzgb = {}));
