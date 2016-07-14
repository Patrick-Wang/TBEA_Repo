///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicEntry.ts"/>
///<reference path="../framework/templates/singleDateReport/entry.ts"/>

module xnyzb{
    interface ZbServResp extends Util.ServResp{
        zbdc:Util.ServResp;
    }

    import router = framework.router;
    import FrameEvent = framework.basic.FrameEvent;
    class XnyzbEntryView extends framework.templates.singleDateReport.EntryView {
        mZbdcAssist : JQTable.JQGridAssistant;

        response():ZbServResp{
            return <ZbServResp>(this.resp);
        }

        updateTable():void {
            $("#" + this.opt.host)
                    .attr("align", "");
            $("#" + this.opt.host).css("float", "left")
                    .css("margin-top","5px");

            let nameDjg = this.opt.host + "_jqgrid_uiframe_djg";
            let nameZbdc = this.opt.host + "_jqgrid_uiframe_zbc";
            this.mTableAssist = Util.JQGridAssistantFactory.createTable(nameDjg, this.response());
            this.mZbdcAssist = Util.JQGridAssistantFactory.createTable(nameZbdc, this.response().zbdc);
            var parent = $("#" + this.opt.host);
            parent.empty();
            parent.append("<table id='" + nameDjg + "'></table></div><table id='" + nameZbdc + "'></table></div>");
            let jqTable = $("#" + nameDjg);
            jqTable.jqGrid(
                this.mTableAssist.decorate({
                    datatype: "local",
                    data: this.mTableAssist.getDataWithId(this.response().data),
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
                    rowNum: 1000,
                    height: '100%',
                    width: '100%',
                    shrinkToFit: true,
                    autoScroll: true,
                    caption:"多晶硅产量（吨）"
                }));
            jqTable = $("#" + nameZbdc);
            jqTable.jqGrid(
                this.mZbdcAssist.decorate({
                    datatype: "local",
                    data: this.mZbdcAssist.getDataWithId(this.response().zbdc.data),
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
                    rowNum: 1000,
                    height: '100%',
                    width: '100%',
                    shrinkToFit: true,
                    autoScroll: true,
                    caption:"自备电厂电量（万kw·h）"
                }));
        }

        onLoadSubmitData() : any{
            return {djg : this.mTableAssist.getAllData(), zbdc : this.mZbdcAssist.getAllData()};
        }
    }
    let ins = new XnyzbEntryView();
}