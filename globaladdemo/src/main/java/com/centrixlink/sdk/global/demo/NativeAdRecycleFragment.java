package com.centrixlink.sdk.global.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.centrixlink.globaladsdk.Adable;
import com.centrixlink.globaladsdk.ads.nativead.NativeAd;
import com.centrixlink.globaladsdk.error.Errorable;

import java.util.ArrayList;

public class NativeAdRecycleFragment extends Fragment {
    private Button btnLoad;
    private Button btnShow;
    private RecyclerView recyclerView;
    ArrayList<NativeAd> nativeAds = new ArrayList<>();
    private int responseCount = 0;
    NativeAdRecycleAdapter adRecycleAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.native_ad_recycle_fragment, container, false);
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
        recyclerView = view.findViewById(R.id.recycle_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ArrayList<NativeAd> nativeAdsEmpty = new ArrayList<>();
        adRecycleAdapter = new NativeAdRecycleAdapter(getContext(), nativeAdsEmpty);
        recyclerView.setAdapter(adRecycleAdapter);
        NativeAd.NativeAdListener listener = new NativeAd.NativeAdListener() {
            @Override
            public void onAdableLoadFinished(Adable ad, Errorable error) {
                SafeToast.showToast(getContext(), error.getDescription());
                responseCount ++;
            }

            @Override
            public void onAdableImpressionFinished(Adable ad, Errorable error) {
                SafeToast.showToast(getContext(), error.getDescription());
            }

            @Override
            public void onAdableClickTrackingFinished(Adable ad, Errorable error) {
                SafeToast.showToast(getContext(), error.getDescription());
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
        NativeAd nativeAd_1 = new NativeAd(DemoPlacementID.NATIVE_0_PLACEMENT_ID.getPlacementID(), listener);
        nativeAds.add(nativeAd_1);
        NativeAd nativeAd_2 = new NativeAd(DemoPlacementID.NATIVE_1_PLACEMENT_ID.getPlacementID(), listener);
        nativeAds.add(nativeAd_2);
        NativeAd nativeAd_3 = new NativeAd(DemoPlacementID.NATIVE_2_PLACEMENT_ID.getPlacementID(), listener);
        nativeAds.add(nativeAd_3);
        NativeAd nativeAd_4 = new NativeAd(DemoPlacementID.NATIVE_3_PLACEMENT_ID.getPlacementID(), listener);
        nativeAds.add(nativeAd_4);
        loadNativeAds();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void loadNativeAds() {
        responseCount = 0;
        adRecycleAdapter.setNativeAds(new ArrayList<NativeAd>());
        recyclerView.getAdapter().notifyDataSetChanged();
        for (NativeAd nativeAd:
                nativeAds) {
            nativeAd.loadAd();
        }
    }

    private void showNativeAds() {
        if (responseCount == nativeAds.size()) {
            adRecycleAdapter.setNativeAds(nativeAds);
            adRecycleAdapter.notifyDataSetChanged();
        }
    }
}
