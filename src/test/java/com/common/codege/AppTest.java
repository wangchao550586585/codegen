package com.common.codege;

import com.common.codege.datasource.DataSourceBean;
import com.common.codege.datasource.DynamicDataSource;
import com.common.codege.entity.GenDatasourceConf;
import com.common.codege.service.GenDatasourceConfService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@AllArgsConstructor
public class AppTest {
    private final GenDatasourceConfService genDatasourceConfService;

    @Test
    public void testDynamicDataSource() {
        List<GenDatasourceConf> list = genDatasourceConfService.list();
        list.forEach(k->{
            DataSourceBean dataSourceBean = new DataSourceBean(k.getName(),k.getUrl(),k.getUsername(), k.getPassword());
            DynamicDataSource.DataSourceContext.setDataSource(dataSourceBean);
            //  ---------------

            DynamicDataSource.DataSourceContext.toDefault();
        });
    }
}
