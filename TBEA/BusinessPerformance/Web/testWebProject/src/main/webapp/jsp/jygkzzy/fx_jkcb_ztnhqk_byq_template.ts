/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="../companySelector.ts" />
/// <reference path="bglx_selector.ts" />
declare var echarts;
declare var $;
module fx_jkcb_ztnhqk_byq_template {

    class JQGridAssistantFactory {

        public static createFlatTable(gridName: string, title: string[], statusList : string[]): JQTable.JQGridAssistant {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i == 0) {
                    nodes.push(new JQTable.Node(title[i], "_" + i, true, JQTable.TextAlign.Left));
                } else {
                    nodes.push(new JQTable.Node(title[i], "_" + i, true));
                }
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        }
    }

    interface IViewOption {
        tableId: string;
        dateId: string;
        date?: Util.Date;
        companyId: string;
        comps: Util.IDataNode[];
        entryType?: Util.ZBType;
    }

    interface ISubmitResult {
        result: string;
    }


    export class View {
        public static instance: View = new View();
        public static getInstance(): View {
            return View.instance;
        }

        private mStatusList: Array<string>;
        private mTableData: Array<string[]>;
        private mDateSelector: Util.DateSelector;
        private mCompanySelector: Util.CompanySelector;
        private mOpt: IViewOption;
        private mDataSet: Util.Ajax = new Util.Ajax("zb_update.do", false);
        private mCondition: Util.Ajax = new Util.Ajax("zb_entry.do");
        private mBglxViewSelector:Util.BglxViewSelector;
        private mTableAssist: JQTable.JQGridAssistant;
        private mTitleCompanyName : string;
        initInstance(opt: IViewOption) {
            $("#date").html("");
            $("#company").html("");
            this.mOpt = opt;
            this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, this.mOpt.date, this.mOpt.dateId);

            this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.companyId, opt.comps);
            if (opt.comps.length == 1){
                this.mCompanySelector.hide();
            }
            
            this.updateTitle();
            
            this.updateUI();
        }
        
        initBglxViewSelect(opt: IViewOption) {
            this.mBglxViewSelector=new Util.BglxViewSelector(opt.bglxId,opt.curbglx);
        }

        public updateUI() {
            $("#nodatatips").css("display", "none");
            $("#entryarea").css("display", "");
            var date = this.mDateSelector.getDate();
            this.mDataSet.get({ year: date.year, month: date.month, entryType: $("#bglx").find("option:selected").val(), companyId: this.mCompanySelector.getCompany() })
                .then((data: any) => {
                this.mStatusList = data.status;
                this.mTableData = data.values;
                this.updateTitle();
                this.updateTable(this.mOpt.tableId);
                $('#export').css("display", "block");
            });
            
        }

        public updateEntry() {
            $("#nodatatips").css("display", "block");
            $("#entryarea").css("display", "none");
            $('#export').css("display", "none");
            this.mCondition.get({ entryType: "20009" })
                .then((data: any) => {
                this.initInstance({
                        tableId : "table",
                        dateId: "date",
                        companyId: "company",
                        comps : data.comps,
                        date : {
                            month :data.month == null ? undefined : data.month, 
                            year : data.year
                        }
                    });
            });
            
        }
        
        private initMatchArray(entryType: Util.ZBType, month: number, year: number): Array<number> {
            var retArray: Array<number> = [];
            switch (entryType) {
                case Util.ZBType.YDJDMJH:
                    retArray.push(month);
                    retArray.push(month + 1);
                    retArray.push(month + 2);
                    break;
                case Util.ZBType.BY20YJ:
                case Util.ZBType.BY28YJ:
                    if (12 == month) {
                        retArray.push(12)
                        retArray.push(1)
                        retArray.push(2)
                        retArray.push(3)
                    } else {
                        for (var i = 0; i < this.mStatusList.length; i++) {
                            retArray.push(month + 　i);
                        }
                    }
                    break;
                default:
                    break;

            }
            return retArray;
        }

        public exportExcel(fName: string) {
            //var date : Util.Date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            $("#export")[0].action = "fx_jkcb_ztnhqk_export.do?" + Util.Ajax.toUrlParam({ 
                month: this.mDateSelector.getDate().month, 
                year: this.mDateSelector.getDate().year, 
                companyId: this.mCompanySelector.getCompany(),
                entryType: $("#bglx").find("option:selected").val()
            });
            $("#export")[0].submit();
        }
        private updateTitle() {
            var header = "";
            var date = this.mDateSelector.getDate();
            var compName = this.mCompanySelector.getCompanyName();
            header = date.year + "年 " + compName + " " + $("#bglx").find("option:selected").text() + "查看";
            
            $('h1').text(header);
            document.title = header;
        }

        private disableEntry(tableId: string) {
            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<div style='font-size:18px'>没有可修改的记录</div>");
            $("#submit").css("display", "none");
        }

        private enableEntry() {
            $("#submit").css("display", "");
        }

        private updateTable(tableId: string): void {
            var name = tableId + "_jqgrid";
            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            this.enableEntry();
            this.mTableAssist = new JQTable.JQGridAssistant([
            new JQTable.Node("", "empty", true, JQTable.TextAlign.Left),
            new JQTable.Node("水（吨）", "sd")
                .append(new JQTable.Node("用量", "syl"))
                .append(new JQTable.Node("金额（元）", "sje")),
            new JQTable.Node("电（度）", "dd")
                .append(new JQTable.Node("用量", "dyl"))
                .append(new JQTable.Node("金额（元）", "dje")),
            new JQTable.Node("蒸汽（立方米）", "zqlfm")
                .append(new JQTable.Node("用量", "zqyl"))
                .append(new JQTable.Node("金额（元）", "zqje")),
            new JQTable.Node("燃气（线缆）（立方米）", "rqxllfm")
                .append(new JQTable.Node("用量", "rqxlyl"))
                .append(new JQTable.Node("金额（元）", "rqxlje")),
            new JQTable.Node("产值", "cz"),
            new JQTable.Node("产量", "cl")
            ], name);
            for (var i = 0; i < this.mTableData.length; ++i) {
                for (var j = 2; j < this.mTableData[i].length; ++j) {
                    if ("" != this.mTableData[i][j] && "--" != this.mTableData[i][j]) {
                        this.mTableData[i][j] = parseFloat(this.mTableData[i][j]) + "";
                    }
                    else {
                        this.mTableData[i][j] = "--";
                    }
                }
            }
            var data = this.mTableData;
            
            $("#" + name).jqGrid(this.mTableAssist.decorate({
                data: this.mTableAssist.getDataWithId(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: data.length > 25 ? 550 : '100%',
                width: 1000,
                shrinkToFit: true,
                autoScroll: true,
                rowNum: 150
            }));
        }
    }
}