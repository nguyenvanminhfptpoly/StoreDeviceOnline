package com.minhnv.admin.storedeviceonline.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minhnv.admin.storedeviceonline.R;
import com.minhnv.admin.storedeviceonline.interrface.OnItemClickListenerRYC;
import com.minhnv.admin.storedeviceonline.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class Adapter_SP extends RecyclerView.Adapter<Adapter_SP.ViewHodel>   {
    private List<Sanpham> sanphams;
    private Context  context;
    private OnItemClickListenerRYC onItemClickListenerRYC;

    public Adapter_SP(List<Sanpham> sanphams, Context context, OnItemClickListenerRYC onItemClickListenerRYC) {
        this.sanphams = sanphams;
        this.context = context;
        this.onItemClickListenerRYC = onItemClickListenerRYC;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v1 = inflater.inflate(R.layout.cardview_sp, viewGroup, false);
        return new ViewHodel(v1, onItemClickListenerRYC);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel viewHodel, final int i) {
        final Sanpham sanpham = sanphams.get(i);
        viewHodel.tvTensp.setText(sanpham.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHodel.tvGiasp.setText("Price : "+ decimalFormat.format(sanpham.getGiaSP()));
        Picasso.with(context).load(sanpham.getHinhanhSP()).into(viewHodel.imgSp);
        viewHodel.imgSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListenerRYC.OnItemSelected(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanphams.size();
    }

    public static class ViewHodel extends RecyclerView.ViewHolder {
        public ImageView imgSp;
        public TextView tvTensp;
        public TextView tvGiasp;
        public TextView tvMotasp;
        public ViewHodel(@NonNull View itemView, OnItemClickListenerRYC onItemClickListenerRYC) {
            super(itemView);
             imgSp = (ImageView) itemView.findViewById(R.id.img_sp);
              tvTensp = (TextView) itemView.findViewById(R.id.tv_tensp);
             tvGiasp = (TextView) itemView.findViewById(R.id.tv_giasp);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }


    }
}
