<div id="xlbhgcpmxEntry" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/cpzlqk/xlbhgcpmx/xlbhgcpmxEntry.js?ver=1.0"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(pluginEntry.xlbhgcpmx)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                host: "xlbhgcpmxEntry",
                tbarea:"tbarea"
            });
});
</script> 
