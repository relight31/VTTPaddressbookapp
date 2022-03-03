package sg.edu.nus.iss.app.VTTPaddressbook;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import sg.edu.nus.iss.app.VTTPaddressbook.controller.AddressBookController;

@SpringBootTest
class VttPaddressbookApplicationTests {
	@Autowired
	private AddressBookController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull(); // confirm that controller can be instantiated
	}
}
