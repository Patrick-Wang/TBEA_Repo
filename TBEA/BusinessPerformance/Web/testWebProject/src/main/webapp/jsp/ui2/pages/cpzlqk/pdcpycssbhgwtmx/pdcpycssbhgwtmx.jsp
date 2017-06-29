<div id="pdcpycssbhgwtmx" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/cpzlqk/pdcpycssbhgwtmx/pdcpycssbhgwtmx.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(plugin.pdcpycssbhgwtmx)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                host: "pdcpycssbhgwtmx",
                tbarea:"tbarea",
    			tableStatus: tableStatus
            });
});
</script>