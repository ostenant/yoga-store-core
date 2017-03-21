package org.ostenant.yoga.store.common.utils;

import java.util.UUID;

public class CharSequenceUtils {

	/**
	 * @Title: getMultiFileDir <br>
	 * @Description: 返回多级文件目录 分目录存储 <br>
	 * @Author: madison <br>
	 * @throws
	 */
	public static String getMultiFileDir() {
		// -- 设置分目录存储
		StringBuilder sb = new StringBuilder();
		String uuid = UUID.randomUUID().toString();
		String hashcode = (uuid.hashCode() + "").replaceFirst("-", "");
		System.out.println(hashcode);
		char[] charArray = hashcode.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (i > 0) {
				sb.append("/");
			}
			sb.append(charArray[i]);
		}
		return sb.toString();
	}

	/**
	 * @Title: arrayToString <br>
	 * @Description: 数组转为string <br>
	 * @Author: madison <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	public static String arrayToString(String... array) {
		StringBuilder sb = new StringBuilder();
		if (array != null && array.length > 0) {
			for (int i = 0; i < array.length; i++) {
				sb.append(array[i]);
				sb.append(",");

			}
		}
		String str = sb.toString();
		return str.substring(0, str.length() - 1);
	}

	public static void main(String[] args) {
		System.out.println(arrayToString(new String[] { "1", "32", "234", "4",
				"43" }));
	}

}
