<div id="xkacptjjg" class="sub-view">
	<div id="tbarea">
		<div class="well">
			<div id="table"></div>
		</div>
	</div>
	<div id="ctarea" class="single-chart">
		<div class="well">
			<div id="chart" class="chart"></div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/ui2/pages/cpzlqk/xkacptjjg/xkacptjjg.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(plugin.xkacptjjg)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                ct:"chart",
                ctarea:"ctarea",
                host: "xkacptjjg",
                tbarea:"tbarea"
            });
});
</script>