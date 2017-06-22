<Table id="basic" align="center" style="display: none;">
    <tr id="tbarea">
        <td>
            <div id="table" align="center"></div>
        </td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/basicReport/basic/basic.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(plugin.basic)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                host: "basic",
                tbarea:"tbarea",
                title:"${title}",
                updateUrl:"${updateUrl}.do",
                exportUrl:"${exportUrl}.do"
            });
});
</script>