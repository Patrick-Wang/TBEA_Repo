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


        constructor(start:Util.Date, end:Util.Date, now:Util.Date, id:string, jdName ?:string[]){
            let dates:Util.IDataNode[] = this.getYears(start, end);
            let jdNames:string[] = ["一季度", "半年度","三季度","年度"];
            if (undefined != jdName){
                jdNames = jdName;
            }
            let seasonEnd : number = parseInt("" + (end.month - 1) / 3);

            let seasons:Util.IDataNode[] = [];
            for (let i = 0; i <= seasonEnd; ++i){
                seasons.push({
                    data:{
                        id:i,
                        value:jdNames[i]
                    }
                })
            }

            let seasonsAll:Util.IDataNode[] = [];
            for (let i = 0; i <= 3; ++i){
                seasonsAll.push({
                    data:{
                        id:i,
                        value:jdNames[i]
                    }
                })
            }

            let seasonNow : number = parseInt("" + (now.month - 1) / 3);

            $("#" + id).append("<div style='float:left' id='" + id +"year'></div>");
            $("#" + id).append("<div style='float:left' id='" + id +"season'></div>");

            let lastYear = now.year;

            this.yearSelector = new UnitedSelector(dates, id + "year", [now.year - start.year]);
            this.seasonSelector = new UnitedSelector(seasons, id + "season", [seasonNow]);
            $("#" + id + " select").css("width", "100px");
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
                    minWidth: 80,
                    height　: '100%',///*itemCount * 27 > 600 ? 600 :*/ itemCount * itemHeight + 3,
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                }).css("text-align:center");

                let year:Util.Date = this.getDate();
                if (year.year != lastYear){
                    lastYear = year.year;
                    let path = this.seasonSelector.getPath();
                    if (lastYear == end.year){
                        if (path[0] > (seasons.length - 1)){
                            path = [seasonNow];
                        }
                        this.seasonSelector.refresh(seasons, path)
                    }else{
                        this.seasonSelector.refresh(seasonsAll, path)
                    }
                    $("#" + id + " select").css("width", "100px");
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
                minWidth: 80,
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