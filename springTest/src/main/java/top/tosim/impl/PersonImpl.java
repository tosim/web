package top.tosim.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.tosim.Person;
import top.tosim.Phone;

@Component
public class PersonImpl implements Person {
    @Autowired
    private Phone phone;
    @Value("${prop.name}")
    private String name;
    @Value("${type}")
    private String type;


    public void dial(String to) {
        System.out.println(name+"开始拨号。。。");
        phone.call(to);
    }
}