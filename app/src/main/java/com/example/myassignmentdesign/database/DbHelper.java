package com.example.myassignmentdesign.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "QLCHITIEU" , null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table LOAI_TC(MaLoai integer primary key autoincrement,"+
                "TenLoai text,TrangThai text)";
        db.execSQL(sql);
        sql = "insert into LOAI_TC(TenLoai,TrangThai) values('Lai ngan hang','Thu')";
        db.execSQL(sql);
        sql = "insert into LOAI_TC(TenLoai,TrangThai) values('Luong','Thu')";
        db.execSQL(sql);
        sql = "insert into LOAI_TC(TenLoai,TrangThai) values('Ban hang','Thu')";
        db.execSQL(sql);
        sql = "insert into LOAI_TC(TenLoai,TrangThai) values('Thu no','Thu')";
        db.execSQL(sql);
        sql = "insert into LOAI_TC(TenLoai,TrangThai) values('Sinh hoat hang ngay','Chi')";
        db.execSQL(sql);
        sql = "insert into LOAI_TC(TenLoai,TrangThai) values('Dong tien bac','Chi')";
        db.execSQL(sql);
        sql = "insert into LOAI_TC(TenLoai,TrangThai) values('Du lich','Chi')";
        db.execSQL(sql);


        sql = "create table KHOAN_TC(MaTc integer primary key autoincrement,"+
                "TenKhoanTc text,Ngay Date,Tien Float,GhiChu Text, "+
                "MaLoai_FK integer references LOAI_TC(MaLoai))";
        db.execSQL(sql);
        sql = "insert into KHOAN_TC(TenKhoanTc,Ngay,Tien,GhiChu,MaLoai_FK) values('Lương tháng 1/2020','2020-01-05',2000.0,'Luong cao qua',1)";
        db.execSQL(sql);
        sql = "insert into KHOAN_TC(TenKhoanTc,Ngay,Tien,GhiChu,MaLoai_FK) values('Lương tháng 6/2020','2020-06-05',6000.0,'Luong cao',1)";
        db.execSQL(sql);
        sql = "insert into KHOAN_TC(TenKhoanTc,Ngay,Tien,GhiChu,MaLoai_FK) values('Luong thang 5/2020','2020-05-25',500.0,'nợ lâu',5)";
        db.execSQL(sql);
        sql = "insert into KHOAN_TC(TenKhoanTc,Ngay,Tien,GhiChu,MaLoai_FK) values('Chi ăn uống tháng 7','2020-07-25',500.0,'Đây là khoản thu chi',5)";
        db.execSQL(sql);
        sql = "insert into KHOAN_TC(TenKhoanTc,Ngay,Tien,GhiChu,MaLoai_FK) values('du lich','2020-07-25',500.0,'Đây là khoản thu chi',5)";
        db.execSQL(sql);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists KHOAN_TC");
        db.execSQL("Drop table if exists LOAI_TC");
        onCreate(db);
    }
}
