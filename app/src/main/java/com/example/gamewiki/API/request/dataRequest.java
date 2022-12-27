package com.example.gamewiki.API.request;

public class dataRequest {
    private final String secretKey = "ThisIsKey";
    private String itemName;
    private String id;
    //Test
    public dataRequest(){
        itemName = "bronze";
        id = "637c9a53f8c7e15f1991c2b0";
    };
    public dataRequest(String itemName, String id) {
        this.itemName = itemName;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "dataRequest{" +
                "secretKey='" + secretKey + '\'' +
                ", itemName='" + itemName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
