package com.common.codege.datasource;

/**
 * @author wangchao
 * @description: TODO
 * @date 2020/11/2113:47
 */

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * 连接数据源前,调用该方法
     */
    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceBean dataSourceBean = DataSourceContext.getDataSource();
        if (dataSourceBean == null) {
            return null;
        }
        try {
            Map<Object, Object> targetSourceMap = getTargetSource();
            synchronized (this) {
                if (!targetSourceMap.keySet().contains(dataSourceBean.getBeanName())) {
                    Object dataSource = createDataSource(dataSourceBean);
                    targetSourceMap.put(dataSourceBean.getBeanName(), dataSource);
                    super.afterPropertiesSet();
                }
            }
            return dataSourceBean.getBeanName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object createDataSource(DataSourceBean dataSourceBean) throws IllegalAccessException {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(HikariDataSource.class);

        Map<String, Object> propertyKeyValues = getPropertyKeyValues(DataSourceBean.class, dataSourceBean);
        for (Map.Entry<String, Object> entry : propertyKeyValues.entrySet()) {
            beanDefinitionBuilder.addPropertyValue(entry.getKey(), entry.getValue());
        }
        beanFactory.registerBeanDefinition(dataSourceBean.getBeanName(), beanDefinitionBuilder.getBeanDefinition());
        return context.getBean(dataSourceBean.getBeanName());
    }

    private <T> Map<String, Object> getPropertyKeyValues(Class<T> clazz, Object object) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Object> map = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }
        map.remove("beanName");
        return map;
    }

    public Map<Object, Object> getTargetSource() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field field = AbstractRoutingDataSource.class.getDeclaredField("targetDataSources");
        field.setAccessible(true);
        return (Map<Object, Object>) field.get(this);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    ///
   public static class DataSourceContext {

        private static ThreadLocal<DataSourceBean> threadLocal = new InheritableThreadLocal<DataSourceBean>();

        /**
         * 获取数据源
         */
        public static DataSourceBean getDataSource() {
            return threadLocal.get();
        }

        /**
         * 设置数据源
         */
        public static void setDataSource(DataSourceBean dataSourceBean) {
            threadLocal.set(dataSourceBean);
        }

        /**
         * 清除数据源
         * 清除后,数据源为默认时间
         */
        public static void toDefault() {
            threadLocal.remove();
        }
    }

}

