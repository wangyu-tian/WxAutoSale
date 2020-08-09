<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container-fluid">
    <section class="content-header">
        <h1><small></small></h1>
        <ol class="breadcrumb">
            <li><a href="/back/index?page=shouye"><i class="fa fa-home"></i> 首页</a></li>
            <li><a href="/back/index?page=order"> 订单管理</a></li>
            <li class="active">订单详情</li>
        </ol>
    </section>

    <div class="panel-body row">
        <div class="panel panel-default ">
            <div class="panel-heading">订单详情</div>
            <div class="panel-body">
                <form id="form-project" class="form-horizontal" action="/project/edit-submit/1387" method="post">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">订单编号</label>
                        <div class="col-sm-6" style="padding-top: 7px">
                            ${orderInfo.code}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商家名称</label>
                        <div class="col-sm-6 " style="padding-top: 7px">
                            ${orderInfo.MName}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户id</label>
                        <div class="col-sm-6" style="padding-top: 7px">
                            ${orderInfo.UId}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品详情</label>
                        <div class="col-sm-6" style="padding-top: 7px">
                            <a target="_blank" href="/view/order/${orderInfo.UId}/${orderInfo.code}">点击查看商品详情</a>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品价格</label>
                        <div class="col-sm-6" style="padding-top: 7px">
                            ${orderInfo.discountAmount}
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">派送费</label>
                        <div class="col-sm-6" style="padding-top: 7px">
                            ${orderInfo.deliveryFee}
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户姓名</label>
                        <div class="col-sm-6" style="padding-top: 7px">
                            ${orderInfo.name}
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户电话</label>
                        <div class="col-sm-6" style="padding-top: 7px">
                            ${orderInfo.phone}
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">派送地址</label>
                        <div class="col-sm-6" style="padding-top: 7px">
                            ${orderInfo.address}
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户备注</label>
                        <div class="col-sm-6" style="padding-top: 7px">
                            ${orderInfo.UMemo}
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">商家备注</label>
                        <div class="col-sm-6" style="padding-top: 7px">
                            ${orderInfo.MMemo}
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">订单状态</label>
                        <div class="col-sm-6" style="padding-top: 7px">
                            ${orderInfo.statusName}
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">订单状态操作记录</label>
                        <div class="col-sm-6" style="padding-top: 7px">
                            <c:forEach items="${orderInfo.msg==null?null:orderInfo.msg.split(';')}" var="item">
                                <div style="padding: 3px;font-size: small">${item}</div>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-6">
                            <button type="submit" class="btn btn-success">保存</button>
                            <button type="button" class="btn btn-primary" onclick="window.history.back();">返回</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>