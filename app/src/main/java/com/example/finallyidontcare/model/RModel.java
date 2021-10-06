package com.example.finallyidontcare.model;

public class RModel {
    int pic;
    String text;

    public RModel(int pic, String text) {
        this.pic = pic;
        this.text = text;
    }

    public int getPic() {
        return pic;
    }

    public String getText() {
        return text;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public void setText(String text) {
        this.text = text;
    }
}
