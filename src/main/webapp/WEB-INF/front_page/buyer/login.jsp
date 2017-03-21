<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>登录馨灵购物商城</title>
<link rel="stylesheet" href="/res/css/style.css" />
<script src="/res/js/jquery.js"></script>
<script src="/res/js/com.js"></script>

<script type="text/javascript">
	$(function(){
		
	});
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
	<li class="dev">您好,欢迎来到馨灵购物商城！</li>
	<li class="dev"><a href="" title="在线客服">在线客服</a></li>
	<li class="dev after"><a href="#" title="English">English</a></li>
	</ul>
</div></div>
<div class="w loc">
	<div class="h-title" id="logo">
		<div class="h-logo l"><img src="/res/img/pic/logo-1.png"  style="width: 200px;height: 40px;margin-top: 20px;margin-left: 40px;"/></div>
	</div>
</div>
<div class="sign">
	<div class="l ad420x205"><a href="javascript:void(0)" title="title"><img id="image" src="/res/img/ppp0.jpg" width="400" height="400"/></a></div>
	<div class="r">
		<h2 title="登录新巴巴运动网">登录馨灵购物商城</h2>
		<form id="jvForm" action="/shopping/login.shtml" method="post">
			<input type="hidden" name="returnUrl" value="${param.returnUrl}"/>
			<ul class="uls form">
				<li id="errorName" class="errorTip" <c:if test="${empty error}">style="display:none"</c:if>>
					${error}
				</li>
				
				<li>
					<label for="username">用户名：</label>
					<span class="bg_text">
						<input type="text" id="username" name="username" maxLength="100" />
					</span>
				</li>
				<li>
					<label for="password">密　码：</label>
					<span class="bg_text">
						<input type="password" id="password" name="password" maxLength="32" />
					</span>
				</li>
				<li>
					<label for="captcha">验证码：</label>
					<span class="bg_text small">
						<input type="text" id="captcha" name="captcha" maxLength="7"/>
					</span>
					<img src="/captcha.svl" onclick="this.src='${base}/captcha.svl?d='+new Date()" class="code" alt="换一张" />
					<a href="javascript:void(0);" onclick="$('.code').attr('src','${base}/captcha.svl?d='+new Date())" title="换一张">换一张</a>
				</li>
				<li>
					<label for="">&nbsp;</label>
					<input type="submit" value="登 录" class="hand btn66x23"/>
					<a href="" title="忘记密码？">忘记密码？</a>
				</li>
			</ul>
		</form>
	</div>
</div>
</body>
</html>