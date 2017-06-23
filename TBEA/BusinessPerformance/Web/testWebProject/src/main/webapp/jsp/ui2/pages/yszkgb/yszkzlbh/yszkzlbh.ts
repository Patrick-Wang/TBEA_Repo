/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../messageBox.ts" />
///<reference path="../../dateSelector.ts"/>
declare var echarts;

module yszkgb { export module yszkzlbh {

    import Endpoint = framework.route.Endpoint;
    import router = framework.router;
    import TextAlign = JQTable.TextAlign;
    class JQGridAssistantFactory {
        public static createTable(gridName:string):JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("月度", "rq", true, TextAlign.Center),
                new JQTable.Node("月度",  "rqa", true, TextAlign.Center),
                new JQTable.Node("5年以上",  "ab"),
                new JQTable.Node("4-5年",  "ac"),
                new JQTable.Node("3-4年",  "ad"),
                new JQTable.Node("2-3年",  "ae"),
                new JQTable.Node("1-2年",  "af"),
                new JQTable.Node("1年以内",  "ag"),
                new JQTable.Node("合计",  "ah")
            ], gridName);
        }
    }

    interface Option extends PluginOption {
        tb:string;
    }

    export class SimpleView extends BasePluginView {

        private static ins : SimpleView = new SimpleView("yszkzlbh");


        private mData:Array<string[]>;
        private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/yszkgb/yszkzlbh/update.do", false);
        private tableAssist:JQTable.JQGridAssistant;
        private mDt: string;
        private mFinalData:any;
        constructor(id:string){
            super(id);
        }

        pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
            return "/BusinessManagement/yszkgb/yszkzlbh/export.do?" + Util.Ajax.toUrlParam({
                date: date,
                companyId: cpType
            });
        }

        private option():Option {
            return <Option>this.mOpt;
        }

        public pluginUpdate(date:string, cpType:Util.CompanyType):void {
            this.mDt = date;
            this.mAjax.get({
                    date: date,
                    companyId: cpType
                })
                .then((jsonData:any) => {
                    this.mData = jsonData;
                    this.refresh();
                });
        }

        public refresh() : void{
            if ( this.mData == undefined){
                return;
            }
            this.$(this.option().ctarea).show();
            this.mFinalData = this.updateTable()
            this.updateEchart(this.mFinalData);
            this.adjustSize();
        }

        private updateEchart(data:any[]):void {

            this.$(this.option().ct).empty();
            this.$(this.option().ct).removeAttr("_echarts_instance_");
            let title = "应收账款账龄变化";

            let legend:Array<string> = ["5年以上",
                "4-5年",
                "3-4年",
                "2-3年",
                "1-2年",
                "1年以内"];

            var xData:string[] = [];
            for (let i = 0; i < data.length; ++i){
                xData.push(data[i][1]);
            }

            let tooltip : any = {
                trigger: 'axis',
            };

            let yAxis : any = [
                {
                    type: 'value',
                }
            ];

            let series = [];
            for (let i = 0; i < legend.length; ++i){
                let rData = [];
                for (let j = 0; j < data.length; ++j){
                    rData.push(data[j][i + 2] == "--" ? 0 : data[j][i + 2]);
                }
                series.push({
                    name: legend[i],
                    type: 'line',
                    smooth: true,
                    // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                    data: rData
                });
            }

            var option = {
                title: {
                    text: title
                },
                tooltip: tooltip,
                legend: {
                    data: legend
                },
                toolbox: {
                    show: true,
                },
                calculable: false,
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: false,
                        data: xData.length < 1 ? [0] : xData
                    }
                ],
                yAxis: yAxis,
                series: series
            };

            echarts.init(this.$(this.option().ct)[0]).setOption(option);

        }

        public init(opt:Option):void {
            super.init(opt);
            framework.router.to(Util.FAMOUS_VIEW).send(Util.MSG_REG, {name: "应收账款账龄变化", plugin:this});
        }

        private adjustSize() {
            var jqgrid = this.jqgrid();
            if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                jqgrid.setGridWidth(this.jqgridHost().width());
            }

            //let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
            //this.tableAssist.resizeHeight(maxTableBodyHeight);

            //if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
            //    jqgrid.setGridWidth(this.jqgridHost().width());
            //}

            this.$(this.option().ct).css("height", "300px");
            this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
            this.updateEchart(this.mFinalData);
        }

        private createJqassist():JQTable.JQGridAssistant{
            var parent = this.$(this.option().tb);
            parent.empty();
            parent.append("<table id='"+ this.jqgridName() +"'></table>");
            this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
            this.tableAssist.mergeRow(0);
            this.tableAssist.mergeTitle();
            return this.tableAssist;
        }

        private updateTable():any {
            this.createJqassist();

            let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
            let month = curDate.getMonth() + 1;
            let data = [];
            for (let i = month + 1; i <= 12; ++i){
                data.push(["上年度", i + "月"].concat(this.mData[i - month - 1]));
            }
            for (let i = 1; i <= month; ++i){
                data.push(["本年度", i + "月"].concat(this.mData[12 - month + i - 1]));
            }

            this.tableAssist.create({
                data: data,
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: '100%',
                width: this.jqgridHost().width(),
                shrinkToFit: true,
                rowNum: 2000,
                autoScroll: true
            });

            return data;
        }

    }
}}