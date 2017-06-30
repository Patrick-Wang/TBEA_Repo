<div id="dyjyxxjlEntry" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/cwgbjyxxjl/dyjyxxjl/dyjyxxjlEntry.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(pluginEntry.dyjyxxjl)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                host: "dyjyxxjlEntry",
                tbarea:"tbarea"
            });
});
</script>