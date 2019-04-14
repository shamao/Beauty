package cc.hiy.baseui.titlebar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import cc.hiy.baseui.R;
import cc.hiy.baseui.titlebar.provider.IViewProvider;

/**
 * 标题栏
 * <p>
 * 关于字体大小问题  xml 接受dp值， 外部传入也是dp值  get方法返回的是px
 *
 * @author lsd
 */
public abstract class UIBaseTitleBar extends RelativeLayout implements IUITitleBar {
    private static final String S_TAG = "UITitleBar";
    IProviderPackager mIProviderPackager;
    /**
     * 全局
     */
    private float mViewAlpha = 1.0f;
    /**
     * 左 View
     */
    protected IViewProvider mLeftViewProvider;

    /**
     * 右 View
     */
    protected IViewProvider mRightViewProvider;

    /**
     * 中 View
     */
    protected IViewProvider mCenterViewProvider;

    /**
     * 下 View
     */
    protected IViewProvider mBottomViewProvider;


    public UIBaseTitleBar(Context context) {
        this(context, null);

    }

    public UIBaseTitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UIBaseTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
        initView();
    }

    private void initAttr(AttributeSet attrs) {
        try {
            @SuppressLint("CustomViewStyleable") final TypedArray a =
                    getContext().obtainStyledAttributes(attrs, R.styleable.UITitleBar);
            mViewAlpha = a.getFloat(R.styleable.UITitleBar_bar_bg_alpha, 1.0f);
            a.recycle();
        } catch (Exception e) {

        }
    }


    /**
     * 初始化入口
     */
    private void initView() {
        setViewAlpha(mViewAlpha);
    }

    public void setViewAlpha(float alpha) {
        mViewAlpha = alpha;

        Drawable drawable = getBackground();
        if (drawable != null) {
            drawable.setAlpha((int) (alpha * 255));
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // 中 View
    ///////////////////////////////////////////////////////////////////////////

    private void addCenterView() {
        if (mCenterViewProvider == null) {
            return;
        }
        View view = mCenterViewProvider.view;
        view.setId(R.id.title_bar_middle_view);
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        if (lp == null) {
            lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        lp.addRule(RelativeLayout.CENTER_IN_PARENT, 1);
        checkChildParent(view);
        addView(view, lp);
    }

    ///////////////////////////////////////////////////////////////////////////
    // 左 View
    ///////////////////////////////////////////////////////////////////////////

    private void addLeftView() {
        if (mLeftViewProvider == null) {
            return;
        }
        View view = mLeftViewProvider.view;
        view.setId(R.id.title_bar_left_view);
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        if (lp == null) {
            lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 1);
        checkChildParent(view);
        addView(view, lp);
    }


    ///////////////////////////////////////////////////////////////////////////
    // 右 View
    ///////////////////////////////////////////////////////////////////////////
    private void addRightView() {
        if (mRightViewProvider == null) {
            return;
        }

        View view = mRightViewProvider.view;
        view.setId(R.id.title_bar_right_view);

        LayoutParams lp = (LayoutParams) mRightViewProvider.view.getLayoutParams();
        if (lp == null) {
            lp = new LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
        }

        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 1);
        checkChildParent(view);
        addView(mRightViewProvider.view, lp);
    }

    ///////////////////////////////////////////////////////////////////////////
    // 下 View
    ///////////////////////////////////////////////////////////////////////////
    private void addBottomView() {
        if (mBottomViewProvider == null) {
            return;
        }

        mBottomViewProvider.view.setId(R.id.title_bar_bottom_view);
        LayoutParams lp = (LayoutParams) mBottomViewProvider.view.getLayoutParams();
        if (lp == null) {
            lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
        addView(mBottomViewProvider.view, lp);
    }
//
//    private IViewProvider onCreateViewHolder(int type) {
//        if (mIProviderPackager == null) {
//            mIProviderPackager = new IProviderPackager();
//        }
//
//        return mIProviderPackager.createProvider(getContext(), type);
//    }

    public void updateLeftViewProvider(IViewProvider viewProvider) {
        if (mLeftViewProvider != null) {
            checkChildParent(mLeftViewProvider.view);
        }
        mLeftViewProvider = viewProvider;
        addLeftView();
    }

    public void updateRightViewProvider(IViewProvider viewProvider) {
        if (mRightViewProvider != null) {
            checkChildParent(mRightViewProvider.view);
        }
        mRightViewProvider = viewProvider;
        addRightView();
    }

    public void updateCenterViewProvider(IViewProvider viewProvider) {
        if (mCenterViewProvider != null) {
            checkChildParent(mCenterViewProvider.view);
        }
        mCenterViewProvider = viewProvider;
        addCenterView();
    }

    public void updateBottomViewProvider(IViewProvider viewProvider) {
        if (mBottomViewProvider != null) {
            checkChildParent(mBottomViewProvider.view);
        }
        mBottomViewProvider = viewProvider;
        addBottomView();
    }

    public void checkChildParent(View view) {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }


    public IViewProvider getLeftViewHolder() {
        return mLeftViewProvider;
    }

    public IViewProvider getRightViewHolder() {
        return mRightViewProvider;
    }

    public IViewProvider getMiddleViewHolder() {
        return mCenterViewProvider;
    }

    public IViewProvider getBottomViewHolder() {
        return mBottomViewProvider;
    }
}