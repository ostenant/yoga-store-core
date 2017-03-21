package org.ostenant.yoga.store.core.service.impl.product;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ostenant.yoga.store.common.utils.ExtBeanUtils;
import org.ostenant.yoga.store.core.bean.product.Brand;
import org.ostenant.yoga.store.core.dao.product.BrandMapper;
import org.ostenant.yoga.store.core.query.BrandQuery;
import org.ostenant.yoga.store.core.query.MultiBrandQuery;
import org.ostenant.yoga.store.core.service.product.BrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

	@Resource
	private BrandMapper brandDao;

	@Transactional(readOnly = true)
	public List<Brand> getBrandListBy(BrandQuery brandSearchCondtion) {
		BrandQuery searchCondtion = new BrandQuery();
		ExtBeanUtils.copyProperties(searchCondtion, brandSearchCondtion);

		List<Brand> brands = brandDao.getBrandListBy(searchCondtion);
		return brands;
	}

	@Transactional(readOnly = true)
	public Integer getTotalCountBy(BrandQuery brandSearchCondtion) {
		Integer totalCount = brandDao.getTotalCountBy(brandSearchCondtion);
		return totalCount;
	}

	public void addBrand(Brand brand) {
		brandDao.insertSelective(brand);
	}

	public void deleteBrandById(int id) {
		brandDao.deleteByPrimaryKey(id);
	}

	public void deleteBrandByIds(int[] ids) {
		List<Integer> idsList = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			idsList.add(ids[i]);
		}
		brandDao.deleteByIds(idsList);
	}

	public void upGrounding(MultiBrandQuery brandSearchCondtion) {
		brandSearchCondtion.setIsDisplay(1);
		brandDao.updateBatchByIds(brandSearchCondtion);
	}

	public void underCarriage(MultiBrandQuery brandSearchCondtion) {
		brandSearchCondtion.setIsDisplay(0);
		brandDao.updateBatchByIds(brandSearchCondtion);
	}

	@Transactional(readOnly = true)
	public Brand getBrandById(Integer id) {
		Brand brand = brandDao.selectByPrimaryKey(id);
		return brand;
	}

	public void editBrand(Brand brand) {
		brandDao.updateByPrimaryKeySelective(brand);
	}

	@Transactional(readOnly = true)
	public List<Brand> getBrandNameList() {
		List<Brand> brandList = brandDao.getBrandNameList();
		return brandList;
	}

	@Transactional(readOnly = true)
	public List<Brand> getBrandList(BrandQuery brandQuery) {
		List<Brand> brandList = brandDao.getBrandList(brandQuery);
		return brandList;
	}

}
