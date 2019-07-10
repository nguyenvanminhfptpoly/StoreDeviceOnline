package com.minhnv.admin.storedeviceonline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.minhnv.admin.storedeviceonline.R;
import com.minhnv.admin.storedeviceonline.ultil.CheckConnecttion;
import com.minhnv.admin.storedeviceonline.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongtinKHActivity extends AppCompatActivity {
    private Toolbar toolbar8;
    private TextView textView;
    private EditText edTenKH;
    private EditText edEmailKH;
    private EditText edSdtKH;
    private Button btnBack;
    private Button btnConfirm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_kh);

        Khaibao();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ActionToolbar();
        if(CheckConnecttion.haveNetworkConnection(getApplicationContext())){
            EventButton();
        }else {
            CheckConnecttion.showToast_short(getApplicationContext(), "connect error");
        }

    }

    private void EventButton() {

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tenkh = edTenKH.getText().toString().trim();
                final String sdtkh = edSdtKH.getText().toString().trim();
                final String emailkh = edEmailKH.getText().toString().trim();
                if (tenkh.length()>0 && sdtkh.length()>0 && emailkh.length()>0){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.donhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            if(Integer.parseInt(madonhang) > 0){
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, Server.chitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.d("madh", madonhang);
                                        if(response.equals("1")){
                                            MainActivity.manggiohang.clear();
                                        CheckConnecttion.showToast_short(getApplicationContext(),"Add successfully");
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        CheckConnecttion.showToast_short(getApplicationContext(),"Continue buy products");
                                        }else {
                                            CheckConnecttion.showToast_short(getApplicationContext(), "Error");
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for (int i = 0; i < MainActivity.manggiohang.size(); i++){
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("madonhang",madonhang);
                                                jsonObject.put("masanpham",MainActivity.manggiohang.get(i).getIdsp());
                                                jsonObject.put("tensanpham",MainActivity.manggiohang.get(i).getTensp());
                                                jsonObject.put("giasanpham",MainActivity.manggiohang.get(i).getGiasp());
                                                jsonObject.put("soluongsanpham",MainActivity.manggiohang.get(i).getSoluongsp());

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String,String> hashMap1 = new HashMap<String, String>();
                                        hashMap1.put("json",jsonArray.toString());
                                        return hashMap1;
                                    }
                                };
                                queue.add(request);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String, String>();
                            hashMap.put("tenkh",tenkh);
                            hashMap.put("sdt",sdtkh);
                            hashMap.put("mail",emailkh);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else {
                    CheckConnecttion.showToast_short(getApplicationContext(), "Data is null");
                }
            }
        });

    }

    private void Khaibao() {
        toolbar8 = (Toolbar) findViewById(R.id.toolbar8);
        textView = (TextView) findViewById(R.id.textView);
        edTenKH = (EditText) findViewById(R.id.ed_TenKH);
        edEmailKH = (EditText) findViewById(R.id.ed_emailKH);
        edSdtKH = (EditText) findViewById(R.id.ed_sdtKH);
        btnBack = (Button) findViewById(R.id.btn_back);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);


    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar8);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar8.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
