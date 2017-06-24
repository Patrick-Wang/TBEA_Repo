
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../companySelector.ts" />
/// <reference path="../../util.ts" />
///<reference path="../../jqgrid/jqassist.ts"/>
///<reference path="../../messageBox.ts"/>
///<reference path="../yszkgbdef.ts"/>
declare var echarts;
declare var $;
module yszkgb { export module yszkyjtztjqs {

    import TextAlign = JQTable.TextAlign;
    import StatusData = yszkgb.StatusData;
    import BaseEntryPluginView = yszkgb.BaseEntryPluginView;

    class JQGridAssistantFactory {
        public static createTable(gridName:string, readOnly : boolean):JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("日期", "aa", true, TextAlign.Center),
                new JQTable.Node("财务账面应收净收余额", "ab", readOnly),
                new JQTable.Node("保理余额（加项）", "ac", readOnly),
                new JQTable.Node("货发票未开金额（加项）", "ad", readOnly),
                new JQTable.Node("票开货未发金额（减项）", "ae", readOnly),
                new JQTable.Node("预收款冲减应收（加项）", "af", readOnly),
                new JQTable.Node("信用证冲减应收（加项）", "ag", readOnly),
                new JQTable.Node("其他应收科目影响（加项）", "ah", readOnly),
                new JQTable.Node("预警台账应收账款余额 ", "ai", readOnly)
            ], gridName);
        }
    }

    interface Option extends yszkgb.PluginOption {

    }

    class SimplePluginEntryView extends BaseEntryPluginView {


        private mData:Array<string[]>;
        private mAjaxUpdate:Util.Ajax = new Util.Ajax("/BusinessManagement/yszkgb//yszkyjtztjqs/entry/update.do", false);
        private mAjaxSave:Util.Ajax = new Util.Ajax("/BusinessManagement/yszkgb/yszkyjtztjqs/entry/save.do", false);
        private mAjaxSubmit:Util.Ajax = new Util.Ajax("/BusinessManagement/yszkgb/yszkyjtztjqs/entry/submit.do", false);
        private mDt:string;
        private mTableAssist:JQTable.JQGridAssistant;
        private mIsReadOnly:boolean;

        private static ins : SimplePluginEntryView = new SimplePluginEntryView("yszkyjtztjqs");

        constructor(id:string){
            super(id);
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
                    Util.Toast.success("保存 成功");
                } else {
                    Util.Toast.failed(resp.message);
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
                        Util.Toast.failed("有空内容 无法提交");
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
                    Util.Toast.success("提交 成功");
                } else {
                    Util.Toast.failed(resp.message);
                }
            });
        }

        public pluginUpdate(date:string, cpType:Util.CompanyType):void {
            this.mDt = date;
            this.mAjaxUpdate.get({
                    date: date,
                    companyId: cpType
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

        public init(opt:Option):void {
            super.init(opt);
            framework.router.to(Util.FAMOUS_VIEW).send(Util.MSG_REG, {name: "应收账款账面与预警台账调节趋势表", plugin:this});
        }

        adjustSize() {
            if (document.body.clientHeight < 10 || document.body.clientWidth < 10){
                return;
            }

            var jqgrid = this.jqgrid();
            if (this.jqgridHost().width() <= this.jqgridHost().children().eq(0).width()) {
                jqgrid.setGridWidth(this.jqgridHost().width());
            }

            let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
            this.mTableAssist.resizeHeight(maxTableBodyHeight);

            if (this.jqgridHost().width() < this.jqgridHost().children().eq(0).width()) {
                jqgrid.setGridWidth(this.jqgridHost().width());
            }
        }

        private createJqassist():JQTable.JQGridAssistant{
            var parent = this.$(this.mOpt.tb);
            parent.empty();
            parent.append("<table id='"+ this.jqgridName() +"'></table>");
            this.mTableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mIsReadOnly);
            return this.mTableAssist;
        }


        private updateTable():void {
            this.createJqassist();

            let ny = this.mDt.substr(0, this.mDt.length - 2).replace("-", "年") + "月";
            for (var i = 0; i < this.mData.length; ++i) {
                for (var j = 0; j < this.mData[i].length; ++j) {
                    if ("" != this.mData[i][j]) {
                        this.mData[i][j] = parseFloat(this.mData[i][j]) + "";
                    }
                }
            }

            this.mTableAssist.create({
                data: [[ny].concat(this.mData[0])],
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
                rowNum: 1000,
                assistEditable: true
            });

            this.adjustSize();
        }
    }
}}