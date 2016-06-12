function ncGet(url, date, suc){
	 $.ajax({
                type: "GET",
                url: url + "?date=" + date,
                success: suc,
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alter("failed");
                }
            });

}

function nextMonth(d){
	var dt;
	if (d.getMonth() == 11){
		dt = d.getFullYear() + 1 + "-1";
	}else{
		dt = d.getFullYear() + "-" + (d.getMonth() + 2);
	}
	dt = dt + "-" + d.getDate();
	return dt;
}

function run (url, ds, de){
	console.log("run(" + url + ", '" + ds + "', '" + de + "')");
	var des = Date.parse(de);
	ncGet(url, ds, function(){
		var dnext = nextMonth(new Date(Date.parse(ds)));
		setTimeout(function(){
			if (Date.parse(dnext) <= des ){
				run(url, dnext,	de); 
			}
		}, 10);
	})
} 

run("../yszkgb/nctest.do", "2012-1-1", "2016-5-1");
run("../cwyjsf/nctest.do", "2012-1-1", "2016-5-1");
run("../chgb/nctest.do", "2012-1-1", "2016-5-1");
run("../wgcpqk/nctest.do", "2012-1-1", "2016-5-1");
run("../cbfx/nctest.do", "2012-1-1", "2016-5-1");
run("../cwcpdlml/nctest.do", "2012-1-1", "2016-5-1");
run("../cwgbjyxxjl/nctest.do", "2012-1-1", "2016-5-1");