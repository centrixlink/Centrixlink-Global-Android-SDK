package com.centrixlink.sdk.global.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.centrixlink.globaladsdk.Adable;
import com.centrixlink.globaladsdk.ads.nativead.NativeAd;
import com.centrixlink.globaladsdk.error.Errorable;

import java.lang.annotation.Native;
import java.util.ArrayList;

public class NativeAdListFragment extends ListFragment {

    NativeAdListFragmentAdapter adapter;
    private Button btnLoad;
    private Button btnShow;
    private Integer responseCount = 0;
    private ArrayList<NativeAd> nativeAds = new ArrayList<>();
    private NativeAd.NativeAdListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.native_ad_list_fragment, container, false);
        btnLoad = view.findViewById(R.id.btn_load);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNativeAds();
            }
        });
        btnShow = view.findViewById(R.id.btn_show);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNativeAds();
            }
        });

        listener = new NativeAd.NativeAdListener() {
            @Override
            public void onAdableLoadFinished(Adable ad, Errorable error) {
                SafeToast.showToast(getContext(), "native load finished" + error.getDescription());
                synchronized (responseCount) {
                    responseCount ++;
                }
            }

            @Override
            public void onAdableImpressionFinished(Adable ad, Errorable error) {
                SafeToast.showToast(getContext(), "native impression finished" + error.getDescription());
            }

            @Override
            public void onAdableClickTrackingFinished(Adable ad, Errorable error) {
                SafeToast.showToast(getContext(), "native tracking finished" + error.getDescription());
            }

            @Override
            public void onAdableDisplayFinished(Adable ad, Errorable error) {
                SafeToast.showToast(getContext(), error.getDescription());
            }

            @Override
            public void onAdalbeWillLeftApplication(Adable ad, Errorable error) {
                SafeToast.showToast(getContext(), "will left application");
            }
        };
        configNativeAdModels();
        adapter = new NativeAdListFragmentAdapter(getContext(), new ArrayList<NativeAd>());
        setListAdapter(adapter);
        loadNativeAds();
        return view;
    }

    private void configNativeAdModels() {
        nativeAds = new ArrayList<>();
        NativeAd nativeAd_1 = new NativeAd(DemoPlacementID.NATIVE_0_PLACEMENT_ID.getPlacementID(), listener);
        nativeAds.add(nativeAd_1);
        NativeAd nativeAd_2 = new NativeAd(DemoPlacementID.NATIVE_1_PLACEMENT_ID.getPlacementID(), listener);
        nativeAds.add(nativeAd_2);
        NativeAd nativeAd_3 = new NativeAd(DemoPlacementID.NATIVE_2_PLACEMENT_ID.getPlacementID(), listener);
        nativeAds.add(nativeAd_3);
        NativeAd nativeAd_4 = new NativeAd(DemoPlacementID.NATIVE_3_PLACEMENT_ID.getPlacementID(), listener);
        nativeAds.add(nativeAd_4);
        adapter = new NativeAdListFragmentAdapter(getContext(), nativeAds);
        setListAdapter(adapter);
    }

    private void loadNativeAds() {
        synchronized (responseCount) {
            responseCount = 0;
        }


        for (NativeAd nativeAd:
                nativeAds) {
            nativeAd.loadAd();
        }
    }

    private void showNativeAds() {
        if (responseCount >= nativeAds.size()) {
            updateList();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void updateList() {
        adapter.clear();
        adapter.addAll(nativeAds);
        adapter.notifyDataSetChanged();
    }

}
