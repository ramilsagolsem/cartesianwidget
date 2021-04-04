package com.tool.cartisianwidget.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.*;

@Entity
@Table(name = "CARTISIANWIDGET")
public class CartisianWidget implements Comparable<CartisianWidget>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="xcoordinate")
    private Integer xcoordinate;
    @Column(name="ycoordinate")
    private Integer ycoordinate;
    @Column(name="zcoordinate")
    private Integer zcoordinate;
    @Column(name="width")
    private Integer width;
    @Column(name="height")
    private Integer height;
    @Column(name="lastmodified")
    private String lastmodified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getXcoordinate() {
        return xcoordinate;
    }

    public void setXcoordinate(Integer xcoordinate) {
        this.xcoordinate = xcoordinate;
    }

    public Integer getYcoordinate() {
        return ycoordinate;
    }

    public void setYcoordinate(Integer ycoordinate) {
        this.ycoordinate = ycoordinate;
    }

    public Integer getZcoordinate() {
        return zcoordinate;
    }

    public void setZcoordinate(Integer zcoordinate) {
        this.zcoordinate = zcoordinate;
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

    public String getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(String lastmodified) {
        this.lastmodified = lastmodified;
    }

    @Override
    public int compareTo(CartisianWidget o) {
        return this.zcoordinate - o.zcoordinate;
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
            if(this.getId().equals(ob.getId())
            && this.getZcoordinate().equals(ob.getZcoordinate())
            && this.getXcoordinate().equals(ob.getXcoordinate())
            && this.getYcoordinate().equals(ob.getYcoordinate())
            && this.getHeight().equals(ob.getHeight())
            && this.getWidth().equals(ob.getWidth())){
                return true;
            }
        }
        return  false;
    }
}
