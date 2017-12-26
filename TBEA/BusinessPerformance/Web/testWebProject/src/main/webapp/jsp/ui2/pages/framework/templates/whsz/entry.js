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
///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
///<reference path="../../../messageBox.ts"/>
///<reference path="../../../components/dateSelectorProxy.ts"/>
///<reference path="../singleDateReport/entry.ts"/>
var framework;
(function (framework) {
    var templates;
    (function (templates) {
        var dateReport;
        (function (dateReport) {
            var Cell = JQTable.Cell;
            var Formula = JQTable.Formula;
            function createInstance() {
                return new EntryView();
            }
            dateReport.createInstance = createInstance;
            var EntryView = (function (_super) {
                __extends(EntryView, _super);
                function EntryView() {
                    return _super !== null && _super.apply(this, arguments) || this;
                }
                EntryView.prototype.onInitialize = function (opt) {
                    this.unitedSelector = new Util.UnitedSelector(opt.itemNodes, opt.itemId);
                    if (opt.itemNodes.length == 1) {
                        this.unitedSelector.hide();
                        if ($("#" + opt.dtId).parent().hasClass("hidden")) {
                            $("#grid-update").hide();
                            $("#" + opt.dtId).hide();
                        }
                    }
                    _super.prototype.onInitialize.call(this, opt);
                };
                EntryView.prototype.getParams = function (date) {
                    return {
                        date: this.getDate(date),
                        item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id
                    };
                };
                EntryView.prototype.createJqassist = function () {
                    _super.prototype.createJqassist.call(this);
                    var MyFormula = (function () {
                        function MyFormula() {
                        }
                        MyFormula.prototype.update = function () {
                            var ids = this.grid.jqGrid('getDataIDs');
                            if (ids.length > 0) {
                                var formulas = [];
                                for (var i = 0; i < ids.length; ++i) {
                                    formulas = formulas.concat(this.collectFormula(ids[i]));
                                }
                                for (var i = 0; i < formulas.length; ++i) {
                                    formulas[i].setGrid(this.grid);
                                    formulas[i].update();
                                }
                            }
                            return false;
                        };
                        MyFormula.prototype.setGrid = function (grid) {
                            this.grid = grid;
                        };
                        MyFormula.prototype.sumCells = function (srcCells) {
                            var tmpResult = 0;
                            var result = undefined;
                            for (var j = 0; j < srcCells.length; ++j) {
                                if (srcCells[j].getVal() != undefined && srcCells[j].getVal() != "") {
                                    tmpResult += parseFloat(srcCells[j].getVal());
                                    result = tmpResult;
                                }
                            }
                            return result;
                        };
                        MyFormula.prototype.sumFormula = function () {
                            var _this = this;
                            return function (dest, srcCells) {
                                var ret = _this.sumCells(srcCells);
                                return ret;
                            };
                        };
                        MyFormula.prototype.collectFormula = function (rid) {
                            var _this = this;
                            return [
                                new Formula(Cell.create(rid, 4), [Cell.create(rid, 3), Cell.create(rid, 2)], this.sumFormula()),
                                new Formula(Cell.create(rid, 7), [Cell.create(rid, 5), Cell.create(rid, 6)], this.sumFormula()),
                                new Formula(Cell.create(rid, 8), [Cell.create(rid, 7), Cell.create(rid, 4)], this.sumFormula()),
                                new Formula(Cell.create(rid, 12), [Cell.create(rid, 11), Cell.create(rid, 10), Cell.create(rid, 9)], this.sumFormula()),
                                new Formula(Cell.create(rid, 17), [Cell.create(rid, 16), Cell.create(rid, 15), Cell.create(rid, 14), Cell.create(rid, 13)], this.sumFormula()),
                                new Formula(Cell.create(rid, 18), [Cell.create(rid, 12), Cell.create(rid, 8), Cell.create(rid, 17)], function (dest, srcCells) {
                                    var ret = _this.sumCells(srcCells.slice(0, 2));
                                    var val = srcCells[2].getVal();
                                    if (val != undefined && val != "") {
                                        val = parseFloat(val);
                                        if (undefined != ret) {
                                            return ret - val;
                                        }
                                        else {
                                            return -val;
                                        }
                                    }
                                    else {
                                        if (undefined != ret) {
                                            return ret;
                                        }
                                    }
                                    return undefined;
                                })
                            ];
                        };
                        return MyFormula;
                    }());
                    this.mTableAssist.addFormula(new MyFormula());
                    return this.mTableAssist;
                };
                EntryView.prototype.submit = function (date) {
                    var _this = this;
                    this.mAjaxSubmit.post($.extend(this.getParams(this.getUDate()), {
                        data: JSON.stringify(this.onLoadSubmitData())
                    }))
                        .then(function (resp) {
                        if (Util.ErrorCode.OK == resp.errorCode) {
                            _this.update(_this.getUDate());
                            Util.Toast.success("提交 成功");
                        }
                        else {
                            Util.Toast.failed(resp.message);
                        }
                    }, function (text) {
                        Util.Toast.failed('提交 失败');
                    });
                };
                return EntryView;
            }(framework.templates.singleDateReport.EntryView));
            dateReport.EntryView = EntryView;
        })(dateReport = templates.dateReport || (templates.dateReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
