package com.luxoft.recruitment.cstr.filter;

import com.luxoft.recruitment.cstr.http.Request;
import com.luxoft.recruitment.cstr.repository.IPAdressBlackListRepository;

public class IPAdressBlackListFilter {

	private final IPAdressBlackListRepository blackListRepository;

	public IPAdressBlackListFilter() {
		blackListRepository = new IPAdressBlackListRepository();
	}

	public boolean shouldBlock(Request request) {
		if (blackListRepository.contais(request.getBlackListRegistry())) {
			return true;
		}

		return false;
	}
}
