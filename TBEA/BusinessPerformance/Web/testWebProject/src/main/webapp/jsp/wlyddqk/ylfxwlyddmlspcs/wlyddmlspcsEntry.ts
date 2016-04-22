/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../wlyddqkdef.ts" />
///<reference path="../../messageBox.ts"/>
///<reference path="..//wlyddqkEntry.ts"/>

declare var echarts;
declare var entryView:wlyddqk.EntryView;

module ylfxwlyddmlspcs {
    export module wlyddmlspcsEntry {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, readOnly : boolean, date : string):JQTable.JQGridAssistant {
                let curDate : Date = new Date(date);
                let month = curDate.getMonth() + 1;
                let year = curDate.getFullYear();
                let data = [];
                let node : JQTable.Node;
                let titleNodes : JQTable.Node[] = [];
                
                node = new JQTable.Node("产品", "wlyddmlspcsentry_cp", readOnly, TextAlign.Left);
                titleNodes.push(node);
                
                node = new JQTable.Node(year + "年" + month + "月", "wlyddmlspcsentry_riqi", readOnly, TextAlign.Center);

                node.append(new JQTable.Node("成本", "wlyddmlspcsentry_cb_", readOnly));
                node.append(new JQTable.Node("收入", "wlyddmlspcsentry_sr_", readOnly));

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
                        if ("" == submitData[i][j]){
                            Util.MessageBox.tip("有空内容 无法提交")
                            return;
                        }
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

            public init(opt:Option):void {
                super.init(opt);
                entryView.register("变压器未履约订单毛利水平测算-综合", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_ZH));
                entryView.register("变压器未履约订单毛利水平测算-电压等级", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_DYDJ));
                entryView.register("变压器未履约订单毛利水平测算-产品分类", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_CPFL));
                entryView.register("线缆未履约订单毛利水平测算-综合", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_XL_ZH));
                entryView.register("线缆未履约订单毛利水平测算-产品分类", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_XL_CPFL));
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, this.mIsReadOnly, this.mDt);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");

                let lastsel = "";
                let lastcell = "";

                this.$(name).jqGrid(
                    this.mTableAssist.decorate({
                        datatype: "local",
                        multiselect: false,
                        drag: false,
                        resize: false,
                        //autowidth : true,
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
                                    left: this.$(name).offset().left + this.$(name).width() / 2 - 290 / 2,
                                    top: this.$(name).offset().top + this.$(name).height() / 2 - 90
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
                $('html').bind('click', (e) => { //用于点击其他地方保存正在编辑状态下的行
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

        export var pluginView = WlyddmlspcsEntryView.newInstance();
    }
}