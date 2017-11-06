package com.luxoft.recruitment.cstr.repository;

import java.util.Set;

import com.luxoft.recruitment.cstr.db.Database;
import com.luxoft.recruitment.cstr.db.Table;

public abstract class AbstractRepository<R> {

	private Table table;

	public AbstractRepository(Table table) {
		this.table = table;
	}

	public final boolean insert(R registry) {
		return Database.<R>insert(table, registry);
	}

	public final boolean remove(R registry) {
		return Database.<R>remove(table, registry);
	}

	public final boolean update(R registry) {
		return Database.<R>update(table, registry);
	}

	public final Set<R> selectAll() {
		return Database.<R>selectAll(table);
	}

	public final void removeAll() {
		Database.<R>removeAll(table);
	}

	public final boolean contais(R registry) {
		return Database.<R>contains(table, registry);
	}

}
