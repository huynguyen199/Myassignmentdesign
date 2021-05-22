package com.example.myassignmentdesign.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class Khoanthuchi implements Serializable {
    private int MaTc;
    private String TenKhoanTc;
    private Date date;
    private float tien;
    private String GhiChu;
    private int MaLoai;
    public Khoanthuchi(){

    }

    public Khoanthuchi(int maTc, String tenKhoanTc, Date date, float tien, String ghiChu) {
        MaTc = maTc;
        TenKhoanTc = tenKhoanTc;
        this.date = date;
        this.tien = tien;
        GhiChu = ghiChu;
    }

    public Khoanthuchi(int maTc, String tenKhoanTc, Date date, float tien, String ghiChu, int maLoai) {
        MaTc = maTc;
        TenKhoanTc = tenKhoanTc;
        this.date = date;
        this.tien = tien;
        GhiChu = ghiChu;
        MaLoai = maLoai;
    }

    public Khoanthuchi(String tenKhoanTc, Date date, float tien, String ghiChu, int maLoai) {
        TenKhoanTc = tenKhoanTc;
        this.date = date;
        this.tien = tien;
        GhiChu = ghiChu;
        MaLoai = maLoai;
    }



    public int getMaTc() {
        return MaTc;
    }

    public void setMaTc(int maTc) {
        MaTc = maTc;
    }

    public String getTenKhoanTc() {
        return TenKhoanTc;
    }

    public void setTenKhoanTc(String tenKhoanTc) {
        TenKhoanTc = tenKhoanTc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTien() {
        return tien;
    }

    public void setTien(float tien) {
        this.tien = tien;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }
}
