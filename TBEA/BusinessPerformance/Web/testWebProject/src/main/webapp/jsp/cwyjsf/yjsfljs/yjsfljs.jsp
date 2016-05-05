<Table id="yjsfljs" align="center" style="display: none;">
    <tr id="tbarea">
        <td>
            <div id="table" align="center"></div>
        </td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/cwyjsf/yjsfljs/yjsfljs.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(plugin.yjsfljs)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                host: "yjsfljs",
                tbarea:"tbarea"
            });
});
</script>