package com.example.mypc.appdoctintuc_nhom53;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnTuoitre,btnThanhnien;
    LinearLayout ln_hotrochuyen;
    ConstraintLayout Manhinchinh;
    public static String link;
    public static String Tieude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // chạy animation chuyển background cho màn hình chính:
        Manhinchinh = findViewById(R.id.manhinhchinh);
        AnimationDrawable animationDrawable = (AnimationDrawable) Manhinchinh.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        //phần ánh xạ
        btnTuoitre = findViewById(R.id.btnBaotuoitre);
        btnThanhnien = findViewById(R.id.btnBaothanhnien);
        ln_hotrochuyen = findViewById(R.id.ln_hotro);
        ln_hotrochuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HotroActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter,R.anim.animation_exit);
            }
        });

        //tạo sự kiện click menu
        btnThanhnien.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                Showmenu_tn();
            }
        });

        btnTuoitre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Showmenu_tt();
            }
        });


    }
    // tạo popup menu thanh niên
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void Showmenu_tn(){
        PopupMenu popupMenu_tn = new PopupMenu(this,btnThanhnien);
        popupMenu_tn.getMenuInflater().inflate(R.menu.menu_baothanhnien,popupMenu_tn.getMenu());
        popupMenu_tn.setGravity(Gravity.END);
        popupMenu_tn.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.itmenu_tinmoi_tn:
                        link = "https://thanhnien.vn/rss/home.rss";
                        Tieude = "Tin mới";
                        break;
                   case R.id.itmenu_doisong_tn:
                        link = "https://thanhnien.vn/rss/doi-song.rss";
                        Tieude = "Đời sống";
                        break;
                    case R.id.itmenu_thegioi_tn:
                        link = "https://thanhnien.vn/rss/the-gioi.rss";
                        Tieude = "Thế giới";
                        break;
                    case R.id.itmenu_giaoduc_tn:
                        link = "https://thanhnien.vn/rss/giao-duc.rss";
                        Tieude = "Giáo dục";
                        break;
                    case R.id.itmenu_congnghe_tn:
                        link = "https://thanhnien.vn/rss/cong-nghe-thong-tin.rss";
                        Tieude = "Công nghệ";
                        break;
                    case R.id.itmenu_suckhoe_tn:
                        link = "https://thanhnien.vn/rss/doi-song/suc-khoe.rss";
                        Tieude = "Sức khỏe";
                        break;
                    case R.id.itmenu_quocphong_tn:
                        link = "https://thanhnien.vn/rss/thoi-su/quoc-phong.rss";
                        Tieude = "Quốc phòng";
                        break;
                    case R.id.itmenu_giaitri_tn:
                        link = "https://thanhnien.vn/rss/ban-can-biet/giai-tri.rss";
                        Tieude = "Giải trí";
                        break;

                }
                // truyền dữ liệu:
                Intent intent = new Intent(MainActivity.this,Dstintuc.class);

                Bundle bundle = new Bundle();
                bundle.putString("duonglink",link);
                bundle.putString("tieude",Tieude);
                intent.putExtra("dulieu1",bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter1,R.anim.animation_exit1);
                return false;
            }
        });


        popupMenu_tn.show();
    }

    // tạo popup menu tuổi trẻ
    private void Showmenu_tt(){
        PopupMenu popupMenu_tt = new PopupMenu(this,btnTuoitre);
        popupMenu_tt.getMenuInflater().inflate(R.menu.menu_baotuoitre,popupMenu_tt.getMenu());
        popupMenu_tt.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.itmenu_tinmoi_tt:
                        link = "https://tuoitre.vn/rss/tin-moi-nhat.rss";
                        Tieude = "Tin mới";
                        break;
                    case R.id.itmenu_thethao_tt:
                        link = "https://tuoitre.vn/rss/the-thao.rss";
                        Tieude = "Thể thao";
                        break;
                    case R.id.itmenu_thegioi_tt:
                        link = "https://tuoitre.vn/rss/the-gioi.rss";
                        Tieude = "Thế giới";
                        break;
                    case R.id.itmenu_khoahoc_tt:
                        link = "https://tuoitre.vn/rss/khoa-hoc.rss";
                        Tieude = "Khoa học";
                        break;
                    case R.id.itmenu_suckhoe_tt:
                        link = "https://tuoitre.vn/rss/suc-khoe.rss";
                        Tieude = "Sức khỏe";
                        break;
                    case R.id.itmenu_congnghe_tt:
                        link = "https://tuoitre.vn/rss/nhip-song-so.rss";
                        Tieude = "Công nghệ";
                        break;
                    case R.id.itmenu_giaoduc_tt:
//                        link = "https://tuoitre.vn/rss/tin-moi-nhat.rss";
                        link = "https://tuoitre.vn/rss/giao-duc.rss";
                        Tieude = "Giáo dục";
                        break;
                    case R.id.itmenu_phapluat_tt:
                        link = "https://tuoitre.vn/rss/phap-luat.rss";
                        Tieude = "Pháp luật";
                        break;


                }
                Intent intent = new Intent(MainActivity.this,Dstintuc.class);
                Bundle bundle = new Bundle();
                bundle.putString("duonglink",link);
                bundle.putString("tieude",Tieude);
                intent.putExtra("dulieu1",bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter1,R.anim.animation_exit1);
                return false;
            }
        });
        popupMenu_tt.show();

    }
}
