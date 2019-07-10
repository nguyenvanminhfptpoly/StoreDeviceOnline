package com.minhnv.admin.storedeviceonline.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.minhnv.admin.storedeviceonline.R;
import com.minhnv.admin.storedeviceonline.adapter.Adapter_giohang;
import com.minhnv.admin.storedeviceonline.model.GioHang;
import com.minhnv.admin.storedeviceonline.ultil.CheckConnecttion;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {
    private Toolbar toolbar6;
    private TextView tvThongbao;
    private ListView lvGiohang;
    private TextView tvTongtien;
    private static TextView tvTinhtien;
    private Button button;
    private Button button2;
    private Adapter_giohang adapter_giohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        Khaibao();
        ActionToolbar();
        ChechData();
        EnventUltil();
        CatchOnItemListVew();
        EnventButton();
    }

    private void EnventButton() {
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.manggiohang.size() > 0){
                    Intent intent = new Intent(getApplicationContext(), ThongtinKHActivity.class);
                    startActivity(intent);
                }else {
                    CheckConnecttion.showToast_short(getApplicationContext(), "Need products to pay ");
                }
            }
        });
    }


    private void CatchOnItemListVew() {
        lvGiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                builder.setTitle("Delete product");
                builder.setMessage(" Do you want delete this products");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.manggiohang.size() <= 0){
                            tvThongbao.setVisibility(View.VISIBLE);
                        }else {
                            MainActivity.manggiohang.remove(position);
                            adapter_giohang.notifyDataSetChanged();
                            EnventUltil();
                            if (MainActivity.manggiohang.size() <= 0){
                                tvThongbao.setVisibility(View.VISIBLE);
                            }else {
                                tvThongbao.setVisibility(View.INVISIBLE);
                                adapter_giohang.notifyDataSetChanged();
                                EnventUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter_giohang.notifyDataSetChanged();
                        EnventUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public  void EnventUltil() {
        long tongtien = 0;
        for (int i = 0; i < MainActivity.manggiohang.size(); i++){
            tongtien += MainActivity.manggiohang.get(i).getGiasp();
        }
        tvTinhtien.setText(tongtien+"");
    }

    private void ChechData() {
        if(MainActivity.manggiohang.size() <= 0){
            adapter_giohang.notifyDataSetChanged();
            tvThongbao.setVisibility(View.VISIBLE);
            lvGiohang.setVisibility(View.INVISIBLE);
        }else {
            adapter_giohang.notifyDataSetChanged();
            tvThongbao.setVisibility(View.INVISIBLE);
            lvGiohang.setVisibility(View.VISIBLE);
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
    private void ActionToolbar() {
        setSupportActionBar(toolbar6);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar6.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Khaibao() {
        toolbar6 = (Toolbar) findViewById(R.id.toolbar6);
        tvThongbao = (TextView) findViewById(R.id.tv_thongbao);
        lvGiohang = (ListView) findViewById(R.id.lv_giohang);
        tvTongtien = (TextView) findViewById(R.id.tvTongtien);
        tvTinhtien = (TextView) findViewById(R.id.tvTinhtien);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        adapter_giohang = new Adapter_giohang(MainActivity.manggiohang, getApplicationContext());
        lvGiohang.setAdapter(adapter_giohang);
    }

}
