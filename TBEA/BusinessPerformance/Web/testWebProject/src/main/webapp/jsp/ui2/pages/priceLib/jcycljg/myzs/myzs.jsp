<div id="myzs" class="sub-view">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/priceLib/jcycljg/myzs/myzs.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    jcycljg.myzs.pluginView.init({
        ct: "chart",
        tb: "table",
        host: "myzs",
        ctarea: "ctarea",
        tbarea: "tbarea"
    });
});
</script>