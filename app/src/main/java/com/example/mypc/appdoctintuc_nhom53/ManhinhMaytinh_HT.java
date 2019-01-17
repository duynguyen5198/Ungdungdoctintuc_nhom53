package com.example.mypc.appdoctintuc_nhom53;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManhinhMaytinh_HT extends AppCompatActivity implements View.OnClickListener{
    // khai báo biến để ánh xạ
     EditText edtNumber;
     TextView tvResult;

     Button btnNumber1;
     Button btnNumber2;
     Button btnNumber3;
     Button btnNumber4;
     Button btnNumber5;
     Button btnNumber6;
     Button btnNumber7;
     Button btnNumber8;
     Button btnNumber9;
     Button btnNumber0;

     Button btnCong;
     Button btnTru;
     Button btnNhan;
     Button btnChia;

     Button btnPoint;
     Button btnResult;
     Button btnClear;
     Button btnClearAll;
    // màn hình animation
    RelativeLayout manhinhmaytinh;


    LinearLayout lnQuayveHotro_maytinh;


    private final String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh_maytinh__ht);
        manhinhmaytinh = findViewById(R.id.manhinhmaytinh);
// chạy aniamtion cho màn hình máy  tính
        AnimationDrawable animationDrawable = (AnimationDrawable) manhinhmaytinh.getBackground();
        animationDrawable.setEnterFadeDuration(6000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
        // quay về hỗ trợ màn hình máy tính: (ánh xạ + sự kiện)
        lnQuayveHotro_maytinh = findViewById(R.id.lnBacktoHotro_maytinh);
        lnQuayveHotro_maytinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManhinhMaytinh_HT.this,HotroActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter1, R.anim.animation_exit1);
            }
        });


        initWidget();
        setEventClickViews();
    }
    public void initWidget() {

        edtNumber = findViewById(R.id.edt_input);
        tvResult = findViewById(R.id.tv_result);

        btnNumber1 = findViewById(R.id.btnNumber1);
        btnNumber2 = findViewById(R.id.btnNumber2);
        btnNumber3 = findViewById(R.id.btnNumber3);
        btnNumber4 = findViewById(R.id.btnNumber4);
        btnNumber5 = findViewById(R.id.btnNumber5);
        btnNumber6 = findViewById(R.id.btnNumber6);
        btnNumber7 = findViewById(R.id.btnNumber7);
        btnNumber8 = findViewById(R.id.btnNumber8);
        btnNumber9 = findViewById(R.id.btnNumber9);
        btnNumber0 = findViewById(R.id.btnNumber0);

        btnCong =  findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        btnNhan =  findViewById(R.id.btnNhan);
        btnChia =  findViewById(R.id.btnChia);

        btnPoint = findViewById(R.id.btnPoint);
        btnClear = findViewById(R.id.btnClear);
        btnClearAll = findViewById(R.id.btnClearAll);
        btnResult = findViewById(R.id.btnResult);
    }
    public void setEventClickViews() {
        btnNumber1.setOnClickListener(this);
        btnNumber2.setOnClickListener(this);
        btnNumber3.setOnClickListener(this);
        btnNumber4.setOnClickListener(this);
        btnNumber5.setOnClickListener(this);
        btnNumber6.setOnClickListener(this);
        btnNumber7.setOnClickListener(this);
        btnNumber8.setOnClickListener(this);
        btnNumber9.setOnClickListener(this);
        btnNumber0.setOnClickListener(this);

        btnCong.setOnClickListener(this);
        btnTru.setOnClickListener(this);
        btnNhan.setOnClickListener(this);
        btnChia.setOnClickListener(this);

        btnPoint.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnClearAll.setOnClickListener(this);
        btnResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNumber0:
                //TO DO
                edtNumber.append("0");
                break;
            case R.id.btnNumber1:
                //TO DO
                edtNumber.append("1");
                break;
            case R.id.btnNumber2:
                //TO DO
                edtNumber.append("2");
                break;
            case R.id.btnNumber3:
                edtNumber.append("3");
                //TO DO
                break;
            case R.id.btnNumber4:
                //TO DO
                edtNumber.append("4");
                break;
            case R.id.btnNumber5:
                //TO DO
                edtNumber.append("5");
                break;
            case R.id.btnNumber6:
                //TO DO
                edtNumber.append("6");
                break;
            case R.id.btnNumber7:
                //TO DO
                edtNumber.append("7");
                break;
            case R.id.btnNumber8:
                //TO DO
                edtNumber.append("8");
                break;
            case R.id.btnNumber9:
                //TO DO
                edtNumber.append("9");
                break;
            case R.id.btnCong:
                //TO DO
                edtNumber.append("+");
                break;
            case R.id.btnTru:
                //TO DO
                edtNumber.append("-");
                break;
            case R.id.btnNhan:
                //TO DO
                edtNumber.append("*");
                break;
            case R.id.btnChia:
                //TO DO
                edtNumber.append("/");
                break;

            case R.id.btnPoint:
                //TO DO
                edtNumber.append(".");
                break;
            case R.id.btnClear:
                //TO DO
                BaseInputConnection textFieldInputConnection = new BaseInputConnection(edtNumber, true);
                textFieldInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                break;
            case R.id.btnClearAll:
                //TO DO
                edtNumber.setText("");
                tvResult.setText("");

                break;
            case R.id.btnResult:
                //To do
                DecimalFormat df = new DecimalFormat("###.#######");
                double result = 0;
                addOperation(edtNumber.getText().toString());
                addNumber(edtNumber.getText().toString());
                // Thuật toán tính toán biểu thức
                if(arrOperation.size()>=arrNumber.size() ||arrOperation.size()<1){
                    tvResult.setText("Lỗi định dạng");
                }else {
                    for (int i = 0; i < (arrNumber.size() - 1); i++) {
                        switch (arrOperation.get(i)) {
                            case "+":
                                if (i == 0) {
                                    result = arrNumber.get(i) + arrNumber.get(i + 1);
                                } else {
                                    result = result + arrNumber.get(i + 1);
                                }
                                break;
                            case "-":
                                if (i == 0) {
                                    result = arrNumber.get(i) - arrNumber.get(i + 1);
                                } else {
                                    result = result - arrNumber.get(i + 1);
                                }
                                break;
                            case "*":
                                if (i == 0) {
                                    result = arrNumber.get(i) * arrNumber.get(i + 1);
                                } else {
                                    result = result * arrNumber.get(i + 1);
                                }
                                break;
                            case "/":
                                if (i == 0) {
                                    result = arrNumber.get(i) / arrNumber.get(i + 1);
                                } else {
                                    result = result / arrNumber.get(i + 1);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    tvResult.setText(df.format(result) + "");
                }

                // tvResult.setText(df.format(result) + "");
                //  edtNumber.setText("");
                //  result = 0;
//        }
        }
    }
    //Mảng chứa các phép tính +, - , x, /
    public ArrayList<String> arrOperation;
    //Mảng chứa các số
    public ArrayList<Double> arrNumber;

    //Lấy tất cả các phép tính lưu vào mảng arrOperation
    public int addOperation(String input) {
        arrOperation = new ArrayList<>();

        char[] cArray = input.toCharArray();
        for (int i = 0; i < cArray.length; i++) {
            switch (cArray[i]) {
                case '+':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '-':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '*':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '/':
                    arrOperation.add(cArray[i] + "");
                    break;
                default:
                    break;
            }
        }
        return 0;
    }
    //Lấy tất cả các số lưu vào mảng arrNumber
    public void addNumber(String stringInput) {
        arrNumber = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while(matcher.find()){
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }
}
