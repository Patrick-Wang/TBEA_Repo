<Table id="byqcpycssbhgwtmxApprove" align="center" style="display: none;">
    <tr>
        <td>
        <div id="tips" style="font-size: 18px;font-weight: bold"></div>
        </td>
    </tr>
    <tr id="tbarea">
        <td>
            <div id="table" align="center"></div>
        </td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/cpzlqk/byqcpycssbhgwtmx/byqcpycssbhgwtmxApprove.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(pluginApprove.byqcpycssbhgwtmx)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                host: "byqcpycssbhgwtmxApprove",
                tbarea:"tbarea",
                tips:"tips"
            });
});
</script> 
