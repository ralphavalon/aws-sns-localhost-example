package com.example.awssnstest.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RepositoryDao {
	
	private static final Map<Integer, String> DATABASE = new HashMap<>();
	private static int i = 0;
	
	public static void add(String message) {
		DATABASE.put(i++, message);
	}
	
	public static Map<Integer, String> list() {
		return Collections.unmodifiableMap(DATABASE);
	}

}
