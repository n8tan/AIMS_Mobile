package com.nathan.prototypemainmenu.com.adminFunctions.deleteAccounts;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nathan.prototypemainmenu.MainActivity;
import com.nathan.prototypemainmenu.R;
import com.nathan.prototypemainmenu.com.adminFunctions.approveRF.ApproveRFActivity;
import com.nathan.prototypemainmenu.com.backgroundService.ServiceConstants;
import com.nathan.prototypemainmenu.com.staffFunctions.viewItem.ViewItemModelAdapter;
import com.nathan.prototypemainmenu.com.utilities.ListCreator;

/**
 * Created by nathan on 4/14/2017.
 */

public class RecyclerDeleteAccountActivity extends Fragment {

    DeleteAccountModelAdapter dama;
    RecyclerView delAccountRecList;

    ServiceReceiver serviceReceiver;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // The filter's action is BROADCAST_ACTION
        IntentFilter statusIntentFilter = new IntentFilter(
                ServiceConstants.BROADCAST_ACTION);
        statusIntentFilter.addCategory(Intent.CATEGORY_DEFAULT);

        serviceReceiver = new RecyclerDeleteAccountActivity.ServiceReceiver();

        //Registering the receiver
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(
                serviceReceiver, statusIntentFilter
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_view_delete_account, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Retrieving the Recycler List
        delAccountRecList = (RecyclerView) view.findViewById(R.id.recycler_view_delete_account);
        delAccountRecList.setHasFixedSize(true);

        //Getting the activity where this Fragment is attached to.
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        delAccountRecList.setLayoutManager(llm);

        //Creating the data and connecting the data to the RecyclerList
        ListCreator lc = new ListCreator(getContext());

        dama = new DeleteAccountModelAdapter(
                lc.createDeleteModelList(10),getContext());
        delAccountRecList.setAdapter(dama);
    }


    @Override
    public void onDestroy() {

        // If the DownloadStateReceiver still exists, unregister it and set it to null
        if (serviceReceiver != null) {
            LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(serviceReceiver);
            serviceReceiver = null;
        }

        // Must always call the super method at the end.
        super.onDestroy();
    }

    private class ServiceReceiver extends BroadcastReceiver {

        private ServiceReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {

            //The second parameter is the default value that will be returned if no match was found.
            switch(intent.getIntExtra(ServiceConstants.DATA_STATUS, ServiceConstants.STATE_ACTION_COMPLETE)) {
                case ServiceConstants.STATE_ACTION_PROGRESS:

                    //Show loading dialog.
                    break;

                case ServiceConstants.STATE_ACTION_COMPLETE:
                    //Perform data retrieval from the intent.
                    break;

                default:
                    break;
            }
        }

    }

}
