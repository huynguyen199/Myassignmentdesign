package com.example.myassignmentdesign.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class ThuChi implements Serializable {
    private int Maloai;
    private String TenLoai;
    private String TrangThai;

    public ThuChi(int maloai, String tenLoai, String trangThai) {
        Maloai = maloai;
        TenLoai = tenLoai;
        TrangThai = trangThai;
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(getMaloai())+" - "+getTenLoai()+" - "+getTrangThai();
    }

    public int getMaloai() {
        return Maloai;
    }

    public void setMaloai(int maloai) {
        Maloai = maloai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
