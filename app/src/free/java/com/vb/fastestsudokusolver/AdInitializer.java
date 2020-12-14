package com.vb.fastestsudokusolver;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AdInitializer {

    private AdView adViewTop;
    private AdView adViewBottom;

    public void initHome(Activity context) {
        adViewTop = (AdView) context.findViewById(R.id.adViewHomeTop);
        AdRequest adRT = new AdRequest.Builder().build();
        adViewTop.loadAd(adRT);

        adViewBottom = (AdView) context.findViewById(R.id.adViewHomeButton);
        AdRequest adRB = new AdRequest.Builder().build();
        adViewBottom.loadAd(adRB);
    }

    public void initHelp(Activity context) {
        adViewTop = (AdView) context.findViewById(R.id.adViewHelpTop);
        AdRequest adRT = new AdRequest.Builder().build();
        adViewTop.loadAd(adRT);

        adViewBottom = (AdView) context.findViewById(R.id.adViewHelpBottom);
        AdRequest adRB = new AdRequest.Builder().build();
        adViewBottom.loadAd(adRB);
    }

}
