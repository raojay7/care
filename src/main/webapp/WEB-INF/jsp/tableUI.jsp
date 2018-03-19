<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui-v2.2.5/layui/css/layui.css" media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
    <script src="https://img.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
    <script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>
    <script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
    <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
    <script src="https://img.hcharts.cn/highcharts/themes/dark-unica.js"></script>
    <style type="text/css">
        #circle1{
            position: absolute;
            top:5px;
            left: 125px;
            z-index: 10;
            width:20px;
            height:20px;
            border-radius: 50%;
            background-color: #5FB878;
        }
        #circle2{
            position: absolute;
            top:5px;
            left: 705px;
            z-index: 10;
            width:20px;
            height:20px;
            border-radius: 50%;
            background-color: #5FB878;
        }
        #circle3{
            position: absolute;
            top:355px;
            left: 125px;
            z-index: 10;
            width:20px;
            height:20px;
            border-radius: 50%;
            background-color: #5FB878;
        }
        #circle4{
            position: absolute;
            top:355px;
            left: 705px;
            z-index: 10;
            width:20px;
            height:20px;
            border-radius: 50%;
            background-color: #5FB878;
        }
    </style>
</head>
<body>


<%--<fieldset class="layui-elem-field layui-field-title site-demo-button" style="margin-top: 30px;">
    <legend>功能点击</legend>
</fieldset>

<div class="site-demo-button" id="layerDemo" style="margin-bottom: 0;">
    <blockquote class="layui-elem-quote layui-quote-nm">
        Tips：为了更清晰演示，每触发下述一个例子之前，都会关闭所有已经演示的层
    </blockquote>
</div>--%>
<div id="circle1"></div>
<div id="circle2"></div>
<div id="circle3"></div>
<div id="circle4"></div>
<div>
<div id="container1" style="width:500px;height:300px;float:left;padding-right: 80px;padding-bottom: 50px;padding-left: 120px"></div>
<div id="container2" style="width:500px;height:300px;float:left"></div>
</div>
<div style="clear: both;"></div>
<div>
<div id="container3" style="width:500px;height:300px;float:left;padding-right: 80px;padding-left: 120px"></div>
<div id="container4" style="width:500px;height:300px;float:left"></div>
</div>
<div style="clear: both;"></div>
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
    $('#container1').highcharts({
        credits: {
            enabled:false
        },
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
                                /*if(y == 37.6){
                                    $("#circle1").css("background-color","#FF0000");
                                }else{

                                }*/
                                series.addPoint([x, y], true, true);
                                activeLastPointToolip(chart)
                            }
                        )
                    }, 1000);
                }
            }
        },
        title: {
            text: '实时温度数据'
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
            name: '温度',
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
    $('#container2').highcharts({
        credits: {
            enabled:false
        },
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
            text: '实时温度数据'
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
            name: '温度',
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
            }()),
            color:'#7CCD7C'
        }]
    }, function(c) {
        activeLastPointToolip(c)
    });
    $('#container3').highcharts({
        credits: {
            enabled:false
        },
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
            text: '实时温度数据'
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
            name: '温度',
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
            }()),
            color:'#CD8500'
        }]
    }, function(c) {
        activeLastPointToolip(c)
    });
    $('#container4').highcharts({
        credits: {
            enabled:false
        },
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
            text: '实时温度数据'
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
            name: '温度',
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
            }()),
            color:'#8968CD'
        }]
    }, function(c) {
        activeLastPointToolip(c)
    });

</script>
<fieldset class="layui-elem-field site-demo-button" style="margin-top: 30px;">
    <legend>功能操作</legend>
    <div>
        <button data-method="confirmTrans" name="01" class="layui-btn layui-btn-normal ">开启</button>
        <button data-method="confirmTrans" id="up" name="02" class="layui-btn layui-btn-normal ">上翻</button>
        <button data-method="confirmTrans" id="down" name="03" class="layui-btn layui-btn-normal ">下翻</button>
    </div>
</fieldset>
<fieldset class="layui-elem-field site-demo-button">

    <!-- 示例-970 -->
    <ins class="adsbygoogle" style="display:inline-block;width:970px;height:90px"
         data-ad-client="ca-pub-6111334333458862" data-ad-slot="3820120620"></ins>


    <script src="../layui-v2.2.5/layui/layui.js" charset="utf-8"></script>
    <!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
    <script type="text/javascript">
        $("#up").click(function(){
            $.post(
                "../send?order=" + "up",
                function(result){
                    alert(result);
                }
            )
        })
        $("#down").click(function(){
            $.post(
                "../send?order=" + "down",
                function(result){
                    alert(result);
                }
            )
        })
    </script>
    <script>
        layui.use('layer', function () { //独立版的layer无需执行这一句
            var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句

            //触发事件
            var active = {
                confirmTrans: function () {
                    $button=$(this);
                    var name=$(this).attr("name");
                    var text=$(this).html();
                    if(name == '01'){
                        $button.attr({name:"04"});
                        $button.html("关闭");
                        window.location.href = "../StartServer";
                    }
                    if(name == '04'){
                        $button.attr({name:"01"});
                        $button.html("开启");
                        window.location.href = "../ShutDown";
                    }

                }


            };

            //询问框

            $('.layui-elem-field .layui-btn').on('click', function () {
                var othis = $(this), method = othis.data('method');
                active[method] ? active[method].call(this, othis) : '';
            });

        });
    </script>
</fieldset>
</body>
</html>