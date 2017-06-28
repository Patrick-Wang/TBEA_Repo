/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="jcycljgdef.ts" />
/// <reference path="../../unitedSelector.ts"/>
///<reference path="../../messageBox.ts"/>
var jcycljg;
(function (jcycljg) {
    var View = (function () {
        function View() {
            this.mNodes = [];
        }
        //private mDisplayType:DisplayType;
        View.prototype.register = function (name, plugin) {
            var data = { id: this.mNodes.length, value: name, plugin: plugin };
            var node = new Util.DataNode(data);
            this.mNodes.push(node);
        };
        View.prototype.unregister = function (name) {
            var nod;
            for (var i = 0; i < this.mNodes.length; ++i) {
                this.mNodes[i].accept({
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
        View.prototype.init = function (opt) {
            var _this = this;
            this.mOpt = opt;
            var start = {
                format: 'YYYY年MM月',
                isinitVal: true,
                isTime: false,
                ishmsVal: false,
                isClear: false,
                isToday: false,
                minDate: Util.date2Str(Util.addYear(this.mOpt.date, -3)),
                maxDate: Util.date2Str(this.mOpt.date),
                choosefun: function (elem, val, date) {
                    setTimeout(function () {
                        end.minDate = Util.date2Str(_this.getStartDate());
                        endDates();
                    }, 0);
                }
            };
            var end = {
                format: 'YYYY年MM月',
                isinitVal: true,
                isTime: false,
                ishmsVal: false,
                isClear: false,
                isToday: false,
                minDate: Util.date2Str(this.mOpt.date),
                maxDate: Util.date2Str(this.mOpt.date),
                choosefun: function (elem, val, date) {
                    start.maxDate = Util.date2Str(_this.getEndDate()); //将结束日的初始值设定为开始日的最大日期
                }
            };
            //这里是日期联动的关键
            function endDates() {
                //将结束日期的事件改成 false 即可
                end.insTrigger = false;
                $("#inpend").jeDate(end);
            }
            $('#' + this.mOpt.dts).jeDate(start);
            $('#' + this.mOpt.dte).jeDate(end);
            this.mItemSelector = new Util.UnitedSelector(this.mNodes, this.mOpt.type);
            this.mNodes = this.mItemSelector.getTopNodes();
            if (this.plugin(this.getActiveNode()).getDateType() == jcycljg.DateType.DAY) {
                $("#" + this.mOpt.dte).hide();
            }
            this.mItemSelector.change(function (sel, depth) {
                if (_this.plugin(_this.getActiveNode()).getDateType() == jcycljg.DateType.MONTH) {
                    $("#" + _this.mOpt.dte).show();
                    $("#" + _this.mOpt.dts).show();
                }
                else if (_this.plugin(_this.getActiveNode()).getDateType() == jcycljg.DateType.YEAR) {
                    $("#" + _this.mOpt.dts).hide();
                    $("#" + _this.mOpt.dte).hide();
                }
                else {
                    $("#" + _this.mOpt.dts).show();
                    $("#" + _this.mOpt.dte).hide();
                }
            });
            $(window).resize(function () {
                _this.mCurrentPlugin.adjustSize();
            });
            //let inputs = $("#
            // " + this.mOpt.contentType + " input");
            //inputs.click((e)=>{
            //    for(let i=0;i<inputs.length;i++){
            //        if(true == inputs[i].checked){
            //            if(inputs[i].id=='rdct'){
            //                this.mDisplayType = DisplayType.CHART;
            //                this.showPluginChart();
            //            }else{
            //                this.mDisplayType = DisplayType.TABLE;
            //                this.showPluginTable();
            //            }
            //        }
            //    }
            //});
            //this.mDisplayType = this.getDisplayType();
            this.updateUI();
        };
        //private getDisplayType() : DisplayType{
        //    let inputs = $("#" + this.mOpt.contentType + " input");
        //    for(let i=0;i<inputs.length;i++){
        //        if(true == inputs[i].checked){
        //            if(inputs[i].id=='rdct'){
        //                return DisplayType.CHART;
        //            }else{
        //                return DisplayType.TABLE;
        //            }
        //        }
        //    }
        //    return DisplayType.CHART;
        //}
        //private showPluginChart():void{
        //    if (this.mCurrentPlugin.getContentType() == ContentType.TABLE_CHART){
        //        this.mCurrentPlugin.switchDisplayType(DisplayType.CHART);
        //    }
        //}
        //
        //private showPluginTable():void{
        //    if (this.mCurrentPlugin.getContentType() == ContentType.TABLE_CHART){
        //        this.mCurrentPlugin.switchDisplayType(DisplayType.TABLE);
        //    }
        //}
        View.prototype.plugin = function (node) {
            return node.getData().plugin;
        };
        View.prototype.getActiveNode = function () {
            return this.mItemSelector.getDataNode(this.mItemSelector.getPath());
        };
        //private checkDate(dts:Util.Date, dse:Util.Date) : boolean{
        //    let start = new Date(dts.year + "/" + dts.month + "/" + dts.day);
        //    let end = new Date(dse.year + "/" + dse.month + "/" + dse.day);
        //    if (start > end){
        //        Util.MessageBox.tip("开始日期不可以大于结束日期");
        //        return false;
        //    }
        //    return true;
        //}
        View.prototype.getStartDate = function () {
            var ret = {};
            if (this.mOpt.date) {
                var curDate = $("#" + this.mOpt.dts).getDate();
                ret = {
                    year: curDate.getFullYear(),
                    month: curDate.getMonth() + 1,
                    day: curDate.getDate()
                };
            }
            return ret;
        };
        View.prototype.getEndDate = function () {
            var ret = {};
            if (this.mOpt.date) {
                var curDate = $("#" + this.mOpt.dte).getDate();
                ret = {
                    year: curDate.getFullYear(),
                    month: curDate.getMonth() + 1,
                    day: curDate.getDate()
                };
            }
            return ret;
        };
        View.prototype.updateUI = function () {
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            var dts = this.getStartDate();
            var dte = this.getEndDate();
            //if (this.plugin(node).getDateType() == DateType.MONTH){
            //    dte = this.mDSEnd.getDate();
            //    dte.day = this.mDSEnd.monthDays();
            //}else {
            //    dte.day = this.mDSStart.monthDays();
            //}
            //
            //if (!this.checkDate(dts, dte)){
            //    return;
            //}
            this.mCurrentPlugin = this.plugin(node);
            for (var i = 0; i < this.mNodes.length; ++i) {
                if (node != this.mNodes[i]) {
                    this.plugin(this.mNodes[i]).hide();
                }
            }
            this.mCurrentPlugin.show();
            //$("#headertitle")[0].innerHTML = node.getData().value;
            //if ( this.mDisplayType == DisplayType.CHART){
            //    this.showPluginChart();
            //}else {
            //    this.showPluginTable();
            //}
            //if (this.mCurrentPlugin.getContentType() == ContentType.TABLE_CHART){
            //    $("#" + this.mOpt.contentType).show();
            //}else{
            //    $("#" + this.mOpt.contentType).hide();
            //}
            this.plugin(node).update(dts, dte);
        };
        return View;
    })();
    jcycljg.View = View;
})(jcycljg || (jcycljg = {}));
var view = new jcycljg.View();
