<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- jquery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery/jquery-1.7.2.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/webuploader.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/syntax.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/styleWebuploader.css">

<!-- message box -->
<link
	href="${pageContext.request.contextPath}/jsp/message-box/css/style.css"
	rel="stylesheet" type="text/css">

<!-- jquery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery/jquery-1.7.2.min.js"></script>
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

<!-- message box -->
<script
	src="${pageContext.request.contextPath}/jsp/message-box/js/Sweefty.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/message-box/js/moaModal.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/messageBox.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/webuploader/webuploader.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/util.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/unitedSelector.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/companySelector.js"></script>



<style type="text/css">
.wu-example {
	width: 600px;
	position: relative;
	padding: 45px 15px 15px;
	margin: 15px 0;
	background-color: #fafafa;
	box-shadow: inset 0 3px 6px rgba(0, 0, 0, .05);
	border-color: #e5e5e5 #eee #eee;
	border-style: solid;
	border-width: 1px 0;
}

.uploader-list {
	width: 100%;
	overflow: hidden;
}

.btn-default {
	width: 72px;
	height: 29px;
	font-size: 12px;
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

.ui-multiselect {
	padding: 2px 0 2px 4px;
	text-align: left;
	font-size: 12px;
}
</style>

<script type="text/javascript">
	function renderItemSelector(itemId) {
		var sels = $("#" + itemId + " select");
		for (var i = 0; i < sels.length; ++i) {
			var opts = $("#" + itemId + " select:eq(" + i + ") option");
			var items = [];
			for (var j = 0; j < opts.length; ++j) {
				items.push(opts[j].text);
			}
			$(sels[i]).multiselect({
				multiple : false,
				header : false,
				minWidth : Util.getUIWidth(items) * 1.2,
				height : '100%',
				// noneSelectedText: "请选择月份",
				selectedList : 1
			}).css("padding", "2px 0 2px 4px").css("text-align", "left").css(
					"font-size", "12px");
		}
	}

	var selectVal = 1;
	var $list;
	var uploader;
	$(document).ready(function() {
			var $ = jQuery, $btn = $('#ctlBtn'), state = 'pending';
			var nodeData = '${nodeData}';
			if (nodeData.length > 0) {
				var comps = JSON.parse(nodeData);
				compSelctor = new Util.CompanySelector(false,
						"compid", comps);
				if (comps.length == 1) {
					compSelctor.hide();
					document.getElementsByTagName("title").innerHTML = compSelctor
							.getCompanyName()
							+ " " + $("title").text();
					//$("title").text(compSelctor.getCompanyName() + " " + $("title").text());
					$("h1").text(
							compSelctor.getCompanyName() + " "
									+ $("h1").text());
				}
			}
			var unitedSelector;
			var itemNodes = '${itemNodes}';
			if (itemNodes != '') {
				var nodes = JSON.parse(itemNodes);
				unitedSelector = new Util.UnitedSelector(nodes,
						"item");
				unitedSelector.change(function() {
					renderItemSelector("item");
				});
				renderItemSelector("item");
			}

			$list = $('#thelist');
			uploader = WebUploader.create({
						// swf文件路径
						swf : '${pageContext.request.contextPath}/js/webuploader/Uploader.swf',

						// 文件接收服务端。
						server : '${importUrl}.do',

						// 选择文件的按钮。可选。
						// 内部根据当前运行是创建，可能是input元素，也可能是flash.
						pick : '#picker',

						formData : {
							compId : nodeData.length > 0 ? compSelctor
									.getCompany()
									: undefined,
							item : unitedSelector == undefined ? undefined
									: unitedSelector
											.getDataNode(unitedSelector
													.getPath()).data.id
						},

						fileVal : 'upfile',
						// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
						resize : false,
						timeout:false
					});

			uploader.on('beforeFileQueued', function(file) {
				var files = uploader.getFiles();
				for (var i = 0; i < files.length; ++i) {
					uploader.removeFile(files[i], true);
				}
				uploader.reset();

			});

			uploader.on('fileQueued', function(file) {
				$list.empty();
				var files = uploader.getFiles();
				for (var i = 0; i < files.length; ++i) {
					$list
							.append('<div id="' + files[i].id + '" class="item">'
									+ '<h4 class="info">'
									+ files[i].name
									+ '</h4>'
									+ '<p class="state">等待上传...</p>'
									+ '</div>');
				}

			});

			uploader.on('uploadProgress', function(file, percentage) {
				var $li = $('#' + file.id), $percent = $li
						.find('.progress .progress-bar');

				// 避免重复创建
				if (!$percent.length) {
					$percent = $(
							'<div class="progress progress-striped active">'
									+ '<div class="progress-bar" role="progressbar" style="width: 0%">'
									+ '</div>'
									+ '</div>')
							.appendTo($li)
							.find('.progress-bar');
				}

				$li.find('p.state').text('上传中');

				$percent.css('width', percentage
						* 100 + '%');
			});

			uploader.on('uploadSuccess', function(file, ret) {
				if (ret.errorCode == 0) {
					$('#' + file.id).find('p.state').text('已上传');
				} else {
					$('#' + file.id).find('p.state').text(
							ret.message);
				}
			});

			uploader.on('uploadError', function(file) {
				$('#' + file.id).find('p.state').text('上传出错');
			});

			uploader.on('uploadComplete', function(file) {
				$('#' + file.id).find('.progress').fadeOut();
			});

			$btn.on('click', function() {
				if (state === 'uploading') {
					uploader.stop();
				} else {
					if (selectVal == 1) {
						$('#docsStatus').css(
								"display", "");
						//$('#docsStatus').text("请选择需要导入的文档");
						$('#docsStatus')[0].innerHTML = "请从下拉列表中选择需要导入的文档类型！";

					} else {
						var files = uploader
								.getFiles();

						if (files.length == 0) {
							$('#docsStatus').css(
									"display",
									"block");
							$('#docsStatus')[0].innerHTML = "请选择要上传的文件！";
						} else {
							//uploader.options.formData.filetype = selectVal;
							uploader.options.formData = {
								filetype : selectVal,
								compId : nodeData.length > 0 ? compSelctor
										.getCompany()
										: undefined,
								item : unitedSelector == undefined ? undefined
										: unitedSelector
												.getDataNode(unitedSelector
														.getPath()).data.id
							}
							uploader.upload();
							$('#docsStatus').css(
									"display",
									"none");
						}

					}

				}
			});

			//Initial the combox
			//onIndexSelected();
			selectVal = $('#documenttype').val();
		});

	function onIndexSelected() {
		selectVal = $('#documenttype').val();
		$list.empty();
		var files = uploader.getFiles();
		for (var i = 0; i < files.length; ++i) {
			uploader.removeFile(files[i], true);
		}
		uploader.reset();
	}
</script>
<title>${title}</title>
</head>
<body>

	<div class=" header" align="center">
		<h1>${title}</h1>
	</div>
	<br />

	<table id="documentTypeSelector" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<div id="compid" style="float: left"></div>
			</td>
		</tr>
		<tr>
			<td>
				<div id="item" style="float: left; margin-right: 5px"></div>
			</td>
		</tr>


		<tr>
			<td>
				<div id="uploader" class="wu-example">
					<!--用来存放文件信息-->
					<div id="thelist" class="uploader-list"></div>

					<div class="btns">
						<table>
							<tr>
								<td><div id="picker">选择文件</div></td>
								<td width="10px"></td>
								<td>
									<button id="ctlBtn" class="btn btn-default">开始上传</button>
								</td>
								<!-- <td>
					<div id="docsStatus" style="font-size: 30px; color: red ;font-weight: 400;  width: 100px; height:20px;display:none"></div>
					</td> -->
							</tr>
						</table>
					</div>
				</div>
			</td>
		</tr>

	</table>




	<table align="center">
		<tr>
			<td>
				<div id="docsStatus"
					style="font-size: 30px; color: red; font-weight: 400; display: none;">
				</div>
			</td>
		</tr>
	</table>






	<!-- <table id="importRes">
	<tr>							
		<td>
			<div id="result" style="font-size: 30px; color: red ;font-weight: 400; display:none">
			</div>
		</td>
	</tr>
	</table> -->

	</table>
</body>

</html>
