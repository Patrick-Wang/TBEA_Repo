<Table id="yszkyjtztjqs" align="center" style="display: none;">
	<tr id="tbarea">
		<td>
			<div id="table" align="center"></div>
		</td>
	</tr>
	<tr id="ctarea" style="display: none;">
		<td>
			<div align="center">
				<div class="panel-content-border"
					style="margin-bottom: 10px; width: 1200px">
					<div id="chart" class="panel-content"></div>
				</div>
			</div>
		</td>
	</tr>
	<tr id="ctarea1" style="display: none;">
		<td>
			<div align="center">
				<div class="panel-content-border"
					style="margin-bottom: 10px; width: 1200px">
					<div id="chart1" class="panel-content"></div>
				</div>
			</div>
		</td>
	</tr>
</Table>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/yszkgb/yszkyjtztjqs/yszkyjtztjqs.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    yszkgb.yszkyjtztjqs.pluginView.init({
        tb: "table",
        host: "yszkyjtztjqs",
        tbarea:"tbarea",
        ctarea:"ctarea",
        ct:"chart",
        ctarea1:"ctarea1",
        ct1:"chart1"
    });
});
</script>