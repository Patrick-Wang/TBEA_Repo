<div id="cwsf" class="sub-view">
	<div id="tbarea">
		<div class="well">
			<div id="table"></div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/ui2/pages/cwsf/cwsf/cwsf.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(plugin.cwsf)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                host: "cwsf",
                tbarea:"tbarea",
                title:"${title}",
                updateUrl:"${updateUrl}.do",
                exportUrl:"${exportUrl}.do"
            });
});
</script>