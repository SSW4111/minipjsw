package com.kh.shop.vo;

import java.util.List;

public class ItemVO extends PageVO{
	//컬러체크박스 안 객체이름리스트 ex)color -> *black, red (colors)  
	private List<String> colors; 
	
	//사이즈 이름리스트 ex(s,m,xxl)
	private List<String> clothesSizes;
	
	//얘는 체크박스 선택시 컬러체크 ㅠ 작명부끄러움
	public boolean colorCheck() {
		return colors != null && !colors.isEmpty();
	}
	//사이즈체크박스 선택시 
	public boolean sizeCheck() {
		return clothesSizes != null && !clothesSizes.isEmpty();
	}
	@Override
	public boolean isSearch() {
		return keyword != null;
	}
	
	public boolean isList() {
		return !isSearch();
	}
	
	
	public int getNextPage() {
		return page+1;
	}
	
	public boolean isLastPage() {
		return getPageCount() == page;
	}	
	
	public boolean hasNextPage() {
		return page < this.getPageCount();
	}
	
	@Override
	public String getParameters() {
		if(isList()) {
			return "size=" + size;
		}
		else {
			StringBuilder parameters = new StringBuilder();
			parameters.append("&size=").append(size);
			
			if(keyword != null) {
				parameters.append("&keyword=").append(keyword);
			}
			
			//생각해보니까 애 &colors계속나오겠네 얘도내일수정
			if(colors != null) {
				for(int i =0; i < colors.size(); i++) {
				parameters.append("&colors=").append(colors.get(i));
				}
			}
			if(clothesSizes != null) {
			    for(int i = 0; i < clothesSizes.size(); i++) {
			        parameters.append("&clothesSizes=").append(clothesSizes.get(i));
			    }
			}
			return parameters.toString();
	}
}
}
