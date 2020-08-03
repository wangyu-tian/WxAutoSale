<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>零售后台</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="//cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="http://u.ifeige.cn/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="http://u.ifeige.cn/dist/css/skins/_all-skins.min.css">
    <!-- Morris chart -->
    <link href="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.24/daterangepicker.min.css" rel="stylesheet"
          type="text/css">
    <link href="//cdn.bootcss.com/datatables/1.10.11/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
    <link href="//cdn.bootcss.com/bootstrap3-dialog/1.35.3/css/bootstrap-dialog.min.css" rel="stylesheet"
          type="text/css">
    <link href="//cdn.bootcss.com/select2/4.0.0/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="//cdn.bootcss.com/Ladda/1.0.0/ladda-themeless.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="http://u.ifeige.cn/css/common.css" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="//cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="http://u.ifeige.cn/dist/js/app.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.24/moment.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.24/daterangepicker.min.js"></script>
    <script src="//cdn.bootcss.com/select2/4.0.0/js/select2.full.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.24/moment.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.24/daterangepicker.min.js"></script>
    <script src="//cdn.bootcss.com/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
    <script src="http://u.ifeige.cn/js/datatable.cn.js"></script>

    <script src="//cdn.bootcss.com/bootstrap3-dialog/1.35.3/js/bootstrap-dialog.min.js"></script>
    <script src="//cdn.bootcss.com/bootbox.js/4.4.0/bootbox.min.js"></script>
    <script src="//cdn.bootcss.com/Ladda/1.0.0/spin.min.js"></script>
    <script src="//cdn.bootcss.com/Ladda/1.0.0/ladda.min.js"></script>
    <script src="http://u.ifeige.cn/js/fnProcessingIndicator.js"></script>
    <script src="/static/js/index_c.js"></script>
</head>
<body class="hold-transition sidebar-mini skin-green-light">
<div class="wrapper">
    <header class="main-header">
        <!-- Logo -->
        <a href="/" class="logo">
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>零售后台</b> 管理中心</span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <div class="navbar-custom-menu">

                <ul class="nav navbar-nav">
                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <span class="hidden-xs">${userName}</span>
                        </a>
                    </li>

                </ul>
            </div>
        </nav>
    </header>
    <!-- 左栏菜单-start -->
    <aside class="main-sidebar">
        <section class="sidebar">
            <ul class="sidebar-menu" id="my-nav-list">
                <c:if test="${menuPermission.get('1000')}">
                    <li class="treeview">
                        <a href="/project">
                            <i class="fa fa-list-alt"></i>
                            <span>订单管理</span>
                            <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <c:if test="${menuPermission.get('1001')}">
                                <li><a href="/back/index?page=order"><i class="fa fa-circle-o"></i>订单管理</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${menuPermission.get('2000')}">
                    <li class="treeview">
                        <a href="/money">
                            <i class="fa fa-file-video-o"></i>
                            <span>财务管理</span>
                            <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <c:if test="${menuPermission.get('2001')}">
                                <li><a href="javascript:alert('功能暂不开放');"><i class="fa fa-circle-o"></i> 充值记录</a></li>
                            </c:if>
                            <c:if test="${menuPermission.get('2002')}">
                                <li><a href="javascript:alert('功能暂不开放');"><i class="fa fa-circle-o"></i> 消费记录</a></li>
                            </c:if>
                            <c:if test="${menuPermission.get('2003')}">
                                <li><a href="javascript:alert('功能暂不开放');"><i class="fa fa-circle-o"></i> 充值中心</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${menuPermission.get('3000')}">
                    <li class="treeview">
                        <a href="/ucenter">
                            <i class="fa fa-home"></i> <span>用户中心</span> <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <c:if test="${menuPermission.get('3001')}">
                                <li><a href="javascript:alert('功能暂不开放');"><i class="fa fa-circle-o"></i> 用户中心</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${menuPermission.get('4000')}">
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-book"></i> <span>帮助中心</span> <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <c:if test="${menuPermission.get('4001')}">
                                <li><a href="javascript:alert('功能暂不开放');"><i class="fa fa-circle-o"></i> 帮助中心</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </section>
    </aside>
    <!-- 左栏菜单-end -->

    <c:if test="${page=='shouye'}">
        <div class="content-wrapper">
            <jsp:include page="contain/shouye.jsp" flush="true"/>
        </div>
    </c:if>

    <c:if test="${page=='order'}">
        <div class="content-wrapper">
            <jsp:include page="contain/order.jsp" flush="true"/>
        </div>
    </c:if>
</div>
</body>
<script type='text/javascript'>
    function is_sub_url(url) {
        return window.location.pathname.indexOf(url) == 0 ? true : false;
    }

    $(document).ready(function () {
        $.widget.bridge('uibutton', $.ui.button);

        //导航条active处理
        $('#my-nav-list').children('li').each(function () {
            var flag = false;
            $(this).children('ul').children('li').each(function () {
                var subFlag = false;
                $(this).children('ul').children('li').each(function () {
                    if (is_sub_url($(this).children('a').attr('href'))) {
                        $(this).addClass('active');
                        subFlag = true;
                        return false;
                    }
                });
                if (subFlag || is_sub_url($(this).children('a').attr('href'))) {
                    $(this).children('ul').addClass('menu-open');
                    $(this).addClass('active');
                    flag = true;
                }
            });

            if (flag || is_sub_url($(this).children('a').attr('href'))) {
                $(this).children('ul').addClass('menu-open');
                $(this).addClass('active');
            }
        });

        //导航默认收缩
        if (window.localStorage) {
            if (!window.localStorage.getItem('offcanvas')) {
                window.localStorage.setItem('offcanvas', 0);
            }
            if (1 == window.localStorage.getItem('offcanvas')) {
                $('body').addClass('sidebar-collapse');
            }

            $('a[data-toggle="offcanvas"]').bind('click', function () {
                window.localStorage.setItem('offcanvas', 1 - window.localStorage.getItem('offcanvas'));
                return true;
            });

        }
    });
</script>
</html>



