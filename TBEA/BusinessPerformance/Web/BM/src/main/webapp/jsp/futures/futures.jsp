<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<!-- message box -->
<link
	href="${pageContext.request.contextPath}/jsp/message-box/css/style.css"
	rel="stylesheet" type="text/css">

<!-- jquery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery.js"></script>

<!-- jquery ui -->
<!-- jquery ui gray -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.js"></script>
<!-- jquery ui blue -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/jqgrid/themes/redmond/jquery-ui-custom.css">
<script
	src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery-ui-custom.min.js"
	type="text/javascript"></script>

<!-- 多选菜单 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/multi-select/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/multi-select/assets/style.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/multi-select/assets/prettify.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/multi-select/assets/prettify.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/multi-select/jquery.multiselect.js"></script>


<!-- jqgrid -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/jqgrid/themes/ui.jqgrid.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/jqgrid/themes/ui.multiselect.css">
<script
	src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery.tablednd.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery.contextmenu.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/jqgrid/js/i18n/grid.locale-cn.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery.layout.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery.jqGrid.js"
	type="text/javascript"></script>

<!-- jqgrid assist -->
<script src="${pageContext.request.contextPath}/jsp/jqgrid/jqassist.js"
	type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/jsp/json2.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/util.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/jqgrid/vector.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/unitedSelector.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/dateSelector.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/futures/futuresdef.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/futures/futures.js"
	type="text/javascript"></script>

<!-- message box -->
<script
	src="${pageContext.request.contextPath}/jsp/message-box/js/Sweefty.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/message-box/js/moaModal.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/messageBox.js"
	type="text/javascript"></script>

<title>期货套保</title>

<style type="text/css">
body {
	background-color: rgb(247, 247, 247);
	visibility: hidden;
}

.panel-content-border {
	height: 350px;
	width: 100%;
	border: 2px solid #e3e3e3;
	align: center;
	valign: center;
	text-align: center;
}

.panel-content {
	height: 100%;
	width: 100%;
}

.contract {
	text-align: center;
}

.contract h1 {
	display: none;
	color: #003B8F;
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
	width: 95%;
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
	height: 28px;
}
</style>
</head>
<body>
	<div class="header">
		<h1 id="headertitle">期货套保</h1>
	</div>

	<Table align="center" style="width: 800px">
		<tr>
			<td>
				<div id="type" style="float: left; height: 28px;"></div>
				<input type="button"
				value="更新" style="float: left; width: 80px; height: 28px; padding: .1em 1em; margin-left: 10px; margin-top: -1px;"
				onclick="view.updateUI()" />
				<div id="radio" style="float: left; width: 80px; margin-left: 10px;">
                    <input type="radio" id="rdct" name="radio" >
                        <label for="rdct" style="height: 28px; margin-top: -1px;">图</label>
                    <input type="radio" id="rdtb" name="radio" checked="checked">
                        <label for="rdtb" style="height: 28px; margin-top: -1px;">表</label>
                </div>
			</td>
		</tr>
		<tr>
			<td>
			    <%@include file="cu/cu.jsp"%>
				<%@include file="al/al.jsp"%>
			</td>
		</tr>
	</Table>
	<script type="text/javascript">
    
    $("#radio").buttonset();
	$("input:button,input:submit,input:reset").button();
    $(document).ready(function () {
    	view.init({
            type: "type",
            contentType: "radio"
        }); 
    	(function () { 
            $("#type select")
                    .multiselect({
                        multiple: false,
                        header: false,
                        minWidth: 50,
                        height: '100%',
                        selectedList: 1
                    })
                    .css("padding", "2px 0 2px 4px")
                    .css("text-align", "left")
                    .css("font-size", "12px")
                    .css("height", "28px");
        }());
        $(document.body).css("visibility", "visible");
        
    });
</script>
	<script src="${pageContext.request.contextPath}/jsp/style_select.js"></script>
	<%--<script src="${pageContext.request.contextPath}/jsp/style_button.js"></script>--%>
	<script
		src="${pageContext.request.contextPath}/js/echarts/echarts.js"></script>
	<%@include file="../components/loading.jsp"%>
</body>


</html>