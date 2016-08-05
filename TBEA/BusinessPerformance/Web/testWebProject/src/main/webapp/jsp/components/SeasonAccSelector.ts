///<reference path="../unitedSelector.ts"/>
///<reference path="../dateSelector.ts"/>
///<reference path="../../js/jquery/jquery.d.ts"/>
/**
 * Created by Floyd on 2016/7/19.
 */
module Util {
    export class SeasonAccSelector{

        yearSelector : Util.UnitedSelector;
        seasonSelector : Util.UnitedSelector;


        constructor(start:Util.Date, end:Util.Date, now:Util.Date, id:string){
            let dates:Util.IDataNode[] = this.getYears(start, end);
            let seasons:Util.IDataNode[] = [{
                data:{
                    id:0,value:"一季度"
                }
            },{
                data:{
                    id:1,value:"半年度"
                }
            },{
                data:{
                    id:2,value:"三季度"
                }
            },{
                data:{
                    id:3,value:"年度"
                }
            }];

            let seasonNow : number = parseInt("" + (now.month - 1) / 3);

            $("#" + id).append("<div id='" + id +"year'></div>");
            $("#" + id).append("<div style='margin-left:5px' id='" + id +"season'></div>");

            this.yearSelector = new UnitedSelector(dates, id + "year", [now.year - start.year]);
            this.seasonSelector = new UnitedSelector(seasons, id + "season", [seasonNow]);

            this.yearSelector.change((sel, depth) =>{
                sel = this.yearSelector.getSelect();
                $(sel).multiselect({
                    multiple: false,
                    header: false,
                    minWidth: 80,
                    height　: '100%',///*itemCount * 27 > 600 ? 600 :*/ itemCount * itemHeight + 3,
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                }).css("text-align:center");
                $(sel[1]).multiselect({
                    multiple: false,
                    header: false,
                    minWidth: 100,
                    height　: '100%',///*itemCount * 27 > 600 ? 600 :*/ itemCount * itemHeight + 3,
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                }).css("text-align:center");
            });
            let sel = this.yearSelector.getSelect();
            $(sel).multiselect({
                multiple: false,
                header: false,
                minWidth: 80,
                height　: '100%',///*itemCount * 27 > 600 ? 600 :*/ itemCount * itemHeight + 3,
                // noneSelectedText: "请选择月份",
                selectedList: 1
            }).css("text-align:center");
            $(sel[1]).multiselect({
                multiple: false,
                header: false,
                minWidth: 100,
                height　: '100%',///*itemCount * 27 > 600 ? 600 :*/ itemCount * itemHeight + 3,
                // noneSelectedText: "请选择月份",
                selectedList: 1
            }).css("text-align:center");

            this.seasonSelector.change((sel, depth) =>{
                sel = this.seasonSelector.getSelect();
                $(sel).multiselect({
                    multiple: false,
                    header: false,
                    minWidth: 80,
                    height　: '100%',///*itemCount * 27 > 600 ? 600 :*/ itemCount * itemHeight + 3,
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                }).css("text-align:center");
                $(sel[1]).multiselect({
                    multiple: false,
                    header: false,
                    minWidth: 100,
                    height　: '100%',///*itemCount * 27 > 600 ? 600 :*/ itemCount * itemHeight + 3,
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                }).css("text-align:center");
            });
            sel = this.seasonSelector.getSelect();
            $(sel).multiselect({
                multiple: false,
                header: false,
                minWidth: 80,
                height　: '100%',///*itemCount * 27 > 600 ? 600 :*/ itemCount * itemHeight + 3,
                // noneSelectedText: "请选择月份",
                selectedList: 1
            }).css("text-align:center");
            $(sel[1]).multiselect({
                multiple: false,
                header: false,
                minWidth: 100,
                height　: '100%',///*itemCount * 27 > 600 ? 600 :*/ itemCount * itemHeight + 3,
                // noneSelectedText: "请选择月份",
                selectedList: 1
            }).css("text-align:center");
        }

        getDate():Util.Date{
            let selNodes : Util.IDataNode[] = this.yearSelector.getNodes();
            let seasonNodes : Util.IDataNode[] = this.seasonSelector.getNodes();
            return {
                year:selNodes[0].data.id,
                month:seasonNodes[0].data.id * 3 + 3
            };
        }

        private getYears(start:Util.Date, end:Util.Date):Util.DataNode[] {
            let dates:Util.DataNode[] = [];
            for (let i = start.year; i <= end.year; ++i){
                let year : Util.DataNode = <Util.DataNode>{
                    data : {
                        id:i,
                        value:i + "年"
                    }
                };
                dates.push(year);
            }
            return dates;
        }
    }
}