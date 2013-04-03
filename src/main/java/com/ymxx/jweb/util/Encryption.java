package com.ymxx.jweb.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.ymxx.jweb.exception.YmxxException;

/**
 * @author Administrator
 * 
 */
public class Encryption {

	/**
	 * 
	 */
	private Encryption() {
	}

	/**
	 * @param str
	 * @return
	 */
	public static String getMD5(String str) {

		if (null == str) {
			throw new YmxxException("for input illegal parameter,null");
		}
		return getMD5(str.getBytes());
	}

	/**
	 * @param source
	 * @return
	 */
	public static String getMD5(byte[] source) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();// MD5 的计算结果是一个 128 位的长整数，
			
			return byteArrayToHex(tmp);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new YmxxException(e.getMessage());
		}
	}

	/**
	 * @param f
	 * @return
	 * @throws IOException
	 */
	public static String getMD5(final File f) throws IOException {
		// 缓冲区大小（这个可以抽出一个参数）
		int bufferSize = 256 * 1024;
		FileInputStream fileInputStream = null;
		DigestInputStream digestInputStream = null;
		try {
			// 拿到一个MD5转换器（同样，这里可以换成SHA1）
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 使用DigestInputStream
			fileInputStream = new FileInputStream(f);
			digestInputStream = new DigestInputStream(fileInputStream,messageDigest);
			// read的过程中进行MD5处理，直到读完文件
			byte[] buffer = new byte[bufferSize];
			while (digestInputStream.read(buffer) > 0);
			// 获取最终的MessageDigest
			messageDigest = digestInputStream.getMessageDigest();
			// 拿到结果，也是字节数组，包含16个元素
			byte[] resultByteArray = messageDigest.digest();
			// 同样，把字节数组转换成字符串
			return byteArrayToHex(resultByteArray);

		} catch (NoSuchAlgorithmException e) {
			return null;
		} finally {
			try {
				digestInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try{
				fileInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * @param byteArray
	 * @return
	 */
	private static String byteArrayToHex(byte[] byteArray) {
		// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = { 
					'0', '1','2','3', 
					'4', '5','6','7', 
					'8', '9','A','B', 
					'C', 'D','E','F'
				};
		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray = new char[byteArray.length * 2];
		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		// 字符数组组合成字符串返回
		return new String(resultCharArray);
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static String Base64Encrypt(String str) {
		
		if(str == null){
			throw new YmxxException("for input illegal parameter,null");
		}
		return Base64Encrypt(str.getBytes());
	}
	/**
	 * @param bys
	 * @return
	 */
	public static String Base64Encrypt(byte[] bys) {
		BASE64Encoder base = new BASE64Encoder();
		String s = base.encode(bys);
		return s;
	}

	/**
	 * base64解码
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public static byte[] Base64Decrypt(String s) throws IOException {
		BASE64Decoder base = new BASE64Decoder();
		byte[] bys = base.decodeBuffer(s);
		return bys;
	}
}
