package com.zimug.courses.security.basic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class BasicServerApplicationTests {

	@Test
	void contextLoads() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String str1="123456";
		String encode = bCryptPasswordEncoder.encode(str1);
		boolean matches = bCryptPasswordEncoder.matches(str1, encode);
		System.out.println(encode);
		System.out.println(matches);
	}

}
