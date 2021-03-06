﻿<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">


    <!-- message box -->
    <link href="${pageContext.request.contextPath}/jsp/message-box/css/style.css" rel="stylesheet" type="text/css">

    <!-- jquery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.7.2.min.js"></script>

    <!-- jquery ui blue -->
    <link rel="stylesheet" type="text/css" media="screen"
          href="${pageContext.request.contextPath}/jsp/jqgrid/themes/redmond/jquery-ui-custom.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jsp/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.js"></script>

    <!-- 多选菜单 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jsp/multi-select/jquery.multiselect.css" />
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jsp/multi-select/assets/style.css" />
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jsp/multi-select/assets/prettify.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/jsp/multi-select/assets/prettify.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jsp/multi-select/jquery.multiselect.js"></script>


    <!-- jqgrid -->
    <link rel="stylesheet" type="text/css" media="screen"
          href="${pageContext.request.contextPath}/jsp/jqgrid/themes/ui.jqgrid.css">
    <link rel="stylesheet" type="text/css" media="screen"
          href="${pageContext.request.contextPath}/jsp/jqgrid/themes/ui.multiselect.css">

    <script src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery.tablednd.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery.contextmenu.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/jqgrid/js/i18n/grid.locale-cn.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery.layout.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery.jqGrid.js" type="text/javascript"></script>

    <!-- jqgrid assist -->
    <script src="${pageContext.request.contextPath}/jsp/jqgrid/vector.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/jqgrid/jqassist.js" type="text/javascript"></script>


    <script src="${pageContext.request.contextPath}/jsp/json2.js" type="text/javascript"></script>

    <!-- message box -->
    <script src="${pageContext.request.contextPath}/jsp/message-box/js/Sweefty.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/message-box/js/moaModal.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/messageBox.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/util.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/unitedSelector.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/dateSelector.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/components/dateSeasonSelector.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/components/dateSelectorProxy.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/companySelector.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/framework/route/route.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/framework/basic/basicdef.js"></script>
    <script src="${pageContext.request.contextPath}/jsp/framework/basic/basic.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/framework/basic/basicShow.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/framework/templates/singleDateReport/show.js"></script>
    <script src="${pageContext.request.contextPath}/jsp/framework/templates/dateChartReport/show.js"></script>
    <title>${title}</title>

    <style type="text/css">
        body {
            background-color: rgb(247, 247, 247);
            visibility: hidden;
        }

        .panel-content-border {
            height: 350px;
            width: 1000px;
            border: 2px solid #e3e3e3;
            margin: 0;
            padding: 0;
            align: center;
            valign: center;
            text-align: center;
        }

        .panel-content {
            height: 100%;
            width: 100%;
            margin: 0;
            padding: 0;
        }

        .right {
            width: 45%;
            height: 180px;
            float: left;
            padding-top: 20px;
            margin-left: 225px;
        }

        .contract {
            text-align: center;
        }

        .contract h1 {
            display: none;
            color: #003B8F;
        }

        .btn_loading, .btn_detail {
            width: 100px;
            height: 30px;
            padding: 5px,10px;
            font-size: 12px;
            line-height: 1.5;
            boder-radius: 3px;
            background-color: #5cb85c;
            boder-color: #4cae4c;
            color: #fff;
        }

        .header {
            width: 100%;
            height: 60px;
        }

        .header h1 {
            text-align: center;
        }

        .companyname h1 {
            width: 30px;
            font-size: 30px;
            word-wrap: break-word;
            letter-spacing: 20px;
            color: #5cb85c;
            float: left;
        }

        .lxian {
            margin-left: 30px;
            width: 1px;
            height: 175px;
            background: #5cb85c;
            float: left;
        }

        .hrclass hr {
            width: 1100px;
            height: 1px;
            margin-top: 10px;
            margin-left: 90px;
            border: 0;
            background-color: #5cb85c;
        }

        th.ui-th-column div {
            /* jqGrid columns name wrap  */
            white-space: normal !important;
            height: auto !important;
            padding: 0px;
        }

        th.ui-th-ltr {
            /* jqGrid columns name wrap  */
            font-size: 14px;
        }
        .ui-multiselect {
            padding: 2px 0 2px 4px;
            text-align: left;
            font-size: 12px;
        }
        .ui-widget{
            font-size: 12px;
        }

         .ui-widget-content{
            font-size: 12px;
        }

        #exportButton {
            height: 23px;
            width:100px;
            padding: .1em 1em;
            margin-top: 2px;
        }
    </style>
</head>
<body>
<div class="header">
    <h1 id="headertitle">${title}</h1>
</div>

<Table id="frameTable" align="center" style="width:1200px">
    <tr>
        <td>
            <c:if test="${relateZl}">
                    <div id="zlAndyclhgl" style="float: left"></div>
            </c:if>
            <div id="dt" style="float: left;margin-right:10px"></div>
            <div  style="float: left;margin-right:10px">
                <div id="im">

                </div>
            </div>
            <div id="chartSel" style="float: left;margin-right:10px;display:none"></div>
            <input type="button" value="更新" style="float: left; width: 80px; margin-left: 10px;"
                   onclick="framework.router.to(framework.templates.singleDateReport.FRAME_ID).send(framework.templates.singleDateReport.FE_UPDATE)" />
            <div id="radio" style="float: right">
                <input type="radio" id="rdct" name="radio"
                       onclick="framework.router.to(framework.templates.singleDateReport.FRAME_ID).send(framework.templates.dateChartReport.FE_CT_CLICKED)">
                    <label for="rdct">图</label>
                <input type="radio" id="rdtb" name="radio" checked="checked"
                       onclick="framework.router.to(framework.templates.singleDateReport.FRAME_ID).send(framework.templates.dateChartReport.FE_TB_CLICKED)">
                    <label for="rdtb">表</label>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <div id="table" align="center"></div>
            <div id="chart" align="center" style="display: none;"></div>
        </td>
    </tr>

    <tr>
        <td>
            <form id="export" method="post">
                <input id="exportButton" type="button" value="导出"
                       onclick="framework.router.to(framework.templates.singleDateReport.FRAME_ID).send(framework.templates.singleDateReport.FE_EXPORTEXCEL, 'export')">
            </form>

        </td>
    </tr>
</Table>
<script type="text/javascript">

    var isApproveShow = '${approve}' == 'true';

    $(document).ready(function () {
    	
        var pageSlector = new Util.UnitedSelector([{
            data:{
                id:0,
                value:"产品一次送试"
            }
        },{
            data:{
                id:1,
                value:"原材料合格率"
            }
        }],"zlAndyclhgl", [1]);

        pageSlector.change(function(){

            if (pageSlector.getPath()[0] == 0){
                if (isApproveShow){
                    window.location.href="${pageContext.request.contextPath}/cpzlqk/approve.do";
                }else{
                    window.location.href="${pageContext.request.contextPath}/cpzlqk/show.do";
                }
            }
        });
        
        $("#zlAndyclhgl select")
        .multiselect({
            multiple: false,
            header: false,
            minWidth: 115,
            height:'100%',
            // noneSelectedText: "请选择月份",
            selectedList: 1
            })
        .css("padding", "2px 0 2px 4px")
        .css("text-align", "left")
        .css("font-size", "12px");
        

        framework.templates.dateChartReport.createInstance();
        var dateEnd;
        var date;
        if ('${date}' == ""){
            date = {
                month:'${month}' == ''?undefined:parseInt('${month}'),
                year:'${year}' == ''?undefined:parseInt('${year}'),
                day:'${day}' == ''?undefined:parseInt('${day}')
            }
        }else{
            var dt = new Date(Date.parse('${date}'.replace(/-/g, '/')));
            date = {
                month: dt.getMonth() + 1,
                year: dt.getFullYear(),
                day: dt.getDate()
            }
            dt = new Date(Date.parse('${dateEnd}'.replace(/-/g, '/')));
            dateEnd = {
                month: dt.getMonth() + 1,
                year: dt.getFullYear(),
                day: dt.getDate()
            }
        }

        framework.router.to(framework.templates.singleDateReport.FRAME_ID).send(framework.basic.FrameEvent.FE_INIT_EVENT,{
            dtId:"dt",
            date: date,
            dateEnd:dateEnd,
            host:"table",
            title:"${title}",
            asSeason:"${asSeason}" == "true" ? true : false,
            chartUrl:"${chartUrl}.do",
            updateUrl:"${updateUrl}.do",
            exportUrl:"${exportUrl}.do",
            itemId:"im",
            itemNodes:JSON.parse('${nodeData}'),
            chartId:"chart",
            chartSelId:"chartSel",
            chartNodes:JSON.parse('${chartNodes}'),
            itemChart:JSON.parse('${itemChart}')
        });

    //$("#zlAndyclhgl").buttonset();

        $("#radio").buttonset();
        $("#exportButton")
            .css("height", "23px")
            .css("padding", ".1em 1em")
            .css("margin-top", "10px")
            .css("width", "90px");
        $(document.body).css("visibility", "visible");

    });
</script>

<script src="${pageContext.request.contextPath}/jsp/style_button.js"></script>
<script src="${pageContext.request.contextPath}/jsp/www2/js/echarts-plain-2-0-0.js"></script>
<%@include file="../../../components/loading.jsp"%>
</body>


</html>