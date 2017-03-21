package org.ostenant.yoga.store.core.service.impl.country;

import java.util.List;

import org.ostenant.yoga.store.core.bean.country.City;
import org.ostenant.yoga.store.core.bean.country.Province;
import org.ostenant.yoga.store.core.bean.country.Town;
import org.ostenant.yoga.store.core.dao.country.CityMapper;
import org.ostenant.yoga.store.core.dao.country.ProvinceMapper;
import org.ostenant.yoga.store.core.dao.country.TownMapper;
import org.ostenant.yoga.store.core.service.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

	@Autowired
	private ProvinceMapper provinceMapper;

	@Autowired
	private CityMapper cityMapper;

	@Autowired
	private TownMapper townMapper;

	@Transactional(readOnly = true)
	public List<Province> getAllProvinceList() {
		List<Province> provinces = provinceMapper.getAllProvinceList();
		return provinces;
	}

	@Transactional(readOnly = true)
	public List<City> getCityListByPid(String provinceCode) {
		List<City> cities = cityMapper.getCityListByPid(provinceCode);
		return cities;
	}

	@Transactional(readOnly = true)
	public List<Town> getTownListByCid(String cityCode) {
		List<Town> towns = townMapper.getTownListByCid(cityCode);
		return towns;
	}

	@Transactional(readOnly = true)
	public Province getProvinceNameById(String province) {
		return provinceMapper.getProvinceNameById(province);
	}

	@Transactional(readOnly = true)
	public City getCityNameById(String city) {
		return cityMapper.getCityNameById(city);
	}

	@Transactional(readOnly = true)
	public Town getTownNameById(String town) {
		return townMapper.getTownNameById(town);
	}

}
