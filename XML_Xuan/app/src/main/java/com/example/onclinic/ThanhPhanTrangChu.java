package com.example.onclinic;

public class ThanhPhanTrangChu {
    private String tenThanhPhan;
    private int anh;
    private int khung;

    public String getTenThanhPhan() {
        return tenThanhPhan;
    }

    public void setTenThanhPhan(String tenThanhPhan) {
        this.tenThanhPhan = tenThanhPhan;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public int getKhung() {
        return khung;
    }

    public void setKhung(int khung) {
        this.khung = khung;
    }



    public ThanhPhanTrangChu(String tenThanhPhan, int anh, int khung) {
        this.tenThanhPhan = tenThanhPhan;
        this.anh = anh;
        this.khung = khung;
    }
}
