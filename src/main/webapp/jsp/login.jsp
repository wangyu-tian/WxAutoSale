<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>后台管理登陆</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css">

    <!-- Javascript -->
    <script src="//cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="//cdn.bootcss.com/bootbox.js/4.4.0/bootbox.min.js"></script>

</head>
<body class="gray-bg">
<div class="content-wrapper">
    <div align="center">
        <div class="middle-box loginscreen text-center animated  login-form">
            <h1 class="logo-name">IFB</h1>
            <h3>欢迎使用后台管理系统</h3>
            <p>微信外送服务后台系统 -- 登录</p>
            <ul class="nav nav-tabs">
                <li role="presentation" id="form-li" class="active">
                    <a href="?username" title="账号" >账号</a></li>
            </ul>

            <div class="panel-body row" id="form-div">
                <form class="m-t" role="form" action="index.html">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Username/Email" required id="form-username"
                               name="form-username">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="Password" required name="form-password"
                               id="form-password">
                    </div>

                    <button type="submit" class="btn btn-primary block full-width m-b">登录</button>
                </form>
            </div>
        </div>
    </div>

    <script type='text/javascript'>

        $(document).ready(function () {
            $('.login-form').on('submit', function (e) {
                var flag = true;
                $(this).find('[required]').each(function () {
                    if ($(this).val() == "") {
                        flag = false;
                    }
                });
                if (!flag) {
                    bootbox.alert('请输入账号/密码');
                    return false;
                }

                var params = {
                    'userName': $("input[name='form-username']").val(),
                    'password': $("input[name='form-password']").val(),
                };

                $.post('/back/login', params, function (data) {
                    console.log(data);
                    if (!data.status) {
                        window.location.href = '/';
                    } else {
                        changeCode($('#yzm'));
                        bootbox.alert(data.msg, function (ret) {
                            window.setTimeout(function () {
                                $("#form-code").focus();
                            }, 10);
                        });
                    }
                }, 'json');

                return false;
            });

        });


    </script>
</div>
<div align="center">
    <p><small>湘ICP备16020893号</small></p>
</div>


</body>
</html>


