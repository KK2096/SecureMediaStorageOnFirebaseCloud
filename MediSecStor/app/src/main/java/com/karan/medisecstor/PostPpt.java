package com.karan.medisecstor;

public class PostPpt {
    String name;
    String url;

    public PostPpt(){

    }

    @Override
    public String toString() {
        return "PostPpt{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
