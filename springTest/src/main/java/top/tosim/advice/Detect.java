package top.tosim.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class Detect {
    @Pointcut("execution(* top.tosim.Person.dial(String)) && args(number)")
    public void dial(String number){}

    @Before("dial(number)")
    public void beforeDial(String number){
        System.out.println("你竟然想打电话给:" + number + "!");
    }
}
