package com.centrixlink.sdk.global.demo;

import android.content.ContentProvider;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.centrixlink.globaladsdk.ads.nativead.NativeAd;

import java.lang.annotation.Native;
import java.util.ArrayList;
import java.util.List;

public class NativeAdRecycleAdapter extends RecyclerView.Adapter<NativeAdRecycleAdapter.ViewHolder> {

    private List<NativeAd> nativeAds;
    public NativeAdRecycleAdapter(Context context, List<NativeAd> nativeAds) {
        super();
        this.nativeAds = nativeAds;
    }

    public void setNativeAds(List<NativeAd> nativeAds) {
        this.nativeAds = nativeAds;
    }

    @Override
    public int getItemCount() {
        return nativeAds.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.native_ad_recycle_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NativeAd nativeAd = nativeAds.get(i);
        RelativeLayout layout = viewHolder.layout;
        nativeAd.renderAdableOn(layout);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.recycle_container);
        }
    }
}
