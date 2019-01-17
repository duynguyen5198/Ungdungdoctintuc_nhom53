package com.example.mypc.appdoctintuc_nhom53;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
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
import android.widget.Toast;

import static com.example.mypc.appdoctintuc_nhom53.MainActivity.Tieude;
import static com.example.mypc.appdoctintuc_nhom53.MainActivity.link;

public class Webview_doctintuc extends AppCompatActivity {

    WebView wv_doctintuc;
    LinearLayout lnQuaylaiDs;
    TextView tvTheloai_Ds;
    ImageView imgVHome1;
    ConstraintLayout manhinhwvdoctintuc;
    Button btsetSizett;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_doctintuc);

        // phần ánh xạ:
        wv_doctintuc = findViewById(R.id.wv_doctintucWV);
        lnQuaylaiDs = findViewById(R.id.lnquaylaiDs);
        imgVHome1 = findViewById(R.id.imgVHome1);
        tvTheloai_Ds = findViewById(R.id.tvTheloai_wvDs);
        btsetSizett = findViewById(R.id.btsetSizewvtt);

        // chạy animation cho màn hình wv đọc tin tức
        manhinhwvdoctintuc = findViewById(R.id.manhinhwvdoctintuc);

        AnimationDrawable animationDrawable = (AnimationDrawable) manhinhwvdoctintuc.getBackground();
        animationDrawable.setEnterFadeDuration(6000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();


        //phần hàm
        // set sự kiện cho button setsize:
        btsetSizett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Showmenu_setSize();
            }
        });

        lnQuaylaiDs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Webview_doctintuc.this,Dstintuc.class);
                Bundle bundle = new Bundle();
                bundle.putString("duonglink",link);
                bundle.putString("tieude",Tieude);
                intent.putExtra("dulieu1",bundle);

                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter,R.anim.animation_exit);
            }
        });

        imgVHome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Webview_doctintuc.this,MainActivity.class);

                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter2,R.anim.animation_exit2);
            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("dulieu2");
        String Tieude = bundle.getString("tieude");
        String link = bundle.getString("duongLink");


        tvTheloai_Ds.setText(Tieude);

        wv_doctintuc.loadUrl(link);
        wv_doctintuc.getSettings().setJavaScriptEnabled(true);
        wv_doctintuc.setWebViewClient(new WebViewClient());




    }
    private void Showmenu_setSize(){
        PopupMenu popupMenu_buttonsetSize = new PopupMenu(this,btsetSizett);
        popupMenu_buttonsetSize.getMenuInflater().inflate(R.menu.choncochu_wv,popupMenu_buttonsetSize.getMenu());
        popupMenu_buttonsetSize.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                WebSettings webSettings = wv_doctintuc.getSettings();
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
