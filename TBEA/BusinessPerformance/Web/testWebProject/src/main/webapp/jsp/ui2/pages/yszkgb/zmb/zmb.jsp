<div id="zmb" class="sub-view">
	<div id="zmbTbarea">
		<div class="well">
			<div id="table"></div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/yszkgb/zmb/zmb.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router.to("zmb").send(Util.MSG_INIT, {
    	tb: "table",
        host: "zmb",
        tbarea:"tbarea"
	});
});
</script>