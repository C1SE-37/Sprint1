package com.example.onclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.NguoiDung;
import com.example.model.TaiKhoan;
import com.example.sqlhelper.CheckData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class DangKy extends AppCompatActivity {

    EditText txtEmailSDT, txtTen, txtMatKhau, txtNhapLaiMK;
    RadioButton rdoKhachHang,rdoPhongKham;
    Button btnDangKy;

    Spinner spNgay,spThang,spNam,spQuan,spThanhPho;
    int lastSelected = -1;
    ArrayList<Integer>dsNgay,dsThang,dsNam;
    ArrayAdapter<Integer>adapterNgay,adapterThang,adapterNam;
    ArrayList<String>dsQuan,dsThanhPho;
    ArrayAdapter<String>adapterQuan,adapterThanhPho;

    private String email_sdt,matkhau;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        addControls();
        addEvents();
    }

    private void addControls() {
        txtTen = findViewById(R.id.txtTen);
        txtEmailSDT = findViewById(R.id.txtSDT);
        txtMatKhau = findViewById(R.id.txtMatKhau);
        txtNhapLaiMK = findViewById(R.id.txtXacNhanMatKhau);
        spNgay = findViewById(R.id.sp_Ngay);
        spThang = findViewById(R.id.sp_Thang);
        spNam = findViewById(R.id.sp_Nam);
        spQuan = findViewById(R.id.sp_Quan);
        spThanhPho = findViewById(R.id.sp_ThanhPho);
        dsThanhPho = new ArrayList<>();
        dsThanhPho.addAll(Arrays.asList(getResources().getStringArray(R.array.arrThanhPho)));
        adapterThanhPho = new ArrayAdapter<String>(
                DangKy.this, android.R.layout.simple_spinner_item, dsThanhPho
        );
        adapterThanhPho.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spThanhPho.setAdapter(adapterThanhPho);

        rdoPhongKham = findViewById(R.id.rdoPhongKham);
        rdoKhachHang = findViewById(R.id.rdoPhongKham);
        btnDangKy = findViewById(R.id.btnDangKy);
    }

    private void addEvents() {
        spThanhPho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                    {
                        HienThiQuanHuyen(R.array.quanAnGiang);
                        break;
                    }

                    case 14:
                    {
                        HienThiQuanHuyen(R.array.quanDaNang);
                        break;
                    }
                    default:
                        adapterQuan = null;
                        break;

                }
                lastSelected = position;
                spQuan.setAdapter(adapterQuan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDangKy();
            }
        });
    }

    private void HienThiQuanHuyen(int position) {
        dsQuan = new ArrayList<>();
        dsQuan.addAll(Arrays.asList(getResources().getStringArray(position)));
        adapterQuan = new ArrayAdapter<String>(
                DangKy.this, android.R.layout.simple_spinner_item, dsQuan);
    }

    private void xuLyDangKy() {
        if (checkInput()==false)
        {
            try
            {
                auth.createUserWithEmailAndPassword(email_sdt,matkhau)
                        .addOnCompleteListener(DangKy.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DangKy.this,DangNhap.class));
                            finish();
                        }
                        else Toast.makeText(DangKy.this, "Lỗi đăng ký tài khoản", Toast.LENGTH_LONG).show();
                    }
                });

            }
            catch (Exception ex)
            {
                Log.e("Lỗi đăng ký tài khoản",ex.toString());
                Toast.makeText(DangKy.this, "Lỗi đăng ký tài khoản", Toast.LENGTH_LONG).show();
            }
        }
        else Toast.makeText(DangKy.this, "Hãy hoàn thành thông tin đăng ký", Toast.LENGTH_LONG).show();
    }

    private boolean checkInput() {
        //kiểm tra dữ liệu nhập vào
        CheckData.isEmpty(txtTen);
        CheckData.isEmpty(txtEmailSDT);
        if(CheckData.isEmpty(txtMatKhau))
        {
            email_sdt = txtEmailSDT.getText().toString().trim();
            matkhau = txtMatKhau.getText().toString().trim();
            if(matkhau.length()<6)
            {
                txtMatKhau.requestFocus();
                txtMatKhau.setError("Mật khẩu quá ngắn");
                return true;
            }
        }
        else if(CheckData.isEmpty(txtNhapLaiMK))
        {
            if(txtNhapLaiMK.equals(txtMatKhau)==false) {
                txtNhapLaiMK.requestFocus();
                txtNhapLaiMK.setError("Mật khẩu không giống");
            }
        }
        return false;
    }

    public void addNewAccount(String email)
    {

    }

}