package jp.co.fullspeed.polymorphicadssample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

import jp.co.fullspeed.polymorphicads.PolymorphicAds;
import jp.co.fullspeed.polymorphicadssample.ad.BannerAdActivity;
import jp.co.fullspeed.polymorphicadssample.ad.CarouselAdActivity;
import jp.co.fullspeed.polymorphicadssample.ad.DoubleSizeBannerAdActivity;
import jp.co.fullspeed.polymorphicadssample.ad.ForceMovieAdActivity;
import jp.co.fullspeed.polymorphicadssample.ad.InfeedAdActivity;
import jp.co.fullspeed.polymorphicadssample.ad.InterstitialAdActivity;
import jp.co.fullspeed.polymorphicadssample.ad.PopupAdActivity;
import jp.co.fullspeed.polymorphicadssample.ad.RectangleMovieAdActivity;
import jp.co.fullspeed.polymorphicadssample.ad.SlideInAdActivity;
import jp.co.fullspeed.polymorphicadssample.ad.TwinPanelAdActivity;

import jp.co.fullspeed.polymorphicads.PolymorphicAds;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // （任意） テストモード設定（デフォルトはtrue, リリース時はfalseにする）
        // TODO: リリース時にコメントアウト解除
        PolymorphicAds.setTestMode(false);
//        PolymorphicAds.setTestMode(true);

        // （任意） ログ出力設定（デフォルトはfalse, リリース時は削除するかfalseにする）
        PolymorphicAds.setLoggingMode(true);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Arrays.asList(getResources().getStringArray(R.array.ad_list_array))
        );
        ListView listView = (ListView) findViewById(R.id.ad_list);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView listView = (ListView) parent;
        String item = (String) listView.getItemAtPosition(position);

        if (item == null) {
            return;
        }

        Class clazz;
        switch (item) {
            case "Banner":
                clazz = BannerAdActivity.class;
                break;
            case "Double Size Banner":
                clazz = DoubleSizeBannerAdActivity.class;
                break;
            case "Twin Panel":
                clazz = TwinPanelAdActivity.class;
                break;
            case "Interstitial":
                clazz = InterstitialAdActivity.class;
                break;
            case "Infeed":
//                clazz = InfeedAdActivity.class;
//                break;
                Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
                return;
            case "Popup":
                clazz = PopupAdActivity.class;
                break;
            case "Slide in":
                clazz = SlideInAdActivity.class;
                break;
            case "Carousel":
//                clazz = CarouselAdActivity.class;
//                break;
                Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
                return;
            case "Picture in Picture":
//                clazz = PInPMovieAdActivity.class;
//                break;
                Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
                return;
            case "Force Movie":
                clazz = ForceMovieAdActivity.class;
                break;
            case "Rectangle Movie":
                clazz = RectangleMovieAdActivity.class;
                break;
            default:
                return;
        }

        startActivity(new Intent(MainActivity.this, clazz));
    }
}
