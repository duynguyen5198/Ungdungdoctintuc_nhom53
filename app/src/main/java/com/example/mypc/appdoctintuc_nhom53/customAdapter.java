package com.example.mypc.appdoctintuc_nhom53;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

// class này để custom listview


public class customAdapter extends ArrayAdapter<Doctintuc> {

    public customAdapter(Context context, int resource, List<Doctintuc> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.custom_list_layout, null);
        }
        Doctintuc p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txttitle =  view.findViewById(R.id.tvTieude);
            txttitle.setText(p.getTitle());


            ImageView imageView = view.findViewById(R.id.imgvHinhanh);
            //sử dụng thư viện picasso để load ảnh trong android:
            if(p.image == null){
                Picasso.get().load("http://static.ilike.com.vn:8880/uploads/2017/12/anh-bia-nhung-giong-meo-dep-nhat-the-gioi.jpg").into(imageView);
            }else{
            Picasso.get().load(p.image).into(imageView);}


        }
        return view;
    }

}
