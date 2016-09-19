<Table id="byqcpycssbhgxxfb" align="center" style="display: none;">
    <tr id="tbarea">
        <td>
            <div id="table" align="center"></div>
        </td>
    </tr>
    <tr id="ctarea" style="display: none;">
        <td>
            <div align="center">
                <div class="panel-content-border"
                     style="margin-bottom: 20px; width: 1200px">
                    <div id="chart" class="panel-content" style="float:left"></div>
                    <div id="pie" class="panel-content" style="float:left"></div>
                </div>
            </div>
        </td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/cpzlqk/byqcpycssbhgxxfb/byqcpycssbhgxxfb.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(plugin.byqcpycssbhgxxfb)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                ct:"chart",
                ct1:"pie",
                host: "byqcpycssbhgxxfb",
                tbarea:"tbarea",
                ctarea:"ctarea",
                tableStatus: tableStatus
            });
});
</script>