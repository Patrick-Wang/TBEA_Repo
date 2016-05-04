<Table id="template" align="center" style="display: none;">
    <tr id="tbarea">
        <td>
            <div id="table" align="center"></div>
        </td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/#FRAME#/template/template.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(plugin.template)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                host: "template",
                tbarea:"tbarea"
            });
});
</script>