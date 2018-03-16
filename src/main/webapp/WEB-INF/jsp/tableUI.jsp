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
</head>
<body>


<fieldset class="layui-elem-field layui-field-title site-demo-button" style="margin-top: 30px;">
    <legend>功能点击</legend>
</fieldset>

<div class="site-demo-button" id="layerDemo" style="margin-bottom: 0;">
    <blockquote class="layui-elem-quote layui-quote-nm">
        Tips：为了更清晰演示，每触发下述一个例子之前，都会关闭所有已经演示的层
    </blockquote>
</div>


<fieldset class="layui-elem-field site-demo-button" style="margin-top: 30px;">
    <legend>功能操作</legend>
    <div>
        <button data-method="confirmTrans" name="01" class="layui-btn layui-btn-normal ">开启</button>
        <button data-method="confirmTrans" name="02" class="layui-btn layui-btn-normal ">上翻</button>
        <button data-method="confirmTrans" name="03" class="layui-btn layui-btn-normal ">下翻</button>
    </div>
</fieldset>
<fieldset class="layui-elem-field site-demo-button">

    <!-- 示例-970 -->
    <ins class="adsbygoogle" style="display:inline-block;width:970px;height:90px"
         data-ad-client="ca-pub-6111334333458862" data-ad-slot="3820120620"></ins>


    <script src="../layui-v2.2.5/layui/layui.js" charset="utf-8"></script>
    <!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
    <script>
        layui.use('layer', function () { //独立版的layer无需执行这一句
            var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句

            //触发事件
            var active = {
                confirmTrans: function () {
                    $button=$(this);
                    var name=$(this).attr("name");
                    var text=$(this).html();
                    layer.confirm('进行'+text+'操作', {
                        btn: ['确定', '取消'] //按钮
                    }, function () {

                        //ajax请求
                        $.post("/bed_control", { instruction:name},
                            function(redata){
                                //返回成功状态
                                if(redata.status==200){
                                    layer.msg('已经成功发送请求', {icon: 1});
                                    if(name=='01'){
                                        $button.attr({name:"04"});
                                        $button.html("关闭");
                                    }else if(name=='04'){
                                        $button.attr({name:"01"});
                                        $button.html("开启");
                                    }
                                }
                                else layer.msg('系统内部出现故障', {icon: 2});
                            });

                    }, function () {
                        layer.msg('已经取消', {
                            time: 20000, //20s后自动关闭
                            btn: ['知道了']
                        });
                    });

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