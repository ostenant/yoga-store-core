<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>babasport-list</title>

<script type="text/javascript">
	$(function(){
		// 编辑
		$(":button[name='edit']").click(function(){
			var index = $(this).attr("id").substr(5);
			$("#skuPrice_" + index).removeAttr("disabled");
			$("#marketPrice_" + index).removeAttr("disabled");
			$("#stockInventory_" + index).removeAttr("disabled");
			$("#skuUpperLimit_" + index).removeAttr("disabled");
			$("#deliveFee_" + index).removeAttr("disabled");
				
		});
		
		// 保存
		$(":button[name='edit']").siblings().click(function(){
				var index = $(this).attr("id").substr(5);
				
				var validation = true; 
				
			    if(!/^[0-9]+$/.test($("#stockInventory_" + index).val())){
			        alert("库存量为数字!");
			        alert($("#stockInventory_" + index).val());
			        validation = false;
		        }
			    
			    if(!/^[0-9]+$/.test($("#skuUpperLimit_" + index).val())){
			        alert("购买限制为数字!");
			        validation = false;
		        }
				
			    if(!/^[0-9]+(.[0-9]{1,2})?$/.test($("#deliveFee_" + index).val())){
			    	alert("邮费为数字（小数）!");
			    	validation = false;
			    }
			    
			    if(!/^[0-9]+(.[0-9]{1,2})?$/.test($("#skuPrice_" + index).val())){
			    	alert("销售价格为数字（小数）!");
			    	validation = false;
			    }
			    
			    if(!/^[0-9]+(.[0-9]{1,2})?$/.test($("#marketPrice_" + index).val())){
			    	alert("市场价格为数字（小数）!");
			    	validation = false;
			    }
			    
			    if(validation){
					$.ajax({
						url: '/admin/product/product/saveSku.do',
						dataType: 'json',
						type: 'POST',
						data: {
							'skuPrice': $("#skuPrice_" + index).val(),
							'marketPrice': $("#marketPrice_" + index).val(),
							'stockInventory': $("#stockInventory_" + index).val(),
							'skuUpperLimit': $("#skuUpperLimit_" + index).val(),
							'deliveFee': $("#deliveFee_" + index).val(),
							'id': $(this).attr("name"),
						},
						success: function(data){
							$("#skuPrice_" + index).attr("disabled",true);
							$("#marketPrice_" + index).attr("disabled",true);
							$("#stockInventory_" + index).attr("disabled",true);
							$("#skuUpperLimit_" + index).attr("disabled",true);
							$("#deliveFee_" + index).attr("disabled",true);
							
							alert(data.msg);
						}
						
					});
			    }
		});
	});
</script>
</head>

<body>
<div class="box-positon">
	<div class="rpos">当前位置: 库存管理 - 列表</div>
	<div class="clear"></div>
</div>
<div class="body-box">
<form method="post" id="tableForm">
<table cellspacing="1" cellpadding="4" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th>商品编号</th>
			<th>商品颜色</th>
			<th>商品尺码</th>
			<th>市场价格</th>
			<th>销售价格</th>
			<th>库       存</th>
			<th>购买限制</th>
			<th>运       费</th>
			<th>是否赠品</th>
			<th>操       作</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${pageBean.elements}" var="sku" varStatus="status">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'" ">
				<td align="center" width="15%">${sku.no}</td>
				<td align="center" width="7%">${sku.color}</td>
				<td align="center" width="7%">${sku.size}</td>
				<td align="center" width="10%"><input type="text" id="skuPrice_${status.index + 1}" value="${sku.skuPrice}" disabled="disabled" size="8"/></td>
				<td align="center" width="10%"><input type="text" id="marketPrice_${status.index + 1}" value="${sku.marketPrice}" disabled="disabled" size="8"/></td>
				<td align="center" width="10%"><input type="text" id="stockInventory_${status.index + 1}" value="${sku.stockInventory}" disabled="disabled" size="8"/></td>
				<td align="center" width="10%"><input type="text" id="skuUpperLimit_${status.index + 1}" value="${sku.skuUpperLimit}" disabled="disabled" size="8"/></td>
				<td align="center" width="10%"><input type="text" id="deliveFee_${status.index + 1}" value="${sku.deliveFee}" disabled="disabled" size="8"/></td>
				<td align="center" width="5%">${sku.skuType == 1?'不是':'是'}</td>
				<td align="center" width="20%">
					<input class="add" id="edit_${status.index + 1}" type="button" value="修改" name="edit"/>&nbsp;&nbsp;
					<input class="add" id="save_${status.index + 1}" type="button" value="保存" name="${sku.id}" title="${sku.productId }"/>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</form>
		<!-- 分页  --><br/>
		<div class="page pb15">
			<span class="r inb_a page_b"> 
				<c:forEach items="${pageView}" var="view">
					${view}					
				</c:forEach>
			</span>
		</div>

</div>
</body>
</html>