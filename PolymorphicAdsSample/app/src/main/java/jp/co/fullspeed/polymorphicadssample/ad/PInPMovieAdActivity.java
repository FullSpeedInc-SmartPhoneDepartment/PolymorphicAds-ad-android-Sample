package jp.co.fullspeed.polymorphicadssample.ad;

import jp.co.fullspeed.polymorphicads.PolymorphicAds;
import jp.co.fullspeed.polymorphicads.PolymorphicAdsCallback;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import jp.co.fullspeed.polymorphicadssample.R;
import jp.co.fullspeed.polymorphicadssample.constant.AdUnitIdConst;


public class PInPMovieAdActivity extends AppCompatActivity implements
        PolymorphicAdsCallback.Callbackable,
        PolymorphicAdsCallback.InitializeCallback,
        PolymorphicAdsCallback.RequestCallback,
        PolymorphicAdsCallback.DisplayCallback,
        PolymorphicAdsCallback.ClickCallback,
        PolymorphicAdsCallback.MovieCallback {

    private static int OVERLAY_PERMISSION_REQ_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_pmovie_ad);

        PolymorphicAds.init(this, AdUnitIdConst.P_IN_P_AD_UNIT_ID, PolymorphicAds.AdType.PICTURE_IN_PICTURE_MOVIE);

        findViewById(R.id.load_pinp_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PolymorphicAds.load(AdUnitIdConst.P_IN_P_AD_UNIT_ID);
            }
        });

        findViewById(R.id.show_pinp_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ③ 広告表示（load()の後であればいつCallしても良い）
                if (!PolymorphicAds.isReady(AdUnitIdConst.P_IN_P_AD_UNIT_ID)) {
                    // 広告の表示準備が未完の場合
                    Toast.makeText(PInPMovieAdActivity.this, getString(R.string.now_preparing), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (PolymorphicAds.isDisplay(AdUnitIdConst.P_IN_P_AD_UNIT_ID)) {
                    // 広告がすでに表示されている場合
                    Toast.makeText(PInPMovieAdActivity.this, getString(R.string.now_on_display), Toast.LENGTH_SHORT).show();
                    return;
                }
                PolymorphicAds.show(AdUnitIdConst.P_IN_P_AD_UNIT_ID, PolymorphicAds.AdPiPPositionType.LEFTTOP);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // （任意） コールバックセット
        PolymorphicAds.setCallback(
                AdUnitIdConst.P_IN_P_AD_UNIT_ID,
                this,
                PolymorphicAdsCallback.CallbackType.INITIALIZE,
                PolymorphicAdsCallback.CallbackType.REQUEST,
                PolymorphicAdsCallback.CallbackType.DISPLAY,
                PolymorphicAdsCallback.CallbackType.CLICK,
                PolymorphicAdsCallback.CallbackType.MOVIE);
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
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + "Overlay使用許可が設定されていない");

        // Overlay使用許可画面を表示させる
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
    }

    @Override
    public void onSuccessClickAd(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_success_click_ad));
    }

    @Override
    public void onFailureClickAd(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_failure_click_ad));
    }

    @Override
    public void onReadyPlayMovie(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_ready_play_movie));

//        Toast.makeText(PInPMovieAdActivity.this, "show ready movie", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailurePreparePlayMovie(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_failure_prepare_play_movie));
    }

    @Override
    public void onCompletePlayMovie(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_complete_play_movie));
    }

    @Override
    public void onExpandedAdView(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + "ユーザータップによる拡大イベント");
    }
}
