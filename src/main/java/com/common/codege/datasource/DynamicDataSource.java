package com.common.codege.datasource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.HashMap;

@Component
public class DynamicDataSource extends AbstractRoutingDataSource  {
    BeanFactory beanFactory;
	@Override
	protected Object determineCurrentLookupKey() {
		if (DynamicDataSourceHolder.isMaster()) {
			Object key = DynamicDataSourceHolder.getDataSourceKey();
			return key;
		}
		return "master";
	}


}
