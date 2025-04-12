package com.kh.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class AdminItemVO extends PageVO{
	//최신순
	private String recent;

	//별점높은순.. 클릭
	private String highStar;
	
	
	public boolean isRecent() {
		return recent != null && !recent.isEmpty();
	}
	
	public boolean isHighStar() {
		return highStar != null && !highStar.isEmpty();
	}
	
	
	
	// 순정 리스트
	@Override
	public boolean isList() {
		return !isSearch() && recent == null && highStar == null;
	}
	
	//컬럼 키워드있으면 검색 isSearch 부모
	
	@Override
	public String getParameters() {
	    StringBuilder parameters = new StringBuilder();
	    parameters.append("size=").append(size);
	    parameters.append("&page=").append(page);

	    if(isSearch()) {
	        parameters.append("&column=").append(column);
	        parameters.append("&keyword=").append(keyword);
	    }
	    if(isRecent()) {
	        parameters.append("&recent=").append(recent);
	    }
	    if(isHighStar()) {
	        parameters.append("&highstar=").append(highStar);
	    }
	    return parameters.toString();
	}

}
