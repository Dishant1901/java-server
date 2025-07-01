package com.server.model;

import java.util.List;

public  class slideTextModel{
    public String text;
    public String fontSize;
    public String fontColor;
    public String x;
    public String y;
    public String width;
    public String height;

    public slideTextModel(String text, String fontSize, String fontColor, String x, String y, String width, String height) {
        this.text = text;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}

