<Table id="eva" align="center" style="display: none;">
    <tr>
        <td>
            <div align="center">
                <div class="panel-content-border"
                     style="margin-bottom: 20px; width: 1200px">
                    <div id="chart" class="panel-content"></div>
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
<script type="text/javascript" src="jsp/priceLib/jcycljg/eva/eva.js"></script>
<script type="text/javascript">
    jcycljg.eva.pluginView.init({
        ct: "chart",
        tb: "table",
        host: "eva"
    });
</script>