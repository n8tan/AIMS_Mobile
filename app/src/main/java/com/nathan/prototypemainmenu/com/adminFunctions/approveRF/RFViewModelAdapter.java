package com.nathan.prototypemainmenu.com.adminFunctions.approveRF;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nathan.prototypemainmenu.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Nathan on 30/05/2017.
 */

public class RFViewModelAdapter extends RecyclerView.Adapter<RFViewModelAdapter.RFViewModelViewHolder>{

        private List<RFViewModel> dataList;
        public static Context activityContext;

        public RFViewModelAdapter(List<RFViewModel> data,Context context){
            this.dataList=data;
            this.activityContext=context;
        }

        @Override
        public RFViewModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View itemView= LayoutInflater.
            from(parent.getContext()).
            inflate(R.layout.view_rf_card,parent,false);

            final RFViewModelAdapter.RFViewModelViewHolder rmvh = new RFViewModelAdapter.RFViewModelViewHolder(itemView);

            RFViewModel data = dataList.get(viewType);
            rmvh.vRFItemName.setText(data.item_name);
            rmvh.vRFItemQty.setText(data.item_qty);

            return rmvh;
        }

    @Override
    public void onBindViewHolder(RFViewModelAdapter.RFViewModelViewHolder holder, int position) {
        //Code below was commented out so that item colors don't get changed
        //everytime user scrolls up and down.

        //Retrieves one piece from the provided dataList
        RFViewModel data = dataList.get(position);

        //Stores the data into the card.
        holder.vRFItemName.setText(data.item_name);
        holder.vRFItemQty.setText(data.item_qty);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class RFViewModelViewHolder extends RecyclerView.ViewHolder  {

        protected TextView vRFItemName;
        protected TextView vRFItemQty;

        public RFViewModelViewHolder(View v) {
            super(v);
            //vRFName = (TextView) v.findViewById(R.id.rf_name);
            vRFItemName = (TextView) v.findViewById(R.id.itemName);
            vRFItemQty = (TextView) v.findViewById(R.id.itemQty);
        }



    }
}
