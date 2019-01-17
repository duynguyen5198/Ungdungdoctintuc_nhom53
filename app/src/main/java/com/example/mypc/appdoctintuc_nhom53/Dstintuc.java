package com.example.mypc.appdoctintuc_nhom53;

import android.app.Application;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dstintuc extends AppCompatActivity {
    TextView tvTheloai;
    ListView lvTieude;
    LinearLayout lnQuayvemain;
    ArrayList<Doctintuc> mangdocbao;
    customAdapter customAdapter;
    RelativeLayout manhinhdanhsach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dstintuc);

        // chạy animation cho màn hình danh sách:
        manhinhdanhsach= findViewById(R.id.manhinhdanhsach);
        AnimationDrawable animationDrawable = (AnimationDrawable) manhinhdanhsach.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        lnQuayvemain = findViewById(R.id.lnQuayvemain);

        lvTieude = findViewById(R.id.lvDanhsachtin);

        tvTheloai = findViewById(R.id.tvTheloai);


        mangdocbao = new ArrayList<>();
        final Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("dulieu1");
        final String Tieude = bundle.getString("tieude");
        String link = bundle.getString("duonglink");

        tvTheloai.setText(Tieude);

        if(link!=null){
        new ReadRss().execute(link);
        }

     //    set sự kiện click cho các item:
        lvTieude.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // truyền dữ liệu qua webview_doctintuc
                Intent intent = new Intent(Dstintuc.this,Webview_doctintuc.class);
                Bundle bundle = new Bundle();
                bundle.putString("duongLink",mangdocbao.get(position).link);
                bundle.putString("tieude",Tieude);
                intent.putExtra("dulieu2",bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter1,R.anim.animation_exit1);
                }
        });
        lvTieude.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Dstintuc.this,Lichsu.class);
                overridePendingTransition(R.anim.animation_enter,R.anim.animation_exit);
                intent.putExtra("dulieu3", mangdocbao.get(position));

                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter,R.anim.animation_exit);

                return false;
            }
        });


        lnQuayvemain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dstintuc.this,MainActivity.class);

                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter,R.anim.animation_exit);
            }
        });

    }
    private class ReadRss extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            // đọc chuỗi liên tục, có thể append cho nó
            StringBuilder stringBuilder = new StringBuilder();
            try {
                // khởi tạo đường dẫn vào
                URL url = new URL(strings[0]);
                // truyền vào inputStream
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());


                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                //biến chứa dữ liệu:
                String line = "";
                // đọc từng dòng cho tới khi line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);

                }
                // đóng biến đọc chuỗi
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // trả về chuỗi stringBuilder về chuyển qua toString();
            return stringBuilder.toString();


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            // tạo đối tượng xmldomparser
            XMLDOMParser parser = new XMLDOMParser();

            // để vào trong document để xml có thể phân tích được
            Document document = parser.getDocument(s);

            // gọi nodelist để chứa danh sách các item: . getElmBy... để đọc vào từng thẻ item;
            try {
                NodeList nodeList = document.getElementsByTagName("item");
                // gọi nodelist để chứa ảnh trong cdata
                NodeList nodeListDesciption = document.getElementsByTagName("description");
                NodeList title = document.getElementsByTagName("title");
                NodeList linkN = document.getElementsByTagName("link");

//            khai báo 1 chuỗi rỗng để truyền vào giá trị
            String tieude = "";
            String links = "";
            String hinhanh = null;
            //           String pubDate = "";
//
            for(int i = 1 ; i < nodeList.getLength();i++) {
                // đọc từng phần tử trong item[i];
                Element element = (Element) nodeList.item(i);
                String cdata = nodeListDesciption.item(i).getTextContent();
                // thẻ pattern để đọc src trongCdata hình ảnh từ html,
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);  // kiểm tra nội dung trùng về những biến có trùng khớp ? sẽ nhóm vào 1 group có trong biến của hình ảnh
                // kiểm tra = hàm if nếu thấy sẽ đưa vào 1 group;
                if (matcher.find()) {
                    hinhanh = matcher.group(1);
                }
                // đọc từng element trong item, sử dụng để nối chuỗi

                Element Noidung = (Element) title.item(i+1);
                Element link = (Element) linkN.item(i+1);
                tieude = getCharacterDataFromElement(Noidung);
                links = getCharacterDataFromElement(link);

                mangdocbao.add(new Doctintuc(tieude,links,hinhanh));
            }
            customAdapter = new customAdapter(Dstintuc.this,android.R.layout.simple_list_item_1,mangdocbao);
            lvTieude.setAdapter(customAdapter);
            customAdapter.notifyDataSetChanged();
            }catch (NullPointerException e ){
                Toast.makeText(Dstintuc.this, "Xin vui lòng kiểm tra lại đường truyền", Toast.LENGTH_SHORT).show();
                mangdocbao.add(new Doctintuc("lỗi đường truyền!","",null));
            }

        }

    }
    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }

}
