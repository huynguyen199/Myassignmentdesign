package com.example.myassignmentdesign;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassignmentdesign.DAO.KhoanthuchiDao;
import com.example.myassignmentdesign.DAO.LoaiThuDao;
import com.example.myassignmentdesign.R;
import com.example.myassignmentdesign.adapter.KhoanTCRCVAdapter;
import com.example.myassignmentdesign.model.Khoanthuchi;
import com.example.myassignmentdesign.model.ThuChi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class KhoanTCFragment extends Fragment {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    RecyclerView rcv;
    Khoanthuchi khoanthuchi;
    KhoanTCRCVAdapter adapter;
    ArrayList<Khoanthuchi> list;
    KhoanthuchiDao khoanthuchiDao;
    FloatingActionButton floatingActionButton;
    Spinner spinner;
    EditText editten,editngay,edittien,editghichu,editmaloai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_khoan_tc,container,false);
        rcv = view.findViewById(R.id.rcvKhoanTC);

        floatingActionButton = view.findViewById(R.id.floating);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_insert();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        list = new ArrayList<>();
//        Date date = new Date();
//        list.add(new Khoanthuchi("abc",date, (float) 200.0,"abc",1));
//        list.add(new Khoanthuchi("12312",date, (float) 200.0,"abc",1));
        KhoanthuchiDao khoanthuchiDao = new KhoanthuchiDao(getContext());
        list = khoanthuchiDao.getAll("Thu");
        adapter = new KhoanTCRCVAdapter((Activity) getContext(),list);
        rcv.setAdapter(adapter);
        return view;
    }


    public void dialog_insert(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View v = inflater.inflate(R.layout.dialog_insert,null);
        editten = v.findViewById(R.id.edittenkhoan_tc);
        //o day loi ne
        editngay = v.findViewById(R.id.editngay);
        edittien = v.findViewById(R.id.editmoney);
        editghichu = v.findViewById(R.id.editghichu);
        spinner = v.findViewById(R.id.spinner_loai);
        ArrayList<ThuChi> arrayList;
        LoaiThuDao loaiThuDao = new LoaiThuDao(getContext());
        arrayList = loaiThuDao.getAll("Thu");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,arrayList);
        spinner.setAdapter(arrayAdapter);
        spinner.getSelectedItem();
//        editmaloai = v.findViewById(R.id.editmaloai);
//        dang loi
        builder.setView(v)
                // Add action buttons
                .setPositiveButton("thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        try {
                            String editname = editten.getText().toString();
                            Date editdate = sdf.parse(String.valueOf(editngay.getText()));
                            float editmoney = Float.parseFloat(edittien.getText().toString());
                            String editnote = editghichu.getText().toString();
                            String a = spinner.getSelectedItem().toString();
                            String[] ma=  a.split(" -");
                            int maloai = Integer.parseInt(ma[0]);
                            khoanthuchiDao = new KhoanthuchiDao(getContext());
                            khoanthuchi = new Khoanthuchi(editname,editdate,editmoney,editnote,maloai);
                            if(khoanthuchiDao.insert(khoanthuchi)){
                                list.clear();
                                list.addAll(khoanthuchiDao.getAll("Thu"));
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getContext(),"thanh cong",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getContext(),"that bai",Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }
}
