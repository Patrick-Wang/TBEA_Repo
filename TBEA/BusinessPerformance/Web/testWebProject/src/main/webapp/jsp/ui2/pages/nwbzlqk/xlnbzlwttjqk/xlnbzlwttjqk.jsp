<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="xlnbzlwttjqk" class="sub-view">
	<div id="tbarea">
       <div class="well">
			<div id="table"></div>
		</div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/ui2/pages/nwbzlqk/xlnbzlwttjqk/xlnbzlwttjqk.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(plugin.xlnbzlwttjqk)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                ct:"chart",
                ct1:"pie",
                ctarea:"ctarea",
                host: "xlnbzlwttjqk",
                tbarea:"tbarea"
            });
});
</script>