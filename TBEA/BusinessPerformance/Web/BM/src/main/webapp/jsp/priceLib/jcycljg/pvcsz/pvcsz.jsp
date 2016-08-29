<Table id="pvcsz" align="center" style="display: none;">
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
                            <div id="chartDsf" class="panel-content"></div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div align="center">
                        <div class="panel-content-border"
                             style="margin-bottom: 20px; width: 800">
                            <div id="chartYxf" class="panel-content"></div>
                        </div>
                    </div>
                </td>
            </tr>
        </table></td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/priceLib/jcycljg/pvcsz/pvcsz.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    jcycljg.pvcsz.pluginView.init({
        dsf: "chartDsf",
        yxf: "chartYxf",
        tb: "table",
        host: "pvcsz",
        ctarea: "ctarea",
        tbarea: "tbarea"
    });
});
</script>