<Table id="byqacptjjgApprove" align="center" style="display: none;">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/cpzlqk/byqacptjjg/byqacptjjgApprove.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(pluginApprove.byqacptjjg)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                host: "byqacptjjgApprove",
                tbarea:"tbarea",
                tips:"tips"
            });
});
</script> 