<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="byqnbzlwtfl" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
        <div id="ctarea" class="single-chart">
        <div class="well pull-left" style="position:static">
			<div id="chart" class="chart"></div>
		</div>
		<div class="well pull-right" style="position:static">
            <div id="pie" class="chart"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/nwbzlqk/byqnbzlwtfl/byqnbzlwtfl.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(plugin.byqnbzlwtfl)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                ct:"chart",
                ct1:"pie",
                ctarea:"ctarea",
                host: "byqnbzlwtfl",
                tbarea:"tbarea"
            });
});
</script>