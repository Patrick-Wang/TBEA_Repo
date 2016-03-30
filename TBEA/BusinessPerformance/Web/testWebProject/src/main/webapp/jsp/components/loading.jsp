<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style>
.lbackground {
	display: block;
	
	-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(opacity=50)";

	opacity: 0.4;
	background: gray;
	position: absolute;
	top: 0;
	left: 0;
	z-index: 2000;
}

.loading { 
	background: white url(images/loading.gif) no-repeat;
}

.lprogressBar {
	border: solid 2px #86A5AD;
	display: block;
	width: 330px;
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
			<td id="loadingText" style = "width:320px">数据处理中，请稍等...</td>
		</tr>
	</table>
</div> 
 
<script type="text/javascript">
			var ajaxbg = $("#background,#progressBar"); 
/* 			if (navigator.appName == "Microsoft Internet Explorer"){
				ajaxbg = $("#progressBar"); 
			} */
        	ajaxbg.hide(); 
        	$(document).ajaxStart(function () { 
        		ajaxbg.show();    
/*         		if (navigator.appName == "Microsoft Internet Explorer"){
        			$("table,input,select, h1").each(function(index, e){e.disabled = true;});	
        			$("#progressBar table")[0].disabled = false;
      			}
      			else{
                	$("#background").css("height", document.body.scrollHeight + "px");
                	$("#background").css("width", document.body.scrollWidth + "px");
      			} */
    			$("#background").css("height", '100%');
                $("#background").css("width", '100%');
        	}).ajaxStop(function () { 
        		ajaxbg.hide(); 
/*         		if (navigator.appName == "Microsoft Internet Explorer"){
        			$("table,input,select, h1").each(function(index, e){e.disabled = false;});
      			} */
    			$("#background").css("height", '100%');
                $("#background").css("width", '100%');
        	}); 

        	$(window).resize(function(){
        		if (navigator.appName != "Microsoft Internet Explorer"){
        			
        		}
        		$("#background").css("height", '100%');
                $("#background").css("width", '100%');
        	})
</script>
