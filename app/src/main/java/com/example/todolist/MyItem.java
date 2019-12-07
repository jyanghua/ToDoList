/*
 * ICS 45J - Fall 2019
 * Lab 5 - Android Studio
 * Group name: JJJ
 * Members: Lillian Won, Linda Le, Jack Yang Huang
 */

package com.example.todolist;

public class MyItem {

    private String itemTitle;
    private String itemDescription;
    private String itemDate;
    private String itemKey;

    //Default constructor
    public MyItem() {
    }

    //Constructor that assigns the parameters to all the field variables
    public MyItem(String itemTitle, String itemDescription, String itemDate, String itemKey) {
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemDate = itemDate;
        this.itemKey = itemKey;
    }

    /**
     * Getter for the title of the item.
     * @return String title of the item
     */
    public String getItemTitle() {
        return itemTitle;
    }

    /**
     * Setter for the title of the item.
     * @param itemTitle a title for the item
     */
    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    /**
     * Getter for the description of the item.
     * @return String description of the item
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Setter for the description of the item.
     * @param itemDescription a description for the item
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * Getter for the date of the item.
     * @return String date of the item
     */
    public String getItemDate() {
        return itemDate;
    }

    /**
     * Setter for the date of the item.
     * @param itemDate a date for the item
     */
    public void setItemDate(String itemDate) {
        this.itemDate = itemDate;
    }

    /**
     * Getter for the key (ID) of the item.
     * @return String key of the item
     */
    public String getItemKey() { return itemKey; }

    /**
     * Setter for the key of the item.
     * @param itemKey a key for the item
     */
    public void setItemKey(String itemKey) { this.itemKey = itemKey; }
}
