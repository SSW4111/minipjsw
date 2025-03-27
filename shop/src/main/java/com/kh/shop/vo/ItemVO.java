package com.kh.shop.vo;

import lombok.Data;

@Data
public class ItemVO extends PageVO{
	//컬러체크박스 안 객체이름
	private String color; 
	
	//사이즈 이름 ex(s,m,xxl)
	private String clothesSize;
	
	//얘는 체크박스 선택시 컬러체크 ㅠ 작명부끄러움
	public boolean colorCheck() {
		return color != null;
	}
	//사이즈체크박스 선택시 
	public boolean sizeCheck() {
		return clothesSize != null;
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
			
	
			if(colorCheck()) {
				parameters.append("&color=").append(color);
			
				}
			if(sizeCheck()) {
				parameters.append("&clothesSize=").append(clothesSize);
			    }
			return parameters.toString();
			}
		}
	}


