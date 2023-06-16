package com.example.homecalcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;
import java.text.DecimalFormat;

public class CalcResultActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView housePrice;
    private TextView houseMeterPrice;
    private TextView tvFirstPaymentSumm;
    private TextView tvTotalDepositSumm;
    private TextView tvDepositEveryMonthSumm;
    private TextView tvRateEveryMonth;
    private TextView tvFinalHousePrice;
    private TextView finalHouseprice;
    private TextView tvTotalDeposit;
    private TextView tvTotalRate;
    private TextView totalDeposit;
    private TextView totalRate;
    private ImageButton btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_result);

        DecimalFormat df = new DecimalFormat("#.###");
        tvFirstPaymentSumm = findViewById(R.id.tvFirstPaymentSumm);
        housePrice = findViewById(R.id.housePrice);
        houseMeterPrice = findViewById(R.id.houseMeterPrice);
        tvTotalDepositSumm = findViewById(R.id.tvTotalDepositSumm);
        tvDepositEveryMonthSumm = findViewById(R.id.tvDepositEveryMonthSumm);
        tvRateEveryMonth = findViewById(R.id.tvRateEveryMonth);
        tvFinalHousePrice = findViewById(R.id.tvFinalHousePrice);
        tvTotalDeposit = findViewById(R.id.tvTotalDeposit);
        tvTotalRate = findViewById(R.id.tvTotalRate);
        btnShare = findViewById(R.id.btnShare);
        finalHouseprice = findViewById(R.id.finalHouseprice);
        totalDeposit = findViewById(R.id.totalDeposit);
        totalRate = findViewById(R.id.totalRate);


        double housePriceOfMeter = getIntent().getDoubleExtra("etCountMeter", 0.0);
        double priceOfHouse = getIntent().getDoubleExtra("etHomePrice", 0.0);
        double firstDeposit = getIntent().getDoubleExtra("etWallet", 0.0);
        double etRate = getIntent().getDoubleExtra("etProcentRate", 0.0);
        double etRateDate = getIntent().getDoubleExtra("etRateDate", 0.0);
        Log.d("simple_app_tag", "getting values from previous activity");

        double priceOfMeter = priceOfHouse / housePriceOfMeter;
        double remainDeposit = priceOfHouse - firstDeposit;
        double etFinalRate = etRate /  100 / 12;

        double depEveryMonth = remainDeposit / etRateDate;
        double depositRate = remainDeposit * etFinalRate;
        double tvTotalDep = depEveryMonth + depositRate;

        double finalHousePrice = firstDeposit + (tvTotalDep * etRateDate);
        double finalDeposit = depEveryMonth * etRateDate;
        double finalRate = depositRate * etRateDate;


        housePrice.setText(df.format(priceOfHouse));
        houseMeterPrice.setText(df.format(priceOfMeter));
        tvFirstPaymentSumm.setText(df.format(firstDeposit));
        tvTotalDepositSumm.setText(df.format(tvTotalDep));
        tvDepositEveryMonthSumm.setText(df.format(depEveryMonth));
        tvRateEveryMonth.setText(df.format(depositRate));
        tvFinalHousePrice.setText(df.format(finalHousePrice));
        tvTotalDeposit.setText(df.format(finalDeposit));
        tvTotalRate.setText(df.format(finalRate));
        Log.d("simple_app_tag", "Output the information on the screen");

        btnShare.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnShare){
            Log.i("home_calc_app_tag", "Click to share button");
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            String finalPrice = finalHouseprice.getText().toString() + ": " + tvFinalHousePrice.getText().toString()  + "\n" +
                    totalDeposit.getText().toString() + ": " + tvTotalDeposit.getText().toString() + "\n" +
                    totalRate.getText().toString() + ": " + tvTotalRate.getText().toString();
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, finalPrice);
            startActivity(Intent.createChooser(shareIntent, "Поделиться результатом"));
        }
    }
}