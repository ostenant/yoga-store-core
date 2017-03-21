package org.ostenant.yoga.store.common.page;

/**
 * 项目名称：yoga-store <br>   
 * 类名称：AbstractPageBean <br>   
 * 类描述： 支持泛型的分页接口 <br>
 * 创建人：madison <br>  
 * 创建时间：2015-10-26 上午11:02:47 <br>  
 * 修改人：madison  <br>
 * 修改时间：2015-10-26 上午11:02:47   <br>
 * 修改备注： 以下的抽象方法均用于EL表达式页面获取数据  <br>
 * @version
 */
public interface AbstractPageBean<T> {

	/**
	 * @Title: isFirstPage <br>
	 * @Description: 判断当前页是否为第一页 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return boolean    返回类型   <br>
	 * @throws
	 */
	public boolean isFirstPage();
	
	/**
	 * @Title: isLastPage <br>
	 * @Description: 判断当前页是否为最后一页 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return boolean    返回类型   <br>
	 * @throws
	 */
	public boolean isLastPage();
	
	/**
	 * @Title: hasNextPage <br>
	 * @Description: 判断当前页是否有下一页 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return boolean    返回类型   <br>
	 * @throws
	 */
	public boolean hasNextPage();
	
	/**
	 * @Title: hasPrevPage <br>
	 * @Description: 判断当前页是否有上一页 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return boolean    返回类型   <br>
	 * @throws
	 */
	public boolean hasPrevPage();
	
	/**
	 * @Title: getNextPageNo <br>
	 * @Description: 获取上一页编号  <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return Integer    返回类型   <br>
	 * @throws
	 */
	public int getNextPageNo();
	
	/**
	 * @Title: getPrevPageNo <br>
	 * @Description: 获取下一页编号  <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return Integer    返回类型   <br>
	 * @throws
	 */
	public int getPrevPageNo();
	
	/**
	 * @Title: getLastPageNo <br>
	 * @Description: 获取最后一页编号  <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return Integer    返回类型   <br>
	 * @throws
	 */
	public int getLastPageNo();
	
	/**
	 * @Title: getTotalPageNo <br>
	 * @Description: 获取总页数编号  <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return Integer    返回类型   <br>
	 * @throws
	 */
	public int getTotalPageNo();
	
	/**
	 * @Title: getTotalCount <br>
	 * @Description: 获取总记录数  <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return Integer    返回类型   <br>
	 * @throws
	 */
	public int getTotalCount();
	
	/**
	 * @Title: getPageNo <br>
	 * @Description: 获取当前页编号 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return Integer    返回类型   <br>
	 * @throws
	 */
	public int getPageNo();
	
	/**
	 * @Title: getPageSize <br>
	 * @Description: 获取每一页的页面大小 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return Integer    返回类型   <br>
	 * @throws
	 */
	public int getPageSize();
	
	/**
	 * @Title: getThisPageFirstElementNo <br>
	 * @Description: 获取当前显示的第一页的编号 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return Integer    返回类型   <br>
	 * @throws
	 */
	public int getThisPageFirstElementNo();
	
	/**
	 * @Title: getThisPageLastElementNo <br>
	 * @Description: 获取当前显示的最后一页的编号 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return Integer    返回类型   <br>
	 * @throws
	 */
	public int getThisPageLastElementNo();
	
	/**
	 * @Title: getThisPageTotalElementsNo <br>
	 * @Description: 获取当前页的页码总数 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return Integer    返回类型   <br>
	 * @throws
	 */
	public int getThisPageTotalElementsNo();
	
	
}
