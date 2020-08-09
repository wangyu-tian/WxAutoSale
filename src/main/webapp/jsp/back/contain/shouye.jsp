<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container-fluid">
    <section class="content-header">
        <h1><small></small></h1>
        <ol class="breadcrumb">
            <li><a href="/back/index?page=shouye"><i class="fa fa-home"></i> 首页</a></li>
            <li class="active">订单管理</li>
        </ol>
    </section>

    <div class="panel panel-default ">
        <div class="panel-heading">查询条件</div>
        <div class="panel-body">
            <form class="navbar-form navbar-left" role="form" id="search-form">
                <div class="form-group">
                    <input type="text" id="dateRange" class="form-control daterange" readonly="" placeholder="日期"
                           name="daterange" style="width: 200px;" value="${dateRange}">
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
                                   id="orderNum-id"
                                   autocomplete="off"
                                   autocorrect="off"
                                   autocapitalize="off"
                                   spellcheck="false" role="textbox"
                                   placeholder="订单号"
                                   value="${orderNum}"
                                   style="width: 248px;"/></li></ul></span></span><span
                                  class="dropdown-wrapper" aria-hidden="true"></span></span>
                </div>
                <div class="form-group">
                    <button type="button" id="searchOrderBtn" class="btn btn-success"><i class="fa fa-search"></i>搜索</button>
                </div>
            </form>
        </div>
    </div>

    <div class="panel-body row">
        <table id="order_list" class="table table-striped table-bordered" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>订单编号</th>
                <th>商家名称</th>
                <th>姓名</th>
                <th>手机号码</th>
                <th>订单金额</th>
                <th>支付状态</th>
                <th>创建时间</th>
                <th>订单状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orderList}" var="item">
                <tr>
                    <td><a href='/back/order/detail?id=${item.id}'>${item.code}</a></td>
                    <td>${item.MName}</td>
                    <td>${item.name}</td>
                    <td>${item.phone}</td>
                    <td>${item.discountAmount}</td>
                    <td>
                        <div class="row">
                            <div class="col-md-9">
                                    ${item.statusName}
                            </div>
                            <div class="col-md-3 btn-group dropup">
                                <button type="button" class="btn btn-primary btn-xs dropdown-toggle"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                        style="float:right;">
                                    修改 <i class="fa fa-cog"></i></button>
                                <ul class="dropdown-menu  dropdown-menu-right">

                                    <c:forEach items="${orderStatusMap}" var="statusItem">
                                        <c:choose>
                                            <c:when test="${item.status == statusItem.key}">
                                                <li><a href="javascript:void(0)" style="color: red" onclick="alert('当前订单状态为：${statusItem.value}')">${statusItem.value}</a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li><a href="javascript:void(0)"
                                                       onclick="var dateRange = $('#dateRange').val();var orderNum = $('#orderNum-id').val();window.location.href='/back/order/update?id=${item.id}&page=order&oldStatus=${item.status}&newStatus=${statusItem.key}&dateRange='+dateRange+'&orderNum='+orderNum;">${statusItem.value}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>

                                </ul>
                            </div>
                        </div>
                    </td>
                    <td>${item.createDate}</td>
                    <td>${item.valid=='1'?'生效中':'已失效'}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <script type='text/javascript'>
        $(document).ready(function () {
            $('#order_list').DataTable({
                order: [[0, 'desc']]
            });

            $("#searchOrderBtn").click(function(){
                var dateRange = $('.daterange').val();
                var orderNum = $('#orderNum-id').val();
                window.location.href="/back/order/search?dateRange="+dateRange+"&orderNum="+orderNum+"&page="+"${page}";
            });
        });


    </script>
</div>