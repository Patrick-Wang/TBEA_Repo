<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<Table id="pdnbzlwtfl" align="center" style="display: none;">
    <tr id="tbarea">
        <td>
            <div id="table" align="center"></div>
        </td>
    </tr>
    <tr id="ctarea" style="display: none;">
        <td>
            <div align="center">
                <div class="panel-content-border"
                     style="margin-bottom: 10px; width: 1200px">
                    <div id="chart" class="panel-content" style="float:left"></div>
                    <div id="pie" class="panel-content" style="float:left"></div>
                </div>
            </div>
        </td>
    </tr>

</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/nwbzlqk/pdnbzlwtfl/pdnbzlwtfl.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    framework.router
            .to(plugin.pdnbzlwtfl)
            .send(framework.basic.FrameEvent.FE_INIT_EVENT,{
                tb: "table",
                ct:"chart",
                ct1:"pie",
                ctarea:"ctarea",
                host: "pdnbzlwtfl",
                tbarea:"tbarea"
            });
});
</script>