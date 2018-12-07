package com.centrixlink.sdk.global.demo;

public enum DemoAdType {

    BANNER_AD("BannerAD"),
    NATIVE_AD_IN_LISTVIEW("NativeAD In ListView"),
    NATIVE_AD_IN_RECYCLEVIEW("NativeAD In RecycleView"),
    INTERSTITIAL_AD("InterstitialAD");

    private String title;
    DemoAdType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
