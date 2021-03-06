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
        var BasicFrameView = /** @class */ (function (_super) {
            __extends(BasicFrameView, _super);
            function BasicFrameView() {
                var _this = _super !== null && _super.apply(this, arguments) || this;
                _this.mNodesAll = [];
                return _this;
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
            BasicFrameView.prototype.createDate = function (opt) {
                //let className = "";
                //let fmt = "YYYY年MM月";
                //if (!this.mOpt.date.month){
                //    fmt = "YYYY年";
                //    className = "year";
                //}else{
                //    if (this.mOpt.date.day){
                //        fmt = "YYYY年MM月DD日";
                //        className = "day";
                //    }
                //}
                //
                //if (opt.format && opt.format.indexOf("&&") >= 0){
                //    className = "season";
                //}
                //
                //if (opt.format && opt.format.indexOf("$$") >= 0){
                //    className = "season-month";
                //}
                //
                //$("#" + this.mOpt.dt)
                //    .removeClass("year")
                //    .removeClass("day")
                //    .removeClass("season")
                //    .removeClass("season-month");
                //let minDate = Util.addYear(this.mOpt.date, -3);
                //minDate.month = 1;
                //
                //let oldOpt = $("#" + this.mOpt.dt).jeOpts();
                //if (!oldOpt){
                //    oldOpt = {
                //        skinCell: "jedatedeepgreen",
                //        format: fmt,
                //        isTime: false,
                //        isinitVal: true,
                //        isClear: false,
                //        isToday: false,
                //        minDate: Util.date2Str(minDate) + " 00:00:00",
                //        maxDate: Util.date2Str(this.mOpt.date) + " 00:00:00",
                //        insTrigger:true
                //    };
                //}else{
                //    oldOpt.insTrigger = false;
                //    oldOpt.format = fmt;
                //}
                //$.extend(oldOpt, opt);
                //
                //$("#" + this.mOpt.dt).empty().jeDate(oldOpt).removeCss("height")
                //    .removeCss("padding")
                //    .removeCss("margin-top")
                //    .addClass(className);
                return this.createInternalDate(this.mOpt.dt, this.mOpt.date, opt);
            };
            BasicFrameView.prototype.createInternalDate = function (id, date, opt) {
                var className = "";
                var fmt = "YYYY年MM月";
                if (!date.month) {
                    fmt = "YYYY年";
                    className = "year";
                }
                else {
                    if (date.day) {
                        fmt = "YYYY年MM月DD日";
                        className = "day";
                    }
                }
                if (opt.format && opt.format.indexOf("&&") >= 0) {
                    className = "season";
                }
                if (opt.format && opt.format.indexOf("$$") >= 0) {
                    className = "season-month";
                }
                $("#" + id)
                    .removeClass("year")
                    .removeClass("day")
                    .removeClass("season")
                    .removeClass("season-month");
                var minDate = Util.addYear(date, -3);
                minDate.month = 1;
                var oldOpt = $("#" + id).jeOpts();
                if (!oldOpt) {
                    oldOpt = {
                        skinCell: "jedatedeepgreen",
                        format: fmt,
                        isTime: false,
                        isinitVal: true,
                        isClear: false,
                        isToday: false,
                        minDate: Util.date2Str(minDate) + " 00:00:00",
                        maxDate: Util.date2Str(date) + " 00:00:00",
                        insTrigger: true
                    };
                }
                else {
                    oldOpt.insTrigger = false;
                    oldOpt.format = fmt;
                }
                $.extend(oldOpt, opt);
                $("#" + id).empty().jeDate(oldOpt).removeCss("height")
                    .removeCss("padding")
                    .removeCss("margin-top")
                    .addClass(className);
                return oldOpt;
            };
            BasicFrameView.prototype.init = function (opt) {
                var _this = this;
                this.mOpt = opt;
                this.createDate({});
                this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.comp, this.mOpt.comps);
                if (opt.comps.length == 1) {
                    this.mCompanySelector.hide();
                }
                this.mCompanySelector.change(function (selector, depth) {
                    _this.updateTypeSelector();
                    _this.adjustHeader();
                    router.to(_this.mCurrentPlugin).send(basic.FrameEvent.FE_ADJUST_SZIE);
                });
                $(window).resize(function () {
                    _this.adjustHeader();
                    router.to(_this.mCurrentPlugin).send(basic.FrameEvent.FE_ADJUST_SZIE);
                });
                this.updateTypeSelector();
                this.adjustHeader();
                router.to(this.mCurrentPlugin).send(basic.FrameEvent.FE_ADJUST_SZIE);
                this.updateUI();
            };
            BasicFrameView.prototype.adjustHeader = function () {
                $("#headerHost").removeCss("width");
                $(".page-header").removeClass("page-header-double");
                if ($("#headerHost").height() > 40) {
                    $(".page-header").addClass("page-header-double");
                    $("#headerHost").css("width", $("#sels").width() + "px");
                }
            };
            BasicFrameView.prototype.getDate = function () {
                var curDate = $("#" + this.mOpt.dt).getDate();
                return {
                    year: curDate.getFullYear(),
                    month: curDate.getMonth() + 1,
                    day: curDate.getDate()
                };
            };
            BasicFrameView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case basic.FrameEvent.FE_UPDATE:
                        this.updateUI();
                        break;
                    case basic.FrameEvent.FE_ADJUST_HEADER:
                        this.adjustHeader();
                        break;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            BasicFrameView.prototype.updateTypeSelector = function (width) {
                if (width === void 0) { width = 285; }
                var type = this.mCompanySelector != undefined ?
                    this.mCompanySelector.getCompany() : undefined;
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
                }
                return typeChange;
            };
            BasicFrameView.prototype.plugin = function (node) {
                return node.getData().plugin;
            };
            BasicFrameView.prototype.updateUI = function () {
                var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
                var dt = this.getDate();
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
                var unit = router.to(this.mCurrentPlugin).send(basic.FrameEvent.FE_GETUNIT);
                if (undefined != unit) {
                    $("#unit").text(unit);
                }
                else {
                    $("#unit").text("");
                }
                router.to(this.mCurrentPlugin).send(basic.FrameEvent.FE_UPDATE, {
                    date: dt,
                    compType: this.mCurrentComp
                });
            };
            return BasicFrameView;
        }(basic.FrameView));
        basic.BasicFrameView = BasicFrameView;
    })(basic = framework.basic || (framework.basic = {}));
})(framework || (framework = {}));
