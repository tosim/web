package top.tosim.advice.impl;

import top.tosim.advice.Sing;

public class SingImpl implements Sing{
    public void sing(String content){
        System.out.println("music... " + content);
    }
}
