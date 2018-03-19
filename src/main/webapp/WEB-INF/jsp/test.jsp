<%--
  Created by IntelliJ IDEA.
  User: Wenqiang Luo
  Date: 2018/3/14
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"><link rel="icon" href="https://static.jianshukeji.com/highcharts/images/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        /* css 代码  */
    </style>
    <script src="https://img.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
    <script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>
    <script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
    <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
    <script src="https://img.hcharts.cn/highcharts/themes/dark-unica.js"></script>

</head>
<body>
<div id="container" style="min-width:400px;height:400px"></div>
<button id="serverOn">开启服务端</button>
<button id="serverOff">关闭服务端</button>
<script type="text/javascript">
    $("#serverOn").click(function(){
        window.location.href = "../StartServer";
    })
    $("#serverOff").click(function(){
        window.location.href = "../ShutDown";
    })
</script>
<script>
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
    function activeLastPointToolip(chart) {
        var points = chart.series[0].points;
        chart.tooltip.refresh(points[points.length -1]);
    }
    $('#container').highcharts({
        chart: {
            type: 'spline',
            animation: Highcharts.svg, // don't animate in old IE
            marginRight: 10,
            events: {
                load: function () {
                    // set up the updating of the chart each second
                    var series = this.series[0],
                        chart = this;
                    setInterval(function () {
                        var page = '../Beat';
                        $.post(
                            page,
                            function(result){
                                //alert(result.charCodeAt());
                                var x = (new Date()).getTime(), // current time
                                    y = parseFloat(result)//这个便是心跳数据
                                series.addPoint([x, y], true, true);
                                activeLastPointToolip(chart)
                            }
                        )
                    }, 1000);
                }
            }
        },
        title: {
            text: '动态模拟实时数据'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 0,
            gridLineWidth: 1
        },
        yAxis: {
            title: {
                text: '值'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#FFFFFF'
            }],
            gridLineWidth: 1
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                    Highcharts.numberFormat(this.y, 2);
            }
        },
        legend: {
            enabled: false
        },
        exporting: {
            enabled: false
        },
        series: [{
            name: '随机数据',
            data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;
                for (i = -10; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000,
                        y: 0
                    });
                }
                return data;
            }())
        }]
    }, function(c) {
        activeLastPointToolip(c)
    });

</script>
</body>
</html>
​
