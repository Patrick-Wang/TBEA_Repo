<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- Head -->
<head>
<meta charset="utf-8" />
<title></title>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<%@include file="../../ie8-t.jsp"%>

<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery/jquery-1.12.3.js"></script>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/jsp/ui2/img/logo.png"
	type="image/x-icon">

<!--Basic Styles-->
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/bootstrap.min.css"
	rel="stylesheet" />

<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/font-awesome.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/weather-icons.min.css"
	rel="stylesheet" />

<!-- jedate -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jedate/skin/jedate.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jedate/skin/deepgreen.css">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jedate/jquery.jedate.js"
	type="text/javascript"></script>
<!--Beyond styles-->
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/beyond.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/demo.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/typicons.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/animate.min.css"
	rel="stylesheet" />
<link id="skin-link" href="" rel="stylesheet" type="text/css" />

<!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
<script
	src="${pageContext.request.contextPath}/jsp/ui2/assets/js/skins.min.js"></script>


<!-- jquery ui -->
<!-- jquery ui gray -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.js"></script>
<!-- jquery ui blue -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/redmond/jquery-ui-custom.css">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery-ui-custom.min.js"
	type="text/javascript"></script>


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/webuploader.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/webuploader/webuploader.js"></script>
<!-- 多选菜单 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/assets/style.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/assets/prettify.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/assets/prettify.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/jquery.multiselect.js"></script>
<link
	href="${pageContext.request.contextPath}/jsp/ui2/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- jedate -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jedate/skin/jedate.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jedate/skin/deepgreen.css">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jedate/jquery.jedate.js"
	type="text/javascript"></script>
<!-- jqgrid -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/ui.jqgrid.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/ui.multiselect.css">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery.tablednd.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery.contextmenu.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/i18n/grid.locale-cn.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery.layout.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery.jqGrid.js"
	type="text/javascript"></script>

<!-- jqgrid assist -->
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/jqassist.js"
	type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/jsp/ui2/pages/json2.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/ui2/pages/util.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/vector.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/dateSelector.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/unitedSelector.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/companySelector.js"
	type="text/javascript"></script>

<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery/jqueryex.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/jsp/ui2/ui2.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/scroll/css/jquery.mCustomScrollbar.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/scroll/js/jquery.mCustomScrollbar.js"></script>

<%@include file="../../ie8-b.jsp"%>

<style>
.webuploader-pick-hover{
	background-color: #e6e6e6;
}

.webuploader-pick {
    position: relative;
    display: inline-block;
    cursor: pointer;
    background: white;
    padding: 0px 0px;
    color: white;
    text-align: center;
    border-radius: 0px;
    overflow: hidden;
}

.result-title-left{
	border:solid 1px lightgray;
	padding:15px 15px 15px 15px;
	border-left:none;
}

.result-title-right{
	border:solid 1px lightgray;
	padding:15px 15px 15px 15px;
	border-left:none;
	border-right:none;
}

.result-body-left{
	border:solid 1px lightgray;
	padding:10px 15px 10px 15px;
	border-left:none;
	border-top:none;
}

.result-body-right{
	border:solid 1px lightgray;
	padding:10px 15px 10px 15px;
	border-left:none;
	border-top:none;
	border-right:none;
}

.progress{
	height:14px;
	margin-bottom:0px;
}

</style>


</head>
<!-- /Head -->
<!-- Body -->
<body>
	<!-- Main Container -->
	<div class="main-container container-fluid">
		<!-- Page Container -->
		<div class="page-container">

			<!-- Page Content -->
			<div class="page-content">
				<!-- Page Breadcrumb -->
				<div class="page-breadcrumbs">
					<ul class="breadcrumb">
					</ul>
				</div>
				<!-- /Page Breadcrumb -->

				<!-- Page Header -->
				<div class="page-header position-relative">
					<div class="header-title">
						<div id="headerHost" class="pull-left">
							<div class="workinput pull-left" style="display:none">
								<input id="grid-date" type="text" readonly="readonly"><i
									class="fa fa-calendar"></i>
							</div>
							<div id="item-sel" class="pull-left"></div>
							<div id="comp-sel" class="pull-left"></div>
							<div id="picker" class="pull-left btn btn-default">选择文件</div>
							<div id="ctlBtn" class="btn btn-default pull-left">开始上传</div>
						</div>
					</div>
					<span id="unit"></span>
				</div>
				<!-- /Page Header -->
				<!-- Page Body -->
				<div class="page-body">
					<div class="row">
						<div class="col-lg-12 col-sm-12 col-xs-12">
							<div class="well">
								<div id="thelist" style="padding-left: 20px;padding-right: 20px;">
									<div class="row">
										<div class="col-lg-8 col-sm-8 col-xs-8 result-title-left">
											<div class="col-lg-5 col-sm-5 col-xs-5"></div>
											<div class="info">文件名称</div>
										</div>
										<div class="col-lg-4 col-sm-4 col-xs-4 result-title-right">
											<div class="col-lg-5 col-sm-5 col-xs-5"></div>
											<div class="info">上传状态</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /Page Body -->
			</div>
			<!-- /Page Content -->
		</div>
		<!-- /Page Container -->
		<!-- Main Container -->
	</div>
	<!--Basic Scripts-->
	<div class="template" style="display:none">
		<div class="row inner-template">
			<div class="col-lg-8 col-sm-8 col-xs-8 result-body-left">
			</div>
			<div class="col-lg-4 col-sm-4 col-xs-4 result-body-right">
			</div>
		</div>
	</div>
	<form id="exportForm" style="display:none" method="post"></form>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/slimscroll/jquery.slimscroll.min.js"></script>

	<!--Beyond Scripts-->
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/beyond.min.js"></script>

	<!--Page Related Scripts-->
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/bootbox/bootbox.js"></script>
	<%@include file="../loading.jsp"%>
	<script>
		var breads = '${param.breads}';
		if (breads != ''){
			Util.Breadcrumb.render(JSON.parse('${param.breads}'));
			if (Util.isIframe()) {
				Util.Breadcrumb.setOnClickListener(function(breadNode) {
					window.parent['onClickBreadcrumb']
							&& window.parent['onClickBreadcrumb'](breadNode);
				});
			}
		}
		if ('${date}' == ""){
        	date = Util.parseDate('${year}', '${month}', '${day}');
        }else{
            date = Util.toDate('${date}');
        }
		
		var getDate;

		function createDate(type){
			$("#grid-date").parent().css("display", "");
			 var seasonClass = "month";
            var fmt = "YYYY年MM月";
            if ('${asSeason}' == 'true') {
                fmt = "YYYY年 && $$MM月";
                seasonClass = "season-month";
            }
            else if ('${asSeasonAcc}' || type == "season") {
                fmt = "YYYY年 &&MM月";
                seasonClass = "season";
            }
            else if (!date.month || type == 'year') {
                fmt = "YYYY年";
                seasonClass = "year";
            } 
            else if (type == 'month') {
                fmt = "YYYY年MM月";
                seasonClass = "month";
            }
            else if (date.day || type == 'day') {
                fmt = "YYYY年MM月DD日";
                seasonClass = "day";
            }
            
            var jdName = '${jdName}' == "" ? undefined : JSON.parse('${jdName}');
            $("#grid-date").jeDate({
                skinCell: "jedatedeepgreen",
                format: fmt,
                isTime: false,
                isinitVal: true,
                isClear: false,
                isToday: false,
                seasonText: jdName
            }).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top")
                .removeClass("season-month season year month day")
                .addClass(seasonClass);
		}
		
		if (date && date.year){
			 createDate();
             getDate = function () {
                 var ret = {};
                 var curDate =  $("#grid-date").getDate();
                 ret = {
                     year: curDate.getFullYear(),
                     month: curDate.getMonth() + 1,
                     day: curDate.getDate()
                 };
                 return ret;
             };
		}
		
		var nodeData = '${nodeData}';
		var compSelctor;
		if (nodeData.length > 0) {
			var comps = JSON.parse(nodeData);
			compSelctor = new Util.CompanySelector(false,
					"comp-sel", comps);
			if (comps.length == 1) {
				compSelctor.hide();
			}
		}
		var unitedSelector;
		var itemNodes = '${itemNodes}';
		if (itemNodes != '') {
			var nodes = JSON.parse(itemNodes);
			unitedSelector = new Util.UnitedSelector(nodes,	"item-sel");
			
			
			var dateNodes = '${dateNodes}';
			if (dateNodes != ''){
				var dtNodes = JSON.parse(dateNodes);
				unitedSelector.change(function() {
					updateBtnsStatus();
					var itemId = getItemId();
					createDate(dtNodes[itemId]);
				});
			}else{
				unitedSelector.change(function() {
					updateBtnsStatus();
				});
			}
		}
		
		
		
		var $list;
		var uploader;
		
		function updateBtnsStatus(){
			if ($(".wait").length == 0){
				$("#ctlBtn").attr("disabled", true);
				$("#ctlBtn").prop("disabled", true);
			}else{
				$("#ctlBtn").attr("disabled", false);
				$("#ctlBtn").prop("disabled", false);
			}
			
			var opVal = $("#item-sel option:selected").val();
			if (opVal < 0){
				$("#picker").attr("disabled", true);
				$("#picker").children().css("display", "none");
			}else{
				$("#picker").attr("disabled", false);
				$("#picker").children().css("display", "");
			}
		}		
		
		
		
		function chainUpload(){
			if (!isRetrying){
				$(".wait:eq(0)").each(function(i, e){
					$e = $(e);
					uploader.options.formData = {
						compId : $e.attr("cid"),
						item : $e.attr("iid"),
						date : $e.attr("dt")
					}
					
					uploader.upload($e.attr("id"));
				}).length > 0;
			}
		}
		
		function getCompId(){
			if (compSelctor){
				return compSelctor.getCompany();
			}
			return "";
		}

		function getItemId(){
			if (unitedSelector){
				return unitedSelector.getDataNode(unitedSelector.getPath()).data.id;
			}
			return "";
		}
		
		function getProText(){
			var ret = "";
			if (getDate){
				ret += $("#grid-date").val() + " ";
			}
			
			if (compSelctor){
				ret += compSelctor.getCompanyName() + " ";
			}
			if (unitedSelector){
				ret += unitedSelector.getDataNode(unitedSelector.getPath()).data.value;
			}
			
			if (ret.length > 0){
				ret += " : ";
			}
			return ret;
		}
		
		var isRetrying = false;

		$(document).ready(function() {
				var $ = jQuery, $btn = $('#ctlBtn'), state = 'pending';
				
				$btn.on('click', function() {
					if ($btn.attr("disabled") == "disabled"){
						return;	
					}
					chainUpload();
				});
				
				$list = $('#thelist');
				uploader = WebUploader.create({
					// swf文件路径
					swf : '${pageContext.request.contextPath}/js/webuploader/Uploader.swf',

					// 文件接收服务端。
					server : '${importUrl}.do',

					// 选择文件的按钮。可选。
					// 内部根据当前运行是创建，可能是input元素，也可能是flash.
					pick : {
						id : '#picker',
						multiple : true
					},

					accept:{
					    title: 'excel',
					    extensions: 'xls,xlsx',
					    mimeTypes: 'application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
					},
					duplicate : true,
					fileVal : 'upfile',
					// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
					resize : false,
					timeout:false
				});

				uploader.on('fileQueued', function(file) {
					$("#ctlBtn").attr("disabled", false);
					var files = uploader.getFiles();
					var template = $(".template").html();
					$list.append(template);
					var jqtemplate = $list.find(".inner-template");
					jqtemplate.removeClass("inner-template");
					jqtemplate.addClass("wait");
					jqtemplate.attr("id", file.id);
					jqtemplate.attr("cid", getCompId());
					jqtemplate.attr("iid", getItemId());
					if (getDate){
						var dt = getDate();
						jqtemplate.attr("dt", dt.year + "-" + dt.month + "-" + dt.day);
					}					
					
					jqtemplate.find(".result-body-left").append('<div>' + 
							getProText() + file.name + 
					'</div>');
					jqtemplate.find(".result-body-right").append('<div style="float:left; margin-right:5px"><i class="fa fa-times" style="cursor:pointer"></i>' + 
							' 等待上传' + 
							'</div>' +
							'<div class="progress progress-striped" style="display:none">'
							+ '<div class="progress-bar" role="progressbar" style="width: 0%">'
							+ '</div>'
							+ '</div>');
					jqtemplate.find(".fa-times").on("click", function(){
						uploader.removeFile(file.id, true);
						jqtemplate.remove();
						updateBtnsStatus();
					});
				});

				uploader.on('uploadProgress', function(file, percentage) {
					var jqtemplate = $('#' + file.id);
							jqtemplate.find('.progress').show();
					$percent = jqtemplate.find('.progress .progress-bar');
					jqtemplate.find(".result-body-right div").eq(0).empty()
						.html('<i class="fa fa-upload"></i>' + 
								' 上传中...');
					$percent.css('width', percentage * 100 + '%');
				});

				uploader.on('uploadSuccess', function(file, ret) {
					var jqtemplate = $('#' + file.id);
					if (ret.errorCode == 0) {
						jqtemplate.find(".result-body-right div").eq(0).empty()
						.html('<i class="fa fa-check"></i>' + 
								' 上传成功');
					} else {
						jqtemplate.find(".result-body-right div").eq(0).empty()
						.html('<i class="fa fa-refresh"></i>' + 
								' ' + ret.message);
						jqtemplate.find(".fa-refresh").css("cursor", "pointer").on("click", function(){
							if (isRetrying){
								return;
							}
							isRetrying = true;
							uploader.options.formData = {
								compId : jqtemplate.attr("cid"),
								item : jqtemplate.attr("iid"),
								date : jqtemplate.attr("dt")
							}
							uploader.retry(file);
							
						});
					}
					
					if (ret.type == 'file'){
						if (ret.token){
							$("#exportForm")[0].action = "${exportUrl}.do?token=" + ret.token;
							$("#exportForm")[0].submit();
						}
					}
				});

				uploader.on('uploadError', function(file) {
					var jqtemplate = $('#' + file.id);
					jqtemplate.find(".result-body-right div").eq(0).empty()
					.html('<i class="fa fa-refresh"></i>' + 
							' 上传出错');
					jqtemplate.find(".fa-refresh").css("cursor", "pointer").on("click", function(){
						if (isRetrying){
							return;
						}
						isRetrying = true;
						uploader.options.formData = {
							compId : jqtemplate.attr("cid"),
							item : jqtemplate.attr("iid"),
							date : jqtemplate.attr("dt")
						}
						uploader.retry(file);
						
					});
				});

				uploader.on('uploadComplete', function(file) {
					var jqtemplate = $('#' + file.id);
					$('#' + file.id).find('.progress').fadeOut();
					$('#' + file.id).removeClass("wait");
					updateBtnsStatus();
					chainUpload();
					isRetrying = false;
				});
				
				var $webpick = $(".webuploader-pick");
				$webpick.removeClass("webuploader-pick");
				setTimeout(function(){
					$webpick.next().css("width", "100%")
					.css("height", "100%")
					.css("left", "0")
					.css("top", "0");
					$webpick.parent().append($webpick.text());
					$webpick.remove();
					updateBtnsStatus();
				}, 10);
				
			});


	</script>

</body>
<!--  /Body -->
</html>
