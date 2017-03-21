package org.ostenant.yoga.store.core.dao.country;

import java.util.List;

import org.ostenant.yoga.store.core.bean.country.Province;

public interface ProvinceMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);

    /**
     * @Title: getAllProvinceList <br>
     * @Description: 获取所有的省份 <br>
     * @Author: madison <br>
     * @param @return    设定文件 <br>
     * @return List<Province>    返回类型   <br>
     * @throws
     */
	List<Province> getAllProvinceList();

	Province getProvinceNameById(String province);
}