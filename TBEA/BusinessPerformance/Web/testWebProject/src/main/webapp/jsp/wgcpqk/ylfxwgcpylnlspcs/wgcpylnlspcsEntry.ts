/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../messageBox.ts"/>
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
declare var $:any;


module pluginEntry {
    export let wgcpylnlspcs : number = framework.basic.endpoint.lastId();
    export let byq_cpfl_t1:number = framework.basic.endpoint.lastId();
}

module ylfxwgcpylnlspcs {
    export module wgcpylnlspcsEntry {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, readOnly : boolean, date : string):JQTable.JQGridAssistant {
                let curDate : Date = new Date(date);
                let month = curDate.getMonth() + 1;
                let year = curDate.getFullYear();
                let data = [];
                let node : JQTable.Node;
                let titleNodes : JQTable.Node[] = [];
                
                node = new JQTable.Node("产品", "wgcpylnlspcsentry_cp", true, TextAlign.Left);
                titleNodes.push(node);
                
                node = new JQTable.Node(year + "年" + month + "月", "wgcpylnlspcsentry_riqi", false, TextAlign.Center);

                node.append(new JQTable.Node("成本", "wgcpylnlspcsentry_cb_", false));
                node.append(new JQTable.Node("收入", "wgcpylnlspcsentry_sr_", false));

                titleNodes.push(node);
            
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            }
        }

        interface Option extends framework.basic.PluginOption {
            tb:string;
        }

        class EntryView extends framework.basic.EntryPluginView {
            static ins = new EntryView();
            private mData:Array<string[]>;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("../wgcpylnlspcs/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("../wgcpylnlspcs/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("../wgcpylnlspcs/entry/submit.do", false);
            private mDt:string;
            
            private mWgcpqkType:wgcpqk.WgcpqkType;
            private mTableAssist:JQTable.JQGridAssistant;
            private mCompType:Util.CompanyType;
            getId():number {
                return pluginEntry.wgcpylnlspcs;
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginSave(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 1; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 1] = submitData[i][j - 1].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType,
                    wgcpqkType:this.mWgcpqkType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        this.pluginUpdate(dt, compType);
                        Util.MessageBox.tip("保存 成功");
                    } else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            }

            public  pluginSubmit(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 1; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 1] = submitData[i][j - 1].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j - 1]) {
                            Util.MessageBox.tip("有空内容 无法提交")
                            return;
                        }
                    }
                }
                this.mAjaxSubmit.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType,
                    wgcpqkType:this.mWgcpqkType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        this.pluginUpdate(dt, compType);
                        Util.MessageBox.tip("提交 成功");
                    } else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            }

            protected isSupported(compType: Util.CompanyType): boolean {
                if (this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH ||
                    this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_DYDJ ||
                    this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL ||
                    this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL_T1) {
                    if (compType == Util.CompanyType.SBGS ||
                        compType == Util.CompanyType.HBGS ||
                        compType == Util.CompanyType.XBC ||
                        compType == Util.CompanyType.TBGS
                    ) {
                        return true;
                    }
                } else {
                    if (compType == Util.CompanyType.LLGS ||
                        compType == Util.CompanyType.XLC ||
                        compType == Util.CompanyType.DLGS
                    ) {
                        return true;
                    }
                }
                return false;
            }


            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = compType;
                this.mAjaxUpdate.get({
                        date: date,
                        companyId: compType,
                        wgcpqkType: this.mWgcpqkType
                    })
                    .then((jsonData:any) => {
                        this.mData = jsonData.data;
                        this.refresh();
                    });
            }

            public refresh():void {
                if (this.mData == undefined) {
                    return;
                }

                this.updateTable();
            }

            protected init(opt:Option):void {
                
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(pluginEntry.byq_cpfl_t1, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "变压器未履约订单毛利水平测算-产品分类特殊1");

            }
            
            onEvent(e: framework.route.Event): any {
                if (e.road != undefined) {
                    switch (e.road[e.road.length - 1]) {
                        case pluginEntry.byq_cpfl_t1:
                            this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL_T1;
                            break;
                        default:
                            this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL_T1;
                    }
                }
                return super.onEvent(e);
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, false, this.mDt);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");

                let jqTable = this.$(name);
                jqTable.jqGrid(
                    this.mTableAssist.decorate({
                        datatype: "local",
                        data: this.mTableAssist.getData(this.mData),
                        multiselect: false,
                        drag: false,
                        resize: false,
                        assistEditable:true,
                        //autowidth : false,
                        cellsubmit: 'clientArray',
                        //editurl: 'clientArray',
                        cellEdit: true,
                        //height: data.length > 25 ? 550 : '100%',
                        // width: titles.length * 200,
                        rowNum: 20,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        viewrecords: true,
                        //pager: '#' + pagername,
                    }));


            }
        }
    }
}