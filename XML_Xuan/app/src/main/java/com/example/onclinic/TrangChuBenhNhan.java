package com.example.onclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.GridView;

import java.util.ArrayList;

public class TrangChuBenhNhan extends AppCompatActivity {
    GridView gridView;
    ArrayList<ThanhPhanTrangChu> arrayList;
    ThanhPhanTrangChuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_benh_nhan);

        gridView = (GridView)findViewById(R.id.gridTrangChu);
        arrayList = new ArrayList<>();
        arrayList.add(new ThanhPhanTrangChu("Đặt Phòng",  R.drawable.dat_phong, R.drawable.khung_thanh_phan));
        arrayList.add(new ThanhPhanTrangChu("Khám Online",  R.drawable.kham_onl, R.drawable.khung_thanh_phan));
        arrayList.add(new ThanhPhanTrangChu("Đo Nhịp Tim",  R.drawable.do_nhip_tim, R.drawable.khung_thanh_phan));
        arrayList.add(new ThanhPhanTrangChu("Lịch Sử Khám",  R.drawable.lich_su_kham, R.drawable.khung_thanh_phan));
        arrayList.add(new ThanhPhanTrangChu("Liên Hệ Chúng ",  R.drawable.img_contact, R.drawable.khung_thanh_phan));

        adapter = new ThanhPhanTrangChuAdapter(this, R.layout.thanh_phan,arrayList);
        gridView.setAdapter(adapter);
    }
}