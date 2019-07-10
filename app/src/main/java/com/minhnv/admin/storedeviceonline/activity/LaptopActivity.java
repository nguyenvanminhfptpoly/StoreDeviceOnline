package com.minhnv.admin.storedeviceonline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.minhnv.admin.storedeviceonline.R;
import com.minhnv.admin.storedeviceonline.adapter.Adapter_SPLT;
import com.minhnv.admin.storedeviceonline.model.Sanpham;
import com.minhnv.admin.storedeviceonline.ultil.CheckConnecttion;
import com.minhnv.admin.storedeviceonline.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LaptopActivity extends AppCompatActivity {
    private Toolbar toolbar3;
    private ListView lvSplt;
    private ArrayList<Sanpham> sanphams;
    private Adapter_SPLT adapter_splt;
    private int idlt = 0;
    private int page = 1;
    private View ftView;
    private boolean isLoading = true;
    private boolean limitdata = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);
        toolbar3 = (Toolbar) findViewById(R.id.toolbar3);
        lvSplt = (ListView) findViewById(R.id.lv_splt);
        sanphams = new ArrayList<>();
        adapter_splt = new Adapter_SPLT( sanphams, getApplicationContext());
        lvSplt.setAdapter(adapter_splt);

        setSupportActionBar(toolbar3);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        if(CheckConnecttion.haveNetworkConnection(getApplicationContext())){
            GetidLaptop();
            GetData(page);
            LoadmoreLaptop();
            ActionToolbar();
        }else {
            CheckConnecttion.showToast_short(getApplicationContext(),"Connect error");
        }
    }

    private void LoadmoreLaptop() {
        lvSplt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LaptopActivity.this, ChitietLaptopActivity.class);
                intent.putExtra("thongtinsplaptop", sanphams.get(position));
                startActivity(intent);
            }
        });
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbar3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar3.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void GetidLaptop() {
        idlt = getIntent().getIntExtra("idsp", -1);

    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdandt = Server.daptopmenu + String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdandt, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String tensp = "";
                int giasp = 0;
                String hinhanhsp = "";
                String motasp = "";
                int idspdt = 0;
                if (response != null && response.length() != 2) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tensp = jsonObject.getString("tensp");
                            giasp = jsonObject.getInt("giasp");
                            motasp = jsonObject.getString("motasp");
                            hinhanhsp = jsonObject.getString("hinhanhsp");
                            idspdt = jsonObject.getInt("idsanpham");
                            sanphams.add(new Sanpham(id, giasp, idspdt, tensp, motasp, hinhanhsp));
                            adapter_splt.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("idsanpham", String.valueOf(idlt));
                return super.getParams();
            }
        };
        requestQueue.add(stringRequest);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
