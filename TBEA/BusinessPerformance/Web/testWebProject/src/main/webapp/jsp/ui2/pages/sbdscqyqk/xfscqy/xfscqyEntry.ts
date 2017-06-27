/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../messageBox.ts"/>
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../sbdscqyqkdef.ts"/>
declare var $:any;


module pluginEntry {
    export let xfscqy : number = framework.basic.endpoint.lastId();
}

module sbdscqyqk {
    export module xfscqyEntry {
        import TextAlign = JQTable.TextAlign;
        import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, readOnly:boolean):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("行业", "hy", true, TextAlign.Center),
                    new JQTable.Node("行业", "hy2", true, TextAlign.Center),
                    new JQTable.Node("签约额(万元)", "qye", readOnly, TextAlign.Right)
                ], gridName);
            }
        }

        interface Option extends framework.basic.PluginOption {
            tb:string;
        }

        class EntryView extends framework.basic.EntryPluginView {
            static ins = new EntryView();
            private mData:Array<string[]>;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("/BusinessManagement/xfscqy/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("/BusinessManagement/xfscqy/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("/BusinessManagement/xfscqy/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;
            getId():number {
                return pluginEntry.xfscqy;
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginSave(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([allData[i][3].replace(new RegExp(' ', 'g'), '')]);
                }
                let tmp = submitData[submitData.length - 1];
                submitData[submitData.length - 1] = submitData[submitData.length - 2];
                submitData[submitData.length - 2] = tmp;
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        this.pluginUpdate(dt, compType);
                        Util.Toast.success("保存 成功");
                    } else {
                        Util.Toast.failed(resp.message);
                    }
                });
            }

            public  pluginSubmit(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([allData[i][3].replace(new RegExp(' ', 'g'), '')]);
                    //if ("" == submitData[i][3]) {
                    //    Util.MessageBox.tip("有空内容 无法提交");
                    //    return;
                    //}
                }
                let tmp = submitData[submitData.length - 1];
                submitData[submitData.length - 1] = submitData[submitData.length - 2];
                submitData[submitData.length - 2] = tmp;
                this.mAjaxSubmit.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        this.pluginUpdate(dt, compType);
                        Util.Toast.success("提交 成功");
                    } else {
                        Util.Toast.failed(resp.message);
                    }
                });
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mAjaxUpdate.get({
                        date: date,
                        companyId: compType
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

            protected init(opt:Option):void {
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "细分市场签约");
            }

            adjustSize() {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                this.mTableAssist.resizeHeight(maxTableBodyHeight);

                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
            }

            private createJqassist():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table>");
                this.mTableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), false);
                this.mTableAssist.mergeColum(0);
                this.mTableAssist.mergeRow(0);
                this.mTableAssist.mergeTitle();

                return this.mTableAssist;
            }

            private updateTable():void {
                this.createJqassist();

                let data = [["传统电力市场"],
                    ["传统电力市场"],
                    ["传统电力市场"],
                    ["传统电力市场"],
                    ["新能源市场"],
                    ["新能源市场"],
                    ["新能源市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["连锁经营"],
                    ["其它"]];
                for (let i = 0; i < data.length; ++i){
                    if (i == data.length - 2){
                        data[i] = data[i].concat(this.mData[i + 1]);
                    }else if(i == data.length - 1){
                        data[i] = data[i].concat(this.mData[i - 1]);
                    }else{
                        data[i] = data[i].concat(this.mData[i]);
                    }
                }

                this.mTableAssist.create({
                    data:data,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.mTableAssist.getColNames().length * 400,
                    shrinkToFit: true,
                    rowNum: 2000,
                    autoScroll: true,
                    assistEditable: true
                });

                this.adjustSize();
            }
        }
    }
}