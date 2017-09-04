package com.example.cuonghx2709.calculator;

import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getName();
    private Button button_0;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_7;
    private Button button_8;
    private Button button_9;
    private RelativeLayout relaytiveLayout_x;
    private RelativeLayout relativeLayout_add;
    private RelativeLayout relaytiveLayout_subtract;
    private RelativeLayout relaytiveLayout_division;

    private Button button_del;
    private Button button_final;
    private Button button_change;
    private Button button_percent;
    private Button button_;

    private TextView textView;

    private double number = 0;
    private double cacheNumber = 0;
    private int status;
    private boolean change = false;
    private boolean error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    private void init() {
        status = 0;
        button_0 = (Button) findViewById(R.id.bt_0);
        button_1 = (Button) findViewById(R.id.bt_1);
        button_2 = (Button) findViewById(R.id.bt_2);
        button_3 = (Button) findViewById(R.id.bt_3);
        button_4 = (Button) findViewById(R.id.bt_4);
        button_5 = (Button) findViewById(R.id.bt_5);
        button_6 = (Button) findViewById(R.id.bt_6);
        button_7 = (Button) findViewById(R.id.bt_7);
        button_8 = (Button) findViewById(R.id.bt_8);
        button_9 = (Button) findViewById(R.id.bt_9);
        relaytiveLayout_x = (RelativeLayout) findViewById(R.id.rl_x);
        relativeLayout_add = (RelativeLayout) findViewById(R.id.rl_add);
        relaytiveLayout_subtract = (RelativeLayout) findViewById(R.id.rl_subtract);
        relaytiveLayout_division = (RelativeLayout) findViewById(R.id.rl_division);
        button_del = (Button) findViewById(R.id.bt_del);
        button_final = (Button) findViewById(R.id.bt_final);
        button_change = (Button) findViewById(R.id.bt_change);
        button_percent = (Button) findViewById(R.id.bt_percent);
        button_ = (Button) findViewById(R.id.bt_);

        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_.setOnClickListener(this);
        relaytiveLayout_x.setOnClickListener(this);
        relativeLayout_add.setOnClickListener(this);
        relaytiveLayout_subtract.setOnClickListener(this);
        relaytiveLayout_division.setOnClickListener(this);
        button_del.setOnClickListener(this);
        button_final.setOnClickListener(this);
        button_change.setOnClickListener(this);
        button_percent.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.tv_number);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_0:
                changeNumber(0);

                break;
            case R.id.bt_1:
                changeNumber(1);
                break;
            case R.id.bt_2:
                changeNumber(2);
                break;
            case R.id.bt_3:
                changeNumber(3);
                break;
            case R.id.bt_4:
                changeNumber(4);
                break;
            case R.id.bt_5:
                changeNumber(5);
                break;
            case R.id.bt_6:
                changeNumber(6);
                break;
            case R.id.bt_7:
                changeNumber(7);
                break;
            case R.id.bt_8:
                changeNumber(8);
                break;
            case R.id.bt_9:
                changeNumber(9);
                break;
            case R.id.rl_add:
                count(1);
                break;
            case R.id.rl_subtract:
                count(2);
                break;
            case R.id.rl_x:
                count(3);
                break;
            case R.id.rl_division:
                count(4);
                break;
            case R.id.bt_del:
                error = false;
                number = 0;
                cacheNumber = 0;
                status = 0;
                break;
            case R.id.bt_final:
                if (status != 0) {
                    change = false;
                    count(0);
                }
                break;
            case R.id.bt_change:
                number = -number;
                break;
            case R.id.bt_percent:
                number = number / 100 ;
                cacheNumber = number;
                break;
            case R.id.bt_:
                number = 0;
                cacheNumber = 0;
                status = 0;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }

        changeColor();


        if (error){
            textView.setText("error");
        }else {
            int check = (int) number;

            if (number - check == 0)
                textView.setText(String.format("%.0f", number));
            else{
                textView.setText(String.format("%.2f", number));
            }
        }

    }

    private void changeNumber(int n) {
        if (change){
            number = 0;
            change = false;
        }else if (error){
            error = false;
            number = 0;
        }
        number  = number * 10  + n;
    }

    private void changeColor() {
        switch (status){
            case 1:
                reset();
                relativeLayout_add.setBackgroundColor(Color.GRAY);
                break;
            case 2:
                reset();
                relaytiveLayout_subtract.setBackgroundColor(Color.GRAY);
                break;
            case 3:
                reset();
                relaytiveLayout_x.setBackgroundColor(Color.GRAY);
                break;
            case 4:
                reset();
                relaytiveLayout_division.setBackgroundColor(Color.GRAY);
                break;
            default:
                reset();
                break;
        }
    }

    private void reset() {
        relativeLayout_add.setBackgroundColor(Color.WHITE);
        relaytiveLayout_subtract.setBackgroundColor(Color.WHITE);
        relaytiveLayout_x.setBackgroundColor(Color.WHITE);
        relaytiveLayout_division.setBackgroundColor(Color.WHITE);
    }


    private void  count(int n){
        if (change){
            status = n;
        }else {
            change = true;
            if (status == 0) {
                cacheNumber = number;
                status = n;
            } else {
                if (status == 1) {
                    number = number + cacheNumber;
                    cacheNumber = number;
                    status = n;
                } else if (status == 2) {
                    number = cacheNumber - number;
                    cacheNumber = number;
                    status = n;
                } else if (status == 3) {
                    number = cacheNumber * number;
                    cacheNumber = number;
                    status = n;
                } else if (status == 4) {
                    if (number != 0) {
                        number = cacheNumber / number;
                        cacheNumber = number;
                        status = n;
                    } else {
                        error = true;
                    }
                }
            }
        }
    }

}
