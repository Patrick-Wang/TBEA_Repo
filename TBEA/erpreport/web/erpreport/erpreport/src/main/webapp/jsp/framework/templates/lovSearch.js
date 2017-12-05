var search;
(function (search) {
    var TablePanel = (function () {
        function TablePanel() {
            var _this = this;
            this.pgSize = 5;
            $(window).on('resize', function () {
                _this.adjustSize();
            });
        }
        TablePanel.prototype.getPgSize = function () {
            return this.pgSize;
        };
        TablePanel.prototype.updatePgSize = function () {
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
            this.pgSize = newPgSize > 5 ? newPgSize : 5;
            $("#table").parent().css("height", leftHeight + "px");
        };
        TablePanel.prototype.adjustSize = function () {
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
        };
        TablePanel.prototype.updateTable = function (gridCtrl, dataOpt) {
            var _this = this;
            $("#table").empty();
            $("#table").append('<table id="table-jq"></table><div id="pager"></div>');
            this.tableAssist = Util.createTable("table-jq", gridCtrl);
            this.tableAssist.create({
                assistDataWithId: gridCtrl.data,
                assistTotal: Util.roundDiv(gridCtrl.dataCount, this.pgSize),
                assistRecords: gridCtrl.dataCount,
                assistPagedata: function (postdata) {
                    dataOpt.pgNum = postdata.page - 1;
                    $.ajax({
                        type: "POST",
                        url: encodeURI(context.updateUrl),
                        data: dataOpt,
                        success: function (data) {
                            var dataNew = JSON.parse(data);
                            _this.tableAssist.addData(Util.roundDiv(dataNew.dataCount, _this.pgSize), postdata.page, dataNew.dataCount, undefined, dataNew.data);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
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
        };
        TablePanel.prototype.getSelRows = function () {
            return this.tableAssist ? this.tableAssist.getCheckedRowIds() : undefined;
        };
        return TablePanel;
    }());
    var SearchPanel = (function () {
        function SearchPanel(tablePanel) {
            this.tablePanel = tablePanel;
            this.buildSearchItems();
            this.tablePanel.updatePgSize();
        }
        SearchPanel.prototype.lovDirector = function (actor) {
            for (var i = 0; i < context.options.length; ++i) {
                if (context.options[i].type == 'lov') {
                    if (context.options[i].lov.direct == actor.param) {
                        return context.options[i];
                    }
                }
            }
        };
        SearchPanel.prototype.lovActor = function (director) {
            for (var i = 0; i < context.options.length; ++i) {
                if (context.options[i].type == 'lov') {
                    if (director.lov.direct == context.options[i].param) {
                        return context.options[i];
                    }
                }
            }
        };
        SearchPanel.prototype.buildSelect = function (rowMap, opt) {
            var _this = this;
            var well = rowMap[opt.param];
            var firstDiv = rowMap.searchArea.find('tbody > tr').eq(well.row).children('td').eq(well.col * 2);
            firstDiv.append('<span class="search-label">' + opt.name + '</span>');
            firstDiv.next().append('<div class="search-item-lov">' +
                '<select id="_' + opt.param + '" class="selectpicker" data-live-search="true">' +
                '<option value="all" style="color:darkgray"> </option>' +
                '</select>' +
                '</div>');
            var sel = rowMap.searchArea.find('#_' + opt.param);
            if (opt.lov.option) {
                if (opt.lov.option[0] && opt.lov.option[0].length == 2) {
                    for (var i = 0; i < opt.lov.option.length; ++i) {
                        sel.append('<option value="' + opt.lov.option[i][0] + '">' + opt.lov.option[i][1] + '</option>');
                    }
                }
            }
            else {
                var speed_1 = 0;
                var timer_1 = setInterval(function () {
                    sel.parent().find("button").attr("disabled", true);
                    var title = "加载中";
                    for (var i = 0; i <= speed_1; ++i) {
                        title += ".";
                    }
                    sel.parent().find(".filter-option").text(title);
                    ++speed_1;
                    speed_1 = speed_1 % 3;
                }, 500);
                Util.Loading.pause();
                $.ajax({
                    type: "POST",
                    url: encodeURI(opt.lov.url),
                    success: function (data) {
                        Util.Loading.resume();
                        clearInterval(timer_1);
                        sel.parent().find("button").attr("disabled", false);
                        opt.lov.option = JSON.parse(data).lov;
                        if (opt.lov.option[0] && opt.lov.option[0].length == 2) {
                            for (var i = 0; i < opt.lov.option.length; ++i) {
                                sel.append('<option value="' + opt.lov.option[i][0] + '">' + opt.lov.option[i][1] + '</option>');
                            }
                        }
                        sel.selectpicker('refresh');
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        Util.Loading.resume();
                        clearInterval(timer_1);
                        sel.parent().find("button").attr("disabled", true);
                        sel.parent().find(".filter-option").text("数据加载失败！");
                    }
                });
            }
            sel.selectpicker({
                // style: 'btn-info'
                size: 10,
                liveSearch: opt.type != 'lovNoSearch'
            });
            sel.on('changed.bs.select', function (e) {
                var val = sel.selectpicker("val");
                if (val != 'all') {
                    if (opt.lov.direct) {
                        var actor = _this.lovActor(opt);
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
        };
        SearchPanel.prototype.setNow = function (time, date) {
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
        };
        SearchPanel.prototype.resetDate = function (opt) {
            var _this = this;
            this.setNow(opt.date, opt.date.init);
            opt.date.ins = layui.laydate.render({
                elem: '#_' + opt.param,
                type: opt.date.type,
                value: opt.date.init,
                trigger: 'click',
                //showBottom: false,
                change: function (value, date, endDate) {
                    _this.setNow(opt.date, date);
                }
            });
        };
        SearchPanel.prototype.buildDate = function (rowMap, opt) {
            var well = rowMap[opt.param];
            var firstDiv = rowMap.searchArea.find('tbody > tr').eq(well.row).children('td').eq(well.col * 2);
            firstDiv.append('<span class="search-label">' + opt.name + '</span>');
            firstDiv.next().append('<div class="search-item-date">' +
                '<input type="text" class="layui-input" id="_' + opt.param + '" readonly="readlony">' +
                '</div>');
            this.resetDate(opt);
        };
        SearchPanel.prototype.resetPeriod = function (opt) {
            var _this = this;
            this.setNow(opt.period.start, opt.period.start.init);
            this.setNow(opt.period.end, opt.period.end.init);
            var cfg = {
                elem: '#_' + opt.param.replace(/(,|\s)/g, ''),
                type: opt.period.start.type,
                trigger: 'click',
                range: true,
                done: function (value, date, endDate) {
                    _this.setNow(opt.period.start, date);
                    _this.setNow(opt.period.end, endDate);
                }
                // ready: (date) {
                //     setNow(opt.period.start, date);
                // }
            };
            if (opt.period.start.init && opt.period.end.init) {
                cfg.value = opt.period.start.init + ' - ' + opt.period.end.init;
            }
            opt.period.start.ins = layui.laydate.render(cfg);
        };
        SearchPanel.prototype.buildPeriod = function (rowMap, opt) {
            var well = rowMap[opt.param];
            var firstDiv = rowMap.searchArea.find('tbody > tr').eq(well.row).children('td').eq(well.col * 2);
            firstDiv.append('<span class="search-label">' + opt.name + '</span>');
            firstDiv.next().append('<div class="search-item-period">' +
                '<input type="text" class="layui-input" id="_' + opt.param.replace(/(,|\s)/g, '') + '" readonly="readlony">' +
                '</div>');
            this.resetPeriod(opt);
        };
        SearchPanel.prototype.assignRow = function () {
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
        };
        SearchPanel.prototype.buildStructure = function () {
            var optArea = $(".option-area");
            var rowMap = this.assignRow();
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
            this.updateFavorite();
            return rowMap;
        };
        SearchPanel.prototype.updateFavorite = function () {
            if (!context.isFavorite) {
                $('.btn-collect').empty();
                $('.btn-collect').append('<i class="fa fa-star"> </i>收藏');
            }
            else {
                $('.btn-collect').empty();
                $('.btn-collect').append('<i class="fa fa-star-o"> </i>取消收藏');
            }
        };
        SearchPanel.prototype.buildInput = function (rowMap, opt) {
            var well = rowMap[opt.param];
            var firstDiv = rowMap.searchArea.find('tbody > tr').eq(well.row).children('td').eq(well.col * 2);
            firstDiv.append('<span class="search-label">' + opt.name + '</span>');
            if (opt.input && opt.input.hint) {
                firstDiv.find(".search-label").append('<i class="search-label-warning hidden">' + opt.input.hint + '</i>');
            }
            else {
                firstDiv.find(".search-label").append('<i class="search-label-warning hidden">输入错误</i>');
            }
            firstDiv.next().append('<div class="search-item-input">' +
                '<input type="text" id="_' + opt.param + '">' +
                '</div>');
            if (opt.input && opt.input.validation) {
                var reg = new RegExp(opt.input.validation, 'g');
                var $input = $("#_" + opt.param);
                $input.on("change", function () {
                    var val = $input.val();
                    if (reg.test(val)) {
                        firstDiv.find(".search-label-warning").addClass("hidden");
                    }
                    else {
                        firstDiv.find(".search-label-warning").removeClass("hidden");
                        $input.val("");
                    }
                });
            }
        };
        SearchPanel.prototype.buildSearchItems = function () {
            var rowMap = this.buildStructure();
            for (var i = 0; i < context.options.length; ++i) {
                if (!context.options[i].type ||
                    context.options[i].type == 'lov' ||
                    context.options[i].type == 'lovNoSearch') {
                    this.buildSelect(rowMap, context.options[i]);
                }
                else if (context.options[i].type == 'date') {
                    this.buildDate(rowMap, context.options[i]);
                }
                else if (context.options[i].type == 'period') {
                    this.buildPeriod(rowMap, context.options[i]);
                }
                else if (context.options[i].type == 'input') {
                    this.buildInput(rowMap, context.options[i]);
                }
            }
            $(".option-area").removeClass("hidden");
        };
        SearchPanel.prototype.updateSearchOption = function () {
            this.dataOpt = {
                pgSize: this.tablePanel.getPgSize(),
                pgNum: 0
            };
            for (var i = 0; i < context.options.length; ++i) {
                if (!context.options[i].type ||
                    context.options[i].type == 'lov' ||
                    context.options[i].type == 'lovNoSearch') {
                    this.dataOpt[context.options[i].param] = $("#_" + context.options[i].param).selectpicker("val");
                    if (!this.dataOpt[context.options[i].param] || 'all' == this.dataOpt[context.options[i].param]) {
                        this.dataOpt[context.options[i].param] = undefined;
                    }
                }
                else if (context.options[i].type == 'date') {
                    this.dataOpt[context.options[i].param] = context.options[i].date.now;
                }
                else if (context.options[i].type == 'period') {
                    var params = context.options[i].param.replace(/\s/g, '').split(",");
                    this.dataOpt[params[0]] = context.options[i].period.start.now;
                    this.dataOpt[params[1]] = context.options[i].period.end.now;
                }
                else if (context.options[i].type == 'input') {
                    this.dataOpt[context.options[i].param] = $("#_" + context.options[i].param).val();
                    if (!this.dataOpt[context.options[i].param]) {
                        this.dataOpt[context.options[i].param] = undefined;
                    }
                }
            }
        };
        SearchPanel.prototype.getSearchOption = function () {
            if (!this.dataOpt) {
                this.updateSearchOption();
            }
            return this.dataOpt;
        };
        SearchPanel.prototype.onClickSearch = function () {
            var _this = this;
            this.tablePanel.updatePgSize();
            this.updateSearchOption();
            $.ajax({
                type: "POST",
                url: encodeURI(context.updateUrl),
                data: this.dataOpt,
                success: function (data) {
                    _this.tablePanel.updateTable(JSON.parse(data), _this.dataOpt);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    //promise.failed(textStatus);
                }
            });
        };
        SearchPanel.prototype.onClickExport = function () {
            var dataOpt = this.getSearchOption();
            var ids = this.tablePanel.getSelRows();
            if (ids) {
                dataOpt['ids'] = JSON.stringify(ids);
            }
            var optTmp = [];
            for (var index_1 in dataOpt) {
                if (dataOpt[index_1]) {
                    optTmp.push(index_1 + "=" + dataOpt[index_1]);
                }
            }
            var params = optTmp.join("&");
            $("#exportForm")[0].action = encodeURI(context.exportUrl + "?" + params);
            $("#exportForm")[0].submit();
        };
        SearchPanel.prototype.onClickPrint = function () {
            var ids = this.tablePanel.getSelRows();
            if (ids) {
                var dataOpt = this.getSearchOption();
                var optTmp = ["ids=" + JSON.stringify(ids)];
                for (var index_2 in dataOpt) {
                    if (dataOpt[index_2]) {
                        optTmp.push(index_2 + "=" + dataOpt[index_2]);
                    }
                }
                var params = optTmp.join("&");
                $("#exportForm")[0].action = encodeURI(context.printUrl + "?" + params);
                $("#exportForm")[0].submit();
            }
        };
        SearchPanel.prototype.onClickReset = function () {
            for (var i = 0; i < context.options.length; ++i) {
                if (!context.options[i].type ||
                    context.options[i].type == 'lov' ||
                    context.options[i].type == 'lovNoSearch') {
                    $("#_" + context.options[i].param + " option:selected").attr("selected", false);
                    $("#_" + context.options[i].param).selectpicker('refresh');
                }
                else if (context.options[i].type == 'date') {
                    this.resetDate(context.options[i]);
                }
                else if (context.options[i].type == 'period') {
                    this.resetPeriod(context.options[i]);
                }
                else if (context.options[i].type == 'input') {
                    $("#_" + context.options[i].param).val("");
                }
            }
        };
        SearchPanel.prototype.onClickCollect = function () {
            var _this = this;
            if (context.isFavorite) {
                $.ajax({
                    type: "POST",
                    url: encodeURI(context.baseUrl + 'report/unfavorite.do'),
                    data: {
                        item: context.item
                    },
                    success: function (data) {
                        context.isFavorite = false;
                        _this.updateFavorite();
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
                        _this.updateFavorite();
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        //promise.failed(textStatus);
                    }
                });
            }
        };
        return SearchPanel;
    }());
    search.searchPanel = null;
    layui.config({
        base: context.baseUrl + 'jsp/plugins/layui/lay/modules/'
    }).use(['laydate', 'element'], function () {
        search.searchPanel = new SearchPanel(new TablePanel());
    });
})(search || (search = {}));
