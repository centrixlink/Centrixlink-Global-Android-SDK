package com.centrixlink.sdk.global.demo;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.centrixlink.globaladsdk.CentrixlinkGlobalAdSDK;
import com.centrixlink.globaladsdk.download.ImageDownloadRequest;
import com.centrixlink.globaladsdk.error.Errorable;

import java.util.ArrayList;

import static android.content.Context.ACTIVITY_SERVICE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CentrixlinkGlobalAdSDK.startWithAppConfig(DemoPlacementID.AppID, DemoPlacementID.AppKEY, this);
        MainAdTypesListFragment fragment = new MainAdTypesListFragment();
        replaceListContainerWithFragment(fragment, false);
    }

    public void replaceListContainerWithFragment(Fragment fragment, Boolean addedToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.list_container, fragment);
        if (addedToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack();
    }
}
