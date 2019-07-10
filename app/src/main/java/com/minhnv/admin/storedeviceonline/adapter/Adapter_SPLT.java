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
import com.minhnv.admin.storedeviceonline.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_SPLT extends BaseAdapter {
    private List<Sanpham> loaisps;
    private Context context;

    public Adapter_SPLT(List<Sanpham> loaisps, Context context) {
        this.loaisps = loaisps;
        this.context = context;
    }

    @Override
    public int getCount() {
        return loaisps.size();
    }

    @Override
    public Object getItem(int position) {
        return loaisps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class Viewhodell{
        public ImageView img_splt;
        public TextView tv_tensplt,tv_giasplt,tv_motasplt;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewhodell viewhodell = null;
        if(convertView == null){
            viewhodell = new Viewhodell();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cardview_laptop, null);
            viewhodell.img_splt = (ImageView) convertView.findViewById(R.id.img_splt);
            viewhodell.tv_tensplt = (TextView) convertView.findViewById(R.id.tv_tensplt);
            viewhodell.tv_giasplt = (TextView) convertView.findViewById(R.id.tv_giasplt);
            viewhodell.tv_motasplt = (TextView) convertView.findViewById(R.id.tv_motasplt);
            convertView.setTag(viewhodell);
        }else {
            viewhodell = (Viewhodell) convertView.getTag();
        }
        Sanpham loaisp = loaisps.get(position);
        Picasso.with(context).load(loaisp.getHinhanhSP()).into(viewhodell.img_splt);
        viewhodell.tv_giasplt.setText(loaisp.getGiaSP()+"");
        viewhodell.tv_motasplt.setText(loaisp.getMotaSP());
        viewhodell.tv_tensplt.setText(loaisp.getTenSP());
        return convertView;
    }
}
