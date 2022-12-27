package com.example.gamewiki.API.response;

import java.io.Serializable;
import java.util.ArrayList;

public class item implements Serializable {
    private String _id;
    private String name;
    private String type;
    private String droppedBy;
    private String image;
    private ArrayList<material> craftingMaterials;
    private int __v;
    private String formula;
    private ArrayList<item> materialArray;
    private String cl;


    public item() {
    }
    public item(String _id, String name, String type, String droppedBy, String image, ArrayList<material> craftingMaterials, int __v, String formula, ArrayList<item> materialArray, String cl) {
        this._id = _id;
        this.name = name;
        this.type = type;
        this.droppedBy = droppedBy;
        this.image = image;
        this.craftingMaterials = craftingMaterials;
        this.__v = __v;
        this.formula = formula;
        this.materialArray = materialArray;
        this.cl = cl;
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

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public ArrayList<item> getMaterialArray() {
        return materialArray;
    }

    public void setMaterialArray(ArrayList<item> materialArray) {
        this.materialArray = materialArray;
    }

    @Override
    public String toString() {
        String str = "" +
                "Tên vật phẩm: " + name + "\n" +
                "Loại vật phẩm: " + type + "\n" +
                "Cách tìm: ";
        str += droppedBy.isEmpty() ? "\t"+droppedBy : "\tVật phẩm không thể tìm kiếm theo cách thông thường" ;
        str += "\nCông thức chế tạo chi tiết: \n" + formula;
        return  str;
    }

    public String materialStrBuilder() {
        String tempStr = "";
        if (craftingMaterials != null)
            for (int i = 0; i < craftingMaterials.size(); i++) {
                tempStr += "\t"+craftingMaterials.get(i).getName() + "\t\tx" + craftingMaterials.get(i).getQuantity() + "\n";
            }
        return tempStr;
    }
}
