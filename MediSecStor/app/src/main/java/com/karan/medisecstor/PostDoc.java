package com.karan.medisecstor;

public class PostDoc {
    String name;
    String url;

    public PostDoc(){

    }

    @Override
    public String toString() {
        return "PostDoc{" +
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
