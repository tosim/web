package top.tosim.eqmanager.service.impl;

import org.springframework.stereotype.Component;

@Component
public class Printing implements Runnable{
    @Override
    public void run() {
        while(true){
            System.out.println("I am printing");
            try{
                Thread.sleep(1000);
            }catch (Exception e){
            }
        }
    }
}
