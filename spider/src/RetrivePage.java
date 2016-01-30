import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;








public class RetrivePage {
	
	private static HttpClient httpClient = new HttpClient();
	//设置代理服务器
	static {
		//设置代理服务器的IP地址和端口
		httpClient.getHostConfiguration().setProxy("172.17.18.84", 8080);
		
	}
	
	public static boolean downLoadPage(String path) throws HttpException,IOException{
		InputStream inputStream = null;
		OutputStream outputStream = null;
		//得到post方法
		PostMethod postMethod = new PostMethod(path);
		//设置post的参数
		NameValuePair [] postData = new NameValuePair[2];
		postData[0] = new NameValuePair("name","lietu");
		postData[1] = new NameValuePair("password","******");
		postMethod.addParameters(postData);
		//执行，返回状态码
		int statusCode = httpClient.executeMethod(postMethod);
		//2XX时
		if(statusCode == HttpStatus.SC_OK)
		{
			inputStream = postMethod.getResponseBodyAsStream();
			//得到文件名
			String fileName = path.substring(path.lastIndexOf('/')+1);
			//获得文件输出流
			outputStream = new FileOutputStream(fileName);
			//输出到文件
			int tempByte = -1;
			while ((tempByte = inputStream.read()) > 0) {
				outputStream.write(tempByte);				
			}
			//关闭输入输出流
			if(inputStream != null)
			{
				inputStream.close();
			}
			if(outputStream != null)
			{
				outputStream.close();
			}
		}
		//若是3XX，则进行转向操作
		if(   (statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
			||(statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
			||(statusCode == HttpStatus.SC_SEE_OTHER)
			||(statusCode == HttpStatus.SC_TEMPORARY_REDIRECT))
		{
			//读取新的URL地址
			Header header = postMethod.getRequestHeader("location");
			if(header!=null)
			{
				String newUrl = header.getValue();
				if( newUrl == null || newUrl.equals("") )
				{
					newUrl = "/";
					//使用post转向
					PostMethod redirect = new PostMethod(newUrl);
					//发送请求，做进一步处理...
				}
			}
		}
		
		return false;
		
	}
	
	/**
	 * 测试代码
	 * @param args
	 */
	public static void main(String [] args)
	{
		//抓取lietu首页，输出
		try {
			RetrivePage.downLoadPage("http://www.lietu.com/");
		} catch (HttpException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
