package com.kadirbozkurt.qrcodegenerator;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.kadirbozkurt.qrcodegenerator.databinding.ActivityLinkGeneratorBinding;

public class LinkGenerator extends AppCompatActivity {
    ActivityLinkGeneratorBinding binding;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLinkGeneratorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        adMobs();

        binding.textView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(getText(binding.editText.getText().toString(),binding.textView10.getText().toString()));
            }
        });
        binding.textView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(getText(binding.editText.getText().toString(),binding.textView11.getText().toString()));
            }
        });
        binding.textView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(getText(binding.editText.getText().toString(),binding.textView12.getText().toString()));
            }
        });
        binding.textView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(getText(binding.editText.getText().toString(),binding.textView13.getText().toString()));
            }
        });
        binding.textView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(getText(binding.editText.getText().toString(),binding.textView14.getText().toString()));
            }
        });
        binding.textView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(getText(binding.editText.getText().toString(),binding.textView15.getText().toString()));
            }
        });

    }
    public String getText(String strIn, String strOut){
        String result="";
        result+=strIn+strOut;
        return result;
    }
    public void showQr(View view){
        Intent intent = new Intent(LinkGenerator.this,ShowQr.class);
        intent.putExtra("qrText",binding.editText.getText().toString());
        startActivity(intent);
    }
    public void adMobs(){
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}