<Table id="xnyzb" align="center" style="display: none;">
    <tr id="tbarea">
        <td>
            <div id="table" align="center"></div>
        </td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/sddb/sddb/sddb.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(plugin.sddb)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                host: "xnyzb",
                tbarea:"tbarea",
                title:"${title}",
                updateUrl:"${updateUrl}.do",
                exportUrl:"${exportUrl}.do"
            });
});
</script>