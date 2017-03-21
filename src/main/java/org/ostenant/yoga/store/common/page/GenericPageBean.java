package org.ostenant.yoga.store.common.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GenericPageBean<T> implements AbstractPageBean<T>, Serializable {

	private static final long serialVersionUID = 7264205925643863460L;

	// -- 1. 传入的参数
	/* 当前页号 */
	private int pageNo;
	/* 页面大小 */
	private int pageSize;
	/* 符合条件的总记录数 */
	private int totalCount;
	/* 当前页分页元素集合 */
	private List<T> elements;

	// -- 2. 计算的结果
	/* 第一页码 */
	private int firstPage;
	/* 最后一页码 */
	private int lastPage;

	/* 当前分页列表起始页码 */
	private int beginPage;
	/* 当前分页列表结束页码 */
	private int endPage;

	/* 上一页页码 */
	private int prevPage;
	/* 下一页页码 */
	private int nextPage;

	/* 页面总数 */
	private int totalPage;

	/* constructor */

	public GenericPageBean() {
		super();
	}

	/**
	 * 使用默认页面大小
	 * 
	 * @param pageNo
	 * @param totalCount
	 * @param elements
	 */
	public GenericPageBean(int pageNo, int totalCount, List<T> elements) {
		this(pageNo, PageConstant.DEFAULT_PAGE_SIZE, totalCount, elements);
	}

	/**
	 * @Description: 构造方法1 根据当前页号、页大小（每页数据个数）、当前页数据列表、数据总个数构造分页数据对象的实例。<br>
	 * @param pageNo
	 *            当前页面大小
	 * @param pageSize
	 *            页面大小
	 * @param totalCount
	 *            总记录数
	 * @param elements
	 *            分页列表元素列表
	 */
	public GenericPageBean(int pageNo, int pageSize, int totalCount,
			List<T> elements) {
		this(pageNo, pageSize, totalCount, elements,
				PageConstant.DEFAULT_PAGINATION);
	}

	/**
	 * @Description: 构造方法1 根据当前页号、页大小（每页数据个数）、当前页数据列表、数据总个数构造分页数据对象的实例。<br>
	 * @param pageNo
	 *            当前页面大小
	 * @param pageSize
	 *            页面大小
	 * @param totalCount
	 *            总记录数
	 * @param elements
	 *            分页列表元素列表
	 * @param pagination
	 *            显示的最大页码
	 */
	public GenericPageBean(int pageNo, int pageSize, int totalCount,
			List<T> elements, int pagination) {

		if (pageNo <= 0) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}

		if (pageSize <= 0) {
			this.pageSize = PageConstant.DEFAULT_PAGINATION;
		} else {
			this.pageSize = pageSize;
		}

		this.totalCount = totalCount;
		this.elements = elements;

		if (pagination == 0) {
			pagination = PageConstant.DEFAULT_PAGINATION;
		} else if (pagination < PageConstant.MIN_PAGINATION
				|| pagination > PageConstant.MAX_PAGINATION) {
			pagination = PageConstant.DEFAULT_PAGINATION;
		}

		if (pagination % 2 == 0) { // 要求显示偶数个页面时
			pagination -= 1;
			caculateBeginPageAndEndPage(pagination);
		}
		// 要求显示奇数个页面时
		else {
			caculateBeginPageAndEndPage(pagination);
		}

	}

	public static void main(String[] args) {
		GenericPageBean<String> genericPageBean = new GenericPageBean<String>(
				1, 10, 3333, new ArrayList<String>(), 6);
		System.out.println(genericPageBean);
	}

	/**
	 * @Title: caculateBeginPageAndEndPage <br>
	 * @Description: 计算页码 <br>
	 * @Author: madison <br>
	 * @param @param pagination 设定文件 <br>
	 * @return void 返回类型 <br>
	 * @throws
	 */
	private void caculateBeginPageAndEndPage(int pagination) {
		if (pageSize > 0) {
			// 计算总页数
			totalPage = (totalCount % pageSize > 0) ? (totalCount / pageSize + 1)
					: (totalCount / pageSize);

			// 计算prevPage和nextPage
			prevPage = (pageNo > 1) ? pageNo - 1 : 1;
			nextPage = (pageNo < totalPage) ? pageNo + 1 : totalPage;

			if (totalPage <= pagination) {
				// a,总页数不超过odd页,全部显示
				firstPage = beginPage = 1;
				lastPage = endPage = totalPage;

			}
			// b,总页数超过odd页,显示当前页前2页和后2页
			else {
				// 初始化firstPage和lastPage
				firstPage = 1;
				lastPage = totalPage;

				// 计算beginPage
				beginPage = pageNo - (pagination - 1) / 2;
				// 计算endPage
				endPage = pageNo + (pagination - 1) / 2;

				if (beginPage < 1) {
					// 如果首页beginPage<0,显示前odd页
					beginPage = 1;
					endPage = pagination;
				} else if (endPage > totalPage) {
					// 如果尾页endPage>totalPage,显示后odd页
					beginPage = totalPage - pagination + 1;
					endPage = totalPage;
				}
			}
		}
	}

	/* to be implemented */

	
	public boolean isFirstPage() {
		return (pageNo == 1) && (prevPage >= pageNo);
	}

	
	public boolean isLastPage() {
		return (pageNo == totalPage) && (pageNo >= nextPage);
	}

	
	public boolean hasPrevPage() {
		return prevPage < pageNo;
	}

	
	public boolean hasNextPage() {
		return pageNo < nextPage;
	}

	
	public int getNextPageNo() {
		if (hasNextPage()) {
			return getNextPage();
		}
		return getLastPage();
	}

	
	public int getPrevPageNo() {
		if (hasPrevPage()) {
			return getPrevPage();
		}
		return 1;
	}

	
	public int getLastPageNo() {
		return getLastPage();
	}

	
	public int getTotalPageNo() {
		return getTotalPage();
	}

	
	public int getTotalCount() {
		return totalCount;
	}

	
	public int getPageNo() {
		return pageNo;
	}

	
	public int getPageSize() {
		return pageSize;
	}

	
	public int getThisPageFirstElementNo() {
		return beginPage;
	}

	
	public int getThisPageLastElementNo() {
		return endPage;
	}

	
	public int getThisPageTotalElementsNo() {
		return totalCount;
	}

	/* setter and getter */

	public List<T> getElements() {
		return elements;
	}

	public void setElements(List<T> elements) {
		this.elements = elements;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	
	public String toString() {
		return "GenericPageBean [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", totalCount=" + totalCount + ", elements=" + elements
				+ ", firstPage=" + firstPage + ", lastPage=" + lastPage
				+ ", beginPage=" + beginPage + ", endPage=" + endPage
				+ ", prevPage=" + prevPage + ", nextPage=" + nextPage
				+ ", totalPage=" + totalPage + "]";
	}

}
