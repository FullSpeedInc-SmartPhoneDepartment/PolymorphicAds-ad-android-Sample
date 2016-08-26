package jp.co.fullspeed.polymorphicadssample.ad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import jp.co.fullspeed.polymorphicads.PolymorphicAds;
import jp.co.fullspeed.polymorphicads.PolymorphicAdsCallback;
import jp.co.fullspeed.polymorphicadssample.R;
import jp.co.fullspeed.polymorphicadssample.adapter.InfeedAdapter;
import jp.co.fullspeed.polymorphicadssample.constant.AdUnitIdConst;


/**
 * インフィード広告を利用したサンプルアクティビティ.
 */
public class InfeedAdActivity extends AppCompatActivity implements
        PolymorphicAdsCallback.Callbackable,
        PolymorphicAdsCallback.InitializeCallback,
        PolymorphicAdsCallback.RequestCallback,
        PolymorphicAdsCallback.DisplayCallback,
        PolymorphicAdsCallback.ClickCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infeed_sample_activity);

        // ① クラス定義
        ListView listView = (ListView) findViewById(R.id.infeed_list);

        // ② インフィード広告を挿入するListViewにPolymorphicAdsInfeedAdapterを継承したAdapterをセット
        // （ここで、管理画面で取得したadUnitIdを渡す）
        listView.setAdapter(new InfeedAdapter(this, AdUnitIdConst.INFEED_AD_UNIT_ID, listView, getSampleList()));
    }

    // ③ オリジナルリスト生成メソッド（例）
    private List<String> getSampleList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("# " + String.valueOf(i));
        }
        return list;
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
