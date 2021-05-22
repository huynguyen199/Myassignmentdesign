package com.example.myassignmentdesign.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassignmentdesign.R;

public class KhoanTCHolder extends RecyclerView.ViewHolder {
    //khong su dung
    TextView tvTieuDe,tvNgay,tvTien;
    ImageView ivEdit,ivDel;
    CardView card;
    public KhoanTCHolder(@NonNull View itemView) {
        super(itemView);


        tvTieuDe = itemView.findViewById(R.id.tvTieuDe);
        tvNgay = itemView.findViewById(R.id.tvNgay);
        tvTien = itemView.findViewById(R.id.tvTien);
        ivEdit = itemView.findViewById(R.id.btnEditKTC);
        ivDel = itemView.findViewById(R.id.btnDelKTC);
        card = itemView.findViewById(R.id.khoantc_item);


    }
}
