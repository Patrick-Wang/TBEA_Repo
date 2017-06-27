/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../messageBox.ts"/>
// <reference path="../sbdddcbjpcqkdef.ts" />
///<reference path="../../wlyddqk/wlyddqkEntry.ts"/>

declare var echarts;
declare var entryView:wlyddqk.EntryView;
declare var $:any;
module wlyddqk {
    export module xlkglyddEntry {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, type:wlyddqk.WlyddType, readOnly:boolean, cplb:string[]):JQTable.JQGridAssistant {
                let nodeFirst:JQTable.Node;
                if (type == wlyddqk.WlyddType.SCDY) {
                    nodeFirst = new JQTable.Node("生产单元（项目公司）", "scdy", readOnly, TextAlign.Center, 0, "text", undefined, false);
                } else {
                    let vals:string = "";
                    for (let i in cplb) {
                        if (i < cplb.length - 1) {
                            vals += cplb[i] + ':' + cplb[i] + ';';
                        } else {
                            vals += cplb[i] + ':' + cplb[i];
                        }
                    }

                    nodeFirst = new JQTable.Node("产品类别", "sclb", readOnly, TextAlign.Center, 0, "select",
                        {value: vals}
                        , false);
                }

                return new JQTable.JQGridAssistant([
                    nodeFirst,
                    new JQTable.Node("月产出能力（产值）", "rqa", readOnly),
                    new JQTable.Node("可供履约订单总额", "ab", readOnly),
                    new JQTable.Node("当年可供履约订单总量", "ac", readOnly),
                    new JQTable.Node("n+1月订单量", "ada", readOnly)
                        .append(new JQTable.Node("已排产", "ba", readOnly))
                        .append(new JQTable.Node("未排产", "bc", readOnly)),
                    new JQTable.Node("n+2月订单量", "adb", readOnly)
                        .append(new JQTable.Node("已排产", "ba", readOnly))
                        .append(new JQTable.Node("未排产", "bc", readOnly)),
                    new JQTable.Node("n+3月订单量", "adc", readOnly)
                        .append(new JQTable.Node("已排产", "ba", readOnly))
                        .append(new JQTable.Node("未排产", "bc", readOnly)),
                    new JQTable.Node("n+3月以后履约订单", "ae", readOnly),
                    new JQTable.Node("次年及以后可供履约订单排产值", "af", readOnly),
                    new JQTable.Node("交货期待定产值", "ag", readOnly),
                    new JQTable.Node("外协", "ah", readOnly)
                ], gridName);
            }
        }

        interface Option extends wlyddqk.PluginOption {
            tb:string;
        }

        class XlkglyddEntryView extends wlyddqk.BaseEntryPluginView {

            private mData:wlyddqk.EntryLyddData;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("/BusinessManagement/sbdddcbjpcqk/xlkglydd/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("/BusinessManagement/sbdddcbjpcqk/xlkglydd/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("/BusinessManagement/sbdddcbjpcqk/xlkglydd/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;

            public static newInstance():XlkglyddEntryView {
                return new XlkglyddEntryView();
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            isSupported(compType:Util.CompanyType):boolean {
                if (compType == Util.CompanyType.LLGS || compType == Util.CompanyType.XLC || compType == Util.CompanyType.DLGS) {
                    return true;
                }
                return false;
            }

            public pluginSave(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    type: this.mType,
                    companyId: compType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.Toast.success("保存 成功");
                        this.pluginUpdate(dt, compType);
                    } else {
                        Util.Toast.failed(resp.message);
                    }
                });
            }

            public  pluginSubmit(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j]) {
                            Util.Toast.failed("有空内容 无法提交")
                            return;
                        }
                    }
                }
                this.mAjaxSubmit.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    type: this.mType,
                    companyId: compType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.Toast.success("提交 成功");
                        this.pluginUpdate(dt, compType);
                    } else {
                        Util.Toast.failed(resp.message);
                    }
                });
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mAjaxUpdate.get({
                        type: this.mType,
                        date: date,
                        companyId: compType
                    })
                    .then((jsonData:wlyddqk.EntryLyddData) => {
                        this.mData = jsonData;
                        this.refresh();
                    });
            }

            public refresh():void {
                this.raiseReadOnlyChangeEvent(this.mData.statusData.readOnly);
                if (this.mData == undefined) {
                    return;
                }

                this.updateTable();
            }

            public init(opt:Option):void {
                super.init(opt);
                entryView.register("可供履约订单情况(产品类别口径)", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.SCLB));
                entryView.register("可供履约订单情况(生产单元口径)", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.SCDY));
                $.extend($.jgrid.edit, {
                    bSubmit: "确定"
                });
            }


            public adjustSize() {

                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() <= this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                //let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                //this.mTableAssist.resizeHeight(maxTableBodyHeight);
                //
                //if (this.jqgridHost().width() < this.jqgridHost().find(".ui-jqgrid").width()) {
                //    jqgrid.setGridWidth(this.jqgridHost().width());
                //}
            }

            private createJqassist():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb);
                var pagername = this.jqgridName() + "pager";
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table><div id='" + pagername + "'></div>");
                this.mTableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mType, false, this.mData.cplb);
                return this.mTableAssist;
            }

            private updateTable():void {
                this.createJqassist();

                this.mTableAssist.create({
                    dataWithId: this.mData.statusData.data,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.mTableAssist.getColNames().length * 400,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 15,
                    assistEditable: true,
                    pager: '#' + this.jqgridName() + "pager"
                });

                this.adjustSize();
            }

            /*private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, this.mType, false, this.mData.cplb);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                let jqTable = this.$(name);
                jqTable.jqGrid(
                    this.mTableAssist.decorate({
                        datatype: "local",
                        data: this.mTableAssist.getDataWithId(this.mData.statusData.data),
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
                        pager: '#' + pagername,
                    }));
            }*/
        }

        export var pluginView = XlkglyddEntryView.newInstance();
    }
}
