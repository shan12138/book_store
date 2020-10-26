package com.hgd.ebp.util;

import java.util.Map;

public class ErrMap {
	public static String getMapData(Map<String ,String > errMap,String str){
		if (errMap == null) 
			return "";
		String error =  errMap.get(str);
		return (error == null) ? "" : error ;
	}
}
