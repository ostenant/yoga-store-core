package org.ostenant.yoga.store.core.dao.product;

import java.util.List;

import org.ostenant.yoga.store.core.bean.product.Color;

public interface ColorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Color record);

    int insertSelective(Color record);

    Color selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Color record);

    int updateByPrimaryKey(Color record);

    /**
     * @Title: getColorList <br>
     * @Description: 获取颜色集合 <br>
     * @Author: madison <br>
     * @param @return    设定文件 <br>
     * @return List<Color>    返回类型   <br>
     * @throws
     */
	List<Color> getColorList();
}