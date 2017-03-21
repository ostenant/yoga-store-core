package org.ostenant.yoga.store.common.mvc.date;


import java.text.DateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;

/**
 * 项目名称：yoga-store <br>   
 * 类名称：DefaultDateEditor <br>   
 * 类描述：   自定义日期转换器 <br>
 * 创建人：madison <br>  
 * 创建时间：2015-10-22 下午4:24:33 <br>  
 * 修改人：madison  <br>
 * 修改时间：2015-10-22 下午4:24:33   <br>
 * 修改备注：   <br>
 * @version
 */
public class DefaultDateEditor extends CustomDateEditor {
	

	public DefaultDateEditor(DateFormat dateFormat, boolean allowEmpty) {
		super(dateFormat, allowEmpty);
	}

	public DefaultDateEditor(DateFormat dateFormat, boolean allowEmpty,
			int exactDateLength) {
		super(dateFormat, allowEmpty, exactDateLength);
	}

}
