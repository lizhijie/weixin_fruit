/* Created by jf on 2015/9/11.
 * Modified by bear on 2016/9/7.
 */
var the_data;
$(function () {
    var pageManager = {
        $container: $('#container'),
        _configs: [],
        _pageAppend: function(){},
        _defaultPage: null,
        _pageIndex: 1,
        setDefault: function (defaultPage) {
            this._defaultPage = this._find('name', defaultPage);
            return this;
        },
        setPageAppend: function (pageAppend) {
            this._pageAppend = pageAppend;
            return this;
        },
        init: function () {
            var self = this;

            $(window).on('hashchange', function () {
                var state = history.state || {};
                var url = mylocal(0);
            	//alert(url);
                var page = self._find('url', url) || self._defaultPage;
                    self._go(page);
            });


            var url = mylocal(0);
            var page = self._find('url', url) || self._defaultPage;
            this._go(page);
            return this;
        },
        push: function (config) {
            this._configs.push(config);
            return this;
        },
        go: function (to) {
        	var arr=new Array();     
      	  
        	arr=to.split('>');//注split可以用字符或字符串分割
        	//alert(arr[0]);
            var config = this._find('name',arr[0]);
            if (!config) {
                return;
            }
            location.hash = to;
        },
        _go: function (config) {
            this._pageIndex ++;

            history.replaceState && history.replaceState({_pageIndex: this._pageIndex}, '', location.href);
            var mypage=this._find('url', '#footer');
            var html = $(config.template).html();
            var page =  $(mypage.template).html();
            var $html = $(html).addClass('slideIn').addClass(config.name);
            $html.on('animationend webkitAnimationEnd', function(){
                $html.removeClass('slideIn').addClass('js_show');
            });
            var $page = $(page).addClass('slideIn').addClass(page.name);
            $page.on('animationend webkitAnimationEnd', function(){
                $page.removeClass('slideIn').addClass('js_show');
            });
            this.$container.children(":not(.page__ft)").remove();
            if(this.$container.children(".page__ft").length<=0)
            this.$container.prepend($page);
            this.$container.prepend($html);
            this._pageAppend.call(this, $html);

            if (!config.isBind) {
                this._bind(config);
            }

            return this;
        },
        back: function () {
            history.back();
        },
        _find: function (key, value) {
            var page = null;
            for (var i = 0, len = this._configs.length; i < len; i++) {
                if (this._configs[i][key] === value) {
                    page = this._configs[i];
                    break;
                }
            }
            return page;
        },
        _bind: function (page) {
            var events = page.events || {};
            for (var t in events) {
                for (var type in events[t]) {
                    this.$container.on(type, t, events[t][type]);
                }
            }
            page.isBind = true;
        }
    };

    function fastClick(){
        var supportTouch = function(){
            try {
                document.createEvent("TouchEvent");
                return true;
            } catch (e) {
                return false;
            }
        }();
        var _old$On = $.fn.on;

        $.fn.on = function(){
            if(/click/.test(arguments[0]) && typeof arguments[1] == 'function' && supportTouch){ // 只扩展支持touch的当前元素的click事件
                var touchStartY, callback = arguments[1];
                _old$On.apply(this, ['touchstart', function(e){
                    touchStartY = e.changedTouches[0].clientY;
                }]);
                _old$On.apply(this, ['touchend', function(e){
                    if (Math.abs(e.changedTouches[0].clientY - touchStartY) > 10) return;

                    e.preventDefault();
                    callback.apply(this, [e]);
                }]);
            }else{
                _old$On.apply(this, arguments);
            }
            return this;
        };
    }
    function preload(){
        $(window).on("load", function(){
            var imgList = [
                "./images/layers/content.png",
                "./images/layers/navigation.png",
                "./images/layers/popout.png",
                "./images/layers/transparent.gif"
            ];
            for (var i = 0, len = imgList.length; i < len; ++i) {
                new Image().src = imgList[i];
            }
        });
    }
    function androidInputBugFix(){
        // .container 设置了 overflow 属性, 导致 Android 手机下输入框获取焦点时, 输入法挡住输入框的 bug
        // 相关 issue: https://github.com/weui/weui/issues/15
        // 解决方法:
        // 0. .container 去掉 overflow 属性, 但此 demo 下会引发别的问题
        // 1. 参考 http://stackoverflow.com/questions/23757345/android-does-not-correctly-scroll-on-input-focus-if-not-body-element
        //    Android 手机下, input 或 textarea 元素聚焦时, 主动滚一把
        if (/Android/gi.test(navigator.userAgent)) {
            window.addEventListener('resize', function () {
                if (document.activeElement.tagName == 'INPUT' || document.activeElement.tagName == 'TEXTAREA') {
                    window.setTimeout(function () {
                        document.activeElement.scrollIntoViewIfNeeded();
                    }, 0);
                }
            })
        }
    }
    function setJSAPI(){
        var option = {
            title: 'WeUI, 为微信 Web 服务量身设计',
            desc: 'WeUI, 为微信 Web 服务量身设计',
            link: "https://weui.io",
            imgUrl: 'https://mmbiz.qpic.cn/mmemoticon/ajNVdqHZLLA16apETUPXh9Q5GLpSic7lGuiaic0jqMt4UY8P4KHSBpEWgM7uMlbxxnVR7596b3NPjUfwg7cFbfCtA/0'
        };

        $.getJSON('https://weui.io/api/sign?url=' + encodeURIComponent(location.href.split('#')[0]), function (res) {
            wx.config({
                beta: true,
                debug: false,
                appId: res.appid,
                timestamp: res.timestamp,
                nonceStr: res.nonceStr,
                signature: res.signature,
                jsApiList: [
                    'onMenuShareTimeline',
                    'onMenuShareAppMessage',
                    'onMenuShareQQ',
                    'onMenuShareWeibo',
                    'onMenuShareQZone',
                    // 'setNavigationBarColor',
                    'setBounceBackground'
                ]
            });
            wx.ready(function () {
                /*
                 wx.invoke('setNavigationBarColor', {
                 color: '#F8F8F8'
                 });
                 */
                wx.invoke('setBounceBackground', {
                    'backgroundColor': '#F8F8F8',
                    'footerBounceColor' : '#F8F8F8'
                });
                wx.onMenuShareTimeline(option);
                wx.onMenuShareQQ(option);
                wx.onMenuShareAppMessage({
                    title: 'WeUI',
                    desc: '为微信 Web 服务量身设计',
                    link: location.href,
                    imgUrl: 'https://mmbiz.qpic.cn/mmemoticon/ajNVdqHZLLA16apETUPXh9Q5GLpSic7lGuiaic0jqMt4UY8P4KHSBpEWgM7uMlbxxnVR7596b3NPjUfwg7cFbfCtA/0'
                });
            });
        });
    }
    function setPageManager(){
        var pages = {}, tpls = $('script[type="text/html"]');
        var winH = $(window).height();

        for (var i = 0, len = tpls.length; i < len; ++i) {
            var tpl = tpls[i], name = tpl.id.replace(/tpl_/, '');
            pages[name] = {
                name: name,
                url: '#' + name,
                template: '#' + tpl.id
            };
        }
        pages.home.url = '#';

        for (var page in pages) {
            pageManager.push(pages[page]);
        }
        pageManager
            .setPageAppend(function($html){
                var $foot = $html.find('.page__ft');
                if($foot.length < 1) return;
                if($foot.position().top + $foot.height() < winH){
                    $foot.addClass('j_bottom');
                }else{
                    $foot.removeClass('j_bottom');
                }
            })
            .setDefault('home')
            .init();
    }

    function init(){	
        preload();
        fastClick();
        androidInputBugFix();
        setJSAPI();
        setPageManager();
        window.pageManager = pageManager;
        window.home = function(){
            location.hash = '';
        };
        window.pageManager.go('index');	
        
    }
    init();
});


function agoods()
{
	$.getJSON('./json.jsp?pages=Index&&alias='+mylocal(1), function(data){
	$('#agoodName').attr("value",data.name);
	$('#agoodAlias').attr("value",data.alias);
	$('#agoodPrice').attr("value",data.price);
	$('#agoodAbout').text(data.about);
	$('#agoodDesxml').text(data.desxml);
	});
}

function get_goods()
{
	$.getJSON('./json.jsp?pages=Index', function(data){
	for(var i = 0;i < data.length; i++) {
	$("#palist").append(xuanran($("#list").clone(),data[i]));
	}
	$("#list").hide();
	$("#ppp").show();
	$("#palist").show();
	
	
})
bar("bar_home");
};
function xuanran(list,oneData){
	list.find("h4").text(oneData.name);
	list.find("p").text(oneData.about);
	list.find("img").attr("src","./file.jsp?pages=File&&getimg="+oneData.alias+".jpg");
	list.find("#price").text("现价")
	.next().text("￥"+oneData.price+"元")
	.next().text("原价: 40 元");
	list.attr('alias',oneData.alias);
	list.find("#updateGoods").attr("href","javascript:window.pageManager.go('agoods>"+oneData.alias+"');");
	var $toast = $('#toast');
	list.find("#delGoods").on('click',function(){
		
		self=$(this);
		$.getJSON('./json.jsp?pages=Admin&&delGoods='+self.parent().parent().attr("alias"), function(mydata){
		//alert(mydata[0]);
			if(mydata[0]<=0)
			{
			$('#toast').find('.weui-toast__content').text("删除失败");
			}
		else
			{
			self.parent().parent().remove();
			$('#toast').find('.weui-toast__content').text("删除成功");
			}
		});
		
		
		 // toast
        if ($toast.css('display') != 'none') return;

        $toast.fadeIn(100);
        setTimeout(function () {
            $toast.fadeOut(100);
        }, 2000);

		
	});
	return list;
}
function mylocal(num)
{
	
	 var url = location.hash.indexOf('#') === 0 ? location.hash : '#';
	 var str=new String();   
	  str=url
	var arr=new Array();     
	  
	arr=str.split('>');//注split可以用字符或字符串分割
	return arr[num];
}
function buybus()
{
	if(mylocal(1))
		{
	$.getJSON('./json.jsp?pages=BuyBus&&addAlias='+mylocal(1), function(data){
		//location.hash=mylocal(0);//备用替换下句不可后退
		buybus_xuanran();
		//setTimeout("location.hash=mylocal(0);",1);
		});
	
		}
	else
		{
		buybus_xuanran();
		}
	bar("bar_buybus");
}

function buybus_xuanran()
{
	var my_content;
	var this_content;
	$.getJSON('./json.jsp?pages=BuyBus', function(data){
		my_content= $("#buybus_parent").find("#buybus_child:first-child");
		$("#buybus_parent").text(" ");
		for(var i = 0;i < data.length; i++) {
			this_content=my_content.clone();
			this_content.find(".weui-form-preview__item").find(".weui-form-preview__label").text(data[i].name)
			.next().find("#the_price").text(data[i].price).parent().parent().next().text(data[i].about);
			my_count=this_content.find("#buts").find(".buybus_count");
			my_count.text(data[i].count);
			this_content.find("#buts").attr("alias",data[i].alias).find(".weui-btn_warn").on(
					'click',
					function() {
						//$.ajaxSettings.async = false; 
						self=$(this);
						$.getJSON('./json.jsp?pages=BuyBus&&delAlias='+self.parent().attr("alias"), function(mydata){
							if(mydata[0]==1)
						self.parent().parent().remove();
							buybus_total();
						});				
});
			plus=this_content.find("#buts").find(":first-child");
			decre=plus.next().next().next();
			decre.on(
					'click',
					function() {
						//$.ajaxSettings.async = false; 
						self=$(this);
						$.getJSON('./json.jsp?pages=BuyBus&&countAlias='+self.parent().attr("alias")+'&&countPlus=no', function(mydata){
							c=self.prev().prev();
							if(mydata[0]==1)
						if((c.text())*1>1)
							{
							c.text(c.text()*1-1);
							buybus_total();
							}
						});				
});
	
			plus.on(
					'click',
					function() {
						//$.ajaxSettings.async = false; 
						self=$(this);
						$.getJSON('./json.jsp?pages=BuyBus&&countAlias='+self.parent().attr("alias")+'&&countPlus=yes', function(mydata){
							if(mydata[0]==1)
							{
					c=self.next();
						c.text(c.text()*1+1);
						buybus_total();
							}
						});				
});
			$("#buybus_parent").prepend(this_content);
			this_content=null;
		}
		$("#buybus_parent").show();
		buybus_total();
		$("#clear_buybus").on(
				'click',
				function() {
					$.getJSON('./json.jsp?pages=Orders&&buy=w',function(data){
						//if(data[0]!=0)
					window.pageManager.go('aorder>'+data[0]);
				});
				});
		});
	//$("#buybus_parent #buybus_child:first-child").hide();
}
function buybus_total()
{
	var cou=0;
	the_parent=$("#buybus_parent #buybus_child")
	the_parent.each( function(){
        child=$(this);
        price=child.find("#the_price").text()*1;
        count=child.find(".buybus_count").text()*1;
       cou=cou*1+price*count;
       //alert(cou);
       
    });
	$("#total").text(cou*1);
}

function orders()
{
	var orders_goods=$('#orders_goods').find('.weui-form-preview__item').clone();
	$('#orders_goods').find('.weui-form-preview__item').remove();
	var one_orders=$('#one_orders').clone();
	//var content=$('#one_orders').clone();
	var parent=$('#orders_parent');
	$('#one_orders').remove();
	$.getJSON('./json.jsp?pages=Orders', function(bigdata){
		data=bigdata[0];
		var f;
		var k;
		 $.each(data,function(num,value){
			 //alert(num);
			 //alert(value[0].name);
			 f=one_orders.clone();
			 f.find("#orders_num").text(num);
			 var result=0;
			 for(var i = 0;i < value.length; i++) {
				 k=orders_goods.clone();
				 k.find("#orders_count").text(value[i].count);
				 k.find("#orders_price").text(value[i].price);
				 k.find("#orders_name").text(value[i].name);
				 f.find("#orders_goods").append(k);
				 result=value[i].price*value[i].count+result*1;
			 }
			 f.find("#orders_total").text(result);
			 del=f.find("#orders_del");
			 del.next().on(
						'click',
						function() {
							self=$(this);
							window.pageManager.go("aorder>"+self.parent().parent().find("#orders_num").text());
						});
			 del.on(
						'click',
						function() {
							//$.ajaxSettings.async = false; 
							self=$(this);
							my_parent=self.parent().parent();
							//alert(my_parent.find("#orders_num").text());
							$.getJSON('./json.jsp?pages=Orders&&delNum='+my_parent.find("#orders_num").text(), function(mydata){
								if(mydata[0]==1)
							{
									my_parent.next().remove();
									my_parent.remove();
							}
							});				
	});
			 parent.prepend("<br>");
			 parent.prepend(f);
		 });
		// parent.append(content);
	});
	bar("bar_orders");
}
function aorder(){
	$.getJSON('./json.jsp?pages=Orders&&num='+mylocal(1), function(bigvalue){
		value=bigvalue[0];
		cool=bigvalue[1];
		temp=$("#aorder_goods").find(".weui-form-preview__item");
		aorder_goods=temp.clone();
		temp.remove();
		 $("#aorder_num").text(value[0].num);
		 $("#aorder_time").text(cool[0].time);
		 $("#aorder_recname").text(cool[0].recname);
		 $("#aorder_recnum").text(cool[0].recnum);
		 $("#aorder_recaddress").text(cool[0].recaddress);
		 var result=0;
		 for(var i = 0;i < value.length; i++) {
			 k=aorder_goods.clone();
			 k.find("#aorder_count").text(value[i].count);
			 k.find("#aorder_price").text(value[i].price);
			 k.find("#aorder_name").text(value[i].name);
			 result=value[i].price*value[i].count+result*1;
			 $("#aorder_goods").append(k);
		 }
		$("#aorder_total").text(result);	
	});
	
	
	// android
    $(function(){
        var $address = $('#address');
        var $androidMask = $address.find('.weui-mask');
        var $add_input = $('#add_input');
        $("#change_address").on('click', function(){
        	recname=$("#aorder_recname").text();
   		 recnum=$("#aorder_recnum").text();
   		 recaddress=$("#aorder_recaddress").text();
   		 
        	$address.find("#address_recname").attr('value',recname);
        	$address.find("#address_recnum").attr('value',recnum);
        	$address.find("#address_recaddress").text(recaddress);
            $address.fadeIn(200);
            $add_input.on('click',function(){
            	inputname=$address.find("#address_recname").val();
            	inputnum=$address.find("#address_recnum").val();
            	inputaddress=$address.find("#address_recaddress").val();
            	$.getJSON('./json.jsp?pages=Orders&&num='+mylocal(1),{"recname":inputname,"recnum":inputnum,"recaddress":inputaddress,"updateaddress":"true"}, function(boolvalue){
            	//alert(boolvalue);
            	if((boolvalue[0]*1)==1)
            		{
            	$("#aorder_recname").text(inputname);
          		$("#aorder_recnum").text(inputnum);
          		$("#aorder_recaddress").text(inputaddress);
            		}
            	});
            	$address.fadeOut(200);
            });
            $androidMask.on('click',function () {
                $address.fadeOut(200);
            });
        });
    });
    bar("bar_orders");
}

function mine()
{
	$.getJSON('./json.jsp?pages=Mine', function(defrec){
		 $("#default_recname").text(defrec.recname);
		 $("#default_recnum").text(defrec.recnum);
		 $("#default_recaddress").text(defrec.recaddress);
	});
	
	$(function(){
        var $address = $('#address');
        var $androidMask = $address.find('.weui-mask');
        var $add_input = $('#add_input');
        $("#default_button").on('click', function(){
        	recname=$("#default_recname").text();
   		 recnum=$("#default_recnum").text();
   		 recaddress=$("#default_recaddress").text();
   		 
        	$address.find("#address_recname").attr('value',recname);
        	$address.find("#address_recnum").attr('value',recnum);
        	$address.find("#address_recaddress").text(recaddress);
            $address.fadeIn(200);
            $add_input.on('click',function(){
            	inputname=$address.find("#address_recname").val();
            	inputnum=$address.find("#address_recnum").val();
            	inputaddress=$address.find("#address_recaddress").val();
            	$.getJSON('./json.jsp?pages=Mine&&num='+mylocal(1),{"recname":inputname,"recnum":inputnum,"recaddress":inputaddress}, function(boolvalue){
            	//alert(boolvalue);
            	if((boolvalue*1)>=1)
            		{
            	$("#default_recname").text(inputname);
          		$("#default_recnum").text(inputnum);
          		$("#default_recaddress").text(inputaddress);
            		}
            	});
            	$address.fadeOut(200);
            });
            $androidMask.on('click',function () {
                $address.fadeOut(200);
            });
        });
    });
	
	
	bar("bar_mine");	
}
function bar(bar_name)
{
	$("#"+bar_name).addClass('weui-bar__item_on').siblings(
	'.weui-bar__item_on').removeClass(
	'weui-bar__item_on');	
}
