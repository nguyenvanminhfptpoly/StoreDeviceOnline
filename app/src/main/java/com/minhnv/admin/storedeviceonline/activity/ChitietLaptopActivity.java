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

public class ChitietLaptopActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton3;
    private LinearLayout linearLayout;
    private Toolbar toolbar5;
    private ImageView imgCt3Sp;
    private Spinner spinner;
    private TextView tvCt3Tensp;
    private TextView tvCt3Giasp;
    private TextView tvCt3Motasp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_laptop);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.floatingActionButton3);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        toolbar5 = (Toolbar) findViewById(R.id.toolbar5);
        imgCt3Sp = (ImageView) findViewById(R.id.img_ct3_sp);
        spinner = (Spinner) findViewById(R.id.spinner);
        tvCt3Tensp = (TextView) findViewById(R.id.tv_ct3_tensp);
        tvCt3Giasp = (TextView) findViewById(R.id.tv_ct3_giasp);
        tvCt3Motasp = (TextView) findViewById(R.id.tv_ct3_motasp);

        Toolbarr();
        GetInfomation();

        SpinnerOnclick();
        ActionToolbar();
    }
    private void SpinnerOnclick() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);

    }
    private void ActionToolbar() {
        setSupportActionBar(toolbar5);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar5.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void GetInfomation() {
        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsplaptop");
        tvCt3Tensp.setText(sanpham.getTenSP());
        tvCt3Giasp.setText(sanpham.getGiaSP()+"");
        tvCt3Motasp.setText(sanpham.getMotaSP());
        Picasso.with(getApplicationContext()).load(sanpham.getHinhanhSP()).into(imgCt3Sp);
    }

    private void Toolbarr() {
        setSupportActionBar(toolbar5);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
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
}
