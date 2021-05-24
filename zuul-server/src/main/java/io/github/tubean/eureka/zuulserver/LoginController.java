package io.github.tubean.eureka.zuulserver;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	@GetMapping("/login/{token}")
	public String homeAdmin(@PathVariable String token) throws FirebaseAuthException {
		FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
		String uid = decodedToken.getUid();

		//TODO generate token
		return uid;
	}
}
