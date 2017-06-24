<div id="yqyszcsysEntry" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/yszkgb/yqyszcsys/yqyszcsysEntry.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router.to("yqyszcsysEntry").send(Util.MSG_INIT, {
        tb: "table",
        host: "yqyszcsysEntry",
        tbarea:"tbarea"
	});
});
</script>
