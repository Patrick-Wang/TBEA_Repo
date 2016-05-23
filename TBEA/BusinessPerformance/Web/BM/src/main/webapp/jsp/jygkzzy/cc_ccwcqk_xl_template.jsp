<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="java.util.List,com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyBglx"%>
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
	<script src="../jsp/jygkzzy/company_selector.js" type="text/javascript"></script>
	<script src="../jsp/jygkzzy/cc_ccwcqk_xl_template.js" type="text/javascript"></script>
	<script src="../jsp/jygkzzy/bglx_selector.js" type="text/javascript"></script>
	
	<!-- message box -->
	<script src="../jsp/message-box/js/Sweefty.js" type="text/javascript"></script>
	<script src="../jsp/message-box/js/moaModal.js" type="text/javascript"></script>
	<script src="../jsp/messageBox.js" type="text/javascript"></script>

	<script type="text/javascript">
		var view = cc_ccwcqk_xl_template.View.getInstance();    
	    (function () {
	    	$(document).ready(function () {
	      	  view.initInstance({
	            		tableId : "table",
	      				dateId:	"date",          				       				
	      				date : {
	      					month : ${!empty month} ? '${month}' : undefined, 
	      					year : ${year}
	      				},
	    	  			companyId: "company",
	      				comps : JSON.parse('${comps}'),
	        			bglxId:"bglx",  		        		
			        		curbglx:"20013", 
			        		isByq:${isByq}, 
			        		isXl:${isXl},  
			        		isSbdcy:${isSbdcy}
	      			});
	        });
	    })(); 
	</script>
	<meta charset="UTF-8">
	
	<title>${year}年${month}月产出完成情况</title>

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
        
        #update {
			height: 23px;
			padding: .1em 1em;
			margin-top: -1px;
		}
		#exportButton {
			height: 23px;
			width:100px;
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

<body>
	<div class="header">
		<h1>${year}年${month}月产出完成情况</h1>
	</div>
	<Table align="center" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<Table>
					<tr>
						<td>
							<div id="bglx"></div>
						<td>
						<td><div id="date"></div>
						</td>
						<td><div id="company"></div>
						</td>
						<td><input id="update" type="button" value="更新" style="width : 80px; margin-left:10px;" class="ui-button ui-widget ui-state-default ui-corner-all"
							onclick="view.updateUI()"></input>
				</Table>
			</td>
		</tr>
		<tr>
			<td>
				<div id="table"></div>
				<div align="center" id="tips" style="margin-top:20px;display:none;font-size:25px">当前项目公司无数据</div>
			<td>
		</tr>
		<tr >
			<td>
			<div style="height:6px"></div>
			</td>
		</tr>
		<tr>
			<td>
				<form id="export" method="post">
					<input id="exportButton" type="button" value="导出"
						onclick="view.exportExcel()"
						class="ui-button ui-widget ui-state-default ui-corner-all"
						role="button" aria-disabled="false"></input>
				</form>
			</td>
			
		</tr>		
	</Table>
	<%@include file="../loading.jsp"%>
	<script src="../jsp/www2/js/echarts-plain-2-0-0.js"></script>
	<script src="../jsp/style_button.js"></script>
</body>

</html>