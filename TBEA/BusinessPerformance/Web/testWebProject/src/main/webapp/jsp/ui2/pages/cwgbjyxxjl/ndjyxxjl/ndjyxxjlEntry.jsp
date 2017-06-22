<Table id="ndjyxxjlEntry" align="center" style="display: none;">
    <tr id="tbarea">
        <td>
            <div id="table" align="center"></div>
        </td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/cwgbjyxxjl/ndjyxxjl/ndjyxxjlEntry.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(pluginEntry.ndjyxxjl)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                host: "ndjyxxjlEntry",
                tbarea:"tbarea"
            });
});
</script> 
