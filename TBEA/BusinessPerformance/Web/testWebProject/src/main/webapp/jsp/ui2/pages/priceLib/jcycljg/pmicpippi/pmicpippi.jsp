<div id="pmicpippi" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
    <div id="ctarea" class="multi-chart">
        <div class="well">
			<div id="pmi" class="chart"></div>
		</div>
		<div class="well">
			<div id="cpi" class="chart"></div>
		</div>
		<div class="well">
			<div id="ppi" class="chart"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/priceLib/jcycljg/pmicpippi/pmicpippi.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    jcycljg.pmicpippi.pluginView.init({
        pmi: "pmi",
        cpi: "cpi",
        ppi: "ppi",
        tb: "table",
        host: "pmicpippi",
        ctarea: "ctarea",
        tbarea: "tbarea"
    });
});
</script>