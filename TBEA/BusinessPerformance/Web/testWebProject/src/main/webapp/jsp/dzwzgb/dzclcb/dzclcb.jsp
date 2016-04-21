<Table id="dzclcb" align="center" style="display: none;">
    <tr id="tbarea">
        <td>
            <div id="table" align="center"></div>
        </td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/dzwzgb/dzclcb/dzclcb.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.route.router
            .to(plugin.dzclcb)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                host: "dzclcb",
                tbarea:"tbarea"
            });
});
</script>