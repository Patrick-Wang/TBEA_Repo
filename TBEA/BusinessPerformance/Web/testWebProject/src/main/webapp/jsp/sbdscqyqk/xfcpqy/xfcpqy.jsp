<Table id="xfcpqy" align="center" style="display: none;">
    <tr id="tbarea">
        <td>
            <div id="table" align="center"></div>
        </td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/sbdscqyqk/xfcpqy/xfcpqy.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(plugin.xfcpqy)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                host: "xfcpqy",
                tbarea:"tbarea"
            });
});
</script>