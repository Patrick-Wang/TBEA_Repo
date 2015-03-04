var version = '2.0.2';
var curPage = location.href.match(/(\w*).html/) ? location.href.match(/(\w*).html/)[1] : 'index';
var RELEASE = false; 
var activeClass = {};
var loc = {};
var entryPlan;
var entryPredict;
var approveplan;
var approvePredict;
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


function init(ePlan, ePredict, aPlan, aPredict)
{
	entryPlan = ePlan;
	entryPredict = ePredict;
	approveplan = aPlan;
	approvePredict = aPredict;
var stringDescription =  '<div class="container">'
        + '<div class="navbar-header">'
          + '<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">'
            + '<span class="sr-only">Toggle navigation</span>'
            + '<span class="icon-bar"></span>'
            + '<span class="icon-bar"></span>'
            + '<span class="icon-bar"></span>'
          + '</button>'
          + '<a class="navbar-brand" href="http://www.tbea.com.cn">TBEA 经营管控平台</a>'
        + '</div>'
        + '<div class="navbar-collapse collapse" id="nav-wrap">'
          + '<ul class="nav navbar-nav navbar-right" id="nav" style="max-width:100%;">'
            + '<li class="' + (activeClass.Sample || '') + '" onclick="delegateCall(this);" value="1"><a>指标汇总明细</a></li>';
			if (!RELEASE){
				if(entryPlan || entryPredict)
				{
					 stringDescription += '<li class="' + (activeClass.Sample || '') + '" onclick="delegateCall(this);" value="2"><a>指标录入</a></li>';
				}
				if(approveplan || approvePredict)
				{
					 stringDescription += '<li class="' + (activeClass.Sample || '') + '" onclick="delegateCall(this);" value="3"><a>指标审核</a></li>';
				}
				//for 财务指标汇总
	             //stringDescription += '<li class="' + (activeClass.About || '') + '" onclick="delegateCall(this);" value="4"><a>财务指标汇总</a></li>'
			}
          + '</ul>'
        + '</div><!--/.nav-collapse -->'
      + '</div>';




	$('#head')[0].innerHTML = stringDescription;

	  $('#footer')[0].style.marginTop = '50px';
	$('#footer')[0].innerHTML =
     '<div class="container">'
        + '<div class="row" style="padding-bottom:20px;">'
            + '<div class="col-md-3">'
                + '<p>友情链接</p>'
                + '<ul>'
                    + '<li><a href="http://www.tbea.com.cn/default.aspx" target="_blank">TBEA 官网</a></li>'
                    // + '<li><a href="http://www.tbea.com.cn/default.aspx"
					// target="_blank">TBEA OA</a></li>'
                    // + '<li><a href="http://www.tbea.com.cn/default.aspx"
					// target="_blank">TBEA Portal</a></li>'
                    // + '<li><a href="http://www.tbea.com.cn/default.aspx"
					// target="_blank">TBEA E-HR</a></li>'
                + '</ul>'
            + '</div>'
            + '<div class="col-md-3">'
                + '<p>更多</p>'
                + '<ul>'
                    + '<li><a href="http://www.tbea.com.cn/default.aspx" target="_blank">TBEA OA</a></li>'
                    + '<li><a href="http://www.tbea.com.cn/default.aspx" target="_blank">TBEA E_HR</a></li>'
                    // + '<li><a href="http://www.tbea.com.cn/default.aspx"
					// target="_blank">TBEA Portal</a></li>'
                + '</ul>'
            + '</div>'
            + '<div class="col-md-3">'
                + '<p>联系我们</p>'
                + '<ul>'
                    + '<li><a href="mailto:18624020715(a)163.com">18624020715(a)163.com</a></li>'
                    // + '<li><a href="http://www.tbea.com.cn/default.aspx"
					// target="_blank">TBEA 官网</a></li>'
                    // + '<li><a href="http://www.tbea.com.cn/default.aspx"
					// target="_blank">TBEA OA</a></li>'
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
	};
    fixFork();
    $(window).on('resize', fixFork);
	$("#nav li").eq(0).addClass("active");
}

function clickli(obj)
{
	var clickval= obj.value;
	switch(clickval)
	{
	case 1:
		$("#navlist").css("display", "");
		$("#navlist1").css("display", "none");
		$("#navlist2").css("display", "none");
		//$("#navlist3").css("display", "none");
		
		$("#IndexSummary").css("display", "");
		$("#InputList").css("display", "none");
		$("#approveList").css("display", "none");
		//$("#financeList").css("display", "none");
		
// $("#nav li").eq(1).removeClass("active");
// $("#nav li").eq(2).removeClass("active");
// $("#nav li").eq(3).removeClass("active");
// $("#nav li").eq(0).addClass("active");

		break;
	case 2:
		$("#navlist").css("display", "none");
		$("#navlist1").css("display", "");
		$("#navlist2").css("display", "none");
		// for 财务指标
		//$("#navlist3").css("display", "none");
		
		$("#IndexSummary").css("display", "none");
		$("#InputList").css("display", "");
		$("#approveList").css("display", "none");
		//for 财务指标
		//$("#financeList").css("display", "none");
		
// obj
// $("#nav li").eq(2).removeClass("active");
// $("#nav li").eq(3).removeClass("active");
// $("#nav li").eq(1).addClass("active");
	break;
	case 3:
		$("#navlist").css("display", "none");
		$("#navlist1").css("display", "none");
		$("#navlist2").css("display", "");
		// for 财务指标
		//$("#navlist3").css("display", "none");
		
		$("#IndexSummary").css("display", "none");
		$("#InputList").css("display", "none");
		$("#approveList").css("display", "");
		// for 财务指标
		//$("#financeList").css("display", "none");
		
// $("#nav li").eq(0).removeClass("active");
// $("#nav li").eq(1).removeClass("active");
// $("#nav li").eq(3).removeClass("active");
// $("#nav li").eq(2).addClass("active");
	break;
	case 4:
		$("#navlist").css("display", "none");
		$("#navlist1").css("display", "none");
		$("#navlist2").css("display", "none");
		// for 财务指标
		//$("#navlist3").css("display", "");
		
		$("#IndexSummary").css("display", "none");
		$("#InputList").css("display", "none");
		$("#approveList").css("display", "none");
		// for 财务指标
		//$("#financeList").css("display", "");
		
// $("#nav li").eq(0).removeClass("active");
// $("#nav li").eq(1).removeClass("active");
// $("#nav li").eq(2).removeClass("active");
// $("#nav li").eq(3).addClass("active");
	break;
	default:
	break;
	}
	$("#nav li").removeClass("active");
	$(obj).addClass("active");
}
      
function back2Top() {
    $("body,html").animate({scrollTop:0},1000);
    return false;
}



