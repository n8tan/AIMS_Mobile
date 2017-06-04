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

public class ViewItemModelAdapter_v2 extends RecyclerView.Adapter<ViewItemModelAdapter_v2.ViewItemModelViewHolder>  {

    //Holds the incoming list when the construct below is called.
    private List<ViewItemModel_v2> dataList;
    public static Context activityContext;

    public ViewItemModelAdapter_v2(List<ViewItemModel_v2> data, Context context) {
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
        //Code below was commented out so that item colors don't get changed
        //everytime user scrolls up and down.

        //Retrieves one piece from the provided dataList
        ViewItemModel_v2 data = dataList.get(i);

        //Stores the data into the card.
        dataViewHolder.vName.setText(data.name);
        //The color gets a new color when it is refreshed since the colors are random.
        dataViewHolder.vName.setBackground(data.statusColor);

        dataViewHolder.vId.setText(data.id);
        dataViewHolder.vId.setBackground(data.statusColor);

        dataViewHolder.vLocation.setText(data.location);
        dataViewHolder.vCategory.setText(data.category);
        dataViewHolder.vStatus.setText(data.status);

    }

    //Method to create the ViewHolder class(Please see the static class below this method)
    @Override
    public ViewItemModelViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
            from(viewGroup.getContext()).
            inflate(R.layout.view_item_card, viewGroup, false);

        final ViewItemModelViewHolder vimvh = new ViewItemModelViewHolder(itemView);

        ViewItemModel_v2 data = dataList.get(i);
        vimvh.vName.setText(data.name);
        vimvh.vName.setBackground(
                data.statusColor
        );
        vimvh.vId.setText(data.id);
        vimvh.vId.setBackground(data.statusColor);

        vimvh.vLocation.setText(data.location);
        vimvh.vCategory.setText(data.category);
        vimvh.vStatus.setText(data.status);

        vimvh.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast = Toast.makeText(ViewItemModelAdapter_v2.activityContext,
                                vimvh.vName.getText() + "'s card was clicked",
                                Toast.LENGTH_SHORT);

                        toast.show();
                        Intent intent = new Intent (view.getContext(), ViewItemActivity.class);
                        intent.putExtra("itemName",vimvh.vName.getText().toString());
                        intent.putExtra("itemId", vimvh.vId.getText().toString());
                        intent.putExtra("itemCategory", vimvh.vCategory.getText().toString());
                        intent.putExtra("itemLocation", vimvh.vLocation.getText().toString());
                        intent.putExtra("itemStatus", vimvh.vStatus.getText().toString());
                        view.getContext().startActivity(intent);

                    }
                }
        );

        vimvh.vEditBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast = Toast.makeText(ViewItemModelAdapter_v2.activityContext,
                                vimvh.vName.getText() + " edit button was clicked",
                                Toast.LENGTH_SHORT);

                        toast.show();
                        Intent intent = new Intent (view.getContext(), EditItemActivity.class);
                        intent.putExtra("itemName",vimvh.vName.getText().toString());
                        intent.putExtra("itemId", vimvh.vId.getText().toString());
                        intent.putExtra("itemCategory", vimvh.vCategory.getText().toString());
                        intent.putExtra("itemLocation", vimvh.vLocation.getText().toString());
                        intent.putExtra("itemStatus", vimvh.vStatus.getText().toString());
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
