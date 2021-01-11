package br.com.alelo.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListUtils {

	public static boolean encotrarDuplicidade(List<String> listBooks) {

		final Set<String> setToReturn = new HashSet<String>(listBooks);
		return setToReturn.size() > 0;
	}

}
