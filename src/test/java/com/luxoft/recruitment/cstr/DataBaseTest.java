package com.luxoft.recruitment.cstr;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.luxoft.recruitment.cstr.model.IPAdressBlackList;
import com.luxoft.recruitment.cstr.repository.IPAdressBlackListRepository;

@RunWith(JUnit4.class)
public class DataBaseTest {

	@Rule
	public ExpectedException exceptionGrabber = ExpectedException.none();

	private IPAdressBlackList b1;
	private IPAdressBlackList b2;
	private IPAdressBlackList b3;
	private IPAdressBlackListRepository repository;

	@Before
	public void setUp() {
		b1 = new IPAdressBlackList("192.168.10.1");
		b2 = new IPAdressBlackList("192.168.10.2");
		b3 = new IPAdressBlackList("192.168.10.3");

		repository = new IPAdressBlackListRepository();
		repository.insert(b1);
		repository.insert(b2);
		repository.insert(b3);
	}

	@Test
	public void insertWithExceptionTest() {
		exceptionGrabber.expect(IllegalArgumentException.class);
		repository.insert(null);
	}

	@Test
	public void selectTest() {
		assertThat(repository.selectAll(), containsInAnyOrder(b1, b2, b3));
	}

	@Test
	public void removeTest() {
		repository.remove(b2);
		assertThat(repository.selectAll(), containsInAnyOrder(b1, b3));
	}

	@Test
	public void removeWithExceptionTest() {
		exceptionGrabber.expect(IllegalArgumentException.class);
		repository.remove(null);
	}

	@Test
	public void containsTest() {
		assertThat(repository.contais(b1), is(Boolean.TRUE));
		assertThat(repository.contais(new IPAdressBlackList("192.168.10.4")), is(Boolean.FALSE));
	}

	@After
	public void clean() {
		repository.removeAll();
	}

}
