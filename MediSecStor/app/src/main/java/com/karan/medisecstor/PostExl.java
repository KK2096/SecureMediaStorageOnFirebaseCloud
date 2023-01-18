package com.karan.medisecstor;

public class PostExl {
    String name;
    String url;

    public PostExl(){

    }

    @Override
    public String toString() {
        return "PostExl{" +
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
