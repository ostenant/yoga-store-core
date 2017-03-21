<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>babasport-add</title>
<script type="text/javascript">
	$(function(){
		$(":file").change(function(){
			var options = {
					url: '/admin/upload.do',
					dataType: 'json',
					type: 'post',
				    beforeSubmit: function(formData,jqForm,options){
						// 校验是否为图片  并且校验图片格式和大小
						var f= jqForm[0]; // 将jqForm转成DOM对象
						var picName = f.picture.value;
						var ext = picName.substring(picName.length - 3).toLowerCase();
						if(ext != "jpg" && ext != "gif" && ext != "bmp" && ext != "png"){
							// alert(picName.substring(picName.indexOf(".")+1));
							alert("必须上传图片格式文件！");
							return false;
						}
						return true;
						
					},
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
	<div class="rpos">当前位置: 品牌管理 - 添加</div>
	<form class="ropt" action="/admin/product/brand/list.do?isDisplay=1" method="post">
		<input type="submit"  value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box" style="float:right">
	<form id="jvForm" action="/admin/product/brand/addBrand.do" method="post" enctype="multipart/form-data">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						品牌名称:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="name" maxlength="100"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						上传商品图片(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">
						注:该尺寸图片必须为90x150。
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
					</td>
						<td width="80%" class="pn-fcontent">
						<input type="hidden" name="imgUrl" id="path"/>
						<img width="100" height="100" id="allImgUrl"/>
						<input type="file" name="picture"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						品牌描述:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="description" maxlength="80"  size="60"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						排序:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="sort" maxlength="80"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						是否可用:</td><td width="80%" class="pn-fcontent">
						<input type="radio" name="isDisplay" value="1" checked="checked"/>可用
						<input type="radio" name="isDisplay" value="0"/>不可用
					</td>
				</tr>
			</tbody>
			<tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" class="submit" value="保存"/> &nbsp; <input type="reset" class="reset" value="重置"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>