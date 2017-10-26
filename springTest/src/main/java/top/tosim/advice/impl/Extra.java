package top.tosim.advice.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;
import top.tosim.advice.Sing;

@Component
@Aspect
public class Extra {//为Person添加了sing接口的功能，并且sing接口是由SingImpl所实现的
    @DeclareParents(value = "top.tosim.Person+",defaultImpl = SingImpl.class)//+号表示是Person的所有子类型
    public static Sing sing;
}
