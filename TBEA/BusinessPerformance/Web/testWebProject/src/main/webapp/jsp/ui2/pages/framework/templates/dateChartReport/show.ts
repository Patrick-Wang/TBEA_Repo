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
    import ShowView = framework.templates.singleDateReport.ShowView;


    export let FE_TB_CLICKED:number = framework.route.nextId();
    export let FE_CT_CLICKED:number = framework.route.nextId();
    export let FE_ZL_APPROVED:number = framework.route.nextId();

    export function create() : ShowView {
        return new SimpleShowView();
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


    class SimpleShowView extends framework.templates.singleDateReport.ShowView{
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


        updateChartSelect(){
            let changed = false;
            let chartSelId = this.option().chartSelId;
            let ctNodeId = this.findChartId(this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id);

            if (this.chartSelector == undefined){
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
            }
        }

        onInitialize(opt:any):void{
            this.opt = opt;
            this.mChartUpdate = new Util.Ajax(this.option().chartUrl, false);
            this.unitedSelector = new UnitedSelector(opt.itemNodes,opt.itemId);
            this.unitedSelector.change(()=>{
                this.updateChartSelect();
                this.adjustHeader();
            });
            this.updateChartSelect();

            $(window).resize(()=>{
                this.adjustHeader();
            });

            super.onInitialize(opt);
            this.adjustHeader();

        }

        option():ShowOption{
            return <ShowOption>(this.opt);
        }


        //onEvent(e:framework.route.Event):any {
        //    switch (e.id) {
        //        case FE_TB_CLICKED:
        //            $("#" + this.option().host).show();
        //            $("#" + this.option().chartId).hide();
        //            $("#" + this.option().chartSelId).hide();
        //            this.updateTable();
        //            break;
        //        case FE_CT_CLICKED:
        //            $("#" + this.option().host).hide();
        //            $("#" + this.option().chartId).show();
        //            $("#" + this.option().chartSelId).show();
        //            this.updateChart();
        //            break;
        //    }
        //
        //    return super.onEvent(e);
        //}


        getParams(date:Util.Date):any{
            return {
                date: this.getDate(date),
                item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id
            };
        }


        update (date:Util.Date){
            let nodes : DataNode[] = this.chartSelector.getNodes();


            this.mAjaxUpdate.get(this.getParams(date))
                .then((jsonData:any) => {
                    this.resp = jsonData;
                    this.mChartUpdate.get($.extend({
                        chart:nodes[nodes.length - 1].data.id
                    }, this.getParams(date))).then((jsonData:any) => {
                        this.mChartResp = jsonData;
                        this.updateTable();
                    });
                });
        }

        adjustHeader(){
            $("#headerHost").removeCss("width");
            $(".page-header").removeClass("page-header-double");
            if ($("#headerHost").height() > 40){
                $(".page-header").addClass("page-header-double");
                $("#headerHost").css("width", $("#sels").width() + "px");
            }
        }

        adjustSize(){
            var jqgrid = this.jqgrid();
            if(this.mChartResp){
                $("#" + this.option().chartId).css("height", "300px");
                $("#" + this.option().chartId).css("width", $("#" + this.opt.host).width() + "px");

            }

            if ($("#" + this.opt.host).width() != $("#" + this.opt.host + " .ui-jqgrid").width()) {
                jqgrid.setGridWidth($("#" + this.opt.host).width());
            }

            //let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
            //this.tableAssist.resizeHeight(maxTableBodyHeight);

            //if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
            //    jqgrid.setGridWidth(this.jqgridHost().width());
            //}
            if(this.mChartResp){
                this.updateChart();
            }
            this.adjustHeader();
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

            echarts.init($("#" + this.option().chartId)[0]).setOption(option);
        }
    }
}