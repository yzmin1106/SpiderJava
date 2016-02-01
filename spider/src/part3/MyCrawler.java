package part3;

import java.util.Set;

import part1.DownLoadFile;
import part1.HtmlParseTool;
import part1.LinkFilter;
import part1.LinkQueue;

public class MyCrawler {
	
	private ComputeUrl computeUrl = null;
	public MyCrawler()
	{
		computeUrl = new PageRankComputeUrl();
	}

	private void initCrawlerWithSeeds(String [] seeds)
	{
		for (int i = 0; i < seeds.length; i++) {
			LinkQueue.addUnvisitedUrl(seeds[i]);
		}
	}
	/**
	 * 抓取过程
	 * @param seeds
	 */
	public void crawling(String [] seeds)
	{
		//定义过滤器，提取以http://www.lietu.com开头的连接
		LinkFilter filter = new LinkFilter() {
			
			@Override
			public boolean accept(String url) {
				if(url.startsWith("http://www.lietu.com"))
				{
					return true;
				}
				return false;
			}
		};
		//初始化URL队列
		initCrawlerWithSeeds(seeds);
		//循环条件：待抓取的连接不空且抓取的网页不多于1000
		while (!LinkQueue.isUnVisitedUrlsEmpty()) {
		
			//队头URL出队列
			String visitUrl = (String)LinkQueue.unVisitedUrlDeQueue();
			//如果URL为空或者没有通过连接分析的计算
			if(visitUrl == null)
			{
			  continue;	
			}
			DownLoadFile downLoadFile = new DownLoadFile();
			//下载网页
			String content = downLoadFile.downLoadFile(visitUrl);
			if(computeUrl.accept(visitUrl, content))
			{
				continue;
			}
			//该URL放入到已经访问的URL中
			LinkQueue.addVisitedUrl(visitUrl);
			//提取出下载网页中的URL
			Set<String> links = HtmlParseTool.extracLinks(visitUrl, filter);
			//新的未访问的URL入队
			for(String link:links)
			{
			  LinkQueue.addUnvisitedUrl(link);	
			}
			
		}
		
	}
	//main 方法入口
	public static void main(String [] args)
	{
		MyCrawler crawler = new MyCrawler();
		crawler.crawling(new String [ ]{"http://www.twt.edu.cn"});
	}
}
