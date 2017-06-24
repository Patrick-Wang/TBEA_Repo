<div id="yszkyjtztjqs" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
    <div id="ctarea">
        <div class="well">
			<div id="chart" style="height:300px"></div>
		</div>
    </div>
    <div id="ctarea1">
        <div class="well">
			<div id="chart1" style="height:300px"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/yszkgb/yszkyjtztjqs/yszkyjtztjqs.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router.to("yszkyjtztjqs").send(Util.MSG_INIT, {
    	tb: "table",
        host: "yszkyjtztjqs",
        tbarea:"tbarea",
        ctarea:"ctarea",
        ct:"chart",
        ctarea1:"ctarea1",
        ct1:"chart1"
	});
});
</script>