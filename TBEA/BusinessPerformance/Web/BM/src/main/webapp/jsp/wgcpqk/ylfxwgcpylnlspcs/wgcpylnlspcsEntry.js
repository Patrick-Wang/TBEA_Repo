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
    pluginEntry.wgcpylnlspcs = framework.basic.endpoint.lastId();
    pluginEntry.byq_cpfl_t1 = framework.basic.endpoint.lastId();
})(pluginEntry || (pluginEntry = {}));
var ylfxwgcpylnlspcs;
(function (ylfxwgcpylnlspcs) {
    var wgcpylnlspcsEntry;
    (function (wgcpylnlspcsEntry) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, readOnly, date) {
                var curDate = new Date(date);
                var month = curDate.getMonth() + 1;
                var year = curDate.getFullYear();
                var data = [];
                var node;
                var titleNodes = [];
                node = new JQTable.Node("产品", "wgcpylnlspcsentry_cp", true, TextAlign.Left);
                titleNodes.push(node);
                node = new JQTable.Node(year + "年" + month + "月", "wgcpylnlspcsentry_riqi", false, TextAlign.Center);
                node.append(new JQTable.Node("成本", "wgcpylnlspcsentry_cb_", false));
                node.append(new JQTable.Node("收入", "wgcpylnlspcsentry_sr_", false));
                titleNodes.push(node);
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            };
            return JQGridAssistantFactory;
        })();
        var EntryView = (function (_super) {
            __extends(EntryView, _super);
            function EntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("../wgcpylnlspcs/entry/update.do", false);
                this.mAjaxSave = new Util.Ajax("../wgcpylnlspcs/entry/save.do", false);
                this.mAjaxSubmit = new Util.Ajax("../wgcpylnlspcs/entry/submit.do", false);
            }
            EntryView.prototype.getId = function () {
                return pluginEntry.wgcpylnlspcs;
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
                    for (var j = 1; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 1] = submitData[i][j - 1].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType,
                    wgcpqkType: this.mWgcpqkType
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
                    companyId: compType,
                    wgcpqkType: this.mWgcpqkType
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
            EntryView.prototype.isSupported = function (compType) {
                if (this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH ||
                    this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_DYDJ ||
                    this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL ||
                    this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL_T1) {
                    if (compType == Util.CompanyType.SBGS ||
                        compType == Util.CompanyType.HBGS ||
                        compType == Util.CompanyType.XBC ||
                        compType == Util.CompanyType.TBGS) {
                        return true;
                    }
                }
                else {
                    if (compType == Util.CompanyType.LLGS ||
                        compType == Util.CompanyType.XLC ||
                        compType == Util.CompanyType.DLGS) {
                        return true;
                    }
                }
                return false;
            };
            EntryView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
                this.mCompType = compType;
                this.mAjaxUpdate.get({
                    date: date,
                    companyId: compType,
                    wgcpqkType: this.mWgcpqkType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData.data;
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
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(pluginEntry.byq_cpfl_t1, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "变压器未履约订单毛利水平测算-产品分类特殊1");
            };
            EntryView.prototype.onEvent = function (e) {
                if (e.road != undefined) {
                    switch (e.road[e.road.length - 1]) {
                        case pluginEntry.byq_cpfl_t1:
                            this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL_T1;
                            break;
                        default:
                            this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL_T1;
                    }
                }
                return _super.prototype.onEvent.call(this, e);
            };
            EntryView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, false, this.mDt);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
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
            EntryView.ins = new EntryView();
            return EntryView;
        })(framework.basic.EntryPluginView);
    })(wgcpylnlspcsEntry = ylfxwgcpylnlspcs.wgcpylnlspcsEntry || (ylfxwgcpylnlspcs.wgcpylnlspcsEntry = {}));
})(ylfxwgcpylnlspcs || (ylfxwgcpylnlspcs = {}));
