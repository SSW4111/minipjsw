package com.kh.shop.vo;

public class ItemVO extends PageVO{

	public int getNextPage() {
		return page+1;
	}
	
	public boolean isLastPage() {
		return getPageCount() == page;
	}	
	
	public boolean hasNextPage() {
		return page < this.getPageCount();
	}
	
	
	
	

}
