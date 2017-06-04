package com.nathan.prototypemainmenu.com.staffFunctions.viewItem;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nathan.prototypemainmenu.R;
import com.nathan.prototypemainmenu.com.adminFunctions.deleteAccounts.RecyclerDeleteAccountActivity;
import com.nathan.prototypemainmenu.com.backgroundService.ServiceConstants;

/**
 * Created by nathan on 3/19/2017.
 */

public class ViewItemActivity extends AppCompatActivity {

    TextView itemName;
    TextView itemStatus;
    TextView itemCategory;
    TextView itemLocation;

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
        setContentView(R.layout.view_item_screen);

        getSupportActionBar().setTitle("Edit Item");

        Intent intent = getIntent();

        itemName = (TextView) findViewById(R.id.itemName);
        itemStatus = (TextView) findViewById(R.id.itemStatus);
        itemLocation = (TextView) findViewById(R.id.itemLocation);
        itemCategory = (TextView) findViewById(R.id.itemCategory);


        String itemNameData = intent.getStringExtra("itemName");
        String itemStatusData = intent.getStringExtra("itemStatus");
        String itemLocationData = intent.getStringExtra("itemLocation");
        String itemCategoryData = intent.getStringExtra("itemCategory");

        itemName.setText(itemNameData);
        itemStatus.setText(itemStatusData);
        itemLocation.setText(itemLocationData);
        itemCategory.setText(itemCategoryData);

        // The filter's action is BROADCAST_ACTION
        IntentFilter statusIntentFilter = new IntentFilter(
                ServiceConstants.BROADCAST_ACTION);
        statusIntentFilter.addCategory(Intent.CATEGORY_DEFAULT);

        serviceReceiver = new ViewItemActivity.ServiceReceiver();

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

    public void onClickBtnBack(View view) {
        ViewItemActivity.this.finish();

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
