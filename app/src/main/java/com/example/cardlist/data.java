package com.example.cardlist;

public class data {
    private String title;
    private String content;
    private String msgTime;
    private String imgURL;
    public data(){}
    public data(String title,String content,String msgTime,String imgURL){
        this.title=title;
        this.content=content;
        this.msgTime=msgTime;
        this.imgURL=imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
