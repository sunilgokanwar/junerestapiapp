package com.industrial.junerestapiapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest
class JunerestapiappApplicationTests {

	@Test
	void contextLoads() {
	}
	String constant="http://localhost:8080/";

    @Test
	public void testGetAllEmp() throws URISyntaxException {

		System.out.println("test stared");

		RestTemplate restTemplate=new RestTemplate();
		String url=constant+"getAllEmps";
		URI uri=new URI(url);
		ResponseEntity<String> response=restTemplate.getForEntity(uri,String.class);
		Assertions.assertEquals(200,response.getStatusCodeValue());
		System.out.println("test ended");

	}
}
