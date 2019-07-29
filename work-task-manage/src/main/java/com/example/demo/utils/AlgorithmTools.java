package com.example.demo.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AlgorithmTools {
	public static String getSHA256(String arg1) throws Exception
	{//sha256加密--待加密字符串
        MessageDigest msgDig = null;
        String sReturn = "";
        try {
        	msgDig = MessageDigest.getInstance("SHA-256");
        	msgDig.update(arg1.getBytes("UTF-8"));
            
            byte[] byteArray = msgDig.digest();
    		StringBuffer sha256StrBuff = new StringBuffer();
    		for (int i = 0; i < byteArray.length; i++) {
    			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
    				sha256StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
    			else
    				sha256StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
    		}
    		sReturn= sha256StrBuff.toString();
        }
        catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException("NO_SUCH_AEC,没有SHA-256运算符");
		} 
		catch (UnsupportedEncodingException e) 
		{
			throw new RuntimeException("UN_ENCODING,没有UTF-8编码");
		}
        return sReturn;
    }
	
	public static String getMD5(String arg1, String sType) throws Exception
	{//md5加密--待加密字符串, 加密类型
		String sReturn = "";
		MessageDigest msgDig = null;
		try 
		{
			msgDig = MessageDigest.getInstance("MD5");
			msgDig.reset();
			msgDig.update(arg1.getBytes("UTF-8"));
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException("NO_SUCH_AEC,没有MD5运算符");
		} 
		catch (UnsupportedEncodingException e) 
		{
			throw new RuntimeException("UN_ENCODING,没有UTF-8编码");
		}
		
		byte[] byteArray = msgDig.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		
		if("MD5Out32Str".equals(sType))
			sReturn = md5StrBuff.toString();
		else if("MD5Out32StrCap".equals(sType))
			sReturn = md5StrBuff.toString().toUpperCase();
		else if("MD5Out16Str".equals(sType))
			sReturn = md5StrBuff.toString().substring(8,24);
		else if("MD5Out16StrCap".equals(sType))
			sReturn = md5StrBuff.toString().substring(8,24).toUpperCase();
		return sReturn;
	}
	
	public static String Base64(String sType, String arg1) throws Exception
	{//base64加密以及解密--加密/解密, 待加密或者解密字符串
		String sReturn = null;
		byte[] temp = null;
		if("Encode".equals(sType))
		{//如果为加密
			try
			{
				temp = arg1.getBytes("utf-8");
			}
			catch(UnsupportedEncodingException e)
			{
				throw new RuntimeException("NO_SUCH_ENCODE,不支持编码格式utf-8");
			}
			if (temp != null)
			{
				sReturn = new BASE64Encoder().encode(temp);
				sReturn = sReturn.replaceAll("\r\n", "");
			}
		}
		else if("Decode".equals(sType))
		{//如果为解密
			try
			{
				BASE64Decoder decoder = new BASE64Decoder();
				temp = decoder.decodeBuffer(arg1);
				sReturn = new String(temp, "utf-8");
			}
			catch (IOException e) 
			{
				throw new RuntimeException("NO_SUCH_DECODE, 不支持解密");
			}
		}
		else
			throw new RuntimeException("ERROR_TYPE, 错误的类型");
		return sReturn; 
	}
}
