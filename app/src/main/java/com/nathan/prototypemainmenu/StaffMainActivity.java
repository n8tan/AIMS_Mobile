package com.nathan.prototypemainmenu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.nathan.prototypemainmenu.com.adminFunctions.deleteAccounts.RecyclerDeleteAccountActivity;
import com.nathan.prototypemainmenu.com.backgroundService.ServiceConstants;
import com.nathan.prototypemainmenu.com.staffFunctions.scanItem.ScanItemActivity;
import com.nathan.prototypemainmenu.com.staffFunctions.viewItem.RecyclerViewItemActivity;
import com.nathan.prototypemainmenu.com.staffFunctions.viewItem.ViewItemActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class StaffMainActivity extends AppCompatActivity {
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private static String TESTSHARED;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    String scannedItemJSON = "";
    String METHOD_NAME = "";
    String scanContent = "";
    String scanFormat = "";
    String itemId = "";
 //   Intent viewItemIntent = new Intent(MainActivity.this, ViewItemActivity.class);

    private FragmentTransaction ft;

    private boolean doubleBackToExitPressedOnce = false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    ServiceReceiver serviceReceiver;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_bar, menu);
        //Inflates the menu bar(The topmost bar on the home screen)
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(
                        Color.parseColor(
                                getResources().getString(R.string.action_bar_color)
                        )
                )
        );
        getSupportActionBar().setTitle(
                //Sets title as "Main Menu"
                getStringResource(R.string.title_closed_drawer)
        );

        //Set the View All Items Fragment as Default
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, new ScanItemActivity());
        ft.commit();


        mDrawerList = (ListView) findViewById(R.id.navList);

        mDrawerList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String optionName = (String) parent.getAdapter().getItem(position);

                        /////Checker stuff /////
                        CharSequence text = optionName;
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(StaffMainActivity.this, text, duration);
                        toast.show();
                        ////////////////////////

                        switch (optionName) {
                            case "View All Items":
                                //Calls the RecyclerViewItemActivity in which containts the sample items.

                                //Intent intent = new Intent(MainActivity.this,RecyclerViewItemActivity.class);
                                //startActivity(intent);

                                ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.content_frame, new RecyclerViewItemActivity());
                                ft.commit();
                                //Once we get the fragment, we close the drawer.
                                mDrawerLayout.closeDrawers();
                                break;

                            case "Scan Item":
                                //onScanClicked();
                                ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.content_frame, new ScanItemActivity());
                                ft.commit();

                                mDrawerLayout.closeDrawers();
                                break;

                        }

                    }
                }
        );

        addDrawerItems();
        //LayoutInflater inflater = getLayoutInflater();
        //View listHeaderView = inflater.inflate(R.layout.menu_screen_header,null,false);
        //mDrawerList.addHeaderView(listHeaderView);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        setupDrawer();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        // The filter's action is BROADCAST_ACTION
        IntentFilter statusIntentFilter = new IntentFilter(
                ServiceConstants.BROADCAST_ACTION);
        statusIntentFilter.addCategory(Intent.CATEGORY_DEFAULT);

        serviceReceiver = new StaffMainActivity.ServiceReceiver();

        //Registering the receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(
                serviceReceiver, statusIntentFilter
        );
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void addDrawerItems() {
        String[] itemArray = getResources().getStringArray(R.array.staff_item_list);
        /*
        * Method below was overriden so that the TextColor will be set to the color
        * 'white' instead of the default color 'black'
        * */

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemArray) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView textView = (TextView) view.findViewById(android.R.id.text1);

                /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.WHITE);
                return view;
            }
        };
        mDrawerList.setAdapter(mAdapter);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(
                        getStringResource(R.string.title_open_drawer)
                );
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(
                        getStringResource(R.string.title_closed_drawer)
                );
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (id) {
            case R.id.action_scan:
                //Insert Scan intent here.
                /////Checker stuff //////
                onScanClicked();
                ////////////////////////
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);
    }

    //This method would be called when user initiates barcode scanning
    public void onScanClicked() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt(
                getStringResource(R.string.scan_prompt)
        );
        integrator.setResultDisplayDuration(0);
        integrator.setWide();  // Wide scanning rectangle, may work better for 1D barcodes
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);


        if (scanningResult.getContents() != null
                && scanningResult.getFormatName() != null) {
            //we have a result
            scanContent = scanningResult.getContents();
            scanFormat = scanningResult.getFormatName();
            METHOD_NAME = "getScannedItem";
            System.out.println("SCANNED ITEM RESULT======" + scannedItemJSON);
            String output = "Scan Format: " + scanFormat + "\nScan Content: " + scanContent;


            Toast toast = Toast.makeText(StaffMainActivity.this, output, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(StaffMainActivity.this, "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //This method calls onScanClicked()
    /*
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            onScanClicked();
        }
    }
    */

    private String getStringResource(int valueToGet) {
        return (String) getResources().getString(valueToGet);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this,
                getStringResource(R.string.double_back_press_msg),
                Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    public  void setScannedItem(String jsonStr){
        Intent viewItemIntent = new Intent(StaffMainActivity.this, ViewItemActivity.class);
        JSONObject mJsonObject = null;
        System.out.println("JSON OBJECT TO BE PARSED===========" + jsonStr);
        try{
            mJsonObject = new JSONObject(jsonStr);
            viewItemIntent.putExtra("itemName", mJsonObject.getString("name"));
            viewItemIntent.putExtra("itemStatus", mJsonObject.getString("location"));
            viewItemIntent.putExtra("itemLocation", mJsonObject.getString("brand"));
            viewItemIntent.putExtra("itemCategory", mJsonObject.getString("categoryName"));
        }
        catch (JSONException e){
            e.printStackTrace();
        }
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
