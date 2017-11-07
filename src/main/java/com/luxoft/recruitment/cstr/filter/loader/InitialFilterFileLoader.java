package com.luxoft.recruitment.cstr.filter.loader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.luxoft.recruitment.cstr.model.IPAdressBlackList;
import com.luxoft.recruitment.cstr.repository.IPAdressBlackListRepository;

public class InitialFilterFileLoader implements InitialFilterLoader {

	private IPAdressBlackListRepository repository;

	public InitialFilterFileLoader(IPAdressBlackListRepository repository) {
		this.repository = repository;
	}

	@Override
	public void load() throws IOException {
		try (Stream<String> ipAdressess = Files.lines(Paths.get("src/main/resources/initial_blacklist"))) {
			ipAdressess.forEach(ipAdress -> repository.insert(new IPAdressBlackList(ipAdress)));
		}
	}

}
