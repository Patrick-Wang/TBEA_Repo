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

<script src="../jsp/json2.js" type="text/javascript"></script>
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
	<table id="filelist">
		
	</table>
	


 	<form id = "uploadform" action="upload.do" method="post" enctype="multipart/form-data">
	 	<table>
			<tr><td><span style="font-size:20px;">请上传 .xls 文件</span></td></tr>
			<tr><td><input type="file" name="upfile" accept="application/msexcel" size="100" value="浏览"></td></tr>
			<tr><td><input type="submit" value="上传"> </td></tr>
		</table>

	</form>
	<table>
		<tr>
			<td><form action="ndjhconvert.do" method="post">
					<input name="file" type="submit" value="下载年度计划"
						style="width: 120px" />
				</form></td>
			<td><form action="ydjhconvert.do" method="post">
					<input name="file" type="submit" value="下载月度计划"
						style="width: 120px" />
				</form></td>
			<td><form action="ydsjconvert.do" method="post">
					<input name="file" type="submit" value="下载月度实际"
						style="width: 120px" />
				</form></td>
		</tr>
	</table>

	<br/>
	<input name="file" type="submit" value="Log" onclick="download()" style="width:120px"/>
	<table id="p1"></table>
	<script type="text/javascript">
	 function download() {
         $.ajax({
             type: "GET",
             url: "log.do",
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

<script type="text/javascript">

	function deletefile(fileName){
		var url =  encodeURI("deletefile.do?filename=" + fileName);
		$.ajax({
		    type: "GET",
		    url: url,
		    success: function (data) {
		    	updateFileList();
		    },
		    error: function (XMLHttpRequest, textStatus, errorThrown) {
		       
		    }
		});
	}

	function updateFileList(){
		$.ajax({
		    type: "GET",
		    url: "checkFiles.do",
		    success: function (data) {
		    	var flist = JSON.parse(data);
		    	$("#filelist").empty();
		    	
		    	if (flist.length > 0){
		    		$("#filelist").append("<tr><td>已提交的文件  :</td></tr>");
		    		for (var f in flist){
		    			$("#filelist").append("<tr><td>" + flist[f] + "</td><td><input type='button' value='-' onclick=\"deletefile('" + flist[f] + "')\"></td></tr>");
		    		}
		    	} else{
		    		$("#filelist").append("<tr><td>没有可用文件</td></tr>");
		    	}
		    	$("#filelist").append("<tr><td></td></tr>");
		    },
		    error: function (XMLHttpRequest, textStatus, errorThrown) {
		       
		    }
		});
	}
	updateFileList();
	
	function clickform(){
		$("#uploadform")[0].submit();
		//setInterval(updateFileList, 100);
	}
</script>

</html>
