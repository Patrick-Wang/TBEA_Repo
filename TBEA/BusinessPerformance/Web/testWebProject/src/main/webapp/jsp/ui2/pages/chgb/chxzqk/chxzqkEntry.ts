/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../messageBox.ts"/>
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../chgbdef.ts"/>
declare var $:any;


module pluginEntry {
    export let chxzqk : number = framework.basic.endpoint.lastId();
}

module chgb {
    export module chxzqkEntry {
        import TextAlign = JQTable.TextAlign;
        import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, readOnly : boolean):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "chxzqk_rq_entry", true, TextAlign.Center),
                    new JQTable.Node("原材料", "chxzqk_ycl_entry", false),
                    new JQTable.Node("半成品", "chxzqk_bcp_entry", false),
                    new JQTable.Node("实际库存商品", "chxzqk_sjkcsp_entry", false),
                    new JQTable.Node("已发货未开票", "chxzqk_yfhwkp_entry", false),
                    new JQTable.Node("期货浮动盈亏(盈+，亏-)", "chxzqk_qhfdyk_entry", false),
                    new JQTable.Node("期货平仓盈亏(盈-，亏+)", "chxzqk_qhpc_entry", false),
                    new JQTable.Node("未发货已开票", "chxzqk_wfhykp_entry", false),
                    new JQTable.Node("其他", "chxzqk_qt_entry", false)
                ], gridName);
            }
        }

        interface Option extends framework.basic.PluginOption {
            tb:string;
        }

        class EntryView extends framework.basic.EntryPluginView {
            static ins = new EntryView();
            private mData:Array<string[]>;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("chxzqk/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("chxzqk/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("chxzqk/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;
            private mCompType:Util.CompanyType;
            getId():number {
                return pluginEntry.chxzqk;
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
                    data: JSON.stringify(submitData)
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("保存 成功", ()=>{
                            this.pluginUpdate(dt, cpType);
                        });
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
                        if ("" == submitData[i][j]){
                            Util.MessageBox.tip("有空内容 无法提交")
                            return;
                        }
                    }
                }
                this.mAjaxSubmit.post({
                    date: dt,
                    companyId: cpType,
                    data: JSON.stringify(submitData)
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("提交 成功", ()=>{
                            this.pluginUpdate(dt, cpType);
                        });
                    } else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            }

            public pluginUpdate(date:string, cpType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = cpType;
                this.mAjaxUpdate.get({
                        date: date,
                        companyId: cpType
                    })
                    .then((jsonData: any) => {
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
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "存货性质情况");
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, false);

                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                let ny = this.mDt.substr(0, this.mDt.length - 2).replace("-", "年") + "月";

                for (var i = 0; i < this.mData.length; ++i) {
                    for (var j = 0; j < this.mData[i].length; ++j) {
                        if ("" != this.mData[i][j]) {
                            this.mData[i][j] = parseFloat(this.mData[i][j]) + "";
                        }
                    }
                }


                this.$(name).jqGrid(
                    this.mTableAssist.decorate({
                        datatype: "local",
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
                        rowNum: 150,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        data: this.mTableAssist.getData([[ny].concat(this.mData[0])]),
                        viewrecords: true
                    }));
            }
        }
    }
}