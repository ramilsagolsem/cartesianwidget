package com.tool.cartisianwidget.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

public class CartisianWidget implements Comparable<CartisianWidget>{

    private Integer id;
    private Integer xCoordinate;
    private Integer yCoordinate;
    private Integer zCoordinate;
    private Integer width;
    private Integer height;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime lastmodified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Integer xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Integer getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Integer yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Integer getzCoordinate() {
        return zCoordinate;
    }

    public void setzCoordinate(Integer zCoordinate) {
        this.zCoordinate = zCoordinate;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public LocalDateTime getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(LocalDateTime lastmodified) {
        this.lastmodified = lastmodified;
    }

    @Override
    public int compareTo(CartisianWidget o) {
        return this.zCoordinate - o.zCoordinate;
    }


    @Override
    public String toString() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(this);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CartisianWidget){
            CartisianWidget ob = (CartisianWidget)obj;
            if(this.getId() == ob.getId()
            && this.getzCoordinate() == ob.getzCoordinate()
            && this.getxCoordinate() == ob.getxCoordinate()
            && this.getyCoordinate() == ob.getyCoordinate()
            && this.getHeight() == ob.getHeight()
            && this.getWidth() == ob.getWidth()){
                return true;
            }
        }
        return  false;
    }
}
