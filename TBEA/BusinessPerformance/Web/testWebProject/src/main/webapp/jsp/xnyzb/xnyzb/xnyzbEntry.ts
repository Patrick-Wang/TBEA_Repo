/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../messageBox.ts"/>
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../xnyzbdef.ts"/>
declare var $:any;


module pluginEntry {
    export let xnyzb : number = framework.basic.endpoint.lastId();
}

module xnyzb {
    export module xnyzbEntry {
        import TextAlign = JQTable.TextAlign;
        import Node = JQTable.Node;
        class JQGridAssistantFactory {

            static  parseHeader(header:Util.Header): Node{
                let node:Node = null;
                if ("date" == header.type){
                    node = Node.create({name : header.name, align : TextAlign.Center, isReadOnly:false,isNumber:false,editType:"text", options:{
                        dataInit: function (element) {
                            $(element).datepicker({
                                dateFormat: 'yy-mm-dd',
                                onSelect: function (dateText, inst) {
                                }
                            });
                        }
                    }});
                }else if ("text" == header.type){
                    node = Node.create({name : header.name, align : TextAlign.Center, isReadOnly:false,isNumber:false,editType:"text"});
                }else if ("hidden" == header.type){
                    node = Node.create({name : header.name, align : TextAlign.Center, isReadOnly:false,isNumber:false,editType:"text", hidden:true});
                }else if ("select" == header.type){
                    node = Node.create({name : header.name, align : TextAlign.Center, isReadOnly:false,isNumber:false,editType: "select", options: { value: header.options }});
                }else{
                    node = Node.create({name : header.name, align : TextAlign.Center, isReadOnly:false});
                }

                if (header.sub != undefined) {
                    for (let i = 0; i < header.sub.length; ++i) {
                        node.append(JQGridAssistantFactory.parseHeader(header.sub[i]));
                    }
                }
                return node;
            }

            public static createTable(gridName:string, headers: Util.Header[]):JQTable.JQGridAssistant {

                let nodes : Node[] = [];
                for (let i= 0; i < headers.length; ++i){
                    nodes.push(JQGridAssistantFactory.parseHeader(headers[i]))
                }
                return new JQTable.JQGridAssistant(nodes, gridName);
            }
        }

        interface Option extends framework.basic.PluginOption {
            tb:string;
        }

        class EntryView extends framework.basic.EntryPluginView {
            static ins = new EntryView();
            private mData:Util.ServResp;
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("xnyzbEntry.do", false);
            private mAjaxSave:Util.Ajax = new Util.Ajax("../xnyzb/entry/save.do", false);
            private mAjaxSubmit:Util.Ajax = new Util.Ajax("../xnyzb/entry/submit.do", false);
            private mTableAssist:JQTable.JQGridAssistant;
            private mCompType:Util.CompanyType;
            mDStart:string;
            mDEnd:string;
            getId():number {
                return pluginEntry.xnyzb;
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginSave(dt:string, compType:Util.CompanyType):void {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 2; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 2] = submitData[i][j - 2].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("保存 成功", ()=>{
                            this.pluginUpdate(dt, compType);
                        });
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
                    for (var j = 2; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 2] = submitData[i][j - 2].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j - 2]) {
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
                        Util.MessageBox.tip("提交 成功", ()=>{
                            this.pluginUpdate(dt, compType);
                        });
                    } else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mCompType = compType;
                this.mAjaxUpdate.get({
                        dStart: this.mDStart,
                        dEnd: this.mDEnd
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
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "新能源周报");
                this.mDStart="2016-1-1";
                $("#dstart").val(2016 + "-" + 1 + "-" + 1);
                $("#dstart").datepicker({
                    //            numberOfMonths:1,//显示几个月
                    //            showButtonPanel:true,//是否显示按钮面板
                    dateFormat: 'yy-mm-dd',//日期格式
                    //            clearText:"清除",//清除日期的按钮名称
                    //            closeText:"关闭",//关闭选择框的按钮名称
                    yearSuffix: '年', //年的后缀
                    showMonthAfterYear: true,//是否把月放在年的后面
                    defaultDate: 2016 + "-" + 1 + "-" + 1,//默认日期
                    //            minDate:'2011-03-05',//最小日期
                    maxDate: 2019 + "-" + 1 + "-" + 1,//最大日期
                    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                    dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                    dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                    dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                    onSelect: (selectedDate) => {//选择日期后执行的操作
                        var d: Date = new Date(selectedDate);
                        this.mDStart = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
                    }
                });
                $("#dEnd").val(2016 + "-" + 1 + "-" + 1);
                this.mDEnd="2016-1-1";
                $("#dEnd").datepicker({
                    //            numberOfMonths:1,//显示几个月
                    //            showButtonPanel:true,//是否显示按钮面板
                    dateFormat: 'yy-mm-dd',//日期格式
                    //            clearText:"清除",//清除日期的按钮名称
                    //            closeText:"关闭",//关闭选择框的按钮名称
                    yearSuffix: '年', //年的后缀
                    showMonthAfterYear: true,//是否把月放在年的后面
                    defaultDate: 2016 + "-" + 1 + "-" + 1,//默认日期
                    //            minDate:'2011-03-05',//最小日期
                    maxDate: 2019 + "-" + 1 + "-" + 1,//最大日期
                    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                    dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                    dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                    dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                    onSelect: (selectedDate) => {//选择日期后执行的操作
                        var d: Date = new Date(selectedDate);
                        this.mDEnd = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
                    }
                });
                $("#ui-datepicker-div").css('font-size', '0.8em'); //改变大小;
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, this.mData.header);

                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                let jqTable = this.$(name);
                jqTable.jqGrid(
                    this.mTableAssist.decorate({
                        datatype: "local",
                        data: this.mTableAssist.getDataWithId(this.mData.data),
                        multiselect: false,
                        drag: false,
                        resize: false,
                        assistEditable:true,
                        //autowidth : false,
                        cellsubmit: 'clientArray',
                        //editurl: 'clientArray',
                        cellEdit: true,
                        // height: data.length > 25 ? 550 : '100%',
                        // width: titles.length * 200,
                        rowNum: 20,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        pager: '#' + pagername,
                        viewrecords: true
                    }));
            }
        }
    }
}
