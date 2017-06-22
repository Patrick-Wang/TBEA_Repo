<Table id="dyjyxxjlEntry" align="center" style="display: none;">
    <tr id="tbarea">
        <td>
            <div id="table" align="center"></div>
        </td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/cwgbjyxxjl/dyjyxxjl/dyjyxxjlEntry.js"></script>
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
