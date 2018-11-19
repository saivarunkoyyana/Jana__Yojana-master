package com.example.kvssaivarun.jana__yojana;

import java.io.Serializable;

class Worker implements Serializable {
    String id;
    String name;
    String product;
    String quantity;
    String price;
    String phnumber;

    public Worker() {

    }

    public Worker(String id, String w_name, String w_product, String w_quantity, String w_price, String w_phnumber) {
        this.id = id;
        this.name = w_name;
        this.product = w_product;
        this.quantity = w_quantity;
        this.price = w_price;
        this.phnumber = w_phnumber;
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