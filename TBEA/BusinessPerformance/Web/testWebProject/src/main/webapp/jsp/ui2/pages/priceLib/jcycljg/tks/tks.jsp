<div id="tks" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
    <div id="ctarea" class="multi-chart">
        <div class="well">
			<div id="chartGc" class="chart"></div>
		</div>
		<div class="well">
			<div id="chartJk" class="chart"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/priceLib/jcycljg/tks/tks.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    jcycljg.tks.pluginView.init({
        gc: "chartGc",
        jk: "chartJk",
        tb: "table",
        host: "tks",
        ctarea: "ctarea",
        tbarea: "tbarea"
    });
});
</script>