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

<meta name="description" content="modals and wells" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<%@include file="../../ie8-t.jsp"%>

<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery/jquery-1.12.3.js"></script>

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/jsp/ui2/assets/img/favicon.png"
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
	src="${pageContext.request.contextPath}/jsp/ui2/assets/js/toastr/toastr.js"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/messageBox.js"
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
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/framework/route/route.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jsp/ui2/bootstrap-select-1.12.4/dist/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="${pageContext.request.contextPath}/jsp/ui2/bootstrap-select-1.12.4/dist/js/bootstrap-select.min.js"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/bootstrap-select-1.12.4/dist/js/i18n/defaults-zh_CN.js"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/authorityMgr/roleMgr.js"></script>

<%@include file="../../ie8-b.jsp"%>


<style>
.header-title .filter-option {
	padding-left: 10px;
	padding-bottom: 3px;
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
							<div id="role-sel" class="pull-left"></div>
							<div id="auth-sel" class="pull-left" style="margin-left:5px;"></div>
							<div id="grid-update" class="btn btn-default">
								查找 <i class="fa fa-search"></i>
							</div>
							<div id="grid-create" class="btn btn-default">创建角色</div>
							<div id="add-auth" class="btn btn-default">添加权限</div>
							<div id="grid-submit" class="btn btn-default">更改</div>
						</div>
					</div>
				</div>
				<!-- /Page Header -->
				<!-- Page Body -->
				<div class="page-body">
					<div class="row">
						<div class="col-lg-12 col-sm-12 col-xs-12">
							<div class="well">
								<div id="table" class="jq-table"></div>
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

	<div id="templates" class="hidden">
		<div id="addAuthTemplate">
			<div id="__addAuth">
				<div class="row">
					<div class="col-md-12">
						<form role="form">
							<div class="form-group">
								<div id="__roleName" class="role_drop"></div>
							</div>
							<div class="form-group">
								<div id="__authName" class="role_drop"></div>
							</div>
							<div class="form-group">
								<div id="__compName" class="role_drop"></div>
							</div>
							<div class="alert alert-danger fade in" id="__warning"
								style="display:none"></div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div id="createRoleTemplate">
			<div id="__createAuth">
				<div class="row">
					<div class="col-md-12">
						<form role="form">
							<div class="form-group">
								<span class="input-icon icon-right">
								<input type="text"
								class="form-control" id="__roleName" placeholder="角色"/>
								<i class="fa fa-user circular"></i>
								</span>
							</div>
							<div class="alert alert-danger fade in" id="__warning"
								style="display:none"></div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

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
		if (Util.isIframe() && breads.length > 0) {
			Util.Breadcrumb.render(JSON.parse(breads));
			Util.Breadcrumb.setOnClickListener(function(breadNode) {
				window.parent['onClickBreadcrumb']
						&& window.parent['onClickBreadcrumb'](breadNode);
			});
		}
		framework.router.to(Util.FAMOUS_VIEW).send(Util.MSG_INIT, {
			tableId : "table",
			auths : JSON.parse('${auths}'),
			roles : JSON.parse('${roles}'),
			comps : JSON.parse('${comps}'),
			updateUrl : '${updateUrl}.do',
			submitUrl : '${submitUrl}.do',
			addAuthUrl : '${addAuthUrl}.do',
			createRoleUrl : '${createRoleUrl}.do',
			roleNameUrl : '${roleNameUrl}.do'
		});
	</script>

</body>
<!--  /Body -->
</html>
