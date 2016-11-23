/* Created by jf on 2015/9/11.
 * Modified by bear on 2016/9/7.
 */
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
	$.getJSON('/json.jsp?pages=Index&&alias='+mylocal(1), function(data){
	$('#goodsname').text(data.name);
	$('#per').next().text(data.about);
	$('#des').next().text(data.desxml);
	$('#agoodsbutton').attr("href","javascript:window.pageManager.go('buybus>"+data.alias+"');");
	});
}

function get_goods()
{
	$.getJSON('/json.jsp?pages=Index', function(data){
	for(var i = 0;i < data.length; i++) {
	$("#palist").append(xuanran($("#list").clone(),data[i]));
	}
	$("#list").hide();
	$("#ppp").show();
	$("#palist").show();
	
	
})
};
function xuanran(list,oneData){
	list.find("h4").text(oneData.name);
	list.find("p").text(oneData.about);
	list.find("#price").text("现价")
	.next().text("￥"+oneData.price+"元")
	.next().text("原价: 40 元");
	list.attr("href","javascript:window.pageManager.go('agoods>"+oneData.alias+"');");
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
	$.getJSON('/json.jsp?pages=BuyBus&&addAlias='+mylocal(1), function(data){
		alert(data[0]);
		alert(mylocal(1));
		});
}