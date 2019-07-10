package com.minhnv.admin.storedeviceonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.minhnv.admin.storedeviceonline.R;
import com.minhnv.admin.storedeviceonline.activity.GioHangActivity;
import com.minhnv.admin.storedeviceonline.activity.MainActivity;
import com.minhnv.admin.storedeviceonline.model.GioHang;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_giohang extends BaseAdapter {
    public List<GioHang> gioHangs;
    Context context;

    public Adapter_giohang(List<GioHang> gioHangs, Context context) {
        this.gioHangs = gioHangs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return gioHangs.size();
    }

    @Override
    public Object getItem(int position) {
        return gioHangs.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public static class ViewHodel{
        public ImageView imgGiohang;
        public TextView tvTengiohang;
        public TextView tvGiagiohang;
        public Button btnGiam;
        public Button btnSo;
        public Button btnTang;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       final ViewHodel viewHodel ;
        if(convertView == null){
              viewHodel = new ViewHodel();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_giohang, null);
            viewHodel.imgGiohang = (ImageView) convertView.findViewById(R.id.img_giohang);
            viewHodel.tvTengiohang = (TextView) convertView.findViewById(R.id.tv_tengiohang);
            viewHodel.tvGiagiohang = (TextView) convertView.findViewById(R.id.tv_giagiohang);
            viewHodel.btnSo = (Button) convertView.findViewById(R.id.btn_so);
            convertView.setTag(viewHodel);

        }else {
           viewHodel = (ViewHodel) convertView.getTag();
        }
        GioHang gioHang = gioHangs.get(position);
        viewHodel.tvTengiohang.setText(gioHang.getTensp());
        viewHodel.tvGiagiohang.setText(gioHang.getGiasp()+"");
        Picasso.with(context).load(gioHang.getHinhanhsp()).into(viewHodel.imgGiohang);
        viewHodel.btnSo.setText(gioHang.getSoluongsp()+"");

        return convertView;
    }
}
