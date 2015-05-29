package org.leg.siteweb.common;

/**
 * 日志对象
 */
public class Logger {
	/**
	 * 系统日志对象
	 */
	private org.apache.log4j.Logger logger = null;
	/**
	 * 日志对象实例
	 */
	private static Logger instance = null;

	
	/**
	 * 获取实例对象
	 */
	public static Logger instance() {
		if(null == instance) {
			synchronized(Logger.class) {
				if(null == instance) {
					instance = new Logger();
					instance.logger = org.apache.log4j.Logger.getLogger(Logger.class);
				}
			}
		}
		return instance;
	}
	
	/**
	 * 记录信息
	 * 
	 * @param log 日志信息
	 */
	public void info(Object log) {
		logger.info(log);
	}
	
	/**
	 * 记录告警
	 * 
	 * @param log 日志信息
	 */
	public void warn(Object log) {
		logger.warn(log);
	}
	
	/**
	 * 记录错误
	 * 
	 * @param log 日志信息
	 */
	public void error(Object log) {
		logger.error(log);
	}
	
	/**
	 * 记录错误
	 * 
	 * @param log 日志信息
	 * @param t 抛出的信息
	 */
	public void error(Object log, Throwable t) {
		logger.error(log, t);
	}
}
