<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

<!-- message box -->
<link href="../jsp/message-box/css/style.css" rel="stylesheet" type="text/css">

<!-- jquery -->
<script type="text/javascript" src="../jsp/jqgrid/js/jquery.js"></script>

<!-- jquery ui -->
<!-- jquery ui gray -->
<link rel="stylesheet" type="text/css" href="../jsp/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.css" />
<script type="text/javascript" src="../jsp/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.js"></script>
<!-- jquery ui blue -->
<link rel="stylesheet" type="text/css" media="screen" href="../jsp/jqgrid/themes/redmond/jquery-ui-custom.css">
<script src="../jsp/jqgrid/js/jquery-ui-custom.min.js" type="text/javascript"></script>

<!-- 多选菜单 -->
<link rel="stylesheet" type="text/css" href="../jsp/multi-select/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" href="../jsp/multi-select/assets/style.css" />
<link rel="stylesheet" type="text/css" href="../jsp/multi-select/assets/prettify.css" />
<script type="text/javascript" src="../jsp/multi-select/assets/prettify.js"></script>
<script type="text/javascript" src="../jsp/multi-select/jquery.multiselect.js"></script>


<!-- jqgrid -->
<link rel="stylesheet" type="text/css" media="screen" href="../jsp/jqgrid/themes/ui.jqgrid.css">
<link rel="stylesheet" type="text/css" media="screen" href="../jsp/jqgrid/themes/ui.multiselect.css">
<script src="../jsp/jqgrid/js/jquery.tablednd.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/js/jquery.contextmenu.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/js/jquery.layout.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/js/jquery.jqGrid.js" type="text/javascript"></script>

<!-- jqgrid assist -->
<script src="../jsp/jqgrid/jqassist.js" type="text/javascript"></script>

<script src="../jsp/json2.js" type="text/javascript"></script>
<script src="../jsp/util.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/vector.js" type="text/javascript"></script>
<script src="../jsp/dateSelector.js" type="text/javascript"></script>
<script src="../jsp/unitedSelector.js" type="text/javascript"></script>
<script src="../jsp/companySelector.js" type="text/javascript"></script>
<script src="../jsp/entry_template.js" type="text/javascript"></script>

<!-- message box -->
<script src="../jsp/message-box/js/Sweefty.js" type="text/javascript"></script>
<script src="../jsp/message-box/js/moaModal.js" type="text/javascript"></script>
<script src="../jsp/messageBox.js" type="text/javascript"></script>

<script type="text/javascript">
	var view = entry_template.View.getInstance();
      (function () {
          $(document).ready(function () {
        	  view.initInstance({
          				tableId : "table",
          				dateId:	"date",
          				companyId: "company",
          				comps : JSON.parse('${nodeData}'),
          				date : {
          					month : ${!empty month} ? '${month}' : undefined, 
          					year : ${year}
          				},
          				entryType : ${entryType},
          				DeputyApproveStatus : 1
          			});
          });
      })();
</script>
<meta charset="UTF-8">

<title></title>

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


#update{
	height:23px;
	padding: .1em 1em;
	margin-top: -1px;
}

.ui-multiselect {
	padding: 2px 0 2px 4px;
	text-align: left;
	font-size: 12px;
}

</style>
</head>
<script type="text/javascript">
  function cellkeydown(event) { 
        if (event.ctrlKey && event.keyCode == 86) { 
            var ss = document.getElementById("textArea"); 
            ss.focus(); 
            ss.select(); 
            // 等50毫秒，keyPress事件发生了再去处理数据 
            setTimeout("dealwithData()",50); 
        }     
    } 
    function dealwithData(event) { 
            var ss = document.getElementById("textArea"); 
            /*  console.log(ss.value);  */
            ss.blur(); 
    } 
</script>

<body onkeydown="return cellkeydown(event)">
	<div class="header">
		<h1></h1>
	</div>
	<Table align="center" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<Table>
					<tr>
						<td>
							<div id="date"></div>
						<td>
						<td>
							<div id="company"></div>
						<td>
						<td><input id="update" type="button" value="选择"
							style="width: 80px; margin-left: 10px;" onclick="view.updateUI()"></input>
						</td>
					</tr>
				</Table>
			</td>
		</tr>
		<tr align="center">
			<td>
				<div style="padding-top:20px;font-size:25px" id="nodatatips">请选择需要录入的数据</div>
			</td>
		</tr>
		<tr >
			<td>
			<div style="height:5px"></div>
			</td>
		</tr>
		
		<tr>
			<td>
				<table id="entryarea" style="display: none" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<div id="table"></div>
						<td>
					</tr>
					<tr>
						<td>
							<div style="height:15px"></div>
						<td>
					</tr>
					<tr>
						<td>
							<Table cellspacing="0" cellpadding="0">
								<tr align="left">
									<td ><input id="save" type="submit" value="保存"
										style="width: 80px;" onclick="view.save()"></input> 
									</td>
									<td>
									<div style="width:140px"></div>
									</td>
									<td>
										<table>
											<tr align = "right">
											<c:if test="${isin13Comps}">
												<td><input id="submitToDeputy" type="submit" value="内部审核"
													style="width: 80px;" onclick="view.submitToDeputy()"></input>
												</td>
											</c:if>
												<td><div style="width:6px"></div>
												</td>
												<td><input id="submit" type="submit" value="提交"
													style="width: 80px;" onclick="view.submit()"></input>
												</td>
											</tr>
										</table>
									</td>
							    </tr>										
								<tr>							
									<td>
										<div id="DeputyAgree" style="font-size: 20px; color: red ;font-weight: 400; display:none">
											经营副总已经审核数据。
										</div>
									</td>
									<td>
										<div id="DeputyDisagree" style="font-size: 20px; color: red ;font-weight: 400; display:none">
											经营副总数据还未审核数据。
										</div>
									</td>
								</tr>
							</Table>
						</td>
					</tr>
				</table>
	</Table>
	<%@include file="loading.jsp"%>
	<script type="text/javascript">
		 $("input:button,input:submit,input:reset").button();  
		 $("input").button( "option", "icons", {primary:'ui-icon-cancel',secondary:'ui-icon-cancel'} );  
	</script>
	<div> 
    <textarea id="textArea" style="width:0px;height:0px;margin-top:0px;margin-left:0px;margin-right:0px;margin-bottom:0px;padding-bottom:0px;padding-left:0px;padding-top:0px;padding-right:0px;border-width:0px;"></textarea></div>
</body>

</html>