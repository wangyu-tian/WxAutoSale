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
    <script src="https://cdn.bootcss.com/blueimp-md5/2.10.0/js/md5.js"></script>

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
                <form class="m-t" name="subForm" role="form" method="get" action="/back/login">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Username/Email" required id="form-username"
                               name="userName">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="Password" required name="password"
                               id="form-password">
                    </div>

                    <button type="button" onclick="onSubmit()" class="btn btn-primary block full-width m-b">登录</button>
                </form>
            </div>
        </div>
    </div>

    <script type='text/javascript'>

        function onSubmit(){
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
            $("#form-password").val(md5($("#form-password").val()));
            document.subForm.submit();
        }


    </script>
</div>
<div align="center">
    <p><small>湘ICP备16020893号</small></p>
</div>


</body>
</html>


