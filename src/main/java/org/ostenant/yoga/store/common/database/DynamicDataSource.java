package org.ostenant.yoga.store.common.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 项目名称：yoga-store <br>   
 * 类名称：DynamicDataSource <br>   
 * 类描述： 动态控制管理数据源  <br>
 * 创建人：madison <br>  
 * 创建时间：2015-10-22 下午6:23:28 <br>  
 * 修改人：madison  <br>
 * 修改时间：2015-10-22 下午6:23:28   <br>
 * 修改备注：   <br>
 * @version
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

	
	@Override
	protected Object determineCurrentLookupKey() {
		logger.debug("now it calls:" + DataSourceHolder.getLookupKey() + " database");
		return DataSourceHolder.getLookupKey();
	}

}
