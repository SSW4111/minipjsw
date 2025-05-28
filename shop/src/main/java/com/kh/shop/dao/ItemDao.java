package com.kh.shop.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.ItemDto;
import com.kh.shop.dto.ItemImagesDto;
import com.kh.shop.mapper.ImagesMapper;
import com.kh.shop.mapper.ItemMapper;
import com.kh.shop.mapper.LikeListMapper;
import com.kh.shop.vo.ItemVO;

@Repository
public class ItemDao {

	@Autowired
	ItemMapper itemMapper;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private ImagesMapper itemImagesMapper;
	
	//시퀀스
	public int sequence() {
		String sql="select item_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	//등록
	public void insert(ItemDto itemDto) {
		System.out.println(itemDto);
		String sql="insert into item(item_no, item_title, item_gender, item_category, "
				+ " item_detail, item_color, item_price, item_content ) "
				+ " values(?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] data = {itemDto.getItemNo(), itemDto.getItemTitle(), itemDto.getItemGender(),
							itemDto.getItemCategory(), itemDto.getItemDetail(), itemDto.getItemColor(),
							itemDto.getItemPrice(), itemDto.getItemContent()};
		jdbcTemplate.update(sql,data);
		
	}
	
	//이미지connect
	public boolean connect(int itemNo, int attachmentNo) {
		String sql = "insert into item_images ("
				+ " item_no, attachment_no "
				+ " ) values(?,?)";
		Object[] data = {itemNo, attachmentNo};
		return jdbcTemplate.update(sql,data) > 0;
	}
	
	//삭제
	public boolean delete(int itemNo) {
		String sql="delete from item where item_no=?";
		Object[] data = {itemNo};
		return jdbcTemplate.update(sql,data)>0;
	}
	//수정
	public boolean update(ItemDto itemDto) {
		String sql = "update item set item_title=?, item_gender=?, item_category=?, item_detail=?, "
						+"item_color=?, item_price=?, item_content=? where item_no =? ";
		Object[] data = {itemDto.getItemTitle(), itemDto.getItemGender(), itemDto.getItemCategory(),
								itemDto.getItemDetail(), itemDto.getItemColor(), itemDto.getItemPrice(),
								itemDto.getItemContent(), itemDto.getItemNo()};
		return jdbcTemplate.update(sql,data) >0;
	}
	//상세 
	public ItemDto selectOne(int itemNo) {
		String sql="select * from item where item_no=?";
		Object[] data = {itemNo};
		List<ItemDto> list = jdbcTemplate.query(sql,itemMapper,data);
		return list.isEmpty()? null : list.get(0);
	}
	
	
	//카운트 , color size 체크시 목록계산추가
	public int count(ItemVO itemVO) {
		StringBuilder sql = new StringBuilder();
		if(itemVO.isList()) {
			sql.append("select count(*) from item");
			return jdbcTemplate.queryForObject(sql.toString(), int.class);
		}
		else {
			//모든타입넣는리스트생성
			List<Object> dataList = new ArrayList <>();
			 sql.append("select count(*) from item ").append("where 1=1 "); 
			//전체검색 이게뭐임ㅠㅠ디비막쓴다
			if(itemVO.isSearch()) {
				 sql.append(" and ( ")
	                .append("instr(item_color, ?) > 0 or ")
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
//			if(itemVO.sizeCheck()) {
//				sql.append(" and item_size= ? ");
//				dataList.add(itemVO.getClothesSize());
//			}
		return jdbcTemplate.queryForObject(sql.toString(), int.class,dataList.toArray());		
		}	
		}
	
	//아이템번호로 어태치리스트 조회 이미지무조건들어가기로
	public List<Integer> findAttachments(int itemNo) {
	    String sql = "select attachment.attachment_no from item "
	               + "LEFT OUTER JOIN item_images on item.item_no = item_images.item_no "
	               + "LEFT OUTER JOIN attachment on item_images.attachment_no = attachment.attachment_no "
	               + "where item.item_no = ? "
	               + "order by attachment_no asc";

	    return jdbcTemplate.queryForList(sql, Integer.class, itemNo);
	}

	public int findAttachmentByItem(int itemNo) {
	    String sql = 
	        "SELECT attachment_no " +
	        "FROM ( " +
	        "    SELECT attachment_no " +
	        "    FROM item_images " +
	        "    WHERE item_no = ? " +
	        "    ORDER BY attachment_no ASC " +
	        ") " +
	        "WHERE ROWNUM = 1";

	    Object[] data = { itemNo };
	    
	    return jdbcTemplate.queryForObject(sql, int.class, data);
	}

	public List<Integer> selectAttachmentList(int itemNo){
		String sql = "select attachment_no from item_images "
				+ "where item_no = ? "
				+ "order by attachment_no asc";
		Object[] data = {itemNo};
		return jdbcTemplate.query(sql,itemImagesMapper,data);
	}
	
	public List<ItemDto>selectListF(ItemVO itemVO){
	//여자 리스트 ...... F...
		if(itemVO.isList()) {
			String sql = "select * from( "
					+ "select rownum rn, TMP.* From( "
					+ "select * from item where item_gender = 'F' order by item_no desc ) "
					+ "TMP) "
					+ "where rn between ? and ? ";
			Object[] data = {itemVO.getStartRownum(), itemVO.getFinishRownum()};
			return jdbcTemplate.query(sql,itemMapper,data);
		}
		
		else {
			StringBuilder sql = new StringBuilder();
			List<Object> dataList = new ArrayList<>();
			sql.append("select * from( ")
				.append("select rownum rn, TMP.*from( ")
				.append("select * from item where item_gender = 'F' " );
			//검색이면 사이에 top n query사이조건괄호 ()맞춰야함 ㄹㅇ짤라서넣어버리는;
			if(itemVO.isSearch()) {
				 sql.append("and ( ")
				 .append("instr(item_color, ?) > 0 or ")
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
//			if(itemVO.sizeCheck()) {
//				 sql.append(" AND item_size = ? ");
//			     dataList.add(itemVO.getClothesSize());
//			}
			sql.append(" order by item_no desc) TMP) where rn between ? and ?");
		
			dataList.add(itemVO.getStartRownum());
		    dataList.add(itemVO.getFinishRownum());   
		    return jdbcTemplate.query(sql.toString(), itemMapper, dataList.toArray());
		}
	}
	
	public List<ItemDto>selectListM(ItemVO itemVO){
		//남자리스트....
			if(itemVO.isList()) {
				String sql = "select * from( "
						+ "select rownum rn, TMP.* From( "
						+ "select * from item where item_gender = 'M' order by item_no desc ) "
						+ "TMP) "
						+ "where rn between ? and ? ";
				Object[] data = {itemVO.getStartRownum(), itemVO.getFinishRownum()};
				return jdbcTemplate.query(sql,itemMapper,data);
			}
			
			else {
				StringBuilder sql = new StringBuilder();
				List<Object> dataList = new ArrayList<>();
				sql.append("select * from( ")
					.append("select rownum rn, TMP.*from( ")
					.append("select * from item where item_gender = 'M' " );

				if(itemVO.isSearch()) {
					 sql.append("and ( ")
					 .append("instr(item_color, ?) > 0 or ")
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
//				if(itemVO.sizeCheck()) {
//					 sql.append(" AND item_size = ? ");
//				     dataList.add(itemVO.getClothesSize());
//				}
				sql.append(" order by item_no desc) TMP) where rn between ? and ?");
			
			
				dataList.add(itemVO.getStartRownum());
			    dataList.add(itemVO.getFinishRownum());   
			    return jdbcTemplate.query(sql.toString(), itemMapper, dataList.toArray());
			}
		}
	
	
	
	
	
	
	
	
	
	
	//평균별점 구하는법 ㅋㅋ
//		String sql = "select i.*, "
//				+ " (select round(AVG(r.reviews_star), 1) "
//				+ " from reviews r "
//				+ " where r.item_no = i.item_no) AS aveStar "
//				+ " from item i"
		
	//avg ->평균별점 리뷰개수는걍count
	//round-->소숫점몇자리까지보여줄건지
	//coalesce -->데이터값없을때 null대신 0줌
	
	//조아요
	public void itemLike(String usersEmail, int itemNo) {
		String sql = "insert into item_like(users_email,item_no) values ( ?, ? )";
		Object[] data = {usersEmail, itemNo};
		jdbcTemplate.update(sql, data);
	}
	//조아요해제
	public void deleteItemLike(String usersEmail, int itemNo) {
		String sql = "delete from item_like where users_email=? and item_no = ? ";
		Object[] data = {usersEmail, itemNo};
		jdbcTemplate.update(sql, data);
	}
	//0보다 크면 좋아요가 존재한다 (조아요여부)
	public boolean checkItemLike(String usersEmail, int itemNo) {
		String sql = "select count(*) from item_like where users_email=? and item_no=?";
		Object[] data = {usersEmail, itemNo};
		return jdbcTemplate.queryForObject(sql, int.class,data) >0;
	}
	//개수
	public int countItemLike(int itemNo) {
		String sql = "select count(*) from item_like where item_no=?";
		Object[] data = {itemNo};
		return jdbcTemplate.queryForObject(sql, int.class, data);
	}
	
	@Autowired
	private LikeListMapper likeListMapper;
	public List<ItemDto> selectItemLike(String usersEamil){
		String sql = "select item_no from item_like where USERS_EMAIL = ?";
		Object[] data = {usersEamil};
		return jdbcTemplate.query(sql, likeListMapper, data);
	}
	
	
	
	//조아요개수갱신
	public boolean updateItemLike(int itemNo, int count) {
		String sql = "update item set item_like = ? where item_no =?";
		Object[] data = {count, itemNo};
		return jdbcTemplate.update(sql,data)>0;
	}
	public boolean updateItemLike(int itemNo) {
		String sql = "update item set item_like =( "
				+ "select count(*) from item_like where item_no =? "
				+ ") where item_no =?";
		Object[] data = {itemNo, itemNo};
		return jdbcTemplate.update(sql,data)>0;
	}
	
	//itemNo 만추출 
	public List<Integer>myItemLikeList(String usersEmail){
		String sql = "select item_no from item_like where users_email = ?";
		Object[] data = {usersEmail};
		return jdbcTemplate.queryForList(sql,Integer.class, data);
	}
	
	public int countM(ItemVO itemVO) {
	    StringBuilder sql = new StringBuilder();
	    List<Object> dataList = new ArrayList<>();
	    
	    sql.append("select count(*) from item where item_gender = 'M' "); 


	    if (itemVO.isSearch()) {
	        sql.append("and ( ")
//	           .append("instr(item_color, ?) > 0 or ")
	           .append("instr(item_title, ?) > 0 or ");
//	           .append("instr(item_content, ?) > 0 or ")
//	           .append("instr(item_gender, ?) > 0 or ")
//	           .append("instr(item_category, ?) > 0 or ")
//	           .append("instr(item_detail, ?) > 0)");
	        
	        String keyword = itemVO.getKeyword();
	        for (int i = 0; i < 7; i++) {
	            dataList.add(keyword);  
	        }
	    }

	    if (itemVO.colorCheck()) {
	        sql.append(" and item_color = ? ");
	        dataList.add(itemVO.getColor());
	    }


	    return jdbcTemplate.queryForObject(sql.toString(), int.class, dataList.toArray());
	}

	public int countF(ItemVO itemVO) {
	    StringBuilder sql = new StringBuilder();
	    List<Object> dataList = new ArrayList<>();
	    
	    sql.append("select count(*) from item where item_gender = 'F' "); 


	    if (itemVO.isSearch()) {
	        sql.append("and ( ")
//	           .append("instr(item_color, ?) > 0 or ")
	           .append("instr(item_title, ?) > 0 or ");
//	           .append("instr(item_content, ?) > 0 or ")
//	           .append("instr(item_gender, ?) > 0 or ")
//	           .append("instr(item_category, ?) > 0 or ")
//	           .append("instr(item_detail, ?) > 0)");
	        
	        String keyword = itemVO.getKeyword();
	        for (int i = 0; i < 7; i++) {
	            dataList.add(keyword);  
	        }
	    }

	    if (itemVO.colorCheck()) {
	        sql.append(" and item_color = ? ");
	        dataList.add(itemVO.getColor());
	    }


	    return jdbcTemplate.queryForObject(sql.toString(), int.class, dataList.toArray());
	}
	
	

	
}

	 

	
	

