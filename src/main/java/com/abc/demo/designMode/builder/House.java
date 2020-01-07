package com.abc.demo.designMode.builder;

/**
 * Created on 2019/12/23 in springboot 项目 .
 */
public class House {

    public String getWoor() {
        return woor;
    }

    public void setWoor(String woor) {
        this.woor = woor;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    private String woor;
    private String floor;
    private String window;
    private String  door;

    public House(HouseBuilder builder){
        this.door = builder.door;
        this.floor = builder.floor;
        this.window = builder.window;
        this.window = builder.woor;
    }

    @Override
    public String toString() {
        return "House{" +
                "woor='" + woor + '\'' +
                ", floor='" + floor + '\'' +
                ", window='" + window + '\'' +
                ", door='" + door + '\'' +
                '}';
    }

    public static class  HouseBuilder{
        private String woor;
        private String floor;
        private String window;
        private String  door;

        public HouseBuilder setWoor(String woor) {
            this.woor = woor;
            return  this;
        }

        public HouseBuilder setFloor(String floor) {
            this.floor = floor;
            return  this;
        }

        public HouseBuilder setWindow(String window) {
            this.window = window;
            return  this;
        }

        public HouseBuilder setDoor(String door) {
            this.door = door;
            return  this;
        }

        public House builder(){
            return new House(this);
        }
    }
}
