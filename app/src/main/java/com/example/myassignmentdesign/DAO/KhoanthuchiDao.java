package com.example.myassignmentdesign.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myassignmentdesign.database.DbHelper;
import com.example.myassignmentdesign.model.Khoanthuchi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class KhoanthuchiDao {
    DbHelper helper;
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public KhoanthuchiDao(Context context){
        helper = new DbHelper(context);
    }
    public ArrayList<Khoanthuchi> getAll(String trangthai){
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<Khoanthuchi> list =  new ArrayList<>();
        String sql = "select * from KHOAN_TC join LOAI_TC on MaLoai= MaLoai_FK"+
                " where TrangThai = '"+trangthai +"'";
        Cursor cs = db.rawQuery(sql,null);
        cs.moveToFirst();
        while(!cs.isAfterLast()){
            int matc = cs.getInt(0);
            String tenkhoantc = cs.getString(1);
            String date = cs.getString(2);
            float tien = cs.getFloat(3);
            String ghichu = cs.getString(4);
            int maloai = cs.getInt(5);
            try {
                list.add(new Khoanthuchi(matc, tenkhoantc, format.parse(date), tien, ghichu, maloai));
            }catch (Exception e){
                e.printStackTrace();
            }
            cs.moveToNext();
        }
        cs.close();
        return list;
    }
    public Boolean insert(Khoanthuchi khoanthuchi){
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values =new ContentValues();
//        values.put("MaTc",1);
        String date = format.format(khoanthuchi.getDate());
        values.put("TenKhoanTc",khoanthuchi.getTenKhoanTc());
        values.put("Ngay",date);
        values.put("Tien",khoanthuchi.getTien());
        values.put("GhiChu",khoanthuchi.getGhiChu());
        values.put("MaLoai_FK",khoanthuchi.getMaLoai());
        long row = db.insert("KHOAN_TC",null,values);
        if(row>0){
            return true;
        }
        return false;
    }
    public boolean remove(String matc){
        SQLiteDatabase db = helper.getReadableDatabase();
       int row =  db.delete("KHOAN_TC","MaTc=?",new String[]{matc});
//        db.delete("LOAI_TC","MaLoai=?",new String[]{matc});
        if(row>0) {
            return true;
        }
        return false;
    }
    public Boolean update(Khoanthuchi khoanthuchi){
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenKhoanTc",khoanthuchi.getTenKhoanTc());
        values.put("Ngay",format.format(khoanthuchi.getDate()));
        values.put("Tien",khoanthuchi.getTien());
        values.put("GhiChu",khoanthuchi.getGhiChu());
        db.update("KHOAN_TC",values,"MaTc=?",new String[]{String.valueOf(khoanthuchi.getMaTc())});
        return true;
    }
}
