package org.ostenant.yoga.store.core.dao.country;

import java.util.List;

import org.ostenant.yoga.store.core.bean.country.Town;

public interface TownMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Town record);

    int insertSelective(Town record);

    Town selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Town record);

    int updateByPrimaryKey(Town record);

    /**
     * @Title: getTownListByCid <br>
     * @Description: 通过城市id获取城镇列表 <br>
     * @Author: madison <br>
     * @param @param cityCode
     * @param @return    设定文件 <br>
     * @return List<Town>    返回类型   <br>
     * @throws
     */
	List<Town> getTownListByCid(String cityCode);

	Town getTownNameById(String town);
	
}