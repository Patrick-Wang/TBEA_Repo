<div id="sddb" class="sub-view">
   <div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
     <div id="ctarea">

    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/sddb/sddb/sddb.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(plugin.sddb)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                host: "sddb",
                tbarea:"tbarea",
                ctarea:"ctarea",
                ct:"chart",
                title:"${title}",
                updateUrl:"${updateUrl}.do",
                exportUrl:"${exportUrl}.do"
            });
});
</script>