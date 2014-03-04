package org.test;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.housemart.framework.baiduapi.BaiduAPIWrapper;
import org.housemart.framework.baiduapi.BaiduApiJsonResultBean;
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
	
	@Test
	public void test1() throws JsonParseException, JsonMappingException, IOException {
		
		
		String result = BaiduAPIWrapper.invokeRequestCityCodeByLatLng("31.19", "121.42");
		System.out.println(result);
		/**
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		  
		BaiduApiJsonResultBean bean = mapper.readValue(result, BaiduApiJsonResultBean.class);
		System.out.println("result:" + bean.getResult().getCityCode());
		**/
		
	}
	
	
}
