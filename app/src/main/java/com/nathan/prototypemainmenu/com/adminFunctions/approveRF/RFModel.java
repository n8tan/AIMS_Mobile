package com.nathan.prototypemainmenu.com.adminFunctions.approveRF;

/**
 * Created by nathan on 4/14/2017.
 */

public class RFModel {
    //For use in the web service...
    public int rf_id;
    public String rf_date;
    public String rf_user;

    public RFModel() {

    }
    public RFModel(String user, String date, int id) {
        this.rf_user = user;
        this.rf_date = date;
        this.rf_id = id;
    }

    //The variables below will be used in creating sample data.
    public static final String RF_USER_PREFIX = "RF User_";
    public static final String RF_DATE_PREFIX = "RF Date_";
}
