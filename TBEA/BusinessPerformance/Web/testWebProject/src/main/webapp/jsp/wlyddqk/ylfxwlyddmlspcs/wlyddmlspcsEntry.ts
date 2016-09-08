/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../wlyddqkdef.ts" />
///<reference path="../../messageBox.ts"/>
///<reference path="../wlyddqkEntry.ts"/>

declare var echarts;
declare var entryView:wlyddqk.EntryView;

module ylfxwlyddmlspcs {
    export module wlyddmlspcsEntry {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, readOnly : boolean, date : string):JQTable.JQGridAssistant {
                let curDate : Date = new Date(Date.parse(date.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
                let year = curDate.getFullYear();
                let data = [];
                let node : JQTable.Node;
                let titleNodes : JQTable.Node[] = [];
                
                node = new JQTable.Node("产品", "wlyddmlspcsentry_cp", true, TextAlign.Left);
                titleNodes.push(node);
                
                node = new JQTable.Node(year + "年" + month + "月", "wlyddmlspcsentry_riqi", readOnly, TextAlign.Center);

                node.append(new JQTable.Node("成本(万元)", "wlyddmlspcsentry_cb_", readOnly));
                node.append(new JQTable.Node("收入(万元)", "wlyddmlspcsentry_sr_", readOnly));

                titleNodes.push(node);
            
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            }
        }

        interface Option extends wlyddqk.PluginOption {
            tb:string;
        }

        class WlyddmlspcsEntryView extends wlyddqk.BaseEntryPluginView {

            private mData:Array<string[]>;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("wlyddmlspcs/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("wlyddmlspcs/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("wlyddmlspcs/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;
            private mIsReadOnly:boolean;

            public static newInstance():WlyddmlspcsEntryView {
                return new WlyddmlspcsEntryView();
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginSave(dt:string, cpType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length - 2; ++j) {
                        submitData[i].push(allData[i][j + 2]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    companyId: cpType,
                    type:this.mType,
                    data: JSON.stringify(submitData)
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("保存 成功");
                    } else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            }

            public  pluginSubmit(dt:string, cpType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length - 2; ++j) {
                        submitData[i].push(allData[i][j + 2]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                        //if ("" == submitData[i][j]){
                        //    Util.MessageBox.tip("有空内容 无法提交")
                        //    return;
                        //}
                    }
                }
                this.mAjaxSubmit.post({
                    date: dt,
                    companyId: cpType,
                    type:this.mType,
                    data: JSON.stringify(submitData)
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("提交 成功");
                    } else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            }

            public pluginUpdate(date:string, cpType:Util.CompanyType):void {
                this.mDt = date;
                this.mAjaxUpdate.get({
                        date: date,
                        companyId: cpType,
                        type:this.mType
                    })
                    .then((jsonData: StatusData) => {
                        this.mData = jsonData.data;
                        this.mIsReadOnly = jsonData.readOnly;
                        this.refresh();
                    });
            }

            public refresh():void {
                this.raiseReadOnlyChangeEvent(this.mIsReadOnly);
                if (this.mData == undefined) {
                    return;
                }

                this.updateTable();
            }
            
            isSupported( compType:Util.CompanyType):boolean{
                if (this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_ZH ||
                    this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_DYDJ ||
                    this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_CPFL ||
                    this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_ZZY){
                    if (compType == Util.CompanyType.SBGS ||
                        compType == Util.CompanyType.HBGS ||
                        compType == Util.CompanyType.XBC ||
                        compType == Util.CompanyType.TBGS
                    ){
                        return true;
                    }
                }else{
                    if (compType == Util.CompanyType.LLGS ||
                        compType == Util.CompanyType.XLC ||
                        compType == Util.CompanyType.DLGS
                    ){
                        return true;
                    }
                }
                return false;
            }

            public init(opt:Option):void {
                super.init(opt);
                entryView.register("未履约订单毛利水平测算(转型业务口径)", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_ZH));
                //entryView.register("未履约订单毛利水平测算(制造主业-电压等级口径)", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_DYDJ));
                //entryView.register("未履约订单毛利水平测算(制造主业-产品类别口径)", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_CPFL));
                entryView.register("未履约订单毛利水平测算(制造业)", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_ZZY));
                entryView.register("未履约订单毛利水平测算(转型业务口径)", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_XL_ZH));
                entryView.register("未履约订单毛利水平测算(制造业)", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_XL_CPFL));
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, this.mIsReadOnly, this.mDt);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");

                this.$(name).jqGrid(
                    this.mTableAssist.decorate({
                        datatype: "local",
                        multiselect: false,
                        drag: false,
                        resize: false,
                        //autowidth : true,
                        cellsubmit: 'clientArray',
                        cellEdit: true,
                        assistEditable:true,
                        //height: data.length > 25 ? 550 : '100%',
                        // width: titles.length * 200,
                        rowNum: 150,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        data: this.mTableAssist.getData(this.mData),
                        viewrecords: true
                    }));
 
            }
        }

        export var pluginView = WlyddmlspcsEntryView.newInstance();
    }
}