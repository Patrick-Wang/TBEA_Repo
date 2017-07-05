
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../companySelector.ts" />
/// <reference path="../../util.ts" />
///<reference path="../../jqgrid/jqassist.ts"/>
///<reference path="../../messageBox.ts"/>
///<reference path="../yszkgbdef.ts"/>
declare var echarts;
declare var $;
module yszkgb { export module yszkkxxz {

    import TextAlign = JQTable.TextAlign;
    import StatusData = yszkgb.StatusData;
    import BaseEntryPluginView = yszkgb.BaseEntryPluginView;

    class JQGridAssistantFactory {
        public static createTable(gridName:string, readOnly : boolean):JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("日期", "aa", true, TextAlign.Center),
                new JQTable.Node("月度", "ab", false, TextAlign.Center)
                    .append(new JQTable.Node("逾期0-1个月", "ba", readOnly))
                    .append(new JQTable.Node("逾期1-3月", "bb", readOnly))
                    .append(new JQTable.Node("逾期3-6月", "bc", readOnly))
                    .append(new JQTable.Node("逾期6-12月", "bd", readOnly))
                    .append(new JQTable.Node("逾期1年以上", "be", readOnly)),
                new JQTable.Node("未到期(不含质保金)", "ah", readOnly),
                new JQTable.Node("未到期质保金", "ai", readOnly)
            ], gridName);
        }
    }

    interface Option extends yszkgb.PluginOption {

    }

    class SimplePluginEntryView extends BaseEntryPluginView {


        private mData:Array<string[]>;
        private mAjaxUpdate:Util.Ajax = new Util.Ajax("/BusinessManagement/yszkgb//yszkkxxz/entry/update.do", false);
        private mAjaxSave:Util.Ajax = new Util.Ajax("/BusinessManagement/yszkgb/yszkkxxz/entry/save.do", false);
        private mAjaxSubmit:Util.Ajax = new Util.Ajax("/BusinessManagement/yszkgb/yszkkxxz/entry/submit.do", false);
        private mDt:string;
        private mTableAssist:JQTable.JQGridAssistant;
        private mIsReadOnly:boolean;

        private static ins : SimplePluginEntryView = new SimplePluginEntryView("yszkkxxz");

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
            framework.router.to(Util.FAMOUS_VIEW).send(Util.MSG_REG, {name: "应收账款款项性质情况", plugin : this});
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
            this.mTableAssist && this.mTableAssist.resizeHeight(maxTableBodyHeight);

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