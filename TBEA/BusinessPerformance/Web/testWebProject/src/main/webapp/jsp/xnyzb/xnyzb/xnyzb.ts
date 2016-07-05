/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../xnyzbdef.ts"/>

module plugin {
    export let xnyzb : number = framework.basic.endpoint.lastId();
}

module xnyzb {
    export module xnyzb {
        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        class JQGridAssistantFactory {

            static  parseHeader(header:Util.Header): Node{
                let node:Node = Node.create({name : header.name, align : TextAlign.Center});
                if (header.sub != undefined) {
                    for (let i = 0; i < header.sub.length; ++i) {
                        node.append(JQGridAssistantFactory.parseHeader(header.sub[i]));
                    }
                }
                return node;
            }

            public static createTable(gridName:string, headers: Util.Header[]):JQTable.JQGridAssistant {

                let nodes : Node[] = [];
                for (let i= 0; i < headers.length; ++i){
                    nodes.push(JQGridAssistantFactory.parseHeader(headers[i]))
                }
                return new JQTable.JQGridAssistant(nodes, gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Util.ServResp;
            private mAjax:Util.Ajax = new Util.Ajax("xnyzb.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            mDStart:string;
            mDEnd;string;
            private mCompType:Util.CompanyType;

            getId():number {
                return plugin.xnyzb;
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "xnyzbExport.do?" + Util.Ajax.toUrlParam({
                        dStart: this.mDStart,
                        dEnd: this.mDEnd
                    });
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = compType;
                this.mAjax.get({
                        dStart: this.mDStart,
                        dEnd: this.mDEnd
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

                this.updateTable();
            }

            public init(opt:Option):void {
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "新能源周报");

                this.mDStart="2016-1-1";
                $("#dstart").val(2016 + "/" + 1 + "/" + 1);
                $("#dstart").datepicker({
                    //            numberOfMonths:1,//显示几个月
                    //            showButtonPanel:true,//是否显示按钮面板
                    dateFormat: 'yy/mm/dd',//日期格式
                    //            clearText:"清除",//清除日期的按钮名称
                    //            closeText:"关闭",//关闭选择框的按钮名称
                    yearSuffix: '年', //年的后缀
                    showMonthAfterYear: true,//是否把月放在年的后面
                    defaultDate: 2016 + "/" + 1 + "/" + 1,//默认日期
                    //            minDate:'2011-03-05',//最小日期
                    maxDate: 2019 + "/" + 1 + "/" + 1,//最大日期
                    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                    dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                    dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                    dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                    onSelect: (selectedDate) => {//选择日期后执行的操作
                        var d: Date = new Date(selectedDate);
                       this.mDStart = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
                    }
                });
                $("#dEnd").val(2016 + "/" + 1 + "/" + 1);
                this.mDEnd="2016-1-1";
                $("#dEnd").datepicker({
                    //            numberOfMonths:1,//显示几个月
                    //            showButtonPanel:true,//是否显示按钮面板
                    dateFormat: 'yy/mm/dd',//日期格式
                    //            clearText:"清除",//清除日期的按钮名称
                    //            closeText:"关闭",//关闭选择框的按钮名称
                    yearSuffix: '年', //年的后缀
                    showMonthAfterYear: true,//是否把月放在年的后面
                    defaultDate: 2016 + "/" + 1 + "/" + 1,//默认日期
                    //            minDate:'2011-03-05',//最小日期
                    maxDate: 2019 + "/" + 1 + "/" + 1,//最大日期
                    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                    dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                    dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                    dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                    onSelect: (selectedDate) => {//选择日期后执行的操作
                        var d: Date = new Date(selectedDate);
                        this.mDEnd = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
                    }
                });
                $("#ui-datepicker-div").css('font-size', '0.8em'); //改变大小;
            }

			private getMonth():number{
				let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
				return month;
			}
			
            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mData.header);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                tableAssist.mergeRow(0);
                tableAssist.mergeRow(1);
                tableAssist.mergeRow(2);
                tableAssist.mergeRow(3);
                tableAssist.mergeColum(3)
                this.$(name).jqGrid(
                    tableAssist.decorate({
						datatype: "local",
						data: tableAssist.getDataWithId(this.mData.data),
                        multiselect: false,
                        drag: false,
                        resize: false,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        rowNum: 1000,
                        viewrecords : true
                    }));
            }
        }
    }
}
