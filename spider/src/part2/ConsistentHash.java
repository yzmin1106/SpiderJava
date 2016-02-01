package part2;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Consistent Hash代码实现，云存储的负载均衡
 * @author jonn
 *
 * @param <T>
 */
public class ConsistentHash<T> {
	/**
	 * hash算法
	 */
	private  HashFunction hashFunction;
	/**
	 * 虚拟节点数目
	 */
	private  int numberOfReplicas;
	
	private SortedMap<Integer, T> circle = new TreeMap<Integer, T>();
	/**
	 * 
	 * @param hashFunction
	 * @param numberOfReplicas
	 * @param nodes 物理节点
	 */
	public ConsistentHash(HashFunction hashFunction,int numberOfReplicas,Collection<T> nodes)
	{
		this.hashFunction = hashFunction;
		this.numberOfReplicas = numberOfReplicas;
		for (T node:nodes) {
			add(node);
		}
		
	}
	
	public void add(T node)
	{
		for (int i = 0; i < numberOfReplicas; i++) {
			circle.put(hashFunction.hash(node.toString() + i), node);
		}
			
	}
	
	public void remove(T node)
	{
		for (int i = 0; i < numberOfReplicas; i++) {
			circle.remove(hashFunction.hash(node.toString() + i));
		}
	}

	/**
	 * 关键算法
	 * @param key
	 * @return
	 */
	public T get(Object key)
	{
		if(circle.isEmpty())
		{
			return null;	
		}
		//计算hash值
		int hash = hashFunction.hash(key);
		//如果不包括这个hash值
		if(!circle.containsKey(hash))
		{
		 SortedMap<Integer, T> tailMap = circle.tailMap(hash);
		 hash = tailMap.isEmpty()?circle.firstKey():tailMap.firstKey();
		}
		return circle.get(hash);
		
	}
}
