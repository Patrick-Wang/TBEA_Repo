///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var xnyzb;
(function (xnyzb) {
    var router = framework.router;
    var FrameEvent = framework.basic.FrameEvent;
    var XnyzbShowView = (function (_super) {
        __extends(XnyzbShowView, _super);
        function XnyzbShowView() {
            _super.apply(this, arguments);
        }
        XnyzbShowView.prototype.init = function (opt) {
            var _this = this;
            this.mOpt = opt;
            this.mDStart = opt.date.year + "-" + opt.date.month + "-" + opt.date.day;
            $("#dstart").val(this.mDStart);
            $("#dstart").datepicker({
                //            numberOfMonths:1,//显示几个月
                //            showButtonPanel:true,//是否显示按钮面板
                dateFormat: 'yy-mm-dd',
                //            clearText:"清除",//清除日期的按钮名称
                //            closeText:"关闭",//关闭选择框的按钮名称
                yearSuffix: '年',
                showMonthAfterYear: true,
                defaultDate: this.mDStart,
                //            minDate:'2011-03-05',//最小日期
                maxDate: 2019 + "-" + 1 + "-" + 1,
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                onSelect: function (selectedDate) {
                    var d = new Date(selectedDate);
                    _this.mDStart = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
                }
            });
            $("#dEnd").val(this.mDStart);
            this.mDEnd = this.mDStart;
            $("#dEnd").datepicker({
                //            numberOfMonths:1,//显示几个月
                //            showButtonPanel:true,//是否显示按钮面板
                dateFormat: 'yy-mm-dd',
                //            clearText:"清除",//清除日期的按钮名称
                //            closeText:"关闭",//关闭选择框的按钮名称
                yearSuffix: '年',
                showMonthAfterYear: true,
                defaultDate: this.mDStart,
                //            minDate:'2011-03-05',//最小日期
                maxDate: 2019 + "-" + 1 + "-" + 1,
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                onSelect: function (selectedDate) {
                    var d = new Date(selectedDate);
                    _this.mDEnd = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
                }
            });
            $("#ui-datepicker-div").css('font-size', '0.8em'); //改变大小;
            this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.comp, this.mOpt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }
            this.mCompanySelector.change(function (selector, depth) {
                _this.updateTypeSelector();
            });
            this.mCurrentDate = { year: 2010, month: 1, day: 1 };
            this.updateTypeSelector();
            this.updateUI();
        };
        XnyzbShowView.prototype.updateUI = function () {
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            this.mCurrentPlugin = this.plugin(node);
            router.broadcast(FrameEvent.FE_HIDE);
            this.mCurrentComp = this.mCompanySelector.getCompany();
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SHOW);
            if (null != this.mCurrentComp) {
                $("#headertitle")[0].innerHTML = this.mCompanySelector.getCompanyName() + " " + node.getData().value;
            }
            else {
                $("#headertitle")[0].innerHTML = node.getData().value;
            }
            var unit = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GETUNIT);
            if (undefined != unit) {
                $("#unit").text(unit);
            }
            else {
                $("#unit").text("");
            }
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                dStart: this.mDStart,
                dEnd: this.mDEnd,
                compType: this.mCurrentComp
            });
        };
        XnyzbShowView.prototype.exportExcel = function (elemId) {
            var url = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GET_EXPORTURL, {
                dStart: this.mDStart,
                dEnd: this.mDEnd,
                compType: this.mCurrentComp
            });
            $("#" + elemId)[0].action = url;
            $("#" + elemId)[0].submit();
        };
        return XnyzbShowView;
    })(framework.basic.ShowFrameView);
    var ins = new XnyzbShowView();
})(xnyzb || (xnyzb = {}));
