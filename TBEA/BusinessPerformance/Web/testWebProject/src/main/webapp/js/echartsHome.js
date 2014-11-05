var version = '2.0.2';
var curPage = location.href.match(/(\w*).html/) ? location.href.match(/(\w*).html/)[1] : 'index';

var activeClass = {};
var loc = {};
var forkWidth = 149;
switch (curPage) {
    case 'index' :
        activeClass[curPage] = 'active';
        loc[curPage] = '.';
        loc.feature = './doc';
        loc.example = './doc';
        loc.doc = './doc';
        loc.about = './doc';
        loc.changelog = './doc';
        loc.start = './doc';
        loc.img = './doc';
        break;
    case 'feature' :
    case 'example' :
    case 'doc' :
    case 'about' :
    case 'changelog' :
    case 'start' :
        activeClass[curPage] = 'active';
        loc.index = '..';
        break;
    default :
        forkWidth = 60;
        activeClass['example'] = 'active';
        loc.index = '../..';
        loc.feature = '../../doc';
        loc.example = '../../doc';
        loc.doc = '../../doc';
        loc.about = '../../doc';
        loc.changelog = '../../doc';
        loc.start = '../../doc';
        loc.img = '../../doc';
        break;
}

$('#head')[0].innerHTML = 
    '<div class="container">'
        + '<div class="navbar-header">'
          + '<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">'
            + '<span class="sr-only">Toggle navigation</span>'
            + '<span class="icon-bar"></span>'
            + '<span class="icon-bar"></span>'
            + '<span class="icon-bar"></span>'
          + '</button>'
          + '<a class="navbar-brand" href="http://www.tbea.com.cn">TBEA 经营管控系统</a>'
        + '</div>'
        + '<div class="navbar-collapse collapse" id="nav-wrap">'
          + '<ul class="nav navbar-nav navbar-right" id="nav" style="max-width:100%;">'
            + '<li class="' + (activeClass.index || '') + '"><a href="' + (loc.index || '.') + '/index.htm">首页</a></li>'
            + '<li class="' + (activeClass.feature || '') + '"><a href="">导出报表</a></li>'
            //+ '<li class="' + (activeClass.example || '') + '"><a href="' + (loc.example || '.') + '/example.html">实例</a></li>'
            //+ '<li class="' + (activeClass.doc || '') + '"><a href="' + (loc.doc || '.') + '/doc.html">文档</a></li>'
            //+ '<li><a href="http://echarts.baidu.com/build/echarts-' + version + '.rar">下载</a></li>'
            + '<li class="' + (activeClass.about || '') + '"><a href="">关于我们</a></li>'
          + '</ul>'
        + '</div><!--/.nav-collapse -->'
      + '</div>';
      
function back2Top() {
    $("body,html").animate({scrollTop:0},1000);
    return false;
}
$('#footer')[0].style.marginTop = '50px';
$('#footer')[0].innerHTML =
     '<div class="container">'
        + '<div class="row" style="padding-bottom:20px;">'
            + '<div class="col-md-3">'
                + '<p>友情链接</p>'
                + '<ul>'
                    + '<li><a href="http://www.tbea.com.cn/default.aspx" target="_blank">TBEA 官网</a></li>'
                    + '<li><a href="http://www.tbea.com.cn/default.aspx" target="_blank">TBEA OA</a></li>'
                    + '<li><a href="http://www.tbea.com.cn/default.aspx" target="_blank">TBEA Portal</a></li>'
                    + '<li><a href="http://www.tbea.com.cn/default.aspx" target="_blank">TBEA E-HR</a></li>'
                + '</ul>'
            + '</div>'
            + '<div class="col-md-3">'
                + '<p>更多</p>'
                + '<ul>'
                    + '<li><a href="http://www.tbea.com.cn/default.aspx" target="_blank">TBEA OA</a></li>'
                    + '<li><a href="http://www.tbea.com.cn/default.aspx" target="_blank">TBEA E_HR</a></li>'
                    + '<li><a href="http://www.tbea.com.cn/default.aspx" target="_blank">TBEA Portal</a></li>'
                + '</ul>'
            + '</div>'
            + '<div class="col-md-3">'
                + '<p>联系我们</p>'
                + '<ul>'
                    + '<li><a href="mailto:18624020715(a)163.com">18624020715(a)163.com</a></li>'
                    + '<li><a href="http://www.tbea.com.cn/default.aspx" target="_blank">TBEA 官网</a></li>'
                    + '<li><a href="http://www.tbea.com.cn/default.aspx" target="_blank">TBEA OA</a></li>'
                + '</ul>'
            + '</div>'
            + '<div class="col-md-3 flogo">'
                + '<a href="javascript:void(0)" onclick="back2Top()" title="回到顶部"/></a>'
            + '</div>'
        + '</div>'
        + '<p class="pull-right"><a href="javascript:void(0)" onclick="back2Top()" >Back to top</a></p>'
        + '<p>&copy; 2014 <a href="http://www.tbea.com.cn/default.aspx" target="_blank">特变电工集团股份有限公司 信息资源管理中心</a></p>'
    + '</div>';


if (document.location.href.indexOf('local') == -1) {
    var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fb78830c9a5dad062d08b90b2bc0cf5da' type='text/javascript'%3E%3C/script%3E"));   
}

function fixFork () {
    var navMarginRight = 0;
    var bodyWidth = document.body.offsetWidth;
    var contnetWidth = $('#nav-wrap')[0].offsetWidth;
    if (bodyWidth < 1440) {
        navMarginRight = 150 - (bodyWidth - contnetWidth) / 2;
    }
    $('#nav')[0].style.marginRight = navMarginRight + 'px';
    $('#fork-image')[0].style.width = (document.body.offsetWidth < 768 ? 1 : forkWidth) + 'px';
};
fixFork();
$(window).on('resize', fixFork);