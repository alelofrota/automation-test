package br.com.alelo.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.datatable.DataTable;

public class ParserUtils {

	public static <M> M jsonToObject(String pathJson, Class<M> clazz) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(new File(pathJson), clazz);
		} catch (IOException e) {
			System.out.println(new StringBuilder("erro: ").append(e).toString());
			return null;
		}
	}

	public static String mapperDataTable(DataTable dataTable, int posicaoDataTable) {
		String value = null;
		for (Map<Object, Object> map : dataTable.asMaps(String.class, String.class)) {
			value = map.get(map.keySet().toArray()[posicaoDataTable]).toString();
		}
		return value;
	}

	public static String objectToJson(Object object) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			System.out.println(new StringBuilder("erro: ").append(e).toString());
			return null;
		}
	}
}
