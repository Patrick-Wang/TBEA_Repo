///<reference path="../unitedSelector.ts"/>
///<reference path="../dateSelector.ts"/>
///<reference path="../../js/jquery/jquery.d.ts"/>
/**
 * Created by Floyd on 2016/7/19.
 */
module Util {

    let months : Util.DataNode[] = [<Util.DataNode>{
        data:{
            id:0,
            value:"首月"
        }
    },<Util.DataNode>{
        data:{
            id:1,
            value:"次月"
        }
    },<Util.DataNode>{
        data:{
            id:2,
            value:"末月"
        }
    }];
    let seasons : Util.DataNode[] = [<Util.DataNode>{
        data:{
            id:0,
            value:"第一季度"
        },
        subNodes:months
    },<Util.DataNode>{
        data:{
            id:1,
            value:"第二季度"
        },
        subNodes:months
    },<Util.DataNode>{
        data:{
            id:2,
            value:"第三季度"
        },
        subNodes:months
    },<Util.DataNode>{
        data:{
            id:3,
            value:"第四季度"
        },
        subNodes:months
    }];
    export class DateSeasonSelector{

        unitedSelector : Util.UnitedSelector;

        constructor(start:Util.Date, end:Util.Date, now:Util.Date, id:string){
            let seasonStart : number = parseInt("" + (start.month - 1) / 3);
            let monthStart : number = (start.month - 1) % 3;
            let seasonEnd : number = parseInt("" + (end.month - 1) / 3);
            let monthEnd : number = (end.month - 1) % 3;
            let dates:Util.DataNode[] = this.getYears(start, end);
            if (dates.length == 1){
                dates[0].subNodes = [];
                let sStartNode:Util.DataNode = $.extend({}, seasons[seasonStart]);
                sStartNode.subNodes = [];
                if (seasonStart == seasonEnd){
                    dates[0].subNodes.push(sStartNode);
                    for (let i = monthStart; i <= monthEnd; ++i){
                        dates[0].subNodes[0].subNodes.push($.extend({},
                             months[i]))
                    }
                }else{
                    dates[0].subNodes.push(this.getStartSeasons(seasonStart, monthStart));

                    for (let i = seasonStart + 1; i < seasonEnd; ++i){
                        let season:Util.DataNode = seasons[i];
                        dates[0].subNodes.push(season);
                    }

                    dates[0].subNodes.push(this.getEndSeasons(seasonEnd, monthEnd));

                }
            }else{
                dates[0].subNodes = [];
                dates[0].subNodes.push(this.getStartSeasons(seasonStart, monthStart));
                for (let i = seasonStart; i < 4; ++i){
                    dates[0].subNodes.push(seasons[i]);
                }

                for (let i = 1; i < dates.length - 1; ++i){
                    dates[i].subNodes = seasons;
                }
                dates[dates.length - 1].subNodes = [];
                for (let i = 0; i < seasonEnd; ++i){
                    dates[dates.length - 1].subNodes.push(seasons[i]);
                }
                dates[dates.length - 1].subNodes.push(this.getEndSeasons(seasonEnd, monthEnd));
            }

            let seasonNow : number = parseInt("" + (now.month - 1) / 3);
            let monthNow : number = (now.month - 1) % 3;
            let nowYearIndex:number = now.year - start.year;
            let nowMonthIndex:number;
            let nowSeasonIndex:number;
            for (let sub in dates[nowYearIndex].subNodes){
                if (dates[nowYearIndex].subNodes[sub].data.id == seasonNow){
                    nowSeasonIndex = sub;
                    break;
                }
            }

            for (let sub in dates[nowYearIndex].subNodes[nowSeasonIndex].subNodes){
                if (dates[nowYearIndex].subNodes[nowSeasonIndex].subNodes[sub].data.id == monthNow){
                    nowMonthIndex = sub;
                    break;
                }
            }

            this.unitedSelector = new UnitedSelector(dates, id, [nowYearIndex, nowSeasonIndex, nowMonthIndex]);
            this.unitedSelector.change((sel, depth) =>{
                sel = this.unitedSelector.getSelect();
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
            let sel = this.unitedSelector.getSelect();
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
            let selNodes : Util.IDataNode[] = this.unitedSelector.getNodes();
            return {
                year:selNodes[0].data.id,
                month:selNodes[1].data.id * 3 + selNodes[2].data.id + 1
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

        private getStartSeasons(seasonStart:number, monthStart:number):Util.DataNode {
            let sStartNode:Util.DataNode = $.extend({}, seasons[seasonStart]);
            sStartNode.subNodes = [];
            for (let i = monthStart; i < 3; ++i){
                sStartNode.subNodes.push($.extend({},
                    months[i]))
            }
            return sStartNode;
        }

        private getEndSeasons(seasonEnd:number, monthEnd:number):Util.DataNode {
            let sEndNode:Util.DataNode = $.extend({}, seasons[seasonEnd]);
            sEndNode.subNodes = [];
            for (let i = 0; i <= monthEnd; ++i){
                sEndNode.subNodes.push($.extend({}, months[i]))
            }
            return sEndNode;
        }
    }
}