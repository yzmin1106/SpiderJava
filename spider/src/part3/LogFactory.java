package part3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 日志工厂
 * @author jonn
 *
 */
public class LogFactory {
	private static  Logger logger;
	static {
		logger = Logger.getLogger("stdout");
		logger.setLevel(Level.WARNING);
		
		
	}
	
	public static void log(String info,Level level,Throwable ex){
		logger.log(level,info,ex);
	}

	public static Level getLogLevel()
	{
		return logger.getLevel();
	}
}
