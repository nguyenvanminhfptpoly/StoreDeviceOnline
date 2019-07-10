package com.minhnv.admin.storedeviceonline.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.minhnv.admin.storedeviceonline.R;
import com.minhnv.admin.storedeviceonline.adapter.Adapter_LoaiSP;
import com.minhnv.admin.storedeviceonline.adapter.Adapter_SP;
import com.minhnv.admin.storedeviceonline.interrface.OnItemClickListenerRYC;
import com.minhnv.admin.storedeviceonline.model.GioHang;
import com.minhnv.admin.storedeviceonline.model.Loaisp;
import com.minhnv.admin.storedeviceonline.model.Sanpham;
import com.minhnv.admin.storedeviceonline.ultil.CheckConnecttion;
import com.minhnv.admin.storedeviceonline.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewFlipper viewfliper;
    private RecyclerView rycProducts;
    private NavigationView nav;
    private ListView listview;
    private DrawerLayout draLay;
    private ArrayList<Loaisp> mangloaisp;
    private Adapter_LoaiSP adapter_loaiSP;
    private int id = 0;
    private  String loaisp = "";
    private String hinhanhsp = "";

    private ArrayList<Sanpham> sanphams;
    private Adapter_SP adapter_sp;
    public static  ArrayList<GioHang> manggiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewfliper = (ViewFlipper) findViewById(R.id.viewfliper);
        rycProducts = (RecyclerView) findViewById(R.id.ryc_products);
        nav = (NavigationView) findViewById(R.id.nav);
        listview = (ListView) findViewById(R.id.listview);

        draLay = (DrawerLayout) findViewById(R.id.dra_lay);
        mangloaisp = new ArrayList<>();
        mangloaisp.add(0, new Loaisp(0,"Trang Chinh", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRH5iTFPd6iaOv_sPAOShLguTPxfgRF9mI74IJN6lty8NBWmS4G"));
        adapter_loaiSP = new Adapter_LoaiSP(mangloaisp, getApplicationContext());


        sanphams = new ArrayList<>();
        adapter_sp = new Adapter_SP(sanphams, getApplicationContext(), new OnItemClickListenerRYC() {
            @Override
            public void OnItemSelected(int position) {
                Intent intent = new Intent(getApplicationContext(), ChitietSPActivity.class);
                intent.putExtra("thongtinspp", sanphams.get(position));
                startActivity(intent);
            }
        });

        rycProducts.setHasFixedSize(true);
        rycProducts.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rycProducts.setAdapter(adapter_sp);


        listview.setAdapter(adapter_loaiSP);
        viewfliper.setFlipInterval(5000);
        viewfliper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewfliper.setInAnimation(animation_slide_in);
        viewfliper.setOutAnimation(animation_slide_out);


        if(CheckConnecttion.haveNetworkConnection(getApplicationContext())){
            QuangCao();
            ActionToolbar();
            getdulieuloaisp();
            GetdulieuSP();
            CatchOnItemListView();

        }else {
            CheckConnecttion.showToast_short(getApplicationContext(), "Connect Error");
        }

        if(manggiohang != null){

        }else {
            manggiohang = new ArrayList<>();
        }
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
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

    private void CatchOnItemListView() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position ){
                    case 0:
                        if (CheckConnecttion.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else {
                            CheckConnecttion.showToast_short(getApplicationContext(), "Connect Error");
                        }
                        draLay.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (CheckConnecttion.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, DienThoaiActivity.class);
                            intent.putExtra("idloaisp", sanphams.get(position).getId());
                            startActivity(intent);
                        }else {
                            CheckConnecttion.showToast_short(getApplicationContext(), "Connect Error");
                        }
                        draLay.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (CheckConnecttion.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, LaptopActivity.class);
                            intent.putExtra("idsp", sanphams.get(position).getId());
                            startActivity(intent);
                        }else {
                            CheckConnecttion.showToast_short(getApplicationContext(), "Connect Error");
                        }
                        draLay.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void GetdulieuSP() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanspmoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    int ID = 0;
                    String tensp = "";
                    int giasp = 0;
                    String hinhanhsp = "";
                    String motasp = "";
                    int id = 0;
                    for (int i = 0; i< response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            tensp = jsonObject.getString("tensp");
                            giasp = jsonObject.getInt("giasp");
                            motasp = jsonObject.getString("motasp");
                            hinhanhsp = jsonObject.getString("hinhanhsp");
                            id = jsonObject.getInt("idsanpham");
                            sanphams.add(new Sanpham(ID, giasp, id,tensp, motasp, hinhanhsp));
                            adapter_sp.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnecttion.showToast_short(getApplicationContext(), error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void getdulieuloaisp(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdansp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for (int i=0; i< response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            loaisp = jsonObject.getString("loaisp");
                            hinhanhsp = jsonObject.getString("hinhanhsp");
                            mangloaisp.add(new Loaisp(id, loaisp,hinhanhsp));
                            adapter_loaiSP.notifyDataSetChanged();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnecttion.showToast_short(getApplicationContext(), error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void QuangCao(){
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("http://mobilecenter.com.vn/uploads/image/images/h%C3%ACnh%20qu%E1%BA%A3ng%20c%C3%A1o%20face.jpg");
        mangquangcao.add("https://cdnmedia.baotintuc.vn/Upload/ESSoZh9IeVhxwO8Bh87Q/files/2019/02/gap3.jpg");
        mangquangcao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRIYYg9xxXWSOa1EF2F0GbNHjBy6ex1XRae9xrAiblQpL3zl7cDQ");
        mangquangcao.add("https://i.ytimg.com/vi/YUhdgQaLEXE/maxresdefault.jpg");

        for (int i = 0; i< mangquangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewfliper.addView(imageView);
        }
    }


}
