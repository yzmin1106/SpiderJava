package part3;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;
import java.util.logging.Level;

public class Util {
	private static StringBuilder sBuilder = new StringBuilder();
	
	/**
	 * 从IP的字符串形式得到字节数组的形式
	 * @param ip 
	 * @return
	 */
	public static byte[] getByteArrayFromString(String ip)
	{
		byte[] ret = new byte[4];
		StringTokenizer sTokenizer = new StringTokenizer(ip,".");
		try {
			ret[0] = (byte)(Integer.parseInt(sTokenizer.nextToken()) & 0xFF);
			ret[1] = (byte)(Integer.parseInt(sTokenizer.nextToken()) & 0xFF);
			ret[2] = (byte)(Integer.parseInt(sTokenizer.nextToken()) & 0xFF);
			ret[3] = (byte)(Integer.parseInt(sTokenizer.nextToken()) & 0xFF);
 		} catch (Exception e) {
 		//	System.err.println("从IP的字符串形式得到字节数组形式报错:"+e);
			LogFactory.log("从IP的字符串形式得到字节数组形式报错",Level.WARNING,e);
		}
 		
 		return ret;
	}
	
	/**
	 * 
	 * @param ip IP的字节数组形式
	 * @return 字符串形式的IP
	 */
	public static String getIpStringFromBytes(byte [] ip)
	{
		sBuilder.delete(0, sBuilder.length());
		sBuilder.append(ip[0] & 0xFF);
		sBuilder.append('.');
		sBuilder.append(ip[1] & 0xFF);
	    sBuilder.append('.');
	    sBuilder.append(ip[2] & 0xFF);
	    sBuilder.append('.');
	    sBuilder.append(ip[3] & 0xFF);
	    return sBuilder.toString();
	}
	
	/**
	 * 根据某种编码方式将字节数组转换成字符串
	 * @param b 字节数组
	 * @param offset 要转换的起始位置
	 * @param len 要转换的长度
	 * @param encoding 如果encoding不支持，返回一个默认编码的字符串
	 * @return 
	 */
	public static String getString(byte [] b,int offset,int len,String encoding)
	{
		try {
			return new String(b,offset,len,encoding);
		} catch (UnsupportedEncodingException e) {
			return new String(b,offset,len);
		}
		
	}
	
	
	
	
}
