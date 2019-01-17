package com.example.mypc.appdoctintuc_nhom53;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.BaseInputConnection;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.Manifest.permission.CALL_PHONE;

public class HotroActivity extends AppCompatActivity{

    LinearLayout lnMaytinh, lnGame, lnThoitiet, lnXoso, lnPhone, lnMessage, lnFanpage, lnLichsu, lnMail, lnQuayveMain;
    ConstraintLayout manhinhhotro;

    // ánh xạ:

    public static final int REQUEST_CODE_MAIL = 101; //số kiểm tra Email có được gửi chưa

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotro);
        // chạy animation cho màn hình hỗ trợ
        manhinhhotro = findViewById(R.id.manhinhhotro);
        AnimationDrawable animationDrawable = (AnimationDrawable) manhinhhotro.getBackground();

        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);

        animationDrawable.start();


        // hàm ánh xạ:
        lnPhone = findViewById(R.id.lnPhone);
        lnMessage = findViewById(R.id.lnMessage);
        lnFanpage = findViewById(R.id.lnFanpage);
        // ánh xạ bên trong tiện ích:
        lnMaytinh = findViewById(R.id.lnMaytinh);
        lnGame = findViewById(R.id.lngame);
        lnThoitiet = findViewById(R.id.lnThoitiet);
        lnXoso = findViewById(R.id.lnSoso);
        lnMail = findViewById(R.id.lnMail);
        lnQuayveMain = findViewById(R.id.lnQuayveMainHT);
        lnLichsu = findViewById(R.id.lnLichsu);


        //ánh xạ animation
        final Animation animationAlpha = AnimationUtils.loadAnimation(this, R.anim.animation_alpha);

        //linear chạy vào màn hình game
        lnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotroActivity.this,game_hotro.class);
                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_exit);

            }
        });
        // animation cho game:
        lnGame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.startAnimation(animationAlpha);
                return false;
            }
        });


        // linear quay về màn hình chính
        lnQuayveMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotroActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter1, R.anim.animation_exit1);
            }
        });

        // đi vào chức năng lịch sử
        lnLichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotroActivity.this, Lichsu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_exit);
            }
        });
        // animation cho lịch sử:
        lnLichsu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.startAnimation(animationAlpha);
                return false;
            }
        });


        // vào chức năng gọi
        lnPhone.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                Callphone();
            }
        });

        //khởi tạo animation khi chạm vào chức năng gọi gọi
        lnPhone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.startAnimation(animationAlpha);
                return false;
            }
        });

        // truy cập fanpage:
        lnFanpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFanpageWeb();
            }
        });

        // animation cho fanpage:
        lnFanpage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.startAnimation(animationAlpha);
                return false;
            }
        });

        // gửi tin nhắn
        lnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSendMess = new Intent();
                intentSendMess.setAction(Intent.ACTION_SENDTO); // mở ứng dụng nhắn tin của điện thoại
                intentSendMess.putExtra("sms_body", "");
                intentSendMess.setData(Uri.parse("sms:0964809640"));
                startActivity(intentSendMess);
            }
        });
        // animation cho lnMessage:
        lnMessage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.startAnimation(animationAlpha);
                return false;
            }
        });

        // gửi Mail:
        lnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        // animation cho lnMail:
        lnMail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.startAnimation(animationAlpha);
                return false;
            }
        });

        // load vào page web thời tiết
        lnThoitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotroActivity.this, Webview.class);
                Bundle bundle = new Bundle();
                bundle.putString("link", "https://www.accuweather.com/vi/vn/vietnam-weather");
                bundle.putString("tieude", "THỜI TIẾT");
                intent.putExtra("dulieu", bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_exit);
            }
        });
        // animation cho ln thời tiết:
        lnThoitiet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.startAnimation(animationAlpha);
                return false;
            }
        });

        // load vào web xổ số
        lnXoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotroActivity.this, Webview.class);
                Bundle bundle = new Bundle();
                bundle.putString("link", "https://xoso.com.vn/");
                bundle.putString("tieude", "XỔ SỐ");
                intent.putExtra("dulieu", bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_exit);
            }
        });
        // animation cho lnXoso
        lnXoso.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.startAnimation(animationAlpha);
                return false;
            }
        });

        //qua màn hình tiện tích máy tính
        lnMaytinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(HotroActivity.this,ManhinhMaytinh_HT.class);
                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_exit);
            }
        });
        // animation cho lnMaytinh
        lnMaytinh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.startAnimation(animationAlpha);
                return false;
            }
        });


    }
    // hàm chức năng:
    // chức năng gửi mail
    private void sendMail() {
        String email = "16520292@gm.uit.edu.vn";
        Intent intentSendMail = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
        intentSendMail.putExtra(Intent.EXTRA_SUBJECT, "phản hồi/ ý kiến");
        intentSendMail.putExtra(Intent.EXTRA_TEXT, "");

        try {
            startActivityForResult(Intent.createChooser(intentSendMail, "Chọn vào hình thức Mail để gửi phản hồi cho chúng tôi"), REQUEST_CODE_MAIL);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Người dùng chưa cài đặt ứng dụng soạn mail!", Toast.LENGTH_SHORT).show();
        }
    }

    // gửi lại phản hồi khi đã nhận / không nhận mail
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_MAIL) {
            if (resultCode == RESULT_OK) {
// tạo 1 alertdialog builder để gửi xác nhận đã nhận mail từ người phản hồi
                AlertDialog.Builder dialogConfirm = new AlertDialog.Builder(this);
                dialogConfirm.setTitle("Xác nhận Mail:");

                //truyền icon vào dialog:
                //   dialogConfirm.setIcon();
                dialogConfirm.setMessage("Chúng tôi đã nhận được thư!\n cảm ơn bạn đã gửi phản hồi về cho chúng tôi! :v");
                dialogConfirm.show();

            } else if (resultCode == RESULT_CANCELED) {
                // Sending was cancelled.

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // load vào fanpageWeb
    private void loadFanpageWeb() {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(HotroActivity.this); // tạo 1 alertdialog để tạo dialog xác nhận cho người dùng
        alertdialog.setTitle("Thông Báo!"); // đặt tiêu đề dialog
        alertdialog.setIcon(R.drawable.warning_icon);
//                đưa thêm ảnh warning
        alertdialog.setMessage("App đọc tin tức muốn bật trình duyệt và mở facebook?"); // nội dung trong dialog alert
        //dialog xác nhận mở trình duyệt
        alertdialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intentFanpage = new Intent();
                intentFanpage.setAction(Intent.ACTION_VIEW);
                intentFanpage.setData(Uri.parse("https://www.facebook.com/")); // đường link dẫn đến Fanpage
                startActivity(intentFanpage);
            }
        });
        alertdialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertdialog.show(); // mở alertdialog;

    }

    // hàm sử dụng để bật chế độ gọi
    private void Callphone() {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(HotroActivity.this); // tạo 1 alertdialog để tạo dialog xác nhận cho người dùng
        alertdialog.setTitle("Thông Báo!"); // đặt tiêu đề dialog
        alertdialog.setIcon(R.drawable.warning_icon);
//                đưa thêm ảnh warning
        alertdialog.setMessage("Chào, Chúng tôi muốn xác nhận cuộc gọi của bạn"); // nội dung trong dialog alert
        //dialog xác nhận gọi
        alertdialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {// nếu user nhấn nút có:
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Tạo 1 intent intentPhone để bật chế độ gọi đi
                Intent intentPhone = new Intent(Intent.ACTION_DIAL); // dùng ACTION CALL ĐỂ THAO TÁC GỌI
                intentPhone.setAction(Intent.ACTION_CALL);
                intentPhone.setData(Uri.parse("tel:0964809640"));

                //yêu cầu cho phép thao tác gọi
                if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intentPhone);
                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);
                }
            }
        });
        alertdialog.setNegativeButton("Không", new DialogInterface.OnClickListener() { // nút không bên hộp thoại người dùng
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // không làm gì cả
            }
        });
        alertdialog.show(); // mở alertdialog;
    }
}

