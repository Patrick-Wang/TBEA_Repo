<div id="ggp" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
    <div id="ctarea" class="multi-chart">
        <div class="well">
			<div id="chartWg" class="chart"></div>
		</div>
		<div class="well">
			<div id="chartBg" class="chart"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/priceLib/jcycljg/ggp/ggp.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    jcycljg.ggp.pluginView.init({
        wg: "chartWg",
        bg: "chartBg",
        tb: "table",
        host: "ggp",
        ctarea: "ctarea",
        tbarea: "tbarea"
    });
});
</script>