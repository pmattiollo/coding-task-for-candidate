package com.luxoft.recruitment.cstr;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.luxoft.recruitment.cstr.filter.loader.InitialFilterFileLoader;
import com.luxoft.recruitment.cstr.filter.loader.InitialFilterLoader;
import com.luxoft.recruitment.cstr.model.IPAdressBlackList;
import com.luxoft.recruitment.cstr.repository.IPAdressBlackListRepository;

@RunWith(JUnit4.class)
public class InitialFilterLoaderTest {

	@Test
	public void loadTest() throws IOException {
		IPAdressBlackListRepository repository = new IPAdressBlackListRepository();
		InitialFilterLoader loader = new InitialFilterFileLoader(repository);
		loader.load();

		assertThat(repository.selectAll(), hasSize(10));
		assertThat(repository.selectAll(), hasItem(new IPAdressBlackList("192.168.10.1")));
		assertThat(repository.selectAll(), not(hasItem(new IPAdressBlackList("255.255.255.255"))));
	}

}
