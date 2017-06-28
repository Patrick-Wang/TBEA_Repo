<div id="ysjs" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
    <div id="ctarea">
        <div class="well">
			<div id="chartCu" style="height:300px"></div>
		</div>
		<div class="well">
			<div id="chartZn" style="height:300px"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/priceLib/jcycljg/ysjs/ysjs.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    jcycljg.ysjs.pluginView.init({
        cu: "chartCu",
        al: "chartAl",
        zn: "chartZn",
        tb: "table",
        host: "ysjs",
        ctarea: "ctarea",
        tbarea: "tbarea"
    });
});
</script>