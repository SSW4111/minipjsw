package com.kh.shop.restcontroller;

import java.util.List;

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

import com.kh.shop.dao.DeliveryDao;
import com.kh.shop.dto.DeliveryDto;

import jakarta.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/rest/delivery")
public class DeliveryRestController {
	@Autowired
	private DeliveryDao deliveryDao;
	
	@GetMapping("/one")
	public ResponseEntity<?> one(HttpSession session){
		try {
			String usersEmail = (String)session.getAttribute("usersEmail");
			DeliveryDto dto = deliveryDao.selectOne(usersEmail);
			return ResponseEntity.ok(dto);
		}
		catch(Exception e) {
			return ResponseEntity.status(404).body("Not Found Exception");
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> add(@ModelAttribute DeliveryDto dto, HttpSession session){
		try {
			String usersEmail = (String)session.getAttribute("usersEmail");
			int seq = deliveryDao.sequence();
			dto.setDeliveryNo(seq);
			dto.setUsersEmail(usersEmail);
			deliveryDao.insert(dto);
			return ResponseEntity.ok("ok");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에러");
		}
	}
	@GetMapping("/delete")
	public ResponseEntity<String> delete(int deliveryNo){
		try {
			deliveryDao.delete(deliveryNo);
			return ResponseEntity.ok("ok");
		}
		catch(Exception e) {			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에러");
		}
	}
	@GetMapping("/list")
	public ResponseEntity<?> list(HttpSession session){
		try {
			String usersEmail = (String)session.getAttribute("usersEmail");
			List<DeliveryDto> list = deliveryDao.selectUserDelivery(usersEmail);
			return ResponseEntity.ok(list);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에러");	
		}
	}

	
	@GetMapping("/set/main")
	public ResponseEntity<String> main(@RequestParam long deliveryNo, HttpSession session){
		String usersEmail = (String)session.getAttribute("usersEmail");
		try {
			deliveryDao.setMain(usersEmail, deliveryNo);
			return ResponseEntity.ok("ok");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에러");	
		}
	}
}

































