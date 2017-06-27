<div id="xfscqy" class="sub-view">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/sbdscqyqk/xfscqy/xfscqy.js"></script>
<script type="text/javascript">
$(document).ready(function(){
       framework.router
               .to(plugin.xfscqy)
               .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                   tb: "table",
                   host: "xfscqy",
                   tbarea:"tbarea",
                   ctarea:"ctarea",
                   ct:"chart"
               });
});
</script>