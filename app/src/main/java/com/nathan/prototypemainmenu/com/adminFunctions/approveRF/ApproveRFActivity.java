package com.nathan.prototypemainmenu.com.adminFunctions.approveRF;

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

import com.nathan.prototypemainmenu.MainActivity;
import com.nathan.prototypemainmenu.R;
import com.nathan.prototypemainmenu.com.adminFunctions.addAccounts.AddAccountActivity;
import com.nathan.prototypemainmenu.com.backgroundService.ServiceConstants;
import com.nathan.prototypemainmenu.com.utilities.ListCreator;

/**
 * Created by nathan on 4/14/2017.
 */

public class ApproveRFActivity extends Fragment {

    RFModelAdapter rma;

    RecyclerView RfRecList;
    Context activityContext;

    ServiceReceiver serviceReceiver;

    //1st
    //We have to save the context for later use.
    //Initialize Linear Layout Manager (This is for the list.)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity){
            activityContext = context;
        }
    }



    // This event fires 2nd, before views are created for the fragment
    // The onCreate method is called when the Fragment instance is being created, or re-created.
    // Use onCreate for any standard setup that does not require the activity to be fully created
    //2nd
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The filter's action is BROADCAST_ACTION
        IntentFilter statusIntentFilter = new IntentFilter(
                ServiceConstants.BROADCAST_ACTION);
        statusIntentFilter.addCategory(Intent.CATEGORY_DEFAULT);

        serviceReceiver = new ApproveRFActivity.ServiceReceiver();

        //Registering the receiver
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(
                serviceReceiver, statusIntentFilter
        );
        MainActivity mainActivity = (MainActivity) getActivity();


    }



    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    //3rd
    //Prepare the layout of the whole fragment.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_view_approve_rf, parent, false);
    }


    // This event is triggered soon after onCreateView().
    // onViewCreated() is only called if the view returned from onCreateView() is non-null.
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    //4th
    //Once we got everything ready, we now create the different parts
    //and finish setting up all the things.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Retrieving the Recycler List
        RfRecList = (RecyclerView) view.findViewById(R.id.recycler_rf);
        RfRecList.setHasFixedSize(true);

        //Getting the activity where this Fragment is attached to.
        Activity parentActivity = ((MainActivity) activityContext).getParent();
        LinearLayoutManager llm = new LinearLayoutManager(parentActivity);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        RfRecList.setLayoutManager(llm);

        ListCreator lc = new ListCreator(activityContext);

        //Creating the data and connecting the data to the RecyclerList
         rma = new RFModelAdapter(
                lc.createRFModelList(10),activityContext);
        RfRecList.setAdapter(rma);
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
