package jp.co.fullspeed.polymorphicadssample.adapter;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.co.fullspeed.polymorphicads.PolymorphicAdsCallback;
import jp.co.fullspeed.polymorphicads.PolymorphicAdsCustomizer;
import jp.co.fullspeed.polymorphicads.PolymorphicAdsInfeedAdapter;
import jp.co.fullspeed.polymorphicadssample.R;


/**
 * インフィード広告を表示するListView用サンプルアダプター.
 */
// ① FSInfeedAdapterを継承する
public class InfeedAdapter extends PolymorphicAdsInfeedAdapter<String> {

    private LayoutInflater mInflater;

    public InfeedAdapter(AppCompatActivity activity, String adUnitId, ListView listView, List<String> list) {
        super(activity, adUnitId, listView, list);
        mInflater = LayoutInflater.from(activity);
    }

    @Override
    // ② BaseAdapter#getView(int, View, ViewGroup)と同等の広告以外のView生成メソッド.
    // （originalPositionはインフィード広告行が無いと仮定した場合のposition）
    public View getOriginalView(int originalPosition, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null || !(convertView.getTag() instanceof ViewHolder)) {
            convertView = mInflater.inflate(R.layout.infeed_sample_list_item_original_layout, parent, false);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(getItem(originalPosition));

        return convertView;
    }

    @Override
    // ③ インフィード広告を表示するpositionの設定メソッド.
    public Set<Integer> getInfeedPosition() {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(5);
        set.add(9);

        for (int i = 10; i < 100; i++) {
            if (i % 3 == 0) {
                set.add(i);
            }
        }
        return set;
    }

    @Override
    // ④ 受信するCallbackタイプの設定メソッド.
    public Set<PolymorphicAdsCallback.CallbackType> getCallbackTypes() {
        Set<PolymorphicAdsCallback.CallbackType> types = new HashSet<>();
        types.add(PolymorphicAdsCallback.CallbackType.INITIALIZE);
        types.add(PolymorphicAdsCallback.CallbackType.REQUEST);
        types.add(PolymorphicAdsCallback.CallbackType.DISPLAY);
        types.add(PolymorphicAdsCallback.CallbackType.CLICK);
        return types;
    }

    @Override
    // （任意） positionごとのインフィード広告タイプの設定メソッド
    // （デフォルトは画像左寄せタイプ）
    public PolymorphicAdsCustomizer.InfeedViewType getInfeedViewType(int position) {
        return super.getInfeedViewType(position);
    }

    @Override
    // （任意） positionごとのインフィード広告内画像位置の設定メソッド
    // （デフォルトは上下中央タイプ）
    public PolymorphicAdsCustomizer.InfeedImagePositionType getInfeedImagePositionType(int position) {
        return super.getInfeedImagePositionType(position);
    }

    @Override
    // （任意） positionごとのインフィード広告内画像の大きさ設定メソッド
    // （デフォルトは90dp）
    public int getInfeedImageDpHeight(int position) {
        return super.getInfeedImageDpHeight(position);
    }

    @Override
    // （任意） positionごとのインフィード広告行の高さ設定メソッド
    // （デフォルトは120dp）
    public int getInfeedRowDpHeight(int position) {
        return super.getInfeedRowDpHeight(position);
    }


    // ListView効率化のHolderパターンクラス.
    private class ViewHolder {

        TextView text;

        ViewHolder(View view) {
            text = (TextView) view.findViewById(R.id.infeed_list_item_text_view);
        }
    }
}
