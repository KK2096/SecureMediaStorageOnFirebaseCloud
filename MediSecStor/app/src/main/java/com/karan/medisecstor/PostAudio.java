package com.karan.medisecstor;

public class PostAudio {
    String name;
    String url;

    public PostAudio(){

    }

    @Override
    public String toString() {
        return "PostAudio{" +
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
