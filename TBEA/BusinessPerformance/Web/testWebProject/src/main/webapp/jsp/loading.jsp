<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style>
.lbackground {
	display: block;
	width: 100%;
	height: 100%;
	opacity: 0.4;
	filter: alpha(opacity = 40);
	background: gray;
	position: absolute;
	top: 0;
	left: 0;
	z-index: 2000;
}

.loading {
	background: white url(../jsp/loading.gif) no-repeat;
}

.lprogressBar {
	border: solid 2px #86A5AD;
	display: block;
	width: 270px;
	height: 66px;
	position: fixed;
	top: 50%;
	left: 50%;
	padding: 0px 0px 0px 0px;
	margin-left: -135px;
	margin-top: -33px;
	text-align: left;
	font-weight: bold;
	position: absolute;
	z-index: 2001;
	background: white;
}
</style>

<div id="background" class="lbackground" style="display: none;"></div>
<div id="progressBar" class="lprogressBar" style="display: none;">
	<table>
		<tr>
			<td valign="middle" style="width: 60px; height: 60px;" align="center">
				<div class="loading" style="width: 30px; height: 30px;"></div>
			</td>
			<td>数据加载中，请稍等...</td>
		</tr>
	</table>
</div>

<script type="text/javascript">
        	var ajaxbg = $("#background,#progressBar"); 
        		ajaxbg.hide(); 
        	$(document).ajaxStart(function () { 
        		ajaxbg.show(); 
        	}).ajaxStop(function () { 
        		ajaxbg.hide(); 
        	}); 
        	$("#background").css("height", document.body.scrollHeight + "px");
</script>
