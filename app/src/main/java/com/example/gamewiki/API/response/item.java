package com.example.gamewiki.API.response;

import java.util.ArrayList;

public class item {
    private String _id;
    private String name;
    private String type;
    private String droppedBy;
    private String image;
    private ArrayList<material> craftingMaterials;
    private int __v;

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
    }

    private String cl;


    public item() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDroppedBy() {
        return droppedBy;
    }

    public void setDroppedBy(String droppedBy) {
        this.droppedBy = droppedBy;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<material> getCraftingMaterials() {
        return craftingMaterials;
    }

    public void setCraftingMaterials(ArrayList<material> craftingMaterials) {
        this.craftingMaterials = craftingMaterials;
    }

    public item(String _id, String name, String type, String droppedBy, String image, ArrayList<material> craftingMaterials, int __v, String cl) {
        this._id = _id;
        this.name = name;
        this.type = type;
        this.droppedBy = droppedBy;
        this.image = image;
        this.craftingMaterials = craftingMaterials;
        this.__v = __v;
        this.cl = cl;
    }
    @Override
    public String toString() {
        return "item{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", droppedBy='" + droppedBy + '\'' +
                ", image='" + image + '\'' +
                ", craftingMaterials=" + craftingMaterials +
                '}';
    }
}
