package com.rahmania;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MosabaqaApplicationTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Test
	public void contextLoads() {
		String pass = "pass1234";
		System.out.println("Bahaa******************");
		logger.info(bCryptPasswordEncoder.encode(pass));
	}

}
