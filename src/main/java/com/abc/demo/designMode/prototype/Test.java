package com.abc.demo.designMode.prototype;

import java.util.Date;

/**
 * Created on 2019/12/24 in springboot 项目 .
 */
public class Test {

    public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {

       Mail mail =  new Mail();
       mail.setAddress("河南");
       mail.setContent("origin");
       mail.setName("李高");
       mail.setSendTime(new Date(0L));
        System.out.println(mail.hashCode());
        System.out.println(mail);

//        Thread.sleep(5000);

        Mail mailClone = (Mail)mail.clone();
        mailClone.getSendTime().setTime(800000L);
        mailClone.setAddress("河北");
        mailClone.setContent("clone");
        mailClone.setName("刘高");
        System.out.println(mailClone.hashCode());
        System.out.println(mailClone);
    }
}
