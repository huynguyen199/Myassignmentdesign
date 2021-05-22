package com.example.myassignmentdesign.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myassignmentdesign.database.DbHelper;
import com.example.myassignmentdesign.model.Khoanthuchi;
import com.example.myassignmentdesign.model.ThuChi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LoaiThuDao  {

    DbHelper helper;

    public LoaiThuDao(Context context){
        helper = new DbHelper(context);
    }
    public ArrayList<ThuChi> getAll(String trangthai){
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<ThuChi> list =  new ArrayList<>();
        Cursor cs = db.rawQuery("select * from LOAI_TC where TrangThai=?",new String[]{trangthai});
        cs.moveToFirst();
        while(!cs.isAfterLast()){
            int matc = cs.getInt(0);
            String tenloai = cs.getString(1);
            String tragthai = cs.getString(2);
            list.add(new ThuChi(matc,tenloai,tragthai));
            cs.moveToNext();
        }
        cs.close();
        return list;
    }

    public Boolean insert(ThuChi thuChi){
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values =new ContentValues();
        values.put("MaLoai",thuChi.getMaloai());
        values.put("TenLoai",thuChi.getTenLoai());
        values.put("TrangThai",thuChi.getTrangThai());
        db.insert("LOAI_TC",null,values);
        return  true;
    }
    public void remove(ThuChi thuChi){
        SQLiteDatabase db = helper.getReadableDatabase();
        db.delete("LOAI_TC","MaLoai=?",new String[]{String.valueOf(thuChi.getMaloai())});
    }
//        db.delete("LOAI_TC","MaLoai=?",new String[]{khoanthuchi.ge});

    public Boolean change(ThuChi thuchi){
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenLoai",thuchi.getTenLoai());
        values.put("TrangThai",thuchi.getTrangThai());
        db.update("KHOAN_TC",values,"MaLoai=?",new String[]{String.valueOf(thuchi.getMaloai())});
        return true;
    }
}
