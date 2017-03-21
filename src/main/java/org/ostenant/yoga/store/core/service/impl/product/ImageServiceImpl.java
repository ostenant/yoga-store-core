package org.ostenant.yoga.store.core.service.impl.product;

import java.util.List;

import javax.annotation.Resource;

import org.ostenant.yoga.store.core.bean.product.Image;
import org.ostenant.yoga.store.core.dao.product.ImageMapper;
import org.ostenant.yoga.store.core.service.product.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageServiceImpl implements ImageService{

	@Resource
	private ImageMapper imageMapper;

	
	public void addImage(Image image) {
		imageMapper.insertSelective(image);
	}

	
	public int deleteByProductId(int productId) {
		return imageMapper.deleteByProductId(productId);
	}

	@Transactional(readOnly = true)
	public List<Image> getByProductId(int productId) {
		List<Image> list = imageMapper.getByProductId(productId);
		return list;
	}
}
