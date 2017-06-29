<div id="byqcpycssbhgwtmxApprove" class="sub-view">
	<div id="tbarea">
       <div class="well">
       		<span id="tips" style="font-size: 18px;font-weight: bold"></span>
			<div id="table"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/cpzlqk/byqcpycssbhgwtmx/byqcpycssbhgwtmxApprove.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(pluginApprove.byqcpycssbhgwtmx)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                host: "byqcpycssbhgwtmxApprove",
                tbarea:"tbarea",
                tips:"tips"
            });
});
</script> 
