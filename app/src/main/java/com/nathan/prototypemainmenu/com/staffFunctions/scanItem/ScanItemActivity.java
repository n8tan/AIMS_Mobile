package com.nathan.prototypemainmenu.com.staffFunctions.scanItem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.nathan.prototypemainmenu.R;
import com.nathan.prototypemainmenu.com.staffFunctions.viewItem.ViewItemActivity;
import com.nathan.prototypemainmenu.com.staffFunctions.viewItem.ViewItemModelAdapter;

/**
 * Created by nathan on 5/14/2017.
 */

public class ScanItemActivity extends Fragment {

    Button scanBtn;
    EditText barcodeInput;
    Button sendBarcodeBtn;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.scan_item_screen, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        scanBtn = (Button) view.findViewById(R.id.btn_scan);
        barcodeInput = (EditText) view.findViewById(R.id.scanItemBarcode);
        sendBarcodeBtn = (Button) view.findViewById(R.id.btn_send);
        scanBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast = Toast.makeText(getContext(),
                                "Scan button was clicked",
                                Toast.LENGTH_SHORT);

                        toast.show();
                        IntentIntegrator integrator = new IntentIntegrator(getActivity());
                        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                        integrator.setPrompt(
                                getActivity().getString(R.string.scan_prompt)
                        );
                        integrator.setResultDisplayDuration(0);
                        integrator.setWide();  // Wide scanning rectangle, may work better for 1D barcodes
                        integrator.setCameraId(0);  // Use a specific camera of the device
                        integrator.initiateScan();
                    }
                }
        );


        sendBarcodeBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast = Toast.makeText(getContext(),
                                "Send button was clicked",
                                Toast.LENGTH_SHORT);

                        toast.show();
                        if(!barcodeInput.getText().equals("")) {
                            //Do web service here.
                        }
                    }
                }
        );
    }


}