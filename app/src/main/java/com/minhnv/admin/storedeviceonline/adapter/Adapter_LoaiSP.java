package com.minhnv.admin.storedeviceonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.minhnv.admin.storedeviceonline.R;
import com.minhnv.admin.storedeviceonline.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_LoaiSP extends BaseAdapter {
    ArrayList<Loaisp> loaispArrayList;
    Context context;

    public Adapter_LoaiSP(ArrayList<Loaisp> loaispArrayList, Context context) {
        this.loaispArrayList = loaispArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return loaispArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return loaispArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public static class ViewHodel{
        public ImageView img_loaisp;
        public TextView tv_loaisp;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodel viewHodel = null;
        if (convertView == null){
            viewHodel = new ViewHodel();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_loaisp, null);
            viewHodel.img_loaisp = convertView.findViewById(R.id.img_loaisp);
            viewHodel.tv_loaisp = convertView.findViewById(R.id.tv_tenloaisp);
            convertView.setTag(viewHodel);
        }else {
            viewHodel = (ViewHodel) convertView.getTag();

        }
        Loaisp loaisp = (Loaisp) getItem(position);
        viewHodel.tv_loaisp.setText(loaisp.getTenloaiSP());
        Picasso.with(context).load(loaisp.getHinhAnhSP()).into(viewHodel.img_loaisp);
        return convertView;
    }
}
