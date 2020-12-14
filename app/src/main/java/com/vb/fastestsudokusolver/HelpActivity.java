package com.vb.fastestsudokusolver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class HelpActivity extends AppCompatActivity {

    private AdView adViewTop;
    private AdView adViewBottom;

    /*private com.facebook.ads.AdView fbBannerAdTop;
    private com.facebook.ads.AdView getFbBannerAdBottom;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        AdInitializer initializer = new AdInitializer();
        initializer.initHelp(this);

        /*// Facebook Instantiate an AdView view
        fbBannerAdTop = new com.facebook.ads.AdView(this, "199431437548527_199839130841091", AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainerTop = (LinearLayout) findViewById(R.id.help_banner_top);
        // Add the ad view to your activity layout
        adContainerTop.addView(fbBannerAdTop);
        // Request an ad
        fbBannerAdTop.loadAd();

        // Facebook Instantiate an AdView view
        getFbBannerAdBottom = new com.facebook.ads.AdView(this, "199431437548527_199839130841091", AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainerBottom = (LinearLayout) findViewById(R.id.help_banner_bottom);
        // Add the ad view to your activity layout
        adContainerBottom.addView(getFbBannerAdBottom);
        // Request an ad
        getFbBannerAdBottom.loadAd();*/

    }
}
