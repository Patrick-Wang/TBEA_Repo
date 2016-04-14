<Table id="chnych" align="center" style="display: none;">
    <tr id="tbarea">
        <td>
            <div id="table" align="center"></div>
        </td>
    </tr>
</Table>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/chgb/chnych/chnych.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    chgb.chnych.pluginView.init({
        tb: "table",
        host: "chnych",
        tbarea:"tbarea" 
    });
});
</script>