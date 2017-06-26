<div id="chxzqk" class="sub-view">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/chgb/chxzqk/chxzqk.js"></script>
<script type="text/javascript">
$(document).ready(function(){
       framework.router
               .to(plugin.chxzqk)
               .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                   tb: "table",
                   host: "chxzqk",
                   tbarea:"tbarea",
                   ctarea:"ctarea",
                   ct:"chart"
               });
});
</script>