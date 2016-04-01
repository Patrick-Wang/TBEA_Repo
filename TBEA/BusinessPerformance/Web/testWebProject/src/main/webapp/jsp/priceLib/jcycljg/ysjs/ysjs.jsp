<Table id = "ysjs" align="center" style="display: none;">
    <tr>
        <td>
            <div align="center">
                <div class="panel-content-border"
                     style="margin-bottom: 20px; width: 1200px">
                    <div id="chartCu" class="panel-content"></div>
                </div>
            </div>
        </td>
    </tr>
    <tr>
        <td> 
            <div align="center">
                <div class="panel-content-border"
                     style="margin-bottom: 20px; width: 1200px">
                    <div id="chartAl" class="panel-content"></div>
                </div>
            </div>
        </td> 
    </tr>
    <tr>
        <td> 
            <div align="center">
                <div class="panel-content-border"
                     style="margin-bottom: 20px; width: 1200px">
                    <div id="chartZn" class="panel-content"></div>
                </div>
            </div>
        </td> 
    </tr>
    <tr> 
        <td>
            <div id="table" align="center"></div>
        </td>
    </tr>
</Table>
<script type="text/javascript" src="jsp/priceLib/jcycljg/ysjs/ysjs.js"></script>
<script type="text/javascript">
    jcycljg.ysjs.pluginView.init({
        cu: "chartCu",
        al: "chartAl",
        zn: "chartZn",
        tb: "table",
        host: "ysjs"
    });
</script>