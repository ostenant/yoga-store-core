<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<jsp:include page="/WEB-INF/back_page/head.jsp" />
<title>babasport-list</title>

<script type="text/javascript">
	$(function(){
		
		// 转到删除页面
		$("span[title='delete']").click(function(){
			var isDisplay =  $("select[name=isDisplay]").val();
			var name =  $(":input[name=name]").val();
				window.location.href = '/admin/product/brand/delete.do?id=' + $(this).attr("id") + '&isDisplay=' + isDisplay + '&name=' + name;
		});
		
		// 转到修改页面
		$("span[title='edit']").click(function(){
			var isDisplay =  $("select[name=isDisplay]").val();
			var name =  $(":input[name=name]").val();
				window.location.href = '/admin/product/brand/edit.do?id=' + $(this).attr("id") + '&isDisplay=' + isDisplay + '&name=' + name;
		});
		
		// checkbox联动
		$(":checkbox[name=head]").unbind("click");
		$(":checkbox[name=head]").bind("click",function(){
			$(":checkbox:[name=ids]").attr("checked",$(this).attr("checked"));
			
		});
		
		// 批量移除
		var options1 = {
				url: '/admin/product/brand/remove.do',
				dataType: 'json',
				type: 'post',//
				cache: false,
				data: {
					isDisplay: $("select[name=isDisplay]").val(),
					name: $(":input[name=name]").val(),
					ids: []
				},
				success: function(data){
				}
		};
		
		// 批量移除
		$("#removeAll").unbind("click");
		$("#removeAll").bind("click",function(){
			 $(":checkbox[name=ids][checked=true]").each(function(index, domEle){
				 options1.data['ids'].push($(domEle).val());
			 });
			 // alert(JSON.stringify(options1));
			 // 提交表单
			 $("#brandlist").ajaxSubmit(options1);
			 // 刷新页面
			 setTimeout(function(){
				 	$("#searchform").submit();		
				 }, 400);	 
		});
		
		
		// 上架
		var options2 = {
				url: '/admin/product/brand/upGrounding.do',
				dataType: 'json',
				type: 'post',//
				cache: false,
				data: {
					isDisplay: $("select[name=isDisplay]").val(),
					name: $(":input[name=name]").val(),
					ids: []
				},
				success: function(data){
				}
		};
		
		// 上架
		$("#up_").unbind("click");
		$("#up_").bind("click" ,function(){
			 $(":checkbox[name=ids][checked=true]").each(function(index, domEle){
				 options2.data['ids'].push($(domEle).val());
			 });
			 
			 // 提交表单
			 $("#brandlist").ajaxSubmit(options2);
			 // 刷新页面
			 setTimeout(function(){
				 	$("#searchform").submit();		
				 }, 400);		
		});
		
		
		// 下架
		var options3 = {
				url: '/admin/product/brand/underCarriage.do',
				dataType: 'json',
				type: 'post',//
				cache: false,
				data: {
					isDisplay: $("select[name=isDisplay]").val(),
					name: $(":input[name=name]").val(),
					ids: []
				},
				success: function(data){
				}
		};
		
		// 下架
		$("#down_").unbind("click");
		$("#down_").bind("click" ,function(){
			 $(":checkbox[name=ids][checked=true]").each(function(index, domEle){
				 options3.data['ids'].push($(domEle).val());
			 });
			 
			 // 提交表单
			 $("#brandlist").ajaxSubmit(options3);
			 // 刷新页面
			 setTimeout(function(){
			 	$("#searchform").submit();		
			 }, 400);
		});
		
	});
</script>
</head>

<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 品牌管理 - 列表</div>
		<form class="ropt">
			<input class="add" type="button" value="添加"
				onclick="javascript:window.location.href='/admin/product/brand/add.do'" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form id="searchform" action="/admin/product/brand/list.do" method="post"
			style="padding-top: 5px;">
			品牌名称: <input type="text" name="name" value="${params.name}"/> 
			<select name="isDisplay">
				<option value="1" <c:if test="${params.isDisplay == 1}">selected="selected"</c:if>> 是</option>
				<option value="0" <c:if test="${params.isDisplay == 0}">selected="selected"</c:if>>不是</option>
			</select> <input type="submit" class="query" value="查询" />
		</form>
		
		<form method="post" id="brandlist">
			<table cellspacing="1" cellpadding="0" border="0" width="100%"
				class="pn-ltable">
				<thead class="pn-lthead">
					<tr>
						<th width="20"><input type="checkbox" name="head"/></th>
						<th>品牌ID</th>
						<th>品牌名称</th>
						<th>品牌图片</th>
						<th>品牌描述</th>
						<th>排序</th>
						<th>是否可用</th>
						<th>操作选项</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${pageBean.elements}" var="brand">
						<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'"
							onmouseover="this.bgColor='#eeeeee'">
							<td><input type="checkbox" value="${brand.id}" name="ids" id="${brand.id}"/></td>
							<td align="center">${brand.id}</td>
							<td align="center">${brand.name}</td>
							<td align="center"><img width="50" height="50"
								src="${baseUrl}${brand.imgUrl}" /></td>
							<td align="center">${brand.description}</td>
							<td align="center">${brand.sort}</td>
							<td align="center"><c:if test="${brand.isDisplay eq true}">
									是
								</c:if> <c:if test="${brand.isDisplay eq false}">
									不是
								</c:if></td>
							<td align="center">
								<span style="cursor: pointer;" title="edit" class="pn-opt" id="${brand.id}">
									<input class="add" id="down" type="button" value="编辑" />
								</span>
								 &nbsp;&nbsp; 
								<span style="cursor: pointer;" title="delete" class="pn-opt" id="${brand.id}">
									<input class="del-button" id="down" type="button" value="删除" />
								</span>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>

		<!-- 分页  -->
		<div class="page pb15">
			<span class="r inb_a page_b"> 
				<c:forEach items="${pageView}" var="view">
					${view}					
				</c:forEach>
			</span>
		</div>


		<div style="margin-top: 15px;">
			<input id="removeAll" class="del-button" type="button" value="删除"/>
			<c:if test="${params.isDisplay == 0}">
				<input class="add" id="up_" type="button" value="上架" />
			</c:if>
			<c:if test="${params.isDisplay == 1}">
				<input class="del-button" id="down_" type="button" value="下架" />
			</c:if>
		</div>
	</div>
</body>
</html>