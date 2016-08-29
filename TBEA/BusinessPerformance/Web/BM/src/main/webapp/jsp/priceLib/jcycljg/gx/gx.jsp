<Table id="gx" align="center" style="display: none;">
    <tr id="tbarea">
        <td>
            <div id="table" align="center"></div>
        </td>
    </tr>
    <tr id="ctarea">
        <td>
            <div align="center">
                <div class="panel-content-border"
                     style="margin-bottom: 20px; width: 800">
                    <div id="chart" class="panel-content"></div>
                </div>
            </div>
        </td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/priceLib/jcycljg/gx/gx.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    jcycljg.gx.pluginView.init({
        ct: "chart",
        tb: "table",
        host: "gx",
        ctarea: "ctarea",
        tbarea: "tbarea"
    });
});
</script>