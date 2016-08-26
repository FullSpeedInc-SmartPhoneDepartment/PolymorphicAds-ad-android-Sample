package jp.co.fullspeed.polymorphicadssample.ad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import jp.co.fullspeed.polymorphicads.PolymorphicAds;
import jp.co.fullspeed.polymorphicads.PolymorphicAdsCallback;
import jp.co.fullspeed.polymorphicadssample.R;
import jp.co.fullspeed.polymorphicadssample.constant.AdUnitIdConst;


/**
 * カルーセル広告を利用したサンプルアクティビティ.
 */
public class CarouselAdActivity extends AppCompatActivity implements
        PolymorphicAdsCallback.Callbackable,
        PolymorphicAdsCallback.InitializeCallback,
        PolymorphicAdsCallback.RequestCallback,
        PolymorphicAdsCallback.DisplayCallback,
        PolymorphicAdsCallback.ClickCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carousel_sample_activity);

        // ① 広告ユニット初期化
        PolymorphicAds.init(this, AdUnitIdConst.CAROUSEL_AD_UNIT_ID, PolymorphicAds.AdType.CAROUSEL, R.id.ad_container);

        // ② 広告ロード（Ad情報取得、表示）
        PolymorphicAds.load(AdUnitIdConst.CAROUSEL_AD_UNIT_ID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // （任意） コールバックセット
        PolymorphicAds.setCallback(
                AdUnitIdConst.CAROUSEL_AD_UNIT_ID,
                this,
                PolymorphicAdsCallback.CallbackType.INITIALIZE,
                PolymorphicAdsCallback.CallbackType.REQUEST,
                PolymorphicAdsCallback.CallbackType.DISPLAY,
                PolymorphicAdsCallback.CallbackType.CLICK);
    }

    // （任意） コールバック受信
    @Override
    public void onSuccessInitRequest(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_success_init_request));
    }

    @Override
    public void onFailureInitRequest(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_failure_init_request));
    }

    @Override
    public void onFailureSendAdRequest(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_failure_send_ad_request));
    }

    @Override
    public void onSuccessResponseAdRequest(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_success_response_ad_request));
    }

    @Override
    public void onFailureResponseAdRequest(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_failure_response_ad_request));
    }

    @Override
    public void onReceiveNoAds(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_receive_no_ads));
    }

    @Override
    public void onSuccessShowAd(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_success_show_ad));
    }

    @Override
    public void onFailureShowAd(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_failure_show_ad));
    }

    @Override
    public void onSkipShowAd(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_skip_show_ad));
    }

    @Override
    public void onCloseAd(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_close_ad));
    }

    @Override
    public void onFailureUseOverlay(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_failure_use_overlay));
    }

    @Override
    public void onSuccessClickAd(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_success_click_ad));
    }

    @Override
    public void onFailureClickAd(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_failure_click_ad));
    }
}
