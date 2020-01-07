package com.abc.demo.designMode.prototype;

import java.util.Date;

/**
 * Created on 2019/12/24 in springboot 项目 .
 */
public class Mail   implements Cloneable {

    private String name;
    private String address;
    private String content;
    private Date sendTime;

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", content='" + content + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("拷贝对象");
        return super.clone();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
