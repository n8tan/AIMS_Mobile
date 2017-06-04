package com.nathan.prototypemainmenu.com.utilities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ArrayAdapter;

import com.nathan.prototypemainmenu.R;
import com.nathan.prototypemainmenu.com.adminFunctions.approveRF.RFModel;
import com.nathan.prototypemainmenu.com.adminFunctions.approveRF.RFViewModel;
import com.nathan.prototypemainmenu.com.adminFunctions.deleteAccounts.DeleteAccountModel;
import com.nathan.prototypemainmenu.com.adminFunctions.resetPassword.ResetPasswordModel;
import com.nathan.prototypemainmenu.com.staffFunctions.viewItem.ViewItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.nathan.prototypemainmenu.com.staffFunctions.viewItem.ViewItemModel;
import com.nathan.prototypemainmenu.com.staffFunctions.viewItem.ViewItemModel_v2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nathan on 4/14/2017.
 */

public class ListCreator {

    private Context currentContext;

    public ListCreator(Context context) {
        currentContext = context;
    }

    public List<ViewItemModel_v2> createAllItems(String jsonArray) {
        System.out.print("CREATING JSONARRAY TO"+ jsonArray);
        List<ViewItemModel_v2> result = new ArrayList<>();

        JSONArray mJsonArray = null;
        JSONObject mJsonObject = null;
        try{
            mJsonArray = new JSONArray(jsonArray);
            for (int i = 0; i < mJsonArray.length(); i++) {
                mJsonObject = mJsonArray.getJSONObject(i);
                result.add(
                        new ViewItemModel_v2(
                                mJsonObject.getString("name"),
                                mJsonObject.getString("brand"),
                                mJsonObject.getString("categoryName"),
                                mJsonObject.getString("location")
                        )
                );
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public List<ViewItemModel_v2> createViewItemModelList(int size) {

        List<ViewItemModel_v2> result = new ArrayList<>();
        for (int i=1; i <= size; i++) {

            ViewItemModel_v2 vim = new ViewItemModel_v2();

            vim.name = ViewItemModel.NAME_PREFIX + i;

            vim.category = ViewItemModel.CATEGORY_PREFIX + i;

            vim.location = ViewItemModel.LOCATION_PREFIX + i;

            vim.status = ViewItemModel.STATUS_PREFIX + i;

            vim.statusColor = new ColorDrawable(
                    Color.parseColor(getRandomColor())
            );
            result.add(vim);
        }
        return result;

    }

    public List<RFModel> createRFModelList(int size) {

        List<RFModel> result = new ArrayList<>();
        for (int i=1; i <= size; i++) {

            RFModel rm = new RFModel();

            rm.rf_user = RFModel.RF_USER_PREFIX + i;
            rm.rf_date = RFModel.RF_DATE_PREFIX + i;
            rm.rf_id = i;
            result.add(rm);
        }
        return result;
    }

    public List<DeleteAccountModel> createDeleteModelList(int size) {

        List<DeleteAccountModel> result = new ArrayList<>();
        for (int i=1; i <= size; i++) {

            DeleteAccountModel dam = new DeleteAccountModel();

            dam.dAccountName = DeleteAccountModel.D_ACCOUNT_NAME + i;
            dam.dAccountId = i;
            result.add(dam);
        }
        return result;
    }

    public List<ResetPasswordModel> createResetPasswordModelList(int size) {

        List<ResetPasswordModel> result = new ArrayList<>();
        for (int i=1; i <= size; i++) {

            ResetPasswordModel rpm = new ResetPasswordModel();

            rpm.rpAccountName = ResetPasswordModel.RP_ACCOUNT_NAME + i;

            result.add(rpm);
        }
        return result;
    }

    public List<RFViewModel> createRFViewModelList(int size) {
        List<RFViewModel> result = new ArrayList<>();
        for (int i=1; i <= size; i++) {
            RFViewModel rvm = new RFViewModel();

            rvm.item_name = RFViewModel.RF_VIEW_NAME_PREFIX + i;
            rvm.item_qty = RFViewModel.RF_VIEW__QTY_PREFIX + i;

            result.add(rvm);
        }
        return result;
    }

    public ArrayList<String> createCategoryList(int size) {
        ArrayList<String> result = new ArrayList<String>();
        for(int i=1; i<=size; i++) {
            result.add(ViewItemModel_v2.CATEGORY_PREFIX + i);
        }
        return result;
    }
    private String getRandomColor() {
        String[] colors = currentContext.getResources().getStringArray(R.array.status_colors);
        int rnd = new Random().nextInt(colors.length);
        return colors[rnd];
    }
}
