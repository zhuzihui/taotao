package com.yc.bean;

import java.util.List;
/**
 *分页返回total和rows的result
 */
public class PageHelperData {
	//2个参数page rows  返回的值total，rows
	private long total;	
	private List<?> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "PageHelperData [total=" + total + ", rows=" + rows + "]";
	}
	
	
}
