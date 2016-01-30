package part1;

import java.util.Set;

public class WideCrawler {

	
   public final static String baseUrl = "http://www.lietu.com";
	/**
	 * 使用种子初始化URL队列
	 * 
	 */
	private void initCrawlerWithSeeds(String [] seeds)
	{
		for (int i = 0; i < seeds.length; i++) {
			LinkQueue.addUnvisitedUrl(seeds[i]);
		}
		
	}
	
	
	public void crawling(String [] seeds)
	{
		//定义过滤器，提取以http://www.lietu.com开头的链接
		LinkFilter filter = new LinkFilter() {
			
			@Override
			public boolean accept(String url) {
			
				if(url.startsWith(baseUrl))
				{
					return true;
				}else {
					return false;
				}
				
			}
		};
		//初始化URL队列
		initCrawlerWithSeeds(seeds);
		//循环条件：待抓取的链接不空且抓取的网页不多于1000
		while(!LinkQueue.isUnVisitedUrlsEmpty() && LinkQueue.getVisitedUrlNum() <= 1000)
		{
			//队头URL出队列
			String visitUrl =  (String)LinkQueue.unVisitedUrlDeQueue();
			if(visitUrl == null)
			{
				continue;
			}
			
			DownLoadFile downLoader = new DownLoadFile();
			//下载网页
			downLoader.downLoadFile(visitUrl);
			//该URL放入已访问的URL中
			LinkQueue.addVisitedUrl(visitUrl);
			//提取出下载网页中的URL
			Set<String> links = HtmlParseTool.extracLinks(visitUrl, filter);
			//新的未访问的URL入队
			for (String link : links) {
				LinkQueue.addUnvisitedUrl(link);
			}
		}
		
	}
	//main方法入口
	public static void main(String [] args)
	{
		WideCrawler crawler = new WideCrawler();
		crawler.crawling(new String[]{baseUrl});
	}
}
