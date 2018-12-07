package com.centrixlink.sdk.global.demo;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.net.ContentHandler;

public class SafeToast {
    static void showToast(Context context, String msg) {
        Log.i("SafeToast", msg);
    }
}
