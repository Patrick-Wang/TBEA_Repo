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
var framework;
(function (framework) {
    var templates;
    (function (templates) {
        var dateReport;
        (function (dateReport) {
            function create() {
                return new ItemShowView();
            }
            dateReport.create = create;
            var ItemShowView = /** @class */ (function (_super) {
                __extends(ItemShowView, _super);
                function ItemShowView() {
                    var _this = _super !== null && _super.apply(this, arguments) || this;
                    _this.doubleHeader = false;
                    _this.firstUpdate = true;
                    return _this;
                }
                ItemShowView.prototype.onInitialize = function (opt) {
                    var _this = this;
                    this.unitedSelector = new Util.UnitedSelector(opt.itemNodes, opt.itemId);
                    this.unitedSelector.change(function () {
                        _this.adjustHeader();
                    });
                    var itemHidden = false;
                    if (opt.itemNodes.length == 1) {
                        $("#" + opt.itemId).hide();
                        itemHidden = true;
                    }
                    var item2Hidden = false;
                    if (opt.itemNodes2 != undefined) {
                        this.unitedSelector2 = new Util.UnitedSelector(opt.itemNodes2, opt.itemId2);
                        this.unitedSelector.change(function () {
                            _this.adjustHeader();
                        });
                        if (opt.itemNodes2.length == 1) {
                            $("#" + opt.itemId2).hide();
                            item2Hidden = true;
                        }
                    }
                    if (opt.searchlist == 'true') {
                        $("#" + opt.itemId + " select").select2({
                            language: "zh-CN"
                        });
                    }
                    if (item2Hidden && itemHidden && $("#" + opt.dtId).hasClass("hidden")) {
                        $("#sels").hide();
                        $("#" + opt.dtId).hide();
                    }
                    $(window).resize(function () {
                        _this.adjustHeader();
                    });
                    _super.prototype.onInitialize.call(this, opt);
                };
                ItemShowView.prototype.getParams = function (date) {
                    return {
                        date: this.getDate(date),
                        item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id,
                        item2: this.unitedSelector2 ? this.unitedSelector2.getDataNode(this.unitedSelector2.getPath()).data.id : undefined
                    };
                };
                ItemShowView.prototype.adjustHeader = function () {
                    $("#headerHost").removeCss("width");
                    if ($("#headerHost").height() > 40) {
                        $(".page-header").addClass("page-header-double");
                        $("#headerHost").css("width", $("#sels").width() + "px");
                        if (!this.doubleHeader) {
                            this.doubleHeader = true;
                            return true;
                        }
                    }
                    else {
                        $(".page-header").removeClass("page-header-double");
                        if (this.doubleHeader) {
                            this.doubleHeader = false;
                            return true;
                        }
                    }
                    return false;
                };
                ItemShowView.prototype.update = function (date) {
                    var _this = this;
                    if (!this.firstUpdate) {
                        this.mAjaxUpdate.get(this.getParams(date))
                            .then(function (jsonData) {
                            _this.resp = jsonData;
                            $("#exportForm")[0].action = encodeURI(_this.opt.exportUrl + "?" + Util.Ajax.toUrlParam(jsonData));
                            $("#exportForm")[0].submit();
                        });
                    }
                    else {
                        this.firstUpdate = false;
                    }
                };
                return ItemShowView;
            }(framework.templates.singleDateReport.ShowView));
        })(dateReport = templates.dateReport || (templates.dateReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));