package com.luxoft.recruitment.cstr.repository;

import com.luxoft.recruitment.cstr.db.Table;
import com.luxoft.recruitment.cstr.model.IPAdressBlackList;

public class IPAdressBlackListRepository extends AbstractRepository<IPAdressBlackList> {

	public IPAdressBlackListRepository() {
		super(Table.BLACKLIST);
	}

}
