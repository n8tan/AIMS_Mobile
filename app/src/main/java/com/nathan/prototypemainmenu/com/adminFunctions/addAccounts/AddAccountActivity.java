package com.nathan.prototypemainmenu.com.adminFunctions.addAccounts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nathan.prototypemainmenu.MainActivity;
import com.nathan.prototypemainmenu.R;
import com.nathan.prototypemainmenu.com.backgroundService.ServiceConstants;


/**
 * Created by nathan on 3/20/2017.
 */

public class AddAccountActivity extends Fragment {
    EditText emailAddress;
    EditText inputFirstName;
    EditText inputLastName;
    Button sendButton;

    ServiceReceiver serviceReceiver;

    //1st
    //We have to save the context for later use.
    //Initialize Linear Layout Manager (This is for the list.)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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

        serviceReceiver = new ServiceReceiver();

        //Registering the receiver
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(
                serviceReceiver, statusIntentFilter
        );


    }
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    //3rd
    //Prepare the layout of the whole fragment.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_account, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // onViewCreated() is only called if the view returned from onCreateView() is non-null.
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    //4th
    //Once we got everything ready, we now create the different parts
    //and finish setting up all the things.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //Getting reference from the different views.
        emailAddress = (EditText) view.findViewById(R.id.txtBoxAddAccount);
        inputFirstName = (EditText) view.findViewById(R.id.inputFirstName);
        inputLastName = (EditText) view.findViewById(R.id.inputLastName);
        sendButton = (Button) view.findViewById(R.id.btnAddAccSend);


        sendButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!emailAddress.getText().toString().isEmpty()) {

                            AlertDialog.Builder alert = new AlertDialog.Builder(getContext())
                                    .setTitle(
                                            String.valueOf(R.string.add_account_notification_title)
                                    )
                                    .setMessage(
                                            String.valueOf(R.string.add_account_notification_msg) +
                                            emailAddress.getText().toString())
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast toast = Toast.makeText(getContext(),
                                                    String.valueOf(R.string.add_account_success_msg),
                                                    Toast.LENGTH_SHORT);
                                            toast.show();
                                        }
                                    })
                                    .setCancelable(true);
                            AlertDialog alertDialog = alert.create();
                            alertDialog.show();


                        }else {
                            Toast toast = Toast.makeText(getContext(),
                                    String.valueOf(R.string.add_account_not_success_msg),
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    }
                }
        );





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
