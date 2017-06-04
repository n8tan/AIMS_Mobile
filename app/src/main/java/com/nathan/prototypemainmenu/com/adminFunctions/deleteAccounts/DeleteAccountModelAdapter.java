package com.nathan.prototypemainmenu.com.adminFunctions.deleteAccounts;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nathan.prototypemainmenu.R;
import com.nathan.prototypemainmenu.com.staffFunctions.viewItem.ViewItemActivity;
import com.nathan.prototypemainmenu.com.staffFunctions.viewItem.ViewItemModel;
import com.nathan.prototypemainmenu.com.staffFunctions.viewItem.ViewItemModelAdapter;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by nathan on 4/14/2017.
 */

public class DeleteAccountModelAdapter
        extends RecyclerView.Adapter<DeleteAccountModelAdapter.DeleteAccountModelViewHolder>{

    //Holds the incoming list when the construct below is called.
    private List<DeleteAccountModel> dataList;
    public static Context activityContext;

    public DeleteAccountModelAdapter(List<DeleteAccountModel> data, Context context) {
        this.dataList= data;
        this.activityContext = context;
    }

    @Override
    public DeleteAccountModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View deleteAccount = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.delete_account_card, parent, false);

        final DeleteAccountModelViewHolder damvh = new DeleteAccountModelViewHolder(deleteAccount);

        DeleteAccountModel data = dataList.get(viewType);
        damvh.dAccountName.setText(data.dAccountName);
        damvh.dAccountId.setText(String.valueOf(data.dAccountId));


        damvh.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast = Toast.makeText(DeleteAccountModelAdapter.activityContext,
                                damvh.dAccountName.getText() + "'s card was clicked",
                                Toast.LENGTH_SHORT);

                        toast.show();

                    }
                }
        );
        damvh.btnAccountRemove.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast toast = Toast.makeText(DeleteAccountModelAdapter.activityContext,
                                damvh.dAccountName.getText() + "'s card button was clicked",
                                Toast.LENGTH_SHORT);

                        toast.show();

                        AlertDialog.Builder alert = new AlertDialog.Builder(activityContext)
                                .setTitle(
                                        R.string.delete_account_alert_title
                                )
                                .setMessage(
                                        R.string.delete_account_alert_msg
                                )
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast toast = Toast.makeText(activityContext,
                                                R.string.delete_account_success_msg,
                                                Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast toast = Toast.makeText(activityContext,
                                                R.string.delete_account_not_success_msg,
                                                Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                })
                                .setCancelable(true);
                        AlertDialog alertDialog = alert.create();
                        alertDialog.show();
                    }
                }
        );
        return damvh;
    }

    @Override
    public void onBindViewHolder(DeleteAccountModelViewHolder holder, int position) {

        DeleteAccountModel data = dataList.get(position);

        holder.dAccountName.setText(data.dAccountName);
        holder.dAccountId.setText(String.valueOf(data.dAccountId));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class DeleteAccountModelViewHolder extends RecyclerView.ViewHolder {
        protected TextView dAccountName;
        protected Button btnAccountRemove;
        protected TextView dAccountId;

        public DeleteAccountModelViewHolder(View v) {
            super(v);
            dAccountName = (TextView) v.findViewById(R.id.delete_account_name);
            btnAccountRemove = (Button) v.findViewById(R.id.btn_delete_acconut);
            dAccountId = (TextView) v.findViewById(R.id.delete_account_id);
        }

    }
}
