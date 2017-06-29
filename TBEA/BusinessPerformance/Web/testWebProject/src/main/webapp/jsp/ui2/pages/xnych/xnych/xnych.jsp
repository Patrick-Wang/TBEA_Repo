<div id="xnych" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
			<div id="prompt"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/xnych/xnych/xnych.js"></script>
<script type="text/javascript">
$(document).ready(function(){
       framework.router
               .to(plugin.xnych)
               .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                   tb: "table",
                   host: "xnych",
                   tbarea:"tbarea",
               });
});
</script>