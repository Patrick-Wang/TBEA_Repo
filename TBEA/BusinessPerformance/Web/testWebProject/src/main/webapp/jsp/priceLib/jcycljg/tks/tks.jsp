<Table id = "tks" align="center" style="display: none;">
    <tr>
        <td>
            <div align="center">
                <div class="panel-content-border"
                     style="margin-bottom: 20px; width: 1200px">
                    <div id="chartGc" class="panel-content"></div>
                </div> 
            </div>
        </td> 
    </tr>
    <tr> 
        <td>
            <div align="center">
                <div class="panel-content-border"
                     style="margin-bottom: 20px; width: 1200px">
                    <div id="chartJk" class="panel-content"></div>
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
<script type="text/javascript" src="jsp/priceLib/jcycljg/tks/tks.js"></script>
<script type="text/javascript">
    jcycljg.tks.pluginView.init({
        gc: "chartGc",
        jk: "chartJk",
        tb: "table",
        host: "tks"
    });
</script>