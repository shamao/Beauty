package cc.hiy.baseui.titlebar.provider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.TextView;

/**
 * @author lsd
 * @date 2019/4/5.
 */
@SuppressLint("AppCompatCustomView")
public class TitleViewProvider extends IViewProvider<TextView> {

    public TitleViewProvider(Context context) {
        super(context);
    }

    @Override
    TextView onCreateView(Context context) {
        TextView tv = new TextView(context);
        tv.setTextSize(16);
        tv.setTextColor(Color.RED);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }



    public void setText(CharSequence charSequence) {
        view.setText(charSequence);
    }

    public void setDraw(Drawable left, Drawable right) {
        view.setCompoundDrawablesRelative(left, null, right, null);
    }

    public void setTextSize(int dp) {
        view.setTextSize(dp);
    }

    public void setTextColor(int color) {
        view.setTextColor(color);
    }
}