<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="xksczzzlwt" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
        <div id="ctarea" class="single-chart">
	        <div class="well pull-left" style="position:static">
				<div id="chart" class="chart"></div>
			</div>
	    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/nwbzlqk/xksczzzlwt/xksczzzlwt.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(plugin.xksczzzlwt)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                ct:"chart",
                ctarea:"ctarea",
                host: "xksczzzlwt",
                tbarea:"tbarea"
            });
});
</script>