package com.example.myassignmentdesign.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassignmentdesign.DAO.KhoanthuchiDao;
import com.example.myassignmentdesign.R;
import com.example.myassignmentdesign.model.Khoanthuchi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class KhoanTCRCVAdapter extends RecyclerView.Adapter<KhoanTCRCVAdapter.KhoanTCHolder> {
    Activity context;
    ArrayList<Khoanthuchi> list;
    Khoanthuchi khoanthuchi;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    EditText edtten,edtngay,edtmoney,edtghichu;

    public KhoanTCRCVAdapter(Activity context, ArrayList<Khoanthuchi> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public KhoanTCHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.khoantc_item,parent,false);
        return (new KhoanTCHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull final KhoanTCHolder holder, final int position) {
            holder.tvTieuDe.setText(list.get(position).getTenKhoanTc());
            holder.tvNgay.setText(sdf.format(list.get(position).getDate()));
            holder.tvTien.setText(String.valueOf(list.get(position).getTien()));
//         button  delete
            holder.ivDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSnackbar(position);
                }
            });
//            button edit
            holder.ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog(position);
                }
            });
//            holder.floatinsert.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
    }
    public void showSnackbar(final int position){
        Snackbar.make(context.findViewById(R.id.relative),"ban co chac chan xoa",5000)
                .setActionTextColor(Color.GREEN)
                .setAction("Yes", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        KhoanthuchiDao khoanthuchiDao = new KhoanthuchiDao(context);
                        String matc = String.valueOf(list.get(position).getMaTc());
                        if(khoanthuchiDao.remove(matc)){
                            list.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(context,"thanh cong",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show();
    }
    public void dialog(final int position){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // Get the layout inflater
        LayoutInflater inflater = context.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View v = inflater.inflate(R.layout.dialog_update, null);
        edtten = v.findViewById(R.id.editten);
        edtngay = v.findViewById(R.id.editdate);
        edtmoney = v.findViewById(R.id.editmoney);
        edtghichu = v.findViewById(R.id.editghichu);
        edtten.setText(list.get(position).getTenKhoanTc());
        try {
            edtngay.setText(sdf.format(list.get(position).getDate()));
        }catch (Exception e){

        }
        edtmoney.setText(String.valueOf(list.get(position).getTien()));
        edtghichu.setText(list.get(position).getGhiChu());
        builder.setView(v)
                // Add action buttons
                .setPositiveButton("cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        try {
                            int matc = list.get(position).getMaTc();
                            String ten = edtten.getText().toString();
                            Date ngay = sdf.parse(String.valueOf(edtngay.getText()));
                            float tien = Float.valueOf(String.valueOf(edtmoney.getText()));
                            String ghichu = edtghichu.getText().toString();
                            int maloai = list.get(position).getMaLoai();
                            khoanthuchi = new Khoanthuchi(matc,ten, ngay, tien, ghichu);
                            KhoanthuchiDao khoanthuchiDao = new KhoanthuchiDao(context);
                            if(khoanthuchiDao.update(khoanthuchi)){
                                khoanthuchi = new Khoanthuchi(matc, ten, ngay, tien, ghichu, maloai);
                                list.set(position,khoanthuchi);
                                notifyDataSetChanged();
                                Toast.makeText(context,"thanh cong",Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            Toast.makeText(context,"Lỗi ngày",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class KhoanTCHolder extends RecyclerView.ViewHolder {
        TextView tvTieuDe, tvNgay, tvTien;
        ImageView ivEdit, ivDel;
        CardView card;
        DrawerLayout drawer;
        FloatingActionButton floatinsert;

        public KhoanTCHolder(@NonNull View itemView) {
            super(itemView);
            tvTieuDe = itemView.findViewById(R.id.tvTieuDe);
            tvNgay = itemView.findViewById(R.id.tvNgay);
            tvTien = itemView.findViewById(R.id.tvTien);
            ivEdit = itemView.findViewById(R.id.btnEditKTC);
            ivDel = itemView.findViewById(R.id.btnDelKTC);
            card = itemView.findViewById(R.id.khoantc_item);
            drawer = itemView.findViewById(R.id.drawer_layout);
            floatinsert = itemView.findViewById(R.id.floating);

        }
    }
}