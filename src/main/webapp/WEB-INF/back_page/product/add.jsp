<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>babasport-add</title>
<style type="">
.h2_ch a:hover,.h2_ch a.here {
	color: #FFF;
	font-weight: bold;
	background-position: 0px -32px;
}

.h2_ch a {
	float: left;
	height: 32px;
	margin-right: -1px;
	padding: 0px 16px;
	line-height: 32px;
	font-size: 14px;
	font-weight: normal;
	border: 1px solid #C5C5C5;
	background: url('/res/itcast/img/admin/bg_ch.gif') repeat-x scroll 0% 0%
		transparent;
}

a {
	color: #06C;
	text-decoration: none;
}
</style>


<script type="text/javascript">
	$(function() { // fckeditor
		var tObj;
		$("#tabs a").each(function() {
			if ($(this).attr("class").indexOf("here") == 0) {
				tObj = $(this);
			}
			$(this).click(function() {
				var c = $(this).attr("class");
				if (c.indexOf("here") == 0) {
					return;
				}
				var ref = $(this).attr("ref");
				var ref_t = tObj.attr("ref");
				tObj.attr("class", "nor");
				$(this).attr("class", "here");
				$(ref_t).hide();
				$(ref).show();
				tObj = $(this);
				if (ref == '#tab_2') {
					var fck = new FCKeditor("productdesc"); // textarea的id
					fck.BasePath = "/res/fckeditor/";
					fck.Height = 400;
			        fck.Config.ImageUploadURL = "/admin/upload/fck.do";
					fck.ReplaceTextarea();
				}
			});
		});
	});
</script>

<script type="text/javascript">
	$().ready(function(){
		// -- 页面加载完成后加载品牌数据
		
		// -- 1.加载商品类型
		$.ajax({
			   type: "GET",
			   url: "/admin/product/product/load/type.do",
			   success: function(data){
			       var element = eval('('+data+')');
			       var $typeList =  $(element.typeList);
			       $typeList.each(function(index,element){
			    	   var id = element.id;
			    	   var name = element.name;
			    	   var $option = $("<option>");
			    	   $option.html(name);
			    	   $option.val(id);
			    	   
			           $("select[name='typeId']").append($option);
			       });
			       
			   }
		});
		
		
		// -- 2.加载品牌
		$.ajax({
			   type: "GET",
			   url: "/admin/product/product/load/brand.do",
			   success: function(data){
			       var element = eval('('+data+')');
			       var $brandList =  $(element.key);
			       $brandList.each(function(index,element){
			    	   var id = element.id;
			    	   var name = element.name;
			    	   var $option = $("<option>");
			    	   $option.html(name);
			    	   $option.val(id);
			    	   
			           $("select[name='brandId']").append($option);
			       });
			          
			   }
		});
		
		// -- 表单提交前初始化数据
		
		$("#sub_button").click(function(){
			
			if (!$(":checkbox[name='isHot']").attr("checked")){
				var $isHot = $("<input>").attr("type","hidden").attr("name","isHot").val(0);
				$("#jvForm").append($isHot);
			}
			
			if (!$(":checkbox[name='isNew']").attr("checked")){
				var $isNew = $("<input>").attr("type","hidden").attr("name","isNew").val(0);
				$("#jvForm").append($isNew);
			}
			
			if (!$(":checkbox[name='isCommend']").attr("checked")){
				var $isCommend = $("<input>").attr("type","hidden").attr("name","isCommend").val(0);
				$("#jvForm").append($isCommend);
			}
			
			/*
			if (!$(":checkbox[name='size']").attr("checked")){
				var $size = $("<input>").attr("type","hidden").attr("name","size").val('');
				$("#jvForm").append($size);
			}
			
			if (!$(":checkbox[name='color']").attr("checked")){
				var $color = $("<input>").attr("type","hidden").attr("name","color").val('');
				$("#jvForm").append($color);
			}
			
			if (!$(":checkbox[name='feature']").attr("checked")){
				var $feature = $("<input>").attr("type","hidden").attr("name","feature").val('');
				$("#jvForm").append($feature);
			}
			*/
			
			var isSub = true;
			if ($("select[name='typeId']").val() == 0){
				isSub = false;
				alert("请选择商品类型!");
			} else {
				isSub = true;
			}
			
			if($("select[name='brandId']").val() == 0){
				isSub = false;
				alert("请选择商品品牌!");
			} else {
				isSub = true;
			}
			
			var $isShow = $("<input>").attr("type","hidden").attr("name","isShow").val(0);
			var $isDel = $("<input>").attr("type","hidden").attr("name","isDel").val(0);
			
			$("#jvForm").append($isShow);
			$("#jvForm").append($isDel);
			
			if(isSub){
			 	$("#jvForm").submit();
			}
			
		});
		
	});
</script>


<script type="text/javascript">
	$(function(){
		$(":file").change(function(){
			var options = {
					url: '/admin/upload.do',
					dataType: 'json',
					type: 'post',
					success: function(data){
						// url: 图片服务器存放图片地址
						$("#allImgUrl").attr("src", data.url);
						// path: 图片相对路径
						$("#path").val(data.path);
					}
			};
			$("#jvForm").ajaxSubmit(options);
		});
	});
</script>

</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 商品管理 - 添加</div>
		<form class="ropt">
			<input type="submit" onclick="this.form.action='/admin/product/product/list.do?isShow=0';"
				value="返回列表" class="return-button" />
		</form>
		<div class="clear"></div>
	</div>
	<h2 class="h2_ch">
		<span id="tabs"> 
			<a href="javascript:void(0);" ref="#tab_1" title="基本信息" class="here">基本信息</a>
			<a href="javascript:void(0);" ref="#tab_2" title="商品描述" class="nor">商品描述</a>
			<a href="javascript:void(3);" ref="#tab_3" title="商品参数" class="nor">包装清单</a>
		</span>
	</h2>
	<div class="body-box" style="float: right">
		<form id="jvForm" action="/admin/product/product/addProduct.do" method="post"
			enctype="multipart/form-data">
			<table cellspacing="1" cellpadding="2" width="100%" border="0"
				class="pn-ftable">
				<tbody id="tab_1">
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 商品类型: </td>
						<td width="80%" class="pn-fcontent">
							<select name="typeId" style="width: 120px">
									<option value="0"> 请选择商品类型  </option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 商品名称: </td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="name" maxlength="100" size="100" value="秋季瑜伽服"/></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">商品品牌: </td>
						<td width="80%" class="pn-fcontent">
							<select name="brandId"  style="width: 160px">
									<option value="0"> 请选择品牌 </option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">商品毛重:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							value="1.6" class="required" name="weight" maxlength="10" />KG</td>
					</tr>
					<tr >
						<td width="20%" class="pn-flabel pn-flabel-h">颜色:</td>
						<td width="80%" style="height: 40px;" class="pn-fcontent">
							<c:forEach items="${colorList}" var="color1" varStatus="status">
								<c:if test="${status.index % 12 == 0}"><br/><br/></c:if>
								<input type="checkbox" value="${color1.id}" name="color" id="c1_${status.index+1}"/>
								<label for="c1_${status.index+1}">${color1.name}</label>&nbsp;&nbsp;&nbsp; 
							</c:forEach>
							<br/><br/>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 材质:</td>
						<td width="80%" class="pn-fcontent">
							<c:forEach items="${featureList}" var="feature1" varStatus="status">
								<input type="checkbox" value="${feature1.name}" name="feature" id="c2_${status.index+1}"/>
								<label for="c2_${status.index+1}">${feature1.name}</label>&nbsp;&nbsp;&nbsp; 
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 尺码:</td>
						<td width="80%" class="pn-fcontent">
							<input type="checkbox" name="size" value="S" id="c3_1" checked="checked"/><label for="c3_1">S</label>&nbsp;&nbsp;&nbsp;  
							<input type="checkbox" name="size" value="M" id="c3_2" checked="checked"/><label for="c3_2">M</label>&nbsp;&nbsp;&nbsp;  
							<input type="checkbox" name="size" value="L" id="c3_3" checked="checked"/><label for="c3_3">L</label>&nbsp;&nbsp;&nbsp;  
							<input type="checkbox" name="size" value="XL" id="c3_4" checked="checked"/><label for="c3_4">XL</label>&nbsp;&nbsp;&nbsp;  
							<input type="checkbox" name="size" value="XXL" id="c3_5"/><label for="c3_5">XXL</label>&nbsp;&nbsp;&nbsp;  
							<input type="checkbox" name="size" value="XXXL" id="c3_6"/><label for="c3_6">XXXL</label>&nbsp;&nbsp;&nbsp;  
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">状态:</td>
						<td width="80%" class="pn-fcontent">
							<input type="checkbox" name="isNew" value="1" id="c4_1" checked="checked"/>
							<label for="c4_1">新品</label>&nbsp;&nbsp;&nbsp;  
							<input type="checkbox" name="isCommend" value="1" id="c4_2" checked="checked"/>
							<label for="c4_2">推荐</label> &nbsp;&nbsp;&nbsp;  
							<input type="checkbox" name="isHot" value="1" id="c4_3"/>
							<label for="c4_3">热卖</label>&nbsp;&nbsp;&nbsp;  
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 上传商品图片(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">注:该尺寸图片必须为90x150。</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
							<input type="hidden" name="url" id="path"/>
							<img width="100" style="padding-top: 6px;" height="100" id="allImgUrl" />
							<input type="file" name="picture"/>
						</td>
					</tr>
				</tbody>
				<tbody id="tab_2" style="display: none">
					<tr>
						<td>
							<textarea rows="10" cols="10" id="productdesc" name="description">
								<p><img src="http://localhost:8088/bbsport_jersey/images/1/3/3/1/6/9/2/3/5/5/20151107045439921055.jpg" width="750" height="3342" alt="" />&nbsp;</p>
							</textarea>
					    </td>
					</tr>
				</tbody>
				<tbody id="tab_3" style="display: none">
					<tr>
						<td>
							<textarea rows="18" cols="136" id="productList" name="packageList" style="text-align: left;font-size: 15px">
套装x1 吊牌x3 包装袋x1

服务承诺：
携程商城平台卖家销售并发货的商品，由平台卖家提供发票和相应的售后服务。请您放心购买！
注：因厂家会在没有任何提前通知的情况下更改产品包装、产地或者一些附件，本司不能确保客户收到的货物与商城图片、
产地、附件说明完全一致。
只能确保为原厂正货！并且保证与当时市场上同样主流新品一致。若本商城没有及时更新，请大家谅解！ 

权利声明：
携程商城上的所有商品信息、客户评价、商品咨询、网友讨论等内容，是携程商城重要的经营资源，未经许可，禁止非法转载使用。
注：本站商品信息均来自于合作方，其真实性、准确性和合法性由信息拥有者（合作方）负责。本站不提供任何保证，
并不承担任何法律责任。

印刷版次不同，印刷时间和版次以实物为准。
							</textarea>
						</td>
					</tr>
				</tbody>
				<tbody>
					<tr>
						<td class="pn-fbutton" colspan="2">
							<input type="button" id="sub_button" class="submit" value="提交" /> &nbsp; 
							<input type="reset" class="reset" value="重置" />
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>