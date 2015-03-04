(function(){
     $("input:button,input:submit,input:reset").button();  
     $("input")
        .button( "option", "icons", {primary:'ui-icon-cancel',secondary:'ui-icon-cancel'} )
        .css("height", "23px")
        .css("padding", ".1em 1em")
        .css("margin-top", "-1px");   
}());