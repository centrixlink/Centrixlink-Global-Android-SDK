package com.centrixlink.sdk.global.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainAdTypesListFragment extends ListFragment {

    private static final String TAG = "MainAdTypesListFragment";

    MainAdTypesListFragmentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.main_adtypes_list_fragment, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<DemoAdType> list = new ArrayList<DemoAdType>();
        list.addAll(Arrays.asList(DemoAdType.values()));
        adapter = new MainAdTypesListFragmentAdapter(getContext(), 0, list);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new BannerAdListFragment();
                break;
            case 1:
                fragment = new NativeAdListFragment();
                break;
            case 2:
                fragment = new NativeAdRecycleFragment();
                break;
            case 3:
                fragment = new InterstitialAdFragment();
                break;
//            case 4:
//                fragment = new InvalidBannerListFragment();
//                break;
//            case 5:
//                fragment = new InvalidNativeAdListFragment();
//                break;
//            case 6:
////                fragment = new DownloadMarketGooglePreviewFragment();
//                break;
                default:
                    fragment = null;

        }
        if (fragment == null) {
            return;
        }
        replaceFragment(fragment);
    }

    void replaceFragment(Fragment fragment) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.replaceListContainerWithFragment(fragment, true);
    }

    void toBannerAdDemoFragment() {
        BannerAdListFragment bannerAdListFragment = new BannerAdListFragment();
        replaceFragment(bannerAdListFragment);
    }

    void toNativeAdDemoFragment() {

    }

    void toInterstitialAdDemoFragment() {

    }
}
