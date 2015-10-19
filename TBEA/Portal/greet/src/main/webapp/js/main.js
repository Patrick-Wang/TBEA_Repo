$(document).ready(function() {


	$('.new_page').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover').slideDown(200);
	});
//	$('.Personal_Change').click(function(){
//		$('.theme-popover-mask').fadeIn(100);
//		$('.theme-popover1').slideDown(200);
//	})
	$('.password_Change').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover2').slideDown(200);
	});
	$('.login_list_bd').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover3').slideDown(200);
	});
	$('.password_Change1').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover4').slideDown(200);
	});
	$('.password_Change2').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover5').slideDown(200);
	});
	$('.theme-poptit .close').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover,.theme-popover1,.theme-popover2,.theme-popover3,.theme-popover4,.theme-popover5').slideUp(200);
	});

//评论回复
$('.hf').click(function(){
		$(this).parent(".right").parent(".info_fw").next(".info_pl_from").toggle();
})

tab_fun(".mk_tab_1",".mk_tab_main","a","active","div");
tab_fun1(".my_work_img_list",".my_work_img","a","active","a");
tab_fun1(".my_work_img_text_ul_1",".my_work_img","li","active","a");

//tab 方法		
function tab_fun(tab_title,tab_main,title_dom,classvalue,main_dom){
	var tab_title=tab_title; //tab—外边框元素
	var $tab_main=tab_main;  //tab内容-外边框元素
	var $title_dom= title_dom; //tab内容元素 dom a li 等
	var $classvalue=classvalue; //tab内容元素a li 等添加的class
	var $main_dom=main_dom; //tab内容元素dom
	var $tab_fun =$(tab_title+" >"+ $title_dom);
	    $tab_fun.hover(function(){
			$(this).addClass(classvalue);
			$(this).siblings($title_dom).removeClass(classvalue);  
            var tab_fun_index=  $tab_fun.index(this);  
		    $($tab_main + " > " + $main_dom).eq(tab_fun_index).show().siblings($main_dom).hide();
			}) 
}

function tab_fun1(tab_title,tab_main,title_dom,classvalue,main_dom){
	var tab_title=tab_title; //tab—外边框元素
	var $tab_main=tab_main;  //tab内容-外边框元素
	var $title_dom= title_dom; //tab内容元素 dom a li 等
	var $classvalue=classvalue; //tab内容元素a li 等添加的class
	var $main_dom=main_dom; //tab内容元素dom
	var $tab_fun =$(tab_title+" >"+ $title_dom);
	    $tab_fun.hover(function(){
			$(this).addClass(classvalue);
			$(this).siblings($title_dom).removeClass(classvalue);  
            var tab_fun_index=  $tab_fun.index(this);  
		    $($tab_main + " > " + $main_dom).eq(tab_fun_index).show().siblings($main_dom).hide();
		    $(".my_work_img_list > a").eq(tab_fun_index).addClass("active").siblings("a").removeClass("active");  
			$(".my_work_img_text_ul_1 > li").eq(tab_fun_index).addClass("active").siblings("li").removeClass("active");  
			}) 
}



function add_class(add_div,add_class){
	var add_div=add_div;
	var add_class=add_class;
	$(add_div).hover(function(){
			$(this).addClass(add_class);
			},function(){
			$(this).removeClass(add_class);
			}) 
	}	
//addClass_solid
function addClass_solid(add_div,add_class){
	var add_div=add_div;
	var add_class=add_class;
	$(add_div).hover(function(){
			$(this).addClass(add_class).siblings().removeClass(add_class);
			},function(){
			
			}) 
	
	}	
	


function index_pc(index_pc_div,index_pc_div_class){
	var index_pc_div=index_pc_div;
	var index_pc_div_class=index_pc_div_class;
	$(index_pc_div).hover(function(){
			$(this).siblings("li").removeClass("active");
			$(this).addClass("active");
			$(index_pc_div_class).addClass("active");
			
			},function(){
			$(this).siblings("li").removeClass("active");
			$(this).removeClass("active");
			}) 
	
	}	




	

});


















