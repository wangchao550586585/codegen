package com.common.codege.datasource;

/**
 * 使用ThreadLocal记录当前线程中的数据源key
 * 
 * @author Administrator
 *
 */
public class DynamicDataSourceHolder {
	// 写库对应数据源key
	public static final String MASTER = "master";
	// 读库对应数据源key
	public static final String SLAVE = "slave";
	// 记录当前数据源key
	public static final ThreadLocal<String> holder = new ThreadLocal<String>();

	/**
	 * 设置数据源key
	 * 
	 * @param key
	 */
	public static void putDataSourceKey(String key) {
		holder.set(key);
	}

	/**
	 * 获取数据源key
	 * 
	 * @return
	 */
	public static String getDataSourceKey() {
		return holder.get();
	}

	/**
	 * 标记写库
	 */
	public static void markMaster() {
		putDataSourceKey(MASTER);
	}

	/**
	 * 标记读库
	 */
	public static void markSlave() {
		putDataSourceKey(SLAVE);
	}

	public static boolean isMaster() {
		return MASTER.equals(getDataSourceKey());
	}

}
