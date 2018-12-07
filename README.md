## CentrixlinkGlobal Android SDK Documentation

## 1. 集成SDK的前期准备
#### 1.1 
申请AppKey,AppID [Centrixlink开发者官网](http://www.centrixlink.com)
#### 1.2
创建不同广告类型的PlacementID [Centrixlink开发者官网](http://www.centrixlink.com)
#### 1.3
    Android 4.0.3+ (Build Version Code 15+)

## 2. 集成SDK
#### 2.1 引入SDK
将libs中的centrixlink_global_sdk.aar引入工程,并在gradle中进行引用
```
libs
    |-----centrixlink_global_sdk_[version].aar
```

#### 2.2 更新 AndroidManifest.xml
```	xml
<manifest>
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <application>
        <activity android:name="com.centrixlink.globaladsdk.ads.interstitialad.InterstitialAdActivity">
            android:configChanges="keyboard|keyboardHidden|orientation|screenSize|screenLayout|smallestScreenSize"
            android:theme="@android:style/Theme.Translucent.NoTitleBar"
            tools:ignore="InnerclassSeparator" />
    </application>
</manifest>

```

## 3. 集成广告
#### 3.1 应用启动,注册AppID, AppKey
在Activity中初始化SDK,提供在平台中生成的AppId和AppKey
```Java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ....
    //实际开发中请务必使用自己申请的AppID 和 AppKey 
    final String appID = "5b8610724900004813e1da42";
    final String appKey = "8a42f6e884442ba754ba79f9ef163a42efc452ee28791f97d109ac51291f6f1f";
    //init SDK
    CentrixlinkGlobalAdSDK.startWithAppConfig(appID, appKey, this);
}
```
在[Centrixlink开发者官网](http://www.centrixlink.com)注册一个应用后,将注册生成的APPID,和APPKEY作为启动广告的参数,传入以成功启动广告SDK.

#### 3.2 SDK提供的广告类型以及集成方法
AndroidSDK设计中,`BannerAd`,`NativeAd`,`InterstitialAd`的回调方法基本相同(`InterstitialAd`中添加了点击关闭的事件以及将要呈现`InterstitialAdActivity`的回调),需要根据回调的实体来判断回调对象.
的具体类型.
#### 3.2.1 BannerAd
##### 3.2.1.1 创建BannerAd
```Java
BannerAd bannerAd = new BannerAd("banner_ad_placement_id", listener);
bannerAd.loadAd();
```
##### 3.2.1.2 BannerAd渲染
将`Banner Ad`渲染到开发者提供的容器视图`ViewGroup`
```Java
bannerAd.renderAdableOn(instance_of_viewgroup)
```
#### 3.2.2 NativeAd
`NativeAd`一共有四种高度的模板,分别为`100`,`120`,`330`,`400`.如果开发者提供的渲染`NativeAd`的容器尺寸不符合四种模板的最小尺寸,则会有渲染错误,并给开发者提供回调.
#### 样式
|类型|图示|
|--|--|
|NativeAd100|![NativeAd100](./DocImages/100dp.png)|
|NativeAd120|![NativeAd120](./DocImages/120dp.png)|
|NativeAd330|![NativeAd330](./DocImages/330dp.png)|
|NativeAd400|![NativeAd400](./DocImages/400dp.png)|
##### 3.2.2.1 创建NativeAd
```Java
NativeAd nativeAd = new NativeAd("native_ad_placement_id", listener);
nativeAd.loadAd();
```
##### 3.2.2.2 NativeAd渲染
将`NativeAd`渲染到开发者提供的容器视图`ViewGroup`
```Java
nativeAd.renderAdableOn(instance_of_viewgroup);
```
#### 3.2.3 InterstitialAd
##### 3.2.3.1 创建InterstitialAd
```Java
InterstitialAd interstitialAd = new InterstitialAd("interstitial_ad_placement_id", listener);
interstitialAd.loadAd();
```
##### 3.2.3.2 展示InterstitialAd
```Java
interstitialAd.showAd(false);
```
如果`showAd`方法的参数为`false`则在点击`interstitialAd`后,不自动关闭`InterstitialAdActivity`,反之,则关闭`InterstitialAdActivity`.

_NOTE_ 如果`interstitialAd`也使用`renderAdableOn(instance_of_viewgroup);`来显示`InterstitialAdActivity`,该方法会调用`interstitialAd.show(false);`.

#### 3.2.4 广告对象的回调方法
```Java
void onAdableLoadFinished(Adable ad, Errorable error);
```
`ad`加载结束回调.
如果`error`为`ErrorCode.NO_ERROR`,则加载成功.
如果`error`不为`ErrorCode.NO_ERROR`,加载失败.

----

```Java
void onAdableDisplayFinished(Adable ad, Errorable error);
```
`ad`校验开发者容器结果回调.
如果`error`为`ErrorCode.NO_ERROR`,开发者提供的容器能够呈现广告内容.
如果`error`不为`ErrorCode.NO_ERROR`,开发者提供的容器不能呈现广告内容.

----

```Java
void onAdableImpressionFinished(Adable ad, Errorable error);
```
`ad`曝光的回调.
如果`error`为`ErrorCode.NO_ERROR`,曝光成功.
如果`error`不为`ErrorCode.NO_ERROR`,曝光失败.

----

```Java
void onAdableClickTrackingFinished(Adable ad, Errorable error);
```
点击`ad`广告的回调.
如果`error`为`ErrorCode.NO_ERROR`,点击跳转`landingPage`成功.
如果`error`不为`ErrorCode.NO_ERROR`,点击跳转`landingPage`失败.

----

```Java
void onAdalbeWillLeftApplication(Adable ad, Errorable error);
```
点击`ad`后跳出当前应用的回调

----

__以下为`InterstitialAd`独有的回调事件__
```Java
void onAdDidClose(Adable adable, Errorable error);
```
点击`interstitialAd`关闭按钮的回调.

----

```Java
void onAdWillShowPreview(Adable adable, Errorable error);
```
将要展示`InterstitialAdActivity`的事件回调.
如果`error`为`ErrorCode.NO_ERROR`,展示`InterstitialAdActivity`成功.
如果`error`不为`ErrorCode.NO_ERROR`,展示`InterstitialAdActivity`失败.

----
## 4. Error信息

#### ErrorDomain 为com.centrixlink.ad.globalsdk.android.error
#### BannerErrorDomain 为com.centrixlink.ad.globalsdk.android.error.bannerad
#### NativeAdErrorDomain 为com.centrixlink.ad.globalsdk.android.error.nativead
#### InterstitialAdErrorDomain 为com.centrixlink.ad.globalsdk.android.error.interstitialad

|errorCode|info|
|--|--|
|1000, 1001, 1002 |SDK启动错误|
|1500|不合规URL|
|1800|图片下载错误|
|1900|placementID错误|
|3000, 3001, 3002, 3003, 3004|nativeAd数据返回错误|
|3100|开发者提供渲染nativeAd视图的容器尺寸不合规,不同类型nativeAd,所需容器最小宽度不同.|
|4000, 4001, 4002, 4003, 4004, 4005|bannerAd数据返回错误|
|4100|开发者提供渲染bannerAd视图的容器尺寸不合规,bannerAd所需渲染容器宽高需要大于等于广告位尺寸|
|5000, 5001, 5002, 5003, 5004|interstitialAd数据返回错误|

## 5.联系我们
mail: [Jiawei](mailto:jiawei.xu@centrixlink.com)
