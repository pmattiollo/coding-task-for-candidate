package com.luxoft.recruitment.cstr.http;

import com.luxoft.recruitment.cstr.model.IPAdressBlackList;

public class Request {

	private final IPAdressBlackList blackListRegistry;

	public Request(String ipAdress) {
		this.blackListRegistry = new IPAdressBlackList(ipAdress);
	}

	public final IPAdressBlackList getBlackListRegistry() {
		return blackListRegistry;
	}

	public final String getIpAdress() {
		return getBlackListRegistry().getIpAdress();
	}
}
