package org.ostenant.yoga.store.core.dao.country;

import java.util.List;

import org.ostenant.yoga.store.core.bean.country.City;

public interface CityMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);

    /**
     * @Title: getCityListByPid <br>
     * @Description: 通过省份id获取城市列表 <br>
     * @Author: madison <br>
     * @param @param provinceCode
     * @param @return    设定文件 <br>
     * @return List<City>    返回类型   <br>
     * @throws
     */
	List<City> getCityListByPid(String provinceCode);

	City getCityNameById(String city);
}