
/// <reference path="dateSelector.ts" />
/// <reference path="companySelector.ts" />
/// <reference path="util.ts" />
///<reference path="jqgrid/jqassist.ts"/>
///<reference path="messageBox.ts"/>
declare var echarts;
declare var $;
module approve_template {

    class JQGridAssistantFactory {

        public static createFlatTable(gridName: string, title: string[]): JQTable.JQGridAssistant {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i == 0) {
                    nodes.push(new JQTable.Node(title[i], "_" + i, true, JQTable.TextAlign.Left, 50));
                } else {
                    nodes.push(new JQTable.Node(title[i], "_" + i, false, JQTable.TextAlign.Right, 50));
                }

            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        }

        public static createQnjhTable(gridName: string, title: string[], ids: string[]): JQTable.JQGridAssistant {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i < 1) {
                    nodes.push(new JQTable.Node(title[i], ids[i], true, JQTable.TextAlign.Left, 50));
                } else {
                    nodes.push(new JQTable.Node(title[i], ids[i], false, JQTable.TextAlign.Right, 50));
                }

            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        }

        public static createSjTable(gridName: string, title: string[], ids: string[]): JQTable.JQGridAssistant {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i <= 1) {
                    nodes.push(new JQTable.Node(title[i], ids[i], true, JQTable.TextAlign.Left, 50));
                } else {
                    nodes.push(new JQTable.Node(title[i], ids[i], false, JQTable.TextAlign.Right, 50));
                }

            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        }

    }

    export interface IViewOption {
        tableApproveId: string;
        tableUnapproveId: string;
        dateId: string;
        companyId: string;
        comps: Util.IDataNode[];
        firstCompany?: number;
        date: Util.Date;
        approveType: Util.ZBType;
    }

    interface ISubmitResult {
        result: boolean;
    }


    function transposition(data: string[][]): Array<string[]> {
        var dataRet: Array<string[]> = [];

        for (var i = 0; i < data[0].length; ++i) {
            dataRet.push([]);
            for (var j = 0; j < data.length; ++j) {
                dataRet[i].push(data[j][i]);
            }
            dataRet[i] = dataRet[i].reverse();
        }
        return dataRet;
    }

    function resize(data: string[], size) {
        if (data.length < size) {
            for (var i = data.length; i < size; ++i) {
                data.push("--");
            }
            return data;
        } else {
            var dataNew = [];
            for (var i = 0; i < size; ++i) {
                dataNew.push(data[i]);
            }
            return dataNew;
        }
    }

    interface ISubView {
        getApprovedData(): number[][];
        getUnapprovedData(): number[][];
        process(data: any, date: Util.Date, companies: Util.IData[]): void;
        getDate(): Util.Date;
    }

    class QNJHSubView implements ISubView {

        private mTableUnapproveAssist: JQTable.JQGridAssistant;
        private mTableApproveAssist: JQTable.JQGridAssistant;
        private mOpt: IViewOption;
        private mData: any;
        private mApproveColZbIds: string[];
        private mDate: Util.Date;
        constructor(opt: IViewOption) {
            this.mOpt = opt;
        }

        getApprovedData(): number[][] {
            if (this.mTableApproveAssist != null) {
                var ids: any = this.mTableApproveAssist.getCheckedRowIds();
                $(ids).each((i) => {
                    ids[i] = parseInt(ids[i]);
                });
                return [ids];
            } else {
                return [[]];
            }
        }
               
        //[[compId ...]]
        getUnapprovedData(): number[][] {
            if (this.mTableUnapproveAssist != null) {
                var ids: any = this.mTableUnapproveAssist.getCheckedRowIds();
                $(ids).each((i) => {
                    ids[i] = parseInt(ids[i]);
                });
                return [ids];
            } else {
                return [[]];
            }
        }

        getDate(): Util.Date {
            return this.mData;
        }
        
        //[[compId ,zbId, zbName, value] ...] approved 
        //[[compId ,zbId, zbName, value] ...] unapproved
        process(data: any, date: Util.Date, companies: Util.IData[]): void {
            this.mData = date;
            if (data[0].length > 0) {
                this.mTableApproveAssist = this.updateTable(data[0], companies, this.mOpt.tableApproveId, "未审核数据");
            }

            if (data[1].length > 0) {
                this.mTableUnapproveAssist = this.updateTable(data[1], companies, this.mOpt.tableUnapproveId, "已审核数据");
            }
        }

        //comps : selected companies
        private updateTable(rawData: string[][], comps: Util.IData[], tableId: string, caption: string): JQTable.JQGridAssistant {
            var tmpData = [];
            var title = ["单位名称"];
            var colZbIds = ["dw"];
            var zbColMap = {};
            var compMap = {};
            var companies = [];
            // remove unused company
            $(comps).each((i) => {
                $(rawData).each((j) => {
                    if (rawData[j][0] == "" + comps[i].id) {
                        compMap["c_" + comps[i].id] = comps[i];
                    }
                });
            });

            for (var i in compMap) {
                companies.push(compMap[i]);
            }

            //make title
            $(rawData).each((i) => {
                if (!Util.isExist(zbColMap["_" + rawData[i][1]])) {
                    colZbIds.push(rawData[i][1]);
                    title.push(rawData[i][2])
                    zbColMap["_" + rawData[i][1]] = title.length;
                }
            });

            //make data
            $(companies).each((i) => {
                tmpData.push([companies[i].id, companies[i].value]);
                $(rawData).each((j) => {
                    if (rawData[j][0] == "" + companies[i].id) {
                        if (tmpData[i].length <= zbColMap["_" + rawData[j][1]]) {
                            resize(tmpData[i], zbColMap["_" + rawData[j][1]]);
                        }
                        tmpData[i][zbColMap["_" + rawData[j][1]]] = rawData[j][3];
                    }
                });
            });

            var name = tableId + "_jqgrid";
            var jqAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createQnjhTable(name, title, colZbIds);


            var maxLength = 0;
            for (let i: any = 0; i < tmpData.length; ++i) {
                if (maxLength < tmpData[i].length) {
                    maxLength = tmpData[i].length;
                }
            }

            for (let i: any = 0; i < tmpData.length; ++i) {
                for (var j = 2; j < maxLength; ++j) {
                    if (j < tmpData[i].length) {
                        if (title[j - 1] == '人数') {
                            tmpData[i][j] = Util.formatInt(tmpData[i][j]) + "";
                        } else if (title[j - 1] == '三项费用率(%)') {
                            tmpData[i][j] = Util.formatPercent(tmpData[i][j]) + "";
                        } else if (title[j - 1] == '人均收入' || title[j - 1] == '人均利润' || title[j - 1] == '精铝块13项元素和值（ppm）') {
                            tmpData[i][j] = Util.formatFordot(tmpData[i][j]) + "";
                        } else if (title[j - 1] == '净资产收益率(%)' || title[j - 1] == '销售利润率(%)') {
                            tmpData[i][j] = Util.formatPercentSignal(tmpData[i][j]) + "";
                        } else if (title[j - 1] == '单位供电成本（元/度）') {
                            tmpData[i][j] = Util.formatFordot(tmpData[i][j], 4) + "";
                        } else if (title[j - 1] == '标煤单耗（g/度）' || title[j - 1] == '厂用电率（%）') {
                            if (this.mOpt.approveType == Util.ZBType.BY20YJ || this.mOpt.approveType == Util.ZBType.BY28YJ || this.mOpt.approveType == Util.ZBType.BYSJ) {
                                tmpData[i][j] = Util.formatFordot(tmpData[i][j], 2) + "";
                            }
                            else {
                                tmpData[i][j] = Util.formatCurrency(tmpData[i][j]) + "";
                            }
                        } else if (title[j - 1] == '人均发电量（万度/人）' || title[j - 1] == '外购电单位成本（元/度）' || title[j - 1] == '铝杆棒一次综合成品率（%）' || title[j - 1] == '其中：5154合金杆一次成品率（%）'
                            || title[j - 1] == '4043&8030&6201合金杆一次成品率（%）' || title[j - 1] == '高纯铝杆产品一次成品率（%）' || title[j - 1] == '铝棒产品一次成品率（%）' || title[j - 1] == '铝电解高品质槽99.90%以上等级13项元素符合率（二级以上）（%）'
                            || title[j - 1] == '失败成本率1（%）' || title[j - 1] == '外部客诉率（%）' || title[j - 1] == '4N6精铝块一次成品率（%）' || title[j - 1] == '精铝杆一次成品率（%）'
                            || title[j - 1] == '综合成品率（%）' || title[j - 1] == '基材成品率（%）' || title[j - 1] == '粉末喷涂成品率（%）' || title[j - 1] == '隔热产品成品率（%）' || title[j - 1] == '失败成本率（%）'
                            || title[j - 1] == '自产箔综合符单率（%）' || title[j - 1] == '委托加工化成箔符单率（%）' || title[j - 1] == '架空电缆（1KV、10KV）合格率（%）' || title[j - 1] == '钢芯铝绞线合格率（%）' || title[j - 1] == '布电线合格率（%）') {
                            tmpData[i][j] = Util.formatFordot(tmpData[i][j], 2) + "";
                        }
                        else {
                            tmpData[i][j] = Util.formatCurrency(tmpData[i][j]) + "";
                        }
                    }
                    else {
                        tmpData[i].push("--");
                    }
                }
            }

            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");

            var width = (title.length) * 50;


            $("#" + name).jqGrid(
                jqAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: jqAssist.getDataWithId(tmpData),
                    datatype: "local",
                    multiselect: true,
                    drag: false,
                    resize: false,
                    //autowidth : false,
                    //cellsubmit: 'clientArray',
                    //cellEdit: false,
                    rowNum: 1350,
                    height: '100%',
                    width: 1350,
                    shrinkToFit: width > 1350 ? false : true,
                    autoScroll: true,
                    caption: caption
                }));
            return jqAssist;
        }
    }

    class YDSubView implements ISubView {

        private mTableUnapproveAssist: JQTable.JQGridAssistant;
        private mTableApproveAssist: JQTable.JQGridAssistant;
        private mOpt: IViewOption;
        private mData: any;
        private mApproveColZbIds: string[];
        private mDate: Util.Date;

        constructor(opt: IViewOption) {
            this.mOpt = opt;
        }

        getApprovedData(): number[][] {
            var ret: any = [[]];
            if (this.mTableApproveAssist != null) {
                var checkedRows: string[] = this.mTableApproveAssist.getCheckedRowIds();
                if (checkedRows.length != 0) {
                    ret = this.format(checkedRows);
                }
            }
            return ret;
        }

        private format(checkedRows: string[]): number[][] {
            var ret: any = [];
            var comps = [];
            var years = [];
            var months = [];
            $(checkedRows).each((i: number) => {
                var row: any = checkedRows[i].split("&");
                $(row).each((j) => {
                    row[j] = parseInt(row[j]);
                });

                comps.push(row[0]);
                if (row.length > 1) {
                    years.push(row[1]);
                    months.push(row[2]);
                }

            });
            ret.push(comps);
            if (years.length > 0) {
                ret.push(years);
            }
            if (months.length > 0) {
                ret.push(months);
            }

            return ret;
        }
        
        //[[compId...], [year...], [month...]]
        getUnapprovedData(): number[][] {
            var ret: any = [[]];
            if (this.mTableUnapproveAssist != null) {
                var checkedRows: string[] = this.mTableUnapproveAssist.getCheckedRowIds();
                if (checkedRows.length != 0) {
                    ret = this.format(checkedRows);
                }
            }
            return ret;
        }

        getDate(): Util.Date {
            return this.mData;
        }
        
        //[[compId ,zbId, zbName, value, year?, month?] ...] approved 
        //[[compId ,zbId, zbName, value, year?, month?] ...] unapproved
        process(data: any, date: Util.Date, companies: Util.IData[]): void {
            this.mData = date;
            if (data[0].length > 0) {
                this.mTableApproveAssist = this.updateTable(data[0], companies, this.mOpt.tableApproveId, "未审核数据");
            }

            if (data[1].length > 0) {
                this.mTableUnapproveAssist = this.updateTable(data[1], companies, this.mOpt.tableUnapproveId, "已审核数据");
            }
        }


        private updateTable(rawData: string[][], comps: Util.IData[], tableId: string, caption: string): JQTable.JQGridAssistant {
            var compMap = {};
            var companies = [];
            // remove unused company
            $(comps).each((i) => {
                $(rawData).each((j) => {
                    if (rawData[j][0] == "" + comps[i].id) {
                        compMap["c_" + comps[i].id] = comps[i];
                    }
                });
            });

            for (var i in compMap) {
                companies.push(compMap[i]);
            }

            var title = ["单位名称"];
            var colZbIds = ["dw"];
            var zbColMap = {};
            var hasDate: boolean = rawData[0].length > 4;
            if (hasDate) {
                title.push("日期");
                colZbIds.push("rq");
            }
            
            //make title
            $(rawData).each((i) => {
                if (!Util.isExist(zbColMap["_" + rawData[i][1]])) {
                    colZbIds.push(rawData[i][1]);
                    title.push(rawData[i][2])
                    zbColMap["_" + rawData[i][1]] = title.length;
                }
            });


            var tmpData = [];
            var compYearMap = {};
            //make data
           
            $(companies).each((i) => {//walk company
                $(rawData).each((j) => {//walk zbs
                    if (rawData[j][0] == "" + companies[i].id) {
                        var index = 0;
                        if (hasDate) {
                            var key = companies[i].id + "&" + rawData[j][4] + "&" + rawData[j][5];
                            if (!Util.isExist(compYearMap[key])) {
                                tmpData.push([key, companies[i].value, rawData[j][4] + "年" + rawData[j][5] + "月"]);
                                compYearMap[key] = tmpData.length - 1;
                            }
                            index = compYearMap[key];
                        } else {
                            if (!Util.isExist(compYearMap["_" + companies[i].id])) {
                                tmpData.push([companies[i].id, companies[i].value]);
                                compYearMap["_" + companies[i].id] = tmpData.length - 1;
                            }
                            index = compYearMap["_" + companies[i].id];
                        }

                        if (tmpData[index].length <= zbColMap["_" + rawData[j][1]]) {
                            resize(tmpData[index], zbColMap["_" + rawData[j][1]]);
                        }
                        tmpData[index][zbColMap["_" + rawData[j][1]]] = rawData[j][3];
                    }
                });
            });

            var maxLength = 0;
            for (var i: any = 0; i < tmpData.length; ++i) {
                if (maxLength < tmpData[i].length) {
                    maxLength = tmpData[i].length;
                }
            }

            for (var i: any = 0; i < tmpData.length; ++i) {
                for (var j = hasDate ? 3 : 2; j < maxLength; ++j) {
                    if (j < tmpData[i].length) {
                        if (title[j - 1] == '人数') {
                            tmpData[i][j] = Util.formatInt(tmpData[i][j]) + "";
                        } else if (title[j - 1] == '三项费用率(%)') {
                            tmpData[i][j] = Util.formatPercent(tmpData[i][j]) + "";
                        } else if (title[j - 1] == '人均收入' || title[j - 1] == '人均利润' || title[j - 1] == '精铝块13项元素和值（ppm）') {
                            tmpData[i][j] = Util.formatFordot(tmpData[i][j]) + "";
                        } else if (title[j - 1] == '净资产收益率(%)' || title[j - 1] == '销售利润率(%)') {
                            tmpData[i][j] = Util.formatPercentSignal(tmpData[i][j]) + "";
                        } else if (title[j - 1] == '单位供电成本（元/度）') {
                            tmpData[i][j] = Util.formatFordot(tmpData[i][j], 4) + "";
                        } else if (title[j - 1] == '标煤单耗（g/度）' || title[j - 1] == '厂用电率（%）') {
                            if (this.mOpt.approveType == Util.ZBType.BY20YJ || this.mOpt.approveType == Util.ZBType.BY28YJ || this.mOpt.approveType == Util.ZBType.BYSJ) {
                                tmpData[i][j] = Util.formatFordot(tmpData[i][j], 2) + "";
                            }
                            else {
                                tmpData[i][j] = Util.formatCurrency(tmpData[i][j]) + "";
                            }
                        } else if (title[j - 1] == '人均发电量（万度/人）' || title[j - 1] == '外购电单位成本（元/度）' || title[j - 1] == '铝杆棒一次综合成品率（%）' || title[j - 1] == '其中：5154合金杆一次成品率（%）'
                            || title[j - 1] == '4043&8030&6201合金杆一次成品率（%）' || title[j - 1] == '高纯铝杆产品一次成品率（%）' || title[j - 1] == '铝棒产品一次成品率（%）' || title[j - 1] == '铝电解高品质槽99.90%以上等级13项元素符合率（二级以上）（%）'
                            || title[j - 1] == '失败成本率1（%）' || title[j - 1] == '外部客诉率（%）' || title[j - 1] == '4N6精铝块一次成品率（%）' || title[j - 1] == '精铝杆一次成品率（%）'
                            || title[j - 1] == '综合成品率（%）' || title[j - 1] == '基材成品率（%）' || title[j - 1] == '粉末喷涂成品率（%）' || title[j - 1] == '隔热产品成品率（%）' || title[j - 1] == '失败成本率（%）'
                            || title[j - 1] == '自产箔综合符单率（%）' || title[j - 1] == '委托加工化成箔符单率（%）' || title[j - 1] == '架空电缆（1KV、10KV）合格率（%）' || title[j - 1] == '钢芯铝绞线合格率（%）' || title[j - 1] == '布电线合格率（%）') {
                            tmpData[i][j] = Util.formatFordot(tmpData[i][j], 2) + "";
                        }
                        else {
                            tmpData[i][j] = Util.formatCurrency(tmpData[i][j]) + "";
                        }
                    } else {
                        tmpData[i].push("--");
                    }
                }
            }

            var name = tableId + "_jqgrid";
            var jqAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createSjTable(name, title, colZbIds);

            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");

            var width = (title.length) * 50;


            $("#" + name).jqGrid(
                jqAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: jqAssist.getDataWithId(tmpData),
                    datatype: "local",
                    multiselect: true,
                    drag: false,
                    resize: false,
                    rowNum: 1000,
                    //autowidth : false,
                    //cellsubmit: 'clientArray',
                    //cellEdit: false,
                    height: '100%',
                    width: 1350,
                    shrinkToFit: width > 1350 ? false : true,
                    autoScroll: true,
                    caption: caption
                }));
            return jqAssist;
        }
    }


    export class View {
        public static instance: View = new View();
        public static getInstance(): View {
            return View.instance;
        }


        private mCurView: ISubView;
        private mDateSelector: Util.DateSelector;
        private mCompanySelector: Util.CompanySelector;
        private mOpt: IViewOption;
        private mDataSet: Util.Ajax = new Util.Ajax("zb_update.do", false);
        private mUnapprove: Util.Ajax = new Util.Ajax("zb_unapprove.do");
        private mApprove: Util.Ajax = new Util.Ajax("zb_approve.do");

        initInstance(opt: IViewOption) {
            this.mOpt = opt;
            if (this.mOpt.approveType == Util.ZBType.YDJDMJH) {
                this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 2 }, Util.addMonth(this.mOpt.date, 1), this.mOpt.dateId, true);
            } else if (this.mOpt.approveType == Util.ZBType.QNJH) {
                this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 2 }, { year: this.mOpt.date.year }, this.mOpt.dateId, true);
            } else {
                this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 2 }, this.mOpt.date, this.mOpt.dateId);
            }

            this.mCompanySelector = new Util.CompanySelector(
                true,
                opt.companyId,
                opt.comps,
                opt.firstCompany
            //                ,{
            //                noneSelectedText : '经营单位',
            //                selectedText: '# 个经营单位被选中'     
            //                }
                );
            this.mCompanySelector.checkAll();

            switch (this.mOpt.approveType) {
                case Util.ZBType.YDJDMJH:
                    this.mCurView = new YDSubView(opt);
                    break;
                case Util.ZBType.QNJH:
                case Util.ZBType.BY20YJ:
                case Util.ZBType.BY28YJ:
                case Util.ZBType.BYSJ:
                    this.mCurView = new QNJHSubView(opt);
                    break;
            }

            this.updateTitle();
            //this.updateUI();
        }

        public updateUI() {
            $("#nodatatips").css("display", "none");
            var comps = this.mCompanySelector.getCompanys();
            if (comps.length != 0) {
                var date = this.mDateSelector.getDate();
                if (this.mOpt.approveType == Util.ZBType.YDJDMJH) {
                    date = Util.addMonth(date, -2);
                }
                this.mDataSet.post({ year: date.year, month: date.month, approveType: this.mOpt.approveType, companies: JSON.stringify(comps) })
                    .then((data: any) => {
                        this.updateTitle();
                        if (data[0].length == 0) {
                            $("#approve").css("display", "none");
                        } else {
                            $("#approve").css("display", "");
                            $("#nothing").css("display", "none");
                        }

                        if (data[1].length == 0) {
                            $("#unapprove").css("display", "none");
                        } else {
                            $("#unapprove").css("display", "");
                            $("#nothing").css("display", "none");
                        }

                        if (data[0].length == 0 && data[1].length == 0) {
                            $("#nothing").css("display", "");
                        }
                        else {
                            this.mCurView.process(data, this.mDateSelector.getDate(), this.mCompanySelector.getRawCompanyData());
                        }
                    });
            } else {
                Util.MessageBox.tip("请选择公司");
            }
        }

        public unapprove() {
            var date = this.mCurView.getDate();
            var compIds = this.mCurView.getUnapprovedData();
            if (compIds[0].length > 0) {
                this.mUnapprove.post({
                    approveType: this.mOpt.approveType,
                    year: date.year,
                    month: date.month,
                    data: JSON.stringify(compIds)
                }).then((data: ISubmitResult) => {
                    if (data.result) {
                        Util.MessageBox.tip("反审核  成功");
                        this.mDateSelector.select(date);
                        this.updateUI();
                    } else {
                        Util.MessageBox.tip("反审核 失敗");
                    }
                });
            } else {
                Util.MessageBox.tip("请选择公司");
            }
        }


        public approve() {
            var date = this.mCurView.getDate();
            var compIds = this.mCurView.getApprovedData();
            if (compIds[0].length > 0) {
                this.mApprove.post({
                    approveType: this.mOpt.approveType,
                    year: date.year,
                    month: date.month,
                    data: JSON.stringify(compIds)
                }).then((data: ISubmitResult) => {
                    if (data.result) {
                        Util.MessageBox.tip("审核 成功");
                        this.mDateSelector.select(date);
                        this.updateUI();
                    } else {
                        Util.MessageBox.tip("审核 失敗");
                    }
                });
            } else {
                Util.MessageBox.tip("请选择公司");
            }
        }

        private updateTitle() {
            var header = "";
            var date = this.mDateSelector.getDate();
            switch (this.mOpt.approveType) {
                case Util.ZBType.QNJH:
                    header = date.year + "年 全年计划数据审核";
                    break;
                case Util.ZBType.YDJDMJH:
                    header = date.year + "年" + " 季度-月度末计划值审核";
                    break;
                case Util.ZBType.BY20YJ:
                    header = date.year + "年" + date.month + "月 20日预计值审核";
                    break;
                case Util.ZBType.BY28YJ:
                    header = date.year + "年" + date.month + "月 28日预计值审核";
                    break;
                case Util.ZBType.BYSJ:
                    header = date.year + "年" + date.month + "月 实际数据审核";
                    break;
            }

            $('h1').text(header);
            document.title = header;
        }
    }
}