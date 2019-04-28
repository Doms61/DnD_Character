package com.example.dnd;

import java.io.Serializable;

public class Item implements Serializable {

    private String item_name;
    private String item_qty;
    private String item_desc;

    public Item(String item_name, String item_qty, String item_desc) {
        this.item_name = item_name;
        this.item_qty = item_qty;
        this.item_desc = item_desc;
    }

    public Item() {}

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_qty() {
        return item_qty;
    }

    public void setItem_qty(String item_qty) {
        this.item_qty = item_qty;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }
}
