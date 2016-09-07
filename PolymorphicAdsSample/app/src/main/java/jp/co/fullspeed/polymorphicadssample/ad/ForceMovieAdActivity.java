package jp.co.fullspeed.polymorphicadssample.ad;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import jp.co.fullspeed.polymorphicads.PolymorphicAds;
import jp.co.fullspeed.polymorphicads.PolymorphicAdsCallback;
import jp.co.fullspeed.polymorphicadssample.R;
import jp.co.fullspeed.polymorphicadssample.constant.AdUnitIdConst;


/**
 * リワード動画広告を利用したサンプルアクティビティ
 */
public class ForceMovieAdActivity extends AppCompatActivity implements
        PolymorphicAdsCallback.Callbackable,
        PolymorphicAdsCallback.InitializeCallback,
        PolymorphicAdsCallback.RequestCallback,
        PolymorphicAdsCallback.DisplayCallback,
        PolymorphicAdsCallback.ClickCallback,
        PolymorphicAdsCallback.MovieCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.force_movie_sample_activity);

        // ① 広告ユニット初期化
        PolymorphicAds.init(this, AdUnitIdConst.FORCE_MOVIE_AD_UNIT_ID, PolymorphicAds.AdType.FORCE_MOVIE);

        // ② 広告ロード（Ad情報取得）
        PolymorphicAds.load(AdUnitIdConst.FORCE_MOVIE_AD_UNIT_ID);

        findViewById(R.id.show_force_movie_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ③ 広告表示（load()の後であればいつCallしても良い）
                if (!PolymorphicAds.isReady(AdUnitIdConst.FORCE_MOVIE_AD_UNIT_ID)) {
                    // 広告の表示準備が未完の場合
                    Toast.makeText(ForceMovieAdActivity.this, getString(R.string.now_preparing), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (PolymorphicAds.isDisplay(AdUnitIdConst.FORCE_MOVIE_AD_UNIT_ID)) {
                    // 広告がすでに表示されている場合
                    Toast.makeText(ForceMovieAdActivity.this, getString(R.string.now_on_display), Toast.LENGTH_SHORT).show();
                    return;
                }
                PolymorphicAds.show(AdUnitIdConst.FORCE_MOVIE_AD_UNIT_ID);
            }
        });
        findViewById(R.id.show_force_movie_button_with_force_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ③ 広告表示（load()の後であればいつCallしても良い）
                if (!PolymorphicAds.isReady(AdUnitIdConst.FORCE_MOVIE_AD_UNIT_ID)) {
                    // 広告の表示準備が未完の場合
                    Toast.makeText(ForceMovieAdActivity.this, getString(R.string.now_preparing), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (PolymorphicAds.isDisplay(AdUnitIdConst.FORCE_MOVIE_AD_UNIT_ID)) {
                    // 広告がすでに表示されている場合
                    Toast.makeText(ForceMovieAdActivity.this, getString(R.string.now_on_display), Toast.LENGTH_SHORT).show();
                    return;
                }
                PolymorphicAds.show(AdUnitIdConst.FORCE_MOVIE_AD_UNIT_ID);

                // PolymorphicAds.forceClose(String)使用サンプル
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            // nothing to do
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        if (!PolymorphicAds.isDisplay(AdUnitIdConst.FORCE_MOVIE_AD_UNIT_ID)) {
                            return;
                        }
                        PolymorphicAds.forceClose(AdUnitIdConst.FORCE_MOVIE_AD_UNIT_ID);
                        Toast.makeText(ForceMovieAdActivity.this, getString(R.string.perform_force_close), Toast.LENGTH_SHORT).show();
                    }
                }.execute();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // （任意） コールバックセット
        PolymorphicAds.setCallback(
                AdUnitIdConst.FORCE_MOVIE_AD_UNIT_ID,
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

    @Override
    public void onReadyPlayMovie(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_ready_play_movie));
    }

    @Override
    public void onFailurePreparePlayMovie(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_failure_prepare_play_movie));
    }

    @Override
    public void onCompletePlayMovie(String adUnitId, PolymorphicAds.AdType adType) {
        Log.i(this.getClass().getSimpleName(), "AdType[" + adType.name() + "] " + getString(R.string.on_complete_play_movie));
    }
}
