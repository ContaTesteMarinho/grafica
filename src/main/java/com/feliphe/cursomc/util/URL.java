package com.feliphe.cursomc.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static String decodeParam(String param) {
		
		try {
			return URLDecoder.decode(param, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> decodeIntList(String ids) {
		
		return Arrays.asList(ids.split(","))
				     .stream()
				     .map(elemento -> Integer.parseInt(elemento))
				     .collect(Collectors.toList());
	}
}
