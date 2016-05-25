/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../messageBox.ts"/>
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cpzlqkdef.ts"/>
///<reference path="../cpzlqkApprove.ts"/>
declare var $:any;


module pluginApprove {
    export let byqcpycssbhgwtmx : number = framework.basic.endpoint.lastId();
}

module cpzlqk {
    export module byqcpycssbhgwtmxApprove {
        import TextAlign = JQTable.TextAlign;
        import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, readOnly:boolean):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    Node.create({name : "产品类型", align : TextAlign.Center}),
                    Node.create({name : "生产号", align : TextAlign.Center}),
                    Node.create({name : "产品型号", align : TextAlign.Center}),
                    Node.create({name : "试验不合格现象", align : TextAlign.Center}),
                    Node.create({name : "不合格类别", align : TextAlign.Center}),
                    Node.create({name : "原因分析", align : TextAlign.Center}),
                    Node.create({name : "处理措施", align : TextAlign.Center}),
                    Node.create({name : "处理结果", align : TextAlign.Center}),
                    Node.create({name : "责任类别", align : TextAlign.Center})
                ], gridName);
            }
        }

        class ApproveView extends ZlApprovePluginView {
            static ins = new ApproveView();
            private mData:CpzlqkResp;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("../byqcpycssbhgwtmx/approve/update.do", false);
            private mAjaxApprove:Util.Ajax = new Util.Ajax("../byqcpycssbhgwtmx/approve/approve.do", false);
            private mDt:string;
            private mTableAssist:JQTable.JQGridAssistant;
            private mCompType:Util.CompanyType;
            getId():number {
                return pluginApprove.byqcpycssbhgwtmx;
            }
            isSupportBhglb():boolean{
                return true;
            }
            protected isSupported(compType:Util.CompanyType):boolean {
                if (compType == Util.CompanyType.SBGS ||
                    compType == Util.CompanyType.HBGS ||
                    compType == Util.CompanyType.TBGS ||
                    compType == Util.CompanyType.XBC){
                    return true;
                }
                return false;
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginApprove(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([allData[i][0]]);
                }
                this.mAjaxApprove.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType,
                    bhgType:this.getBhglx()
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("审核 成功", ()=>{
                            this.pluginUpdate(dt, compType);
                        });
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
                        companyId: compType,
                        bhgType:this.getBhglx()
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
                if (this.mData.status == Util.ZBStatus.APPROVED){
                    this.$(this.option().tbarea).show();
                    this.$(this.option().tips).text("数据已审核");
                    framework.router
                        .fromEp(this)
                        .to(framework.basic.endpoint.FRAME_ID)
                        .send(framework.basic.FrameEvent.FE_SUBMITTED);
                    this.updateTable();
                }else if (this.mData.status == Util.ZBStatus.SUBMITTED){
                    this.$(this.option().tbarea).show();
                    this.$(this.option().tips).text("数据未审核");
                    framework.router
                        .fromEp(this)
                        .to(framework.basic.endpoint.FRAME_ID)
                        .send(framework.basic.FrameEvent.FE_SUBMITTED);
                    this.updateTable();
                }else{
                    this.$(this.option().tbarea).hide();
                    this.$(this.option().tips).text("数据尚未提交");
                    framework.router
                        .fromEp(this)
                        .to(framework.basic.endpoint.FRAME_ID)
                        .send(framework.basic.FrameEvent.FE_NOT_SUBMITTED);
                }
            }

            protected init(opt:Option):void {
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "产品一次送试不合格明细");
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, false);

                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                let jqTable = this.$(name);
                jqTable.jqGrid(
                    this.mTableAssist.decorate({
                        datatype: "local",
                        data: this.mTableAssist.getDataWithId(this.mData.tjjg),
                        multiselect: false,
                        drag: false,
                        resize: false,
                        //assistEditable:true,
                        //autowidth : false,
                        cellsubmit: 'clientArray',
                        //editurl: 'clientArray',
                        cellEdit: true,
                        // height: data.length > 25 ? 550 : '100%',
                        // width: titles.length * 200,
                        rowNum: 1000,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        //pager: '#' + pagername,
                        viewrecords: true
                    }));
            }
        }
    }
}
