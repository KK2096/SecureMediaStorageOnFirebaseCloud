package com.karan.medisecstor;

public class PostPdf {
    String name;
    String url;

    public PostPdf(){

    }

    @Override
    public String toString() {
        return "PostPdf{" +
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
