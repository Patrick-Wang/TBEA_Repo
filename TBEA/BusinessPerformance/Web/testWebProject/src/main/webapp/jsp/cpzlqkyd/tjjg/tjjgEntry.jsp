<Table id="tjjgEntry" align="center" style="display: none;">
    <tr id="tbarea">
        <td>
            <div id="table" align="center"></div>
        </td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/cpzlqkyd/tjjg/tjjgEntry.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(pluginEntry.tjjg)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                host: "tjjgEntry",
                tbarea:"tbarea"
            });
});
</script> 
