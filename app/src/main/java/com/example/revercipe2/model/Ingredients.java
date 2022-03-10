package com.example.revercipe2.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Entity
public class Ingredients implements Comparable<Ingredients>{

    @PrimaryKey
    @NonNull
    String name = "";
    String imgPath;
    boolean flag = false;

    public Ingredients(){}
    public Ingredients(String name, String imgPath, boolean flag) {
        this.name = name;
        this.imgPath = imgPath;
        this.flag = flag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public boolean isFlag() {
        return flag;
    }

    @Override
    public int compareTo(Ingredients ingredient) {
        return this.getName().compareTo(ingredient.getName());
    }

    @NonNull
    public String toString(){
        return "\nname : " + this.getName()  + " flag : " + this.isFlag();
    }
}
