package com.centrixlink.sdk.global.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.centrixlink.globaladsdk.ads.nativead.NativeAd;

import java.util.List;

public class NativeAdListFragmentAdapter extends ArrayAdapter<NativeAd> {

    private LayoutInflater inflater;
    private List<NativeAd> mNativeAds;
    static class ViewHolder {
        public LinearLayout adContainer;
    }

    public NativeAdListFragmentAdapter(Context context, List<NativeAd> nativeAds) {
        super(context, 0, nativeAds);
        mNativeAds = nativeAds;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.native_ad_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.adContainer = convertView.findViewById(R.id.ad_display_container);
            convertView.setTag(viewHolder);
        } else {
            viewHolder =(ViewHolder) convertView.getTag();
        }
        NativeAd nativeAd = mNativeAds.get(position);
        nativeAd.renderAdableOn(viewHolder.adContainer);
        return convertView;
    }
}
