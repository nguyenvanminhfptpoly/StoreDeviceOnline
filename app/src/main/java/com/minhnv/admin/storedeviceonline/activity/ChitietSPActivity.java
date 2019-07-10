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
import com.minhnv.admin.storedeviceonline.model.GioHang;
import com.minhnv.admin.storedeviceonline.model.Sanpham;
import com.squareup.picasso.Picasso;

public class ChitietSPActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private LinearLayout linearLayout;
    private Toolbar toolbar4;
    private ImageView imgCt2Sp;
    private Spinner spinner;
    private TextView tvCt2Tensp;
    private TextView tvCt2Giasp;
    private TextView tvCt2Motasp;
    private int giasp, id = 0;
    private String Tensp, Motasp, HinhanhSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_sp2);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        toolbar4 = (Toolbar) findViewById(R.id.toolbar4);
        imgCt2Sp = (ImageView) findViewById(R.id.img_ct2_sp);
        spinner = (Spinner) findViewById(R.id.spinner);
        tvCt2Tensp = (TextView) findViewById(R.id.tv_ct2_tensp);
        tvCt2Giasp = (TextView) findViewById(R.id.tv_ct2_giasp);
        tvCt2Motasp = (TextView) findViewById(R.id.tv_ct2_motasp);
        setSupportActionBar(toolbar4);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        GetInfomation();

        SpinnerOnclick();

        EventButton();
        ActionToolbar();
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
    private void EventButton() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.manggiohang.size() > 0){
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exite = false;
                    for (int i = 0;i < MainActivity.manggiohang.size(); i++){
                        if(MainActivity.manggiohang.get(i).getIdsp() == id){
                            MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() + sl);
                            if(MainActivity.manggiohang.get(i).getSoluongsp() > 0){
                                MainActivity.manggiohang.get(i).setSoluongsp(10);
                            }
                            MainActivity.manggiohang.get(i).setGiasp(giasp + MainActivity.manggiohang.get(i).getSoluongsp());
                            exite = true;
                        }
                    }
                    if (exite == false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long Giamoi = soluong * giasp;
                        MainActivity.manggiohang.add(new GioHang(id,soluong,Tensp,HinhanhSP,Giamoi ));
                    }
                }else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi = soluong * giasp;
                    MainActivity.manggiohang.add(new GioHang(id,soluong,Tensp,HinhanhSP,Giamoi ));
                }
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void SpinnerOnclick() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);

    }

    private void GetInfomation() {
        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinspp");
        id = sanpham.getId();
        Tensp =  sanpham.getTenSP();
        giasp = sanpham.getGiaSP();
        tvCt2Giasp.setText(giasp+"");
        tvCt2Motasp.setText(Tensp);
        tvCt2Tensp.setText(Tensp);
        HinhanhSP = sanpham.getHinhanhSP();
        Picasso.with(getApplicationContext()).load(sanpham.getHinhanhSP()).into(imgCt2Sp);
    }



}
