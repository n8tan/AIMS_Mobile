package com.nathan.prototypemainmenu.com.staffFunctions.viewItem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nathan.prototypemainmenu.R;

import java.util.List;


/**
 * Created by nathan on 2/13/2017.
 */

public class ViewItemModelAdapter extends RecyclerView.Adapter<ViewItemModelAdapter.ViewItemModelViewHolder>  {

    //Holds the incoming list when the construct below is called.
    private List<ViewItemModel> dataList;
    public static Context activityContext;

    public ViewItemModelAdapter(List<ViewItemModel> data, Context context) {
        this.dataList= data;
        this.activityContext = context;
    }

    @Override
    public int getItemCount() {
         return dataList.size();
    }

    //Method to connect the data with the Card UI when a single Card is shown
    //Meaning if you were to look at the first card then scrolled down
    //and scrolled back up, the items would be recreated...or something like that.
    @Override
    public void onBindViewHolder(ViewItemModelViewHolder dataViewHolder, int i) {

        //Retrieves one piece from the provided dataList
        ViewItemModel data = dataList.get(i);

        //Stores the data into the card.
        dataViewHolder.vName.setText(data.name);
        dataViewHolder.vCategory.setText(data.category);
        dataViewHolder.vLocation.setText(data.location);
        //The color gets a new color when it is refreshed since the colors are random.

        dataViewHolder.vStatus.setText(data.status);

    }

    //Method to create the ViewHolder class(Please see the static class below this method)
    @Override
    public ViewItemModelViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
            from(viewGroup.getContext()).
            inflate(R.layout.view_item_card, viewGroup, false);

        final ViewItemModelViewHolder vimvh = new ViewItemModelViewHolder(itemView);

        ViewItemModel data = dataList.get(i);
        vimvh.vName.setText(data.name);
        vimvh.vCategory.setText(data.category);
        vimvh.vLocation.setText(data.location);

        vimvh.vStatus.setText(data.status);

        vimvh.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast = Toast.makeText(ViewItemModelAdapter.activityContext,
                                vimvh.vName.getText() + "'s card was clicked",
                                Toast.LENGTH_SHORT);

                        toast.show();
                        Intent intent = new Intent (view.getContext(), ViewItemActivity.class);
                        intent.putExtra("itemName",vimvh.vName.getText().toString());
                        intent.putExtra("itemStatus",vimvh.vStatus.getText().toString());
                        intent.putExtra("itemLocation",vimvh.vLocation.getText().toString());
                        intent.putExtra("itemCategory",vimvh.vCategory.getText().toString());
                        view.getContext().startActivity(intent);

                    }
                }
        );

        return vimvh;
    }

    //This class holds the 'reference' to the different views within the 'viewHolder'
    //The Card itself is the ViewHolder.
    public static class ViewItemModelViewHolder extends RecyclerView.ViewHolder  {

        protected TextView vName;
        protected TextView vId;
        protected TextView vLocation;
        protected TextView vStatus;
        protected TextView vCategory;
        protected TextView vEditBtn;

        public ViewItemModelViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.itemName);
            vId = (TextView) v.findViewById(R.id.itemId);
            vLocation = (TextView) v.findViewById(R.id.itemLocation);
            vStatus = (TextView) v.findViewById(R.id.itemStatus);
            vCategory = (TextView) v.findViewById(R.id.itemCategory);
            vEditBtn = (Button) v.findViewById(R.id.btnEdit);


        }

    }

}
