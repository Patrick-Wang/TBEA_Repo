///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="../components/dateSelectorProxy.ts"/>
///<reference path="../unitedSelector.ts"/>
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
var cwsf;
(function (cwsf) {
    var router = framework.router;
    var FrameEvent = framework.basic.FrameEvent;
    var CwsfShowView = /** @class */ (function (_super) {
        __extends(CwsfShowView, _super);
        function CwsfShowView() {
            return _super !== null && _super.apply(this, arguments) || this;
        }
        CwsfShowView.prototype.getMaxWidth = function (opts) {
            var max = 0;
            var tmp = 0;
            var fontSize = Util.isMSIE() ? 14 : 13;
            for (var i = 0; i < opts.length; ++i) {
                tmp = $(opts[i]).text().getWidth(fontSize) + 25;
                if (max < tmp) {
                    max = tmp;
                }
            }
            return max;
        };
        CwsfShowView.prototype.init = function (opt) {
            var _this = this;
            this.mOpt = opt;
            this.mDStartSel = new Util.DateSelectorProxy("dstart", opt.dateStart == undefined ? Util.addYear(opt.date, -3) : opt.dateStart, opt.dateEnd == undefined ? Util.addYear(opt.date, 20) : opt.dateEnd, Util.addDay(opt.date, -5 * 7));
            this.mDEndSel = new Util.DateSelectorProxy("dEnd", opt.dateStart == undefined ? Util.addYear(opt.date, -3) : opt.dateStart, opt.dateEnd == undefined ? Util.addYear(opt.date, 20) : opt.dateEnd, opt.date);
            this.mDEndSel.change(function (date) {
                var d = _this.mDStartSel.getDate();
                var dS = Util.uDate2sDate(d).getTime();
                var dE = Util.uDate2sDate(date).getTime();
                if (dS > dE) {
                    _this.mDStartSel.setDate(date);
                }
            });
            $("#ui-datepicker-div").css('font-size', '0.8em'); //改变大小;
            var nodes = [];
            for (var i = 0; i < this.mNodesAll.length; ++i) {
                if (router.to(this.plugin(this.mNodesAll[i])).send(FrameEvent.FE_IS_COMPANY_SUPPORTED)) {
                    nodes.push(this.mNodesAll[i]);
                }
            }
            this.mItemSelector = new Util.UnitedSelector(nodes, this.mOpt.type);
            if (nodes.length == 1) {
                this.mItemSelector.hide();
            }
            this.mCompanySelector = new Util.CompanySelector(true, this.mOpt.comp, this.mOpt.comps);
            if (opt.comps.length <= 10) {
                this.mCompanySelector.hide();
            }
            this.mCompanySelector.checkAll();
            this.unitedSelector = new Util.UnitedSelector(opt.itemNodes, opt.itemId);
            var sel = $("#" + opt.itemId + " select");
            var width = this.getMaxWidth(sel.children());
            sel.css("width", width);
            sel.multiselect({
                multiple: false,
                header: false,
                minWidth: 120,
                height: '100%',
                // noneSelectedText: "请选择月份",
                selectedList: 1
            });
            this.updateUI();
        };
        CwsfShowView.prototype.onEvent = function (e) {
            switch (e.id) {
                case FrameEvent.FE_EXPORTEXCEL:
                    this.exportExcel(e.data);
                    break;
            }
            return _super.prototype.onEvent.call(this, e);
        };
        CwsfShowView.prototype.updateUI = function () {
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            this.mCurrentPlugin = this.plugin(node);
            router.broadcast(FrameEvent.FE_HIDE);
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SHOW);
            $("#headertitle")[0].innerHTML = node.getData().value;
            this.mCompanySelector.getCompanyNames();
            //let unit = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GETUNIT);
            //if (undefined != unit){
            //    $("#unit").text(unit);
            //}else{
            //    $("#unit").text("");
            //}
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                dStart: this.mDStartSel == undefined ? undefined : Util.date2Str(this.mDStartSel.getDate()),
                dEnd: this.mDEndSel == undefined ? undefined : Util.date2Str(this.mDEndSel.getDate()),
                comps: JSON.stringify(this.mCompanySelector.getCompanyNames()),
                item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.value
            });
        };
        CwsfShowView.prototype.exportExcel = function (elemId) {
            var url = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GET_EXPORTURL, {
                dStart: this.mDStartSel == undefined ? undefined : Util.date2Str(this.mDStartSel.getDate()),
                dEnd: this.mDEndSel == undefined ? undefined : Util.date2Str(this.mDEndSel.getDate()),
                comps: JSON.stringify(this.mCompanySelector.getCompanyNames()),
                item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.value
            });
            $("#" + elemId)[0].action = url;
            $("#" + elemId)[0].submit();
        };
        return CwsfShowView;
    }(framework.basic.ShowFrameView));
    var ins = new CwsfShowView();
})(cwsf || (cwsf = {}));
