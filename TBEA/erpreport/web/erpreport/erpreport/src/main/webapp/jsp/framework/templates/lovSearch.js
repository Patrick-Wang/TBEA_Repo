var search;
(function (search) {
    var pgSize = 5;
    function lovDirector(actor) {
        for (var i = 0; i < context.options.length; ++i) {
            if (context.options[i].type == 'lov') {
                if (context.options[i].lov.direct == actor.param) {
                    return context.options[i];
                }
            }
        }
    }
    function lovActor(director) {
        for (var i = 0; i < context.options.length; ++i) {
            if (context.options[i].type == 'lov') {
                if (director.lov.direct == context.options[i].param) {
                    return context.options[i];
                }
            }
        }
    }
    function buildSelect(rowMap, opt) {
        var well = rowMap[opt.param];
        var firstDiv = rowMap.searchArea.find('tbody > tr').eq(well.row).children('td').eq(well.col * 2);
        firstDiv.append('<span class="search-label">' + opt.name + '</span>');
        firstDiv.next().append('<div class="search-item-lov">' +
            '<select id="_' + opt.param + '" class="selectpicker" data-live-search="true">' +
            '<option value="all" style="color:darkgray"> </option>' +
            '</select>' +
            '</div>');
        var sel = rowMap.searchArea.find('#_' + opt.param);
        if (opt.lov.option[0] && opt.lov.option[0].length == 2) {
            for (var i = 0; i < opt.lov.option.length; ++i) {
                sel.append('<option value="' + opt.lov.option[i][0] + '">' + opt.lov.option[i][1] + '</option>');
            }
        }
        sel.selectpicker({
            // style: 'btn-info'
            size: 10
        });
        sel.on('changed.bs.select', function (e) {
            var val = sel.selectpicker("val");
            if (val != 'all') {
                if (opt.lov.direct) {
                    var actor = lovActor(opt);
                    if (actor.lov.option) {
                        var actorElem = rowMap.searchArea.parent().find("#" + actor.param);
                        actorElem.empty();
                        actorElem.append('<option value="all" style="color:darkgray"></option>');
                        for (var i = 0; i < actor.lov.option.length; ++i) {
                            if (actor.lov.option[i][2] == val) {
                                actorElem.append('<option value="' + actor.lov.option[i][0] + '">' + actor.lov.option[i][1] + '</option>');
                            }
                        }
                        actorElem.selectpicker('refresh');
                    }
                }
            }
            else {
                sel.find("option:selected").attr("selected", false);
                sel.selectpicker('refresh');
            }
        });
    }
    function buildAsyncSelect(rowMap, opt) {
        var well = rowMap[opt.param];
        var firstDiv = rowMap.searchArea.find('tbody > tr').eq(well.row).children('td').eq(well.col * 2);
        firstDiv.append('<span class="search-label">' + opt.name + '</span>');
        firstDiv.next().append('<div class="search-item-lov">' +
            '<select id="_' + opt.param + '" class="selectpicker" data-live-search="true">' +
            '<option value="all" style="color:darkgray"> </option>' +
            '</select>' +
            '</div>');
        var sel = rowMap.searchArea.find('#_' + opt.param);
        var speed = 0;
        var ins = setInterval(function () {
            sel.parent().find("button").attr("disabled", true);
            var title = "加载中";
            for (var i = 0; i <= speed; ++i) {
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
            success: function (data) {
                Util.Loading.resume();
                clearInterval(ins);
                sel.parent().find("button").attr("disabled", false);
                opt.lov.option = JSON.parse(data).lov;
                if (opt.lov.option[0] && opt.lov.option[0].length == 2) {
                    for (var i = 0; i < opt.lov.option.length; ++i) {
                        sel.append('<option value="' + opt.lov.option[i][0] + '">' + opt.lov.option[i][1] + '</option>');
                    }
                    sel.selectpicker('refresh');
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                Util.Loading.resume();
                sel.parent().find("button").attr("disabled", true);
                clearInterval(ins);
                sel.parent().find(".filter-option").text("数据加载失败！");
            }
        });
        sel.selectpicker({
            // style: 'btn-info'
            size: 10
        });
        sel.on('changed.bs.select', function (e) {
            var val = sel.selectpicker("val");
            if (val != 'all') {
                if (opt.lov.direct) {
                    var actor = lovActor(opt);
                    if (actor.lov.option) {
                        var actorElem = rowMap.searchArea.parent().find("#" + actor.param + "']");
                        actorElem.empty();
                        actorElem.append('<option value="all" style="color:darkgray"></option>');
                        for (var i = 0; i < actor.lov.option.length; ++i) {
                            if (actor.lov.option[i][2] == val) {
                                actorElem.append('<option value="' + actor.lov.option[i][0] + '">' + actor.lov.option[i][1] + '</option>');
                            }
                        }
                        actorElem.selectpicker('refresh');
                    }
                }
            }
            else {
                sel.find("option:selected").attr("selected", false);
                sel.selectpicker('refresh');
            }
        });
    }
    function setNow(time, date) {
        if ('string' != typeof date) {
            if (date.year || date.hours) {
                if (time.type == 'year') {
                    time.now = [date.year, 1, 1].join('-');
                }
                else if (time.type == 'month') {
                    time.now = [date.year, date.month, 1].join('-');
                }
                else if (!time.type || time.type == 'date') {
                    time.now = [date.year, date.month, date.date].join('-');
                }
                else if (time.type == 'time') {
                    time.now = [date.hours, date.minutes, date.seconds].join(':');
                }
                else if (time.type == 'datetime') {
                    time.now = [date.year, date.month, date.date].join('-') + " " + [date.hours, date.minutes, date.seconds].join(':');
                }
            }
            else {
                time.now = undefined;
            }
        }
        else {
            if (date) {
                if (time.type == 'year') {
                    time.now = [date.substring(0, 4), 1, 1].join('-');
                }
                else if (time.type == 'month') {
                    time.now = [date.substring(0, 4), date.substring(5, 7), 1].join('-');
                }
                else if (!time.type || time.type == 'date') {
                    time.now = date;
                }
                else if (time.type == 'time') {
                    time.now = date;
                }
                else if (time.type == 'datetime') {
                    time.now = date;
                }
            }
            else {
                time.now = undefined;
            }
        }
    }
    function resetDate(opt) {
        setNow(opt.date, opt.date.init);
        opt.date.ins = layui.laydate.render({
            elem: '#_' + opt.param,
            type: opt.date.type,
            value: opt.date.init,
            trigger: 'click',
            //showBottom: false,
            change: function (value, date, endDate) {
                setNow(opt.date, date);
            }
            // ready: function (date) {
            //     setNow(opt.date, date);
            // }
        });
        // setTimeout(function () {
        //     $('#_' + opt.param).trigger('click');
        //     $('#_' + opt.param).parent().trigger('click');
        // }, 100);
    }
    function buildDate(rowMap, opt) {
        var well = rowMap[opt.param];
        var firstDiv = rowMap.searchArea.find('tbody > tr').eq(well.row).children('td').eq(well.col * 2);
        firstDiv.append('<span class="search-label">' + opt.name + '</span>');
        firstDiv.next().append('<div class="search-item-date">' +
            '<input type="text" class="layui-input" id="_' + opt.param + '" readonly="readlony">' +
            '</div>');
        resetDate(opt);
    }
    // function resetPeriodStart(opt: Option) {
    //     let params = opt.param.replace(/\s/g, '').split(",");
    //     opt.period.start.ins = layui.laydate.render({
    //         elem: '#_' + params[0],
    //         type: opt.period.start.type,
    //         value: opt.period.start.init,
    //         trigger: 'click',
    //         showBottom: false,
    //         change: function (value, date, endDate) {
    //             setNow(opt.period.start, date);
    //         },
    //         ready: function (date) {
    //             setNow(opt.period.start, date);
    //         }
    //     });
    //     setTimeout(function () {
    //         $('#_' + params[0]).trigger('click');
    //         $('#_' + params[0]).parent().trigger('click');
    //     }, 100);
    // }
    function resetPeriod(opt) {
        setNow(opt.period.start, opt.period.start.init);
        setNow(opt.period.end, opt.period.end.init);
        var cfg = {
            elem: '#_' + opt.param.replace(/(,|\s)/g, ''),
            type: opt.period.start.type,
            trigger: 'click',
            range: true,
            done: function (value, date, endDate) {
                setNow(opt.period.start, date);
                setNow(opt.period.end, endDate);
            }
            // ready: function (date) {
            //     setNow(opt.period.start, date);
            // }
        };
        if (opt.period.start.init && opt.period.end.init) {
            cfg.value = opt.period.start.init + ' - ' + opt.period.end.init;
        }
        opt.period.start.ins = layui.laydate.render(cfg);
    }
    // function resetPeriodEnd(opt: Option) {
    //     let params = opt.param.replace(/\s/g, '').split(",");
    //     opt.period.end.ins = layui.laydate.render({
    //         elem: '#_' + params[1],
    //         type: opt.period.end.type,
    //         value: opt.period.end.init,
    //         trigger: 'click',
    //         change: function (value, date, endDate) {
    //             setNow(opt.period.end, date);
    //         },
    //         ready: function (date) {
    //             setNow(opt.period.end, date);
    //         }
    //     });
    //     setTimeout(function () {
    //         $('#_' + params[1]).trigger('click');
    //         $('#_' + params[1]).parent().trigger('click');
    //     }, 100);
    // }
    function buildPeriod(rowMap, opt) {
        var well = rowMap[opt.param];
        var firstDiv = rowMap.searchArea.find('tbody > tr').eq(well.row).children('td').eq(well.col * 2);
        //  let names = opt.name.replace(/\s/g, '').split(",");
        // let params = opt.param.replace(/\s/g, '').split(",");
        firstDiv.append('<span class="search-label">' + opt.name + '</span>');
        firstDiv.next().append('<div class="search-item-period">' +
            '<input type="text" class="layui-input" id="_' + opt.param.replace(/(,|\s)/g, '') + '" readonly="readlony">' +
            '</div>');
        // let secondDiv = firstDiv.next().next();
        // secondDiv.append('<span class="search-label">' + names[1] + '</span>');
        // secondDiv.next().append(
        //     '<div class="search-item-period">' +
        //     '<input type="text" class="layui-input" id="_' + params[1] + '" readonly="readlony">' +
        //     '</div>');
        resetPeriod(opt);
        // resetPeriodStart(opt);
        // resetPeriodEnd(opt);
    }
    function assignRow() {
        var rowMap = {};
        var itemCount = 0;
        var rowCount = 0;
        //每两个条件一行
        for (var i = 0; i < context.options.length; ++i) {
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
    function buildStructure() {
        var optArea = $(".option-area");
        var rowMap = assignRow();
        var searchbtn = optArea.find('.btn-search').remove();
        var resetbtn = optArea.find('.btn-reset').remove();
        var funbtns = optArea.find('.cux-btn-group-fun').remove();
        var optMap = {};
        optArea.append("<table><thead><tr>" +
            '<th class="th1"></th>' +
            '<th class="th2"></th>' +
            '<th class="th3"></th>' +
            '<th class="th4"></th>' +
            '<th class="th5"></th>' +
            "</tr></thead><tbody></tbody></table>");
        var tbArea = optArea.find("table > tbody");
        for (var i = 0; i < rowMap.rowCount - 1; ++i) {
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
        }
        else {
            tbArea.find('tr:eq(0)').children('td:eq(4)').append(searchbtn);
            tbArea.find('tr:eq(1)').children('td:eq(4)').append(resetbtn);
        }
        optArea.children('div').children('div').append(funbtns);
        rowMap.searchArea = optArea;
        updateFavorite();
        return rowMap;
    }
    function updateFavorite() {
        if (!context.isFavorite) {
            $('.btn-collect').empty();
            $('.btn-collect').append('<i class="fa fa-star"> </i>收藏');
        }
        else {
            $('.btn-collect').empty();
            $('.btn-collect').append('<i class="fa fa-star-o"> </i>取消收藏');
        }
    }
    function buildInput(rowMap, opt) {
        var well = rowMap[opt.param];
        var firstDiv = rowMap.searchArea.find('tbody > tr').eq(well.row).children('td').eq(well.col * 2);
        firstDiv.append('<span class="search-label">' + opt.name + '</span>');
        firstDiv.next().append('<div class="search-item-input">' +
            '<input type="text" id="_' + opt.param + '">' +
            '</div>');
    }
    function buildSearchItems() {
        var rowMap = buildStructure();
        for (var i = 0; i < context.options.length; ++i) {
            if (!context.options[i].type || context.options[i].type == 'lov') {
                if (context.options[i].lov.option) {
                    buildSelect(rowMap, context.options[i]);
                }
                else {
                    buildAsyncSelect(rowMap, context.options[i]);
                }
            }
            else if (context.options[i].type == 'date') {
                buildDate(rowMap, context.options[i]);
            }
            else if (context.options[i].type == 'period') {
                buildPeriod(rowMap, context.options[i]);
            }
            else if (context.options[i].type == 'input') {
                buildInput(rowMap, context.options[i]);
            }
        }
        $(".option-area").removeClass("hidden");
    }
    function updatePgSize() {
        var tbHeader = 28;
        var tbPager = 28;
        var rowHeight = 26;
        var wellPaddingTop = $("#table").parent().css("padding-top").replace("px", "");
        var wellPaddingBottom = $("#table").parent().css("padding-bottom").replace("px", "");
        var navHeight = $(".nav-area").css("height").replace("px", "");
        var searchHeight = $(".search-area").css("height").replace("px", "");
        var leftHeight = document.documentElement.clientHeight -
            navHeight -
            searchHeight;
        var bodyHeight = leftHeight - tbHeader - tbPager - wellPaddingBottom - wellPaddingTop;
        var newPgSize = Util.zeroDiv(bodyHeight, rowHeight);
        pgSize = newPgSize > 5 ? newPgSize : 5;
        $("#table").parent().css("height", leftHeight + "px");
    }
    function getSearchOption() {
        var dataOpt = {
            pgSize: pgSize,
            pgNum: 0
        };
        for (var i = 0; i < context.options.length; ++i) {
            if (!context.options[i].type || context.options[i].type == 'lov') {
                dataOpt[context.options[i].param] = $("#_" + context.options[i].param).selectpicker("val");
                if (!dataOpt[context.options[i].param] || 'all' == dataOpt[context.options[i].param]) {
                    dataOpt[context.options[i].param] = undefined;
                }
            }
            else if (context.options[i].type == 'date') {
                dataOpt[context.options[i].param] = context.options[i].date.now;
            }
            else if (context.options[i].type == 'period') {
                var params = context.options[i].param.replace(/\s/g, '').split(",");
                dataOpt[params[0]] = context.options[i].period.start.now;
                dataOpt[params[1]] = context.options[i].period.end.now;
            }
            else if (context.options[i].type == 'input') {
                dataOpt[context.options[i].param] = $("#_" + context.options[i].param).val();
                if (!dataOpt[context.options[i].param]) {
                    dataOpt[context.options[i].param] = undefined;
                }
            }
        }
        return dataOpt;
    }
    function onClickSearch() {
        updatePgSize();
        var dataOpt = getSearchOption();
        $.ajax({
            type: "POST",
            url: encodeURI(context.updateUrl),
            data: dataOpt,
            success: function (data) {
                updateTable(JSON.parse(data), dataOpt);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                //promise.failed(textStatus);
            }
        });
    }
    search.onClickSearch = onClickSearch;
    function onClickExport() {
        var dataOpt = getSearchOption();
        var optTmp = [];
        for (var index_1 in dataOpt) {
            if (dataOpt[index_1]) {
                optTmp.push(index_1 + "=" + dataOpt[index_1]);
            }
        }
        var params = optTmp.join("&");
        $("#exportForm")[0].action = context.exportUrl + "?" + params;
        $("#exportForm")[0].submit();
    }
    search.onClickExport = onClickExport;
    function onClickReset() {
        for (var i = 0; i < context.options.length; ++i) {
            if (!context.options[i].type || context.options[i].type == 'lov') {
                $("#_" + context.options[i].param + " option:selected").attr("selected", false);
                $("#_" + context.options[i].param).selectpicker('refresh');
            }
            else if (context.options[i].type == 'date') {
                resetDate(context.options[i]);
            }
            else if (context.options[i].type == 'period') {
                resetPeriod(context.options[i]);
            }
            else if (context.options[i].type == 'input') {
                $("#_" + context.options[i].param).val("");
            }
        }
    }
    search.onClickReset = onClickReset;
    function onClickCollect() {
        if (context.isFavorite) {
            $.ajax({
                type: "POST",
                url: encodeURI(context.baseUrl + 'report/unfavorite.do'),
                data: {
                    item: context.item
                },
                success: function (data) {
                    context.isFavorite = false;
                    updateFavorite();
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    //promise.failed(textStatus);
                }
            });
        }
        else {
            $.ajax({
                type: "POST",
                url: encodeURI(context.baseUrl + 'report/favorite.do'),
                data: {
                    item: context.item
                },
                success: function (data) {
                    context.isFavorite = true;
                    updateFavorite();
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    //promise.failed(textStatus);
                }
            });
        }
    }
    search.onClickCollect = onClickCollect;
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
        var tableAssist = Util.createTable("table-jq", gridCtrl);
        tableAssist.create({
            assistData: gridCtrl.data,
            assistTotal: Util.roundDiv(gridCtrl.dataCount, pgSize),
            assistRecords: gridCtrl.dataCount,
            assistPagedata: function (postdata) {
                dataOpt.pgNum = postdata.page - 1;
                $.ajax({
                    type: "POST",
                    url: encodeURI(context.updateUrl),
                    data: dataOpt,
                    success: function (data) {
                        var dataNew = JSON.parse(data);
                        tableAssist.addData(Util.roundDiv(dataNew.dataCount, pgSize), postdata.page, dataNew.dataCount, dataNew.data, undefined);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
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
    updatePgSize();
})(search || (search = {}));
