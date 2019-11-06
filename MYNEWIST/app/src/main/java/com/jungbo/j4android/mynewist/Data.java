package com.jungbo.j4android.mynewist;

public class Data {
    String userID;
    String styleName;
    String brandName;
    String totalPrice;
    String comment;
    int imageview;


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public Data(String userID,String styleName,String brandName,String totalPrice,String comment)
    {

        this.userID=userID;
        this.styleName=styleName;
        this.brandName=brandName;
        this.totalPrice=totalPrice;
        this.comment=comment;
    }



}
