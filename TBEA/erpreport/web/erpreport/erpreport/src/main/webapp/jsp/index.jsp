<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta http-equiv="Content-Type" content="text/javascript;charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv = "X-UA-Compatible" content = "IE=edge" />
    <title>特变电工报表管理平台</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/jsp/build/images/icon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/build/css/app.css" media="all">
    <script src="${pageContext.request.contextPath}/jsp/plugins/jquery/jquery-1.12.3.js"></script>
    <%--<script src="${pageContext.request.contextPath}/jsp/es5-shim.js"></script>--%>
    <%--<script src="${pageContext.request.contextPath}/jsp/es5-sham.js"></script>--%>
    <script src="${pageContext.request.contextPath}/jsp/json2.js"></script>
    <script src="${pageContext.request.contextPath}/jsp/ie8.js"></script>
    <script src="${pageContext.request.contextPath}/jsp/layui-ie8.js"></script>
    <!--[if gte IE 8]>
    <style>
        .layui-nav li{
            padding-right:1px;
        }
    </style>
    <![endif]-->

    <script>
        var context = {
            baseUrl:"${pageContext.request.contextPath}/jsp",
            userName:'${userName}',
            navTree:JSON.parse('${navTree}'),
            item:'${item}'
        }
    </script>
</head>

<body>
<div class="layui-layout layui-layout-admin kit-layout-admin">
    <div class="layui-header">
        <div class="layui-logo"><img style="width:190px;"
                src="${pageContext.request.contextPath}/jsp/build/images/logo_word.png"></div>
        <div class="layui-logo kit-logo-mobile">TBEA</div>
        <ul id="navTop" class="layui-nav layui-layout-left kit-nav">
            <%--<li class="layui-nav-item"><a href="javascript:;"><i class="fa fa-calculator" aria-hidden="true"></i> 财务</a>--%>
            <%--</li>--%>
            <%--<li class="layui-nav-item"><a href="javascript:;"><i class="fa fa-cogs" aria-hidden="true"></i> 生产</a></li>--%>
            <%--<li class="layui-nav-item"><a href="javascript:;"><i class="fa fa-cubes" aria-hidden="true"></i> 进销存</a>--%>
            <%--</li>--%>
            <%--<li class="layui-nav-item"><a href="javascript:;"><i class="fa fa-group" aria-hidden="true"></i> 人力资源</a>--%>
            <%--</li>--%>
            <%--<li class="layui-nav-item"><a href="javascript:;"><i class="fa fa-forumbee" aria-hidden="true"></i> 项目</a>--%>
            <%--</li>--%>
            <%--<li class="layui-nav-item">--%>
                <%--<a href="javascript:;">其它</a>--%>
                <%--<dl class="layui-nav-child">--%>
                    <%--<dd><a href="javascript:;"><i class="fa fa-building-o" aria-hidden="true"></i> 后勤</a></dd>--%>
                    <%--<dd><a href="javascript:;"><i class="fa fa-connectdevelop" aria-hidden="true"></i> 综合</a></dd>--%>
                <%--</dl>--%>
            <%--</li>--%>
        </ul>
        <ul class="layui-nav layui-layout-right kit-nav">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${pageContext.request.contextPath}/jsp/build/images/user2.jpg" class="layui-nav-img"> ${userName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">修改密码</a></dd>
                    <dd><a href="javascript:;">系统设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="javascript:;"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
            </li>
        </ul>
    </div>
	<div id="layuit"></div>
    <div class="layui-side layui-bg-black kit-side">
        <div class="layui-side-scroll">
            <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
            <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>
                <%--<li class="layui-nav-item layui-nav-itemed">--%>
                    <%--<a class="" href="javascript:;"><i class="fa fa-bar-chart-o" aria-hidden="true"></i><span> 综合分析报表</span></a>--%>
                    <%--<dl class="layui-nav-child">--%>
                        <%--<dd>--%>
                            <%--<a href="javascript:;" data-url="404.html" data-icon="fa fa-fax" data-title="资产负债表2233333331313123"--%>
                               <%--kit-target data-id='1'>--%>
                                <%--<i class="fa fa-fax" aria-hidden="true"></i><span> 资产负债表</span>--%>
                            <%--</a>--%>
                        <%--</dd>--%>
                        <%--<dd>--%>
                            <%--<a href="javascript:;" data-url="404.html" data-icon="fa fa-briefcase" data-title="利润分析表"--%>
                               <%--kit-target data-id='2'>--%>
                                <%--<i class="fa fa-briefcase" aria-hidden="true"></i><span> 利润分析表</span>--%>
                            <%--</a>--%>
                        <%--</dd>--%>
                        <%--<dd>--%>
                            <%--<a href="javascript:;" data-url="404.html" data-icon="fa fa-history" data-title="费用分析表"--%>
                               <%--kit-target data-id='3'>--%>
                                <%--<i class="fa fa-history" aria-hidden="true"></i><span> 费用分析表</span>--%>
                            <%--</a>--%>
                        <%--</dd>--%>
                        <%--<dd>--%>
                            <%--<a href="javascript:;" data-url="404.html" data-icon="fa fa-desktop" data-title="固定资产分析表"--%>
                               <%--kit-target data-id='4'>--%>
                                <%--<i class="fa fa-desktop" aria-hidden="true"></i><span> 固定资产分析表</span>--%>
                            <%--</a>--%>
                        <%--</dd>--%>
                        <%--<dd>--%>
                            <%--<a href="javascript:;" data-url="404.html" data-icon="fa fa-rss" data-title="现金流量表"--%>
                               <%--kit-target data-id='5'>--%>
                                <%--<i class="fa fa-rss" aria-hidden="true"></i><span> 现金流量表</span>--%>
                            <%--</a>--%>
                        <%--</dd>--%>
                    <%--</dl>--%>
                <%--</li>--%>
                <%--<li class="layui-nav-item">--%>
                    <%--<a class="" href="javascript:;"><i class="fa fa-sliders" aria-hidden="true"></i><span> 总账</span></a>--%>
                    <%--<dl class="layui-nav-child">--%>
                        <%--<dd>--%>
                            <%--<a href="javascript:;" data-url="404.html" data-icon="fa fa-sort-amount-desc" data-title="总账明细查询"--%>
                               <%--kit-target data-id='6'>--%>
                                <%--<i class="fa fa-sort-amount-desc" aria-hidden="true"></i><span> 总账明细查询</span>--%>
                            <%--</a>--%>
                        <%--</dd>--%>
                        <%--<dd>--%>
                            <%--<a href="javascript:;" data-url="404.html" data-icon="fa-chain-broken" data-title="总账明细导出"--%>
                               <%--kit-target data-id='7'>--%>
                                <%--<i class="fa fa-chain-broken" aria-hidden="true"></i><span> 总账明细导出</span>--%>
                            <%--</a>--%>
                        <%--</dd>--%>
                    <%--</dl>--%>
                <%--</li>--%>
                <%--<li class="layui-nav-item">--%>
                    <%--<a href="javascript:;"><i class="fa fa-dedent" aria-hidden="true"></i><span> 应收</span></a>--%>
                    <%--<dl class="layui-nav-child">--%>
                        <%--<dd>--%>
                            <%--<a href="javascript:;" data-url="404.html" data-icon="fa fa-sort-amount-desc" data-title="应收明细查询"--%>
                               <%--kit-target data-id='8'>--%>
                                <%--<i class="fa fa-sort-amount-desc" aria-hidden="true"></i><span> 应收明细查询</span>--%>
                            <%--</a>--%>
                        <%--</dd>--%>
                        <%--<dd>--%>
                            <%--<a href="javascript:;" data-url="404.html" data-icon="fa fa-chain-broken" data-title="应收明细导出"--%>
                               <%--kit-target data-id='9'>--%>
                                <%--<i class="fa fa-chain-broken" aria-hidden="true"></i><span> 应收明细导出</span>--%>
                            <%--</a>--%>
                        <%--</dd>--%>
                    <%--</dl>--%>
                <%--</li>--%>
                <%--<li class="layui-nav-item">--%>
                    <%--<a href="javascript:;" data-url="404.html" data-name="table" kit-loader>--%>
                        <%--<i class="fa fa-indent" aria-hidden="true"></i><span> 应付</span>--%>
                    <%--</a>--%>
                    <%--<dl class="layui-nav-child">--%>
                        <%--<dd>--%>
                            <%--<a href="javascript:;" data-url="404.html" data-icon="fa fa-sort-amount-desc" data-title="应付明细查询"--%>
                               <%--kit-target data-id='10'>--%>
                                <%--<i class="fa fa-sort-amount-desc" aria-hidden="true"></i><span> 应付明细查询</span>--%>
                            <%--</a>--%>
                        <%--</dd>--%>
                        <%--<dd>--%>
                            <%--<a href="javascript:;" data-url="404.html" data-icon="fa fa-chain-broken" data-title="应付明细导出"--%>
                               <%--kit-target data-id='11'>--%>
                                <%--<i class="fa fa-chain-broken" aria-hidden="true"></i><span> 应付明细导出</span>--%>
                            <%--</a>--%>
                        <%--</dd>--%>
                    <%--</dl>--%>
                <%--</li>--%>
                <%--<li class="layui-nav-item">--%>
                    <%--<a href="javascript:;" data-url="404.html" data-name="form" kit-loader>--%>
                        <%--<i class="fa fa-search" aria-hidden="true"></i><span> 综合查询</span>--%>
                    <%--</a>--%>
                    <%--<dl class="layui-nav-child">--%>
                        <%--<dd>--%>
                            <%--<a href="javascript:;" data-url="404.html" data-icon="fa fa-list-alt" data-title="应付应付综合查询"--%>
                               <%--kit-target data-id='12'>--%>
                                <%--<i class="fa fa-list-alt" aria-hidden="true"></i><span> 应付应付综合查询</span>--%>
                            <%--</a>--%>
                        <%--</dd>--%>
                        <%--<dd>--%>
                            <%--<a href="javascript:;" data-url="404.html" data-icon="fa fa-table" data-title="费用明细综合查询"--%>
                               <%--kit-target data-id='13'>--%>
                                <%--<i class="fa fa-table" aria-hidden="true"></i><span> 费用明细综合查询</span>--%>
                            <%--</a>--%>
                        <%--</dd>--%>
                    <%--</dl>--%>
                <%--</li>--%>
            </ul>
        </div>
    </div>
    <div class="layui-body" id="container">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">数据加载中,请稍等...</div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        2017 &copy;
        <a href="http://www.tbea.com/">特变电工股份有限公司</a>

    </div>
</div>
<script type="text/javascript">
    var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cspan id='cnzz_stat_icon_1264021086'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s22.cnzz.com/z_stat.php%3Fid%3D1264021086%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));
</script>
<script src="${pageContext.request.contextPath}/jsp/plugins/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/jsp/index.js"></script>
<script>
    var message;
    layui.config({
        base: '${pageContext.request.contextPath}/jsp/build/js/'
    }).use(['app', 'message'], function () {
        var app = layui.app,
                $ = layui.jquery,
                layer = layui.layer;
        //将message设置为全局以便子页面调用
        message = layui.message;
        //主入口
        app.set({
            type: 'iframe'
        }).init();

        index.init();
    });
</script>

</body>

</html>