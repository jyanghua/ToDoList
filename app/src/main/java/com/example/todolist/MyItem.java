package com.example.todolist;

public class MyItem {

    private String itemTitle;
    private String itemDescription;
    private String itemDate;
    private String itemKey;

    public MyItem() {
    }

    public MyItem(String itemTitle, String itemDescription, String itemDate, String itemKey) {
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemDate = itemDate;
        this.itemKey = itemKey;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemDate() {
        return itemDate;
    }

    public void setItemDate(String itemDate) {
        this.itemDate = itemDate;
    }

    public String getItemKey() { return itemKey; }

    public void setItemKey(String itemKey) { this.itemKey = itemKey; }
}
