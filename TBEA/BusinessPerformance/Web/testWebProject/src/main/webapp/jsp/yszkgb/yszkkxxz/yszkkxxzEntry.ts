/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../yszkgbdef.ts" />
///<reference path="../../messageBox.ts"/>

declare var echarts;
declare var view:yszkgb.FrameView;

module yszkgb {
    export module yszkkxxzEntry {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "aa", true, TextAlign.Center),
                    new JQTable.Node("月度", "ab", true, TextAlign.Center)
                        .append(new JQTable.Node("逾期0-1个月", "ba"))
                        .append(new JQTable.Node("逾期1-3月", "bb"))
                        .append(new JQTable.Node("逾期3-6月", "bc"))
                        .append(new JQTable.Node("逾期6-12月", "bd"))
                        .append(new JQTable.Node("逾期1年以上", "be")),
                    new JQTable.Node("逾期款（含到期保证金）", "ag"),
                    new JQTable.Node("未到期(不含质保金)", "ah"),
                    new JQTable.Node("未到期质保金", "ai"),
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            tb:string;
        }

        class YszkkxxzEntryView extends EntryPluginView {
            private mData:Array<string[]>;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("yszkkxxz/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("yszkkxxz/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("yszkkxxz/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;

            public static newInstance():YszkkxxzEntryView {
                return new YszkkxxzEntryView();
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginSave(dt:string, cpType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    companyId: cpType,
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
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i]);
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
                        companyId: cpType
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

            public init(opt:Option):void {
                super.init(opt);
                view.register("应收账款款项性质情况", this);
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                this.mTableAssist = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                this.$(name).jqGrid(
                    this.mTableAssist.decorate({
                        datatype: "local",
                        multiselect: false,
                        drag: false,
                        resize: false,
                        //autowidth : false,
                        cellsubmit: 'clientArray',
                        cellEdit: true,
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

        export var pluginView = YszkkxxzEntryView.newInstance();
    }
}
