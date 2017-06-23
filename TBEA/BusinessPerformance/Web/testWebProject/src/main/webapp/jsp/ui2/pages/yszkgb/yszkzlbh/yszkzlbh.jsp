<div id="yszkzlbh" class="sub-view">
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
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/yszkgb/yszkzlbh/yszkzlbh.js"></script>
<script src="${pageContext.request.contextPath}/jsp/www2/js/echarts-plain-2-0-0.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router.to("yszkzlbh").send(Util.MSG_INIT, {
    	tb: "table",
        host: "yszkzlbh",
        tbarea:"tbarea" ,
        ctarea:"ctarea",
        ct:"chart"
	});
});
</script>