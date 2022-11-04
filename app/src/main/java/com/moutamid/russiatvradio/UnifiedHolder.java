package com.moutamid.russiatvradio;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAdLayout;

public class UnifiedHolder extends RecyclerView.ViewHolder {
    NativeAdLayout nativeAdLayout;
    MediaView mediaView, ivAdIcon;
    TextView tv_title, tv_body, tv_social, tv_sponsored;
    Button callToAction;
    LinearLayout adContainer;

    public UnifiedHolder(NativeAdLayout nativeAdLayout) {
        super(nativeAdLayout);
        this.nativeAdLayout = nativeAdLayout;
        this.mediaView = nativeAdLayout.findViewById(R.id.native_ad_media);
        this.tv_title = nativeAdLayout.findViewById(R.id.native_ad_title);
        this.tv_body = nativeAdLayout.findViewById(R.id.native_ad_body);
        this.tv_social = nativeAdLayout.findViewById(R.id.native_ad_social_context);
        this.tv_sponsored = nativeAdLayout.findViewById(R.id.native_ad_sponsored_label);
        this.callToAction = nativeAdLayout.findViewById(R.id.native_ad_call_to_action);
        this.ivAdIcon = nativeAdLayout.findViewById(R.id.native_ad_icon);
        this.adContainer = nativeAdLayout.findViewById(R.id.ad_choices_container);
    }
}
