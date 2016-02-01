package part1;

import java.io.FileNotFoundException;
import java.security.KeyStore.Entry;
import java.util.Set;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.je.DatabaseException;
/**
 * 实现真正的TODO表
 * @author jonn
 *
 */
public class BDBFrontier extends AbstractFronter implements Frontier{

	
	private StoredMap pendingUrisDB = null;
	/**
	 * 使用默认的路径和缓存大小
	 * @param homeDirectory
	 * @throws DatabaseException
	 * @throws FileNotFoundException
	 */
	public BDBFrontier(String homeDirectory) throws DatabaseException,
			FileNotFoundException {
		super(homeDirectory);
		//设置key的类型
		EntryBinding keyBinding = new SerialBinding(javaCatalog, String.class);
		//设置value的类型
		EntryBinding valueBinding = new SerialBinding(javaCatalog, CrawUrl.class);
		pendingUrisDB = new StoredMap(database, keyBinding, valueBinding, true);
		
	}

	/**
	 * 删除
	 */
	@Override
	protected Object delete(Object key) {
		
		return pendingUrisDB.remove(key);
	}

	/**
	 * 取出
	 */
	@Override
	protected Object get(Object key) {
		
		return pendingUrisDB.get(key);
	}

	/**
	 * 存入数据库的方法
	 */
	@Override
	protected void put(Object key, Object value) {
		pendingUrisDB.put(key, value);
		
	}

	/**
	 * 获得下一条记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CrawUrl getNext() throws Exception {
		CrawUrl result = null;
		if(!pendingUrisDB.isEmpty())
		{
			Set entrySet = pendingUrisDB.entrySet();
			System.out.println(entrySet);
			java.util.Map.Entry<String,CrawUrl> entry = (java.util.Map.Entry<String,CrawUrl>)pendingUrisDB.entrySet().iterator().next();
		    result = entry.getValue();
		 //   delete(entry.getKey());
		}
		return result;
	}

	/**
	 * 存入URL
	 */
	@Override
	public boolean putUrl(CrawUrl url) throws Exception {
		put(url.getOriUrl(), url);
		return true;
	}
	/**
	 * 根据URL计算键值，可以使用各种压缩算法，包括MD5等压缩算法
	 * @param url
	 * @return
	 */
	private String caculateUrl(String url)
	{
		return url;
	}
	//测试函数
	public static void main(String [] strs)
	{
		try {
			BDBFrontier bdbFrontier = new BDBFrontier("D:\\bdb");
			CrawUrl url = new CrawUrl();
			url.setOriUrl("http://www.163.com");
			bdbFrontier.putUrl(url);
System.out.println( (bdbFrontier.getNext()).getOriUrl() );
			bdbFrontier.close();		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
	}

}
