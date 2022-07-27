package com.kadirbozkurt.qrcodegenerator;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.kadirbozkurt.qrcodegenerator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    //uygulama kimliği: ca-app-pub-8301793912429911~9704924386
    //banner reklam id:ca-app-pub-8301793912429911/5130991226
    //geçiş reklam id: ca-app-pub-8301793912429911/8889454590

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        adMobs();
        adMob2();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adMob2();
    }

    public void textGenerate(View view){
        Intent intent = new Intent(MainActivity.this,TextGenerator.class);
        startActivity(intent);


    }
    public void linkGenerate(View view){
        Intent intent = new Intent(MainActivity.this,LinkGenerator.class);
        startActivity(intent);
        showInterstitial();

    }

    public void phoneGenerate(View view){
        Intent intent = new Intent(MainActivity.this,PhoneGenerator.class);
        startActivity(intent);
        showInterstitial();

    }
    public void whatsAppGenerate(View view){
        Intent intent = new Intent(MainActivity.this,WhatsAppGenerator.class);
        startActivity(intent);
        showInterstitial();

    }
    public void instagramGenerate(View view){
        Intent intent = new Intent(MainActivity.this,InstagramGenerator.class);
        startActivity(intent);
        showInterstitial();

    }
    public void contactGenerate(View view){
        Intent intent = new Intent(MainActivity.this,ContactGenerator.class);
        startActivity(intent);
        showInterstitial();

    }
    public void smsGenerate(View view){
        Intent intent = new Intent(MainActivity.this,SmsGenerator.class);
        startActivity(intent);
        showInterstitial();

    }
    public void wifiGenerate(View view){
        Intent intent = new Intent(MainActivity.this,WifiGenerator.class);
        startActivity(intent);
        showInterstitial();

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
    public void adMob2(){

        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                this,
                "ca-app-pub-8301793912429911/8889454590",
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        MainActivity.this.mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        MainActivity.this.mInterstitialAd = null;
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        MainActivity.this.mInterstitialAd = null;
                                        Log.d("TAG", "The ad failed to show.");
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                        // Called when fullscreen content is shown.
                                        Log.d("TAG", "The ad was shown.");
                                    }
                                });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;

                        String error =
                                String.format(
                                        "domain: %s, code: %d, message: %s",
                                        loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());

                    }
                });
    }
    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd != null) {
            mInterstitialAd.show(this);
        } else {

        }
    }
}