/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module zbhz_overview {

	 enum CompanyType {
		SB, HB, XB, TB, LL, XL, DL, 
		GY, XNY,
		TCNY, NDGS, ZJWL,
		JCK,   GCGS,  
		ZH, 
		SBDCY, XNYCY, NYCY, GCL, JT,
		ALL = 100
	}

	 
	 class YDZBDataSet{
	 	private companyId : CompanyType = CompanyType.JT;
	 	private mYd: Array<string[]>;
	 	private mJd: Array<string[]>;
	 	private mNd: Array<string[]>;
	 	private mYdtb: Array<string[]>;
	 	private mJdtb: Array<string[]>;
	 	
	 	public getYd(): Array<string[]>{
	 		return this.mYd;
	 	}
	 	public getJd(): Array<string[]>{
	 		return this.mJd;
	 	}
	 	public getNd(): Array<string[]>{
	 		return this.mNd;
	 	}
	 	public getYdtb(): Array<string[]>{
	 		return this.mYdtb;
	 	}
	 	public getJdtb(): Array<string[]>{
	 		return this.mJdtb;
	 	}
	 	
	 	public getType() : CompanyType{
	 		return this.companyId;
	 	}
	 	
	 	constructor(comId : CompanyType, 
		 	yd : Array<string[]>, 
		 	jd : Array<string[]>,
		 	nd : Array<string[]>,
		 	ydtb : Array<string[]>,
		 	jdtb : Array<string[]>){
	 		this.companyId = comId;
	 		this.mYd = yd;
	 		this.mJd = jd;
	 		this.mNd = nd;
	 		this.mYdtb = ydtb;
	 		this.mJdtb = jdtb;
	 	}
	 }

	class DataSetManager{
		private mZbid : string;
		private dataSetMap = {};
		
		public constructor(zbid : string){
			this.mZbid = zbid;
		}
		
		public addDataSet(dataSet : YDZBDataSet){
			this.dataSetMap[dataSet.getType() + ""] = dataSet;
		}
		
		public getData(ty : CompanyType, callBack : (dataSet: YDZBDataSet) => void) : void{
			if (this.dataSetMap[ty + ""] == undefined){
				$.ajax({
		             type: "GET",
		             url: "zbhz_overview_update.do?" + "companyId=" + ty + "&zb=" + this.mZbid,
		             success: (data : any) =>{
		             	var dataObj = JSON.parse(data);
		             	this.addDataSet(new YDZBDataSet(ty, dataObj.yd, dataObj.jd, dataObj.nd, dataObj.ydtb, dataObj.jdtb))
		             	callBack(this.dataSetMap[ty + ""]);
		             },
		             error : (XMLHttpRequest, textStatus, errorThrown)=>{
		             	callBack(null);
		             }
         		});
			} 
			else {
				callBack(this.dataSetMap[ty + ""]);
			}
		}
		
	}
	
		enum ChartType{
				YDZB, JDZB, NDZB, YDTQ, JDTQ
		}
	
     export class View {
        private static ins: View;

        public static newInstance(): View {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        }

		private mSelectCy : boolean = true;
		private mDataSetMgr : DataSetManager;
 		private mCy: CompanyType = CompanyType.JT;
        private mDw: CompanyType = CompanyType.ALL;
        private mMonth: number;
        private mYear: number;
        private mChartIds: string[];
        public init(echartIds: string[], month : number, year : number, zbid : string): void {
           this.mDataSetMgr = new DataSetManager(zbid);
           this.mMonth = month;
           this.mYear = year;
           this.mChartIds = echartIds;
           this.updateUI();
        }

		private getCurrentCompany() :　CompanyType{
			if (this.mDw != CompanyType.ALL){
				return this.mDw;
			}
			else{
				return this.mCy;
			}
		}

		private updateUI() : void{
			this.mDataSetMgr.getData(this.getCurrentCompany(), (dataSet : YDZBDataSet) =>{
				if (dataSet != null){
					this.updateYdUI(dataSet.getYd());
					this.updateJdUI(dataSet.getJd());
					this.updateNdUI(dataSet.getNd());
					this.updateYdtbUI(dataSet.getYdtb());
					this.updateJdtbUI(dataSet.getJdtb());
				}
			});
		}

		private getMonth(): string[]{
			var month: string[] = [];
		   for (var i = 0; i < this.mMonth; ++i){
		   		month.push((i + 1) + "月")
		   }
		   return month;
		} 

		private updateYdUI(data : Array<string[]>){

            var month: string[] = this.getMonth();
            var legend = [(this.mMonth) + "月计划", (this.mMonth) + "月完成", (this.mMonth) + "月计划完成率"];
            var option = {
				title : {
				        text: '月度指标完成情况'
				},	   
                tooltip: {
                    trigger: 'axis'
                },
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
                        boundaryGap: true,
                        data: month
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
			        {
			            type : 'value',
			            axisLabel : {
			                formatter: '{value} %'
			            }
			        }
                ],
                series: [
                    {
                        name: legend[0],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[0]
                    },
                    {
                        name: legend[1],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[1]
                    },
                    {
                        name: legend[2],
                        type: 'line',
                        smooth: true,
                        yAxisIndex: 1,
                        data: data[2]
                    }
                ]
            }
            echarts.init($('#' + this.mChartIds[ChartType.YDZB])[0]).setOption(option);
		}

		private updateJdUI(data : Array<string[]>){

			var jdCount = data[0].length;
			var jd = [];
			 var legend = ["季度计划", "季度累计", "季度完成率"];
			for (var i = 1; i <= jdCount; ++i){
				jd.push("第" + i + "季度");
			}
			
            var option = {
				title : {
				        text: '季度指标完成情况'
				},
                tooltip: {
                    trigger: 'axis'
                },
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
                        boundaryGap: true,
                        data: jd
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
			        {
			            type : 'value',
			            axisLabel : {
			                formatter: '{value} %'
			            }
			        }
                ],
                series: [
                    {
                        name: legend[0],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[0]
                    },
                    {
                        name: legend[1],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[1]
                    },
                    {
                        name: legend[2],
                        type: 'line',
                        smooth: true,
                        yAxisIndex: 1,
                        data: data[2]
                    }
                ]
            }
            echarts.init($('#' + this.mChartIds[ChartType.JDZB])[0]).setOption(option);
		
		}
		
		private updateNdUI(data : Array<string[]>){
			var jdCount = data[0].length;
			var jd = [];
			var legend = ["年度计划", "年度累计", "累计完成率"];
			var xYear = [this.mYear - 2 + "年", this.mYear - 1 + "年", this.mYear + "年",]
			
			
			for (var i = 1; i <= jdCount; ++i){
				jd.push("第" + i + "季度");
			}
			
            var option = {
				title : {
			        text: '年度指标完成情况'
			    },
                tooltip: {
                    trigger: 'axis'
                },
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
                        boundaryGap: true,
                        data: xYear
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
			        {
			            type : 'value',
			            axisLabel : {
			                formatter: '{value} %'
			            }
			        }
                ],
                series: [
                    {
                        name: legend[0],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[0]
                    },
                    {
                        name: legend[1],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[1]
                    },
                    {
                        name: legend[2],
                        type: 'line',
                        smooth: true,
                        yAxisIndex: 1,
                        data: data[2]
                    }
                ]
            }
            echarts.init($('#' + this.mChartIds[ChartType.NDZB])[0]).setOption(option);
		}
		
		private updateYdtbUI(data : Array<string[]>){
		    var month: string[] = this.getMonth();
            var legend = [(this.mYear - 1) + "年*月完成", (this.mYear) + "年*月完成", "同比增长率"];
            var option = {
				title : {
			        text: '月度同期对比'
			    },
                tooltip: {
                    trigger: 'axis'
                },
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
                        boundaryGap: true,
                        data: month
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
			        {
			            type : 'value',
			            axisLabel : {
			                formatter: '{value} %'
			            }
			        }
                ],
                series: [
                    {
                        name: legend[0],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[1]
                    },
                    {
                        name: legend[1],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[0]
                    },
                    {
                        name: legend[2],
                        type: 'line',
                        smooth: true,
                        yAxisIndex: 1,
                        data: data[2]
                    }
                ]
            }
            echarts.init($('#' + this.mChartIds[ChartType.YDTQ])[0]).setOption(option);
		}
		
		private updateJdtbUI(data : Array<string[]>){
			var jdCount = data[0].length;
			var jd = [];
			var legend = [this.mYear - 1 + "年季度累计", this.mYear + "年季度累计", "同比增长率"];
			for (var i = 1; i <= jdCount; ++i){
				jd.push("第" + i + "季度");
			}
			
            var option = {
				title : {
			        text: '季度同期对比'
			    },
                tooltip: {
                    trigger: 'axis'
                },
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
                        boundaryGap: true,
                        data: jd
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
			        {
			            type : 'value',
			            axisLabel : {
			                formatter: '{value} %'
			            }
			        }
                ],
                series: [
                    {
                        name: legend[0],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[1]
                    },
                    {
                        name: legend[1],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[0]
                    },
                    {
                        name: legend[2],
                        type: 'line',
                        smooth: true,
                        yAxisIndex: 1,
                        data: data[2]
                    }
                ]
            }
            echarts.init($('#' + this.mChartIds[ChartType.JDTQ])[0]).setOption(option);
		}

		public onCySelected(val : number){
			this.mCy = val;
			var select = $("#dw select")[0];
			this.mDw = parseInt(select.options[select.selectedIndex].value);
		}
		
		public onDwSelected(val : number){
			this.mDw = val;
		}
    }
}