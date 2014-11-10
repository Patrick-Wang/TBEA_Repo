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
		SBDCY, XNYCY, NYCY, GCL, JT
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

		
		private mDataSetMgr : DataSetManager;
		private mChecked: boolean = false;
 		private mCy: CompanyType = CompanyType.JT;
        private mDw: CompanyType = CompanyType.SB;
        private mMonth: number;
        private mYear: number;
        private mChartIds: string[];
        public init(echartIds: string[], month : number, year : number, jtData: any, zbid : string): void {
           this.mDataSetMgr = new DataSetManager(zbid);
           this.mDataSetMgr.addDataSet(new YDZBDataSet(CompanyType.JT, jtData.yd, jtData.jd, jtData.nd, jtData.ydtb, jtData.jdtb));
           this.mMonth = month;
           this.mYear = year;
           this.mChartIds = echartIds;
           this.updateUI();
        }

		private getCurrentCompany() :　CompanyType{
			if (this.mChecked){
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
		}
		
		private updateNdUI(data : Array<string[]>){
		}
		
		private updateYdtbUI(data : Array<string[]>){
		}
		
		private updateJdtbUI(data : Array<string[]>){
		}

		public onCySelected(val : number){
			var curComp = this.getCurrentCompany();
			this.mCy = val;
			var select = $("#dw select")[0];
			this.mDw = parseInt(select.options[select.selectedIndex].value);
			if (curComp != this.getCurrentCompany()){
				this.updateUI();
			}
		}
		
		public onDwSelected(val : number){
			var curComp = this.getCurrentCompany();
			this.mDw = val;
			if (curComp != this.getCurrentCompany()){
				this.updateUI();
			}
		}
		
		public onChecked(val : boolean){
			this.mChecked = val;
		}

//        private fillData(month: string[]) {
// 
//            if (this.mChartData == undefined) {
//                this.mChartData.push([]);
//                this.mChartData.push([]);
//                for (var i = 1; i <= this.mMonth; ++i) {
//                    month.push(i + "月");
//                    this.mChartData[0].push(Math.floor(Math.random() * (1000 + 1)) + "");
//                    this.mChartData[1].push(Math.floor(Math.random() * (1000 + 1)) + "");
//                }
//            }
//            else {
//                for (var i = 1; i <= this.mMonth; ++i) {
//                    month.push(i + "月");
//                }
//            }
//        }
//
//        private updateEchart(echart: string): void {
//            var month: string[] = [];
//           
//            this.fillData(month);
//            var data = this.mChartData;
//            var option = {
//
//                tooltip: {
//                    trigger: 'axis'
//                },
//                legend: {
//                    data: [this.mYear - 1 + "年", this.mYear + "年"]
//                },
//                toolbox: {
//                    show: true,
//                },
//                calculable: false,
//                xAxis: [
//                    {
//                        type: 'category',
//                        boundaryGap: false,
//                        data: month
//                    }
//                ],
//                yAxis: [
//                    {
//                        type: 'value'
//                    }
//                ],
//                series: [
//                    {
//                        name: this.mYear - 1 + '年',
//                        type: 'line',
//                        smooth: true,
//                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
//                        data: data[0]
//                    },
//                    {
//                        name: this.mYear + '年',
//                        type: 'line',
//                        smooth: true,
//                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
//                        data: data[1]
//                    },
//                ]
//            }
//
//            echarts.init($('#' + echart)[0]).setOption(option);
//
//        }
    }
}