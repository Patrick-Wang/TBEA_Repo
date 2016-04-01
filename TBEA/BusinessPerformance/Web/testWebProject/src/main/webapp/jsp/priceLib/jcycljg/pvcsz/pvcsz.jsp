<Table id = "pvcsz" align="center" style="display: none;">
    <tr>
        <td>
            <div align="center">
                <div class="panel-content-border"
                     style="margin-bottom: 20px; width: 1200px">
                    <div id="chartDsf" class="panel-content"></div>
                </div>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <div align="center"> 
                <div class="panel-content-border"
                     style="margin-bottom: 20px; width: 1200px">
                    <div id="chartYxf" class="panel-content"></div>
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
<script type="text/javascript" src="jsp/priceLib/jcycljg/pvcsz/pvcsz.js"></script>
<script type="text/javascript">
    jcycljg.pvcsz.pluginView.init({
        dsf: "chartDsf",
        yxf: "chartYxf",
        tb: "table",
        host: "pvcsz"
    });
</script>