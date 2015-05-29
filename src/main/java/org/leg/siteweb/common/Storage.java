package org.leg.siteweb.common;

import org.leg.library.db.DBConnectionPoolConfig;
import org.leg.library.db.DBExecutor;
import org.leg.library.db.core.IDBExecutor;

public class Storage {
	/**
	 * 数据库执行器
	 */
	private static IDBExecutor executor = null;
	
	
	/**
	 * 隐藏构造函数
	 */
	private Storage() {}
	
	/**
	 * 获取SQL执行器
	 * 
	 * @return SQL执行器
	 */
	public static IDBExecutor executor() {
		if(null == executor) {
			synchronized(Storage.class) {
				if(null == executor) {
					executor = new DBExecutor();
					DBConnectionPoolConfig conf = new DBConnectionPoolConfig();
					if(!conf.load(Configuration.storage())) {
						Logger.instance().error("加载数据配置失败");
						return null;
					}
					if(!((DBExecutor)executor).initialize(conf)) {
						Logger.instance().error("建立数据库连接失败");
						return null;
					}
				}
			}
		}
		return executor;
	}
}
