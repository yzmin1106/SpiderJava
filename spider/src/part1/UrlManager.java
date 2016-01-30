package part1;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class UrlManager {

	
	public static String encoder(String url)
	{
		//对URL的特殊字符串转义
		URL _url = null;  
        try  
        {  
            _url = new URL(url);  
        }  
        catch (MalformedURLException e)  
        {  
            e.printStackTrace();  
        }  
        
        int index  = _url.getHost().length();
        	
			try {
				System.err.println(url);
				url ="http://"+_url.getHost() + URLEncoder.encode( url.substring(index), "utf-8");
				System.err.println(url);
			
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
   	   return url;
        	
		
	}
}
