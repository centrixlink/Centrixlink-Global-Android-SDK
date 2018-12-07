package com.centrixlink.sdk.global.demo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.centrixlink.globaladsdk.ads.bannerad.BannerAd;

import java.util.List;

public class BannerAdListFragmentAdapter extends ArrayAdapter {

    static class ViewHolder {
        public LinearLayout bannerAdContainer;
    }
    LayoutInflater inflater;
    public BannerAdListFragmentAdapter(@NonNull Context context, @LayoutRes int resource,
                                          @NonNull List<BannerAd> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final View view;
        final ViewHolder viewHolder;
        if (convertView == null) {
            view = inflater.inflate(R.layout.banner_ad_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.bannerAdContainer = view.findViewById(R.id.banner_container);
        } else  {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        BannerAd bannerAd = (BannerAd) getItem(position);
        view.setTag(viewHolder);
        bannerAd.renderAdableOn(viewHolder.bannerAdContainer);
        return view;
    }
}
