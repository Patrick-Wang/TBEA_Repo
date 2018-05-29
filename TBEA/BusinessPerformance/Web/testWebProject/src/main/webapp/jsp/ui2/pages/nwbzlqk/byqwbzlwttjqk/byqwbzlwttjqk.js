/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../nwbzlqkdef.ts"/>
///<reference path="../nwbzlqk.ts"/>
///<reference path="../../jqgrid/jqgrid.d.ts"/>
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var plugin;
(function (plugin) {
    plugin.byqwbzlwttjqk = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var nwbzlqk;
(function (nwbzlqk) {
    var byqwbzlwttjqk;
    (function (byqwbzlwttjqk) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = /** @class */ (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                var tbs = ["公司名称", "问题发生时间", "产品类型", "生产号", "产品型号",
                    "一级问题类型", "二级问题类型", "质量故障现象", "问题详细描述",
                    "原材料数量", "计量单", "供应商名称", "责任部门", "填报人", "产品发货日期",
                    "故障主体名称", "原材料问题处理措施", "现场处理措施", "现场处理结果",
                    "用户单位名称", "本单位现场售后人员", "本单位现场售后人员电话"
                ];
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
        }());
        var ShowView = /** @class */ (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                var _this = _super !== null && _super.apply(this, arguments) || this;
                _this.mAjax = new Util.Ajax("/BusinessManagement/byqwbzlwttjqk/update.do", false);
                return _this;
                //private updateTable():void {
                //    var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                //    var tableAssist:JQTable.JQGridAssistant;
                //    tableAssist = JQGridAssistantFactory.createTable(name);
                //
                //    let pagername = name + "pager"
                //    var parent = this.$(this.option().tb);
                //    parent.empty();
                //    parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                //    tableAssist.mergeTitle();
                //    this.$(name).jqGrid(
                //        tableAssist.decorate({
                //            datatype: "local",
                //            data: tableAssist.getData(this.mData.tjjg),
                //            multiselect: false,
                //            drag: false,
                //            resize: false,
                //            height: this.mData.tjjg.length > 20 ? 20 * 22 : '100%',
                //            width: 1300,
                //            shrinkToFit: true,
                //            autoScroll: true,
                //            rowNum: this.mData.tjjg.length + 10,
                //            viewrecords : true,
                //            pager:'#' + pagername,
                //        }));
                //}
            }
            ShowView.prototype.onSaveComment = function (data) {
            };
            ShowView.prototype.getId = function () {
                return plugin.byqwbzlwttjqk;
            };
            ShowView.prototype.isSupported = function (compType) {
                return compType == Util.CompanyType.SBGS || compType == Util.CompanyType.HBGS
                    || compType == Util.CompanyType.XBC;
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
                return "/BusinessManagement/byqwbzlwttjqk/export.do?" + Util.Ajax.toUrlParam({
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
                    //ydjd:this.mYdjdType,
                    //all: this.mCompType == Util.CompanyType.BYQCY
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
                this.adjustSize();
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "外部质量问题统计情况");
            };
            ShowView.prototype.adjustSize = function () {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                //this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                //this.updateEchart();
            };
            ShowView.prototype.createJqassist = function () {
                var pagername = this.jqgridName() + "pager";
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table><div id='" + pagername + "'></div>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
                this.tableAssist.mergeTitle();
                return this.tableAssist;
            };
            ShowView.prototype.updateTable = function () {
                this.createJqassist();
                this.tableAssist.create({
                    data: this.mData.tjjg,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 15,
                    autoScroll: true,
                    pager: '#' + this.jqgridName() + "pager",
                });
            };
            ShowView.ins = new ShowView();
            return ShowView;
        }(nwbzlqk.ZlPluginView));
    })(byqwbzlwttjqk = nwbzlqk.byqwbzlwttjqk || (nwbzlqk.byqwbzlwttjqk = {}));
})(nwbzlqk || (nwbzlqk = {}));
