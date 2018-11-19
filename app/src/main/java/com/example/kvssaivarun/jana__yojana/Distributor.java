package com.example.kvssaivarun.jana__yojana;

import java.io.Serializable;

public class Distributor implements Serializable {
    String id;
    String name;
    String product;
    String quantity;
    String price;
    String phnumber;

    public Distributor() {

    }

    public Distributor(String id, String D_name, String D_product, String D_quantity, String D_price, String D_phnumber) {
        this.id = id;
        this.name = D_name;
        this.product = D_product;
        this.quantity = D_quantity;
        this.price = D_price;
        this.phnumber = D_phnumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhnumber() {
        return phnumber;
    }

    public void setPhnumber(String phnumber) {
        this.phnumber = phnumber;
    }
}
