package com.centrixlink.sdk.global.demo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.centrixlink.globaladsdk.Adable;
import com.centrixlink.globaladsdk.AdableListener;
import com.centrixlink.globaladsdk.ads.bannerad.BannerAd;
import com.centrixlink.globaladsdk.error.ErrorCode;
import com.centrixlink.globaladsdk.error.Errorable;

import java.util.ArrayList;

public class BannerAdListFragment extends Fragment implements BannerAd.BannerAdListener {

    private static final String TAG = "BannerAdListFragment";
    private String BANNER_320_50_PLACEMENTID = DemoPlacementID.BANNER_320_50_PLACEMENT_ID.getPlacementID();
    private String BANNER_300_250_PLACEMENTID = DemoPlacementID.BANNER_300_250_PLACMENT_ID.getPlacementID();
    private BannerAd bannerAd_320_50;
    private BannerAd bannerAd_300_250;
    private RelativeLayout banner_320_50_container;
    private RelativeLayout banner_300_250_container;
    private Button btnLoad;
    private Button btnShow;

    ArrayList<BannerAd> bannerAds = new ArrayList<BannerAd>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bannerAds = new ArrayList<>();
        bannerAd_320_50 = new BannerAd(BANNER_320_50_PLACEMENTID, this);
        bannerAd_300_250 = new BannerAd(BANNER_300_250_PLACEMENTID, this);
        bannerAds.add(bannerAd_320_50);
        bannerAds.add(bannerAd_300_250);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.banner_ad_list_fragment, container, false);
        btnLoad = view.findViewById(R.id.btn_load);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadBannerAds();
            }
        });
        btnShow = view.findViewById(R.id.btn_show);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renderBannerAd();
            }
        });

        banner_300_250_container = view.findViewById(R.id.banner_300_250_container);
        banner_320_50_container = view.findViewById(R.id.banner_320_50_container);

        return view;
    }

    @Override
    public void onAdableClickTrackingFinished(Adable ad, Errorable error) {
        Log.i(TAG, "banner tracking finished");
    }

    @Override
    public void onAdableDisplayFinished(Adable ad, Errorable error) {
//        Log.i(TAG, error != ErrorCode.NO_ERROR ? "banner display error" : "banner display success");
        SafeToast.showToast(getContext(), error != ErrorCode.NO_ERROR ?
                "banner display with error"
                : "banner display success");
    }

    @Override
    public void onAdableImpressionFinished(Adable ad, Errorable error) {
        Log.i(TAG, "banner impression finished");
    }

    @Override
    public void onAdableLoadFinished(Adable ad, Errorable error) {
        if (error == ErrorCode.NO_ERROR) {
//            renderBannerAd();
            Log.i(TAG, "banner load finished");
        } else {
            Log.i(TAG, "banner load failed");
//            Toast.makeText(getContext(), "banner ad load error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAdalbeWillLeftApplication(Adable ad, Errorable error) {
        SafeToast.showToast(getContext(), "will left application");
        Log.i(TAG, "will left application");
//        Toast.makeText(getContext(), "will left application", Toast.LENGTH_SHORT).show();
    }

    void renderBannerAd() {
        bannerAd_320_50.renderAdableOn(banner_320_50_container);
        bannerAd_300_250.renderAdableOn(banner_300_250_container);
    }

    private void loadBannerAds() {
        bannerAd_300_250.loadAd();
        bannerAd_320_50.loadAd();
    }
}
