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


<title>馨灵购物商城-商品详情页</title>
<link rel="stylesheet" href="/res/css/style.css" />
<script src="/res/js/jquery.js"></script>
<script type="text/javascript" src="/res/js/com.js"></script>
<style type="text/css">
.changToRed {
	border: 2px solid #e4393c;
	padding: 2px 4px;
	float: left;
	margin-right: 4px;
	text-decoration: none;
}

.changToWhite {
	border: 1px solid #ccc;
	padding: 2px 4px;
	float: left;
	margin-right: 4px;
	text-decoration: none;
}

.not-allow {
	cursor: not-allowed;
	color: #999;
	border: 1px dashed #ccc;
	padding: 2px 4px;
	float: left;
	margin-right: 4px;
	text-decoration: none;
}
</style>
</head>
<script type="text/javascript">
	//加入购物车
	function addCart() {
		alert("添加购物车成功!");
	}
	//立即购买
	function buy() {
		window.location.href = 'cart.jsp';
	}
</script>

<script type="text/javascript">
	$(function() {
		
		$.ajax({
			url: '/product/product/history.shtml',
			method: 'POST',
			dataType: 'json',
			success: function(data){
				if(data != null){
					var historyList = data.histProductList;
					for(var i = 0; i < historyList.length; i++){
						var $li = $("<li></li>");
						
						var $img = $("<img></img>");
						$img.attr("src","${basePath}" + historyList[i].url);
						
						var $a1 = $("<a></a>");
						$a1.attr("class","pic");
						$a1.attr("onclick","window.open('/product/display/detail.shtml?productId=" + historyList[i].id + "')");
						$a1.attr("href","javascript:void(0)");
						$a1.html($img);
						
						
						
						var $a2 = $("<a></a>");
						$a2.attr("href","javascript:void(0)");
						$a2.attr("title",historyList[i].name);
						$a2.html(historyList[i].name);
						var $dt = $("<dt>").html($a2);
						var $dd = $("<dd>").attr("class","orange").html("￥" + historyList[i].price);
						var $dl = $("<dl>");
						
						$dl.append($dt);
						$dl.append($dd);
						
						$li.append($a1);
						$li.append($dl);
						
						$("#hist").append($li);
					}
					
					
				}
			}
		});
		
		var skuUpperLimit = 1; // 限购
		var skuId = 0; // 销售单元id

		// 1. 页面加载完毕后通过默认选择颜色id初始化默认选择的尺码id
		var colorId = $("#colors .changToRed").attr("title");
		var index = 0;
		// 改变标题
		var colorName = '';
		<c:forEach items="${skuCells}" var="sku" varStatus="status">
		if (colorId == '${sku.colorId }') {
			if (index == 0) {
				$("#" + "${sku.size }").attr("class", "changToRed").css(
						"cursor: pointer;");
				colorName = '${sku.color }';
				if ('均色' != colorName) {
					colorName += '色';
				}
				index++;

				//   巴巴价格,市场价格,运费,库存
				// -- 巴巴价格
				$(".word b").html("￥" + '${sku.skuPrice}');
				// -- 市场价格
				$(".word del").html("￥" + '${sku.marketPrice}');
				// -- 运费
				$("#deliveFee").html('${sku.deliveFee}' + "元");
				// -- 库存
				$("#stockInventory").html('${sku.stockInventory}');

				skuUpperLimit = '${sku.skuUpperLimit}';
				skuId = '${sku.id}';
			} else {
				$("#" + "${sku.size }").attr("class", "changToWhite").css(
						"cursor: pointer;");
				index++;
			}

		}
		</c:forEach>

		var fullName = $("#tl").html() + ' - ' + colorName;
		$("#tl").html(fullName);

		// 2. 点击颜色id时加载对应尺码id
		$("a[accesskey=color]").click(
				function() {
					$("#colors a").each(function() {
						$(this).attr("class", "changToWhite");
					});

					$(this).attr("class", "changToRed");
					var id = $(this).attr("title");

					var flag = 0;
					var colorName = '';
					<c:forEach items="${skuCells}" var="sku" varStatus="status">
					if (id == '${sku.colorId }') {
						if (flag == 0) {
							colorName = '${sku.color }';
							if ('均色' != colorName) {
								colorName += '色';
							}
							$("#" + "${sku.size }").attr("class", "changToRed")
									.css("cursor: pointer;");
							flag++;

							// 联动  巴巴价格,市场价格,运费,库存
							// -- 巴巴价格
							$(".word b").html("￥" + '${sku.skuPrice}');
							// -- 市场价格
							$(".word del").html("￥" + '${sku.marketPrice}');
							// -- 运费
							$("#deliveFee").html('${sku.deliveFee}' + "元");
							// -- 库存
							$("#stockInventory").html('${sku.stockInventory}');

							skuUpperLimit = '${sku.skuUpperLimit}';
							skuId = '${sku.id}';
						} else {
							$("#" + "${sku.size }").attr("class",
									"changToWhite").css("cursor: pointer;");
							flag++;
						}

					}
					</c:forEach>

					var fullName = '${product.name}' + ' - ' + colorName;
					$("#tl").html(fullName);

				});

		// -- 减少
		$("#sub").click(function() {
			var num = $("#num").val();
			if (num > 1) {
				$("#num").val(--num);
			} else {
				alert('购买数量不能小于0!');
			}
		});

		// -- 增加
		$("#add").click(function() {
			var num = $("#num").val();
			if (num < skuUpperLimit) {
				$("#num").val(++num);
			} else {
				alert('购买数量不能超过购买限制' + skuUpperLimit);
			}
		});

	});

	var skuUpperLimit; // 限购
	var skuId; // 销售单元id
	// 利用js
	function onSizeChange(target) {
		var targetClass = $(target).attr("class");
		if (targetClass != 'not-allow') {
			// 改编自身颜色
			$(target).siblings(".changToRed").attr("class", "changToWhite");
			$(target).attr("class", "changToRed");

			var size = $(target).attr("id");
			var colorId = $("#colors").find(".changToRed").attr("title");

			<c:forEach items="${skuCells}" var="sku" varStatus="status">
			if (colorId == '${sku.colorId }' && size == '${sku.size }') {
				// 联动  巴巴价格,市场价格,运费,库存
				// -- 巴巴价格
				$(".word b").html("￥" + '${sku.skuPrice}');
				// -- 市场价格
				$(".word del").html("￥" + '${sku.marketPrice}');
				// -- 运费
				$("#deliveFee").html('${sku.deliveFee}' + "元");
				// -- 库存
				$("#stockInventory").html('${sku.stockInventory}');

				skuUpperLimit = '${sku.skuUpperLimit}';
				skuId = '${sku.id}';
			}
			</c:forEach>

		}
	}
</script>
</head>


<body>
	<div class="bar">
		<div class="bar_w">
			<p class="l">
				<span class="l"> 收藏本网站！北京<a href="#" title="更换">[更换]</a>
				</span>
			</p>
			<ul class="r uls">
				<li class="dev">您好,欢迎来到馨灵购物商城！</li>
				<li class="dev"><a href="javascript:void(0)" onclick="login()"
					title="登陆">[登陆]</a></li>
				<li class="dev"><a href="javascript:void(0)"
					onclick="register()" title="免费注册">[免费注册]</a></li>
				<li class="dev"><a href="javascript:void(0)" onclick="logout()"
					title="退出">[退出]</a></li>
				<li class="dev"><a href="javascript:void(0)"
					onclick="myOrder()" title="我的订单">我的订单</a></li>
				<li class="dev"><a href="#" title="在线客服">在线客服</a></li>
				<li class="dev after"><a href="#" title="English">English</a></li>
			</ul>
		</div>
	</div>
	<div class="w loc">
		<div class="h-title">
			<div class="h-logo">
				<a href="http://localhost:8080"><img
					src="/res/img/pic/logo-1.png"  style="width: 180px;height: 30px;"/></a>
			</div>
			<div class="h-search">
				<input type="text" />
				<div class="h-se-btn">
					<a href="#">搜索</a>
				</div>
			</div>
		</div>
		<dl id="cart" class="cart r">
			<dt>
				<a href="#" title="结算">结算</a>购物车:<b id="">123</b>件
			</dt>
			<dd class="hidden">
				<p class="alg_c hidden">购物车中还没有商品，赶紧选购吧！</p>
				<h3 title="最新加入的商品">最新加入的商品</h3>
				<ul class="uls">
					<li><a href="#"
						title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
							<img src="/res/img/pic/p50x50.jpg"
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
					<li><a href="#"
						title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
							<img src="/res/img/pic/p50x50.jpg"
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
					<li><a href="#"
						title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
							<img src="/res/img/pic/p50x50.jpg"
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
					<li><a href="#"
						title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
							<img src="/res/img/pic/p50x50.jpg"
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
					<li><a href="#"
						title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
							<img src="/res/img/pic/p50x50.jpg"
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
				</ul>
				<div>
					<p>
						共<b>5</b>件商品&nbsp;&nbsp;&nbsp;&nbsp;共计<b class="f20">¥640.00</b>
					</p>
					<a href="#" title="去购物车结算" class="inb btn120x30c">去购物车结算</a>
				</div>
			</dd>
		</dl>
	</div>



	<div class="w ofc mt">
		<div class="l">
			<div class="showPro">
				<div class="big">
					<a id="showImg" class="cloud-zoom" href="${path}"
						rel="adjustX:10,adjustY:-1"><img alt="" src="${path}"></a>
				</div>
			</div>
		</div>
		<div class="r" style="width: 640px">
			<ul class="uls form" style="margin-left: 10px;">
				<li><h2 id="tl">${product.name }</h2></li>
				<li><label>巴　巴　价：</label> <span class="word"> <b
						class="f14 red mr">￥${product.price}</b> (市场价:<del>￥150.00</del>)
				</span></li>
				<li><label>商品评价：</label><span class="word"><span
						class="val_no val3d4" title="4分">4分</span>
					<var class="blue">(已有888人评价)</var></span></li>
				<li><label>运　　 费：</label><span class="word" id="deliveFee">10元</span></li>
				<li><label>库 　　存：</label><span class="word" id="stockInventory">100</span><span
					class="word">件</span></li>
				<li><label>选择颜色：</label>
					<div id="colors" class="pre spec">
						<c:forEach items="${colors}" var="entry" varStatus="status">
							<c:if test="${status.index == 0 }">
								<a accesskey="color" href="javascript:void(0)"
									title="${entry.key}" class="changToRed"
									style="margin-right: 5px;"> <img width="25" height="25"
									data-img="1" src="${path}" alt="${entry.value} " /> <label>${entry.value}</label>
								</a>
							</c:if>
							<c:if test="${status.index > 0 }">
								<a accesskey="color" href="javascript:void(0)"
									title="${entry.key}" class="changToWhite"
									style="margin-right: 5px;"> <img width="25" height="25"
									data-img="1" src="${path}" alt="${entry.value} " /> <label>${entry.value}</label>
								</a>
							</c:if>
						</c:forEach>

					</div></li>
				<li id="sizes"><label>尺　　码：</label> <a
					href="javascript:void(0)" class="not-allow" id="S"
					onclick="onSizeChange(this)"><label>&nbsp;&nbsp;S&nbsp;&nbsp;</label></a>
					<a href="javascript:void(0)" class="not-allow"
					style="margin-left: 5px;" id="M" onclick="onSizeChange(this)"><label>&nbsp;&nbsp;M&nbsp;&nbsp;</label></a>
					<a href="javascript:void(0)" class="not-allow"
					style="margin-left: 5px;" id="L" onclick="onSizeChange(this)"><label>&nbsp;&nbsp;L&nbsp;&nbsp;</label></a>
					<a href="javascript:void(0)" class="not-allow"
					style="margin-left: 5px;" id="XL" onclick="onSizeChange(this)"><label>&nbsp;XL&nbsp;</label></a>
					<a href="javascript:void(0)" class="not-allow"
					style="margin-left: 5px;" id="XXL" onclick="onSizeChange(this)"><label>XXL</label></a>
				</li>


				<li style="margin-top: 10px;"><label>购买数量：</label> <a id="sub"
					class="inb arr"
					style="border: 1px solid #919191; width: 10px; height: 10px; line-height: 5px; text-align: center; padding: 5px; font-size: 20px"
					title="减" href="javascript:void(0);"> - </a> <input id="num"
					type="text" style="padding: 2px; text-align: center;" value="1"
					name="" size="1" readonly="readonly"> <a id="add"
					class="inb arr"
					style="border: 1px solid #919191; width: 10px; height: 10px; line-height: 5px; text-align: center; padding: 5px; font-size: 20px"
					title="加" href="javascript:void(0);"> + </a></li>

				<li class="submit" style="margin-top: 10px;"><input
					type="button" value="" style="margin-top: 10px;"
					class="hand btn138x40" onclick="buy();" /> <input type="button"
					value="" style="margin-top: 10px; margin-left: 10px;"
					class="hand btn138x40b" onclick="addCart()" /></li>
			</ul>
		</div>
	</div>




	<div class="w ofc mt">
		<div class="l wl">
			<h2 class="h2 h2_l mt">
				<em title="销量排行榜">销量排行榜</em><cite></cite>
			</h2>
			<div class="box bg_white">
				<ul class="uls x_50x50" id="sal">
					
				</ul>
			</div>
			
			
			<h2 class="h2 h2_l mt">
				<em title="我的浏览记录">我的浏览记录</em><cite></cite>
			</h2>
			<div class="box bg_white">
				<ul class="uls x_50x50" id="hist">
					
				</ul>
			</div>

			<h2 class="h2 h2_l mt">
				<em title="商家精选">商家精选</em><cite></cite>
			</h2>
			<img src="/res/img/pic/ad200x75.jpg" alt="" />
		</div>
		<div class="r wr">
			<h2 class="h2 h2_ch mt">
				<em> <a href="javascript:void(0);" title="商品介绍"
					rel="#detailTab1" class="here">商品介绍</a> <a
					href="javascript:void(0);" title="规格参数" rel="#detailTab2">规格参数</a>
					<a href="javascript:void(0);" title="包装清单" rel="#detailTab3">包装清单</a></em><cite></cite>
			</h2>
			<div class="box bg_white ofc">
				<div id="detailTab1" class="detail">
					<img src="/res/img/pic/p800b.jpg" /><img
						src="/res/img/pic/p800a.jpg" /><img src="/res/img/pic/p800c.jpg" /><img
						src="/res/img/pic/p800d.jpg" />
				</div>

				<div id="detailTab2" style="display: none">
					<strong>服务承诺：</strong><br>
					京东向您保证所售商品均为正品行货，京东自营商品开具机打发票或电子发票。凭质保证书及京东发票，可享受全国联保服务（奢侈品、钟表除外；奢侈品、钟表由京东联系保修，享受法定三包售后服务），与您亲临商场选购的商品享受相同的质量保证。京东还为您提供具有竞争力的商品价格和<a
						target="_blank" href="http://www.jd.com/help/kdexpress.aspx">运费政策</a>，请您放心购买！
					<br>
					<br>
					注：因厂家会在没有任何提前通知的情况下更改产品包装、产地或者一些附件，本司不能确保客户收到的货物与商城图片、产地、附件说明完全一致。只能确保为原厂正货！并且保证与当时市场上同样主流新品一致。若本商城没有及时更新，请大家谅解！
					<br /> <strong>权利声明：</strong><br>京东上的所有商品信息、客户评价、商品咨询、网友讨论等内容，是京东重要的经营资源，未经许可，禁止非法转载使用。
					<p>
						<b>注：</b>本站商品信息均来自于合作方，其真实性、准确性和合法性由信息拥有者（合作方）负责。本站不提供任何保证，并不承担任何法律责任。
					</p>

				</div>

				<div id="detailTab3" class="detail" style="display: none">

					<pre class="f14">
		上衣*1 裤子*1 抹胸*1 包装*1 
	</pre>

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
						<a href="#" title="购物流程">购物流程</a>
					</dd>
					<dd>
						<a href="#" title="购物流程">购物流程</a>
					</dd>
					<dd>
						<a href="#" target="_blank" title="联系客服">联系客服</a>
					</dd>
					<dd>
						<a href="#" target="_blank" title="联系客服">联系客服</a>
					</dd>
				</dl></li>
			<li><span class="way"></span>
				<dl>
					<dt title="支付方式">支付方式</dt>
					<dd>
						<a href="#" title="货到付款">货到付款</a>
					</dd>
					<dd>
						<a href="#" title="在线支付">在线支付</a>
					</dd>
					<dd>
						<a href="#" title="分期付款">分期付款</a>
					</dd>
					<dd>
						<a href="#" title="分期付款">分期付款</a>
					</dd>
				</dl></li>
			<li><span class="help"></span>
				<dl>
					<dt title="配送方式">配送方式</dt>
					<dd>
						<a href="#" title="上门自提">上门自提</a>
					</dd>
					<dd>
						<a href="#" title="上门自提">上门自提</a>
					</dd>
					<dd>
						<a href="#" title="上门自提">上门自提</a>
					</dd>
					<dd>
						<a href="#" title="上门自提">上门自提</a>
					</dd>
				</dl></li>
			<li><span class="service"></span>
				<dl>
					<dt title="售后服务">售后服务</dt>
					<dd>
						<a href="#" target="_blank" title="售后策略">售后策略</a>
					</dd>
					<dd>
						<a href="#" target="_blank" title="售后策略">售后策略</a>
					</dd>
					<dd>
						<a href="#" target="_blank" title="售后策略">售后策略</a>
					</dd>
					<dd>
						<a href="#" target="_blank" title="售后策略">售后策略</a>
					</dd>
				</dl></li>
			<li><span class="problem"></span>
				<dl>
					<dt title="特色服务">特色服务</dt>
					<dd>
						<a href="#" target="_blank" title="夺宝岛">夺宝岛</a>
					</dd>
					<dd>
						<a href="#" target="_blank" title="夺宝岛">夺宝岛</a>
					</dd>
					<dd>
						<a href="#" target="_blank" title="夺宝岛">夺宝岛</a>
					</dd>
					<dd>
						<a href="#" target="_blank" title="夺宝岛">夺宝岛</a>
					</dd>
				</dl></li>
		</ul>
	</div>
</body>
</html>