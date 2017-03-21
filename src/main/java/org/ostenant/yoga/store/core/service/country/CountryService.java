package org.ostenant.yoga.store.core.service.country;

import java.util.List;

import org.ostenant.yoga.store.core.bean.country.City;
import org.ostenant.yoga.store.core.bean.country.Province;
import org.ostenant.yoga.store.core.bean.country.Town;

public interface CountryService {

	/**
	 * @Title: getAllProvinceList <br>
	 * @Description: 获取所有的省份 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return List<Province>    返回类型   <br>
	 * @throws
	 */
	List<Province> getAllProvinceList();

	/**
	 * @Title: getCityListByPid <br>
	 * @Description: 根据省份id获取城市列表 <br>
	 * @Author: madison <br>
	 * @param @param provinceCode
	 * @param @return    设定文件 <br>
	 * @return List<City>    返回类型   <br>
	 * @throws
	 */
	List<City> getCityListByPid(String provinceCode);

	/**
	 * @Title: getTownListByCid <br>
	 * @Description: 根据城市id获取城镇列表  <br>
	 * @Author: madison <br>
	 * @param @param city
	 * @param @return    设定文件 <br>
	 * @return List<Town>    返回类型   <br>
	 * @throws
	 */
	List<Town> getTownListByCid(String city);

	/**
	 * @Title: getProvinceNameById <br>
	 * @Description: 获取省份名称 <br>
	 * @Author: madison <br>
	 * @return Province    返回类型   <br>
	 * @throws
	 */
	Province getProvinceNameById(String province);

	/**
	 * @Title: getCityNameById <br>
	 * @Description: 获取城市名称 <br>
	 * @Author: madison <br>
	 * @return City    返回类型   <br>
	 * @throws
	 */
	City getCityNameById(String city);

	/**
	 * @Title: getTownNameById <br>
	 * @Description: 获取城镇名称 <br>
	 * @Author: madison <br>
	 * @return Town    返回类型   <br>
	 * @throws
	 */
	Town getTownNameById(String town);

}
