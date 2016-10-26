/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../nwbzlqkdef.ts"/>
///<reference path="../nwbzlqk.ts"/>
///<reference path="../../jqgrid/jqgrid.d.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.byqnbzlwttjqk = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var nwbzlqk;
(function (nwbzlqk) {
    var byqnbzlwttjqk;
    (function (byqnbzlwttjqk) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                var tbs = ["公司名称", "问题发生时间", "产品类型", "生产号", "产品型号", "一级问题类型",
                    "二级问题类型", "原材料质量故障现象", "问题详细描述", "原材料问题发生阶段",
                    "原材料数量", "计量单位", "供应商名称", "问题所在工序", "责任部门", "原材料问题处理措施",
                    "现场处理措施", "现场处理结果", "原因分析", "考核情况", "填报人"];
                var nodes = [];
                for (var i = 0; i < tbs.length; ++i) {
                    if (i < 1) {
                        nodes.push(Node.create({ name: tbs[i], align: TextAlign.Center, hidden: true }));
                    }
                    else {
                        nodes.push(Node.create({ name: tbs[i], align: TextAlign.Center }));
                    }
                }
                return new JQTable.JQGridAssistant(nodes, gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../byqnbzlwttjqk/update.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.byqnbzlwttjqk;
            };
            ShowView.prototype.isSupported = function (compType) {
                return compType == Util.CompanyType.SBGS || compType == Util.CompanyType.HBGS
                    || compType == Util.CompanyType.XBC;
            };
            ShowView.prototype.onSaveComment = function (data) {
            };
            ShowView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case nwbzlqk.Event.ZLFE_IS_YDJD_SUPPORTED:
                        return false;
                    case nwbzlqk.Event.ZLFE_IS_COMPANY_SUPPORTED:
                        return true;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../byqnbzlwttjqk/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: compType,
                    ydjd: this.mYdjdType
                });
            };
            ShowView.prototype.option = function () {
                return this.mOpt;
            };
            ShowView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
                this.mCompType = compType;
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(nwbzlqk.Event.ZLFE_COMMENT_DENY);
                //this.mCommentGet.get({condition:Util.Ajax.toUrlParam({
                //    url : this.mAjax.baseUrl(),
                //    date: date,
                //    companyId:compType,
                //    ydjd:this.mYdjdType
                //}),compId:compType}).then((jsonData:any)=>{
                //    if (jsonData.deny == "deny"){
                //        framework.router
                //            .fromEp(this)
                //            .to(framework.basic.endpoint.FRAME_ID)
                //            .send(Event.ZLFE_COMMENT_DENY);
                //    }else {
                //        framework.router
                //            .fromEp(this)
                //            .to(framework.basic.endpoint.FRAME_ID)
                //            .send(Event.ZLFE_COMMENT_UPDATED, jsonData.comment);
                //    }
                //});
                this.mAjax.get({
                    date: date,
                    companyId: compType,
                    pageType: pageType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            ShowView.prototype.toCtVal = function (val) {
                var index = val.lastIndexOf('%');
                if (index >= 0) {
                    return val.substring(0, index);
                }
                return val == '--' ? 0 : val;
            };
            ShowView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "内部质量问题统计情况");
            };
            ShowView.prototype.getMonth = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                return month;
            };
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist;
                tableAssist = JQGridAssistantFactory.createTable(name);
                var pagername = name + "pager";
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                tableAssist.mergeTitle();
                this.$(name).jqGrid(tableAssist.decorate({
                    datatype: "local",
                    data: tableAssist.getData(this.mData.tjjg),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: this.mData.tjjg.length > 20 ? 20 * 22 : '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: this.mData.tjjg.length + 10,
                    viewrecords: true,
                    pager: '#' + pagername
                }));
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(nwbzlqk.ZlPluginView);
    })(byqnbzlwttjqk = nwbzlqk.byqnbzlwttjqk || (nwbzlqk.byqnbzlwttjqk = {}));
})(nwbzlqk || (nwbzlqk = {}));
