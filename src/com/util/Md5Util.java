package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {

	static MessageDigest MD5 = null;

    static {
        try {
        MD5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ne) {
        ne.printStackTrace();
        }
    }


    /**
     * 对一个文件获取md5值
     * @return md5串
     */
    public static String getMd5File(String filePath) {
    	File file = null;
        file = new File(filePath);
        FileInputStream fileInputStream = null;
        try {
        fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
            MD5.update(buffer, 0, length);
            }

            return new String(Hex.encodeHex(MD5.digest()));
        } catch (FileNotFoundException e) {
        e.printStackTrace();
            return null;
        } catch (IOException e) {
        e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStream != null)
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 求一个字符串的md5值
     * @param target 字符串
     * @return md5 value
     */
    public static String MD5(String target) {
    	char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
  				'A', 'B', 'C', 'D', 'E', 'F' };
  		try {
  			byte[] btInput = target.getBytes("utf-8");
  			// 获得MD5摘要算法的 MessageDigest 对象
  			MessageDigest mdInst = MessageDigest.getInstance("MD5");
  			// 使用指定的字节更新摘要
  			mdInst.update(btInput);
  			// 获得密文
  			byte[] md = mdInst.digest();
  			// 把密文转换成十六进制的字符串形式
  			// byte[] data = { (byte) 0xfe, (byte) 0xff, 0x00, 0x61 };
  			int j = md.length;
  			char str[] = new char[j * 2];
  			int k = 0;

  			for (int i = 0; i < j; i++) {
  				byte byte0 = md[i];
  				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
  				str[k++] = hexDigits[byte0 & 0xf];
  			}
  			return new String(str);
  		} catch (Exception e) {
  			return null;
  		}
    }
    
  	public final static String md5(String s) {
  		 return DigestUtils.md5Hex(s);
  	}


	public static void main(String[] args){
//		System.out.println(getMd5File("D:/zd.html"));
	}
}
