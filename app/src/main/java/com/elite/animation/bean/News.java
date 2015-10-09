package com.elite.animation.bean;

/**
 * Elite Group
 * Created by wjc133 on 2015/9/16.
 */
public class News implements CoreBean {
    private String name;
    private String picSmall;
    private String description;
    private String learner;

    public String getLearner() {
        return learner;
    }

    public void setLearner(String learner) {
        this.learner = learner;
    }

    public String getPicSmall() {
        return picSmall;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
