package com.luxoft.recruitment.cstr;

import static com.luxoft.recruitment.cstr.http.HttpStatus.BAD_REQUEST;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.luxoft.recruitment.cstr.http.Request;
import com.luxoft.recruitment.cstr.http.Response;
import com.luxoft.recruitment.cstr.model.IPAdressBlackList;
import com.luxoft.recruitment.cstr.repository.IPAdressBlackListRepository;

@RunWith(JUnit4.class)
public class SuperApiControllerTest {

	private SuperApiController superApiController = new SuperApiController();
	private IPAdressBlackListRepository blackListRepository = new IPAdressBlackListRepository();

	private static final String IP_ADRESSS = "74.125.224.72";

	@Test
	public void test1() {
		IPAdressBlackList registry = new IPAdressBlackList(IP_ADRESSS);
		blackListRepository.insert(registry);

		Request request = new Request(IP_ADRESSS);
		Response response = superApiController.healthCheck(request);

		assertEquals(response.getCode(), BAD_REQUEST);
	}

}
