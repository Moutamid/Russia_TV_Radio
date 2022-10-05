package com.moutamid.russiatvradio;

public class Model_Radio {
    String name , link , id;
    String image1 ;
    boolean isFavourite = false;

    public Model_Radio() {
    }

    public Model_Radio(String name, String link, String id, String image1, boolean isFavourite) {
        this.name = name;
        this.link = link;
        this.id = id;
        this.image1 = image1;
        this.isFavourite = isFavourite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
