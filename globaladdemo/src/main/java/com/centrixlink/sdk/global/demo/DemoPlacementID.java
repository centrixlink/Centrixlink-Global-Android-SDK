package com.centrixlink.sdk.global.demo;

public enum DemoPlacementID {

    BANNER_320_50_PLACEMENT_ID("5b8392b728eb3a70cef44bc2"),
    BANNER_300_250_PLACMENT_ID("5bab08005c0000a40137dba8"),
    NATIVE_0_PLACEMENT_ID("5b8611ae28eb3a70ce5e798f"),
    NATIVE_1_PLACEMENT_ID("5b8611b828eb3a70ce5e79a4"),
    NATIVE_2_PLACEMENT_ID("5b8611c028eb3a70ce5e79b4"),
    NATIVE_3_PLACEMENT_ID("5b8611c728eb3a70ce5e7c8e"),
    INTERSTITIAL_PLACEMENT_ID("5bac4b7455000023046210e7");

    private String placementID;

    DemoPlacementID(String placementID) {
        this.placementID = placementID;
    }

    public String getPlacementID() {
        return placementID;
    }

    public String getTitle() {
        switch (this) {
            case NATIVE_0_PLACEMENT_ID:
                return "NATIVE_0_PLACEMENT_ID";
            case NATIVE_1_PLACEMENT_ID:
                return "NATIVE_1_PLACEMENT_ID";
            case NATIVE_2_PLACEMENT_ID:
                return "NATIVE_2_PLACEMENT_ID";
            case NATIVE_3_PLACEMENT_ID:
                return "NATIVE_3_PLACEMENT_ID";
            case INTERSTITIAL_PLACEMENT_ID:
                return "INTERSTITIAL_PLACEMENT_ID";
            case BANNER_300_250_PLACMENT_ID:
                return "BANNER_300_250_PLACMENT_ID";
            case BANNER_320_50_PLACEMENT_ID:
                return "BANNER_320_50_PLACEMENT_ID";
                default:
                    return "ERROR_TYPE";
        }
    }

    public static final String AppID = "5b8610724900004813e1da42";
    public static final String AppKEY = "8a42f6e884442ba754ba79f9ef163a42efc452ee28791f97d109ac51291f6f1f";
}
