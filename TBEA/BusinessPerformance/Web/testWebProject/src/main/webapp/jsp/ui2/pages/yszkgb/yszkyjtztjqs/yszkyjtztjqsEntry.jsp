<div id="yszkyjtztjqsEntry" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/yszkgb/yszkyjtztjqs/yszkyjtztjqsEntry.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router.to("yszkyjtztjqs").send(Util.MSG_INIT, {
    	 tb: "table",
         host: "yszkyjtztjqsEntry",
         tbarea:"tbarea"
	});
});
</script>
