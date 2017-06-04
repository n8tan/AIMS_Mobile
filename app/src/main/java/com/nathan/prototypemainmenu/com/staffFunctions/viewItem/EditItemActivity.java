package com.nathan.prototypemainmenu.com.staffFunctions.viewItem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.nathan.prototypemainmenu.R;
import com.nathan.prototypemainmenu.com.adminFunctions.deleteAccounts.RecyclerDeleteAccountActivity;
import com.nathan.prototypemainmenu.com.backgroundService.ServiceConstants;
import com.nathan.prototypemainmenu.com.utilities.ListCreator;

import java.util.ArrayList;

/**
 * Created by nathan on 3/19/2017.
 */

public class EditItemActivity extends AppCompatActivity {

    EditText itemName;
    EditText itemStatus;
    Spinner itemCategory;
    EditText itemLocation;

    ArrayAdapter<String> spinnerAdapter;

    ServiceReceiver serviceReceiver;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(
                        Color.parseColor("#014FB3")
                )
        );
        getMenuInflater().inflate(R.menu.view_item_menu_bar, menu);
        //Inflates the menu bar(The topmost bar on the home screen)
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_item_screen_v2);

        getSupportActionBar().setTitle("Edit Item");

        Intent intent = getIntent();

        itemName = (EditText) findViewById(R.id.itemName);
        itemStatus = (EditText) findViewById(R.id.itemStatus);
        itemLocation = (EditText) findViewById(R.id.itemLocation);
        itemCategory = (Spinner) findViewById(R.id.itemCategory);


        String itemNameData = intent.getStringExtra("itemName");
        String itemStatusData = intent.getStringExtra("itemStatus");
        String itemLocationData = intent.getStringExtra("itemLocation");
        String currentitemCategory = intent.getStringExtra("itemCategory");

        ListCreator lc = new ListCreator(this);
        ArrayList<String> categoryList = lc.createCategoryList(3);
        spinnerAdapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item,
                    categoryList);


        itemName.setText(itemNameData);
        itemStatus.setText(itemStatusData);
        itemLocation.setText(itemLocationData);

        itemCategory.setPrompt("Select A Category");
        itemCategory.setAdapter(spinnerAdapter);

        //Getting the kind of item category
        int currentCategory = spinnerAdapter.getPosition(currentitemCategory);
        itemCategory.setSelection(currentCategory);

        // The filter's action is BROADCAST_ACTION
        IntentFilter statusIntentFilter = new IntentFilter(
                ServiceConstants.BROADCAST_ACTION);
        statusIntentFilter.addCategory(Intent.CATEGORY_DEFAULT);

        serviceReceiver = new EditItemActivity.ServiceReceiver();

        //Registering the receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(
                serviceReceiver, statusIntentFilter
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickBtnSave(View view) {
        //Getting the reference to the Text Fields
        itemName = (EditText) findViewById(R.id.itemName);
        itemStatus = (EditText) findViewById(R.id.itemStatus);
        itemLocation = (EditText) findViewById(R.id.itemLocation);
        itemCategory = (Spinner) findViewById(R.id.itemCategory);

        //Extracting the String data to pass onto the web service.
        //If the Text fields are not empty, extract and proceed to web service.
        if(!itemName.getText().toString().isEmpty() &&
                !itemStatus.getText().toString().isEmpty() &&
                !itemLocation.getText().toString().isEmpty() &&
                !itemCategory.getSelectedItem().toString().isEmpty()) {

            String inputName = itemName.getText().toString();
            String inputStatus = itemStatus.getText().toString();
            String inputLocation = itemLocation.getText().toString();
            String inputCategory = itemCategory.getSelectedItem().toString();

            Toast toast = Toast.makeText(EditItemActivity.this, "Save successful!" , Toast.LENGTH_SHORT);
            toast.show();

        }else {
            //Tell there are text fields that need to be filled out.

            Toast toast = Toast.makeText(EditItemActivity.this, "Some text fields are blank.", Toast.LENGTH_SHORT);
            toast.show();



        }

    }

    public void onClickBtnCancel(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(EditItemActivity.this)
                .setTitle("Exit Confirmation")
                .setMessage("Exit without saving?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = Toast.makeText(EditItemActivity.this, "Exited View Item Screen!",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        EditItemActivity.this.finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = Toast.makeText(EditItemActivity.this, "Stayed on the View Item Screen!",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                })
                .setCancelable(true);
        AlertDialog alertDialog = alert.create();
        alertDialog.show();


    }


    @Override
    public void onDestroy() {

        // If the DownloadStateReceiver still exists, unregister it and set it to null
        if (serviceReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(serviceReceiver);
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
