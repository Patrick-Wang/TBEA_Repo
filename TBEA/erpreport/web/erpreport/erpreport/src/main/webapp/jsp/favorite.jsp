<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="zh-cn">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Document</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/plugins/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css"
		  href="${pageContext.request.contextPath}/jsp\plugins\font-awesome\css\font-awesome.css">
	<style>

		.info-box {
			height: 85px;
			background-color: white;
			background-color: #ecf0f5;
            cursor:pointer;
		}

		.info-box .info-box-icon {
			border-top-left-radius: 2px;
			border-top-right-radius: 0;
			border-bottom-right-radius: 0;
			border-bottom-left-radius: 2px;
			display: block;
			float: left;
			height: 85px;
			width: 85px;
			text-align: center;
			font-size: 45px;
			line-height: 85px;
			background: rgba(0, 0, 0, 0.2);
		}

		.info-box .info-box-content {
			padding: 5px 10px;
			margin-left: 85px;
		}

		.info-box .info-box-content .info-box-text {
            display: block;
            font-size: 16px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            text-transform: uppercase;
            font-weight: bold;
            margin-top: 5px;
		}

		.info-box .info-box-content .info-box-number {
            display: block;
            /* font-weight: bold; */
            font-size: 12px;
            margin-top: 5px;
		}

		.major {
			font-weight: 10px;
			color: #01AAED;
		}

		.main {
			margin-top: 25px;
		}

		.main .layui-row {
			margin: 10px 0;
		}
        body {
            margin: 0px;
            font-family: "Microsoft YaHei";
        }
	</style>
	<script>
        var context = {
            baseUrl: "${pageContext.request.contextPath}/",
            userName: '${userName}',
            favorites: JSON.parse('${myFavorites}')
        }
	</script>
</head>

<body>
<div class="layui-fluid main">

</div>
</div>
<script src="${pageContext.request.contextPath}/jsp/plugins/jquery/jquery-1.12.3.js"></script>
<script src="${pageContext.request.contextPath}/jsp/plugins/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/jsp/framework/templates/util.js"></script>
<script src="${pageContext.request.contextPath}/jsp/favorite.js"></script>
<script>
    layui.use('jquery', function() {
        var $ = layui.jquery;
        $('#test').on('click', function() {
            parent.message.show({
                skin: 'cyan'
            });
        });
    });
</script>
</body>

</html>