var version = '2.0.2';
var curPage = location.href.match(/(\w*).html/) ? location.href
    .match(/(\w*).html/)[1] : 'index';
var RELEASE = false;
var activeClass:any = {};
var loc:any = {};
var entryPlan;
var entryPredict;
var approveplan;
var approvePredict;
var forkWidth = 149;
declare var React:any;
declare var $:any;
switch (curPage) {
    case 'index':
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
    case 'feature':
    case 'example':
    case 'doc':
    case 'about':
    case 'changelog':
    case 'start':
        activeClass[curPage] = 'active';
        loc.index = '..';
        break;
    default:
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

function logout() {
    var logoutAjax = new Util.Ajax("logout.do");
    logoutAjax.get().then(function onSuccess() {

    }, function onFailed() {
        alert("网络错误");
    });
}

function init(ePlan, ePredict, aPlan, aPredict, userName) {
    entryPlan = ePlan;
    entryPredict = ePredict;
    approveplan = aPlan;
    approvePredict = aPredict;
    var tab = <div className="container">
        <div className="navbar-header">
            <button type="button" className="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span className="sr-only">Toggle navigation</span>
                <span className="icon-bar"></span>
                <span className="icon-bar"></span>
                <span className="icon-bar"></span>
            </button>
            <a className="navbar-brand" href="http://www.tbea.com.cn">TBEA 经营管控信息平台</a>
        </div>
        <div className="navbar-collapse collapse" id="nav-wrap" style={{maxWidth:'100%'}}>
            <ul className="nav navbar-nav navbar-right" id="nav">
                <li className={activeClass.Sample || ''}
                    onClick={delegateCall} value="1">
                    <a style={{cursor:'pointer'}}>数据汇总明细</a>
                </li>
                { (()=> {
                    if (entryPlan || entryPredict) {
                        return <li className={activeClass.Sample || ''} onClick={delegateCall} value="2">
                            <a style={{cursor:'pointer'}}>数据录入</a>
                        </li>
                        } })()}
                { (()=> {
                    if (approveplan || approvePredict) {
                        return <li className={activeClass.Sample || ''} onClick={delegateCall} value="3">
                            <a style={{cursor:'pointer'}}>数据审核</a>
                        </li>
                        } })()}
                { (()=> {
                    return <li className={activeClass.Sample || ''} onClick={delegateCall} value="4">
                        <a style={{cursor:'pointer'}}>价格库</a>
                    </li>
                    })()}
                <li>
                    <a className="dropdown-toggle" data-toggle="dropdown" style={{cursor:'pointer'}}>
                        {userName}
                        <b className="caret"></b>
                    </a>
                    <ul className="dropdown-menu" style={{width:'100%'}}>
                        <li>
                            <div onClick={logout} style={{cursor:'pointer', textAlign:'center'}}>退出</div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>;
    var footer = <div className="container">
        <div className="row" style={{paddingBottom:'20px'}}>
            <div className="col-md-3">
                <p>友情链接</p>
                <ul>
                    <li>
                        <a href="http://www.tbea.com.cn/default.aspx" target="_blank">TBEA 官网</a>
                    </li>
                </ul>
            </div>
            <div className="col-md-3">
                <p>更多</p>
                <ul>
                    <li>
                        <a href="http://oagroup.tbea.com.cn/HQ/myportal/HQ/" target="_blank">TBEA OA</a>
                    </li>
                    <li>
                        <a href="http://192.168.7.76" target="_blank">TBEA E_HR</a>
                    </li>
                </ul>
            </div>
            <div className="col-md-3">
                <p>联系我们</p>
                <ul>
                    <li>
                        <a href="mailto:anfengling@tbea.com">anfengling@tbea.com</a>
                    </li>
                </ul>
            </div>
            <div className="col-md-3 flogo">
                <a href="javascript:void(0)" onClick={back2Top} title="回到顶部"/>
            </div>
        </div>
        <p className="pull-right">
            <a href="javascript:void(0)" onClick={back2Top}>Back to top</a>
        </p>
        <p>&copy; 2015
            <a href="http://www.tbea.com.cn/default.aspx" target="_blank">特变电工集团股份有限公司 信息资源管理中心</a>
        </p>
    </div >;
    React.render(
        tab,
        $('#head')[0]
    );
    React.render(
        footer,
        $('#footer')[0]
    );
    $('#footer').css("marginTop", '50px');

    if (document.location.href.indexOf('local') == -1) {
        var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
            : " http://");
        document
            .write(unescape("%3Cscript src='"
                + _bdhmProtocol
                + "hm.baidu.com/h.js%3Fb78830c9a5dad062d08b90b2bc0cf5da' type='text/javascript'%3E%3C/script%3E"));
    }
    function fixFork() {
        var navMarginRight = 0;
        var bodyWidth = document.body.offsetWidth;
        var contnetWidth = $('#nav-wrap')[0].offsetWidth;
        if (bodyWidth < 1440) {
            navMarginRight = 150 - (bodyWidth - contnetWidth) / 2;
        }
        $('#nav')[0].style.marginRight = navMarginRight + 'px';
    }

    fixFork();
    $(window).on('resize', fixFork);
    $("#nav li").eq(0).addClass("active");
}


var navs = ["#navlist", "#navlist1","#navlist2","#navlist4"];
var contents = ["#IndexSummary", "#InputList","#approveList","#priceLib"];

function showTab(index : number){
    for (var i = 1; i <= navs.length; ++i){
        if (i == index){
            $(navs[i - 1]).css("display", "");
            $(contents[i - 1]).css("display", "");
        }else{
            $(navs[i - 1]).css("display", "none");
            $(contents[i - 1]).css("display", "none");
        }
    }
}

function clickli(obj) {
    var clickval = obj.currentTarget.value;
    showTab(obj.currentTarget.value);
    $("#nav li").removeClass("active");
    $(obj.currentTarget).addClass("active");
    //switch (clickval) {
    //    case 1:
    //        $("#navlist").css("display", "");
    //        $("#navlist1").css("display", "none");
    //        $("#navlist2").css("display", "none");
    //        $("#navlist4").css("display", "none");
    //
    //        $("#IndexSummary").css("display", "");
    //        $("#InputList").css("display", "none");
    //        $("#approveList").css("display", "none");
    //        $("#priceLib").css("display", "");
    //
    //        break;
    //    case 2:
    //        $("#navlist").css("display", "none");
    //        $("#navlist1").css("display", "");
    //        $("#navlist2").css("display", "none");
    //
    //        $("#IndexSummary").css("display", "none");
    //        $("#InputList").css("display", "");
    //        $("#approveList").css("display", "none");
    //        break;
    //    case 3:
    //        $("#navlist").css("display", "none");
    //        $("#navlist1").css("display", "none");
    //        $("#navlist2").css("display", "");
    //
    //        $("#IndexSummary").css("display", "none");
    //        $("#InputList").css("display", "none");
    //        $("#approveList").css("display", "");
    //        break;
    //    case 4:
    //        $("#navlist").css("display", "none");
    //        $("#navlist1").css("display", "none");
    //        $("#navlist2").css("display", "none");
    //        $("#navlist4").css("display", "");
    //        // for 财务指标
    //        //$("#navlist3").css("display", "");
    //
    //        $("#IndexSummary").css("display", "none");
    //        $("#InputList").css("display", "none");
    //        $("#approveList").css("display", "none");
    //        $("#priceLib").css("display", "");
    //
    //
    //        // for 财务指标
    //        //$("#financeList").css("display", "");
    //
    //        // $("#nav li").eq(0).removeClass("active");
    //        // $("#nav li").eq(1).removeClass("active");
    //        // $("#nav li").eq(2).removeClass("active");
    //        // $("#nav li").eq(3).addClass("active");
    //        break;
    //    default:
    //        break;
    //}
}

function back2Top() {
    $("body,html").animate({
        scrollTop: 0
    }, 1000);
    return false;
}
