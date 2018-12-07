package com.centrixlink.sdk.global.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.centrixlink.globaladsdk.Adable;
import com.centrixlink.globaladsdk.ads.interstitialad.InterstitialAd;
import com.centrixlink.globaladsdk.error.Errorable;

public class InterstitialAdFragment extends Fragment {

    private Button btnLoad;
    private Button btnShow;
    private Switch swAutoClose;
    private InterstitialAd interstitialAd;
    private Boolean isAutoClose = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        InterstitialAd.InterstitialAdListener listener = new InterstitialAd.InterstitialAdListener() {
            @Override
            public void onAdDidClose(Adable adable, Errorable error) {
                Log.i("InterstitialAd", "did close");
//                SafeToast.showToast(getContext(), "did close");
            }

            @Override
            public void onAdableLoadFinished(Adable ad, Errorable error) {
                Log.i("InterstitialAd", "load finished");
//                SafeToast.showToast(getContext(), "load finished");
            }

            @Override
            public void onAdableImpressionFinished(Adable ad, Errorable error) {
                Log.i("InterstitialAd", "impression finished");
//                SafeToast.showToast(getContext(), "impression finished");
            }

            @Override
            public void onAdableClickTrackingFinished(Adable ad, Errorable error) {
                Log.i("InterstitialAd", "tracking finished");
//                SafeToast.showToast(getContext(), "tracking finished");
            }

            @Override
            public void onAdableDisplayFinished(Adable ad, Errorable error) {
                Log.i("InterstitialAd", "display finished");
//                SafeToast.showToast(getContext(), "display finished");
            }

            @Override
            public void onAdalbeWillLeftApplication(Adable ad, Errorable error) {
                Log.i("InterstitialAd", "will left application");
//                SafeToast.showToast(getContext(), "will left application");
            }

            @Override
            public void onAdWillShowPreview(Adable adable, Errorable error) {
                Log.i("InterstitialAd", "will show preview");
//                SafeToast.showToast(getContext(), "will show preview");
            }
        };
        interstitialAd = new InterstitialAd(DemoPlacementID.INTERSTITIAL_PLACEMENT_ID.getPlacementID(), listener);
        final View view = inflater.inflate(R.layout.interstitial_ad_list_fragment, container, false);
        btnLoad = view.findViewById(R.id.btn_load);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadInterstitialAd();
            }
        });
        btnShow = view.findViewById(R.id.btn_show);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInterstitialAd();
            }
        });
        swAutoClose = view.findViewById(R.id.auto_close_switch);
        swAutoClose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isAutoClose = buttonView.isChecked();
            }
        });
//        swAutoClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void loadInterstitialAd() {
        interstitialAd.loadAd();
    }

    private void showInterstitialAd() {
        interstitialAd.showAd(swAutoClose.isChecked());
    }
}
