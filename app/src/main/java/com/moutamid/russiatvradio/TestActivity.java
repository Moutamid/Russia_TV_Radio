package com.moutamid.russiatvradio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private LinearLayout adView;
    private NativeAd nativeAd;

    private NativeAdLayout nativeAdLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        AudienceNetworkAds.initialize(TestActivity.this);
        showFbNativeAdSplash(TestActivity.this, TestActivity.this);

    }


    public  void showFbNativeAdSplash(final Context context, final Activity activity) {


        final String TAG = "NativeAdActivity".getClass().getSimpleName();


        final NativeAd nativeAdfb = new NativeAd(context, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID");


        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                // Native ad finished downloading all assets
                Log.e(TAG, "Native ad finished downloading all assets.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
//                loadAdmobNativeAds(context, activity);
//                isAdLoad = false;
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (nativeAdfb != ad) {

                }
                // Inflate Native Ad into Container
                inflateAdSplah(nativeAdfb, context, activity);
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Native ad clicked
                Log.d(TAG, "Native ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Native ad impression
                Log.d(TAG, "Native ad impression logged!");
            }
        };

        // Request an ad
        nativeAdfb.loadAd(
                nativeAdfb.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());

    }

    public  void inflateAdSplah(NativeAd nativeAd, Context context, Activity activity) {

        nativeAd.unregisterView();

        nativeAdLayout = activity.findViewById(R.id.native_ad_container);
        // Add the Ad view into the ad container.
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
        LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.facebook_native_ad, nativeAdLayout, false);
        nativeAdLayout.addView(adView);


//        LinearLayout adChoicesContainer = activity.findViewById(R.id.ad_choices_container);
//        AdOptionsView adOptionsView = new AdOptionsView(context, nativeAd, nativeAdLayout);
//        adChoicesContainer.removeAllViews();
//        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        com.facebook.ads.MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        com.facebook.ads.MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        nativeAd.registerViewForInteraction(
                adView, nativeAdMedia, nativeAdIcon, clickableViews);

    }
}