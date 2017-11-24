package top.tosim.actrainer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("top.tosim.actrainer.controller")//扫描mvc组件（controller）
public class WebConfig extends WebMvcConfigurerAdapter{
    //配置JSP视图解析器
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    //配置静态资源处理
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    /*
    //文件上传，bean必须写name属性且必须为multipartResolver，不然取不到文件对象
    @Bean(name="multipartResolver")
    protected CommonsMultipartResolver MultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        //multipartResolver.setUploadTempDir(new FileSystemResource("/tmp"));//可不设置
        multipartResolver.setMaxUploadSize(2097152);//2M
        multipartResolver.setMaxInMemorySize(0);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }*/
}
