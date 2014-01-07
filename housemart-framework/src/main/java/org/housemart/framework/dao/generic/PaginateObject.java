/**
 * 
 */
package org.housemart.framework.dao.generic;

import java.util.List;

/**
 * @author user
 *
 */
public class PaginateObject {

	private List<Object> result;
	private int count;
	private int pageNo;
	private int pageSize;
	public List<Object> getResult() {
		return result;
	}
	public void setResult(List<Object> result) {
		this.result = result;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
	
}
