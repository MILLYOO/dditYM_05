package kr.or.ddit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

public class PasswordEncodeTest {

	@Test
	public void test() {
		String java = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("java");
		System.out.println(java);
	}

}
