<Table id="ggp" align="center" style="display: none;">
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
                             style="margin-bottom: 20px; width: 1200px">
                            <div id="chartWg" class="panel-content"></div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div align="center">
                        <div class="panel-content-border"
                             style="margin-bottom: 20px; width: 1200px">
                            <div id="chartBg" class="panel-content"></div>
                        </div>
                    </div>
                </td>

            </tr>
        </table></td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/priceLib/jcycljg/ggp/ggp.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    jcycljg.ggp.pluginView.init({
        wg: "chartWg",
        bg: "chartBg",
        tb: "table",
        host: "ggp",
        ctarea: "ctarea",
        tbarea: "tbarea"
    });
});
</script>