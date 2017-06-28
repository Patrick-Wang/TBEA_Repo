<div id="pvcsz" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
    <div id="ctarea" class="multi-chart">
        <div class="well">
			<div id="chartDsf"  class="chart"></div>
		</div>
		<div class="well">
			<div id="chartYxf"  class="chart"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/priceLib/jcycljg/pvcsz/pvcsz.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    jcycljg.pvcsz.pluginView.init({
        dsf: "chartDsf",
        yxf: "chartYxf",
        tb: "table",
        host: "pvcsz",
        ctarea: "ctarea",
        tbarea: "tbarea"
    });
});
</script>