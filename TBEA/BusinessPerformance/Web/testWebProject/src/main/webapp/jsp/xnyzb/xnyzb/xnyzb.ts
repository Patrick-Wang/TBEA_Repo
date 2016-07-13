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
            public static createTable(gridName:string, headers: Util.Header[]):JQTable.JQGridAssistant {

                let nodes : Node[] = [];
                for (let i= 0; i < headers.length; ++i) {
                    let node = Util.parseHeader(headers[i]);
                    if (null != node) {
                        nodes.push(node);
                    }
                }
                return new JQTable.JQGridAssistant(nodes, gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Util.ServResp;
            private mAjax:Util.Ajax;
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            private mCompType:Util.CompanyType;

            getId():number {
                return plugin.xnyzb;
            }

            onEvent(e:framework.route.Event):any {
                switch (e.id) {
                    case framework.basic.FrameEvent.FE_UPDATE:
                    {
                        this.pluginUpdate(e.data.dStart, e.data.dEnd, e.data.compType);
                    }
                        return;
                    case framework.basic.FrameEvent.FE_GET_EXPORTURL:
                    {

                        return this.pluginGetExportUrl(e.data.dStart, e.data.dEnd, e.data.compType);
                    }
                    default:
                        break;
                }
                return super.onEvent(e);
            }

            pluginGetExportUrl(dStart:string, dEnd:string, compType:Util.CompanyType):string {
                return this.option().exportUrl + "?" +  Util.Ajax.toUrlParam({
                        dStart: dStart,
                        dEnd:dEnd,
                        compId: compType
                    });
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(dStart:string, dEnd:string, compType:Util.CompanyType):void {
                this.mCompType = compType;
                this.mAjax.get({
                        dStart: dStart,
                        dEnd:dEnd,
                        compId: compType
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
                this.mAjax = new Util.Ajax(opt.updateUrl, false)
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, opt.title);
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

                if (this.mData.mergeTitle != undefined){
                    tableAssist.mergeTitle();
                }

                if (this.mData.mergeRows != undefined){
                    for (let i =0; i < this.mData.mergeRows.length; ++i){
                        if (this.mData.mergeRows[i].col != undefined){
                            if (this.mData.mergeRows[i].rowStart != undefined && this.mData.mergeRows[i].rowLen != undefined){
                                tableAssist.mergeRow(parseInt(this.mData.mergeRows[i].col),
                                    parseInt(this.mData.mergeRows[i].rowStart),
                                    parseInt(this.mData.mergeRows[i].rowLen));
                            }else if (this.mData.mergeRows[i].rowStart != undefined){
                                tableAssist.mergeRow(parseInt(this.mData.mergeRows[i].col),
                                    parseInt(this.mData.mergeRows[i].rowStart));
                            }else{
                                tableAssist.mergeRow(parseInt(this.mData.mergeRows[i].col));
                            }
                        }
                    }
                }

                if (this.mData.mergeCols != undefined){
                    for (let i =0; i < this.mData.mergeCols.length; ++i){
                        if (this.mData.mergeCols[i].col != undefined){
                            tableAssist.mergeColum(parseInt(this.mData.mergeCols[i].col));
                        }
                    }
                }

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
