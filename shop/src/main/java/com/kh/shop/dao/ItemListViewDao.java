package com.kh.shop.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.ItemListViewDto;
import com.kh.shop.mapper.ItemListViewMapper;
import com.kh.shop.mapper.ItemListViewMapper2;
import com.kh.shop.vo.AdminItemVO;
import com.kh.shop.vo.ItemVO;

import lombok.extern.slf4j.Slf4j;

@Repository
public class ItemListViewDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ItemListViewMapper itemListViewMapper;
	@Autowired
	private ItemListViewMapper2 itemListViewMapper2;
	

	public List<ItemListViewDto> selectListMen(ItemVO itemVO){
		if(itemVO.isList()) {
			String sql = "select * from( "
			        + "select rownum rn, TMP.* From( "
			        +  "select * from  ITEM_LIST_VIEW where item_gender = 'M' order by item_no desc  "
			        + ") TMP "
			        + ") "
			        + "WHERE rn BETWEEN ? AND ?";
			Object[] data = {itemVO.getStartRownum(), itemVO.getFinishRownum()};
			return jdbcTemplate.query(sql, itemListViewMapper, data);
		}		
		else {
			StringBuilder sql = new StringBuilder();
			List<Object> dataList = new ArrayList<>();
			sql.append("select * from( ")
				.append("select rownum rn, TMP.*from( ")
				.append("select * from ITEM_LIST_VIEW where item_gender = 'M' and item_color = ? " );

		//	if(itemVO.isSearch()) {
//				 sql.append("and ( ")
//				 .append("instr (item_color, ?) > 0 or ")
//	                .append("instr(item_title, ?) > 0 or ")
//	                .append("instr(item_gender, ?) > 0 or ")
//	                .append("instr(item_category, ?) > 0 or ")
//	                .append("instr(item_price, ?) > 0 or ")
//	                .append("instr (item_detail, ?) > 0)");
		       
//				 String keyword = itemVO.getKeyword();
//					for (int i=0; i<7 ; i++) {
//					dataList.add(keyword);
//			//};
//			};
			//컬러선택이면 
//			if(itemVO.colorCheck()) {
//				sql.append(" and item_color = ? ");
		        dataList.add(itemVO.getColor());
//		  //      System.out.println("colorwerwerwerwerwer = " + itemVO.getColor());
//			}
			//사이즈선택이면 
			if(itemVO.sizeCheck()) {
				 sql.append(" AND item_size = ? ");
			     dataList.add(itemVO.getClothesSize());
			}
			sql.append(" order by item_no desc) TMP) where rn between ? and ?");
//			System.out.println("itemtieitietmietiemtietmiemi"+itemVO.getColor());
//			System.out.println("sqlsqlsqlsqlsqlsql = "+sql.toString());
//			System.out.println(dataList);
			dataList.add(itemVO.getStartRownum());
		    dataList.add(itemVO.getFinishRownum());   
		   // List<ItemListViewDto> listRR =  jdbcTemplate.query(sql.toString(), itemListViewMapper, dataList.toArray());
		    //System.out.println(listRR);
		    return jdbcTemplate.query(sql.toString(), itemListViewMapper, dataList.toArray());
		}
	}
	
	
	
	
	public List<ItemListViewDto> selectListWomen(ItemVO itemVO){
		String sql = "select * from( "
		        + "select rownum rn, TMP.* From( "
		        +  "select * from  ITEM_LIST_VIEW where item_gender = 'F' order by item_no desc  "
		        + ") TMP "
		        + ") "
		        + "WHERE rn BETWEEN ? AND ?";
		Object[] data = {itemVO.getStartRownum(), itemVO.getFinishRownum()};
		System.out.println("Strart RN = "+itemVO.getStartRownum());
		System.out.println("Finish RnN = "+itemVO.getFinishRownum());
		return jdbcTemplate.query(sql, itemListViewMapper, data);
		
	}
	
	
	
	
	
	
	
	
	
	
	//진심 진심 maybe 시간남으면 개노답리스트 최적화 도전
	
	//카운트 , color size 체크시 목록계산추가
	public int count(ItemVO itemVO) {
		StringBuilder sql = new StringBuilder();
		if(itemVO.isList()) {
			sql.append("select count(*) from ITEM_LIST_VIEW");
			return jdbcTemplate.queryForObject(sql.toString(), int.class);
		}
		else {
			List<Object> dataList = new ArrayList <>();
			sql.append("select count(*) from ITEM_LIST_VIEW where item_color = ? and item_gender = 'M'");
			dataList.add(itemVO.getColor());
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
//	
//	
//	public List<ItemListViewDto>selectListF(ItemVO itemVO){
//	//여자 리스트 ...... F...
//		if(itemVO.isList()) {
//			String sql = "select * from( "
//					+ "select rownum rn, TMP.* From( "
//					+ "select * from ITEM_LIST_VIEW where item_gender = 'F' order by item_no desc ) "
//					+ "TMP) "
//					+ "where rn between ? and ? ";
//			Object[] data = {itemVO.getStartRownum(), itemVO.getFinishRownum()};
//			return jdbcTemplate.query(sql,itemListViewMapper,data);
//		}
//		
//		else {
//			StringBuilder sql = new StringBuilder();
//			List<Object> dataList = new ArrayList<>();
//			sql.append("select * from( ")
//				.append("select rownum rn, TMP.*from( ")
//				.append("select * from ITEM_LIST_VIEW where item_gender = 'F' " );
//			//검색이면 사이에 top n query사이조건괄호 ()맞춰야함 ㄹㅇ짤라서넣어버리는;
//			if(itemVO.isSearch()) {
//				 sql.append("and ( ")
//				 .append("instr(item_color, ?) > 0 or ")
//	                .append("instr(item_size, ?) > 0 or ")
//	                .append("instr(item_title, ?) > 0 or ")
//	                .append("instr(item_content, ?) > 0 or ")
//	                .append("instr(item_gender, ?) > 0 or ")
//	                .append("instr(item_category, ?) > 0 or ")
//	                .append("instr(item_detail, ?) > 0)");
//		       
//				 String keyword = itemVO.getKeyword();
//					for (int i=0; i<7 ; i++) {
//					dataList.add(keyword);
//			};
//			};
//			//컬러선택이면 
//			if(itemVO.colorCheck()) {
//				sql.append(" and item_color = ? ");
//		        dataList.add(itemVO.getColor());
//			}
//			//사이즈선택이면 
//			if(itemVO.sizeCheck()) {
//				 sql.append(" AND item_size = ? ");
//			     dataList.add(itemVO.getClothesSize());
//			}
//			sql.append(" order by item_no desc) TMP) where rn between ? and ?");
//		
//			dataList.add(itemVO.getStartRownum());
//		    dataList.add(itemVO.getFinishRownum());   
//		    return jdbcTemplate.query(sql.toString(), itemListViewMapper, dataList.toArray());
//		}
//	}
//	
//	public List<ItemListViewDto2> selectListMen(ItemVO itemVO){
////		String sql = "SELECT ITEM_TITLE, ITEM_CATEGORY, ITEM_DETAIL,\r\n"
////				+ "       CAST(COLLECT(ITEM_NO) AS SYS.OdciNumberList) AS ITEM_NO_LIST\r\n"
////				+ "FROM KH15S2.ITEM\r\n"
////				+ "where item_gender = 'M'\r\n"
////				+ "GROUP BY ITEM_TITLE, ITEM_CATEGORY, ITEM_DETAIL\r\n"
////				+ "ORDER BY ITEM_TITLE, ITEM_CATEGORY, ITEM_DETAIL\r\n";
//		
//		String sql = "select * from( "
//		        + "select rownum rn, TMP.* From( "
//		        + "SELECT ITEM_TITLE, ITEM_CATEGORY, ITEM_DETAIL, "
//		        + "CAST(COLLECT(ITEM_NO) AS SYS.OdciNumberList) AS ITEM_NO_LIST "
//		        + "FROM KH15S2.ITEM "
//		        + "WHERE item_gender = 'M' "
//		        + "GROUP BY ITEM_TITLE, ITEM_CATEGORY, ITEM_DETAIL "
//		        + "ORDER BY ITEM_TITLE, ITEM_CATEGORY, ITEM_DETAIL "
//		        + ") TMP "
//		        + ") "
//		        + "WHERE rn BETWEEN ? AND ?";
//		Object[] data = {itemVO.getStartRownum(), itemVO.getFinishRownum()};
//		System.out.println("Strart RN = "+itemVO.getStartRownum());
//		System.out.println("Finish RnN = "+itemVO.getFinishRownum());
//		return jdbcTemplate.query(sql, itemListViewMapper2, data);
//	}
//	
//	
//	
//	public List<ItemListViewDto>selectListM(ItemVO itemVO){
//		//남자리스트....
//			if(itemVO.isList()) {
//				String sql = "select * from( "
//						+ "select rownum rn, TMP.* From( "
//						+ "select * from  ITEM_LIST_VIEW where item_gender = 'M' order by item_no desc ) "
//						+ "TMP) "
//						+ "where rn between ? and ? ";
//				Object[] data = {itemVO.getStartRownum(), itemVO.getFinishRownum()};
//				return jdbcTemplate.query(sql,itemListViewMapper,data);
//			}
//			
//			else {
//				StringBuilder sql = new StringBuilder();
//				List<Object> dataList = new ArrayList<>();
//				sql.append("select * from( ")
//					.append("select rownum rn, TMP.*from( ")
//					.append("select * from ITEM_LIST_VIEW where item_gender = 'M' " );
//
//				if(itemVO.isSearch()) {
//					 sql.append("and ( ")
//					 .append("instr(item_color, ?) > 0 or ")
//		                .append("instr(item_size, ?) > 0 or ")
//		                .append("instr(item_title, ?) > 0 or ")
//		                .append("instr(item_content, ?) > 0 or ")
//		                .append("instr(item_gender, ?) > 0 or ")
//		                .append("instr(item_category, ?) > 0 or ")
//		                .append("instr(item_detail, ?) > 0)");
//			       
//					 String keyword = itemVO.getKeyword();
//						for (int i=0; i<7 ; i++) {
//						dataList.add(keyword);
//				};
//				};
//				//컬러선택이면 
//				if(itemVO.colorCheck()) {
//					sql.append(" and item_color = ? ");
//			        dataList.add(itemVO.getColor());
//				}
//				//사이즈선택이면 
//				if(itemVO.sizeCheck()) {
//					 sql.append(" AND item_size = ? ");
//				     dataList.add(itemVO.getClothesSize());
//				}
//				sql.append(" order by item_no desc) TMP) where rn between ? and ?");
//			
//			
//				dataList.add(itemVO.getStartRownum());
//			    dataList.add(itemVO.getFinishRownum());   
//			    return jdbcTemplate.query(sql.toString(), itemListViewMapper, dataList.toArray());
//			}
//		}
//	
//		public List<ItemListViewDto> color(int itemNo){
//				String sql = "select item_color from item_list_view where item_no = ?";
//				Object[] data = {itemNo};
//				return jdbcTemplate.query(sql, itemListViewMapper, data);
//		}
//	
//	
//	
	
	//관리자용 All list view (+참고 최신순은 그냥 no desc임) 바꾸고싶음ㅁ바꾸생
	public List<ItemListViewDto> adminItemList(AdminItemVO adminItemVO){
		if(adminItemVO.isList()) {
			String sql = "select * from( "
					+ "select rownum rn, TMP.* from( "
					+ "select * from item_list_view order by item_no asc "
					+ ")  "
					+ "TMP) "
					+ "where rn between ? and ? ";
			Object[] data = {adminItemVO.getStartRownum(), adminItemVO.getFinishRownum()};
			return jdbcTemplate.query(sql, itemListViewMapper,data);
		}
		
		else {
			StringBuilder sql = new StringBuilder();
			List<Object> dataList = new ArrayList<>();
			sql.append("select * from( ");
			sql.append("select rownum rn, TMP.* from( ");
			//서치
				if(adminItemVO.isSearch()) {
					sql.append("select * from item_list_view where instr(" + adminItemVO.getColumn() + ", ?) > 0 ");
					dataList.add(adminItemVO.getKeyword());

				}
			//X서치
				else {
					sql.append("select * from item_list_view ");
				}
			sql.append("order by ");
				if(adminItemVO.isHighStar()) {
					sql.append("AVESTAR desc");
				} else if(adminItemVO.isRecent()) {
				    sql.append("item_no desc ");
				} else {
				    sql.append("item_no asc ");
				}
			sql.append(") ");
			sql.append("TMP) ");
			sql.append("where rn between ? and ? ");
			dataList.add(adminItemVO.getStartRownum());
			dataList.add(adminItemVO.getFinishRownum());

			return jdbcTemplate.query(sql.toString(), itemListViewMapper, dataList.toArray());
		}	
	}
	
	
}
