<div id="xnyzbEntry" class="sub-view">
	<div id="tbarea">
		<div class="well">
			<div id="table"></div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/ui2/pages/xnyzb/xnyzb/xnyzbEntry.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(pluginEntry.xnyzb)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                host: "xnyzbEntry",
                tbarea:"tbarea",
                title:"${title}",
                updateUrl:"${updateUrl}.do",
                submitUrl:"${submitUrl}.do"
            });
});
</script>
