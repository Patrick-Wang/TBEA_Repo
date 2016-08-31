///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
///<reference path="../../basic/basicShow.ts"/>
///<reference path="../../../messageBox.ts"/>
///<reference path="../../../components/dateSelectorProxy.ts"/>
///<reference path="../singleDateReport/show.ts"/>
module framework.templates.dateChartReport {
    import router = framework.router;
    import Node = JQTable.Node;
    import BasicEndpoint = framework.basic.BasicEndpoint;
    import FrameEvent = framework.basic.FrameEvent;
    import UnitedSelector = Util.UnitedSelector;
    import IDataNode = Util.IDataNode;
    import DataNode = Util.DataNode;

    String.prototype["getWidth"] = function(fontSize)
    {
        var span = document.getElementById("__getwidth");
        if (span == null) {
            span = document.createElement("span");
            span.id = "__getwidth";
            document.body.appendChild(span);
            span.style.visibility = "hidden";
            span.style.whiteSpace = "nowrap";
        }
        span.innerText = this;
        span.style.fontSize = fontSize + "px";

        return span.offsetWidth;
    }

    export let FE_TB_CLICKED:number = framework.route.nextId();
    export let FE_CT_CLICKED:number = framework.route.nextId();

    export function createInstance() : ShowView {
        return new ShowView();
    }

    declare var echarts;


    export interface ItemChart{
        item:number;
        chart:number;
    }

    export interface ChartNodes{
        chart:number;
        nodes:IDataNode[];
    }
    export interface ShowOption extends framework.templates.singleDateReport.ShowOption{
        itemNodes:IDataNode[];
        chartNodes:ChartNodes[];
        itemChart:ItemChart[];
        itemId:string;
        chartId:string;
        chartSelId:string;
        chartUrl:string;
    }


    export class ShowView extends framework.templates.singleDateReport.ShowView{
        unitedSelector : Util.UnitedSelector;
        chartSelector : Util.UnitedSelector;
        mChartUpdate:Util.Ajax;
        mChartResp:Util.ChartCtrl;
        mChartType:number;


        findChartId(itemId:number) : number{
            for (let i = 0; i < this.option().itemChart.length; ++i) {
                if (this.option().itemChart[i].item == itemId) {
                    return this.option().itemChart[i].chart;
                }
            }
            return undefined;
        }

        findChartNode(chartId:number) : IDataNode[]{
            for (let i = 0; i < this.option().chartNodes.length; ++i) {
                if (this.option().chartNodes[i].chart == chartId) {
                    return this.option().chartNodes[i].nodes;
                }
            }
            return undefined;
        }

        private getMaxWidth(opts : any) : number{
            var max = 0;
            var tmp = 0;
            var fontSize = Util.isMSIE() ? 14 : 13;
            for (var i = 0; i < opts.length; ++i){
                tmp = $(opts[i]).text().getWidth(fontSize) + 25;
                if (max < tmp){
                    max = tmp;
                }
            }
            return max;
        }

        updateChartSelect(){
            let changed = false;
            let chartSelId = this.option().chartSelId;
            let ctNodeId = this.findChartId(this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id);


            if (this.chartSelector == undefined){
                $("#" + this.option().chartId).append(
                    "<div id='" + this.option().chartId + "ct' style='width:1200px;height:500px'/>");
                this.mChartType = ctNodeId;
                changed = true;
            }else{
                if (this.mChartType != ctNodeId){
                    this.mChartType = ctNodeId;
                    changed = true;
                    $("#" + chartSelId).empty();
                }
            }

            if (changed){
                this.chartSelector = new UnitedSelector(this.findChartNode(ctNodeId), chartSelId);
                var width = this.getMaxWidth($("#" + chartSelId + " select").children());
                $("#" + chartSelId + " select").css("width", width);
                $("#" + chartSelId + " select")
                    .multiselect({
                        multiple: false,
                        header: false,
                        minWidth: 100,
                        height: '500px',
                        // noneSelectedText: "请选择月份",
                        selectedList: 1
                    })
                    .css("padding", "2px 0 2px 4px")
                    .css("text-align", "left")
                    .css("font-size", "12px");
            }
        }

        onInitialize(opt:any):void{
            this.opt = opt;
            this.mChartUpdate = new Util.Ajax(this.option().chartUrl, false);
            this.unitedSelector = new UnitedSelector(opt.itemNodes,opt.itemId);
            this.unitedSelector.change(()=>{
                var width = this.getMaxWidth($("#" + opt.itemId + " select").children());
                $("#" + opt.itemId + " select").css("width", width);
                $("#" + opt.itemId + " select")
                    .multiselect({
                        multiple: false,
                        header: false,
                        minWidth: 100,
                        height:'100%',
                        // noneSelectedText: "请选择月份",
                        selectedList: 1
                    })
                    .css("padding", "2px 0 2px 4px")
                    .css("text-align", "left")
                    .css("font-size", "12px");
                this.updateChartSelect();
            });
            var width = this.getMaxWidth($("#" + opt.itemId + " select").children());
            $("#" + opt.itemId + " select").css("width", width);
            $("#" + opt.itemId + " select")
                .multiselect({
                    multiple: false,
                    header: false,
                    minWidth: 100,
                    height: '100%',
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                })
                .css("padding", "2px 0 2px 4px")
                .css("text-align", "left")
                .css("font-size", "12px");

            this.updateChartSelect();

            super.onInitialize(opt);

        }

        option():ShowOption{
            return <ShowOption>(this.opt);
        }


        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case FE_TB_CLICKED:
                    $("#" + this.option().host).show();
                    $("#" + this.option().chartId).hide();
                    $("#" + this.option().chartSelId).hide();
                    this.updateTable();
                    break;
                case FE_CT_CLICKED:
                    $("#" + this.option().host).hide();
                    $("#" + this.option().chartId).show();
                    $("#" + this.option().chartSelId).show();
                    this.updateChart();
                    break;
            }

            return super.onEvent(e);
        }


        getParams(date:Util.Date):any{
            return {
                date: this.getDate(date),
                item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id
            };
        }

        getDate(date:Util.Date):string{
            return "" + (date.year + "-" + (date.month == undefined ? 1 :date.month) + "-" + (date.day == undefined ? 1 :date.day));
        }

        update (date:Util.Date){

            let nodes : DataNode[] = this.chartSelector.getNodes();
            this.mChartUpdate.get($.extend({
                chart:nodes[nodes.length - 1].data.id
            }, this.getParams(date))).then((jsonData:any) => {
                this.mChartResp = jsonData;
                if (!$("#" + this.option().chartId).is(":hidden")) {
                    this.updateChart();
                }
            });

            this.mAjaxUpdate.get(this.getParams(date))
                .then((jsonData:any) => {
                    this.resp = jsonData;
                    if (!$("#" + this.option().host).is(":hidden")) {
                        this.updateTable();
                    }
                });
        }

        updateTable():void {
            var name = this.opt.host + "_jqgrid_uiframe";
            var pagername = name + "pager";
            this.mTableAssist = Util.createTable(name, this.resp);

            var parent = $("#" + this.opt.host);
            parent.empty();
            parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
            let jqTable = $("#" + name);
            jqTable.jqGrid(
                this.mTableAssist.decorate({
                    datatype: "local",
                    data: this.mTableAssist.getData(this.resp.data),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    assistEditable:false,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    //editurl: 'clientArray',
                    cellEdit: false,
                    // height: data.length > 25 ? 550 : '100%',
                    // width: titles.length * 200,
                    rowNum: 1000,
                    height: this.resp.data.length > 25 ? 550 : '100%',
                    width: this.resp.width == undefined ? 1200 : this.resp.width,
                    shrinkToFit: true,
                    autoScroll: true
                }));
        }

        exportExcel(date:Util.Date, id:string): void {
            $("#" + id)[0].action = this.opt.exportUrl + "?" +  Util.Ajax.toUrlParam(this.getParams(date));
            $("#" + id)[0].submit();
        }

        private updateChart():void {
            let series = [];
            for (let i in this.mChartResp.yNames) {
                series.push({
                    name: this.mChartResp.yNames[i],
                    type: 'line',
                    smooth: true,
                    // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                    data: this.mChartResp.data[i].length < 1 ? [0] : Util.replaceNull(this.mChartResp.data[i])
                })
            }

            var option = {
                title: {
                    text: this.chartSelector.getDataNode(this.chartSelector.getPath()).data.value
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: this.mChartResp.yNames
                },
                toolbox: {
                    show: true,
                },
                calculable: false,
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: false,
                        data: this.mChartResp.xNames.length < 1 ? [0] : this.mChartResp.xNames
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: series
            };

            echarts.init($("#" + this.option().chartId + "ct")[0]).setOption(option);
        }
    }
}