package com.example.mypc.appdoctintuc_nhom53;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

public class Webview extends AppCompatActivity {
WebView webviewHotro;
TextView tvtieudeHotro;
LinearLayout lnquaylaihotro;
ImageView imgvHome;
ConstraintLayout manhinhwv;
Button btsetSizewv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        // chạy animation cho màn hình webview
        manhinhwv = findViewById(R.id.manhinhwv);

        AnimationDrawable animationDrawable = (AnimationDrawable) manhinhwv.getBackground();
        animationDrawable.setEnterFadeDuration(6000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        //phần ánh xạ
        tvtieudeHotro = findViewById(R.id.tvTitleWV);
        lnquaylaihotro = findViewById(R.id.lnBacktoHotroWV);
        imgvHome = findViewById(R.id.imgvHomeWV);
        webviewHotro = findViewById(R.id.wv_hotroWV);
        btsetSizewv = findViewById(R.id.btsetSizewv);

        //phần hàm:
        btsetSizewv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Showmenu_setSize();
            }
        });

        lnquaylaihotro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Webview.this,HotroActivity.class);

                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter1,R.anim.animation_exit1);
            }
        });

        imgvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Webview.this,MainActivity.class);

                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter2,R.anim.animation_exit2);
            }
        });

        // phần webview:
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("dulieu");
        String Tieude = bundle.getString("tieude");
        String link = bundle.getString("link");

        tvtieudeHotro.setText(Tieude);

        webviewHotro.loadUrl(link);
        webviewHotro.getSettings().setJavaScriptEnabled(true);
        webviewHotro.setWebViewClient(new WebViewClient());






    }
    private void Showmenu_setSize(){
        PopupMenu popupMenu_buttonsetSize = new PopupMenu(this,btsetSizewv);
        popupMenu_buttonsetSize.getMenuInflater().inflate(R.menu.choncochu_wv,popupMenu_buttonsetSize.getMenu());
        popupMenu_buttonsetSize.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                WebSettings webSettings = webviewHotro.getSettings();
                webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
                webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

                switch (item.getItemId())
                {

                    case R.id.sizeNho:
                        webSettings.setTextZoom(60);
                        break;
                    case R.id.sizeVua:
                        webSettings.setTextZoom(100);
                        break;
                    case R.id.sizeLon:
                        webSettings.setTextZoom(140);
                        break;

                }

                return false;
            }
        });


        popupMenu_buttonsetSize.show();
    }
}
