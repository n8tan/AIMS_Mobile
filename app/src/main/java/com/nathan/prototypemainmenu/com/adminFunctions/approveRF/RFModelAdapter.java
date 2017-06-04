package com.nathan.prototypemainmenu.com.adminFunctions.approveRF;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.source.ByteArraySource;
import com.nathan.prototypemainmenu.MainActivity;
import com.nathan.prototypemainmenu.R;
import com.nathan.prototypemainmenu.com.staffFunctions.scanItem.ViewScanItemActivity;
import com.nathan.prototypemainmenu.com.staffFunctions.viewItem.RecyclerViewItemActivity;
import com.nathan.prototypemainmenu.com.utilities.PDFViewer;

import java.util.List;

/**
 * Created by nathan on 4/14/2017.
 */

public class RFModelAdapter extends RecyclerView.Adapter<RFModelAdapter.RFModelViewHolder> {


    //Holds the incoming list when the construct below is called.
    private List<RFModel> dataList;
    public static Context activityContext;

    public RFModelAdapter(List<RFModel> data, Context context) {
        this.dataList= data;
        this.activityContext = context;
    }

    @Override
    public RFModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.approve_rf_card, parent, false);

        final RFModelViewHolder rmvh = new RFModelViewHolder(itemView);

        RFModel data = dataList.get(viewType);
        rmvh.vRFUser.setText(data.rf_user);
        rmvh.vRFDate.setText(data.rf_date);
        rmvh.vRFId.setText(String.valueOf(data.rf_id));

        rmvh.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast = Toast.makeText(activityContext,
                                rmvh.vRFUser.getText() + "'s card was clicked",
                                Toast.LENGTH_SHORT);

                        toast.show();
                        /*
                        Here we will insert Intent calling out the pdf. --Request web service--
                        Intent intent = new Intent (view.getContext(), ViewItemActivity.class);
                        intent.putExtra("itemName",rmvh.vName.getText().toString());
                        intent.putExtra("itemStatus",rmvh.vStatus.getText().toString());
                        intent.putExtra("itemLocation",rmvh.vLocation.getText().toString());
                        intent.putExtra("itemCategory",rmvh.vCategory.getText().toString());
                        view.getContext().startActivity(intent);
                        */

                    }
                }
        );

        rmvh.vBtnApprove.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Insert webservice thing here.
                        Toast toast = Toast.makeText(RFModelAdapter.activityContext,
                                "RF Approve Button triggered!", Toast.LENGTH_SHORT);
                        toast.show();
                        AlertDialog.Builder alert = new AlertDialog.Builder(activityContext)
                                .setTitle("Confirm RF Form Approval?")
                                .setMessage("Approve this RF Form?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast toast  = Toast.makeText(activityContext, "RF Form Approved!", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast toast = Toast.makeText(activityContext, "RF Form not approved!", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                })
                                .setCancelable(true);
                        AlertDialog alertDialog = alert.create();
                        alertDialog.show();
                    }
                }
        );

        rmvh.vBtnReject.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Insert webservice thing here.
                        Toast toast = Toast.makeText(RFModelAdapter.activityContext,
                                "RF Reject Button triggered!", Toast.LENGTH_SHORT);
                        toast.show();
                        AlertDialog.Builder alert = new AlertDialog.Builder(activityContext)
                                .setTitle("Confirm RF Form Reject")
                                .setMessage("Reject this form?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast toast = Toast.makeText(activityContext, "RF Form Rejected!", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast toast = Toast.makeText(activityContext, "RF Form Not Rejected!", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                })
                                .setCancelable(true);
                        AlertDialog alertDialog = alert.create();
                        alertDialog.show();
                    }
                }
        );

        rmvh.vBtnViewRF.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Insert webservice thing here.
                        Toast toast = Toast.makeText(RFModelAdapter.activityContext,
                                "RF View Button triggered!", Toast.LENGTH_SHORT);
                        toast.show();

                        //Now we call web service

                        //Place the result of web service into byteArray
                        //byte[] byteArray = null;
                        Intent viewRFIntent = new Intent(view.getContext(), RecyclerViewRFActivity.class);
                        view.getContext().startActivity(viewRFIntent);
                    }
                }
        );
        return rmvh;
    }

    @Override
    public void onBindViewHolder(RFModelViewHolder holder, int position) {
        //Code below was commented out so that item colors don't get changed
        //everytime user scrolls up and down.

        //Retrieves one piece from the provided dataList
        RFModel data = dataList.get(position);

        //Stores the data into the card.
        holder.vRFUser.setText(data.rf_user);
        holder.vRFDate.setText(data.rf_date);
        holder.vRFId.setText(String.valueOf(data.rf_id));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    //This class holds the 'reference' to the different views within the 'viewHolder'
    //The Card itself is the ViewHolder.
    public static class RFModelViewHolder extends RecyclerView.ViewHolder  {

        protected TextView vRFUser;
        protected Button vBtnApprove;
        protected Button vBtnReject;
        protected Button vBtnViewRF;
        protected TextView vRFId;
        protected TextView vRFDate;

        public RFModelViewHolder(View v) {
            super(v);
            //vRFName = (TextView) v.findViewById(R.id.rf_name);
            vRFUser = (TextView) v.findViewById(R.id.rf_user);
            vBtnApprove = (Button) v.findViewById(R.id.btn_approve);
            vBtnReject = (Button) v.findViewById(R.id.btn_reject);
            vBtnViewRF = (Button) v.findViewById(R.id.btn_view_rf);
            vRFId = (TextView) v.findViewById(R.id.rf_id);
            vRFDate = (TextView) v.findViewById(R.id.rf_date);
        }



    }


}
