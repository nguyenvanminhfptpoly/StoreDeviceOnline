package com.minhnv.admin.storedeviceonline.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.minhnv.admin.storedeviceonline.R;
import com.minhnv.admin.storedeviceonline.model.Sanpham;
import com.squareup.picasso.Picasso;

public class ChitietSP extends AppCompatActivity {
    private FloatingActionButton floatingActionButton2;
    private LinearLayout linearLayout;
    private Toolbar toolbar4;
    private ImageView imgCtSp;
    private Spinner spinner;
    private TextView tvCtTensp;
    private TextView tvCtGiasp;
    private TextView tvCtMotasp;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_sp);

        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        toolbar4 = (Toolbar) findViewById(R.id.toolbar4);
        imgCtSp = (ImageView) findViewById(R.id.img_ct_sp);
        spinner = (Spinner) findViewById(R.id.spinner);
        tvCtTensp = (TextView) findViewById(R.id.tv_ct_tensp);
        tvCtGiasp = (TextView) findViewById(R.id.tv_ct_giasp);
        tvCtMotasp = (TextView) findViewById(R.id.tv_ct_motasp);
        Toolbarr();
        GetInfomation();

        SpinnerOnclick();
    ActionToolbar();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dienthoai, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_giohang:
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbar4);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar4.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void SpinnerOnclick() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);

    }

    private void GetInfomation() {
        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsp");
        tvCtTensp.setText(sanpham.getTenSP());
        tvCtGiasp.setText(sanpham.getGiaSP()+"");
        tvCtMotasp.setText(sanpham.getMotaSP());
        Picasso.with(getApplicationContext()).load(sanpham.getHinhanhSP()).into(imgCtSp);
    }

    private void Toolbarr() {
        setSupportActionBar(toolbar4);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

}
