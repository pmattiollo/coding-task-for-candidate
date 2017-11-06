package com.luxoft.recruitment.cstr.db;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Database {

	public static Map<Table, Set<?>> schema;

	static {
		schema = new HashMap<>();
		Arrays.asList(Table.values()).forEach(t -> schema.put(t, new HashSet<>()));
	}

	@SuppressWarnings("unchecked")
	public static final <T> boolean insert(Table table, T registry) {
		if (registry == null) {
			throw new IllegalArgumentException("The register must be not null");
		}

		schema.merge(table, Stream.of(registry).collect(Collectors.toSet()), (Set<?> oldValue, Set<?> value) -> {
			Set<T> oldSet = (Set<T>) oldValue;
			Set<T> newSet = (Set<T>) value;
			oldSet.addAll(newSet);

			return new HashSet<>(oldSet);
		});

		return true;
	}

	@SuppressWarnings("unchecked")
	public static final <T> boolean remove(Table table, T registry) {
		if (registry == null) {
			throw new IllegalArgumentException("The register must be not null");
		}

		schema.computeIfPresent(table, (key, value) -> {
			Set<T> set = (Set<T>) value;
			set.remove(registry);
			return new HashSet<>(set);
		});

		return true;
	}

	@SuppressWarnings("unchecked")
	public static final <T> boolean update(Table table, T registry) {
		schema.compute(table, (key, value) -> {
			if (value == null) {
				throw new IllegalArgumentException("There's no value for the associated key");
			}

			Set<T> set = (Set<T>) value;
			if (!set.contains(registry)) {
				throw new IllegalArgumentException("The register does not exists on Database");
			}

			set.remove(registry);
			set.add(registry);
			return new HashSet<>(set);
		});

		return true;
	}

	@SuppressWarnings("unchecked")
	public static final <T> boolean contains(Table table, T registry) {
		if (registry == null) {
			throw new IllegalArgumentException("The register must be not null");
		}

		Set<T> set = (Set<T>) schema.get(table);
		return set.contains(registry);
	}

	@SuppressWarnings("unchecked")
	public static final <T> Set<T> selectAll(Table table) {
		return Collections.unmodifiableSet((Set<T>) schema.computeIfPresent(table, (key, value) -> {
			return (Set<T>) value;
		}));
	}

	public static final <T> void removeAll(Table table) {
		schema.get(table).clear();
	}

}
