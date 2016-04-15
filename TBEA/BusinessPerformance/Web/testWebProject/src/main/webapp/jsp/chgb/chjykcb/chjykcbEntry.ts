/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../chgbdef.ts" />
///<reference path="../../messageBox.ts"/>
///<reference path="../chgbEntry.ts"/>

declare var echarts;
declare var entryView:chgb.EntryView;

module chgb {
    export module chjykcbEntry {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, readOnly : boolean):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("项目", "chjykcbentry_xm", true, TextAlign.Center),
                    new JQTable.Node("项目", "chjykcbentry_xm1", true, TextAlign.Center),
                    new JQTable.Node("上月余额", "chjykcbentry_syye", false),
                    new JQTable.Node("本月新增", "chjykcbentry_byxz", false),
                    new JQTable.Node("本月处置", "chjykcbentry_bycz", false),
                    new JQTable.Node("期末余额", "chjykcbentry_qmye", false)
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            tb:string;
        }

        class ChjykcbEntryView extends BaseEntryPluginView {

            private mData:Array<string[]>;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("chjykcb/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("chjykcb/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("chjykcb/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;
            private mIsReadOnly:boolean;

            public static newInstance():ChjykcbEntryView {
                return new ChjykcbEntryView();
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
                entryView.register("积压库存表", this);
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, this.mIsReadOnly);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                
                let data = [];
                data.push(["积压库存（原值）"].concat(this.mData[0]));
                data.push(["积压库存（原值）"].concat(this.mData[1]));
                data.push(["积压库存（原值）"].concat(this.mData[2]));
                
                this.mTableAssist.mergeRow(0);
                this.mTableAssist.mergeTitle();

                let lastsel = "";
                let lastcell = "";

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
                        data: this.mTableAssist.getData(data),
                        viewrecords: true,

                        onSelectCell: (id, nm, tmp, iRow, iCol) => {
                            //                       console.log(iRow +', ' + iCol);
                        },

                        //                    onCellSelect: (ri,ci,tdHtml,e) =>{
                        //                       console.log(ri +', ' + ci);
                        //                    },
                        beforeSaveCell: (rowid, cellname, v, iRow, iCol) => {
                            var ret = parseFloat(v.replace(new RegExp(',', 'g'), ''));
                            if (isNaN(ret)) {
                                $.jgrid.jqModal = {
                                    width: 290,
                                    left: $("#table").offset().left + $("#table").width() / 2 - 290 / 2,
                                    top: $("#table").offset().top + $("#table").height() / 2 - 90
                                };
                                return v;
                            } else {
                                return ret;
                            }
                        },
                        beforeEditCell: (rowid, cellname, v, iRow, iCol) => {
                            lastsel = iRow;
                            lastcell = iCol;
                            //                        console.log(iRow +', ' + iCol);
                            $("input").attr("disabled", true);
                        },

                        afterEditCell: (rowid, cellname, v, iRow, iCol) => {
                            $("input[type=text]").bind("keydown", function(e) {
                                if (e.keyCode === 13) {
                                    setTimeout(function() {
                                        $("#" + name).jqGrid("editCell", iRow + 1, iCol, true);
                                    }, 10);
                                }
                            });
                        },

                        afterSaveCell: () => {
                            $("input").attr("disabled", false);
                            lastsel = "";
                        },

                        afterRestoreCell: () => {
                            $("input").attr("disabled", false);

                            lastsel = "";
                        }
                        //                    ,
                        //                    afterEditCell:(rowid,cellname,v,iRow,iCol)=>{
                        //                        lastsel = "";
                        //                        lastcell = "";
                        //                    }
                    }));
                $('html').bind('click', function(e) { //用于点击其他地方保存正在编辑状态下的行
                    if (lastsel != "") { //if a row is selected for edit
                        if ($(e.target).closest("#" + name).length == 0) { //and the click is outside of the grid //save the row being edited and unselect the row
                            //  $("#" + name).jqGrid('saveRow', lastsel);
                            $("#" + name).jqGrid("saveCell", lastsel, lastcell);
                            //$("#" + name).resetSelection();
                            lastsel = "";
                        }
                    }
                });
            }
        }

        export var pluginView = ChjykcbEntryView.newInstance();
    }
}
