package com.kadirbozkurt.qrcodegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.kadirbozkurt.qrcodegenerator.databinding.ActivityContactGeneratorBinding;

public class ContactGenerator extends AppCompatActivity {

    ActivityContactGeneratorBinding binding;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactGeneratorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        adMobs();
    }

    public void showQr(View view){
        String name = binding.nameText.getText().toString();
        String phone= binding.phoneText.getText().toString();
        String email= binding.mailText.getText().toString();
        String company = binding.companyText.getText().toString();
        String jobTitle= binding.jobTitleText.getText().toString();
        String adress = binding.adressText.getText().toString();

        if (name.trim().isEmpty()||phone.trim().isEmpty()){
            Toast.makeText(this, "You must fill name and phone number fields!", Toast.LENGTH_SHORT).show();
        }else{
            String qrText = "BEGIN:VCARD\nVERSION:3.0\nN:"+name+"\nTEL:"+phone+"\nEMAIL:"+email+"\nORG:"+company+"\nTITLE:"+jobTitle+"\nADR:"+adress+"END:VCARD";
            Intent intent = new Intent(ContactGenerator.this,ShowQr.class);
            intent.putExtra("qrText",qrText);
            startActivity(intent);
        }
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