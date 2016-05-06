<div id="yjsfljs" style="display: none;">
    <Table align="center">
        <tr>
            <td>
                <div id="table" align="center"></div>
            </td>
        </tr>
    </Table>
    <Table align="center">
        <tr>
            <td>
                <div id="table1" align="center"></div>
            </td>
        </tr>
    </Table>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/cwyjsf/yjsfljs/yjsfljs.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(plugin.yjsfljs)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                tb1: "table1",
                host: "yjsfljs"
            });
});
</script>