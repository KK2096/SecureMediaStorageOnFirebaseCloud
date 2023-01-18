package com.karan.medisecstor;


public class PostImage {
    String name;
    String url;

    public PostImage(){

    }

    @Override
    public String toString() {
        return "PostImage{" +
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
