package com.server.model;

import java.util.List;

public  class slideDataModel {
    public String slideName;
    public List<slideTextModel> objects;

    public slideDataModel(String slideName, List<slideTextModel> objects) {
        this.slideName = slideName;
        this.objects = objects;
    }
}
 