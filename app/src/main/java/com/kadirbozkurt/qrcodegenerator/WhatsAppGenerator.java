package com.kadirbozkurt.qrcodegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.kadirbozkurt.qrcodegenerator.databinding.ActivityWhatsAppGeneratorBinding;

public class WhatsAppGenerator extends AppCompatActivity {
    ActivityWhatsAppGeneratorBinding binding;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWhatsAppGeneratorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        adMobs();
    }
    public void showQr(View view){
        String qrText ="whatsapp://send?phone="+binding.editText.getText().toString().replace("+","");
        Intent intent = new Intent(WhatsAppGenerator.this,ShowQr.class);
        intent.putExtra("qrText",qrText);
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