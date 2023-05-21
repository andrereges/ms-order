package br.com.andre.order.msorder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MsOrderApplicationTests {

	@Test
	void shouldBootSuccessfully() {
		assertDoesNotThrow( () -> {
			String[] args = {};
			MsOrderApplication.main(args);
		});
	}
}