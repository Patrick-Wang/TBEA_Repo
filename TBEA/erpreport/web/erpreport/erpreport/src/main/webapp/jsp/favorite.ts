module index {

    interface Favorite{
        id:number;
        title: string;
        icon: string;
        url: string;
        createTime: string;
    }

    interface Context {
        userName: number;
        favorites: any[];
                            // id,
                            // nav.title,
                            // nav.icon,
                            // nav.url,
                            // to_char(fav.createTime, 'yyyy-mm-dd HH24:mi:ss:ff')
        baseUrl: string;
    }


    declare var context: Context;
    declare var $: any;
    declare var layui: any;

    function buildFavoriteItem(p:any, fav:any[]){
        p.append('<div class="layui-col-md3"><div class="info-box" id="fav_' + fav[0] + '">' +
            '<span class="info-box-icon" style="background-color:#f39c12 !important;color:white;">' +
            '<i class="' + fav[2] + '" aria-hidden="true"></i></span>' +
            '<div class="info-box-content">' +
            '<span class="info-box-text">' + fav[1] + '</span>' +
            '<span class="info-box-number">收藏时间：' + fav[4] + '</span>' +
            '</div>' +
            '</div></div>');
        p.find("#fav_" + fav[0]).on('click', ()=>{
            window.parent['index']['onClickNavFromSub'](fav[0]);
        });
    }

    function buildFavoriteBlocks(){
        var rowCount = Util.roundDiv(context.favorites.length, 4);
        for (let i = 0; i < rowCount; ++i){
            $(".layui-fluid").append('<div class="layui-row layui-col-space15"></div>');
        }
        for (let i = 0; i < context.favorites.length; ++i){
            buildFavoriteItem($(".layui-fluid .layui-row").eq(i % 4), context.favorites[i]);
        }

        if (rowCount == 0){
            $(".layui-fluid").append('<div class="layui-row layui-col-space15"></div>');
            let p = $(".layui-fluid .layui-row");
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
}