package com.example.gamewiki.API.request;

public class dataRequest {
    private String secretKey;
    private String itemName;
    private String id;
    public dataRequest(){
        secretKey = "ThisIsKey";
        itemName = "bronze";
        id = "637c9a53f8c7e15f1991c2b0";
    };

    public dataRequest(String secretKey, String itemName, String id) {
        this.secretKey = secretKey;
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

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
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
