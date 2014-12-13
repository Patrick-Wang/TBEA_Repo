<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.lang.String"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" media="screen"
	href="../jsp/jqgrid/themes/ui.jqgrid.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="../jsp/jqgrid/themes/ui.multiselect.css">
<script src="../jsp/jqgrid/js/jquery.js" type="text/javascript"></script>

<script src="../jsp/jqgrid/js/jquery.layout.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/js/i18n/grid.locale-en.js"
	type="text/javascript"></script>


<script src="../jsp/jqgrid/js/jquery.jqGrid.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/js/jquery.tablednd.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/js/jquery.contextmenu.js"
	type="text/javascript"></script>
<script src="../jsp/jqgrid/vector.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/jqassist.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" media="screen"
	href="../jsp/jqgrid/themes/redmond/jquery-ui-custom.css">
<script src="../jsp/jqgrid/js/jquery-ui-custom.min.js"
	type="text/javascript"></script>
<script src="../jsp/jqgrid/js/ui.multiselect.js" type="text/javascript"></script>
<script src="../jsp/util.js" type="text/javascript"></script>
<script src="../jsp/cqk.js" type="text/javascript"></script>

<script type="text/javascript">

   	 	var instance = cqk.View.newInstance();
        (function () {
            $(document).ready(function () {
                instance.init("piechart", "squarechart", "linechart", "table", [
                    ${month},//month
                    ${year}
//                     eval(${cqkCompare}),//for line
//                     eval(${cqk})//for table and pie
                ]);
            });
        })();
    </script>
<meta charset="UTF-8">

<title>${year}年${month}月陈欠款</title>

<style type="text/css">
body {
	background-color: rgb(247, 247, 247);
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
	padding: 5px, 10px;
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
</style>
</head>
<body style="width: 1300px">
	<div class=" header">
		<h1>${year}年${month}月陈欠款</h1>
	</div>

	<Table align="center">
		<tr>
			<td>
				<Table>
					<tr>
						<td><%@include file="date_selection.jsp"%>
						</td>
						<td><select id="companys"
							onchange="instance.onCompanySelected(this.value)"
							style="width: 125px;">
								<c:forEach begin="0" end="${company_size - 1}" var="i">
									<c:choose>
										<c:when test="${i == 0}">
											<option value="${ids[i]}" selected="selected">${names[i]}</option>
										</c:when>

										<c:otherwise>
											<option value="${ids[i]}">${names[i]}</option>
										</c:otherwise>

									</c:choose>

								</c:forEach>
						</select></td>
						<td><input type="button" value="更新"
							style="width: 80px; margin-left: 10px;"
							onclick="instance.updateUI()"></input>
				</Table>
			</td>
		</tr>
		<tr>
			<td>
				<div id="table"></div>
			<td>
		</tr>
	</Table>
	<%@include file="loading.jsp"%>
	<div align="center" style="margin-top: 15px">
				<div class="panel-content-border" style="margin-bottom: 20px;">
			<div id="piechart" class="panel-content"></div>
		</div>

		<div>
			行业 <select onchange="instance.onSelected(this.value)">
				<option value="0" selected="selected">国网、南网</option>
				<option value="1">省、市电力公司</option>
				<option value="2">五大发电</option>
				<option value="3">其他电源</option>
				<option value="4">石油石化</option>
				<option value="5">轨道交通</option>
				<option value="6">出口合同</option>
				<option value="7">其他</option>
			</select>
		</div>

		<div style="margin-left: 50px; margin-top: 10px">
			<div class="panel-content-border" style="float: left; width: 600px">
				<div id="squarechart" class="panel-content"></div>
			</div>
			<div class="panel-content-border"
						style="float: left; width: 600px; margin-left: 20px">
				<div id="linechart" class="panel-content"></div>
			</div>
		</div>
	</div>




		</body>
<script src="../jsp/www2/js/echarts-plain-2-0-0.js"></script>

</html>