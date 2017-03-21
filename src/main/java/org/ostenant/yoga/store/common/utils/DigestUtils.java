package org.ostenant.yoga.store.common.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class DigestUtils {

	private DigestUtils() {
	}

	/** MD5 + 十六进制加密 */
	public static String md5WithHexEncoder(String password) {
		String algorithm = "MD5";
		// 加盐所谓加盐就是在用户密码的基础上按照某一规则添加不规律的字符
		MessageDigest instance = null;
		try {
			instance = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 加密
		byte[] digest = instance.digest(password.getBytes());
		// 十六进制加密
		char[] encodeHex = Hex.encodeHex(digest);

		return new String(encodeHex);

	}

	/** base64编码 */
	public static String base64Encoder(String text) {
		return base64Encoder(text.getBytes(Charset.defaultCharset()));
	}

	public static String base64Encoder(byte[] bytes) {
		return new String(Base64.encodeBase64(bytes));
	}

	/** base64解码 */
	public static String base64Decoder(String base64Text) {
		return new String(Base64.decodeBase64(base64Text.getBytes(Charset
				.defaultCharset())));
	}

	/** base64编码安全性检查 */
	public static String base64URLSafe(byte[] binaryData) {
		return new String(Base64.encodeBase64URLSafe(binaryData),
				Charset.defaultCharset());
	}

	/** md5加密 无法破解 */
	public static String md5(String text) {
		return org.apache.commons.codec.digest.DigestUtils.md5Hex(text);
	}

	public static String md5(byte[] bytes) {
		return org.apache.commons.codec.digest.DigestUtils.md5Hex(bytes);
	}

}
