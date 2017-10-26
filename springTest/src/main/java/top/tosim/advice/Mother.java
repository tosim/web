package top.tosim.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Mother {
    @Pointcut("execution(* top.tosim.Person.dial(..))")
    public void dial(){}
    @Before("dial()")
    public void beforeCall(){
        System.out.println("[Before] 在你打电话之前：你妈妈跟你说，你要好好学习，不要老是玩手机。");
    }
    @Around("dial()")
    public void aroundCall(ProceedingJoinPoint pjb){
        try{
            System.out.println("[Aruond_before] 在你打电话前,你妈妈一直就非常生气，还不知道要打你多少顿，所以以后你不要打电话了");
            pjb.proceed();
            System.out.println("[Aruond_after] 在你打电话后,你妈妈一直就非常生气，被打了几顿都不知道，所以以后你不要打电话了");
        }catch (Throwable e){

        }
    }
    @After("dial()")
    public void afterCall_after(){
        System.out.println("[After] 当你很爽的打完电话了，你妈很生气得打了你一顿");
    }
    @AfterReturning("dial()")
    public void afterCall_afterReturning(){
        System.out.println("[AfterReturning] 当你很爽的打完电话了，你妈很生气得打了你一顿");
    }
    @AfterThrowing("dial()")
    public void afterCall_afterThrowing(){
        System.out.println("[AfterThrowing] 你打电话的时候你女朋友挂了电话，打电话不成功，你妈很生气得打了你一顿");
    }
}
