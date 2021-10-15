package com.example.model;

import java.util.Date;

public class NguoiDung {
    private String tenNguoiDung;
    private Date ngaySinh;
    private String quan,thanhpho;


    @Override
    public String toString() {
        return this.tenNguoiDung+" đăng ký thành công";
    }
}
