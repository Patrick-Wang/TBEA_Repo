<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta http-equiv="Content-Type" content="text/javascript;charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>ERP报表管理平台</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/jsp/build/images/icon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/plugins/layui/css/layui.css" media="all">

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jsp\plugins\font-awesome\css\font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/build/css/app.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/ui2.css">
    <script src="${pageContext.request.contextPath}/jsp/plugins/jquery/jquery-1.12.3.js"></script>
    <%--<script src="${pageContext.request.contextPath}/jsp/es5-shim.js"></script>--%>
    <%--<script src="${pageContext.request.contextPath}/jsp/es5-sham.js"></script>--%>
    <script src="${pageContext.request.contextPath}/jsp/json2.js"></script>
    <script src="${pageContext.request.contextPath}/jsp/ie8.js"></script>
    <script src="${pageContext.request.contextPath}/jsp/layui-ie8.js"></script>
    <!--[if gte IE 8]>
    <style>
        .layui-nav li {
            padding-right: 1px;
        }
    </style>
    <![endif]-->

    <script>
        var context = {
            baseUrl: "${pageContext.request.contextPath}/",
            userName: '${userName}',
            navTree: JSON.parse('${navTree}'),
            item: '${item}'
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

        </ul>
        <ul class="layui-nav layui-layout-right kit-nav">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    欢迎 ${userName} !
                </a>
            </li>
        </ul>
    </div>
    <div id="layuit"></div>
    <div class="layui-side layui-bg-black kit-side">
        <div class="layui-side-scroll">
            <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
            <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>

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
<%--<script type="text/javascript">--%>
<%--var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");--%>
<%--document.write(unescape("%3Cspan id='cnzz_stat_icon_1264021086'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s22.cnzz.com/z_stat.php%3Fid%3D1264021086%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));--%>
<%--</script>--%>
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