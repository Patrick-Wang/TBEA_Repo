var dashboard;
(function (dashboard) {
    $(document).ready(function () {
        var option = {
            tooltip: {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                feature: {}
            },
            series: [
                {
                    name: '业务指标',
                    type: 'gauge',
                    min: 0,
                    max: 110,
                    radius: '80%',
                    detail: { formatter: '{value}%' },
                    data: [{ value: 70, name: '应收账款' }]
                }
            ]
        };
        //   $(".block").css("height", document.documentElement.clientHeight / 2 + "px");
        var myChart = echarts.init(document.getElementById("c1"));
        myChart.setOption(option);
        var option1 = {
            tooltip: {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                feature: {}
            },
            series: [
                {
                    name: '存货',
                    type: 'gauge',
                    min: 0,
                    max: 110,
                    radius: '80%',
                    detail: { formatter: '{value}%' },
                    data: [{ value: 20, name: '存货' }]
                }
            ]
        };
        var myChart1 = echarts.init(document.getElementById("c2"));
        myChart1.setOption(option1);
        var option2 = {
            tooltip: {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                feature: {}
            },
            series: [
                {
                    name: '销售收入',
                    type: 'gauge',
                    min: 0,
                    max: 110,
                    radius: '80%',
                    detail: { formatter: '{value}%' },
                    data: [{ value: 89, name: '销售收入' }]
                }
            ]
        };
        var myChart2 = echarts.init(document.getElementById("c3"));
        myChart2.setOption(option2);
        var option3 = {
            tooltip: {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                feature: {}
            },
            series: [
                {
                    name: '利润',
                    type: 'gauge',
                    min: 0,
                    max: 110,
                    radius: '80%',
                    detail: { formatter: '{value}%' },
                    data: [{ value: 50, name: '利润' }]
                }
            ]
        };
        var myChart3 = echarts.init(document.getElementById("c4"));
        myChart3.setOption(option3);
        var option4 = {
            tooltip: {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                feature: {}
            },
            series: [
                {
                    name: '现金流',
                    type: 'gauge',
                    min: 0,
                    max: 110,
                    radius: '80%',
                    detail: { formatter: '{value}%' },
                    data: [{ value: 46, name: '现金流' }]
                }
            ]
        };
        var myChart4 = echarts.init(document.getElementById("c5"));
        myChart4.setOption(option4);
        $(window).resize(function () {
            //  $(".block").css("height", document.documentElement.clientHeight / 2 + "px");
            echarts.init(document.getElementById("c1")).setOption(option);
            echarts.init(document.getElementById("c2")).setOption(option1);
            echarts.init(document.getElementById("c3")).setOption(option2);
            echarts.init(document.getElementById("c4")).setOption(option3);
            echarts.init(document.getElementById("c5")).setOption(option4);
        });
    });
})(dashboard || (dashboard = {}));
