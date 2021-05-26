package com.ptit.sale.service;

import com.ptit.sale.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {

	@Autowired
	RestTemplate restTemplate;

	@Async
	public void sendMail(Orders orders){
		try {
			Map<String, Object> user = restTemplate.getForObject("http://user-service/v1/users/" + orders.getUserId(), HashMap.class);
			String email = user.get("email").toString();
//            Object user = restTemplate.getForObject("http://user-service/v1/users/" + orders.getUserId(), Object.class);
//            String email = ((User) user).getEmail();
			String requestEmail = "Don hang " + orders.getId() + " cua ban da duoc tao";
			restTemplate.postForObject("http://mail-service" + "/v1/orders/send-mail/" + email, requestEmail, String.class);
		} catch (Exception e){
			System.out.println(e);
		}
	}
}
