module search {

    interface Option {
        param:string;
        name: string;
        option: string[];
        direct: string;
    }

    interface Context {
        pager: string;
        options: Option[];
        updateUrl: string;
        exportUrl: string;
    }


    declare var context: Context;
    declare var $: any;
    declare var layui: any;

    function findDirector(actor: Option) {
        for (let i = 0; i < context.options.length; ++i) {
            if (context.options[i].direct == actor.name) {
                return context.options[i];
            }
        }
    }

    function findActor(director: Option) {
        for (let i = 0; i < context.options.length; ++i) {
            if (director.direct == context.options[i].name) {
                return context.options[i];
            }
        }
    }

    function appendSelect(i: number, p: any, opt: Option) {
        p.append('<select id="sel' + i + '" class="selectpicker" data-live-search="true" title="' + opt.name + '" ></select>');
        let sel = p.find('#sel' + i);
        if (opt.option[0] && opt.option[0].length == 2) {
            for (let i = 0; i < opt.option.length; ++i) {
                sel.append('<option value="' + opt.option[i][0] + '">' + opt.option[i][1] + '</option>');
            }
        }
        sel.selectpicker({
            // style: 'btn-info'
        });
        sel.on('changed.bs.select', function (e) {
            if (opt.direct) {
                let val = sel.selectpicker("val");
                let actor = findActor(opt);
                let actorElem = p.find("select[title='" + actor.name + "']");
                actorElem.empty();
                for (let i = 0; i < actor.option.length; ++i) {
                    if (actor.option[i][2] == val) {
                        actorElem.append('<option value="' + actor.option[i][0] + '">' + actor.option[i][1] + '</option>');
                    }
                }
                actorElem.selectpicker('refresh');
            }
        });
    }

    function zeroDiv(sub, base){
        return (sub - (sub % base)) / base;
    }

    function roundDiv(sub, base){
        let ret = (sub - (sub % base)) / base;
        ret += (sub % base > 0) ?  1 : 0;
        return ret;
    }

    var optArea = $(".option-area");
    var colCount = 3;
    var rowCount = roundDiv((context.options.length + 1) , colCount);

    var btns = optArea.find('.cux-btn-group').remove();
    for (let i = 0; i < rowCount; ++i){
        optArea.append('<div class="row"><div class="col-md-4"></div><div class="col-md-4"></div><div class="col-md-4"></div></div>');
    }
    optArea.children('.row').eq(rowCount - 1).children('div:eq(2)').append(btns);


    function findWell(index){
        return optArea.children('.row').eq(zeroDiv(index , colCount)).children('div').eq(index % colCount);
    }


    for (let i = 0; i < context.options.length; ++i) {
        var select = appendSelect(i, findWell(i), context.options[i]);
    }

    var pgSize = 5;

    function updatePgSize(){
        let tbHeader = 30;
        let tbPager = 30;
        let rowHeight = 33;
        let wellPaddingTop = $("#table").parent().css("padding-top").replace("px", "");
        let wellPaddingBottom = $("#table").parent().css("padding-bottom").replace("px", "");
        let navHeight =  $(".nav-area").css("height").replace("px", "");
        let searchHeight = $(".search-area").css("height").replace("px", "");
        let leftHeight = document.documentElement.clientHeight -
            navHeight -
            searchHeight;
        let bodyHeight = leftHeight - tbHeader - tbPager - wellPaddingBottom - wellPaddingTop;
        let newPgSize = zeroDiv(bodyHeight, rowHeight);
        pgSize = newPgSize > 5 ? newPgSize : 5;
    }


    export function onClickSearch(){
        updatePgSize();

        var dataOpt = {
            pgSize:pgSize,
            pgNum:0
        };
        for (let i = 0; i < context.options.length; ++i) {
            dataOpt[context.options[i].param] = $("#sel" + i).selectpicker("val");
            if(!dataOpt[context.options[i].param]){
                dataOpt[context.options[i].param] = 'all';
            }
        }

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
            assistTotal: roundDiv(gridCtrl.dataCount, pgSize),
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
                            roundDiv(dataNew.dataCount, pgSize),
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
            pager: context.pager == 'pager' ? "#pager" : undefined
        });
        adjustSize();
    }

    $(window).on('resize', function (){
        adjustSize();
    });

}