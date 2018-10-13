package com.dell.lab6han.model;

public class NhanVien {

    private String mMa;
    private String mTennv;
    private String mluong;

    public NhanVien() {
    }

    public NhanVien(String mTennv, String mluong) {
        this.mTennv = mTennv;
        this.mluong = mluong;
    }

    public NhanVien(String mMa, String mTennv, String mluong) {
        this.mMa = mMa;
        this.mTennv = mTennv;
        this.mluong = mluong;
    }

    public String getmMa() {
        return mMa;
    }

    public void setmMa(String mMa) {
        this.mMa = mMa;
    }

    public String getmTennv() {
        return mTennv;
    }

    public void setmTennv(String mTennv) {
        this.mTennv = mTennv;
    }

    public String getMluong() {
        return mluong;
    }

    public void setMluong(String mluong) {
        this.mluong = mluong;
    }
}
