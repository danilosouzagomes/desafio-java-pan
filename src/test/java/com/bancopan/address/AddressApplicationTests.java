package com.bancopan.address;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AddressApplicationTests {

	@DisplayName("Test Danilo")
	@Test
	void test1(){
		Assertions.assertEquals("teste", "teste");
	}

}
