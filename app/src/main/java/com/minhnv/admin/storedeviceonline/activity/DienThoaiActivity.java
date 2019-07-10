package com.minhnv.admin.storedeviceonline.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
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
import com.minhnv.admin.storedeviceonline.adapter.Adapter_SPDT;
import com.minhnv.admin.storedeviceonline.model.Sanpham;
import com.minhnv.admin.storedeviceonline.ultil.CheckConnecttion;
import com.minhnv.admin.storedeviceonline.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DienThoaiActivity extends AppCompatActivity {
    private Toolbar toolbar2;
    private ListView lv_spdt;
    private ArrayList<Sanpham> sanphams;
    private Adapter_SPDT adapter_spdt;
    private int iddt = 0;
    private int page = 1;
    private View ftView;
    private boolean isLoading = true;
    private boolean limitdata = false;
    mHandler mha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        lv_spdt = (ListView) findViewById(R.id.lv_spdt);
        sanphams = new ArrayList<>();
        adapter_spdt = new Adapter_SPDT(sanphams, getApplicationContext());
        lv_spdt.setAdapter(adapter_spdt);

        setSupportActionBar(toolbar2);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        ftView = inflater.inflate(R.layout.progressbar, null);
        if (CheckConnecttion.haveNetworkConnection(getApplicationContext())){
            Getidloaisp();
            GetData(page);
            LoadmoreData();
        }else {
            CheckConnecttion.showToast_short(getApplicationContext(), "Connect error");
        }

        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



       mha = new mHandler();
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
    private void LoadmoreData() {
        lv_spdt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChitietSP.class);
                intent.putExtra("thongtinsp", sanphams.get(position));
                startActivity(intent);
            }
        });
        lv_spdt.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && isLoading == false && limitdata == true){
                    Thearddata thearddata = new Thearddata();
                    isLoading = true;
                }
            }
        });
    }

    public class  mHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case  0:
                    lv_spdt.addFooterView(ftView);
                    break;
                case 1:
                    GetData(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class Thearddata extends Thread{
        @Override
        public void run() {
            mha.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mha.obtainMessage();
            mha.sendMessage(message);
            super.run();
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdandt = Server.duongdanspdt + String.valueOf(Page);
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
                    lv_spdt.removeFooterView(ftView);
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
                            adapter_spdt.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    limitdata = true;
                    lv_spdt.removeFooterView(ftView);
                    CheckConnecttion.showToast_short(getApplicationContext(),"Data is end");
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
                params.put("idsanpham", String.valueOf(iddt));
                return super.getParams();
            }
        };
        requestQueue.add(stringRequest);
    }

    private void Getidloaisp() {
        iddt = getIntent().getIntExtra("idloaisp", -1);

    }
}
