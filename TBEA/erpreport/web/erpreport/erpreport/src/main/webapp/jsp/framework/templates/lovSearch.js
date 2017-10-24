var search;
(function (search) {
    var pgSize = 5;
    function findDirector(actor) {
        for (var i = 0; i < context.options.length; ++i) {
            if (context.options[i].direct == actor.param) {
                return context.options[i];
            }
        }
    }
    function findActor(director) {
        for (var i = 0; i < context.options.length; ++i) {
            if (director.direct == context.options[i].param) {
                return context.options[i];
            }
        }
    }
    function appendSelect(i, p, opt) {
        p.children('div:eq(0)').append('<span class="search-label">' + opt.name + '</span>');
        p.children('div:eq(0)').append('<div class="search-item">' +
            '<select id="sel' + i + '" class="selectpicker" data-live-search="true" title="' + opt.name + '" >' +
            '<option value="all" style="color:darkgray">' + opt.name + '</option>' +
            '</select>' +
            '</div>');
        var sel = p.find('#sel' + i);
        if (opt.option[0] && opt.option[0].length == 2) {
            for (var i_1 = 0; i_1 < opt.option.length; ++i_1) {
                sel.append('<option value="' + opt.option[i_1][0] + '">' + opt.option[i_1][1] + '</option>');
            }
        }
        sel.selectpicker({});
        sel.on('changed.bs.select', function (e) {
            var val = sel.selectpicker("val");
            if (val != 'all') {
                if (opt.direct) {
                    var actor = findActor(opt);
                    var actorElem = p.parent().find("select[title='" + actor.name + "']");
                    actorElem.empty();
                    actorElem.append('<option value="all" style="color:darkgray">' + actor.name + '</option>');
                    for (var i_2 = 0; i_2 < actor.option.length; ++i_2) {
                        if (actor.option[i_2][2] == val) {
                            actorElem.append('<option value="' + actor.option[i_2][0] + '">' + actor.option[i_2][1] + '</option>');
                        }
                    }
                    actorElem.selectpicker('refresh');
                }
            }
            else {
                sel.find("option:selected").attr("selected", false);
                sel.selectpicker('refresh');
            }
        });
    }
    function buildSearchItems() {
        var optArea = $(".option-area");
        var rowCount = context.options.length;
        rowCount = rowCount > 0 ? rowCount : 1;
        var btns = optArea.find('.cux-btn-group').remove();
        for (var i = 0; i < rowCount; ++i) {
            optArea.append('<div class="row"><div class="col-md-8 search-col"></div><div class="col-md-4"></div></div>');
        }
        optArea.children('.row').eq(rowCount - 1).children('div:eq(1)').append(btns);
        for (var i = 0; i < context.options.length; ++i) {
            appendSelect(i, optArea.children('.row').eq(i), context.options[i]);
        }
    }
    function updatePgSize() {
        var tbHeader = 30;
        var tbPager = 30;
        var rowHeight = 33;
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
    }
    function onClickSearch() {
        updatePgSize();
        var dataOpt = {
            pgSize: pgSize,
            pgNum: 0
        };
        for (var i = 0; i < context.options.length; ++i) {
            dataOpt[context.options[i].param] = $("#sel" + i).selectpicker("val");
            if (!dataOpt[context.options[i].param]) {
                dataOpt[context.options[i].param] = 'all';
            }
        }
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
})(search || (search = {}));
