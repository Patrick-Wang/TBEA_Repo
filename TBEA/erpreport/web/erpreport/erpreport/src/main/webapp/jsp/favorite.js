var index;
(function (index) {
    var colors = [
        '#dd4b39',
        '#00a65a',
        '#f39c12',
        '#00c0ef'
    ];
    if (!Array.prototype['shuffle']) {
        Array.prototype['shuffle'] = function () {
            this.sort(function (a, b) { return Math.random() > .5 ? -1 : 1; });
        };
    }
    function buildFavoriteItem(color, p, fav) {
        p.append('<div class="layui-col-md3"><div class="info-box" id="fav_' + fav[0] + '">' +
            '<span class="info-box-icon" style="background-color:' + color + ' !important;color:white;">' +
            '<i class="' + fav[2] + '" aria-hidden="true"></i></span>' +
            '<div class="info-box-content">' +
            '<span class="info-box-text">' + fav[1] + '</span>' +
            '<span class="info-box-number">收藏时间：' + fav[4] + '</span>' +
            '</div>' +
            '</div></div>');
        p.find("#fav_" + fav[0]).on('click', function () {
            window.parent['index']['onClickNavFromSub'](fav[0]);
        });
    }
    function buildFavoriteBlocks() {
        var rowCount = Util.roundDiv(context.favorites.length, 4);
        for (var i = 0; i < rowCount; ++i) {
            $(".layui-fluid").append('<div class="layui-row layui-col-space15"></div>');
        }
        for (var i = 0; i < context.favorites.length; ++i) {
            if (i % 4 == 0) {
                colors.shuffle();
            }
            buildFavoriteItem(colors[i % 4], $(".layui-fluid .layui-row").eq(Util.zeroDiv(i, 4)), context.favorites[i]);
        }
        if (rowCount == 0) {
            $(".layui-fluid").append('<div class="layui-row layui-col-space15"></div>');
            var p = $(".layui-fluid .layui-row");
            p.append('<div class="layui-col-md3"><div class="info-box">' +
                '<span class="info-box-icon" style="background-color:#f39c12 !important;color:white;">' +
                '<i class="fa fa-star-o" aria-hidden="true"></i></span>' +
                '<div class="info-box-content">' +
                '<span class="info-box-text">我的收藏</span>' +
                '<span class="info-box-number">快快收藏吧！</span>' +
                '</div>' +
                '</div></div>');
        }
    }
    buildFavoriteBlocks();
})(index || (index = {}));
