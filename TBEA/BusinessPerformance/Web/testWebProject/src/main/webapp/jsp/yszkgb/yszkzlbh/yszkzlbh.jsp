<Table id="yszkzlbh" align="center" style="display: none;">
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
                    <div id="chart" class="panel-content"></div>
                </div>
            </div>
        </td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/yszkgb/yszkzlbh/yszkzlbh.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    yszkgb.yszkzlbh.pluginView.init({
        tb: "table",
        host: "yszkzlbh",
        tbarea:"tbarea" ,
        ctarea:"ctarea",
        ct:"chart"
    });
});
</script>