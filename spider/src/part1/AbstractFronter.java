package part1;

import java.io.File;
import java.io.FileNotFoundException;

import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
/**
 * 使用一个抽象类来封装对BerkeleyDB的操作
 * @author jonn
 *
 */
public abstract class AbstractFronter {

	private Environment env;
	private static final String CLASS_CATALOG = "java_class_catalog";
	protected StoredClassCatalog javaCatalog;
	protected Database catalogDatabase;
	protected Database database;
	
	public AbstractFronter(String homeDirectory) throws DatabaseException,FileNotFoundException
	{
		//打开environment
		System.out.println("Opening environment in: " + homeDirectory);
		EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setTransactional(true);
		envConfig.setAllowCreate(true);
		env = new Environment(new File(homeDirectory),envConfig);
		//设置DatabaseConfig
		DatabaseConfig dbConfig = new DatabaseConfig();
		dbConfig.setTransactional(true);
		dbConfig.setAllowCreate(true);
		//打开数据库
		catalogDatabase = env.openDatabase(null, CLASS_CATALOG, dbConfig);
		javaCatalog = new StoredClassCatalog(catalogDatabase);
		//设置DatabaseConfig
		DatabaseConfig dbConfig0 = new DatabaseConfig();
		dbConfig0.setTransactional(true);
		dbConfig0.setAllowCreate(true);
		//打开数据库
		database = env.openDatabase(null, "URL",dbConfig);
		
		
	}
	//关闭数据库，关闭环境
	public void close() throws DatabaseException{
		database.close();
		javaCatalog.close();
		env.close();
	}
	
	//put方法
	protected abstract void put(Object key,Object value);
	//get方法
	protected abstract Object get(Object key);
	//delete 方法
	protected abstract Object delete(Object key);
}
