package com.example.gamewiki.API.response;

import java.io.Serializable;

public class material implements Serializable {
    private String name;
    private int quantity;

    public material() {
    }

    public material(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "material{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
