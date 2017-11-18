package top.tosim.eqmanager.config;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"top.tosim.eqmanager"},excludeFilters = {@Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)})//包扫描，排除mvc组件
@Import(DataSourceConfig.class)//加载数据源配置
public class RootConfig {
    //配置aop自动代理
    /*
    @Bean
    public BeanNameAutoProxyCreator proxycreate(){
        BeanNameAutoProxyCreator proxycreate = new BeanNameAutoProxyCreator();
        proxycreate.setProxyTargetClass(true);
        proxycreate.setBeanNames("*ServiceImpl");
        proxycreate.setInterceptorNames("transactionInterceptor");
        return proxycreate;
    }*/
}
