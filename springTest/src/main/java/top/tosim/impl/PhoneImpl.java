package top.tosim.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.tosim.Phone;

@Component
public class PhoneImpl implements Phone {
    @Value("${phoneType}")
    private String phoneType;

    public void call(String to) {
        System.out.println("手机型号："+phoneType+",正在接通"+to);
    }
}