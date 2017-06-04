package com.nathan.prototypemainmenu.com.adminFunctions.approveRF;

/**
 * Created by Nathan on 30/05/2017.
 */

public class RFViewModel {
    public String item_name;
    public String item_qty;

    public RFViewModel() {

    }

    public RFViewModel(String name, String qty) {
        this.item_name = name;
        this.item_qty = qty;
    }

    public static final String RF_VIEW_NAME_PREFIX = "ITEM_NAME_";
    public static final String RF_VIEW__QTY_PREFIX = "ITEM_QTY_";
}
