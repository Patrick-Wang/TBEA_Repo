/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../messageBox.ts"/>
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../sbdscqyqkdef.ts"/>
declare var $:any;


module pluginEntry {
    export let xfcpqy : number = framework.basic.endpoint.lastId();

    export let xfcpqy_byq:number = framework.basic.endpoint.lastId();

    export let xfcpqy_xl:number = framework.basic.endpoint.lastId();
}

module sbdscqyqk {
    export module xfcpqyEntry {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, readOnly : boolean, date : string):JQTable.JQGridAssistant {
                let curDate : Date = new Date(Date.parse(date.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
                let year = curDate.getFullYear();
                let data = [];
                let node : JQTable.Node;
                let titleNodes : JQTable.Node[] = [];

                node = new JQTable.Node("产品", "xfcpqyEntry_cp", true, TextAlign.Left);
                titleNodes.push(node);

                node = new JQTable.Node(year + "年" + month + "月", "xfcpqyEntry_riqi", false, TextAlign.Center);

                node.append(new JQTable.Node("签约额(万元)", "xfcpqyEntry_cb_", false));

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
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("../xfcpqy/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("../xfcpqy/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("../xfcpqy/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;
            private mCompType:Util.CompanyType;

            private mType:sbdscqyqk.SbdscqyqkType;

            getId():number {
                return pluginEntry.xfcpqy;
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginSave(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 2; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 2] = submitData[i][j - 2].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType,
                    type: this.mType
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
                    for (var j = 2; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 2] = submitData[i][j - 2].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j - 2]) {
                            Util.MessageBox.tip("有空内容 无法提交")
                            return;
                        }
                    }
                }
                this.mAjaxSubmit.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType,
                    type: this.mType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        this.pluginUpdate(dt, compType);
                        Util.MessageBox.tip("提交 成功");
                    } else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = compType;
                this.mAjaxUpdate.get({
                        date: date,
                        companyId: compType,
                        type: this.mType
                    })
                    .then((jsonData:any) => {
                        this.mData = jsonData;
                        this.refresh();
                    });
            }

            public refresh():void {
                if (this.mData == undefined) {
                    return;
                }

                this.updateTable();
            }

            isSupported(compType:Util.CompanyType):boolean {
                if (this.mType == sbdscqyqk.SbdscqyqkType.BYQ) {
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

            protected init(opt:Option):void {
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(pluginEntry.xfcpqy_byq, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "细分产品签约情况及趋势（国内市场制造业签约）");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(pluginEntry.xfcpqy_xl, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "细分产品签约情况及趋势（国内市场制造业签约）");
            }


            onEvent(e:framework.route.Event):any {
                if (e.road != undefined) {
                    switch (e.road[e.road.length - 1]) {
                        case pluginEntry.xfcpqy_byq:
                            this.mType = sbdscqyqk.SbdscqyqkType.BYQ;
                            break;
                        case pluginEntry.xfcpqy_xl:
                            this.mType = sbdscqyqk.SbdscqyqkType.XL;
                            break;
                        default:
                            this.mType = sbdscqyqk.SbdscqyqkType.BYQ;
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
                        rowNum: 1000,
                        height: this.mData.length > 25 ? 550 : '100%',
                        width: 700,
                        shrinkToFit: true,
                        autoScroll: true,
                        viewrecords: true,
                        //pager: '#' + pagername,
                    }));
            }
        }
    }
}
