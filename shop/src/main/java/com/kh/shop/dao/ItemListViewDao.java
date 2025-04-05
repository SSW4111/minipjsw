package com.kh.shop.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.ItemListViewDto;
import com.kh.shop.mapper.ItemListViewMapper;
import com.kh.shop.vo.ItemVO;

@Repository
public class ItemListViewDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ItemListViewMapper itemListViewMapper;
	
	//진심 진심 maybe 시간남으면 개노답리스트 최적화 도전
	
	//카운트 , color size 체크시 목록계산추가
	public int count(ItemVO itemVO) {
		StringBuilder sql = new StringBuilder();
		if(itemVO.isList()) {
			sql.append("select count(*) from ITEM_LIST_VIEW");
			return jdbcTemplate.queryForObject(sql.toString(), int.class);
		}
		else {
			//모든타입넣는리스트생성
			List<Object> dataList = new ArrayList <>();
			 sql.append("select count(*) from ITEM_LIST_VIEW ").append("where 1=1 "); 
			//전체검색 이게뭐임ㅠㅠ디비막쓴다
			if(itemVO.isSearch()) {
				 sql.append(" and ( ")
	                .append("instr(item_color, ?) > 0 or ")
	                .append("instr(item_size, ?) > 0 or ")
	                .append("instr(item_title, ?) > 0 or ")
	                .append("instr(item_content, ?) > 0 or ")
	                .append("instr(item_gender, ?) > 0 or ")
	                .append("instr(item_category, ?) > 0 or ")
	                .append("instr(item_detail, ?) > 0)");
				
			String keyword = itemVO.getKeyword();
			for (int i=0; i<7 ; i++) {
				dataList.add(keyword);
			}
			}
			//컬러골랐다면
			if(itemVO.colorCheck()) {
				sql.append(" and item_color = ? ");
				dataList.add(itemVO.getColor());
			}
			if(itemVO.sizeCheck()) {
				sql.append(" and item_size= ? ");
				dataList.add(itemVO.getClothesSize());
			}
		return jdbcTemplate.queryForObject(sql.toString(), int.class,dataList.toArray());		
		}	
		}
	
	//전체검색추가
	public List<ItemListViewDto>selectList(ItemVO itemVO){
		if(itemVO.isList()) {
		String sql="select * from ("
				+ "select rownum rn, TMP.* From( "
				+ "select * from ITEM_LIST_VIEW order by item_no desc "
				+ ")TMP "
				+ ")where rn between ? and ?";
		Object[] data = {itemVO.getStartRownum(), itemVO.getFinishRownum()};
		return jdbcTemplate.query(sql, itemListViewMapper,data);
		}
		else {
			StringBuilder sql = new StringBuilder();
			List<Object> dataList = new ArrayList<>();
			sql.append("select * from( ")
				.append("select rownum rn, TMP.*from(  ")
				.append("select * from ITEM_LIST_VIEW ");
			if(itemVO.isSearch()) {
				 sql.append(" and ( ")
				 .append("instr(item_color, ?) > 0 or ")
	                .append("instr(item_size, ?) > 0 or ")
	                .append("instr(item_title, ?) > 0 or ")
	                .append("instr(item_content, ?) > 0 or ")
	                .append("instr(item_gender, ?) > 0 or ")
	                .append("instr(item_category, ?) > 0 or ")
	                .append("instr(item_detail, ?) > 0)");
		       
				 String keyword = itemVO.getKeyword();
					for (int i=0; i<7 ; i++) {
					dataList.add(keyword);
			};
			};
			//컬러선택이면 
			if(itemVO.colorCheck()) {
				sql.append(" and item_color = ? ");
		        dataList.add(itemVO.getColor());
			}
			//사이즈선택이면 
			if(itemVO.sizeCheck()) {
				 sql.append(" AND item_size = ? ");
			     dataList.add(itemVO.getClothesSize());
			}
			sql.append(" order by item_no desc) TMP) where rn between ? and ?");
		
			dataList.add(itemVO.getStartRownum());
		    dataList.add(itemVO.getFinishRownum());   
		    return jdbcTemplate.query(sql.toString(), itemListViewMapper, dataList.toArray());
			
		}
	}
	
	
	public List<ItemListViewDto>selectListF(ItemVO itemVO){
	//여자 리스트 ...... F...
		if(itemVO.isList()) {
			String sql = "select * from( "
					+ "select rownum rn, TMP.* From( "
					+ "select * from ITEM_LIST_VIEW where item_gender = 'F' order by item_no desc ) "
					+ "TMP) "
					+ "where rn between ? and ? ";
			Object[] data = {itemVO.getStartRownum(), itemVO.getFinishRownum()};
			return jdbcTemplate.query(sql,itemListViewMapper,data);
		}
		
		else {
			StringBuilder sql = new StringBuilder();
			List<Object> dataList = new ArrayList<>();
			sql.append("select * from( ")
				.append("select rownum rn, TMP.*from( ")
				.append("select * from ITEM_LIST_VIEW where item_gender = 'F' " );
			//검색이면 사이에 top n query사이조건괄호 ()맞춰야함 ㄹㅇ짤라서넣어버리는;
			if(itemVO.isSearch()) {
				 sql.append("and ( ")
				 .append("instr(item_color, ?) > 0 or ")
	                .append("instr(item_size, ?) > 0 or ")
	                .append("instr(item_title, ?) > 0 or ")
	                .append("instr(item_content, ?) > 0 or ")
	                .append("instr(item_gender, ?) > 0 or ")
	                .append("instr(item_category, ?) > 0 or ")
	                .append("instr(item_detail, ?) > 0)");
		       
				 String keyword = itemVO.getKeyword();
					for (int i=0; i<7 ; i++) {
					dataList.add(keyword);
			};
			};
			//컬러선택이면 
			if(itemVO.colorCheck()) {
				sql.append(" and item_color = ? ");
		        dataList.add(itemVO.getColor());
			}
			//사이즈선택이면 
			if(itemVO.sizeCheck()) {
				 sql.append(" AND item_size = ? ");
			     dataList.add(itemVO.getClothesSize());
			}
			sql.append(" order by item_no desc) TMP) where rn between ? and ?");
		
			dataList.add(itemVO.getStartRownum());
		    dataList.add(itemVO.getFinishRownum());   
		    return jdbcTemplate.query(sql.toString(), itemListViewMapper, dataList.toArray());
		}
	}
	
	public List<ItemListViewDto>selectListM(ItemVO itemVO){
		//남자리스트....
			if(itemVO.isList()) {
				String sql = "select * from( "
						+ "select rownum rn, TMP.* From( "
						+ "select * from  ITEM_LIST_VIEW where item_gender = 'M' order by item_no desc ) "
						+ "TMP) "
						+ "where rn between ? and ? ";
				Object[] data = {itemVO.getStartRownum(), itemVO.getFinishRownum()};
				return jdbcTemplate.query(sql,itemListViewMapper,data);
			}
			
			else {
				StringBuilder sql = new StringBuilder();
				List<Object> dataList = new ArrayList<>();
				sql.append("select * from( ")
					.append("select rownum rn, TMP.*from( ")
					.append("select * from ITEM_LIST_VIEW where item_gender = 'M' " );

				if(itemVO.isSearch()) {
					 sql.append("and ( ")
					 .append("instr(item_color, ?) > 0 or ")
		                .append("instr(item_size, ?) > 0 or ")
		                .append("instr(item_title, ?) > 0 or ")
		                .append("instr(item_content, ?) > 0 or ")
		                .append("instr(item_gender, ?) > 0 or ")
		                .append("instr(item_category, ?) > 0 or ")
		                .append("instr(item_detail, ?) > 0)");
			       
					 String keyword = itemVO.getKeyword();
						for (int i=0; i<7 ; i++) {
						dataList.add(keyword);
				};
				};
				//컬러선택이면 
				if(itemVO.colorCheck()) {
					sql.append(" and item_color = ? ");
			        dataList.add(itemVO.getColor());
				}
				//사이즈선택이면 
				if(itemVO.sizeCheck()) {
					 sql.append(" AND item_size = ? ");
				     dataList.add(itemVO.getClothesSize());
				}
				sql.append(" order by item_no desc) TMP) where rn between ? and ?");
			
			
				dataList.add(itemVO.getStartRownum());
			    dataList.add(itemVO.getFinishRownum());   
			    return jdbcTemplate.query(sql.toString(), itemListViewMapper, dataList.toArray());
			}
		}
	
	
	
}
