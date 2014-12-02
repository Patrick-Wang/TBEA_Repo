<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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

<script src="../jsp/zbhz_overview.js" type="text/javascript"></script>

<script type="text/javascript">
            (function () {
                $(document).ready(function () {

                	zbhz_overview.View.newInstance().init(
                		["chart_yd", "chart_jd", "chart_nd", "chart_ydtb", "chart_jdtb"], 
                        ${month},//month
                        ${year},//year
                        ${zbid}//JT data
                    );
                });
            })();
    </script>
<meta charset="UTF-8">

<title>指标汇总 ${zbmc}</title>

<style type="text/css">
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
<body>

	<div class="header">
		<h1>指标汇总 ${zbmc}</h1>
	</div>
	<script type="text/javascript">
		function onCySelected(index){
			$("#dw select").each(function(i, e){
				if (e.id != index){
					e.style.display = "none";
				}
				else{
					e.style.display = "";
				}
			});
			zbhz_overview.View.newInstance().onCySelected(parseInt(index));
		}
	</script>
	<Table align="center">
		<tr>
			<td>
				<Table align="left">
					<tr>
						<td>

							<div>
								按产业: <select onchange="onCySelected(this.value)">
									<option value="${id_19}" selected="selected">${name_19}</option>
									<option value="${id_15}">${name_15}</option>
									<option value="${id_16}">${name_16}</option>
									<option value="${id_17}">${name_17}</option>
									<option value="${id_18}">${name_18}</option>
								</select>
							</div>

						</td>
						<td>
							<div style="margin-left: 15px;" id="dw">
								按单位: <select id="${id_19}"
									onchange="zbhz_overview.View.newInstance().onDwSelected(this.value)"
									style="width: 125px;">
									<option value="100" selected="selected">全部</option>
									<option value="${id_0}">${name_0}</option>
									<option value="${id_1}">${name_1}</option>
									<option value="${id_2}">${name_2}</option>
									<option value="${id_3}">${name_3}</option>
									<option value="${id_4}">${name_4}</option>
									<option value="${id_5}">${name_5}</option>
									<option value="${id_6}">${name_6}</option>
									<option value="${id_7}">${name_7}</option>
									<option value="${id_8}">${name_8}</option>
									<option value="${id_9}">${name_9}</option>
									<option value="${id_10}">${name_10}</option>
									<option value="${id_11}">${name_11}</option>
									<option value="${id_12}">${name_12}</option>
									<option value="${id_13}">${name_13}</option>
									<option value="${id_14}">${name_14}</option>
								</select> <select id="${id_15}"
									onchange="zbhz_overview.View.newInstance().onDwSelected(this.value)"
									id="sbd" style="width: 125px; display: none">
									<option value="100" selected="selected">全部</option>
									<option value="${id_0}">${name_0}</option>
									<option value="${id_1}">${name_1}</option>
									<option value="${id_2}">${name_2}</option>
									<option value="${id_3}">${name_3}</option>
									<option value="${id_4}">${name_4}</option>
									<option value="${id_5}">${name_5}</option>
									<option value="${id_6}">${name_6}</option>
								</select> <select id="${id_16}"
									onchange="zbhz_overview.View.newInstance().onDwSelected(this.value)"
									style="width: 125px; display: none">
									<option value="100" selected="selected">全部</option>
									<option value="${id_7}">${name_7}</option>
									<option value="${id_8}">${name_8}</option>
								</select> <select id="${id_17}"
									onchange="zbhz_overview.View.newInstance().onDwSelected(this.value)"
									style="width: 125px; display: none">
									<option value="100" selected="selected">全部</option>
									<option value="${id_9}">${name_9}</option>
									<option value="${id_10}">${name_10}</option>
									<option value="${id_11}">${name_11}</option>
								</select> <select id="${id_18}"
									onchange="zbhz_overview.View.newInstance().onDwSelected(this.value)"
									style="width: 125px; display: none">
									<option value="100" selected="selected">全部</option>
									<option value="${id_12}">${name_12}</option>
									<option value="${id_13}">${name_13}</option>
								</select>
							</div>
						</td>
						<td>
							<div style="float: left; margin-left: 15px;">
								<input type="button"
									onclick="zbhz_overview.View.newInstance().updateUI()" style="width : 80px; margin-left:10px;"
									value="更新" ></input>
							</div>
						</td>


					</tr>
				</Table>
		</tr>
		<tr>
			<td>

				<div align="center" style="margin-bottom: 15px; margin-top: 15px">
					<div class="panel-content-border">
						<div id="chart_yd" class="panel-content"></div>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div align="center" style="margin-bottom: 15px">
					<div class="panel-content-border">
						<div id="chart_jd" class="panel-content"></div>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div align="center" style="margin-bottom: 15px">
					<div class="panel-content-border">
						<div id="chart_nd" class="panel-content"></div>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div align="center" style="margin-bottom: 15px">
					<div class="panel-content-border">
						<div id="chart_ydtb" class="panel-content"></div>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div align="center" style="margin-bottom: 15px">
					<div class="panel-content-border">
						<div id="chart_jdtb" class="panel-content"></div>
					</div>
				</div>
			</td>
		</tr>
	</Table>
	<script src="../jsp/www2/js/echarts-plain-2-0-0.js"></script>
	<%@include file="loading.jsp"%>
</body>



</html>