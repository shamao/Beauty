package cc.hiy.baseui.flexbox;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 * @author lsd
 * @date 2017/10/24
 */

public abstract class FlexBoxAdapter<T extends FlexBoxViewHolder> {

    public abstract T onCreateViewHolder(ViewGroup parent);

    public abstract void onLayoutView(View itemView, int spanCount, int position);

    public abstract void onBindViewHolder(T holder, int position);

    public abstract int getCount();

    private final DataSetObservable mDataSetObservable = new DataSetObservable();

    public void registerDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    public void unregisterDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }

    public void notifyDataSetChanged() {
        mDataSetObservable.notifyChanged();
    }
}
