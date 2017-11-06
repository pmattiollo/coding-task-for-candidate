package com.luxoft.recruitment.cstr.model;

public class IPAdressBlackList {

	private String ipAdress;

	public IPAdressBlackList(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ipAdress == null) ? 0 : ipAdress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		IPAdressBlackList other = (IPAdressBlackList) obj;
		if (ipAdress == null) {
			if (other.ipAdress != null) {
				return false;
			}
		} else if (!ipAdress.equals(other.ipAdress)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "BlackList [ipAdress=" + ipAdress + "]";
	}

}
