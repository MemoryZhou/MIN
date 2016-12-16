package com.simpleway.min.view;

import android.os.Bundle;
import android.view.View;

import com.simpleway.min.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AbsActionbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.AppTheme_other);//必须在setContentView 之前调用有效
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setTitle("敏敏");

    }


    @OnClick({R.id.svg,R.id.drawable})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.svg:
                StartActivity(SvgActivity.class);
                break;
            case R.id.drawable:
                StartActivity(DrawableActivity.class);
                break;
        }
    }


}
