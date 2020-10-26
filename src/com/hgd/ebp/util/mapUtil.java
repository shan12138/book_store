package com.hgd.ebp.util;
import java.util.Map;
public class mapUtil {
	public static final int MAX_PAGE_LINES = 6;
	public static final int MAX_ORDER_LINES = 3;
	public static boolean flag = false;
	public static  String getMapData(Map<String,String> map,String key){
		if(map==null){
			return "";
		}
		String msg=map.get(key);
		return (msg==null) ? "" :msg;
	}
}
