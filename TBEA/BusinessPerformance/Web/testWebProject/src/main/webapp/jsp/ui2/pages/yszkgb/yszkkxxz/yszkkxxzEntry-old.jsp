<div id="yszkkxxzEntry" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/yszkgb/yszkkxxz/yszkkxxzEntry.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router.to("yszkkxxzEntry").send(Util.MSG_INIT, {
    	 tb: "table",
         host: "yszkkxxzEntry",
         tbarea:"tbarea"
	});
});
</script>
