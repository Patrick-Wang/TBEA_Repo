/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../messageBox.ts"/>
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
declare var $:any;


module pluginEntry {
    export let dzclcb : number = framework.basic.endpoint.lastId();
}

module dzwzgb {
    export module dzclcbEntry {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, readOnly:boolean):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("期货盈亏（万元）", "ac", readOnly),
                    new JQTable.Node("市场现货月均价（元/吨）", "ada", readOnly),
                    new JQTable.Node("采购月均价（元/吨）（摊入当月期货盈亏）", "adb", readOnly),
                    new JQTable.Node("三项费用保本价（元/吨）", "adc", readOnly),
                    new JQTable.Node("目标利润倒算价（元/吨）", "ae", readOnly),
                    new JQTable.Node("采购量（吨）", "af", readOnly),
                    new JQTable.Node("期现货合计盈亏", "ag", readOnly)
                        .append(new JQTable.Node("指导价格按照保本价（万元）", "ah", readOnly))
                        .append(new JQTable.Node("指导价格按照目标利润价（万元）", "ai", readOnly))
                ], gridName);
            }
        }

        interface Option extends framework.basic.PluginOption {
            tb:string;
        }

        class DzclcbEntryView extends framework.basic.EntryPluginView {
            static ins = new DzclcbEntryView();
            private mData:Array<string[]>;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("dzclcb/entry/update.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("dzclcb/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("dzclcb/entry/submit.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;
            private mCompType:Util.CompanyType;
            getId():number {
                return pluginEntry.dzclcb;
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginSave(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 1; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 1] = submitData[i][j - 1].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        this.pluginUpdate(dt, compType);
                        Util.MessageBox.tip("保存 成功");
                    } else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            }

            public  pluginSubmit(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 1; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 1] = submitData[i][j - 1].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j - 1]) {
                            Util.MessageBox.tip("有空内容 无法提交")
                            return;
                        }
                    }
                }
                this.mAjaxSubmit.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        this.pluginUpdate(dt, compType);
                        Util.MessageBox.tip("提交 成功");
                    } else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = compType;
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
                framework.router.fromEp(this).to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_REGISTER, "大宗材料控成本");
                $.extend($.jgrid.edit, {
                    bSubmit: "确定"
                });
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, false);
                let data = [];
                if (this.mCompType == Util.CompanyType.SBGS ||
                    this.mCompType == Util.CompanyType.HBGS ||
                    this.mCompType == Util.CompanyType.TBGS ||
                    this.mCompType == Util.CompanyType.XBC){
                    data.push(["铜"].concat(this.mData[0]));
                }else{
                    data.push(["铜"].concat(this.mData[0]));
                    data.push(["铝"].concat(this.mData[1]));
                }

                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                let jqTable = this.$(name);
                jqTable.jqGrid(
                    this.mTableAssist.decorate({
                        datatype: "local",
                        data: this.mTableAssist.getData(this.mData),
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
                        //pager: '#' + pagername,
                    }));
            }
        }
    }
}
