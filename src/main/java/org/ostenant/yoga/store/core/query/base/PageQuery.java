package org.ostenant.yoga.store.core.query.base;

import org.apache.ibatis.type.Alias;
import org.ostenant.yoga.store.common.page.PageConstant;

/**
 * 项目名称：yoga-store <br>
 * 类名称：PageSearchCondition <br>
 * 类描述： 分页查询条件 <br>
 * 创建人：madison <br>
 * 创建时间：2015-10-27 上午11:39:20 <br>
 * 修改人：madison <br>
 * 修改时间：2015-10-27 上午11:39:20 <br>
 * 修改备注： <br>
 * 
 * @version
 */
@Alias("PageQuery")
public class PageQuery extends Query {

	private static final long serialVersionUID = -53921566530877382L;

	private int pageNo;

	private int pageSize = PageConstant.DEFAULT_PAGE_SIZE;

	// 开始行
	private int startRow;

	public int getPageNo() {
		if (pageNo <= 0) {
			return 1;
		}
		return pageNo;
	}

	public int getPageSize() {
		if (pageSize <= 0) {
			return PageConstant.DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartRow() {
		startRow = (getPageNo() - 1) * getPageSize();
		return startRow;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public String toString() {
		return "PageSearchCondition [pageNo=" + pageNo + ", pageSize="
				+ pageSize + ", startRow=" + startRow + "]";
	}

}
