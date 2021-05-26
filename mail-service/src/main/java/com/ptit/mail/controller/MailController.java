package com.ptit.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class MailController {
	@Autowired
	private JavaMailSender javaMailSender;

	@PostMapping(value = "/orders/send-mail/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
	public void sendNotifyCreateOrder(@PathVariable String email, @RequestBody String content){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@petshop.com");
		message.setTo(email);
		message.setSubject("Thông báo: Đơn hàng đã được tạo");
		message.setText(content);
		javaMailSender.send(message);
	}
}
