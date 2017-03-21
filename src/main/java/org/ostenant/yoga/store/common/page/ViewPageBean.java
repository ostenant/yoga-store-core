package org.ostenant.yoga.store.common.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class ViewPageBean<T> extends GenericPageBean<T> {

	private static final long serialVersionUID = 6327776659519102146L;

	/* 分页视图 */
	private List<String> pageView;
	/* url路径 */
	private String url;
	/* 参数字符串 */
	private String paramStr;

	/* 参数map */
	private Map<String, Object> params;
	

	public ViewPageBean() {
		super();
	}

	public ViewPageBean(int pageNo, int totalCount, List<T> elements) {
		super(pageNo,totalCount,elements);
	}
	
	
	/**
	 * @Title: getTagA <br>
	 * @Description:  <br>
	 * @Author: madison <br>
	 * @param url
	 *            页面url
	 * @param params
	 *            参数列表字符串
	 * @param text
	 *            文本
	 * @param pageNo
	 *            当前页号
	 * @throws
	 */
	public String getTagA(String url, String params, Object text, int pageNo,
			int pageSize) {
		return "<a href='" + url + "?" + "&pageNo=" + pageNo + "&pageSize="
				+ pageSize + params + "'>" + text + "</a>";
	}

	public String getTagSpan(Object text) {
		return "<span>" + text + "</span>";
	}

	/**
	 * @Title: renderPageView <br>
	 * @Description: 渲染桌面视图 <br>
	 * @Author: madison <br>
	 * @param url
	 * @param params
	 */
	public List<String> renderPageView(String url,
			HashMap<String, Object> params) {
		
		Set<Map.Entry<String, Object>> entrySet = params.entrySet();
		Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
		StringBuilder sb = new StringBuilder();
		this.pageView = new ArrayList<String>();
		this.params = params;
		this.url = url;

		if (entrySet != null && entrySet.size() > 0) {
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				String key = entry.getKey();
				Object value = entry.getValue();
				String param = key + "=" + value;
				sb.append("&");
				sb.append(param);
			}
			this.paramStr = sb.toString().trim();
		} else {
			this.paramStr = "";
		}

		/* 首页和上一页 */
		if (getPageNo() != 1) {
			this.pageView.add(getTagA(this.url, this.paramStr, "首页", 1, (getPageSize() > 0) ? getPageSize() : PageConstant.DEFAULT_PAGE_SIZE ));
			this.pageView.add(getTagA(this.url, this.paramStr, "上一页",
					getPrevPage(), (getPageSize() > 0) ? getPageSize() : PageConstant.DEFAULT_PAGE_SIZE));
		} else {
			this.pageView.add("<a href='javascript:void(0)' >"
					+ "首页" + "</a>");
			this.pageView.add("<a href='javascript:void(0)' >"
					+ "上一页" + "</a>");
		}

		this.pageView.add("&nbsp;&nbsp;");
		for (int index = getBeginPage(); index <= getEndPage(); index++) {
			if (getPageNo() != index) {
				this.pageView.add(getTagA(this.url, this.paramStr, index,
						index, (getPageSize() > 0) ? getPageSize() : PageConstant.DEFAULT_PAGE_SIZE));
			} else {
				this.pageView
						.add("<a href='javascript:void(0)' style='background-color: #009FCC;color: white;'>"
								+ index + "</a>");
			}
		}
		this.pageView.add("&nbsp;&nbsp;");

		/* 下一页和最后一页 */
		if (getPageNo() != getLastPage()) {
			this.pageView.add(getTagA(this.url, this.paramStr, "下一页",
					getNextPage(), (getPageSize() > 0) ? getPageSize() : PageConstant.DEFAULT_PAGE_SIZE));
			this.pageView.add(getTagA(this.url, this.paramStr, "尾页",
					getLastPage(), (getPageSize() > 0) ? getPageSize() : PageConstant.DEFAULT_PAGE_SIZE));
		} else {
			this.pageView.add("<a href='javascript:void(0)' >"
					+ "下一页" + "</a>");
			this.pageView.add("<a href='javascript:void(0)' >"
					+ "尾页" + "</a>");
		}
		
		this.pageView.add("<a href='javascript:void(0)' >" + "共" + getTotalPage() + "页" + "</a>");
		this.pageView.add("<a href='javascript:void(0)' >" + "共" + getTotalCount() + "条记录" + "</a>");
		this.pageView.add("转到  <input type='text' id='PAGENO' size='2' /> 页 <input type='button' id='skip' class='hand btn60x20' value='确定' onclick=\"javascript:window.location.href = '" + url + "?" + "&pageNo=' + (/^[0-9]+$/.test($('#PAGENO').val()) ? $('#PAGENO').val() : 1) + '"+ this.paramStr + "'\"/><br>");
		return this.pageView;
	}

	/**
	 * @param pageNo
	 *            当前页号
	 * @param pageSize
	 *            页面元素大小
	 * @param totalCount
	 *            总记录数
	 * @param elements
	 *            分页元素集合
	 */
	public ViewPageBean(int pageNo, int pageSize, int totalCount,
			List<T> elements) {
		super(pageNo, pageSize, totalCount, elements,
				PageConstant.DEFAULT_PAGINATION);
	}

	/**
	 * @param pageNo
	 *            当前页号
	 * @param pageSize
	 *            页面元素大小
	 * @param totalCount
	 *            总记录数
	 * @param elements
	 *            分页元素集合
	 * @param pagination
	 *            页码数目
	 */
	public ViewPageBean(int pageNo, int pageSize, int totalCount,
			List<T> elements, int pagination) {
		super(pageNo, pageSize, totalCount, elements, pagination);
	}

	public List<String> getPageView() {
		return pageView;
	}

	public void setPageView(List<String> pageView) {
		this.pageView = pageView;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getParamStr() {
		return paramStr;
	}

	public void setParamStr(String paramStr) {
		this.paramStr = paramStr;
	}

}
