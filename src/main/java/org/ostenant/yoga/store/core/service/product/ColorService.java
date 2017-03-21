package org.ostenant.yoga.store.core.service.product;

import java.util.List;

import org.ostenant.yoga.store.core.bean.product.Color;

public interface ColorService {

	/**
	 * @Title: getColorList <br>
	 * @Description: 获取颜色集合 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return List<Color>    返回类型   <br>
	 * @throws
	 */
	List<Color> getColorList();

	/**
	 * @Title: getColorById <br>
	 * @Description:获取颜色<br>
	 * @Author: madison <br>
	 * @param @param colorId
	 * @param @return    设定文件 <br>
	 * @return Color    返回类型   <br>
	 * @throws
	 */
	Color getColorById(Integer colorId);

}
