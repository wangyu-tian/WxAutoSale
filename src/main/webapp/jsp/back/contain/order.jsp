<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container-fluid">
    <section class="content-header">
        <h1><small></small></h1>
        <ol class="breadcrumb">
            <li><a href="/back/index?sysToken="><i class="fa fa-home"></i> 首页</a></li>
            <li class="active">订单管理</li>
        </ol>
    </section>

    <div class="panel panel-default ">
        <div class="panel-heading">查询条件</div>
        <div class="panel-body">
            <form class="navbar-form navbar-left" role="form" id="search-form">
                <div class="form-group">
                    <input type="text" class="form-control daterange" readonly="" placeholder="日期"
                           name="daterange" style="width: 200px;" value="2020-07-26 - 2020-08-02">
                </div>
                <div class="form-group">
                          <span class="select2 select2-container select2-container--default" dir="ltr"
                                style="width: 250px;">
                              <span class="selection">
                                  <span class="select2-selection select2-selection--multiple" role="combobox"
                                        aria-autocomplete="list" aria-haspopup="true" aria-expanded="false"
                                        tabindex="0">
                                      <ul class="select2-selection__rendered">
                            <li class="select2-search select2-search--inline">
                            <input class="select2-search__field"
                                   type="search" tabindex="-1"
                                   autocomplete="off"
                                   autocorrect="off"
                                   autocapitalize="off"
                                   spellcheck="false" role="textbox"
                                   placeholder="订单号"
                                   style="width: 248px;"></li></ul></span></span><span
                                  class="dropdown-wrapper" aria-hidden="true"></span></span>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-success"><i class="fa fa-search"></i>搜索</button>
                </div>
            </form>
        </div>
    </div>

    <div class="panel-body row">
        <table id="order_list" class="table table-striped table-bordered" cellspacing="0" width="100%">
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
                            <button type="button" class="btn btn-primary btn-xs dropdown-toggle"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                    style="float:right;">
                                操作 <i class="fa fa-cog"></i></button>
                            <ul class="dropdown-menu  dropdown-menu-right">
                                <li><a href="javascript:void(0)"
                                       onclick="window.location.href='/project/edit/1387';">编辑</a></li>
                                <li><a href="javascript:void(0)" onclick="removeData('/project/delete/1387');">删除</a>
                                </li>
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
        $(document).ready(function () {
            $('#order_list').DataTable({
                order: [[0, 'desc']]
            });
        });

    </script>
</div>