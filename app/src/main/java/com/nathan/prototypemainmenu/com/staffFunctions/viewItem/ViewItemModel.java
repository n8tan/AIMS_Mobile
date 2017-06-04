package com.nathan.prototypemainmenu.com.staffFunctions.viewItem;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

/**
 * Created by nathan on 2/13/2017.
 */

public class ViewItemModel {
    public String name;
    public String category;
    public String location;
    public String status;
    public ColorDrawable statusColor;
    public ViewItemModel(){}
    public ViewItemModel(String name, String category, String location, String status) {
        this.name = name;
        this.category = category;
        this.location = location;
        this.status = status;
        this.statusColor = new ColorDrawable(
                Color.parseColor("#dd4b39")
        );
    }

    //The variables below will be used in creating sample data.
    public static final String NAME_PREFIX = "Name_";
    public static final String CATEGORY_PREFIX = "Category_";
    public static final String LOCATION_PREFIX = "Location_";
    public static final String STATUS_PREFIX = "Status_";
}
