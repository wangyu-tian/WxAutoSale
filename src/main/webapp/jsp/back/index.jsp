<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title> 飞鸽网</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="//cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="/dist/css/skins/_all-skins.min.css">
    <!-- Morris chart -->
    <link href="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.24/daterangepicker.min.css" rel="stylesheet" type="text/css">
    <link href="//cdn.bootcss.com/datatables/1.10.11/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
    <link href="//cdn.bootcss.com/bootstrap3-dialog/1.35.3/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css">
    <link href="//cdn.bootcss.com/select2/4.0.0/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="//cdn.bootcss.com/Ladda/1.0.0/ladda-themeless.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/css/common.css" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="//cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="/dist/js/app.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.24/moment.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.24/daterangepicker.min.js"></script>
    <script src="//cdn.bootcss.com/select2/4.0.0/js/select2.full.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.24/moment.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.24/daterangepicker.min.js"></script>
    <script src="//cdn.bootcss.com/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
    <script src="/js/datatable.cn.js"></script>

    <script src="//cdn.bootcss.com/bootstrap3-dialog/1.35.3/js/bootstrap-dialog.min.js"></script>
    <script src="//cdn.bootcss.com/bootbox.js/4.4.0/bootbox.min.js"></script>
    <script src="//cdn.bootcss.com/Ladda/1.0.0/spin.min.js"></script>
    <script src="//cdn.bootcss.com/Ladda/1.0.0/ladda.min.js"></script>
    <script src="/js/fnProcessingIndicator.js"></script>
    <script src="/js/common.js"></script>
</head>
<body class="hold-transition sidebar-mini skin-green-light">
<div class="wrapper">
    <header class="main-header">
        <!-- Logo -->
        <a href="/" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini">飞鸽网${sysToken}</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>飞鸽网</b> 管理中心</span>
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
                            <span class="hidden-xs">wechat_511936</span>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-right">
                                    <a href="/ucenter/profile" class="btn btn-default btn-flat">修改个人资料</a>
                                    <a href="/logout" class="btn btn-default btn-flat">退出</a>
                                </div>
                            </li>
                        </ul>
                    </li>

                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu" id="my-nav-list">


                <li class="treeview">
                    <a href="/project">
                        <i class="fa fa-list-alt"></i>
                        <span>群组管理</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="/project"><i class="fa fa-circle-o"></i>群组列表</a></li>
                        <li><a href="/member"><i class="fa fa-circle-o"></i>人员管理</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="/message">
                        <i class="fa fa-tasks"></i>
                        <span>消息管理</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="/message/history"><i class="fa fa-circle-o"></i> 发送记录</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="/money">
                        <i class="fa fa-file-video-o"></i>
                        <span>财务管理</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="/money/index"><i class="fa fa-circle-o"></i> 充值记录</a></li>
                        <li><a href="/money/consume"><i class="fa fa-circle-o"></i> 消费记录</a></li>
                        <li><a href="/money/add"><i class="fa fa-circle-o"></i> 充值中心</a></li>
                        <!-- <li><a href="/money/withdraw"><i class="fa fa-circle-o"></i> 提现管理</a></li> -->
                    </ul>
                </li>

                <li class="treeview">
                    <a href="/ucenter">
                        <i class="fa fa-home"></i> <span>用户中心</span> <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="/ucenter/wechat"><i class="fa fa-circle-o"></i> 微信绑定<img src="/images/hot.png" style="margin-top:0px;margin-bottom:15px;"/></a></li>
                        <li><a href="/ucenter/profile"><i class="fa fa-circle-o"></i> 修改资料</a></li>
                        <li><a href="/ucenter/changepwd"><i class="fa fa-circle-o"></i> 修改密码</a></li>
                        <li><a href="/ucenter/secret"><i class="fa fa-circle-o"></i> 查看密钥</a></li>
                        <!-- <li><a href="/ucenter/withdraw-account"><i class="fa fa-circle-o"></i> 提现帐户</a></li> -->
                        <li><a href="/ucenter/recommend"><i class="fa fa-circle-o"></i> 推荐用户</a></li>
                        <li><a href="/user/logout"><i class="fa fa-circle-o"></i> 退出登录</a></li>
                    </ul>
                </li>

                <li class="treeview">
                    <a href="#" >
                        <i class="fa fa-book"></i> <span>帮助中心</span> <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="/docs/help.pdf" target="_blank"><i class="fa fa-circle-o"></i> 使用说明</a></li>
                        <li><a href="/wiki"  target="_blank"><i class="fa fa-circle-o"></i> 接口WIKI</a></li>

                    </ul>
                </li>

            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>
    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
            <!-- Home tab content -->
            <div class="tab-pane" id="control-sidebar-home-tab">
            </div>
            <!-- /.tab-pane -->
            <!-- Stats tab content -->
            <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
        </div>
    </aside>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <div class="container-fluid">
            <section class="content-header">
                <h1><small></small></h1>
                <ol class="breadcrumb">
                    <li><a href="/"><i class="fa fa-home"></i> 首页</a></li>
                    <li class="active">群组管理</li>
                </ol>
            </section>

            <div class="panel-body row">
                <div class="panel panel-default ">
                    <div class="panel-heading">群组列表</div>
                    <div class="panel-body">
                        <button type="button" class="btn btn-success"  onclick="window.location.href='/project/add';"><i class="fa fa-plus"></i>新增</button>
                    </div>
                    <div class="panel-footer">
                        <img src='/wechat/share-qrcode/wechat_511936' width="100px;" height="100px;" title="扫一扫，添加人员"/>
                        <p>需要接收消息的成员可以通过扫描上面的二维码实现快捷关注，之后您将相应成员添加到接收群组后即可</p>
                    </div>
                </div>
            </div>

            <div class="panel-body row">
                <table id="order_list" class="table table-striped table-bordered" cellspacing="0" width="100%" >
                    <thead>
                    <tr>
                        <th>群组ID</th>
                        <th>群组名称</th>
                        <th>消息模板</th>
                        <th>群组app_key</th>
                        <th>简介</th>
                        <th>创建时间</th>
                        <th>状态</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><a href='/project/edit/1387'>1387</a></td>
                        <td>
                            <div class="row">
                                <div class="col-md-9">
                                    <a href='/project/edit/1387'>功能开发组</a>
                                </div>
                                <div class="col-md-3 btn-group dropup">
                                    <button type="button" class="btn btn-primary btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="float:right;">
                                        操作 <i class="fa fa-cog"></i></button>
                                    <ul class="dropdown-menu  dropdown-menu-right">
                                        <li><a href="javascript:void(0)" onclick="window.location.href='/project/edit/1387';">编辑</a></li>
                                        <li><a href="javascript:void(0)" onclick="removeData('/project/delete/1387');">删除</a></li>
                                    </ul>
                                </div>
                            </div>
                        </td>
                        <td><a href="/project/template/1387" target="_blank" title="试一试消息">发消息</a></td>
                        <td>e05d96188a39752534ca7bf096f76c06</td>
                        <td title="开发功能测试群组">开发功能测试群组</td>
                        <td>2020-07-21 10:59:11</td>
                        <td title="">启用</td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <script type='text/javascript'>
                $(document).ready(function(){
                    $('#order_list').DataTable({
                        order: [[0, 'desc']]
                    });
                });

            </script>
        </div>
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <small>Copyright  &copy; 2015-2019 飞鸽网. All Rights Reserved.</small>
            <small><a href="http://www.miitbeian.gov.cn/" target="_blank">湘ICP备16020893号</a></small>
        </div>
        <div>
            <p><small>联系我们 QQ群：572792115  反馈邮箱：feedback@ifeige.cn 飞鸽快信微信公众号：
                <a class="weixin" href="#share-weixin-modal" data-toggle="modal"><i class="fa fa-weixin"></i></a>
            </small>
            </p>
        </div>
        <div class="modal fade" id="share-weixin-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="btn btn-default close" data-dismiss="modal" aria-hidden="true"><span>&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel"><strong>关注飞鸽快信微信公众号，开始使用。</strong></h4>
                    </div>
                    <div class="modal-body">
                        <p>微信中搜索「飞鸽快信」或「ifeigecn」或者扫一扫下方二维码关注：</p>
                        <img src="/images/qrcode_258.jpg" alt="飞鸽快信">
                    </div>
                </div>
            </div>
        </div>
    </footer>
</body>
<script src="/dist/js/demo.js"></script>
<script type='text/javascript'>
    function is_sub_url(url) {
        return window.location.pathname.indexOf(url)==0?true:false;
    }

    $(document).ready(function(){
        $.widget.bridge('uibutton', $.ui.button);

        //导航条active处理
        $('#my-nav-list').children('li').each(function(){
            var flag = false;
            $(this).children('ul').children('li').each(function(){
                var subFlag = false;
                $(this).children('ul').children('li').each(function(){
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

            $('a[data-toggle="offcanvas"]').bind('click', function() {
                window.localStorage.setItem('offcanvas', 1-window.localStorage.getItem('offcanvas'));
                return true;
            });

        }
    });
</script>
</html>



