<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<head >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- jquery -->
<script type="text/javascript" src="../js/jquery/jquery-1.7.2.min.js"></script>

<link rel="stylesheet" type="text/css" href="../css/webuploader.css">
<link rel="stylesheet" type="text/css" href="../css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../css/syntax.css">
<link rel="stylesheet" type="text/css" href="../css/styleWebuploader.css">



	
<script type="text/javascript" src="../js/webuploader/webuploader.js"></script>

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

</style>

<script type="text/javascript">
 var selectVal = 1;
 var $list ;
 var uploader;
 $(document).ready(function () {
	 var $ = jQuery,    	
         $btn = $('#ctlBtn'),
         state = 'pending';
	 $list = $('#thelist');	
	 uploader = WebUploader.create({	
	    // swf文件路径
	    swf: '../js/webuploader/Uploader.swf',

	    // 文件接收服务端。
	    server: 'import.do',

	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#picker',
		
	    formData: {
	    	filetype: selectVal
	    },
	  
	   	fileVal:'upfile',
	    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: false
	});
	 
	 uploader.on('beforeFileQueued', function(file){
		 var files = uploader.getFiles();
		 for(var i=0; i<files.length;++i)
		 {
			 uploader.removeFile(files[i],true);
		 }
		 uploader.reset();
		 
	 });
	 
	 uploader.on( 'fileQueued', function( file ) {
		 $list.empty();
		 var files = uploader.getFiles();
		 for(var i=0; i<files.length;++i)
		 {
			 $list.append( '<div id="' + files[i].id + '" class="item">' +
				        '<h4 class="info">' + files[i].name + '</h4>' +
				        '<p class="state">等待上传...</p>' +
				    '</div>' );
		 }
		    
	});
		 		 
	 uploader.on( 'uploadProgress', function( file, percentage ) {
			var $li = $( '#'+file.id ),
		        $percent = $li.find('.progress .progress-bar');

		    // 避免重复创建
		    if ( !$percent.length ) {
		        $percent = $('<div class="progress progress-striped active">' +
		          '<div class="progress-bar" role="progressbar" style="width: 0%">' +
		          '</div>' +
		        '</div>').appendTo( $li ).find('.progress-bar');
		    }

		    $li.find('p.state').text('上传中');

		    $percent.css( 'width', percentage * 100 + '%' );
	});
		 
 	uploader.on( 'uploadSuccess', function( file, ret ) {
 		if (ret.result == "OK"){
	    	$( '#'+file.id ).find('p.state').text('已上传');
 		} else{
 			$( '#'+file.id ).find('p.state').text(ret.result);
 		}
	});

	uploader.on( 'uploadError', function( file ) {
	    $( '#'+file.id ).find('p.state').text('上传出错');
	});

	uploader.on( 'uploadComplete', function( file ) {
	    $( '#'+file.id ).find('.progress').fadeOut();
	});
									
	 $btn.on( 'click', function() {
	        if ( state === 'uploading' ) {
	            uploader.stop();
	        } else {
	        	if(selectVal == 1)
	       		{
	        		$('#docsStatus').css("display", "");
	        		//$('#docsStatus').text("请选择需要导入的文档");
	        		$('#docsStatus')[0].innerHTML = "请从下拉列表中选择需要导入的文档类型！";
	        		
	        	}else {
	        		var files = uploader.getFiles();
	        		
	        		if(files.length == 0)
	        		{
	        			 $('#docsStatus').css("display", "block");
	        			 $('#docsStatus')[0].innerHTML = "请选择要上传的文件！";
	        		}
	        		else{
		        		uploader.options.formData.filetype = selectVal;
			            uploader.upload();
			            $('#docsStatus').css("display", "none");
	        		}

	        	}
	        	
	        }
	 });
	 
	 //Initial the combox
	 //onIndexSelected();
	 selectVal = $('#documenttype').val();
});
 
function onIndexSelected()
{
	 selectVal = $('#documenttype').val();
	 $list.empty();
	 var files = uploader.getFiles();
	 for(var i=0; i<files.length;++i)
	 {
		 uploader.removeFile(files[i],true);
	 }
	 uploader.reset();
}
 
    
</script>
<title>市场部数据导入</title>
</head>
<body>

<div class=" header" align="center">
	<h1>市场部门数据导入</h1>
</div>
</br>

<table id="documentTypeSelector" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td>
		<table>
			<tr>
			<td><span style="font-size:20px;">请选择需要导入的文件类型</span></td>
			<td width="40px"></td>
			<td style="padding-right:5px">
		<select id="documenttype"	onchange="onIndexSelected()" style="width: 125px;">
				<option value="1" selected="selected">无</option>
				<option value="2" >项目信息文档</option>
				<option value="3" >投标台账</option>
				<option value="4" >签约台账</option>	
		</select>
		</table>
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
			    	<td> <button id="ctlBtn" class="btn btn-default">开始上传</button></td>
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
				<div id="docsStatus" style="font-size: 30px; color: red ;font-weight: 400; display:none; ">
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
