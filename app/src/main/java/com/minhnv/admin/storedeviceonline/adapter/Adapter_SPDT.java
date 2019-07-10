package com.minhnv.admin.storedeviceonline.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.minhnv.admin.storedeviceonline.R;
import com.minhnv.admin.storedeviceonline.model.Loaisp;
import com.minhnv.admin.storedeviceonline.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_SPDT extends BaseAdapter {
    private ArrayList<Sanpham> sanphams;
    private Context context;

    public Adapter_SPDT(ArrayList<Sanpham> sanphams, Context context) {
        this.sanphams = sanphams;
        this.context = context;
    }

    public class Viewhoder {
        public ImageView imgSpdt;
        public TextView tvTenspdt, tvGiaspdt, tvMotaspdt;
    }
    @Override
    public int getCount() {
        return sanphams.size();
    }

    @Override
    public Object getItem(int position) {
        return sanphams.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewhoder viewhodel = null;
        if (convertView == null){
            viewhodel = new Viewhoder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cardview_dienthoai, null);
            viewhodel.imgSpdt = (ImageView) convertView.findViewById(R.id.img_spdt);
            viewhodel.tvTenspdt = (TextView) convertView.findViewById(R.id.tv_tenspdt);
            viewhodel.tvGiaspdt = (TextView) convertView.findViewById(R.id.tv_giaspdt);
            viewhodel.tvMotaspdt = (TextView) convertView.findViewById(R.id.tv_motaspdt);
            convertView.setTag(viewhodel);
        }else {
            viewhodel = (Viewhoder) convertView.getTag();

        }
        Sanpham sanpham = (Sanpham) getItem(position);
        Picasso.with(context).load(sanpham.getHinhanhSP()).into(viewhodel.imgSpdt);
        viewhodel.tvTenspdt.setText(sanpham.getTenSP());
        viewhodel.tvGiaspdt.setText(sanpham.getGiaSP()+"");
        viewhodel.tvMotaspdt.setText(sanpham.getMotaSP());
        return convertView;
    }
}
