package org.test;

import org.housemart.framework.security.AuthenticationService;
import org.junit.Test;

public class Tester {

	
	@Test
	public void test() throws Exception{
		
		AuthenticationService serivce = new AuthenticationService();
		String output = serivce.generateAppCode();
		System.out.println(output);
		serivce.decryptAppCode(output);

	}
	
	
}
