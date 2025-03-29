package com.kh.shop.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import com.kh.shop.dao.CertDao;
import com.kh.shop.dto.CertDto;
import com.kh.shop.util.RandomGenerator;

@Service
public class CertService {

	@Autowired
	private JavaMailSender sender;
	@Autowired
	private CertDao certDao;
	@Autowired
	private RandomGenerator randomGenerator;

	public void sendCertEmail(String certEmail) {
		String number = randomGenerator.randomNumber(8);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(certEmail);
		message.setSubject("인증번호 안내");
		message.setText("인증번호 ["+number+"] 입니다");
		sender.send(message);
		
		// 보낼 객체 certDto 가 없어서 각 요소들을 모아서 builder 로 객체로 만들어서 파라미터로 보내는거임
		certDao.insertOrUpdate(CertDto.builder()
				.certEmail(certEmail)
				.certNumber(number)
				.build());
	}
}


























