package com.vb.fastestsudokusolver;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    int i, j, pX = 0, pY = 0, x, y;
    private Button B[][], cBtn, pBtn, iB[], btnClr;
    private InterstitialAd mInterstitialAd;

    /*private com.facebook.ads.AdView fbBannerAdTop;
    private com.facebook.ads.AdView getFbBannerAdBottom;
    private com.facebook.ads.InterstitialAd mInterstitialAd;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(BuildConfig.FLAVOR == "free") {
            MobileAds.initialize(this, "ca-app-pub-8989913366273345~1437267312");

            AdInitializer initializer = new AdInitializer();
            initializer.initHome(this);

            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId("ca-app-pub-8989913366273345/2089742119");

            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    requestNewInterstitial();
                }
            });

            requestNewInterstitial();

            /*// Facebook Instantiate an AdView view
            fbBannerAdTop = new com.facebook.ads.AdView(this, "199431437548527_199431677548503", AdSize.BANNER_HEIGHT_50);
            // Find the Ad Container
            LinearLayout adContainerTop = (LinearLayout) findViewById(R.id.home_banner_top);
            // Add the ad view to your activity layout
            adContainerTop.addView(fbBannerAdTop);
            // Request an ad
            fbBannerAdTop.loadAd();

            // Facebook Instantiate an AdView view
            getFbBannerAdBottom = new com.facebook.ads.AdView(this, "199431437548527_199838550841149", AdSize.BANNER_HEIGHT_50);
            // Find the Ad Container
            LinearLayout adContainerBottom = (LinearLayout) findViewById(R.id.home_banner_bottom);
            // Add the ad view to your activity layout
            adContainerBottom.addView(getFbBannerAdBottom);
            // Request an ad
            getFbBannerAdBottom.loadAd();

            mInterstitialAd = new com.facebook.ads.InterstitialAd(this, "199431437548527_199838794174458");

            mInterstitialAd.setAdListener(new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    mInterstitialAd.loadAd();
                }

                @Override
                public void onError(Ad ad, AdError adError) {

                }

                @Override
                public void onAdLoaded(Ad ad) {

                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });

            mInterstitialAd.loadAd();*/
        }

        B = new Button[9][9];

        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                final int ii = i, jj = j;
                int resID = getResources().getIdentifier("btn" + i + j, "id", getPackageName());
                B[i][j] = (Button) findViewById(resID);
                B[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pBtn = cBtn;
                        cBtn = B[ii][jj];
                        x = ii;
                        y = jj;
                        pBtn.setBackgroundResource(getBackgroundId(pX, pY));
                        cBtn.setBackgroundResource(R.drawable.s_btn);
                        pX = ii;
                        pY = jj;

                    }
                });
            }
        }
        cBtn = B[0][0];
        iB = new Button[9];
        for (j = 0; j < 9; j++) {
            final int jj = j;
            int resID = getResources().getIdentifier("btn" + (j + 1), "id", getPackageName());
            iB[j] = (Button) findViewById(resID);
            iB[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SudokuSolver.legal(x, y, Integer.parseInt(iB[jj].getText().toString()), B))
                        cBtn.setText(iB[jj].getText().toString());
                    else
                        Snackbar.make(findViewById(R.id.content_main), "Invalid Value.", Snackbar.LENGTH_LONG).show();
                }
            });
        }
        btnClr = (Button) findViewById(R.id.btnClr);
        btnClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cBtn.setText("");
            }
        });
    }

    private void requestNewInterstitial() {
        if(BuildConfig.FLAVOR == "free") {
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);
        }
    }

    private int getBackgroundId(int x, int y) {
        if (isBtw(0, 2, x)) {
            if (isBtw(3, 5, y)) {
                return 2130837590;
            } else if (isBtw(6, 8, y)) {
                return 2130837589;
            }
        } else if (isBtw(3, 5, x)) {
            if (isBtw(0, 2, y)) {
                return 2130837590;
            } else if (isBtw(3, 5, y)) {
                return 2130837593;
            } else if (isBtw(6, 8, y)) {
                return 2130837592;
            }
        } else if (isBtw(6, 8, x)) {
            if (isBtw(0, 2, y)) {
                return 2130837591;
            } else if (isBtw(3, 5, y)) {
                return 2130837594;
            } else if (isBtw(6, 8, y)) {
                return 2130837595;
            }
        }
        return 2130837589;
    }

    private boolean isBtw(int a, int b, int value) {
        if (value >= a && value <= b) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if(BuildConfig.FLAVOR.equals("paid")) {
            invalidateOptionsMenu();
            MenuItem item = menu.findItem(R.id.action_get_pro);
            item.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (BuildConfig.FLAVOR == "free" && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.solvebtn) {
            if (SudokuSolver.solve(0, 0, B))
                Snackbar.make(findViewById(R.id.content_main), "Sudoku Solved.", Snackbar.LENGTH_LONG).show();
            else
                Snackbar.make(findViewById(R.id.content_main), "No solution exists", Snackbar.LENGTH_LONG).show();
        } else if (id == R.id.resetbtn) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    B[i][j].setText("");
                }
            }
        } else if (id == R.id.action_help) {
            Intent i = new Intent(MainActivity.this, HelpActivity.class);
            startActivity(i);
        } else if (id == R.id.action_more_apps) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://developer?id=Techbuddy")));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Techbuddy")));
            }
        } else if (id == R.id.action_get_pro) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.vb.fastestsudokusolver.paid")));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.vb.fastestsudokusolver.paid")));
            }
        }

        return super.onOptionsItemSelected(item);
    }
}

class SudokuSolver {
    static int count;

    static boolean solve(int i, int j, Button[][] cells) {
        Log.d("Count: ", "" + (count++) + "(" + i + "," + j + ")");
        if (i == 9) {
            i = 0;
            if (++j == 9)
                return true;
        }
        if (!cells[i][j].getText().toString().equals(""))  // skip filled cells
            return solve(i + 1, j, cells);

        for (int val = 1; val <= 9; ++val) {
            if (legal(i, j, val, cells)) {
                cells[i][j].setText("" + val);
                if (solve(i + 1, j, cells))
                    return true;
            }
        }
        cells[i][j].setText(""); // reset on backtrack
        return false;
    }

    static boolean legal(int i, int j, int val, Button[][] cells) {
        for (int k = 0; k < 9; ++k)  // row
            if (cells[k][j].getText().toString().equals("" + val))
                return false;

        for (int k = 0; k < 9; ++k) // col
            if (cells[i][k].getText().toString().equals("" + val))
                return false;

        int boxRowOffset = (i / 3) * 3;
        int boxColOffset = (j / 3) * 3;
        for (int k = 0; k < 3; ++k) // box
            for (int m = 0; m < 3; ++m)
                if (cells[boxRowOffset + k][boxColOffset + m].getText().toString().equals("" + val))
                    return false;

        return true; // no violations, so it's legal
    }
}