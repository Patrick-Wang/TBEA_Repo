<Table id="pmicpippi" align="center" style="display: none;">
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
                            <div id="pmi" class="panel-content"></div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div align="center">
                        <div class="panel-content-border"
                             style="margin-bottom: 20px; width: 1200px">
                            <div id="cpi" class="panel-content"></div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div align="center">
                        <div class="panel-content-border"
                             style="margin-bottom: 20px; width: 1200px">
                            <div id="ppi" class="panel-content"></div>
                        </div>
                    </div>
                </td>
            </tr>
        </table></td>
    </tr>
</Table>
<script type="text/javascript" src="jsp/priceLib/jcycljg/pmicpippi/pmicpippi.js"></script>
<script type="text/javascript">
    jcycljg.pmicpippi.pluginView.init({
        pmi: "pmi",
        cpi: "cpi",
        ppi: "ppi",
        tb: "table",
        host: "pmicpippi",
        ctarea: "ctarea",
        tbarea: "tbarea"
    });
</script>