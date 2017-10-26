package top.tosim;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.tosim.advice.Sing;


public class TestSimpleDial {
    @Test
    public void initTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beansConfig.xml");
        Person person = ctx.getBean("personImpl" , Person.class);
        person.dial("10086");
    }
}
