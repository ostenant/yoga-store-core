package org.ostenant.yoga.store.core.service.impl.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ostenant.yoga.store.core.bean.product.SkuCell;
import org.ostenant.yoga.store.core.dao.product.SkuCellMapper;
import org.ostenant.yoga.store.core.query.base.PageQuery;
import org.ostenant.yoga.store.core.response.SkuCellResponse;
import org.ostenant.yoga.store.core.service.product.SkuCellService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

@Service
@Transactional
public class SkuCellServiceImpl implements SkuCellService {

	@Resource
	private SkuCellMapper skuCellMapper;

	@Transactional(readOnly = true)
	public List<SkuCellResponse> getSkuListByProductId(Integer productId,
			PageQuery pageQuery) {

		Map<String, Object> paramMap = Maps.newHashMap();
		int pageSize = pageQuery.getPageSize();
		int startRow = pageQuery.getStartRow();
		paramMap.put("pageSize", pageSize);
		paramMap.put("startRow", startRow);
		paramMap.put("productId", productId);
		List<SkuCellResponse> responses = skuCellMapper
				.getSkuListByProductId(paramMap);
		return responses;

	}

	@Transactional(readOnly = true)
	public int getTotalCountByProductId(Integer productId) {
		int totalCount = skuCellMapper.getTotalCountByProductId(productId);
		return totalCount;
	}

	
	public int saveSku(SkuCell skuCell) {
		int row = skuCellMapper.updateByPrimaryKeySelective(skuCell);
		return row ;
	}

	@Transactional(readOnly = true)
	public List<SkuCellResponse> getSkuListByPidWithoutPage(Integer productId) {
		List<SkuCellResponse> skuList = skuCellMapper.getSkuListByPidWithoutPage(productId);
		return skuList;
	}

	@Transactional(readOnly = true)
	public SkuCell getSkuById(Integer skuId) {
		SkuCell skuCell = skuCellMapper.selectByPrimaryKey(skuId);
		return skuCell;
	}

}
