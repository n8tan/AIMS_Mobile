package com.nathan.prototypemainmenu.com.adminFunctions.resetPassword;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nathan.prototypemainmenu.R;
import com.nathan.prototypemainmenu.com.adminFunctions.deleteAccounts.DeleteAccountModel;
import com.nathan.prototypemainmenu.com.adminFunctions.deleteAccounts.DeleteAccountModelAdapter;

import java.util.List;

/**
 * Created by nathan on 4/15/2017.
 */

public class ResetPasswordModelAdapter extends
        RecyclerView.Adapter<ResetPasswordModelAdapter.ResetPasswordModelViewHolder> {

    //Holds the incoming list when the construct below is called.
    private List<ResetPasswordModel> dataList;
    public static Context activityContext;

    public ResetPasswordModelAdapter(List<ResetPasswordModel> data, Context context) {
        this.dataList = data;
        this.activityContext = context;
    }
    @Override
    public ResetPasswordModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rpAccount = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.reset_password_card, parent, false);

        final ResetPasswordModelViewHolder rpmvh = new ResetPasswordModelViewHolder(rpAccount);

        ResetPasswordModel data = dataList.get(viewType);
        rpmvh.rpAccountName.setText(data.rpAccountName);

        rpmvh.itemView.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Toast toast = Toast.makeText(ResetPasswordModelAdapter.activityContext,
                                rpmvh.rpAccountName.getText() + "'s card was clicked",
                                Toast.LENGTH_SHORT);

                        toast.show();
                    }
                }
        );

        rpmvh.btnRpAccount.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast toast = Toast.makeText(ResetPasswordModelAdapter.activityContext,
                                rpmvh.rpAccountName.getText() + "'s card button was clicked",
                                Toast.LENGTH_SHORT);

                        toast.show();

                        AlertDialog.Builder alert = new AlertDialog.Builder(activityContext)
                                .setTitle(
                                        R.string.reset_password_alert_title
                                )
                                .setMessage(
                                        R.string.reset_password_alert_msg
                                )
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast toast = Toast.makeText(activityContext,
                                                R.string.reset_password_success_msg,
                                                Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast toast = Toast.makeText(activityContext,
                                                R.string.reset_password_not_success_msg,
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
        return rpmvh;
    }

    @Override
    public void onBindViewHolder(ResetPasswordModelViewHolder holder, int position) {

        ResetPasswordModel data = dataList.get(position);

        holder.rpAccountName.setText(data.rpAccountName);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ResetPasswordModelViewHolder extends RecyclerView.ViewHolder {

        protected TextView rpAccountName;
        protected Button btnRpAccount;

        public ResetPasswordModelViewHolder(View v) {
            super(v);
            rpAccountName = (TextView) v.findViewById(R.id.rp_account_name);
            btnRpAccount = (Button) v.findViewById(R.id.btn_reset_password);

        }
    }


}
