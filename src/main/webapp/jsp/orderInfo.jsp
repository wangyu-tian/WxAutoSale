<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <link href="/static/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<section class="aui-flexView">
    <header class="aui-navBar aui-navBar-fixed">
        <a href="javascript:;" class="aui-navBar-item">
            订单详情
        </a>
        <div class="aui-center">
            <span class="aui-center-title"></span>
        </div>
    </header>
    <section class="aui-scrollView">
        <div class="aui-pay-change">
            <div class="aui-flex">
                <div class="aui-flex-box">
                    <h3>
                        订单编号：${orderInfo.code}
                    </h3>
                    <h3>订单状态：${orderInfo.statusName}</h3>
                </div>
            </div>
        </div>
        <div class="aui-flex">

            <div class="aui-flex-box">

                <h3>
                    ${orderInfo.name} <em>${orderInfo.phone}</em>
                </h3>
                <p>${orderInfo.address}</p>
            </div>
        </div>
        <div class="divHeight"></div>
        <div class="aui-commodity-bg">

            <c:forEach items="${goods}" var="good">

                <div class="aui-flex">
                    <div class="aui-commodity-img">
                        <img src="${good.url}" alt="">
                    </div>
                    <div class="aui-flex-box">
                        <h4>${good.name}</h4>
                        <c:if test="${good.discount!=''}">
                            <p>折扣：${good.discount}</p>
                        </c:if>
                        <p>单价：${good.price}</p>
                        <p>原总价：${good.total}</p>
                        <p>折扣总价：${good.discountTotal}</p>
                    </div>
                    <div>
                        <p class="aui-sml-text">X${good.count}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="aui-flex aui-insurance-box b-line">
            <div class="aui-flex-box">
                <h4>商品数量：${orderInfo.count}</h4>
                <h5>${orderInfo.discountMemo}</h5>
            </div>
        </div>
        <div class="aui-cp-edu b-line">
            <div class="aui-san-sml"></div>
            <div class="aui-flex aui-flex-ed">
                <div class="aui-flex-box">
                    商品总价 <em>￥${orderInfo.orderAmount}</em>
                </div>
            </div>
            <div class="aui-flex aui-flex-ed">
                <div class="aui-flex-box">
                    优惠价格 <em>￥${disAmount}</em>
                </div>
            </div>
            <div class="aui-flex aui-flex-ed">
                <div class="aui-flex-box" style="color:#333; font-size:15px;">
                    订单总价<em style="color:#333">￥${orderInfo.discountAmount}</em>
                </div>
            </div>
        </div>
        <div class="divHeight b-line"></div>
        <div class="aui-flex aui-flex-text-sml b-line">
            <div class="aui-flex-box">
                <p>
                    <em>买家备注:</em>
                    ${orderInfo.UMemo}
                </p>
                <p>
                    <em>商家备注:</em>
                    ${orderInfo.MMemo}
                </p>
                <p>
                    <em>下单时间:</em>
                    ${orderInfo.createDate}
                </p>
            </div>
        </div>

        <div class="aui-shop-list aui-shop-list-selected">


        </div>
    </section>

</section>


</body>
</html>