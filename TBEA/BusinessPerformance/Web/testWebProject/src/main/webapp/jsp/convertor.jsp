<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- jquery -->
<script type="text/javascript" src="../jsp/jqgrid/js/jquery.js"></script>

<!-- jquery ui -->
<!-- jquery ui gray -->
<link rel="stylesheet" type="text/css"
	href="../jsp/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.css" />
<script type="text/javascript"
	src="../jsp/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.js"></script>
<!-- jquery ui blue -->
<link rel="stylesheet" type="text/css" media="screen"
	href="../jsp/jqgrid/themes/redmond/jquery-ui-custom.css">
<script src="../jsp/jqgrid/js/jquery-ui-custom.min.js"
	type="text/javascript"></script>

<!-- 多选菜单 -->
<link rel="stylesheet" type="text/css"
	href="../jsp/multi-select/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css"
	href="../jsp/multi-select/assets/style.css" />
<link rel="stylesheet" type="text/css"
	href="../jsp/multi-select/assets/prettify.css" />
<script type="text/javascript"
	src="../jsp/multi-select/assets/prettify.js"></script>
<script type="text/javascript"
	src="../jsp/multi-select/jquery.multiselect.js"></script>


<!-- jqgrid -->
<link rel="stylesheet" type="text/css" media="screen"
	href="../jsp/jqgrid/themes/ui.jqgrid.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="../jsp/jqgrid/themes/ui.multiselect.css">
<script src="../jsp/jqgrid/js/jquery.tablednd.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/js/jquery.contextmenu.js"
	type="text/javascript"></script>
<script src="../jsp/jqgrid/js/i18n/grid.locale-cn.js"
	type="text/javascript"></script>
<script src="../jsp/jqgrid/js/jquery.layout.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/js/jquery.jqGrid.js" type="text/javascript"></script>

<!-- jqgrid assist -->
<script src="../jsp/jqgrid/jqassist.js" type="text/javascript"></script>


<script src="../jsp/util.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/vector.js" type="text/javascript"></script>
<script src="../jsp/dateSelector.js" type="text/javascript"></script>
<script src="../jsp/unitedSelector.js" type="text/javascript"></script>
<script src="../jsp/companySelector.js" type="text/javascript"></script>
<script src="../jsp/hzb_zbhz_prediction.js" type="text/javascript"></script>
<style type="text/css">
._box {
	width: 119px;
	height: 37px;
	background-color: #53AD3F;
	background-image: url(images/bg.png);
	background-repeat: no-repeat;
	background-position: 0 0;
	background-attachment: scroll;
	line-height: 37px;
	text-align: center;
	color: white;
	cursor: pointer;
}

.none {
	width: 0px;
	height: 0px;
	display: none;
}
</style>
<title>Excel 数据转换器</title>
</head>
<body>
	<form action="ndjh_convert.do" method="get">
	  <input name="file" type="submit" value="下载"  style="width:80px"/>
	</form>
	</br>
	<input name="file" type="submit" value="Log" onclick="download()" style="width:80px"/>
	<table id="p1"></table>
	<script type="text/javascript">
	 function download() {
         $.ajax({
             type: "GET",
             url: "ndjh_log.do",
             success: function (data) {
                 $("#p1")[0].innerHTML = data;
             },
             error: function (XMLHttpRequest, textStatus, errorThrown) {
                 promise.failed(textStatus);
             }
         });
     }
    </script>
</body>
</html>
