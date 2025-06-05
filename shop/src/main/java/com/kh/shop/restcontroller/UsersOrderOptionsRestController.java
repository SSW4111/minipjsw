package com.kh.shop.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.shop.dao.UsersOrderOptionsDao;
import com.kh.shop.dto.UsersOrderOptionsDto;

import jakarta.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/rest/option")
public class UsersOrderOptionsRestController {

	@Autowired
	private UsersOrderOptionsDao usersOrderOptionsDao;

	@GetMapping("/one")
	public ResponseEntity<?> one(HttpSession session){
		try {
			String usersEmail = (String)session.getAttribute("usersEmail");
			UsersOrderOptionsDto dto = usersOrderOptionsDao.selectOne(usersEmail);
			return ResponseEntity.ok(dto);
			
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에러");	
		}
		
	} 

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestParam String request, HttpSession session){
		try {
			String usersEmail = (String)session.getAttribute("usersEmail");
			
			UsersOrderOptionsDto target = usersOrderOptionsDao.selectOne(usersEmail);
			if(target == null) {
				long no = usersOrderOptionsDao.sequence();
				UsersOrderOptionsDto dto = new UsersOrderOptionsDto();
				dto.setUsersOrderOptionsNo(no);
				dto.setUsersEmail(usersEmail);
				dto.setUsersOrderOptionsRequest(request);
				usersOrderOptionsDao.insert(dto);				
			}
			else {
				target.setUsersOrderOptionsRequest(request);
				usersOrderOptionsDao.update(target);
			}
			return ResponseEntity.ok("ok");
		}
		catch(Exception e) {
			System.err.println("예외 발생: " + e.getMessage());
			e.printStackTrace(); 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에러");	
			
		}
	}
	
	

	
	@GetMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam long no){
		try {
			usersOrderOptionsDao.delete(no);
			return ResponseEntity.ok("ok");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에러");	
			
		}
	}
	
	

}



























