package com.kh.shop.vo;

public class MorePageVO extends PageVO{
	//itemVO랑 똑같지만 그냥 페이징쓰고싶으면 쓰고 더보기쓰고싶으면 쓰셍VO
	int page =1;
	int size = 10;
	

	public int getNextPage() {
		return page+1;
	}
	
	public boolean isLastPage() { 
		return page == getPageCount();
	}	
	
	public boolean hasNextPage() {
		return page < this.getPageCount();
	}

}
