module search {

    import JQGridAssistant = JQTable.JQGridAssistant;

    interface Time {
        type: string; //year month date time datetime
        init: string;
        now?: string; //YYYY-MM-DD HH-mm-SS
        ins?: any;
    }

    interface Period {
        start: Time;
        end: Time;
    }

    interface Lov {
        direct: string;
        option: string[];
        url: string;
    }

    interface Input{
        validation:string;
        hint:string;
    }

    interface Option {
        param: string; //对于period param = first,second
        name: string; //对于period name = first,second
        type: string;//lov lovNoSearch date period input
        date: Time;
        lov: Lov;
        period: Period;
        input:Input;
    }

    interface Context {
        pager: boolean;
        options: Option[];
        updateUrl: string;
        exportUrl: string;
        printUrl: string;
        baseUrl: string;
        isFavorite: boolean;
        item: number;
    }

    declare var context: Context;
    declare var $: any;
    declare var layui: any;

    class TablePanel {

        pgSize: number = 5;
        tableAssist:JQGridAssistant;
        constructor() {
            $(window).on('resize', () => {
                this.adjustSize();
            });
        }

        getPgSize() {
            return this.pgSize;
        }

        updatePgSize() {
            let tbHeader = 28;
            let tbPager = 28;
            let rowHeight = 26;
            let wellPaddingTop = $("#table").parent().css("padding-top").replace("px", "");
            let wellPaddingBottom = $("#table").parent().css("padding-bottom").replace("px", "");
            let navHeight = $(".nav-area").css("height").replace("px", "");
            let searchHeight = $(".search-area").css("height").replace("px", "");
            let leftHeight = document.documentElement.clientHeight -
                navHeight -
                searchHeight;
            let bodyHeight = leftHeight - tbHeader - tbPager - wellPaddingBottom - wellPaddingTop;
            let newPgSize = Util.zeroDiv(bodyHeight, rowHeight);
            this.pgSize = newPgSize > 5 ? newPgSize : 5;
            $("#table").parent().css("height", leftHeight + "px");
        }


        adjustSize() {
            var jqgrid = $("#table-jq");
            if (jqgrid.width() != $("#table").width()) {
                jqgrid.setGridWidth($("#table").width());
            }

            // let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
            // this.tableAssist && this.tableAssist.resizeHeight(maxTableBodyHeight);
            //
            // if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
            //     jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            // }
        }

        updateTable(gridCtrl, dataOpt) {
            $("#table").empty();
            $("#table").append('<table id="table-jq"></table><div id="pager"></div>');
            this.tableAssist = Util.createTable("table-jq", gridCtrl)
            this.tableAssist.create({
                assistDataWithId: gridCtrl.data,
                assistTotal: Util.roundDiv(gridCtrl.dataCount, this.pgSize),
                assistRecords: gridCtrl.dataCount,
                assistPagedata: (postdata) => {
                    dataOpt.pgNum = postdata.page - 1;
                    $.ajax({
                        type: "POST",
                        url: encodeURI(context.updateUrl),
                        data: dataOpt,
                        success: (data: any) => {
                            let dataNew = JSON.parse(data);
                            this.tableAssist.addData(
                                Util.roundDiv(dataNew.dataCount, this.pgSize),
                                postdata.page,
                                dataNew.dataCount,
                                undefined, dataNew.data);

                        },
                        error: (XMLHttpRequest, textStatus, errorThrown) => {
                            //promise.failed(textStatus);
                        }
                    });

                },
                cellEdit: false,
                cellsubmit: 'clientArray',
                multiselect: !!context.printUrl,
                drag: false,
                resize: false,
                height: '100%',
                shrinkToFit: (gridCtrl.shrinkToFit == undefined) ? "true" : gridCtrl.shrinkToFit,
                assistEditable: false,
                autoScroll: true,
                rowNum: this.pgSize,
                viewrecords: true,
                pager: context.pager ? "#pager" : undefined
            });
            this.adjustSize();
        }

        getSelRows(){
            return this.tableAssist ? this.tableAssist.getCheckedRowIds() : undefined;
        }
    }

    class SearchPanel {

        tablePanel: TablePanel;
        dataOpt:any;

        constructor(tablePanel: TablePanel) {
            this.tablePanel = tablePanel;
            this.buildSearchItems();
            this.tablePanel.updatePgSize();
        }

        lovDirector(actor: Option) {
            for (let i = 0; i < context.options.length; ++i) {
                if (context.options[i].type == 'lov') {
                    if (context.options[i].lov.direct == actor.param) {
                        return context.options[i];
                    }
                }
            }
        }

        lovActor(director: Option) {
            for (let i = 0; i < context.options.length; ++i) {
                if (context.options[i].type == 'lov') {
                    if (director.lov.direct == context.options[i].param) {
                        return context.options[i];
                    }
                }
            }
        }

        buildSelect(rowMap: any, opt: Option) {
            let well: any = rowMap[opt.param];
            let firstDiv = rowMap.searchArea.find('tbody > tr').eq(well.row).children('td').eq(well.col * 2);
            firstDiv.append('<span class="search-label">' + opt.name + '</span>');
            firstDiv.next().append(
                '<div class="search-item-lov">' +
                '<select id="_' + opt.param + '" class="selectpicker" data-live-search="true">' +
                '<option value="all" style="color:darkgray"> </option>' +
                '</select>' +
                '</div>');

            let sel = rowMap.searchArea.find('#_' + opt.param);


            if (opt.lov.option) {

                if (opt.lov.option[0] && opt.lov.option[0].length == 2) {
                    for (let i = 0; i < opt.lov.option.length; ++i) {
                        sel.append('<option value="' + opt.lov.option[i][0] + '">' + opt.lov.option[i][1] + '</option>');
                    }
                }
            } else {
                let speed = 0;
                let timer = setInterval(() => {
                    sel.parent().find("button").attr("disabled", true);
                    let title = "加载中";
                    for (let i = 0; i <= speed; ++i) {
                        title += ".";
                    }
                    sel.parent().find(".filter-option").text(title);
                    ++speed;
                    speed = speed % 3;
                }, 500);

                Util.Loading.pause();
                $.ajax({
                    type: "POST",
                    url: encodeURI(opt.lov.url),
                    success: (data: any) => {
                        Util.Loading.resume();
                        clearInterval(timer);
                        sel.parent().find("button").attr("disabled", false);
                        opt.lov.option = JSON.parse(data).lov;
                        if (opt.lov.option[0] && opt.lov.option[0].length == 2) {
                            for (let i = 0; i < opt.lov.option.length; ++i) {
                                sel.append('<option value="' + opt.lov.option[i][0] + '">' + opt.lov.option[i][1] + '</option>');
                            }
                        }
                        sel.selectpicker('refresh');
                    },
                    error: (XMLHttpRequest, textStatus, errorThrown) => {
                        Util.Loading.resume();
                        clearInterval(timer);
                        sel.parent().find("button").attr("disabled", true);
                        sel.parent().find(".filter-option").text("数据加载失败！");
                    }
                });
            }


            sel.selectpicker({
                // style: 'btn-info'
                size: 10,
                liveSearch : opt.type != 'lovNoSearch'
            });
            sel.on('changed.bs.select', (e) => {
                let val = sel.selectpicker("val");
                if (val != 'all') {
                    if (opt.lov.direct) {
                        let actor = this.lovActor(opt);
                        if (actor.lov.option) {
                            let actorElem = rowMap.searchArea.parent().find("#" + actor.param);
                            actorElem.empty();
                            actorElem.append('<option value="all" style="color:darkgray"></option>');
                            for (let i = 0; i < actor.lov.option.length; ++i) {
                                if (actor.lov.option[i][2] == val) {
                                    actorElem.append('<option value="' + actor.lov.option[i][0] + '">' + actor.lov.option[i][1] + '</option>');
                                }
                            }
                            actorElem.selectpicker('refresh');
                        }
                    }
                } else {
                    sel.find("option:selected").attr("selected", false);
                    sel.selectpicker('refresh');
                }
            });
        }

        setNow(time: Time, date) {
            if ('string' != typeof date) {
                if (date.year || date.hours) {
                    if (time.type == 'year') {
                        time.now = [date.year, 1, 1].join('-');
                    } else if (time.type == 'month') {
                        time.now = [date.year, date.month, 1].join('-');
                    } else if (!time.type || time.type == 'date') {
                        time.now = [date.year, date.month, date.date].join('-');
                    } else if (time.type == 'time') {
                        time.now = [date.hours, date.minutes, date.seconds].join(':');
                    } else if (time.type == 'datetime') {
                        time.now = [date.year, date.month, date.date].join('-') + " " + [date.hours, date.minutes, date.seconds].join(':')
                    }
                } else {
                    time.now = undefined;
                }
            } else {
                if (date) {
                    if (time.type == 'year') {
                        time.now = [date.substring(0, 4), 1, 1].join('-');
                    } else if (time.type == 'month') {
                        time.now = [date.substring(0, 4), date.substring(5, 7), 1].join('-');
                    } else if (!time.type || time.type == 'date') {
                        time.now = date
                    } else if (time.type == 'time') {
                        time.now = date
                    } else if (time.type == 'datetime') {
                        time.now = date
                    }
                } else {
                    time.now = undefined;
                }

            }

        }

        resetDate(opt: Option) {
            this.setNow(opt.date, opt.date.init);
            opt.date.ins = layui.laydate.render({
                elem: '#_' + opt.param,
                type: opt.date.type,
                value: opt.date.init,
                trigger: 'click',
                //showBottom: false,
                change: (value, date, endDate) => {
                    this.setNow(opt.date, date);
                }
            });
        }

        buildDate(rowMap: any, opt: Option) {
            let well: any = rowMap[opt.param];
            let firstDiv = rowMap.searchArea.find('tbody > tr').eq(well.row).children('td').eq(well.col * 2);
            firstDiv.append('<span class="search-label">' + opt.name + '</span>');
            firstDiv.next().append(
                '<div class="search-item-date">' +
                '<input type="text" class="layui-input" id="_' + opt.param + '" readonly="readlony">' +
                '</div>');
            this.resetDate(opt);
        }

        resetPeriod(opt: Option) {
            this.setNow(opt.period.start, opt.period.start.init);
            this.setNow(opt.period.end, opt.period.end.init);

            let cfg: any = {
                elem: '#_' + opt.param.replace(/(,|\s)/g, ''),
                type: opt.period.start.type,
                trigger: 'click',
                range: true,
                done: (value, date, endDate) => {
                    this.setNow(opt.period.start, date);
                    this.setNow(opt.period.end, endDate);
                }
                // ready: (date) {
                //     setNow(opt.period.start, date);
                // }
            };
            if (opt.period.start.init && opt.period.end.init) {
                cfg.value = opt.period.start.init + ' - ' + opt.period.end.init;
            }
            opt.period.start.ins = layui.laydate.render(cfg);
        }


        buildPeriod(rowMap: any, opt: Option) {
            let well: any = rowMap[opt.param];
            let firstDiv = rowMap.searchArea.find('tbody > tr').eq(well.row).children('td').eq(well.col * 2);

            firstDiv.append('<span class="search-label">' + opt.name + '</span>');
            firstDiv.next().append(
                '<div class="search-item-period">' +
                '<input type="text" class="layui-input" id="_' + opt.param.replace(/(,|\s)/g, '') + '" readonly="readlony">' +
                '</div>');

            this.resetPeriod(opt);

        }

        assignRow() {
            let rowMap: any = {};
            let itemCount = 0;
            let rowCount = 0;
            //每两个条件一行
            for (let i = 0; i < context.options.length; ++i) {
                // if (context.options[i].type == 'period') {
                //     //区间单独占一行显示
                //     rowCount = Util.roundDiv(itemCount, 2);
                //     rowCount += 1;
                //     itemCount = rowCount * 2;
                //     rowMap[context.options[i].param] = {
                //         row: rowCount - 1,
                //         col: 0
                //     };
                // }
                // else {
                ++itemCount;
                rowCount = Util.roundDiv(itemCount, 2);
                rowMap[context.options[i].param] = {
                    row: rowCount - 1,
                    col: (itemCount - 1) % 2
                };

                // }
            }
            rowCount = Util.roundDiv(itemCount, 2);

            //打印与导出单独占一行
            rowMap.rowCount = rowCount + 1;
            return rowMap;
        }

        buildStructure() {
            let optArea = $(".option-area");
            let rowMap: any = this.assignRow();
            let searchbtn = optArea.find('.btn-search').remove();
            let resetbtn = optArea.find('.btn-reset').remove();
            let funbtns = optArea.find('.cux-btn-group-fun').remove();
            let optMap = {};
            optArea.append("<table><thead><tr>" +
                '<th class="th1"></th>' +
                '<th class="th2"></th>' +
                '<th class="th3"></th>' +
                '<th class="th4"></th>' +
                '<th class="th5"></th>' +
                "</tr></thead><tbody></tbody></table>");
            let tbArea = optArea.find("table > tbody");
            for (let i = 0; i < rowMap.rowCount - 1; ++i) {
                tbArea.append('<tr>' +
                    '<td></td>' +
                    '<td></td>' +
                    '<td></td>' +
                    '<td></td>' +
                    '<td></td>' +
                    '</tr>');
            }
            optArea.append('<div class="row">' +
                '<div class="col-md-12" class="search-func"></div>' +
                '</div>');

            if (rowMap.rowCount == 2) {
                tbArea.find('tr:eq(0)').children('td:eq(4)').append(searchbtn)
                    .append(resetbtn);
            } else {
                tbArea.find('tr:eq(0)').children('td:eq(4)').append(searchbtn);
                tbArea.find('tr:eq(1)').children('td:eq(4)').append(resetbtn);
            }

            optArea.children('div').children('div').append(funbtns);
            rowMap.searchArea = optArea;
            this.updateFavorite();
            return rowMap;
        }

        updateFavorite() {
            if (!context.isFavorite) {
                $('.btn-collect').empty();
                $('.btn-collect').append('<i class="fa fa-star"> </i>收藏');
            } else {
                $('.btn-collect').empty();
                $('.btn-collect').append('<i class="fa fa-star-o"> </i>取消收藏');
            }
        }


        buildInput(rowMap: any, opt: Option) {
            let well: any = rowMap[opt.param];
            let firstDiv = rowMap.searchArea.find('tbody > tr').eq(well.row).children('td').eq(well.col * 2);
            firstDiv.append('<span class="search-label">' + opt.name + '</span>');
            if (opt.input && opt.input.hint){
                firstDiv.find(".search-label").append('<i class="search-label-warning hidden">' + opt.input.hint + '</i>');
            }else{
                firstDiv.find(".search-label").append('<i class="search-label-warning hidden">输入错误</i>');
            }
            firstDiv.next().append(
                '<div class="search-item-input">' +
                '<input type="text" id="_' + opt.param + '">' +
                '</div>');
            if (opt.input && opt.input.validation){
                var reg = new RegExp(opt.input.validation, 'g');
                var $input = $("#_" + opt.param);
                $input.on("change", ()=>{
                    let val = $input.val();
                    if (reg.test(val)){
                        firstDiv.find(".search-label-warning").addClass("hidden");
                    }else{
                        firstDiv.find(".search-label-warning").removeClass("hidden");
                        $input.val("");
                    }
                });
            }
        }

        buildSearchItems() {
            let rowMap: any = this.buildStructure();
            for (let i = 0; i < context.options.length; ++i) {
                if (!context.options[i].type ||
                    context.options[i].type == 'lov' ||
                    context.options[i].type == 'lovNoSearch') {
                    this.buildSelect(rowMap, context.options[i]);
                } else if (context.options[i].type == 'date') {
                    this.buildDate(rowMap, context.options[i]);
                } else if (context.options[i].type == 'period') {
                    this.buildPeriod(rowMap, context.options[i]);
                } else if (context.options[i].type == 'input') {
                    this.buildInput(rowMap, context.options[i]);
                }
            }
            $(".option-area").removeClass("hidden");
        }

        updateSearchOption(){
            this.dataOpt = {
                pgSize: this.tablePanel.getPgSize(),
                pgNum: 0
            };
            for (let i = 0; i < context.options.length; ++i) {
                if (!context.options[i].type ||
                    context.options[i].type == 'lov' ||
                    context.options[i].type == 'lovNoSearch') {
                    this.dataOpt[context.options[i].param] = $("#_" + context.options[i].param).selectpicker("val");
                    if (!this.dataOpt[context.options[i].param] || 'all' == this.dataOpt[context.options[i].param]) {
                        this.dataOpt[context.options[i].param] = undefined;
                    }
                } else if (context.options[i].type == 'date') {
                    this.dataOpt[context.options[i].param] = context.options[i].date.now;
                } else if (context.options[i].type == 'period') {
                    let params = context.options[i].param.replace(/\s/g, '').split(",");
                    this.dataOpt[params[0]] = context.options[i].period.start.now;
                    this.dataOpt[params[1]] = context.options[i].period.end.now;
                } else if (context.options[i].type == 'input') {
                    this.dataOpt[context.options[i].param] = $("#_" + context.options[i].param).val();
                    if (!this.dataOpt[context.options[i].param]) {
                        this.dataOpt[context.options[i].param] = undefined;
                    }
                }

            }
        }

        getSearchOption() {
            if (!this.dataOpt){
                this.updateSearchOption();
            }
            return this.dataOpt;
        }

        onClickSearch() {
            this.tablePanel.updatePgSize();
            this.updateSearchOption();

            $.ajax({
                type: "POST",
                url: encodeURI(context.updateUrl),
                data: this.dataOpt,
                success: (data: any) => {
                    this.tablePanel.updateTable(JSON.parse(data), this.dataOpt);
                },
                error: (XMLHttpRequest, textStatus, errorThrown) => {
                    //promise.failed(textStatus);
                }
            });
        }

        onClickExport() {
            var dataOpt = this.getSearchOption();
            let ids = this.tablePanel.getSelRows();
            if (ids){
                dataOpt['ids'] = JSON.stringify(ids);
            }
            let optTmp = [];

            for (let index in dataOpt) {
                if (dataOpt[index]) {
                    optTmp.push(index + "=" + dataOpt[index]);
                }
            }
            var params = optTmp.join("&");
            $("#exportForm")[0].action = encodeURI(context.exportUrl + "?" + params);
            $("#exportForm")[0].submit();
        }

        onClickPrint() {
            let ids = this.tablePanel.getSelRows();
            if (ids){
                var dataOpt = this.getSearchOption();
                let optTmp = ["ids=" + JSON.stringify(ids)];
                for (let index in dataOpt) {
                    if (dataOpt[index]) {
                        optTmp.push(index + "=" + dataOpt[index]);
                    }
                }
                var params = optTmp.join("&");
                $("#exportForm")[0].action = encodeURI(context.printUrl + "?" + params);
                $("#exportForm")[0].submit();
            }
        }

        onClickReset() {
            for (let i = 0; i < context.options.length; ++i) {
                if (!context.options[i].type ||
                    context.options[i].type == 'lov' ||
                    context.options[i].type == 'lovNoSearch') {
                    $("#_" + context.options[i].param + " option:selected").attr("selected", false)
                    $("#_" + context.options[i].param).selectpicker('refresh');
                } else if (context.options[i].type == 'date') {
                    this.resetDate(context.options[i]);
                } else if (context.options[i].type == 'period') {
                    this.resetPeriod(context.options[i]);
                } else if (context.options[i].type == 'input') {
                    $("#_" + context.options[i].param).val("");
                }
            }
        }

        onClickCollect() {
            if (context.isFavorite) {
                $.ajax({
                    type: "POST",
                    url: encodeURI(context.baseUrl + 'report/unfavorite.do'),
                    data: {
                        item: context.item
                    },
                    success: (data: any) => {
                        context.isFavorite = false;
                        this.updateFavorite();
                    },
                    error: (XMLHttpRequest, textStatus, errorThrown) => {
                        //promise.failed(textStatus);
                    }
                });
            } else {
                $.ajax({
                    type: "POST",
                    url: encodeURI(context.baseUrl + 'report/favorite.do'),
                    data: {
                        item: context.item
                    },
                    success: (data: any) => {
                        context.isFavorite = true;
                        this.updateFavorite();
                    },
                    error: (XMLHttpRequest, textStatus, errorThrown) => {
                        //promise.failed(textStatus);
                    }
                });
            }
        }
    }

    export let searchPanel: SearchPanel = null;

    layui.config({
        base: context.baseUrl + 'jsp/plugins/layui/lay/modules/'
    }).use(['laydate', 'element'], function () {
        searchPanel = new SearchPanel(new TablePanel());
    });


}