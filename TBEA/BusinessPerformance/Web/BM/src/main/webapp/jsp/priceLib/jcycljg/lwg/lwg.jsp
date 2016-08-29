<Table id="lwg" align="center" style="display: none;">
    <tr id="tbarea">
        <td>
            <div id="table" align="center"></div>
        </td>
    </tr>
    <tr id="ctarea"><td>
        <table>
            <tr>
                <td>
                    <div align="center">
                        <div class="panel-content-border"
                             style="margin-bottom: 20px; width: 800">
                            <div id="chart1214" class="panel-content"></div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div align="center">
                        <div class="panel-content-border"
                             style="margin-bottom: 20px; width: 800">
                            <div id="chart1625" class="panel-content"></div>
                        </div>
                    </div>
                </td>
            </tr>
        </table></td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/priceLib/jcycljg/lwg/lwg.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    jcycljg.lwg.pluginView.init({
        ct1214: "chart1214",
        ct1625: "chart1625",
        tb: "table",
        host: "lwg",
        ctarea: "ctarea",
        tbarea: "tbarea"
    });
});
</script>