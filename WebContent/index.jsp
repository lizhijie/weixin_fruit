<%
	long startTime = System.currentTimeMillis();
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="index" class="cn.bean.Control" scope="request" />
<%
	index.make(request, response);
%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>WeUI</title>
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
						<a href="javascript:;" class="weui-tabbar__item weui-bar__item_on" data-id="home">
							<img src="./images/icon_tabbar.png" alt=""
							class="weui-tabbar__icon">
							<p class="weui-tabbar__label">鲜果</p>
						</a> <a href="javascript:;" class="weui-tabbar__item" data-id="buybus"> <img
							src="./images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
							<p class="weui-tabbar__label" >果篮</p>
						</a> <a href="javascript:;" class="weui-tabbar__item" data-id="toast"> <img
							src="./images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
							<p class="weui-tabbar__label">订单</p>
						</a> <a href="javascript:;" class="weui-tabbar__item"> <img
							src="./images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
							<p class="weui-tabbar__label">信息</p>
						</a>
					</div>
				</div>
			</div>
<script type="text/javascript">
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
							src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAMAAAAOusbgAAAAeFBMVEUAwAD///+U5ZTc9twOww7G8MYwzDCH4YcfyR9x23Hw+/DY9dhm2WZG0kbT9NP0/PTL8sux7LFe115T1VM+zz7i+OIXxhes6qxr2mvA8MCe6J6M4oz6/frr+us5zjn2/fa67rqB4IF13XWn6ad83nxa1loqyirn+eccHxx4AAAC/klEQVRo3u2W2ZKiQBBF8wpCNSCyLwri7v//4bRIFVXoTBBB+DAReV5sG6lTXDITiGEYhmEYhmEYhmEYhmEY5v9i5fsZGRx9PyGDne8f6K9cfd+mKXe1yNG/0CcqYE86AkBMBh66f20deBc7wA/1WFiTwvSEpBMA2JJOBsSLxe/4QEEaJRrASP8EVF8Q74GbmevKg0saa0B8QbwBdjRyADYxIhqxAZ++IKYtciPXLQVG+imw+oo4Bu56rjEJ4GYsvPmKOAB+xlz7L5aevqUXuePWVhvWJ4eWiwUQ67mK51qPj4dFDMlRLBZTqF3SDvmr4BwtkECu5gHWPkmDfQh02WLxXuvbvC8ku8F57GsI5e0CmUwLz1kq3kD17R1In5816rGvQ5VMk5FEtIiWislTffuDpl/k/PzscdQsv8r9qWq4LRWX6tQYtTxvI3XyrwdyQxChXioOngH3dLgOFjk0all56XRi/wDFQrGQU3Os5t0wJu1GNtNKHdPqYaGYQuRDfbfDf26AGLYSyGS3ZAK4S8XuoAlxGSdYMKwqZKM9XJMtyqXi7HX/CiAZS6d8bSVUz5J36mEMFDTlAFQzxOT1dzLRljjB6+++ejFqka+mXIe6F59mw22OuOw1F4T6lg/9VjL1rLDoI9Xzl1MSYDNHnPQnt3D1EE7PrXjye/3pVpr1Z45hMUdcACc5NVQI0bOdS1WA0wuz73e7/5TNqBPhQXPEFGJNV2zNqWI7QKBd2Gn6AiBko02zuAOXeWIXjV0jNqdKegaE/kJQ6Bfs4aju04lMLkA2T5wBSYPKDGF3RKhFYEa6A1L1LG2yacmsaZ6YPOSAMKNsO+N5dNTfkc5Aqe26uxHpx7ZirvgCwJpWq/lmX1hA7LyabQ34tt5RiJKXSwQ+0KU0V5xg+hZrd4Bn1n4EID+WkQdgLfRNtvil9SPfwy+WQ7PFBWQz6dGWZBLkeJFXZGCfLUjCgGgqXo5TuSu3cugdcTv/HjqnBTEMwzAMwzAMwzAMwzAMw/zf/AFbXiOA6frlMAAAAABJRU5ErkJggg=="
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
						class="weui-media-box__desc">UserId: ${index.bean.userId}</p></a>
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
                        <img src="./images/pic_article.png" alt="">
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
<div class="page__bd" id="buybus_parent">
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
</div>
<script type="text/javascript">
buybus();
</script>
</script>
    
    
    
    <script src="./js/zepto.min.js"></script>
    <script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script src="./js/main.js"></script>
</body>
</html>
<%
	long endTime = System.currentTimeMillis();System.out.println("程序运行时间："+(endTime-startTime)+"ms");
%>
