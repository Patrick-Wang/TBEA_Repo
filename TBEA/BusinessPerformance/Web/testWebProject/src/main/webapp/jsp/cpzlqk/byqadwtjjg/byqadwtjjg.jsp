<Table id="byqadwtjjg" align="center" style="display: none;">
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
                    <div id="chart" class="panel-content"></div>
                </div>
            </div>
        </td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/cpzlqk/byqadwtjjg/byqadwtjjg.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(plugin.byqadwtjjg)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                ct:"chart",
                ctarea:"ctarea",
                host: "byqadwtjjg",
                tbarea:"tbarea",
                tableStatus: tableStatus
            });
});
</script>