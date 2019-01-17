package com.example.mypc.appdoctintuc_nhom53;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class Lichsu extends AppCompatActivity {
ListView lvDanhsachtin_ls;
ImageView imgVHome_ls;
LinearLayout lnQuayvehotro_ls;
ArrayList<Doctintuc> mangdocbao_ls;
customAdapter customAdapter_ls;
Doctintuc doctintuc_ls;
SharedPreferences sharedPreferences;
String Tieude_LS,Image_LS,Link_LS;
RelativeLayout manhinhlichsu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsu);

        // chạy animation cho màn hình lịch sử:
        manhinhlichsu = findViewById(R.id.manhinhlichsu);

        AnimationDrawable animationDrawable = (AnimationDrawable) manhinhlichsu.getBackground();
        animationDrawable.setEnterFadeDuration(6000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        //
        //khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("savedData",MODE_PRIVATE);

        // hàm ánh xạ
        lvDanhsachtin_ls = findViewById(R.id.lvDanhsachtin_LS);
        imgVHome_ls = findViewById(R.id.imgVHome_LS);
        lnQuayvehotro_ls = findViewById(R.id.lnQuayvehotro_LS);



        lnQuayvehotro_ls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lichsu.this,HotroActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter,R.anim.animation_exit);
            }
        });
        imgVHome_ls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lichsu.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter2,R.anim.animation_exit2);
            }
        });

        //
        mangdocbao_ls = new ArrayList<>();
        Intent intent = getIntent();
        doctintuc_ls = (Doctintuc) intent.getSerializableExtra("dulieu3");

        if((doctintuc_ls)!=null) {
            SharedPreferences.Editor editor_ls = sharedPreferences.edit();
            editor_ls.putString("tieude",doctintuc_ls.title);
            editor_ls.putString("duonglink",doctintuc_ls.link);
            editor_ls.putString("hinhanh",doctintuc_ls.image);
            editor_ls.commit(); // xác nhận việc chỉnh sửa
        }

        Tieude_LS = sharedPreferences.getString("tieude","");
        Link_LS = sharedPreferences.getString("duonglink","");
        Image_LS = sharedPreferences.getString("hinhanh",null);
        // kiểm tra null để thông báo cho người dùng:
        if(Image_LS == null)
        {
            Toast.makeText(this, "chưa có lịch sử nào được ghi nhận!", Toast.LENGTH_SHORT).show();
        }



        mangdocbao_ls.add(new Doctintuc(Tieude_LS,Link_LS, Image_LS));
        customAdapter_ls = new customAdapter(Lichsu.this,android.R.layout.simple_list_item_1,mangdocbao_ls);
        lvDanhsachtin_ls.setAdapter(customAdapter_ls);
        customAdapter_ls.notifyDataSetChanged();
        lvDanhsachtin_ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Lichsu.this,Webview.class);
                Bundle bundle = new Bundle();
                bundle.putString("link",mangdocbao_ls.get(position).link);
                bundle.putString("tieude","LỊCH SỬ");
                intent.putExtra("dulieu",bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter,R.anim.animation_exit);
            }
        });

           lvDanhsachtin_ls.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
               @Override
               public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                   mangdocbao_ls.remove(position);
                   SharedPreferences.Editor editor_ls = sharedPreferences.edit();
                   editor_ls.putString("tieude", "");
                   editor_ls.putString("duonglink", "http://static.ilike.com.vn:8880/uploads/2017/12/anh-bia-nhung-giong-meo-dep-nhat-the-gioi.jpg");
                   editor_ls.putString("hinhanh", null);
                   editor_ls.commit(); // xác nhận việc chỉnh sửa
                   customAdapter_ls.notifyDataSetChanged();
                   return false;
               }
           });
        }

    }

