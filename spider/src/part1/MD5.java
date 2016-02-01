package part1;

public class MD5 {
	public static String getMD5(byte [] source)
	{
		String s = null;
		char hexDigits [] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		try {
			java.security.MessageDigest mDigest = java.security.MessageDigest.getInstance("MD5");
			mDigest.update(source);
			/**
			 * MD5的计算结果就是一个128位的长整数，用字节表示就是16个字节
			 * 每个字节用16位进制表示的话，使用两个字符，所以表示成16进制需要32个字符
			 */
			byte tmp []  =  mDigest.digest();
			char str []  = new char [16 * 2];
			/**
			 * 表示转换结果中对应的字符位置
			 */
			int k = 0;
			/**
			 * 从第一个字节开始，将MD5的每一个字节转换成16进制字符
			 * 取第i个字节
			 */
			for (int i = 0; i < 16; i++) {
				byte byte0  = tmp[i];
				/**
				 * 取字节中高4位的数字转换，>>>为逻辑右移，将符号位一起右移，
				 * 取字节低4位的数字转换
				 * 将换后的结果转换成字符串
				 */
				str[ k++ ] = hexDigits[ byte0 >>> 4 & 0xf ];
				str[ k++ ] = hexDigits[ byte0 & 0xf ];
				
			}
			s = new String(str);
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return s;
	}

}
