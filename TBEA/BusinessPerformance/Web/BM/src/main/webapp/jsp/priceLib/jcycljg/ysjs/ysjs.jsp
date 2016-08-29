<Table id="ysjs" align="center" style="display: none;">
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
                            <div id="chartCu" class="panel-content"></div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div align="center">
                        <div class="panel-content-border"
                             style="margin-bottom: 20px; width: 800">
                            <div id="chartAl" class="panel-content"></div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div align="center">
                        <div class="panel-content-border"
                             style="margin-bottom: 20px; width: 800">
                            <div id="chartZn" class="panel-content"></div>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/priceLib/jcycljg/ysjs/ysjs.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    jcycljg.ysjs.pluginView.init({
        cu: "chartCu",
        al: "chartAl",
        zn: "chartZn",
        tb: "table",
        host: "ysjs",
        ctarea: "ctarea",
        tbarea: "tbarea"
    });
});
</script>