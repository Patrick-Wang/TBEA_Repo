module search {

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
    }

    interface Option {
        param: string; //对于period param = first,second
        name: string; //对于period name = first,second
        type: string;//lov date period input
        date: Time;
        lov: Lov;
        period: Period;
    }


    interface Context {
        pager: boolean;
        options: Option[];
        updateUrl: string;
        exportUrl: string;
        baseUrl:string;
        isFavorite:boolean;
        item:number;
    }


    declare var context: Context;
    declare var $: any;
    declare var layui: any;


    let pgSize = 5;

    function lovDirector(actor: Option) {
        for (let i = 0; i < context.options.length; ++i) {
            if (context.options[i].type == 'lov') {
                if (context.options[i].lov.direct == actor.param) {
                    return context.options[i];
                }
            }
        }
    }

    function lovActor(director: Option) {
        for (let i = 0; i < context.options.length; ++i) {
            if (context.options[i].type == 'lov') {
                if (director.lov.direct == context.options[i].param) {
                    return context.options[i];
                }
            }
        }
    }

    function buildSelect(rowMap: any, opt: Option) {
        let well: any = rowMap[opt.param];
        let firstDiv = rowMap.searchArea.find('tbody > tr').eq(well.row).children('td').eq(well.col * 2);
        firstDiv.append('<span class="search-label">' + opt.name + '</span>');
        firstDiv.next().append(
            '<div class="search-item-lov">' +
            '<select id="_' + opt.param + '" class="selectpicker" data-live-search="true" title="' + opt.name + '" >' +
            '<option value="all" style="color:darkgray">' + opt.name + '</option>' +
            '</select>' +
            '</div>');

        let sel = rowMap.searchArea.find('#_' + opt.param);
        if (opt.lov.option[0] && opt.lov.option[0].length == 2) {
            for (let i = 0; i < opt.lov.option.length; ++i) {
                sel.append('<option value="' + opt.lov.option[i][0] + '">' + opt.lov.option[i][1] + '</option>');
            }
        }
        sel.selectpicker({
            // style: 'btn-info'
            size: 10
        });
        sel.on('changed.bs.select', function (e) {
            let val = sel.selectpicker("val");
            if (val != 'all') {
                if (opt.lov.direct) {
                    let actor = lovActor(opt);
                    let actorElem = rowMap.searchArea.parent().find("select[title='" + actor.name + "']");
                    actorElem.empty();
                    actorElem.append('<option value="all" style="color:darkgray">' + actor.name + '</option>');
                    for (let i = 0; i < actor.lov.option.length; ++i) {
                        if (actor.lov.option[i][2] == val) {
                            actorElem.append('<option value="' + actor.lov.option[i][0] + '">' + actor.lov.option[i][1] + '</option>');
                        }
                    }
                    actorElem.selectpicker('refresh');
                }
            } else {
                sel.find("option:selected").attr("selected", false);
                sel.selectpicker('refresh');
            }
        });
    }


    function setNow(time: Time, date) {
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
    }

    function resetDate(opt: Option) {
        opt.date.ins = layui.laydate.render({
            elem: '#_' + opt.param,
            type: opt.date.type,
            value: opt.date.init,
            trigger: 'click',
            change: function (value, date, endDate) {
                setNow(opt.date, date);
            },
            ready: function (date) {
                setNow(opt.date, date);
            }
        });

        setTimeout(function () {
            $('#_' + opt.param).trigger('click');
            $('#_' + opt.param).parent().trigger('click');
        }, 100);
    }

    function buildDate(rowMap: any, opt: Option) {
        let well: any = rowMap[opt.param];
        let firstDiv = rowMap.searchArea.find('tbody > tr').eq(well.row).children('td').eq(well.col * 2);
        firstDiv.append('<span class="search-label">' + opt.name + '</span>');
        firstDiv.next().append(
            '<div class="search-item-date">' +
            '<input type="text" class="layui-input" id="_' + opt.param + ' readonly="readlony">' +
            '</div>');
        resetDate(opt);
    }

    function resetPeriodStart(opt: Option) {
        let params = opt.param.replace(/\s/g, '').split(",");
        opt.period.start.ins = layui.laydate.render({
            elem: '#_' + params[0],
            type: opt.period.start.type,
            value: opt.period.start.init,
            trigger: 'click',
            change: function (value, date, endDate) {
                setNow(opt.period.start, date);
            },
            ready: function (date) {
                setNow(opt.period.start, date);
            }
        });
        setTimeout(function () {
            $('#_' + params[0]).trigger('click');
            $('#_' + params[0]).parent().trigger('click');
        }, 100);
    }

    function resetPeriodEnd(opt: Option) {
        let params = opt.param.replace(/\s/g, '').split(",");
        opt.period.end.ins = layui.laydate.render({
            elem: '#_' + params[1],
            type: opt.period.end.type,
            value: opt.period.end.init,
            trigger: 'click',
            change: function (value, date, endDate) {
                setNow(opt.period.end, date);
            },
            ready: function (date) {
                setNow(opt.period.end, date);
            }
        });
        setTimeout(function () {
            $('#_' + params[1]).trigger('click');
            $('#_' + params[1]).parent().trigger('click');
        }, 100);
    }

    function buildPeriod(rowMap: any, opt: Option) {
        let well: any = rowMap[opt.param];
        let firstDiv = rowMap.searchArea.find('tbody > tr').eq(well.row).children('td').eq(well.col * 2);
        let names = opt.name.replace(/\s/g, '').split(",");
        let params = opt.param.replace(/\s/g, '').split(",");
        firstDiv.append('<span class="search-label">' + names[0] + '</span>');
        firstDiv.next().append(
            '<div class="search-item-period">' +
            '<input type="text" class="layui-input" id="_' + params[0] + '" readonly="readlony">' +
            '</div>');
        let secondDiv = firstDiv.next().next();
        secondDiv.append('<span class="search-label">' + names[1] + '</span>');
        secondDiv.next().append(
            '<div class="search-item-period">' +
            '<input type="text" class="layui-input" id="_' + params[1] + '" readonly="readlony">' +
            '</div>');

        resetPeriodStart(opt);
        resetPeriodEnd(opt);

    }

    function assignRow() {
        let rowMap: any = {};
        let itemCount = 0;
        let rowCount = 0;
        //每两个条件一行
        for (let i = 0; i < context.options.length; ++i) {
            if (context.options[i].type == 'period') {
                //区间单独占一行显示
                rowCount = Util.roundDiv(itemCount, 2);
                rowCount += 1;
                itemCount = rowCount * 2;
                rowMap[context.options[i].param] = {
                    row: rowCount - 1,
                    col: 0
                };
            }
            else {
                ++itemCount;
                rowCount = Util.roundDiv(itemCount, 2);
                rowMap[context.options[i].param] = {
                    row: rowCount - 1,
                    col: (itemCount - 1) % 2
                };

            }
        }
        rowCount = Util.roundDiv(itemCount, 2);

        //打印与导出单独占一行
        rowMap.rowCount = rowCount + 1;
        return rowMap;
    }

    function buildStructure() {
        let optArea = $(".option-area");
        let rowMap: any = assignRow();
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
        updateFavorite();
        return rowMap;
    }
    
    function updateFavorite(){
        if (!context.isFavorite){
            $('.btn-collect').empty();
            $('.btn-collect').append('<i class="fa fa-star"> </i>收藏');
        }else{
            $('.btn-collect').empty();
            $('.btn-collect').append('<i class="fa fa-star-o"> </i>取消收藏');
        }
    }


    function buildInput(rowMap: any, opt: Option) {
        let well: any = rowMap[opt.param];
        let firstDiv = rowMap.searchArea.find('tbody > tr').eq(well.row).children('td').eq(well.col * 2);
        firstDiv.append('<span class="search-label">' + opt.name + '</span>');
        firstDiv.next().append(
            '<div class="search-item-input">' +
                '<input type="text" id="_' + opt.param + '">' +
            '</div>');
    }

    function buildSearchItems() {
        let rowMap: any = buildStructure();
        for (let i = 0; i < context.options.length; ++i) {
            if (!context.options[i].type || context.options[i].type == 'lov') {
                buildSelect(rowMap, context.options[i]);
            } else if (context.options[i].type == 'date') {
                buildDate(rowMap, context.options[i]);
            } else if (context.options[i].type == 'period') {
                buildPeriod(rowMap, context.options[i]);
            } else if (context.options[i].type == 'input') {
                buildInput(rowMap, context.options[i]);
            }
        }
        $(".option-area").removeClass("hidden");
    }


    function updatePgSize() {
        let tbHeader = 30;
        let tbPager = 30;
        let rowHeight = 33;
        let wellPaddingTop = $("#table").parent().css("padding-top").replace("px", "");
        let wellPaddingBottom = $("#table").parent().css("padding-bottom").replace("px", "");
        let navHeight = $(".nav-area").css("height").replace("px", "");
        let searchHeight = $(".search-area").css("height").replace("px", "");
        let leftHeight = document.documentElement.clientHeight -
            navHeight -
            searchHeight;
        let bodyHeight = leftHeight - tbHeader - tbPager - wellPaddingBottom - wellPaddingTop;
        let newPgSize = Util.zeroDiv(bodyHeight, rowHeight);
        pgSize = newPgSize > 5 ? newPgSize : 5;
    }


    function getSearchOption() {
        var dataOpt = {
            pgSize: pgSize,
            pgNum: 0
        };
        for (let i = 0; i < context.options.length; ++i) {
            if (!context.options[i].type || context.options[i].type == 'lov') {
                dataOpt[context.options[i].param] = $("#_" + context.options[i].param).selectpicker("val");
                if (!dataOpt[context.options[i].param]) {
                    dataOpt[context.options[i].param] = 'all';
                }
            } else if (context.options[i].type == 'date') {
                dataOpt[context.options[i].param] = context.options[i].date.now;
            } else if (context.options[i].type == 'period') {
                let params = context.options[i].param.replace(/\s/g, '').split(",");
                dataOpt[params[0]] = context.options[i].period.start.now;
                dataOpt[params[1]] = context.options[i].period.end.now;
            } else if (context.options[i].type == 'input') {
                dataOpt[context.options[i].param] = $("#_" + context.options[i].param).val("");
                if (!dataOpt[context.options[i].param]) {
                    dataOpt[context.options[i].param] = 'all';
                }
            }
        }
        return dataOpt;
    }

    export function onClickSearch() {
        updatePgSize();

        var dataOpt = getSearchOption();


        $.ajax({
            type: "POST",
            url: encodeURI(context.updateUrl),
            data: dataOpt,
            success: (data: any) => {
                updateTable(JSON.parse(data), dataOpt);
            },
            error: (XMLHttpRequest, textStatus, errorThrown) => {
                //promise.failed(textStatus);
            }
        });
    }

    export function onClickExport() {
        var dataOpt = getSearchOption();
        let optTmp = [];

        for (let index in dataOpt) {
            optTmp.push(index + "=" + dataOpt[index]);
        }
        var params = optTmp.join("&");
        $("#exportForm")[0].action = context.exportUrl + "?" + params;
        $("#exportForm")[0].submit();
    }

    export function onClickReset() {
        for (let i = 0; i < context.options.length; ++i) {
            if (!context.options[i].type || context.options[i].type == 'lov') {
                $("#_" + context.options[i].param + " option:selected").attr("selected", false)
                $("#_" + context.options[i].param).selectpicker('refresh');
            } else if (context.options[i].type == 'date') {
                resetDate(context.options[i]);
            } else if (context.options[i].type == 'period') {
                resetPeriodStart(context.options[i]);
                resetPeriodEnd(context.options[i]);
            } else if (context.options[i].type == 'input') {
                $("#_" + context.options[i].param).val("");
            }
        }
    }

    export function onClickCollect(){
        if (context.isFavorite){
            $.ajax({
                type: "POST",
                url: encodeURI(context.baseUrl + 'report/unfavorite.do'),
                data: {
                    item : context.item
                },
                success: (data: any) => {
                    context.isFavorite = false;
                    updateFavorite();
                },
                error: (XMLHttpRequest, textStatus, errorThrown) => {
                    //promise.failed(textStatus);
                }
            });
        }else{
            $.ajax({
                type: "POST",
                url: encodeURI(context.baseUrl + 'report/favorite.do'),
                data: {
                    item : context.item
                },
                success: (data: any) => {
                    context.isFavorite = true;
                    updateFavorite();
                },
                error: (XMLHttpRequest, textStatus, errorThrown) => {
                    //promise.failed(textStatus);
                }
            });
        }
    }


    function adjustSize() {
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

    function updateTable(gridCtrl, dataOpt) {
        $("#table").empty();
        $("#table").append('<table id="table-jq"></table><div id="pager"></div>');
        let tableAssist = Util.createTable("table-jq", gridCtrl)
        tableAssist.create({
            assistData: gridCtrl.data,
            assistTotal: Util.roundDiv(gridCtrl.dataCount, pgSize),
            assistRecords: gridCtrl.dataCount,
            assistPagedata: (postdata) => {
                dataOpt.pgNum = postdata.page - 1;
                $.ajax({
                    type: "POST",
                    url: encodeURI(context.updateUrl),
                    data: dataOpt,
                    success: (data: any) => {
                        let dataNew = JSON.parse(data);
                        tableAssist.addData(
                            Util.roundDiv(dataNew.dataCount, pgSize),
                            postdata.page,
                            dataNew.dataCount,
                            dataNew.data, undefined);

                    },
                    error: (XMLHttpRequest, textStatus, errorThrown) => {
                        //promise.failed(textStatus);
                    }
                });

            },
            cellEdit: false,
            cellsubmit: 'clientArray',
            multiselect: false,
            drag: false,
            resize: false,
            height: '100%',
            shrinkToFit: true,
            assistEditable: false,
            autoScroll: true,
            rowNum: pgSize,
            viewrecords: true,
            pager: context.pager ? "#pager" : undefined
        });
        adjustSize();
    }

    $(window).on('resize', function () {
        adjustSize();
    });

    buildSearchItems();
}