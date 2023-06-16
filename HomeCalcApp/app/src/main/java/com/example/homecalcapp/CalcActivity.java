package com.example.homecalcapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CalcActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etCountMeter;
    private EditText etHomePrice;
    private EditText etWallet;
    private EditText etProcentRate;
    private EditText etRateDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        etCountMeter = findViewById(R.id.etCountMeter);
        etHomePrice = findViewById(R.id.etHomePrice);
        etWallet = findViewById(R.id.etWallet);
        etProcentRate = findViewById(R.id.etProcentRate);
        etRateDate = findViewById(R.id.etRateDate);

        Button btnCalc = findViewById(R.id.btnCalc);
        Button btnBack = findViewById(R.id.btnBack);

        btnCalc.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnBack){
            Log.i("home_calc_app_tag", "Click to back button");
            finish();
        } else if (v.getId() == R.id.btnCalc) {
            try {
                Log.i("home_calc_app_tag", "Click to Calc button");
                String etCountMet = etCountMeter.getText().toString();
                String valPrice = etHomePrice.getText().toString();
                String wallet = etWallet.getText().toString();
                String rate = etProcentRate.getText().toString();
                String rateDate = etRateDate.getText().toString();

                Double etMeterCount = Double.parseDouble(etCountMet);
                Double etHomePrice = Double.parseDouble(valPrice);
                Double etWallet = Double.parseDouble(wallet);
                Double etProcentRate = Double.parseDouble(rate);
                Double etRateDate = Double.parseDouble(rateDate);

                if(etMeterCount == 0 || etHomePrice == 0 || etWallet == 0 || etProcentRate == 0 || etRateDate == 0 ) {
                    Toast.makeText(this, "Введите действительные значения!", Toast.LENGTH_SHORT).show();
                    Log.w("home_calc_app_tag", "Invalid values entered");
                }else {
                    if(etMeterCount > 1000 || etHomePrice > 1000000000 || etWallet > 1000000000 || etProcentRate > 100 || etRateDate > 1200){
                        Toast.makeText(this, "Введите действительные значения!", Toast.LENGTH_SHORT).show();
                        Log.w("home_calc_app_tag", "The values are too large");
                    }else{
                        if(etHomePrice <= etWallet){
                            Toast.makeText(this, "Цена квартиры не может быть меньше вашей суммы!", Toast.LENGTH_SHORT).show();
                            Log.w("home_calc_app_tag", "Invalid values entered");
                        }else{
                            Intent calcResultIntent = new Intent(getApplicationContext(), CalcResultActivity.class);
                            calcResultIntent.putExtra("etCountMeter", etMeterCount);
                            calcResultIntent.putExtra("etHomePrice", etHomePrice);
                            calcResultIntent.putExtra("etWallet", etWallet);
                            calcResultIntent.putExtra("etProcentRate", etProcentRate);
                            calcResultIntent.putExtra("etRateDate", etRateDate);
                            startActivity(calcResultIntent);
                            Log.i("home_calc_app_tag", "Start CalcResultActivity");
                        }
                    }
                }
            }catch (Exception ex){
                Intent intent = new Intent(this, CalcResultActivity.class);
                intent.putExtra("etCountMeter", "Error!");
                intent.putExtra("etHomePrice", "Error!");
                intent.putExtra("etWallet", "Error!");
                intent.putExtra("etProcentRate", "Error!");
                intent.putExtra("etRateDate", "Error!");
                Toast.makeText(this, "Заполните все строки!", Toast.LENGTH_SHORT).show();
                Log.w("home_calc_app_tag", "Empty lines left");
            }
        }
    }

}