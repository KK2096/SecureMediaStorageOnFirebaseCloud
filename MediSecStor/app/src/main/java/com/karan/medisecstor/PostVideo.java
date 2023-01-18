package com.karan.medisecstor;

public class PostVideo {
    String name;
    String url;

    public PostVideo(){

    }

    @Override
    public String toString() {
        return "PostVideo{" +
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
