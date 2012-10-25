package com.bookshop.util;

import java.util.HashMap;
import java.util.Map;

import com.bookshop.model.Book;

public class Util {

	public static float calculateAmount(HashMap<Integer, Book> map) {
		float retval = 0.0f;

		for (Map.Entry<Integer, Book> val : map.entrySet()) {

			Book b = val.getValue();
			float t_val = b.getQuantity() * Float.parseFloat(b.getPrice());

			retval += t_val;
		}

		return retval;
	}
}
