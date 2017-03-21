package org.ostenant.yoga.store;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ostenant.yoga.store.core.bean.country.Province;
import org.ostenant.yoga.store.core.response.ProductResponse;
import org.ostenant.yoga.store.core.service.country.CountryService;
import org.ostenant.yoga.store.core.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.common.web.aop.MemCachedUtil;

import com.danga.MemCached.MemCachedClient;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/application-context.xml"})
public class CaseTest {

	@Autowired
	private MemCachedClient memCachedClient;

	@Autowired
	private CountryService countryService;
	
	@Autowired
	private ProductService productService;
	
	@Test
	public void testMemcached(){
		
		memCachedClient.set("ip", "192.168.125.136");
		
		Object o = memCachedClient.get("ip");
		
		System.out.println(o);
		
		Map<String, Object> map = MemCachedUtil.getKeySet(memCachedClient);
		
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey());
		}
		
	}
	
	@Test
	public void testAop(){
		List<Province> allProvinceList = countryService.getAllProvinceList();
		for (Province province : allProvinceList) {
			System.out.println(province);
		}
	}
	@Test
	public void testAop2(){
		ProductResponse productResponse = productService.getProductById(44);
		System.out.println(productResponse);
	}
	
}
