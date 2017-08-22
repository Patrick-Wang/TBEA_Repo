var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
///<reference path="../framework/route/route.ts"/>
///<reference path="../framework/basic/basicdef.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="../framework/templates/singleDateReport/show.ts"/>
var rzywtj;
(function (rzywtj) {
    rzywtj.FE_SOLVED = framework.route.nextId();
    function create() {
        return new ItemShowView();
    }
    rzywtj.create = create;
    var ItemShowView = (function (_super) {
        __extends(ItemShowView, _super);
        function ItemShowView() {
            _super.apply(this, arguments);
            this.doubleHeader = false;
        }
        ItemShowView.prototype.onInitialize = function (opt) {
            var _this = this;
            this.mAjaxSubmit = new Util.Ajax(opt.submitUrl, false);
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
            if (item2Hidden && itemHidden && $("#" + opt.dtId).hasClass("hidden")) {
                $("#sels").hide();
                $("#" + opt.dtId).hide();
            }
            $(window).resize(function () {
                _this.adjustHeader();
            });
            _super.prototype.onInitialize.call(this, opt);
        };
        ItemShowView.prototype.onEvent = function (e) {
            var _this = this;
            switch (e.id) {
                case rzywtj.FE_SOLVED:
                    this.mAjaxSubmit.post($.extend({
                        data: JSON.stringify(this.mTableAssist.getAllData())
                    }, this.getParams(this.getUDate())))
                        .then(function (jsonData) {
                        var resp = jsonData;
                        if (Util.ErrorCode.OK == resp.errorCode) {
                            Util.Toast.success("处理成功");
                            _this.updateStatus();
                        }
                        else {
                            Util.Toast.failed(resp.message);
                        }
                    });
            }
            return _super.prototype.onEvent.call(this, e);
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
            this.mAjaxUpdate.get(this.getParams(date))
                .then(function (jsonData) {
                _this.resp = jsonData;
                _this.updateTable();
            });
        };
        ItemShowView.prototype.updateColor = function () {
            for (var i = 0; i < this.resp.data.length; ++i) {
                var dt = this.resp.data[i][this.resp.data[i].length - 2];
                if (dt && this.mTableAssist.getCellValue(this.mTableAssist.getRid(i), this.resp.data[i].length - 1) == 'N') {
                    var deadline = Util.toDate(dt);
                    var dtNow = Util.now();
                    var dDeadline = new Date(deadline.year, deadline.month, deadline.day);
                    var dNow = new Date(dtNow.year, dtNow.month, dtNow.day);
                    if (dDeadline.getTime() <= dNow.getTime()) {
                        this.mTableAssist.updateRowBgColor(this.mTableAssist.getRid(i), 237, 28, 36);
                        continue;
                    }
                }
                this.mTableAssist.updateRowBgColor(this.mTableAssist.getRid(i));
            }
        };
        ItemShowView.prototype.updateRowColor = function (rowid) {
            var dt = this.mTableAssist.getCellValue(rowid, this.resp.data[0].length - 2);
            if (dt && this.mTableAssist.getCellValue(rowid, this.resp.data[0].length - 1) == 'N') {
                var deadline = Util.toDate(dt);
                var dtNow = Util.now();
                var dDeadline = new Date(deadline.year, deadline.month, deadline.day);
                var dNow = new Date(dtNow.year, dtNow.month, dtNow.day);
                if (dDeadline.getTime() <= dNow.getTime()) {
                    this.mTableAssist.updateRowBgColor(rowid, 237, 28, 36);
                    return;
                }
            }
            this.mTableAssist.updateRowBgColor(rowid);
        };
        ItemShowView.prototype.updateStatus = function () {
            var _this = this;
            var sels = this.mTableAssist.getSelection();
            for (var i = 0; i < this.resp.data.length; ++i) {
                if (this.resp.data[i][this.resp.data[i].length - 1] == 'Y') {
                    var isSeled = false;
                    $(sels).each(function (j, e) {
                        if (e == _this.mTableAssist.getRid(i)) {
                            isSeled = true;
                            return false;
                        }
                    });
                    if (!isSeled) {
                        this.mTableAssist.setSelection(this.mTableAssist.getRid(i));
                    }
                }
            }
        };
        ItemShowView.prototype.updateTable = function () {
            var _this = this;
            if (this.resp.data.length == 0) {
                $("#tip").removeClass("hidden");
                $("#" + this.opt.host).hide();
                $("#grid-solve").addClass("hidden");
            }
            else {
                $("#tip").addClass("hidden");
                $("#grid-solve").removeClass("hidden");
                $("#" + this.opt.host).show();
                this.createJqassist();
                this.mTableAssist.create({
                    dataWithId: this.resp.data,
                    datatype: "local",
                    multiselect: true,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: $("#" + this.opt.host).width(),
                    shrinkToFit: this.resp.shrinkToFit == "false" ? false : true,
                    rowNum: 2000,
                    autoScroll: true,
                    onSelectRow: function (rowid, status) {
                        if (status) {
                            _this.mTableAssist.setCellValue(rowid, _this.resp.data[0].length - 1, 'Y');
                        }
                        else {
                            _this.mTableAssist.setCellValue(rowid, _this.resp.data[0].length - 1, 'N');
                        }
                        _this.updateRowColor(rowid);
                    },
                    onSelectAll: function (rowids, status) {
                        if (status) {
                            for (var i = 0; i < rowids.length; ++i) {
                                _this.mTableAssist.setCellValue(rowids[i], _this.resp.data[0].length - 1, 'Y');
                            }
                        }
                        else {
                            for (var i = 0; i < rowids.length; ++i) {
                                _this.mTableAssist.setCellValue(rowids[i], _this.resp.data[0].length - 1, 'N');
                            }
                        }
                        _this.updateColor();
                    }
                });
                this.updateStatus();
                this.updateColor();
                this.adjustSize();
            }
        };
        return ItemShowView;
    })(framework.templates.singleDateReport.ShowView);
})(rzywtj || (rzywtj = {}));
