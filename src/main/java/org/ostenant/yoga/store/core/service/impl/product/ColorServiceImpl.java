package org.ostenant.yoga.store.core.service.impl.product;

import java.util.List;

import javax.annotation.Resource;

import org.ostenant.yoga.store.core.bean.product.Color;
import org.ostenant.yoga.store.core.dao.product.ColorMapper;
import org.ostenant.yoga.store.core.service.product.ColorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ColorServiceImpl implements ColorService {

	@Resource
	private ColorMapper colorMapper;

	
	public List<Color> getColorList() {
		List<Color> colors = colorMapper.getColorList();
		return colors;
	}

	
	public Color getColorById(Integer colorId) {
		Color color = colorMapper.selectByPrimaryKey(colorId);
		return color;
	}
}
