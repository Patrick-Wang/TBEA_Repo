<div id="yjsfljs" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
    <div id="tbarea">
       <div class="well">
			<div id="table1"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/cwyjsf/yjsfljs/yjsfljs.js"></script>
<script type="text/javascript">
$(document).ready(function(){
       framework.router
               .to(plugin.yjsfljs)
               .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
            	   tb: "table",
                   tb1: "table1",
                   host: "yjsfljs"
               });
});
</script>