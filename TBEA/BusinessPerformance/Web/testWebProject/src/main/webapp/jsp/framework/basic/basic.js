var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../unitedSelector.ts"/>
///<reference path="../../messageBox.ts"/>
///<reference path="../../companySelector.ts"/>
///<reference path="basicdef.ts"/>
///<reference path="../route/route.ts"/>
var framework;
(function (framework) {
    var basic;
    (function (basic) {
        var router = framework.router;
        var BasicFrameView = (function (_super) {
            __extends(BasicFrameView, _super);
            function BasicFrameView() {
                _super.apply(this, arguments);
                this.mNodesAll = [];
            }
            BasicFrameView.prototype.register = function (name, plugin) {
                var data = { id: this.mNodesAll.length, value: name, plugin: plugin };
                var node = new Util.DataNode(data);
                this.mNodesAll.push(node);
            };
            BasicFrameView.prototype.unregister = function (name) {
                var nod;
                for (var i = 0; i < this.mNodesAll.length; ++i) {
                    this.mNodesAll[i].accept({
                        visit: function (node) {
                            if (node.getData().value == name) {
                                nod = node;
                                return true;
                            }
                            return false;
                        }
                    });
                    if (nod != undefined) {
                        break;
                    }
                }
                return this.plugin(nod);
            };
            BasicFrameView.prototype.init = function (opt) {
                var _this = this;
                this.mOpt = opt;
                this.mDtSec = new Util.DateSelector({ year: this.mOpt.date.year - 3, month: 1 }, {
                    year: this.mOpt.date.year,
                    month: this.mOpt.date.month
                }, this.mOpt.dt);
                this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.comp, this.mOpt.comps);
                if (opt.comps.length == 1) {
                    this.mCompanySelector.hide();
                }
                this.mCompanySelector.change(function (selector, depth) {
                    _this.updateTypeSelector();
                });
                this.updateTypeSelector();
                this.updateUI();
            };
            BasicFrameView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case basic.FrameEvent.FE_UPDATE:
                        this.updateUI();
                        break;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            BasicFrameView.prototype.updateTypeSelector = function (width) {
                if (width === void 0) { width = 285; }
                var type = this.mCompanySelector.getCompany();
                var nodes = [];
                for (var i = 0; i < this.mNodesAll.length; ++i) {
                    if (router.to(this.plugin(this.mNodesAll[i])).send(basic.FrameEvent.FE_IS_COMPANY_SUPPORTED, type)) {
                        nodes.push(this.mNodesAll[i]);
                    }
                }
                var typeChange = false;
                var curNodes = [];
                if (this.mItemSelector != undefined) {
                    curNodes = this.mItemSelector.getTopNodes();
                }
                if (nodes.length != curNodes.length) {
                    typeChange = true;
                }
                else {
                    for (var i_1 = 0; i_1 < nodes.length; ++i_1) {
                        if (this.plugin(nodes[i_1]) != this.plugin(curNodes[i_1])) {
                            typeChange = true;
                            break;
                        }
                    }
                }
                if (typeChange) {
                    this.mItemSelector = new Util.UnitedSelector(nodes, this.mOpt.type);
                    if (nodes.length == 1) {
                        this.mItemSelector.hide();
                    }
                    $("#" + this.mOpt.type + " select")
                        .multiselect({
                        multiple: false,
                        header: false,
                        minWidth: width,
                        height: '100%',
                        // noneSelectedText: "请选择月份",
                        selectedList: 1
                    })
                        .css("padding", "2px 0 2px 4px")
                        .css("text-align", "left")
                        .css("font-size", "12px");
                }
                return typeChange;
            };
            BasicFrameView.prototype.plugin = function (node) {
                return node.getData().plugin;
            };
            BasicFrameView.prototype.updateUI = function () {
                var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
                var dt = this.mDtSec.getDate();
                if (dt.month == undefined) {
                    dt.month = 1;
                }
                if (dt.day == undefined) {
                    dt.day = 1;
                }
                this.mCurrentPlugin = this.plugin(node);
                router.broadcast(basic.FrameEvent.FE_HIDE);
                this.mCurrentComp = this.mCompanySelector.getCompany();
                this.mCurrentDate = dt;
                router.to(this.mCurrentPlugin).send(basic.FrameEvent.FE_SHOW);
                if (null != this.mCurrentComp) {
                    $("#headertitle")[0].innerHTML = this.mCompanySelector.getCompanyName() + " " + node.getData().value;
                }
                else {
                    $("#headertitle")[0].innerHTML = node.getData().value;
                }
                router.to(this.mCurrentPlugin).send(basic.FrameEvent.FE_UPDATE, {
                    date: dt,
                    compType: this.mCurrentComp
                });
            };
            return BasicFrameView;
        })(basic.FrameView);
        basic.BasicFrameView = BasicFrameView;
    })(basic = framework.basic || (framework.basic = {}));
})(framework || (framework = {}));
