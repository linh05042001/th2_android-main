package com.example.thuchanh2.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private  String ten,noidung,ngaydenhan,hinhthuc,trangthai;

    public Item() {
    }

    public Item(String ten, String noidung,  String trangthai, String ngaydenhan,  String hinhthuc) {
        this.hinhthuc = hinhthuc;
        this.ten = ten;
        this.noidung = noidung;
        this.ngaydenhan = ngaydenhan;
        this.trangthai = trangthai;
    }

    public Item(int id, String ten, String noidung,  String trangthai, String ngaydenhan,  String hinhthuc) {
        this.id = id;
        this.hinhthuc = hinhthuc;
        this.ten = ten;
        this.noidung = noidung;
        this.ngaydenhan = ngaydenhan;
        this.trangthai = trangthai;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getHinhthuc() {
        return hinhthuc;
    }

    public void setHinhthuc( String hinhthuc) {
        this.hinhthuc = hinhthuc;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgaydenhan() {
        return ngaydenhan;
    }

    public void setNgaydenhan(String ngaydenhan) {
        this.ngaydenhan = ngaydenhan;
    }

    public  String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai( String trangthai) {
        this.trangthai = trangthai;
    }
}
