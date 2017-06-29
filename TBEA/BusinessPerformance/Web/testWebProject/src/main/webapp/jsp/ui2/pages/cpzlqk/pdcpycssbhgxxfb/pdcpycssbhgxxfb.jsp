<div id="pdcpycssbhgxxfb" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
    <div id="ctarea" class="single-chart">
        <div class="well">
			<div id="chart" class="chart pull-left" style="width:50%"></div>
            <div id="pie" class="chart pull-left" style="width:50%"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/cpzlqk/pdcpycssbhgxxfb/pdcpycssbhgxxfb.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(plugin.pdcpycssbhgxxfb)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                ct:"chart",
                ct1:"pie",
                host: "pdcpycssbhgxxfb",
                tbarea:"tbarea",
                ctarea:"ctarea",
    			tableStatus: tableStatus
            });
});
</script>