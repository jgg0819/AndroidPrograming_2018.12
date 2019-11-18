package com.jungbo.j4android.mynewist;

public class Person {
    String title;
    String subtitle;
    String price;
    String comment;

    public Person(String title, String subtitle, String price, String comment) {
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
