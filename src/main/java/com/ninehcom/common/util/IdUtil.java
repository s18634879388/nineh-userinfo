package com.ninehcom.common.util;

public class IdUtil {
	
	private static Integer idIndex = 1;
	/**
	 * 生成唯一性id
	 * <p>
	 * 当前时间秒值加3位同步数字，使用秒值避免long型越界
	 * </p>
	 * 
	 * @return
	 */
	public static synchronized long nextId() {
		if (idIndex > 999)
			idIndex = 1;
		return Long.parseLong(System.currentTimeMillis() + IdUtil.getIntAsString(idIndex++,3));
	}

	public static String getIntAsString(Integer value,Integer digit){
		String _value =  String.valueOf(value);
		if (_value.length()==digit) {
			return _value;
		} else if (_value.length()>=digit) {
		   return _value.substring(_value.length()-digit,_value.length());
		} else {
			int distance = digit - _value.length();
			for (int i = 0 ;i<distance; i++) {
				_value = "0"+_value;
			}
		}
		return _value;
	}

}
