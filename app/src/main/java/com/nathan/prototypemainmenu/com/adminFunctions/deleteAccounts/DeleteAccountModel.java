package com.nathan.prototypemainmenu.com.adminFunctions.deleteAccounts;

/**
 * Created by nathan on 4/14/2017.
 */

public class DeleteAccountModel {
    public String dAccountName;
    public int dAccountId;

    public DeleteAccountModel() {

    }
    public DeleteAccountModel(String name, int id) {
        this.dAccountName = name;
        this.dAccountId = id;
    }

    public static final String D_ACCOUNT_NAME = "Account_name_";

}
