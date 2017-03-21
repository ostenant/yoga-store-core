<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/WEB-INF/back_page/head.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我的购物车</title>
<link rel="stylesheet" href="/res/css/style.css" />
<script src="/res/js/jquery.js"></script>
<script src="/res/js/com.js"></script>
<script type="text/javascript">
//结算
function addCart(){
 	window.location.href = "/buyer/buyerCart/settle.shtml";
}

function goOn(){
	if('${buyerCart.productId}' != null && '${buyerCart.productId}' != ''){
		window.open('/static/product/${buyerCart.productId}.html');
	}else{
		alert('还没有历史购物信息！');
	}
}

function clearCart(){
	window.location.href = '/shopping/buyerCart/clear.shtml';
}

function add(skuId){
	window.location.href = '/shopping/buyerCart/addAmount.shtml?skuId=' + skuId;
}

function del(skuId){
	window.location.href = '/shopping/buyerCart/deleteOne.shtml?skuId=' + skuId;
}

function sub(skuId){
	window.location.href = '/shopping/buyerCart/lessenAmount.shtml?skuId=' + skuId;
	if('${msg}'!= null && '${msg}' != ''){
		alert('${msg}');
	}
}
</script>

</head>
<body>
<div class="bar"><div class="bar_w">
	<p class="l">
		<span class="l">
			收藏本网站！北京<a href="" title="更换">[更换]</a>
		</span>
	</p>
	<ul class="r uls">
		<li class="dev">
			您好,欢迎来到馨灵购物商城！
		</li>
	<li class="dev"><a href="javascript:void(0)" onclick="login()"  title="登陆">[登陆]</a></li>
	<li class="dev"><a href="javascript:void(0)" onclick="register()" title="免费注册">[免费注册]</a></li>
	<li class="dev"><a href="javascript:void(0)" onclick="logout()" title="退出">[退出]</a></li>
	<li class="dev"><a href="javascript:void(0)" onclick="myOrder()" title="我的订单">我的订单</a></li>
	<li class="dev"><a href="" title="在线客服">在线客服</a></li>
	<li class="dev after"><a href="" title="English">English</a></li>
	</ul>
</div></div>
<ul class="ul step st3_1">
<li title="1.我的购物车" class="here">1.我的购物车</li>
<li title="2.填写核对订单信息">2.填写核对订单信息</li>
<li title="3.成功提交订单">3.成功提交订单</li>
</ul>
<div class="w ofc case">
	<div class="confirm">
		<div class="tl"></div><div class="tr"></div>
		<div class="ofc pb40">
			<c:if test="${buyerCart==null }">
				<p style="font-weight: bolder;color: red;font-size: xx-large;text-align: center;margin-top: 120px;">购物车空空如也，去挑选点东西吧~~</p>
				<input type="button" style="margin-left: 700px;margin-top: 100px;margin-bottom: 100px;" onclick="window.location.href='/product/display/list.shtml'" class="hand btn130x36" title="去购物" value="去购物">
			</c:if>
			<c:if test="${buyerCart!=null }">
			<div class="page">
				<b class="l f14 blue pt48">
					我挑选的商品：
				</b>
			</div>
			<table cellspacing="0" class="tab tab4" summary="">
				<thead>
					<tr>
						<th width="70%">商品</th>
						<th width="10%">单价（元）</th>
						<th width="10%">数量</th>
						<th width="10%">操作</th>
					</tr>     
				</thead>
				<tbody>
					
					<c:forEach items="${buyerCart.buyerItems }" var="buyerItem">
						<tr> 
							<td class="nwp pic">
								<ul class="uls">
									<li>
										<img style="width: 75px;height: 75px;margin-left: 10px;" title="${buyerItem.skuCell.product.name }" src="${basePath}${buyerItem.skuCell.skuImg}"/>
										<dl style="padding-left: 50px;">
											<dt >
												<a title="${buyerItem.skuCell.product.name }" href=""> 
													${buyerItem.skuCell.product.name }一${buyerItem.skuCell.color.name }一${buyerItem.skuCell.size}
												</a>
											</dt>
											<dd>
												<span class="red">【赠品】</span>
												<span class="red" style="padding-left: 10px;">库存量：${buyerItem.skuCell.stockInventory}件</span>
												<span class="red" style="padding-left: 10px;">购买限制：${buyerItem.buyLimit}件</span>
												<p class="box_d bg_gray2 gray"><a title="${buyerItem.skuCell.type.name }" href="">${buyerItem.skuCell.type.name }</a><br></p>
											</dd>
										</dl>
									</li>
								</ul>
							</td>
							
							<td style="text-align: center;font-size: 13px;font-weight: bold;">￥${buyerItem.skuCell.skuPrice }</td>
							
							<td style="text-align: center;">
								<a id="sub" class="inb arr" style="border: 1px solid #919191; width: 10px; height: 10px; line-height: 5px; text-align: center; padding: 5px; font-size: 20px" title="减" href="javascript:void(0);" onclick="sub('${buyerItem.skuCell.id}')"> - </a> 
								<input id="num" type="text" style="padding-top: 10px;padding-bottom:10px; text-align: center;" value="${buyerItem.amount}" name="" size="1" readonly="readonly"> 
								<a id="add" class="inb arr" style="border: 1px solid #919191; width: 10px; height: 10px; line-height: 5px; text-align: center; padding: 5px; font-size: 20px"
								title="加" href="javascript:void(0);" onclick="add('${buyerItem.skuCell.id}')"> + </a>
							</td>
							
							<td class="blue"  style="text-align: center;">
								<a onclick="delProduct(503)" title="删除" href="javascript:void(0);">
									<input class="del-button" type="button" value="删除" id="del" name="delete" onclick="del('${buyerItem.skuCell.id}')"/>
								</a>
							</td>
						</tr>
					</c:forEach>
					           
				</tbody>
			</table>
			<div class="page">
				<span class="l">
					<input type="button" onclick="goOn()" class="hand btn130x36" title="继续购物" value="继续购物">
					<input type="button" onclick="clearCart()" class="hand btn130x36" title="清空购物车" value="清空购物车">
				</span>
				<span class="r box_gray">
					<dl class="total">
						
						<dt>购物车金额小计：<cite>(共<var id="productAmount">
							<c:if test="${buyerCart.productSize != null && buyerCart.productSize != ''}">${buyerCart.productSize}</c:if>
							<c:if test="${buyerCart.productSize == null || buyerCart.productSize == ''}">0</c:if>
						</var>个商品)</cite></dt>
						<dd><em class="l">商品金额：</em>￥<var id="productPrice">
							<c:if test="${buyerCart.totalProdExpenses != null && buyerCart.totalProdExpenses != ''}">${buyerCart.totalProdExpenses}</c:if>
							<c:if test="${buyerCart.totalProdExpenses == null || buyerCart.totalProdExpenses == ''}">0</c:if></var>元</dd>
						<dd><em class="l">运费：</em>￥<var id="fee">
							<c:if test="${buyerCart.totalDeliverFee != null && buyerCart.totalDeliverFee != ''}">${buyerCart.totalDeliverFee}</c:if>
							<c:if test="${buyerCart.totalDeliverFee == null || buyerCart.totalDeliverFee == ''}">0</c:if></var>元</dd>
						<dd class="orange"><em class="l">应付总额：</em>￥<var id="totalPrice">
							<c:if test="${buyerCart.totalExpenses != null && buyerCart.totalExpenses != ''}">${buyerCart.totalExpenses}</c:if>
							<c:if test="${buyerCart.totalExpenses == null || buyerCart.totalExpenses == ''}">0</c:if></var>元</dd>
						<dd >
							<input type="button" id="addCart" value="" style="margin-top: 10px; margin-left: 10px;" class="hand btn138x40d" onclick="addCart()" />
						</dd>
					</dl>
				</span>
			</div>
			</c:if>
			
		</div>
	</div>
</div>
<div class="w ofc case" style="display: none;">
	<div class="confirm">
		<div class="tl"></div><div class="tr"></div>
		<div class="ofc pb40" style="text-align: center;height: 200px;margin-top: 80px">
       		 <a href="" style="color: red;font-size: 30px;">去首页</a>挑选喜欢的商品
		</div>
	</div>
</div>
<div class="mode">
	<div class="tl"></div><div class="tr"></div>
	<ul class="uls">
		<li class="first">
			<span class="guide"></span>
			<dl>
			<dt title="购物指南">购物指南</dt>
			<dd><a href="" title="购物流程">购物流程</a></dd>
			<dd><a href="" title="购物流程">购物流程</a></dd>
			<dd><a href="" target="_blank" title="联系客服">联系客服</a></dd>
			<dd><a href="" target="_blank" title="联系客服">联系客服</a></dd>
			</dl>
		</li>
		<li>
			<span class="way"></span>
			<dl>
			<dt title="支付方式">支付方式</dt>
			<dd><a href="" title="货到付款">货到付款</a></dd>
			<dd><a href="" title="在线支付">在线支付</a></dd>
			<dd><a href="" title="分期付款">分期付款</a></dd>
			<dd><a href="" title="分期付款">分期付款</a></dd>
			</dl>
		</li>
		<li>
			<span class="help"></span>
			<dl>
			<dt title="配送方式">配送方式</dt>
			<dd><a href="" >上门自提</a></dd>
			<dd><a href="" >上门自提</a></dd>
			<dd><a href="" >上门自提</a></dd>
			<dd><a href="" >上门自提</a></dd>
			</dl>
		</li>
		<li>
			<span class="service"></span>
			<dl>
			<dt title="售后服务">售后服务</dt>
			<dd><a href=""  title="售后策略">售后策略</a></dd>
			<dd><a href=""  title="售后策略">售后策略</a></dd>
			<dd><a href=""  title="售后策略">售后策略</a></dd>
			<dd><a href=""  title="售后策略">售后策略</a></dd>
			</dl>
		</li>
		<li>
			<span class="problem"></span>
			<dl>
			<dt title="特色服务">特色服务</dt>
			<dd><a href="" title="夺宝岛">夺宝岛</a></dd>
			<dd><a href="" title="夺宝岛">夺宝岛</a></dd>
			<dd><a href="" title="夺宝岛">夺宝岛</a></dd>
			<dd><a href="" title="夺宝岛">夺宝岛</a></dd>
			</dl>
		</li>
	</ul>
</div>
</body>
</html>