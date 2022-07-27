package com.kadirbozkurt.qrcodegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.kadirbozkurt.qrcodegenerator.databinding.ActivityWifiGeneratorBinding;


public class WifiGenerator extends AppCompatActivity {
        ActivityWifiGeneratorBinding binding;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWifiGeneratorBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);
        adMobs();

        binding.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    binding.toggleButton2.setChecked(false);
                    binding.toggleButton3.setChecked(false);
                }

            }
        });
        binding.toggleButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    binding.toggleButton.setChecked(false);
                    binding.toggleButton3.setChecked(false);
                }
            }
        });
        binding.toggleButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    binding.toggleButton2.setChecked(false);
                    binding.toggleButton.setChecked(false);
                }
            }
        });
    }
    public void showQr(View view){
        String qrText ="";
        Intent intent = new Intent(WifiGenerator.this,ShowQr.class);


        if (binding.toggleButton.isChecked()){

            qrText="WIFI:S:"+binding.editText.getText().toString()+";T:WPA;P:"+binding.editText3.getText().toString()+";H:false;";
            if (binding.switch1.isChecked()){
                qrText="WIFI:S:"+binding.editText.getText().toString()+";T:WPA;P:"+binding.editText3.getText().toString()+";H:true;";

            }
            intent.putExtra("qrText",qrText);
            startActivity(intent);

        }else if (binding.toggleButton2.isChecked()){

            qrText="WIFI:S:"+binding.editText.getText().toString()+";T:WEP;P:"+binding.editText3.getText().toString()+";H:false;";
            if (binding.switch1.isChecked()){
                qrText="WIFI:S:"+binding.editText.getText().toString()+";T:WEP;P:"+binding.editText3.getText().toString()+";H:true;";
            }
            intent.putExtra("qrText",qrText);
            startActivity(intent);
        }else if (binding.toggleButton3.isChecked()){

            qrText="WIFI:S:"+binding.editText.getText().toString()+";P:;H:false;";
            if (binding.switch1.isChecked()){
                qrText="WIFI:S:"+binding.editText.getText().toString()+";P:;H:true;";
            }
            intent.putExtra("qrText",qrText);
            startActivity(intent);
        }else{
            Toast.makeText(WifiGenerator.this, "Please select a password type!", Toast.LENGTH_SHORT).show();
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