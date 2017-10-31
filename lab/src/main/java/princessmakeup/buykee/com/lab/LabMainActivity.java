package princessmakeup.buykee.com.lab;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import me.drakeet.multitype.MultiTypeAdapter;
import princessmakeup.buykee.com.common.base.BaseActivity;
import princessmakeup.buykee.com.common.utils.ActivityUtils;
import princessmakeup.buykee.com.common.utils.OnRecyclerItemClickListener;
import princessmakeup.buykee.com.common.utils.StringUtils;
import princessmakeup.buykee.com.lab.view.layoutManager.ScrollSpeedLinearLayoutManager;
import princessmakeup.buykee.com.lab.view.provider.TextProvider;

public class LabMainActivity extends BaseActivity {

    private EditText mPositionEt;
    private Button mScrollBtn;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MultiTypeAdapter mAdapter;
    private List<String> mData;
    boolean isCanBackPressed = false;

    @Override
    public int getLayoutId() {
        return R.layout.lab_activity_main;
    }

    @Override
    public void initData() {
        mData = new ArrayList<>();
        mData.add("自定义Drawable");
        mData.add("Transition");
        mData.add("Canvas");
        for (int i = 0; i < 250; i++) {
            mData.add(i + "");
        }
        mAdapter = new MultiTypeAdapter(mData);
        mAdapter.register(String.class, new TextProvider());
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mPositionEt = (EditText) findViewById(R.id.position_et);
        mScrollBtn = (Button) findViewById(R.id.scroll_btn);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new ScrollSpeedLinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        mScrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mPositionEt.getText().toString().trim();
                int position = StringUtils.toInt(content, 0);
                mLayoutManager.scrollToPositionWithOffset(position, 0);
            }
        });

        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(
                mRecyclerView,
                new OnRecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (position == 0) {
                            ActivityUtils.startActivity(LabMainActivity.this, LabDrawableActivity.class);
                        } else if (position == 1) {
                            ActivityUtils.startActivity(LabMainActivity.this, LabTransitionActivity.class);
                        } else if (position == 2) {
                            ActivityUtils.startActivity(LabMainActivity.this, LabCanvasActivity.class);
                        }
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                }
        ));
    }

    @Override
    public void onBackPressed() {
        if (isCanBackPressed) {
            isCanBackPressed = true;
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(value = {R2.id.scroll_btn})
    void onScrollClick(View view){

    }
}
