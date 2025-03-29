package com.kh.shop.restcontroller;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.shop.dao.CertDao;
import com.kh.shop.dto.CertDto;
import com.kh.shop.service.CertService;

@RestController
@RequestMapping("/rest/cert")
public class CertRestController {
	@Autowired
	private CertService certService;
	@Autowired
	private CertDao certDao;
	
	@PostMapping("/send")
	public boolean send(@RequestParam String certEmail) {
		certService.sendCertEmail(certEmail);
		return true;
	}
	@PostMapping("/check")
	public boolean check(@ModelAttribute CertDto certDto) {
		CertDto findDto = certDao.selectOne(certDto.getCertEmail());
		if(findDto == null) {
			return false;
		}
		// 전송번호(DB), 전송번호(사용자가 입력) 비교, 유효기간 
		boolean condition1 = findDto.getCertNumber().equals(certDto.getCertNumber());
		// 발송시각
		LocalDateTime t1 = findDto.getCertTime().toLocalDateTime();
		LocalDateTime t2 = LocalDateTime.now();
		Duration duration = Duration.between(t1, t2); //Duration 은 시간까지 period 는 날짜만
		boolean condition2 = duration.toMinutes() < 10;
		boolean isValid = condition1 && condition2;
		if(isValid) {
			certDao.confirm(certDto.getCertEmail()); //인증 시간 DB 저장
		}
		
		return isValid;
	}
	
}

























