package com.kh.shop.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


import lombok.Data;

//appllication.properties 와 같은 설정파일의 내용을 프로그램에 연동하는 파일
@Data 
@Component
@ConfigurationProperties(prefix = "custom.email") // 접두사
public class EmailProperties {
	private String username; // custom.email.username 
	private String password; // custom.email.password
}
