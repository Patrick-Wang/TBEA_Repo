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
        var whszfb;
        (function (whszfb) {
            var Cell = JQTable.Cell;
            var Formula = JQTable.Formula;
            function createInstance() {
                return new EntryView();
            }
            whszfb.createInstance = createInstance;
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
                        MyFormula.prototype.getVal = function (cell) {
                            if (cell.getVal() != undefined && cell.getVal() != "") {
                                return parseFloat(cell.getVal());
                            }
                            return 0;
                        };
                        MyFormula.prototype.sumFormula = function () {
                            var _this = this;
                            return function (dest, srcCells) {
                                return (_this.getVal(srcCells[0]) + _this.getVal(srcCells[1]) - _this.getVal(srcCells[2])).toFixed(4);
                            };
                        };
                        MyFormula.prototype.collectFormula = function (rid) {
                            return [
                                new Formula(Cell.create(rid, 8), [Cell.create(rid, 5), Cell.create(rid, 6), Cell.create(rid, 7)], this.sumFormula())
                            ];
                        };
                        return MyFormula;
                    }());
                    if (this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id <= 1) {
                        this.mTableAssist.addFormula(new MyFormula());
                    }
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
                EntryView.prototype.updateTable = function () {
                    this.createJqassist();
                    var opt = {
                        datatype: "local",
                        dataWithId: this.resp.data,
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
                        rowNum: 15,
                        height: '100%',
                        width: $("#" + this.opt.host).width(),
                        shrinkToFit: this.resp.shrinkToFit == "false" ? false : true,
                        autoScroll: true,
                        pager: '#' + this.jqgridName() + "pager",
                        viewrecords: true
                    };
                    if (this.resp.pager == 'none') {
                        opt.pager = undefined;
                        opt.rowNum = 2000;
                    }
                    this.mTableAssist.create(opt);
                    this.adjustSize();
                };
                return EntryView;
            }(framework.templates.singleDateReport.EntryView));
            whszfb.EntryView = EntryView;
        })(whszfb = templates.whszfb || (templates.whszfb = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
