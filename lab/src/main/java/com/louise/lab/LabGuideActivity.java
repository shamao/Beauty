package com.louise.lab;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.louise.base.base.BaseActivity;
import com.louise.lab.adapter.LabGuideAdapter;
import com.louise.lab.view.ParallaxTransformer;

import butterknife.BindView;

/**
 * @author lsd
 * @date 2018/2/8.
 */
@Route(path = "/lab/guide")
public class LabGuideActivity extends BaseActivity {
    @BindView(R2.id.lab_pager)
    ViewPager mViewPager;
    private LabGuideAdapter mAdapter;

    @Override
    public void initContentLayout() {
        setContentView( R.layout.lab_ac_guide);
    }

    @Override
    public void initData() {
        mAdapter = new LabGuideAdapter();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageTransformer(false, new ParallaxTransformer());
    }
}