/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="jcycljgdef.ts" />
/// <reference path="../../unitedSelector.ts"/>
///<reference path="../../messageBox.ts"/>

module jcycljg {

    import DataNode = Util.DataNode;
    interface Option {
        dts:string;
        dte:string;
        type:string;
        contentType:string;
        date : Util.Date;
    }

    interface PluginData extends Util.IData {
        plugin : PluginView;
    }

    export class View implements FrameView {
        private mOpt:Option;
        //private mDSStart:Util.DateSelector;
        //private mDSEnd:Util.DateSelector;
        private mItemSelector:Util.UnitedSelector;
        private mNodes:Util.DataNode[] = [];
        private mCurrentPlugin: PluginView;
        //private mDisplayType:DisplayType;
        public register(name:string, plugin:PluginView):void {
            var data:PluginData = {id: this.mNodes.length, value: name, plugin: plugin};
            var node:Util.DataNode = new Util.DataNode(data);
            this.mNodes.push(node);
        }

        public unregister(name:string):PluginView {
            var nod:Util.DataNode;

            for (var i = 0; i < this.mNodes.length; ++i) {
                this.mNodes[i].accept({
                    visit: (node:Util.DataNode) => {
                        if (node.getData().value == name) {
                            nod = node;
                            return true;
                        }
                        return false;
                    }
                })
                if (nod != undefined) {
                    break;
                }
            }

            return this.plugin(nod);
        }

        public init(opt:Option):void {
            this.mOpt = opt;

            var start : any= {
                format: 'YYYY年MM月',
                isinitVal:true,
                isTime: false,
                ishmsVal:false,
                isClear: false,
                isToday: false,
                minDate: Util.date2Str(Util.addYear(this.mOpt.date, -3)) + " 00:00:00",
                maxDate: Util.date2Str(this.mOpt.date) + " 00:00:00",
                choosefun: (elem, val, date) =>{
                    setTimeout(()=>{
                        end.minDate = Util.date2Str(this.getStartDate()) + " 00:00:00";
                        endDates();
                    },0);
                }
            };
            var end : any= {
                format: 'YYYY年MM月',
                isinitVal:true,
                isTime: false,
                ishmsVal:false,
                isClear: false,
                isToday: false,
                minDate: Util.date2Str(this.mOpt.date) + " 00:00:00",
                maxDate: Util.date2Str(this.mOpt.date) + " 00:00:00",
                choosefun: (elem, val, date)=>{
                    start.maxDate = Util.date2Str(this.getEndDate()) + " 00:00:00"; //将结束日的初始值设定为开始日的最大日期
                }
            };

            //这里是日期联动的关键
            function endDates() {
                //将结束日期的事件改成 false 即可
                end.insTrigger = false;
                $("#inpend").jeDate(end);
            }

            $('#' +  this.mOpt.dts).jeDate(start);
            $('#' +  this.mOpt.dte).jeDate(end);

            this.mItemSelector = new Util.UnitedSelector(this.mNodes, this.mOpt.type);
            this.mNodes = this.mItemSelector.getTopNodes();
            if (this.plugin(this.getActiveNode()).getDateType() == DateType.DAY){
                $("#" + this.mOpt.dte).parent().hide();
            }
            this.mItemSelector.change((sel:any, depth:number)=>{
                if (this.plugin(this.getActiveNode()).getDateType() == DateType.MONTH){
                    $("#" + this.mOpt.dte).parent().show();
                    $("#" + this.mOpt.dts).parent().show();
                }else if (this.plugin(this.getActiveNode()).getDateType() == DateType.YEAR){
                    $("#" + this.mOpt.dts).parent().hide();
                    $("#" + this.mOpt.dte).parent().hide();
                }else {
                    $("#" + this.mOpt.dts).parent().show();
                    $("#" + this.mOpt.dte).parent().hide();
                }
            });

            $(window).resize(()=>{
                this.mCurrentPlugin.adjustSize();
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
        }

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

        private plugin(node:DataNode):PluginView{
            return  (<PluginData>node.getData()).plugin;
        }

        private getActiveNode():DataNode{
            return this.mItemSelector.getDataNode(this.mItemSelector.getPath());
        }

        //private checkDate(dts:Util.Date, dse:Util.Date) : boolean{
        //    let start = new Date(dts.year + "/" + dts.month + "/" + dts.day);
        //    let end = new Date(dse.year + "/" + dse.month + "/" + dse.day);
        //    if (start > end){
        //        Util.MessageBox.tip("开始日期不可以大于结束日期");
        //        return false;
        //    }
        //    return true;
        //}

        protected getStartDate():Util.Date {
            let ret : any = {};
            if (this.mOpt.date){
                let curDate = $("#" + this.mOpt.dts).getDate();
                ret = {
                    year : curDate.getFullYear(),
                    month : curDate.getMonth() + 1,
                    day : curDate.getDate()
                };
            }
            return ret;
        }

        protected getEndDate():Util.Date {
            let ret : any = {};
            if (this.mOpt.date){
                let curDate = $("#" + this.mOpt.dte).getDate();
                ret = {
                    year : curDate.getFullYear(),
                    month : curDate.getMonth() + 1,
                    day : curDate.getDate()
                };
            }
            return ret;
        }

        public updateUI() {
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());

            let dts:Util.Date = this.getStartDate();
            let dte:Util.Date = this.getStartDate();
            if (this.plugin(node).getDateType() == DateType.MONTH){
                dte = this.getEndDate();
            }
            dts.day = 1;
            dte.day = Util.monthDaysCount(dte);
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
        }
    }
}

var view:jcycljg.FrameView = new jcycljg.View();