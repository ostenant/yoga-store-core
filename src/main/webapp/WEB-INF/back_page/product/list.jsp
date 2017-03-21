<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>babasport-list</title>
<script type="text/javascript">
	function getTableForm() {
		return document.getElementById('tableForm');
	}
	function optDelete() {
		if (Pn.checkedCount('ids') <= 0) {
			alert("请至少选择一个!");
			return;
		}
		if (!confirm("确定删除吗?")) {
			return;
		}
		var f = getTableForm();
		f.action = "o_delete.do";
		f.submit();
	}
	function changePageNo() {
		$("input[name='pageNo']").val(1);
	}
</script>

<script type="text/javascript">
	$().ready(function(){
		// -- 页面加载完成后加载品牌数据
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
			    	   
			    	   if(id == '${params.brandId}'){
			    		   $option.attr("selected","selected");
			    	   }
			    	   
			           $("select[name='brandId']").append($option);
			           
			       });
			          
			       
			       
			   }
		});
		
		$(":button[name=stock]").click(function(){
			window.location.href = '/admin/product/product/' + $(this).attr("id") + '/toStockList.do';
		});
		
		$(":button[name=delete_]").click(function(){
			window.location.href = '/admin/product/product/deleteProduct.do?isShow=0&id=' + $(this).attr("id");
		});
		
		$(":checkbox[id=header]").click(function(){
			$(":checkbox[name=id]").attr("checked", $(this).attr("checked"));
		});
		
		// 批量移除
		var options1 = {
				url: '/admin/product/product/topCarriage.do',
				dataType: 'json',
				type: 'post',//
				cache: false,
				data: {
					ids: []
				},
				success: function(data){
				}
		};
		
		// 上架
		$(":button[name=up_]").click(function(){
			$(":checkbox[name=id][checked=true]").each(function(index,domEle){
				options1.data['ids'].push($(domEle).val());
			});
			// alert(JSON.stringify(options1));
			// 提交表单
			$("#idsForm").ajaxSubmit(options1);
			// 刷新页面
			setTimeout(function(){
				$("#searchform").submit();
			}, 400);
			
		});
		
		
		// 批量下架
		var options2 = {
				url: '/admin/product/product/underCarriage.do',
				dataType: 'json',
				type: 'post',//
				cache: false,
				data: {
					ids: []
				},
				success: function(data){
				}
		};
		
		// 下架
		$(":button[name=down_]").click(function(){
			$(":checkbox[name=id][checked=true]").each(function(index,domEle){
				options2.data['ids'].push($(domEle).val());
			});
			// alert(JSON.stringify(options1));
			// 提交表单
			$("#idsForm").ajaxSubmit(options2);
			// 刷新页面
			
			setTimeout(function(){
			 	$("#searchform").submit();		
			 }, 500);
		});
		
		
		$(":button[name=query_]").click(function(){
			// 已上架
			window.open('/static/product/' + $(this).attr('id') + '.html');
		});
	});
</script>
</head>


<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 商品管理 - 列表</div>
		<form class="ropt">
			<input class="add" type="button" value="添加"
				onclick="javascript:window.location.href='/admin/product/product/toAdd.do'" />
		</form>
		
		<form action="" id="idsForm">
		</form>
		
		<div class="clear"></div>
	</div>
	
	
	<div class="body-box">
		<form id="searchform" action="/admin/product/product/list.do" method="post"
			style="padding-top: 5px;">
			<input type="hidden" value="1" name="pageNo" /> 
			名称: <input type="text" value="${params.name}" name="name" />
			
			<select name="brandId" style="width: 160px">
				<option value=""> 请选择品牌  </option>
			</select> 
			
			<select name="isShow" >
				<option value="1" <c:if test="${params.isShow != null and params.isShow == true}">selected="selected"</c:if>>上架</option>
				<option value="0" <c:if test="${params.isShow != null and params.isShow == false}">selected="selected"</c:if>>下架</option>
			</select> 
			
			<input type="submit" name="sub" class="query" value="查询" />
		</form>
		<form method="post" id="tableForm">
			<input type="hidden" value="" name="pageNo" /> <input type="hidden"
				value="" name="queryName" />
			<table cellspacing="1" cellpadding="0" width="100%" border="0"
				class="pn-ltable">
				<thead class="pn-lthead">
					<tr>
						<th width="2%"><input type="checkbox" id="header"/></th>
						<th width="12%">商品编号</th>
						<th width="35%">商品名称</th>
						<th width="6%">图片</th>
						<th width="4%">新品</th>
						<th width="4%">热卖</th>
						<th width="4%">推荐</th>
						<th width="6%">上下架</th>
						<th >操作选项</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">

					<c:forEach items="${pageBean.elements}" var="product">
						<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'"
							onmouseout="this.bgColor='#ffffff'">
							<td align="center"><input type="checkbox" name="id" value="${product.id}" /></td>
							<td align="center">${product.no}</td>
							<td align="center"><span style="padding-left: 3px;padding-right: 3px;">${product.name}</span></td>
							<td align="center"><img width="50" height="50" src="${basePath}${product.url}" /></td>
							<td align="center">${product.isNew ? '是' : '不是'}</td>
							<td align="center">${product.isHot ? '是' : '不是'}</td>
							<td align="center">${product.isCommend ? '是' : '不是'}</td>
							<td align="center">${product.isShow ? '已上架' : '已下架'}</td>
							<td align="center">
							
								<input class="query" id="${product.id}" type="button" value="查看" name="query_" 
								  	<c:if test="${product.isShow == false}">disabled="disabled"</c:if>
								/>
								<input class="add" id="${product.id}" type="button" value="修改" name="update_"/>
								<input class="del-button" id="${product.id}" type="button" value="删除" name="delete_"/>
								<input class="query" id="${product.id}" type="button" value="库存" name="stock"/>
							</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>


			<!-- 分页  -->
			<div class="page pb15">
				<span class="r inb_a page_b"> <c:forEach items="${pageView}"
						var="view">
						${view}
					</c:forEach>
				</span>
			</div>
			<div style="margin-top: 15px;">
				<input class="del-button" type="button" value="删除" onclick="optDelete();" />
				<c:if test="${!params.isShow}">
					<input class="add" type="button" value="上架" name="up_" />
				</c:if>
				<c:if test="${params.isShow}">
					<input class="del-button" type="button" value="下架" name="down_" />
				</c:if>
			</div>
		</form>
	</div>
</body>
</html>