package com.kh.shop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.shop.dto.IoListViewDto;
import com.kh.shop.mapper.IoListViewMapper;
import com.kh.shop.vo.PageVO;

@Repository
public class IoListViewDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IoListViewMapper ioListViewMapper;
	public List<IoListViewDto>ioList(int itemNo, PageVO pageVO){
		String sql = "select * from("
				+ "   select rownum rn, TMP.* from("
				+ "         select * from io_list_view where item_no = ? "
				+ "	order by item_no asc"
				+ "   )TMP"
				+ ") where rn between ? and ?";
		Object[] data = {itemNo, pageVO.getStartRownum(),pageVO.getFinishRownum()};
		return jdbcTemplate.query(sql, ioListViewMapper, data);
	}
}
