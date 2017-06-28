<div id="lwg" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
    <div id="ctarea" class="multi-chart">
        <div class="well">
			<div id="chart1214" class="chart"></div>
		</div>
		<div class="well">
			<div id="chart1625" class="chart"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/priceLib/jcycljg/lwg/lwg.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    jcycljg.lwg.pluginView.init({
        ct1214: "chart1214",
        ct1625: "chart1625",
        tb: "table",
        host: "lwg",
        ctarea: "ctarea",
        tbarea: "tbarea"
    });
});
</script>