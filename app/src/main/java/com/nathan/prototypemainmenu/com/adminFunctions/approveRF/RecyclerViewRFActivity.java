package com.nathan.prototypemainmenu.com.adminFunctions.approveRF;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.nathan.prototypemainmenu.R;
import com.nathan.prototypemainmenu.com.adminFunctions.deleteAccounts.DeleteAccountModelAdapter;
import com.nathan.prototypemainmenu.com.adminFunctions.deleteAccounts.RecyclerDeleteAccountActivity;
import com.nathan.prototypemainmenu.com.backgroundService.ServiceConstants;
import com.nathan.prototypemainmenu.com.utilities.ListCreator;

/**
 * Created by Nathan on 30/05/2017.
 */

public class RecyclerViewRFActivity extends AppCompatActivity {

    RFViewModelAdapter rvma;
    RecyclerView rfViewRecList;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_view_rf);
        rfViewRecList = (RecyclerView) findViewById(R.id.recycler_view_rf);
        rfViewRecList.setHasFixedSize(true);

        //Getting the activity where this Fragment is attached to.
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rfViewRecList.setLayoutManager(llm);

        //Creating the data and connecting the data to the RecyclerList
        ListCreator lc = new ListCreator(this);

        rvma = new RFViewModelAdapter(
                lc.createRFViewModelList(10),this);
        rfViewRecList.setAdapter(rvma);
    }

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

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//
//        rfViewRecList = (RecyclerView) view.findViewById(R.id.recycler_view_rf);
//        rfViewRecList.setHasFixedSize(true);
//
//        //Getting the activity where this Fragment is attached to.
//        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//
//        rfViewRecList.setLayoutManager(llm);
//
//        //Creating the data and connecting the data to the RecyclerList
//        ListCreator lc = new ListCreator(getContext());
//
//        rvma = new RFViewModelAdapter(
//                lc.createRFViewModelList(10),getContext());
//        rfViewRecList.setAdapter(rvma);
//    }
}
