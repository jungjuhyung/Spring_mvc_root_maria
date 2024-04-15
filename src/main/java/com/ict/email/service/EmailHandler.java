package com.ict.email.service;

import javax.mail.internet.MimeMessage;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

// EmailService에서 사용할 클래스
public class EmailHandler {
	private JavaMailSender mailSender;
	// Mime는 multipartFile 같은 데이터의 문서 타입이다. 사진, 글자 등을 포함하는 이메일 데이터
	private MimeMessage message;
	private MimeMessageHelper messageHelper;
	
	public EmailHandler(JavaMailSender mailSender) throws Exception {
		this.mailSender = mailSender;
		message = this.mailSender.createMimeMessage();
		// true가 없으면 단순 텍스트 메세지만 사용 가능하다.
		//messageHelper = new MimeMessageHelper(message, "UTF-8");
		
		// true : multipart 메세지를 사용하겠다는 의미(이미지 또한 사용 가능하다는 의미)
		messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		
	}
	
	// 제목
	public void setSubject(String subject) throws Exception {
		messageHelper.setSubject(subject);
	}
	
	// 내용
	public void setText(String text) throws Exception {
		// 여기에 true를 넣으면 태그가 적용된다. 디폴트값은 false
		messageHelper.setText(text, true);
	}
	
	// 보낸 사람의 이메일과 제목
	public void setFrom(String email, String name) throws Exception {
		messageHelper.setFrom(email, name);
	}
	
	// 받는 이메일
	public void setTo(String email) throws Exception {
		messageHelper.setTo(email);
	}
	
	// 보내기
	public void send() {
		mailSender.send(message);
	}
}
