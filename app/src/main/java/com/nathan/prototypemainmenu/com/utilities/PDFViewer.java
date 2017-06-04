package com.nathan.prototypemainmenu.com.utilities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.source.ByteArraySource;
import com.nathan.prototypemainmenu.R;
import com.nathan.prototypemainmenu.com.staffFunctions.viewItem.ViewItemActivity;

public class PDFViewer extends AppCompatActivity {
    public static final String SAMPLE_FILE = "python_tutorial.pdf";
    Integer pageNumber = 0;
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_view_screen);
        getSupportActionBar().setTitle("PDF Viewer");


        Intent intent = getIntent();
        byte[] bytes = intent.getByteArrayExtra("byteArray");
        setPDF(bytes);

    }

    private void setPDF(byte[] bytes){
        pdfView = (PDFView) findViewById(R.id.pdfView);
        pdfView.fromBytes(bytes)
                .defaultPage(pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }

    public void onClickBtnBack(View view) {
        PDFViewer.this.finish();

    }


}
