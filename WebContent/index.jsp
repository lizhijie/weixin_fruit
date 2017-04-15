<%
	long startTime = System.currentTimeMillis();
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="cn.debug.*"%>
<jsp:useBean id="index" class="cn.bean.Control" scope="request" />
<%
	index.make(request, response);
%>

<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>水果小店</title>
    <link rel="stylesheet" href="./style/weui.min.css"/>
    <link rel="stylesheet" href="./style/main.css"/>
</head>
<body ontouchstart>
    <div class="weui-toptips weui-toptips_warn js_tooltips">错误提示</div>

    <div class="container" id="container"></div>

    <script type="text/html" id="tpl_footer">
<div class="page__ft">

				<div class="page_ft weui-footer_fixed-bottom tabbar js_show">
					<div class="weui-tabbar">
						<a href="javascript:;" class="weui-tabbar__item weui-bar__item_on" data-id="home" id="bar_home">
							<img src="./images/icon_tabbar.png" alt=""
							class="weui-tabbar__icon">
							<p class="weui-tabbar__label">鲜果</p>
						</a> <a href="javascript:;" class="weui-tabbar__item" data-id="buybus" id="bar_buybus"> <img
							src="./images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
							<p class="weui-tabbar__label" >果篮</p>
						</a> <a href="javascript:;" class="weui-tabbar__item" data-id="orders" id="bar_orders"> <img
							src="./images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
							<p class="weui-tabbar__label">订单</p>
						</a> <a href="javascript:;" class="weui-tabbar__item" data-id="mine" id="bar_mine"> <img
							src="./images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
							<p class="weui-tabbar__label">信息</p>
						</a>
					</div>
				</div>
			</div>
<script type="text/javascript" class="page__ft">
		$(function() {	
			$('.weui-tabbar__item').on(
					'click',
					function() {
var id = $(this).data('id');
            window.pageManager.go(id);	
						$(this).addClass('weui-bar__item_on').siblings(
								'.weui-bar__item_on').removeClass(
								'weui-bar__item_on');				
});		
});
	</script>
</script>


<script type="text/html" id="tpl_home">
<div class="page__bd">
		<div class="weui-panel weui-panel_access">
			<div class="weui-panel__hd">水果天地</div>
			<div id="palist" hidden="hidden" class="weui-panel__bd">

				<a id="list" href="javascript:;"
					class="weui-btn weui-media-box weui-media-box_appmsg" data-id="toast">
					<div class="weui-media-box__hd">
						<img class="weui-media-box__thumb"
							src=""
							alt="">
					</div>
					<div class="weui-media-box__bd">
						<h4 class="weui-media-box__title"></h4>
						<p class="weui-media-box__desc"></p>
						<ul class="weui-media-box__info">
                        <li class="weui-media-box__info__meta" id="price"></li>
                        <li class="weui-media-box__info__meta"></li>
                        <li class="weui-media-box__info__meta">其它信息</li>
                    </ul>
					</div>

				</a>
			</div>
<a href="json.jsp?pages=Orders&&buy=w"><p
						class="weui-media-box__desc">用户名: ${index.bean.userName}</p></a>
		</div>
</div>
<script type="text/javascript">
get_goods();

</script>
</script>


<script type="text/html" id="tpl_toast">
<div class="page__bd">
    <div class="page__bd">
        <h1 class="page__title">Toast</h1>
        <p class="page__desc">弹出式提示</p>
    </div>
    <div class="page__bd page__bd_spacing">
        <a href="javascript:;" class="weui-btn weui-btn_default" id="showToast">成功提示</a>
        <a href="javascript:;" class="weui-btn weui-btn_default" id="showLoadingToast">加载中提示</a>
    </div>

    <!--BEGIN toast-->
    <div id="toast" style="display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-icon-success-no-circle weui-icon_toast"></i>
            <p class="weui-toast__content">已完成</p>
        </div>
    </div>
    <!--end toast-->

    <!-- loading toast -->
    <div id="loadingToast" style="display:none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-loading weui-icon_toast"></i>
            <p class="weui-toast__content">数据加载中</p>
        </div>
    </div>
</div>
<script type="text/javascript">
    // toast
    $(function(){
        var $toast = $('#toast');
        $('#showToast').on('click', function(){
            if ($toast.css('display') != 'none') return;

            $toast.fadeIn(100);
            setTimeout(function () {
                $toast.fadeOut(100);
            }, 2000);
        });
    });

    // loading
    $(function(){
        var $loadingToast = $('#loadingToast');
        $('#showLoadingToast').on('click', function(){
            if ($loadingToast.css('display') != 'none') return;

            $loadingToast.fadeIn(100);
            setTimeout(function () {
                $loadingToast.fadeOut(100);
            }, 2000);
        });
    });
</script>
</script>

<script type="text/html" id="tpl_agoods">
    <div class="page__bd">
        <article class="weui-article">
            <h3 id="goodsname"></h3><hr></hr>
            <section>
                <section>
                    <p>
                        <img id="img_des" src="./images/pic_article.png" alt="">
                    </p>
					<h4 id="per">【商品规格】</h4>
                    <p>
                       每箱10斤,A级果
                    </p>
                </section>
                <section>
                    <h4 id="des">【商品介绍】</h4>
                    <p>
                        苹果功效作用很大，它不仅是我国最主要的果品，也是世界上种植最广、产量最多的果品。其味道酸甜适口，香甜多汁，营养丰富。美国流传一种说法：“每天吃一个苹果，就不用请医生”。此话虽然有些夸张，但苹果的营养和药用价值由此可窥见不一般。又因为苹果所含的营养既全面又易被人体消化吸收，所以，非常适合婴幼儿，老人和病人食用
                    </p>
                </section>
				<section>
                    <h4 id="tran">【发货说明】</h4>
                    <p>
                        发货范围在郑州市内，由我们直接送达。
                    </p>
                </section>
				<section>
                    <h4 id="we">【联系我们】</h4>
                    <p>
                       店名：鲜果超市<br></br>
公众号：ceshi<br></br>
客服电话：15516937925
                    </p>
                </section>
            </section>
        </article>
<a href="javascript:;" class="weui-btn weui-btn_plain-primary" id="agoodsbutton">加入购物车</a>
    </div>
<script type="text/javascript">
agoods();
</script>
</script>
    
    
    
    
    <script type="text/html" id="tpl_buybus">
    <div class="page__bd">
<label class="weui-form-preview__label">我的购物车</label>
<span class="weui-form-preview__value"><span id="total">0</span><span >元</span></span>
<hr></hr>
<div class="page__bd" id="buybus_parent" hidden="hidden">
<div class="weui-form-preview" id="buybus_child">
            
            <div class="weui-form-preview__bd">
            
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">苹果</label>
                    <span class="weui-form-preview__value"><span>￥</span><span id="the_price">0</span><span>元</span></span>
                </div>
                    <div class="weui-form-preview__value">关于</div>
                
            </div>
            <div class="weui-form-preview__item" id="buts">
                <a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary" id="buybus_buttons">加</a>
				<span class="buybus_count">0</span>
				<span>份</span>
				<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary" id="buybus_buttons">减</a>
				<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_warn" id="buybus_buttons">删除</a>

            </div>
        </div>
    </div>
<a href="javascript:;" class="weui-btn weui-btn_plain-primary" id="clear_buybus">结算购物车</a>
</div>
<script type="text/javascript">
buybus();
</script>
</script>
    
    
    
   <script type="text/html" id="tpl_orders">
<div class="page__bd">
		<div class="preview js_show">
    <div class="page__hd">
        <h1 class="page__title">我的订单</h1>
		<hr></hr>
    </div>
<div class="weui-navbar">
                <div class="weui-navbar__item weui-bar__item_on" status="1" >
                    待付款
                </div>
                <div class="weui-navbar__item" status="2" >
                    待发货
                </div>
                <div class="weui-navbar__item" status="3">
                    已发货
                </div>
    			<div class="weui-navbar__item" status="4">
                    已完成
                </div>
    			<div class="weui-navbar__item" status="0">
                    全部
                </div>

</div>
    <div class="page__bd" id="orders_parent">
        <div class="weui-form-preview" id="one_orders">
            <div class="weui-form-preview__hd">
                <label class="weui-form-preview__label"><span>订单号:6839482</span><span id="orders_num">1157585903</span></label>
                <em class="weui-form-preview__value"><span>￥</span><span id="orders_total">0</span></em>
            </div>
            <div class="weui-form-preview__bd" id="orders_goods">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label"><span id="orders_name">苹果</span></label>
                    <span class="weui-form-preview__value"><span id="orders_count">0</span><span>份*</span><span id="orders_price">0</span><span>元</span></span>
                </div>
            </div>
            <div class="weui-form-preview__ft">
                <a class="weui-form-preview__btn weui-form-preview__btn_default" href="javascript:" id="orders_del">删除订单</a>
                <button type="submit" class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:">订单详情</button>
            </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
 $('.weui-navbar__item').on('click', function () {
            $(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
        orders();
        });
orders();
</script>
</script> 


<script type="text/html" id="tpl_aorder">
<div class="page__bd">
    <div class="page__bd">
        <h1 class="page__title"><span>单号:6839482</span><span id="aorder_num">541413430124</span></h1>
		<hr></hr>
    </div>
        <div class="weui-form-preview">
            <div class="weui-form-preview__bd" id="aorder_goods">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label"><span id="aorder_name">苹果</span></label>
                    <span class="weui-form-preview__value"><span id="aorder_count">0</span><span>份*</span><span id="aorder_price">0</span><span>元</span></span>
                </div>
            </div>
			
			<div class="weui-form-preview__bd">
				<div class="weui-form-preview__item">
                    <label class="weui-form-preview__label"><span>订单金额:</span></label>
                    <span class="weui-form-preview__value"><span>￥</span><span id="aorder_total">220</span></span>
               	</div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label"><span>订单时间:</span></label>
                    <span class="weui-form-preview__value"><span id="aorder_time">2016-12-01 13:26:30</span></span>
                </div>
				<div class="weui-form-preview__item">
                    <label class="weui-form-preview__label"><span>订单状态:</span></label>
                    <span class="weui-form-preview__value" id="aorder_status"><span></span></span>
                </div>
				<div class="weui-form-preview__item">
                    <label class="weui-form-preview__label"><span>收件人:</span></label>
                    <span class="weui-form-preview__value"><span id="aorder_recname">李志杰</span></span>
               	</div>
				<div class="weui-form-preview__item">
                    <label class="weui-form-preview__label"><span>手机号码:</span></label>
                    <span class="weui-form-preview__value"><span id="aorder_recnum">15516937925</span></span>
               	</div>
				<div class="weui-form-preview__item">
                    <label class="weui-form-preview__label"><span>收货地址:</span></label>
                    <span class="weui-form-preview__value"><span id="aorder_recaddress">郑州市高新区郑州轻工业学院</span></span>
               	</div>
            </div>


			

			
            <div class="weui-form-preview__ft">
                <a class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:" id="change_address">修改收货信息</a>
                <button type="submit" class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:" id="aorder_next">确认付款</button>
            </div>




		<div class="weui-skin_android" id="address" style="opacity: 0; display: none;">
        <div class="weui-mask"></div>
        <div class="weui-actionsheet">
            <div class="weui-actionsheet__menu" id="address_input">
                <div class="weui-actionsheet__cell"><input class="weui-input" type="text" placeholder="收货人" id="address_recname"></div>
                <div class="weui-actionsheet__cell"><input class="weui-input" type="text" placeholder="联系方式" id="address_recnum"></div>
                <div class="weui-actionsheet__cell"><textarea class="weui-textarea" placeholder="请输入收货人地址" rows="3" id="address_recaddress"></textarea></div>
				<a href="javascript:;" class="weui-btn weui-btn_plain-default" id="add_input">修改</a>
            </div>
        </div>
    </div>


        </div>
</div>
<script type="text/javascript">
aorder();
 
</script>
</script> 
    
    
    
    
    
<script type="text/html" id="tpl_mine">
<div class="page__bd">
<h1 class="page__title">我的信息</h1>
<div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">昵称:</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" type="number" pattern="[0-9]*" placeholder="用于网页登陆名">
                </div>
  <a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary" id="buybus_buttons">确认昵称</a>
            </div>
<div class="weui-cells__title">默认收货信息</div>
	<div class="weui-form-preview__bd">
				
                
				
				<div class="weui-form-preview__item">
                    <label class="weui-form-preview__label"><span>默认收件人:</span></label>
                    <span class="weui-form-preview__value"><span id="default_recname"></span></span>
               	</div>
				<div class="weui-form-preview__item">
                    <label class="weui-form-preview__label"><span>默认手机号码:</span></label>
                    <span class="weui-form-preview__value"><span id="default_recnum"></span></span>
               	</div>
				<div class="weui-form-preview__item">
                    <label class="weui-form-preview__label"><span>默认收货地址:</span></label>
                    <span class="weui-form-preview__value"><span id="default_recaddress"></span></span>
               	</div>
     </div>
<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary" id="default_button">修改默认收货信息</a>
<div class="weui-skin_android" id="address" style="opacity: 0; display: none;">
    <div class="weui-mask"></div>
        <div class="weui-actionsheet">
            <div class="weui-actionsheet__menu" id="address_input">
                <div class="weui-actionsheet__cell"><input class="weui-input" type="text" placeholder="收货人" id="address_recname"></div>
                <div class="weui-actionsheet__cell"><input class="weui-input" type="text" placeholder="联系方式" id="address_recnum"></div>
                <div class="weui-actionsheet__cell"><textarea class="weui-textarea" placeholder="请输入收货人地址" rows="3" id="address_recaddress"></textarea></div>
				<a href="javascript:;" class="weui-btn weui-btn_plain-default" id="add_input">修改</a>
            </div>
        </div>
    </div>
</div>

<div class="weui-cells__title">商家留言</div>
  <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label"><span>你的货物已经到达</span></label>
                    <span class="weui-form-preview__value"><span id="aorder_recnum">17-06-03 15:20</span></span>
               	</div>
   <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label"><span>你的货物已经到达</span></label>
                    <span class="weui-form-preview__value"><span id="aorder_recnum">17-06-03 15:20</span></span>
               	</div>
   <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label"><span>你的货物已经到达</span></label>
                    <span class="weui-form-preview__value"><span id="aorder_recnum">17-06-03 15:20</span></span>
               	</div>
   <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label"><span>你的货物已经到达</span></label>
                    <span class="weui-form-preview__value"><span id="aorder_recnum">17-06-03 15:20</span></span>
               	</div>
   <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label"><span>你的货物已经到达</span></label>
                    <span class="weui-form-preview__value"><span id="aorder_recnum">17-06-03 15:20</span></span>
               	</div>
</div>
<script type="text/javascript">
mine();
</script>
</script>  
    
    
    
    
    
    <script src="./js/zepto.min.js"></script>
    <script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script src="./js/main.js"></script>
</body>
</html>
<%
	long endTime = System.currentTimeMillis();MyDebug.println(this,"程序运行时间："+(endTime-startTime)+"ms");
%>
