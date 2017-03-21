<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<title>馨灵购物商城-电子商城</title>
<link rel="stylesheet" href="/res/css/style.css" />
<script src="/res/js/jquery.js"></script>
<script src="/res/js/com.js"></script>
<script type="text/javascript">
	//登陆
	function login() {
		// returnUrl作为登陆后回显的页面
		window.location.href = "/shopping/login.shtml?returnUrl=" + window.location.href;
	}
	
	function logout(){
		window.location.href = "/shopping/logout.shtml?returnUrl=" + window.location.href;
	}
	
	function profile(){
		window.location.href = "/buyer/index.shtml";
	}
</script>

<script type="text/javascript">
	$(function() {
		
		function submit(){
			$("#f_con").attr("action","/product/display/list.shtml");
			setTimeout(function(){
				$("#f_con").submit();
			}, 50);
		}
		
		// 筛选价格
		$("a[accesskey=a_price]").click(function() {
			var priceName = $(this).attr("title");
			var priceRan = $(this).attr("title").split("-");
			var len = priceRan.length;
			if (len == 2) {
				var minPrice = priceRan[0];
				var maxPrice = priceRan[1];
				$(":hidden[name=minPrice]").val(minPrice);
				$(":hidden[name=maxPrice]").val(maxPrice);
				$(":hidden[name=priceName]").val(priceName);
			} else if (len == 1) {
				var minPrice = priceRan[0];
				$(":hidden[name=minPrice]").val(minPrice);
				$(":hidden[name=priceName]").val("大于" + priceName);
			}
			// window.location.href = '/product/display/list.shtml';
			
			submit();
		});
		
		// 筛选品牌
		$("a[accesskey=a_brand]").click(function() {
			$(":hidden[name=brandId]").val($(this).attr("id"));
			$(":hidden[name=brandName]").val($(this).attr("title"));
		
			submit();
		});
		
		// 筛选类型
		$("a[accesskey=a_type]").click(function() {
			$(":hidden[name=typeId]").val($(this).attr("id"));
			$(":hidden[name=typeName]").val($(this).attr("title"));
		
			submit();
		});
		
		// 筛选材质
		$("a[accesskey=a_feature]").click(function() {
			$(":hidden[name=featureId]").val($(this).attr("id"));
			$(":hidden[name=featureName]").val($(this).attr("title"));
		
			submit();
		});
		
		
		// 重置
		$("#filterReset").click(function(){
			$("#f_con :hidden").removeAttr("value");
			submit();
		});
		
		// 去除已选条件
		$("cite").click(function(){
			var citeId = $(this).attr("id");
			if (citeId == 'price'){
				$(":hidden[name=minPrice]").removeAttr("value");
				$(":hidden[name=maxPrice]").removeAttr("value");
				$(":hidden[name=priceName]").removeAttr("value");
			} else {
				$(":hidden[name=" + citeId + "Id]").removeAttr("value");
				$(":hidden[name=" + citeId + "Name]").removeAttr("value");
			}
			
			submit();
		});
		
		$("a[accesskey=orderBy]").click(function(){
			$(":hidden[name=orderByDesc]").val($(this).attr("class"));
			submit();
		});
		
	});
</script>
</head>

<body>
	<div class="bar">
		<form action="" method="POST" id="f_con" >
			<input type="hidden" name="brandId" value="${brandId}" />
			<input type="hidden" name="brandName" <c:if test="${brandName != null and brandName != '' }">value="${brandName}"</c:if> />
			
			<input type="hidden" name="typeId" value="${typeId}" />
			<input type="hidden" name="typeName" <c:if test="${typeName != null and typeName != '' }">value="${typeName}"</c:if> />
			
			<input type="hidden" name="minPrice" <c:if test="${minPrice != null}">value="${minPrice}"</c:if> />
			<input type="hidden" name="maxPrice" <c:if test="${maxPrice != null}">value="${maxPrice}"</c:if> />
			<input type="hidden" name="priceName" <c:if test="${priceName != null and priceName != '' }">value="${priceName}"</c:if> />
			
			<input type="hidden" name="featureId" <c:if test="${featureId != null}">value="${featureId}"</c:if>/>
			<input type="hidden" name="featureName" <c:if test="${featureName != null and featureName != ''}">value="${featureName}"</c:if>/>
			
			<input type="hidden" name="orderByDesc" <c:if test="${orderByDesc != null}">value="${orderByDesc}"</c:if>/>
		</form>
	
		<div class="bar_w">
			<p class="l">
				<span class="l"> 收藏本网站！北京<a href="" title="更换">[更换]</a>
				</span>
			</p>
			<ul class="r uls">
			
				<li class="dev">你好，<c:if test="${sessionScope.BUYER_ID != null}">${sessionScope.BUYER_ID.username}，</c:if>欢迎来到馨灵购物商城！
				</li>
				<c:if test="${!sessionScope.isLogin}">
					<li class="dev"><a href="javascript:void(0)" onclick="login()"
						title="登陆">[登陆]</a></li>
				</c:if>
				<c:if test="${sessionScope.isLogin}">
					<li class="dev"><a href="javascript:void(0)"
						onclick="profile()" title="个人中心">[个人中心]</a>
					</li>
					<li class="dev"><a href="javascript:void(0)" onclick="logout()"
						title="退出">[退出]</a>
					</li>
				</c:if>
				<li class="dev"><a href="javascript:void(0)" onclick="myOrder()" title="我的订单">我的订单</a></li>
				<li class="dev"><a href="" title="在线客服">在线客服</a></li>
				<li class="dev after"><a href="" title="English">English</a></li>
			</ul>
		</div>
	</div>
	<div class="w loc">
		<div class="h-title">
			<div class="h-logo">
				<a href="/"><img
					src="/res/img/pic/logo-1.png" style="width: 180px;height: 30px;"/></a>
			</div>
			<div class="h-search">
				<input type="text" />
				<div class="h-se-btn">
					<a href="">搜索</a>
				</div>
			</div>
		</div>
		<dl id="cart" class="cart r">
			<dt>
				<a href="" title="结算">结算</a>购物车:<b id="">5</b>件
			</dt>
			<dd class="hidden">
				<p class="alg_c hidden">购物车中还没有商品，赶紧选购吧！</p>
				<h3 title="最新加入的商品">最新加入的商品</h3>
				<ul class="uls">
					<li><a href=""
						title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
							<img src="${basePath}images/1/9/4/3/2/6/5/0/4/5/20151109105706507792.jpg"
							alt="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元" />
					</a>
						<p class="dt">
							<a href="#"
								title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">依琦莲2014瑜伽服套装新
								瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价
								千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元</a>
						</p>
						<p class="dd">
							<b><var>¥128</var><span>x1</span></b> <a
								href="javascript:void(0);" title="删除" class="del">删除</a>
						</p></li>
					<li><a href=""
						title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
							<img src="${basePath}images/1/0/1/2/9/7/1/7/4/9/20151107043908141818.jpg"
							alt="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元" />
					</a>
						<p class="dt">
							<a href=""
								title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">依琦莲2014瑜伽服套装新
								瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价
								千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元</a>
						</p>
						<p class="dd">
							<b><var>¥128</var><span>x1</span></b> <a
								href="javascript:void(0);" title="删除" class="del">删除</a>
						</p></li>
					<li><a href=""
						title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
							<img src="${basePath}images/1/2/1/3/0/0/0/0/5/7/20151108033608544084.jpg"
							alt="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元" />
					</a>
						<p class="dt">
							<a href=""
								title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">依琦莲2014瑜伽服套装新
								瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价
								千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元</a>
						</p>
						<p class="dd">
							<b><var>¥128</var><span>x1</span></b> <a
								href="javascript:void(0);" title="删除" class="del">删除</a>
						</p></li>
					<li><a href=""
						title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
							<img src="${basePath}images/2/9/9/6/0/8/1/8/2/20151108112449877831.jpg"
							alt="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元" />
					</a>
						<p class="dt">
							<a href=""
								title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">依琦莲2014瑜伽服套装新
								瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价
								千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元</a>
						</p>
						<p class="dd">
							<b><var>¥128</var><span>x1</span></b> <a
								href="javascript:void(0);" title="删除" class="del">删除</a>
						</p></li>
					<li><a href=""
						title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
							<img src="${basePath}images/1/7/2/5/5/7/0/7/9/6/20151108112111666086.jpg"
							alt="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元" />
					</a>
						<p class="dt">
							<a href=""
								title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">依琦莲2014瑜伽服套装新
								瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价
								千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元</a>
						</p>
						<p class="dd">
							<b><var>¥128</var><span>x1</span></b> <a
								href="javascript:void(0);" title="删除" class="del">删除</a>
						</p></li>
				</ul>
				<div>
					<p>
						共<b>5</b>件商品&nbsp;&nbsp;&nbsp;&nbsp;共计<b class="f20">¥640.00</b>
					</p>
					<a href="" title="去购物车结算" class="inb btn120x30c">去购物车结算</a>
				</div>
			</dd>
		</dl>
	</div>

	<div class="w ofc">
		<div class="l wl">
			<h2 class="h2 h2_l">
				<em title="销量排行榜">销量排行榜</em><cite></cite>
			</h2>
			<div class="box bg_white">
				<ul class="uls x_50x50">
					<li><var class="sfont">1</var> <a href="" title="富一代"
						 class="pic"><img src="${basePath}images/1/9/1/7/8/1/6/2/0/6/20151107044538743161.jpg"
							alt="依琦莲2014瑜伽服套装新" /></a>
						<dl>
							<!-- dt 8个文字+... -->
							<dt>
								<a href="" title="依琦莲2014瑜伽服套装新" >依琦莲2014瑜伽服套装新</a>
							</dt>
							<dd class="orange">￥120 ~ ￥150</dd>
						</dl></li>
					<li><var class="sfont">2</var> <a href="" title="富一代"
						 class="pic"><img src="${basePath}images/1/8/2/9/9/5/2/2/0/5/20151107052235937112.jpg"
							alt="依琦莲2014瑜伽服套装新" /></a>
						<dl>
							<!-- dt 8个文字+... -->
							<dt>
								<a href="" title="依琦莲2014瑜伽服套装新" >依琦莲2014瑜伽服套装新</a>
							</dt>
							<dd class="orange">￥120 ~ ￥150</dd>
						</dl></li>
					<li><var class="sfont">3</var> <a href="" title="富一代"
						 class="pic"><img src="${basePath}images/1/8/4/6/4/2/5/0/4/3/20151107053912166057.jpg"
							alt="依琦莲2014瑜伽服套装新" /></a>
						<dl>
							<!-- dt 8个文字+... -->
							<dt>
								<a href="" title="依琦莲2014瑜伽服套装新" >依琦莲2014瑜伽服套装新</a>
							</dt>
							<dd class="orange">￥120 ~ ￥150</dd>
						</dl></li>
					<li><a href="" title="富一代" class="pic"><img
							src="${basePath}images/2/1/2/7/7/0/7/8/6/6/20151109110040872909.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
						<dl>
							<!-- dt 8个文字+... -->
							<dt>
								<a href="" title="依琦莲2014瑜伽服套装新" >依琦莲2014瑜伽服套装新</a>
							</dt>
							<dd class="orange">￥120 ~ ￥150</dd>
						</dl></li>
					<li><a href="" title="富一代"  class="pic"><img
							src="${basePath}images/4/5/5/6/7/9/4/6/6/20151107051003391312.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
						<dl>
							<!-- dt 8个文字+... -->
							<dt>
								<a href="" title="依琦莲2014瑜伽服套装新" >依琦莲2014瑜伽服套装新</a>
							</dt>
							<dd class="orange">￥120 ~ ￥150</dd>
						</dl></li>
					<li><a href="" title="富一代" class="pic"><img
							src="${basePath}images/1/6/5/6/0/5/4/8/5/5/20151107051042217051.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
						<dl>
							<!-- dt 8个文字+... -->
							<dt>
								<a href="" title="依琦莲2014瑜伽服套装新" >依琦莲2014瑜伽服套装新</a>
							</dt>
							<dd class="orange">￥120 ~ ￥150</dd>
						</dl></li>
				</ul>
			</div>

			<h2 class="h2 h2_l mt">
				<em title="我的浏览记录">我的浏览记录</em><cite></cite>
			</h2>
			
				<div class="box bg_white">
					<ul class="uls x_50x50">
						<c:forEach items="${sessionScope.histProductList}" var="history">
							<li>
								<a href="javascript:void(0)" title="富一代"  class="pic" onclick="window.open('/product/display/detail.shtml?productId=${history.id}')">
									<img src="${basePath}${history.url}" alt="${history.name}" />
								</a>
								<dl>
									<!-- dt 8个文字+... -->
									<dt>
										<a href="javascript:void(0)" title="依琦莲2014瑜伽服套装新" >
											<c:choose> 
										      <c:when test="${fn:length(history.name) > 10}"> 
											      <c:out value="${fn:substring(history.name, 0, 10)}..." /> 
										      </c:when> 
										      <c:otherwise> 
											      <c:out value="${history.name}" /> 
										      </c:otherwise>
										    </c:choose>
										</a>
									</dt>
									<dd class="orange">￥120 ~ ￥150</dd>
								</dl>
							</li>
						</c:forEach>
					</ul>
				</div>
			
			

			<h2 class="h2 h2_l mt">
				<em title="商家精选">商家精选</em><cite></cite>
			</h2>
			<img src="/res/img/addr.jpg" alt="" width="200px;"/>
			<img src="/res/img/pic/ad200x75.jpg" alt="" />
		</div>
		<div class="r wr">
			<h2 class="h2 h2_r rel">
				<samp></samp>
				<em title="热卖推荐">&nbsp;&nbsp;&nbsp;热卖推荐</em>
			</h2>
			<div class="box bg_white">
				<ul class="uls i_150x150 x4_150x150">
					<li><a href="" title="富一代" 
						class="pic"><img src="	${basePath}images/5/6/6/5/4/6/5/3/7/20151107053350597118.jpg"
							alt="依琦莲2014瑜伽服套装新" /></a>
						<dl>
							<dt>
								<a href="" title="依琦莲2014瑜伽服套装新"
									>依琦莲2014瑜伽服套装新</a>
							</dt>
							<dd class="h40">依琦莲2014瑜伽服套装新！</dd>
							<dd class="orange">￥120 ~ ￥150</dd>
							<dd>
								<a href="" title="立即抢购" class="inb btn70x21 mr">立即抢购</a>
							</dd>
						</dl></li>
					<li><a href="" title="富一代" 
						class="pic"><img src="	${basePath}images/2/1/0/6/5/3/5/0/1/7/20151107052801101702.jpg"
							alt="依琦莲2014瑜伽服套装新" /></a>
						<dl>
							<dt>
								<a href="" title="依琦莲2014瑜伽服套装新"
									>依琦莲2014瑜伽服套装新</a>
							</dt>
							<dd class="h40">依琦莲2014瑜伽服套装新！</dd>
							<dd class="orange">￥120 ~ ￥150</dd>
							<dd>
								<a href="" title="立即抢购" class="inb btn70x21 mr">立即抢购</a>
							</dd>
						</dl></li>
					<li>
						<a href="" title="富一代"
						class="pic">
						<img src="${basePath}images/6/1/4/4/4/8/1/8/9/20151113111027621605.jpg"
							alt="依琦莲2014瑜伽服套装新" /></a>
						<dl>
							<dt>
								<a href="" title="依琦莲2014瑜伽服套装新"
									>依琦莲2014瑜伽服套装新</a>
							</dt>
							<dd class="h40">依琦莲2014瑜伽服套装新！</dd>
							<dd class="orange">￥120 ~ ￥150</dd>
							<dd>
								<a href="" title="立即抢购" class="inb btn70x21 mr">立即抢购</a>
							</dd>
						</dl></li>
					<li><a href="" title="富一代" 
						class="pic"><img src="	${basePath}images/1/8/7/3/5/2/8/7/4/1/20151109110825673948.jpg"
							alt="依琦莲2014瑜伽服套装新" /></a>
						<dl>
							<dt>
								<a href="" title="依琦莲2014瑜伽服套装新"
									>依琦莲2014瑜伽服套装新</a>
							</dt>
							<dd class="h40">依琦莲2014瑜伽服套装新！</dd>
							<dd class="orange">￥120 ~ ￥150</dd>
							<dd>
								<a href="productDetail.jsp" title="立即抢购" class="inb btn70x21 mr">立即抢购</a>
							</dd>
						</dl></li>
				</ul>
			</div>





			<h2 class="h2 h2_filter mt">
				<em title="商品筛选">商品筛选</em><cite><a href="javascript:void(0);"
					id="filterReset" title="重置筛选条件">重置筛选条件</a></cite>
			</h2>
			
			<!-- form begin  -->
				
				<ul class="uls filter">
					<li>
						<p class="sel">
							<c:forEach items="${queryMap}" var="entry" varStatus="status">
								<c:if test="${status.index == 0}">
									<b>已选条件：</b>
								</c:if>
								
									<a href="javascript:void(0);" id="${entry.value.key}"> 
										<em>${entry.key}：</em>
										${entry.value.name}
										<cite title="关闭此筛选条件" id="${entry.value.key}">X</cite>
									</a>
							</c:forEach>
						</p>
					</li>
					
					<c:if test="${displayBrand}">
						<li>
							<b>品牌：</b>
							<p>
								<a href="javascript:void(0);" id="no_brand"  title="不限" class="here">不限</a>
								<c:forEach items="${brandList}" var="item">
									<a href="javascript:void(0);" title="${item.name }" id="${item.id }" accesskey="a_brand">
										${item.name}
									</a>
								</c:forEach>
							</p>
						</li>
					</c:if>
					
					
					<c:if test="${displayType}">
						<li>
							<b>类型：</b>
							<p>
								<a href="javascript:void(0);" id="no_type" title="不限" class="here">不限</a>
								<c:forEach items="${typeList}" var="item">
									<a href="javascript:void(0);" title="${item.name}" id="${item.id}" accesskey="a_type">${item.name}</a>
								</c:forEach>
							</p>
						</li>
					</c:if>
					
					<c:if test="${displayFeature}">
						<li>
							<b>材质：</b>
							<p>
								<a href="javascript:void(0);" id="no_feature" title="不限" class="here">不限</a>
								<c:forEach items="${featureList}" var="item">
									<a href="javascript:void(0);" title="${item.name}" id="${item.id}" accesskey="a_feature">${item.name}</a>
								</c:forEach>
							</p>
						</li>
					</c:if>


					<c:if test="${displayPrice}">
						<li>
							<b>价格：</b>
							<p>
								<a href="javascript:void(0);" id="no_price" class="here" accesskey="a_price">不限</a> 
								<a href="javascript:void(0);" title="0-99" accesskey="a_price">0-99</a>
								<a href="javascript:void(0);" title="100-199" accesskey="a_price">100-199</a>
								<a href="javascript:void(0);" title="200-299" accesskey="a_price">200-299</a>
								<a href="javascript:void(0);" title="300-399" accesskey="a_price">300-399</a>
								<a href="javascript:void(0);" title="400-499" accesskey="a_price">400-499</a>
								<a href="javascript:void(0);" title="500" accesskey="a_price">500以上</a>
							</p>
						</li>
					</c:if>

					<li>
						<b>适用人群：</b>
						<p>
							<a href="javascript:void(0);" title="不限" class="here">不限</a>
							<a href="javascript:void(0);" title="男士">男士</a> 
							<a href="javascript:void(0);" title="女士">女士</a> 
							<a href="javascript:void(0);" title="儿童">儿童</a> 
							<a href="javascript:void(0);" title="中性">中性</a>
						</p>
					</li>
				</ul>
				<div class="sort_type">
					<c:if test="${orderByDesc == 'sales'}">
						<a href="javascript:void(0);" accesskey="orderBy" title="销量"  class="sales" style="color: red;font-weight: bolder;">销量</a>
					</c:if>
					<c:if test="${orderByDesc != 'sales'}">
						<a href="javascript:void(0);" accesskey="orderBy" title="销量"  class="sales" style="font-weight: bolder;">销量</a>
					</c:if>
					<c:if test="${orderByDesc == 'price'}">
						<a href="javascript:void(0);" accesskey="orderBy" title="价格" class="price" style="color: red;font-weight: bolder;">价格</a>
					</c:if>
					<c:if test="${orderByDesc != 'price'}">
						<a href="javascript:void(0);" accesskey="orderBy" title="价格" class="price" style="font-weight: bolder;">价格</a>
					</c:if>
					<c:if test="${orderByDesc == 'time'}">
						<a href="javascript:void(0);" accesskey="orderBy" title="上架时间" class="time" style="color: red;font-weight: bolder;">上架时间</a>
					</c:if>
					<c:if test="${orderByDesc != 'time'}">
						<a href="javascript:void(0);" accesskey="orderBy" title="上架时间" class="time" style="font-weight: bolder;">上架时间</a>
					</c:if>
				</div>
			<!-- form end  -->
			



			<!-- 商品列表开始 -->
			<div class="mt ofc">
				<ul class="uls i_150x150 x4_150x150b">
					<c:forEach items="${pageBean.elements}" var="item" varStatus="status">
						<li>
							<a href="javascript:void(0)" title="瑜伽服" class="pic" onclick="window.open('/static/product/${item.id}.html')">
								<img src="${basePath}${item.url}" alt="瑜伽服" />
							</a>
							<dl>
								<!-- dt 10个文字+... -->
								<dt>
									<a href="javascript:void(0)" title="${item.name}" onclick="window.open('/static/product/${item.id}.html')">
										${item.name}
									</a>
								</dt>
								<!-- dt 25个文字+... -->
								<dd class="h40">四川有货</dd>
								<dd class="orange">￥${item.price}</dd>
								<dd>总销售量 ${item.sales}件</dd>
								<dd>
									<a href="" title="加入购物车" class="inb btn70x21 mr" onclick="window.open('/static/product/${item.id}.html')">加入购物车</a>
								</dd>
							</dl>
							<c:choose>
								<c:when test="${item.isHot}">
									<img src="/res/img/pic/hot.gif" alt="热门" class="type" />
								</c:when>
								<c:when test="${item.isNew}">
									<img src="/res/img/pic/new.gif" alt="新品" class="type" />
								</c:when>
							</c:choose>
						</li>
					</c:forEach>
				</ul>
				
				
				<div class="page pb15">
					<span class="r inb_a page_b">
					 	<c:forEach items="${pageView}" var="item">
					 		${item}
					 	</c:forEach>
					</span>
				</div>
			</div>
		</div>
	</div>
	<div class="mode">
		<div class="tl"></div>
		<div class="tr"></div>
		<ul class="uls">
			<li class="first"><span class="guide"></span>
				<dl>
					<dt title="购物指南">购物指南</dt>
					<dd>
						<a href="" title="购物流程">购物流程</a>
					</dd>
					<dd>
						<a href="" title="购物流程">购物流程</a>
					</dd>
					<dd>
						<a href=""  title="联系客服">联系客服</a>
					</dd>
					<dd>
						<a href=""  title="联系客服">联系客服</a>
					</dd>
				</dl></li>
			<li><span class="way"></span>
				<dl>
					<dt title="支付方式">支付方式</dt>
					<dd>
						<a href="" title="货到付款">货到付款</a>
					</dd>
					<dd>
						<a href="" title="在线支付">在线支付</a>
					</dd>
					<dd>
						<a href="" title="分期付款">分期付款</a>
					</dd>
					<dd>
						<a href="" title="分期付款">分期付款</a>
					</dd>
				</dl></li>
			<li><span class="help"></span>
				<dl>
					<dt title="配送方式">配送方式</dt>
					<dd>
						<a href="" title="上门自提">上门自提</a>
					</dd>
					<dd>
						<a href="" title="上门自提">上门自提</a>
					</dd>
					<dd>
						<a href="" title="上门自提">上门自提</a>
					</dd>
					<dd>
						<a href="" title="上门自提">上门自提</a>
					</dd>
				</dl></li>
			<li><span class="service"></span>
				<dl>
					<dt title="售后服务">售后服务</dt>
					<dd>
						<a href=""  title="售后策略">售后策略</a>
					</dd>
					<dd>
						<a href=""  title="售后策略">售后策略</a>
					</dd>
					<dd>
						<a href=""  title="售后策略">售后策略</a>
					</dd>
					<dd>
						<a href=""  title="售后策略">售后策略</a>
					</dd>
				</dl></li>
			<li><span class="problem"></span>
				<dl>
					<dt title="特色服务">特色服务</dt>
					<dd>
						<a href=""  title="夺宝岛">夺宝岛</a>
					</dd>
					<dd>
						<a href=""  title="夺宝岛">夺宝岛</a>
					</dd>
					<dd>
						<a href=""  title="夺宝岛">夺宝岛</a>
					</dd>
					<dd>
						<a href=""  title="夺宝岛">夺宝岛</a>
					</dd>
				</dl></li>
		</ul>
	</div>
</body>
</html>